package com.burrow.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.burrow.utilities.BurrowUtilities;

public class LoginBurrowPage {
	public static Logger log = Logger.getLogger(LoginBurrowPage.class);
	WebDriver driver;
	public LoginBurrowPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'sign-in active-component')]//input[@name='email']")
	WebElement txtboxEmail;
	
	@FindBy(xpath="//div[contains(@class,'sign-in active-component')]//input[@name='password']")
	WebElement txtboxPassword;
	
	@FindBy(xpath="//button[text()='Log In']")
	WebElement btnLogin;
	
	public void clickContinueButton() {
		btnLogin.click();
	}
	
	public void setUserName() {
		txtboxEmail.sendKeys("ravinskanni@gmail.com");
	}
	
	public void setPassword() {
		txtboxPassword.sendKeys("abcd@123E");
	}
	
	public String verifyWelcomePage() {
		return driver.getCurrentUrl();
	}
	
	public AccountPage login() {
		HomePage homePage = new HomePage(driver);
		log.info("username ;");
		log.info("password ;");
			try {
			homePage.clickLogin();
			Thread.sleep(5000);
			setUserName();
			Thread.sleep(5000);
			setPassword();
			clickContinueButton();
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AccountPage(driver);
	}
}
