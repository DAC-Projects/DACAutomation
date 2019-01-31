package com.dac.testcases.CA;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;

import junit.framework.Assert;
import resources.BaseClass;
import resources.CurrentState;

public class CA_Visibility_Test extends BaseClass {

	
	static List<Map<String, String>> export;
	Navigationpage np;
	CA_Visibility_Page data;

	@SuppressWarnings("unchecked")
  @Test(groups= {"smoke"},description = "Test for navigating to Visibility page")
	public void navigateToVisibilityPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
	np.navigateCA_Visibility();
		 /*new CA_Visibility_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);*/
		 CurrentState.getLogger().log(Status.PASS,
		          "Navigated successfully to CA Visibility page");    
		      addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes"); 

     //Assert.assertFalse( "sample error", true);
	}

	@SuppressWarnings("unchecked")
  @Parameters({ "Filter" })
	@Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups= {"smoke"}, description = "Verify Visibility page loads after filter applied")
	public void verifyFilteringReportsVisibility(String Filter) throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		data.verify_pageloadCompletely(10);
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    addEvidence(CurrentState.getDriver(), "Apply Filter from Visibility page", "yes"); 
	}

	@SuppressWarnings("unchecked")
  @Test(dependsOnMethods = { "navigateToVisibilityPage"}, groups= {"smoke"}, description = "Test for overview export and export verification")
	public void verifyOverviewReportnExportVisibility() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes"); 
	}

	@SuppressWarnings("unchecked")
  @Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltipVisibility() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview visibility report", "yes");    
	}

	@SuppressWarnings("unchecked")
  @Test(dependsOnMethods = { "navigateToVisibilityPage", "verifyOverviewReportnExportVisibility" }, groups= {"smoke"},description = "Test for verifying tooltips in Visibility page")
	public void verifySiteTablenExportVisibility() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareExportnTable(export, data.verifySitetable());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    addEvidence(CurrentState.getDriver(), "Site level scores in Visibility site table  and overview visibility export found matching", "yes");   
	}

}
