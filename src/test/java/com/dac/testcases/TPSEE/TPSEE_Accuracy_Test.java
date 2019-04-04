package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Accuracy_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Accuracy_Page data;

	
	@Test(groups = { "smoke" }, description = "Test for navigating to Accuracy page")
	public void navigateToAccuracyPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Accuracy();
		/*
		 */
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
		addEvidence(CurrentState.getDriver(), "Navigate to Accuracy page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@SuppressWarnings("unchecked")
	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "navigateToAccuracyPage" }, groups = {
			"smoke" }, description = "Verify Accuracy page loads after filter applied")
	public void verifyFilteringReportsAccuracy(String Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		//data.verify_pageloadCompletely(10);
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Apply Filter from Accuracy page", "yes");
	}

	
	//Test for export and overview report in Accuracy Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToAccuracyPage" ,"verifyFilteringReportsAccuracy"}, groups = {
					"smoke" }, description = "Test for overview export and export verification")
		public void verifyOverviewReportnExportAccuarcy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.getOverviewReport();
		//data.compareExprttoOvervw(data.getExportData(), data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
	}
			
			
	
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifyOverviewReportnExportAccuarcy" }, groups = { "smoke" }, description = "Test for overview report and tooltip data")
	public void verifyOverviewReportnTooltipAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Accuracy report", "yes");
	}
	
	
	//Test to display siteTable data
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifyOverviewReportnTooltipAccuracy"}, groups = {
			"smoke" }, description = "Test for verifying site data in Accuracy page")
	public void verifySiteTablenExportAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyaccuracySitetable();
		//data.compareExportnTable(data.verifyHistoryGraph(), data.verifyaccuracySitetable());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(),
				"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
	}
	
	//Test for number of rows in Accuracy Page
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = {"verifySiteTablenExportAccuracy"}, groups = {
				"smoke" }, description = "Test for verifying site table data in Accuracy page")
		public void siteLinkdata() throws Exception {
				data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
				data.verifysitelinkdata();
				//data.numberofentries();
				//data.compareexporttableDatannumberofentries(data.verifysitelinkdata(),data.numberofentries());
				CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
				CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
				addEvidence(CurrentState.getDriver(),
						"printing data found and total number of rows", "yes");
		}
		
		//Test for compare number of rows from export table and table data in Accuracy Page
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = {"siteLinkdata"}, groups = {
				"smoke" }, description = "Test for verifying site link data in Accuracy page")
		public void numberofentriesnExporttableAccuracy() throws Exception {
				data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
				//data.getExporttableData();
				data.compareexporttableDatantable(data.getExporttableData(),data.verifysitelinkdata());
				CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
				CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
				addEvidence(CurrentState.getDriver(),
						"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
		}
		
		//Test for compare number of rows from export table and table data in Accuracy Page
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = { "numberofentriesnExporttableAccuracy"}, groups = {
						"smoke" }, description = "Test for verifying InAccuracy case data and export data in Accuracy page")
				public void numberofentriesnInAccuracyExporttableAccuracy() throws Exception {
						data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
						//data.getExporttableData();
						data.compareexporttableDatanInAccuracytable(data.getInAccuracyExporttableData(),data.verifyInAccuracysitelinkdata());
						CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
						CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
						addEvidence(CurrentState.getDriver(),
								"Site level scores in Accuracy site table  and overview Accuracy export found matching for InAccuracy", "yes");
				}
				
				//Test for compare number of rows from export table and table data in Accuracy Page
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = { "numberofentriesnInAccuracyExporttableAccuracy"}, groups = {
						"smoke" }, description = "Test for verifying ignored case and export data in Accuracy page")
				public void numberofentriesnIgnoredExporttableAccuracy() throws Exception {
						data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
						//data.getExporttableData();
						data.compareexporttableDatanIgnoredtable(data.getIgnoredExporttableData(),data.verifyIgnoredsitelinkdata());
						CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
						CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
						addEvidence(CurrentState.getDriver(),
								"Site level scores in Accuracy site table  and overview Accuracy export found matching for InAccuracy", "yes");
				}
}
