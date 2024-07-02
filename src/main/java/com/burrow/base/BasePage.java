package com.burrow.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.burrow.utilities.ExcelReadWrite;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {
	public static String screenshotsSubFolderName;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(BasePage.class);
	public static ExcelReadWrite excel = new ExcelReadWrite();
	public static WebDriverWait wait;
	public static String browser;

	@Parameters("browserName")
	@BeforeTest
	public void setUp(ITestContext context, @Optional("chrome") String browserName) {
		if (driver == null) {
			PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\log4j.properties");
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\Config.properties");
				config.load(fis);
				log.debug("Config file loaded !!!");
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\OR.properties");
				OR.load(fis);
				log.debug("OR file loaded !!!");
				//Workbook workbook = WorkbookFactory.create(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excels\\testdata.xlsx"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if((browserName.toLowerCase()).contains("chrome")){
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--remote-allow-origins=*"); // chrome version 111+
					driver = new ChromeDriver(options);
			}else if((browserName.toLowerCase()).contains("edge")) {
					WebDriverManager.edgedriver().clearDriverCache().setup();
					driver = new EdgeDriver();
			}else if((browserName.toLowerCase()).contains("firefox")){
					WebDriverManager.firefoxdriver().clearDriverCache().setup();
					driver = new FirefoxDriver();
			}else {
					System.out.println("Browsername is invalid");
			}
			
//			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
//				browser = System.getenv("browser");
//			}else{
//				browser = config.getProperty("browser");
//			}
//			config.setProperty("browser", browser);

//			if (config.getProperty("browser").equals("firefox")) {
//				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
//				WebDriverManager.firefoxdriver().setup();
//				driver = new FirefoxDriver();
//				log.debug("Firefox Launched !!!");
//			} else if (config.getProperty("browser").equals("chrome")) {
//				/*
//				 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
//				 * + "\\src\\test\\resources\\executables\\chromedriver.exe");
//				 */
//				WebDriverManager.chromedriver().setup();
//				driver = new ChromeDriver();
//				log.debug("Chrome Launched !!!");
//			} else if (config.getProperty("browser").equals("ie")) {
//				System.setProperty("webdriver.ie.driver",
//						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
//				driver = new InternetExplorerDriver();
//				log.debug("IE Launched !!!");
//			}
			
			driver.manage().window().maximize();
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			System.out.println("DRIVER : " + driver.getClass());
			//extentTest = extentReports.createTest(context.getName());
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("test execution completed !!!");
	}

	//@BeforeSuite
	public void initialiseExtentReports() {
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("E:\\Selenium_Class\\diathrive\\src\\main\\resources\\reports\\AllTests.html");
		sparkReporter_all.config().setReportName("All Tests report");
		
		ExtentSparkReporter sparkReporter_failed = new ExtentSparkReporter("E:\\Selenium_Class\\diathrive\\src\\main\\resources\\reports\\FailedTests.html");
		sparkReporter_failed.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		sparkReporter_failed.config().setReportName("Failure report");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all, sparkReporter_failed);
		
		//extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		//extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	//@AfterSuite
	public void generateExtentReports() throws Exception {
		extentReports.flush();
		Desktop.getDesktop().browse(new File("E:\\Selenium_Class\\diathrive\\src\\main\\resources\\reports\\AllTests.html").toURI());
		Desktop.getDesktop().browse(new File("E:\\Selenium_Class\\diathrive\\src\\main\\resources\\reports\\FailedTests.html").toURI());
	}

	
	//@AfterMethod
	public void checkStatus(Method m, ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = null;
			screenshotPath = captureScreenshot(result.getTestContext().getName()+ "_" +result.getMethod().getMethodName()+".jpg");
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(result.getThrowable());
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName() + " is passed");
		}
		
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}
	
	public String captureScreenshot(String fileName) {
		if(screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		    screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/"+ screenshotsSubFolderName+"/"+fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();
	}
	
	public WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(Duration.ofSeconds(10))
	            .pollingEvery(Duration.ofSeconds(2))
	            .ignoring(NoSuchElementException.class);

	    WebElement unlockSale = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });
	    return  unlockSale;
	};
}
