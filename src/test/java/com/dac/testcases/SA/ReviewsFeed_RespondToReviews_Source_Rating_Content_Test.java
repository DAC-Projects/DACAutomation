package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed_Response_To_Reviews;

import resources.BaseClass;
import resources.CurrentState;

public class ReviewsFeed_RespondToReviews_Source_Rating_Content_Test extends BaseClass {
	
	Navigationpage np;
	Reviews_Feed_Response_To_Reviews data;
	
	@Test(priority = 1, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		//data = new Reviews_Feed(CurrentState.getDriver());
		//data.CancelWalkme();
		//addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 2, description = "To Click on Respond to Reviews")
	public void NavigateToResponseToReviewsTab() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.ClickResToReviews();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response to Reviews Tab", "yes");
	}
	
	@Test(priority = 3, description = "Test to compare review count and entries in table")
	public void compareReviewCountandNumberofEntries() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.verifyReviewCount();
	}

	@Test(priority = 4, description = "Test to select source")
	public void compareSourcewithReviewsTable() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.SelectSource();
	}
	
	@Test(priority = 3, description = "Test to verify content filter")
	public void verifyContentFilter() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.SelectContent();
	}

	@Test(priority = 4, description = "Test to verify rating filter")
	public void verifyratingfilter() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.SelectRating();
	}
	
	@Test(priority = 5, description = "Test to verify Notice text")
	public void VerifyNoticeText() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.verifyNotice();
	}

}
