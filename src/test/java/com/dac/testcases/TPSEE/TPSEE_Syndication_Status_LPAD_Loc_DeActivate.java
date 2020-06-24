package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Syndication_Status_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Syndication_Status_LPAD_Loc_DeActivate extends BaseClass{
	TPSEE_Syndication_Status_Page data;
	Navigationpage np;
	
	/**
	 * Test to navigate to Syndication Status Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "To Navigate to Syndication Report")
	public void navigatetosyndicationstatus() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		Thread.sleep(5000);
		np.navigateToSyndicationStatus();
		CurrentState.getLogger().log(Status.PASS, "Navigated to Syndication Report Page");
		addEvidence(CurrentState.getDriver(), "To Navigate to Syndication Report Page", "yes");
	}
	
	@Test(priority = 2, description = "Verify Location Number deactivated")
	public void DeActivateScenario() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SyndicationLPAD");
		wb.deleteEmptyRows();
		for (int k = 1; k <= wb.getRowCount(); k++) {
			String LocNum = wb.getCellValue(k, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
			System.out.println("The Location Number is :" + LocNum);
			data.VerifyLocationNumber(LocNum);
			addEvidence(CurrentState.getDriver(), "Test for deactivating location", "yes");
		}
	}
}
