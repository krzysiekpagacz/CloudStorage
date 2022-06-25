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
	public List<User> findAllUsers();
	
	@Select("select * from USERS where username=#{userName}")
	public User findUserByUserName(String userName);
	
	@Select("select * from USERS where userid=#{userId}")
	public User findUserById(int userId);
	
	@Insert("insert into USERS(username, salt, password, firstname, lastname) values(#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	public int insertUser(User user);

}
