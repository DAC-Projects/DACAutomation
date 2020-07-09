package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CF_Campaigns_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CF_Create_GRL_MultiLoc_Campaign extends BaseClass{

	Navigationpage np;
	CF_Campaigns_Page data;
	ExcelHandler wb;
	
	/**
	 * Navigate to Campaign Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Navigate to Campaigns Page")
	public void navigateToCampaignPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToCampaignsPage();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Campaigns page");
		addEvidence(CurrentState.getDriver(), "Navigate to Content Management page from Dashboard", "yes");
	}
	
	@Test(priority = 2, description = "Test to create email brand campaign", dependsOnMethods = "navigateToCampaignPage")
	public void CreateBrandEmailCampaign() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/CF.xlsx", "GRLMultiLocation");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String CampaignType = wb.getCellValue(i, wb.seacrh_pattern("CamType", 0).get(0).intValue());
			System.out.println("The Campaign Type is:" + CampaignType);
			String CampaignOption = wb.getCellValue(i, wb.seacrh_pattern("CamOption", 0).get(0).intValue());
			System.out.println("The Campaign Option is :" + CampaignOption);
			String CampLanguage = wb.getCellValue(i, wb.seacrh_pattern("CamLang", 0).get(0).intValue());
			System.out.println("The Campaign Language is :" + CampLanguage);
			String CampaignName = wb.getCellValue(i, wb.seacrh_pattern("CamName", 0).get(0).intValue());
			System.out.println("The Campaign Name is :" + CampaignName);
			String CampaignDescription = wb.getCellValue(i, wb.seacrh_pattern("CamDes", 0).get(0).intValue());
			System.out.println("The Campaign Description is :" + CampaignDescription);
			data.CampaignInfo(CampaignType, CampaignOption, CampLanguage, CampaignName, CampaignDescription);
			data.GRLMultiLocSetUp();
			addEvidence(CurrentState.getDriver(), "To Enter set up details", "yes");
			data.GRLocationScheduling();
			data.ThankYouPage("GRLMultiLocation");
			data.SummaryPage();	
			data.Processedcampname(i,"GRLMultiLocation");
		}
	}
	
	@Test(priority = 3, description = "Verify Processed Campaign")
	public void VerifyProcessedCamp() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/CF.xlsx", "GRLMultiLocation");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String CampaignName = wb.getCellValue(i, wb.seacrh_pattern("ProcessedCampaign Name", 0).get(0).intValue());
			System.out.println("Campaign Name is :" +CampaignName);
			data.ProcessedCampaign("GRLMultiLocation");
			addEvidence(CurrentState.getDriver(), "Verify Campaign", "yes");
		}
	}
}