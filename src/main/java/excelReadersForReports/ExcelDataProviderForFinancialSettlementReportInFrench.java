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
public class ExcelDataProviderForFinancialSettlementReportInFrench {
	XSSFWorkbook wb;

	public ExcelDataProviderForFinancialSettlementReportInFrench() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		// get current date time with Date()
		Date date = new Date();
		// Now format the date
		String date1 = dateFormat.format(date);
		File src = new File(folderPath+File.separator+"Rapport des paiements - " + eventNameFR + " " + date1 + ".xlsx");
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