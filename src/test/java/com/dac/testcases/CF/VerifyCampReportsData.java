package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.CustomerActivityReportPage_RS;
import com.dac.main.POM_CF.ReportsPage_RS;

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
		
		rp.getFromDate();
		/*rp.overviewSectionCountData();
		
		rp.getReviewSubmittedGraphText();*/
		
	}
}
