package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialForm;
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
	public String createCredential(@ModelAttribute("credForm") CredentialForm credForm, Authentication auth, Model model) {
		Integer userId = userService.getUser(auth.getName()).getUserId();
		credForm.setUserId(userId);
		credForm.setKey(prepareEncodedKey());
		
		if (credForm.getCredentialId() == null) {
			credentialService.createOrUpdateCredentials(credForm, "create");
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Credential has been created!");
//			model.addAttribute("createCredential", true);
		} else {
			credentialService.createOrUpdateCredentials(credForm, "update");
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Credential has been updated!");
		}
		
		model.addAttribute("credentials", this.credentialService.getUserCredentials(userId));
		
		return "result";
	}
	
	@GetMapping("/{credentialId}/delete")
	public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Model model) {
		try {
			credentialService.deleteCredential(credentialId);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Credential has been deleted!");
		} catch(Exception e) {
			model.addAttribute("isError", true);
			model.addAttribute("errorMsg", "An error occured during Credential deletion.");
		}
		return "result";
	}
	
	private String prepareEncodedKey() {
		SecureRandom random = new SecureRandom();
		byte[] key = new byte[16];
		random.nextBytes(key);
		return Base64.getEncoder().encodeToString(key);
	}

}
