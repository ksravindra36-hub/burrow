package com.burrow.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.burrow.utilities.BurrowUtilities;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='account-history-empty-button']/a")
	private WebElement btnGoToLogin;
	
	@FindBy(xpath="//h2[text()='Welcome back']")
	private WebElement txtWelcomBack;
	
	
	public void clickLogin() {
		btnGoToLogin.click();
		BurrowUtilities diathriveUtilities = new BurrowUtilities(driver);
		diathriveUtilities.isDisplayed(txtWelcomBack);
	}
}
