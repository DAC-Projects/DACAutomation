package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_ContentAnalysis_Page;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_ContentAnalysis_Test extends BaseClass{

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_ContentAnalysis_Page data;
	
	@Test(groups = { "smoke" }, description = "Test for navigating to ContentAnalysis page")
	public void navigateToContentAnalysisPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToContentAnalysis();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
		addEvidence(CurrentState.getDriver(), "Navigate to ContentAnalysis page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(dependsOnMethods = { "navigateToContentAnalysisPage" }, groups = {
	"smoke" }, description = "Verify Content Analysis page loads after filter applied")
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
		@Test(dependsOnMethods = { "navigateToContentAnalysisPage" ,"verifyFilteringReportsContentAnalysis"}, groups = {
						"smoke" }, description = "Test for overview export and export verification")
			public void verifyOverviewReportnExportContentAnalysis() throws Exception {
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());			
			//data.compareExprttoAnalysisSiteData(data.getExportData(), data.AnalysisSiteData());
		//	data.getExportData();
		//	data.getSiteData();
			
			
			String [][] excel = data.getExportDataNew();
			String [][] UI = data.getSiteData();
			
			if(Arrays.deepEquals(excel,UI)) {
				
				
				System.out.println("Value matches");
			}
			else {
				System.out.println("not match");
			}
			addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
			
			for(int i =0; i<UI.length;i++) {
				assertEquals(Arrays.toString(excel[i]), Arrays.toString(UI[i]));
			}
			
			
		}
		
		//Test for Tooltip and overview report in Content Analysis Page
		@SuppressWarnings("unchecked")
//		@Test(dependsOnMethods = { "verifyOverviewReportnExportContentAnalysis" }, groups = { "smoke" }, 
//								description = "Test to compare ToolTip Value and Overall Analysis Score")
		public void verifyOverviewReportnTooltipContentAnalysis() throws Exception {
			data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
			data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
			addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Content Analysis report", "yes");
		}
		
		//Test for export and overview report in Content Analysis Page
				@SuppressWarnings("unchecked")
//				@Test(dependsOnMethods = { "verifyFilteringReportsContentAnalysis"}, groups = {
//								"smoke" }, description = "Test for overview export and export verification")
					public void verifyTableDataoExport() throws Exception {
					data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
					data.SitelLinkData();
					data.getSiteLinkExporttableData();
					data.compareXlData_UIdata();
					addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
				}
				
				//Test to compare vendors in the application in Visibility Page
				@SuppressWarnings("unchecked")
//				@Test(dependsOnMethods = {"verifyTableDataoExport"},groups = {"smoke"},
//						description ="Verify Site Vendors List")
				public void comparevendorsListnverifySitevendors() throws Exception{
					data = new TPSEE_ContentAnalysis_Page(CurrentState.getDriver());
					data.comparevendorsListnverifySitevendors(data.verifyAnalysisSitevendors(), data.vendorsList());
					addEvidence(CurrentState.getDriver(),
						"Site Vendors in Content Analysis site vendors ", "yes");
			}
}
