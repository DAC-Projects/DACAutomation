package com.dac.testcases.CA;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_ContentAnalysis_Page;
import com.dac.main.POM_CA.CA_ContentAnalysis_Page;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.Utilities;
//
public class CA_ContentAnalysis_Test extends BaseClass {


	static List<Map<String, String>> export;
	Navigationpage np;
	CA_ContentAnalysis_Page data;
	List<Map<String, String>>  exportData;

	@Test(groups= {"smoke"})
	public void navigateToCAPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateCA_ContentAnalysispage();
//		new CA_ContentAnalysis_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);
		 CurrentState.getLogger().log(Status.PASS, "Navigated successfully to CA Review page");
	}

	
//	@Test(dependsOnMethods = { "navigateToCAPage" }, groups= {"smoke"})
	public void verifyFilteringReports() throws Exception {try {	
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "CA_FIL"); wb.deleteEmptyRows();
		CA_ContentAnalysis_Page s = new CA_ContentAnalysis_Page(CurrentState.getDriver());
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

	@Test(dependsOnMethods = { "navigateToCAPage"}, groups= {"smoke"})
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		export = data.getExportData();
		exportData = data.getOverviewReport();
		data.compareExprttoOvervw(export, exportData);
		CurrentState.getLogger().log(Status.PASS, "Overview report export and Overview report data found matching");
		
	}

//	@Test( dependsOnMethods = { "navigateToCAPage" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), exportData);
		CurrentState.getLogger().log(Status.PASS, "Review history graph tooltip and overview report score found matching");
		
	}

	@Test( dependsOnMethods = { "navigateToCAPage" }, groups= {"smoke"})
	public void verifyOverviewReportncalculation() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		data.calculateContentAnalysisScore();		
	}

}
