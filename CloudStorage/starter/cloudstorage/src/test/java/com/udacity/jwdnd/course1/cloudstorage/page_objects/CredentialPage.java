package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
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
	
	public void createNewCredential(String url, String userName, String password, JavascriptExecutor jsDriver) {
		jsDriver.executeScript("arguments[0].click();", this.newCredentialButton);
		jsDriver.executeScript("arguments[0].click();", this.urlField);
		jsDriver.executeScript("arguments[0].value='" + url + "';", this.urlField);
		jsDriver.executeScript("arguments[0].click();", this.userNameField);
		jsDriver.executeScript("arguments[0].value='" + userName + "';", this.userNameField);
		jsDriver.executeScript("arguments[0].click();", this.passwordField);
		jsDriver.executeScript("arguments[0].value='" + password + "';", this.passwordField);
		jsDriver.executeScript("arguments[0].click();", this.saveButton);
		Assertions.assertTrue(this.driver.getPageSource().contains("Credential has been created!"));
		jsDriver.executeScript("arguments[0].click();", this.homePage);
	}
	
	public void goToEditCredential() {
		this.editCredentialButton.click();
	}
	
	public void updateUrlForFirstCredential(String newUrl, JavascriptExecutor jsDriver) {
		jsDriver.executeScript("arguments[0].click();", this.urlField);
		jsDriver.executeScript("arguments[0].value='';", this.urlField);
		jsDriver.executeScript("arguments[0].value='" + newUrl + "';", this.urlField);
		jsDriver.executeScript("arguments[0].click();", this.saveButton);
		Assertions.assertTrue(this.driver.getPageSource().contains("Credential has been updated!"));
		jsDriver.executeScript("arguments[0].click();", this.homePage);
	}
	
	public void deleteCredential(JavascriptExecutor jsDriver) {
		jsDriver.executeScript("arguments[0].click();", this.deleteCredentialButton);
		Assertions.assertTrue(this.driver.getPageSource().contains("Credential has been deleted!"));
		jsDriver.executeScript("arguments[0].click();", this.homePage);
	}
	
	public void closeCredentialModal() {
		this.closeModalCredentialButton.click();
	}

}
