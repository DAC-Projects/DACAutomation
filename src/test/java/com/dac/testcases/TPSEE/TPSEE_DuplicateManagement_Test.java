package com.dac.testcases.TPSEE;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	public static String time_Stamp;
	
	public void time_get() {
		time_Stamp=timeStamp();
		System.out.println("Getting time"+time_Stamp);
	}
	
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Timestamp is :"+formattedDate);
		return formattedDate;
		
	}
	
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
				"https://www.google.com/maps/place/?q=place_id:ChIJMQ6lFrfW1IkRFe1flYsZ06A");
		addEvidence(CurrentState.getDriver(), "To Add Duplicate Listing", "yes");
	}

	public static WebDriver driver;
	WebDriverWait wait;
	String url = "https://beta-dtc-web.azurewebsites.net/";

	/**
	 * Test to Launch New Web Browser for DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void launchBrowser() throws Exception {
		WebDriverManager.chromedriver().version("83.0.4103.39").setup();
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
	@Test(priority = 5)
	public void LoginDTC() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		dtcLogin.submitLogin("adevaraj@dacgroup.com", "laptop@123");
		dtcLogin.pressYesKey();
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		System.out.println(pageTitle);
		addEvidence(driver, "Test to Login to DTC", "yes");
	}

	/**
	 * Test to change the status to new in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 6)
	public void DTC_New() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 1;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "Change status to new", "yes");
	}

	/**
	 * Test to change the status to In progress in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 7)
	public void DTC_Inprogress() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 2;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "change status to In Progress", "yes");
	}

	@Test(priority = 8)
	public void verifyInProgressPendingTab() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" +LocNum);
		String Status = wb.getCellValue(1, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The status is : "+Status);
		data.VerifyFix_PendingTab(LocNum, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To verify New Status of Location in Pending Tab", "yes");
	}
	
	/**
	 * Test to change the status to merge
	 * 
	 * @throws Exception
	 */

	@Test(priority = 9)
	public void DTC_Merged() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 3;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "Test to merge in DTC", "yes");
	}

	/**
	 * To verify status merge
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10)
	public void verifyStatus_Merged() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(3, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Merged Status", "yes");
	}

	/**
	 * Test to change status to Supressed in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 11)
	public void DTC_Suppressed() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 4;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To Supress the Location", "yes");
	}

	/**
	 * Test to verify status Supressed in TSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 12)
	public void verifyStatus_Suppressed() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(4, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Suppressed Status", "yes");
	}

	/**
	 * Test to change the status to not a duplicate in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 13)
	public void DTC_Notaduplicate() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 5;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To change status to Not a Duplicate", "yes");
	}

	/**
	 * Test to verify the status to not a duplicate in TSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 14)
	public void verifyStatus_Notaduplicate() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(5, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Not a Duplicate Status", "yes");
	}

	/**
	 * Test to change the status to Unable to process in DTC
	 * 
	 * @throws Exception
	 */
	@Test(priority = 15)
	public void DTC_Unabletoprocess() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 6;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To change the status to Unable to process", "yes");
	}

	/**
	 * Test to verify the status to Unable to process in TSEE
	 * 
	 * @throws Exception
	 */
	@Test(priority = 16)
	public void verifyStatus_Unabletoprocess() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(6, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Status Unable to 'process", "yes");
	}
	
	@Test(priority = 17)
	public void DTC_Delete() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 7;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To change the status to Unable to process", "yes");
	}
	
	@Test(priority = 18)
	public void verifyStatus_Deleted() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(7, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Status Unable to 'process", "yes");
	}
	
	/**
	 * To Add Potential Duplicate manually
	 * 
	 * @throws Exception
	 */
	@Test(priority = 19)
	public void AddDupList_Toverify_Ignore() throws Exception {
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
	
	
	@Test(priority = 20)
	public void verifyIgnore_PendingTab() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		DTC_Navigation navi = new DTC_Navigation(driver);
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocNum);
		String Status = wb.getCellValue(7, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);	
		navi.Count_check(LocNum);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		data.VerifyIgnore_PendingTab(LocNum);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		navi.Count_check_ignore(LocNum);
		addEvidence(CurrentState.getDriver(), "To verify ignore scenario", "yes");
		driver.close();
		driver.quit();
	}
	
/*	@Test(priority = 21)
	public void verifyPotentialFix() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocationNumber = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocationNumber);
		data.verifyfixPot_Dup(LocationNumber);
		addEvidence(CurrentState.getDriver(), "To take action on Potential Duplicate Tab", "yes");
	}

	*//**
	 * Test to change the status to In progress in DTC
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 22)
	public void DTC__Pot_Inprogress() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 2;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "change status to In Progress", "yes");
	}

	@Test(priority = 23)
	public void verify__Pot_InProgressPendingTab() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocNum = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" +LocNum);
		String Status = wb.getCellValue(1, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The status is : "+Status);
		data.VerifyFix_PendingTab(LocNum, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To verify New Status of Location in Pending Tab", "yes");
	}
	
	*//**
	 * Test to change the status to merge
	 * 
	 * @throws Exception
	 *//*

	@Test(priority = 24)
	public void DTC__Pot__Merged() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 3;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "Test to merge in DTC", "yes");
	}

	*//**
	 * To verify status merge
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 25)
	public void verify__Pot_Status_Merged() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(3, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Merged Status", "yes");
	}

	*//**
	 * Test to change status to Supressed in DTC
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 26)
	public void DTC_Pot_Suppressed() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 4;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To Supress the Location", "yes");
	}

	*//**
	 * Test to verify status Supressed in TSEE
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 27)
	public void verify_Pot_Status_Suppressed() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(4, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Suppressed Status", "yes");
	}

	*//**
	 * Test to change the status to not a duplicate in DTC
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 28)
	public void DTC_Pot_Notaduplicate() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 5;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To change status to Not a Duplicate", "yes");
	}

	*//**
	 * Test to verify the status to not a duplicate in TSEE
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 29)
	public void verify_Pot_Status_Notaduplicate() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(5, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Not a Duplicate Status", "yes");
	}

	*//**
	 * Test to change the status to Unable to process in DTC
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 30)
	public void DTC_Pot_Unabletoprocess() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 6;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To change the status to Unable to process", "yes");
	}

	*//**
	 * Test to verify the status to Unable to process in TSEE
	 * 
	 * @throws Exception
	 *//*
	@Test(priority = 31)
	public void verify_Pot_Status_Unabletoprocess() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(6, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Status Unable to 'process", "yes");
	}
	
	@Test(priority = 32)
	public void DTC_Pot__Delete() throws Exception {
		DTC_Duplicate_Management dtcLogin = new DTC_Duplicate_Management(driver);
		DTC_Navigation navi = new DTC_Navigation(driver);
		String pageTitle = dtcLogin.getTitle(driver);
		navi.Dup();
		time_Stamp = timeStamp();
		int i = 7;
		navi.excel(i,time_Stamp);
		System.out.println(pageTitle);
		addEvidence(driver, "To change the status to Unable to process", "yes");
		driver.close();
		driver.quit();
	}
	
	@Test(priority = 33)
	public void verify_Pot_Status_Deleted() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String PhNumber = wb.getCellValue(1, wb.seacrh_pattern("Phone Number ", 0).get(0).intValue());
		System.out.println("The Phone Number is :" + PhNumber);
		String Status = wb.getCellValue(7, wb.seacrh_pattern("Status", 0).get(0).intValue());
		System.out.println("The Status is :" + Status);
		data.verifyCompleteTab(PhNumber, Status,time_Stamp);
		addEvidence(CurrentState.getDriver(), "To Verify Status Unable to 'process", "yes");
	}
	
	@Test(priority = 34)
	public void verifyPotentialDup() throws Exception {
		data = new TPSEE_DuplicateManagement_Page(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Duplicate_Management");
		String LocationNumber = wb.getCellValue(1, wb.seacrh_pattern("Location Number", 0).get(0).intValue());
		System.out.println("The Location Number is :" + LocationNumber);		
		data.Pot_Dup(LocationNumber);
		addEvidence(CurrentState.getDriver(), "To take action on Potential Duplicate Tab", "yes");
		
	}*/
}