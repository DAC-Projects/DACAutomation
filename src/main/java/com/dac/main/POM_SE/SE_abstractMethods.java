package com.dac.main.POM_SE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;
import com.dac.main.POM_TPSEE.TPSEERepository;

import resources.JSWaiter;

public abstract class SE_abstractMethods extends BasePage implements SE_Repository{

	public SE_abstractMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='menu transition visible']")
	private WebElement filterDropDown;
	
	@FindBy(id="myGroups")
	private WebElement fiterGroup;
	
	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList")
	private WebElement FilterCountry;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList1")
	private WebElement FilterState;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList2")
	private WebElement FilterCity;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList3")
	private WebElement Filterlocation;

	@FindBy(css = "button#apply_filter")
	private WebElement Apply_filter;

	@FindBy(css = "div#filter-options")
	public WebElement filter_Panel;
	
	@FindBy(xpath="//h1[contains(text(),'')]")
	private WebElement PageTitle;

	// History graph
	@FindBy(css = "rect.highcharts-plot-background")
	public WebElement hstryGrph;

	@FindBy(css = ".highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 
	
	//section of overall report
	@FindBy(xpath = "//*[@id='divOverallScoreHeader']//*[@id='divOverallScoreHeader']/div[1]//*[@id='divOverallScoreValue']")
	private WebElement overall;

	//site tableesTable
	@FindBy(xpath = "//div[@class='barContainer']") 
	public WebElement siteTable;
	
	@FindBy(xpath = "//*[@id='accuracyScoresTable']")
	public WebElement accuracysite;

	@FindBy(xpath = "//a[contains(text(),'Next')]")
	private WebElement pagination;
	
	@FindBy(xpath = "//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']")
	private WebElement vendorslist;
	
	//Keyword Search  Filter
	@FindBy(xpath = "//input[@id='usrSearch']")
	private WebElement KeywordSearch;
	
	@FindBy(xpath="//button[@id='searchBtn']")
	private WebElement SearchApplyFilter;
	
	//Assigned Locations tab
	@FindBy(xpath="//*[@id='tabsDiv']/div/div/ul/li[2]/a")
	private WebElement AssignedLocationsTab;

	
	public void applyGlobalFilter(String Group, String CountryCode, String State, String City, String Location) {
		JSWaiter.waitJQueryAngular();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='visibility-page']"))).getText();
		WebElement country,state,city,location,group;
		if(Group == null || Group.equalsIgnoreCase("none")) Group = "None";
		if (CountryCode == null || CountryCode.equalsIgnoreCase("null")) CountryCode = "All Countries";
		if (CountryCode == null || State == null || State.equalsIgnoreCase("null")) State = "All States";
		if (CountryCode == null || State == null || City == null || City.equalsIgnoreCase("null")) City = "All Cities"; 
		if (CountryCode == null || State == null || City == null || Location == null || Location.equalsIgnoreCase("null")) Location = "All Locations";
		try {
			waitForElement(filter_Panel, 25);
			//scrollByElement(PageTitle);
			waitUntilLoad(driver);
			if(!Group.equals("None")) {			
				clickelement(fiterGroup);
				waitForElement(filterDropDown, 20);
				group = fiterGroup.findElement(By.xpath("//div[text()='"+Group+"']"));
				waitForElement(group, 10);
				clickelement(group);
				waitUntilLoad(driver);
			}
			if(!CountryCode.equals("All Countries")) {
				clickelement(FilterCountry);
				waitForElement(filterDropDown, 20);
				country = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[1]//div[text()='"+CountryCode.toUpperCase()+"']"));
				waitForElement(country, 10);
				Thread.sleep(1000);
				clickelement(country);
				waitUntilLoad(driver);
			}
			if(!State.equals("All States")) {			
				clickelement(FilterState);
				waitForElement(filterDropDown, 20);
				state = FilterState.findElement(By.xpath("//div[text()='"+State+"']"));
				waitForElement(state, 10);
				Thread.sleep(1000);
				clickelement(state);
				waitUntilLoad(driver);
			}
			if(!City.equals("All Cities")) {
				clickelement(FilterCity);
				waitForElement(filterDropDown, 20);
				city = FilterCity.findElement(By.xpath("//div[text()='"+City+"']"));
				waitForElement(city, 10);
				Thread.sleep(1000);
				clickelement(city);
				waitUntilLoad(driver);
			}
			if(!Location.equals("All Locations")) {			
				clickelement(Filterlocation);
				waitForElement(filterDropDown, 20);
				location = Filterlocation.findElement(By.xpath("//div[text()='"+Location+"']"));
				waitForElement(location, 10);
				Thread.sleep(1000);
				clickelement(location);
				waitUntilLoad(driver);
			}
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("searched Country/State/City/Location may not be there or may be a typo error please check it");
		}
		waitUntilLoad(driver);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
	}
	
	public void clickApplyFilterBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if(Apply_filter.isDisplayed()) {
			clickelement(Apply_filter);
			Thread.sleep(3000);
		}
	}
	
	public void clickSearchApplyFilterBTN() throws InterruptedException
	{
		JSWaiter.waitJQueryAngular();
		if(Apply_filter.isDisplayed()) {
			clickelement(SearchApplyFilter);
			Thread.sleep(3000);
		}
	}
	
	public void applyKeywordSearch(String Keyword)
	{
		JSWaiter.waitJQueryAngular();
		KeywordSearch.sendKeys(Keyword);
		
	}
	
	public void clickAssignedLocationsTab()
	{
		JSWaiter.waitJQueryAngular();
		clickelement(AssignedLocationsTab);
	}

}
