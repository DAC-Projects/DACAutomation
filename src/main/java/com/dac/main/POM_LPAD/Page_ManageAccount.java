package com.dac.main.POM_LPAD;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.ExcelHandler;
import resources.IAutoconst;
import resources.Utilities;

public class Page_ManageAccount extends BasePage {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor executor;
	ExcelHandler accountData;
	
	//-----------Account Info Tab------------------
	@FindBy(xpath="//select[@id='select_Status']")
	private WebElement select_Status;
	
	@FindBy(xpath="//input[@id='txt_businessName']")
	private WebElement txt_AccountName;
	
	@FindBy(xpath="//input[@id='txt_OSAName']")
	private WebElement txt_OSAName;

	@FindBy(xpath="//input[@id='txt_OSAEmail']")
	private WebElement txt_OSAEmail;
	
	@FindBy(xpath="//input[@id='Chek_OSACheck']")
	private WebElement check_OSA;
	
	@FindBy(xpath="//select[@id='select_BusinessUnit']")
	private WebElement select_BusinessUnit;
	
	@FindBy(xpath="//select[@id='select_ParentAccount']")
	private WebElement select_ParentAccount;
	
	@FindBy(xpath="//div[@id='s2id_select_PSAAccount']")
	private WebElement btn_PSAAccount;
	
	@FindBy(xpath="//select[@id='select_countryName']")
	private WebElement select_CountryName;
	
	@FindBy(xpath="//input[@id='txt_addressName']")
	private WebElement txt_AddressLine1;
	
	@FindBy(xpath="//input[@id='txt_Unit']")
	private WebElement txt_AddressLine2;
	
	@FindBy(xpath="//input[@id='Address3Val']")
	private WebElement txt_AddressLine3;
	
	@FindBy(xpath="//input[@id='Address4Val']")
	private WebElement txt_AddressLine4;
	
	@FindBy(xpath="//input[@id='Address5Val']")
	private WebElement txt_AddressLine5;
	
	@FindBy(xpath="//input[@id='NeighborhoodVal']")
	private WebElement txt_Neighborhood;
	
	@FindBy(xpath="//input[@id='txt_cityName']")
	private WebElement txt_City;
	
	@FindBy(xpath="//select[@id='stateProvince']")
	private WebElement select_StateProvince;
	
	@FindBy(xpath="//input[@id='txt_codeName']")
	private WebElement txt_ZipCode;
	
	@FindBy(xpath="//input[@id='txt_phoneNumber']")
	private WebElement txt_PhoneNumber;
	
	@FindBy(xpath="//input[@id='txt_website']")
	private WebElement txt_Website;
	
	@FindBy(xpath="//input[@id='txt_EndDateRise']")
	private WebElement txt_StartDate;
	
	@FindBy(xpath="//input[@id='txt_EndDateStop']")
	private WebElement txt_EndDate;
	
	@FindBy(xpath="//input[@id='btn_save']")
	private WebElement btn_Save;
	
	@FindBy(xpath="//input[@id='btn_cancel']")
	private WebElement btn_Cancel;
	
	@FindBy(xpath="//a[@data-original-title='Permission Settings']")
	private WebElement btn_PermissionSettings;
	
	
	//Popup Locator
	@FindBy(xpath="//button[@data-bb-handler='OK']")
	private WebElement btnOK;
	
	@FindBy(xpath="//button[@data-bb-handler='OK']/../..//div[@class='bootbox-body']")
	private WebElement popupMessage;
	
	public Page_ManageAccount(WebDriver driver) {
		super(driver);
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	

	private void setStatus(String status) {
		uiSelect=new Select(select_Status);
		uiSelect.selectByVisibleText(status);
		wait = new WebDriverWait(driver, 30);
	}
	
	private void setAccountName(String acName) {
		String time=Utilities.getCurrentTime();
		int rn=Utilities.randomNumber();
		System.out.println(rn);
		acName=acName+rn+time;
		txt_AccountName.sendKeys(acName);
	}
	
	private void setOSAName(String name) {
		txt_OSAName.clear();
		txt_OSAName.sendKeys(name);
	}
	
	private void setOSAEmail(String name) {
		txt_OSAEmail.clear();
		txt_OSAEmail.sendKeys(name);
	}
	
	private void setOSACheck() {
		String checked=check_OSA.getAttribute("checked");
		System.out.println("Checked is "+ checked);
		if (!checked.equals("true")) {
			check_OSA.click();
		}
	}
	private void setCountry(String country) {
		uiSelect=new Select(select_CountryName);
		uiSelect.selectByVisibleText(country);
	}
	
	private void setAddressLine1(String add) {
		txt_AddressLine1.sendKeys(add);
	}
	
	private void setAddressLine2(String add) {
		txt_AddressLine2.sendKeys(add);
	}
	private void setAddressLine3(String add) {
		txt_AddressLine3.sendKeys(add);
	}
	private void setAddressLine4(String add) {
		txt_AddressLine4.sendKeys(add);
	}
	private void setAddressLine5(String add) {
		txt_AddressLine5.sendKeys(add);
	}
	
	private void setNeighborhood(String location) {
		txt_Neighborhood.sendKeys(location);
	}
	private void setCity(String location) {
		txt_City.sendKeys(location);
	}
	
	private void setState(String state) {
		uiSelect=new Select(select_StateProvince);
		uiSelect.selectByVisibleText(state);
	}
	
	private void setZipCode(String zip) {
		txt_ZipCode.sendKeys(zip);
	}
	private void setPhoneNumber(String phone) {
		txt_PhoneNumber.sendKeys(phone);
	}
	
	private void setWebSite(String site) {
		txt_Website.sendKeys(site);
	}
	private void setStartDate(String date) {
		txt_StartDate.sendKeys(date);
	}
	
	private void setEndDate(String date) {
		txt_EndDate.sendKeys(date);
	}
	public void submitAccount() {
		btn_Save.click();
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		String msg=popupMessage.getText();
		if(msg.equals("Account information successfully saved")){
			System.out.println("Account Created Successfully");
		}else {
			System.out.println("Account Creation Failed");
		}
		btnOK.click();
	}
	public void setAccountData(int excelRow) throws Exception {
		accountData = new ExcelHandler(IAutoconst.LocationDataExcelPath,"AccountInfo");
		String [][] inputData=accountData.getExcelTable();
		
		String strStatus=inputData[excelRow][0];String strAccountName=inputData[excelRow][1]; 
		String strOSAName =inputData[excelRow][2];String strOSAEmail =inputData[excelRow][3];
		String strBusinessUnit=inputData[excelRow][4]; String strParentAccount=inputData[excelRow][5];
		String strMappingAccount=inputData[excelRow][6]; String strCountry=inputData[excelRow][7];
		
		String strAddressLine1=inputData[excelRow][8]; String strAddressLine2=inputData[excelRow][9];
		String strAddressLine3=inputData[excelRow][10]; String strAddressLine4=inputData[excelRow][11];
		String strAddressLine5=inputData[excelRow][12]; String strNeighborhood=inputData[excelRow][13];
		
		String strCity=inputData[excelRow][14]; String strState=inputData[excelRow][15];
		String strZipcode=inputData[excelRow][16]; String strMainBusinessPhoneNumber=inputData[excelRow][17];
		String strWebsite=inputData[excelRow][18]; String strStartDate=inputData[excelRow][19]; 
		String strEndDate=inputData[excelRow][20]; 
		
		System.out.println(strStatus);				System.out.println(strAccountName);
		System.out.println(strOSAName);				System.out.println(strOSAEmail);
		System.out.println(strBusinessUnit);		System.out.println(strParentAccount);
		System.out.println(strMappingAccount);		System.out.println(strCountry);
		System.out.println(strAddressLine1);		System.out.println(strAddressLine2);
		System.out.println(strAddressLine3);		System.out.println(strAddressLine4);
		System.out.println(strAddressLine5);		System.out.println(strNeighborhood);
		System.out.println(strCity);				System.out.println(strState);
		System.out.println(strZipcode);				System.out.println(strMainBusinessPhoneNumber);
		System.out.println(strWebsite);				System.out.println(strStartDate);
		System.out.println(strEndDate);
		
		setAccountName(strAccountName);
		if(!strOSAName.equals(null)) {
			setOSAName(strOSAName);
		}
		if(!strOSAEmail.equals(null)) {
			setOSAEmail(strOSAEmail);
			
			setOSACheck();
		}
		
		setCountry(strCountry);
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(txt_AddressLine1));
		setAddressLine1(strAddressLine1);
		
		
		if(txt_AddressLine2.isDisplayed()) {
			setAddressLine2(strAddressLine2);
		}
		if(txt_AddressLine3.isDisplayed()) {
			setAddressLine3(strAddressLine3);
		}
		if(txt_AddressLine4.isDisplayed()){
			setAddressLine4(strAddressLine4);
		}
		if(txt_AddressLine5.isDisplayed()) {
			setAddressLine5(strAddressLine5);
		}
		if(txt_Neighborhood.isDisplayed()) {
			setNeighborhood(strNeighborhood);
		}
		
		setCity(strCity);
		
		if(select_StateProvince.isDisplayed()) {
			setState(strState);
		}
		if(txt_ZipCode.isDisplayed()) {
			setZipCode(strZipcode);
		}
		setPhoneNumber(strMainBusinessPhoneNumber);
		
	}
	public String getAccountName() throws InterruptedException {
		wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(txt_AccountName));
		Thread.sleep(2000);
		txt_AccountName.click();
		String name=txt_AccountName.getAttribute("value");
//		System.out.println(name);
		return name;
	}
}
