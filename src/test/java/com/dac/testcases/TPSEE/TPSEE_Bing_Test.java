package com.dac.testcases.TPSEE;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_Bing_Page;
import com.dac.main.POM_TPSEE.TPSEE_GoogleRanking_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Bing_Test extends BaseClass {

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Bing_Page data;

	String grph = ".highcharts-label.highcharts-tooltip-box.highcharts-color-none";
	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";

	String chromepath = "./downloads/chromeBingXLSX.xlsx";
	String IEpath = "./downloads/IEBingXLSX.xlsx";
	String FFpath = "./downloads/FFBingXLSX.xlsx";

	String chromepath1m = "./downloads/chromeBingXLSX1m.xlsx";
	String IEpath1m = "./downloads/IEBingXLSX1m.xlsx";
	String FFpath1m = "./downloads/FFBingXLSX1m.xlsx";

	String chromepath3m = "./downloads/chromeBingXLSX3m.xlsx";
	String IEpath3m = "./downloads/IEBingXLSX3m.xlsx";
	String FFpath3m = "./downloads/FFBingXLSX3m.xlsx";

	String chromepath1y = "./downloads/chromeBingXLSX1y.xlsx";
	String IEpath1y = "./downloads/IEBingXLSX1y.xlsx";
	String FFpath1y = "./downloads/FFBingXLSX1y.xlsx";

	String chromepathytd = "./downloads/chromeBingXLSXytd.xlsx";
	String IEpathytd = "./downloads/IEBingXLSXytd.xlsx";
	String FFpathytd = "./downloads/FFBingXLSXytd.xlsx";

	String chromepath6m = "./downloads/chromeBingXLSX6m.xlsx";
	String IEpath6m = "./downloads/IEBingXLSX6m.xlsx";
	String FFpath6m = "./downloads/FFBingXLSX6m.xlsx";

	String chromepathall = "./downloads/chromeBingXLSXall.xlsx";
	String IEpathall = "./downloads/IEBingXLSXall.xlsx";
	String FFpathall = "./downloads/FFBingXLSXall.xlsx";

	String chromepathdateselect = "./downloads/chromeBingXLSXdateselect.xlsx";
	String IEpathdateselect = "./downloads/IEBingXLSXdateselect.xlsx";
	String FFpathdateselect = "./downloads/FFBingXLSXdateselect.xlsx";

	String BingXLSX = "BingXLSX.xlsx";
	String BingXLSX1m = "BingXLSX1m.xlsx";
	String BingXLSX3m = "BingXLSX3m.xlsx";
	String BingXLSX6m = "BingXLSX6m.xlsx";
	String BingXLSXytd = "BingXLSXytd.xlsx";
	String BingXLSX1y = "BingXLSX1y.xlsx";
	String BingXLSXall = "BingXLSXall.xlsx";
	String BingXLSXdateselect = "BingXLSXdateselect.xlsx";

	// Test to navigate to Bing Page
	@Test(priority = 1, groups = { "smoke" }, description = "Test for navigating to Bing page")
	public void navigateToBingPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToBingPlacesForBusiness();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Bing page");
		addEvidence(CurrentState.getDriver(), "Navigate to Bing page from Dashboard", "yes");
	}
	
	@Test(priority = 2, description = "Test to verify highlight of report")
	public void VerifyBingHighlight() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		data.Binghighlight();
		addEvidence(CurrentState.getDriver(), "Test to verify report highlight", "yes");
	}

	@Test(priority = 3, groups = { "smoke" }, description = "Test for verify title and description")
	public void verifyText() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		data.VerifyTitleText1("Bing Places for Business",
				"This report provides insights into the weekly number of impressions that occurred for each of your location(s) on Bing over time. This information is obtained from Bing and is on average two weeks behind. Read Manual");

		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
	}

	@Test(priority = 4, groups = { "smoke" }, description = "Test for verify hover text")
	public void verifyHoverText() {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		data.GetHoverText("Total Impressions based on the selected weekly range. ");
	}

	// Test to apply filter in Bing Page
	@Parameters({ "Filter" })
	@Test(priority = 5, groups = { "smoke" }, description = "Verify Bing page loads after filter applied")
	public void verifyFilteringReportsBing(int Filter) throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Bing");
			wb.deleteEmptyRows();
			TPSEE_Bing_Page s = new TPSEE_Bing_Page(CurrentState.getDriver());
				System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
				s.waitUntilLoad(CurrentState.getDriver());
				String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(Filter, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(Filter, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(Filter, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(Filter, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode
						+ ", " + State + ", " + City + ", " + Location + "", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Test for Tooltip score in Bing Page
	@Test(priority = 6, groups = { "smoke" }, description = "Test to get ToolTip Value Score")
	public void verifyTooltipBing() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		data.verifyBingHistoryGraph();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified", "yes");
	}

	// Test to export Bing report

	@Test(priority = 7, groups = { "smoke" }, description = "Verify Site Vendors List")
	public void BingCSVExport() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		data.exportCSVBing();
		addEvidence(CurrentState.getDriver(), "Verifying export functionality ", "yes");
	}

	@Test(priority = 8, groups = { "smoke" }, description = "Verify Site Vendors List")
	public void BingExport() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		data.exportXLSXBing(BingXLSX);
		addEvidence(CurrentState.getDriver(), "Verifying export functionality ", "yes");
	}

	// Test for comparing UI and export Impression count
	@Test(priority = 9, groups = { "smoke" }, description = "Verify Impressions")	
	public void VerifyTotalImpressionUIExport() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		data.compareUInExportImpressions(chromepath, IEpath, FFpath);
		addEvidence(CurrentState.getDriver(), "Compare UI and export Impressions ", "yes");
	}

	// Test to verify Zoom Functionality
	@Test(priority = 10, groups = { "smoke" }, description = "Verify Zoom Functionality")
	public void gethighchartsdate() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		String Isdataavailable = data.validdata();
		if (!Isdataavailable.equals("There is currently not enough data from Bing to display this report")) {
			try {
				try {
					String OneMonth = "1m";
					data.clickHighchartCriteria(OneMonth);
					addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					BingCSVExport();
					addEvidence(CurrentState.getDriver(), "CSVExport", "yes");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					data.exportXLSXBing(BingXLSX1m);
					addEvidence(CurrentState.getDriver(), "XLSXExport", "yes");
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
					data.exportXLSXBing(BingXLSX3m);
					addEvidence(CurrentState.getDriver(), "XLSXExport", "yes");
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
					data.exportXLSXBing(BingXLSX6m);
					addEvidence(CurrentState.getDriver(), "XLSXExport", "yes");
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
					data.exportXLSXBing(BingXLSX1y);
					addEvidence(CurrentState.getDriver(), "XLSXExport", "yes");
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
					data.exportXLSXBing(BingXLSXytd);
					addEvidence(CurrentState.getDriver(), "XLSXExport", "yes");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					String ALLDATA = "all";
					data.clickHighchartCriteria(ALLDATA);
					addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					data.exportXLSXBing(BingXLSXall);
					addEvidence(CurrentState.getDriver(), "XLSXExport", "yes");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 

	@SuppressWarnings("unused")
	@Test(priority = 11, dataProvider = "testData",description = "Verify manual date Selection")
	public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {

		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		String Isdataavailable = data.validdata();
		if (!Isdataavailable.equals("There is currently not enough data from Bing to display this report")) {
			if (!(from_day.equals("null")) | !(to_day.equals("null"))) {
				data.selectCalender_FromDate(grphfromDate, (int) (Double.parseDouble(from_day)), from_month,
						(int) (Double.parseDouble(from_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				data.selectCalender_ToDate(grphtoDate, (int) (Double.parseDouble(to_day)), to_month,
						(int) (Double.parseDouble(to_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				Date fromcal = data.getCurrentfromDate();
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				Date tocal = data.getCurrenttoDate();
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				Thread.sleep(5000);
				data.exportXLSXBing(BingXLSXdateselect);
				Thread.sleep(5000);
				addEvidence(CurrentState.getDriver(), "XLSXExport", "Yes");
			//	data.compareUInExportImpressions(chromepathdateselect, IEpathdateselect, FFpathdateselect);
				Thread.sleep(5000);
				addEvidence(CurrentState.getDriver(), "Comparision", "Yes");
				Thread.sleep(5000);
			}
		} else {
			System.out.println("No Data Available");
		}
	}
	
	@Test(priority = 12, description = "Test to verify filter data is in order")
	public void verifyFilterDataOrderBingFacebook() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "Bing");
		String Country1 = wb1.getCellValue(1, wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
			CurrentState.getDriver().navigate().refresh();
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "FilterOrderBing");
		wb.deleteEmptyRows();
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String CountryCode = wb.getCellValue(1, wb.seacrh_pattern("Country", 0).get(0).intValue());
		String State = wb.getCellValue(1, wb.seacrh_pattern("State", 0).get(0).intValue());
		String City = wb.getCellValue(1, wb.seacrh_pattern("City", 0).get(0).intValue());
		String Location = wb.getCellValue(1, wb.seacrh_pattern("Location", 0).get(0).intValue());
		data.GetDataListnVerifyOrderBingFacebook(Group, CountryCode, State, City, Location);
		}else {
			System.out.println("The group is not empty");
		}
	}

	//@SuppressWarnings("finally")
	@DataProvider
	public String[][] testData() {
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "BingZoom");
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
