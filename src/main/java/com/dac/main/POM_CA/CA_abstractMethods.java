
package com.dac.main.POM_CA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.CurrentState;
import resources.FileHandler;
import resources.JSWaiter;
import resources.formatConvert;

public abstract class CA_abstractMethods extends BasePage implements CARepository {
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	List<WebElement> rows;

	public CA_abstractMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	// Global Filter locators
	@FindBy(xpath="(//*[@id='divCIAccuracy']/h1)")
	private WebElement CATitleContent;
	
	@FindBy(id="myGroups")
	private WebElement fiterGroup;
	
	@FindBy(xpath="//*[@class='menu transition visible']")
	private WebElement filterDropDown;

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

	// History graph
	@FindBy(css = "rect.highcharts-plot-background")
	public WebElement hstryGrph;

	@FindBy(css = "div.highcharts-label.highcharts-tooltip.highcharts-color-undefined")
	private WebElement grphTable;
	
	@FindBy(xpath="//*[@id='compIntAccuracyContainer']//h3")
	public WebElement Accuracysitescore;
	
	@FindBy(xpath="(//*[text()='Bing'])[1]")
	public WebElement site1;
	
	@FindBy(xpath="(//*[text()='Yellowpages.ca'])[1]")
	public WebElement site2;
	
	@FindBy(xpath="(//*[text()='Facebook'])[1]")
	public WebElement site3;
	
	@FindBy(xpath="(//*[text()='Foursquare'])[1]")
	public WebElement site4;
	
	

//	@FindBy(css = "div.highcharts-label.highcharts-tooltip.highcharts-color-0>span>table")
//	private WebElement grphTable;

	// site table
	@FindBy(css = "table#compIntVisibilitySitesTable")
	public WebElement siteTable;

	/**
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 *            for Global filtering reports
	 *            
	 */
	
	
	
public void applyFilter(String Country, String State, String City, String Location) {

	
	
	JSWaiter.waitJQueryAngular();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("apply_filter")));
	WebElement country,state,city,location;
	if (Country == null || Country.equalsIgnoreCase("null")) Country = "All Countries";
	if (Country == null || State == null || State.equalsIgnoreCase("null")) State = "All States";
	if (Country == null || State == null || City == null || City.equalsIgnoreCase("null")) City = "All Cities"; 
	if (Country == null || State == null || City == null | Location == null || Location.equalsIgnoreCase("null")) Location = "All Locations";
	try {
		waitForElement(filter_Panel, 25);

		scrollByElement(CATitleContent);
		waitUntilLoad(driver);
		if(!Country.equals("All Countries")) {
			clickelement(FilterCountry);
			waitForElement(filterDropDown, 20);
			country =FilterCountry.findElement(By.xpath("//div[text()='" + Country + "']"));
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
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("apply_filter")));
	}


/**
 * This method used to click on the Apply Filter button		*/
public void clickApplyFilterBTN() throws InterruptedException {
	JSWaiter.waitJQueryAngular();
	if(Apply_filter.isDisplayed()) {
		clickelement(Apply_filter);
		Thread.sleep(3000);
	}
}

	/**
	 * @return must implement overview report for all pages
	 */
	public abstract List<Map<String, String>> getOverviewReport();


	/**
	 * @param filename
	 * @param export
	 * Converts passed file to excel format and renames the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 * 
	 * 
	 */
	
				   
		

	public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
		String report_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
		FileHandler.renameTo(new File(Exportpath + report_export), Exportpath + export);
	}

	/**
	 * @return History graph value read
	 */
	public List<Map<String, String>> verifyHistoryGraph() {
		//display tool tip
		waitForElement(hstryGrph, 10);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
		//read the tooltip variables
		rows = grphTable.findElements(By.tagName("tr"));
		String[][] table = readTable(grphTable);

		List<Map<String, String>> tooltipdata = new ArrayList<Map<String, String>>();
		for (int i = 0; i < table.length / 4; i += 4) {
			Map<String, String> kMap = new HashMap<String, String>();
			kMap.put("compName", table[i][0]);
			kMap.put(table[i + 1][0], table[i + 1][1]);
			kMap.put(table[i + 2][0], table[i + 2][1]);
			kMap.put(table[i + 3][0], table[i + 3][1]);
			tooltipdata.add(kMap);
		}

		System.out.println(tooltipdata.get(0).get("compName"));
		System.out.println(tooltipdata.get(0).get("Date"));
		System.out.println(tooltipdata.get(0).get("Overall"));
		System.out.println(tooltipdata.get(0).get("Total Locations"));
		return tooltipdata;

	}

	/**
	 * @param s
	 * @return Finds no within a string and returns as float
	 */
	public float formatFloat(String s) {

		Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
		Matcher matcher = regex.matcher(s);
		if (matcher.find())
			return Float.parseFloat(matcher.group(1));
		else
			return 0f;

	}
	/**
	 * @param s
	 * @to read site table values
	 */
	public List<Map<String, String>> verifySitetable() {
		waitForElement(siteTable, 10);
		scrollByElement(siteTable);
		System.out.println("Reading site table**********");
		String[][] table = readTable(siteTable);
		List<Map<String, String>> siteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		for (int j = 0; j < table[0].length - 1; j++) {

			kMap.put("compName", table[0][j + 1]);
			for (int i = 1; i < table.length; i++) {
				kMap.put(table[i][0], table[i][j + 1]);
			}
			siteTableData.add(kMap);
		}

		System.out.println("MAP******************MAP");
		for (String name : siteTableData.get(1).keySet()) {

			String key = name.toString();
			String value = siteTableData.get(1).get(name).toString();
			System.out.println(key + " " + value);
		}

		return siteTableData;

	}

	public void compareExprttoOvervw(List<Map<String, String>> exportData, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : exportData) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(formatFloat(m1.get("score")), formatFloat(m2.get("Overall")), 0.05f,
							"Verifying score for" + m1.get("compName"));
				}
			}
		}
	}

	public void compareReportnGraph(List<Map<String, String>> tooltipdata, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : tooltipdata) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(formatFloat(m1.get("score")), formatFloat(m2.get("Overall")), 0.05f,
							"Verifying score for" + m1.get("compName"));
				}
			}
		}

	}
	
public void AccuracyScrolldata()
{
	
	waitForElement(site1, 25);
	scrollByElement(site1);
	waitUntilLoad(driver);
	
}	
public void AccuracyScrolldataSite2()
{
	waitForElement(site2, 25);
	scrollByElement(site2);
	waitUntilLoad(driver);
}

public void AccuracyScrolldataSite3()
{
	waitForElement(site3, 25);
	scrollByElement(site3);
	waitUntilLoad(driver);
}

public void AccuracyScrolldataSite4()
{
	waitForElement(site4, 25);
	scrollByElement(site4);
	waitUntilLoad(driver);
}

}





