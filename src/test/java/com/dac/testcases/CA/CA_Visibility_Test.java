package com.dac.testcases.CA;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	public void verifyCACalculation() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		/*np.navigateCA_Visibility();
		 new CA_Visibility_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);*/
		System.out.println("Test");
		 CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
     CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
     addEvidence(CurrentState.getDriver(), "Click in Download link", "yes");
     Assert.assertFalse( "sample error", true);
	}
	/*
	@SuppressWarnings("unchecked")
  @Parameters({ "Filter" })
	@Test(dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"}, description = "Verify Visibility page loads after filter applied")
	public void verifyFilteringReports(String Filter) throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		data.verify_pageloadCompletely(10);
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Click in Download link", takeScreenshot(CurrentState.getDriver())));   
	}

	@SuppressWarnings("unchecked")
  @Test(dependsOnMethods = { "verifyCACalculation"}, groups= {"smoke"}, description = "Test for overview export and export verification")
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Click in Download link", takeScreenshot(CurrentState.getDriver())));   
	}

	@SuppressWarnings("unchecked")
  @Test(dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Click in Download link", takeScreenshot(CurrentState.getDriver())));   
	}

	@SuppressWarnings("unchecked")
  @Test(dependsOnMethods = { "verifyCACalculation", "verifyOverviewReportnExport" }, groups= {"smoke"},description = "Test for verifying tooltips in Visibility page")
	public void verifySiteTablenExport() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareExportnTable(export, data.verifySitetable());
		Assert.assertFalse("Testing failure test evidence", true);
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
    CurrentState.getEvidenceList().add(new SeleniumEvidence("Click in Download link", takeScreenshot(CurrentState.getDriver())));   
	}*/

}
