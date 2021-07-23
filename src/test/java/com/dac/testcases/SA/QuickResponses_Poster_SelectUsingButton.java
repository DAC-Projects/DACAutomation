package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Quick_Responses;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class QuickResponses_Poster_SelectUsingButton extends BaseClass {
	
	Navigationpage np;
	Quick_Responses data;
	
	@Test(priority = 1, description = "Test to navigate to Reviews Feed Page")
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 2, description = "Test to add response using buttons")
	public void AddResponseUsingButtons() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group is : " +group);
		data = new Quick_Responses(CurrentState.getDriver());
		data.AddResponseusingexistingPoster(group);
	}
	
	@Test(priority = 3, description = "Test to verify status of response added", dependsOnMethods = {"AddResponseUsingButtons"})
	public void VerifyStatusofResponse() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String quickResponse = wb.getCellValue(1, wb.seacrh_pattern("AddResponse", 0).get(0).intValue());
		System.out.println("The quick response to be added is : " +quickResponse);
		data = new Quick_Responses(CurrentState.getDriver());
		data.verifyResponseStatusPoster(quickResponse);
	}
}
