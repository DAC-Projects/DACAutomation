package com.dac.main.POM_LPAD;

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

public class Page_LocationBasicInfoTab extends LaunchLPAD {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor executor;
	ExcelHandler excel;
	//-----------Basic Info Tab------------------
	@FindBy(xpath="//*[@id='lblLocationNumber']")
	private WebElement locationNumber;
	
	@FindBy(xpath="//*[@id='selectAccountName']")
	private WebElement selectAccountName;
	
	@FindBy(id="ReferenceID")
	private WebElement ReferenceCode;
	
	@FindBy(id="ReferenceCode2")
	private WebElement ReferenceCode2;
	
	@FindBy(id="LongBusinessName")
	private WebElement BusinessName;
	
	@FindBy(id="BusinessName")
	private WebElement ShortBusinessName;
	
	@FindBy(id="Country")
	private WebElement selectCountry;
	
	@FindBy(id="AccountAddressFill")
	private WebElement chkAccountAddressFill;
	
	@FindBy(id="LocationAddress")
	private WebElement AddressLine1;
	
	@FindBy(id="Address1")
	private WebElement AddressLine2;
	
	@FindBy(id="Address3")
	private WebElement AddressLine3;
	
	@FindBy(id="Address4")
	private WebElement AddressLine4;
	
	@FindBy(id="Address5")
	private WebElement AddressLine5;
	
	@FindBy(id="Neighborhood")
	private WebElement Neighborhood;
	
	@FindBy(id="City")
	private WebElement City;
	
	@FindBy(id="State")
	private WebElement selectState;
	
	@FindBy(id="ZipCode")
	private WebElement ZipCode;
	
	@FindBy(id="PhoneNumber")
	private WebElement MainBusinessPhoneNumber;
	
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
		ReferenceCode.sendKeys(Code);
	}
	
	public void setReferenceCode2(String Code) {
		ReferenceCode2.sendKeys(Code);
	}
	
	public void setBusinessName(String Name) {
		BusinessName.sendKeys(Name);
	}
	
	public void setShortBusinessName(String Name) {
		ShortBusinessName.sendKeys(Name);
	}
	
	public void setCountry(String country) {
		uiSelect=new Select(selectCountry);
		uiSelect.selectByVisibleText(country);
	}
	
	public void setAddress1(String address) {
		AddressLine1.sendKeys(address);
	}
	public void setAddress2(String address) {
		AddressLine2.sendKeys(address);
	}
	
	public void setAddress3(String address) {
		AddressLine3.sendKeys(address);
	}
	public void setAddress4(String address) {
		AddressLine4.sendKeys(address);
	}
	public void setAddress5(String address) {
		AddressLine5.sendKeys(address);
	}
	
	public void setNeighborhood(String location) {
		Neighborhood.sendKeys(location);
	}
	public void setCity(String location) {
		City.sendKeys(location);
	}
	
	public void setState(String state) {
		uiSelect=new Select(selectState);
		uiSelect.selectByVisibleText(state);
	}
	
	public void setZipCode(String zip) {
		ZipCode.sendKeys(zip);
	}
	public void setPhoneNumber(String phone) {
		MainBusinessPhoneNumber.sendKeys(phone);
	}
	
	public void setPrimaryLanguage(String language) {
		uiSelect=new Select(selectPrimaryLanguage);
		uiSelect.selectByVisibleText(language);
	}
	
	public void fillBasicInfoData(int excelRow) throws Exception {
	
		String [][] inputData=new ExcelHandler(LocationDataExcelPath, "BasicInfo").getExcelTable();
		
		String AccountName=inputData[excelRow][0]; String ReferenceCode1 =inputData[excelRow][1];
		String ReferenceCode2=inputData[excelRow][2]; String BusinessName=inputData[excelRow][3];
		String ShortBusinessName=inputData[excelRow][4]; String Country=inputData[excelRow][5];
		String Address1=inputData[excelRow][6]; String Address2=inputData[excelRow][7];
		String Address3=inputData[excelRow][8]; String Address4=inputData[excelRow][9];
		String Address5=inputData[excelRow][10]; String strNeighborhood=inputData[excelRow][11];
		String City=inputData[excelRow][12]; String state=inputData[excelRow][13];
		String strZipCode=inputData[excelRow][14]; String PhoneNumber=inputData[excelRow][15];
		String Primarylanguage=inputData[excelRow][16]; 
		
		System.out.println(AccountName);	System.out.println(ReferenceCode1);
		System.out.println(ReferenceCode2);		System.out.println(BusinessName);
		System.out.println(ShortBusinessName);		System.out.println(Country);
		System.out.println(Address1);		System.out.println(Address2);
		System.out.println(Address3);		System.out.println(Address4);
		System.out.println(Address5);		System.out.println(strNeighborhood);
		System.out.println(City);		System.out.println(state);
		System.out.println(strZipCode);		System.out.println(PhoneNumber);
		System.out.println(Primarylanguage);
		
		setAccountName(AccountName);
		setReferenceCode(ReferenceCode1);
		setReferenceCode2(ReferenceCode2);
		setBusinessName(BusinessName);
		setShortBusinessName(ShortBusinessName);
		setCountry(Country);
		wait.until(ExpectedConditions.visibilityOfAllElements(AddressLine1));
		setAddress1(Address1);
		if(AddressLine2.isDisplayed()) {
			setAddress2(Address2);
		}
		if(AddressLine3.isDisplayed()) {
			setAddress3(Address3);
		}
		if(AddressLine4.isDisplayed()){
			setAddress4(Address4);
		}
		if(AddressLine5.isDisplayed()) {
			setAddress5(Address5);
		}
		if(Neighborhood.isDisplayed()) {
			setNeighborhood(strNeighborhood);
		}
		
		setCity(City);
		
		if(selectState.isDisplayed()) {
			setState(state);
		}
		if(ZipCode.isDisplayed()) {
			setZipCode(strZipCode);
		}
		setPhoneNumber(PhoneNumber);
		setPrimaryLanguage(Primarylanguage);
		
		
		
	}
	public String getLocationNumber() {
		String number= locationNumber.getText();
//		System.out.println("Location Number is: " + number);
		
		return number;
	}
}
