package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Visibility_Test extends BaseClass {
	
	@FindBy(css = "path.highcharts-halo.highcharts-color-undefined")
	private WebElement grphtooltip; 

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Visibility_Page data;

	@SuppressWarnings("unchecked")
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
		addEvidence(CurrentState.getDriver(), "Apply Filter from Visibility page", "yes");
	}

	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups = {
			"smoke" }, description = "Test for overview export and export verification")
	public void verifyOverviewReportnExportVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
	}

	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups = { "smoke" })
	public void verifyOverviewReportnTooltipVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview visibility report", "yes");
	}

	@SuppressWarnings("unchecked")
	//@Test(dependsOnMethods = { "navigateToVisibilityPage", "verifyOverviewReportnExportVisibility" }, groups = {
	@Test(dependsOnMethods = { "navigateToVisibilityPage"}, groups = {
			"smoke" }, description = "Test for verifying sitetable in Visibility page")
	public void verifySiteTablenExportVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		data.compareExportnTable(data.verifyHistoryGraph(), data.verifySitetable());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(),
				"Site level scores in Visibility site table  and overview visibility export found matching", "yes");
	}
	
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage"}, groups = {
			"smoke" }, description = "Test for verifying progress bar in Visibility page")
	public void verifySiteProgressnExporttableVisibility() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			//data.compareexporttableDatannumberofentries(data.numberofentries(), data.verifyprogresstable());
			data.verifyprogresstable();
			data.exporttable();
			data.numberofentries();
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
			addEvidence(CurrentState.getDriver(),
					"Site level scores in Visibility site table  and overview visibility export found matching", "yes");
}

}
