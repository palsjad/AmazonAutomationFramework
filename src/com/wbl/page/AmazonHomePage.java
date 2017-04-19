package com.wbl.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wbl.helper.ByClass;
 
public class AmazonHomePage {

	static Logger logger = LogManager.getLogger(AmazonHomePage.class);

	WebDriver driver;

	public AmazonHomePage(WebDriver driver) {
		System.out.println("amazon home page constructor");

		this.driver = driver;
	}

	
	// ************************ Prime *********************************************
	
	
	public String getHomePage() {
		
		String title = "http://www.amazon.com";
		
		return title;
	}
	
	
	
	
	public String getTitle() {
		
	String title =	driver.getTitle();
	
	return title;
		
	}
	
	
	public Boolean primeMessageDisplay() {
		
		System.out.println("prime message");
				    WebDriverWait wait=new WebDriverWait(driver,20);
	    
	    WebElement element = wait.until(ExpectedConditions
	    		.visibilityOfElementLocated(By.xpath(".//a[contains(@class,'nav-prime-try')]")));

 			
			Actions action = new Actions(driver);
			
	 		action.moveToElement(element).build().perform();

 						
		 	driver.findElement(By.xpath(".//*[@id='nav-prime-tooltip'and @class='nav-template nav-flyout-content']")).click();
			 
		 	
		    
		     wait=new WebDriverWait(driver,20);
		    
		    element = wait.until(ExpectedConditions
		    		.visibilityOfElementLocated(By.cssSelector("div[class='nav-npt-text-title']")));
			
			
			return element.isDisplayed();
					
	}
	
	
	
	
	public String tryPrime() {
	 		
  		
	logger.info("inside prime");
	
 	driver.findElement(ByClass.getByObject("xpath-getPrimeButton")).click(); // 'try prime' 

 	
		WebDriverWait wait = new WebDriverWait(driver,35);
 

		wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-startPrimeTrial"))).click();
 
		List<WebElement> buttons = new ArrayList<WebElement>();
		
	    wait = new WebDriverWait(driver,35);
 		
 		buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
				(ByClass.getByObject("xpath-primeButtonOptions") ));
		
		buttons.get(1).click();
		
		buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".a-button-input")));
 
		buttons.get(2).click();
 		
 		logger.info("title for amazon prime page is : " + driver.getTitle());
	 
 		
		return driver.getTitle();
			 
 	  
			
	}
	
	
	 
	// ****************************** create account **************************************************
 	

	public RegisterAccountPage createAccountPage() {
		
		logger.info(" create account");


		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-AccountLink"))).click();
 
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-AccountSubmit")));

		element.click();
 
	//	return driver.getTitle();

	  return new RegisterAccountPage(driver);

	}

	// ****************************** end of create account *************************************

	// ****************************** sign in **************************************************

	public AmazonLoginPage getSignInPage() {
		
		logger.info("sign in page");


		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-AccountLink"))).click();

		// return driver.getTitle();

		return new AmazonLoginPage(driver);

	}

	// ****************************** end of sign in **************************************************

	// ****************************Department drop down count **************************************************

	public int getDeptDropDownCount() {
		
		logger.info("dept drop down");


		WebDriverWait wait = new WebDriverWait(driver, 25);

		List<WebElement> dropDownList = new ArrayList<WebElement>();

		Actions action = new Actions(driver);

		WebElement dropDown = wait
 				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-deptLinkHover")));

//				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-deptLinkHover")));

		action.moveToElement(dropDown).perform();
		
		  wait = new WebDriverWait(driver, 25);


		dropDownList = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(ByClass.getByObject("xpath-countDropDownPanel")));

		return dropDownList.size();

	}

	// ****************************end of Department drop down count **************************************************

	// ****************************navigate department drop down **************************************************

	public String navigateDeptDropDown(String firstChoice, String secondChoice) {
		
		logger.info("navigate");


		WebDriverWait wait = new WebDriverWait(driver, 20);

		Actions action = new Actions(driver);

		WebElement dropDown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-deptLinkClick")));

		action.moveToElement(dropDown).perform();

		wait = new WebDriverWait(driver, 30);

		WebElement nav = wait.until(ExpectedConditions
				.visibilityOfElementLocated(ByClass.getByObject("xpath-bookPanelOnDropDownHover", firstChoice, "']")));

		action.moveToElement(nav).perform();

		wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(ByClass.getByObject("xpath-clickOnChildrensBooks", secondChoice, "')]")))
				.click();

		return driver.getTitle();

	}

	// ****************************end of navigate department drop down *************************************

	// **************************** accounts and list choices **********************************************

	public String getAccountDetails(String listChoice) {
		
		logger.info("get accountdetails ");


		String[] opts = listChoice.split(",");

		String choice = opts[0].trim();
		String type = opts[1].trim();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error("thread.sleep errored out in the getAccountDetails-1");
			e.printStackTrace();
		}

 
		WebDriverWait wait = new WebDriverWait(driver, 25);

		Actions action = new Actions(driver);

		List<WebElement> acc = new ArrayList<WebElement>();

		// *********to click on Account and lists **********************

		acc = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByClass.getByObject("css-headerLinkList")));

		for (WebElement ele : acc) {
			
			logger.info("getAccountDetails header list : " + ele.getText());


			if (ele.getText().equals("Account & Lists")) {


				action.moveToElement(ele).perform();
				break;
			}

		}

		wait = new WebDriverWait(driver, 30);

		if (type.equals("list")) {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-listSearch", choice, "]")))
					.click();

		} else if (type.equals("account")) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(ByClass.getByObject("xpath-accountSearch", choice, "]"))).click();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error("thread.sleep erroroed out in the getAccountDetails-2");
			e.printStackTrace();
		}

		return driver.getTitle();
	}

	// **************************** end of accounts and list choices **********************************************

}
