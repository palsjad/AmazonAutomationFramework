package com.wbl.test;



 
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wbl.base.BaseTest;
import com.wbl.helper.ExcelHelper;
import com.wbl.page.AmazonHomePage;
import com.wbl.page.AmazonLoginPage;
import com.wbl.page.RegisterAccountPage;

 

public class RegisterAccountPageTest extends BaseTest {
	 	
		AmazonHomePage ahp;
		
		AmazonLoginPage alp;
		
		RegisterAccountPage rap;
		
		Object[][] data;
		
		@BeforeClass 
		public void before() {
			
			ahp = new AmazonHomePage(driver);
	 		rap = ahp.createAccountPage();
//	 		rap = new RegisterAccountPage(driver);
			
		}
	  
		 
			@DataProvider(name="accountDetails")
			public Object[][] readDetails() {
				
		 		
				Object[][] data = ExcelHelper.getExcelData("test-data\\AmazonLoginTestData.xlsx","createAccount");//  test-data\\AmazonTestData.xlsx
			
			 
				
				return data;
			}  
		
	 
		@Test(dataProvider="accountDetails")
	 	public void  enterDetailsTest(String nm, String userId, String pwd, String pwd2) {
			
	  

			
	 	 	List<String> details = new ArrayList<String>();
		 
	        details.add(nm);
	        details.add(userId);
	        details.add(pwd);
	         details.add(pwd2);
	   
	 		
	 		 
	        System.out.println(" inside test details "  + details.get(0) + "  " +  details.get(1) + "  "  + details.get(2) );
	        
	        System.out.println("before entring"); 
			 
	         
	//  		Assert.assertEquals(rap.enterDetails(details),"Amazon Registration");
			
	        Assert.assertEquals(rap.enterDetails(details),
	        		"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
	}
		
		
		
	}

	
	
	
	

 
