package com.dac.main.POM_CA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.FileHandler;
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

	@FindBy(css = "div.highcharts-label.highcharts-tooltip.highcharts-color-0>span>table")
	private WebElement grphTable;

	
	
	/**
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 * for Global filtering reports
	 */
	public void applyFilter(String Country, String State, String City, String Location) {

		waitForElement(filter_Panel, 10);
		scrollByElement(filter_Panel);
		clickelement(FilterCountry);
		WebElement country = driver.findElement(By.xpath("//div[@value='" + Country + "']"));
		clickelement(country);
		clickelement(FilterState);
		WebElement state = driver.findElement(By.xpath("//div[@value='" + State + "']"));
		waitForElement(state, 10);
		clickelement(state);
		clickelement(FilterCity);
		WebElement city = driver.findElement(By.xpath("//div[@value='" + City + "']"));
		waitForElement(city, 10);
		clickelement(city);
		clickelement(Filterlocation);
		WebElement location = driver.findElement(By.xpath("//div[contains(text(),'" + Location + "')]"));
		waitForElement(location, 10);
		clickelement(location);
		clickelement(Apply_filter);

	}

	/**
	 * @return
	 * must implement overview report for all pages
	 */
	public abstract List<Map<String, String>> getOverviewReport();

	public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
		String visibility_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
		FileHandler.renameTo(new File(Exportpath + visibility_export), Exportpath + export);
	}

	
	
	/**
	 * @return
	 * History graph value read
	 */
	public List<Map<String, String>> verifyHistoryGraph() {
		waitForElement(hstryGrph, 10);
		scrollByElement(hstryGrph);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
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
	
	public float formatFloat(String s) {
		
		if(s.contains("%"))
		 return Float.parseFloat(s.replace("%", ""));
		else if(s.equals("-")|s.isEmpty())
			return 0f;
		else
			return Float.parseFloat(s);
		
	}

}
