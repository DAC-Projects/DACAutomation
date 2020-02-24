package com.dac.testcases.CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_ContentAnalysis_Page;
import com.dac.main.POM_CA.CA_Review_Page;

import com.selenium.testevidence.EvidenceReport;
import com.selenium.testevidence.EvidenceType;
import com.selenium.testevidence.GenerateEvidenceReport;
import com.selenium.testevidence.SeleniumEvidence;


import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.Utilities;

public class CA_Review_Test extends BaseClass {

  static List<Map<String, String>> export;
  Navigationpage np;
  CA_Review_Page data;
  String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
  String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";
  

  @Test(groups = {
      "smoke" }, description = "Test for navigating to Review page")
  public void navigateToReviewPage() throws Exception {

  
      np = new Navigationpage(CurrentState.getDriver());
      np.navigateCA_Reviewpage();
      /*new CA_Review_Page(CurrentState.getDriver())
          .verify_pageloadCompletely(10);*/
      CurrentState.getLogger().log(Status.PASS,
          "Navigated successfully to CA Review page");    
      addEvidence(CurrentState.getDriver(), "Navigate to Review page from Dashboard", "yes"); 

  }

  @Parameters({ "Filter" })
  @Test(dependsOnMethods = { "navigateToReviewPage" }, groups = {
      "smoke" }, description = "Verify review page loads after filter applied from Review page")
  public void verifyFilteringReportsReview(String Filter) throws Exception {

      data = new CA_Review_Page(CurrentState.getDriver());
      String[] filter = Filter.split(",");
      data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
      /*data.verify_pageloadCompletely(10);*/
      CurrentState.getLogger().log(Status.PASS,
          "Global filter applied for " + Arrays.toString(filter));
      addEvidence(CurrentState.getDriver(), "Apply Filter from Review page", "no");
      
  }

  @Test(dependsOnMethods = { "navigateToReviewPage" }, groups = {
      "smoke" }, description = "Test for Review overview export and export verification")
  public void verifyOverviewReportnExportReview() throws Exception {
 
      data = new CA_Review_Page(CurrentState.getDriver());
      export = data.getExportData();
      data.compareExprttoOvervw(export, data.getOverviewReport());
      CurrentState.getLogger().log(Status.PASS,
          "Overview report export and Overview report data found matching");
      addEvidence(CurrentState.getDriver(), "Verified overview export for review report", "yes"); 
  }

  @Test(dependsOnMethods = { "navigateToReviewPage" }, groups = {
      "smoke" }, description = "Test for verifying tooltips in Review page")
  public void verifyOverviewReportnTooltipReview() throws Exception {
  
      data = new CA_Review_Page(CurrentState.getDriver());
      data.compareReportnGraph(data.verifyHistoryGraph(),
          data.getOverviewReport());
      CurrentState.getLogger().log(Status.PASS,
          "Review history graph tooltip and overview report score found matching");
      addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview report", "yes"); 
    
  }

  @Test(dependsOnMethods = { "navigateToReviewPage",
      "verifyOverviewReportnExportReview" }, groups = {
          "smoke" }, description = "Test for comparing Review Site table and overview export values")
  public void verifySiteTablenExport() throws Exception {

      data = new CA_Review_Page(CurrentState.getDriver());
      data.compareExportnTable(export, data.verifySitetable());
      CurrentState.getLogger().log(Status.PASS,
          "Site level scores in site table  and overview report export found matching");
      addEvidence(CurrentState.getDriver(), "Site level scores in Review site table  and overview report export found matchin", "yes"); 

  }

//Test to verify Zoom Functionality
		@Test(priority=2,groups = {"smoke"},
				description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new CA_Review_Page(CurrentState.getDriver());
					
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
		
		@Test(priority = 3,enabled = true, dataProvider = "testData")
		public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws Exception {
			
			CA_Review_Page s = new CA_Review_Page(CurrentState.getDriver());
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
}
