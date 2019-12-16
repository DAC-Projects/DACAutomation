package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_ContentAnalysis_Page;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Accuracy_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Accuracy_Page data;
	double score;
	int location;
	
	
		/**
		 * Test to get dashboard scores
		 * @throws Exception
		 */
		@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
		public void GetKPIValues() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			Thread.sleep(10000);
			score =data.getAccuracyscore();
			System.out.println(score);
			location = data.getAccuracyLoc();
			System.out.println(location);
			CurrentState.getLogger().log(Status.PASS, "KPI Scores");
			addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
		}
		
		/**
		 * Test to navigate to Accuracy Page
		 * @throws Exception
		 */
		@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to Accuracy page")
		public void navigateToAccuracyPage() throws Exception {
			np = new Navigationpage(CurrentState.getDriver());
			np.navigateTPSEE_Accuracy();
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
			addEvidence(CurrentState.getDriver(), "Navigate to Accuracy page from Dashboard", "yes");
		}
	
	/*	*//**
		 * Test to Compare KPI Values with Report
		 * @throws Exception
		 */
		@Test(priority = 2, groups = { "smoke" }, description = "Test for compare KPI Values")
		public void ovrviewlocscorecompare() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			Thread.sleep(5000);
			int ovrvwloc = data.overviewlocation();
			Assert.assertEquals(location, ovrvwloc);		
			double ovrvwscr = data.overviewscore();
			Assert.assertEquals(score, ovrvwscr);		
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
			addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
		}
	
		/**
		 * Test to very Zoom Functionality
		 * @throws Exception
		 */
		@Test(priority = 4,groups = {"smoke"},
			description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			String OneMonth ="1m";
			int start = 980;
			int end = 0;
			data.clickHighchartCriteria(OneMonth,start,end);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String ThreeMonths = "3m";
			data.clickHighchartCriteria(ThreeMonths,start,end);
			addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String SixMonths = "6m";
			data.clickHighchartCriteria(SixMonths,start,end);
			addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String OneYear = "1y";
			data.clickHighchartCriteria(OneYear,start,end);
			addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
			Thread.sleep(5000);
			String YearToDate ="ytd";
			data.clickHighchartCriteria(YearToDate,start,end);
			addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
			Thread.sleep(5000);
			String ALLDATA = "all";
			data.clickHighchartCriteria(ALLDATA,start,end);
			addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			}
	
		/**
		 * Test to apply Filters
		 * @throws Exception
		 */
		@Test(priority = 5, groups = {
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
	
		/**
		 * Test to export as overall CSV Accuracy Report
		 * @throws Exception
		 */
		@Test(priority = 6, groups = {
			"smoke" }, description = "Test for export file as CSV")
		public void verifyExportAccuracyCSV() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.exportaccuracyrptCSV();
			addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
		}
		
		/**
		 * Test to export as overall CSV Accuracy Report
		 * @throws Exception
		 */
		@Test(priority = 7, groups = {
			"smoke" }, description = "Test for export file as XLSX")
		public void verifyOverviewReportnExportAccuracyXLSX() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.exportaccuracyrptXLSX();
			addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
		}
			
		/**
		 * Test to verify Tooltip data and overall score of accuracy	
		 * @throws Exception
		 */
	
		@Test(priority = 8, groups = { "smoke" }, description = "Test for overview report and tooltip data")
		public void verifyOverviewReportnTooltipAccuracy() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
			addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Accuracy report", "yes");
		}
	
	/**
	 * Test to get SiteTable data
	 * @throws Exception
	 */
		@Test(priority = 9, groups = {
			"smoke" }, description = "Test for verifying site data in Accuracy page")
		public void verifySiteTablenExportAccuracy() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.verifyaccuracySitetable();
			addEvidence(CurrentState.getDriver(),
					"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
		}
	
		/**
		 * Test for compare number of rows from export table and table data in Accuracy Page
		 * @throws Exception
		 */
		@Test(priority = 10, groups = {
				"smoke" }, description = "Test for verifying site link data in Accuracy page")
		public void numberofentriesnExporttableAccuracy() throws Exception {
				data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
				data.compareexporttableDatannumberofentries(data.verifysitelinkdata(),data.getExporttableData());
				addEvidence(CurrentState.getDriver(),
						"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
		}
		
		/**
		 * Test to verify inaccuracy and ignored checkbox
		 * @throws Exception
		 */
		@Test(priority = 11, groups = {
		"smoke" }, description = "Test for verifying site link data in Accuracy page")
		public void verifycheckbox() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.showinaccuracy();
			data.showignored();
			addEvidence(CurrentState.getDriver(),
				"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
		}
		
		/**
		 * Test to verify Top Button
		 * @throws Exception
		 */
		@Test(priority = 12, groups = {"smoke"},
			description = "Verify Top Button")
		public void GetTopBtn() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.TopButton();
			addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
		}		
		
		/*//Test for compare number of rows from export table and table data in Accuracy Page
				@Test(priority = 13, groups = {
						"smoke" }, description = "Test for verifying InAccuracy case data and export data in Accuracy page")
				public void numberofentriesnInAccuracyExporttableAccuracy() throws Exception {
						data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
						data.compareexporttableDatanInaccuracytable(data.verifyInAccuracysitelinkdata(),data.getInAccuracyExporttableData());
						addEvidence(CurrentState.getDriver(),
								"Site level scores in Accuracy site table  and overview Accuracy export found matching for InAccuracy", "yes");
				}
				*/
				/*//Test to compare vendors in the application in Visibility Page
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = {"numberofentriesnExporttableAccuracy"},groups = {"smoke"},
						description ="Verify Site Vendors List")
				public void comparevendorsListnverifySitevendors() throws Exception{
					data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
					data.comparevendorsListnverifySitevendors(data.verifyAccuracySitevendors(), data.vendorsList());
					addEvidence(CurrentState.getDriver(),
						"Site Vendors in Content Analysis site vendors ", "yes");
			}*/
				
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
