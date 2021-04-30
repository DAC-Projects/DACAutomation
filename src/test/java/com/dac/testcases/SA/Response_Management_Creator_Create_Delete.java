package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Response_Management;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Response_Management_Creator_Create_Delete extends BaseClass{

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
		String ReviewSelected = wb.getCellValue(1, wb.seacrh_pattern("Review", 0).get(0).intValue());
		System.out.println("The Review selected is : " +ReviewSelected);
		String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
		System.out.println("The Response to be added is : " +ResponseSelected);
		data.AddResponse(ResponseSelected);
		}
	}
	
	@Test(priority = 4, description = "Test to navigate to Response Management Page", dependsOnMethods = {"VerifyResponseAdded"})
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
	
	@Test(priority = 5, description = "Test to change the status to pending approval", dependsOnMethods = {"NavigateToResponseManagement"})
	public void ChangeStatusToPending() throws Exception{
		data = new Response_Management(CurrentState.getDriver());
		data.selectPendingStatus();
		addEvidence(CurrentState.getDriver(), "Test to change status to pending approval", "yes");
	}
	
	@Test(priority = 6, description = "Test to Delete Response without editing/approve/reject", dependsOnMethods = {"ChangeStatusToPending"})
	public void Delete() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			data.DeleteResponse(ResponseSelected);
			Thread.sleep(3000);
			NavigateToReviewsFeed();
		}
	}
	
	@Test(priority = 7, description = "verify deleted Response", dependsOnMethods = {"Delete"})
	public void DeleteResponse() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.verifyDeletedResponse();
	}
}
