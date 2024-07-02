package com.burrow.testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.burrow.base.BasePage;
import com.burrow.listeners.ITestListenerClass;
import com.burrow.pages.AccountPage;
import com.burrow.pages.LoginBurrowPage;
import com.burrow.pages.MemorialDaySale;
import com.burrow.pages.Seating;

//@Listeners(ITestListenerClass.class)
public class CrossBrowserTest extends BasePage{
	
	
	@Test(enabled = true, testName = "Memorial Day Sale", groups = {"smoke"})
	public void verifyMemorialDaySale() throws Exception {
		LoginBurrowPage LoginBurrowPage = new LoginBurrowPage(getDriver());
		AccountPage accountPage = LoginBurrowPage.login();
		Assert.assertTrue(accountPage.getCurrentURL().contains("account"),"Successfully logged in");
		accountPage.goToMemorialDaySale();
		
		extentTest.info("create object for Resources page");
		MemorialDaySale memorialDaySale = new MemorialDaySale(getDriver());
		extentTest.info("get the recomended title");
		Assert.assertEquals(memorialDaySale.getTitleBestSellers(), "Best Sellers", "Title has matched");
		extentTest.pass("Assertion is passed for webpage title");
	}

	@Test(enabled = false, testName = "Seating", groups = {"smoke","regression"})
	public void verifySeating() throws Exception {
		LoginBurrowPage LoginBurrowPage = new LoginBurrowPage(getDriver());
		AccountPage accountPage = LoginBurrowPage.login();
		Assert.assertTrue(accountPage.getCurrentURL().contains("account"),"Successfully logged in");
		accountPage.clickSeating();
		accountPage.clickLinkSofasInSeating();
		
		extentTest.info("create object for Store page");
		Seating storePage = new Seating(getDriver());
		extentTest.info("get the recomended title");
		Assert.assertEquals(storePage.getModularSofasHeading(),"Modular Sofas", "Heading has Matched");
		extentTest.pass("Assertion is passed for webpage Heading");
	}
	
}
