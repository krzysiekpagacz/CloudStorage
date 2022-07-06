package com.udacity.jwdnd.course1.cloudstorage.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Service
public class FileService {

	private FileMapper fileMapper;

	public FileService(FileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}

	public int uploadFile(MultipartFile file, Integer userId) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileSize = String.valueOf(file.getSize());
		return fileMapper
				.addFile(new File(null, fileName, file.getContentType(), fileSize, userId, file.getBytes()));
	}

	public List<File> getAllFiles() {
		return fileMapper.getAllFiles();
	}

	public void deleteFile(Integer fileId) {
		fileMapper.deleteFile(fileId);
	}

	public File getFileById(Integer fileId) {
		return fileMapper.getFileById(fileId);
	}

	public List<File> getUserFiles(Integer userId) {
		return fileMapper.getUserFiles(userId);
	}

}
