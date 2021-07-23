package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Quick_Responses;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Quick_Responses_Approver_EditandVerify extends BaseClass {

	Navigationpage np;
	Quick_Responses data;
	
	@Test(priority = 1, description = "Test to navigate to Response Management Page")
	public void NavigateToResponseManagement() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 2, dependsOnMethods= {"NavigateToResponseManagement"}, description = "Test to Add Quick Response")
	public void AddQuickResponse() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group is : " +group);
		String quickResponse = wb.getCellValue(1, wb.seacrh_pattern("AddResponse", 0).get(0).intValue());
		System.out.println("The quick response to be added is : " +quickResponse);
		data = new Quick_Responses(CurrentState.getDriver());
		data.AddOwnerQuickResponseApprover(group , quickResponse);
	}
	
	@Test(priority = 3, dependsOnMethods = {"AddQuickResponse"}, description = "Test to Edit and verify edited response")
	public void EditandVerify() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String quickResponse = wb.getCellValue(1, wb.seacrh_pattern("AddResponse", 0).get(0).intValue());
		System.out.println("The quick response to be added is : " +quickResponse);
		data.EditandVerifyResponseApprover(quickResponse);
	}
	
	@Test(priority = 4, dependsOnMethods = "EditandVerify", description = "Test to delete added Response")
	public void DeleteResponse() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		String quickResponse = wb.getCellValue(1, wb.seacrh_pattern("AddResponse", 0).get(0).intValue());
		System.out.println("The quick response to be added is : " +quickResponse);
		data = new Quick_Responses(CurrentState.getDriver());
		data.DeleteResponseApprover(quickResponse);
	}
	
	@Test(priority = 5, dependsOnMethods = "DeleteResponse", description = "Test to verify deleted Response")
	public void VerifyResponseDeleted() {
		data = new Quick_Responses(CurrentState.getDriver());
		data.verifyDeletedResponseApprover();
	}
}
