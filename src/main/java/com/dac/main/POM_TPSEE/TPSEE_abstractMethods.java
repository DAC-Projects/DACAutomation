package com.dac.main.POM_TPSEE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.FileHandler;
import resources.formatConvert;

public abstract class TPSEE_abstractMethods extends BasePage implements TPSEERepository {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	String tooltipvalue;
	List<WebElement> rows;

	public TPSEE_abstractMethods(WebDriver driver) {
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

	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 
	
	//section of overall report
	@FindBy(xpath = "//*[@id=\"divOverallScoreHeader\"]//*[@id=\"divOverallScoreHeader\\\"]/div[1]//*[@id=\"divOverallScoreValue\"]")
	private WebElement overall;

	//site tableesTable
	@FindBy(css = "div#barContainer.barContainer") 
	public WebElement siteTable;
	
	@FindBy(xpath = "//*[@id=\"accuracyScoresTable\"]")
	public WebElement accuracysite;

	/**
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 *            for Global filtering reports
	 */
	public void applyFilter(String Country, String State, String City, String Location) {

		waitForElement(filter_Panel, 25);
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
		waitForElement(location, 20);
		clickelement(location);
		scrollByElement(location);
		clickelement(Apply_filter);
		scrollByElement(hstryGrph);
		waitForElement(grphtooltip,10);
	}

	/**
	 * @return must implement overview report for all pages
	 */
	public abstract List<Map<String, String>> getOverviewReport();

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
		
		//read the tooltip variables
		tooltipvalue = grphtooltip.getText();
		System.out.println("Reading tooltipdata **********");
		System.out.println("tooltipvalue is "+ tooltipvalue);
		
		//read the tooltip variables
		rows = grphtooltip.findElements(By.tagName("span"));
		String[][] table = readTable(grphtooltip);
		
		//adding tooltipdata into List
	List<Map<String, String>> tooltipdata = new ArrayList<Map<String, String>>();
		for (int i = 0; i < table.length; i += 4) {
			Map<String, String> kMap = new HashMap<String, String>();
			kMap.put("Date", table[i][0]);
			kMap.put(table[i + 1][0], table[i + 1][1]);
			kMap.put(table[i + 2][0], table[i + 2][1]);
			tooltipdata.add(kMap);
		}
		/*System.out.println(tooltipdata.get(0).get("Date"));
		System.out.println(tooltipdata.get(0).get("NoofLocation"));
		System.out.println(tooltipdata.get(0).get("score"));*/
		//returning tooltipdata from list
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
		System.out.println("\n Reading site table********** \n");
		List<Map<String, String>> siteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"barContainer\"]//div//div"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    while (program.hasNext()) {
		        String values = program.next().getText();

		        if(!values.equals("null"))
		        {
		            System.out.println("" +values);
		        }
		        else
		        {
		            System.out.println("No sites displayed");
		        }

		    }
		return siteTableData;
	}
	
	
	//Comparing values of overall visibility score and visibility export data
	public void compareExprttoOvervw(List<Map<String, String>> exportData, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : exportData) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(formatFloat(m1.get("score")), formatFloat(m2.get("overallscore")), 0.05f,
							"Verifying score for" + m1.get("compName"));
				}
			}
		}
	}
	
	//Comparing values of overall visibility score and visibility tooltip data
	public void compareReportnGraph(List<Map<String, String>> tooltipdata, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : tooltipdata) {
				if (m1.get("compName").equals(m2.get("overall"))) {
					Assert.assertEquals(formatFloat(m1.get("overallscore")), formatFloat(m2.get("Overallscore")), 0.05f,
							"Verifying score for" + m1.get("NoofLocation"));
				}
			}
		}
	}
	
	//getting data of accuracy score table
	public List<Map<String, String>> verifyaccuracySitetable() {
		waitForElement(accuracysite, 10);
		scrollByElement(accuracysite);
		System.out.println("\n Reading accuracy site table********** \n");
		List<Map<String, String>> accuracysiteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"accuracyScoresTable\"]"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    while (program.hasNext()) {
		        String values = program.next().getText();

		        if(!values.equals("null"))
		        {
		            System.out.println("" +values);
		        }
		        else
		        {
		            System.out.println("No sites displayed");
		        }

		    }
		return accuracysiteTableData;
	}

}