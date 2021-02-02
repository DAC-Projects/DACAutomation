package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Response_Management;

import resources.BaseClass;
import resources.CurrentState;

public class Response_Management_Test extends BaseClass {

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
	
	@Test(priority = 3, description = "Test to select pending approval Status from Dropdown")
	public void SelectpendingStatus() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.selectPendingStatus();
		addEvidence(CurrentState.getDriver(), "Test to select pending approval status", "yes");
	}
	
	@Test(priority = 4, description = "Test to verify Pending approval")
	public void VerifyPendingStatusnTable() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.verifyResponseStatus();
		addEvidence(CurrentState.getDriver(), "Test to verify pending approval", "yes");
	}
	
	@Test(priority = 5, description = "Test to select Rejected Status from Dropdown")
	public void verifyRejectedStatusnTable() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.SelectRejectStatus();
		addEvidence(CurrentState.getDriver(), "Test to veify rejected status", "yes");
	}
	
	@Test(priority = 6, description = "Test to verify Rejected Status")
	public void VerifyRejectedStatusnTable() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.verifyResponseStatus();
		addEvidence(CurrentState.getDriver(), "Test to verify Rejected", "yes");
	}
}
