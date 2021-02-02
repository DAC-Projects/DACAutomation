package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed;

import resources.BaseClass;
import resources.CurrentState;

public class ReviewsFeed_TextVerification_Test extends BaseClass {

	Navigationpage np;
	Reviews_Feed data;
	
	@Test(priority = 1, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		/*data = new Reviews_Feed(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");*/
	}

	/*@Test(priority = 2, description = "Test to verify active state of the report")
	public void verifyreportactivestate() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.Review_Feed_Highlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of the report", "yes");
	}

	@Test(priority = 3, description = "Test to verify Title and Title Text")
	public void VerifyTitleText() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifyTitle("Review Feed",
				"The Review Feed lists all reviews that have been collected across the sites that are being monitored.");
		addEvidence(CurrentState.getDriver(), "Test to verify title and title text", "yes");
	}
	*/
	@Test(priority = 4, description = "Test to verify Notice text")
	public void VerifyNoticeText() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifyNotice();
	}
}
