package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;

public class Reviews_Insights_TextVerification_Test extends BaseClass {


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
	 * Test to verify active state of Report / Report Section
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to verify active state of the report")
	public void verifyreportactivestate() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.Review_Insights_Highlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of the report", "yes");
	}

	/**
	 * Test to verify title and title text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify Title and Title Text")
	public void VerifyTitleText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyTitle("Review Insights",
				"This report provides metrics for location reviews across the sites that are being monitored. Read Manual");
		addEvidence(CurrentState.getDriver(), "Test to verify title and title text", "yes");
	}

	/**
	 * Test to verify notice tooltip text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to verift text of Notice")
	public void VerifyNoticeText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.NoticeText();
		addEvidence(CurrentState.getDriver(), "Test to verify text of Notice", "yes");
	}

	/**
	 * Test to verify tooltip text for reviews, recommendations and responses
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to verify tooltip text for reviews, recommendations and responses")
	public void verifyTootipText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.tooltiptext();
	}

	/**
	 * Test to verify Learn More Content
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6, description = "Test to verify Learn More Content")
	public void VerifyLearnMoreText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyLearntext();
	}
	
	@Test(priority = 7, description = "Test to verify read manual content")
	public void VerifyReadManual() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyContentInPDf("Review insights is broken out into three different sections outlined below:", "To set a filter, follow the steps below:", "A. Location/Time Filters", "Review_Insights_Manual.pdf");
	}
}
