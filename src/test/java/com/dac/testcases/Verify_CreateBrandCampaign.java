package com.dac.testcases;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.CampaignLivePreviewPage;
import com.dac.main.CampaignsPage;
import com.dac.main.CreateNewCampaignPage;
import com.dac.main.Navigationpage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.ExcelTestDataHandler;
import resources.IAutoconst;
import resources.Utilities;

public class Verify_CreateBrandCampaign extends BaseTest{
	
	public static String BrandName;
	
	@Test(enabled=true)
	public void createBrandCamp_Test() throws Exception {
		
		int englishLangColumn = 9;
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.click_CreateCampaignBTN();
				
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		
		newCampaign.selectCampType(2);
		logger.log(LogStatus.INFO, "Selecting campaign Type as Brand");
		
		
		newCampaign.selectCampLang(1);
		String campLang = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 3, englishLangColumn);
		logger.log(LogStatus.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("Brand", 5, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		CampName = newCampaign.setCampaignName("Brand",6, englishLangColumn);
		
		BrandName = newCampaign.setCampaignBrandName(englishLangColumn);
		
		newCampaign.setCampDescr("Brand", 8, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("Brand",9,englishLangColumn);
	
		newCampaign.uploadLogo();
		logger.log(LogStatus.INFO, "Adding the Logo of the Campaign");
		
		String logoName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 11, englishLangColumn);
		
		File logoPath=new File(".\\filesToUpload\\"+logoName);
		String filepath=logoPath.getAbsolutePath();
		String filepathexe="./Logo.exe";
		//String filepathexe = "./UploadFile.exe";
		Runtime.getRuntime().exec(filepathexe+" "+filepath);
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyLogoUploaded();
		newCampaign.verifyUploadLogoToolTipText("Brand", 10, englishLangColumn);
		
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
		
		newCampaign.downloadCampEmailTemplate();
		
		newCampaign.uploadCampEmailTemplate("Brand", 13, englishLangColumn);
		logger.log(LogStatus.INFO, "Uploading Email Template");
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
		
		newCampaign.setCampSubject("Brand", 14, englishLangColumn);
		
		newCampaign.setCampBanner("Brand", 15, englishLangColumn);
		
		newCampaign.verifyPersonalizationToolTipText("Brand", 16, englishLangColumn);
		
		newCampaign.setCampBodyCopy("Brand", 17, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(4).toString());
		logger.log(LogStatus.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		
		newCampaign.setCampSignature("Brand",18 , englishLangColumn);
		
		newCampaign.verifyContactInfoToolTipText(englishLangColumn);
		
		newCampaign.setContactInfo(9);
		Utilities.addScreenshot(driver, imgnames.get(5).toString());
		logger.log(LogStatus.INFO, "Verifying the Contact info section tool tip and could able to data into it");
		
		newCampaign.setScheduledStartDate();
		
		newCampaign.clickStartDatePicker();
		
		newCampaign.verifyTimeToolTipText("Brand", 27, englishLangColumn);
		
		newCampaign.clickEndDatePicker();

		newCampaign.verifyCampEndDateToolTipText("Brand", 29, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(6).toString());
		logger.log(LogStatus.INFO, "Verifying the Scheduling campaign Date controls");
		
		newCampaign.clickCreateCampBTN();
		Utilities.addScreenshot(driver, imgnames.get(7).toString());
		logger.log(LogStatus.INFO, "Verifying the Campaign creation Pop up");
		
		newCampaign.clickViewAllCampaignBTN();	
		
		cp.verifyCampTableData("Scheduled", CampName, BrandName);
		System.out.println(cp.getReleaseDateTime());
		Utilities.addScreenshot(driver, imgnames.get(8).toString());
		logger.log(LogStatus.INFO, "Verifying the Created Campaign whether displayed in Sceduled campaign Section");
		
		
	}

}
