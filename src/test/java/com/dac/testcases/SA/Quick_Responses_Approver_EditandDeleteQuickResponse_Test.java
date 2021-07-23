package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Quick_Responses;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Quick_Responses_Approver_EditandDeleteQuickResponse_Test extends BaseClass {
	
	Navigationpage np;
	Quick_Responses data;
	
	@Test(priority = 1, description = "Test to navigate to Response Management Page")
	public void NavigateToResponseManagement() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 2, description = "Test to Edit quick Response")
	public void EditQuickResposnes() throws Exception {
		data = new Quick_Responses(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group is : " +group);
		data.EditQuickResponses(group);
		CurrentState.getDriver().navigate().refresh();
	}

	@Test(priority = 3, description = "Test to delete quick response")
	public void DeleteQuickResponses() throws Exception {
		data = new Quick_Responses(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group is : " +group);
		data.DeleteQuickResponse(group);
	}
}
