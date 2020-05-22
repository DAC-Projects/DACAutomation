package com.dac.main.POM_LPAD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page_LocationsListPage {
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div/div/div[2]/div/div[1]/div[1]/span/div/button")
	private WebElement BusinessStatus;
	
	@FindBy(id="btn_locationFilter")
	private WebElement btnLocationFilter;
	
	@FindBy(xpath="//*[@id=\"LocationListTable_filter\"]/label/input")
	private WebElement txtSearchBox;
	
	@FindBy(id="btnTableSearch")
	private WebElement btnTableSearch;
	
	@FindBy(id="LocationListDownload")
	private WebElement btnExport;
		
	@FindBy(xpath="//*[@href=\"/LocationAdd/AddIndex?AccountLocationList=locationList&locationListType=locationList\"]")
	private WebElement btnCreateLocation;
	
	/* ----------table---------*/
	@FindBy(xpath="//td//i[@class='fa fa-pencil']")
	private WebElement btnManageLocation;
	@FindBy(xpath="//div[text()='Showing 1 to 1 of 1 entries']")
	public WebElement locationSearchCountInfo;
	
	
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
}
