package com.dac.testcases.TPSEE;

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
import com.dac.main.POM_TPSEE.TPSEE_ContentAnalysis_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_ContentAnalysis_Test extends BaseClass{

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_ContentAnalysis_Page data;
	double score;
	int location;
	String grph = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none";
	int start = 980;
	int end = 0;
	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";
	
	
	/**
	 * Test to get dashboard scores
	 * @throws Exception
	 */
		@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
		public void GetKPIValues() throws Exception {
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
			Thread.sleep(10000);
			score =data.getContentscore();
			System.out.println(score);
			location = data.getContentLoc();
			System.out.println(location);
			CurrentState.getLogger().log(Status.PASS, "KPI Scores");
			addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
		}
		
		
	@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to ContentAnalysis page")
	public void navigateToContentAnalysisPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToContentAnalysis();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
		addEvidence(CurrentState.getDriver(), "Navigate to ContentAnalysis page from Dashboard", "yes");
	}
	
	
	//CAScorenLoc
	@Test(priority = 3, groups = { "smoke" }, description = "Test for navigating to ContentAnalysis page")
	public void Verifyscorenloc() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		double CAscore = data.CAScore();
		Assert.assertEquals(CAscore, score);
		int CALoc = data.CALoc();
		Assert.assertEquals(CALoc, location);
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
		addEvidence(CurrentState.getDriver(), "Navigate to ContentAnalysis page from Dashboard", "yes");
	}
	
	//Test to verify Zoom Functionality 
		@Test(priority = 4,groups = {"smoke"},
				description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
			String OneMonth ="1m";
			int start = 1060;
			int end = 0;
			data.clickHighchartCriteria(OneMonth,start,end,grph);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String ThreeMonths = "3m";
			data.clickHighchartCriteria(ThreeMonths,1070,end,grph);
			addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String SixMonths = "6m";
			data.clickHighchartCriteria(SixMonths,1070,end,grph);
			addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String OneYear = "1y";
			data.clickHighchartCriteria(OneYear,1070,end,grph);
			addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
			Thread.sleep(5000);
			String YearToDate ="ytd";
			data.clickHighchartCriteria(YearToDate,1070,end,grph);
			addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
			Thread.sleep(5000);
			String ALLDATA = "all";
			data.clickHighchartCriteria(ALLDATA,1070,end,grph);
			addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			}
	
		@Test(priority = 5, groups = {"smoke" }, description = "Verify Content Analysis page loads after filter applied")
	public void verifyFilteringReportsContentAnalysis() throws Exception {
		data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_ContentAnalysis_Page s = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
	
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
	
	
	
	//Test for export and overview report in Content Analysis Page
		@SuppressWarnings("unchecked")
		@Test(priority = 6, groups = {
						"smoke" }, description = "Test for overview export and export verification")
			public void verifyOverviewReportnExportContentAnalysis() throws Exception {
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
			data.compareExprttoAnalysisSiteData(data.getExportData(), data.AnalysisSiteData());
			addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
		}
		
		//Test for Tooltip and overview report in Content Analysis Page
		@SuppressWarnings("unchecked")
		@Test(priority = 7, groups = { "smoke" }, 
								description = "Test to compare ToolTip Value and Overall Analysis Score")
		public void verifyOverviewReportnTooltipContentAnalysis() throws Exception {
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
			data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
			addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Content Analysis report", "yes");
		}
		
		@Test(priority = 7,enabled = true, dataProvider = "testData")
		public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws Exception {
			
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
			if(!(from_day.equals("null")) | !(to_day.equals("null")) ) {
				data.selectCalender_FromDate(grphfromDate,(int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				data.selectCalender_ToDate(grphtoDate,(int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));	
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");				
				Date fromcal = data.getCurrentfromDate();
				Date fromgrph = data.verifyinitialHistoryGraph(start, end, grph);
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				Assert.assertEquals(fromgrph, fromcal);
				Date tocal = data.getCurrenttoDate();
				Date togrph = data.verifyfinalHistorygraph(grph);
				Assert.assertEquals(togrph, tocal);
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			}				
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
		
		//Test for export and overview report in Content Analysis Page
				@Test(priority = 8, groups = {
								"smoke" }, description = "Test for overview export and export verification")
					public void verifyTableDataoExport() throws Exception {
					data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
					data.compareexporttableDatannumberofentries(data.SitelLinkData(),data.getSiteLinkExporttableData());
					addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
				}
				
				/*//Test to compare vendors in the application in Visibility Page
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = {"verifyTableDataoExport"},groups = {"smoke"},
						description ="Verify Site Vendors List")
				public void comparevendorsListnverifySitevendors() throws Exception{
					data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
					data.comparevendorsListnverifySitevendors(data.verifyAnalysisSitevendors(), data.vendorsList());
					addEvidence(CurrentState.getDriver(),
						"Site Vendors in Content Analysis site vendors ", "yes");
			}*/
	
	/**
	 * Test to verify Top button functionality
	 * @throws Exception
	 */
		@Test(priority = 9, groups = {"smoke"},
			description = "Verify Top Button")
		public void GetTopBtn() throws Exception {
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
			data.TopButton();
			addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
		}		
				
				
}
