package com.dac.testcases.TPSEE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Accuracy_Test extends BaseClass {

	static List<Map<String, String>> export;
	ArrayList<String> foundlistingVendors;
	Navigationpage np;
	TPSEE_Accuracy_Page data;
	TPSEE_Visibility_Page data1;
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
	 */
	@Parameters({ "Filter" })
	@SuppressWarnings("static-access")
	@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
	public void GetKPIValues(int Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		Thread.sleep(10000);
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		score = data.getAccuracyscore();
		System.out.println(score);
		location = data.getAccuracyLoc();
		System.out.println(location);
		CurrentState.getLogger().log(Status.PASS, "KPI Scores");
		addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}

	/**
	 * Test to navigate to Visibility Page
	 * 
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to Visibility page")
	public void navigateToVisibilityPage(int Filter) throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		np.navigateTPSEE_Visibility();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}

	@Test(priority = 3, groups = { "smoke" }, description = "Test for export file as CSV", dependsOnMethods = "navigateToVisibilityPage")
	public void verifyFoundVendors() throws Exception {
		data1 = new TPSEE_Visibility_Page(CurrentState.getDriver());
		foundlistingVendors = data1.verifyfoundSitevendors();
		System.out.println(foundlistingVendors);
		addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
	}

	/**
	 * Test to navigate to Accuracy Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, groups = { "smoke" }, description = "Test for navigating to Accuracy page")
	public void navigateToAccuracyPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Accuracy();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
		addEvidence(CurrentState.getDriver(), "Navigate to Accuracy page from Dashboard", "yes");
	}

	@Parameters({ "Filter" })
	@Test(priority = 5, groups = { "smoke" }, description = "Test for verify title and description")
	public void verifyText(int Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		data.VerifyTitleText1("Accuracy Report",
				"This report identifies the accuracy of a location by field, across the sites that are being monitored. Read Manual");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}
	
	@Test(priority = 6, description = "Test to verify the read manual pdf")
	public void ReadManualPdf() throws InterruptedException {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyContentInPDf("Accuracy report" , "To view this report, select Accuracy under Local Reports on the left navigation." , "A. Location Filters" , "Accuracy_Manual.pdf");
	}

	/**
	 * Test to get SiteTable data
	 * 
	 * @throws Exception
	 */
	// Test to compare vendors in the application in Visibility Page
	@Test(priority = 7, groups = { "smoke" }, description = "Verify Site Vendors List", dependsOnMethods = "verifyFoundVendors")
	public void comparevendorsListnverifySitevendors() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		ArrayList<String> accuracyvendors = data.verifyAccuracySitevendors();
		Assert.assertEquals(accuracyvendors, foundlistingVendors);
		addEvidence(CurrentState.getDriver(), "Site Vendors in Content Analysis site vendors ", "yes");
	}

	/**
	 * Test to Compare KPI Values with Report
	 * 
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 8, groups = { "smoke" }, description = "Test for compare KPI Values")
	public void ovrviewlocscorecompare(int Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		int ovrvwloc = data.overviewlocation();
		Assert.assertEquals(location, ovrvwloc);
		double ovrvwscr = data.overviewscore();
		Assert.assertEquals(score, ovrvwscr);
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
		}else {
			System.out.println("The Group selected is : " +Group);
		}
	}

	/**
	 * to set date
	 * 
	 * @param from_day
	 * @param from_month
	 * @param from_year
	 * @param to_day
	 * @param to_month
	 * @param to_year
	 * @throws Exception
	 */
	
	@SuppressWarnings("unused")
	@Test(priority = 9, enabled = true, dataProvider = "testData", description = "Test for manual date selection")
	public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
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
			Date togrph = data.verifyfinalHistorygraph(start1, end1, grph);
			// Assert.assertEquals(togrph, tocal);
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
		}
	}

	/**
	 * Test to very Zoom Functionality
	 * 
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 10, groups = { "smoke" }, description = "Verify Zoom Functionality")
	public void gethighchartsdate() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
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

	/**
	 * Test to apply Filters
	 * 
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 11, groups = { "smoke" }, description = "Verify Accuracy page loads after filter applied")
	public void verifyFilteringReportsAccuracy(int Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_Accuracy_Page s = new TPSEE_Accuracy_Page(CurrentState.getDriver());
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
	
	@Test(priority = 12, description = "Test to verify percentage of accuracy and inaccuracy scores")
	public void VerifyPercentage() throws InterruptedException {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyPercentageAccuracy();
		Thread.sleep(3000);
		data.verifyPercentageInaccuracy();
	}

	/**
	 * Test to export as overall CSV Accuracy Report
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13, groups = { "smoke" }, description = "Test for export file as CSV")
	public void verifyExportAccuracyCSV() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.exportaccuracyrptCSV();
		addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
	}

	/**
	 * Test to export as overall CSV Accuracy Report
	 * 
	 * @throws Exception
	 */
	@Test(priority = 14, groups = { "smoke" }, description = "Test for export file as XLSX")
	public void verifyOverviewReportnExportAccuracyXLSX() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.exportaccuracyrptXLSX();
		addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
	}

	/**
	 * Test to verify Tooltip data and overall score of accuracy
	 * 
	 * @throws Exception
	 */

	@Test(priority = 15, groups = { "smoke" }, description = "Test for overview report and tooltip data")
	public void verifyOverviewReportnTooltipAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyHistoryGraph1();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Accuracy report", "yes");
		data.comparegraphoverviewscore();
		addEvidence(CurrentState.getDriver(), "Test to compare overview score and graph score", "yes");
	}

	/**
	 * Test to get SiteTable data
	 * 
	 * @throws Exception
	 */
	@Test(priority = 16, groups = { "smoke" }, description = "Test for verifying site data in Accuracy page")
	public void verifySiteTablenExportAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyaccuracySitetable();
		addEvidence(CurrentState.getDriver(),
				"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
	}

	/**
	 * Test for compare number of rows from export table and table data in Accuracy
	 * Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 17, groups = { "smoke" }, description = "Test for verifying site link data in Accuracy page")
	public void numberofentriesnExporttableAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifysitelinkdata(soft);
		addEvidence(CurrentState.getDriver(),
				"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
	}
	
	@Test(priority = 18, dependsOnMethods = {"numberofentriesnExporttableAccuracy"})
	public void verifyListingURL() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyListingUrl();
	}
	
	@Test(priority = 19, dependsOnMethods = {"numberofentriesnExporttableAccuracy"})
	public void verifyLink() {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyWebsiteLink();
	}

	/**
	 * Test to verify inaccuracy and ignored checkbox
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19, groups = { "smoke" }, description = "Test for verifying site link data in Accuracy page")
	public void verifycheckbox() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.showinaccuracy();
		data.verifyinaccuracycolor();
		data.showignored();
		addEvidence(CurrentState.getDriver(),
				"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
		soft.assertAll();
	}

	/**
	 * test to verify table headers
	 * 
	 * @throws Exception
	 */
	@Test(priority = 20, groups = { "smoke" }, description = "Test for verifying sitetable in Visibility page")
	public void verifyTableHeaders() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyAllTab();
		addEvidence(CurrentState.getDriver(), "Data of All Sites Section ", "yes");
		Thread.sleep(1000);
		data.verifyNameTab();
		addEvidence(CurrentState.getDriver(), "Data of Search Engine Sites", "yes");
		Thread.sleep(1000);
		data.verifyAddressTab();
		addEvidence(CurrentState.getDriver(), "Data of Directory Sites", "yes");
		Thread.sleep(1000);
		data.verifyPHNOTab();
		addEvidence(CurrentState.getDriver(), "Data of Social Sites Tab", "yes");
		soft.assertAll();
	}

	/**
	 * Test to verify Top Button
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21, groups = { "smoke" }, description = "Verify Top Button")
	public void GetTopBtn() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.TopButton();
		addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
	}

	/**
	 * test to verify location address
	 * 
	 * @param Filter
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 22, description = "Test to verify location details")
	public void VerifyLocationDetailsLocationTab(int Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Location = wb.getCellValue(Filter, wb.seacrh_pattern("Location", 0).get(0).intValue());
		System.out.println("The Location selected is :" + Location);
		if (!Location.equals("null")) {
			data.verifyLocationFilterAddress(Location);
		} else {
			System.out.println("No location selected");
		}
	}

	/**
	 * test to verify location details from site location tab
	 * 
	 * @param Filter
	 * @throws Exception
	 *//*
	@Parameters({ "Filter" })
	@Test(priority = 21, description = "Test to verify location details of vendors sites")
	public void VerifySiteLocationTab(int Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Location = wb.getCellValue(Filter, wb.seacrh_pattern("Location", 0).get(0).intValue());
		System.out.println("The Location selected is :" + Location);
		if (!Location.equals("null")) {
			data.verifyLocationFilterSiteAddress(Location);
		} else {
			System.out.println("No location selected");
		}
	}

	*//**
	 * test to verify filter data order
	 * 
	 * @param Filter
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 23, description = "Test to verify filter data is in order")
	public void verifyFilterDataOrder(int Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group1 = wb1.getCellValue(Filter, wb1.seacrh_pattern("Group", 0).get(0).intValue());
		if (Group1.equals("None")) {
			CurrentState.getDriver().navigate().refresh();
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "FilterOrder");
			wb.deleteEmptyRows();
			String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
			String CountryCode = wb.getCellValue(1, wb.seacrh_pattern("Country", 0).get(0).intValue());
			String State = wb.getCellValue(1, wb.seacrh_pattern("State", 0).get(0).intValue());
			String City = wb.getCellValue(1, wb.seacrh_pattern("City", 0).get(0).intValue());
			data.GetDataListnVerifyOrder(Group, CountryCode, State, City);
		} else {
			System.out.println("The group is not empty");
		}
	}

	/*
	 * @Test(priority = 18, description = "Test to verify name ignore inaccuracy")
	 * public void verifyNameInaccuracy() throws Exception { data = new
	 * TPSEE_Accuracy_Page(CurrentState.getDriver());
	 * data.verifyupdateinaccuracyname(); addEvidence(CurrentState.getDriver(),
	 * "Test to verify name ignore inaccuracy", "yes"); }
	 * 
	 * @Test(priority = 19, description = "Test to verify address inaccuracy")
	 * public void verifyAddressInaccuracy() throws Exception { data = new
	 * TPSEE_Accuracy_Page(CurrentState.getDriver());
	 * data.verifyupdateinaccuracyaddress(); addEvidence(CurrentState.getDriver(),
	 * "Test to verify address inaccuracy", "yes"); }
	 * 
	 * @Test(priority = 20, description = "Test to verify phone inaccuracy") public
	 * void verifyPhoneInaccuracy() throws Exception { data = new
	 * TPSEE_Accuracy_Page(CurrentState.getDriver());
	 * data.verifyupdateinaccuracyphone(); addEvidence(CurrentState.getDriver(),
	 * "Test to verify phone inaccuracy", "yes"); }
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

	/**
	 * // Test for compare number of rows from export table and table data in
	 *
	 * 
	 * Accuracy Page**
	 * 
	 * @Test(priority = 13, groups = { "smoke" }, description = "Test for verifying
	 *                InAccuracy case data and export data in Accuracy page") public
	 *                void numberofentriesnInAccuracyExporttableAccuracy() throws
	 *                Exception { data = new
	 *                TPSEE_Accuracy_Page(CurrentState.getDriver());
	 *                data.compareexporttableDatanInaccuracytable(data.verifyInAccuracysitelinkdata
	 *                (),data.getInAccuracyExporttableData());
	 *                addEvidence(CurrentState.getDriver(), "Site level scores in
	 *                Accuracy site table and overview Accuracy export found
	 *                matching for InAccuracy" , "yes"); }
	 * 
	 *                //Test to compare vendors in the application in Visibility
	 *                Page
	 * 
	 * 				@SuppressWarnings("unchecked")
	 * 
	 * @Test(dependsOnMethods = {"numberofentriesnExporttableAccuracy"},groups =
	 *                        {"smoke"}, description ="Verify Site Vendors List")
	 *                        public void
	 *
	 * 
	 *                        comparevendorsListnverifySitevendors() throws
	 *                        Exception{ data = new
	 *                        TPSEE_Accuracy_Page(CurrentState.getDriver());
	 *                        data.comparevendorsListnverifySitevendors(data.verifyAccuracySitevendors(),
	 *                        data.vendorsList());
	 *                        addEvidence(CurrentState.getDriver(), "Site Vendors in
	 *                        Content Analysis site vendors ", "yes"); }** // Test
	 *                        for compare number of rows from export table // and
	 *                        table data in
	 *
	 * 
	 *                        Accuracy
	 *                        Page**@SuppressWarnings("unchecked")**@Test(dependsOnMethods={"numberofentriesnInAccuracyExporttableAccuracy"},*groups=
	 *                        { "smoke" },description=*"Test for verifying ignored
	 *                        case and export data in Accuracy page")
	 * 
	 *                        public void
	 *                        numberofentriesnIgnoredExporttableAccuracy() throws
	 *                        Exception { data = new
	 *                        TPSEE_Accuracy_Page(CurrentState.getDriver());
	 *                        data.verifyIgnoredsitelinkdata();
	 *                        data.getIgnoredExporttableData();
	 *                        data.compareXlData_UIdata();
	 *                        data.compareexporttableDatanIgnoredtable(data.verifyIgnoredsitelinkdata(),
	 *                        data.getIgnoredExporttableData());
	 *                        addEvidence(CurrentState.getDriver(), "Site level
	 *                        scores in Accuracy site table and overview Accuracy
	 *                        export found matching for InAccuracy" , "yes"); }
	 *//*
		*/
}
