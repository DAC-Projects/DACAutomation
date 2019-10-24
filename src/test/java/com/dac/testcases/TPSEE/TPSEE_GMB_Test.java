package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_GMB;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_GMB_Test extends BaseClass{

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_GMB data;
	
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
							description = "Test to get ToolTip Value ")
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
	
	//Test for getting tooltip value
	@Test(dependsOnMethods = {"verifyTooltip"},groups = {"smoke"},
			description ="Verify Export")
	public void GMBExport() throws Exception{
		data = new TPSEE_GMB(CurrentState.getDriver());
		data.exportGMB();
		addEvidence(CurrentState.getDriver(),
			"Verifying export functionality ", "yes");
	}
	
	//Test for getting tooltip value
		@Test(dependsOnMethods = {"GMBExport"},groups = {"smoke"},
				description ="Verify Customer Action UI")
		public void VerifyCustomerActionUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.CustomerAction();
			addEvidence(CurrentState.getDriver(),
				"Verifying Customer Actions ", "yes");
	}
		
		@Test(dependsOnMethods = {"VerifyCustomerActionUI"},groups = {"smoke"},
				description ="Verify Where Listing found UI")
		public void VerifyWhereListingUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.WhereListingFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying Where Listing Found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyWhereListingUI"},groups = {"smoke"},
				description ="Verify How Listing found UI")
		public void VerifyHowListingUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.HowListingFound();
			addEvidence(CurrentState.getDriver(),
				"Verifying How Listing found UI ", "yes");
		}
		
		@Test(dependsOnMethods = {"VerifyHowListingUI"},groups = {"smoke"},
				description ="Verify Photo Views UI")
		public void VerifyPhotoViewsUI() throws Exception{
			data = new TPSEE_GMB(CurrentState.getDriver());
			data.PhotoViews();
			addEvidence(CurrentState.getDriver(),
				"Verifying Photo Views UI ", "yes");
		}
}
