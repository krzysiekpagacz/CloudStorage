package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface NoteMapper {
	
	@Insert("INSERT INTO NOTES (notetitle, notedescription) VALUES(#{noteTitle}, #{noteDescription})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insertNote(Note note);

}
