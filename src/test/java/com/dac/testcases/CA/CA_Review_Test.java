package com.dac.testcases.CA;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Review_Page;
import resources.BaseTest;
import resources.BaseTest2;
import resources.CurrentState;
import resources.Utilities;

public class CA_Review_Test extends BaseTest2 {


	static List<Map<String, String>> export;
	Navigationpage np;
	CA_Review_Page data;

	@Test(groups= {"smoke"})
	public void navigateToReviewPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateCA_Reviewpage();
		 new CA_Review_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);
		 Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(0));
		 CurrentState.getLogger().log(Status.PASS, "Navigated successfully to CA Review page");
	}

	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "navigateToReviewPage" }, groups= {"smoke"})
	public void verifyFilteringReports(String Filter) throws Exception {
		data = new CA_Review_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		data.verify_pageloadCompletely(10);
		Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(1));
		CurrentState.getLogger().log(Status.PASS, "Global filter applied for "+ Arrays.toString(filter));
	}

	@Test(dependsOnMethods = { "navigateToReviewPage"}, groups= {"smoke"})
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_Review_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
		Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(2));
		CurrentState.getLogger().log(Status.PASS, "Overview report export and Overview report data found matching");
		
	}

	@Test( dependsOnMethods = { "navigateToReviewPage" }, groups= {"smoke"})
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_Review_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(3));
		CurrentState.getLogger().log(Status.PASS, "Review history graph tooltip and overview report score found matching");
		
	}

	@Test(dependsOnMethods = { "navigateToReviewPage", "verifyOverviewReportnExport" }, groups= {"smoke"})
	public void verifySiteTablenExport() throws Exception {
		data = new CA_Review_Page(CurrentState.getDriver());
		data.compareExportnTable(export, data.verifySitetable());
		Utilities.addScreenshot(CurrentState.getDriver(), CurrentState.getImgnames().get(4));
		CurrentState.getLogger().log(Status.PASS, "Site level scores in site table  and overview report export found matching");
	
	
	}

}
