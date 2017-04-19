package com.wbl.page;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wbl.helper.ByClass;
 
public class AmazonAddToCart {

	static Logger logger = LogManager.getLogger(AmazonAddToCart.class);

	WebDriver driver;

	AmazonSearch as;

	double cartTotal = 0.0;

	public AmazonAddToCart(WebDriver driver) {

		this.driver = driver;
		as = new AmazonSearch(driver);

	}

	// **************************** add to cart ****************************************

	public String addItem(String item) {

		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-cartItem", item, "')]")))
				.click();

		logger.info("the add item title is : " + driver.getTitle());

		return driver.getTitle();

	}

	// *********************************************************************************************

	public String addSize(String itemSize) {

		String result = "";

		if (itemSize.equals(" ")) {
			return "Size not applicable for this item as its empty";
		}

		String[] str = itemSize.split(",");

		String size = str[0];

		String size1 = str[1]; // value of option

		logger.info("the size enterred is : " + str[0] + "  " + str[1]);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement element = driver.findElement(By.id("native_dropdown_selected_size_name"));

		Select sel = new Select(element);

		List<WebElement> sizeOpts = sel.getOptions();

		for (WebElement opts : sizeOpts) {

			// if (size.trim().equals(opts.getText().trim() )) {

			logger.info("the shoe option value " + opts.getAttribute("id") + "  " + opts.getAttribute("class"));

			if (size.equalsIgnoreCase(opts.getAttribute("id"))) {

				if (opts.getAttribute("class").equals("dropdownAvailable")) {

					sel.selectByIndex(Integer.parseInt(size1));

					result = " size selelcted";
					break;
				}

			}

		} // end of for loop

		return result;

	}

	// *********************************************************************************************

	public String addColor(String color) {

		String result = "";

		if (color.equals(" ")) {
			return "Color  not applicable for this item as its empty";
		}

		String[] colorDetails = color.split(",");

		String colorNum = colorDetails[1];

		logger.info("colornum " + colorNum);

		// WebDriverWait wait = new WebDriverWait(driver, 20);

		// WebElement element = wait.until(ExpectedConditions
		// .visibilityOfElementLocated(ByClass.getByObject("css-addColor",colorNum,"']")));

		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

		WebElement element = driver.findElement(ByClass.getByObject("css-addColor", colorNum, "']"));

		String colAvailability = element.getAttribute("class");

		logger.info("color availibity : " + colAvailability);

		if (colAvailability.equalsIgnoreCase("swatchAvailable") || colAvailability.equalsIgnoreCase("swatchSelect")) {

			element.click();

			result = " color matched";

		} else {

			logger.info("given color not available ");
			result = ("given color not available ");

		}

		return result;

	}

	// *********************************************************************************************

	public String addQuantity(String qnty) throws InterruptedException, TimeoutException {

		String result = " ";

		WebElement ele;

		Boolean present = false;

		if (qnty.equals(" ")) {

			logger.info("the quantity is not given");

			return "the quantity is not given";
		}

		try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		if (Integer.parseInt(qnty) > 1) {

			WebDriverWait wait = new WebDriverWait(driver, 20);

			try {

				ele = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-addQnty")));

				logger.info("entereed try to check quantity available :  " + ele);
				present = true;

			}

			catch (NoSuchElementException e) {

				logger.info("enteredd catch ");

				present = false;

				logger.info("no quantity for this item found on the page ");

				return "no quantity found on the page ";

			} catch (TimeoutException e) {
				present = false;

			}

			wait = new WebDriverWait(driver, 30);

			if (present) {

				WebElement element = driver.findElement(ByClass.getByObject("id-qntySelect"));

				Select select = new Select(element);

				select.selectByValue(qnty); // qnty got from the excel file

				result = "quantity selected " + qnty;
			}

			else if (wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-QntyLeft1")))
					.isDisplayed()) {

				logger.info("entered else in - only 1 item left in stock");

				result = ("only 1 item left in stock");

			}

			return result;

		}

		else {
			return "quantity given is 1";
		}

	}

	// *****************************************************************************************************

	public String addShipping(String ship) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		String res = "";
		if (ship.equals("Y")) {

			if (wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-addShipping")))
					.isDisplayed()) {

				driver.findElement(ByClass.getByObject("id-shippingButton")).click();

				res = "Free shipping chosen";

			} else {
				res = ("Free shipping not available on this item");
			}
		} else {

			res = "Free shipping not chosen by the user";
		}

		return res;

	}

	// *********************************************************************************************

	public String addAreaCode(String areaCode) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("thread.sleep erroroed out in the addAreaCode");
			e.printStackTrace();
		}

		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-shipBox"))).click();

		driver.findElement(ByClass.getByObject("id-addAreaCode")).sendKeys("94568");

		wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-updateAreaCode"))).click();

		String address = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("xpath-shipBox")))
				.getText();

		return address;

	}

	// *********************************************************************************************

	public String[] addToCartButton() throws InterruptedException {

		// ********* add to cart **************

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			logger.error("thread.sleep erroroed out in the addtocartbutton-1");
			e.printStackTrace();
		}

		WebDriverWait wait = new WebDriverWait(driver, 20);

		String count1 = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-cartCount")))
				.getText();

		logger.info("the initial count is : " + count1);

		wait = new WebDriverWait(driver, 20);

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-addToCart")));

		element.click();

		if (isDisplayed("css-alertPopup")) {

			wait = new WebDriverWait(driver, 15);

			element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-acceptPopup")));

			element.click();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				logger.error("thread.sleep erroroed out in the addtocartbutton-2");
				e.printStackTrace();
			}

		}

		wait = new WebDriverWait(driver, 20);

		String count2 = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-cartCount")))
				.getText();

		// String count2 =
		// driver.findElement(ByClass.getByObject("css-cartCount")).getText();

		String title = "";
		title = driver.getTitle();

		String[] result = { count1, count2, title };

		// String[] result = {count2,title};

		System.out.println("results : " + result[0] + "  " + result[1] + "  " + result[2]);

		logger.info(
				"results of cart page are (count of items before adding, count of items after adding , page title : "
						+ result[0] + "  " + result[1] + "  " + result[2]);

		return result;

	}

	// *******************************************************************************

	public String[] updatingCartPage() {

		WebDriverWait wait = new WebDriverWait(driver, 20);
 

		String itemPrice = driver.findElement(ByClass.getByObject("xpath-updatingCartPriceList")).getText();

		String totalItems = wait
				.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject("css-cartCount"))).getText();

 
		logger.info("total items added in the cart are : " + totalItems);
		 
																		 
		driver.findElement(ByClass.getByObject("id-cartButtonClick")).click();

		String title = driver.getTitle();

		String[] res = { itemPrice, totalItems, title };

		return res;

	}

	// *****************************************************************************

 

	public String viewCartPage() {
		/*
		 * 
		 * List<WebElement> priceList = new ArrayList<WebElement>();
		 * 
		 * 
		 * List<WebElement> itemName = new ArrayList<WebElement>();
		 * 
		 * priceList = driver.findElements(By.xpath(
		 * ".//span[contains(@class,'sc-product-price')]"));
		 * 
		 * itemName = driver.findElements(By.xpath
		 * (".//span[@class='a-list-item']//following::a/span[@class='a-size-medium sc-product-title a-text-bold']"
		 * ));
		 * 
		 * 
		 * List[] cartItems = {priceList, itemName};
		 * 
		 * 
		 */

		/*
		 * String itemQnty = driver.findElement(By.cssSelector
		 * ("span[class='a-dropdown-prompt']")).getText();
		 */

		String itemQnty = driver.findElement(ByClass.getByObject("css-viewPageDropDown")).getText();

		// return(cartItems);

		return (itemQnty);

	}

	// ******************************************************************************************

	private Boolean isDisplayed(String popupIdLocator) throws InterruptedException {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ByClass.getByObject(popupIdLocator)));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
