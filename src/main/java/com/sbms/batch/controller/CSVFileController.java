package com.sbms.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbms.batch.service.CSVFileService;
import com.sbms.batch.util.ResponseMessage;

@RestController
public class CSVFileController {

	@Autowired
	private CSVFileService service;

	@PostMapping(value = "/uploadAndShow")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if ("text/csv".equals(file.getContentType())) {
			try {
				service.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMessage(message, file.getContentType(), String.valueOf(file.getSize()), 200));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseMessage(message, file.getContentType(), String.valueOf(file.getSize()), 500));
			}
		}
		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseMessage(message, file.getContentType(), String.valueOf(file.getSize()), 400));
	}

}
