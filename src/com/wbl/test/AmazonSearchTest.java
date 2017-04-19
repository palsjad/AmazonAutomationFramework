package com.wbl.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wbl.base.BaseTest;
import com.wbl.helper.ExcelHelper;
//import com.wbl.page.AmazonHomePageCopy;
import com.wbl.page.AmazonSearch;

public class AmazonSearchTest extends BaseTest {

	AmazonSearch as;
	Object[][] data = null;
	int dataCount = 0;

	String fileName = "";

	// @Parameters({"searchDatabase1"})
	@BeforeClass
	// public void before(String file) {

	public void before() {

		as = new AmazonSearch(driver);
		System.out.println("inside before");
		/*
		 * fileName = file;
		 * 
		 * System.out.println("file name " + fileName);
		 */

	}

	/*
	 * 
	 * @Parameters({"searchDatabase2"})
	 * 
	 * @DataProvider(name="dropDownChoice") public Object[][]
	 * dropDownChoiceData(String fileName){
	 * 
	 * System.out.println("dropDown choice data = reading excel " + fileName);
	 * 
	 * data = ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx",
	 * "fileName");
	 * 
	 * // data =
	 * ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx",
	 * "dropDownChoice");
	 * 
	 * return data;
	 * 
	 * }
	 * 
	 * @Parameters({"searchDatabase3"})
	 * 
	 * @DataProvider(name="clothing") public Object[][] clothingData(String
	 * fileName){
	 * 
	 * System.out.println("clothing data = reading excel");
	 * 
	 * 
	 * // data =
	 * ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx",
	 * "clothing");
	 * 
	 * 
	 * data = ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx",
	 * "fileName");
	 * 
	 * 
	 * 
	 * return data;
	 * 
	 * }
	 * 
	 */

	@DataProvider(name = "allDropDown")
	public Object[][] allDropDownData() {

		System.out.println("alldropdown data = reading excel");

		data = ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx", "AllDropDown");

		return data;

	}

	@DataProvider(name = "dropDownChoice")
	public Object[][] dropDownChoiceData() {

		System.out.println("dropDown choice data = reading excel");

		data = ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx", "DropDownChoice");

		return data;

	}

	@DataProvider(name = "clothing")
	public Object[][] clothingData() {

		System.out.println("clothing data = reading excel");

		data = ExcelHelper.getExcelData("test-data\\AmazonSearchTestData.xlsx", "Clothing");

		return data;

	}

	@Test(dataProvider = "allDropDown")
	public void allDropDownTest(String dropDownSearch, String expected1) {

		System.out.println("allDropDownTest");

		Assert.assertEquals(as.allDropDown(dropDownSearch), expected1);

	}

	@Test(dataProvider = "dropDownChoice")
	public void dropDownChoiceTest(String dropDownSearch, String item, String person, String expected1) {

		System.out.println("dropDownChoiceTest");

		as.allDropDown(dropDownSearch);

		Assert.assertEquals(as.dropDownChoice(item, person), expected1);

	}
	
	
	
	@Test(dataProvider = "clothing")
	public void allDropDownTest1(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode,
			String Expected1, String Expected2, String Expected3, String Expected4, String Expected5) {

		System.out.println("allDropDownTest- complete test");
		
		as.allDropDown(dropDownSearch);

	//	Assert.assertEquals(as.allDropDown(dropDownSearch), expected1);

	}

	@Test(dataProvider = "clothing")
	public void dropDownChoiceTest1(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode,
			String Expected1, String Expected2, String Expected3, String Expected4, String Expected5)  {

		System.out.println("dropDownChoiceTest - complete test" );

		as.allDropDown(dropDownSearch);
		
		as.dropDownChoice(item, person);

	//	Assert.assertEquals(as.dropDownChoice(item, person), Xxpected1);

	}

	@Test(dataProvider = "clothing")
	public void sortChoiceTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode,
			String Expected1, String Expected2, String Expected3, String Expected4, String Expected5) {

		System.out.println("sortChoiceTest");

		as.allDropDown(dropDownSearch);
		as.dropDownChoice(item, person);

		Assert.assertEquals(as.sortChoice(price, sortType), Expected1);

	}

	@Test(dataProvider = "clothing")
	public void searchResultsTest(String dropDownSearch, String item, String person, String price, String searchOpt,
			String sortType, String cartItem, String size, String color, String qnty, String ship, String areaCode,
			String Expected1, String Expected2, String Expected3, String Expected4, String Expected5) {

		as.allDropDown(dropDownSearch);
		as.dropDownChoice(item, person);
		as.sortChoice(price, sortType);

		Assert.assertEquals(as.searchResults(searchOpt), Expected3);

		// Assert.assertEquals(as.searchResults(searchOpt),Expected1);

	}
}
