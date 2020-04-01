package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_ReviewStream_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_ReviewStream_Test extends BaseClass{
	
	static List<Map<String, String>> export, reviewTable;
	Navigationpage np;
	TPSEE_ReviewStream_Page data;
	public String[][] exceldata;
	
	
	//Navigation Test
	@Test(groups = { "smoke" }, description = "TC: Navigating to Review Stream")
	public void navigateToReviewStreamPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToReviewStream();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Review Stream page");
		addEvidence(CurrentState.getDriver(), "Navigate to Review Stream page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}

//Test for export 7 days data
		@Test(dependsOnMethods = { "navigateToReviewStreamPage"}, groups = {
						"smoke" }, description = "TC: export 7 day Review Stream data")
			public void verifyReviewStreamReportExport() throws Exception {
			data = new TPSEE_ReviewStream_Page(CurrentState.getDriver());
			exceldata=data.excelExported();
			export = data.getReviewStreamExportData(exceldata);
			addEvidence(CurrentState.getDriver(), "Exported 7 day Review Stream report", "yes");
		}
		

//Compare exported 7 days data with 7 days data in Table
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = { "verifyReviewStreamReportExport"}, groups = {
						"smoke" }, description = "TC: Review Stream Table and export verification")
			public void verifyTableDataWithExport() throws Exception {
			data = new TPSEE_ReviewStream_Page(CurrentState.getDriver());
			
			data.compareExprttoAnalysisSiteLinkData(data.ReviewStreamDataTable(exceldata), export );
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
			CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
			addEvidence(CurrentState.getDriver(), "Verified Review Stream for export for 7 days", "yes");
		}	
}

