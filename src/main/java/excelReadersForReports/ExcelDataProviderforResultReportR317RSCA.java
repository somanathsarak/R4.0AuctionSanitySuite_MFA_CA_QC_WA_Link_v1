package excelReadersForReports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.ObjectRepo;

import static com.wci.auction.org.resources.CommonMethods.folderPath;
import static com.wci.auction.org.resources.BaseClass.prop;
public class ExcelDataProviderforResultReportR317RSCA  {
	XSSFWorkbook wb;

	public ExcelDataProviderforResultReportR317RSCA() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("Event_Name_CA1") + " "
				+ date1 + ".xlsx");

		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(7);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						System.out.print(cell.toString() + " ");
					}
					System.out.println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	{

	}

	public String getStringData(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
	}

	public double getNumericData(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

	{

	}

}