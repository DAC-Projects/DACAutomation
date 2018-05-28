package com.dac.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.dac.main.CampaignLivePreviewPage;
import com.dac.main.CampaignsPage;
import com.dac.main.Navigationpage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.Utilities;

public class Verify_LivePreviewN_DeleteCamp extends BaseTest{

	@Test
	public void livePreviewNDeleteTest() throws IOException, InterruptedException {
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ScheduledCampaign("Test Brand Regression DEU - 05/18");
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "Checking the Created campaign displaying in Scheduled section");
		
		cp.clickLivePreviewBTN();
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Checking the Email view section of Scheduled campaign Live Preview");
		
		CampaignLivePreviewPage LP=new CampaignLivePreviewPage(driver);
		LP.clickfeedBackBTN();
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
		logger.log(LogStatus.INFO, "Checking the Feed Back section of Scheduled campaign Live Preview");
		
		LP.clickthankYouBTN();
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		logger.log(LogStatus.INFO, "Checking the Thank you section of Scheduled campaign Live Preview");
		
		LP.clickClosePreviewBTN();
		
		cp.clickDeleteBTN();
		Utilities.addScreenshot(driver, imgnames.get(4).toString());
		logger.log(LogStatus.INFO, "Clicking delete button of Scheduled campaign and checking the delete pop up displying or not");
		
		cp.clickDeleteAcceptBTN();
		Utilities.addScreenshot(driver, imgnames.get(5).toString());
		logger.log(LogStatus.INFO, "clicking on confirmation to delete Sceduled campaign");
	}
	
}
