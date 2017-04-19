package com.wbl.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wbl.helper.ByClass;

public class AmazonLoginPage {
	
	static Logger logger = LogManager.getLogger(AmazonLoginPage.class);

	
	WebDriver driver;
	
	AmazonHomePage ahp;
	
	public AmazonLoginPage(WebDriver driver) {
		
		this.driver = driver;
		
 	}
	
	
	public String enterLoginDetails(String nm, String pwd) {
		
		
		driver.findElement(By.cssSelector("input[id='ap_email']")).sendKeys(nm);
		
		driver.findElement(By.cssSelector("input[id='ap_password']")).sendKeys(pwd);
		
		driver.findElement(By.cssSelector("input[id='signInSubmit']")).click();
		
 
		return driver.getTitle();
		
	}
	
	
	

  	public String enterDetails(List<String> accountDetails) {
 
  
		WebDriverWait wait = new WebDriverWait(driver, 30);
 
		List<WebElement> account = new ArrayList<WebElement>();

		wait = new WebDriverWait(driver, 30);
 
 	    account = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class*='a-input-text']")));
		 
  
	 
		int i = 0;
		
		for (WebElement ele : account) {

  		ele.clear();
  		ele.sendKeys(accountDetails.get(i));
  		
  		 i++;
		} 
		
		

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-AccountSubmit")));

		element.click();

	 
  
	 	return driver.getTitle();	
		
 
		
	}
  	
  
	

  	}
  	
  	
  	
 
