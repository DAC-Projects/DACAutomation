package com.dac.testcases;

import org.testng.annotations.Test;

import com.dac.main.CampaignsPage;
import com.dac.main.CustomerActivityReportPage_RS;
import com.dac.main.Navigationpage;


import resources.BaseTest;

public class VerifyCampCustomerActReport extends BaseTest{

	@Test
	public void campResponsesData_Test() throws Exception {

		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ProcessedCampaign("Test MLC Spanish (Mexico)");
		
		cp.clickCustActReportLink();
		
		CustomerActivityReportPage_RS cust=new CustomerActivityReportPage_RS(driver);
		cust.verifyCampName("Test MLC Spanish (Mexico)");
		cust.getCustActivityRepoTableData();
		cust.clickDownloadReport();
		
	}
}
