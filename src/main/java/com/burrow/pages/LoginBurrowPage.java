package com.burrow.pages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.burrow.base.BasePage;
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
		hardWait(3);
	}
	
	public void setUserName() {
		txtboxEmail.sendKeys("ravinskanni@gmail.com");
		ExpectedConditions.visibilityOf(txtboxEmail);
	}
	
	public void setPassword() {
		txtboxPassword.sendKeys("abcd@123EFG");
		ExpectedConditions.visibilityOf(txtboxPassword);
	}
	
	public String verifyWelcomePage() {
		return driver.getCurrentUrl();
	}
	
	By btnCloseUnlockSaleBy = By.xpath("//button[@id='button3']");
	By frameUnlockSalePopupBy = By.xpath("//iframe[@aria-label='Modal Overlay Box Frame']");
	
	public AccountPage login() {
		HomePage homePage = new HomePage(driver);
		BurrowUtilities bUtil = new BurrowUtilities(driver);
		log.info("username ;");
		log.info("password ;");
		try {
			//checkPopUpDisplayed();
			homePage.clickLogin();
			setUserName();
			setPassword();
			//checkPopUpDisplayed();
			clickContinueButton();
			//checkPopUpDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AccountPage(driver);
	}
	
	private void checkPopUpDisplayed() {
		BasePage basePage = new BasePage();
		if(basePage.fluentWait(frameUnlockSalePopupBy).isDisplayed()) {
			driver.switchTo().frame(driver.findElement(frameUnlockSalePopupBy));
			driver.findElement(btnCloseUnlockSaleBy).click();
		}
	}
	
	public void hardWait(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
