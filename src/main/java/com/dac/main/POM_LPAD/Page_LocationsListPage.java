package com.dac.main.POM_LPAD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page_LocationsListPage {
	WebDriver driver;
	Actions action;
	
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
	
	public Page_LocationsListPage(WebDriver driver) {
		this.driver=driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	public void searchLocation(String name) {
		txtSearchBox.sendKeys(name);
		btnTableSearch.click();
	}
	public void NaviagteToCreateLocation() {
		System.out.println("Navigate to Create Location Page");
		btnCreateLocation.click();
	}
	public void ExportLocations() {
		System.out.println("Navigate to Create Location Page");
		btnExport.click();
	}
	
}
