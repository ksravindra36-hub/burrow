package com.burrow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MemorialDaySale {
	WebDriver driver;
	public MemorialDaySale(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='collection-title']//h3[text()='Best Sellers']")
	private WebElement titleBestSellers;
	
	public String getTitleBestSellers() {
		System.out.println("=== title " + titleBestSellers.getText());
		return titleBestSellers.getText();
	}
	
}	
