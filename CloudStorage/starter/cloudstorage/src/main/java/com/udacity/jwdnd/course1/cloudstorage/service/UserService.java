package com.udacity.jwdnd.course1.cloudstorage.service;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class UserService {
	
	private UserMapper userMapper;
	private HashService hashService;
	
	public UserService(UserMapper userMapper, HashService hashService) {
		this.userMapper = userMapper;
		this.hashService = hashService;
	}
	
	public boolean isUserNameAvailable(String userName) {
		return userMapper.findUserByUserName(userName) == null;
	}
	
	public int createUser(User user) {
		SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insertUser(new User(null, user.getUserName(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
	}

}
