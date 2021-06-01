package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Syndication_Status_Page;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Syndication_Status_DTC_API extends BaseClass {

	TPSEE_Syndication_Status_Page data;
	Navigationpage np;
	String[] VendorList;
	String[] StatusList;
	SoftAssert soft = new SoftAssert();

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

	/**
	 * Test to verify Title and Title text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, dependsOnMethods = {
			"navigatetosyndicationstatus" }, description = "To Verify Title and Title Text")
	public void VerifyTitleTxt() throws Exception {
		data = new TPSEE_Syndication_Status_Page(CurrentState.getDriver());
		data.VerifyTitlenText();
		addEvidence(CurrentState.getDriver(), "To Verify Title and Title Text", "yes");
	}
}
