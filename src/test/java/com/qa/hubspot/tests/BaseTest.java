package com.qa.hubspot.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.Page;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public WebDriverWait wait;
	public Page page;

	@BeforeMethod
	@Parameters({"browser"})
	public void setUp(String browser) {
	    if (browser.equals("chrome"))
	     {
	    	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
	     } 
	    else if (browser.equals("firefox"))
	     {
	    	 WebDriverManager.firefoxdriver().setup();
	    	 driver = new FirefoxDriver();
	     }
	    else 
	    {
	    	System.out.println("no browser is defined in xml file...!!"); 
	    }
	    
	    wait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		driver.get("https://app.hubspot.com/login");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// create the object of page class: Instantiate Page Class
		page = new Page(driver, wait);
	}
	
/*	public void setUp() {
		//System.setProperty("webdriver.chrome.driver", "/Users/NaveenKhunteta/Downloads/chromedriver");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		driver.get("https://app.hubspot.com/login");
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// create the object of page class: Instantiate Page Class
		page = new Page(driver, wait);
	}
*/


	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
