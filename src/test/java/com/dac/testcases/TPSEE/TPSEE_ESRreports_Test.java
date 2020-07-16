package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_ESRreports_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_ESRreports_Test extends BaseClass {
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_ESRreports_Page data;

	// Navigation to ESR Page
	@Test(groups = { "smoke" }, description = "Test for navigating to ESR page")
	public void navigateToESRPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToESR();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE ESR page");
		addEvidence(CurrentState.getDriver(), "Navigate to ESR page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}

	@Test(priority = 2, groups = {"smoke" }, description = "Test for verifying title and description of report")
	public void verifyText() throws Exception {
		data = new TPSEE_ESRreports_Page(CurrentState.getDriver());
		data.VerifyTitleText("Executive Summary");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
	}

	@Test(priority = 3, groups = { "smoke" }, description = "Test for Set Frequency")
	public void setFrequency() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/ESRReport.xlsx", "ESR");
			wb.deleteEmptyRows();
			TPSEE_ESRreports_Page s = new TPSEE_ESRreports_Page(CurrentState.getDriver());
			for (int i = 1; i <= wb.getRowCount(); i++) {
				System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
				if (i > 1)
					CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String ESRFrequency = wb.getCellValue(i, wb.seacrh_pattern("ESRFrequency", 0).get(0).intValue());
				String Email = wb.getCellValue(i, wb.seacrh_pattern("Email", 0).get(0).intValue());
				System.out.println(ESRFrequency);
				s.clicksendReport(ESRFrequency, Email);
				addEvidence(CurrentState.getDriver(), "Frequency check", "yes");
				count++;

				System.out.println(Email);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
