package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Displayed_Review_Score_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Displayed_Review_Score_Test extends BaseClass {

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Displayed_Review_Score_Page data;
	String chromepath = "./downloads/chromeReviewScoreExportXLSX.xlsx";
	String IEpath = "./downloads/IEReviewScoreExportXLSX.xlsx";
	String FFpath = "./downloads/FFReviewScoreExportXLSX.xlsx";
	SoftAssert soft = new SoftAssert();

	@Test(priority = 1, groups = { "smoke" }, description = "Test for navigating to Displayed Riview page")
	public void navigateToDisplayedReviewScore() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToDisplayedReviewScore();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Displayed Riview page");
		addEvidence(CurrentState.getDriver(), "Navigate to Displayed Riview page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(priority = 2, description = "Test to verify highlight of report")
	public void VerifyDRSHighlight() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.DRShighlight();
		addEvidence(CurrentState.getDriver(), "Test to verify report highlight", "yes");
	}

	@Test(priority = 3, groups = {"smoke" }, description = "Test for verifying title and description of report")
	public void verifyText() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.VerifyTitleText1("Displayed Review Score",
				"The displayed review score for each location is what the star rating is on the listing for the previous day. The displayed star rating may be different from the average star rating for the site in the review report if these sites apply a different equation to calculate reviews using factors such as recency.");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
	}

	@Test(priority = 4, groups = { "smoke" }, description = "Verify Displayed Riview page loads after filter applied")
	public void verifyFilteringReportsDisplayedReview() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_Displayed_Review_Score_Page s = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());

			for (int i = 1; i <= wb.getRowCount(); i++) {
				System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
				if (i > 1)
					CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());

				String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode
						+ ", " + State + ", " + City + ", " + Location + "", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5, groups = { "smoke" }, description = "Export as csv")
	public void exportascsv() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.DRSTableExportCSV();
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}

	@Test(priority = 6, groups = { "smoke" }, description = "Export as csv")
	public void exportasxlsx() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.DRSTableExportXLSX();
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}

	@Test(priority = 7, groups = { "smoke" }, description = "Export as csv")
	public void Compare_UI_XLGoogleRating() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.compareUInExportGoogle_Score(chromepath, IEpath, FFpath);
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}

	@Test(priority = 8, groups = { "smoke" }, description = "Export as csv")
	public void Compare_UI_XLFacebookRating() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.compareUInExportFaceBook_Score(chromepath, IEpath, FFpath);
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}

	@Test(priority = 9, groups = { "smoke" }, description = "Export as csv")
	public void tabledataexport() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.ReviewDataTable();
		addEvidence(CurrentState.getDriver(), "Verified Location export for All Locations", "yes");
	}
	
	@Test(priority = 10, description = "Test to GoTo Page verification")
	public void verifyGotoPage() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
		data.GoTo();
		addEvidence(CurrentState.getDriver(), "Test to verify GoTo Page", "yes");
	}
	
	@Test(priority = 11, description = "Test to results per page")
	public void verifyResultperPage() throws Exception {
		data = new TPSEE_Displayed_Review_Score_Page(CurrentState.getDriver());
			data.resultperpage(soft);	
			addEvidence(CurrentState.getDriver(), "Test to verify Results per page", "yes");
			Thread.sleep(5000);		
			soft.assertAll();
	}

}
