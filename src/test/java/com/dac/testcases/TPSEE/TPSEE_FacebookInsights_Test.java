package com.dac.testcases.TPSEE;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Bing_Page;
import com.dac.main.POM_TPSEE.TPSEE_FacebookInsights_Page;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_FacebookInsights_Test extends BaseClass {

	Navigationpage np;
	TPSEE_FacebookInsights_Page data;
	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";

	/**
	 * Test to navigate to Facebook Insights Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to navigate to Facebook Page")
	public void navigateToFaceBookInsights() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToFacebookInsights();
		CurrentState.getLogger().log(Status.PASS, "Navigated Successfully to Facebook Page");
		addEvidence(CurrentState.getDriver(), "Navigation to Facebook Page", "yes");
		Thread.sleep(5000);
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		data.clickDone();
	}

	@Test(priority = 2, description = "Test to verify highlight of report")
	public void VerifyFacebookHighlight() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		data.Facebookhighlight();
		addEvidence(CurrentState.getDriver(), "Test to verify report highlight", "yes");
	}
	
	/**
	 * Test to verify Title and Title Text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify title")
	public void verifyTitleText() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		data.VerifyFacebookTitleText("Facebook Insights",
				"The below report outlines key performance indicators on Facebook for your locations such as page impressions, post engagements, actions, check-in’s and fans. This data is updated daily. Read Manual");
		addEvidence(CurrentState.getDriver(), "To Verify Title and Title Text", "yes");
	}

	/**
	 * Test to Apply Filters
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to apply filters")
	public void verifyFilteringReportsFacebook() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_FacebookInsights_Page s = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
			for (int i = 1; i <= wb.getRowCount(); i++) {
				System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
				if (i > 1)
					CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode
						+ ", " + State + ", " + City + ", " + Location + "", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * verification of date
	 */
	@Test(priority = 6, description = "Test to verify graph")
	public void verifyGraphncompareDate() {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		boolean datavalidation = data.IsDataAvailable();
		if (!datavalidation == true) {
		try {
			data.getGraphDatenVerify();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	/**
	 * To verify zoom functionality
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to verify highcharts")
	public void gethighchartsdate() throws Exception {
		boolean datavalidation = data.IsDataAvailable();
		if (!datavalidation == true) {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
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
	}

	/**
	 * To Verify manual date selection
	 * 
	 * @param from_day
	 * @param from_month
	 * @param from_year
	 * @param to_day
	 * @param to_month
	 * @param to_year
	 * @throws Exception
	 */
	@Test(priority = 7, enabled = true, dataProvider = "testData")
	public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		boolean datavalidation = data.IsDataAvailable();
		if (!datavalidation == true) {
			if (!(from_day.equals("null")) | !(to_day.equals("null"))) {
				data.selectCalender_FromDate(grphfromDate, (int) (Double.parseDouble(from_day)), from_month,
						(int) (Double.parseDouble(from_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				data.selectCalender_ToDate(grphtoDate, (int) (Double.parseDouble(to_day)), to_month,
						(int) (Double.parseDouble(to_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				CurrentState.getDriver().navigate().refresh();
			}
		}
	}

	/**
	 * Test to verify Pie Chart Tooltip Data
	 */
	@Test(priority = 8, description = "Test to verify Pie chart")
	public void VerifyPieChartData() {
		boolean datavalidation = data.IsDataAvailable();
		if (!datavalidation == true) {
			data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
			data.VerifyPieChart();
		}
	}

	/**
	 * Test to Export Data
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to verify Export")
	public void Export() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		boolean datavalidation = data.IsDataAvailable();
		if (!datavalidation == true) {
			try {
				data.ExportasCSV();
				addEvidence(CurrentState.getDriver(), "Export as CSV", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				data.ExportasXLSX();
				addEvidence(CurrentState.getDriver(), "Export as XLSX", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Test to Compare UI ann XL Data
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10, description = "Test to compare UI and XL")
	public void CompareUIXLData() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		boolean datavalidation = data.IsDataAvailable();
		if (!datavalidation == true) {
			try {
				data.PageAction();
				addEvidence(CurrentState.getDriver(), "To get Number of Phone Calls", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				data.Checkin();
				addEvidence(CurrentState.getDriver(), "To Compare UI and XL CheckIn", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				data.Fans();
				addEvidence(CurrentState.getDriver(), "To Compare XL and UI Fans", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				data.PostEngagement();
				addEvidence(CurrentState.getDriver(), "To Compare XL and UI Post Engagements", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				data.getImpressionCount();
				addEvidence(CurrentState.getDriver(), "To Compare XL and UI Impressions", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Test Data for manual date selection
	 * 
	 * @return
	 */
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
}
