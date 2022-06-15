package com.sbms.batch.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sbms.batch.repo.CSVFileCustomRepo;

@Service
public class CSVFileServiceImpl implements CSVFileService {

	@Autowired
	private CSVFileCustomRepo repository;

		// if nothing knows and wanted to store them into DB for usage.

	@SuppressWarnings("resource")
	@Override
	public boolean save(MultipartFile file) {
		try {

			BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
			List<String> headers = new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader().withTrim())
					.getHeaderNames();

			StringBuilder columns = new StringBuilder();
			StringBuilder columnsWithoutType = new StringBuilder();
			StringBuilder values = new StringBuilder();
			values.append("(");
			for (String s : headers) {
				columns.append(" " + s + " varchar2(250),");
				columnsWithoutType.append(s + ",");
				values.append("?,");
			}
			values = values.deleteCharAt(values.length() - 1);
			columnsWithoutType = columnsWithoutType.deleteCharAt(columnsWithoutType.length() - 1);

			values.append(")");
			String columnsWithTypes = columns.toString();
			String columnsWithoutTypeStr = columnsWithoutType.toString();

			columnsWithTypes = columnsWithTypes.substring(0, columnsWithTypes.length() - 1);
			String valuesStr = values.toString();

			String dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());

			String TableName = "CSV_FILE_TABLE_" + dtf;

			boolean tableCreationStatus = repository.createTabel(TableName, columnsWithTypes, columnsWithoutTypeStr,
					valuesStr, new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8")));

			System.out.println("*****************   " + tableCreationStatus);
			return tableCreationStatus;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

}
