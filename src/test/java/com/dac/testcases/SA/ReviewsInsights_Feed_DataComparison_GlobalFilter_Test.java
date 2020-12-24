package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;

public class ReviewsInsights_Feed_DataComparison_GlobalFilter_Test extends BaseClass {

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
	 * Test to verify total reviews
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to verify total reviews")
	public void VerifyTotalReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.TotalReviews();
		data.PositiveReco();
		data.NegativeReco(); 
		data.reviewResponse();
		data.reviewNoResponse();
		data.RNPSScore();
	}
	
	/**
	 * Test to navigate to Reviews Feed
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		data = new Reviews_Insights(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes"); 
	}

	/**
	 * Test to Compare Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to verify total reviews")
	public void compareTotReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.compareReviewCountbetReports();
	}

	/**
	 * Test to Compare Positive Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to verify positive review count")
	public void verifyPositiveCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyPositiveRatingnVerifyCount();
	}

	/**
	 * Test to Compare Negative Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6, description = "Test to verify negative review count")
	public void verifyNegativeCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyNegativeRatingVerifyCount();
	}

	/**
	 * Test to Compare Response Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7, description = "Test to verify Response Review Count")
	public void VerifyResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyResponseCount();

	}

	/**
	 * Test to Compare No Response Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8, description = "Test to verify No Response Review Count")
	public void VerifyNoResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyNotResponseCount();
	}

	/**
	 * Test to Compare RNPS Score between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to get star rating count, calculate RNPS score and compare with Report")
	public void CompareRNPSScore() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.getOneStar();
		data.getTwoStar();
		data.getThreeStar();
		data.getFourStar();
		data.getFiveStar();
		data.getTotalStarCount();
		data.getPromoterScore();
		data.getTotThreeScore();
		data.getDetractorScore();
		data.CompareRNPSScore();
	}
}
