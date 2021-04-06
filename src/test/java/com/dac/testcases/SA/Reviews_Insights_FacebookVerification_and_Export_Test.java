package com.dac.testcases.SA;

import java.io.IOException;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;

public class Reviews_Insights_FacebookVerification_and_Export_Test extends BaseClass {

	Navigationpage np;
	Reviews_Insights data;
	
	/**
	 * Test to navigate to Reviews Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to Navigate to Reviews Insights Page")
	public void navigateToReviewsInsights() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsInsights();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Insights", "yes");
	}
	
	/**
	 * Test to verify Positive recommendations
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to verify Positive recommendations")
	public void VerifyPositiveReco() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.PositiveReco();
	}

	/**
	 * Test to verify Negative Recommendations
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify Negative Recommendations")
	public void VerifyNegativeReco() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.NegativeReco(); 
	}
	
	/**
	 * Test to read Recommended and Not Recommended from chart and compare with
	 * overview Recommended count
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to read Reco and Not Reco from chart and compare with overview reco count")
	public void compareFacebookCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.FacebookVerify();
		addEvidence(CurrentState.getDriver(),
				"Test to read Reco and Not Reco from chart and compare with overview reco count", "yes");
	}
	
	/**
	 * Test to export file and read data from CSV
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to export file and read data from CSV")
	public void ExportLocationData() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ExportLoc();
		addEvidence(CurrentState.getDriver(), "Test to export location data", "yes");
	}

	/**
	 * Test to read data from CSV
	 * 
	 * @throws IOException
	 */
	@Test(priority = 6, description = "Test to verify data in CSV" , dependsOnMethods = {"ExportLocationData"})
	public void verifyCSVData() throws IOException {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ReadDataCSV();
	}
}
