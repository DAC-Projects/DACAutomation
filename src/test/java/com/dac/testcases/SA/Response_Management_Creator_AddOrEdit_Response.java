package com.dac.testcases.SA;


import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Response_Management;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Response_Management_Creator_AddOrEdit_Response extends BaseClass {

	Navigationpage np;
	Response_Management data;
	String From_Date = "//*[@id='dateFrom']";
	String To_Date = "//*[@id='dateTo']";
	
	@Test(priority = 1, description = "Test to navigate to Reviews Feed")
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 2, description = "Test to add response in Respond to Reviews Page")
	public void VerifyResponseAdded() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
		String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
		System.out.println("The Response to be added is : " +ResponseSelected);
		data.AddResponse(ResponseSelected);
		}
	}
	
	@Test(priority = 3, description = "Test to navigate to Response Management Page", dependsOnMethods = {"VerifyResponseAdded"})
	public void NavigateToResponseManagement() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			data.clickManageLink(ResponseSelected);
			addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
		}
	}
	
	@Test(priority = 4, description = "Test to change the status to pending approval" , dependsOnMethods = {"NavigateToResponseManagement"})
	public void ChangeStatusToPending() throws Exception{
		data = new Response_Management(CurrentState.getDriver());
		data.selectPendingStatus();
		addEvidence(CurrentState.getDriver(), "Test to change status to pending approval", "yes");
		
	}
	
	@Test(priority = 5, description = "Test to verify Edit Response and verify Count" , dependsOnMethods = {"ChangeStatusToPending"})
	public void EditResponseAndVerifyHistory() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			data.EditResponseAndVerifyCountandStatus(ResponseSelected);
		}
	}
}
