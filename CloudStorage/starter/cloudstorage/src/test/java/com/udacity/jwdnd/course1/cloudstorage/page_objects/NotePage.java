package com.udacity.jwdnd.course1.cloudstorage.page_objects;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {
	
	private WebDriver driver;

	@FindBy(id = "nav-notes-tab")
	private WebElement notesTab;
	
	@FindBy(id = "add-new-note-button")
	private WebElement newNoteButton;
	
	@FindBy(id = "note-title")
	private WebElement noteTitleField;
	
	@FindBy(id = "note-description")
	private WebElement noteDescriptionField;
	
	@FindBy(id = "note-save-changes")
	private WebElement saveButton;
	
	@FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/th")
	private WebElement titleField;
	
	@FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[2]")
	private WebElement descriptionField;
	
	@FindBy(id = "home-page")
	private WebElement homePage;
	
	@FindBy(id = "note-edit-button")
	private WebElement noteEditButton;
	
	@FindBy(id = "note-delete-button")
	private WebElement noteDeleteButton;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div[1]/table/tbody/tr/th")
	private List<WebElement> notesList;
	
	public NotePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void goToNotesTab() {
		this.notesTab.click();
	}
	
	public void createNewNote(String title, String description, JavascriptExecutor jsDriver) {
		jsDriver.executeScript("arguments[0].click();", this.newNoteButton);
		jsDriver.executeScript("arguments[0].click();", this.noteTitleField);
		jsDriver.executeScript("arguments[0].value='" + title +"';", this.noteTitleField);
		jsDriver.executeScript("arguments[0].click();", this.noteDescriptionField);
		jsDriver.executeScript("arguments[0].value='" + description +"';", this.noteDescriptionField);
		jsDriver.executeScript("arguments[0].click();", this.saveButton);
		Assertions.assertTrue(this.driver.getPageSource().contains("Note has been created!"));
		jsDriver.executeScript("arguments[0].click();", this.homePage);
	}
	
	public int getNumberOfNotes() {
		return this.notesList.size();
	}
	
	public String getTitle() {
		return this.titleField.getText();
	}
	
	public String getDescription() {
		return this.descriptionField.getText();
	}
	
	public void goToHomePage() {
		this.homePage.click();
	}
	
	public void editNote(String newTitle, String newDescription, JavascriptExecutor jsDriver) {
		jsDriver.executeScript("arguments[0].click();", this.noteEditButton);
		jsDriver.executeScript("arguments[0].click();", this.noteTitleField);
		jsDriver.executeScript("arguments[0].value='';", this.noteTitleField);
		jsDriver.executeScript("arguments[0].value='" + newTitle +"';", this.noteTitleField);
		jsDriver.executeScript("arguments[0].click();", this.noteDescriptionField);
		jsDriver.executeScript("arguments[0].value='';", this.noteDescriptionField);
		jsDriver.executeScript("arguments[0].value='" + newDescription +"';", this.noteDescriptionField);
		jsDriver.executeScript("arguments[0].click();", this.saveButton);
		Assertions.assertTrue(this.driver.getPageSource().contains("Note has been updated!"));
		jsDriver.executeScript("arguments[0].click();", this.homePage);
	}
	
	public void deleteNote(JavascriptExecutor jsDriver) {
		jsDriver.executeScript("arguments[0].click();", this.noteDeleteButton);
		Assertions.assertTrue(this.driver.getPageSource().contains("Note has been deleted!"));
		jsDriver.executeScript("arguments[0].click();", this.homePage);
	}
}
