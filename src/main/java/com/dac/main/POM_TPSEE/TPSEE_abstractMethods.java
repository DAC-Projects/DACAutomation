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

import resources.ExcelHandler;
import resources.FileHandler;
import resources.JSWaiter;
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
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	// Global Filter locators 

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
	
	/**
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 *            for Global filtering reports
	 */
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
			scrollByElement(PageTitle);
			waitUntilLoad(driver);
			if(!Group.equals("None")) {			
				clickelement(fiterGroup);
				waitForElement(filterDropDown, 20);
				group = fiterGroup.findElement(By.xpath("//div[@data-value='"+Group+"']"));
				waitForElement(group, 10);
				clickelement(group);
				waitUntilLoad(driver);
			}
			if(!CountryCode.equals("All Countries")) {
				clickelement(FilterCountry);
				waitForElement(filterDropDown, 20);
				country = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[1]//div[@data-value='"+CountryCode.toUpperCase()+"']"));
				waitForElement(country, 10);
				Thread.sleep(1000);
				clickelement(country);
				waitUntilLoad(driver);
			}
			if(!State.equals("All States")) {			
				clickelement(FilterState);
				waitForElement(filterDropDown, 20);
				state = FilterState.findElement(By.xpath("//div[@data-value='"+State+"']"));
				waitForElement(state, 10);
				Thread.sleep(1000);
				clickelement(state);
				waitUntilLoad(driver);
			}
			if(!City.equals("All Cities")) {
				clickelement(FilterCity);
				waitForElement(filterDropDown, 20);
				city = FilterCity.findElement(By.xpath("//div[@data-value='"+City+"']"));
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

	//convert exported file from csv to xlsx
	public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
		String report_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
		FileHandler.renameTo(new File(Exportpath + report_export), Exportpath + export);
		
	}
	
	public void renamefile(String filename, String export) throws FileNotFoundException, IOException{
		FileHandler.renameTo(new File(Exportpath + filename), Exportpath + export);
	}	

	/**
	 * @return History graph value read
	 */
	public List<Map<String, String>> verifyHistoryGraph() {
		
		//display tool tip
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
		
		//read the tooltip variables
		tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +tooltipvalue);
		
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
		waitForElement(siteTable, 30);
		scrollByElement(siteTable);
		System.out.println("\n Reading site table********** \n");
		List<Map<String, String>> siteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='barContainer']//div//div"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    while (program.hasNext()) {
		    	String values = program.next().getText();
		        
		        if(!values.equals("null"))
		        {
		            System.out.println("" +values);
		        }
		        else
		        {
		            System.out.println("\n No sites displayed \n");
		        }
		        	siteTableData.add(kMap);
		    }
		return siteTableData;
	}
	
	//Comparing values of overall visibility score and visibility export data
	public void compareExprttoOvervw(List<Map<String, String>> exportData, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : exportData) {
				if (m1.get("NoofLocation").equals(m2.get("compName"))) {
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
	//To get Vendors List displaying in the application
	public List<Map<String, String>> verifySitevendors() {
			JSWaiter.waitJQueryAngular();
			waitForElement(vendorslist, 40);
			scrollByElement(vendorslist);
			Map<String, String> kMap;
			List<Map<String, String>> Vendors = new ArrayList<Map<String, String>>();
			List<WebElement> elements = driver.findElements(By.xpath("//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    kMap = new HashMap<String, String>();
		        
		    //reading Vendors data
		    while (program.hasNext()) {
		        String values = program.next().getText();
		        if(!values.equals("null"))
		        {
		        	kMap.put("vendors", values);
		        	System.out.println("\n" +values);
		        }
		        else
		        {
		            System.out.println("\n No sites displayed \n");
		        }
		        //adding into the map
		        Vendors.add(kMap);
		    }
			return Vendors;
			}
	//getting data of accuracy score table
	public List<Map<String, String>> verifyaccuracySitetable() {
		waitForElement(accuracysite, 30);
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
		            kMap.put("sites", values);
		        }
		        else
		        {
		            System.out.println("No sites displayed");
		        }
		        accuracysiteTableData.add(kMap);
		    }
		return accuracysiteTableData;
	}
	
	//Adding Vendors List to Array 
		public List<Map<String, String>> vendorsList() throws Exception {
								
					List<Map<String, String>> Vendorslist = new ArrayList<Map<String, String>>();
					// Creation of HashMap 
			    	  Map<String, String> kMap = new HashMap<String, String>();
			    	   ExcelHandler a = new ExcelHandler("./data/VendorList.xlsx","VendorList");
			    	   a.deleteEmptyRows();
			    	   String[][] vendors = a.getExcelTable();
			    		int colSize = vendors[0].length;
			    		String size = Integer.toString(colSize);
			    		for (int col = 1; col < colSize; col++) {
			    			for (int i = 1; i < vendors.length; i++) {
			    				kMap.put("Vendors", vendors[0][col]);
			    				kMap.put(vendors[i][0], vendors[i][col]);
			    			}
			      	  Vendorslist.add(kMap);	
			    	}
						return Vendorslist;
				}
		
		//Comparing Vendors List from application and Vendors List from Array
		public void comparevendorsListnverifySitevendors(List<Map<String, String>> verifySitevendors,
					List<Map<String, String>> vendorsList) {
				for (Map<String, String> m1 : verifySitevendors) {
					for (Map<String, String> m2 : vendorsList) {
						Assert.assertEquals(m1.size(), m2.size());
						Assert.assertEquals(m1.get("vendors"), m2.get("vendors"), "Data Matches");
					}
				}
			}
}