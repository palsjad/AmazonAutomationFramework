package com.wbl.page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class AmazonCommonMethods {
	
	
	WebDriver driver;
	
	AmazonSearch as;
	
	AmazonAddToCart atc;
	
	
	public AmazonCommonMethods(WebDriver driver){
		
		this.driver = driver;
		
		as = new AmazonSearch(driver);
		atc = new AmazonAddToCart(driver);
	}
	

	public void commonMethodsForCart(String cartItem, String size, String color, String qnty, String ship,
			String areaCode) throws TimeoutException, InterruptedException {
		
		
		atc.addItem(cartItem);
		atc.addSize(size);
		atc.addColor(color);

		atc.addQuantity(qnty);
		atc.addShipping(ship);
		atc.addAreaCode(areaCode);

	}
		
		
	 
	
	public void commonMethodsForSearch(String dropDownSearch, String item, String person,String price,
			String sortType, String searchOpt) {
		
		
		as.allDropDown(dropDownSearch);
		as.dropDownChoice(item, person);
		as.sortChoice(price, sortType);
		as.searchResults(searchOpt);
		
		
		
 
		
	}
	
	public String getAddToCartStartPage(int count){
		String title = "";
		if (count == 0) {
			title =  "https://www.amazon.com/s/ref=sr_pg_2?fst=as%3Aoff&rh=n%3A7141123011%2Cn%3A7147441"
					+ "011%2Ck%3Aleather+shoes%2Cp_36%3A2661614011&page=2&bbn=7141123011&sort=price-desc-rank&keywords=leather+shoes&ie=UTF8&qid=1492015522"   ;
			 
		}
		
		else if ( count == 1 ) {
			title = "https://www.amazon.com/gp/search/ref=sr_st?fst=as%3Aoff&rh=n%3A7141123011%2Cn%3"
					+ "A10886357011%2Cn%3A7147442011%2Cp_36%3A2661612011&qid=1492016577&bbn=10886357011&sort=price-desc-rank";
 
		}
		
		return title;
		  
		
	}
}
