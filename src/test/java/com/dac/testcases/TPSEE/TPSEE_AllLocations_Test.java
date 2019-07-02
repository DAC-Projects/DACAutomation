package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_AllLocations_Page;
import com.dac.main.POM_TPSEE.TPSEE_GoogleRanking_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_AllLocations_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_AllLocations_Page data;
	
	@Test(groups = { "smoke" }, description = "Test for navigating to All Locations page")
	public void navigateToAllLocationsPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToAllLocations();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE All Locations page");
		addEvidence(CurrentState.getDriver(), "Navigate to All Locations page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(dependsOnMethods = { "navigateToAllLocationsPage" }, groups = {
	"smoke" }, description = "Verify All Locations page loads after filter applied")
	public void verifyFilteringReportsnavigateToAllLocations() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_AllLocations_Page s = new TPSEE_AllLocations_Page(CurrentState.getDriver());
	
			for(int i=1;i<=wb.getRowCount();i++) {
				System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
				if(i>1) CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
		
				String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group+", "+CountryCode+", "+State+", "+City+", "+Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(),
				"Applied global filter: "+Group+", "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Test for export and overview report in Content Analysis Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifyFilteringReportsnavigateToAllLocations"}, groups = {
					"smoke" }, description = "Test for Location export and export verification")
		public void verifyTableDataoExport() throws Exception {
		data = new TPSEE_AllLocations_Page(CurrentState.getDriver());
		data.compareExprttoAnalysisSiteLinkData(data.LocationDataTable(), data.getLocationDataTableExport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}

}
