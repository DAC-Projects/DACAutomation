package com.dac.main.POM_LPAD;


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

public class Page_LocationBusinessInfoTab extends LaunchLPAD {
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	public JavascriptExecutor js;
	
	
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
	
	
	public Page_LocationBusinessInfoTab(WebDriver driver) {
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	public void setCategory(String category) {
		uiSelect=new Select(selectCategoryList);
		actions.moveToElement(txtDacCategory).click().build().perform();
		uiSelect.selectByVisibleText(category);
		
	}
	
	public void clickOnAddCategoryBtn() {
		btnAddCategory.click();
	}
	
	public void fillBusinessInfoData() throws Exception {
		String [][] inputData=new ExcelHandler(LocationDataExcelPath, "BusinessInfo").getExcelTable();
		int excelRow=1;
		wait = new WebDriverWait(driver, 30);
		String strcategory=inputData[excelRow][0];		System.out.println(strcategory);
		wait.until(ExpectedConditions.visibilityOf(btnAddCategory));
		setCategory(strcategory);
		clickOnAddCategoryBtn();
		Thread.sleep(2000);
		ChainName.sendKeys(inputData[excelRow][1]);System.out.println(inputData[excelRow][1]);
		shorBusinessDesc.sendKeys(inputData[excelRow][2]);System.out.println(inputData[excelRow][2]);
		
	}
}
