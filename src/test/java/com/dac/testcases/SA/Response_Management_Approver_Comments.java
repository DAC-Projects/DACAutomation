package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Response_Management;

import resources.BaseClass;
import resources.CurrentState;

public class Response_Management_Approver_Comments extends BaseClass {
	
	Navigationpage np;
	Response_Management data;
	
	@Test(priority = 1, description = "Test to navigate to Response Management Page")
	public void NavigateToResponseManagement() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToResponseManagement();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 2, description = "Test to change the status to pending approval")
	public void ChangeStatusToPending() throws Exception{
		data = new Response_Management(CurrentState.getDriver());
		data.selectPendingStatus();
		addEvidence(CurrentState.getDriver(), "Test to change status to pending approval", "yes");
	}
	
	@Test(priority = 3, description = "Test to Approve the Response with Comments")
	public void ApprovewithComments() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.ApproveResponsewithComments();
	}
	
	@Test(priority = 4, description = "Test to navigate to Reviews Feed Page")
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed Page", "yes");
	}
	
	@Test(priority = 5, description = "Test to Delete Approved Response")
	public void DeleteApprovedResponse() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.DeleteApprovedResponse();
	}

}
