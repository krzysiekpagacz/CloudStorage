package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface NoteMapper {
	
	@Select("SELECT * FROM NOTES WHERE notetitle=#{noteTitle}")
	Note getNoteByTitle(String noteTitle);
	
	@Select("SELECT * FROM NOTES")
	List<Note> getAllNotes();
	
	@Select("SELECT * FROM NOTES WHERE userid = #{userId}")
	List<Note> getUserNotes(Integer userId);
	
	@Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insertNote(Note note);
	
	@Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
	void deleteNote(Integer noteId);
	
	@Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
	int updateNote(Note note);
	
}
