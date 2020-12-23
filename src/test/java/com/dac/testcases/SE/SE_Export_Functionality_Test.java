package com.dac.testcases.SE;

import org.testng.ISuite;
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
	
	@Test(priority= 2, enabled=false,   groups = { "smoke" }, description = "Test for Renaming Excel")
	public void manageExcel() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}

	
	@Test(priority= 5,enabled=false, groups = {
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
	
	@Test(priority= 6,  enabled=false, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilter() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}

	
	@Test(priority= 3,enabled=false,   groups = {
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
	
	@Test(priority= 4,  enabled=false,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilter() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 7,  enabled=false, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void assignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.clickSearchApplyFilterBTN();
		exp.clickAssignedLocationsTab();
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 8, enabled=false,  groups = {
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
	
	@Test(priority= 9, enabled=false,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilterAssignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 10,  enabled=false, groups = {
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

	@Test(priority= 11, enabled=false,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilterAssignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 12,  enabled=false, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void unassignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.clickSearchApplyFilterBTN();
		exp.clickUnassignedLocationsTab();
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 13, enabled=false,  groups = {
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
	
	@Test(priority= 14, enabled=false,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilterUnassignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 15,enabled=false,   groups = {
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

	@Test(priority= 16,  enabled=false, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilterUnassignedLocations() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 17,  enabled=false, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportBrands() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.cickBrands();
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UIBrandRead();
		exp.UIPagesReadInBrands();
		exp.excelRead_UIexcelcomparisonBrands();
	}
	
	@Test(priority= 18 ,  enabled=false,groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void keywordSearchValidationBrands() throws Exception {
exp = new SE_Export_Functionality(CurrentState.getDriver());
exp.cickBrands();
try {	
	int count = 1;
	ExcelHandler wb = new ExcelHandler("./data/KeyWordSearch.xlsx", "Brands"); wb.deleteEmptyRows();
	SE_Export_Functionality s = new SE_Export_Functionality(CurrentState.getDriver());
	
	for(int i=1;i<=wb.getRowCount();i++) {
		System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
		if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		
		String keyword = wb.getCellValue(i, wb.seacrh_pattern("Keyword", 0).get(0).intValue());
		
		s.applyKeywordSearch(keyword);
		System.out.println(keyword);
		s.clickSearchApplyFilterBTN();
		//Thread.sleep(5000);
		BaseClass.addEvidence(CurrentState.getDriver(),
				"Applied keyword: "+keyword+"", "yes");
	}
		}catch(Exception e) {
	e.printStackTrace();
}
}
	
	@Test(priority= 19,  enabled=false,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterKeywordSearchBrands() throws Exception{
	
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UIBrandRead();
		exp.UIPagesReadInBrands();
		
		exp.excelRead_UIexcelcomparisonBrands();
		
	}
	
	@Test(priority= 20, groups = { "smoke" }, description = "Test for navigating to Manage Connections page")
	public void navigateToManagePagesGoogle() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.clickManageConnectionslink();
		np.navigateToGoogleManagePages();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Manage Connections Page");
		addEvidence(CurrentState.getDriver(), "Navigate to Manage Connections Page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(priority= 21,   groups = { "smoke" }, description = "Test for Renaming Excel")
	public void manageExcelGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 22,  groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void keywordSearchValidationAllLocationsGoogle() throws Exception {
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
	
	@Test(priority= 23,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilterAllLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 24, groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void globalFilterValidationAllLocationsGoogle() throws Exception {
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
	
	@Test(priority= 25,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilterAllLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 26,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void assignedLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.clickSearchApplyFilterBTN();
		exp.clickAssignedLocationsTab();
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 27,  groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void keywordSearchValidationAssignedLocationsGoogle() throws Exception {
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
	
	@Test(priority= 28,   groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilterAssignedLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 29,   groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void globalFilterValidationAssignedLocationsGoogle() throws Exception {
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

	@Test(priority= 30,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilterAssignedLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 31,   groups = { "smoke" }, description = "Test for Renaming Excel")
	public void unassignedLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.clickSearchApplyFilterBTN();
		exp.clickUnassignedLocationsTab();
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 32,   groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void keywordSearchValidationUnassignedLocationsGoogle() throws Exception {
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
	
	@Test(priority= 33,  groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportKeywordSearchFilterUnassignedLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}
	
	@Test(priority= 34,   groups = {
	"smoke" }, description = "Verify Manage Pages page loads after filter applied")
public void globalFilterValidationUnassignedLocationsGoogle() throws Exception {
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

	@Test(priority= 35,   groups = { "smoke" }, description = "Test for Renaming Excel")
	public void exportAfterGlobalFilterUnassignedLocationsGoogle() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.onStart();
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
		
	}

}
