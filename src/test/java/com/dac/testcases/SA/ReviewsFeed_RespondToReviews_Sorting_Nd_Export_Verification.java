package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed;
import com.dac.main.POM_SA.Reviews_Feed_Response_To_Reviews;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class ReviewsFeed_RespondToReviews_Sorting_Nd_Export_Verification extends BaseClass {
	
	Navigationpage np;
	Reviews_Feed_Response_To_Reviews data;
	
	@Test(priority = 1, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		//data = new Reviews_Feed(CurrentState.getDriver());
		//data.CancelWalkme();
		//addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 2, description = "To Click on Respond to Reviews")
	public void NavigateToResponseToReviewsTab() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.ClickResToReviews();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response to Reviews Tab", "yes");
	}
	
	@Test(priority = 3, description = "verify sorting of table by latest date")
	public void verifyLatestDatesortbytable() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.VerifySortByNewest();
	}

	@Test(priority = 4, description = "verify sorting of table by oldest date")
	public void verifyOldestDatesortbytable() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.VerifySortByOldest();
	}

	@Test(priority = 5, description = "verify sorting of table by source")
	public void verifySourcesortby() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.VerifySortSource();
	}

	@Test(priority = 6, description = "verify sorting of table by star rating")
	public void verifyhigheststarratingsort() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.VerifyHighStar();
	}

	@Test(priority = 7, description = "verify sorting of table by star rating")
	public void verifyloweststarratingsort() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.VerifyLowStar();
	}

	@Test(priority = 8, description = "Test to verify Ref Code")
	public void verifyRefCodeSort() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.verifySortReferenceCode();
	}

	@Test(priority = 9, description = "Test to verify Location Name sort")
	public void verifyLocationNameSort() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.verifySortLocationName();
	}
	
	@Test(priority = 10, description = "Test to Export Location Data")
	public void ExportLocation() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.LocationExport();
		addEvidence(CurrentState.getDriver(), "Test to export", "yes");
	}

	@Test(priority = 11, description = "Test to Compare UI and XL Data")
	public void compareUIandXLData() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Filters");
		wb.deleteEmptyRows();
		String Text = null;
		for (int i = 1; i <= wb.getRowCount(); i++) {
			if (i > 1)
				CurrentState.getDriver().navigate().refresh();
			data.waitUntilLoad(CurrentState.getDriver());
			Text = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
		}
		data.CompareReviewTableDatawithExport(Text);
		addEvidence(CurrentState.getDriver(), "Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
	}
	
	@Test(priority = 12, description = "Test to verify top button")
	public void verifymultisentiments() throws Exception {
		data = new Reviews_Feed_Response_To_Reviews(CurrentState.getDriver());
		data.TopButton();
	}

}
