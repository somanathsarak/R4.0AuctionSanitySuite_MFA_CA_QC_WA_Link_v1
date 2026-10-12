package excelReadersForReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.CommonMethods.folderPath;
public class ExcelDataProviderforResultReportQC_Single_Auction {
	XSSFWorkbook wb;

	public ExcelDataProviderforResultReportQC_Single_Auction() throws Exception {
//		File src = new File("C:\\Users\\VishalGiram\\git\\wci-ap-endtoend-automation\\TestData\\Results Report - JOINT AUCTION NO LINK RnD 2 2 06-29-2022.xlsx");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);
		File src = new File(folderPath+File.separator+"Results Report - " + eventNameQC + " " + date1 + ".xlsx");
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
