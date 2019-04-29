package com.dac.testcases.CA;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_ContentAnalysis_Page;
import com.dac.main.POM_CA.CA_Review_Page;
import resources.BaseClass;
import resources.CurrentState;
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

	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "navigateToCAPage" }, groups= {"smoke"})
	public void verifyFilteringReports(String Filter) throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");

//		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		data.verify_pageloadCompletely(10);

		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		//data.verify_pageloadCompletely(10);
		CurrentState.getLogger().log(Status.PASS, "Global filter applied for "+ Arrays.toString(filter));
	}

	@Test(dependsOnMethods = { "navigateToCAPage"}, groups= {"smoke"})
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		export = data.getExportData();
		exportData = data.getOverviewReport();
		data.compareExprttoOvervw(export, exportData);
		CurrentState.getLogger().log(Status.PASS, "Overview report export and Overview report data found matching");
		
	}

	@Test( dependsOnMethods = { "navigateToCAPage" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), exportData);
		CurrentState.getLogger().log(Status.PASS, "Review history graph tooltip and overview report score found matching");
		
	}



}
