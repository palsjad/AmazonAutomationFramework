package com.wbl.base;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.wbl.helper.ByClass;
import com.wbl.helper.WebUIDriver;

public class BaseTest {

	protected WebDriver driver;
	// static Properties prop;

	@BeforeSuite
	public void beforeSuite() {

		System.out.println("before suite");
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
		 * driver = new ChromeDriver();
		 * 
		 * 
		 */

		try {
			WebUIDriver.loadProperties("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver = WebUIDriver.getDriver();
		driver.get(WebUIDriver.appUrl);
		ByClass.loadProperties();

	}

	@AfterSuite
	public void afterSuite() {

	//	driver.close();

	}

}
