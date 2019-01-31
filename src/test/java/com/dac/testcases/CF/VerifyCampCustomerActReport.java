package com.dac.testcases.CF;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CustomerActivityReportPage_RS;
import resources.CurrentState;

public class VerifyCampCustomerActReport extends BaseTest_CF{

	@Test
	public void campCustomerActReport_Test() throws Exception {

		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		
		cp.search_ProcessedCampaign(campName);
		addEvidence(CurrentState.getDriver(), "verifying the Campaign is moved from Scheduled Campaign section to processed campaign section", "yes");
		CurrentState.getLogger().log(Status.INFO, "verifying the Campaign is moved from Scheduled Campaign section to processed campaign section");
		
		cp.clickCustActReportLink();
		addEvidence(CurrentState.getDriver(), "verifying that user is able to navigate to Customer Activity report's page", "yes");
		
		CustomerActivityReportPage_RS cust=new CustomerActivityReportPage_RS(CurrentState.getDriver());
		cust.verifyPageName("Customer Activity Report");
		cust.verifyPageDesc("This report identifies the customer activity for a specific campaign.");
		
		cust.verifyCampName(campName);
		
		String[] tableHeaderData = {"Location Name", "Location Number", "Email", "Name", "Star Rating", "Comments", "Unsubscribed"};
		cust.verifyOrderOfTableHeader(tableHeaderData);
		
		cust.verifyDownloadBTNtext("Download Report");
		
		cust.clickDownloadReport();
		addEvidence(CurrentState.getDriver(), "verifying that user able to download the Customer activity report's of a campaign", "no");
		
		cust.getCustActivityRepoTableData();
		cust.compareXlData_UIdata();
		addEvidence(CurrentState.getDriver(), "verifying that exported customer activity report file data matches with UI table data", "yes");
	}
}
