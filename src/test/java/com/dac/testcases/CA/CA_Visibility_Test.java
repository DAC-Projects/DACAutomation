package com.dac.testcases.CA;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Visibility_Page;



import resources.BaseTest;
import resources.BaseTest2;
import resources.CurrentState;

public class CA_Visibility_Test extends BaseTest2 {

	
	static List<Map<String, String>> export;
	Navigationpage np;
	CA_Visibility_Page data;

	@Test(groups= {"smoke"})
	public void verifyCACalculation() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateCA_Visibility();
		 new CA_Visibility_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);
	}

	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"})
	public void verifyFilteringReports(String Filter) throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		data.verify_pageloadCompletely(10);
	}

	@Test(dependsOnMethods = { "verifyCACalculation"}, groups= {"smoke"})
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
	}

	@Test(dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
	}

	@Test(dependsOnMethods = { "verifyCACalculation", "verifyOverviewReportnExport" }, groups= {"smoke"})
	public void verifySiteTablenExport() throws Exception {
		data = new CA_Visibility_Page(CurrentState.getDriver());
		data.compareExportnTable(export, data.verifySitetable());
	}

}
