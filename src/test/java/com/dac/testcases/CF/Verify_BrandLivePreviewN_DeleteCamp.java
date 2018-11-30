package com.dac.testcases.CF;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.BaseTest_CF;
import com.dac.main.POM_CF.CampaignLivePreviewPage;
import com.dac.main.POM_CF.CampaignsPage;

import resources.CurrentState;

public class Verify_BrandLivePreviewN_DeleteCamp extends BaseTest_CF{
	
	@Test(priority = 0,dependsOnMethods= {"com.dac.testcases.CF.Verify_CreateBrandCampaign.createBrandCamp_Test"})
	public void livePreviewBrandTest() throws Exception {
		
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.clickCampaigns();
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		
		cp.search_ScheduledCampaign(campName);
		addEvidence(CurrentState.getDriver(), "Verifying that scheduled campaign should display in scheduled section", "yes");
		CurrentState.getLogger().log(Status.INFO, "Checking the Created campaign displaying in Scheduled section");
		
		cp.clickLivePreviewBTN();
		addEvidence(CurrentState.getDriver(), "Verifying that User able to navigate to Live Preview's Email view and able to check the email view section", "yes");
		CurrentState.getLogger().log(Status.INFO, "Checking the Email view section of Scheduled campaign Live Preview");
				
		CampaignLivePreviewPage LP=new CampaignLivePreviewPage(CurrentState.getDriver());
		
		LP.verifyemailViewData();
		
		LP.clickfeedBackBTN();
		addEvidence(CurrentState.getDriver(), "Verifying that User can able to navigate to Live preview's feedback and able to check the feedback section", "yes");
		CurrentState.getLogger().log(Status.INFO, "Checking the Feed Back section of Scheduled campaign Live Preview");
	
		LP.clickthankYouBTN();
		addEvidence(CurrentState.getDriver(), "Verifying that User can able to navigate to Live preview's Thank you page and able to check the Thank you message, logo(if any) and etc.", "yes");
		CurrentState.getLogger().log(Status.INFO, "Checking the Thank you section of Scheduled campaign Live Preview");
		
		LP.clickClosePreviewBTN();
		
		np.waitUntilLoad(CurrentState.getDriver());
		
	}
	
	@Test(enabled = true)
	public void deleteBrandCampTest() throws Exception {
		
		CampaignsPage cp=new CampaignsPage(CurrentState.getDriver());
		
		cp.search_ScheduledCampaign(campName);
		
		cp.clickDeleteBTN();
		addEvidence(CurrentState.getDriver(), "By clicking delete button of Scheduled campaign and checking the delete pop up displying or not", "yes");
		CurrentState.getLogger().log(Status.INFO, "Clicking delete button of Scheduled campaign and checking the delete pop up displying or not");
		
		cp.clickDeleteAcceptBTN();
		addEvidence(CurrentState.getDriver(), "Verifying that confirmation to delete scheduled campign is displaying or not", "yes");
		CurrentState.getLogger().log(Status.INFO, "clicking on confirmation to delete Sceduled campaign");
		
		Navigationpage np=new Navigationpage(CurrentState.getDriver());

		np.waitUntilLoad(CurrentState.getDriver());
	}
	
}
