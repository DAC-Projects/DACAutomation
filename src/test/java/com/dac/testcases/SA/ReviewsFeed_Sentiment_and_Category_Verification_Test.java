package com.dac.testcases.SA;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class ReviewsFeed_Sentiment_and_Category_Verification_Test extends BaseClass {

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
	
	@Test(priority = 3, description = "Test to verify sentiment")
	public void verifySentiment() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.applySentiment();
	}
	
	@Test(priority = 4, description = "Test to verify sentiment category")
	public void verifysentimentcategory() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.SelectSentimentcategory();
	}
	
	@Test(priority = 5, description = "Test to verify sentiment combination")
	public void sentimentcombination() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.selectsentandcatandverifyreview();
	}
	
	
}
