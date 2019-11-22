package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_LocalReportsScoreChange_Page;
import com.dac.main.POM_TPSEE.TPSEE_ReviewStream_Page;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_LocalReportsScoreChange_Test extends BaseClass {
	Navigationpage np;
	TPSEE_LocalReportsScoreChange_Page notification ;
	String configuration[][];

	
	//Navigation Test
		@Test(groups = { "smoke" }, description = "TC: Navigating to Local Reports Score Change Notification")
		public void navigateToLocalReportsScoreChange() throws Exception {
			np = new Navigationpage(CurrentState.getDriver());
			np.navigateToLocalReportsScoreChange();
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Local Reports Score Change Page");
			addEvidence(CurrentState.getDriver(), "Navigate to Local Reports Score Change from Dashboard", "yes");

		}
		
		//Test for Create Email Notification
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = { "navigateToLocalReportsScoreChange"}, groups = {
								"smoke" }, description = "TC: Creating New Notification to Local Report Score Change")
		public void createEmailNotification() throws Exception {
			
					notification = new TPSEE_LocalReportsScoreChange_Page(CurrentState.getDriver());
					configuration=notification.readConfiguration();
					
					notification.LocalReportScoreChangeNotifiction(configuration[1][0],configuration[1][1],configuration[1][2],configuration[1][3],configuration[1][4]);
					notification.verifyEmailNotification(configuration, 1);
					CurrentState.getLogger().log(Status.PASS, "Notification Created");
					addEvidence(CurrentState.getDriver(), "New Local Report Score Change Notification Created", "yes");
				}
				
		//Test for Edit Email Notification
		@SuppressWarnings("unchecked")
		@Test(dependsOnMethods = { "createEmailNotification"}, groups = {
								"smoke" }, description = "TC: Updating Notification to Local Report Score Change")
		public void editEmailNotification() throws Exception {
					notification = new TPSEE_LocalReportsScoreChange_Page(CurrentState.getDriver());
					notification.editEmailNotification(configuration);
					notification.verifyEmailNotification(configuration, 2);
					CurrentState.getLogger().log(Status.PASS, "Notification Updated");
					addEvidence(CurrentState.getDriver(), "Local Report Score Change Notification Updated", "yes");
				}
		
		
		//Test for Edit Email Notification
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = { "editEmailNotification"}, groups = {
										"smoke" }, description = "TC: Deleting Notification to Local Report Score Change")
				public void deleteEmailNotification() throws Exception {
							notification = new TPSEE_LocalReportsScoreChange_Page(CurrentState.getDriver());
							notification.deleteEmailNotification();
							CurrentState.getLogger().log(Status.PASS, "Notification deleted");
							addEvidence(CurrentState.getDriver(), "Local Report Score Change Notification deleted", "yes");
						}
}
