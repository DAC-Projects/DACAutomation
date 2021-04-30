package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Response_Management;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Response_Management_Approver_Reject extends BaseClass {
	
	Navigationpage np;
	Response_Management data;
	String From_Date = "//*[@id='dateFrom']";
	String To_Date = "//*[@id='dateTo']";
	
	
	@Test(priority = 1, description = "Test to navigate to Reviews Feed Page")
	public void navigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	@Test(priority = 2, description = "Test to verify reponse added", dependsOnMethods = {"navigateToReviewsFeed"})
	public void verifyResponseAdded() throws Exception{
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			data.verifyresponse(ResponseSelected);
			}
	}
	
	
	@Test(priority = 3, description = "Test to navigate to Response Management Page", dependsOnMethods = {"verifyResponseAdded"})
	public void NavigateToResponseManagement() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToResponseManagement();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 4, description = "Test to change the status to pending approval", dependsOnMethods = {"NavigateToResponseManagement"})
	public void ChangeStatusToPending() throws Exception{
		data = new Response_Management(CurrentState.getDriver());
		data.selectPendingStatus();
		addEvidence(CurrentState.getDriver(), "Test to change status to pending approval", "yes");
	}
	
	@Test(priority = 5, description = "Test to Approve Response" , dependsOnMethods = {"ChangeStatusToPending"})
	public void RejectResponse() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			data.RejectResponse(ResponseSelected);	
		}
	}
	
	@Test(priority = 6, description = "Test to navigate to Reviews Feed" , dependsOnMethods = {"RejectResponse"})
	public void NavigateToReviewsFeed() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
/*	@Test(priority = 5, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider = "testData")
	public void DateFilter(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.selectCalender_FromDate(From_Date, (int) (Double.parseDouble(from_day)), from_month,
				(int) (Double.parseDouble(from_year)));
		Thread.sleep(3000);
		data.selectCalender_ToDate(To_Date, (int) (Double.parseDouble(to_day)), to_month,
				(int) (Double.parseDouble(to_year)));
		Thread.sleep(3000);
		data.clickApplyFilterBTN();
		addEvidence(CurrentState.getDriver(), "Applied Global Filters", "yes");
	}

	@SuppressWarnings("finally")
	@DataProvider
	public String[][] testData() {
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Response_Management");
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
	}*/
	
	@Test(priority = 7, description = "Test to verify Status", dependsOnMethods = {"NavigateToReviewsFeed"})
	public void VerifyStatus() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			data.verifyRejectResponse(ResponseSelected); 
			addEvidence(CurrentState.getDriver(), "Test to verify status of response", "yes");
		}
	}
	
	@Test(priority = 8, description = "Test to navigate to Response Management Page" , dependsOnMethods ={"VerifyStatus"})
	public void NavigateToResponseManagemen() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToResponseManagement();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 9, description = "Test to change status to Rejected", dependsOnMethods = {"NavigateToResponseManagemen"})
	public void ChangeStatustoReject() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.SelectRejectStatus();
		addEvidence(CurrentState.getDriver(), "Test to change status to Rejected", "yes");
	}
	
	@Test(priority = 10, description = "Test to delete Response", dependsOnMethods = {"ChangeStatustoReject"})
	public void DeleteRejectedResponse() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_Feed");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String ResponseSelected = wb.getCellValue(1, wb.seacrh_pattern("Response", 0).get(0).intValue());
			System.out.println("The Response to be added is : " +ResponseSelected);
			data.DeleteResponse(ResponseSelected);
			addEvidence(CurrentState.getDriver(), "Test to delete the response", "yes");
		}
	}

	@Test(priority = 11, description = "Test to navigate to Reviews Feed", dependsOnMethods = {"DeleteRejectedResponse"})
	public void NavigateToReviewsFee() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Reviews Feed", "yes");
	}
	
	/*@Test(priority = 11, description = "Test to verify date selected in Date filter and check with reviews table", dataProvider = "testData")
	public void DateFilter1(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.selectCalender_FromDate(From_Date, (int) (Double.parseDouble(from_day)), from_month,
				(int) (Double.parseDouble(from_year)));
		Thread.sleep(3000);
		data.selectCalender_ToDate(To_Date, (int) (Double.parseDouble(to_day)), to_month,
				(int) (Double.parseDouble(to_year)));
		Thread.sleep(3000);
		data.clickApplyFilterBTN();
		addEvidence(CurrentState.getDriver(), "Applied Global Filters", "yes");
	}*/

	
	@Test(priority = 12, description = "Test to verify Response Deleted", dependsOnMethods = {"NavigateToReviewsFee"})
	public void VerifyResponseDeleted() throws Exception {
		data = new Response_Management(CurrentState.getDriver());
		data.verifyDeletedResponse();
	}
}
