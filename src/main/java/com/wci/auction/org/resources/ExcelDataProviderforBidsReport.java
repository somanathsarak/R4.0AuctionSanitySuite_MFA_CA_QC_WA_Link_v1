package com.wci.auction.org.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class ExcelDataProviderforBidsReport  {
	XSSFWorkbook wb;

	public ExcelDataProviderforBidsReport() throws Exception {
		File src = new File(folderPath+File.separator+"Bids Report - ALE1 06-23-2022.xlsx");
		System.out.println("path :"+folderPath+File.separator+"Bids Report - ALE1 06-23-2022.xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getStringData(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
	}

	public double getNumericData(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}
}
