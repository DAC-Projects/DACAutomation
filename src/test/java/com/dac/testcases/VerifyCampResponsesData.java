package com.dac.testcases;

import org.testng.annotations.Test;

import com.dac.main.CampaignsPage;
import com.dac.main.Navigationpage;
import com.dac.main.ResponsesPage_RS;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.Utilities;


public class VerifyCampResponsesData extends BaseTest{

	@Test
	public void campResponsesData_Test() throws Exception {

		String CampName = "Test MLC Spanish (Mexico)";
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "Campaign Page is displayed");
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ProcessedCampaign(CampName);
		
		cp.verifyCampName("Processed", CampName);
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Verifying the Processed Campaign whether displayed in Processed campaign Section");
		
		cp.clickResponsesLink();
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
		logger.log(LogStatus.INFO, "Responses Page is displayed of Processed Campaign");
		
		ResponsesPage_RS rprs=new ResponsesPage_RS(driver);
		String avgRating = rprs.avgStarRatingData();
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		logger.log(LogStatus.INFO, "Overall Star Ratings : "+avgRating);
		
		rprs.getReviewTableData();
		
	}
}
