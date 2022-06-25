package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.List;

public class User {
	
	private Integer userId;
	private String userName;
	private String salt;
	private String password;
	private String firstName;
	private String lastName;
	private List<Note> notes;
	private List<File> files;
	private List<Credential> credentials;
	
//	public User(Integer userId, String userName, String salt, String password, String firtName, String lastName,
//			List<Note> notes, List<File> files, List<Credential> credentials) {
//		super();
//		this.userId = userId;
//		this.userName = userName;
//		this.salt = salt;
//		this.password = password;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.notes = notes;
//		this.files = files;
//		this.credentials = credentials;
//	}

	public User(Integer userId, String userName, String salt, String password, String firstName, String lastName) {
		this.userId = userId;
		this.userName = userName;
		this.salt = salt;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<Credential> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<Credential> credentials) {
		this.credentials = credentials;
	}

}
