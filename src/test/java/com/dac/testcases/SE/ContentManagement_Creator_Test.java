package com.dac.testcases.SE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_SE.ContentManagement_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class ContentManagement_Creator_Test extends BaseClass {

	Navigationpage np;
	ContentManagement_Page data;
	ExcelHandler wb;

	/**
	 * Test to navigate to Content Management Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test for navigating to Content Management page")
	public void navigateToContentManagementPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSE_ContentManagement();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Content Management page");
		addEvidence(CurrentState.getDriver(), "Navigate to Content Management page from Dashboard", "yes");
	}

		@Test(priority = 2)
	public void verifyText() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		data.VerifyTitleText();
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
	}

	/**
	 * Test to search in a table for entered keyword
	 * 
	 * @throws Exception
	 */

	@Test(priority = 3)
	public void VerifySearchKeywordinUnpublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String UnpublishedKey = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Search", 0).get(0).intValue());
			System.out.println("The Unpublished key to search is :" + UnpublishedKey);
			if (!UnpublishedKey.equals("null")) {
				data.SearchUnpublishedUsingKeyword(UnpublishedKey);
				addEvidence(CurrentState.getDriver(), "Enter the Keyword to Search", "yes");
				data.SearchUnpublishedKeyinTable(UnpublishedKey);
				addEvidence(CurrentState.getDriver(), "Search the keyword entered in unpublished table", "yes");
				CurrentState.getDriver().navigate().refresh();
				addEvidence(CurrentState.getDriver(), "Value set to default", "yes");
			}
		}
	}

	/**
	 * Test to verify type exists
	 * 
	 * @throws Exception
	 */

	@Test(priority = 4)
	public void VerifySearchTypeinUnpublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String UnpublishedType = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Type", 0).get(0).intValue());
			System.out.println("The Unpublished Type is :" + UnpublishedType);
			if (!UnpublishedType.equals("null")) {
				data.SearchUnpublishedUsingType(UnpublishedType);
				addEvidence(CurrentState.getDriver(), "Type Selected", "yes");
				data.SearchUnpublishedTypeinTable(UnpublishedType);
				addEvidence(CurrentState.getDriver(), "Verify Type exists in the table", "yes");
				CurrentState.getDriver().navigate().refresh();
				addEvidence(CurrentState.getDriver(), "Value set to default", "yes");
			}
		}
	}

	/**
	 * Test to search in a table for entered keyword
	 * 
	 * @throws Exception
	 */

	@Test(priority = 5)
	public void VerifySearchKeywordinPublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String PublishedKey = wb.getCellValue(row, wb.seacrh_pattern("Published Search", 0).get(0).intValue());
			System.out.println("The Published key to search is :" + PublishedKey);
			if (!PublishedKey.equals("null")) {
				data.SearchPublishedUsingKeyword(PublishedKey);
				addEvidence(CurrentState.getDriver(), "Enter the Keyword to Search", "yes");
				data.SearchPublishedKeyinTable(PublishedKey);
				addEvidence(CurrentState.getDriver(), "Search the keyword entered in unpublished table", "yes");
				CurrentState.getDriver().navigate().refresh();
				addEvidence(CurrentState.getDriver(), "Value set to default", "yes");
			}
		}
	}

	/**
	 * Test to verify type exists
	 * 
	 * @throws Exception
	 */

	@Test(priority = 6)
	public void VerifySearchTypeinPublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String PublishedType = wb.getCellValue(row, wb.seacrh_pattern("Published Type", 0).get(0).intValue());
			System.out.println("The Published key to search is :" + PublishedType);
			if (!PublishedType.equals("null")) {
				data.SearchPublishedUsingType(PublishedType);
				addEvidence(CurrentState.getDriver(), "Enter the Keyword to Search", "yes");
				data.SearchPublishedTypeinTable(PublishedType);
				addEvidence(CurrentState.getDriver(), "Search the keyword entered in published table", "yes");
				CurrentState.getDriver().navigate().refresh();
				addEvidence(CurrentState.getDriver(), "Value set to default", "yes");
			}
		}
	}

	/**
	 * @throws Exception
	 * 
	 */

	@Test(priority = 7)
	public void UnpublishedTypeTxtSearch() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String UnpublishedType = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Type", 0).get(0).intValue());
			System.out.println("The Unpublished key to search is :" + UnpublishedType);
			String UnpublishedKey = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Search", 0).get(0).intValue());
			System.out.println("The Unpublished key to search is :" + UnpublishedKey);
			if (!UnpublishedKey.equalsIgnoreCase("null") && !UnpublishedType.equalsIgnoreCase("null")) {
				data.UnpublishedTXTTypeSearch(UnpublishedKey, UnpublishedType);
				addEvidence(CurrentState.getDriver(), "To Enter with Key and Type", "yes");
				data.SearchUnpublishedKeynTypeinTable(UnpublishedKey, UnpublishedType);
				addEvidence(CurrentState.getDriver(), "Search Entered Keyword and Type in Table", "yes");
				CurrentState.getDriver().navigate().refresh();
				addEvidence(CurrentState.getDriver(), "To Set the default values", "yes");
			}
		}
	}

	/**
	 * @throws Exception
	 * 
	 */

	@Test(priority = 8)
	public void PublishedTypeTxtSearch() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String PublishedType = wb.getCellValue(row, wb.seacrh_pattern("Published Type", 0).get(0).intValue());
			System.out.println("The Published key to search is :" + PublishedType);
			String PublishedKey = wb.getCellValue(row, wb.seacrh_pattern("Published Search", 0).get(0).intValue());
			System.out.println("The Published key to search is :" + PublishedKey);
			if (!PublishedKey.equalsIgnoreCase("null") && !PublishedType.equalsIgnoreCase("null")) {
				data.PublishedTXTTypeSearch(PublishedKey, PublishedType);
				addEvidence(CurrentState.getDriver(), "To Enter with Key and Type", "yes");
				data.SearchPublishedKeynTypeinTable(PublishedKey, PublishedType);
				addEvidence(CurrentState.getDriver(), "Search Entered Keyword and Type in Table", "yes");
				CurrentState.getDriver().navigate().refresh();
			}
		}
	}

	/**
	 * Test to search data with status
	 * 
	 * @throws Exception
	 */

	@Test(priority = 9)
	public void searchwithStatus() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String Status = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Status", 0).get(0).intValue());
			System.out.println("The status to search is :" + Status);
			if (!Status.equalsIgnoreCase("null")) {
				data.SearchStatus(Status);
				addEvidence(CurrentState.getDriver(), "To set the status in Unpublished Conetent", "yes");
				data.VerifyCreatorstatusinTable(Status);
				addEvidence(CurrentState.getDriver(), "To verify data in Unpublished Conetent", "yes");
				CurrentState.getDriver().navigate().refresh();
			}
		}
	}

	/**
	 * Test to Delete Data using Type in Published Table
	 * 
	 * @throws Exception
	 */

	@Test(priority = 10)
	public void DeleteDatausingTypePublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String Type = wb.getCellValue(row, wb.seacrh_pattern("Published Type", 0).get(0).intValue());
			System.out.println("The Type selected is :" + Type);
			if (!Type.equalsIgnoreCase("null") && !Type.equalsIgnoreCase("All") && !Type.equalsIgnoreCase("Posts")) {
				data.SearchPublishedUsingType(Type);
				addEvidence(CurrentState.getDriver(), "To Select the Type", "yes");
				data.DeleteDataPublishedTableusingType(Type);
				addEvidence(CurrentState.getDriver(), "Delete from Published Table", "yes");
				CurrentState.getDriver().navigate().refresh();
			}
		}
	}

	/**
	 * To delete data from Unpublished Table using Type
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void DeleteDatausingTypeUnPublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String Type = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Type", 0).get(0).intValue());
			System.out.println("The Type selected is :" + Type);
			data.DeleteDataUnPublishedTableusingType(Type);
			addEvidence(CurrentState.getDriver(), "Delete from Published Table", "yes");
			CurrentState.getDriver().navigate().refresh();
		}
	}

	/**
	 * To delete data from Unpublished Table using Type
	 * 
	 * @throws Exception
	 */
	@Test(priority = 12)
	public void DeleteDatausingStatusUnPublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String Status = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Status", 0).get(0).intValue());
			System.out.println("The Type selected is :" + Status);
			data.DeleteDataUnPublishedTableusingStatus(Status);
			addEvidence(CurrentState.getDriver(), "Delete from Published Table", "yes");
			CurrentState.getDriver().navigate().refresh();
		}
	}

	/**
	 * To delete data from Unpublished Table using Type
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void DeleteDatausingStatusnTypeUnPublishedTable() throws Exception {
		data = new ContentManagement_Page(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Social_Engagement.xlsx", "SE");
		wb.deleteEmptyRows();
		int row;
		for (row = 1; row <= wb.getRowCount(); row++) {
			String Type = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Type", 0).get(0).intValue());
			System.out.println("The Type Selected is :" + Type);
			String Status = wb.getCellValue(row, wb.seacrh_pattern("Unpublished Status", 0).get(0).intValue());
			System.out.println("The Status selected is :" + Status);
			data.DeleteDataUnPublishedTableusingTypenStatus(Type, Status);
			addEvidence(CurrentState.getDriver(), "Delete from Published Table", "yes");
			CurrentState.getDriver().navigate().refresh();
		}
	}

}
