package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_AllLocations_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_AllLocations_Test extends BaseClass {

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_AllLocations_Page data;
	int location;
	SoftAssert soft = new SoftAssert();
	String result;
	
	 

	/**
	 * Test to get dashboard scores
	 * 
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
	public void GetKPIValues(int Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		location = data.getLocations();
		System.out.println(location);
		CurrentState.getLogger().log(Status.PASS, "KPI Scores");
		addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}

	/**
	 * test to navigate to All Locations Page
	 * @throws Exception
	 */
	@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to All Locations page")
	public void navigateToAllLocationsPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToAllLocations();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE All Locations page");
		addEvidence(CurrentState.getDriver(), "Navigate to All Locations page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	/**
	 * test to verify title and title text
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 3, groups = { "smoke" }, description = "Test for verify title and description")
	public void verifyText(int Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		data.VerifyLocationsTitleText("Locations",
				"This is where all the locations currently associated to your account are listed. Read Manual");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}

	/**
	 * Test To get overall score and compare with dashboard values
	 * 
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 4, groups = { "smoke" }, description = "Test for compare KPI Values")
	public void ovrviewlocscorecompare(int Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		Thread.sleep(5000);
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		int loc = data.numberoflocation();
		System.out.println(loc);
		Assert.assertEquals(loc, location);
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}

	/**
	 * test to apply filters
	 * @param Filter
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 5, groups = { "smoke" }, description = "Verify All Locations page loads after filter applied")
	public void verifyFilteringReportsnavigateToAllLocations(int Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_AllLocations_Page s = new TPSEE_AllLocations_Page(CurrentState.getDriver());
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
	 * test to export as CSV
	 * @throws Exception
	 */
	@Test(priority = 6, description = "Export as csv")
	public void exportascsv() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.LocationDataTableExportCSV();
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 7, description = "Test for Location export and export verification")
	public void verifyTableDataoExport() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	/**
	 * test to verify goto page
	 * @throws Exception
	 */
	@Parameters({"Filter"})
	@Test(priority = 8, description = "Test to GoTo Page verification")
	public void verifyGotoPage(int Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		data.GoTo();
		addEvidence(CurrentState.getDriver(), "Test to verify GoTo Page", "yes");
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}
	
	/**
	 * test to verify results per page
	 * @throws Exception
	 */
	@Parameters({"Filter"})
	@Test(priority = 9, description = "Test to results per page")
	public void verifyResultperPage(int Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The Group selected is : " +Group);
		if(Group.equalsIgnoreCase("None")) {
		data.resultperpage(soft);	
		addEvidence(CurrentState.getDriver(), "Test to verify Results per page", "yes");
		Thread.sleep(5000);		
		soft.assertAll();
		}else {
			System.out.println("The group selected is : " +Group);
		}
	}
	
	/**
	 * test to verify filter data order
	 * @param Filter
	 * @throws Exception
	 */
	@Parameters({ "Filter" })
	@Test(priority = 10, description = "Test to verify filter data is in order")
	public void verifyFilterDataOrder(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
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
	
	@Parameters("Filter")
	@Test(priority = 11, description = "Test to search using location number")
	public void verifySearchByLocationNum(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx" , "Location_Page_Search");
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
		for(int i = 1; i <= wb.getRowCount(); i++) {
		String LocationNumber = wb.getCellValue(i, wb.seacrh_pattern("Search By Location", 0).get(0).intValue());
		System.out.println("The location number selected is : " +LocationNumber);
		data.SearchwithKeywords(LocationNumber);
		addEvidence(CurrentState.getDriver(), "Test to search using location number", "yes");
		}
		}else {
			System.out.println("The group is not empty");
		}
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 12, description = "Test for Location export and export verification for location nummber search", dependsOnMethods = "verifySearchByLocationNum")
	public void verifyTableDataoExportLocNum() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 13, description = "Test to clear search box", dependsOnMethods = "verifyTableDataoExportLocNum")
	public void clearsearchLocNum() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.ClearSearchText();
		addEvidence(CurrentState.getDriver(), "Test to clear data from searchbox", "yes");
	}
	
	@Parameters("Filter")
	@Test(priority = 14, description = "Test to search using name")
	public void verifySearchByName(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx" , "Location_Page_Search");
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
		for(int i = 1; i <= wb.getRowCount(); i++) {
		String LocationNumber = wb.getCellValue(i, wb.seacrh_pattern("Search By Name", 0).get(0).intValue());
		System.out.println("The location number selected is : " +LocationNumber);
		data.SearchwithKeywords(LocationNumber);
		addEvidence(CurrentState.getDriver(), "Test to search using name", "yes");
		}
		}else {
			System.out.println("The group is not empty");
		}
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 15, description = "Test for Location export and export verification for location nummber search", dependsOnMethods = "verifySearchByName")
	public void verifyTableDataoExportName() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 16, description = "Test to clear search box", dependsOnMethods = "verifyTableDataoExportName")
	public void clearsearchName() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.ClearSearchText();
		addEvidence(CurrentState.getDriver(), "Test to clear data from searchbox", "yes");
	}
	
	@Parameters("Filter")
	@Test(priority = 17, description = "Test to search using name")
	public void verifySearchByAddress(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx" , "Location_Page_Search");
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
		for(int i = 1; i <= wb.getRowCount(); i++) {
		String LocationNumber = wb.getCellValue(i, wb.seacrh_pattern("Search By Address", 0).get(0).intValue());
		System.out.println("The location number selected is : " +LocationNumber);
		data.SearchwithKeywords(LocationNumber);
		addEvidence(CurrentState.getDriver(), "Test to search using Address", "yes");
		}
		}else {
			System.out.println("The group is not empty");
		}
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 18, description = "Test for Location export and export verification for location nummber search", dependsOnMethods = "verifySearchByAddress")
	public void verifyTableDataoExportAddress() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 19, description = "Test to clear search box", dependsOnMethods = "verifyTableDataoExportAddress")
	public void clearsearchNameAddress() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.ClearSearchText();
		addEvidence(CurrentState.getDriver(), "Test to clear data from searchbox", "yes");
	}
	
	@Parameters("Filter")
	@Test(priority = 20, description = "Test to search using name")
	public void verifySearchByCity(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx" , "Location_Page_Search");
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
		for(int i = 1; i <= wb.getRowCount(); i++) {
		String LocationNumber = wb.getCellValue(i, wb.seacrh_pattern("Search By City", 0).get(0).intValue());
		System.out.println("The location number selected is : " +LocationNumber);
		data.SearchwithKeywords(LocationNumber);
		addEvidence(CurrentState.getDriver(), "Test to search using City", "yes");
		}
		}else {
			System.out.println("The group is not empty");
		}
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 21, description = "Test for Location export and export verification for city search", dependsOnMethods = "verifySearchByCity")
	public void verifyTableDataoExportCity() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 22, description = "Test to clear search box", dependsOnMethods = "verifyTableDataoExportCity")
	public void clearsearchNameCity() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.ClearSearchText();
		addEvidence(CurrentState.getDriver(), "Test to clear data from searchbox", "yes");
	}
	
	@Parameters("Filter")
	@Test(priority = 23, description = "Test to search using name")
	public void verifySearchByState(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx" , "Location_Page_Search");
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
		for(int i = 1; i <= wb.getRowCount(); i++) {
		String LocationNumber = wb.getCellValue(i, wb.seacrh_pattern("Search By State", 0).get(0).intValue());
		System.out.println("The location number selected is : " +LocationNumber);
		data.SearchwithKeywords(LocationNumber);
		addEvidence(CurrentState.getDriver(), "Test to search using Address", "yes");
		}
		}else {
			System.out.println("The group is not empty");
		}
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 24, description = "Test for Location export and export verification for location nummber search", dependsOnMethods = "verifySearchByState")
	public void verifyTableDataoExportState() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 25, description = "Test to clear search box", dependsOnMethods = "verifyTableDataoExportState")
	public void clearsearchNameState() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.ClearSearchText();
		addEvidence(CurrentState.getDriver(), "Test to clear data from searchbox", "yes");
	}
	
	@Parameters("Filter")
	@Test(priority = 26, description = "Test to search using Postal Code")
	public void verifySearchByPostCode(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx" , "Location_Page_Search");
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
		for(int i = 1; i <= wb.getRowCount(); i++) {
		String LocationNumber = wb.getCellValue(i, wb.seacrh_pattern("Search By Postal Code", 0).get(0).intValue());
		System.out.println("The location number selected is : " +LocationNumber);
		data.SearchwithKeywords(LocationNumber);
		addEvidence(CurrentState.getDriver(), "Test to search using Post Code", "yes");
		}
		}else {
			System.out.println("The group is not empty");
		}
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 27, description = "Test for Location export and export verification for location nummber search", dependsOnMethods = "verifySearchByPostCode")
	public void verifyTableDataoExportPostCode() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 28, description = "Test to clear search box", dependsOnMethods = "verifyTableDataoExportPostCode")
	public void clearsearchNamePostCode() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.ClearSearchText();
		addEvidence(CurrentState.getDriver(), "Test to clear data from searchbox", "yes");
	}
	
	@Parameters("Filter")
	@Test(priority = 29, description = "Test to search using Phone Number")
	public void verifySearchByPhone(String Filter) throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx" , "Location_Page_Search");
		ExcelHandler wb1 = new ExcelHandler("./data/Filter.xlsx" , "TPSEE");
		String Country1 = wb1.getCellValue(Integer.parseInt(Filter), wb1.seacrh_pattern("Country", 0).get(0).intValue());
		if(Country1.equals("null")) {
		for(int i = 1; i <= wb.getRowCount(); i++) {
		String LocationNumber = wb.getCellValue(i, wb.seacrh_pattern("Search By Phone", 0).get(0).intValue());
		System.out.println("The location number selected is : " +LocationNumber);
		data.SearchwithKeywords(LocationNumber);
		addEvidence(CurrentState.getDriver(), "Test to search using Address", "yes");
		}
		}else {
			System.out.println("The group is not empty");
		}
	}
	
	/**
	 * test to compare UI and XL data
	 * @throws Exception
	 */
	@Test(priority = 30, description = "Test for Location export and export verification for location nummber search", dependsOnMethods = "verifySearchByPhone")
	public void verifyTableDataoExportPhone() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 31, description = "Test to clear search box", dependsOnMethods = "verifyTableDataoExportPhone")
	public void clearsearchNamePhone() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.ClearSearchText();
		addEvidence(CurrentState.getDriver(), "Test to clear data from searchbox", "yes");
	}
	
/*	@Test(priority = 10, description = "Test to sort data and verify")
	public void verifySortedLocationNumber() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.verifyLocationNumber();
		addEvidence(CurrentState.getDriver(), "Test to verify data sorted", "yes");
	}
	
	@Test(priority = 11, description = "Test to sort data and verify")
	public void verifySortedName() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.verifyName();
		addEvidence(CurrentState.getDriver(), "Test to verify data sorted", "yes");
	}
	
	@Test(priority = 12, description = "Test to sort data and verify")
	public void verifySortedAddress() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.verifyAddress();
		addEvidence(CurrentState.getDriver(), "Test to verify data sorted", "yes");
	}
	
	@Test(priority = 13, description = "Test to sort data and verify")
	public void verifySortedCity() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.verifyCity();
		addEvidence(CurrentState.getDriver(), "Test to verify data sorted", "yes");
	}
	
	@Test(priority = 14, description = "Test to sort data and verify")
	public void verifySortedState() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.verifyState();
		addEvidence(CurrentState.getDriver(), "Test to verify data sorted", "yes");
	}
	
	@Test(priority = 15, description = "Test to sort data and verify")
	public void verifySortedPostCode() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.verifyPostCode();
		addEvidence(CurrentState.getDriver(), "Test to verify data sorted", "yes");
	}
	
	@Test(priority = 16, description = "Test to sort data and verify")
	public void verifySortedPhone() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.verifyPhone();
		addEvidence(CurrentState.getDriver(), "Test to verify data sorted", "yes");
	}*/
}
