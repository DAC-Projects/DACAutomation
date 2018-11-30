package com.dac.testcases.CF;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.ResponsesPage_RS;
import resources.CurrentState;

public class VerifyCampResponsesData extends BaseTest_CF {

	@Test
	public void campResponsesData_Test() throws Exception {
	
		
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.clickCampaigns();
		
		CurrentState.getLogger().log(Status.INFO, "Campaign Page is displayed");
		addEvidence(CurrentState.getDriver(), "User should be able to navigate to Campaign's page", "yes");
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		
		cp.verifyCampName("Processed", campName);
		addEvidence(CurrentState.getDriver(), "Verifying that scheduled campaign should display in scheduled section", "yes");
		CurrentState.getLogger().log(Status.INFO, "Verifying the Processed Campaign whether displayed in Processed campaign Section");
		
		cp.clickResponsesLink();

		ResponsesPage_RS rprs=new ResponsesPage_RS(CurrentState.getDriver());
		
		rprs.verifyToDate("en", "US");
		
		rprs.verifyFromDate("en", "US");
		/*rprs.selectCamp("All Unarchived Campaigns");
		rprs.clickApplyFilterBTN();*/
		rprs.VerifyNumOfReviews();
		
		//String avgRating = rprs.avgStarRatingData();
		
		
		String[] tableHeaderData = {"Mark for Inclusion", "Campaign Name", "Location Name", "Location Number",
									"Email", "Date/Time Submitted", "Star Rating", "Review" };
		
		String[] xlvalues = {"Location Name", "Location Number"};
		
		rprs.verifyOrderOfTableHeader(tableHeaderData, xlvalues);
		
		rprs.clickExportBTN();
		
		rprs.getReviewTableData();
		addEvidence(CurrentState.getDriver(), "Verifying the UI Table Data of Responses Page", "no");
		CurrentState.getLogger().log(Status.INFO, "Verifying the UI Table Data of Responses Page");

		rprs.verifyOverallRatingWidget("Overall Star Ratings");
		String avgRating = rprs.avgStarRatingData("Average Star Rating"); 
		addEvidence(CurrentState.getDriver(), "Verifying that displaying of average star rating is corretly calculated or not", "yes");
		CurrentState.getLogger().log(Status.INFO, "Overall Star Ratings : "+avgRating);
		
		rprs.compareXlData_UIdata();
		addEvidence(CurrentState.getDriver(), "Verifying that downloaded responses data is matches with UI Responses data", "no");
		
	}
}
