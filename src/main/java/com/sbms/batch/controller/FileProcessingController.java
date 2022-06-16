package com.sbms.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sbms.batch.service.FileProcessingService;
import com.sbms.batch.util.ResponseMessage;

@RestController
@RequestMapping("/columntofieldmapping")
public class FileProcessingController {

	@Autowired
	private FileProcessingService service;

	@PostMapping(value = "/uploadAndShow")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if ("text/csv".equals(file.getContentType())) {
			try {
				Boolean status = service.upalodAndProcessData(file);
				if (status) {
					message = "Uploaded the file successfully: " + file.getOriginalFilename();
					return ResponseEntity.status(HttpStatus.OK).body(
							new ResponseMessage(message, file.getContentType(), String.valueOf((file.getSize()/102)+" KB"), 200));

				} else {
					message = "Could not upload the file: " + file.getOriginalFilename();
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
							new ResponseMessage(message, file.getContentType(), String.valueOf(file.getSize()), 500));

				}
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseMessage(message, file.getContentType(), String.valueOf(file.getSize()), 500));
			}
		}
		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseMessage(message, file.getContentType(), String.valueOf(file.getSize()), 403));
	}
}
