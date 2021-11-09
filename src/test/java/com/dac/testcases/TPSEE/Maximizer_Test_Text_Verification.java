package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.Maximizer_Page;

import resources.BaseClass;
import resources.CurrentState;

public class Maximizer_Test extends BaseClass {
	
	Navigationpage np;
	Maximizer_Page data;
	
	/**
	 * Test to navigate to Maximizer Page
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to navigate to Maximizer")
	public void NavigateToMaximizer() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToMaximizer();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Maximizer", "yes");
	}

	/**
	 * Test to verify title and title text
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to verify title and title text of maximizer page")
	public void verifyTitleText() throws Exception {
		data = new Maximizer_Page(CurrentState.getDriver());
		data.VerifyTitleText();
	}
	
	/**
	 * Test to verify PDF Content
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify read manual pdf content")
	public void VerifyPdfContent() throws Exception {
		data = new Maximizer_Page(CurrentState.getDriver());
		data.verifyContentInPDf("Maximizer", "Maximizer is broken into three main parts", 
				"A. Total Recommendations", "Maximizer_Manual.pdf");
	}
	
	/**
	 * Test to verify Tooltip text verification
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to verify tool tip text")
	public void VerifyTooltipText() throws Exception {
		data = new Maximizer_Page(CurrentState.getDriver());
		data.TooltipTextVerification();
	}
	
	/**
	 * Test to verify Recommendation Count
	 */
	@Test(priority = 5, description = "Test to verify Recommendation count")
	public void TotalRecommendationCount() {
		data = new Maximizer_Page(CurrentState.getDriver());
		data.VerifyRecommendationCount();
	}
	
	/**
	 * Test to verify percentage
	 */
	@Test(priority = 6, description = "Test to verify percentage")
	public void VerifyPercentage() {
		data = new Maximizer_Page(CurrentState.getDriver());
		data.verifyPercentageComplete();
	}
	
	/**
	 * Test to verify texts of the reco
	 * @throws Exception
	 */
	@Test(priority = 7, description = "Test to verify Details, Impact and Benefits of Reco")
	public void VerifyText() throws Exception {
		data = new Maximizer_Page(CurrentState.getDriver());
		data.getDatafromUIandverifywithXLdata();
		addEvidence(CurrentState.getDriver(), "Test to verify text", "yes");
	}
}
