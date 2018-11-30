package com.dac.testcases.CF;

import org.testng.annotations.Test;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;

import resources.CurrentState;

public class VerifyProcessedCamp extends BaseTest_CF{

	public static String BrandName;
	
	@Test(enabled=true)
	public void processedCamp_Test() throws Exception {
		
		int englishLangColumn = 9;
		
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		cp.search_ProcessedCampaign(campName);
		
		cp.clickDetailsLink();

		CurrentState.getDriver().navigate().back();

		cp.search_ProcessedCampaign(campName);
		cp.clickResponsesLink();
		
		CurrentState.getDriver().navigate().back();
		
		cp.search_ProcessedCampaign(campName);
		cp.clickReportsLink();
		
		CurrentState.getDriver().navigate().back();
		
		cp.search_ProcessedCampaign(campName);
		cp.clickCustActReportLink();
		
		CurrentState.getDriver().navigate().back();
			
	}
}
