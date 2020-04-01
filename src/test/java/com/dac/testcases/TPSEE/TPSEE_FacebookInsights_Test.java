package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_FacebookInsights_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_FacebookInsights_Test extends BaseClass {

	Navigationpage np;
	TPSEE_FacebookInsights_Page data;

	/**
	 * Test to navigate to Facebook Insights Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void navigateToFaceBookInsights() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToFacebookInsights();
		CurrentState.getLogger().log(Status.PASS, "Navigated Successfully to Facebook Page");
		addEvidence(CurrentState.getDriver(), "Navigation to Facebook Page", "yes");
	}

	/**
	 * Test to verify Title and Title Text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void verifyTitleText() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		data.VerifyFacebookTitleText("Facebook Insights",
				"The below report outlines key performance indicators on Facebook for your locations such as page impressions, post engagements, actions, check-in’s and fans. This data is updated daily.");
		addEvidence(CurrentState.getDriver(), "To Verify Title and Title Text", "yes");
	}

	/**
	 * Test to Apply Filters
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3, dependsOnMethods = { "verifyTitleText" })
	public void verifyFilteringReportsAccuracy() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_Accuracy_Page s = new TPSEE_Accuracy_Page(CurrentState.getDriver());
			for (int i = 1; i <= wb.getRowCount(); i++) {
				System.out.println("*******************  Scenarios : " + count + "Starts ****************************");
				if (i > 1)
					CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group + ", " + CountryCode + ", " + State + ", " + City + ", " + Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(), "Applied global filter: " + Group + ", " + CountryCode
						+ ", " + State + ", " + City + ", " + Location + "", "yes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test to verify Pie Chart Tooltip Data
	 */
	@Test(priority = 4)
	public void VerifyPieChartData() {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		data.VerifyPieChart();
	}

	/**
	 * Test to Export Data
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void Export() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		try {
			data.ExportasCSV();
			addEvidence(CurrentState.getDriver(), "Export as CSV", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			data.ExportasXLSX();
			addEvidence(CurrentState.getDriver(), "Export as XLSX", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test to Compare UI ann XL Data
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6)
	public void CompareUIXLData() throws Exception {
		data = new TPSEE_FacebookInsights_Page(CurrentState.getDriver());
		try {
			data.PageAction();
			addEvidence(CurrentState.getDriver(), "To get Number of Phone Calls", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			data.Checkin();
			addEvidence(CurrentState.getDriver(), "To Compare UI and XL CheckIn", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			data.Fans();
			addEvidence(CurrentState.getDriver(), "To Compare XL and UI Fans", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			data.PostEngagement();
			addEvidence(CurrentState.getDriver(), "To Compare XL and UI Post Engagements", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			data.getImpressionCount();
			addEvidence(CurrentState.getDriver(), "To Compare XL and UI Impressions", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
