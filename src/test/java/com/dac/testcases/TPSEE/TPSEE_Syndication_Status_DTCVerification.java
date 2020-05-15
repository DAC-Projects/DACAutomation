package com.dac.testcases.TPSEE;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Syndication_Status_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Syndication_Status_DTCVerification extends BaseClass {

	TPSEE_Syndication_Status_Page data;
	Navigationpage np;
	List<String> VendorList = new ArrayList<String>();

	/**
	 * Test to navigate to Syndication Status Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "To Navigate to Syndication Report")
	public void navigatetosyndicationstatus() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		Thread.sleep(5000);
		np.navigateToSyndicationStatus();
		CurrentState.getLogger().log(Status.PASS, "Navigated to Syndication Report Page");
		addEvidence(CurrentState.getDriver(), "To Navigate to Syndication Report Page", "yes");
	}

	/**
	 * Test to verify Title and Title text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, dependsOnMethods = {
			"navigatetosyndicationstatus" }, description = "To Verify Title and Title Text")
	public void VerifyTitleTxt() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		data.VerifyTitlenText();
		addEvidence(CurrentState.getDriver(), "To Verify Title and Title Text", "yes");
	}

	@Test(priority = 3, description = "To verify Status of vendor after DTC transmission")
	public void verifyDTCManualApi() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication DTCManualApi");
		wb.deleteEmptyRows();
		int row = 0;
		String Vendor, Status;
		for (int k = 1; k <= wb.getRowCount(); k++) {
			String LocNum = wb.getCellValue(k, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			row = data.getLocationNumberRowNum(LocNum);
			if (!LocNum.equals(null)) {
				for (int i = 1; i <= wb.getRowCount(); i++) {
					Vendor = wb.getCellValue(i, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
					System.out.println("The Vendor is :" + Vendor);
					Status = wb.getCellValue(i, wb.seacrh_pattern("Status", 0).get(0).intValue());
					try {
						data.verifyStatus(Vendor, Status, row);
						System.out.println("The row number is :" + row);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						data.verifydatesumbitted(row, Vendor);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		addEvidence(CurrentState.getDriver(), "To verify status of Vendors processed in DTC", "yes");
		CurrentState.getDriver().navigate().refresh();
	}

	/**
	 * To verify Status of Vendor Apple
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "To verify Status of Vendor Apple")
	public void VerifyvendorsStatusinTableDTCAppleVendor() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		int row = 0;
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String LocNum = wb.getCellValue(i, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			row = data.getLocationNumberRowNum(LocNum);
			String Vendor = wb.getCellValue(1, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
			System.out.println("The Location Number is :" + Vendor);
			String Status = wb.getCellValue(1, wb.seacrh_pattern("Status", 0).get(0).intValue());
			System.out.println("The status is :" + Status);
			data.verifyStatus(Vendor, Status, row);
			addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
			CurrentState.getDriver().navigate().refresh();
		}
	}

	/**
	 * To verify Status of Vendor TomTom
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "To verify Status of Vendor TomTom")
	public void VerifyvendorsStatusinTableDTCVendorTomTom() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String LocNum = wb.getCellValue(i, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			int row = data.getLocationNumberRowNum(LocNum);
			String Vendor = wb.getCellValue(2, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
			System.out.println("The Location Number is :" + Vendor);
			String Status = wb.getCellValue(2, wb.seacrh_pattern("Status", 0).get(0).intValue());
			System.out.println("The status is :" + Status);
			data.verifyStatus(Vendor, Status, row);
			addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
			CurrentState.getDriver().navigate().refresh();
		}
	}

	/**
	 * To verify Status of Vendor HERE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6, description = "To verify Status of Vendor HERE")
	public void VerifyvendorsStatusinTableDTCVendorHERE() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String LocNum = wb.getCellValue(i, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			int row = data.getLocationNumberRowNum(LocNum);
			String Vendor = wb.getCellValue(3, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
			System.out.println("The Location Number is :" + Vendor);
			String Status = wb.getCellValue(3, wb.seacrh_pattern("Status", 0).get(0).intValue());
			System.out.println("The status is :" + Status);
			data.verifyStatus(Vendor, Status, row);
			addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
			CurrentState.getDriver().navigate().refresh();
		}
	}

	/**
	 * To verify Status of Vendor Zomato
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7, description = "To verify Status of Vendor Zomato")
	public void VerifyvendorsStatusinTableDTCVendorZomato() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String LocNum = wb.getCellValue(i, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			int row = data.getLocationNumberRowNum(LocNum);
			String Vendor = wb.getCellValue(4, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
			System.out.println("The Location Number is :" + Vendor);
			String Status = wb.getCellValue(4, wb.seacrh_pattern("Status", 0).get(0).intValue());
			System.out.println("The status is :" + Status);
			data.verifyStatus(Vendor, Status, row);
			addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
			CurrentState.getDriver().navigate().refresh();
		}
	}
}
