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

import com.dac.testcases.LPAD.LaunchLPAD;

import resources.ExcelHandler;
import resources.Utilities;

public class Page_LocationBasicInfoTab extends LaunchLPAD {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor executor;
	ExcelHandler basic;
	String BName="";
	//-----------Basic Info Tab------------------
	@FindBy(xpath="//*[@id='lblLocationNumber']")
	private WebElement locationNumber;
	
	@FindBy(xpath="//*[@id='selectAccountName']")
	private WebElement selectAccountName;
	
	@FindBy(id="ReferenceID")
	private WebElement elReferenceCode;
	
	@FindBy(id="ReferenceCode2")
	private WebElement elReferenceCode2;
	
	@FindBy(id="LongBusinessName")
	private WebElement elBusinessName;
	
	@FindBy(id="BusinessName")
	private WebElement elShortBusinessName;
	
	@FindBy(id="Country")
	private WebElement selectCountry;
	
	@FindBy(id="AccountAddressFill")
	private WebElement chkAccountAddressFill;
	
	@FindBy(id="LocationAddress")
	private WebElement elAddressLine1;
	
	@FindBy(id="Address1")
	private WebElement elAddressLine2;
	
	@FindBy(id="Address3")
	private WebElement elAddressLine3;
	
	@FindBy(id="Address4")
	private WebElement elAddressLine4;
	
	@FindBy(id="Address5")
	private WebElement elAddressLine5;
	
	@FindBy(id="Neighborhood")
	private WebElement elNeighborhood;
	
	@FindBy(id="City")
	private WebElement elCity;
	
	@FindBy(id="State")
	private WebElement selectState;
	
	@FindBy(id="ZipCode")
	private WebElement elZipCode;
	
	@FindBy(id="PhoneNumber")
	private WebElement elMainBusinessPhoneNumber;
	
	@FindBy(id="PrimaryLanguage")
	private WebElement selectPrimaryLanguage;
	
	
	public Page_LocationBasicInfoTab(WebDriver driver) {
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
		
	}
	

	public void setAccountName(String AccountName) {
		uiSelect=new Select(selectAccountName);
		uiSelect.selectByVisibleText(AccountName);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("GoogleMaps"))));
		
	}
	
	public void setReferenceCode(String Code) {
		elReferenceCode.sendKeys(Code);
	}
	
	public void setReferenceCode2(String Code) {
		elReferenceCode2.sendKeys(Code);
	}
	private String generateBName() {
		String time=Utilities.getCurrentTime();
		return time;
	}
	public void setBusinessName(String Name) {
//		String time=Utilities.getCurrentTime();
		String tmp=generateBName();
		System.out.println(Name);
		Name=Name+tmp;
		System.out.println(Name);
		elBusinessName.sendKeys(Name);
	}
	
	public void setShortBusinessName(String Name) {
//		String time=Utilities.getCurrentTime();
		Name=Name+BName;
		System.out.println(Name);
		elShortBusinessName.sendKeys(Name);
	}
	
	public String getSBName() {
		String name=elShortBusinessName.getAttribute("value");
		return name;
	}
	public void setCountry(String country) {
		uiSelect=new Select(selectCountry);
		uiSelect.selectByVisibleText(country);
	}
	
	public void setAddress1(String address) {
		elAddressLine1.sendKeys(address);
	}
	public void setAddress2(String address) {
		elAddressLine2.sendKeys(address);
	}
	
	public void setAddress3(String address) {
		elAddressLine3.sendKeys(address);
	}
	public void setAddress4(String address) {
		elAddressLine4.sendKeys(address);
	}
	public void setAddress5(String address) {
		elAddressLine5.sendKeys(address);
	}
	
	public void setNeighborhood(String location) {
		elNeighborhood.sendKeys(location);
	}
	public void setCity(String location) {
		elCity.sendKeys(location);
	}
	
	public void setState(String state) {
		uiSelect=new Select(selectState);
		uiSelect.selectByVisibleText(state);
	}
	
	public void setZipCode(String zip) {
		elZipCode.sendKeys(zip);
	}
	public void setPhoneNumber(String phone) {
		elMainBusinessPhoneNumber.sendKeys(phone);
	}
	
	public void setPrimaryLanguage(String language) {
		uiSelect=new Select(selectPrimaryLanguage);
		uiSelect.selectByVisibleText(language);
	}
	
	public void fillBasicInfoData(int excelRow) throws Exception {
		basic = new ExcelHandler(LocationDataExcelPath,"BasicInfo");
		String [][] inputData=basic.getExcelTable();
		
		String AccountName=inputData[excelRow][0]; String ReferenceCode =inputData[excelRow][1];
		String ReferenceCode2=inputData[excelRow][2]; String BusinessName=inputData[excelRow][3];
		String ShortBusinessName=inputData[excelRow][4]; String Country=inputData[excelRow][5];
		String AddressLine1=inputData[excelRow][6]; String AddressLine2=inputData[excelRow][7];
		String AddressLine3=inputData[excelRow][8]; String AddressLine4=inputData[excelRow][9];
		String AddressLine5=inputData[excelRow][10]; String Neighborhood=inputData[excelRow][11];
		String City=inputData[excelRow][12]; String State=inputData[excelRow][13];
		String Zipcode=inputData[excelRow][14]; String MainBusinessPhoneNumber=inputData[excelRow][15];
		String PrimaryLanguage=inputData[excelRow][16]; 
		
		System.out.println(AccountName);	System.out.println(ReferenceCode);
		System.out.println(ReferenceCode2);		System.out.println(BusinessName);
		System.out.println(ShortBusinessName);		System.out.println(Country);
		System.out.println(AddressLine1);		System.out.println(AddressLine2);
		System.out.println(AddressLine3);		System.out.println(AddressLine4);
		System.out.println(AddressLine5);		System.out.println(Neighborhood);
		System.out.println(City);		System.out.println(State);
		System.out.println(Zipcode);		System.out.println(MainBusinessPhoneNumber);
		System.out.println(PrimaryLanguage);
		
		setAccountName(AccountName);
		setReferenceCode(ReferenceCode);
		setReferenceCode2(ReferenceCode2);
		setBusinessName(BusinessName);
		setShortBusinessName(ShortBusinessName);
		setCountry(Country);
		wait.until(ExpectedConditions.visibilityOfAllElements(elAddressLine1));
		setAddress1(AddressLine1);
		if(elAddressLine2.isDisplayed()) {
			setAddress2(AddressLine1);
		}
		if(elAddressLine3.isDisplayed()) {
			setAddress3(AddressLine3);
		}
		if(elAddressLine4.isDisplayed()){
			setAddress4(AddressLine4);
		}
		if(elAddressLine5.isDisplayed()) {
			setAddress5(AddressLine5);
		}
		if(elNeighborhood.isDisplayed()) {
			setNeighborhood(Neighborhood);
		}
		
		setCity(City);
		
		if(selectState.isDisplayed()) {
			setState(State);
		}
		if(elZipCode.isDisplayed()) {
			setZipCode(Zipcode);
		}
		setPhoneNumber(MainBusinessPhoneNumber);
		setPrimaryLanguage(PrimaryLanguage);
		
		
		
	}
	public String getLocationNumber() {
		String number= locationNumber.getText();
//		System.out.println("Location Number is: " + number);
		
		return number;
	}
}
