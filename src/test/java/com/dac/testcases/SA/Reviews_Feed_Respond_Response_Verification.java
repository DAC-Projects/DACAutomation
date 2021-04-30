package com.dac.testcases.SA;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed_Response_To_Reviews;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Reviews_Feed_Respond_Response_Verification extends BaseClass {
	
	Navigationpage np;
	Reviews_Feed_Response_To_Reviews data;
	int RCountBefore;
	
	@Test(priority = 1 , description = "Test to navigate to Reviews Feed" )
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}

	@Test(priority = 2, description = "To Click on Respond to Reviews")
	public void NavigateToResponseToReviewsTab() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.ClickResToReviews();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response to Reviews Tab", "yes");
	}
	
	@Test(priority = 3, description = "Test to verify add response link count")
	public void VerifyResponseLinkCount() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.verifyReviewsCount();
		addEvidence(CurrentState.getDriver(), "Test to verify Review count with Response link count", "yes");
	}
	
	@Test(priority = 4, description = "Test to add response in Respond to Reviews Page")
	public void VerifyResponseAdded() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
		String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
		System.out.println("The Response to be added is : " +ResponseSelected);
		RCountBefore = data.getReviewCount();
		System.out.println("The Review Count before Adding Response is : " +RCountBefore);
		data.AddResponse(ResponseSelected);
		}
	}
	
	@Test(priority = 5, description = "Test to verify response", dependsOnMethods = {"VerifyResponseAdded"})
	public void verifyresponse() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			Thread.sleep(3000);
			data.clickonReviews();
			Thread.sleep(3000);
			data.VerifyResponse(ResponseSelected);
			Thread.sleep(3000);
			data.ClickResToReviews();
			Thread.sleep(3000);
			int RCountAfter = data.getReviewCount();
			System.out.println("The review count after adding response is : " +RCountAfter);
			Assert.assertEquals(RCountAfter, RCountBefore - 1);
		}
	}
}
