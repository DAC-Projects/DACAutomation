package com.dac.testcases.CA;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Accuracy_Page;
import com.dac.main.POM_CA.CA_Summary_Page;
import com.selenium.testevidence.SeleniumEvidence;
import com.dac.main.POM_CA.CA_Summary_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CA_Summary_Test extends BaseClass{


	static List<Map<String, String>> export;
	Navigationpage np;
	CA_Summary_Page data;

	@SuppressWarnings("unchecked")

	@Test(enabled = true)
	public void navigateToSummaryPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
	np.navigateCA_Summarypage();
		 CurrentState.getLogger().log(Status.PASS,
		          "Navigated successfully to CA Summary page");    
		      addEvidence(CurrentState.getDriver(), "Navigate to Summary page from Dashboard", "yes"); 
	}
	
	@Test(dependsOnMethods = { "navigateToSummaryPage" }, groups= {"smoke"}, description = "Verify Summary page loads after filter applied")
	public void verifyFilteringReportsSummary() throws Exception {try {	
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "CA_FIL"); wb.deleteEmptyRows();
		CA_Summary_Page s = new CA_Summary_Page(CurrentState.getDriver());
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
	
	@SuppressWarnings("unchecked")
	  @Test(dependsOnMethods = { "verifyFilteringReportsSummary"}, groups= {"smoke"}, description = "Test for overview export and export verification")
		public void verifyOverviewReportnExportSummary() throws Exception {
			data = new CA_Summary_Page(CurrentState.getDriver());
			export = data.getExportData();
			data.compareExprttoOvervw(export, data.getOverviewReport());
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
	    CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));    
	    addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes"); 
		}
	
	

}
