package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeCotroller {

	private NoteService noteService;
	private UserService userService;
	private CredentialService credentialService;


	public HomeCotroller(NoteService noteService, UserService userService, CredentialService credentialService) {
		this.noteService = noteService;
		this.userService = userService;
		this.credentialService = credentialService;
	}


	@GetMapping
	public String homeView(Authentication auth, Model model) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		Integer userId = userService.getUser(auth.getName()).getUserId();
		model.addAttribute("notes", noteService.getUserNotes(userId));
		model.addAttribute("credentials", credentialService.getUserCredentials(userId));
		return "home";
	}

}
