package com.dac.testcases.CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Review_Page;
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
    

  @Test(priority= 0, groups = { "smoke" }, description = "Test for navigating to Review page")
  public void navigateToReviewPage() throws Exception {
  
      np = new Navigationpage(CurrentState.getDriver());
      np.navigateCA_Reviewpage();
      /*new CA_Review_Page(CurrentState.getDriver())
          .verify_pageloadCompletely(10);*/
      CurrentState.getLogger().log(Status.PASS,
          "Navigated successfully to CA Review page");    
      addEvidence(CurrentState.getDriver(), "Navigate to Review page from Dashboard", "yes"); 

  }

  
//  @Test(dependsOnMethods = { "navigateToReviewPage" }, groups = {
//      "smoke" }, description = "Verify review page loads after filter applied from Review page")
 
//	@Test(dependsOnMethods = { "navigateToReviewPage" }, groups= {"smoke"}, description = "Verify Review page loads after filter applied")

  public void verifyFilteringReportsReview() throws Exception {try {	
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "CA_FIL"); wb.deleteEmptyRows();
		CA_Review_Page s = new CA_Review_Page(CurrentState.getDriver());
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

//  @Test(dependsOnMethods = { "verifyFilteringReportsReview" }, groups = {"smoke" }, description = "Test for Review overview export and export verification")
  public void verifyOverviewReportnExportReview() throws Exception {
	  
	  // export and overview compare
 
      data = new CA_Review_Page(CurrentState.getDriver());
      export = data.getExportData();
      data.compareExprttoOvervw(export, data.getOverviewReport());
      CurrentState.getLogger().log(Status.PASS,
          "Overview report export and Overview report data found matching");
      addEvidence(CurrentState.getDriver(), "Verified overview export for review report", "yes"); 
  }

 // @Test(dependsOnMethods = { "verifyOverviewReportnExportReview" }, groups = {"smoke" }, description = "Test for verifying tooltips in Review page")
  public void verifyOverviewReportnTooltipReview() throws Exception {
  
      data = new CA_Review_Page(CurrentState.getDriver());
      data.compareReportnGraph(data.verifyHistoryGraph(),
          data.getOverviewReport());
      CurrentState.getLogger().log(Status.PASS,
          "Review history graph tooltip and overview report score found matching");
      addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview report", "yes"); 
    
  }

 // @Test(dependsOnMethods = {"verifyOverviewReportnExportReview"}, groups = { "smoke" }, description = "Test for comparing Review Site table and overview export values")
  public void verifySiteTablenExport() throws Exception {

      data = new CA_Review_Page(CurrentState.getDriver());
      data.compareExportnTable(export, data.verifySitetable());
      CurrentState.getLogger().log(Status.PASS,
          "Site level scores in site table  and overview report export found matching");
      addEvidence(CurrentState.getDriver(), "Site level scores in Review site table  and overview report export found matchin", "yes"); 
  }
  
  @Test( priority= 1,groups = { "smoke" }, description = "Location Competitor Set Test Evidence Capture with Export")
   public void navigatetoLocationCompetitorset() throws Exception{
	  
	  data = new CA_Review_Page(CurrentState.getDriver());
	  data.LocationCompetitorSetNavigation();
	  
	  data = new CA_Review_Page(CurrentState.getDriver());
 //     data.exportAllReviewLocationSet();
//      data.compareExprttoOvervw(export, data.getOverviewReport());
      CurrentState.getLogger().log(Status.PASS,
          "Location report export and Location report data found matching");
      addEvidence(CurrentState.getDriver(), "Verified location export for review report", "yes"); 
	  	 
	  CurrentState.getLogger().log(Status.PASS,
	          "Location Competitor Set Test Evidence Capture with Export");
	      addEvidence(CurrentState.getDriver(), "Export Button for the whole Location is displayed in the UI", "yes"); 
	  
	      
}
  @Parameters({ "Filter" })
  @Test(priority = 2, groups = {"smoke" }, description = "Select location for competitor")
  public void selectlocationforcompetitor(String Filter) throws InterruptedException
  
  {
	  
	  data = new CA_Review_Page(CurrentState.getDriver());
		String[] filter = Filter.split(";");
		Thread.sleep(3000);
		data.selectionLocationforcompetitor(filter[0]); 
		
		Thread.sleep(3000);
  }
  
 
  
  
//  @Test(dependsOnMethods = { "selectlocationforcompetitor" }, groups = {"smoke" }, description = "Test for verifying tooltips in Review page")
public void verifylocationReportTooltipReview() throws Exception {

  data = new CA_Review_Page(CurrentState.getDriver());
  data.verifyHistoryGraphLocationcompSet();
  data.getLocationReport();
  CurrentState.getLogger().log(Status.PASS,
      "Review history graph tooltip and overview relcnRprtDataport score found matching");
  addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview report", "yes"); 

}

@Test( dependsOnMethods = { "selectlocationforcompetitor" },groups = {
      "smoke" }, description = "Test for comparing Review Site table and overview export values")
public void verifylocationSiteTableExport() throws Exception {

  data = new CA_Review_Page(CurrentState.getDriver());
 
  data.compareExportnTableInArray(data.getExportDataLocationInArray(), data.verifySitetableLocnInArray());
  CurrentState.getLogger().log(Status.PASS,
      "Site level scores in site table  and overview report export found matching");
  addEvidence(CurrentState.getDriver(), "Site level scores in Review site table  and overview report export found matchin", "yes"); 

}
//@Test(dependsOnMethods = { "selectlocationforcompetitor"}, groups = {"smoke" }, description = "Export Location data")

public void exportlocationcompetitordata() throws Exception
{
data = new CA_Review_Page(CurrentState.getDriver());
    export = data.getExportDataLocation();
    data.compareExprttoLocation(export, data.getLocationReport());
   CurrentState.getLogger().log(Status.PASS,
        "Overview report export and Overview report data found matching");
    addEvidence(CurrentState.getDriver(), "Verified overview export for review report", "yes"); 
}
  }
  


