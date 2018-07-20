package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CampaignLivePreviewPage;
import com.dac.main.POM_CF.CampaignsPage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.Utilities;

public class Verify_BrandLivePreviewN_DeleteCamp extends BaseTest_CF{
	
	@Test(dependsOnMethods= {"com.dac.testcases.CF.Verify_CreateBrandCampaign.createBrandCamp_Test"})
	public void livePreviewNDeleteTest() throws Exception {
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ScheduledCampaign(campName);
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
