package com.dac.testcases.TPSEE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Accuracy_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	ArrayList<String> foundlistingVendors ;
	Navigationpage np;
	TPSEE_Accuracy_Page data;
	TPSEE_Visibility_Page data1;
	double score;
	int location;
	String grph = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none";
	
	int start;
	int end ;
	int start1;
	int end1;
	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";
	
	
	
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
	 * Test to navigate to Visibility Page
	 * @throws Exception
	 */
		@Test(priority = 2,groups = { "smoke" }, description = "Test for navigating to Visibility page")
		public void navigateToVisibilityPage() throws Exception {
			np = new Navigationpage(CurrentState.getDriver());
			np.navigateTPSEE_Visibility();				
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
			addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
		}
	
	@Test(priority = 3, groups = {
	"smoke" }, description = "Test for export file as CSV")
	public void verifyFoundVendors() throws Exception {
		data1 = new TPSEE_Visibility_Page(CurrentState.getDriver());
		foundlistingVendors = data1.verifyfoundSitevendors();
		System.out.println(foundlistingVendors);
		addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
		}
		
		
		/**
		 * Test to navigate to Accuracy Page
		 * @throws Exception
		 */
		@Test(priority = 4, groups = { "smoke" }, description = "Test for navigating to Accuracy page")
		public void navigateToAccuracyPage() throws Exception {
			np = new Navigationpage(CurrentState.getDriver());
			np.navigateTPSEE_Accuracy();
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
			addEvidence(CurrentState.getDriver(), "Navigate to Accuracy page from Dashboard", "yes");
		}
		
		/**
		 * Test to get SiteTable data
		 * @throws Exception
		 */
		//Test to compare vendors in the application in Visibility Page
		@Test(priority = 5,groups = {"smoke"},
				description ="Verify Site Vendors List")
		public void comparevendorsListnverifySitevendors() throws Exception{
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			ArrayList<String> accuracyvendors = data.verifyAccuracySitevendors();
			Assert.assertEquals(accuracyvendors, foundlistingVendors);
			addEvidence(CurrentState.getDriver(),
				"Site Vendors in Content Analysis site vendors ", "yes");
	}
	
		/**
		 * Test to Compare KPI Values with Report
		 * @throws Exception
		 */
		 
		@Test(priority = 6, groups = { "smoke" }, description = "Test for compare KPI Values")
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
	
		@Test(priority = 8,enabled = true, dataProvider = "testData")
		public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws Exception {
			
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			if(!(from_day.equals("null")) | !(to_day.equals("null")) ) {
				data.selectCalender_FromDate(grphfromDate,(int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				data.selectCalender_ToDate(grphtoDate,(int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));	
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");				
				Date fromcal = data.getCurrentfromDate();
				Date fromgrph = data.verifyinitialHistoryGraph(start, end, grph);
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				//Assert.assertEquals(fromgrph, fromcal);
				Date tocal = data.getCurrenttoDate();
				Date togrph = data.verifyfinalHistorygraph(start1, end1, grph);
				//Assert.assertEquals(togrph, tocal);
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			}				
		}
		/**
		 * Test to very Zoom Functionality
		 * @throws Exception
		 */
		@Test(priority = 7,groups = {"smoke"},
			description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			String OneMonth ="1m";
			data.clickHighchartCriteria(OneMonth);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String ThreeMonths = "3m";
			data.clickHighchartCriteria(ThreeMonths);
			addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String SixMonths = "6m";
			data.clickHighchartCriteria(SixMonths);
			addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String OneYear = "1y";
			data.clickHighchartCriteria(OneYear);
			addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
			Thread.sleep(5000);
			String YearToDate ="ytd";
			data.clickHighchartCriteria(YearToDate);
			addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
			Thread.sleep(5000);
			String ALLDATA = "all";
			data.clickHighchartCriteria(ALLDATA);
			addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			}
	
		/**
		 * Test to apply Filters
		 * @throws Exception
		 */
		@Test(priority = 9, groups = {
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
		@Test(priority = 10, groups = {
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
		@Test(priority = 11, groups = {
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
	
		@Test(priority = 12, groups = { "smoke" }, description = "Test for overview report and tooltip data")
		public void verifyOverviewReportnTooltipAccuracy() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
			addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Accuracy report", "yes");
		}
	
	/**
	 * Test to get SiteTable data
	 * @throws Exception
	 */
		@Test(priority = 13, groups = {
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
		@Test(priority = 14, groups = {
				"smoke" }, description = "Test for verifying site link data in Accuracy page")
		public void numberofentriesnExporttableAccuracy() throws Exception {
				data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
				data.compareexporttableDatannumberofentries(data.verifysitelinkdata(),data.getExporttableData());
				addEvidence(CurrentState.getDriver(),
						"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
		}
		
		@Test(priority = 15, groups = {
		"smoke" }, description = "Test for verifying sitetable in Visibility page")
	public void verifyTableHeaders() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.verifyAllTab();
		addEvidence(CurrentState.getDriver(),
			"Data of All Sites Section ", "yes");
		Thread.sleep(5000);
		data.verifyNameTab();
		addEvidence(CurrentState.getDriver(),
			"Data of Search Engine Sites", "yes");
		Thread.sleep(5000);
		data.verifyAddressTab();
		addEvidence(CurrentState.getDriver(),
			"Data of Directory Sites", "yes");
		Thread.sleep(5000);
		data.verifyPHNOTab();
		addEvidence(CurrentState.getDriver(),
			"Data of Social Sites Tab", "yes");
	}
		
		/**
		 * Test to verify inaccuracy and ignored checkbox
		 * @throws Exception
		 */
		@Test(priority = 16, groups = {
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
		@Test(priority = 17, groups = {"smoke"},
			description = "Verify Top Button")
		public void GetTopBtn() throws Exception {
			data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			data.TopButton();
			addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
		}		
		
		
		@DataProvider
		public String[][] testData(){
			String[][] data = null, data1 = null;
			try {
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Zoom");
			wb.deleteEmptyRows();
			int rowCount = wb.getRowCount();
			System.out.println("rowCount : "+rowCount);
			data = new String[rowCount-1][6];
			data1 = new String[rowCount-1][6];
			int row = 0;
			for(int i = 2; i<=rowCount;i++) {
				 int colCount = wb.getColCount(i);
				 for(int j = 0;j<colCount; j++) {
					 if(j > 0) {
						 if(((wb.getCellValue(i, j).trim()).equalsIgnoreCase("null")) | 
								 ((wb.getCellValue(i, j).trim()).length() == 0)){
							 data1[row][j] = "null";
						 }else data1[row][j] = wb.getCellValue(i, j).trim();
					 }else data1[row][j] = wb.getCellValue(i, j).trim();		 
				 }
				 row++;
				 }
				 data[0][0] = wb.getCellValue(2, 0);  // from day
				 data[0][1] = wb.getCellValue(2, 1);  // from month
				 data[0][2] = wb.getCellValue(2, 2);  // from year
				 data[0][3] = wb.getCellValue(2, 3);  // to day
				 data[0][4] = wb.getCellValue(2, 4);  // to month
				 data[0][5] = wb.getCellValue(2, 5);  // to year
				 System.out.println("Arrays.deepToString(data) : "+Arrays.deepToString(data));
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				return data;
			}
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
				
				//Test for compare number of rows from export table and table data in Accuracy Page
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
