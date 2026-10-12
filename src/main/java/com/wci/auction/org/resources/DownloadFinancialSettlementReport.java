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

public class DownloadFinancialSettlementReport extends CommonLib {
	XSSFWorkbook wb;

	public DownloadFinancialSettlementReport() throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Financial Settlement Report - " + eventNameEng + " " + date1 + ".xlsx");
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

	public void getDataFromFinancialSettlementReport() throws Exception {

		DownloadFinancialSettlementReport ref = new DownloadFinancialSettlementReport();
		

		String currentSettPriceUSD = ref.getStringData("Financial Settlement Report", 4, 0);
		String currentSettPriceCAD = ref.getStringData("Financial Settlement Report", 5, 0);
		String settlementpriceUSD = ref.getStringData("Financial Settlement Report", 4, 1);
		double settlementpriceCAD = ref.getNumericData("Financial Settlement Report", 5, 1);

		String Colomn1 = ref.getStringData("Financial Settlement Report", 7, 0);
		String regGenAccNo1 = ref.getStringData("Financial Settlement Report", 8, 0);
		String regGenAccNo2 = ref.getStringData("Financial Settlement Report", 9, 0);
		String regGenAccNo3 = ref.getStringData("Financial Settlement Report", 10, 0);
		String regGenAccNo4 = ref.getStringData("Financial Settlement Report", 11, 0);
		String regGenAccNo5 = ref.getStringData("Financial Settlement Report", 12, 0);

		String Colomn12 = ref.getStringData("Financial Settlement Report", 7, 11);
		double totalBidGuarantee1 = ref.getNumericData("Financial Settlement Report", 8, 11);
		double totalBidGuarantee2 = ref.getNumericData("Financial Settlement Report", 9, 11);
		double totalBidGuarantee3 = ref.getNumericData("Financial Settlement Report", 10, 11);
		double totalBidGuarantee4 = ref.getNumericData("Financial Settlement Report", 11, 11);
		double totalBidGuarantee5 = ref.getNumericData("Financial Settlement Report", 12, 11);

		String Colomn13 = ref.getStringData("Financial Settlement Report", 7, 12);
		double currentSuccBidAllowances1 = ref.getNumericData("Financial Settlement Report", 8, 12);
		double currentSuccBidAllowances2 = ref.getNumericData("Financial Settlement Report", 9, 12);
		double currentSuccBidAllowances3 = ref.getNumericData("Financial Settlement Report", 10, 12);
		double currentSuccBidAllowances4 = ref.getNumericData("Financial Settlement Report", 11, 12);
		double currentSuccBidAllowances5 = ref.getNumericData("Financial Settlement Report", 12, 12);

		String Colomn15 = ref.getStringData("Financial Settlement Report", 7, 14);
		double totalsuccessfullbidcost1 = ref.getNumericData("Financial Settlement Report", 8, 14);
		double totalsuccessfullbidcost2 = ref.getNumericData("Financial Settlement Report", 9, 14);
		double totalsuccessfullbidcost3 = ref.getNumericData("Financial Settlement Report", 10, 14);
		double totalsuccessfullbidcost4 = ref.getNumericData("Financial Settlement Report", 11, 14);
		double totalsuccessfullbidcost5 = ref.getNumericData("Financial Settlement Report", 12, 14);

		String Colomn16 = ref.getStringData("Financial Settlement Report", 7, 15);
		double amountOwed1 = ref.getNumericData("Financial Settlement Report", 8, 15);
		double amountOwed2 = ref.getNumericData("Financial Settlement Report", 9, 15);
		double amountOwed3 = ref.getNumericData("Financial Settlement Report", 10, 15);
		double amountOwed4 = ref.getNumericData("Financial Settlement Report", 11, 15);
		double amountOwed5 = ref.getNumericData("Financial Settlement Report", 12, 15);

		String Colomn17 = ref.getStringData("Financial Settlement Report", 7, 16);
		String amountOwedBy1 = ref.getStringData("Financial Settlement Report", 8, 16);
		String amountOwedBy2 = ref.getStringData("Financial Settlement Report", 9, 16);
		String amountOwedBy3 = ref.getStringData("Financial Settlement Report", 10, 16);
		String amountOwedBy4 = ref.getStringData("Financial Settlement Report", 11, 16);
		String amountOwedBy5 = ref.getStringData("Financial Settlement Report", 12, 16);

		System.out.println(currentSettPriceUSD + " : " + settlementpriceUSD);
		System.out.println(currentSettPriceCAD + " : " + settlementpriceCAD);

		System.out.println(Colomn1 + " : " + regGenAccNo1 + " , " + Colomn12 + " : " + totalBidGuarantee1 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances1 + " , " + Colomn15 + " : " + totalsuccessfullbidcost1
				+ " , " + Colomn16 + " : " + amountOwed1 + " , " + Colomn17 + " : " + amountOwedBy1);
		System.out.println(Colomn1 + " : " + regGenAccNo2 + " , " + Colomn12 + " : " + totalBidGuarantee2 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances2 + " , " + Colomn15 + " : " + totalsuccessfullbidcost2
				+ " , " + Colomn16 + " : " + amountOwed2 + " , " + Colomn17 + " : " + amountOwedBy2);
		System.out.println(Colomn1 + " : " + regGenAccNo3 + " , " + Colomn12 + " : " + totalBidGuarantee3 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances3 + " , " + Colomn15 + " : " + totalsuccessfullbidcost3
				+ " , " + Colomn16 + " : " + amountOwed3 + " , " + Colomn17 + " : " + amountOwedBy3);
		System.out.println(Colomn1 + " : " + regGenAccNo4 + " , " + Colomn12 + " : " + totalBidGuarantee4 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances4 + " , " + Colomn15 + " : " + totalsuccessfullbidcost4
				+ " , " + Colomn16 + " : " + amountOwed4 + " , " + Colomn17 + " : " + amountOwedBy4);
		System.out.println(Colomn1 + " : " + regGenAccNo5 + " , " + Colomn12 + " : " + totalBidGuarantee5 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances5 + " , " + Colomn15 + " : " + totalsuccessfullbidcost5
				+ " , " + Colomn16 + " : " + amountOwed5 + " , " + Colomn17 + " : " + amountOwedBy5);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo1 + " , " + Colomn12 + " : " + totalBidGuarantee1 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances1 + " , " + Colomn15 + " : " + totalsuccessfullbidcost1
						+ " , " + Colomn16 + " : " + amountOwed1 + " , " + Colomn17 + " : " + amountOwedBy1);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo2 + " , " + Colomn12 + " : " + totalBidGuarantee2 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances2 + " , " + Colomn15 + " : " + totalsuccessfullbidcost2
						+ " , " + Colomn16 + " : " + amountOwed2 + " , " + Colomn17 + " : " + amountOwedBy2);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo3 + " , " + Colomn12 + " : " + totalBidGuarantee3 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances3 + " , " + Colomn15 + " : " + totalsuccessfullbidcost3
						+ " , " + Colomn16 + " : " + amountOwed3 + " , " + Colomn17 + " : " + amountOwedBy3);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo4 + " , " + Colomn12 + " : " + totalBidGuarantee4 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances4 + " , " + Colomn15 + " : " + totalsuccessfullbidcost4
						+ " , " + Colomn16 + " : " + amountOwed4 + " , " + Colomn17 + " : " + amountOwedBy4);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo5 + " , " + Colomn12 + " : " + totalBidGuarantee5 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances5 + " , " + Colomn15 + " : " + totalsuccessfullbidcost5
						+ " , " + Colomn16 + " : " + amountOwed5 + " , " + Colomn17 + " : " + amountOwedBy5);

	}

	
}