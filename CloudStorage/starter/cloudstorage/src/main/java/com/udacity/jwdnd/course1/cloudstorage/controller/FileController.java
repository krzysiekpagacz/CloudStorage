package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.HttpHeaders;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

@Controller
public class FileController {
	
	private FileService fileService;
	private UserService userService;
	
	public FileController(FileService fileService, UserService userService) {
		this.fileService = fileService;
		this.userService = userService;
	}

	@GetMapping("/{fileId}/delete")
	public String deleteFile(@PathVariable Integer fileId, Model model) {
		try {
			this.fileService.deleteFile(fileId);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "File has been deleted!");
		} catch(Exception e) {
			model.addAttribute("isError", true);
			model.addAttribute("errorMsg", "An error occured during File delete.");
		}
		return "result";
	}
	
	@GetMapping(value = "/{fileId}/download",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Integer fileId) {
		
		File file = fileService.getFileById(fileId);
		ByteArrayResource resource = new ByteArrayResource(file.getFileData());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getFileName() + "\"")
				.contentType(MediaType.parseMediaType(file.getContentType()))
				.body(resource);
	}
	
	@PostMapping("/file-upload")
	public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file, Authentication auth, Model model) {
		Integer userId = userService.getUser(auth.getName()).getUserId();
		List<File> userFiles = fileService.getUserFiles(userId);
		
		for(File userFile : userFiles) {
			if(userFile.getFileName().equals(file.getOriginalFilename())) {
				model.addAttribute("isError", true);
				model.addAttribute("errorMsg", "File with the name " + file.getOriginalFilename() + " already exists.");
				return "result";
			}
		}
		
		try {
			this.fileService.uploadFile(file, userId);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "File " + file.getOriginalFilename() + " has been uploaded!");
		} catch (IOException e) {
			model.addAttribute("isError", true);
			model.addAttribute("errorMsg", "An error occured during File upload.");
		}
		return "result";
	}

}
