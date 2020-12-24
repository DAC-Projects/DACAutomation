package com.dac.testcases.SA;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class ReviewsFeed_Test extends BaseClass {

	
	Navigationpage np;
	Reviews_Feed data;
	String[] VendorList;
	String Vendor;
	SoftAssert soft = new SoftAssert();

	String From_Date = "//*[@id='dateFrom']";
	String To_Date = "//*[@id='dateTo']";

	@Test(priority = 1, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		data = new Reviews_Feed(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}

	@Test(priority = 2, description = "Test to verify active state of the report")
	public void verifyreportactivestate() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.Review_Feed_Highlight();
		addEvidence(CurrentState.getDriver(), "Test to verify active state of the report", "yes");
	}

	@Test(priority = 3, description = "Test to verify Title and Title Text")
	public void VerifyTitleText() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifyTitle("Review Feed",
				"The Review Feed lists all reviews that have been collected across the sites that are being monitored.");
		addEvidence(CurrentState.getDriver(), "Test to verify title and title text", "yes");
	}

	
	@Parameters({ "Filter" })
	@Test(priority = 4, description = "Test to apply Global Filter")
	
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
	
	

	@Test(priority = 5, description = "Test to Export Location Data")
	public void ExportLocation() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.LocationExport();
		addEvidence(CurrentState.getDriver(), "Test to export", "yes");
	}

	@Test(priority = 6, description = "Test to Compare UI and XL Data")
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

	@Test(priority = 7, description = "Test to compare review count and entries in table")
	public void compareReviewCountandNumberofEntries() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifyReviewCount();
	}

	@Test(priority = 8, description = "Test to select source")
	public void compareSourcewithReviewsTable() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.SelectSource();
	}

	@Test(priority = 9, description = "Test to enter and verify tag")
	public void comparetag() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifyTag();
	}

	@Test(priority = 10, description = "Test to verify sentiment")
	public void verifySentiment() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.applySentiment();
	}

	@Test(priority = 11, description = "verify sorting of table by latest date")
	public void verifyLatestDatesortbytable() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifySortByNewest();
	}

	@Test(priority = 12, description = "verify sorting of table by oldest date")
	public void verifyOldestDatesortbytable() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifySortByOldest();
	}

	@Test(priority = 13, description = "verify sorting of table by source")
	public void verifySourcesortby() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifySortSource();
	}

	@Test(priority = 14, description = "verify sorting of table by star rating")
	public void verifyhigheststarratingsort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifyHighStar();
	}

	@Test(priority = 15, description = "verify sorting of table by star rating")
	public void verifyloweststarratingsort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.VerifyLowStar();
	}

	@Test(priority = 16, description = "Test to verify Ref Code")
	public void verifyRefCodeSort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifySortReferenceCode();
	}

	@Test(priority = 17, description = "Test to verify Location Name sort")
	public void verifyLocationNameSort() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.verifySortLocationName();
	}

	@Test(priority = 18, description = "Test to verify keyword")
	public void verifyKeywordentered() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.KeywordSearch();
	}

	@Test(priority = 19, description = "Test to verify response type")
	public void verifyResponseType() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.SelectResponse();
	}

	@Test(priority = 20, description = "Test to verify content filter")
	public void verifyContentFilter() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.SelectContent();
	}

	@Test(priority = 21, description = "Test to verify rating filter")
	public void verifyratingfilter() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.SelectRating();
	}

	@Test(priority = 22, description = "Test to verify sentiment category")
	public void verifysentimentcategory() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.SelectSentimentcategory();
	}

	@Test(priority = 23, description = "Test to verify top button")
	public void verifymultisentiments() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.TopButton();
	}

	@Test(priority = 24, description = "Test to verify sentiment combination")
	public void sentimentcombination() throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.selectsentandcatandverifyreview();
	}

	@Test(priority = 25, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider = "testData")
	public void DateFilter(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		data = new Reviews_Feed(CurrentState.getDriver());
		data.selectCalender_FromDate(From_Date, (int) (Double.parseDouble(from_day)), from_month,
				(int) (Double.parseDouble(from_year)));
		Thread.sleep(3000);
		data.selectCalender_ToDate(To_Date, (int) (Double.parseDouble(to_day)), to_month,
				(int) (Double.parseDouble(to_year)));
		Thread.sleep(3000);
		data.clickApplyFilterBTN();
		addEvidence(CurrentState.getDriver(), "Applied Global Filters", "yes");
		data.verifyDateSelected();
	}

	@SuppressWarnings("finally")
	@DataProvider
	public String[][] testData() {
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Zoom");
			wb.deleteEmptyRows();
			int rowCount = wb.getRowCount();

			System.out.println("rowCount : " + rowCount);
			data = new String[rowCount - 1][6];
			data1 = new String[rowCount - 1][6];
			int row = 0;
			for (int i = 2; i <= rowCount; i++) {
				int colCount = wb.getColCount(i);
				for (int j = 0; j < colCount; j++) {
					if (j > 0) {
						if (((wb.getCellValue(i, j).trim()).equalsIgnoreCase("null"))
								| ((wb.getCellValue(i, j).trim()).length() == 0)) {
							data1[row][j] = "null";
						} else
							data1[row][j] = wb.getCellValue(i, j).trim();
					} else
						data1[row][j] = wb.getCellValue(i, j).trim();
				}
				row++;
			}
			data[0][0] = wb.getCellValue(2, 0); // from day
			data[0][1] = wb.getCellValue(2, 1); // from month
			data[0][2] = wb.getCellValue(2, 2); // from year
			data[0][3] = wb.getCellValue(2, 3); // to day
			data[0][4] = wb.getCellValue(2, 4); // to month
			data[0][5] = wb.getCellValue(2, 5); // to year
			System.out.println("Arrays.deepToString(data) : " + Arrays.deepToString(data));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return data;
		}
	}
}
