package com.dac.testcases;

import java.io.File;

import org.testng.annotations.Test;

import com.dac.main.CampaignsPage;
import com.dac.main.CreateNewCampaignPage;
import com.dac.main.Navigationpage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.ExcelTestDataHandler;
import resources.IAutoconst;
import resources.Utilities;

public class Verify_CreateCampaign extends BaseTest{
	
	@Test(enabled=true)
	public void createBrandCamp_Test() throws Exception {
	
		int langColumn = 9;
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.click_CreateCampaignBTN();
				
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		
		newCampaign.selectCampType(2);
		logger.log(LogStatus.INFO, "Selecting campaign Type as Brand");
		
		
		newCampaign.selectCampLang(1);
		String campLang = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Location", 3, langColumn);
		logger.log(LogStatus.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("Brand", 5, langColumn);
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		String CampName = newCampaign.setCampaignName("Brand",6, langColumn);
		
		newCampaign.setCampaignBrandName(langColumn);
		
		newCampaign.setCampDescr("Brand", 8, langColumn);
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("Brand",9,langColumn);
	
		newCampaign.uploadLogo();
		logger.log(LogStatus.INFO, "Adding the Logo of the Campaign");
		
		String logoName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 11, langColumn);
		
		File logoPath=new File(".\\filesToUpload\\"+logoName);
		String filepath=logoPath.getAbsolutePath();
		String filepathexe="./Logo.exe";
		//String filepathexe = "./UploadFile.exe";
		Runtime.getRuntime().exec(filepathexe+" "+filepath);
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyUploadLogoToolTipText("Brand", 10, langColumn);
		
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
		
		//newCampaign.downloadCampEmailTemplate();
		
		newCampaign.uploadCampEmailTemplate("Brand", 13, langColumn);
		logger.log(LogStatus.INFO, "Uploading Email Template");
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		
		newCampaign.setCampSubject("Brand", 14, langColumn);
		
		newCampaign.setCampBanner("Brand", 15, langColumn);
		
		newCampaign.setCampBodyCopy("Brand", 17, langColumn);
		Utilities.addScreenshot(driver, imgnames.get(4).toString());
		logger.log(LogStatus.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		
		newCampaign.setCampSignature("Brand",18 , langColumn);
		
		newCampaign.setContactInfo(9);
		Utilities.addScreenshot(driver, imgnames.get(5).toString());
		logger.log(LogStatus.INFO, "Verifying the Contact info section tool tip and could able to data into it");
		
		newCampaign.setScheduledStartDate("Brand",26, langColumn);
		
		newCampaign.clickEndDatePicker();
		Utilities.addScreenshot(driver, imgnames.get(6).toString());
		logger.log(LogStatus.INFO, "Verifying the Scheduling campaign Date controls");
		
		newCampaign.clickCreateCampBTN();
		Utilities.addScreenshot(driver, imgnames.get(7).toString());
		logger.log(LogStatus.INFO, "Verifying the Campaign creation Pop up");
		
		newCampaign.clickViewAllCampaignBTN();	
		
		cp.search_ScheduledCampaign(CampName);
		cp.verifyCampName("Scheduled", CampName);
		Utilities.addScreenshot(driver, imgnames.get(8).toString());
		logger.log(LogStatus.INFO, "Verifying the Created Campaign whether displayed in Sceduled campaign Section");
		
	}
	
	
	@Test(enabled=false)
	public void createLocCamp_Test() throws Exception {
		
		int column = 9;
	
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.click_CreateCampaignBTN();
				
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		
		newCampaign.selectCampType(1);
		logger.log(LogStatus.INFO, "Selecting campaign Type as Location");
		
		newCampaign.selectCampLang(1);
		String campLang = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Location", 3, column);
		logger.log(LogStatus.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("Location", 5, column);
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		String CampName = newCampaign.setCampaignName("Location", 6, column);
		
		newCampaign.selectCampaignLoc(0, column);
		
		newCampaign.setCampDescr("Location", 8, column);
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("Location", 9, column);
	
		newCampaign.uploadLogo();
		logger.log(LogStatus.INFO, "Adding the Logo of the Campaign");
		
		String logoName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 11, column);
		
		File logoPath=new File(".\\filesToUpload\\"+logoName);
		String filepath=logoPath.getAbsolutePath();
		String filepathexe="./Logo.exe";
		Runtime.getRuntime().exec(filepathexe+" "+filepath);
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyUploadLogoToolTipText("Location", 10, column);
		
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
		
		//newCampaign.downloadCampEmailTemplate();
		
		newCampaign.uploadCampEmailTemplate("Location", 13, column);
		logger.log(LogStatus.INFO, "Uploading Email Template");
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		
		newCampaign.setCampSubject("Location", 14, column);
		
		newCampaign.setCampBanner("Location", 15, column);
		
		newCampaign.setCampBodyCopy("Location", 17, column);
		Utilities.addScreenshot(driver, imgnames.get(4).toString());
		logger.log(LogStatus.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		
		newCampaign.setCampSignature("Location",18 , column);
		
		newCampaign.setScheduledStartDate("Location", 19, column);
		
		newCampaign.clickEndDatePicker();
		Utilities.addScreenshot(driver, imgnames.get(5).toString());
		logger.log(LogStatus.INFO, "Verifying the Scheduling campaign Date controls");
		
		newCampaign.clickCreateCampBTN();
		Utilities.addScreenshot(driver, imgnames.get(6).toString());
		logger.log(LogStatus.INFO, "Verifying the Campaign creation Pop up");
		
		newCampaign.clickViewAllCampaignBTN();	
		
		cp.search_ScheduledCampaign(CampName);
		cp.verifyCampName("Scheduled", CampName);
		Utilities.addScreenshot(driver, imgnames.get(7).toString());
		logger.log(LogStatus.INFO, "Verifying the Created Campaign whether displayed in Sceduled campaign Section");
		
	}
}
