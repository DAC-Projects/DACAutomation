package com.dac.testcases.CF;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CreateNewCampaignPage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.ExcelTestDataHandler;
import resources.IAutoconst;
import resources.Utilities;

public class Verify_CreateMLCCampaign extends BaseTest{
	
	@Test(enabled=true)
	public void createMLCCamp_Test() throws Exception {
		
		int langColumn = 9;
	
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.click_CreateCampaignBTN();
				
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		
		newCampaign.selectCampType(3);
		logger.log(LogStatus.INFO, "Selecting campaign Type as MLC");
		
		newCampaign.selectCampLang(1);
		String campLang = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "MLC").getCellValue(3, langColumn);
		logger.log(LogStatus.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("MLC", 5, langColumn);
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "verifying the Existing Campaign tool tip is it in "+campLang+" lang and text of it");
		
		CampName = newCampaign.setCampaignName("MLC", 6, langColumn);
		
		newCampaign.selectMLCs("BN- Business");
		newCampaign.selectMLCs("DF - Germany");
		newCampaign.clickAddBTN();
		
		newCampaign.verifyAddToolTipText("MLC", 28, langColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.verifyRemoveToolTipText("MLC", 30, langColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.verifyAddAllToolTipText("MLC", 29, langColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.verifyRemoveAllToolTipText("MLC", 31, langColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.setCampDescr("MLC", 8, langColumn);
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("MLC", 9, langColumn);
	
		newCampaign.uploadLogo();
		logger.log(LogStatus.INFO, "Adding the Logo of the Campaign");
		
		String logoName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "MLC").getCellValue(11, langColumn);
		
		File logoPath=new File(".\\filesToUpload\\"+logoName);
		String filepath=logoPath.getAbsolutePath();
		String filepathexe="./Logo.exe";
		Runtime.getRuntime().exec(filepathexe+" "+filepath);
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyUploadLogoToolTipText("MLC", 10, langColumn);
		
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
		
		//newCampaign.downloadCampEmailTemplate();
		
		newCampaign.uploadCampEmailTemplate("MLC", 13, langColumn);
		logger.log(LogStatus.INFO, "Uploading Email Template");
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		
		newCampaign.setCampSubject("MLC", 14, langColumn);
		
		newCampaign.setCampBanner("MLC", 15, langColumn);
		
		newCampaign.verifyPersonalizationToolTipText("Brand", 16, langColumn);
		
		newCampaign.setCampBodyCopy("MLC", 17, langColumn);
		Utilities.addScreenshot(driver, imgnames.get(4).toString());
		logger.log(LogStatus.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		
		newCampaign.setCampSignature("MLC",18 , langColumn);
		
		newCampaign.setScheduledStartDate();
		
		newCampaign.clickStartDatePicker();
		
		newCampaign.verifyTimeToolTipText("MLC", 20, langColumn);
		
		newCampaign.clickEndDatePicker();
		
		newCampaign.verifyCampEndDateToolTipText("MLC", 22, langColumn);
		Utilities.addScreenshot(driver, imgnames.get(5).toString());
		logger.log(LogStatus.INFO, "Verifying the Scheduling campaign Date controls");
		
		newCampaign.clickCreateCampBTN();
		Utilities.addScreenshot(driver, imgnames.get(6).toString());
		logger.log(LogStatus.INFO, "Verifying the Campaign creation Pop up");
		
		newCampaign.clickViewAllCampaignBTN();	
		
		cp.verifyCampName("Scheduled", CampName);
		Utilities.addScreenshot(driver, imgnames.get(7).toString());
		logger.log(LogStatus.INFO, "Verifying the Created Campaign whether displayed in Sceduled campaign Section");
	}
}