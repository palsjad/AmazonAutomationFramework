package com.wbl.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ByClass {

 	static  Logger logger = LogManager.getLogger(ByClass.class);
 	
 

	static Properties prop;
	static Properties prop1;
	static Properties prop2;

	public static void loadProperties() {

		try {
			prop = ConfigUtils.loadProperties("locators\\commonLocatorsFile.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * try { prop1 =
		 * ConfigUtils.loadProperties("locators\\homepage-locators.properties");
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 * 
		 * 
		 * 
		 * try { prop2 =
		 * ConfigUtils.loadProperties("locators\\search-locators.properties"); }
		 * catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 * 
		 */

//		logger.info("The path of the locator properties file is " + prop);
		
//		System.out.println("path is : " + prop);
	}

	public static By getByObject(String key) {

		By by = null;

 
		String value = prop.getProperty(key); // replace prop.getProperty(key) by value below

		if (key.startsWith("css")) {

			return By.cssSelector(value);

		} else if (key.startsWith("xpath")) {

			return (By.xpath(value));

		} else if (key.startsWith("link")) {
			return By.linkText(value);

		} else if (key.startsWith("id")) {
			return By.id(value);

		} else if (key.startsWith("partialLink")) {
			return By.partialLinkText(value);

		} else if (key.startsWith("tag")) {
			return By.tagName(value);

		} else if (key.startsWith("class")) {
			return By.className(value);

		} else if (key.startsWith("name")) {
			return By.name(value);
		} else
			return by;

	}

	public static By getByObject(String key, String input, String locator2) {

		By by = null;

		String value = prop.getProperty(key); // replace prop.getProperty(key) by value below

		value = value + input + locator2;

//		System.out.println("the key is :" + value);
		
		logger.info("the key created in the overridden ByClass method is :" + value);


		if (key.startsWith("css")) {

			return By.cssSelector(value);

		} else if (key.startsWith("xpath")) {

			return (By.xpath(value));

		} else if (key.startsWith("link")) {
			return By.linkText(value);

		} else if (key.startsWith("id")) {
			return By.id(value);

		} else if (key.startsWith("partialLink")) {
			return By.partialLinkText(value);

		} else if (key.startsWith("tag")) {
			return By.tagName(value);

		} else if (key.startsWith("class")) {
			return By.className(value);

		} else if (key.startsWith("name")) {
			return By.name(value);
		} else
			return by;

	}

}
