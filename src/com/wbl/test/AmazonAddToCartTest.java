package com.wbl.test;

 
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
 import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wbl.base.BaseTest;
import com.wbl.helper.ExcelHelper;
import com.wbl.page.AmazonAddToCart;
import com.wbl.page.AmazonCommonMethods;
  import com.wbl.page.AmazonSearch;

public class AmazonAddToCartTest extends BaseTest {

	AmazonSearch as;
	AmazonAddToCart atc;
	AmazonCommonMethods acm;
	
	
	Object[][] data = null;

	int itemCount = 0;

	int count = 0;
	
	int dataRow = 0;
	
	String result = "";
	
	@BeforeClass
	public void before() {

 		as = new AmazonSearch(driver);
 		atc = new AmazonAddToCart(driver);
 		acm = new AmazonCommonMethods(driver);
		
 
	}

	@DataProvider(name = "search")
	public Object[][] searchData() {    // multiple records

		data = ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx", "Clothing");

		return data;

	}

    
	          
	          @DataProvider(name = "cart")       // single record
	          public Object[][] addToCartData() {
	          
	          data = ExcelHelper.getExcelData("test-data\\AmazonAddToCartData.xlsx", "AddToCart");
	          
	          return data;
	          
	          }
	          
	          
	         
	          
	          
	 
	          

	@Test(dataProvider = "search")
	public void addItemsToCartTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) throws TimeoutException, InterruptedException {

	 
		
	//	acm.commonMethodsForCart(dropDownSearch, cartItem, person, price, sortType, searchOpt);
		
		
		if (itemCount == 0) {
			driver.get("https://www.amazon.com/s/ref=sr_pg_2?fst=as%3Aoff&rh=n%3A7141123011%2Cn%3A7147441"
					+ "011%2Ck%3Aleather+shoes%2Cp_36%3A2661614011&page=2&bbn=7141123011&sort=price-desc-rank&keywords=leather+shoes&ie=UTF8&qid=1492015522");
			itemCount++;
		
		}
		
		else if ( itemCount == 1 ) {
			driver.get("https://www.amazon.com/gp/search/ref=sr_st?fst=as%3Aoff&rh=n%3A7141123011%2Cn%3"
					+ "A10886357011%2Cn%3A7147442011%2Cp_36%3A2661612011&qid=1492016577&bbn=10886357011&sort=price-desc-rank");
			itemCount++;
		
		}
		
		atc.addItem(cartItem);
		atc.addSize(size);
		atc.addColor(color);

		atc.addQuantity(qnty);
		atc.addShipping(ship);
		atc.addAreaCode(areaCode);
		
		atc.addToCartButton();
		atc.updatingCartPage();
		atc.viewCartPage();

	}

	 

 	
	
	@Test(dataProvider = "cart", groups={"FunctionalityTest"})
	public void addItemTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) {
 
		
		driver.get("https://www.amazon.com/s/ref=sr_pg_2?fst=as%3Aoff&rh=n%3A7141123011%2Cn%3A7147441"
				+ "011%2Ck%3Aleather+shoes%2Cp_36%3A2661614011&page=2&bbn=7141123011&sort=price-desc-rank&keywords=leather+shoes&ie=UTF8&qid=1492015522");
		 
	
  
	/*if (itemCount == 0) {
		driver.get("https://www.amazon.com/s/ref=sr_pg_2?fst=as%3Aoff&rh=n%3A7141123011%2Cn%3A7147441"
				+ "011%2Ck%3Aleather+shoes%2Cp_36%3A2661614011&page=2&bbn=7141123011&sort=price-desc-rank&keywords=leather+shoes&ie=UTF8&qid=1492015522");
		itemCount++;
	
	}
	
	else if ( itemCount == 1 ) {
		driver.get("https://www.amazon.com/gp/search/ref=sr_st?fst=as%3Aoff&rh=n%3A7141123011%2Cn%3"
				+ "A10886357011%2Cn%3A7147442011%2Cp_36%3A2661612011&qid=1492016577&bbn=10886357011&sort=price-desc-rank");
		itemCount++;
	
	}
	 */
  
	
		atc.addItem(cartItem);

	}
	
	

 
	@Test(dataProvider = "cart", dependsOnMethods = "addItemTest", groups={"FunctionalityTest"})
	public void addSizeTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) {
		
	 
		
System.out.println("entered add size test and the count is : " + itemCount);
		
	
 

          
	// 	atc.addItem(cartItem);


		String result = atc.addSize(size);

		System.out.println("the result " + result);
		
		
	}
	
	

 	@Test(dataProvider = "cart" , dependsOnMethods = "addSizeTest", groups={"FunctionalityTest"})
	public void addColorTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) {


		System.out.println("entered add color test and the count is : " + itemCount);

	/*	
		atc.addItem(cartItem);
		atc.addSize(size);*/
	 
 
	  result = atc.addColor(color);
		
 		
 

	}

 	
	@Test(dataProvider = "cart", dependsOnMethods = "addColorTest", groups={"FunctionalityTest"})
	public void addQuantityTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) throws TimeoutException, InterruptedException {
		
		 
		
System.out.println("entered add quantity test and the count is : " + itemCount);


 
     /*   
		atc.addItem(cartItem);

		atc.addSize(size);
		atc.addColor(color);
		 */

		result = atc.addQuantity(qnty);
		
		System.out.println(result);
		
		Assert.assertEquals(result, Expected3);


	}

 
	
	@Test(dataProvider = "cart", dependsOnMethods = "addQuantityTest", groups={"FunctionalityTest"})
	public void addShippingTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) throws WebDriverException, InterruptedException {
		
System.out.println("entered shipping test and the count is : " + itemCount);
		
 /*
		
		atc.addItem(cartItem);

		atc.addSize(size);

		atc.addColor(color);


		atc.addQuantity(qnty);*/

 
	/*	result = atc.addShipping(ship);
		
		System.out.println(result);
*/
	Assert.assertEquals(atc.addShipping(ship),"Free shipping chosen");
	}

 
	
	@Test(dataProvider = "cart", dependsOnMethods = "addShippingTest",groups={"FunctionalityTest"})
	public void addAreaCodeTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) throws TimeoutException, InterruptedException {
		
		
System.out.println("entered areaCode test and the count is : " + itemCount);


	 
		/*atc.addItem(cartItem);

		atc.addSize(size);

		atc.addColor(color);


		atc.addQuantity(qnty);


		atc.addShipping(ship);
 */

		Assert.assertEquals(atc.addAreaCode(areaCode),"LIVERMORE, CA 94550");
		
 		

	}
	
	

//	@Test(dataProvider = "search", dependsOnMethods = "addAreaCodeTest",groups={"FunctionalityTest"})
	
	@Test(dataProvider = "cart", dependsOnMethods = "addAreaCodeTest",groups={"FunctionalityTest"})
	void addToCartButtonTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) throws TimeoutException, InterruptedException {

		
System.out.println("entered addToCartButton test and the count is : " + itemCount);
		

 
	/*			
		atc.addItem(cartItem);

		atc.addSize(size);

		atc.addColor(color);


		atc.addQuantity(qnty);


		atc.addShipping(ship);

		atc.addAreaCode(areaCode);
		
	  */
		
		String[] res = atc.addToCartButton();
		

		System.out.println("the cart title is : " + res[2]);

		int tot1 = Integer.parseInt(res[0]);

		int tot2 = Integer.parseInt(res[1]);

		
		if (tot1 == tot2 + 1) {

			System.out.println("The number of items in the cart is   updated by 1");
		} else {
			System.out.println("The number of items in the cart is not updated");

		}

 
		Assert.assertEquals(res[2], "Amazon.com Shopping Cart");

	}
	
	
	
	
//	@Test(dataProvider = "search", dependsOnMethods = "addToCartButtonTest",groups={"FunctionalityTest"})
	
	@Test(dataProvider = "cart", dependsOnMethods = "addToCartButtonTest",groups={"FunctionalityTest"})
	public void updatingCartPageTest ( String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) throws TimeoutException, InterruptedException {
		
		
System.out.println("entered cartPageTest and the count is : " + itemCount);
		
	/* 
		atc.addItem(cartItem);

		atc.addSize(size);

		atc.addColor(color);


		atc.addQuantity(qnty);


		atc.addShipping(ship);

		atc.addAreaCode(areaCode);
		
	    atc.addToCartButton();
		
	*/
 

		String[] res = atc.updatingCartPage();
		
		System.out.println(res[0] + "   " + res[1]  + "  " + res[2]  );
		
		
		Assert.assertEquals(res[0],"$158.26");
		Assert.assertEquals(res[1],"2");
		Assert.assertEquals(res[2],"Amazon.com Shopping Cart");


	}
	
	
	
	
	
//	@Test(dataProvider = "search")
	
	@Test(dataProvider = "cart", dependsOnMethods = "updatingCartPageTest",groups={"FunctionalityTest"})
	public void viewCartPageTest ( String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode
			,String Expected1,String Expected2,String Expected3,String Expected4, String Expected5) throws TimeoutException, InterruptedException {
		
		
System.out.println("entered cartPageTest and the count is : " + itemCount);


		driver.get(acm.getAddToCartStartPage(itemCount));
		itemCount++;
		
		atc.addItem(cartItem);

		atc.addSize(size);

		atc.addColor(color);


		atc.addQuantity(qnty);


		atc.addShipping(ship);

		atc.addAreaCode(areaCode);
		
	    atc.addToCartButton();
		
	    atc.updatingCartPage();
	    
	   
        String cnt = atc.viewCartPage();
        
        Assert.assertEquals(Integer.parseInt(cnt), 2);
	    
	    
  
       
	}
 	
 
}


