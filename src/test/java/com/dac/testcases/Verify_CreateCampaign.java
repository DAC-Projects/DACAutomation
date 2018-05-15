package com.dac.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.testng.annotations.Test;

import com.dac.main.CampaignsPage;
import com.dac.main.CreateNewCampaignPage;
import com.dac.main.Navigationpage;

import autoitx4java.AutoItX;
import resources.BaseTest;
import resources.CreateEvidence;
import resources.Utilities;

public class Verify_CreateCampaign extends BaseTest{
	
	@Test
	public void createCamp_Test() throws Exception {

		String docNameNPath="C:\\Users\\wasim\\git\\DACAuto\\data\\Test Evidence.docx";
		
		CreateEvidence cr=new CreateEvidence();

		Navigationpage NP=new Navigationpage(driver);
		NP.clickCampaigns();
		
		CampaignsPage CP=new CampaignsPage(driver);
		CP.click_CreateCampaignBTN();
		//Thread.sleep(5000);
	
		CreateNewCampaignPage newCampaign=new CreateNewCampaignPage(driver);
		
		newCampaign.selectCampType(1);
		
		newCampaign.selectCampLang(1);
		
		newCampaign.setCampaignName("Test English Campaign8");
		
		//newCampaign.setCampaignBrandName("wasim brand");
		
		newCampaign.setCampDescr("Camp Description");
		
		newCampaign.setSenderName("wasim");
	
		newCampaign.uploadLogo("logo", ".jpeg");		
		Thread.sleep(5000);
	
		/*newCampaign.uploadCampEmailTemplate("./EmailTemplate.xlsx");
		
		newCampaign.setCampSubject("subject campaign name");
		
		newCampaign.setCampBanner("introduction banner");
		
		newCampaign.setCampBodyCopy("Hello wasim");
		
		newCampaign.setCampSignature("thank you camp");
		
		newCampaign.setContactInfo("#8/20", "Ananthapur", "Behind Post Office", "515774", "8179615605");
		
		newCampaign.setScheduledStartDate("05", "10", "2018");
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		
		newCampaign.setCampaignTime("02:00 AM");*/
		
		/*newCampaign.clickSaveDraft();
		
		newCampaign.clickViewAllCampaignBTN();*/
		
		
	}
}
