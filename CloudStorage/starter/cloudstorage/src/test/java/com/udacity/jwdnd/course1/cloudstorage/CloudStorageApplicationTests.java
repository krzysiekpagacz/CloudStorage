package com.udacity.jwdnd.course1.cloudstorage;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.udacity.jwdnd.course1.cloudstorage.page_objects.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.page_objects.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.params.EncryptionServiceParameter;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;

import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(EncryptionServiceParameter.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private WebDriverWait webDriverWait;
	private JavascriptExecutor jsDriver;
	private NotePage notePage;
	private CredentialPage credentialPage;
	private String firstName = "Krzysztof";
	private String lastName = "Pagacz";
	private String user = "test user";
	private String password = "superSecretPassword_";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach(EncryptionService encryptionService) {
		this.driver = new ChromeDriver();
		this.webDriverWait = new WebDriverWait(driver, 2);
		this.jsDriver = (JavascriptExecutor) this.driver;
		this.notePage = new NotePage(this.driver);
		this.credentialPage = new CredentialPage(this.driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method. Helper method for Udacity-supplied sanity
	 * checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password) {
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/*
		 * Check that the sign up was successful. // You may have to modify the element
		 * "success-msg" and the sign-up // success message below depening on the rest
		 * of your code.
		 */
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method. Helper method for Udacity-supplied sanity
	 * checks.
	 **/
	private void doLogIn(String userName, String password) {
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	private void doLogOut() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-button")));
		WebElement logout = driver.findElement(By.id("logout-button"));
		logout.click();
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code. This test is provided by Udacity to perform some basic
	 * sanity testing of your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling redirecting
	 * users back to the login page after a succesful sign up. Read more about the
	 * requirement in the rubric: https://review.udacity.com/#!/rubrics/2724/view
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp(this.firstName, this.lastName, this.user, this.password);

		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code. This test is provided by Udacity to perform some basic
	 * sanity testing of your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at:
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		doMockSignUp(this.firstName, this.lastName, this.user, this.password);
		doLogIn(this.user, this.password);

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code. This test is provided by Udacity to perform some basic
	 * sanity testing of your code to ensure that it meets certain rubric criteria.
	 * 
	 * If this test is failing, please ensure that you are handling uploading large
	 * files (>1MB), gracefully in your code.
	 * 
	 * Read more about file size limits here:
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload
	 * Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		doMockSignUp(this.firstName, this.lastName, this.user, this.password);
		doLogIn(this.user, this.password);

		// Try to upload an arbitrary large file
		String fileName = "upload5m.zip";

		this.webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 ??? Forbidden"));

	}

	@Test
	public void unauthorizedUserAccess() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertTrue(driver.getCurrentUrl().contains("login"));

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertTrue(driver.getCurrentUrl().contains("signup"));

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertFalse(driver.getPageSource().contains("home"));
	}

	@Test
	public void testNotesHandling() {
		String noteTitle = "New test note";
		String noteDescription = "Description for the note";
		String newTitle = "2nd note";
		String newDescription = "Descriptin for 2nd note";

		doMockSignUp(this.firstName, this.lastName, this.user, this.password);
		doLogIn(this.user, this.password);
		this.webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-notes-tab"))));

		// creates a note, and verifies it is displayed.
		notePage.goToNotesTab();
		notePage.createNewNote(noteTitle, noteDescription, this.jsDriver);
		notePage.goToNotesTab();
		Assertions.assertEquals(noteTitle, driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/th")).getAttribute("innerHTML"));
		Assertions.assertEquals(noteDescription, driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr[1]/td[2]")).getAttribute("innerHTML"));
		Assertions.assertEquals(1, notePage.getNumberOfNotes());

		doLogOut();
		// edits an existing note and verifies that the changes are displayed.
		doLogIn(this.user, this.password);
		notePage.goToNotesTab();;
		notePage.editNote(newTitle, newDescription, this.jsDriver);
		notePage.goToNotesTab();
		Assertions.assertEquals(newTitle, driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr/th")).getAttribute("innerHTML"));
		Assertions.assertEquals(newDescription, driver.findElement(By.xpath("//*[@id=\"userTable\"]/tbody/tr[1]/td[2]")).getAttribute("innerHTML"));

		// deletes a note and verifies that the note is no longer displayed
		notePage.deleteNote(this.jsDriver);
		notePage.goToNotesTab();

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			Assertions.assertEquals(notePage.getTitle(), newTitle);
			Assertions.assertEquals(notePage.getDescription(), newDescription);
		});
	}

	@Test
	public void testCredentialsHandling(EncryptionService encryptionService) {
		String[] urls = { "www.google.de", "www.amazon.de", "www.gmail.com" };
		String userName = "Krzysztof Pagacz";
		String password = "pass_for_";
		String newUrl = "cloudstorage.com";

		doMockSignUp(this.firstName, this.lastName, this.user, this.password);
		doLogIn(this.user, this.password);
		this.webDriverWait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-credentials-tab"))));

		// create credentials
		for (int i = 0; i < urls.length; i++) {
			String url = urls[i];
			String thisPassword = password + url;
			credentialPage.goToCredentialTab();
			credentialPage.createNewCredential(url, userName, thisPassword, this.jsDriver);
			credentialPage.goToCredentialTab();
		}

		Assertions.assertEquals(urls[0],
				driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/th")).getAttribute("innerHTML"));
		Assertions.assertEquals(urls[1],
				driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[2]/th")).getAttribute("innerHTML"));
		Assertions.assertEquals(urls[2],
				driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[3]/th")).getAttribute("innerHTML"));

		Assertions.assertEquals(userName, driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[1]/td[2]"))
				.getAttribute("innerHTML"));
		Assertions.assertEquals(userName, driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[2]/td[2]"))
				.getAttribute("innerHTML"));
		Assertions.assertEquals(userName, driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[3]/td[2]"))
				.getAttribute("innerHTML"));

		Assertions.assertNotEquals(password + urls[0], driver
				.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[1]/td[3]")).getAttribute("innerHTML"));
		Assertions.assertNotEquals(password + urls[1], driver
				.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[2]/td[3]")).getAttribute("innerHTML"));
		Assertions.assertNotEquals(password + urls[2], driver
				.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[3]/td[3]")).getAttribute("innerHTML"));

		doLogOut();

		// edit credentials
		doLogIn(this.user, this.password);
		credentialPage.goToCredentialTab();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("credentials-edit-button")));
		credentialPage.goToEditCredential();
		credentialPage.updateUrlForFirstCredential(newUrl, this.jsDriver);
		credentialPage.goToCredentialTab();
		Assertions.assertEquals(newUrl,
				driver.findElement(By.xpath("//*[@id=\"credentialTable\"]/tbody/tr[1]/th")).getAttribute("innerHTML"));

		// delete credentials
		credentialPage.deleteCredential(this.jsDriver);
		credentialPage.goToCredentialTab();
		credentialPage.deleteCredential(this.jsDriver);
		credentialPage.goToCredentialTab();
		credentialPage.deleteCredential(this.jsDriver);
		credentialPage.goToCredentialTab();

		Assertions.assertThrows(AssertionFailedError.class, () -> {
			Assertions.assertEquals(credentialPage.getUrlField(), urls[0]);
		});

	}

}
