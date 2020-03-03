package com.dac.testcases.SE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_SE.ContentManagement_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class ContentManagement_Test extends BaseClass{

	Navigationpage np;
	ContentManagement_Page data;
	ExcelHandler wb;

	/**
	 * Test to navigate to Content Management Page
	 * @throws Exception
	 */
	@Test(priority = 1, groups = { "smoke" }, description = "Test for navigating to Content Management page")
	public void navigateToContentManagementPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSE_ContentManagement();				
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Content Management page");
		addEvidence(CurrentState.getDriver(), "Navigate to Content Management page from Dashboard", "yes");
	}

	/**
	 * Test to Enter keyword to search
	 * @throws Exception
	 */
	@Test(priority = 2, description="Test to search using keyword")
	public void SearchUnpublishedContent() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String UnpublishedKey = wb.getCellValue(1, wb.seacrh_pattern("Unpublished Search", 0).get(0).intValue());
		System.out.println("The Unpublished key to search is :" +UnpublishedKey);
		data.SearchUnpublishedUsingKeyword(UnpublishedKey);
		addEvidence(CurrentState.getDriver(), "Enter the Keyword to Search", "yes");
	}

	/**
	 * Test to search in a table for entered keyword
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void VerifySearchKeywordinUnpublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String UnpublishedKey = wb.getCellValue(1, wb.seacrh_pattern("Unpublished Search", 0).get(0).intValue());
		System.out.println("The Unpublished key to search is :" +UnpublishedKey);
		data.SearchUnpublishedKeyinTable(UnpublishedKey);
		addEvidence(CurrentState.getDriver(), "Search the keyword entered in unpublished table", "yes");
	}

	/**
	 * Test to Enter Type to search
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void SearchUnpublishedType() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String UnpublishedType = wb.getCellValue(1, wb.seacrh_pattern("Unpublished Type", 0).get(0).intValue());
		System.out.println("The Unpublished Type is :" +UnpublishedType);
		data.SearchUnpublishedUsingType(UnpublishedType);
		addEvidence(CurrentState.getDriver(), "Select Type to Search", "yes");
	}

	/**
	 * Test to verify type exists
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void VerifySearchTypeinUnpublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String UnpublishedType = wb.getCellValue(1, wb.seacrh_pattern("Unpublished Type", 0).get(0).intValue());
		System.out.println("The Unpublished Type is :" +UnpublishedType);
		data.SearchUnpublishedTypeinTable(UnpublishedType);
		addEvidence(CurrentState.getDriver(),"Verify Type exists in the table","yes");
	}

	/**
	 * Test to Enter keyword to search
	 * @throws Exception
	 */
	@Test(priority = 6, description="Test to search using keyword")
	public void SearchPublishedContent() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String UnpublishedKey = wb.getCellValue(1, wb.seacrh_pattern("Published Search", 0).get(0).intValue());
		System.out.println("The Unpublished key to search is :" +UnpublishedKey);
		data.SearchPublishedUsingKeyword(UnpublishedKey);
		addEvidence(CurrentState.getDriver(), "Enter the Keyword to Search", "yes");
	}

	/**
	 * Test to search in a table for entered keyword
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void VerifySearchKeywordinPublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String UnpublishedKey = wb.getCellValue(1, wb.seacrh_pattern("Published Search", 0).get(0).intValue());
		System.out.println("The Unpublished key to search is :" +UnpublishedKey);
		data.SearchPublishedKeyinTable(UnpublishedKey);
		addEvidence(CurrentState.getDriver(), "Search the keyword entered in unpublished table", "yes");
	}

	/**
	 * Test to Enter Type to search
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void SearchPublishedType() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String PublishedType = wb.getCellValue(1, wb.seacrh_pattern("Published Type", 0).get(0).intValue());
		System.out.println("The Published Type is :" +PublishedType);
		data.SearchPublishedUsingType(PublishedType);
		addEvidence(CurrentState.getDriver(), "Select Type to Search", "yes");
	}

	/**
	 * Test to verify type exists
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void VerifySearchTypeinPublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE"); wb.deleteEmptyRows();
		String PublishedType = wb.getCellValue(1, wb.seacrh_pattern("Published Type", 0).get(0).intValue());
		System.out.println("The Published Type is :" +PublishedType);
		data.SearchPublishedTypeinTable(PublishedType);
		addEvidence(CurrentState.getDriver(),"Verify Type exists in the table","yes");
	}
	
	

}
