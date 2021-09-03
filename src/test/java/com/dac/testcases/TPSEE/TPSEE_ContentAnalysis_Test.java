package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_ContentAnalysis_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_ContentAnalysis_Test extends BaseClass {

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_ContentAnalysis_Page data;
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
	@Parameters({ "Filter" })
	@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
	public void GetKPIValues(int Filter) throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		Thread.sleep(10000);
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		score = data.getContentscore();
		System.out.println(score);
		location = data.getContentLoc();
		System.out.println(location);
		CurrentState.getLogger().log(Status.PASS, "KPI Scores");
		addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}*/

	/**
	 * test to navigate to content analysis
	 * @throws Exception
	 */
	@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to ContentAnalysis page")
	public void navigateToContentAnalysisPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToContentAnalysis();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE ContentAnalysis page");
		addEvidence(CurrentState.getDriver(), "Navigate to ContentAnalysis page from Dashboard", "yes");
	}
	
	/**
	 * test to verify title and title text
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 3, groups = { "smoke" }, description = "Test for verifying title and description of report")
	public void verifyText(int Filter) throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		data.VerifyTitleText("Content Analysis Report",
				"This report identifies how complete your listings' primary data is across the sites that are being monitored. Read Manual");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}

	/**
	 * test to compare KPI and report score
	 * @throws Exception
	 *//*
	@Parameters({ "Filter" })
	@Test(priority = 4, groups = { "smoke" }, description = "Test for navigating to ContentAnalysis page")
	public void Verifyscorenloc(int Filter) throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		double CAscore = data.CAScore();
		soft.assertEquals(CAscore, score);
		int CALoc = data.CALoc();
		soft.assertEquals(CALoc, location);
		soft.assertAll();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Content Analysis page");
		addEvidence(CurrentState.getDriver(), "Navigate to ContentAnalysis page from Dashboard", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}*/


	/**
	 * test for zoom functionality
	 * @throws Exception
	 *//*
	@Test(priority = 5, groups = { "smoke" }, description = "Verify Zoom Functionality")
	public void gethighchartsdate() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
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
	}*/

	/**
	 * test to set calendar date
	 * @param from_day
	 * @param from_month
	 * @param from_year
	 * @param to_day
	 * @param to_month
	 * @param to_year
	 * @throws Exception
	 */
	/*@SuppressWarnings("unused")
	@Test(priority = 6, enabled = true, dataProvider = "testData",description = "Verify manual date selection")
	public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {

		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
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
	}*/

	/**
	 * test to apply filter
	 * @param Filter
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 7, groups = { "smoke" }, description = "Verify Content Analysis page loads after filter applied")
	public void verifyFilteringReportsContentAnalysis(int Filter) throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_ContentAnalysis_Page s = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
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


	/**
	 * Compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 8, groups = { "smoke" }, description = "Test for overview export and export verification")
	public void verifyOverviewReportnExportContentAnalysis() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteData(data.getExportData(), data.AnalysisSiteData());
		addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
	}


	/**
	 * to verify history graph
	 * @throws Exception
	 */
	/*@Test(priority = 9, groups = { "smoke" }, description = "Test to verify latest date in graph")
	public void verifyOverviewReportnTooltipContentAnalysis() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		data.verifyHistoryGraph1();
		addEvidence(CurrentState.getDriver(), "Test to verify latest date in graph", "yes");
	}*/
	
	/**
	 * compare overview and graph score
	 * @throws Exception
	 */
	/*@Test(priority = 10, description = "Test to compare ToolTip Value and Overall Analysis Score")
	public void comparegrphnovrscore() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		data.compareovrviewngraphscore();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Content Analysis report", "yes");
	}*/
	
	
	/*@SuppressWarnings("finally")
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
	}*/


	/**
	 * compare UI and Xl data
	 * @throws Exception
	 */
	@Test(priority = 11, groups = { "smoke" }, description = "Test for overview export and export verification")
	public void verifyTableDataoExport() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		data.SitelLinkData();
		addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
	}


	/**
	 * Test to verify Top button functionality
	 * 
	 * @throws Exception
	 */
	@Test(priority = 12, groups = { "smoke" }, description = "Verify Top Button")
	public void GetTopBtn() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		data.TopButton();
		addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
	}
	
	/**
	 * to verify location details
	 * @param Filter
	 * @throws Exception
	 */
	@Parameters({"Filter"})
	@Test(priority = 13, description = "Test to verify location details")
	public void VerifyLocationDetailsLocationTab(int Filter) throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Location = wb.getCellValue(Filter, wb.seacrh_pattern("Location", 0).get(0).intValue());
		System.out.println("The Location selected is :" +Location);
		if(!Location.equals("null")) {
			data.verifyLocationFilterAddress(Location);
		}else {
			System.out.println("No location selected");
		}
	}
	
	/**
	 * to verify location details from site location tab
	 * @param Filter
	 * @throws Exception
	 */
	/*@Parameters({"Filter"})
	@Test(priority = 14, description = "Test to verify location details of vendors sites")
	public void VerifySiteLocationTab(int Filter) throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Location = wb.getCellValue(Filter, wb.seacrh_pattern("Location", 0).get(0).intValue());
		System.out.println("The Location selected is :" +Location);
		if(!Location.equals("null")) {
			data.verifyLocationFilterSiteAddress(Location);
		}else {
			System.out.println("No location selected");
		}
	}*/
	
	/**
	 * test to verify filter data order
	 * @param Filter
	 * @throws Exception
	 */
	@Parameters({"Filter"})
	@Test(priority = 15, description = "Test to verify filter data is in order")
	public void verifyFilterDataOrder(int Filter) throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Filter, wb1.seacrh_pattern("Country", 0).get(0).intValue());
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
