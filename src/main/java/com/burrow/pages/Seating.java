package com.burrow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Seating {
	WebDriver driver;
	public Seating(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[text()='Modular Sofas']")
	private WebElement modularSofasHeading;
	
	public String getModularSofasHeading() {
		System.out.println("=== title " + modularSofasHeading.getText());
		return modularSofasHeading.getText();
	}
	
}	
