package com.dac.testcases.CF;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CampaignsPage;

import resources.BaseTest;

public class VerifyProcessedCamp extends BaseTest{

	public static String BrandName;
	
	@Test(enabled=true)
	public void processedCamp_Test() throws Exception {
		
		int englishLangColumn = 9;
		
		Navigationpage np=new Navigationpage(driver);
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.search_ProcessedCampaign("Test ENG_US Date Format");
		
		cp.clickDetailsLink();

		driver.navigate().back();

		cp.search_ProcessedCampaign("Test ENG_US Date Format");
		cp.clickResponsesLink();
		
		driver.navigate().back();
		
		cp.search_ProcessedCampaign("Test ENG_US Date Format");
		cp.clickReportsLink();
		
		driver.navigate().back();
		
		cp.search_ProcessedCampaign("Test ENG_US Date Format");
		cp.clickCustActReportLink();
		
		driver.navigate().back();
			
	}
}
