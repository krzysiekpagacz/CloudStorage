package com.udacity.jwdnd.course1.cloudstorage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.forms.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Service
public class NoteService {
	
	private NoteMapper noteMapper;

	public NoteService(NoteMapper noteMapper) {
		this.noteMapper = noteMapper;
	}
	
	public int createNote(NoteForm noteForm) {
		Note note = new Note();
		note.setNoteTitle(noteForm.getNoteTitle());
		note.setNoteDescription(noteForm.getNoteDescription());
		note.setUserId(noteForm.getUserId());
		return noteMapper.insertNote(note);
	}
	
	public List<Note> getUserNotes(){
		return noteMapper.getAllNotes();
	}
}
