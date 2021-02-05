package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Response_Management;

import resources.BaseClass;
import resources.CurrentState;

public class Response_Management_Approver_Reject extends BaseClass {
	
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
	
	@Test(priority = 3, description = "Test to Approve Response")
	public void RejectResponse() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.RejectResponse();		
	}
	
	@Test(priority = 4, description = "Test to navigate to Reviews Feed")
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 5, description = "Test to verify Status")
	public void VerifyStatus() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.verifyRejectResponse(); 
	}
	
	@Test(priority = 6, description = "Test to navigate to Response Management Page")
	public void NavigateToResponseManagemen() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToResponseManagement();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 7, description = "Test to change status to Rejected")
	public void ChangeStatustoReject() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.SelectRejectStatus();
		addEvidence(CurrentState.getDriver(), "Test to change status to Rejected", "yes");
	}
	
	@Test(priority = 8, description = "Test to delete Response")
	public void DeleteRejectedResponse() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.DeleteResponse();
		addEvidence(CurrentState.getDriver(), "Test to delete the response", "yes");
	}

	@Test(priority = 9, description = "Test to navigate to Reviews Feed")
	public void NavigateToReviewsFee() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 10, description = "Test to verify Response Deleted")
	public void VerifyResponseDeleted() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.verifyDeletedResponse();
	}
}
