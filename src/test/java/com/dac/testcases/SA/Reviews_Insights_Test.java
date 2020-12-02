package com.dac.testcases.SA;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Reviews_Insights_Test extends BaseClass{
	
	Navigationpage np;
	Reviews_Insights data;
	Reviews_Feed data1;
	SoftAssert soft = new SoftAssert();
	String From_Date = "//*[@id='dateFrom']";
	String To_Date = "//*[@id='dateTo']";
	static int totalreviews;
	
	/**
	 * Test to get Score from KPI Dashboard
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
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to Navigate to Reviews Insights Page")
	public void navigateToReviewsInsights() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsInsights();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Insights", "yes");
	}
	
	/**
	 * Test to verify active state of Report / Report Section
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify active state of the report")
	public void verifyreportactivestate() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.Review_Insights_Highlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of the report", "yes");
	}

	/**
	 * Test to verify title and title text
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to verify Title and Title Text")
	public void VerifyTitleText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyTitle("Review Insights",
				"This report provides metrics for location reviews across the sites that are being monitored. Read Manual");
		addEvidence(CurrentState.getDriver(), "Test to verify title and title text", "yes");
	}
	
	/**
	 * Test to verify notice tooltip text 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to verift text of Notice")
	public void VerifyNoticeText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.NoticeText();
		addEvidence(CurrentState.getDriver(), "Test to verify text of Notice", "yes");
	}
	
	/**
	 * Test to verify tooltip text for reviews, recommendations and responses 
	 * @throws Exception
	 */
	@Test(priority = 6, description = "Test to verify tooltip text for reviews, recommendations and responses")
	public void verifyTootipText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.tooltiptext();
		addEvidence(CurrentState.getDriver(), "Test to verify tootip text for reviews, recommendations and responses", "yes");
	}
	
	/**
	 * Test to verify Learn More Content
	 * @throws Exception
	 */
	@Test(priority = 7, description = "Test to verify Learn More Content")
	public void VerifyLearnMoreText() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyLearntext();
		addEvidence(CurrentState.getDriver(), "Test to verify LearnMore Text", "yes");
	}
	
	/**
	 * Test to verify average star rating
	 * @throws Exception
	 */
	@Test(priority = 8, description = "Test to verify average star rating")
	public void VerifyReviewStarAvg() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.AverageStar();
		addEvidence(CurrentState.getDriver(), "Test to verify average star rating", "yes");
	}

	/**
	 * Test to verify total reviews
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to verify total reviews")
	public void VerifyTotalReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		totalreviews = data.TotalReviews();
		addEvidence(CurrentState.getDriver(), "Test to verify total reviews", "yes");
	}
	
	/**
	 * Test to verify Positive recommendations
	 * @throws Exception
	 */
	@Test(priority = 10, description = "Test to verify Positive recommendations")
	public void VerifyPositiveReco() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		data.PositiveReco();
		addEvidence(CurrentState.getDriver(), "Test to verify Positive Recommendations", "yes");
	}
	
	/**
	 * Test to verify Negative Recommendations
	 * @throws Exception
	 */
	@Test(priority = 11, description = "Test to verify Negative Recommendations")
	public void VerifyNegativeReco() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		data.NegativeReco();
		addEvidence(CurrentState.getDriver(), "Test to verify Negative Recommendation", "yes");
	}
	
	/**
	 * Test to verify Response for Reviews
	 * @throws Exception
	 */
	@Test(priority = 12, description = "Test to verify Response for Reviews")
	public void VerifyResponse() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.reviewResponse();
		addEvidence(CurrentState.getDriver(), "Test to verify Response for Reviews", "yes");
	}
	
	/**
	 * Test to verify No Response for Reviews
	 * @throws Exception
	 */
	@Test(priority = 13, description = "Test to verify No Response for Reviews")
	public void VerifyNoResponse() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		data.reviewNoResponse();
		addEvidence(CurrentState.getDriver(), "Test to verify No Response for Reviews", "yes");
	}
	
	/**
	 * Test to verify RNPS Score
	 * @throws Exception
	 */
	@Test(priority = 14, description = "Test to verify RNPS Score")
	public void VerifyRNPS() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		data.RNPSScore();
		addEvidence(CurrentState.getDriver(), "Test to verify RNPS Score", "yes");
	}
	
	/**
	 * Test to compare KPI and Report score
	 */
	@Test(priority = 15, description = "Test to compare KPI and Report score")
	public void CompareKPIandReportValues() {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.CompareKPIandReportReviewScore();
	}
	
	/**
	 * Test to compare applied and chart dates
	 * @throws Exception
	 */
	@Test(priority = 16, description = "Test to compare applied and chart dates")
	public void CompareDateAppliednChart() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		data.CompareAppliedDatenChartDate();
		addEvidence(CurrentState.getDriver(), "Test to compare applied and chart dates", "yes");
	}
	
	/**
	 * Test to read review count from chart and compare overview review count
	 * @throws Exception
	 */
	@Test(priority = 17, description = "Test to read review count from chart and compare overview review count")
	public void compareChartCountnOvrCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.compareChartnReviewCount();
		addEvidence(CurrentState.getDriver(), "Test to read review count from chart and compare overview review count", "yes");
	}
	
	/**
	 * Test to read Recommended and Not Recommended from chart and compare with overview Recommended count
	 * @throws Exception
	 */
	@Test(priority = 18, description = "Test to read Reco and Not Reco from chart and compare with overview reco count")
	public void compareFacebookCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.FacebookVerify();
		addEvidence(CurrentState.getDriver(), "Test to read Reco and Not Reco from chart and compare with overview reco count", "yes");
	}
	
	/**
	 * Test to verify visbility of highcharts of different types
	 * @throws Exception
	 */
	@Test(priority = 19, description = "Test to verify visbility of highcharts of different types")
	public void verifyVisibilityOfHighchart() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyRecoHighchart();
		addEvidence(CurrentState.getDriver(), "Test to verify visbility of highcharts of Recommended type", "yes");
		data.verifyFiveStarHighChart();
		addEvidence(CurrentState.getDriver(), "Test to verify visbility of highcharts of Five Stars type", "yes");
		data.verifyThreeStarHighChart();
		addEvidence(CurrentState.getDriver(), "Test to verify visbility of highcharts of Three Stars type", "yes");
		data.verifyOneStarHighChart();
		addEvidence(CurrentState.getDriver(), "Test to verify visbility of highcharts of One Star type", "yes");
		data.verifyNotRecommendHighChart();
		addEvidence(CurrentState.getDriver(), "Test to verify visbility of highcharts of Not Recommended type", "yes");
		data.verifyNoAvailableHighChart();
		addEvidence(CurrentState.getDriver(), "Test to verify visbility of highcharts of Not Available type", "yes");
		soft.assertAll();
	}
	
	/**
	 * Test to export file and read data from CSV
	 * @throws Exception
	 */
	@Test(priority = 20, description = "Test to export file and read data from CSV")
	public void ExportLocationData() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ExportLoc();
		addEvidence(CurrentState.getDriver(), "Test to export location data" , "yes");
	}
	
	@Test(priority = 21, description = "Test to verify data in CSV")
	public void verifyCSVData() throws IOException {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ReadDataCSV();
	}
	
	@Test(priority = 22, description = "Test to verify highcharts zoom")
	public void verifyZoomFunction() {
		data = new Reviews_Insights(CurrentState.getDriver());
		try {
			try {
				String OneMonth = "1m";
				data.clickHighchartCriteria(OneMonth);
				addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String ThreeMonths = "3m";
				data.clickHighchartCriteria(ThreeMonths);
				addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String SixMonths = "6m";
				data.clickHighchartCriteria(SixMonths);
				addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String OneYear = "1y";
				data.clickHighchartCriteria(OneYear);
				addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String YearToDate = "ytd";
				data.clickHighchartCriteria(YearToDate);
				addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String ALLDATA = "all";
				data.clickHighchartCriteria(ALLDATA);
				addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 23, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 24, description = "Test to verify total reviews")
	public void compareTotReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.compareReviewCountbetReports();
	}
	
	@Test(priority = 25, description = "Test to verify positive review count")
	public void verifyPositiveCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyPositiveRatingnVerifyCount();
	}
	
	@Test(priority = 26, description = "Test to verify negative review count")
	public void verifyNegativeCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyNegativeRatingVerifyCount();
	}
	
	@Test(priority = 27, description = "Test to verify Response Review Count")
	public void VerifyResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyResponseCount();
		
	}
	
	@Test(priority = 28, description = "Test to verify No Response Review Count")
	public void VerifyNoResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyNotResponseCount();
	}
	
	@Test(priority = 29, description = "Test to get star rating count, calculate RNPS score and compare with Report")
	public void getStarCount() throws InterruptedException {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.getOneStar();
		Thread.sleep(2000);
		data.getTwoStar();
		Thread.sleep(2000);
		data.getThreeStar();
		Thread.sleep(2000);
		data.getFourStar();
		Thread.sleep(2000);
		data.getFiveStar();
		Thread.sleep(2000);
		data.getTotalStarCount();
		Thread.sleep(2000);
		data.getPromoterScore();
		Thread.sleep(2000);
		data.getTotThreeScore();
		Thread.sleep(2000);
		data.getDetractorScore();
		Thread.sleep(2000);
		data.CompareRNPSScore(); 
	}
	
	@Test(priority = 30, description = "Navigate back to Reviews Insights")
	public void naviagateToInsights() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsInsights();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Insights", "yes");
	}
	
	
	@Test(priority = 31, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider= "testData")
	public void DateFilter(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.selectCalender_FromDate(From_Date, (int) (Double.parseDouble(from_day)), from_month,
				(int) (Double.parseDouble(from_year)));
		Thread.sleep(3000);
		data.selectCalender_ToDate(To_Date, (int) (Double.parseDouble(to_day)), to_month,
				(int) (Double.parseDouble(to_year)));
		Thread.sleep(3000);
		data.clickApplyFilterBTN();
		addEvidence(CurrentState.getDriver(), "Applied Global Filters", "yes");
		data.CompareAppliedDatenChartDate();
		addEvidence(CurrentState.getDriver(), "Test to verify Date", "yes");
	}
	
	@Test(priority =32, description = "Test for date filter verification")
	public void DateFilterVerification() throws Exception {  
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyReviewStarAvg();
	} 
	
	@Test(priority = 33, description = "Test to verify Total reviews after applying date filter")
	public void verifyDateTotReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyTotalReviews(); 
	}
	
	@Test(priority = 34, description = "Test to verify Positive Reco after applying date filter")
	public void verifyPosReco() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyPositiveReco();
	}
	
	@Test(priority = 35, description = "Test to verify Negative Reco after applying date filter")
	public void verifyNegReco() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyNegativeReco();
	}
	
	@Test(priority = 36, description = "Test to verify Review Response after applying date filter")
	public void verifyResponse() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyResponse();
	}
	@Test(priority = 37, description = "Test to verify RNPS after applying date filter")
	public void verifyRNPSScore() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyRNPS();
	}
	
	@Test(priority = 38, description = "Test to Date verification in chart after applying date filter")
	public void chartDateVerify() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		CompareDateAppliednChart();	
	}
	
	@Test(priority = 39, description = "Test to verify Chart and overview count after applying date filter")
	public void verifyChartnOvrCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		compareChartCountnOvrCount();
	}
	
	@Test(priority = 40, description = "Test to verify Facebook Count after applying date filter")
	public void verifyFaceCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		compareFacebookCount();
	}
	
	@Test(priority = 41, description = "Test to navigate to Insights Page")
	public void navigateInsights() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		navigateToReviewsFeed();
	}
	
	
	@Test(priority = 42, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider= "testData")
	public void FeedDateFilter(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.selectCalender_FromDate(From_Date, (int) (Double.parseDouble(from_day)), from_month,
				(int) (Double.parseDouble(from_year)));
		Thread.sleep(3000);
		data.selectCalender_ToDate(To_Date, (int) (Double.parseDouble(to_day)), to_month,
				(int) (Double.parseDouble(to_year)));
		Thread.sleep(3000);
		data.clickApplyFilterBTN();
		addEvidence(CurrentState.getDriver(), "Applied Global Filters", "yes");
	}
	
	@Test(priority = 43, description = "Comparison of total reviews between reports after applying filter ")
	public void FeedInsightsCompare() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		compareTotReviews();
	}
	
	@Test(priority = 44, description = "Comparison of Positive reviews between reports after applying filter ")
	public void verifyComPositiveCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		verifyPositiveCount(); 
	}
	
	@Test(priority = 45, description = "Comparison of Negative reviews between reports after applying filter ")
	public void verifyComNagativeCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		verifyNegativeCount();
	}
	
	@Test(priority = 46, description = "Comparison of Reponse reviews between reports after applying filter ")
	public void veifyResCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyResponseReviewCount();
	}
	
	@Test(priority = 47, description = "Comparison of No Resonse reviews between reports after applying filter ")
	public void VerifyNoResCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyNoResponseReviewCount();
	}
	
	@Test(priority = 48, description = "Comparison of RNPS Score between reports after applying filter ")
	public void verifyRNPS() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		getStarCount();
	}
	
	@Test(priority = 49, description = "navigate to reviews insights ")
	public void naviagtionInsights() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		naviagateToInsights();
	}
	
	/**
	 * Test to apply filter
	 * @throws Exception
	 */
	@Test(priority = 50, description = "Test to apply Global Filter")
	public void ApplyGlobalFilters() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Filter");
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
	
	
	@Test(priority = 51, description = "Export file and calculate score")
	public void ExportInsights() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ExportInsightsFilter();
		addEvidence(CurrentState.getDriver(), "Test to export", "yes");
		Thread.sleep(3000);
		data.ReadnverifyData();
		addEvidence(CurrentState.getDriver(), "Test to validate scores", "yes");		
	}
	
	@Test(priority =52, description = "Test for date filter verification")
	public void LocationFilterVerification() throws Exception {  
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyReviewStarAvg();
	} 
	
	@Test(priority = 53, description = "Test to verify Total reviews after applying location filter")
	public void LocationverifyDateTotReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyTotalReviews(); 
	}
	
	@Test(priority = 54, description = "Test to verify Positive Reco after applying location filter")
	public void LocationverifyPosReco() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyPositiveReco();
	}
	
	@Test(priority = 55, description = "Test to verify Negative Reco after applying location filter")
	public void LocationverifyNegReco() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyNegativeReco();
	}
	
	@Test(priority = 56, description = "Test to verify Review Response after applying location filter")
	public void LocationverifyResponse() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyResponse();
	}
	@Test(priority = 57, description = "Test to verify RNPS after applying location filter")
	public void LocationverifyRNPSScore() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyRNPS();
	}
	
	@Test(priority = 58, description = "Test to Date verification in chart after applying Location filter")
	public void LocationchartDateVerify() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		CompareDateAppliednChart();	
	}
	
	@Test(priority = 59, description = "Test to verify Chart and overview count after applying Location filter")
	public void LocationverifyChartnOvrCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		compareChartCountnOvrCount();
	}
	
	@Test(priority = 60, description = "Test to verify Facebook Count after applying date filter")
	public void LocationverifyFaceCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		compareFacebookCount();
	}
	
	@Test (priority = 61, description = "Test to navigate to Reviews Feed Page")
	public void navigationFeed() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		navigateToReviewsFeed();
	}
	
	/**
	 * Test to apply filter
	 * @throws Exception
	 */
	@Test(priority = 62, description = "Test to apply Global Filter")
	public void FeedApplyGlobalFilters() throws Exception { 
		data = new Reviews_Insights(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Filter");
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
	
	@Test(priority = 63, description = "Comparison of total reviews between reports after applying filter ")
	public void LocationFeedInsightsCompare() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		compareTotReviews();
	}
	
	@Test(priority = 64, description = "Comparison of Positive reviews between reports after applying filter ")
	public void LocationverifyComPositiveCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		verifyPositiveCount(); 
	}
	
	@Test(priority = 65, description = "Comparison of Negative reviews between reports after applying filter ")
	public void LocationverifyComNagativeCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		verifyNegativeCount();
	}
	
	@Test(priority = 66, description = "Comparison of Reponse reviews between reports after applying filter ")
	public void LocationveifyResCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyResponseReviewCount();
	}
	
	@Test(priority = 67, description = "Comparison of No Resonse reviews between reports after applying filter ")
	public void LocationVerifyNoResCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		VerifyNoResponseReviewCount();
	}
	
	@Test(priority = 68, description = "Comparison of RNPS Score between reports after applying filter ")
	public void LocationverifyRNPS() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		getStarCount();
	}
	
	@Test(priority = 69, description = "navigate to reviews insights ")
	public void navigationInsights() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		naviagateToInsights();
	}
	
	
	
	
	@SuppressWarnings("finally")
	@DataProvider
	public String[][] testData() {
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Zoom");
			wb.deleteEmptyRows();
			int rowCount = wb.getRowCount();
			System.out.println("rowCount : " + rowCount);
			data = new String[rowCount - 1][6];
			data1 = new String[rowCount - 1][6];
			int row = 0;
			for (int i = 2; i <= rowCount; i++) {
				int colCount = wb.getColCount(i);
				for (int j = 0; j < colCount; j++) {
					if (j > 0) {
						if (((wb.getCellValue(i, j).trim()).equalsIgnoreCase("null"))
								| ((wb.getCellValue(i, j).trim()).length() == 0)) {
							data1[row][j] = "null";
						} else
							data1[row][j] = wb.getCellValue(i, j).trim();
					} else
						data1[row][j] = wb.getCellValue(i, j).trim();
				}
				row++;
			}
			data[0][0] = wb.getCellValue(2, 0); // from day
			data[0][1] = wb.getCellValue(2, 1); // from month
			data[0][2] = wb.getCellValue(2, 2); // from year
			data[0][3] = wb.getCellValue(2, 3); // to day
			data[0][4] = wb.getCellValue(2, 4); // to month
			data[0][5] = wb.getCellValue(2, 5); // to year
			System.out.println("Arrays.deepToString(data) : " + Arrays.deepToString(data));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return data;
		}
	}
}
