package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {
	
	private WebDriver driver;
	
	@FindBy(id = "nav-credentials-tab")
	private WebElement credentialTab;
	
	@FindBy(id = "add-new-credential-button")
	private WebElement newCredentialButton;
	
	@FindBy(id = "credential-url")
	private WebElement urlField;
	
	@FindBy(id = "credential-username")
	private WebElement userNameField;
	
	@FindBy(id="credential-password")
	private WebElement passwordField;
	
	@FindBy(id = "credentials-save-buttoon")
	private WebElement saveButton;
	
	@FindBy(id = "home-page")
	private WebElement homePage;
	
	@FindBy(id = "credentials-edit-button")
	private WebElement editCredentialButton;
	
	@FindBy(id = "credential-delete-button")
	private WebElement deleteCredentialButton;
	
	@FindBy(id = "credential-modal-close-button")
	private WebElement closeModalCredentialButton;
	
	public CredentialPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public WebElement getUrlField() {
		return urlField;
	}

	public void setUrlField(WebElement urlField) {
		this.urlField = urlField;
	}

	public WebElement getUserNameField() {
		return userNameField;
	}

	public void setUserNameField(WebElement userNameField) {
		this.userNameField = userNameField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(WebElement passwordField) {
		this.passwordField = passwordField;
	}

	public void goToCredentialTab() {
		this.credentialTab.click();
	}
	
	public void createNewCredential(String url, String userName, String password) {
		this.newCredentialButton.click();
		this.urlField.click();
		this.urlField.sendKeys(url);
		this.userNameField.click();
		this.userNameField.sendKeys(userName);
		this.passwordField.click();
		this.passwordField.sendKeys(password);
		this.saveButton.click();
		Assertions.assertTrue(this.driver.getPageSource().contains("Credential has been created!"));
		this.homePage.click();
	}
	
	public void goToEditCredential() {
		this.editCredentialButton.click();
	}
	
	public void updateUrlForFirstCredential(String newUrl) {
		this.urlField.click();
		this.urlField.clear();
		this.urlField.sendKeys(newUrl);
		this.saveButton.click();
		Assertions.assertTrue(this.driver.getPageSource().contains("Credential has been updated!"));
		this.homePage.click();
	}
	
	public void deleteCredential() {
		this.deleteCredentialButton.click();
		Assertions.assertTrue(this.driver.getPageSource().contains("Credential has been deleted!"));
		this.homePage.click();
	}
	
	public void closeCredentialModal() {
		this.closeModalCredentialButton.click();
	}

}
