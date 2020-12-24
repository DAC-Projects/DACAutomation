package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;

public class Reviews_Insights_KPIVerification_Test extends BaseClass {

	Navigationpage np;
	Reviews_Insights data;
	
	
	/**
	 * Test to get Score from KPI Dashboard
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to get KPI Score")
	public void GetKPIReviewScore() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.getKPIReviewScore();
		Thread.sleep(4000);
		data.getKPIRNPS();
		addEvidence(CurrentState.getDriver(), "Test to get KPI Score", "yes");
	}
	
	/**
	 * Test to navigate to Reviews Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to Navigate to Reviews Insights Page")
	public void navigateToReviewsInsights() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsInsights();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Insights", "yes");
	}
	
	/**
	 * Test to verify average star rating
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify average star rating")
	public void GetReviewStarAvg() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.AverageStar(); 
	}

	/**
	 * Test to verify RNPS Score
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to verify RNPS Score")
	public void VerifyRNPS() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.RNPSScore();
	}

	/**
	 * Test to compare KPI and Report score
	 */
	@Test(priority = 5, description = "Test to compare KPI and Report score")
	public void CompareKPIandReportValues() {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.CompareKPIandReportReviewScore(); 
	}	
}
