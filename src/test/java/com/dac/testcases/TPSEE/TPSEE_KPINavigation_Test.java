package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.dac.main.POM_TPSEE.TPSEE_KPI_Navigation;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_KPINavigation_Test extends BaseClass{

	TPSEE_KPI_Navigation data;
	
	/**
	 * Test to navigate All Locations
	 * @throws Exception
	 */
	@Test(priority = 1, description ="Test to navigate to All Locations Page from KPI")
	public void navigateToLocationsKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToAllLocations();
	}
	
	/**
	 * Test to navigate Visibility Report
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to navigate to Visibility Report from KPI")
	public void navigateToVisibilityKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToVisibilityRpt();
	}
	
	/**
	 * Test to navigate Accuracy Report
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to navigate to Accuracy Report from KPI")
	public void navigateToAccuracyKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToAccuracyReport();
	}
	
	/**
	 * Test to navigate Content Analysis
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to navigate to Content Analysis from KPI")
	public void navigateToContentAnalysisKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToContentAnalysis();
	}
	
	/**
	 * Test to navigate Google Ranking
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to navigate to Google Ranking from KPI")
	public void navigateToGoogleRankingKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToGoogleRanking();
	}
	
	/**
	 * Test to navigate Reviews
	 * @throws Exception
	 *//*
	@Test(priority = 6, description = "Test to naviagate to Review Insights")
	public void navigateToReviewsInsightsKPI() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.navigateToReviews();
	}*/
	
	/**
	 * Test to verify order of reports
	 * @throws Exception
	 */
	@Test(priority = 7, description = "Test to verify the order of the reports")
	public void VerifyOrderofReports() {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.VerifyOrder();
	}
	
	/**
	 * Test to verify icon and text size
	 * @throws Exception
	 */
	@Test(priority = 8, description = "Test to verify font size of text and icon size")
	public void VerifyIconandFontSize() {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.verifyIconsizeandtextsize();
	}
	
	/**
	 * Test to verify KPI dashboard hidden
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to verify KPI is hidden")
	public void verifyhidden() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.HideandVerify();
	}
	
	/**
	 * Test to verify languages listed
	 * @throws Exception
	 */
	@Test(priority = 10, description = "Test to verify the list of languages")
	public void verifyLanguageList() throws Exception {
		data = new TPSEE_KPI_Navigation(CurrentState.getDriver());
		data.getLangListnVerify();
	}
}
