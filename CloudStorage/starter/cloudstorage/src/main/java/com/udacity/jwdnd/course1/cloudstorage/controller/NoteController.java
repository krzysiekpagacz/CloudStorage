package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	private NoteService noteService;
	private UserService userService;
	
	public NoteController(NoteService noteService, UserService userService) {
		this.noteService = noteService;
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public String addNewNote(@ModelAttribute NoteForm noteForm, Authentication auth, Model model) {
		String userName = auth.getName();
		noteForm.setUserId(userService.getUser(userName).getUserId());
		try {			
			noteService.createNote(noteForm);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Note has been created!");
		} catch (Exception e){
			model.addAttribute("isError", true);
			model.addAttribute("errorMsg", "An error occured during crefation of a Note.");
		}
		model.addAttribute("notes", this.noteService.getAllNotes());
		return "result";
	}
	
	@PutMapping("/update")
	public String updateNote(@ModelAttribute NoteForm noteForm, Authentication auth, Model model) {
		String userName = auth.getName();
		noteForm.setUserId(userService.getUser(userName).getUserId());
		try {
			noteService.updateNote(noteForm);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Note has been updated!");
		} catch (Exception e) {
			model.addAttribute("isError", true);
			model.addAttribute("errorMsg", "Note has not been updated. An error occured!");
		}
		return "result";
	}

}
