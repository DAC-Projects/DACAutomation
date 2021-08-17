package com.dac.testcases.SA;


import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Quick_Responses;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Quick_Responses_Approver_UseExistingResponse_Test extends BaseClass {

	Navigationpage np;
	Quick_Responses data;
	
	@Test(priority = 1, description = "Test to navigate to Response Management Page")
	public void NavigateToResponseManagement() throws Exception {
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
	
	@Test(priority = 3, description = "Test to delete Response added")
	public void DeleteResponseAdded() {
		data = new Quick_Responses(CurrentState.getDriver());
		data.DeleteExistingResponseApprover();
	}
}
