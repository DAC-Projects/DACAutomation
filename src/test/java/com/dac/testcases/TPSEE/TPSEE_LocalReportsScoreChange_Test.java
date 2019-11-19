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

	
	//Navigation Test
		@Test(groups = { "smoke" }, description = "TC: Navigating to Local Reports Score Change Notification")
		public void navigateToLocalReportsScoreChange() throws Exception {
			np = new Navigationpage(CurrentState.getDriver());
			np.navigateToLocalReportsScoreChange();
			CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Local Reports Score Change Page");
			addEvidence(CurrentState.getDriver(), "Navigate to Local Reports Score Change from Dashboard", "yes");

			// Assert.assertFalse( "sample error", true);
		}
		
		//Test for export 7 days data
				@SuppressWarnings("unchecked")
				@Test(dependsOnMethods = { "navigateToLocalReportsScoreChange"}, groups = {
								"smoke" }, description = "TC: Naviated to Local Report Score Change")
					public void createEmailNotification() throws Exception {
					notification = new TPSEE_LocalReportsScoreChange_Page(CurrentState.getDriver());
					
					notification.LocalReportScoreChangeNotifiction("Notification3","spillai@dacgroup.com","Visibility","below","50");
					CurrentState.getLogger().log(Status.PASS, "Notification Created");
					addEvidence(CurrentState.getDriver(), "New Local Report Score Chnage Notification Created", "yes");
				}
}
