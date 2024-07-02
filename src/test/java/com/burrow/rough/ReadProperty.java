package com.burrow.rough;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadProperty {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		 Workbook workbook = WorkbookFactory.create(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\excels\\testdata.xlsx"));
         Sheet sheet = workbook.getSheet("Sheet1");
         int rowCount = sheet.getPhysicalNumberOfRows();
         int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

         Object[][] testData = new Object[rowCount - 1][colCount];

         for (int i = 1; i < rowCount; i++) {
             Row row = sheet.getRow(i);
             for (int j = 0; j < colCount; j++) {
                 Cell cell = row.getCell(j);
                 testData[i - 1][j] = cell.getStringCellValue();
                 System.out.print(testData[i - 1][j] + "\t");
             }
             System.out.println();
         }
         System.out.println(testData);
	}
}
