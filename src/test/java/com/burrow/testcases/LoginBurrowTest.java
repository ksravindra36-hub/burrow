package com.burrow.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.burrow.base.BasePage;
import com.burrow.pages.LoginBurrowPage;
import com.burrow.utilities.ExcelDataReader;


public class LoginBurrowTest extends BasePage{
	public static Logger log = Logger.getLogger(LoginBurrowTest.class);
	
	@DataProvider(name = "testData")
    public Object[][] testData() {
        return ExcelDataReader.getTestData(System.getProperty("user.dir")+"\\src\\main\\resources\\excels\\testdata.xlsx", "Sheet1");
    }
	
	//dataProvider = "testData" String username, String password
	@Test
	public void loginDiathriveTest() {
		LoginBurrowPage loginPage = new LoginBurrowPage(getDriver());
		log.info("username ;");
		log.info("password ;");
		loginPage.setUserName();
		loginPage.setPassword();
		loginPage.clickContinueButton();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(loginPage.verifyWelcomePage().contains("account"),"Successfully logged in");
	}
}
