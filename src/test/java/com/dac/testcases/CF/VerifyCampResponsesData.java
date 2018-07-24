package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.ResponsesPage_RS;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.Utilities;


public class VerifyCampResponsesData extends BaseTest{

	@Test
	public void campResponsesData_Test() throws Exception {
	
		String CampName = "Test MLC unsubscribe functionality";
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "Campaign Page is displayed");
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.verifyCampName("Processed", CampName);
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Verifying the Processed Campaign whether displayed in Processed campaign Section");
		
		cp.clickResponsesLink();

		ResponsesPage_RS rprs=new ResponsesPage_RS(driver);
		
		rprs.verifyToDate("en", "US");
		
		rprs.verifyFromDate("en", "US");
		/*rprs.selectCamp("All Unarchived Campaigns");
		rprs.clickApplyFilterBTN();*/
		rprs.VerifyNumOfReviews();
		
		//String avgRating = rprs.avgStarRatingData();
		/*Utilities.addScreenshot(driver, imgnames.get(2).toString());
		logger.log(LogStatus.INFO, "Overall Star Ratings : "+avgRating);*/
		
		String[] tableHeaderData = {"Mark for Inclusion", "Campaign Name", "Location Name", "Location Number",
									"Email", "Date/Time Submitted", "Star Rating", "Review" };
		
		rprs.verifyOrderOfTableHeader(tableHeaderData);
		
		rprs.clickExportBTN();


		rprs.getReviewTableData();
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		logger.log(LogStatus.INFO, "Verifying the UI Table Data of Responses Page");

		rprs.compareXlData_UIdata();
		
		rprs.verifyOverallRatingWidget("Overall Star Ratings");
		String avgRating = rprs.avgStarRatingData("Average Star Rating"); 
		
		
	}
}
