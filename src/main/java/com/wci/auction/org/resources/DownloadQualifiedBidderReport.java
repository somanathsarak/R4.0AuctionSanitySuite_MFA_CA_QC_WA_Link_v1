package com.wci.auction.org.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.aventstack.extentreports.Status;
import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class DownloadQualifiedBidderReport {
	XSSFWorkbook wb;

	public DownloadQualifiedBidderReport() throws Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath+File.separator+"Qualified Bidders Report - "+eventNameEng+" "+ date1+".xlsx");
		System.out.println("filepath:="+folderPath+File.separator+"Qualified Bidders Report - "+eventNameEng+" "+ date1+".xlsx");
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

	public long getNumericData1(String Sheetname, int row, int col) {
		return (long) wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

	public void getQualifiedBidderReportData() throws Exception {
		DownloadQualifiedBidderReport report = new DownloadQualifiedBidderReport();

		String colomn1name = report.getStringData("Qualified Bidders Report", 5, 0);
		String regGenAccNo1 = report.getStringData("Qualified Bidders Report", 6, 0);
		String regGenAccNo2 = report.getStringData("Qualified Bidders Report", 7, 0);
		String regGenAccNo3 = report.getStringData("Qualified Bidders Report", 8, 0);
		String regGenAccNo4 = report.getStringData("Qualified Bidders Report", 9, 0);
		String regGenAccNo5 = report.getStringData("Qualified Bidders Report", 10, 0);

		// Entity legal name
		String colomn2name = report.getStringData("Qualified Bidders Report", 5, 1);
		String entityLegalName1 = report.getStringData("Qualified Bidders Report", 6, 1);
		String entityLegalName2 = report.getStringData("Qualified Bidders Report", 7, 1);
		String entityLegalName3 = report.getStringData("Qualified Bidders Report", 8, 1);
		String entityLegalName4 = report.getStringData("Qualified Bidders Report", 9, 1);
		String entityLegalName5 = report.getStringData("Qualified Bidders Report", 10, 1);

		// Entity Operating Name
		String colomn3name = report.getStringData("Qualified Bidders Report", 5, 2);
		String entityOperatingName1 = report.getStringData("Qualified Bidders Report", 6, 2);
		String entityOperatingName2 = report.getStringData("Qualified Bidders Report", 7, 2);
		String entityOperatingName3 = report.getStringData("Qualified Bidders Report", 8, 2);
		String entityOperatingName4 = report.getStringData("Qualified Bidders Report", 9, 2);
		String entityOperatingName5 = report.getStringData("Qualified Bidders Report", 10, 2);

		// Entity type
		String colomn4name = report.getStringData("Qualified Bidders Report", 5, 3);
		String entityType1 = report.getStringData("Qualified Bidders Report", 6, 3);
		String entityType2 = report.getStringData("Qualified Bidders Report", 7, 3);
		String entityType3 = report.getStringData("Qualified Bidders Report", 8, 3);
		String entityType4 = report.getStringData("Qualified Bidders Report", 9, 3);
		String entityType5 = report.getStringData("Qualified Bidders Report", 10, 3);

		// Entity Jurisdiction
		String colomn5name = report.getStringData("Qualified Bidders Report", 5, 4);
		String entJurisdiction1 = report.getStringData("Qualified Bidders Report", 6, 4);
		String entJurisdiction2 = report.getStringData("Qualified Bidders Report", 7, 4);
		String entJurisdiction3 = report.getStringData("Qualified Bidders Report", 8, 4);
		String entJurisdiction4 = report.getStringData("Qualified Bidders Report", 9, 4);
		String entJurisdiction5 = report.getStringData("Qualified Bidders Report", 10, 4);

		// FSA Acc No
		String colomn6name = report.getStringData("Qualified Bidders Report", 5, 5);
		String fsaAccNum1 = report.getStringData("Qualified Bidders Report", 6, 5);
		String fsaAccNum2 = report.getStringData("Qualified Bidders Report", 7, 5);
		String fsaAccNum3 = report.getStringData("Qualified Bidders Report", 8, 5);
		String fsaAccNum4 = report.getStringData("Qualified Bidders Report", 9, 5);
		String fsaAccNum5 = report.getStringData("Qualified Bidders Report", 10, 5);

		// Bid Guarantee Currency
		String colomn7name = report.getStringData("Qualified Bidders Report", 5, 5);
		String bidGuaranteeCurrency1 = report.getStringData("Qualified Bidders Report", 6, 6);
		String bidGuaranteeCurrency2 = report.getStringData("Qualified Bidders Report", 7, 6);
		String bidGuaranteeCurrency3 = report.getStringData("Qualified Bidders Report", 8, 6);
		String bidGuaranteeCurrency4 = report.getStringData("Qualified Bidders Report", 9, 6);
		String bidGuaranteeCurrency5 = report.getStringData("Qualified Bidders Report", 10, 6);

		// Total bid Guarantee value
		String colomn11name = report.getStringData("Qualified Bidders Report", 5, 11);
		double totalbidGuaranteeCurrency1 = report.getNumericData("Qualified Bidders Report", 6, 11);
		double totalbidGuaranteeCurrency2 = report.getNumericData("Qualified Bidders Report", 7, 11);
		double totalbidGuaranteeCurrency3 = report.getNumericData("Qualified Bidders Report", 8, 11);
		double totalbidGuaranteeCurrency4 = report.getNumericData("Qualified Bidders Report", 9, 11);
		double totalbidGuaranteeCurrency5 = report.getNumericData("Qualified Bidders Report", 10, 11);

		// Purchase limit for current auction
		String colomn12name = report.getStringData("Qualified Bidders Report", 5, 12);
		double purchaselimit1 = report.getNumericData("Qualified Bidders Report", 6, 12);
		double purchaselimit2 = report.getNumericData("Qualified Bidders Report", 7, 12);
		double purchaselimit3 = report.getNumericData("Qualified Bidders Report", 8, 12);
		double purchaselimit4 = report.getNumericData("Qualified Bidders Report", 9, 12);
		double purchaselimit5 = report.getNumericData("Qualified Bidders Report", 10, 12);

		// Holding limit for current auction
		String colomn14name = report.getStringData("Qualified Bidders Report", 5, 14);
		long holdingLimit1 = report.getNumericData1("Qualified Bidders Report", 6, 14);
		long holdingLimit2 = report.getNumericData1("Qualified Bidders Report", 7, 14);
		long holdingLimit3 = report.getNumericData1("Qualified Bidders Report", 8, 14);
		long holdingLimit4 = report.getNumericData1("Qualified Bidders Report", 9, 14);
		long holdingLimit5 = report.getNumericData1("Qualified Bidders Report", 10, 14);


		System.out.println(colomn1name + " : " + regGenAccNo1 + ",  " + colomn2name + " : " + entityLegalName1 + ",  "
				+ colomn3name + " : " + entityOperatingName1 + ",  " + colomn4name + " : " + entityType1 + ",  "
				+ colomn5name + " : " + entJurisdiction1 + ",  " + colomn6name + " : " + fsaAccNum1 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency1 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency1 + ",  " + colomn12name + " : " + purchaselimit1 + ",  " + colomn14name
				+ " : " + holdingLimit1);

		ObjectRepo.getTest().log(Status.PASS, colomn1name + " : " + regGenAccNo1 + ",  " + colomn2name + " : " + entityLegalName1 + ",  "
				+ colomn3name + " : " + entityOperatingName1 + ",  " + colomn4name + " : " + entityType1 + ",  "
				+ colomn5name + " : " + entJurisdiction1 + ",  " + colomn6name + " : " + fsaAccNum1 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency1 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency1 + ",  " + colomn12name + " : " + purchaselimit1 + ",  " + colomn14name
				+ " : " + holdingLimit1);
		
		System.out.println(colomn1name + " : " + regGenAccNo2 + ",  " + colomn2name + " : " + entityLegalName2 + ",  "
				+ colomn3name + " : " + entityOperatingName2 + ",  " + colomn4name + " : " + entityType2 + ",  "
				+ colomn5name + " : " + entJurisdiction2 + ",  " + colomn6name + " : " + fsaAccNum2 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency2 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency2 + ",  " + colomn12name + " : " + purchaselimit2 + ",  " + colomn14name
				+ " : " + holdingLimit2);
		
		ObjectRepo.getTest().log(Status.PASS, colomn1name + " : " + regGenAccNo2 + ",  " + colomn2name + " : " + entityLegalName2 + ",  "
				+ colomn3name + " : " + entityOperatingName2 + ",  " + colomn4name + " : " + entityType2 + ",  "
				+ colomn5name + " : " + entJurisdiction2 + ",  " + colomn6name + " : " + fsaAccNum2 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency2 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency2 + ",  " + colomn12name + " : " + purchaselimit2 + ",  " + colomn14name
				+ " : " + holdingLimit2);

		System.out.println(colomn1name + " : " + regGenAccNo3 + ",  " + colomn2name + " : " + entityLegalName3 + ",  "
				+ colomn3name + " : " + entityOperatingName3 + ",  " + colomn4name + " : " + entityType3 + ",  "
				+ colomn5name + " : " + entJurisdiction3 + ",  " + colomn6name + " : " + fsaAccNum3 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency3 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency3 + ",  " + colomn12name + " : " + purchaselimit3 + ",  " + colomn14name
				+ " : " + holdingLimit3);
		
		ObjectRepo.getTest().log(Status.PASS, colomn1name + " : " + regGenAccNo3 + ",  " + colomn2name + " : " + entityLegalName3 + ",  "
				+ colomn3name + " : " + entityOperatingName3 + ",  " + colomn4name + " : " + entityType3 + ",  "
				+ colomn5name + " : " + entJurisdiction3 + ",  " + colomn6name + " : " + fsaAccNum3 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency3 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency3 + ",  " + colomn12name + " : " + purchaselimit3 + ",  " + colomn14name
				+ " : " + holdingLimit3);

		System.out.println(colomn1name + " : " + regGenAccNo4 + ",  " + colomn2name + " : " + entityLegalName4 + ",  "
				+ colomn3name + " : " + entityOperatingName4 + ",  " + colomn4name + " : " + entityType4 + ",  "
				+ colomn5name + " : " + entJurisdiction4 + ",  " + colomn6name + " : " + fsaAccNum4 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency4 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency4 + ",  " + colomn12name + " : " + purchaselimit4 + ",  " + colomn14name
				+ " : " + holdingLimit4);
		
		ObjectRepo.getTest().log(Status.PASS, colomn1name + " : " + regGenAccNo4 + ",  " + colomn2name + " : " + entityLegalName4 + ",  "
				+ colomn3name + " : " + entityOperatingName4 + ",  " + colomn4name + " : " + entityType4 + ",  "
				+ colomn5name + " : " + entJurisdiction4 + ",  " + colomn6name + " : " + fsaAccNum4 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency4 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency4 + ",  " + colomn12name + " : " + purchaselimit4 + ",  " + colomn14name
				+ " : " + holdingLimit4);

		System.out.println(colomn1name + " : " + regGenAccNo5 + ",  " + colomn2name + " : " + entityLegalName5 + ",  "
				+ colomn3name + " : " + entityOperatingName5 + ",  " + colomn4name + " : " + entityType5 + ",  "
				+ colomn5name + " : " + entJurisdiction5 + ",  " + colomn6name + " : " + fsaAccNum5 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency5 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency5 + ",  " + colomn12name + " : " + purchaselimit5 + ",  " + colomn14name
				+ " : " + holdingLimit5);
		
		ObjectRepo.getTest().log(Status.PASS, colomn1name + " : " + regGenAccNo5 + ",  " + colomn2name + " : " + entityLegalName5 + ",  "
				+ colomn3name + " : " + entityOperatingName5 + ",  " + colomn4name + " : " + entityType5 + ",  "
				+ colomn5name + " : " + entJurisdiction5 + ",  " + colomn6name + " : " + fsaAccNum5 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency5 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency5 + ",  " + colomn12name + " : " + purchaselimit5 + ",  " + colomn14name
				+ " : " + holdingLimit5);

	}

}
