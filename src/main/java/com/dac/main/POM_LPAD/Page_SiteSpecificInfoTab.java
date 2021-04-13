package com.dac.main.POM_LPAD;


import java.util.List;
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

public class Page_SiteSpecificInfoTab extends BasePage {
	WebDriver driver;
	Actions actions;
	Select estSelect,cuSelect; 
	WebDriverWait wait;
	JavascriptExecutor js;
	ExcelHandler siteSpecificData;
	String [][] xlInput;
	
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
	
	
	/*--------Vendor Section--------*/
	/*----Zomato-----*/
	@FindBy(xpath="//div[@id='ZomatoSummary']")
	private WebElement divZomato;
	
	@FindBy(xpath="//input[@id='zomatoEdit']")
	private WebElement btnZomatoEdit;
	
	@FindBy(xpath="//div[@id='s2id_EstablishmentTypes']")
	private WebElement establishmentType;
	
	@FindBy(xpath="//div[@id='s2id_CuisinesTypes']")
	private WebElement cuisineType;
	
	@FindBy(xpath="//select[@id='EstablishmentTypes']")
	private WebElement selectEstablishmentTypes;
	
	@FindBy(xpath="//select[@id='CuisinesTypes']")
	private WebElement selectCuisines;
	
	@FindBy(xpath="//input[@id='CateAdd2']")
	private WebElement btnZomatoEstAdd;
	
	@FindBy(xpath="//input[@id='CateAdd3']")
	private WebElement btnZomatoCuisineAdd;
	
	@FindBy(xpath="//div[@id='ZomatoSummary']//button[@class='btn btn-primary']")
	private WebElement btnZomatoSubmit;

	
	
	@FindBy(xpath = "//table[@id='ListTable2']/tbody/tr//img")
	private List<WebElement> btnSelectedEstType;
	
	@FindBy(xpath = "//table[@id='ListTable3']/tbody/tr//img")
	private List<WebElement> btnSelectedCuisType;
	
	/*-------Apple--------------*/
	@FindBy(xpath="//input[@id='appleEdit']")
	private WebElement btnAppleEdit;
	
	@FindBy(xpath="//input[@id='AppleBusinessName']")
	private WebElement AppleBusinessName;
	
	@FindBy(xpath="//div[@id='s2id_AppleAmenities']")
	private WebElement AppleAmenities;
	
	@FindBy(xpath="//ul[@id='select2-results-15']")
	private WebElement AppleAmenitiesList;
	
	@FindBy(xpath="//input[@id='CateAdd12']")
	private WebElement btnAppleAmAdd;
	
	@FindBy(xpath="//div[@id='AppleSummary']//button[@class='btn btn-primary']")
	private WebElement btnAppleSubmit;
	
	//ul[@id='select2-results-15']/li/div[contains(text(),'Delivery')]
	//div[@id='select2-drop']//ul/li/div[contains(text(),'Delivery')]
	
	@FindBy(xpath="//button[@data-bb-handler='confirm']")
	private WebElement btnConfrim;
	@FindBy(xpath="//button[@data-bb-handler='ok']")
	private WebElement btnOK;

	public Page_SiteSpecificInfoTab(WebDriver driver) {
		super(driver);
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	private void setAppleBusinessname(String name) {
		AppleBusinessName.clear();
		AppleBusinessName.sendKeys(name);
	}
	private void submitAppleValue() {
		btnAppleSubmit.click();
	}
	private void setAppleAmenities(String Amenities) throws InterruptedException {
		System.out.println(Amenities);
		String[] options=Amenities.split(",");
		
		js = (JavascriptExecutor) driver;
		
		int TotalRow=options.length;
		System.out.println("Total row in array >> "+TotalRow );
		
		for (int i=0;i<TotalRow;i++) {
			AppleAmenities.click();
			WebElement elementType = null;
			String strChecked=null;
			System.out.println(options[i]);
			String item=options[i].trim();
			elementType=driver.findElement(By.xpath("//div[@id='select2-drop']//ul/li/div[contains(text(),'"+item+"')]"));
			Thread.sleep(2000);
			elementType.click();
			Thread.sleep(2000);
			btnAppleAmAdd.click();
			
			
			
		}
		System.out.println("Amenities Selected");	
	}
	private void setAppleValues(ExcelHandler valueOptions) throws InterruptedException {
		//start from here
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		

		String StrAppleBusinessName=  valueOptions.getCellValue(1, valueOptions.seacrh_pattern("AppleBusinessName", 0).get(0).intValue());
		String strAppelAmenities=  valueOptions.getCellValue(1, valueOptions.seacrh_pattern("AppleAmenites", 0).get(0).intValue());
		setAppleBusinessname(StrAppleBusinessName);
		setAppleAmenities(strAppelAmenities);
//		js.executeScript("arguments[0].scrollIntoView(true);", btnAppleSubmit);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		submitAppleValue();
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		btnOK.click();
	}
	
	private void setZomatoValue(ExcelHandler valueOptions) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
		estSelect = new Select(selectEstablishmentTypes);
		cuSelect = new Select(selectCuisines);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		
		//Add Establishment Type
//		deselectEstTypes(btnSelectedEstType);
		String establishment=  valueOptions.getCellValue(1, valueOptions.seacrh_pattern("Zomato_EstablishmentType", 0).get(0).intValue());
		String strCuisineType=  valueOptions.getCellValue(1, valueOptions.seacrh_pattern("Zomato_Cuisine", 0).get(0).intValue());
		
		actions.moveToElement(establishmentType).click().build().perform();
		estSelect.selectByVisibleText(establishment);
		Thread.sleep(2000);
		btnZomatoEstAdd.click();
		
		//Add Cuisine Type
		js.executeScript("arguments[0].scrollIntoView(true);", establishmentType);
//		deselectEstTypes(btnSelectedCuisType);
		actions.moveToElement(cuisineType).click().build().perform();
		cuSelect.selectByVisibleText(strCuisineType);
		Thread.sleep(2000);
		btnZomatoCuisineAdd.click();
		
		js.executeScript("arguments[0].scrollIntoView(true);", btnZomatoSubmit);
//		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		btnZomatoSubmit.click();
//		Thread.sleep(2000);
//		if(btnConfrim.isDisplayed()) {
//			btnConfrim.click();
//		}
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		btnOK.click();
	}
	
	private void deselectEstTypes(List<WebElement> optionsList) {
		
		int selectedCount=optionsList.size();
		if(selectedCount>0) {
			for(int i=0;i<selectedCount;i++) {
				optionsList.get(i).click();
			}
			
		}else {
			System.out.println("No Options Selected");
		}
	}

	private void clickOnAddCategoryBtn() {
		btnAddCategory.click();
	}
	
	public void fillSiteSpecificInfoData( String vendor, ExcelHandler data, int row) throws Exception {
		wait = new WebDriverWait(driver, 30);
		String vendors= data.getCellValue(row, data.seacrh_pattern("Vendors_Create", 0).get(0).intValue());
		String[] vendorsList=vendors.split(",");
		siteSpecificData=new ExcelHandler(IAutoconst.LocationDataExcelPath, "SiteSpecificInfo");
//		String strcategory=inputData[excelRow][0];		System.out.println(strcategory);
		vendor=vendor.toUpperCase();
		if(checkVendor(vendor,vendorsList)) {
			switch (vendor){
			case "ZOMATO":
				
				setZomatoAmenities(siteSpecificData);
			
				break;
			case "APPLE":
				
				setAppleAmenities(siteSpecificData);
			
				break;
			default:
				System.out.println("Invalid Vendor");
			}
		}
		
		
		
		
	}
	private boolean checkVendor(String vendor,String[] XLvendrosList) throws Exception {
//		xlInput = new ExcelHandler(LocationDataExcelPath, "Products").getExcelTable();
		int count=XLvendrosList.length;
		
		boolean flag=false;
		for(int i=0;i<count;i++) {
			String xlVendor=XLvendrosList[i].toString();
			System.out.println(xlVendor+" = "+ vendor);
			if(xlVendor.equalsIgnoreCase(vendor)) {
				flag=true;
				break;
			}else {
				flag=false;
			}
		}
		return flag;
	}
	private void setZomatoAmenities(ExcelHandler values) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		System.out.println("Clicking on Zomato-Edit Button");
		js.executeScript("arguments[0].scrollIntoView(true);", btnZomatoEdit);
		js.executeScript("arguments[0].click();",btnZomatoEdit);
		Thread.sleep(5000);
		setZomatoValue(values);
	}
	private void setAppleAmenities(ExcelHandler values) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		System.out.println("Clicking on Apple-Edit Button");
		js.executeScript("arguments[0].scrollIntoView(true);", btnAppleEdit);
		js.executeScript("arguments[0].click();",btnAppleEdit);
		Thread.sleep(2000);
		setAppleValues(values);
	}
	
}
