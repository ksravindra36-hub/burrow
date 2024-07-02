package com.burrow.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.burrow.base.BasePage;
import com.burrow.pages.AccountPage;
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
	public void loginBurrowTest() {
		LoginBurrowPage loginPage = new LoginBurrowPage(getDriver());
		log.info("username ;");
		log.info("password ;");
		AccountPage accountPage = loginPage.login();
		log.info("URL " + accountPage.getCurrentURL());
		Assert.assertTrue(accountPage.getCurrentURL().contains("login"),"Successfully logged in");
	}
}
