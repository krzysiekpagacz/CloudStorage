package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

@Controller
@RequestMapping("/credential")
public class CredentialController {
	
	private CredentialService credentialService;
	private UserService userService;

	public CredentialController(CredentialService credentialService, UserService userService) {
		this.credentialService = credentialService;
		this.userService = userService;
	}

	@PostMapping("/create")
	public String createCredential(@ModelAttribute Credential credential, Authentication auth, Model model) {
		String url = credential.getUrl();
		String userName = credential.getUserName();
		String key = "test";
		String pass = credential.getPassword();
		Integer userId = userService.getUser(auth.getName()).getUserId();
		credentialService.createCredential(new Credential(null, url, userName, key, pass, userId));
		model.addAttribute("isSuccess", true);
		model.addAttribute("successMsg", "Credential has been created!");
		return "result";
	}

}
