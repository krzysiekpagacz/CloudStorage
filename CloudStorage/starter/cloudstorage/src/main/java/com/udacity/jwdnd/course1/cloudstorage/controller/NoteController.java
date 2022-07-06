package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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
		
		if (noteForm.getNoteId() == null) {			
			try {
				noteService.createNote(noteForm);
				model.addAttribute("isSuccess", true);
				model.addAttribute("successMsg", "Note has been created!");
			} catch (Exception e) {
				model.addAttribute("isError", true);
				model.addAttribute("errorMsg", "An error occured during creation of a Note.");
			}
		} else {
			noteService.updateNote(noteForm);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Note has been updated!");
		}
		
		model.addAttribute("notes", this.noteService.getAllNotes());
		
		return "result";
	}
	
	@GetMapping("/{noteId}/delete")
	public String deleteNote(@PathVariable Integer noteId, Model model) {
		try {
			noteService.deleteNote(noteId);
			model.addAttribute("isSuccess", true);
			model.addAttribute("successMsg", "Note has been deleted!");
		} catch(Exception e) {
			model.addAttribute("isError", true);
			model.addAttribute("errorMsg", "An error occured during Note deletion.");
		}
		return "result";
	}

}
