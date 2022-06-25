package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;

@Controller
@RequestMapping("/home")
public class HomeCotroller {
	
	private NoteService noteService;
	
	public HomeCotroller(NoteService noteService) {
		this.noteService = noteService;
	}
	
	@GetMapping
	public String homeView() {
		return "home";
	}
	
	@PostMapping
	public String addNewNote(@ModelAttribute Note note, Model model) {
		noteService.createNote(note);
		return "home";
	}

}
