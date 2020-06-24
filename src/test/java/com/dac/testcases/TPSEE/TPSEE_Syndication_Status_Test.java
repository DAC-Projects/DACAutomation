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

public class TPSEE_Syndication_Status_Test extends BaseClass {

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

	/**
	 * Test to apply filters
	 * 
	 * @param Group
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 * @throws Exception
	 */
	@Test(priority = 3, groups = { "smoke" }, dependsOnMethods = {
			"navigatetosyndicationstatus" }, description = "Verify Data Syndication page loads after filter applied")
	public void verifyFilteringReportsVisibility() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_Syndication_Status_Page s = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
			for (int i = 1; i <= wb.getRowCount(); i++) {
				System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
				if (i > 1)
					CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode
						+ ", " + State + ", " + City + ", " + Location + "", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To get Number of Locations
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "To get Number of Location")
	public void GetNumberofLocation() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		data.getNumofLoc();
		addEvidence(CurrentState.getDriver(), "To get Number of Location", "yes");
	}

	/**
	 * To get Vendors from the table
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "To get Vendors List")
	public void GetVendorList() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		data.getvendorList();
		addEvidence(CurrentState.getDriver(), "To get Vendor List", "yes");
	}

	/**
	 * To get the location details
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6, description = "To verify Vendors for LPAD")
	public void VerifyvendorsinTable() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is" + LocNum);
		VendorList = data.getLocationDetailsLPAD(LocNum);
		addEvidence(CurrentState.getDriver(), "To Verify Vendor List", "yes");
	}

	/**
	 * To verify Status of Vendors manually/Api processed in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7, description = "To verify Status of Vendors processed in DTC")
	public void VerifyvendorsStatusinTableDTC() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is" + LocNum);
		data.getLocationDetailsDTCManualApi(LocNum);
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}

	/**
	 * To verify Status of Vendor Apple
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8, description = "To verify Status of Vendor Apple")
	public void VerifyvendorsStatusinTableDTCAppleVendor() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		String Vendor = wb.getCellValue(1, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		System.out.println("The Location Number is :" + Vendor);
		data.getLocationDetailsDTCVendors(LocNum, Vendor);
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}

	/**
	 * To verify Status of Vendor TomTom
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9, description = "To verify Status of Vendor TomTom")
	public void VerifyvendorsStatusinTableDTCVendorTomTom() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(2, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		String Vendor = wb.getCellValue(2, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		System.out.println("The Location Number is :" + Vendor);
		data.getLocationDetailsDTCVendors(LocNum, Vendor);
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}

	/**
	 * To verify Status of Vendor HERE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10, description = "To verify Status of Vendor HERE")
	public void VerifyvendorsStatusinTableDTCVendorHERE() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(3, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		String Vendor = wb.getCellValue(3, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		System.out.println("The Location Number is :" + Vendor);
		data.getLocationDetailsDTCVendors(LocNum, Vendor);
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}

	/**
	 * To verify Status of Vendor Zomato
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11, description = "To verify Status of Vendor Zomato")
	public void VerifyvendorsStatusinTableDTCVendorZomato() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(4, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		String Vendor = wb.getCellValue(4, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		System.out.println("The Location Number is :" + Vendor);
		data.getLocationDetailsDTCVendors(LocNum, Vendor);
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}
}
