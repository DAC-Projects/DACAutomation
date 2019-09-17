package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_ContentAnalysis_Page;
import com.selenium.testevidence.SeleniumEvidence;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Accuracy_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Accuracy_Page data;

	//Navigation Test
	@Test(groups = { "smoke" }, description = "Test for navigating to Accuracy page")
	public void navigateToAccuracyPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Accuracy();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
		addEvidence(CurrentState.getDriver(), "Navigate to Accuracy page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	//Apply Filters 
	@Test(dependsOnMethods = { "navigateToAccuracyPage" }, groups = {
			"smoke" }, description = "Verify Accuracy page loads after filter applied")
	public void verifyFilteringReportsAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_Accuracy_Page s = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			
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
	

	
	//Test for export and overview report in Accuracy Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToAccuracyPage" ,"verifyFilteringReportsAccuracy"}, groups = {
					"smoke" }, description = "Test for overview export and export verification")
		public void verifyOverviewReportnExportAccuarcy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.getOverviewReport();
		addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
	}
			
			
	
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifyOverviewReportnExportAccuarcy" }, groups = { "smoke" }, description = "Test for overview report and tooltip data")
	public void verifyOverviewReportnTooltipAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Accuracy report", "yes");
	}
	
	
	//Test to display siteTable data
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "verifyOverviewReportnTooltipAccuracy"}, groups = {
			"smoke" }, description = "Test for verifying site data in Accuracy page")
	public void verifySiteTablenExportAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyaccuracySitetable();
		addEvidence(CurrentState.getDriver(),
				"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
	}
	
	//Test for number of rows in Accuracy Page
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = {"verifySiteTablenExportAccuracy"}, groups = {
				"smoke" }, description = "Test for verifying site table data in Accuracy page")
		public void siteLinkdata() throws Exception {
				data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
				data.verifysitelinkdata();
				addEvidence(CurrentState.getDriver(),
						"printing data found and total number of rows", "yes");
		}
		
		//Test for compare number of rows from export table and table data in Accuracy Page
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = {"siteLinkdata"}, groups = {
				"smoke" }, description = "Test for verifying site link data in Accuracy page")
		public void numberofentriesnExporttableAccuracy() throws Exception {
				data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
				data.compareexporttableDatannumberofentries(data.getExporttableData(),data.verifysitelinkdata());
				/*data.getExporttableData();
				data.verifysitelinkdata();
				data.compareXlData_UIdata();*/
				addEvidence(CurrentState.getDriver(),
						"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
		}
		
		//Test for compare number of rows from export table and table data in Accuracy Page
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = { "numberofentriesnExporttableAccuracy"}, groups = {
						"smoke" }, description = "Test for verifying InAccuracy case data and export data in Accuracy page")
				public void numberofentriesnInAccuracyExporttableAccuracy() throws Exception {
						data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
						data.compareexporttableDatanInaccuracytable(data.verifyInAccuracysitelinkdata(),data.getInAccuracyExporttableData());
						/*data.verifyInAccuracysitelinkdata();
						data.getInAccuracyExporttableData();
						data.compareXlData_UIdata();*/
						addEvidence(CurrentState.getDriver(),
								"Site level scores in Accuracy site table  and overview Accuracy export found matching for InAccuracy", "yes");
				}
				
				//Test to compare vendors in the application in Visibility Page
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = {"numberofentriesnExporttableAccuracy"},groups = {"smoke"},
						description ="Verify Site Vendors List")
				public void comparevendorsListnverifySitevendors() throws Exception{
					data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
					data.comparevendorsListnverifySitevendors(data.verifyAccuracySitevendors(), data.vendorsList());
					addEvidence(CurrentState.getDriver(),
						"Site Vendors in Content Analysis site vendors ", "yes");
			}
				
				/*//Test for compare number of rows from export table and table data in Accuracy Page
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = { "numberofentriesnInAccuracyExporttableAccuracy"}, groups = {
						"smoke" }, description = "Test for verifying ignored case and export data in Accuracy page")
				public void numberofentriesnIgnoredExporttableAccuracy() throws Exception {
						data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
						data.verifyIgnoredsitelinkdata();
						data.getIgnoredExporttableData();
						data.compareXlData_UIdata();
						data.compareexporttableDatanIgnoredtable(data.verifyIgnoredsitelinkdata(),data.getIgnoredExporttableData());
						addEvidence(CurrentState.getDriver(),
								"Site level scores in Accuracy site table  and overview Accuracy export found matching for InAccuracy", "yes");
				}*/
}
