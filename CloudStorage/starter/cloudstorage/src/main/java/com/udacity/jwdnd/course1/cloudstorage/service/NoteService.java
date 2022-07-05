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
		note.setNoteId(noteForm.getNoteId());
		return noteMapper.insertNote(note);
	}

	public List<Note> getUserNotes(Integer userId) {
		return noteMapper.getUserNotes(userId);
	}

	public List<Note> getAllNotes() {
		return noteMapper.getAllNotes();
	}
	
	public int updateNote(NoteForm noteForm) {
		Note note = new Note();
		note.setUserId(noteForm.getUserId());
		note.setNoteId(noteForm.getNoteId());
		note.setNoteTitle(noteForm.getNoteTitle());
		note.setNoteDescription(noteForm.getNoteDescription());
		return noteMapper.updateNote(note);
	}

	public void deleteNote(Integer noteId) {
		noteMapper.deleteNote(noteId);
	}
}
