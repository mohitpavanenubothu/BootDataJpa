package com.sbms.batch.util;

public class ResponseMessage {

	private String message;
	
	public ResponseMessage(String message, String fileType, String fileSize, Integer statusCode) {
		super();
		this.message = message;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.statusCode = statusCode;
	}

	private String fileType;
	private String fileSize;
	private Integer statusCode;

	/*
	 * public ResponseMessage(String message, String fileType, String fileSize,
	 * Integer statusCode) {
	 * 
	 * this.message = message; this.fileType = fileType; this.fileSize = fileSize;
	 * this.statusCode = statusCode; }
	 */
	
	

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
