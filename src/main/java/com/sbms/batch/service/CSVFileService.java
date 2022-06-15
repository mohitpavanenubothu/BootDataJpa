package com.sbms.batch.service;

import org.springframework.web.multipart.MultipartFile;

public interface CSVFileService {

	public boolean save(MultipartFile file);

}
