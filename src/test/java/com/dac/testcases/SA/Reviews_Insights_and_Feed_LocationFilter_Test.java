package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Reviews_Insights_and_Feed_LocationFilter_Test extends BaseClass {

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
	 * Test to apply filter
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to apply Global Filter")
	public void ApplyGlobalFilters() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Insights");
		int count = 1;
		for (int i = 1; i <= wb.getRowCount(); i++) {
			System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
			if (i > 1)
				CurrentState.getDriver().navigate().refresh();
			data.waitUntilLoad(CurrentState.getDriver());
			String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
			String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
			String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
			String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
			String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
			data.applyGlobalFilter(Group, CountryCode, State, City, Location);
			System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
			data.clickApplyFilterBTN();
			addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode + ", " + State
					+ ", " + City + ", " + Location + "", "yes");
		}
	}

	/**
	 * Test to export and verify data after applying location filter with
	 * calculations included
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Export file and calculate score")
	public void ExportInsights() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ExportInsightsFilter();
		addEvidence(CurrentState.getDriver(), "Test to export", "yes");
	}
	
	@Test(priority = 4, description = "Read data from CSV file" , dependsOnMethods = {"ExportInsights"})
	public void ReadandVerifyData() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ReadnverifyData();
		addEvidence(CurrentState.getDriver(), "Test to validate scores", "yes");
	}
	
	/**
	 * Test to verify average star rating
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to verify average star rating" , dependsOnMethods = {"ReadandVerifyData"})
	public void GetReviewStarAvg() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.AverageStar(); 
		data.TotalReviews();
		data.PositiveReco();
		data.NegativeReco(); 
		data.reviewResponse();
		data.reviewNoResponse();
		data.RNPSScore();
	}

	
	/**
	 * Test to compare applied and chart dates
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6, description = "Test to compare applied and chart dates", dependsOnMethods = {"GetReviewStarAvg"})
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
	@Test(priority = 7, description = "Test to read review count from chart and compare overview review count" , dependsOnMethods = {"CompareDateAppliednChart"})
	public void compareChartCountnOvrCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.compareChartnReviewCount(); 
	}

	/**
	 * Test to read Recommended and Not Recommended from chart and compare with
	 * overview Recommended count
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8, description = "Test to read Reco and Not Reco from chart and compare with overview reco count" , dependsOnMethods = {"compareChartCountnOvrCount"})
	public void compareFacebookCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.FacebookVerify();
		addEvidence(CurrentState.getDriver(),
				"Test to read Reco and Not Reco from chart and compare with overview reco count", "yes");
	}
	
	/**
	 * Test to navigate to Reviews Feed
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to Navigate to Reviews Feed Page" , dependsOnMethods = {"compareFacebookCount"})
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		/*data = new Reviews_Insights(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");*/ 
	}
	
	/**
	 * Test to apply filter
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10, description = "Test to apply Global Filter" , dependsOnMethods = {"navigateToReviewsFeed"})
	public void FeedApplyGlobalFilters() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Insights");
		int count = 1;
		for (int i = 1; i <= wb.getRowCount(); i++) {
			System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
			if (i > 1)
				CurrentState.getDriver().navigate().refresh();
			data.waitUntilLoad(CurrentState.getDriver());
			String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
			String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
			String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
			String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
			String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
			data.applyGlobalFilter(Group, CountryCode, State, City, Location);
			System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
			data.clickApplyFilterBTN();
			addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode + ", " + State
					+ ", " + City + ", " + Location + "", "yes");
		}
	}

	/**
	 * Test to Compare Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11, description = "Test to verify total reviews", dependsOnMethods = {"FeedApplyGlobalFilters"})
	public void compareTotReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.compareReviewCountbetReports();
	}

	/**
	 * Test to Compare Positive Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 12, description = "Test to verify positive review count" , dependsOnMethods = {"FeedApplyGlobalFilters"})
	public void verifyPositiveCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyPositiveRatingnVerifyCount();
	}

	/**
	 * Test to Compare Negative Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13, description = "Test to verify negative review count", dependsOnMethods = {"FeedApplyGlobalFilters"})
	public void verifyNegativeCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyNegativeRatingVerifyCount();
	}

	/**
	 * Test to Compare Response Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 14, description = "Test to verify Response Review Count" , dependsOnMethods = {"FeedApplyGlobalFilters"})
	public void VerifyResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyResponseCount();

	}

	/**
	 * Test to Compare No Response Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15, description = "Test to verify No Response Review Count" , dependsOnMethods = {"FeedApplyGlobalFilters"})
	public void VerifyNoResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyNotResponseCount();
	}

	/**
	 * Test to Compare RNPS Score between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 16, description = "Test to get star rating count, calculate RNPS score and compare with Report" , dependsOnMethods = {"FeedApplyGlobalFilters"})
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
