package com.dac.testcases.CF;

import org.testng.annotations.Test;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignsPage;
import com.dac.main.POM_CF.ReportsPage_RS;
import resources.CurrentState;

public class VerifyCampReportsData extends BaseTest_CF {

	@Test
	public void campResponsesData_Test() throws Exception {

		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		
		cp.search_ProcessedCampaign(campName);
		
		cp.clickReportsLink();
		
		ReportsPage_RS rp=new ReportsPage_RS(CurrentState.getDriver());
		
		rp.getFromDate();
		/*rp.overviewSectionCountData();
		
		rp.getReviewSubmittedGraphText();*/
		
	}
}
