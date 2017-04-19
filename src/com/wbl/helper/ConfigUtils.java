package com.wbl.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {
	
	
	public static Properties loadProperties(String fileName) throws FileNotFoundException, IOException {
		
		Properties prop = new Properties();
		
		prop.load(new FileInputStream(Constants.PATH+fileName));
		
		return prop;
		
		
		
	}
	
	

}
