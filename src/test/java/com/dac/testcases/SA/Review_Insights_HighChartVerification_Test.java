package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;

public class Review_Insights_HighChartVerification_Test extends BaseClass {

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
	}
	
	/**
	 * Test to compare applied and chart dates
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to compare applied and chart dates")
	public void CompareDateAppliednChart() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.CompareAppliedDatenChartDate(); 
		addEvidence(CurrentState.getDriver(), "Test to compare applied and chart dates", "yes");
	}
	
	/**
	 * Test to read review count from chart and compare overview review count
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to read review count from chart and compare overview review count")
	public void compareChartCountnOvrCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.compareChartnReviewCount(); 
	}
	
	/**
	 * Test to verify visbility of highcharts of different types
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to verify visbility of highcharts of different types")
	public void verifyVisibilityOfHighchart() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyRecoHighchart();
		data.verifyFiveStarHighChart();
		data.verifyThreeStarHighChart();
		data.verifyOneStarHighChart();
		data.verifyNotRecommendHighChart();
		data.verifyNoAvailableHighChart();
	}
	
	/**
	 * Test for Zoom filter verification
	 */
	@Test(priority = 6, description = "Test to verify highcharts zoom")
	public void verifyZoomFunction() {
		data = new Reviews_Insights(CurrentState.getDriver());
		try {
			try {
				String OneMonth = "1m";
				data.clickHighchartCriteria(OneMonth);
				data.CompareAppliedDatenChartDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String ThreeMonths = "3m";
				data.clickHighchartCriteria(ThreeMonths);
				data.CompareAppliedDatenChartDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String SixMonths = "6m";
				data.clickHighchartCriteria(SixMonths);
				data.CompareAppliedDatenChartDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String OneYear = "1y";
				data.clickHighchartCriteria(OneYear);
				data.CompareAppliedDatenChartDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String YearToDate = "ytd";
				data.clickHighchartCriteria(YearToDate);
				data.CompareAppliedDatenChartDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String ALLDATA = "all";
				data.clickHighchartCriteria(ALLDATA);
				data.CompareAppliedDatenChartDate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
