package com.sbms.batch.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileProcessingService {

	Boolean upalodAndProcessData(MultipartFile file);
	
}
