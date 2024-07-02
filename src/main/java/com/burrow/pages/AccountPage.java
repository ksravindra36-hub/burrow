package com.burrow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	WebDriver driver;
	public AccountPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@data-testid='desktopNav']//div[text()='Memorial Day Sale']")
	private WebElement tabMemorialDaySale;
	
	@FindBy(xpath="//div[@data-testid='desktopNav']//div[contains(@class,'hidden-mobile')]//span[text()='Seating']")
	private WebElement tabSeating;
	
	@FindBy(xpath="(//a[text()='Sofas'])[1]")
	private WebElement lnkSofas_seating;
	
	
	public void goToMemorialDaySale() {
		tabMemorialDaySale.click();
	}
	
	public void clickSeating() {
		tabSeating.click();
	}
	
	public void clickLinkSofasInSeating() {
		lnkSofas_seating.click();
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl().trim();
	}
	
}
