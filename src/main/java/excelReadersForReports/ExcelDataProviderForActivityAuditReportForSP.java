package excelReadersForReports;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class ExcelDataProviderForActivityAuditReportForSP {
	XSSFWorkbook wb;

	public ExcelDataProviderForActivityAuditReportForSP() throws Exception {
		File src = new File(folderPath+File.separator+"User Activity Audit Report.xlsx");
		String Path = folderPath+File.separator+"User Activity Audit Report.xlsx";
		String newString = Path.substring(113);
		System.out.println(newString);
		Assert.assertEquals("User Activity Audit Report.xlsx", newString);
		System.out.println("Report title verified successfully ");
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