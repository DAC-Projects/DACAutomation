package com.dac.testcases.CF;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.BasePage;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CreateNewCampaignPage;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.ExcelTestDataHandler;
import resources.IAutoconst;
import resources.Utilities;

public class Verify_CreateMLCCampaign extends BaseTest_CF{
	
	static int englishLangColumn = 9;
	
	@Test(enabled = true, priority = 0, groups = {"Smoke"})
	public void navigateToCF() throws Exception {
		
		Navigationpage np=new Navigationpage(driver);
		np.select_DB_Lang_Link("en", "US");

		np.clickCampaigns();	
	}
	
	@Test(enabled=true, dependsOnMethods = {"navigateToCF"})
	public void createMLCCamp_Test() throws Exception {
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.click_CreateCampaignBTN();
				
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		
		newCampaign.selectCampType(3);
		logger.log(LogStatus.INFO, "Selecting campaign Type as MLC");
		
		newCampaign.selectCampLang(1);
		String campLang = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "MLC").getCellValue(3, englishLangColumn);
		logger.log(LogStatus.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("MLC", 5, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "verifying the Existing Campaign tool tip is it in "+campLang+" lang and text of it");
		
		campName = newCampaign.setCampaignName("MLC", 6, englishLangColumn);
		
		newCampaign.selectMLCs("BN- Business");
		newCampaign.selectMLCs("DF - Germany");
		newCampaign.clickAddBTN();
		
		newCampaign.verifyAddToolTipText("MLC", 28, englishLangColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.verifyRemoveToolTipText("MLC", 30, englishLangColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.verifyAddAllToolTipText("MLC", 29, englishLangColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.verifyRemoveAllToolTipText("MLC", 31, englishLangColumn);
		logger.log(LogStatus.INFO, "verifying the tool tip lang and text of it");
		
		newCampaign.setCampDescr("MLC", 8, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("MLC", 9, englishLangColumn);
	
		String logoName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "MLC").getCellValue(11, englishLangColumn);

		newCampaign.uploadLogo(logoName);
		logger.log(LogStatus.INFO, "Adding the Logo of the Campaign");
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyLogoUploaded();
		newCampaign.verifyUploadLogoToolTipText("MLC", 10, englishLangColumn);
			
		Utilities.addScreenshot(driver, imgnames.get(2).toString());
				
		newCampaign.downloadCampEmailTemplate();
				
		/*Thread.sleep(4000);
				
		System.out.println("---------file downloaded-------");
		String fileName = BasePage.getFileNames_Dir("./downloads");
			
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").getAllCellsData();
				
		String[] cellValues = {"akram.1wasi@gmail.com", "wasim","akram", "wakram@dacgroup.com", "B", "Wasim"};
				
		Thread.sleep(3000);
				
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").deleteEmptyRows();
				
		Thread.sleep(3000);
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").createRowInExcel(2, 3, cellValues);
				
		Thread.sleep(3000);
				
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").getAllCellsData();*/
				
		Thread.sleep(3000);
		newCampaign.uploadCampEmailTemplate("MLC", 13, englishLangColumn);
		//newCampaign.uploadEmailTemplate(fileName);
				
		//newCampaign.uploadCampEmailTemplate("Location", 13, englishLangColumn);
		logger.log(LogStatus.INFO, "Uploading Email Template");
		Utilities.addScreenshot(driver, imgnames.get(3).toString());
				
		campSubject = newCampaign.setCampSubject("MLC", 14, englishLangColumn);
		/*campSubjectNew = campSubject.replace("(FirstName)", cellValues[1]);
		campSubjectNew = campSubjectNew.replace("(LastName)", cellValues[2]);*/

				
		campBanner = newCampaign.setCampBanner("MLC", 15, englishLangColumn);
		/*campBannerNew = campBanner.replace("(FirstName)", cellValues[1]);
		campBannerNew = campBannerNew.replace("(LastName)", cellValues[2]);*/
				
		newCampaign.verifyPersonalizationToolTipText("MLC", 16, englishLangColumn);
				
		campBodyCopy = newCampaign.setCampBodyCopy("MLC", 17, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(4).toString());
		logger.log(LogStatus.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		/*campBodyCopyNew = campBodyCopy.replace("(FirstName)", cellValues[1]);
		campBodyCopyNew = campBodyCopyNew.replace("(LastName)", cellValues[2]);*/
				
		campSignature = newCampaign.setCampSignature("MLC",18 , englishLangColumn);
		/*campSignatureNew = campSignature.replace("(FirstName)", cellValues[1]);
		campSignatureNew = campSignatureNew.replace("(LastName)", cellValues[2]);*/
				
		newCampaign.setScheduledStartDate("en", "US");
				
		newCampaign.clickStartDatePicker();
				
		//newCampaign.verifyLongDateFormat("en", "US");
				
		newCampaign.verifyTimeToolTipText("MLC", 20, englishLangColumn);
				
		newCampaign.clickEndDatePicker();
				
		newCampaign.verifyCampEndDate("en", "US");
		//newCampaign.verifyLongDateFormat("en", "US");

		newCampaign.verifyCampEndDateToolTipText("MLC", 22, englishLangColumn);
		Utilities.addScreenshot(driver, imgnames.get(5).toString());
		logger.log(LogStatus.INFO, "Verifying the Scheduling campaign Date controls");
				
		newCampaign.clickCreateCampBTN();
		Utilities.addScreenshot(driver, imgnames.get(6).toString());
		logger.log(LogStatus.INFO, "Verifying the Campaign creation Pop up");
				
		newCampaign.clickViewAllCampaignBTN();	
				
		//cp.getReleaseDateTime();
		
		cp.verifyCampName("Scheduled", campName);
		//System.out.println(cp.getReleaseDateTime());
		Utilities.addScreenshot(driver, imgnames.get(7).toString());
		logger.log(LogStatus.INFO, "Verifying the Created Campaign whether displayed in Scheduled campaign Section");
	}
}