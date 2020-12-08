package com.dac.testcases.SE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_SE.SE_Export_Functionality;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_Groups;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class SE_Export_Functionality_Test extends BaseClass{
	Navigationpage np;
	SE_Export_Functionality exp;
	
	@Test(priority= 1, groups = { "smoke" }, description = "Test for navigating to Manage Connections page")
	public void navigateToManageConnections() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSE_Connections();
		np.navigateToFacebookManagePages();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Manage Connections Page");
		addEvidence(CurrentState.getDriver(), "Navigate to Manage Connections Page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(priority= 2, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void manageExcel() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}

	
	@Test(priority= 5, groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void globalFilterValidation() throws Exception {
exp = new SE_Export_Functionality(CurrentState.getDriver());
try {	
	int count = 1;
	ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Configuration"); wb.deleteEmptyRows();
	SE_Export_Functionality s = new SE_Export_Functionality(CurrentState.getDriver());
	
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
	
	@Test(priority= 6, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilter() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}

	
	@Test(priority= 3, groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void keywordSearchValidation() throws Exception {
exp = new SE_Export_Functionality(CurrentState.getDriver());
try {	
	int count = 1;
	ExcelHandler wb = new ExcelHandler("./data/KeyWordSearch.xlsx", "Configuration"); wb.deleteEmptyRows();
	SE_Export_Functionality s = new SE_Export_Functionality(CurrentState.getDriver());
	
	for(int i=1;i<=wb.getRowCount();i++) {
		System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
		if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		
		String keyword = wb.getCellValue(i, wb.seacrh_pattern("Keyword", 0).get(0).intValue());
		
		s.applyKeywordSearch(keyword);
		System.out.println(keyword);
		s.clickSearchApplyFilterBTN();
		BaseClass.addEvidence(CurrentState.getDriver(),
				"Applied keyword: "+keyword+"", "yes");
	}
		}catch(Exception e) {
	e.printStackTrace();
}
}
	
	@Test(priority= 4, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilter() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 7, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void assignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.clickSearchApplyFilterBTN();
		exp.clickAssignedLocationsTab();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 8, groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void keywordSearchValidationAssignedLocations() throws Exception {
exp = new SE_Export_Functionality(CurrentState.getDriver());
try {	
	int count = 1;
	ExcelHandler wb = new ExcelHandler("./data/KeyWordSearch.xlsx", "Configuration"); wb.deleteEmptyRows();
	SE_Export_Functionality s = new SE_Export_Functionality(CurrentState.getDriver());
	
	for(int i=1;i<=wb.getRowCount();i++) {
		System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
		if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		
		String keyword = wb.getCellValue(i, wb.seacrh_pattern("Keyword", 0).get(0).intValue());
		
		s.applyKeywordSearch(keyword);
		System.out.println(keyword);
		s.clickSearchApplyFilterBTN();
		BaseClass.addEvidence(CurrentState.getDriver(),
				"Applied keyword: "+keyword+"", "yes");
	}
		}catch(Exception e) {
	e.printStackTrace();
}
}
	
	@Test(priority= 9, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilterAssignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 10, groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void globalFilterValidationAssignedLocations() throws Exception {
exp = new SE_Export_Functionality(CurrentState.getDriver());
try {	
	int count = 1;
	ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Configuration"); wb.deleteEmptyRows();
	SE_Export_Functionality s = new SE_Export_Functionality(CurrentState.getDriver());
	
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

	@Test(priority= 11, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilterAssignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 12, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void unassignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.clickSearchApplyFilterBTN();
		exp.clickUnassignedLocationsTab();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 13, groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void keywordSearchValidationUnassignedLocations() throws Exception {
exp = new SE_Export_Functionality(CurrentState.getDriver());
try {	
	int count = 1;
	ExcelHandler wb = new ExcelHandler("./data/KeyWordSearch.xlsx", "Configuration"); wb.deleteEmptyRows();
	SE_Export_Functionality s = new SE_Export_Functionality(CurrentState.getDriver());
	
	for(int i=1;i<=wb.getRowCount();i++) {
		System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
		if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		
		String keyword = wb.getCellValue(i, wb.seacrh_pattern("Keyword", 0).get(0).intValue());
		
		s.applyKeywordSearch(keyword);
		System.out.println(keyword);
		s.clickSearchApplyFilterBTN();
		BaseClass.addEvidence(CurrentState.getDriver(),
				"Applied keyword: "+keyword+"", "yes");
	}
		}catch(Exception e) {
	e.printStackTrace();
}
}
	
	@Test(priority= 14, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilterUnassignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 15, groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void globalFilterValidationUnassignedLocations() throws Exception {
exp = new SE_Export_Functionality(CurrentState.getDriver());
try {	
	int count = 1;
	ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Configuration"); wb.deleteEmptyRows();
	SE_Export_Functionality s = new SE_Export_Functionality(CurrentState.getDriver());
	
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

	@Test(priority= 16, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilterUnassignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
}
