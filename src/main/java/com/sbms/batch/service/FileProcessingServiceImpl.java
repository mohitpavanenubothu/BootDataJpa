package com.sbms.batch.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbms.batch.entity.BankDetails;
import com.sbms.batch.entity.Person;
import com.sbms.batch.entity.PolicyDetails;
import com.sbms.batch.repo.BankDetailsRepo;
import com.sbms.batch.repo.PersonRepo;
import com.sbms.batch.repo.PolicyRepo;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

@Service
public class FileProcessingServiceImpl implements FileProcessingService {

	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private BankDetailsRepo bankDetailsRepo;

	@Autowired
	private PolicyRepo policyRepo;

	@Override
	public Boolean upalodAndProcessData(MultipartFile file) {

		try {
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withTrim());

			List<String> headers = csvParser.getHeaderNames();

			ColumnPositionMappingStrategy<PolicyDetails> strategy1 = new ColumnPositionMappingStrategy<>();
			strategy1.setType(PolicyDetails.class);
			strategy1.setColumnMapping(headers.toArray(new String[headers.size()]));

			ColumnPositionMappingStrategy<Person> strategy2 = new ColumnPositionMappingStrategy<>();
			strategy2.setType(Person.class);
			strategy2.setColumnMapping(headers.toArray(new String[headers.size()]));

			ColumnPositionMappingStrategy<BankDetails> strategy3 = new ColumnPositionMappingStrategy<>();
			strategy3.setType(BankDetails.class);
			strategy3.setColumnMapping(headers.toArray(new String[headers.size()]));

			// Reader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader1 = new CSVReader(new InputStreamReader(file.getInputStream()));
			CSVReader csvReader2 = new CSVReader(new InputStreamReader(file.getInputStream()));
			CSVReader csvReader3 = new CSVReader(new InputStreamReader(file.getInputStream()));
			csvReader1.readNext();
			csvReader2.readNext();
			csvReader3.readNext();

			CsvToBean<PolicyDetails> csv1 = new CsvToBean<>();
			CsvToBean<Person> csv2 = new CsvToBean<>();
			CsvToBean<BankDetails> csv3 = new CsvToBean<>();

			List<?> Datalist1 = csv1.parse(strategy1, csvReader1);
			List<?> Datalist2 = csv2.parse(strategy2, csvReader2);
			List<?> Datalist3 = csv3.parse(strategy3, csvReader3);

			List<PolicyDetails> list1 = new LinkedList<>();
			List<Person> list2 = new LinkedList<>();
			List<BankDetails> list3 = new LinkedList<>();

			for (Object object : Datalist1) {
				PolicyDetails student = (PolicyDetails) object;
				list1.add(student);
			}

			for (Object object : Datalist2) {
				Person student = (Person) object;
				list2.add(student);
			}

			for (Object object : Datalist3) {
				BankDetails student = (BankDetails) object;
				list3.add(student);
			}

			policyRepo.saveAll(list1);
			personRepo.saveAll(list2);
			bankDetailsRepo.saveAll(list3);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
