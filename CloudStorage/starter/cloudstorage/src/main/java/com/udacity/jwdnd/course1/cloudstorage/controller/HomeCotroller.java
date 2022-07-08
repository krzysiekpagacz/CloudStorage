package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeCotroller {

	private NoteService noteService;
	private UserService userService;
	private CredentialService credentialService;
	private FileService fileService;
	private EncryptionService encryptionService;


	public HomeCotroller(NoteService noteService
			, UserService userService
			, CredentialService credentialService
			, FileService fileService
			, EncryptionService encryptionService) {
		this.noteService = noteService;
		this.userService = userService;
		this.credentialService = credentialService;
		this.fileService = fileService;
		this.encryptionService = encryptionService;
	}


	@GetMapping
	public String homeView(Authentication auth, Model model) {
		Integer userId = userService.getUser(auth.getName()).getUserId();
		model.addAttribute("notes", noteService.getUserNotes(userId));
		model.addAttribute("encryptionService", this.encryptionService);
		model.addAttribute("credentials", credentialService.getUserCredentials(userId));
		model.addAttribute("files", fileService.getUserFiles(userId));
		return "home";
	}

}
