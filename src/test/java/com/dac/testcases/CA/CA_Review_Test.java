package com.dac.testcases.CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
import resources.Utilities;

public class CA_Review_Test extends BaseClass {

  static List<Map<String, String>> export;
  Navigationpage np;
  CA_Review_Page data;

  

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
}
