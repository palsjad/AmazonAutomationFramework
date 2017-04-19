package com.wbl.test;

 import org.testng.Assert;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wbl.base.BaseTest;
import com.wbl.helper.ExcelHelper;
import com.wbl.page.AmazonHomePage;
import com.wbl.page.AmazonLoginPage;

 
public class AmazonLoginTest extends BaseTest {
	
	AmazonHomePage ahp;
	
	AmazonLoginPage alp;
	
	Object[][] data;
	
	@BeforeClass 
	public void before() {
		
		ahp = new AmazonHomePage(driver);
 		alp = ahp.getSignInPage();
		
	}
 	
	/*@BeforeMethod 
	public void beforemethod() {
		
		System.out.println("before method");
		
		alp = ahp.getSignInPage();
		
		

	}
	*/
	 
	@DataProvider(name="login")
	public Object[][] loginData() {
		
	 	data = ExcelHelper.getExcelData("test-data\\AmazonloginTestData.xlsx","login");
	
	 	return data;
	 	
	}
	
	 
		@DataProvider(name="accountDetails")
		public Object[][] readDetails() {
			
	 		
			Object[][] data = ExcelHelper.getExcelData("test-data\\AmazonLoginTestData.xlsx","CreateAccount");//  test-data\\AmazonTestData.xlsx
		
		 
			
			return data;
		}  
	
	
	@Test(dataProvider ="login")
	public void enterLoginDetailsTest(String nm, String pwd) {
		
    
		
 		
  		Assert.assertEquals(alp.enterLoginDetails(nm, pwd),"Amazon Sign In");

		
		
	}
	
 
	
}
