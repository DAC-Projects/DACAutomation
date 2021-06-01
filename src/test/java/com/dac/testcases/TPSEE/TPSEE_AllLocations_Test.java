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
	@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
	public void GetKPIValues() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		location = data.getLocations();
		System.out.println(location);
		CurrentState.getLogger().log(Status.PASS, "KPI Scores");
		addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
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
	@Test(priority = 3, groups = { "smoke" }, description = "Test for verify title and description")
	public void verifyText() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.VerifyLocationsTitleText("Locations",
				"This is where all the locations currently associated to your account are listed. Read Manual");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");

	}

	/**
	 * Test To get overall score and compare with dashboard values
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, groups = { "smoke" }, description = "Test for compare KPI Values")
	public void ovrviewlocscorecompare() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		Thread.sleep(5000);
		int loc = data.numberoflocation();
		System.out.println(loc);
		Assert.assertEquals(loc, location);
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
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
	@Test(priority = 8, description = "Test to GoTo Page verification")
	public void verifyGotoPage() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.GoTo();
		addEvidence(CurrentState.getDriver(), "Test to verify GoTo Page", "yes");
	}
	
	/**
	 * test to verify results per page
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to results per page")
	public void verifyResultperPage() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.resultperpage(soft);	
		addEvidence(CurrentState.getDriver(), "Test to verify Results per page", "yes");
		Thread.sleep(5000);		
		soft.assertAll();
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
