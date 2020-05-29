package com.dac.main.POM_LPAD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Page_LocationsListPage {
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	
	
	@FindBy(xpath="//*[@id=\"LocationListTable_filter\"]/label/input")
	private WebElement txtSearchBox;
	
	@FindBy(id="btnTableSearch")
	private WebElement btnTableSearch;
	
	@FindBy(id="LocationListDownload")
	private WebElement btnExport;
		
	@FindBy(xpath="//*[@href=\"/LocationAdd/AddIndex?AccountLocationList=locationList&locationListType=locationList\"]")
	private WebElement btnCreateLocation;
	/*------- Business Status filters------*/
	@FindBy(xpath="//button[@class='multiselect dropdown-toggle btn btn-default']")
	private WebElement BusinessStatusFilter;
	
	@FindBy(xpath="//a[@id='btn_locationFilter']")
	private WebElement btnLocationFilter;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[1]")
	private WebElement itemSelectAll;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[2]")
	private WebElement itemOpenActive;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[3]")
	private WebElement itemClosed;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[4]")
	private WebElement itemDeactivated;
	
	@FindBy(xpath="//ul[@class='multiselect-container dropdown-menu']/li[5]")
	private WebElement itemDeleted;
	/* ----------In the table---------*/
	@FindBy(xpath="//td//i[@class='fa fa-pencil']")
	private WebElement btnManageLocation;
	
	@FindBy(xpath="//i[@class='icon-ban']")
	private WebElement btnDeactivateLocation;
	
	@FindBy(xpath="//table[@id='LocationListTable']//i[@class='icon-user']")
	private WebElement btnActivateLocation;
	
	@FindBy(xpath="//i[@class='fa fa-trash-o']")
	private WebElement btnDeleteLocation;
	
	@FindBy(xpath="//i[@class='fa fa-times']")
	private WebElement btnCloseLocation;
	
	@FindBy(xpath="//i[@class='fa fa-check']")
	private WebElement btnOpenLocation;
	
	@FindBy(xpath="//div[text()='Showing 1 to 1 of 1 entries']")
	public WebElement locationSearchCountInfo;
	
	@FindBy(xpath="//div[@id='LocationListTable_paginate']")
	private WebElement LocationPagination;
	
	/*----------Message Popup----------*/
	@FindBy(xpath="//button[text()='Deactivate']")
	private WebElement btnDeactivateConfirm;
	
	@FindBy(xpath="//button[text()='Confirm']")
	private WebElement btnConfirm;
	
	@FindBy(xpath="//button[text()='Activate']")
	private WebElement btnActivate;
	
	@FindBy(xpath="//button[@data-bb-handler='ok']")
	private WebElement btnOK;
	
	//ul[@class='multiselect-container dropdown-menu']/li[3]
	
	public Page_LocationsListPage(WebDriver driver) {
		this.driver=driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	public void searchLocation(String name) {
		wait=new WebDriverWait(driver, 30);
		txtSearchBox.sendKeys(name);
		btnTableSearch.click();
		wait.until(ExpectedConditions.visibilityOf(locationSearchCountInfo));
	}
	public void NaviagteToCreateLocation() {
		System.out.println("Navigate to Create Location Page");
		btnCreateLocation.click();
	}
	public void ExportLocations() {
		btnExport.click();
	}
	public void NavigateManageLocation() {
		System.out.println("Navigate to Manage Location");
		wait=new WebDriverWait(driver, 30);
		btnManageLocation.click();
	}
	public void DeactivateLocation() {
		System.out.println("Clicking on Deactivate Location Button");
		wait=new WebDriverWait(driver, 30);
		btnDeactivateLocation.click();
		wait.until(ExpectedConditions.visibilityOf(btnDeactivateConfirm));
		btnDeactivateConfirm.click();
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		btnOK.click();
	}
	public void DeleteLocation() {
		System.out.println("Clicking on Delete Location Button");
		wait=new WebDriverWait(driver, 30);
		btnDeleteLocation.click();
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		btnOK.click();
	}
	
	public void CloseLocation() {
		System.out.println("Clicking on Close Location Button");
		wait=new WebDriverWait(driver, 30);
		btnCloseLocation.click();
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		btnOK.click();
	}
	public void SetBusinessStatus(String option) {
		wait=new WebDriverWait(driver, 30);
		BusinessStatusFilter.click();
		if(option.equalsIgnoreCase("Select All")) {
			itemSelectAll.click();
		}else if(option.equalsIgnoreCase("Open/Active")) {
			itemOpenActive.click();
		}else if(option.equalsIgnoreCase("Closed")) {
			itemClosed.click();
		}else if(option.equalsIgnoreCase("Deactivated")) {
			itemDeactivated.click();
		}else if(option.equalsIgnoreCase("Deleted")) {
			itemDeleted.click();
		}else {
			System.out.println("Item not Exist>> "+ option);
			
		}
		btnLocationFilter.click();
		wait.until(ExpectedConditions.visibilityOf(LocationPagination));
		
	}
	public void ReactivateLocation() {
		System.out.println("Clicking on Activate Location Button");
		wait=new WebDriverWait(driver, 30);
		btnActivateLocation.click();
		wait.until(ExpectedConditions.visibilityOf(btnActivate));
		btnActivate.click();
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		btnOK.click();
	}
	public void ReOpenLocation() {
		System.out.println("Clicking on Open Location Button");
		wait=new WebDriverWait(driver, 30);
		btnOpenLocation.click();
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.visibilityOf(btnOK));
		btnOK.click();
	}
}
