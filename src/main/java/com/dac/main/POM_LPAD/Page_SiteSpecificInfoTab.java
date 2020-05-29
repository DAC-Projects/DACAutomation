package com.dac.main.POM_LPAD;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.testcases.LPAD.LaunchLPAD;

import resources.ExcelHandler;

public class Page_SiteSpecificInfoTab extends LaunchLPAD {
	WebDriver driver;
	Actions actions;
	Select estSelect,cuSelect; 
	WebDriverWait wait;
	public JavascriptExecutor js;
	String [][] siteSpecificData,xlInput;
	
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
	
	@FindBy(xpath="//button[@data-bb-handler='confirm']")
	private WebElement btnConfrim;
	@FindBy(xpath="//button[@data-bb-handler='ok']")
	private WebElement btnOK;

	public Page_SiteSpecificInfoTab(WebDriver driver) {
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	public void setValue(String[][] valueOptions) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
		estSelect = new Select(selectEstablishmentTypes);
		cuSelect = new Select(selectCuisines);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		
		//Add Establishment Type
//		deselectEstTypes(btnSelectedEstType);
		actions.moveToElement(establishmentType).click().build().perform();
		estSelect.selectByVisibleText(valueOptions[1][0]);
		Thread.sleep(2000);
		btnZomatoEstAdd.click();
		
		//Add Cuisine Type
		js.executeScript("arguments[0].scrollIntoView(true);", establishmentType);
//		deselectEstTypes(btnSelectedCuisType);
		actions.moveToElement(cuisineType).click().build().perform();
		cuSelect.selectByVisibleText(valueOptions[1][1]);
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

	public void clickOnAddCategoryBtn() {
		btnAddCategory.click();
	}
	
	public void fillSiteSpecificInfoData(String vendor) throws Exception {
		wait = new WebDriverWait(driver, 30);
		siteSpecificData=new ExcelHandler(LocationDataExcelPath, "SiteSpecificInfo").getExcelTable();
//		String strcategory=inputData[excelRow][0];		System.out.println(strcategory);
		
		if(checkVendor(vendor)) {
			switch (vendor){
			case "ZOMATO":
				setZomatoAmenities(siteSpecificData);
			default:
				System.out.println("Invalid Vendor");
			}
		}
		
		
		
		
	}
	private boolean checkVendor(String vendor) throws Exception {
		xlInput = new ExcelHandler(LocationDataExcelPath, "Products").getExcelTable();
		int count=xlInput.length;
		boolean flag=false;
		for(int i=1;i<count;i++) {
			String xlVendor=xlInput[i][0];
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
	private void setZomatoAmenities(String [][] values) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait=new WebDriverWait(driver, 30);
		System.out.println("Clicking on Edit Button");
		js.executeScript("arguments[0].scrollIntoView(true);", btnZomatoEdit);
		js.executeScript("arguments[0].click();",btnZomatoEdit);
		Thread.sleep(2000);
		setValue(values);
	}
	private void openAmenities() throws InterruptedException {
		
	}
}
