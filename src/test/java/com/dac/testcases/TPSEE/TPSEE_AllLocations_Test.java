package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

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
	 *//*
	@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
	public void GetKPIValues() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		location = data.getLocations();
		System.out.println(location);
		CurrentState.getLogger().log(Status.PASS, "KPI Scores");
		addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
	}*/

	@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to All Locations page")
	public void navigateToAllLocationsPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToAllLocations();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE All Locations page");
		addEvidence(CurrentState.getDriver(), "Navigate to All Locations page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(priority = 3, description = "Test to verify report highlight")
	public void VerifyLocationHighlight() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.Locationhighlight();
		addEvidence(CurrentState.getDriver(), "Test to verify report highlight", "yes");
	}

	@Test(priority = 4, groups = { "smoke" }, description = "Test for verify title and description")
	public void verifyText() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.VerifyLocationsTitleText("Locations",
				"This is where all the locations currently associated to your account are listed.");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");

	}

	/**
	 * Test To get overall score and compare with dashboard values
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 4, groups = { "smoke" }, description = "Test for compare KPI Values")
	public void ovrviewlocscorecompare() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		Thread.sleep(5000);
		int loc = data.numberoflocation();
		System.out.println(loc);
		Assert.assertEquals(loc, location);
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
	}*/

	@Test(priority = 5, groups = { "smoke" }, description = "Verify All Locations page loads after filter applied")
	public void verifyFilteringReportsnavigateToAllLocations() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_AllLocations_Page s = new TPSEE_AllLocations_Page(CurrentState.getDriver());

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
				s.LAVapplyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
				s.clickApplyFilterBTNLAV();
				BaseClass.addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode
						+ ", " + State + ", " + City + ", " + Location + "", "yes");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 6, description = "Export as csv")
	public void exportascsv() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.LocationDataTableExportCSV();
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}
	
	@Test(priority = 7, description = "Test for Location export and export verification")
	public void verifyTableDataoExport() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}	
	
	@Test(priority = 8, description = "Test to GoTo Page verification")
	public void verifyGotoPage() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.GoTo();
		addEvidence(CurrentState.getDriver(), "Test to verify GoTo Page", "yes");
	}
	
	@Test(priority = 9, description = "Test to results per page")
	public void verifyResultperPage() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
			data.resultperpage(soft);	
			addEvidence(CurrentState.getDriver(), "Test to verify Results per page", "yes");
			Thread.sleep(5000);		
			soft.assertAll();
	}
}
