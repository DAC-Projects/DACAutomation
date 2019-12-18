package com.dac.testcases.CA;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CA_Visibility_Test extends BaseClass {

	
	static List<Map<String, String>> export;
	Navigationpage np;
	CA_Visibility_Page data;

	@SuppressWarnings("unchecked")
//  @Test(groups= {"smoke"},description = "Test for navigating to Visibility page")
//	  @Test(groups= {"smoke"},description = "Test for navigating to Visibility page with all filter condition check")
	@Test(enabled = true)
	public void navigateToVisibilityPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateCA_Visibility();
		new CA_Visibility_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to CA Visibility page");    
		      addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes"); 

     //Assert.assertFalse( "sample error", true);
	}

	@SuppressWarnings("unchecked")
//  @Parameters({ "Filter" })
	//Filter Condition Apply
	@Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups= {"smoke"}, description = "Verify Visibility page loads after filter applied")
	public void verifyFilteringReportsVisibility() throws Exception {try {	
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "CA_FIL"); wb.deleteEmptyRows();
		CA_Visibility_Page s = new CA_Visibility_Page(CurrentState.getDriver());
		for(int i=1;i<=wb.getRowCount();i++) {
			System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			if(i>1) CurrentState.getDriver().navigate().refresh();
			s.waitUntilLoad(CurrentState.getDriver());
			
			
			String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
			String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
			String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
			String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
			
			s.applyFilter(CountryCode, State, City, Location);
			System.out.println(CountryCode+", "+State+", "+City+", "+Location);
			s.clickApplyFilterBTN();
			BaseClass.addEvidence(CurrentState.getDriver(),
					"Applied global filter: "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
			
			Thread.sleep(4000);
			System.out.println("-------------- Scenarios : "+ count++ + "Ends --------------------");
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		//Assert.fail("");
	}}
	
	public void verifyFilteringReportsVisibility(String Filter) throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0],filter[1],filter[2],filter[3]);
		data.verify_pageloadCompletely(10); 
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
  @Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups= {"smoke"}, description = "Test for overview report and tooltip")
	public void verifyOverviewReportnTooltipVisibility() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());  
    addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview visibility report", "yes");    
	}

	@SuppressWarnings("unchecked")
  @Test(dependsOnMethods = { "navigateToVisibilityPage", "verifyOverviewReportnExportVisibility" }, groups= {"smoke"},description = "Test for comparing export and table")
	public void verifySiteTablenExportVisibility() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareExportnTable(export, data.verifySitetable());  
    addEvidence(CurrentState.getDriver(), "Site level scores in Visibility site table  and overview visibility export found matching", "yes");   
	}

}
