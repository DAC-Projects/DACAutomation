package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_LeftHand_Navigation;
import resources.BaseClass;
import resources.CurrentState;
import resources.JSWaiter;

public class Left_Hand_Navigation_Test extends BaseClass {
	
	Navigationpage np;
	TPSEE_LeftHand_Navigation data;
	
	/**
	 * Test to verify All Locations active state
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to verify active state of All Locations")
	public void verifyAllLocationsactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToAllLocations();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.AllLocationshightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of all locations page", "yes");
	}
	
	/**
	 * Test to verify Visibility active state
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to verify active state of Visibilty Report")
	public void verifyVisibilityactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Visibility();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.visibilityhightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Visibility Report", "yes");
	}
	
	/**
	 * Test to verify Accuracy active state
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to verify active state of Accuracy Report")
	public void verifyAccuracyactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Accuracy();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.accuracyhightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Visibility Report", "yes");
	}
	
	/**
	 * Test to verify Analysis active state
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to verify active state of Content Analysis Report")
	public void verifyAnalysisactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToContentAnalysis();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.contentanalysishightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Content Analysis Report", "yes");
	}
	
	/**
	 * Test to verify Google Ranking active state
	 * @throws Exception
	 */
	@Test(priority = 5, description = "Test to verify active state of Google Ranking Report")
	public void verifyGoogleRankingactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToGoogleRanking();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.GoogleRankinghightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Google Ranking Report", "yes");
	}
	
	/**
	 * Test to verify Duplicate Management active state
	 * @throws Exception
	 */
	@Test(priority = 6, description = "Test to verify active state of Duplicate Management")
	public void verifyDuplicateManagementactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToDuplicateManagement();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.Duplicatehightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Duplicate Management Report", "yes");
	}
	
	/**
	 * Test to verify Listing Verification active state
	 * @throws Exception
	 */
	@Test(priority = 7, description = "Test to verify active state of Listing Verification Report")
	public void verifyListingVerificationactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSyndicationStatus();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.ListingVerificationhightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Listing Verification Report", "yes");
	}
	
	/**
	 * Test to verify Local Rank Plus active state
	 * @throws Exception
	 */
	@Test(priority = 8, description = "Test to verify active state of Local Rank Plus Report")
	public void verifyLocalRankPlusactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_LocalRankPlus();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.LocalRankPlushightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Local Rank Plus Report", "yes");
	}
	
	/**
	 * Test to verify Google My Business active state
	 * @throws Exception
	 */
	@Test(priority = 9, description = "Test to verify active state of Google My Business Report")
	public void verifyGoogleMyBusinessactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToGoogleMyBusiness();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.GoogleMyBusinesshightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Google My Business Report", "yes");
	}
	
	/**
	 * Test to verify Facebook Insights active state
	 * @throws Exception
	 */
	@Test(priority = 10, description = "Test to verify active state of Facebook Insights Report")
	public void verifyFacebookInsightssactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToFacebookInsights();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.clickDone();
		data.FacebookInsightshightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Facebook Insights Report", "yes");
	}
	
	/**
	 * Test to verify Bing Places for Business active state
	 * @throws Exception
	 */
	@Test(priority = 11, description = "Test to verify active state of Bing Places for Business Report")
	public void verifyBingPlacessactivestate() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToBingPlacesForBusiness();
		JSWaiter.waitJQueryAngular();
		data = new TPSEE_LeftHand_Navigation(CurrentState.getDriver());
		data.BingPlacesforBusinesshightlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of Bing Places for Business Report", "yes");
	}

}
