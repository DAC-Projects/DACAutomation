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

import com.dac.testcases.LPAD.LaunchLPAD;

import resources.ExcelHandler;

public class Page_LocationManageProductsTab extends LaunchLPAD {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor js;
	String xcelInputData[][];
	
	//---------------PFO------------------//
	@FindBy(xpath="//*[@id='rdioDiv']/div[4]/div/div[1]/div[3]/div/b[1]")
	private WebElement ToggleBoxSE;
	
	//-----------For enable Data syndication on LPM(Only for DAC Group Reseller)---------//
	@FindBy(xpath="//*[@id='rdioDiv']/div[5]/div/div[1]/div[3]/div/b[1]")
	private WebElement ToggleBoxLPM;
	
	@FindBy(xpath="//*[@id='rdioDiv']/div[5]/div/div[1]/div[3]/div/b[1]/../input")
	private WebElement CheckBoxLPM;
	
	@FindBy(xpath="//*[@id='8LPM']/div[1]/input")
	private WebElement CheckBoxDataSyndication;
	
	@FindBy(xpath="//*[@id='1LPM']/div[1]")
	private WebElement ToggleSyndicationStatus;
	
	@FindBy(xpath="//*[@id='8LPM']/div[3]/a")
	private WebElement OptionsDataSyndication;
	
	@FindBy(xpath="//div[@class='modal-body']//*[contains(text(),'Apple')]/../input")
	private WebElement CheckBoxOptionValue;
	
	@FindBy(xpath="//div[@prefix='LPM|DataSyndication|2|1']//a[contains(text(),'OK')]")
	private WebElement OKButtonOption;
	
	@FindBy(xpath="//div[@prefix='LPM|DataSyndication|2|1']//h4")
	private WebElement OptionsHeading;
	
	@FindBy(xpath = "//div[@prefix='LPM|DataSyndication|2|1']//input[@type='checkbox']")
	private List<WebElement> allvendors;
	
	
	//-----------Page-----------//
	@FindBy(xpath="//div[@id='productsDiv']//i")
	private WebElement headingProductsTab;
	
	
	public Page_LocationManageProductsTab(WebDriver driver) {
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
		
	}
	private boolean verifyElementEnabled(WebElement element) {
		if(element.isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	private void enableLPM() throws InterruptedException {
		
		
	}
	
	private void enableDataSyndication() throws InterruptedException {
	
		
	}
	public void clickOnDSOptions() throws Exception {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(headingProductsTab));
		
		System.out.println("Clicking on LPM");
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleBoxSE);
		js.executeScript("arguments[0].click();",CheckBoxLPM);
		Thread.sleep(2000);

		System.out.println("Clicking on DS");
		js.executeScript("arguments[0].scrollIntoView(true);", ToggleSyndicationStatus);
		js.executeScript("arguments[0].click();",CheckBoxDataSyndication);
		Thread.sleep(2000);
		
		System.out.println("Clicking on Options");
		OptionsDataSyndication.click();
		Thread.sleep(2000);
		deselectAllVendors();
		xcelInputData = new ExcelHandler(LocationDataExcelPath, "Products").getExcelTable();
		selectOption(xcelInputData);
	}
	
	private void deselectAllVendors() throws InterruptedException {
		List < WebElement > vendors = allvendors;
		int vendors_count = vendors.size();	
//		System.out.println(vendors_count);
		for(int i=1;i<=vendors_count;i++) {
			
			WebElement elementVendor = null;
			String strChecked=null;
			elementVendor=driver.findElement(By.xpath("//div[@prefix='LPM|DataSyndication|2|1']//input[@type='checkbox']["+i+"]"));
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
	private void selectOption(String [][] optionValue) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		int TotalRow=optionValue.length-1;
		for (int i=1;i<=TotalRow;i++) {
			
			WebElement elementOption = null;
			String strChecked=null;
			elementOption=driver.findElement(By.xpath("//div[@prefix='LPM|DataSyndication|2|1']//input[@value='"+ optionValue[i][1] +"']"));
//			System.out.println("Vendor name is: "+optionValue[i][0]+": ID is>> "+ optionValue[i][1]+" Selected");
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
			
		OKButtonOption.click();
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
