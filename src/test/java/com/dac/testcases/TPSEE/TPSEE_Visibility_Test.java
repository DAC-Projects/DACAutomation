package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;
import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Visibility_Test extends BaseClass {
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Visibility_Page data;

	//Test for Navigation to Visibilty Page
	@Test(groups = { "smoke" }, description = "Test for navigating to Visibility page")
	public void navigateToVisibilityPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Visibility();
		/*
		 */
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	//Test for applying filters to Visibilty Page
	@SuppressWarnings("unchecked")
	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups = {
			"smoke" }, description = "Verify Visibility page loads after filter applied")
	public void verifyFilteringReportsVisibility(String Filter) throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		//data.verify_pageloadCompletely(10);
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Test for applying filters to Visibilty Page", "yes");
	}

	//Test for export and overview report in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage" ,"verifyFilteringReportsVisibility"}, groups = {
			"smoke" }, description = "Test for overview export and export verification")
	public void verifyOverviewReportnExportVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
	}

	//Test for Tooltip and overview report in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage","verifyFilteringReportsVisibility" }, groups = { "smoke" })
	public void verifyOverviewReportnTooltipVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview visibility report", "yes");
	}

	//Test for SiteTable data in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage","verifyFilteringReportsVisibility"}, groups = {
			"smoke" }, description = "Test for verifying sitetable in Visibility page")
	public void verifySiteTablenExportVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		data.verifySitetable();
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(),
				"Data of sitesTable", "yes");
	}
	
	//Test for number of rows in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage","verifySiteTablenExportVisibility"}, groups = {
			"smoke" }, description = "Test for verifying progress bar in Visibility page")
	public void verifySiteProgressnExporttableVisibility() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.compareexporttableDatannumberofentries(data.verifyprogresstablefound(),data.numberofentries());
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
			addEvidence(CurrentState.getDriver(),
					"printing data found and total number of rows", "yes");
	}
	
	//Test for number of rows in Visibility Page for not found locations
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = { "navigateToVisibilityPage","verifySiteTablenExportVisibility"}, groups = {
				"smoke" }, description = "Test for verifying progress bar in Visibility page")
		public void verifySiteProgressNotFoundnExporttableVisibility() throws Exception {
				data = new TPSEE_Visibility_Page(CurrentState.getDriver());
				data.compareexporttableDatannumberofentriesNotFound(data.verifyprogresstableNotfound(),data.numberofentries());
				CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
				CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
				addEvidence(CurrentState.getDriver(),
						"printing data found and total number of rows", "yes");
		}
	
	//Test for compare number of rows from export table and table data in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage","verifySiteProgressnExporttableVisibility"}, groups = {
			"smoke" }, description = "Test for verifying progress bar in Visibility page")
	public void numberofentriesnExporttableVisibility() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.compareexporttableDatantable(data.getExporttableData(),data.tabledata());
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
			addEvidence(CurrentState.getDriver(),
					"Site level scores in Visibility site table  and overview visibility export found matching", "yes");
	}
	
	//Test to compare vendors in the application (Country = US) in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = {"navigateToVisibilityPage","numberofentriesnExporttableVisibility"},groups = {"smoke"},
				description ="Verify Site Vendors List")
	public void comparevendorsListnverifySitevendors() throws Exception{
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		data.comparevendorsListnverifySitevendors(data.verifySitevendors(), data.vendorsList());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(),
				"Site Vendors in Visibility site vendors ", "yes");
	}

}