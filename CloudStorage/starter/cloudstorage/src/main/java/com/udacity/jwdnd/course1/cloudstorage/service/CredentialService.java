package com.udacity.jwdnd.course1.cloudstorage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Service
public class CredentialService {
	
	private CredentialMapper credMapper;
	private EncryptionService encryptionService;
	
	public CredentialService(CredentialMapper credMapper, EncryptionService encryptionService) {
		this.credMapper = credMapper;
		this.encryptionService = encryptionService;
	}

	public List<Credential> getUserCredentials(Integer userId) {
		return credMapper.getUserCredentials(userId);
	}
	
	public int createOrUpdateCredentials(CredentialForm credForm, String option) {
		int entryId = 0;
		String key = credForm.getKey();
		String password = encryptionService.encryptValue(credForm.getPassword(), key);
		
		Credential credential = new Credential();
		credential.setCredentialId(credForm.getCredentialId());
		credential.setUrl(credForm.getUrl());
		credential.setUserName(credForm.getUserName());
		credential.setUserId(credForm.getUserId());
		credential.setPassword(password);
		credential.setKey(key);
		
		if (option.equals("create")) {
			entryId = this.credMapper.createCredential(credential);
		} else if (option.equals("update")) {
			entryId = this.credMapper.updateCredential(credential);
		}
		return entryId;
	}

	public void deleteCredential(Integer credentialId) {
		this.credMapper.deleteCredential(credentialId);
	}
	
	public String getKeyForUrlEntry(String password) {
		return this.credMapper.getKeyForUrlEntry(password);
	}

}
