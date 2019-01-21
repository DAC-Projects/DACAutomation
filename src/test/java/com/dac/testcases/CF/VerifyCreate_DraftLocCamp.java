package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.BasePage;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CreateNewCampaignPage;

import resources.CurrentState;
import resources.ExcelHandler;
import resources.IAutoconst;

public class VerifyCreate_DraftLocCamp extends BaseTest_CF{
	
	@Test(enabled=true)
	public void createLocCamp_Test() throws Exception {
		
		int englishLangColumn = 9;
	
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.select_DB_Lang_Link("en", "US");
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		cp.click_CreateCampaignBTN();
				
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(CurrentState.getDriver());
		
		newCampaign.selectCampType(1);
		CurrentState.getLogger().log(Status.INFO, "Selecting campaign Type as Location");
		
		newCampaign.selectCampLang(1);
		String campLang = new ExcelHandler(IAutoconst.RS_XL_PATH, "Location").getCellValue(3, englishLangColumn);
		CurrentState.getLogger().log(Status.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("Location", 5, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying the selected Location campaign type and existing campaign tool tip", "yes");
		CurrentState.getLogger().log(Status.INFO, "verifying the tool tip lang and text of it");
		
		campName = newCampaign.setCampaignName("Location", 6, englishLangColumn);
		
		newCampaign.selectCampaignLoc(0, englishLangColumn);
		
		newCampaign.setCampDescr("Location", 8, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying that user is able to enter the details into Campaign name, Location name and description", "yes");
		CurrentState.getLogger().log(Status.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("Location", 9, englishLangColumn);
	
		String logoName = new ExcelHandler(IAutoconst.RS_XL_PATH, "Location").getCellValue(11, englishLangColumn);

		newCampaign.uploadLogo(logoName);
		CurrentState.getLogger().log(Status.INFO, "Adding the Logo of the Campaign");
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyUploadLogoToolTipText("Location", 10, englishLangColumn);
		
		addEvidence(CurrentState.getDriver(), "Verifying whether logo is uploaded into the creating campaign", "yes");
		
		newCampaign.downloadCampEmailTemplate(CurrentState.getBrowser());
		
		Thread.sleep(4000);
		
		System.out.println("---------file downloaded-------");
		String fileName = BasePage.getFileNames_Dir("./downloads");
		
		new ExcelHandler("./downloads/"+fileName, "Email Template").getAllCellsData();
		
		String[] cellValues = {"akram.1wasi@gmail.com", "wasim","akram", "wakram@dacgroup.com", "B", "Wasim"};
		
		Thread.sleep(3000);
		
		new ExcelHandler("./downloads/"+fileName, "Email Template").deleteEmptyRows();
		
		Thread.sleep(3000);
		new ExcelHandler("./downloads/"+fileName, "Email Template").createRowInExcel(2, 3, cellValues);
		
		Thread.sleep(3000);
		
		new ExcelHandler("./downloads/"+fileName, "Email Template").getAllCellsData();
		
		Thread.sleep(3000);
		newCampaign.uploadEmailTemplate(fileName);
		CurrentState.getLogger().log(Status.INFO, "Uploading Email Template");
		addEvidence(CurrentState.getDriver(), "Verifying the edited the downloaded email template and uploaded the template", "yes");
		
		newCampaign.setCampSubject("Location", 14, englishLangColumn);
		
		newCampaign.setCampBanner("Location", 15, englishLangColumn);
		
		newCampaign.verifyPersonalizationToolTipText("Brand", 16, englishLangColumn);
		
		newCampaign.setCampBodyCopy("Location", 17, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying whether user is able to enter the details about the campaign in body copy etc.", "yes");
		CurrentState.getLogger().log(Status.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		
		newCampaign.setCampSignature("Location",18 , englishLangColumn);
		
		newCampaign.setScheduledStartDate("en", "US");
		
		newCampaign.clickEndDatePicker();
		
		newCampaign.verifyCampEndDateToolTipText("Location", 22, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying that user is able to add the scheduled start date and end date", "yes");
		CurrentState.getLogger().log(Status.INFO, "Verifying the Scheduling campaign Date controls");
		
		newCampaign.clickSaveDraft();
		addEvidence(CurrentState.getDriver(), "Verifying that campaign creation succesful message is displaying", "yes");
		//logger.log(LogStatus.INFO, "Verifying the Draft Location Campaign creation Pop up");
		
		newCampaign.clickViewAllCampaignBTN();	
		
		cp.verifyCampName("Draft", campName);
		addEvidence(CurrentState.getDriver(), "Verifying that scheduled campaign should display in scheduled section", "yes");
		//logger.log(LogStatus.INFO, "Verifying the Created Campaign whether displayed in Sceduled campaign Section");
		
	}
}
