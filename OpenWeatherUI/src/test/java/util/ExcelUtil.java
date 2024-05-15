package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;

	public static String[][] getExcelDataIn2DArray(String Path,String SheetName) throws Exception {
		String[][] excelDataArray = null;
		try {
			
			FileInputStream ExcelFile = new FileInputStream(Path);
	
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int numOfColumns = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
			int numOfRows = ExcelWSheet.getPhysicalNumberOfRows();
			
			excelDataArray = new String[numOfRows-1][numOfColumns];
			
			for (int i= 1 ; i < numOfRows; i++) {

				for (int j=0; j < numOfColumns; j++) {
					excelDataArray[i-1][j] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return excelDataArray;
	}
	 public static List<String> getList(String columnName)  {
		 String excelFilePath = "C:\\Users\\USER\\eclipse-workspace\\assignment\\OpenWeatherUI\\testData\\Api.xlsx";
	    	
	        
	    	List<String> list = new ArrayList();
	        FileInputStream fis;
			try {
				fis = new FileInputStream(excelFilePath);
			
	        	
	             Workbook workbook = WorkbookFactory.create(fis); 

	            Sheet sheet = workbook.getSheet("Sheet1");
	            if (sheet == null) {
	                throw new IllegalArgumentException("column not found in testData sheet");
	            }

	            // Iterate through rows
	            Row header = sheet.getRow(0);
	            int targetColumnIndex=-1;
	            for (Cell cell : header) {
	                if (cell.getStringCellValue().equals(columnName)) {
	                    targetColumnIndex = cell.getColumnIndex();
	                    break;
	                }
	            }
	             for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	                  Row row = sheet.getRow(rowIndex);
	                  if (row.getCell(targetColumnIndex) != null) {
	                  Cell cell = row.getCell(targetColumnIndex);
	                  list.add(cell.toString());
	           
	        } 
	        }
	             
			} catch (IOException | EncryptedDocumentException e) {
				
				e.printStackTrace();
			}
			return list;
	          
	    }
	
	

	
		
	 
}

//	    public static void main(String[] args) {
//	        ExcelWriter excelWriter = new ExcelWriter();
//
//	        // Example list of values to write to Excel
//	        List<String> dataList = List.of("Value 1", "Value 2", "Value 3", "Value 4");
//
//	        try {
//	            excelWriter.writeListToExcel("output.xlsx", dataList);
//	            System.out.println("Data has been written to Excel successfully.");
//	        } catch (IOException e) {
//	            System.out.println("Error writing data to Excel: " + e.getMessage());
//	        }
//	    }
//


