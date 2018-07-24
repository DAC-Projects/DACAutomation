package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CustomerActivityReportPage_RS;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.Utilities;

public class VerifyCampCustomerActReport extends BaseTest_CF{

	@Test
	public void campCustomerActReport_Test() throws Exception {

		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ProcessedCampaign("Test MLC Campaign Beta");
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		logger.log(LogStatus.INFO, "verifying the Campaign is moved from Scheduled Campaign section to processed campaign section");
		
		cp.clickCustActReportLink();
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		
		CustomerActivityReportPage_RS cust=new CustomerActivityReportPage_RS(driver);
		cust.verifyPageName("Customer Activity Report");
		cust.verifyPageDesc("This report identifies the customer activity for a specific campaign.");
		
		cust.verifyCampName("Test MLC Campaign Beta");
		
		String[] tableHeaderData = {"Location Name", "Location Number", "Email", "Name", "Star Rating", "Comments", "Unsubscribed"};
		cust.verifyOrderOfTableHeader(tableHeaderData);
		
		cust.verifyDownloadBTNtext("Download Report");
		
		cust.clickDownloadReport();
		//Utilities.addScreenshot(driver, imgnames.get(2).toString());
		
		cust.getCustActivityRepoTableData();
		Utilities.addScreenshot(driver, imgnames.get(2).toString());		
		
		cust.compareXlData_UIdata();
	}
}
