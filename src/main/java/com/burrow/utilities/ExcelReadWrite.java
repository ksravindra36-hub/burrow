package com.burrow.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReadWrite {
	static Workbook workbook;
	static Sheet sheet;
	static String path =System.getProperty("user.dir")+"\\src\\main\\resources\\excels\\testdata.xlsx"; 
	static {
		try {
			workbook = WorkbookFactory.create(new File(path));
			sheet = workbook.getSheet("Sheet1");
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void printCellValue() {
        try {
          //  Workbook workbook = WorkbookFactory.create(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excels\\testdata.xlsx"));
          //  Sheet sheet = workbook.getSheet("Sheet1"); // Replace "Sheet1" with your sheet name
            
            for (Row row : sheet) {
                for (Cell cell : row) {
                    CellType cellType = cell.getCellType();
                    if (cellType == CellType.STRING) {
                        String cellValue = cell.getStringCellValue();
                        System.out.print(cellValue + "\t");
                    } else if (cellType == CellType.NUMERIC) {
                        double cellValue = cell.getNumericCellValue();
                        System.out.print(cellValue + "\t");
                    }
                }
                System.out.println();
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        //public static void main(String[] args) {
//            try {
//                Workbook workbook = WorkbookFactory.create(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excels\\testdata.xlsx"));
//                Sheet sheet = workbook.getSheet("Sheet1"); // Replace "Sheet1" with your sheet name
//                
//                // Write data to the Excel file
//                Row row = sheet.createRow(0); // Creating a new row at index 0
//                Cell cell = row.createCell(0); // Creating a new cell at column 0
//                cell.setCellValue("Hello, World!"); // Setting the cell value
//                
//                // Save the changes to the Excel file
//                FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\excels\\testdata.xlsx");
//                workbook.write(outputStream);
//                workbook.close();
//                outputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
    }
    
    public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
	}
    
    public static int getColumnCount(String sheetName) {
    	int numberOfColumns = 0;
	    Row firstRow = sheet.getRow(0);
	    return numberOfColumns = firstRow.getLastCellNum();
    }
    
    public static String getCellData(String sheetName, int rowNum, int colNum) {
        String cellData = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            cellData = cell.toString();
            fileInputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellData;
    }
}
