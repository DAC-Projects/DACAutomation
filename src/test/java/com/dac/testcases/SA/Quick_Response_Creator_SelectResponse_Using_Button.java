package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Quick_Responses;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Quick_Response_Creator_SelectResponse_Using_Button extends BaseClass {
	
	Navigationpage np;
	Quick_Responses data;
	
	@Test(priority = 1, description = "Test to navigate to Response Management Page")
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 2, description = "Test to use existing response")
	public void AddExistingQuickResponse() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The quick response to be added is : " +group);
		data = new Quick_Responses(CurrentState.getDriver());
		data.AddResponseusingexisting(group);
		CurrentState.getDriver().navigate().refresh();
	}
	
	@Test(priority = 3, description = "Test to verify Response Added")
	public void verifyResponseaddedandStatus() {
		data = new Quick_Responses(CurrentState.getDriver());
		data.verifyResponseAddedUsingButtonStatus();
	}
	
	@Test(priority = 4, description = "Test to navigate to Response Management")
	public void NavigateToResponseManagement() {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToResponseManagement();
	}
	
	@Test(priority = 5, description = "Test to select status")
	public void SelectStatus() throws InterruptedException {
		data = new Quick_Responses(CurrentState.getDriver());
		data.selectPendingStatus();
	}

	@Test(priority = 6, description = "Test to edit the response added", dependsOnMethods = "SelectStatus")
	public void EditResponseAdded() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String quickResponse = wb.getCellValue(1, wb.seacrh_pattern("AddResponse", 0).get(0).intValue());
		System.out.println("The quick response to be added is : " +quickResponse);
		data = new Quick_Responses(CurrentState.getDriver());
		data.EditResponse(quickResponse);
	}
	
	@Test(priority = 7, description = "Test to delete added response")
	public void DeleteResponse() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String quickResponse = wb.getCellValue(1, wb.seacrh_pattern("AddResponse", 0).get(0).intValue());
		System.out.println("The quick response to be added is : " +quickResponse);
		data = new Quick_Responses(CurrentState.getDriver());
		data.DeleteResponse(quickResponse);
		np.navigateToSA_ReviewsFeed();
	}
	
	@Test(priority = 8, description = "Test to verify response deleted")
	public void verifyResponseDeleted() throws Exception {
		data = new Quick_Responses(CurrentState.getDriver());
		data.verifyDeletedResponse();
	}
}
