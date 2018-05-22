package com.dac.testcases;

import org.testng.annotations.Test;

import com.dac.main.CampaignsPage;
import com.dac.main.CustomerActivityReportPage_RS;
import com.dac.main.Navigationpage;
import com.dac.main.ReportsPage_RS;

import resources.BaseTest;

public class VerifyCampReportsData extends BaseTest {

	@Test
	public void campResponsesData_Test() throws Exception {

		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ProcessedCampaign("Test MLC Spanish (Mexico)");
		
		cp.clickReportsLink();
		
		ReportsPage_RS rp=new ReportsPage_RS(driver);
		rp.overviewSectionCountData();
		

		
	}
}
