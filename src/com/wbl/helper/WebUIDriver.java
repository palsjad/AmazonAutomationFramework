package com.wbl.helper;

 import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebUIDriver {
	
	
	public static String  browserName;
	public static String appUrl;
	
	
/*	Move this to ConfigsUtil. Instead now we will write like this where configsUtil returns prop
 * 
 * public static void loadProperties(String fileName) throws FileNotFoundException, IOException {
		
		Properties prop = new Properties();
		
		prop.load(new FileInputStream(System.getProperty("user.dir")+"\\resources\\" + fileName));
		
*/	
	
	
public static void loadProperties(String fileName) throws FileNotFoundException, IOException {
		
		Properties prop = ConfigUtils.loadProperties(fileName);		
		browserName = prop.getProperty("browser");
		appUrl = prop.getProperty("appUrl");
		
		System.out.println("in WebUIDriver. The browserr is : " + browserName);

		System.out.println("in WebUIDriver. The web page is : " + appUrl);
	}


	
	public static WebDriver getDriver() {
		WebDriver driver = null;

		switch (browserName) {
//      moved to Constants interface in helper package. So now instead we can write like this
			 
		case "chrome" :
//		  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\chromedriver.exe");
		  System.setProperty("webdriver.chrome.driver", Constants.PATH + "chromedriver.exe");
		  driver = new ChromeDriver();
		  break;
		  
		case "firefox" :
//			  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\firefoxdriver.exe");
 			  System.setProperty("webdriver.chrome.driver", Constants.PATH + "firefoxdriver.exe");
			  driver = new ChromeDriver();
			  break;
			  
		case "safari" :
//			  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\safaridriver.exe");
 			  System.setProperty("webdriver.chrome.driver", Constants.PATH + "safaridriver.exe");
			  driver = new ChromeDriver();
			  break;
			  
		case "ie" :
//			  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\resources\\internetexplorerdriver.exe");
 			  System.setProperty("webdriver.chrome.driver", Constants.PATH + "internetexplorerdriver.exe");			
			  driver = new ChromeDriver();	  
			  break;
			  
		case "htmlunit" :
			   driver =  new HtmlUnitDriver();
			   break;
		}
		   return driver;
	}

}
