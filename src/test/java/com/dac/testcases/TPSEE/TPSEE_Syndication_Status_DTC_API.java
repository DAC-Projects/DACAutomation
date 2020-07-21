package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Syndication_Status_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Syndication_Status_DTC_API extends BaseClass {

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

	@Test(priority = 5, description = "To verify Status of vendor after DTC API transmission")
	public void verifyDTCAPI() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
		wb.deleteEmptyRows();
		int row = 0;
		String Vendor, Status;
		for (int k = 4; k <= 4; k++) {
			String LocNum = wb.getCellValue(4, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			row = data.getLocationNumberRowNum(LocNum);
			System.out.println("The row number is :" + row);
			String vendors = wb.getCellValue(4, wb.seacrh_pattern("Vendors_Create", 0).get(0).intValue());
			System.out.println("The vendors listed are :" + vendors);
			VendorList = vendors.split(",");
			int size = VendorList.length;
			System.out.println("The size of list is :" + size);
			String status = wb.getCellValue(4, wb.seacrh_pattern("Status Text", 0).get(0).intValue());
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
