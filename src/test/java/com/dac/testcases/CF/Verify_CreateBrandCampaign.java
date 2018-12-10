package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.BasePage;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;

import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CreateNewCampaignPage;

import resources.CurrentState;
import resources.ExcelTestDataHandler;
import resources.IAutoconst;

public class Verify_CreateBrandCampaign extends BaseTest_CF {
	
	static int englishLangColumn = 9;
	
	@Test(enabled = true)
	public void navigateToCF() throws Exception {
		
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.select_DB_Lang_Link("en", "US");

		np.clickCampaigns();	
	}
	
	@Test(enabled=true, dependsOnMethods = {"navigateToCF"})
	public void createBrandCamp_Test() throws Exception {
			
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(CurrentState.getDriver());
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());		
		cp.click_CreateCampaignBTN();	
		
		newCampaign.selectCampType(2);
		CurrentState.getLogger().log(Status.INFO, "Selecting campaign Type as Brand");
		
		
		newCampaign.selectCampLang(1);
		String campLang = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(3, englishLangColumn);
		CurrentState.getLogger().log(Status.INFO, "Selecting campaign Language as "+campLang);
		
		newCampaign.verifyExistCampToolTipText("Brand", 5, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying the selected campaign type and existing campaign tool tip", "yes");
		CurrentState.getLogger().log(Status.INFO, "verifying the tool tip lang and text of it");
		
		campName = newCampaign.setCampaignName("Brand",6, englishLangColumn);
		
		brandName = newCampaign.setCampaignBrandName(englishLangColumn);
		
		newCampaign.setCampDescr("Brand", 8, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying that user is able to enter the details into Campaign name, Brand name and description", "yes");
		CurrentState.getLogger().log(Status.INFO, "Entered the Campaign Name, Brand Name and Description");
		
		newCampaign.setSenderName("Brand",9,englishLangColumn);
	
		String logoName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(11, englishLangColumn);

		newCampaign.uploadLogo(logoName);
		CurrentState.getLogger().log(Status.INFO, "Adding the Logo of the Campaign");
		
		//Below Thread.sleep method for wait to finish the uploading of Logo through AutoIt. 
		//Other waiting condition can't give because selenium not able to handle window based apps
		Thread.sleep(5000);
		newCampaign.verifyLogoUploaded();
		newCampaign.verifyUploadLogoToolTipText("Brand", 10, englishLangColumn);
		
		addEvidence(CurrentState.getDriver(), "Verifying whether logo is uploaded into the creating campaign", "yes");
		
		newCampaign.downloadCampEmailTemplate(CurrentState.getBrowser());
		
		Thread.sleep(4000);
		
		System.out.println("---------file downloaded-------");
		String fileName = BasePage.getFileNames_Dir("./downloads");
		
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").getAllCellsData();
		
		String[] cellValues = {"akram.1wasi@gmail.com", "wasim","akram", "wakram@dacgroup.com", "B", "Wasim"};
		
		Thread.sleep(3000);
		
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").deleteEmptyRows();
		
		Thread.sleep(3000);
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").createRowInExcel(2, 3, cellValues);
		
		/*Thread.sleep(3000);
		
		new ExcelTestDataHandler("./downloads/"+fileName, "Email Template").getAllCellsData();*/
		
		Thread.sleep(3000);
		newCampaign.uploadEmailTemplate(fileName);
		
		//newCampaign.uploadCampEmailTemplate("Brand", 13, englishLangColumn);
		newCampaign.getTofieldData();
		CurrentState.getLogger().log(Status.INFO, "Uploading Email Template");
		addEvidence(CurrentState.getDriver(), "Verifying the edited the downloaded email template and uploaded the template", "yes");
		
		campSubject = newCampaign.setCampSubject("Brand", 14, englishLangColumn);
		//System.out.println("campSubject :"+campSubject);
		campSubjectNew = campSubject.replace("(FirstName)", cellValues[1]);
		campSubjectNew = campSubjectNew.replace("(LastName)", cellValues[2]);
		//System.out.println("campSubjectNew :"+campSubjectNew);
		
		campBanner = newCampaign.setCampBanner("Brand", 15, englishLangColumn);
		campBannerNew = campBanner.replace("(FirstName)", cellValues[1]);
		campBannerNew = campBannerNew.replace("(LastName)", cellValues[2]);
		
		newCampaign.verifyPersonalizationToolTipText("Brand", 16, englishLangColumn);
		
		campBodyCopy = newCampaign.setCampBodyCopy("Brand", 17, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying whether user is able to enter the details about the campaign in body copy etc.", "yes");
		CurrentState.getLogger().log(Status.INFO, "Entered Subject, Banner Info and Body Copy data and verifying the Tokens");
		campBodyCopyNew = campBodyCopy.replace("(FirstName)", cellValues[1]);
		campBodyCopyNew = campBodyCopyNew.replace("(LastName)", cellValues[2]);
		
		campSignature = newCampaign.setCampSignature("Brand",18 , englishLangColumn);
		campSignatureNew = campSignature.replace("(FirstName)", cellValues[1]);
		campSignatureNew = campSignatureNew.replace("(LastName)", cellValues[2]);
		
		newCampaign.verifyContactInfoToolTipText(englishLangColumn);
		
		newCampaign.setContactInfo(9);
		addEvidence(CurrentState.getDriver(), "Verifying that whether Contact info section is displaying", "yes");
		CurrentState.getLogger().log(Status.INFO, "Verifying the Contact info section tool tip and could able to data into it");
		
		newCampaign.setScheduledStartDate("en", "US");
		
		newCampaign.clickStartDatePicker();
		
		//newCampaign.verifyLongDateFormat("en", "US");
		
		newCampaign.verifyTimeToolTipText("Brand", 27, englishLangColumn);
		
		newCampaign.clickEndDatePicker();
		
		newCampaign.verifyCampEndDate("en", "US");
		//newCampaign.verifyLongDateFormat("en", "US");

		newCampaign.verifyCampEndDateToolTipText("Brand", 29, englishLangColumn);
		addEvidence(CurrentState.getDriver(), "Verifying that user is able to add the scheduled start date and end date", "yes");
		CurrentState.getLogger().log(Status.INFO, "Verifying the Scheduling campaign Date controls");
		
		newCampaign.clickCreateCampBTN();
		addEvidence(CurrentState.getDriver(), "Verifying that campaign creation succesful message is displaying", "yes");
		CurrentState.getLogger().log(Status.INFO, "Verifying the Campaign creation Pop up");
		
		newCampaign.clickViewAllCampaignBTN();	
		
		//cp.getReleaseDateTime();
		
		//CampaignsPage cp=new CampaignsPage(driver);
		
		cp.verifyCampTableData("Scheduled", campName, brandName);
		//System.out.println(cp.getReleaseDateTime());
		addEvidence(CurrentState.getDriver(), "Verifying that scheduled campaign should display in scheduled section", "yes");
		CurrentState.getLogger().log(Status.INFO, "Verifying the Created Campaign whether displayed in Scheduled campaign Section");
		
		
	}

}
