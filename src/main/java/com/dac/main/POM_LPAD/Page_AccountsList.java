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
import resources.Utilities;

public class Page_AccountsList extends BasePage {
	
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	JavascriptExecutor executor;
	ExcelHandler basic;
	
	//Locators Starting..........
	
	@FindBy(xpath="//a[text()='Create Account']")
	private WebElement btnCreateAccount;
		
	@FindBy(xpath="//input[@type='search']")
	private WebElement txtSearch;
	
	@FindBy(xpath="//button[@id='btnTableSearch']")
	private WebElement btnGo;
	
	//-----------Manage Account Icons--------------
	
	@FindBy(xpath="//button[@id='btnTableSearch']")
	private WebElement btnBulkPFO;
	//------------Account Filters----------------
	@FindBy(xpath="//button[@class='btn-group open']")
	private WebElement accountStatusFilter;
	
	@FindBy(xpath="//a[@id='btn_AccountFilter']")
	private WebElement btnApplyFilter;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[1]")
	private WebElement itemSelectAll;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[2]")
	private WebElement itemOpenActive;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[3]")
	private WebElement itemDeactivated;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[4]")
	private WebElement itemDeleted;
	
	@FindBy(xpath="//div[text()='Showing 1 to 1 of 1 entries']")
	public WebElement accountSearchCountInfo;
	
	//---------------Message
	@FindBy(xpath="//button[@data-bb-handler='OK']")
	private WebElement btnOK;
	
	//Locators End.
	
	public Page_AccountsList(WebDriver driver) {
		super(driver);
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
		
	}
	

	public void navigateCreateAccount() {
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateAccount));
		btnCreateAccount.click();
	
		
	}
	
	public void searchAccount(String AccountName) {
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(txtSearch));
		txtSearch.sendKeys(AccountName);
		btnGo.click();
		wait.until(ExpectedConditions.visibilityOf(accountSearchCountInfo));
		
	}
	private void navigateToBulkPFOAssignment() {
		// TODO Auto-generated method stub

	}
	
}
