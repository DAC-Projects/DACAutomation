package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Syndication_Status_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Syndication_Status_DTC_VendorsVerification extends BaseClass {

	TPSEE_Syndication_Status_Page data;
	Navigationpage np;
	String[] VendorList;
	String[] StatusList;
	SoftAssert soft = new SoftAssert();

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
	 * Test to apply filters
	 * 
	 * @param Group
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 * @throws Exception
	 *//*
	@Test(priority = 2, groups = { "smoke" }, dependsOnMethods = {
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
*/
/*	*//**
	 * To verify Status of Vendor Apple
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 3, description = "To verify Status of Vendor Apple")
	public void VerifyvendorsStatusinTableDTCAppleVendor() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		int row = 0;
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		row = data.getLocationNumberRowNum(LocNum);
		String Vendor = wb.getCellValue(1, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		String Status = wb.getCellValue(1, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The status is :" + Status);
		System.out.println("The Vendor is :" + Vendor);
		data.verifyStatus(Vendor, Status, row, soft, LocNum);
		soft.assertAll();
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}

	*//**
	 * To verify Status of Vendor TomTom
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 4, description = "To verify Status of Vendor TomTom")
	public void VerifyvendorsStatusinTableDTCVendorTomTom() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		int row = data.getLocationNumberRowNum(LocNum);
		String Vendor = wb.getCellValue(2, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		System.out.println("The vendor is :" + Vendor);
		String Status = wb.getCellValue(2, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The status is :" + Status);
		data.verifyStatus(Vendor, Status, row, soft, LocNum);
		soft.assertAll();
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}

	*//**
	 * To verify Status of Vendor HERE
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 5, description = "To verify Status of Vendor HERE")
	public void VerifyvendorsStatusinTableDTCVendorHERE() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		int row = data.getLocationNumberRowNum(LocNum);
		String Vendor = wb.getCellValue(3, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		System.out.println("The vendor is :" + Vendor);
		String Status = wb.getCellValue(3, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The status is :" + Status);
		data.verifyStatus(Vendor, Status, row, soft, LocNum);
		soft.assertAll();
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}

	*//**
	 * To verify Status of Vendor Zomato
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 6, description = "To verify Status of Vendor Zomato")
	public void VerifyvendorsStatusinTableDTCVendorZomato() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Syndication");
		wb.deleteEmptyRows();
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		int row = data.getLocationNumberRowNum(LocNum);
		String Vendor = wb.getCellValue(4, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
		System.out.println("The vendor is :" + Vendor);
		String Status = wb.getCellValue(4, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The status is :" + Status);
		data.verifyStatus(Vendor, Status, row, soft, LocNum);
		soft.assertAll();
		addEvidence(CurrentState.getDriver(), "To Verify Status of Vendors of DTC", "yes");
	}*/
	
	@Test(priority = 3, description = "To verify Status of vendor Apple,Zomato,TomTom,HERE")
	public void verifyDTCVendors() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
		wb.deleteEmptyRows();
		int row = 0;
		String Vendor, Status;
		for (int k = 1; k <= 1; k++) {
			String LocNum = wb.getCellValue(k, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			row = data.getLocationNumberRowNum(LocNum);
			System.out.println("The row number is :" + row);
			String vendors = wb.getCellValue(k, wb.seacrh_pattern("Vendors_Create", 0).get(0).intValue());
			System.out.println("The vendors listed are :" + vendors);
			VendorList = vendors.split(",");
			int size = VendorList.length;
			System.out.println("The size of list is :" + size);
			String status = wb.getCellValue(k, wb.seacrh_pattern("Status Text", 0).get(0).intValue());
			System.out.println("The status is :" + status);
			StatusList = status.split(",");
			for (int i = 0; i <= size - 1; i++) {
				try {
					Vendor = VendorList[i];
					System.out.println("The Vendor is :" + Vendor);
					Status = StatusList[i].trim();
					try {
						data.verifyStatus(Vendor, Status, row, soft, LocNum);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if (!Status.equals("In Progress")) {
							data.verifydatesumbitted(row, Vendor, soft);
						} else {
							System.out.println("Date is not available");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						data.verifyNotes(Vendor, row, soft);
					} catch (Exception e) {
						System.out.println("No vendors");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			soft.assertAll();
			addEvidence(CurrentState.getDriver(), "To verify status of Vendors processed in DTC", "yes");
		}
	}

}
