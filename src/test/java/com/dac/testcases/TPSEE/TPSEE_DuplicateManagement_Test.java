package com.dac.testcases.TPSEE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_DTC.DTC_Duplicate_Management;
import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_TPSEE.TPSEE_DuplicateManagement_Page;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_DuplicateManagement_Test extends BaseClass {

	TPSEE_DuplicateManagement_Page data;
	Navigationpage np;

	/**
	 * To Navigate to Duplicate Management Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void NavigateToDupManagementPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToDuplicateManagement();
		CurrentState.getLogger().log(Status.PASS, "Navigated to Duplicate Managment Page");
		addEvidence(CurrentState.getDriver(), "To Navigate to Duplicate Management Page", "yes");
	}

	/**
	 * To Verify Title and Title Text
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2, groups = {"smoke" }, description = "Test for verifying title and description of report")
	public void VerifyTitleTxt() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		data.VerifyTitlenText();
		addEvidence(CurrentState.getDriver(), "To Verify Title and Title Text", "yes");
	}

	/**
	 * To Add Potential Duplicate manually
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void AddDupList() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Url = wb.getCellValue(1, wb.seacrh_pattern("URL", 0).get(0).intValue());
		System.out.println("The URL Provided is :" + Url);
		data.AddPotentialDuplicate(PhNumber,
				"https://www.google.com/maps/place/?q=place_id:ChIJrTehGtCVwokRrYtNXQff1NI");
		addEvidence(CurrentState.getDriver(), "To Add Duplicate Listing", "yes");
	}

	/**
	 * Test to take action on Duplicate Listing
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void TakeActiononDupList() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Action = wb.getCellValue(1, wb.seacrh_pattern("Action Value", 0).get(0).intValue());
		System.out.println("The action preformed is : " + Action);
		data.TakeAction(PhNumber, Action);
		addEvidence(CurrentState.getDriver(), "To take action on listing URL's", "yes");
	}

	public static WebDriver driver;
	WebDriverWait wait;
	String url = "https://beta-dtc-web.azurewebsites.net/";

	/**
	 * Test to Launch New Web Browser for DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5)
	public void launchBrowser() throws Exception {
		WebDriverManager.chromedriver().version("80.0.3987.16").setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		addEvidence(driver, "To Launch new browser and navigate to DTC", "yes");
	}

	/**
	 * Test to Login to DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6)
	public void LoginDTC() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		dtcLogin.submitLogin("adevaraj@dacgroup.com", "lockdown@123");
		dtcLogin.pressYesKey();
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 1;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Test to Login to DTC", "yes");
	}

	/**
	 * Test to change the status to merge
	 * 
	 * @throws Exception
	 */

	@Test(priority = 7)
	public void DTC_Merged() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 2;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Test to merge in DTC", "yes");
	}

	/**
	 * To verify status merge
	 * 
	 * @throws Exception
	 */
	@Test(priority = 8)
	public void verifyStatus_Merged() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(2, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Merged Status", "yes");
	}

	/**
	 * Test to change status to Supressed in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 9)
	public void DTC_Suppressed() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 3;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "To Supress the Location", "yes");
	}

	/**
	 * Test to verify status Supressed in TSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10)
	public void verifyStatus_Suppressed() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(3, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Suppressed Status", "yes");
	}

	/**
	 * Test to change the status to not a duplicate in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void DTC_Notaduplicate() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 4;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "To change status to Not a Duplicate", "yes");
	}

	/**
	 * Test to verify the status to not a duplicate in TSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 12)
	public void verifyStatus_Notaduplicate() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(4, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Not a Duplicate Status", "yes");
	}

	/**
	 * Test to change the status to Unable to process in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void DTC_Unabletoprocess() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 5;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "To change the status to Unable to process", "yes");
	}

	/**
	 * Test to verify the status to Unable to process in TSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 14)
	public void verifyStatus_Unabletoprocess() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(5, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Status Unable to 'process", "yes");
	}

	/**
	 * Test to change the status to new in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void DTC_New() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 6;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to new", "yes");
	}

	/**
	 * Test to change the status to In progress in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 16)
	public void DTC_Inprogress() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 7;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "change status to In Progress", "yes");
	}

	/**
	 * Test to verify Ignore Scenario in Pot_Dup Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 17)
	public void verifyPotentialDup() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocationNumber = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocationNumber);
		data.Pot_Dup(LocationNumber);
		addEvidence(CurrentState.getDriver(), "To take action on Potential Duplicate Tab", "yes");

	}
	
	@Test(priority = 18)
	public void verifyPotentialFix() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocationNumber = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocationNumber);
		data.verifyfixPot_Dup(LocationNumber);
		addEvidence(CurrentState.getDriver(), "To take action on Potential Duplicate Tab", "yes");
	}

	/**
	 * Test to verify Fix in Pot_Dup and Ignore the same with Pending Tab
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void PendingTabVerification() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocationNumber = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocationNumber);
		data.VerifyIgnore_PendingTab(LocationNumber);
		addEvidence(CurrentState.getDriver(), "To verify potential duplicate in Pending Tab", "yes");
	}

	/**
	 * Test to change the status to Merged in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 20)
	public void DTC_Merged_Pot_Dup() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 2;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to Merged", "yes");
	}

	/**
	 * Test to verify the status Merged in TPSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 20)
	public void verifyStatus_Merged_Pot_Dup() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(2, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Status Merged", "yes");
	}

	/**
	 * Test to change the status to Suppress in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 21)
	public void DTC_Suppressed_Pot_Dup() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 3;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to Suppressed", "yes");
	}

	/**
	 * Test to verify the status to Suppress in TPSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 22)
	public void verifyStatus_Suppressed_Pot_Dup() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(3, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Status Suppressed", "yes");
	}

	/**
	 * Test to change the status to Not a Duplicate in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 23)
	public void DTC_Notaduplicate_Pot_Dup() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 4;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to Not a Duplicate", "yes");
	}

	/**
	 * Test to verify the status to Not a Duplicate in TPSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 24)
	public void verifyStatus_Notaduplicate_Pot_Dup() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(4, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Status Not a Duplicate", "yes");
	}

	/**
	 * Test to change the status to Unable to process in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 25)
	public void DTC_Unabletoprocess_Pot_Dup() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 5;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to Unable to process", "yes");
	}

	/**
	 * Test to verify the status to Unable to process in TPSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 26)
	public void verifyStatus_Unabletoprocess_Pot_Dup() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(5, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status);
		addEvidence(CurrentState.getDriver(), "To Verify Status Unable to Process", "yes");
	}

	/**
	 * Test to change the status to New in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 27)
	public void DTC_New_Pot_Dup() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 6;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to new", "yes");
	}

	/**
	 * Test to change the status to In Progress in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 28)
	public void DTC_Inprogress_Pot_Dup() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		int i = 7;
		navi.excel(i);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to In Progress", "yes");
		driver.close();
	}

}