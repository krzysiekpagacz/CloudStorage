package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {
	
	private UserService userService;
	
	public SignupController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String signupView() {
		return "signup";
	}
	
	@PostMapping
	public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
		String signupError = null;
		
		if(!userService.isUserNameAvailable(user.getUserName())) {
			signupError = "User Name: " + user.getUserName() + " is already assigned!";
		}
		
		if(signupError == null) {
			int generatedKey = userService.createUser(user);
			if(generatedKey < 0) {
				signupError = "There was an error while creating new user. User has not been created";
			}
		}
		
		if(signupError == null) {
			redirectAttributes.addFlashAttribute("signupSuccess", "User " + user.getUserName() + " successfully created!");
		} else {
			redirectAttributes.addFlashAttribute("signupError", signupError);
		}
		
		return "redirect:/login";
	}

}
