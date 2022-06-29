package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface NoteMapper {
	
	@Select("SELECT * FROM NOTES WHERE notetitle=#{noteTitle}")
	Note getNoteByTitle(String noteTitle);
	
	@Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insertNote(Note note);
	
	@Select("SELECT * FROM NOTES")
	List<Note> getAllNotes();
	
}
