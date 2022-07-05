package com.udacity.jwdnd.course1.cloudstorage.controller;

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
	public String createCredential(@ModelAttribute CredentialForm credForm, Authentication auth, Model model) {
		String userName = auth.getName();
		Integer userId = userService.getUser(userName).getUserId();
		System.out.println("***********************************");
		System.out.println(userId);
		credForm.setUserId(userId);
		
		if (credForm.getCredentialId() == null) {
			System.out.println("***********************************");
			System.out.println(credForm.getUserId());
			credentialService.createCredential(credForm);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Credential has been created!");
//			model.addAttribute("createCredential", true);
		} else {
			System.out.println("***********************************");
			System.out.println(credForm.getUserId());
			credentialService.updateCredentials(credForm);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Credential has been updated!");
		}
		
		model.addAttribute("credentials", this.credentialService.getUserCredentials());
		
		return "result";
	}
	
	@GetMapping("/{credentialId}/delete")
	public String deleteCredential(@PathVariable Integer credentialId, Model model) {
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

}
