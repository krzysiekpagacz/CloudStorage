package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeCotroller {

	private NoteService noteService;
	private UserService userService;

	public HomeCotroller(NoteService noteService, UserService userService) {
		this.noteService = noteService;
		this.userService = userService;
	}

	@GetMapping
	public String homeView() {
		return "home";
	}

	@PostMapping
	public String addNewNote(@ModelAttribute NoteForm noteForm, Authentication auth, Model model) {
		String userName = auth.getName();
		noteForm.setUserId(userService.getUser(userName).getUserId());
		noteService.createNote(noteForm);
		model.addAttribute("notes", this.noteService.getUserNotes());
		return "home";
	}

}
