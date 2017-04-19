package com.wbl.page;

 
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wbl.helper.ByClass;

	
	public class RegisterAccountPage  {
		
		WebDriver driver;
		
 		
		public RegisterAccountPage(WebDriver driver) {
			
			this.driver = driver;
			
 		}
		
	 
		

	  	public String enterDetails(List<String> accountDetails) {
	 
	  
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
	 
		/*	WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-AccountSubmit")));

			element.click();

		 */
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
					.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-AccountRegister")));

			element.click();

		 
	  
		 	return driver.getTitle();	
			
		//	return new AmazonHomePage(driver);

			
		}
	  	
	  
		

	  	}
	  	
	  	
	  	
	 
