package com.dac.testcases.TPSEE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Visibility_Test extends BaseClass {	
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Visibility_Page data;
	double score;
	int location;
	ArrayList<String> UIsite = new ArrayList<String>();
	ArrayList<String> XLsite = new ArrayList<String>();
	
	
	/**
	 * Test to get dashboard scores
	 * @throws Exception
	 */
		@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
		public void GetKPIValues() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			Thread.sleep(10000);
			score =data.getVisibilityscore();
			System.out.println(score);
			location = data.getVisibilityLoc();
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
	
	/**
	 * Test To get overall score and compare with dashboard values 
	 * @throws Exception
	 */		
		@Test(dependsOnMethods = {"navigateToVisibilityPage"},groups = { "smoke" }, description = "Test for compare KPI Values")
		public void ovrviewlocscorecompare() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			Thread.sleep(5000);
			int ovrvwloc = data.overviewlocation();
			Assert.assertEquals(location, ovrvwloc);		
			double ovrvwscr = data.overviewscore();
			Assert.assertEquals(score, ovrvwscr);		
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
			addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");
		}
	
	/**
	 * Test to verify zoom functionality 
	 * @throws Exception
	 */
		@Test(priority = 4,groups = {"smoke"},
			description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
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
	 * Test to apply filters
	 * @param Group
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 * @throws Exception
	 */
		@Test(dependsOnMethods = {"ovrviewlocscorecompare"}, groups = {
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
	

	/**
	 * Test to export file as CSV
	 * @throws Exception
	 */
		@Test(priority = 6, groups = {
			"smoke" }, description = "Test for export file as CSV")
		public void verifyOverviewReportnExportVisibilityCSV() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.exportvisibilityrptCSV();
			addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
			}

	/**
	 * Test to export file as XLSX
	 * @throws Exception
	 */
		@Test(priority = 7, groups = {
			"smoke" }, description = "Test for export file as XLSX")
		public void verifyOverviewReportnExportVisibilityXLSX() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.exportvisibilityrptXLSX();
			addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
		}
	
	/**
	 * Test to export as Current Date Pdf
	 * @throws Exception
	 */
		@Test(priority = 8, groups = {
			"smoke" }, description = "Test for export file as pdf for Current Date")
		public void verifyexportcurrentpdf() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.exportcurrentvisibilityrptPDF();
			addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
		}
	
	/**
	 * Test to export a file as PDF of applied date
	 * @throws Exception
	 */
		@Test(priority = 9,groups = { "smoke" }, 
			description = "Test for export file as Visibility History pdf")
		public void PDFHistoryExport() throws Exception {		
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.exporthistoryvisibilityrptPDF();
			addEvidence(CurrentState.getDriver(), "Verified overview export for visibility report", "yes");
		}
	
	/**
	 * Test for Comparing Tooltip and overview report in Visibility Page
	 * @throws Exception
	 */
		@Test(priority = 10, groups = { "smoke" }, 
					description = "Test to compare ToolTip Value and Overall Visibility Score")
		public void verifyOverviewReportnTooltipVisibility() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
			addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview visibility report", "yes");
		}

	/**
	 * Test for SiteTable data in Visibility Page
	 * @throws Exception
	 */
		@Test(priority = 11, groups = {
			"smoke" }, description = "Test for verifying sitetable in Visibility page")
		public void verifySiteTable() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.verifyAllsites();
			addEvidence(CurrentState.getDriver(),
				"Data of All Sites Section ", "yes");
			data.verifySearchEngineSites();
			addEvidence(CurrentState.getDriver(),
				"Data of Search Engine Sites", "yes");
			data.verifyDirectorySites();
			addEvidence(CurrentState.getDriver(),
				"Data of Directory Sites", "yes");
			data.verifySocialSites();
			addEvidence(CurrentState.getDriver(),
				"Data of Social Sites Tab", "yes");
		}
	
	/**
	 * Test for compare number of rows and data from export table and table data in Visibility Page
	 * @throws Exception
	 */
		@Test(priority = 12, groups = {
			"smoke" }, description = "Test for verifying progress bar in Visibility page")
		public void numberofentriesnExporttableVisibility() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.compareexporttableDatannumberofentries(data.DataTablefound(),data.getExporttableDataFound());
			addEvidence(CurrentState.getDriver(),
					"Site level scores in Visibility site table  and overview visibility export found matching", "yes");
			data.exporttablefoundCSV();
		}

	/**
	 * Test to verify Top button functionality
	 * @throws Exception
	 */
		@Test(priority = 13, groups = {"smoke"},
			description = "Verify Top Button")
		public void GetTopBtn() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.TopButton();
			addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
		}					
	
	
	/**
	 * Test for compare number of rows and data from export table and table data in Visibility Page
	 * @throws Exception
	 */
		@Test(priority = 14, groups = {
			"smoke" }, description = "Test for verifying progress bar in Visibility page")
		public void numberofentriesnExporttableNotFoundVisibility() throws Exception {
			data = new TPSEE_Visibility_Page(CurrentState.getDriver());
			data.compareexporttableDatannumberofentriesNotFound(data.DataTableNotfound(),data.getExporttableDataNotFound());
			addEvidence(CurrentState.getDriver(),
				"Site level scores in Visibility site table  and overview visibility export found matching", "yes");
			data.exporttableNotfoundCSV();
		}
		
		/*@Test(dependsOnMethods="verifyFilteringReportsVisibility",enabled = true, dataProvider = "testData")
			public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) {
				
				TPSEE_Visibility_Page s = new TPSEE_Visibility_Page(CurrentState.getDriver());
				String fromDate =s.getCurrentFromDate();
				String toDate =s.getCurrentToDate();
				if(!(from_day.equals("null")) | !(to_day.equals("null")) ) {
					s.selectCalender_FromDate((Integer.parseInt(from_day)), from_month, (Integer.parseInt(from_year)));					
					s.selectCalender_ToDate(Integer.parseInt(to_day), to_month, (Integer.parseInt(to_year)));			
				}				
				
				System.out.println("fromDate : "+fromDate+" toDate : "+toDate);
				s.enterFromDate(fromDate);
				s.enterToDate(toDate);
			}
			
			@DataProvider
			public String[][] testData(){
				String[][] data = null, data1 = null;
				try {
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Zoom");
				wb.deleteEmptyRows();
				int rowCount = wb.getRowCount();
				System.out.println("rowCount : "+rowCount);
				data = new String[rowCount][6];
				data1 = new String[rowCount][6];
				int row = 0;
				for(int i = 2; i<=rowCount;i++) {
					 int colCount = wb.getColCount(i);
					 for(int j = 0;j<colCount; j++) {
						 //System.out.println("row : "+row+" j : "+j+" i : "+i);
						 //System.out.println("wb.getCellValue(i, j).trim() : "+wb.getCellValue(i, j).trim());
						 if(j > 0) {
							 if(((wb.getCellValue(i, j).trim()).equalsIgnoreCase("null")) | 
									 ((wb.getCellValue(i, j).trim()).length() == 0)){
								 data1[row][j] = "null";
							 }else data1[row][j] = wb.getCellValue(i, j).trim();
						 }else data1[row][j] = wb.getCellValue(i, j).trim();
						 //System.out.println(wb.getCellValue(i, j));					 
					 }
					 row++;
					 }
					 data[0][0] = wb.getCellValue(2, 0);  // from day
					 data[0][1] = wb.getCellValue(2, 1);  // from month
					 data[0][2] = wb.getCellValue(2, 2);  // from year
					 data[0][3] = wb.getCellValue(2, 3);  // to day
					 data[0][4] = wb.getCellValue(2, 4);  // to month
					 data[0][5] = wb.getCellValue(2, 5);  // to year
					 System.out.println("Arrays.deepToString(data1) : "+Arrays.deepToString(data1));
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					return data1;
				}
			}*/
}
