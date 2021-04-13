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

import resources.ExcelHandler;
import resources.IAutoconst;
import resources.Utilities;

public class Page_LocationBasicInfoTab extends BasePage {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor executor;
	ExcelHandler data;
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
	
	@FindBy(id="Latitude")
	private WebElement Latitude;
	
	@FindBy(id="Longitude")
	private WebElement Longitude;
	
	
	
	public Page_LocationBasicInfoTab(WebDriver driver) {
		super(driver);
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
		elShortBusinessName.sendKeys(Name);
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
	public String getLatitude() {
		String lat=Latitude.getAttribute("value");
		return lat;
	}
	
	public String getLongitude() {
		String longi=Longitude.getAttribute("value");
		return longi;
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
	
	public void fillBasicInfoData(int row) throws Exception {
		data = new ExcelHandler(IAutoconst.LocationDataExcelPath,"BasicInfo");
//		String [][] inputData=data.getExcelTable();
		
		String AccountName  	= data.getCellValue(row, data.seacrh_pattern("AccountName", 0).get(0).intValue());
		String ReferenceCode  	= data.getCellValue(row, data.seacrh_pattern("ReferenceCode", 0).get(0).intValue());
		String ReferenceCode2  	= data.getCellValue(row, data.seacrh_pattern("ReferenceCode2", 0).get(0).intValue());
		String BusinessName  	= data.getCellValue(row, data.seacrh_pattern("BusinessName", 0).get(0).intValue());
		String ShortBusinessName= data.getCellValue(row, data.seacrh_pattern("ShortBusinessName", 0).get(0).intValue());
		String Country  		= data.getCellValue(row, data.seacrh_pattern("Country", 0).get(0).intValue());
		String AddressLine1  	= data.getCellValue(row, data.seacrh_pattern("AddressLine1", 0).get(0).intValue());
		String AddressLine2  	= data.getCellValue(row, data.seacrh_pattern("AddressLine2", 0).get(0).intValue());
		String AddressLine3  	= data.getCellValue(row, data.seacrh_pattern("AddressLine3", 0).get(0).intValue());
		String AddressLine4  	= data.getCellValue(row, data.seacrh_pattern("AddressLine4", 0).get(0).intValue());
		String AddressLine5  	= data.getCellValue(row, data.seacrh_pattern("AddressLine5", 0).get(0).intValue());
		String Neighborhood  	= data.getCellValue(row, data.seacrh_pattern("Neighborhood", 0).get(0).intValue());
		String City  			= data.getCellValue(row, data.seacrh_pattern("City", 0).get(0).intValue());
		String State 			= data.getCellValue(row, data.seacrh_pattern("State", 0).get(0).intValue());
		String Zipcode  		= data.getCellValue(row, data.seacrh_pattern("Zipcode", 0).get(0).intValue());
		String MainBusinessPhoneNumber  = data.getCellValue(row, data.seacrh_pattern("MainBusinessPhoneNumber", 0).get(0).intValue());
		String PrimaryLanguage  = data.getCellValue(row, data.seacrh_pattern("PrimaryLanguage", 0).get(0).intValue());
		
		
		System.out.println(AccountName);	System.out.println(ReferenceCode);
		System.out.println(ReferenceCode2);		System.out.println(BusinessName);
		System.out.println(ShortBusinessName);		System.out.println(Country);
		System.out.println(AddressLine1);		System.out.println(AddressLine2);
		System.out.println(AddressLine3);		System.out.println(AddressLine4);
		System.out.println(AddressLine5);		System.out.println(Neighborhood);
		System.out.println(City);		System.out.println(State);
		System.out.println(Zipcode);		System.out.println(MainBusinessPhoneNumber);
		System.out.println(PrimaryLanguage);
		Thread.sleep(5000);
		setAccountName(AccountName);
		setReferenceCode(ReferenceCode);
		setReferenceCode2(ReferenceCode2);
		setBusinessName(BusinessName);
//		setShortBusinessName(ShortBusinessName);
		setCountry(Country);
		Thread.sleep(5000);
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
		System.out.println("Location Number is: " + number);
		
		return number;
	}
}
