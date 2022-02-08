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

public class Page_PresetLocationValues extends BasePage {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor executor;
	ExcelHandler presetData;
	
	//-----------Location Preset Values------------------
	@FindBy(xpath="//select[@id='massupdate']")
	private WebElement select_BulkPreset;
	
	@FindBy(xpath="//select[@id='allPresetDropdown']")
	private WebElement select_PresetValues;
	
	@FindBy(xpath="//input[@id='btnAddPresets']")
	private WebElement btn_AddPresetValues;
	
	@FindBy(xpath="//input[@id='export-preset-values']")
	private WebElement btn_Export;
	
	//------------------Tabs------------
	
	@FindBy(xpath="//*[@id='liStep1']")
	private WebElement tabBasicInfo;
	
	@FindBy(xpath="//*[@id='liStep2']")
	private WebElement tabBusinessInfo;
	
	@FindBy(xpath="//*[@id='liStep3']")
	private WebElement tabDetails;
	
	@FindBy(xpath="//*[@id='liStep4']")
	private WebElement tabSitespcific;
	
	@FindBy(xpath="//*[@id='liStep5']")
	private WebElement tabCustom;
	
	//------------------------------------------
	
	@FindBy(xpath="//select[@id='selectAccountName']")
	private WebElement select_AccountName;
	
	@FindBy(xpath="//select[@id='ReferenceID']")
	private WebElement select_ReferenceCode;
	
	@FindBy(xpath="//input[@id='ReferenceID']")
	private WebElement txt_ReferenceCode;
	
	@FindBy(xpath="//select[@id='ReferenceCode2']")
	private WebElement select_ReferenceCode2;

	@FindBy(xpath="//input[@id='ReferenceCode2']")
	private WebElement txt_ReferenceCode2;
	
	@FindBy(xpath="//select[@id='LongBusinessName']")
	private WebElement select_BusinessName;
	
	@FindBy(xpath="//input[@id='LongBusinessNameID']")
	private WebElement txt_BusinessName;
		
	@FindBy(xpath="//select[@id='BusinessName']")
	private WebElement select_shortBusinessName;
	
	@FindBy(xpath="//input[@id='ShortBusinessNameID']")
	private WebElement txt_ShortBusinessName;
	
	@FindBy(xpath="//select[@id='LocationAddress']")
	private WebElement select_Address1;
	
	@FindBy(xpath="//select[@id='select_hideaddress']")
	private WebElement select_HideAddress;
	
	@FindBy(xpath="//select[@id='Address1']")
	private WebElement select_Address2;
	
	@FindBy(xpath="//select[@id='Address3']")
	private WebElement select_Address3;
	
	@FindBy(xpath="//select[@id='Address4']")
	private WebElement select_Address4;
	
	@FindBy(xpath="//select[@id='Address5']")
	private WebElement select_Address5;
	
	@FindBy(xpath="//select[@id='Neighborhood']")
	private WebElement select_Neighborhood;
	
	@FindBy(xpath="//select[@id='City']")
	private WebElement select_City;
	
	@FindBy(xpath="//select[@id='State']")
	private WebElement select_State;
	
	@FindBy(xpath="//select[@id='ZipCode']")
	private WebElement select_ZipCode;
	
	@FindBy(xpath="//select[@id='PhoneNumber']")
	private WebElement select_PhoneNumber;
	
	@FindBy(xpath="//select[@id='select_hidephonenumber']")
	private WebElement select_hidephonenumber;
	
	@FindBy(xpath="//select[@id='PrimaryLanguage']")
	private WebElement select_PrimaryLanguage;
	
	@FindBy(xpath="//select[@id='PrimaryLanguageID']")
	private WebElement select_PrimaryLanguageID;
	
	@FindBy(xpath="//select[@id='ReportGroup']")
	private WebElement select_ReportGroup;
	
	@FindBy(xpath="//input[@id='ReportGroupID']")
	private WebElement txt_ReportGroupID;
	
	@FindBy(xpath="//select[@id='LatAndLong']")
	private WebElement select_LatAndLong;
	
	@FindBy(xpath="//select[@id='Pinmarker']")
	private WebElement select_Pinmarker;
		
	@FindBy(xpath="//a[@id='preset-field-save']")
	private WebElement btn_submit;
	
	
	//**************//	
		
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
	
	public Page_PresetLocationValues(WebDriver driver) {
		super(driver);
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	
	


	/************/
	private void setTextBoxValue(String vaalue,WebElement textbox) {
		textbox.clear();
		textbox.sendKeys(vaalue);
	}
	
		
	private void selectDropdownValue(String selValue, WebElement selEelement) {
		uiSelect=new Select(selEelement);
		uiSelect.selectByVisibleText(selValue);
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
	
	public void setPresettDataBasicInfo() throws Exception {
		presetData = new ExcelHandler(IAutoconst.PresetLocationDataPath,"PresetLocationData");
//		String [][] inputData=presetData.getExcelTable();
		int excelRow=1;
		String BusinessName  	= presetData.getCellValue(excelRow, presetData.seacrh_pattern("Business Name", 0).get(0).intValue());
		String ShortBusinessName  	= presetData.getCellValue(excelRow, presetData.seacrh_pattern("Short Business Name", 0).get(0).intValue());
		String ReportGroup  	= presetData.getCellValue(excelRow, presetData.seacrh_pattern("Report Group", 0).get(0).intValue());
		
		System.out.println(BusinessName);
				
		setTextBoxValue(BusinessName, txt_BusinessName);	
		setTextBoxValue(ShortBusinessName, txt_ShortBusinessName);	
		setTextBoxValue(ShortBusinessName, txt_ShortBusinessName);	
		setTextBoxValue(ReportGroup, txt_ReportGroupID);	
	}
	/*
	public String getAccountName() throws InterruptedException {
		wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(txt_AccountName));
		Thread.sleep(2000);
		txt_AccountName.click();
		String name=txt_AccountName.getAttribute("value");
//		System.out.println(name);
		return name;
	}*/

}
