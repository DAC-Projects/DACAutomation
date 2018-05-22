package com.dac.testcases;

import org.testng.annotations.Test;

import com.dac.main.CampaignsPage;
import com.dac.main.Navigationpage;
import com.dac.main.ResponsesPage_RS;

import resources.BaseTest;


public class VerifyCampResponsesData extends BaseTest{

	@Test
	public void campResponsesData_Test() throws Exception {

		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		
		cp.search_ProcessedCampaign("Test MLC Spanish (Mexico)");
		
		cp.clickResponsesLink();
		
		ResponsesPage_RS rprs=new ResponsesPage_RS(driver);
		rprs.avgStarRatingData();
		
		rprs.getReviewTableData();
		
	}
}
