package com.dac.testcases.SA;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Reviews_Feed;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Reviews_Feed_DateFilter_Verification_Test extends BaseClass {

	Navigationpage np;
	Reviews_Feed data;
	String From_Date = "//*[@id='dateFrom']";
	String To_Date = "//*[@id='dateTo']";
	
	@Test(priority = 1, description = "Test to Navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
		/*data = new Reviews_Feed(CurrentState.getDriver());
		data.CancelWalkme();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");*/
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
	
	@Test(priority = 3, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider = "testData")
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
