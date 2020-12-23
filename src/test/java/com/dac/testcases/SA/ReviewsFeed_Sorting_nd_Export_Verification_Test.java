package com.dac.testcases.SA;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class ReviewsFeed_Sorting_nd_Export_Verification_Test extends BaseClass {

	Navigationpage np;
	Reviews_Feed data;
	
	@Test(priority = 1, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		data = new Reviews_Feed(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Parameters({ "Filter" })
	@Test(priority = 2, description = "Test to apply Global Filter")
	
	public void selectFilters(int Filter) throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Filter");
		data.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(Filter, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String CountryCode = wb.getCellValue(Filter, wb.seacrh_pattern("Country", 0).get(0).intValue());
		String State = wb.getCellValue(Filter, wb.seacrh_pattern("State", 0).get(0).intValue());
		String City = wb.getCellValue(Filter, wb.seacrh_pattern("City", 0).get(0).intValue());
		String Location = wb.getCellValue(Filter, wb.seacrh_pattern("Location", 0).get(0).intValue());
		data.applyGlobalFilter(Group, CountryCode, State, City, Location);
		data.clickApplyFilterBTN();
		addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode + ", " + State
				+ ", " + City + ", " + Location + "", "yes");
	}
	
	@Test(priority = 3, description = "verify sorting of table by latest date")
	public void verifyLatestDatesortbytable() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifySortByNewest();
	}

	@Test(priority = 4, description = "verify sorting of table by oldest date")
	public void verifyOldestDatesortbytable() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifySortByOldest();
	}

	@Test(priority = 5, description = "verify sorting of table by source")
	public void verifySourcesortby() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifySortSource();
	}

	@Test(priority = 6, description = "verify sorting of table by star rating")
	public void verifyhigheststarratingsort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifyHighStar();
	}

	@Test(priority = 7, description = "verify sorting of table by star rating")
	public void verifyloweststarratingsort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifyLowStar();
	}

	@Test(priority = 8, description = "Test to verify Ref Code")
	public void verifyRefCodeSort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifySortReferenceCode();
	}

	@Test(priority = 9, description = "Test to verify Location Name sort")
	public void verifyLocationNameSort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifySortLocationName();
	}
	
	@Test(priority = 10, description = "Test to Export Location Data")
	public void ExportLocation() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.LocationExport();
		addEvidence(CurrentState.getDriver(), "Test to export", "yes");
	}

	@Test(priority = 11, description = "Test to Compare UI and XL Data")
	public void compareUIandXLData() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
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
		data = new Reviews_Feed(CurrentState.getDriver());
		data.TopButton();
	}
}
