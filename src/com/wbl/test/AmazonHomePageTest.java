package com.wbl.test;

 import org.testng.Assert;
import org.testng.annotations.BeforeClass;
 import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wbl.base.BaseTest;
import com.wbl.helper.Constants;
import com.wbl.helper.ExcelHelper;
import com.wbl.page.AmazonHomePage;
 
public class AmazonHomePageTest extends BaseTest{

//	WebDriver driver;   // do not have it here as it reinitializes the webdriver 
                        // 	and we get a null pointer excepton as its already initializedin the base class
	
	
	AmazonHomePage ahp;
	
//	AmazonLoginPage alp;
	
	Object data[][] = null;

	

	@BeforeClass(alwaysRun=true)
	public void before() {
		
		System.out.println("inside test before");
		
 		ahp = new AmazonHomePage(driver);
 		
	}
	
	 

 
 
 
 	@DataProvider(name="accountList")
	public Object[][] accountListData(){
		
		System.out.println("home page data = reading excel");

		  data = ExcelHelper.getExcelData("test-data\\HomePageTestData.xlsx","AccountListDropDown");
		
		return data;
		
	}
 	
 	
	@DataProvider(name="navigateDeptDropDown")
	public Object[][] deptDropDownData(){
		
		System.out.println("home page data = reading excel");

		  data = ExcelHelper.getExcelData("test-data\\HomePageTestData.xlsx","navigateDeptDropDown");
		
		return data;
		
	}
	
	/*
	@Test
	public void primeMessageDisplayTest() {
		
	 Assert.assertTrue(ahp.primeMessageDisplay());
	 
	 
	}
	*/
	
	
	
 @Test
	
	// @Test(dependsOnMethods="getSignInTest")

	public void tryPrimeTest() {
				 
		 
//		 driver.navigate().to("http://www.amazon.com");
	 
	 driver.navigate().to(Constants.HOMEPAGE);
		 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 Assert.assertEquals(ahp.tryPrime(),"Amazon Sign In");
	}
	 
	
 
	 @Test
	 public void getSignInTest() {
		 
       String title = ahp.getHomePage( );
       
	//	 driver.navigate().to("http://www.amazon.com");
       
       driver.navigate().to(title);
		 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
      
	   Assert.assertNotNull(ahp.getSignInPage());
     
 //  Assert.assertEquals(ahp.getSignInPage(),"Amazon Registration");

	 }



// 	 @Test(dependsOnMethods="getSignInTest")
	 
	@Test 
	 public void createAccountPageTest() {
	 
  //    String title =  ahp.getHomePage();
      
  //    driver.navigate().to(Constants.HOMEPAGE);
 		 
 		 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 
 	  Assert.assertNotNull(ahp.createAccountPage());

	  //  Assert.assertEquals(ahp.createAccountPage(),"Amazon Registration");
 
 	 }

	 
	
 // @Test(dependsOnMethods="createAccountPageTest")
	 
 @Test	 
 public void getDeptDropDownCountTest() {


 //    ahp.getHomePage( );
	  
	  
      driver.navigate().to(Constants.HOMEPAGE);

//		 driver.navigate().to("http://www.amazon.com");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 int result = ahp.getDeptDropDownCount();
	 
	 System.out.println("the count is : " + result);
	 
	 // count is 21

	 Assert.assertEquals(ahp.getDeptDropDownCount(), 21);
 }
 
 
 //@Test(dataProvider="navigateDeptDropDown", dependsOnMethods="getDeptDropDownCountTest")
 
  @Test(dataProvider="navigateDeptDropDown")
 public void navigateDeptDropDownTest(String first, String second, String expected) {
	 
 //    ahp.getHomePage( );
	 
	// driver.navigate().to("http://www.amazon.com");
	 
     driver.navigate().to(Constants.HOMEPAGE);


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 String title = ahp.navigateDeptDropDown(first, second);
	 
	 Assert.assertEquals(title,expected);
			 
	//title is = Amazon.com: Children's Books, Kids Books, Stories for Kids 
	 
 }
 

 
// @Test(dataProvider="accountList", dependsOnMethods="navigateDeptDropDownTest") 
 
 @Test(dataProvider="accountList")
 public void getAccountDetailsTest(String choice, String number, String expected) {
	 
     driver.navigate().to(Constants.HOMEPAGE);

	 
//	 driver.navigate().to("http://www.amazon.com");
	 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 
	 Assert.assertEquals(ahp.getAccountDetails(number),expected);
	 
 	 
	 //title is = Amazon Sign In

 }
	

}
