package com.dac.testcases.TPSEE;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_GMB;
import com.dac.main.POM_TPSEE.TPSEE_GoogleRanking_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_GoogleRanking_Test extends BaseClass {

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_GoogleRanking_Page data;
	double score;
	int location;
	String grph = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none";
	int start;
	int end;
	int start1;
	int end1;
	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";
	SoftAssert soft = new SoftAssert();

	/**
	 * Test to get dashboard scores
	 * 
	 * @throws Exception
	 *//*
	@SuppressWarnings("static-access")
	@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
	public void GetKPIValues() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		Thread.sleep(10000);
		score = data.getGRScore();
		System.out.println(score);
		location = data.getGRLoc();
		System.out.println(location);
		CurrentState.getLogger().log(Status.PASS, "KPI Scores");
		addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
	}*/

	@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to Google Ranking page")
	public void navigateToGoogleRankingPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToGoogleRanking();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Google Ranking page");
		addEvidence(CurrentState.getDriver(), "Navigate to Google Ranking page from Dashboard", "yes");
	}
	
	@Test(priority = 3, description = "Test to verify highlight of report")
	public void VerifyGRHighlight() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.GoogleRankinghighlight();
		addEvidence(CurrentState.getDriver(), "Test to verify report highlight", "yes");
	}

	@Test(priority = 4, groups = {"smoke" }, description = "Test for verifying title and description of report")
	public void verifytitle() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.VerifyGRText();
		addEvidence(CurrentState.getDriver(), "verification f title", "yes");
	}

	/*// GRScorenLoc
	@Test(priority = 3, groups = { "smoke" }, description = "Test for navigating to ContentAnalysis page")
	public void Verifyscorenloc() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		double GRscore = data.GRScore();
		Assert.assertEquals(GRscore, score);
		int GRLoc = data.GRLoc();
		Assert.assertEquals(GRLoc, location);
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
		addEvidence(CurrentState.getDriver(), "Navigate to ContentAnalysis page from Dashboard", "yes");
	}*/

	// Test to verify Zoom Functionality
	@Test(priority = 5, groups = { "smoke" }, description = "Verify Zoom Functionality")
	public void gethighchartsdate() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
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

	@SuppressWarnings("unused")
	@Test(priority = 6, enabled = true, dataProvider = "testData", description = "Test for Manual date selection")
	public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {

		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		if (!(from_day.equals("null")) | !(to_day.equals("null"))) {
			data.selectCalender_FromDate(grphfromDate, (int) (Double.parseDouble(from_day)), from_month,
					(int) (Double.parseDouble(from_year)));
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			data.selectCalender_ToDate(grphtoDate, (int) (Double.parseDouble(to_day)), to_month,
					(int) (Double.parseDouble(to_year)));
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			Date fromcal = data.getCurrentfromDate();
			Date fromgrph = data.verifyinitialHistoryGraph(start, end, grph);
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			// Assert.assertEquals(fromgrph, fromcal);
			Date tocal = data.getCurrenttoDate();
			Date togrph = data.verifyfinalHistorygraph(2, 0, grph);
			// Assert.assertEquals(togrph, tocal);
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
		}
	}

	@SuppressWarnings("finally")
	@DataProvider
	public String[][] testData() {
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Zoom");
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

	@Test(priority = 7, groups = { "smoke" }, description = "Add Account Level and Group Level Keyword")
	public void verifyApplyKeywords() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		try {
			ExcelHandler wb = new ExcelHandler("./data/GroupAndAccountKeywords.xlsx", "AccountLevelKeywords");
			wb.deleteEmptyRows();
			TPSEE_GoogleRanking_Page s = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
			for (int i = 1; i <= wb.getRowCount(); i++) {
				if (i > 1)
				s.waitUntilLoad(CurrentState.getDriver());
				String AccKey = wb.getCellValue(i, wb.seacrh_pattern("AccountKeyword", 0).get(0).intValue());
				String GrKey = wb.getCellValue(i, wb.seacrh_pattern("GroupKey", 0).get(0).intValue());
				String GrKeyword = wb.getCellValue(i, wb.seacrh_pattern("GroupKeyword", 0).get(0).intValue());
				s.applyKeywords(AccKey, GrKey, GrKeyword);
				System.out.println(AccKey + ", " + GrKey + ", " + GrKeyword);
				System.out.println();
				s.clickApplyKeyword();
				BaseClass.addEvidence(CurrentState.getDriver(),
						"Applied Account and Group Level Keywords: " + AccKey + ", " + GrKey + ", " + GrKeyword + "",
						"yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "Filter" })
	@Test(priority = 8, groups = { "smoke" }, description = "Verify Google Ranking page loads after filter applied")
	public void verifyFilteringReportsnavigateToGoogleRanking(int Filter) throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_GoogleRanking_Page s = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
				s.waitUntilLoad(CurrentState.getDriver());

				String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(Filter, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(Filter, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(Filter, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(Filter, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.LAVapplyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
				s.clickApplyFilterBTNLAV();
				BaseClass.addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode
						+ ", " + State + ", " + City + ", " + Location + "", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Test for export and overview report in Content Analysis Page
	@Test(priority = 9, groups = { "smoke" }, description = "Test for Ranking scores Data")
	public void verifyRankingScoreDataGoogleRanking() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.RankingScoresData();
		addEvidence(CurrentState.getDriver(), "Verified Ranking Score for Google Ranking", "yes");
	}

	// Test for Tooltip and overview report in Content Analysis Page
	@Test(priority = 10, groups = { "smoke" }, description = "Test to get Tooltip Data")
	public void verifyToolTipGoogleRanking() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.verifyHistoryGraph1();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Google Ranking", "yes");
	}
	
	@Test(priority = 11, description = "Test to compare overall and graph score")
	public void verifygraphscorenovrscore() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.comparegraphovrscr();
		addEvidence(CurrentState.getDriver(), "Test to compare overview and graph score", "yes");
	}

	@Test(priority = 12, groups = { "smoke" }, description = "Export as csv")
	public void exportascsv() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.GRDataTableExportCSV();
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}

	// Test for export and overview report in Content Analysis Page
	@Test(priority = 13, groups = { "smoke" }, description = "Test for Ranking export and export verification")
	public void verifyTableDataoExport() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.compareexporttableDatanrankingdetails(data.RankingDataTable(), data.getRankingDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Ranking export for Google Ranking", "yes");
	}
	
	@Test(priority = 14, description ="Test to verify GoTo page")
	public void GOTO() throws InterruptedException {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.GoTo();
	}
	
	@Test(priority = 15, description = "Test to verify Resultsperpage")
	public void ResultsperPage() throws InterruptedException {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.resultperpage(soft);
		soft.assertAll();
		
	}

	/**
	 * Test to verify Top button functionality
	 * 
	 * @throws Exception
	 */
	@Test(priority = 16, groups = { "smoke" }, description = "Verify Top Button")
	public void GetTopBtn() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		data.TopButton();
		addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
	}
	
	@Test(priority = 17, description = "Test to verify filter data is in order")
	public void verifyFilterDataOrder() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(1, wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
			CurrentState.getDriver().navigate().refresh();
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "FilterOrder");
		wb.deleteEmptyRows();
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String CountryCode = wb.getCellValue(1, wb.seacrh_pattern("Country", 0).get(0).intValue());
		String State = wb.getCellValue(1, wb.seacrh_pattern("State", 0).get(0).intValue());
		String City = wb.getCellValue(1, wb.seacrh_pattern("City", 0).get(0).intValue());
		data.GetDataListnVerifyOrder(Group, CountryCode, State, City);
		}else {
			System.out.println("The group is not empty");
		}
	}
}
