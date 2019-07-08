package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Visibility_Test extends BaseClass {
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Visibility_Page data;

	//Test for Navigation to Visibilty Page
	@Test(groups = { "smoke" }, description = "Test for navigating to Visibility page")
	public void navigateToVisibilityPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Visibility();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
	}
	
	//Test for applying filters to Visibilty Page
	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "navigateToVisibilityPage" }, groups = {
			"smoke" }, description = "Verify Visibility page loads after filter applied")
	public void verifyFilteringReportsVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_Visibility_Page s = new TPSEE_Visibility_Page(CurrentState.getDriver());
			for(int i=1;i<=wb.getRowCount();i++) {
				System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
				if(i>1) CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group+", "+CountryCode+", "+State+", "+City+", "+Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(),
						"Applied global filter: "+Group+", "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
			}
				}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	//Test for export and overview report in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToVisibilityPage" ,"verifyFilteringReportsVisibility"}, groups = {
			"smoke" }, description = "Test for overview export and export verification")
	public void verifyOverviewReportnExportVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(data.getOverviewReport(),export);
		addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
	}

	//Test for Tooltip and overview report in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifyOverviewReportnExportVisibility" }, groups = { "smoke" }, 
							description = "Test to compare ToolTip Value and Overall Visibility Score")
	public void verifyOverviewReportnTooltipVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview visibility report", "yes");
	}

	//Test for SiteTable data in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifyOverviewReportnTooltipVisibility"}, groups = {
			"smoke" }, description = "Test for verifying sitetable in Visibility page")
	public void verifySiteTablenExportVisibility() throws Exception {
		data = new TPSEE_Visibility_Page(CurrentState.getDriver());
		data.verifySitetable();
		addEvidence(CurrentState.getDriver(),
				"Data of sitesTable", "yes");
	}
	
	//Test for compare number of rows from export table and table data in Visibility Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifySiteTablenExportVisibility"}, groups = {
			"smoke" }, description = "Test for verifying progress bar in Visibility page")
	public void numberofentriesnExporttableVisibility() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.DataTablefound();
			data.getExporttableDataFound();
			data.compareXlDataNotFoundandNotFound_UIdata();
			addEvidence(CurrentState.getDriver(),
					"Site level scores in Visibility site table  and overview visibility export found matching", "yes");
	}
	
	//Test for compare number of rows from export table and table data in Visibility Page
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = { "numberofentriesnExporttableVisibility"}, groups = {
				"smoke" }, description = "Test for verifying progress bar in Visibility page")
		public void numberofentriesnExporttableNotFoundVisibility() throws Exception {
				data = new TPSEE_Visibility_Page(CurrentState.getDriver());
				data.DataTableNotfound();
				data.getExporttableDataNotFound();
				data.compareXlDataNotFoundandNotFound_UIdata();
				addEvidence(CurrentState.getDriver(),
						"Site level scores in Visibility site table  and overview visibility export found matching", "yes");
		}
	
	//Test to compare vendors in the application in Visibility Page
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = {"verifyFilteringReportsVisibility"},groups = {"smoke"},
				description ="Verify Site Vendors List")
		public void comparevendorsListnverifySitevendors() throws Exception{
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.comparevendorsListnverifySitevendors(data.verifySitevendors(), data.vendorsList());
			addEvidence(CurrentState.getDriver(),
				"Site Vendors in Visibility site vendors ", "yes");
	}

}