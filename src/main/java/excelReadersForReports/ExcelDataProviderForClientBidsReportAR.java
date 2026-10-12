package excelReadersForReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import static com.wci.auction.org.resources.CommonMethods.folderPath;
public class ExcelDataProviderForClientBidsReportAR {
	XSSFWorkbook wb;

	public ExcelDataProviderForClientBidsReportAR() throws Exception {
		File src = new File(folderPath+File.separator+"Client Bids Report - Auction 187 09-14-2022 QC4446-5674.xlsx");
		String Path = folderPath+File.separator+"Client Bids Report - Auction 187 09-14-2022.xlsx";
		String newString = Path.substring(113);
		System.out.println(newString);
		Assert.assertEquals("Client Bids Report - Auction 187 09-14-2022.xlsx", newString);
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