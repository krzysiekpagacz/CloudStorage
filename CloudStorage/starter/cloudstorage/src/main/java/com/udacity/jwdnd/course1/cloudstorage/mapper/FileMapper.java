package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Mapper
public interface FileMapper {
	
	@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
	@Options(useGeneratedKeys = true, keyProperty = "fileId")
	public int addFile(File file);
	
	@Select("SELECT * FROM FILES")
	public List<File> getAllFiles();

	@Delete("DELETE FROM FILES WHERE fileid=#{fileId}")
	public void deleteFile(Integer fileId);

	@Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
	public File getFileById(Integer fileId);

}
