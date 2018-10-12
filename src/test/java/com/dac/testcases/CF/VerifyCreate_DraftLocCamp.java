package com.dac.testcases.CF;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CreateNewCampaignPage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.CurrentState;
import resources.ExcelTestDataHandler;
import resources.IAutoconst;
import resources.Utilities;

public class VerifyCreate_DraftLocCamp extends BaseTest_CF{
	
	@Test(enabled=true)
	public void createLocCamp_Test() throws Exception {
		
		int englishLangColumn = 9;
	
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		cp.click_CreateCampaignBTN();
				
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(CurrentState.getDriver());
		
		newCampaign.selectCampType(1);
		//logger.log(LogStatus.INFO, "Selecting campaign Type as Location");
		
		newCampaign.selectCampLang(1);
		String campLang = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Location").getCellValue(3, englishLangColumn);
		//logger.log(LogStatus.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("Location", 5, englishLangColumn);
		Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(0).toString());
		//logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		campName = newCampaign.setCampaignName("Location", 6, englishLangColumn);
		
		newCampaign.selectCampaignLoc(0, englishLangColumn);
		
		/*newCampaign.setCampDescr("Location", 8, englishLangColumn);
		Utilities.addScreenshot(CurrentState.getDriver(), imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("Location", 9, englishLangColumn);
	
		String logoName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Location").getCellValue(11, englishLangColumn);

		newCampaign.uploadLogo(logoName);
		logger.log(LogStatus.INFO, "Adding the Logo of the Campaign");
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyUploadLogoToolTipText("Location", 10, englishLangColumn);
		
		Utilities.addScreenshot(CurrentState.getDriver(), imgnames.get(2).toString());
		
		//newCampaign.downloadCampEmailTemplate();
		
		newCampaign.uploadCampEmailTemplate("Location", 13, englishLangColumn);
		logger.log(LogStatus.INFO, "Uploading Email Template");
		Utilities.addScreenshot(CurrentState.getDriver(), imgnames.get(3).toString());
		
		newCampaign.setCampSubject("Location", 14, englishLangColumn);
		
		newCampaign.setCampBanner("Location", 15, englishLangColumn);
		
		newCampaign.verifyPersonalizationToolTipText("Brand", 16, englishLangColumn);
		
		newCampaign.setCampBodyCopy("Location", 17, englishLangColumn);
		Utilities.addScreenshot(CurrentState.getDriver(), imgnames.get(4).toString());
		logger.log(LogStatus.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		
		newCampaign.setCampSignature("Location",18 , englishLangColumn);
		
		newCampaign.setScheduledStartDate("en", "US");
		
		newCampaign.clickEndDatePicker();
		
		newCampaign.verifyCampEndDateToolTipText("Location", 22, englishLangColumn);
		Utilities.addScreenshot(CurrentState.getDriver(), imgnames.get(5).toString());
		logger.log(LogStatus.INFO, "Verifying the Scheduling campaign Date controls");*/
		
		newCampaign.clickSaveDraft();
		Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(6).toString());
		//logger.log(LogStatus.INFO, "Verifying the Draft Location Campaign creation Pop up");
		
		newCampaign.clickViewAllCampaignBTN();	
		
		cp.verifyCampName("Draft", campName);
		Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(7).toString());
		//logger.log(LogStatus.INFO, "Verifying the Created Campaign whether displayed in Sceduled campaign Section");
		
	}
}
