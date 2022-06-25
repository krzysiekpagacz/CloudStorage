package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from USERS")
	List<User> findAllUsers();
	
	@Select("SELECT * FROM USERS WHERE username=#{userName}")
	User findUserByUserName(String userName);
	
	@Select("SELECT * FROM USERS WHERE userid=#{userId}")
	User findUserById(int userId);
	
	@Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	int insertUser(User user);

}
