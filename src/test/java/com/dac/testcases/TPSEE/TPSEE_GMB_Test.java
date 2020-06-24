package com.dac.testcases.TPSEE;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_GMB;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_GMB_Test extends BaseClass {

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_GMB data;
	WebDriver driver;
	String grph = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none";
	int start;
	int end;
	int start1;
	int end1;
	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";

	String GMBXLSX = "GMBXLSX.xlsx";
	String GMBXLSX1m = "GMBXLSX1m.xlsx";
	String GMBXLSX3m = "GMBXLSX3m.xlsx";
	String GMBXLSX6m = "GMBXLSX6m.xlsx";
	String GMBXLSX1y = "GMBXLSX1y.xlsx";
	String GMBXLSXytd = "GMBXLSXytd.xlsx";
	String GMBXLSXall = "GMBXLSXall.xlsx";

	String chromepath = "./downloads/chromeGMBXLSX.xlsx";
	String IEpath = "./downloads/IEGMBXLSX.xlsx";
	String FFpath = "./downloads/FFGMBXLSX.xlsx";

	String chromepath1m = "./downloads/chromeGMBXLSX1m.xlsx";
	String IEpath1m = "./downloads/IEGMBXLSX1m.xlsx";
	String FFpath1m = "./downloads/FFGMBXLSX1m.xlsx";

	String chromepath3m = "./downloads/chromeGMBXLSX3m.xlsx";
	String IEpath3m = "./downloads/IEGMBXLSX3m.xlsx";
	String FFpath3m = "./downloads/FFGMBXLSX3m.xlsx";

	String chromepath6m = "./downloads/chromeGMBXLSX.xlsx";
	String IEpath6m = "./downloads/IEGMBXLSX.xlsx";
	String FFpath6m = "./downloads/FFGMBXLSX.xlsx";

	String chromepath1y = "./downloads/chromeGMBXLSX1y.xlsx";
	String IEpath1y = "./downloads/IEGMBXLSX1y.xlsx";
	String FFpath1y = "./downloads/FFGMBXLSX1y.xlsx";

	String chromepathytd = "./downloads/chromeGMBXLSXytd.xlsx";
	String IEpathytd = "./downloads/IEGMBXLSXytd.xlsx";
	String FFpathytd = "./downloads/FFGMBXLSXytd.xlsx";

	String chromepathall = "./downloads/chromeGMBXLSXall.xlsx";
	String IEpathall = "./downloads/IEGMBXLSXall.xlsx";
	String FFpathall = "./downloads/FFGMBXLSXall.xlsx";

	// There is currently not enough data from Google to display this report

	@Test(priority = 1, groups = { "smoke" }, description = "Test for navigating to GMB page")
	public void navigateToGoogleMyBusiness() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToGoogleMyBusiness();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE page");
		addEvidence(CurrentState.getDriver(), "Navigate to GMB page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}

	@Test(priority = 2, groups = {"smoke" }, description = "Test for verifying title and description of report")
	public void verifyText() throws Exception {
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.VerifyGMBTitleText("Google My Business",
				"This report provides insights into the number of views, clicks and actions that occured for each of your locations on Google over time. This information is obtained from Google and is updated daily.");

		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
	}

	@Test(priority = 3, groups = { "smoke" }, description = "Test for verify hover text")
	public void verifyHoverText() throws Exception {
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.verifyMouseHoverText();
	}

	@Test(priority = 4, groups = { "smoke" }, description = "Verify GMB page loads after filter applied")
	public void verifyFilteringReportsGMB() throws Exception {
		data = new TPSEE_GMB(CurrentState.getDriver());
		try {
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE");
			wb.deleteEmptyRows();
			TPSEE_GMB s = new TPSEE_GMB(CurrentState.getDriver());

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

	@Test(priority = 5, groups = { "smoke" }, description = "Verify ToolTip Value ")
	public void verifyTooltip() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {

			data = new TPSEE_GMB(CurrentState.getDriver());
			try {
				data.verifyCustomerActionsGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified from Cusotmer Actions GMB report",
						"yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.verifyWhereListingGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified from Where Listing found GMB report",
						"yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.verifyHowListingGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified from How Listing found GMB report",
						"yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.verifyPhotoViewGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified from Photo View GMB report", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.verifyPhotoQtyGraph();
				addEvidence(CurrentState.getDriver(), "Tooltip values verified from Photo Quantity GMB report", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 6, groups = { "smoke" }, description = "Verify CSV Export")
	public void GMBExportasCSV() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.exportcsvGMB();
			addEvidence(CurrentState.getDriver(), "Verifying CSV export functionality ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 7, groups = { "smoke" }, description = "Verify XLSX Export")
	public void GMBExportasXLSX() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.exportXLSXGMB(GMBXLSX);
			addEvidence(CurrentState.getDriver(), "Verifying XLSX export functionality ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 8, groups = { "smoke" }, description = "UI and XL comparision for WebActions")
	public void UIXLCompareofWebActions() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLWebActions(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for WebActions  ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 9, groups = { "smoke" }, description = "UI and XL comparision for Request Directory")
	public void UIXLCompareofReqDirActions() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLReqDirActions(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Request Directory ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 10, groups = { "smoke" }, description = "UI and XL comparision for Phone Calls")
	public void UIXLCompareofPhCalls() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLPhCalls(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Phone Calls ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 11, groups = { "smoke" }, description = "UI and XL comparision for Total Actions")
	public void UIXLCompareofTotalActions() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLTotActions(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Actions ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 12, groups = { "smoke" }, description = "Verify Customer Action UI")
	public void VerifyCustomerActionUI() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CustomerAction();
			addEvidence(CurrentState.getDriver(), "Verifying Customer Actions ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 13, groups = { "smoke" }, description = "Verify Customer Web Action UI")
	public void VerifyCustomerActionUIWebVisits() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.VerifyCustomerActiononWebVisits();
			addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Web Visits", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 14, groups = { "smoke" }, description = "Verify Customer Req Direct Action UI")
	public void VerifyCustomerActionUIReqDirectory() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.VerifyCustomerActiononWebReqDirectory();
			addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Request Directory", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 15, groups = { "smoke" }, description = "Verify Customer Phone Action UI")
	public void VerifyCustomerActionUIPhCalls() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.VerifyCustomerActiononPhCalls();
			addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Phone Calls", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 16, groups = { "smoke" }, description = "Verify Where Listing found UI")
	public void VerifyWhereListingUI() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.WhereListingFound();
			addEvidence(CurrentState.getDriver(), "Verifying Where Listing Found UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 17, groups = { "smoke" }, description = "Verify Where Listing Search found UI")
	public void VerifyWhereListingUISearch() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.WhereListingSearchFound();
			addEvidence(CurrentState.getDriver(), "Verifying Where Listing Search Found UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 18, groups = { "smoke" }, description = "Verify Where Listing Map found UI")
	public void VerifyWhereListingUIMaps() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.WhereListingMapFound();
			addEvidence(CurrentState.getDriver(), "Verifying Where Listing Map Found UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 19, groups = { "smoke" }, description = "UI and XL comparision for Where Search Listing")
	public void UIXLCompareofWhereSearch() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLWhereSearch(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Search Listing", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 20, groups = { "smoke" }, description = "UI and XL comparision for Where Map Listing")
	public void UIXLCompareofWhereMap() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLWhereMap(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Map Listing ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 21, groups = { "smoke" }, description = "UI and XL comparision for Where Total View Listing")
	public void UIXLCompareofWhereTotalViews() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLWhereTotalViews(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Total View Listing ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 22, groups = { "smoke" }, description = "Verify How Listing found UI")
	public void VerifyHowListingUI() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingFound();
			addEvidence(CurrentState.getDriver(), "Verifying How Listing found UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 23, groups = { "smoke" }, description = "Verify How Listing Discovery found UI")
	public void VerifyHowListingUIDiscovery() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingDiscoveryFound();
			addEvidence(CurrentState.getDriver(), "Verifying How Listing Discovery found UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 24, groups = { "smoke" }, description = "Verify How Listing Directory found UI")
	public void VerifyHowListingUIDirect() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingDirFound();
			addEvidence(CurrentState.getDriver(), "Verifying How Listing Directory found UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 25, groups = { "smoke" }, description = "Verify How Brand found UI")
	public void VerifyHowListingUIBrand() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingBrandFound();
			addEvidence(CurrentState.getDriver(), "Verifying How Brand found UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 26, groups = { "smoke" }, description = "Verify Photo Views UI")
	public void VerifyPhotoViewsUI() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.PhotoViews();
			addEvidence(CurrentState.getDriver(), "Verifying Photo Views UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 27, groups = { "smoke" }, description = "UI and XL comparision for How Discovery Listing")
	public void UIXLCompareofDiscovery() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLDiscovery(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Discovery Listing ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 28, groups = { "smoke" }, description = "UI and XL comparision for How Direct Listing")
	public void UIXLCompareofDirect() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLDirect(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Direct Listing", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 29, groups = { "smoke" }, description = "UI and XL comparision for How Brand Listing")
	public void UIXLCompareofBranded() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLBranded(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Brand Listing ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 30, groups = { "smoke" }, description = "UI and XL comparision for How total Listing")
	public void UIXLCompareofTotalSearch() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLTotalSearch(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for How total Listing ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 31, groups = { "smoke" }, description = "Verify Owner Photo Views UI")
	public void VerifyPhotoViewsUIOwner() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.OwnerPhotoViews();
			addEvidence(CurrentState.getDriver(), "Verifying Owner Photo Views UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 32, groups = { "smoke" }, description = "Verify Customer Photo Views UI")
	public void VerifyPhotoViewsUICustomer() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CustomerPhotoViews();
			addEvidence(CurrentState.getDriver(), "Verifying Customer Photo Views UI ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 33, groups = { "smoke" }, description = "UI and XL comparision for Owner Photo Views")
	public void UIXLCompareofOwnerPhView() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLOwnerPhView(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Owner Photo Views ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 34, groups = { "smoke" }, description = "UI and XL comparision for Customer Photo Views")
	public void UIXLCompareofCustPhView() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLCustPhView(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Customer Photo Views", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 35, groups = { "smoke" }, description = "UI and XL comparision for Total Photo Views")
	public void UIXLCompareofTotPhView() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLTotPhView(chromepath);
			addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Photo Views ", "yes");
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	// Test to verify Zoom Functionality
	@Test(priority = 36, groups = { "smoke" }, description = "Verify Zoom Functionality")
	public void gethighchartsdate() throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());

			try {
				String OneMonth = "1m";
				data.clickHighchartCriteria(OneMonth);
				addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.exportXLSXGMB(GMBXLSX1m);
				addEvidence(CurrentState.getDriver(), "Verifying XLSX export functionality ", "yes");
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWebActions(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for WebActions  ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLReqDirActions(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Request Directory ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLPhCalls(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Phone Calls ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotActions(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerAction();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebVisits();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Web Visits", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebReqDirectory();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Request Directory", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononPhCalls();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Phone Calls", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingSearchFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Search Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingMapFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Map Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereSearch(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Search Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereMap(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Map Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereTotalViews(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Total View Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDiscoveryFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Discovery found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDirFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Directory found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingBrandFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Brand found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.PhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDiscovery(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Discovery Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDirect(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Direct Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLBranded(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Brand Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotalSearch(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How total Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.OwnerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Owner Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLOwnerPhView(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Owner Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLCustPhView(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Customer Photo Views", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotPhView(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String ThreeMonths = "3m";
				data.clickHighchartCriteria(ThreeMonths);
				addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.exportXLSXGMB(GMBXLSX3m);
				addEvidence(CurrentState.getDriver(), "Verifying XLSX export functionality ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWebActions(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for WebActions  ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLReqDirActions(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Request Directory ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLPhCalls(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Phone Calls ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotActions(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerAction();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebVisits();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Web Visits", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebReqDirectory();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Request Directory", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononPhCalls();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Phone Calls", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingSearchFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Search Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingMapFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Map Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereSearch(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Search Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereMap(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Map Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereTotalViews(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Total View Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDiscoveryFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Discovery found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDirFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Directory found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingBrandFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Brand found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.PhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDiscovery(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Discovery Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDirect(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Direct Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLBranded(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Brand Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotalSearch(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How total Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.OwnerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Owner Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLOwnerPhView(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Owner Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLCustPhView(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Customer Photo Views", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotPhView(chromepath3m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String SixMonths = "6m";
				data.clickHighchartCriteria(SixMonths);
				addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.exportXLSXGMB(GMBXLSX6m);
				addEvidence(CurrentState.getDriver(), "Verifying XLSX export functionality ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWebActions(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for WebActions  ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLReqDirActions(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Request Directory ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLPhCalls(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Phone Calls ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotActions(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerAction();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebVisits();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Web Visits", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebReqDirectory();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Request Directory", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononPhCalls();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Phone Calls", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingSearchFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Search Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingMapFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Map Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereSearch(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Search Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereMap(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Map Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereTotalViews(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Total View Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDiscoveryFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Discovery found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDirFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Directory found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingBrandFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Brand found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.PhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDiscovery(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Discovery Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDirect(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Direct Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLBranded(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Brand Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotalSearch(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How total Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.OwnerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Owner Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLOwnerPhView(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Owner Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLCustPhView(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Customer Photo Views", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotPhView(chromepath6m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String OneYear = "1y";
				data.clickHighchartCriteria(OneYear);
				addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.exportXLSXGMB(GMBXLSX1y);
				addEvidence(CurrentState.getDriver(), "Verifying XLSX export functionality ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWebActions(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for WebActions  ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLReqDirActions(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Request Directory ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLPhCalls(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Phone Calls ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotActions(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerAction();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebVisits();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Web Visits", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebReqDirectory();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Request Directory", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononPhCalls();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Phone Calls", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Found UI ", "yes");
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingSearchFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Search Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingMapFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Map Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereSearch(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Search Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereMap(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Map Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereTotalViews(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Total View Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDiscoveryFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Discovery found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDirFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Directory found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingBrandFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Brand found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.PhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDiscovery(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Discovery Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDirect(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Direct Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLBranded(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Brand Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotalSearch(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How total Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.OwnerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Owner Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLOwnerPhView(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Owner Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLCustPhView(chromepath1y);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Customer Photo Views", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotPhView(chromepath1m);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String YearToDate = "ytd";
				data.clickHighchartCriteria(YearToDate);
				addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.exportXLSXGMB(GMBXLSXytd);
				addEvidence(CurrentState.getDriver(), "Verifying XLSX export functionality ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWebActions(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for WebActions  ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLReqDirActions(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Request Directory ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLPhCalls(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Phone Calls ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotActions(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerAction();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebVisits();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Web Visits", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebReqDirectory();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Request Directory", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononPhCalls();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Phone Calls", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingSearchFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Search Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingMapFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Map Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereSearch(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Search Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereMap(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Map Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereTotalViews(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Total View Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDiscoveryFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Discovery found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDirFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Directory found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingBrandFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Brand found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.PhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDiscovery(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Discovery Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDirect(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Direct Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLBranded(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Brand Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotalSearch(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How total Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.OwnerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Owner Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLOwnerPhView(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Owner Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLCustPhView(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Customer Photo Views", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotPhView(chromepathytd);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String ALLDATA = "all";
				data.clickHighchartCriteria(ALLDATA);
				addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.exportXLSXGMB(GMBXLSXall);
				addEvidence(CurrentState.getDriver(), "Verifying XLSX export functionality ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWebActions(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for WebActions  ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLReqDirActions(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Request Directory ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLPhCalls(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Phone Calls ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotActions(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerAction();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebVisits();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Web Visits", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononWebReqDirectory();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Request Directory", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.VerifyCustomerActiononPhCalls();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Actions on Phone Calls", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingSearchFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Search Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.WhereListingMapFound();
				addEvidence(CurrentState.getDriver(), "Verifying Where Listing Map Found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereSearch(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Search Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereMap(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Map Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLWhereTotalViews(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Where Total View Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDiscoveryFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Discovery found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingDirFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Listing Directory found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.HowListingBrandFound();
				addEvidence(CurrentState.getDriver(), "Verifying How Brand found UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.PhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDiscovery(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Discovery Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLDirect(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Direct Listing", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLBranded(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How Brand Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotalSearch(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for How total Listing ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.OwnerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Owner Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CustomerPhotoViews();
				addEvidence(CurrentState.getDriver(), "Verifying Customer Photo Views UI ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLOwnerPhView(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Owner Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLCustPhView(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Customer Photo Views", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				data.CompareUIXLTotPhView(chromepathall);
				addEvidence(CurrentState.getDriver(), "UI and XL comparision for Total Photo Views ", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@Test(priority = 37, enabled = true, dataProvider = "testData", description = "Test for Manual date selection")
	public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		String UIdat = data.IsDataAvailable();
		if (!UIdat.equals("There is currently not enough data from Google to display this report")) {
			data = new TPSEE_GMB(CurrentState.getDriver());
			if (!(from_day.equals("null")) | !(to_day.equals("null"))) {
				data.selectCalender_FromDate(grphfromDate, (int) (Double.parseDouble(from_day)), from_month,
						(int) (Double.parseDouble(from_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				data.selectCalender_ToDate(grphtoDate, (int) (Double.parseDouble(to_day)), to_month,
						(int) (Double.parseDouble(to_year)));
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				Date fromcal = data.getCurrentfromDate();
				Date fromgrph = data.verifyinitialHistoryGraph(start, end, grph);
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
				Assert.assertEquals(fromgrph, fromcal);
				Date tocal = data.getCurrenttoDate();
				Date togrph = data.verifyfinalHistorygraph(2, 0, grph);
				Assert.assertEquals(togrph, tocal);
				addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			}
		} else {
			System.out.println("No Data Available for GMB");
		}
	}

	@SuppressWarnings("finally")
	@DataProvider
	public String[][] testData() {
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Zoom");
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
