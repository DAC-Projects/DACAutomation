package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_GoogleRanking_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_GoogleRanking_Test extends BaseClass{

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_GoogleRanking_Page data;
	
	@Test(groups = { "smoke" }, description = "Test for navigating to Google Ranking page")
	public void navigateToGoogleRankingPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToGoogleRanking();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Google Ranking page");
		addEvidence(CurrentState.getDriver(), "Navigate to Google Ranking page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(dependsOnMethods = { "navigateToGoogleRankingPage" }, groups = {
			"smoke" }, description = "Add Account Level and Group Level Keyword")
	public void verifyApplyKeywords() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		try {
			ExcelHandler wb = new ExcelHandler("./data/GroupAndAccountKeywords.xlsx", "AccountLevelKeywords"); wb.deleteEmptyRows();
			TPSEE_GoogleRanking_Page s = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
			for(int i=1;i<=wb.getRowCount();i++) {
				if(i>1) CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String AccKey = wb.getCellValue(i, wb.seacrh_pattern("AccountKeyword", 0).get(0).intValue());
				String GrKey = wb.getCellValue(i, wb.seacrh_pattern("GroupKey", 0).get(0).intValue());
				String GrKeyword = wb.getCellValue(i, wb.seacrh_pattern("GroupKeyword", 0).get(0).intValue());
				s.applyKeywords(AccKey , GrKey , GrKeyword);
				System.out.println(AccKey+", "+GrKey+", "+GrKeyword);
				System.out.println();
				s.clickApplyKeyword();
				BaseClass.addEvidence(CurrentState.getDriver(),
						"Applied Account and Group Level Keywords: "+AccKey+", "+GrKey+", "+GrKeyword+"", "yes");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = { "verifyApplyKeywords" }, groups = {
	"smoke" }, description = "Verify Google Ranking page loads after filter applied")
	public void verifyFilteringReportsnavigateToGoogleRanking() throws Exception {
		data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_GoogleRanking_Page s = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
	
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
			@Test(dependsOnMethods = { "verifyFilteringReportsnavigateToGoogleRanking"}, groups = {
							"smoke" }, description = "Test for Ranking scores Data")
				public void verifyRankingScoreDataGoogleRanking() throws Exception {
				data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				data.RankingScoresData();
				addEvidence(CurrentState.getDriver(), "Verified Ranking Score for Google Ranking", "yes");
			}
	
	//Test for Tooltip and overview report in Content Analysis Page
			@SuppressWarnings("unchecked")
			@Test(dependsOnMethods = { "verifyRankingScoreDataGoogleRanking" }, groups = { "smoke" }, 
									description = "Test to get Tooltip Data")
			public void verifyToolTipGoogleRanking() throws Exception {
				data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				data.verifyHistoryGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified from Google Ranking", "yes");
			}
			
			//Test for export and overview report in Content Analysis Page
			@SuppressWarnings("unchecked")
			@Test(dependsOnMethods = { "verifyToolTipGoogleRanking"}, groups = {
							"smoke" }, description = "Test for Ranking export and export verification")
				public void verifyTableDataoExport() throws Exception {
				data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				data.RankingDataTable();
				data.getRankingDataTableExport();
				data.compareXlData_UIdata();
				addEvidence(CurrentState.getDriver(), "Verified Ranking export for Google Ranking", "yes");
			}
}
