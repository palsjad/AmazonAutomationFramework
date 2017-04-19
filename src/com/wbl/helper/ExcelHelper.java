package com.wbl.helper;

import java.io.FileInputStream;
 import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
 //import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

 	static  Logger logger = LogManager.getLogger(ExcelHelper.class);
	
	public static Object[][] getExcelData(String fileName, String sheetName) {
     
		Object data[][] = null;

		XSSFWorkbook wb = null;
		
		 
		try {
			// wb = new XSSFWorkbook(new FileInputStream(Constants.PATH +
			// "test-data\\AmazonTestData.xlsx"));

			wb = new XSSFWorkbook(new FileInputStream(Constants.PATH + fileName));
			
//			System.out.println(Constants.PATH + fileName);
			
			logger.info("The path of the file read is " + Constants.PATH + fileName);

 
			
			XSSFSheet sheet = wb.getSheet(sheetName);

			int rows = sheet.getLastRowNum();
  
	//		System.out.println("no of rows : " + rows);
			
			logger.info("no of rows : " + rows);
 

			data = new Object[rows][];

			for (int i = 1; i <= rows; i++) {
			

				XSSFRow row = sheet.getRow(i);

				if(emptyRow(row)) {
				 
				int cols = row.getLastCellNum();

	//			System.out.println("no of cols: " + cols + "row number : " + i);
				
				logger.info("no of cols: " + cols + "row number : " + i);
  
				String[] totalCols = new String[cols];

				for (int j = 0; j < cols; j++) {

					totalCols[j] = row.getCell(j).getStringCellValue();
					
					logger.info(" column details : " + totalCols[j]);


//					System.out.println(" column details : " + totalCols[j]);

				}
				
                 if(i <= rows) {
                	 
				data[i - 1] = totalCols;
				
                 }
                 
				}   // end of empty row
 			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != wb) {

					wb.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return data;

	}
	
	
	public static Boolean emptyRow(XSSFRow row ) {
	 
		Iterator<Cell> iterator = row.cellIterator();
		if(iterator.hasNext()) {
		    return true;
		}
		else {
		    return false;
		}
		 
	}
	
}