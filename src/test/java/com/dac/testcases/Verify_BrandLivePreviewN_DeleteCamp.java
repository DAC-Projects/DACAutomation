package com.dac.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.CampaignLivePreviewPage;
import com.dac.main.CampaignsPage;
import com.dac.main.Navigationpage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.Utilities;

public class Verify_BrandLivePreviewN_DeleteCamp extends BaseTest{

	@DataProvider
	public String[][] LivePreviewBrandNDeleteCampData() {
		String[][] data= new String[1][3];
		data[0][0] = "./TC-RS.xlsx";
		data[0][1] = "LivePreview&Delete";
		data[0][2] = "id:54211";
		return data;
	}
	
	
	@Test(dataProvider="LivePreviewBrandNDeleteCampData", dependsOnMethods= {"com.dac.testcases.Verify_CreateBrandCampaign.createBrandCamp_Test"})
	public void livePreviewNDeleteTest(String testcasePath, String Sheet, String ID) throws Exception {
		
		HandleScenariosInxlSheet(testcasePath, Sheet, ID);
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ScheduledCampaign(CampName);
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
