package com.dac.testcases.CA;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_ContentAnalysis_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
//
public class CA_ContentAnalysis_Test extends BaseClass {


	static List<Map<String, String>> export;
	Navigationpage np;
	CA_ContentAnalysis_Page data;
	List<Map<String, String>>  exportData;
	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";

	@Test(priority=1,groups= {"smoke"})
	public void navigateToCAPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateCA_ContentAnalysispage();
//		new CA_ContentAnalysis_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);
		 CurrentState.getLogger().log(Status.PASS, "Navigated successfully to CA Review page");
	}

	
	@Test(priority=2)
	public void verifyFilteringReports() throws Exception {try {	
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "CA_FIL"); wb.deleteEmptyRows();
		CA_ContentAnalysis_Page s = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		for(int i=1;i<=wb.getRowCount();i++) {
			System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			if(i>1) CurrentState.getDriver().navigate().refresh();
			s.waitUntilLoad(CurrentState.getDriver());
			
			
			String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
			String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
			String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
			String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
			
			s.applyFilter(CountryCode, State, City, Location);
			System.out.println(CountryCode+", "+State+", "+City+", "+Location);
			s.clickApplyFilterBTN();
			BaseClass.addEvidence(CurrentState.getDriver(),
					"Applied global filter: "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
			
			Thread.sleep(4000);
			System.out.println("-------------- Scenarios : "+ count++ + "Ends --------------------");
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		//Assert.fail("");
	}}

	@Test(priority=3)
	public void verifyOverviewReportnExport() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		export = data.getExportData();
		exportData = data.getOverviewReport();
		data.compareExprttoOvervw(export, exportData);
		CurrentState.getLogger().log(Status.PASS, "Overview report export and Overview report data found matching");
		
	}

	@Test(priority=4)
	public void verifyOverviewReportnTooltip() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), exportData);
		CurrentState.getLogger().log(Status.PASS, "Review history graph tooltip and overview report score found matching");
		
	}


	
	//Test to verify Zoom Functionality
		@Test(priority=5,groups = {"smoke"},
				description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
					
			String OneMonth ="1m";
			data.clickHighchartCriteria(OneMonth);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			
			String ThreeMonth ="3m";
			data.clickHighchartCriteria(ThreeMonth);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			
			String SixMonth ="6m";
			data.clickHighchartCriteria(SixMonth);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			
			String OneYear ="1y";
			data.clickHighchartCriteria(OneYear);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			
			String YearToDate ="ytd";
			data.clickHighchartCriteria(YearToDate);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
			
			String ALLDATA ="all";
			data.clickHighchartCriteria(ALLDATA);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Thread.sleep(5000);
		}


		@Test(priority = 6,enabled = true, dataProvider = "testData")
		public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws Exception {
			
			CA_ContentAnalysis_Page s = new CA_ContentAnalysis_Page(CurrentState.getDriver());
			if(!(from_day.equals("null")) | !(to_day.equals("null")) ) {
				s.selectCalender_FromDate(grphfromDate,(int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				s.selectCalender_ToDate(grphtoDate,(int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));	
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");				
				Date fromcal = s.getCurrentfromDate();
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				Date tocal = s.getCurrenttoDate();
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


	@Test(priority=7)
	public void verifyOverviewReportncalculation() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		data.calculateContentAnalysisScore();		
	}
	
	@Test(priority=8)
	public void UiCalculation() throws Exception {
		data = new CA_ContentAnalysis_Page(CurrentState.getDriver());
		data.UiCalculation();		
	}
	
}

