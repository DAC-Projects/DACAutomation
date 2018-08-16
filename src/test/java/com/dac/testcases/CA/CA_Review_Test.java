package com.dac.testcases.CA;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Review_Page;
import com.dac.main.POM_CA.CA_Visibility_Page;
import com.dac.main.POM_CA.CA_exports;


import resources.BaseTest;

public class CA_Review_Test extends BaseTest {

	CA_exports exports;
	static List<Map<String, String>> export;
	Navigationpage np;
	CA_Review_Page data;

	@Test(priority = 1, groups= {"smoke"})
	public void verifyCACalculation() throws Exception {
		np = new Navigationpage(driver);
		np.navigateCA_Reviewpage();
		 new CA_Review_Page(driver).verify_pageloadCompletely(10);
	}

	@Parameters({ "Filter" })
	@Test(priority = 2, dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"})
	public void verifyFilteringReports(String Filter) throws Exception {
		data = new CA_Review_Page(driver);
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		data.verify_pageloadCompletely(10);
	}

	@Test(priority = 3, dependsOnMethods = { "verifyCACalculation"}, groups= {"smoke"})
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_Review_Page(driver);
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
	}

	@Test(priority = 4, dependsOnMethods = { "verifyCACalculation" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_Review_Page(driver);
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
	}

	@Test(priority = 5, dependsOnMethods = { "verifyCACalculation", "verifyOverviewReportnExport" }, groups= {"smoke"})
	public void verifySiteTablenExport() throws Exception {
		data = new CA_Review_Page(driver);
		data.compareExportnTable(export, data.verifySitetable());
	}

}
