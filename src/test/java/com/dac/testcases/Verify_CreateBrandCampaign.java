package com.dac.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.testng.annotations.Test;

import com.dac.main.CampaignLivePreviewPage;
import com.dac.main.CampaignsPage;
import com.dac.main.CreateNewCampaignPage;
import com.dac.main.CustomerActivityReportPage_RS;
import com.dac.main.Navigationpage;
import com.dac.main.ReportsPage_RS;
import com.dac.main.ResponsesPage_RS;

import autoitx4java.AutoItX;
import resources.BaseTest;
import resources.CreateEvidence;
import resources.Utilities;

public class Verify_CreateBrandCampaign extends BaseTest{
	
	@Test
	public void createBrandCamp_Test() throws Exception {

		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ProcessedCampaign("Test MLC Spanish (Mexico)");
		
		cp.clickResponsesLink();
		
		ResponsesPage_RS rprs=new ResponsesPage_RS(driver);
		rprs.avgStarRatingData();
		
		
		
		//cp.click_CreateCampaignBTN();
		
		/*CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		
		newCampaign.selectCampType(2);
		
		newCampaign.selectCampLang(1);
		
		newCampaign.verifyExistCampToolTipText("Copy an existing campaign to quickly create a new one.");
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		
		newCampaign.setCampaignName("Test Auto Brand Campaign");
		
		newCampaign.setCampaignBrandName("Auto brand");
		
		newCampaign.setCampDescr("Camp Auto Description");
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		
		newCampaign.setSenderName("Sender: Test Auto Brand Campaign");
	
		newCampaign.uploadLogo("logo", ".jpeg");
		
		newCampaign.verifyUploadLogoToolTipText("Recommended size is 160 x 70 pixels");
		
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
	
		newCampaign.downloadCampEmailTemplate();
		
		newCampaign.uploadCampEmailTemplate("EmailTemplate", ".xlsx");
		
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		
		newCampaign.setCampSubject("subject : Test Auto Brand Campaign");
		
		newCampaign.setCampBanner("Banner : Test Auto Brand Campaign");
		
		newCampaign.setCampBodyCopy("Hello (FirstName) (LastName)");
		
		Utilities.addScreenshot(driver, imgnames.get(4).toString());
		
		newCampaign.setCampSignature("thank you");
		
		newCampaign.setContactInfo("Mississauga", "Canada", "3045 Glen Erin Dr", "ON L5L 1J3", "+1 905-607-4000");
		
		Utilities.addScreenshot(driver, imgnames.get(5).toString());
		
		newCampaign.setScheduledStartDate("05", "21", "2018");
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		
		newCampaign.clickEndDatePicker();
				
		newCampaign.clickCreateCampBTN();
		
		Utilities.addScreenshot(driver, imgnames.get(6).toString());
		
		newCampaign.clickViewAllCampaignBTN();
		
		cp.search_ScheduledCampaign("Test Auto Brand Campaign");
		
		Utilities.addScreenshot(driver, imgnames.get(7).toString());
		
		cp.clickLivePreviewBTN();
		
		Utilities.addScreenshot(driver, imgnames.get(8).toString());
		
		CampaignLivePreviewPage LP=new CampaignLivePreviewPage(driver);
		LP.clickfeedBackBTN();
		
		Utilities.addScreenshot(driver, imgnames.get(9).toString());
		LP.clickthankYouBTN();
		
		Utilities.addScreenshot(driver, imgnames.get(10).toString());
		
		LP.clickClosePreviewBTN();
				
		cp.clickDeleteBTN();
		Utilities.addScreenshot(driver, imgnames.get(11).toString());
		
		cp.clickDeleteAcceptBTN();
		Utilities.addScreenshot(driver, imgnames.get(12).toString());*/
		
		
	}
}
