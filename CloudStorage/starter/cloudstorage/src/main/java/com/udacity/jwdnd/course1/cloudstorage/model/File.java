package com.udacity.jwdnd.course1.cloudstorage.model;

import java.nio.ByteBuffer;

public class File {
	
	private Integer fileId;
	private String fileName;
	private String contentType;
	private String fileSize;
	private ByteBuffer fileData;
	
	public File(Integer fileId, String fileName, String contentType, String fileSize, ByteBuffer fileData) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.fileData = fileData;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public ByteBuffer getFileData() {
		return fileData;
	}

	public void setFileData(ByteBuffer fileData) {
		this.fileData = fileData;
	}
	
}
