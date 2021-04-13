package com.dac.main.POM_LPAD;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.ExcelHandler;
import resources.IAutoconst;

public class Page_LocationBusinessInfoTab extends BasePage {
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	public JavascriptExecutor js;
	
	ExcelHandler data;
	
	@FindBy(xpath="//*[@id=\"main-wrapper\"]/section/div[1]/h1")
	private WebElement header;
	
	@FindBy(xpath="//*[@id='CateAdd']")
	private WebElement btnAddCategory;
	
	@FindBy(xpath="//*[@id='s2id_selectCategoryData']")
	private WebElement txtDacCategory;
	
	@FindBy(xpath="//*[@id='selectCategoryData']")
	private WebElement selectCategoryList;
	
	@FindBy(xpath="//*[@class='select2-input']")
	private WebElement Category;
	
	@FindBy(xpath="//*[@id='ChainName']")
	private WebElement ChainName;
	
	@FindBy(xpath="//*[@id='BusinessDesc']")
	private WebElement shorBusinessDesc;
	
	@FindBy(xpath="//*[@id='AlternateBusinessPhone']")
	private WebElement AlternateBusinessPhone;
	
	@FindBy(xpath="//*[@id='TollFreePhone']")
	private WebElement TollFreePhone;
	
	@FindBy(xpath="//*[@id='Mobile']")
	private WebElement MobilePhone;
	
	@FindBy(xpath="//*[@id='Fax']")
	private WebElement FaxNumber;
	
	@FindBy(xpath="//*[@id='BusinessURL']")
	private WebElement BusinessWebsite;
	
	//------------Hours Section
	@FindBy(xpath="//div[@id='ContactInfoDIV']//div[@id='div9']")
	private WebElement divLanguage;
	
	
	@FindBy(xpath="//*[@id='sunradio1']")
	private WebElement radioButton_24Hrs_SUNDAY;
	
	@FindBy(xpath="//*[@id='btnApplyToAllDay']")
	private WebElement btnApplytoAll;
	
	@FindBy(xpath="//*[@id='tableHours']")
	private WebElement tableHours;
	
	@FindBy(xpath="//*[contains(text(), 'Google Hours')]")
	private WebElement GoogleHours;
	
	public Page_LocationBusinessInfoTab(WebDriver driver) {
		super(driver);
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	private void setCategory(String category) {
		uiSelect=new Select(selectCategoryList);
		actions.moveToElement(txtDacCategory).click().build().perform();
		uiSelect.selectByVisibleText(category);
		
	}
	private void setFaxNumber(String number) {
		FaxNumber.clear();
		FaxNumber.sendKeys(number);
	}
	private void setChainName(String name) {
		ChainName.clear();
		ChainName.sendKeys(name);
	}
	private void steShortBusinessDesc(String desc) {
		shorBusinessDesc.clear();
		shorBusinessDesc.sendKeys(desc);
	}
	private void setMobileNumber(String number) {
		MobilePhone.sendKeys(number);
	}
	private void setTollFreeNumber(String number) {
		TollFreePhone.sendKeys(number);
	}
	private void setLandLineNumber(String number) {
		AlternateBusinessPhone.clear();
		AlternateBusinessPhone.sendKeys(number);
	}
	private void setBusinessWebsite(String site) {
		BusinessWebsite.clear();
		BusinessWebsite.sendKeys(site);
	}
	private void clickOnAddCategoryBtn() {
		btnAddCategory.click();
	}
	
	private void setHours() {
		js = (JavascriptExecutor) driver;
		System.out.println("Inside Hours>>> ");
		js.executeScript("arguments[0].scrollIntoView(true);", divLanguage);
		boolean selected=radioButton_24Hrs_SUNDAY.isSelected();
//		System.out.println(selected);
		if (!selected){
			System.out.println("Inside select Hours");
			radioButton_24Hrs_SUNDAY.click();
		}
		actions.moveToElement(btnApplytoAll).click().build().perform();
//		btnApplytoAll.click();
	}
	
	private void setPaymentType(String types) throws InterruptedException {
		System.out.println(types);
		String[] options=types.split(",");
		
		js = (JavascriptExecutor) driver;
		
		int TotalRow=options.length;
		System.out.println("Total row in array >> "+TotalRow );
		
		for (int i=0;i<TotalRow;i++) {
			
			WebElement elementType = null;
			String strChecked=null;
			System.out.println(options[i]);
			String item=options[i].trim();
			elementType=driver.findElement(By.xpath("//*[@id='tablePayment']//*[contains(text(),'"+item+"')]//preceding-sibling::Input"));
			Thread.sleep(2000);
			strChecked=elementType.getAttribute("checked");
			js.executeScript("arguments[0].scrollIntoView(true);", GoogleHours);
			if (!(strChecked==null)) {
//				System.out.println(" is Checked");
				strChecked=null;
			}else {
//				System.out.println("Inside for Check the Option>> ");
				elementType.click();
//				actions.moveToElement(elementType).click().build().perform();
				strChecked=null;
			}
		}
		System.out.println("Options Selected");	
	}
	public void fillBusinessInfoData() throws Exception {
		
		data = new ExcelHandler(IAutoconst.LocationDataExcelPath,"BusinessInfo");
		int row=1;
		wait = new WebDriverWait(driver, 30);
		
		String strcategory  	= data.getCellValue(row, data.seacrh_pattern("DACCategory", 0).get(0).intValue());
		String sbDesc  	= data.getCellValue(row, data.seacrh_pattern("Short Business Description", 0).get(0).intValue());
		String strFaxNumber  	= data.getCellValue(row, data.seacrh_pattern("FaxNumber", 0).get(0).intValue());
		String strMobileNumber  	= data.getCellValue(row, data.seacrh_pattern("MobileNumber", 0).get(0).intValue());
		String strTollFreeNumber  	= data.getCellValue(row, data.seacrh_pattern("TollFreeNumber", 0).get(0).intValue());
		String strLandLineNumber  	= data.getCellValue(row, data.seacrh_pattern("LandLineNumber", 0).get(0).intValue());
		String strBusinessWebsite  	= data.getCellValue(row, data.seacrh_pattern("BusinessWebsite", 0).get(0).intValue());
		String chainname  	= data.getCellValue(row, data.seacrh_pattern("Chain Name", 0).get(0).intValue());
		String strPaymentType  	= data.getCellValue(row, data.seacrh_pattern("PaymentType", 0).get(0).intValue());
		
		wait.until(ExpectedConditions.visibilityOf(btnAddCategory));
		setCategory(strcategory);
		clickOnAddCategoryBtn();
		Thread.sleep(2000);
		
		
		setChainName(chainname);
		setBusinessWebsite(strBusinessWebsite);
		steShortBusinessDesc(sbDesc);
		setLandLineNumber(strLandLineNumber);
		setTollFreeNumber(strTollFreeNumber);
		setMobileNumber(strMobileNumber);
		setFaxNumber(strFaxNumber);
		setHours();
		setPaymentType(strPaymentType);
	}
}
