package com.dac.testcases.CA;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Visibility_Page;



import resources.BaseTest;

public class CA_Visibility_Test extends BaseTest {

	
	static List<Map<String, String>> export;
	Navigationpage np;
	CA_Visibility_Page data;

	@Test(priority = 0, groups= {"smoke"})
	public void verifyCACalculation() throws Exception {
		np = new Navigationpage(driver);
		np.navigateCA_Visibility();
		 new CA_Visibility_Page(driver).verify_pageloadCompletely(10);
	}

	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"})
	public void verifyFilteringReports(String Filter) throws Exception {
		data = new CA_Visibility_Page(driver);
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		data.verify_pageloadCompletely(10);
	}

	@Test(dependsOnMethods = { "verifyCACalculation"}, groups= {"smoke"})
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_Visibility_Page(driver);
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
	}

	@Test(dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_Visibility_Page(driver);
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
	}

	@Test(dependsOnMethods = { "verifyCACalculation", "verifyOverviewReportnExport" }, groups= {"smoke"})
	public void verifySiteTablenExport() throws Exception {
		data = new CA_Visibility_Page(driver);
		data.compareExportnTable(export, data.verifySitetable());
	}

}
