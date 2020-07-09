package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CF_Campaigns_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CF_VerifyFilters_Campaigns_Test extends BaseClass{
	
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
	
	@Test(priority = 2, description = "To verify data with filters")
	public void verifyAllTabdatatablewithFilters() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/CF.xlsx", "Filters");
		data.verifyAllTabDatawithFilters();
		addEvidence(CurrentState.getDriver(), "Test to click on All Tab", "yes");
		for(int i = 1; i<=wb.getRowCount(); i++) {
		String Type = wb.getCellValue(i, wb.seacrh_pattern("FilterType", 0).get(0).intValue());
		System.out.println("The Campaign Type is:" + Type);
		String SetUp = wb.getCellValue(i, wb.seacrh_pattern("FilterSetUp", 0).get(0).intValue());
		System.out.println("The Campaign setup is:" + SetUp);
		data.SelectAllFilter(Type, SetUp);
		addEvidence(CurrentState.getDriver(), "Test to select values for filter", "yes");
		data.verifyTypeFilter(Type, SetUp, "tblAllCampaigns_paginate","tblAllCampaigns");
		addEvidence(CurrentState.getDriver(), "Test to verify campaign type", "yes");
		}
	}

	@Test(priority = 3, description = "To verify data with filters")
	public void verifyActiveTabdatatablewithFilters() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/CF.xlsx", "Filters");
		data.verifyActiveTabDatawithFilters();
		addEvidence(CurrentState.getDriver(), "Test to click on All Tab", "yes");
		for(int i = 1; i<=wb.getRowCount(); i++) {
		String Type = wb.getCellValue(i, wb.seacrh_pattern("FilterType", 0).get(0).intValue());
		System.out.println("The Campaign Type is:" + Type);
		String SetUp = wb.getCellValue(i, wb.seacrh_pattern("FilterSetUp", 0).get(0).intValue());
		System.out.println("The Campaign setup is:" + SetUp);
		data.SelectActiveFilter(Type, SetUp);
		addEvidence(CurrentState.getDriver(), "Test to select values for filter", "yes");
		data.verifyTypeFilter(Type, SetUp, "tblActiveCampaigns_paginate","tblActiveCampaigns");
		addEvidence(CurrentState.getDriver(), "Test to verify campaign type", "yes");
		}
	}
	
	@Test(priority = 4, description = "To verify data with filters")
	public void verifyCompletedTabdatatablewithFilters() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/CF.xlsx", "Filters");
		data.verifyCompletedTabDatawithFilters();
		addEvidence(CurrentState.getDriver(), "Test to click on All Tab", "yes");
		for(int i = 1; i<=wb.getRowCount(); i++) {
		String Type = wb.getCellValue(i, wb.seacrh_pattern("FilterType", 0).get(0).intValue());
		System.out.println("The Campaign Type is:" + Type);
		String SetUp = wb.getCellValue(i, wb.seacrh_pattern("FilterSetUp", 0).get(0).intValue());
		System.out.println("The Campaign setup is:" + SetUp);
		data.SelectCompletedFilter(Type, SetUp);
		addEvidence(CurrentState.getDriver(), "Test to select values for filter", "yes");
		data.verifyTypeFilter(Type, SetUp, "tblCompletedCampaigns_paginate","tblCompletedCampaigns");
		addEvidence(CurrentState.getDriver(), "Test to verify campaign type", "yes");
		}
	}

	@Test(priority = 5, description = "To verify data with filters")
	public void verifyArchivedTabdatatablewithFilters() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/CF.xlsx", "Filters");
		data.verifyArchivedTabDatawithFilters();
		addEvidence(CurrentState.getDriver(), "Test to click on All Tab", "yes");
		for(int i = 1; i<=wb.getRowCount(); i++) {
		String Type = wb.getCellValue(i, wb.seacrh_pattern("FilterType", 0).get(0).intValue());
		System.out.println("The Campaign Type is:" + Type);
		String SetUp = wb.getCellValue(i, wb.seacrh_pattern("FilterSetUp", 0).get(0).intValue());
		System.out.println("The Campaign setup is:" + SetUp);
		data.SelectArchivedFilter(Type, SetUp);
		addEvidence(CurrentState.getDriver(), "Test to select values for filter", "yes");
		data.verifyTypeFilter(Type, SetUp, "tblArchivedCampaigns_paginate","tblArchivedCampaigns");
		addEvidence(CurrentState.getDriver(), "Test to verify campaign type", "yes");
		}
	}
	
	
	@Test(priority = 6, description = "To verify date in active and completed tab")
	public void verifyDateActiveTab() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		data.verifyActiveTabDatawithFilters();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Active section", "yes");
		data.verifyDateActive("tblActiveCampaigns_paginate","tblActiveCampaigns");
		addEvidence(CurrentState.getDriver(), "Test to verify date", "yes");
	}
	
	@Test(priority = 7, description = "To verify date in active and completed tab")
	public void verifyDateCompletedTab() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		data.verifyCompletedTabDatawithFilters();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Active section", "yes");
		data.verifyDateCompleted("tblCompletedCampaigns_paginate","tblCompletedCampaigns");
		addEvidence(CurrentState.getDriver(), "Test to verify date", "yes");
	}
}
