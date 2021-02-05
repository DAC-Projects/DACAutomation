package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Response_Management;

import resources.BaseClass;
import resources.CurrentState;

public class Response_Management_Approver extends BaseClass {

	Navigationpage np;
	Response_Management data;
	
	@Test(priority = 1, description = "Test to navigate to Response Management Page")
	public void NavigateToResponseManagement() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToResponseManagement();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 2, description = "Test to verify Title and Title Text")
	public void verifyTitlenText() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.verifyTitle("Review Response Management", "The Response Management page provides a designated area to view and manage review responses that have been drafted by users with different permission levels.");
		addEvidence(CurrentState.getDriver(), "Test to verify title and title text", "yes");
	}
	
	@Test(priority = 3, description = "Test to change the status to pending approval")
	public void ChangeStatusToPending() throws Exception{
		data = new Response_Management(CurrentState.getDriver());
		data.selectPendingStatus();
		addEvidence(CurrentState.getDriver(), "Test to change status to pending approval", "yes");
	}
	
	@Test(priority = 4, description = "Test to Approve Response")
	public void ApproveResponseWithoutComments() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.ApproveResponse();		
	}
	
	@Test(priority =5, description = "Test to navigate to Reviews Feed")
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 6, description = "Test to Delete Approved Response")
	public void DeleteApprovedResponse() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.DeleteApprovedResponse();
	}
}
