package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_DuplicateManagement_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_DuplicateManagement_Test extends BaseClass {

	TPSEE_DuplicateManagement_Page data;
	Navigationpage np;

	/**
	 * To Navigate to Duplicate Management Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void NavigateToDupManagementPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToDuplicateManagement();
		CurrentState.getLogger().log(Status.PASS, "Navigated to Duplicate Managment Page");
		addEvidence(CurrentState.getDriver(), "To Navigate to Duplicate Management Page", "yes");
	}

	/**
	 * To Verify Title and Title Text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, groups = {"smoke" }, description = "Test for verifying title and description of report")
	public void VerifyTitleTxt() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		data.VerifyTitlenText();
		addEvidence(CurrentState.getDriver(), "To Verify Title and Title Text", "yes");
	}

	/**
	 * To Add Potential Duplicate manually
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void AddDupList() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Url = wb.getCellValue(1, wb.seacrh_pattern("URL", 0).get(0).intValue());
		System.out.println("The URL Provided is :" + Url);
		data.AddPotentialDuplicate(PhNumber,
				"https://www.google.com/maps/place/?q=place_id:ChIJrTehGtCVwokRrYtNXQff1NI");
		addEvidence(CurrentState.getDriver(), "To Add Duplicate Listing", "yes");
	}

	/**
	 * Test to take action on Duplicate Listing
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void TakeActiononDupList() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Action = wb.getCellValue(1, wb.seacrh_pattern("Action Value", 0).get(0).intValue());
		System.out.println("The action preformed is : " + Action);
		data.TakeAction(PhNumber, Action);
		addEvidence(CurrentState.getDriver(), "To take action on listing URL's", "yes");
	}

	/**
	 * Test to verify status
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void verifyStatus() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(1, wb.seacrh_pattern("Status Text", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Status", "yes");
	}

	/**
	 * Test to verify Ignore Scenario in Pot_Dup Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6)
	public void verifyPotentialDup() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocationNumber = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocationNumber);
		data.Pot_Dup(LocationNumber);
		addEvidence(CurrentState.getDriver(), "To take action on Potential Duplicate Tab", "yes");

	}

	/**
	 * Test to verify Fix in Pot_Dup and Ignore the same with Pending Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void PendingTabVerification() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocationNumber = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocationNumber);
		data.VerifyIgnore_PendingTab(LocationNumber);
		addEvidence(CurrentState.getDriver(), "To verify potential duplicate in Pending Tab", "yes");
	}
}