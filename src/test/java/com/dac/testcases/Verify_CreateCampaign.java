package com.dac.testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.testng.annotations.Test;

import com.dac.main.CampaignLivePreviewPage;
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

		/*String docNameNPath="C:\\Users\\wasim\\git\\DACAuto\\data\\Test Evidence.docx";*/
		
//		CreateEvidence cr=new CreateEvidence();

		Navigationpage NP=new Navigationpage(driver);
		NP.clickCampaigns();
		
		CampaignsPage CP=new CampaignsPage(driver);
		CP.search_ScheduledCampaign("Test MLC Spanish LA 1");
		
		CP.clickReScheduleBTN();
		CP.clickLivePreviewBTN();
	}
}
