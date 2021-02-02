package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed_Response_To_Reviews;

import resources.BaseClass;
import resources.CurrentState;

public class ReviewsFeed_RespondToReviews_Keyword_Tag_Test extends BaseClass {
	
	Navigationpage np;
	Reviews_Feed_Response_To_Reviews data;
	
	@Test(priority = 1, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		/*data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");*/
	}
	
	@Test(priority = 2, description = "To Click on Respond to Reviews")
	public void NavigateToResponseToReviewsTab() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.ClickResToReviews();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response to Reviews Tab", "yes");
	}

	@Test(priority = 3, description = "Test to verify keyword")
	public void verifyKeywordentered() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.KeywordSearch();
	}
	
	@Test(priority = 4, description = "Test to verify tag")
	public void verifyTagentered() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.VerifyTag();
	}
}
