package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_GoogleRanking_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_GoogleRanking_Test extends BaseClass{

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_GoogleRanking_Page data;	
	double score;
	int location;
	
	
	/**
	 * Test to get dashboard scores
	 * @throws Exception
	 */
		@Test(priority = 1, groups = { "smoke" }, description = "Test for getting KPI Values")
		public void GetKPIValues() throws Exception {
			data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
			Thread.sleep(10000);
			score =data.getGRScore();
			System.out.println(score);
			location = data.getGRLoc();
			System.out.println(location);
			CurrentState.getLogger().log(Status.PASS, "KPI Scores");
			addEvidence(CurrentState.getDriver(), "Get KPI Score", "yes");
		}
	
	@Test(priority = 2, groups = { "smoke" }, description = "Test for navigating to Google Ranking page")
	public void navigateToGoogleRankingPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToGoogleRanking();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Google Ranking page");
		addEvidence(CurrentState.getDriver(), "Navigate to Google Ranking page from Dashboard", "yes");
	}
	
	//GRScorenLoc
		@Test(priority = 3, groups = { "smoke" }, description = "Test for navigating to ContentAnalysis page")
		public void Verifyscorenloc() throws Exception {
			data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
			double GRscore = data.GRScore();
			Assert.assertEquals(GRscore, score);
			int GRLoc = data.GRLoc();
			Assert.assertEquals(GRLoc, location);
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Accuracy page");
			addEvidence(CurrentState.getDriver(), "Navigate to ContentAnalysis page from Dashboard", "yes");
		}
	
		//Test to verify Zoom Functionality
		@Test(priority = 4 ,groups = {"smoke"},
				description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
			String OneMonth ="1m";
		int start = 1060;
		int end = 0;
		data.clickHighchartCriteria(OneMonth,start,end);
		addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
		Thread.sleep(5000);
		String ThreeMonths = "3m";
		data.clickHighchartCriteria(ThreeMonths,start,end);
		addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
		Thread.sleep(5000);
		String SixMonths = "6m";
		data.clickHighchartCriteria(SixMonths,start,end);
		addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
		Thread.sleep(5000);
		String OneYear = "1y";
		data.clickHighchartCriteria(OneYear,start,end);
		addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
		Thread.sleep(5000);
		String YearToDate ="ytd";
		data.clickHighchartCriteria(YearToDate,start,end);
		addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
		Thread.sleep(5000);
		String ALLDATA = "all";
		data.clickHighchartCriteria(ALLDATA,start,end);
		addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			}
		
		
	@Test(priority = 5, groups = {
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
	
	@Test(priority = 6, groups = {
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
			@Test(priority = 7, groups = {
							"smoke" }, description = "Test for Ranking scores Data")
				public void verifyRankingScoreDataGoogleRanking() throws Exception {
				data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				data.RankingScoresData();
				addEvidence(CurrentState.getDriver(), "Verified Ranking Score for Google Ranking", "yes");
			}
	
	//Test for Tooltip and overview report in Content Analysis Page
			@SuppressWarnings("unchecked")
			@Test(priority = 8, groups = { "smoke" }, 
									description = "Test to get Tooltip Data")
			public void verifyToolTipGoogleRanking() throws Exception {
				data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				data.verifyHistoryGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified from Google Ranking", "yes");
			}
			
			//Test for export and overview report in Content Analysis Page
			@SuppressWarnings("unchecked")
			@Test(priority = 9, groups = {
							"smoke" }, description = "Test for Ranking export and export verification")
				public void verifyTableDataoExport() throws Exception {
				data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				data.compareexporttableDatanrankingdetails(data.RankingDataTable(),data.getRankingDataTableExport());
				addEvidence(CurrentState.getDriver(), "Verified Ranking export for Google Ranking", "yes");
			}			
			
		
		/**
		 * Test to verify Top button functionality
		 * @throws Exception
		 */
			@Test(priority = 10, groups = {"smoke"},
				description = "Verify Top Button")
			public void GetTopBtn() throws Exception {
				data = new TPSEE_GoogleRanking_Page(CurrentState.getDriver());
				data.TopButton();
				addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
			}		
}
