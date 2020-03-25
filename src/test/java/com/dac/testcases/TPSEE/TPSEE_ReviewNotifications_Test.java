package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_ReviewNotifications_Page;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_ReviewNotifications_Test extends BaseClass {

	Navigationpage np;
	TPSEE_ReviewNotifications_Page rn;
	String xcelInputData[][];

	// Navigation Test
	@Test(priority = 1, groups = { "smoke" }, description = "TC: Navigating to Review Notifications")
	public void navigateReviewNotifications() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToReviewNotifications();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Review Notifications Page");
		addEvidence(CurrentState.getDriver(), "Navigate to Review Notifications from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}

	@Test(priority = 2)
	public void verifyText() throws Exception {
		rn = new TPSEE_ReviewNotifications_Page(CurrentState.getDriver());
		rn.VerifyTitleText("Review Notifications",
				"Create email notifications to receive emails based on criteria that is important to you.");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
	}

	// Test for Create Email Notification
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateReviewNotifications" }, groups = {
			"smoke" }, description = "TC: Creating New Review Notification")
	public void createEmailNotification() throws Exception {

		rn = new TPSEE_ReviewNotifications_Page(CurrentState.getDriver());
		xcelInputData = rn.readXcelInput();

		rn.createAndVerifyEmailNotification(xcelInputData, 1);

		CurrentState.getLogger().log(Status.PASS, "Notification Created");
		addEvidence(CurrentState.getDriver(), "New Review Notification Created", "yes");
	}

	// Test for Edit Email Notification
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "createEmailNotification" }, groups = {
			"smoke" }, description = "TC: Updating Review Notification")
	public void editEmailNotification() throws Exception {
		rn = new TPSEE_ReviewNotifications_Page(CurrentState.getDriver());

		rn.editAndVerifyNotification(xcelInputData, 1, 2);

		CurrentState.getLogger().log(Status.PASS, "Notification Updated");
		addEvidence(CurrentState.getDriver(), "Review Notification Updated", "yes");
	}

	// Test for Edit Email Notification
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "editEmailNotification" }, groups = {
			"smoke" }, description = "TC: Deleting Notification from Review Notification")
	public void deleteEmailNotification() throws Exception {
		rn = new TPSEE_ReviewNotifications_Page(CurrentState.getDriver());
		rn.deleteEmailNotification(xcelInputData, 2);
		CurrentState.getLogger().log(Status.PASS, "Notification deleted");
		addEvidence(CurrentState.getDriver(), "Review Notification deleted", "yes");
	}

	// Test for Custom All Reviews Email Notification
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "deleteEmailNotification" }, groups = {
			"smoke" }, description = "TC: All Reviews Notification from Review Notification")
	public void customAllReviewsNotification() throws Exception {
		rn = new TPSEE_ReviewNotifications_Page(CurrentState.getDriver());

		rn.createCustomNotification(xcelInputData, "All Reviews");
		rn.verifyEmailNotification(xcelInputData, 5);
		CurrentState.getLogger().log(Status.PASS, "Notification Created");
		addEvidence(CurrentState.getDriver(), "All Reviews Notification created and verified", "yes");
	}

	// Test for Less-than 3 stars Reviews Email Notification
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "customAllReviewsNotification" }, groups = {
			"smoke" }, description = "TC: Less-than 3 stars Reviews Notification from Review Notification")
	public void customLessThan3ReviewsNotification() throws Exception {
		rn = new TPSEE_ReviewNotifications_Page(CurrentState.getDriver());

		rn.createCustomNotification(xcelInputData, "Less-than 3 stars");
		rn.verifyEmailNotification(xcelInputData, 6);
		CurrentState.getLogger().log(Status.PASS, "Notification Created");
		addEvidence(CurrentState.getDriver(), "Less-than 3 stars Review Notification created and verified", "yes");
	}
}