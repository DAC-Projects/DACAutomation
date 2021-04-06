package com.dac.testcases.SA;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Insights;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Reviews_Insights_and_Feed_DateFilterDataVerication_Test extends BaseClass {

	Navigationpage np;
	Reviews_Insights data;
	String From_Date = "//*[@id='dateFrom']";
	String To_Date = "//*[@id='dateTo']";
	
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
	 * Test to apply Date Filter and Compare Data
	 * 
	 * @param from_day
	 * @param from_month
	 * @param from_year
	 * @param to_day
	 * @param to_month
	 * @param to_year
	 * @throws Exception 
	 */
	@Test(priority = 2, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider = "testData")
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
	
	/**
	 * Test to verify average star rating
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify average star rating")
	public void GetReviewStarAvg() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.AverageStar(); 
		data.TotalReviews();
		data.PositiveReco();
		data.NegativeReco(); 
		data.reviewResponse();
		data.reviewNoResponse();
	}
	
	/**
	 * Test to compare applied and chart dates
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to compare applied and chart dates")
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
	@Test(priority = 5, description = "Test to read review count from chart and compare overview review count")
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
	@Test(priority = 6, description = "Test to read Reco and Not Reco from chart and compare with overview reco count")
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
	@Test(priority = 7, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		/*data = new Reviews_Insights(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes"); */
	}
	
	/**
	 * Test to apply date Filter
	 * 
	 * @param from_day
	 * @param from_month
	 * @param from_year
	 * @param to_day
	 * @param to_month
	 * @param to_year
	 * @throws Exception
	 */
	@Test(priority = 8, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider = "testData")
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
	
	/**
	 * Test to Compare Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to verify total reviews")
	public void compareTotReviews() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.compareReviewCountbetReports();
	}

	/**
	 * Test to Compare Positive Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10, description = "Test to verify positive review count")
	public void verifyPositiveCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyPositiveRatingnVerifyCount();
	}

	/**
	 * Test to Compare Negative Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11, description = "Test to verify negative review count")
	public void verifyNegativeCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.ApplyNegativeRatingVerifyCount();
	}

	/**
	 * Test to Compare Response Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 12, description = "Test to verify Response Review Count")
	public void VerifyResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyResponseCount();

	}

	/**
	 * Test to Compare No Response Count between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13, description = "Test to verify No Response Review Count")
	public void VerifyNoResponseReviewCount() throws Exception {
		data = new Reviews_Insights(CurrentState.getDriver());
		data.verifyNotResponseCount();
	}

	/**
	 * Test to Compare RNPS Score between Feed and Insights
	 * 
	 * @throws Exception
	 */
	@Test(priority = 14, description = "Test to get star rating count, calculate RNPS score and compare with Report")
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
