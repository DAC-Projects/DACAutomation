package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_GMB;
import com.dac.main.POM_TPSEE.TPSEE_GoogleRanking_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_GMB_Test extends BaseClass{

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_GMB data;
	WebDriver driver;
	
	@Test(groups = { "smoke" }, description = "Test for navigating to GMB page")
	public void navigateToGoogleMyBusiness() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToGoogleMyBusiness();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE page");
		addEvidence(CurrentState.getDriver(), "Navigate to GMB page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(dependsOnMethods = { "navigateToGoogleMyBusiness" }, groups = {
	"smoke" }, description = "Verify GMB page loads after filter applied")
	public void verifyFilteringReportsGMB() throws Exception {
		data = new TPSEE_GMB(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "TPSEE"); wb.deleteEmptyRows();
			TPSEE_GMB s = new TPSEE_GMB(CurrentState.getDriver());
	
			for(int i=1;i<=wb.getRowCount();i++) {
				System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
				if(i>1) CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
				String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
				String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				s.applyGlobalFilter(Group, CountryCode, State, City, Location);
				System.out.println(Group+", "+CountryCode+", "+State+", "+City+", "+Location);
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(),
				"Applied global filter: "+Group+", "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = { "verifyFilteringReportsGMB" }, groups = { "smoke" }, 
							description = "Verify ToolTip Value ")
	public void verifyTooltip() throws Exception {
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.verifyCustomerActionsGraph();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Cusotmer Actions GMB report", "yes");
		Thread.sleep(5000);
		data.verifyWhereListingGraph();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Where Listing found GMB report", "yes");
		Thread.sleep(5000);
		data.verifyHowListingGraph();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from How Listing found GMB report", "yes");
		Thread.sleep(5000);
		data.verifyPhotoViewGraph();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Photo View GMB report", "yes");
		Thread.sleep(5000);
		data.verifyPhotoQtyGraph();
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Photo Quantity GMB report", "yes");
	}
	
	
	@Test(dependsOnMethods = {"verifyFilteringReportsGMB"},groups = {"smoke"},
			description ="Verify CSV Export")
	public void GMBExportasCSV() throws Exception{
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.exportcsvGMB();
		addEvidence(CurrentState.getDriver(),
			"Verifying CSV export functionality ", "yes");
	}
	
	@Test(dependsOnMethods = {"GMBExportasCSV"},groups = {"smoke"},
			description ="Verify XLSX Export")
	public void GMBExportasXLSX() throws Exception{
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.exportXLSXGMB();
		addEvidence(CurrentState.getDriver(),
			"Verifying XLSX export functionality ", "yes");
	}
	
	@Test(dependsOnMethods = {"GMBExportasXLSX"},groups = {"smoke"},
			description ="UI and XL comparision for WebActions")
	public void UIXLCompareofWebActions() throws Exception{
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.CompareUIXLWebActions();
		addEvidence(CurrentState.getDriver(),
			"UI and XL comparision for WebActions  ", "yes");
	}
	
	@Test(dependsOnMethods = {"UIXLCompareofWebActions"},groups = {"smoke"},
			description ="UI and XL comparision for Request Directory")
	public void UIXLCompareofReqDirActions() throws Exception{
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.CompareUIXLReqDirActions();
		addEvidence(CurrentState.getDriver(),
			"UI and XL comparision for Request Directory ", "yes");
	}
	
	@Test(dependsOnMethods = {"UIXLCompareofReqDirActions"},groups = {"smoke"},
			description ="UI and XL comparision for Phone Calls")
	public void UIXLCompareofPhCalls() throws Exception{
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.CompareUIXLPhCalls();
		addEvidence(CurrentState.getDriver(),
			"UI and XL comparision for Phone Calls ", "yes");
	}
	
	@Test(dependsOnMethods = {"UIXLCompareofPhCalls"},groups = {"smoke"},
			description ="UI and XL comparision for Total Actions")
	public void UIXLCompareofTotalActions() throws Exception{
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.CompareUIXLTotActions();
		addEvidence(CurrentState.getDriver(),
			"UI and XL comparision for Total Actions ", "yes");
	}
	

		@Test(dependsOnMethods = {"UIXLCompareofTotalActions"},groups = {"smoke"},
				description ="Verify Customer Action UI")
		public void VerifyCustomerActionUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CustomerAction();
			addEvidence(CurrentState.getDriver(),
				"Verifying Customer Actions ", "yes");
	}
		@Test(dependsOnMethods = {"VerifyCustomerActionUI"},groups = {"smoke"},
				description ="Verify Customer Web Action UI")
		public void VerifyCustomerActionUIWebVisits() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.VerifyCustomerActiononWebVisits();
			addEvidence(CurrentState.getDriver(),
					"Verifying Customer Actions on Web Visits", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyCustomerActionUIWebVisits"},groups = {"smoke"},
				description ="Verify Customer Req Direct Action UI")
		public void VerifyCustomerActionUIReqDirectory() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.VerifyCustomerActiononWebReqDirectory();
			addEvidence(CurrentState.getDriver(),
					"Verifying Customer Actions on Request Directory", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyCustomerActionUIReqDirectory"},groups = {"smoke"},
				description ="Verify Customer Phone Action UI")
		public void VerifyCustomerActionUIPhCalls() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.VerifyCustomerActiononPhCalls();
			addEvidence(CurrentState.getDriver(),
					"Verifying Customer Actions on Phone Calls", "yes");
		}
		
		
		@Test(dependsOnMethods = {"VerifyCustomerActionUIPhCalls"},groups = {"smoke"},
				description ="Verify Where Listing found UI")
		public void VerifyWhereListingUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.WhereListingFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying Where Listing Found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyWhereListingUI"},groups = {"smoke"},
				description ="Verify Where Listing Search found UI")
		public void VerifyWhereListingUISearch() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.WhereListingSearchFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying Where Listing Search Found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyWhereListingUISearch"},groups = {"smoke"},
				description ="Verify Where Listing Map found UI")
		public void VerifyWhereListingUIMaps() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.WhereListingMapFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying Where Listing Map Found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyWhereListingUIMaps"},groups = {"smoke"},
				description ="UI and XL comparision for Where Search Listing")
		public void UIXLCompareofWhereSearch() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLWhereSearch();
			addEvidence(CurrentState.getDriver(),
				"UI and XL comparision for Where Search Listing", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofWhereSearch"},groups = {"smoke"},
				description ="UI and XL comparision for Where Map Listing")
		public void UIXLCompareofWhereMap() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLWhereMap();
			addEvidence(CurrentState.getDriver(),
				"UI and XL comparision for Where Map Listing ", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofWhereMap"},groups = {"smoke"},
				description ="UI and XL comparision for Where Total View Listing")
		public void UIXLCompareofWhereTotalViews() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLWhereTotalViews();
			addEvidence(CurrentState.getDriver(),
				"UI and XL comparision for Where Total View Listing ", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofWhereTotalViews"},groups = {"smoke"},
				description ="Verify How Listing found UI")
		public void VerifyHowListingUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying How Listing found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyHowListingUI"},groups = {"smoke"},
				description ="Verify How Listing Discovery found UI")
		public void VerifyHowListingUIDiscovery() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingDiscoveryFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying How Listing Discovery found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyHowListingUIDiscovery"},groups = {"smoke"},
				description ="Verify How Listing Directory found UI")
		public void VerifyHowListingUIDirect() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingDirFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying How Listing Directory found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyHowListingUIDirect"},groups = {"smoke"},
				description ="Verify How Brand found UI")
		public void VerifyHowListingUIBrand() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingBrandFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying How Brand found UI ", "yes");
		}
		
	
	
		@Test(dependsOnMethods = {"VerifyHowListingUIBrand"},groups = {"smoke"},
				description ="Verify Photo Views UI")
		public void VerifyPhotoViewsUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.PhotoViews();
			addEvidence(CurrentState.getDriver(),
				"Verifying Photo Views UI ", "yes");
		}
				
		@Test(dependsOnMethods = {"VerifyPhotoViewsUI"},groups = {"smoke"},
		description ="UI and XL comparision for How Discovery Listing")
		public void UIXLCompareofDiscovery() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CompareUIXLDiscovery();
			addEvidence(CurrentState.getDriver(),
					"UI and XL comparision for How Discovery Listing ", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofDiscovery"},groups = {"smoke"},
				description ="UI and XL comparision for How Direct Listing")
			public void UIXLCompareofDirect() throws Exception{
				data = new TPSEE_GMB(CurrentState.getDriver());
				data.CompareUIXLDirect();
				addEvidence(CurrentState.getDriver(),
						"UI and XL comparision for How Direct Listing", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofDirect"},groups = {"smoke"},
				description ="UI and XL comparision for How Brand Listing")
			public void UIXLCompareofBranded() throws Exception{
				data = new TPSEE_GMB(CurrentState.getDriver());
				data.CompareUIXLBranded();
				addEvidence(CurrentState.getDriver(),
						"UI and XL comparision for How Brand Listing ", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofBranded"},groups = {"smoke"},
				description ="UI and XL comparision for How total Listing")
			public void UIXLCompareofTotalSearch() throws Exception{
				data = new TPSEE_GMB(CurrentState.getDriver());
				data.CompareUIXLTotalSearch();
				addEvidence(CurrentState.getDriver(),
						"UI and XL comparision for How total Listing ", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofTotalSearch"},groups = {"smoke"},
				description ="Verify Owner Photo Views UI")
		public void VerifyPhotoViewsUIOwner() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.OwnerPhotoViews();
			addEvidence(CurrentState.getDriver(),
				"Verifying Owner Photo Views UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyPhotoViewsUIOwner"},groups = {"smoke"},
				description ="Verify Customer Photo Views UI")
		public void VerifyPhotoViewsUICustomer() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CustomerPhotoViews();
			addEvidence(CurrentState.getDriver(),
				"Verifying Customer Photo Views UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyPhotoViewsUICustomer"},groups = {"smoke"},
				description ="UI and XL comparision for Owner Photo Views")
			public void UIXLCompareofOwnerPhView() throws Exception{
				data = new TPSEE_GMB(CurrentState.getDriver());
				data.CompareUIXLOwnerPhView();
				addEvidence(CurrentState.getDriver(),
						"UI and XL comparision for Owner Photo Views ", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofOwnerPhView"},groups = {"smoke"},
				description ="UI and XL comparision for Customer Photo Views")
			public void UIXLCompareofCustPhView() throws Exception{
				data = new TPSEE_GMB(CurrentState.getDriver());
				data.CompareUIXLCustPhView();
				addEvidence(CurrentState.getDriver(),
						"UI and XL comparision for Customer Photo Views", "yes");
		}
		
		@Test(dependsOnMethods = {"UIXLCompareofCustPhView"},groups = {"smoke"},
				description ="UI and XL comparision for Total Photo Views")
			public void UIXLCompareofTotPhView() throws Exception{
				data = new TPSEE_GMB(CurrentState.getDriver());
				data.CompareUIXLTotPhView();
				addEvidence(CurrentState.getDriver(),
						"UI and XL comparision for Total Photo Views ", "yes");
		}
		
		//Test to verify Zoom Functionality
		@Test(dependsOnMethods = {"verifyFilteringReportsGMB"},groups = {"smoke"},
				description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			String OneMonth ="1m";
			data.clickHighchartCriteria(OneMonth);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");			
			Thread.sleep(5000);
			String ThreeMonths = "3m";
			data.clickHighchartCriteria(ThreeMonths);
			addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String SixMonths = "6m";
			data.clickHighchartCriteria(SixMonths);
			addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
			Thread.sleep(5000);
			String OneYear = "1y";
			data.clickHighchartCriteria(OneYear);
			addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
			Thread.sleep(5000);
			String YearToDate ="ytd";
			data.clickHighchartCriteria(YearToDate);
			addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
			Thread.sleep(5000);
			String ALLDATA = "all";
			data.clickHighchartCriteria(ALLDATA);
			addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			}
}
