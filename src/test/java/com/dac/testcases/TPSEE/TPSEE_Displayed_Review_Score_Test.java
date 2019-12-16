package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_AllLocations_Page;
import com.dac.main.POM_TPSEE.TPSEE_Displayed_Review_Score_Page;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Displayed_Review_Score_Test extends BaseClass {
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Displayed_Review_Score_Page data;
	
	@Test(groups = { "smoke" }, description = "Test for navigating to Displayed Riview page")
	public void navigateToDisplayedReviewScore() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToDisplayedReviewScore();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Displayed Riview page");
		addEvidence(CurrentState.getDriver(), "Navigate to Displayed Riview page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(dependsOnMethods = { "navigateToDisplayedReviewScore" }, groups = {
	"smoke" }, description = "Verify Displayed Riview page loads after filter applied")
	public void verifyFilteringReportsDisplayedReview() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_Displayed_Review_Score_Page s = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
	
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
	
	@Test(dependsOnMethods = {"verifyFilteringReportsDisplayedReview"}, groups = {"smoke"},
			description ="To get Google and Facebook Ratings")
	public void verifyscores() throws Exception{
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.getscrores();
		addEvidence(CurrentState.getDriver(), "Ratings of Google and facebook", "yes");
	}
	

	//Test for export and overview report in Content Analysis Page
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = { "verifyscores"}, groups = {
						"smoke" }, description = "Test for Review export and export verification")
			public void verifyTableDataoExport() throws Exception {
			data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
			data.compareexporttableDatanstardetails(data.ReviewDataTable(),data.getReviewDataTableExport());
			addEvidence(CurrentState.getDriver(), "Verified Review export for UI Data", "yes");
		}
}
