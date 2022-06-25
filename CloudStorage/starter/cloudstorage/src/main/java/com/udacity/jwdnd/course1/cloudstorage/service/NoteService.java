package com.udacity.jwdnd.course1.cloudstorage.service;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Service
public class NoteService {
	
	private NoteMapper noteMapper;
	private UserMapper userMapper;

	public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
		this.noteMapper = noteMapper;
		this.userMapper = userMapper;
	}
	
	public int createNote(Note note) {
		return noteMapper.insertNote(new Note(null, note.getNoteTitle(), note.getNoteDescription()));
	}
	

}
