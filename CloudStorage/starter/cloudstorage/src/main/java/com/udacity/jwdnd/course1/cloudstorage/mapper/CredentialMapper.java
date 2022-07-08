package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Mapper
public interface CredentialMapper {

	@Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
	List<Credential> getUserCredentials(Integer userId);

	@Insert("INSERT INTO CREDENTIALS(credentialid, url, username, key, password, userid) VALUES(#{credentialId}, #{url}, #{userName}, #{key}, #{password}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "credentialId")
	Integer createCredential(Credential credential);

	@Update("UPDATE CREDENTIALS SET url=#{url}, username=#{userName}, key=#{key}, password=#{password}, userid=#{userId} WHERE credentialid=#{credentialId}")
	Integer updateCredential(Credential credential);

	@Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialId}")
	void deleteCredential(Integer credentialId);

}
