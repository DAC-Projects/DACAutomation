package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.dac.main.POM_TPSEE.TPSEE_KPI_Navigation;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_KPINavigation_Test extends BaseClass{

	TPSEE_KPI_Navigation data;
	
	@Test(priority = 1, description ="Test to navigate to All Locations Page from KPI")
	public void navigateToLocationsKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToAllLocations();
	}
	
	@Test(priority = 2, description = "Test to navigate to Visibility Report from KPI")
	public void navigateToVisibilityKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToVisibilityRpt();
	}
	
	@Test(priority = 3, description = "Test to navigate to Accuracy Report from KPI")
	public void navigateToAccuracyKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToAccuracyReport();
	}
	
	@Test(priority = 4, description = "Test to navigate to Content Analysis from KPI")
	public void navigateToContentAnalysisKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToContentAnalysis();
	}
	
	@Test(priority = 5, description = "Test to navigate to Google Ranking from KPI")
	public void navigateToGoogleRankingKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToGoogleRanking();
	}
	
	/*@Test(priority = 6, description = "Test to naviagate to Review Insights")
	public void navigateToReviewsInsightsKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToReviews();
	}*/
	
	@Test(priority = 7, description = "Test to verify the order of the reports")
	public void VerifyOrderofReports() {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.VerifyOrder();
	}
	
	@Test(priority = 8, description = "Test to verify font size of text and icon size")
	public void VerifyIconandFontSize() {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.verifyIconsizeandtextsize();
	}
}
