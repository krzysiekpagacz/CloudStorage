package com.udacity.jwdnd.course1.cloudstorage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Service
public class CredentialService {
	
	private CredentialMapper credMapper;

	public CredentialService(CredentialMapper credMapper) {
		this.credMapper = credMapper;
	}
	
	public Integer createCredential(CredentialForm credForm) {
		Credential credential = new Credential();
		credential.setCredentialId(credForm.getCredentialId());
		credential.setUrl(credForm.getUrl());
		credential.setPassword(credForm.getPassword());
		credential.setUserName(credForm.getUserName());
		credential.setUserId(credForm.getUserId());
		credential.setKey("dummy key");
		return credMapper.createCredential(credential);
	}

	public List<Credential> getUserCredentials(Integer userId) {
		return credMapper.getUserCredentials(userId);
	}

	public int updateCredentials(CredentialForm credForm) {
		Credential credential = new Credential();
		credential.setCredentialId(credForm.getCredentialId());
		credential.setUrl(credForm.getUrl());
		credential.setPassword(credForm.getPassword());
		credential.setUserName(credForm.getUserName());
		credential.setUserId(credForm.getUserId());
		credential.setKey("dummy key");
		return credMapper.updateCredential(credential);
	}

	public void deleteCredential(Integer credentialId) {
		credMapper.deleteCredential(credentialId);
	}

}
