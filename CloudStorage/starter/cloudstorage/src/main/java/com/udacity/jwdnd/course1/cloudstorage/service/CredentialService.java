package com.udacity.jwdnd.course1.cloudstorage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Service
public class CredentialService {
	
	private CredentialMapper credMapper;

	public CredentialService(CredentialMapper credMapper) {
		this.credMapper = credMapper;
	}
	
	public Integer createCredential(Credential credential) {
		Integer credId = credMapper.createCredential(credential);
		return credId;
	}

	public List<Credential> getUserCredentials(Integer userId) {
		return credMapper.getUserCredentials(userId);
	}

}
