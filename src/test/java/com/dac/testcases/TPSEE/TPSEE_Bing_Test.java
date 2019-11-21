package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Bing_Page;
import com.dac.main.POM_TPSEE.TPSEE_GMB;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Bing_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Bing_Page data;
	
	//Test to navigate to Bing Page
	@Test(groups = { "smoke" }, description = "Test for navigating to Bing page")
	public void navigateToBingPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToBingPlacesForBusiness();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Bing page");
		addEvidence(CurrentState.getDriver(), "Navigate to Bing page from Dashboard", "yes");
	}
	
	//Test to apply filter in Bing Page		
	@Test(dependsOnMethods = { "navigateToBingPage" }, groups = {
	"smoke" }, description = "Verify Bing page loads after filter applied")
	public void verifyFilteringReportsBing() throws Exception {
		data = new TPSEE_Bing_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_Bing_Page s = new TPSEE_Bing_Page(CurrentState.getDriver());
	
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
	
		//Test for Tooltip score in Bing Page
			@Test(dependsOnMethods = { "verifyFilteringReportsBing" }, groups = { "smoke" }, 
									description = "Test to get ToolTip Value Score")
			public void verifyTooltipBing() throws Exception {
				data = new TPSEE_Bing_Page(CurrentState.getDriver());
				data.verifyBingHistoryGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified", "yes");
			}
			
		//Test to export Bing report 
			
			@Test(dependsOnMethods = {"verifyTooltipBing"},groups = {"smoke"},
					description ="Verify Site Vendors List")
			public void BingCSVExport() throws Exception{
				data = new TPSEE_Bing_Page(CurrentState.getDriver());
				data.exportCSVBing();
				addEvidence(CurrentState.getDriver(),
					"Verifying export functionality ", "yes");
			}
			
			
			@Test(dependsOnMethods = {"BingCSVExport"},groups = {"smoke"},
					description ="Verify Site Vendors List")
			public void BingExport() throws Exception{
				data = new TPSEE_Bing_Page(CurrentState.getDriver());
				data.exportXLSXBing();
				addEvidence(CurrentState.getDriver(),
					"Verifying export functionality ", "yes");
			}

		//Test for comparing UI and export Impression count	
			@Test(dependsOnMethods = {"BingExport"},groups = {"smoke"},
					description ="Verify Impressions")
			public void VerifyTotalImpressionUIExport() throws Exception{
				data = new TPSEE_Bing_Page(CurrentState.getDriver());
				data.compareUInExportImpressions();
				addEvidence(CurrentState.getDriver(),
					"Compare UI and export Impressions ", "yes");
			}
			
			//Test to verify Zoom Functionality
			@Test(dependsOnMethods = {"verifyFilteringReportsBing"},groups = {"smoke"},
					description ="Verify Zoom Functionality")
			public void gethighchartsdate() throws Exception{
				data = new TPSEE_Bing_Page(CurrentState.getDriver());
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
}
