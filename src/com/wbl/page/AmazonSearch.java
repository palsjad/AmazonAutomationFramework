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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.wbl.helper.ByClass;

public class AmazonSearch {

	static Logger logger = LogManager.getLogger(AmazonSearch.class);

	WebDriver driver;

	AmazonAddToCart atc;
	
	AmazonHomePage ahp;
	
	
	public AmazonSearch(WebDriver driver) {


		this.driver = driver;
		
		ahp = new AmazonHomePage(driver);
	}
	
	

	// ********************** allDropDown Method ***************************************************

	public String allDropDown(String search) {

		logger.info("allDropDown");
	 
 		driver.get(ahp.getTitle());
 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("thread.sleep errored out in the allDropDown-1");

 			e.printStackTrace();
		}

		WebElement element = driver.findElement(ByClass.getByObject("xpath-searchDropDown"));

	 
		Select sel = new Select(element);

		sel.selectByVisibleText(search); // 'search' comes from the excel file - Clothing, Shoes & Jewelry
	 
		WebDriverWait wait = new WebDriverWait(driver, 20);

		element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-goToHomePage")));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("thread.sleep erroroed out in the allDropDown-2");

 			e.printStackTrace();
		}

		// **************************************************************

  	return (driver.getTitle());

 
	}

 
	// *************************************** dropDownChoice Method **************************************************

	public String dropDownChoice(String itm, String person) {

 
		WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement element = driver.findElement(ByClass.getByObject("id-searchbox"));
		element.clear();
		element.sendKeys(itm); // itm comes from the excel file - watches, leather shoes, dresses

		driver.findElement(ByClass.getByObject("xpath-searchSubmit")).click();

		 wait = new WebDriverWait(driver, 40);

		// person comes from ecel file - women, men, girls

		/*element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(text(),'" + person + "')]")));*/
		 
		 element = wait.until(
					ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-dropDownChoice" + person + "')]")));

		element.click();

		// ***************** to pause the page so that the page title is read ************************

		wait = new WebDriverWait(driver, 20);

		element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-goToHomePage")));

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("thread.sleep erroroed out in the dropdownchoice-1");
			e.printStackTrace();
		}

 
 
		return driver.getTitle();

	}
	
	

	// ************************************** sortChoice Method **************************************************

	public String sortChoice(String prc, String sortType) {

 
		
		logger.info("sortChoice");
		WebDriverWait wait = new WebDriverWait(driver, 35);

		// prc comes from ecel file - index,2 , text,Price: High to Low, text,Price: Low to High
		
		/*wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath(".//span[contains(text(),'" + prc + "')]"))).click();
*/		
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(ByClass.getByObject("xpath-sortChoice" + prc + "')]"))).click();

		WebDriverWait expWait = new WebDriverWait(driver, 30);

		WebElement element = expWait
				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-sortOptions")));

		Select sel1 = new Select(element);

		List<WebElement> optionsList = sel1.getOptions();

		for (WebElement el : optionsList) {

			logger.info("price options : " + el.getText());
		}

		String[] sortSplit = sortType.split(",");

  
		if (sortSplit[0].equals("index")) {

 			sel1.selectByIndex(2);
		}

		else if (sortSplit[0].equals("text")) {

 
			sel1.selectByVisibleText(sortSplit[1]);
		}

		wait = new WebDriverWait(driver, 35);

		String firstPrice = wait.until(func);

        logger.info("the first price inthe list is : " + firstPrice);
        
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.info("thread.sleep errored out in sort chocie");
 			e.printStackTrace();
		}

	logger.info("the end of sortChoice title is : " + driver.getTitle());
 
		wait = new WebDriverWait(driver, 30);
 
		return driver.getTitle();

	}

 
	// *************************************** Wait Function/ **************************************************

	Function<WebDriver, String> func = new Function<WebDriver, String>() {
		public String apply(WebDriver input) {

 
			WebDriverWait wait = new WebDriverWait(driver, 25);
			List<WebElement> price = new ArrayList<WebElement>();
			price = wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(ByClass.getByObject("xpath-sortedPrices")));

			for (WebElement p : price) {

				logger.info("sorted price : " + p.getText());
			}

			return price.get(0).getText();  // returning the first value to compare

			 
		}

	};

	// ****************************** end of Function *******************************************
	
	
	

	// ****************************** Search Results *********************************************

	public String searchResults(String opt) {

 

		String[] options = opt.split(",");
		String choice = "";

		String title = "";

  
		WebDriverWait wait = new WebDriverWait(driver, 25);
		
		if(options[0].equals("")){
			
			title = driver.getTitle();
		}

		else if (options[0].equalsIgnoreCase("brand button")) {

			choice = options[1];

		 

			/*wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(".//span[@class='refinementLink'][text()='" + choice + "']")))
					.click();
*/
			
			
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(ByClass.getByObject("xpath-brandButton" + choice + "']"))).click();
					 

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.error("thread.sleep errored out in search results-1");
 				e.printStackTrace();
			}

			title = driver.getTitle();

 
		}

		else if (options[0].equalsIgnoreCase("page")) {

 
			choice = options[1];

			if (choice.equalsIgnoreCase("Next Page")) {

 
				wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("id-nextPage"))).click();

				wait = new WebDriverWait(driver, 20);

				WebElement element = wait.until(
						ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-confirmCurrentPage")));

				title = element.getText();
 
			}

			else {

 
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(choice))).click(); // choosing
																										// by
																										// pagenumber
																										// from
																										// excel
																										// file

				WebElement element = wait.until(
						ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-confirmCurrentPage")));

				title = element.getText();

 
			}

		}

		else if (options[0].equalsIgnoreCase("customer review")) {

 
			choice = options[1].trim();

 
			List<WebElement> reviews = new ArrayList<WebElement>();

			wait = new WebDriverWait(driver, 30);
			
			Actions action = new Actions(driver);
			
			  WebElement reviewHeading =
					  wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-listForCustReviews")));
 
			  action.moveToElement(reviewHeading).perform();
			  
			  wait = new WebDriverWait(driver, 30);
			/*	
			   reviews =
			  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByClass.getByObject("xpath-listForCustReviewsX1")));  */
			 
			   reviews =
					  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByClass.getByObject("css-listForCustReviewsCSS")));  
			 
 
//			reviews = driver.findElements(ByClass.getByObject("xpath-listForCustReviewsX"));
			 
				logger.info("about to print the reviews");
 
				WebElement opt1 ;
 
			for (WebElement rev : reviews) {
				
/* 			     opt1 = rev.findElement(By.xpath(".//span[@class='a-icon-alt']"));
*/ 			  
 			     opt1 = rev.findElement(ByClass.getByObject("xpath-getReviewsByStars"));
				
 			     
 			    logger.info("the reviews in the second for loop are : " + opt1.getText());
 			    
 			    
	 

			}

			int val = 0;
			val = Integer.parseInt(choice);

 			for (WebElement ele : reviews) {

				switch (val) {
				case 4:
					if (ele.getText().equals("4 Stars & Up")) {
						logger.info("4 stars");

						ele.click();
					}
					break;
				case 3:
					if (ele.getText().equals("3 Stars & Up")) {

						ele.click();
					}
					break;
				case 2:
					if (ele.getText().equals("2 Stars & Up")) {

						ele.click();
					}
					break;
				case 1:
					if (ele.getText().equals("1 Star & Up")) {

						ele.click();
					}
					break;

				}

  			}

			title = driver.getTitle();

  
		}

		return title;

	}

	// ****************************** end of Search Results *********************************************
	
	
	// **************************** add to cart ****************************************

 		public String addItem(String item ) {
			
//	public AmazonAddToCart addItem(String item ) {	
 
 		
		WebDriverWait wait = new WebDriverWait(driver, 20);	
		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(ByClass.getByObject("xpath-cartItem",item,"')]"))).click();
		
		
 		
 		return driver.getTitle();
		
		
	//	return new AmazonAddToCart(driver);

		}
		
		
	// *********************************************************************************************	
		
	
	

}
