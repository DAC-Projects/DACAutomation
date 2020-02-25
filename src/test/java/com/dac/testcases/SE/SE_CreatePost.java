package com.dac.testcases.SE;


import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SE.SE_Post_Page;
import com.dac.main.POM_SE.SE_Report_Page;

import junit.framework.Assert;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;


public class SE_CreatePost extends BaseClass{
	Navigationpage np = null;

	@Test(enabled = true, dataProvider = "testData")
	public void createNewPost() {

		np = new Navigationpage(CurrentState.getDriver());

		//Navigate to Social Engagement Post Page

		np.navigateToSE_Post();                

		addEvidence(CurrentState.getDriver(), "Navigated to Social Posts Page", "yes");

		SE_Post_Page sp = new SE_Post_Page(CurrentState.getDriver());

		sp.clickCreateNewPostButton();

		if (sp.displayChoosePlatformHeading().getText().contains("Step 1"))
			addEvidence(CurrentState.getDriver(), "Navigated to 'Step 1 Choose Platform'", "yes");
		else addEvidence(CurrentState.getDriver(), "User is not navigated to 'Step 1 Choose Platform'", "yes"); 

		sp.clickNextButton();

		if (sp.displayChoosePageHeading().getText().contains("Step 2"))
			addEvidence(CurrentState.getDriver(), "Navigated to 'Step 2 Choose Pages/Brands'", "yes");
		else addEvidence(CurrentState.getDriver(), "User is not navigated to 'Step 2 Choose Pages/Brands'", "yes");

		sp.clickAllPageNameCheckBox();

		sp.clickNextButton();

		sp.clickPostDetailsArea();

		sp.enterData(sp.returnPostDetailsAreaWebElement(), "Test");

		sp.clickSubmitForApprovalButton();
		sp.clickOkButtonCreatePostSuccessPopup();

		/// location level user has only facebook.
		/// client level user has both facebook and gmb.
		/* single facebook connection - single page
		 * single facebook connection - multiple pages
		 * multiple facebook connection - multiple pages 
		 * 
		 */


	}

}



