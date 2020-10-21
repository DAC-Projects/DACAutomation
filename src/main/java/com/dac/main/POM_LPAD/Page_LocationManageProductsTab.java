package com.dac.main.POM_LPAD;

import java.util.List;
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
import com.dac.testcases.LPAD.LaunchLPAD;

import resources.ExcelHandler;

public class Page_LocationManageProductsTab extends BasePage {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor js;
	String xcelInputData[][];
	String LocatorDataSyndication="LPM|DataSyndication|1|1";//Locator for identify the Options Popup
	//LPM|DataSyndication|2|1- Internal
	//LPM|DataSyndication|1|1
	
	//---------------PFO------------------//
	@FindBy(xpath="//div[@class='toggle-sw t-SocialEngagement']")
	private WebElement ToggleBoxSE;
	
	//-----------For enable Data syndication on LPM(Only for DAC Group Reseller)---------//
	@FindBy(xpath="//div[@class='toggle-sw t-LPM']")
	private WebElement ToggleBoxLPM;

	@FindBy(xpath="//input[@class='check-stat sw-LPM']")
	private WebElement CheckBoxLPM;
	
	@FindBy(xpath="//*[@id='mark_LPM']")
	private WebElement TickMarkLPM;
	
	@FindBy(xpath="//input[@id='LPM_SyndicationStatusReport']/../../span")
	private WebElement TickMarkSyndicationStatus;
	
	
	@FindBy(xpath="//input[@id='LPM_DataSyndication']")
	private WebElement CheckBoxDataSyndication;
	
	//*[@id='8LPM']/div[1]/input
	@FindBy(xpath="//input[@id='LPM_SyndicationStatusReport']")
	private WebElement ToggleSyndicationStatus;
	//*[@id='1LPM']/div[1]
	
	@FindBy(xpath="//a[@id='LPM_DataSyndication_options']")
	private WebElement OptionsDataSyndication;
	//*[@id='8LPM']/div[3]/a
	
	
	//This locators only working with "Domain N" only
	@FindBy(xpath="//div[@prefix='LPM|DataSyndication|1|1']//a[contains(text(),'OK')]")
	private WebElement OKButtonOption;
	
	@FindBy(xpath="//div[@prefix='LPM|DataSyndication|1|1']//h4")
	private WebElement OptionsHeading;
	
	@FindBy(xpath = "//div[@prefix='LPM|DataSyndication|1|1']//input[@type='checkbox']")
	private List<WebElement> allvendors;
	
	
	//-----------Page-----------//
	@FindBy(xpath="//div[@id='productsDiv']//i")
	private WebElement headingProductsTab;
	
	
	public Page_LocationManageProductsTab(WebDriver driver) {
		super(driver);
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
		
	}
	private void clickOnLPM() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		System.out.println("Clicking on LPM");
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleBoxSE);//ToggleBoxLPM
		js.executeScript("arguments[0].click();",CheckBoxLPM);
		Thread.sleep(2000);
	}
	private void clickOnDS() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		System.out.println("Clicking on Data Syndication");
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleSyndicationStatus);
		js.executeScript("arguments[0].click();",CheckBoxDataSyndication);
		Thread.sleep(2000);
	}
	private void clickOnSSyndicationStatus() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		System.out.println("Clicking on Syndication Status Report");
		js.executeScript("arguments[0].scrollIntoView(true);", CheckBoxLPM);
		js.executeScript("arguments[0].click();",ToggleSyndicationStatus);
		Thread.sleep(2000);
	}
	
	public void clickOnDSOptions(String type,ExcelHandler data, int row) throws Exception {
		js = (JavascriptExecutor) driver;
		String Vendor_IDs = null;
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(headingProductsTab));
		if (type.equalsIgnoreCase("NEW")) {
//			xcelInputData = new ExcelHandler(LocationDataExcelPath, "Products").getExcelTable();
			Vendor_IDs = data.getCellValue(row, data.seacrh_pattern("Vendor_ID_Create", 0).get(0).intValue());
			enableLPM();
//			System.out.println("Clicking on LPM");
//			js.executeScript("arguments[0].scrollIntoView(true);", ToggleBoxSE);
//			js.executeScript("arguments[0].click();",CheckBoxLPM);
//			Thread.sleep(2000);
			enableSyndicationStatus();
			clickOnDS();
//			System.out.println("Clicking on DS");
//			js.executeScript("arguments[0].scrollIntoView(true);", ToggleSyndicationStatus);
//			js.executeScript("arguments[0].click();",CheckBoxDataSyndication);
//			Thread.sleep(2000);
			System.out.println("Clicking on Options");
			js.executeScript("arguments[0].scrollIntoView(true);", OptionsDataSyndication);
			js.executeScript("arguments[0].scrollIntoView(true);", ToggleBoxLPM);
			OptionsDataSyndication.click();
			Thread.sleep(2000);
		}else if (type.equalsIgnoreCase("UPDATE")) {
			Vendor_IDs = data.getCellValue(row, data.seacrh_pattern("Vendro_ID_Update", 0).get(0).intValue());
//			xcelInputData = new ExcelHandler(LocationDataExcelPath, "UpdateOptions").getExcelTable();
			System.out.println("Clicking on Options");
			js.executeScript("arguments[0].scrollIntoView(true);", ToggleSyndicationStatus);
			js.executeScript("arguments[0].scrollIntoView(true);", ToggleBoxLPM);
			OptionsDataSyndication.click();
			Thread.sleep(2000);
		}else {
			System.out.println("clickOnDSOptions>> Not matching");
		}
		
		deselectAllVendors();
		String[] options=Vendor_IDs.split(",");
		selectOption(options);//select vendors available in Excel
	}
	
	public void clickOnSyndicationStatusReport() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		enableSyndicationStatus();
		Thread.sleep(2000);
	}
	private void deselectAllVendors() throws InterruptedException {
		List < WebElement > vendors = allvendors;
		int vendors_count = vendors.size();	
//		System.out.println(vendors_count);
		for(int i=1;i<=vendors_count;i++) {
			
			WebElement elementVendor = null;
			String strChecked=null;
			elementVendor=driver.findElement(By.xpath("//div[@prefix='"+LocatorDataSyndication+"']//input[@type='checkbox']["+i+"]"));
			Thread.sleep(1000);
			strChecked=elementVendor.getAttribute("checked");
			if (strChecked==null) {
//				System.out.println(i +" Not Checked");
				strChecked=null;
			}else {
//				System.out.println("Inside for unCheck the Option>> ");
				js.executeScript("arguments[0].scrollIntoView(true);", elementVendor);
				elementVendor.click();
				strChecked=null;
			}
		}
		
	}
	private void selectOption(String [] options)  throws Exception {
		js = (JavascriptExecutor) driver;
		
		int TotalRow=options.length;
		System.out.println("Total row in array >> "+TotalRow );
		
		for (int i=0;i<TotalRow;i++) {
			
			WebElement elementOption = null;
			String strChecked=null;
			String item=options[i].trim();
			elementOption=driver.findElement(By.xpath("//div[@prefix='"+LocatorDataSyndication+"']//input[@value='"+ item +"']"));
			Thread.sleep(2000);
			strChecked=elementOption.getAttribute("checked");
			if (!(strChecked==null)) {
//				System.out.println(" is Checked");
				strChecked=null;
			}else {
//				System.out.println("Inside for Check the Option>> ");
				js.executeScript("arguments[0].scrollIntoView(true);", elementOption);
				elementOption.click();
				strChecked=null;
			}
		}
		System.out.println("Options Selected");	
		OKButtonOption.click();
	}
	/*private boolean checkVendor(String vendor) throws Exception {
		xcelInputData = new ExcelHandler(LocationDataExcelPath, "UpdateOptions").getExcelTable();
		int count=xcelInputData.length;
		boolean flag=false;
		for(int i=1;i<count;i++) {
			String xlVendor=xcelInputData[i][0];
			System.out.println(xlVendor+" = "+ vendor);
			if(xlVendor.equalsIgnoreCase(vendor)) {
				flag=true;
				break;
			}else {
				flag=false;
			}
		}
		return flag;
	}*/
	public boolean disableLPM() throws InterruptedException {
		String value= TickMarkLPM.getAttribute("style");
//		System.out.println(value);
		if (value.contains("inline")) {
//			 System.out.println("Product ON");
			 clickOnLPM();
			 System.out.println("Product: ON >> OFF");
			 return true;
		}else {
			System.out.println("Product Already OFF");
			return false;
		}
	}
	
	public boolean enableLPM() throws InterruptedException {
		String value= TickMarkLPM.getAttribute("style");
//		System.out.println(value);
		if (!value.contains("inline")) {
//			 System.out.println("Product OFF");
			 clickOnLPM();
			 System.out.println("Product: OFF >> ON");
			 return true;
		}else {
			System.out.println("Product Already ON");
			return false;
		}
	}
	public boolean enableSyndicationStatus() throws InterruptedException {
		String value= TickMarkSyndicationStatus.getAttribute("style");
//		System.out.println(value);
		if (!value.contains("inline")) {
//			 System.out.println("Product OFF");
			 clickOnSSyndicationStatus();
			 System.out.println("Product: OFF >> ON");
			 return true;
		}else {
			System.out.println("Product Already ON");
			return false;
		}
	}
	/*private void deSelectOption(String option) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		WebElement elementOption=driver.findElement(By.xpath("//div[@prefix='LPM|DataSyndication|2|1']//*[contains(text(),'"+ option +"')]/../input"));
		js.executeScript("arguments[0].scrollIntoView(true);", elementOption);
		String checked=elementOption.getAttribute("checked");
		if (checked.contentEquals("true")) {
			js.executeScript("arguments[0].click();",elementOption);
//			actions.moveToElement(elementOption).click().build().perform();
		}else {
			System.out.println(option  +" is NOT Checked");
			
		}
	}*/
}
