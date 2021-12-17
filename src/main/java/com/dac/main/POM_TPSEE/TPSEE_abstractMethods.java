package com.dac.main.POM_TPSEE;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.FileHandler;
import resources.JSWaiter;
import resources.formatConvert;

public abstract class TPSEE_abstractMethods extends BasePage implements TPSEERepository {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	String tooltipvalue;
	List<WebElement> rows;
	List<String> List1 = new ArrayList<String>();
	int sortrow;
	SoftAssert soft = new SoftAssert();

	public TPSEE_abstractMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	// Global Filter locators

	/*------------------------ KPI Dashboard ------------------------*/

	@FindBy(xpath = "//*[@id='location']/div[2]/span")
	private static WebElement Locations;

	@FindBy(xpath = "//*[@id='visibility']/div[2]/span")
	private static WebElement Visibilityscore;

	@FindBy(xpath = "//*[@id='visibility']/div[2]/div[2]/span")
	private static WebElement VisibilityLoc;

	@FindBy(xpath = "//*[@id='accuracy']/div[2]/span")
	private static WebElement Accuracyscore;

	@FindBy(xpath = "//*[@id='accuracy']/div[2]/div[2]/span")
	private static WebElement AccuracyLoc;

	@FindBy(xpath = "//*[@id='contentAnalysis']/div[2]/span")
	private static WebElement Contentscore;

	@FindBy(xpath = "//*[@id='contentAnalysis']/div[2]/div[2]/span")
	private static WebElement ContentLoc;

	@FindBy(xpath = "//*[@id='googleRanking']/div[2]/span")
	private static WebElement GRScore;

	@FindBy(xpath = "//*[@id='googleRanking']/div[2]/div[2]/span")
	private static WebElement GRLoc;

	/*------------------------ KPI Dashboard ------------------------*/

	/*------------------------ Filter Criteria ------------------------*/

	@FindBy(xpath = "//*[@class='menu transition visible']")
	private WebElement filterDropDown;

	@FindBy(id = "myGroups")
	private WebElement fiterGroup;

	@FindBy(xpath = "//div//select[@name='group']/..")
	private WebElement LAVfiterGroup;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList")
	private WebElement FilterCountry;

	@FindBy(xpath = "//div//select[@name='country']/..")
	private WebElement LAVFilterCountry;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList1")
	private WebElement FilterState;

	@FindBy(xpath = "//div//select[@name='state']/..")
	private WebElement LAVFilterState;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList2")
	private WebElement FilterCity;

	@FindBy(xpath = "//div//select[@name='city']/..")
	private WebElement LAVFilterCity;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList3")
	private WebElement Filterlocation;

	@FindBy(xpath = "//div//select[@name='location']/..")
	private WebElement LAVFilterlocation;

	@FindBy(css = "button#apply_filter")
	private WebElement Apply_filter;

	@FindBy(xpath = "//button[@id='applyFilterBtn']")
	private WebElement LAV_Apply_Filter;

	@FindBy(css = "div#filter-options")
	public WebElement filter_Panel;

	@FindBy(css = "div.tsee-location-filter.row")
	public WebElement LAVfilter_Panel;

	@FindBy(xpath = "//div[@id='singleLocationReport']//div[@class='location-address']")
	public WebElement Location_Filter_Address;

	@FindBy(xpath = "//div[@id='singleLocationReport']//div[@class='item-content']")
	public WebElement Site_Location_Address;

	/*------------------------ Filter Criteria ------------------------*/

	@FindBy(xpath = "//*[@id='page-content']//h1")
	private WebElement PageTitle;

	@FindBy(xpath = "//*[@id='page-content']//h3")
	private WebElement PageTitle1;

	@FindBy(xpath = "//p[@class='lead']")
	private WebElement PageTitletext;

	// History graph
	@FindBy(css = "rect.highcharts-plot-background")
	public WebElement hstryGrph;

	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip;

	@FindBy(xpath = "//div[@class='highcharts-label highcharts-tooltip-box highcharts-color-none']//span//span[1]")
	private WebElement grphtooltipDate;

	@FindBy(xpath = "//div[@class='highcharts-label highcharts-tooltip-box highcharts-color-none']//span")
	private WebElement grphtooltipScore;

	String grph = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none";

	String grphGR = ".highcharts-label.highcharts-tooltip-box.highcharts-color-none";

	// section of overall report
	@FindBy(xpath = "//*[@id='divOverallScoreHeader']//*[@id='divOverallScoreHeader']/div[1]//*[@id='divOverallScoreValue']")
	private WebElement overall;

	// site tableesTable
	@FindBy(xpath = "//div[@class='barContainer']")
	public WebElement siteTable;

	@FindBy(xpath = "//*[@id='accuracyScoresTable']")
	public WebElement accuracysite;

	@FindBy(xpath = "//a[contains(text(),'Next')]")
	private WebElement pagination;

	@FindBy(xpath = "//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']")
	private WebElement vendorslist;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-group']/*[name()='g'])[1]")
	private WebElement highChartZoom;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[1]")
	private WebElement highChart_1M;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[2]")
	private WebElement highChart_3M;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[3]")
	private WebElement highChart_6M;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[4]")
	private WebElement highChart_YTD;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[5]")
	private WebElement highChart_1y;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[6]")
	private WebElement highChart_All;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-input-group']/*[name()='g'])[2]/*[name()='text']")
	private WebElement highChart_fromDate;

	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-input-group']/*[name()='g'])[4]/*[name()='text']")
	private WebElement highChart_toDate;

	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;

	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;

	@FindBy(xpath = "//*[@class='back-to-top btn btn-primary']")
	private WebElement Top;

	@FindBy(xpath = "//*[@data-handler='prev']")
	private WebElement prevMonth;

	@FindBy(xpath = "//*[@data-handler='next']")
	private WebElement nextMonth;

	@FindBy(xpath = "//*[@id = 'exportStartDate']")
	private WebElement pdffromdate;

	@FindBy(xpath = "//*[@id = 'exportEndDate']")
	private WebElement pdftoDate;

	@FindBy(xpath = "(//*[@class='highcharts-label highcharts-range-input'])[1]")
	private WebElement fromDate;

	@FindBy(xpath = "(//*[@class='highcharts-label highcharts-range-input'])[2]")
	private WebElement toDate;

	@FindBy(xpath = "//button//span[@class='walkme-custom-balloon-button-text' and contains(text(),'Cancel')]")
	private WebElement WalkMeCancel;

	@FindBy(xpath = "//span[@class= 'walkme-action-destroy-0 wm-close-link' and contains(text(),'Okay')]")
	private WebElement NotificationPopUp;

	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "//ul[@class='pagination']//li[@class='active']")
	private WebElement paginationFirst;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private List<WebElement> paginationLast;

	/**
	 * @param Group
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location for Global filtering reports
	 */
	public void LAVapplyGlobalFilter(String Group, String CountryCode, String State, String City, String Location) {
		JSWaiter.waitJQueryAngular();
		WebElement country, state, city, location, group;
		if (Group == null || Group.equalsIgnoreCase("none"))
			Group = "None";
		if (CountryCode == null || CountryCode.equalsIgnoreCase("null"))
			CountryCode = "All Countries";
		if (CountryCode == null || State == null || State.equalsIgnoreCase("null"))
			State = "All States";
		if (CountryCode == null || State == null || City == null || City.equalsIgnoreCase("null"))
			City = "All Cities";
		if (CountryCode == null || State == null || City == null || Location == null
				|| Location.equalsIgnoreCase("null"))
			Location = "All Locations";
		try {
			waitForElement(LAVfilter_Panel, 10);
			waitUntilLoad(driver);
			if (!Group.equals("None")) {
				clickelement(LAVfiterGroup);
				waitForElement(filterDropDown, 10);
				group = driver.findElement(By.xpath(
						"(*//div[contains(@class,'selection ui dropdown fluid search')])[1]//div[contains(@class,'item') and contains(text(),'"
								+ Group + "')]"));
				waitForElement(group, 5);
				clickelement(group);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!CountryCode.equals("All Countries")) {
				clickelement(LAVFilterCountry);
				waitForElement(filterDropDown, 10);
				country = driver.findElement(By.xpath(
						"(*//div[contains(@class,'selection ui dropdown fluid search')])[2]//div[contains(@class,'item') and contains(text(),'"
								+ CountryCode.toUpperCase() + "')]"));
				// (//*[contains(@class,'myList')])[1]//div[contains(text(),'US')]"+CountryCode.toUpperCase()+"']
				waitForElement(country, 5);
				clickelement(country);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!State.equals("All States")) {
				clickelement(LAVFilterState);
				waitForElement(filterDropDown, 10);
				state = driver.findElement(By.xpath(
						"(*//div[contains(@class,'selection ui dropdown fluid search')])[3]//div[contains(@class,'item') and contains(text(),'"
								+ State + "')]"));
				waitForElement(state, 5);
				clickelement(state);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!City.equals("All Cities")) {
				clickelement(LAVFilterCity);
				waitForElement(filterDropDown, 10);
				city = driver.findElement(By.xpath(
						"(*//div[contains(@class,'selection ui dropdown fluid search')])[4]//div[contains(@class,'item') and contains(text(),'"
								+ City + "')]"));
				waitForElement(city, 5);
				clickelement(city);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!Location.equals("All Locations")) {
				clickelement(LAVFilterlocation);
				waitForElement(filterDropDown, 10);
				location = driver.findElement(By.xpath(
						"(*//div[contains(@class,'selection ui dropdown fluid search')])[5]//div[contains(@class,'item') and contains(text(),'"
								+ Location + "')]"));
				waitForElement(location, 5);
				Thread.sleep(1000);
				clickelement(location);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("searched Country/State/City/Location may not be there or may be a typo error please check it");
		}
		waitUntilLoad(driver);
	}

	/**
	 * To get the list of groups,countries,states,city,locations
	 */
	public List<String> getList(List<WebElement> ele) {
		List<String> data = new ArrayList<String>();
		int size = ele.size();
		System.out.println("The size of webelemnt is : " + size);
		for (WebElement webElement : ele) {
			String dat = webElement.getText();
			System.out.println("The data is : " + dat);
			data.add(dat);
			System.out.println("The list contains : " + data);
			if (data.contains("None")) {
				data.remove("None");
				System.out.println("The data contains after removing None : " + data);
			} else if (data.contains("All Countries")) {
				data.remove("All Countries");
				System.out.println("The data contains after removing All Countries : " + data);
			} else if (data.contains("All States")) {
				data.remove("All States");
				System.out.println("The data contains after removing All States : " + data);
			} else if (data.contains("All Cities")) {
				data.remove("All Cities");
				System.out.println("The data contains after removing All Cities : " + data);
			} else if (data.contains("All Locations")) {
				data.remove("All Locations");
				System.out.println("The data contains after removing All Locations : " + data);
			}
		}
		return data;
	}

	/**
	 * 
	 * To verify the order of filter data
	 */
	public void GetDataListnVerifyOrder(String Group, String CountryCode, String State, String City) {
		List<String> Groups = new ArrayList<String>();
		List<String> Country = new ArrayList<String>();
		List<String> States = new ArrayList<String>();
		List<String> Cities = new ArrayList<String>();
		try {
			waitForElement(LAVfilter_Panel, 10);
			waitUntilLoad(driver);
			clickelement(LAVfiterGroup);
			waitForElement(filterDropDown, 5);
			// BaseClass.addEvidence(driver, "Test to get data from group filter and verify
			// the order", "yes");
			List<WebElement> grps = driver.findElements(By.xpath(
					"((*//div[contains(@class,'selection ui dropdown fluid search')])[1]//div[contains(@class,'item')])"));
			Groups = getList(grps);
			System.out.println("The list of groups contains : " + Groups);
			WebElement group = driver.findElement(By.xpath(
					"(*//div[contains(@class,'selection ui dropdown fluid search')])[1]//div[contains(@class,'item') and contains(text(),'"
							+ Group + "')]"));
			waitForElement(group, 5);
			clickelement(group);
			waitUntilLoad(driver);

			List<String> groupss = new ArrayList<String>();
			groupss.addAll(Groups);
			Collections.sort(groupss);
			System.out.println("The groups after sorted : " + groupss);
			if (Groups.size() == groupss.size()) {
				for (int i = 0; i < Groups.size(); i++) {
					soft.assertTrue(Groups.get(i).equals(groupss.get(i)),
							"The group doesnot match is : " + Groups.get(i) + "with " + groupss.get(i));
				}
			} else {
				System.out.println("Lists not equal");
			}

			clickelement(LAVFilterCountry);
			waitForElement(filterDropDown, 10);
			List<WebElement> contry = driver.findElements(By.xpath(
					"((*//div[contains(@class,'selection ui dropdown fluid search')])[2]//div[contains(@class,'item')])"));
			Country = getList(contry);
			System.out.println("The list of country contains : " + Country);
			WebElement country = driver.findElement(By.xpath(
					"(*//div[contains(@class,'selection ui dropdown fluid search')])[2]//div[contains(@class,'item') and contains(text(),'"
							+ CountryCode.toUpperCase() + "')]"));
			waitForElement(country, 5);
			clickelement(country);
			waitUntilLoad(driver);

			List<String> coontry = new ArrayList<String>();
			coontry.addAll(Country);
			Collections.sort(coontry);
			System.out.println("The Countries after sort : " + coontry);
			if (Country.size() == coontry.size()) {
				for (int i = 0; i < Country.size(); i++) {
					soft.assertTrue(Country.get(i).equals(coontry.get(i)),
							"The Country doesnot match is : " + Country.get(i) + "with " + coontry.get(i));
				}
			} else {
				// System.out.println("Lists not equal");
			}

			clickelement(LAVFilterState);
			waitForElement(filterDropDown, 10);
			List<WebElement> sttate = driver.findElements(By.xpath(
					"((*//div[contains(@class,'selection ui dropdown fluid search')])[3]//div[contains(@class,'item')])"));
			States = getList(sttate);
			System.out.println("The list of state contains : " + States);
			WebElement state = driver.findElement(By.xpath(
					"(*//div[contains(@class,'selection ui dropdown fluid search')])[3]//div[contains(@class,'item') and contains(text(),'"
							+ State + "')]"));
			waitForElement(state, 5);
			clickelement(state);
			waitUntilLoad(driver);

			List<String> staate = new ArrayList<String>();
			staate.addAll(States);
			Collections.sort(staate);
			System.out.println("The States after sorted : " + staate);
			if (States.size() == staate.size()) {
				for (int i = 0; i < States.size(); i++) {
					soft.assertTrue(States.get(i).equals(staate.get(i)),
							"The group doesnot match is : " + States.get(i) + "with " + staate.get(i));

				}
			} else {
				System.out.println("Lists not equal");
			}

			Thread.sleep(3000);
			clickelement(LAVFilterCity);
			waitForElement(filterDropDown, 10);
			List<WebElement> ciity = driver.findElements(By.xpath(
					"((*//div[contains(@class,'selection ui dropdown fluid search')])[4]//div[contains(@class,'item')])"));
			Cities = getList(ciity);
			System.out.println("The list of cities contains : " + Cities);
			// BaseClass.addEvidence(driver, "Test to get data from City filter and verify
			// the order", "yes");
			WebElement city = driver.findElement(By.xpath(
					"(*//div[contains(@class,'selection ui dropdown fluid search')])[4]//div[contains(@class,'item') and contains(text(),'"
							+ City + "')]"));
			waitForElement(city, 5);
			clickelement(city);
			waitUntilLoad(driver);

			List<String> citty = new ArrayList<String>();
			citty.addAll(Cities);
			Collections.sort(citty);
			System.out.println("The Cities after sorted : " + citty);
			if (Cities.size() == citty.size()) {
				for (int i = 0; i < Cities.size(); i++) {
					soft.assertTrue(Cities.get(i).equals(citty.get(i)),
							"The group doesnot match is : " + Cities.get(i) + "with " + ciity.get(i));

				}
			} else {
				System.out.println("Lists not equal");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("searched Country/State/City/Location may not be there or may be a typo error please check it");
		}
		waitUntilLoad(driver);
		soft.assertAll();
	}

	/**
	 * @param Group
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location for Global filtering reports
	 */
	public void applyGlobalFilter(String Group, String CountryCode, String State, String City, String Location) {
		JSWaiter.waitJQueryAngular();
		WebElement country, state, city, location, group;
		if (Group == null || Group.equalsIgnoreCase("none"))
			Group = "None";
		if (CountryCode == null || CountryCode.equalsIgnoreCase("null"))
			CountryCode = "All Countries";
		if (CountryCode == null || State == null || State.equalsIgnoreCase("null"))
			State = "All States";
		if (CountryCode == null || State == null || City == null || City.equalsIgnoreCase("null"))
			City = "All Cities";
		if (CountryCode == null || State == null || City == null || Location == null
				|| Location.equalsIgnoreCase("null"))
			Location = "All Locations";
		try {
			waitForElement(filter_Panel, 10);
			waitUntilLoad(driver);
			if (!Group.equals("None")) {
				clickelement(fiterGroup);
				waitForElement(filterDropDown, 10);
				group = fiterGroup.findElement(By.xpath(
						"//*[@id = 'myGroups']//div[contains(@class,'item') and contains(text(),'" + Group + "')]"));
				waitForElement(group, 5);
				clickelement(group);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!CountryCode.equals("All Countries")) {
				clickelement(FilterCountry);
				waitForElement(filterDropDown, 10);
				country = driver.findElement(By
						.xpath("(//*[contains(@class,'myList')])[1]//div[contains(@class,'item') and contains(text(),'"
								+ CountryCode.toUpperCase() + "')]"));
				waitForElement(country, 5);
				clickelement(country);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!State.equals("All States")) {
				clickelement(FilterState);
				waitForElement(filterDropDown, 10);
				state = FilterState.findElement(
						By.xpath("(//*[contains(@class,'myList')])[2]//div[contains(text(),'" + State + "')]"));
				// (//*[contains(@class,'myList')])[2]//div[@data-value=contains(text(),'Illinois')]"+State+"
				waitForElement(state, 5);
				clickelement(state);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!City.equals("All Cities")) {
				clickelement(FilterCity);
				waitForElement(filterDropDown, 10);
				city = FilterCity.findElement(
						By.xpath("(//*[contains(@class,'myList')])[3]//div[contains(text(),'" + City + "')]"));
				waitForElement(city, 5);
				clickelement(city);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
			if (!Location.equals("All Locations")) {
				clickelement(Filterlocation);
				waitForElement(filterDropDown, 10);
				location = Filterlocation.findElement(
						By.xpath("(//*[contains(@class,'myList')])[4]//div[contains(text(),'" + Location + "')]"));
				waitForElement(location, 5);
				clickelement(location);
				waitUntilLoad(driver);
				JSWaiter.waitJQueryAngular();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("searched Country/State/City/Location may not be there or may be a typo error please check it");
		}
		waitUntilLoad(driver);
	}

	/**
	 * to get and verify order of filter
	 * 
	 * @param Group
	 * @param CountryCode
	 * @param State
	 * @param City
	 * @param Location
	 */
	public void GetDataListnVerifyOrderBingFacebook(String Group, String CountryCode, String State, String City,
			String Location) {
		List<String> Groups = new ArrayList<String>();
		List<String> Country = new ArrayList<String>();
		List<String> States = new ArrayList<String>();
		List<String> Cities = new ArrayList<String>();
		try {
			waitForElement(filter_Panel, 10);
			waitUntilLoad(driver);
			clickelement(fiterGroup);
			waitForElement(filterDropDown, 10);
			List<WebElement> grps = driver
					.findElements(By.xpath("//*[@id = 'myGroups']//div[contains(@class,'item')]"));
			Groups = getList(grps);
			System.out.println("The list of groups contains : " + Groups);
			WebElement group = driver.findElement(By
					.xpath("//*[@id = 'myGroups']//div[contains(@class,'item') and contains(text(),'" + Group + "')]"));
			waitForElement(group, 5);
			clickelement(group);
			waitUntilLoad(driver);

			List<String> groupss = new ArrayList<String>();
			groupss.addAll(Groups);
			Collections.sort(groupss);
			System.out.println("The groups after sorted : " + groupss);
			if (Groups.size() == groupss.size()) {
				for (int i = 0; i < Groups.size(); i++) {
					soft.assertTrue(Groups.get(i).equals(groupss.get(i)),
							"The group doesnot match is : " + Groups.get(i) + "with " + groupss.get(i));
				}
			} else {
				System.out.println("Lists not equal");
			}

			clickelement(FilterCountry);
			waitForElement(filterDropDown, 10);
			List<WebElement> contry = driver
					.findElements(By.xpath("(//*[contains(@class,'myList')])[1]//div[contains(@class,'item')]"));
			Country = getList(contry);
			System.out.println("The list of country contains : " + Country);
			WebElement country = driver.findElement(
					By.xpath("(//*[contains(@class,'myList')])[1]//div[contains(@class,'item') and contains(text(),'"
							+ CountryCode.toUpperCase() + "')]"));
			waitForElement(country, 5);
			clickelement(country);
			waitUntilLoad(driver);

			List<String> coontry = new ArrayList<String>();
			coontry.addAll(Country);
			Collections.sort(coontry);
			System.out.println("The Countries after sort : " + coontry);
			if (Country.size() == coontry.size()) {
				for (int i = 0; i < Country.size(); i++) {
					soft.assertTrue(Country.get(i).equals(coontry.get(i)),
							"The group doesnot match is : " + Country.get(i) + "with " + coontry.get(i));
				}
			} else {
				// System.out.println("Lists not equal");
			}

			clickelement(FilterState);
			waitForElement(filterDropDown, 10);
			List<WebElement> sttate = driver
					.findElements(By.xpath("(//*[contains(@class,'myList')])[2]//div[contains(@class,'item')]"));
			States = getList(sttate);
			System.out.println("The list of state contains : " + States);
			// BaseClass.addEvidence(driver, "Test to get data from State filter and verify
			// the order", "yes");
			WebElement state = driver.findElement(
					By.xpath("(//*[contains(@class,'myList')])[2]//div[contains(@class,'item') and contains(text(),'"
							+ State + "')]"));
			waitForElement(state, 5);
			clickelement(state);
			waitUntilLoad(driver);

			List<String> staate = new ArrayList<String>();
			staate.addAll(States);
			Collections.sort(staate);
			System.out.println("The States after sorted : " + staate);
			if (States.size() == staate.size()) {
				for (int i = 0; i < States.size(); i++) {
					soft.assertTrue(States.get(i).equals(staate.get(i)),
							"The group doesnot match is : " + States.get(i) + "with " + staate.get(i));

				}
			} else {
				System.out.println("Lists not equal");
			}

			clickelement(FilterCity);
			waitForElement(filterDropDown, 10);
			List<WebElement> ciity = driver
					.findElements(By.xpath("(//*[contains(@class,'myList')])[3]//div[contains(@class,'item')]"));
			Cities = getList(ciity);
			System.out.println("The list of cities contains : " + Cities);
			// BaseClass.addEvidence(driver, "Test to get data from City filter and verify
			// the order", "yes");
			WebElement city = driver.findElement(
					By.xpath("(//*[contains(@class,'myList')])[3]//div[contains(@class,'item') and contains(text(),'"
							+ City + "')]"));
			waitForElement(city, 5);
			clickelement(city);
			waitUntilLoad(driver);

			List<String> citty = new ArrayList<String>();
			citty.addAll(Cities);
			Collections.sort(citty);
			System.out.println("The Cities after sorted : " + citty);
			if (Cities.size() == citty.size()) {
				for (int i = 0; i < Cities.size(); i++) {
					soft.assertTrue(Cities.get(i).equals(citty.get(i)),
							"The group doesnot match is : " + Cities.get(i) + "with " + citty.get(i));

				}
			} else {
				System.out.println("Lists not equal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("searched Country/State/City/Location may not be there or may be a typo error please check it");
		}
		waitUntilLoad(driver);
		soft.assertAll();
	}

	/**
	 * This method used to click on the Apply Filter button
	 */
	public void clickApplyFilterBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if (Apply_filter.isDisplayed()) {
			clickelement(Apply_filter);
			Thread.sleep(1000);
		}
	}

	/**
	 * This method used to click on the Apply Filter button
	 */
	public void clickApplyFilterBTNLAV() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if (LAV_Apply_Filter.isDisplayed()) {
			clickelement(LAV_Apply_Filter);
			Thread.sleep(1000);
		}
	}

	/**
	 * @return must implement overview report for all pages
	 */
	public abstract List<Map<String, String>> getOverviewReport();

	/**
	 * convert exported file from csv to xlsx
	 * 
	 * @param filename
	 * @param export
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
		String report_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
		FileHandler.renameTo(new File(Exportpath + report_export), Exportpath + export);

	}

	/**
	 * Renaming the file downloaded
	 * 
	 * @param filename
	 * @param export
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void renamefile(String filename, String export) throws FileNotFoundException, IOException {
		FileHandler.renameTo(new File(Exportpath + filename), Exportpath + export);
	}

	/**
	 * @return History graph value read
	 */
	public List<Map<String, String>> verifyHistoryGraph() {
		JSWaiter.waitJQueryAngular();
		waitForElement(hstryGrph, 10);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 1, 0).click().perform();
		tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + tooltipvalue);
		rows = grphtooltip.findElements(By.tagName("span"));
		String[][] table = readTable(grphtooltip);
		List<Map<String, String>> tooltipdata = new ArrayList<Map<String, String>>();
		for (int i = 0; i < table.length; i += 4) {
			Map<String, String> kMap = new HashMap<String, String>();
			kMap.put("Date", table[i][0]);
			kMap.put(table[i + 1][0], table[i + 1][1]);
			kMap.put(table[i + 2][0], table[i + 2][1]);
			tooltipdata.add(kMap);
		}
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
		JSWaiter.waitJQueryAngular();
		waitForElement(siteTable, 10);
		scrollByElement(siteTable);
		System.out.println("\n Reading site table********** \n");
		List<Map<String, String>> siteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='barContainer']//div//div"));
		java.util.Iterator<WebElement> program = elements.iterator();
		while (program.hasNext()) {
			String values = program.next().getText();
			if (!values.equals("null")) {
				System.out.println("" + values);
			} else {
				System.out.println("\n No sites displayed \n");
			}
			siteTableData.add(kMap);
		}
		return siteTableData;
	}

	/**
	 * Comparing values of overall visibility score and visibility export data
	 * 
	 * @param exportData
	 * @param ovrwRprtData
	 */
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

	/**
	 * Comparing values of overall visibility score and visibility tooltip data
	 * 
	 * @param tooltipdata
	 * @param ovrwRprtData
	 */
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

	/**
	 * To get Vendors List displaying in the application
	 * 
	 * @return
	 */
	public ArrayList<String> verifySitevendors() {
		JSWaiter.waitJQueryAngular();
		waitForElement(vendorslist, 10);
		scrollByElement(vendorslist);
		ArrayList<String> Vendors = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='col-lg-2 bar-chart-column']"));
		java.util.Iterator<WebElement> program = elements.iterator();
		while (program.hasNext()) {
			String values = program.next().getText();
			if (!values.equals("null")) {
				Vendors.add(values);
				System.out.println("\n" + values);
			} else {
				System.out.println("\n No sites displayed \n");
			}

		}
		return Vendors;
	}

	/**
	 * getting data of accuracy score table
	 * 
	 * @return
	 */
	public List<Map<String, String>> verifyaccuracySitetable() {
		JSWaiter.waitJQueryAngular();
		waitForElement(accuracysite, 10);
		scrollByElement(accuracysite);
		System.out.println("\n Reading accuracy site table********** \n");
		List<Map<String, String>> accuracysiteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='accuracyScoresTable']"));
		java.util.Iterator<WebElement> program = elements.iterator();
		while (program.hasNext()) {
			String values = program.next().getText();
			if (!values.equals("null")) {
				System.out.println("" + values);
				kMap.put("sites", values);
			} else {
				System.out.println("No sites displayed");
			}
			accuracysiteTableData.add(kMap);
		}
		return accuracysiteTableData;
	}

	/**
	 * Get data using column name and sum for Bing and GMB Page
	 * 
	 * @param PathofXL
	 * @param Col_Name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public int GetDataUsingColName(String PathofXL, String Col_Name) throws Exception {
		FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
		Workbook wb = new XSSFWorkbook(excelFilePath);
		Sheet sh = wb.getSheetAt(0);
		Row row = sh.getRow(0);
		int col = row.getLastCellNum();
		int Last_row = sh.getLastRowNum();
		int col_num = 0;
		System.out.println("" + col);

		for (int i = 0; i < row.getLastCellNum(); i++) {
			if ((row.getCell(i).toString()).equals(Col_Name)) {
				col_num = i;
				System.out.println("" + col_num);
			}
		}
		String s = null;
		int cellValue = 0;
		int y = 0;
		int sum = 0;
		for (int j = 1; j <= Last_row; j++) {
			row = sh.getRow(j);
			Cell cell = row.getCell(col_num);
			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					String cellValue1 = cell.getStringCellValue().toString();
					if (cellValue1.contains("-")) {
						s = cellValue1.replace("-", "0");
						y = Integer.parseInt(s);
						System.out.println("\n " + s);
						sum = sum + y;
					}
				} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					double cellValue2 = cell.getNumericCellValue();
					cellValue = (int) cellValue2;
					System.out.println("\n " + cellValue);
					sum = sum + cellValue;
				}
				System.out.println("" + sum);
			} else {
				System.out.println("Smt wrong");
			}
			System.out.println(":" + sum);
			wb.close();
		}
		return sum;
	}

	/**
	 * To get site details
	 * 
	 * @param PathofXL
	 * @param Col_Name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation") 
	public ArrayList<String> GetSiteDataUsingColName(String PathofXL, String Col_Name) throws Exception {
		FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
		Workbook wb = new XSSFWorkbook(excelFilePath);
		Sheet sh = wb.getSheetAt(0);
		Row row = sh.getRow(0);
		int col = row.getLastCellNum();
		int Last_row = sh.getLastRowNum();
		int col_num = 0;
		System.out.println("" + col);
		ArrayList<String> sites = new ArrayList<String>();
		for (int i = 0; i <= row.getLastCellNum() - 1; i++) {
			if ((row.getCell(i).toString()).equals(Col_Name)) {
				col_num = i;
				System.out.println("" + col_num);
			}
		}
		String cellValue = null;
		for (int j = 1; j <= Last_row; j++) {
			row = sh.getRow(j);
			Cell cell = row.getCell(col_num);
			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue().toString();
					sites.add(cellValue);
				}
				System.out.println(":" + sites);
				wb.close();
			}
		}
		return sites;
	}

	/**
	 * Export as CSV/XLSX of overall report
	 * 
	 * @param ExportDropdown
	 * @param                ExportType[CSV/XLSX]
	 * @param                DatePicker/Calendar
	 * @param Date           to Select
	 * @param Export         button
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void exportVA(WebElement ExportDropdown, WebElement ExportType, WebElement DatePicker, WebElement SelectDate,
			WebElement ExportBtn) throws InterruptedException, FileNotFoundException, IOException {
		JSWaiter.waitJQueryAngular();
		waitForElement(ExportDropdown, 20);
		clickelement(ExportDropdown);
		waitForElement(ExportType, 20);
		clickelement(ExportType);
		waitForElement(DatePicker, 20);
		clickelement(DatePicker);
		waitForElement(SelectDate, 20);
		clickelement(SelectDate);
		waitForElement(ExportBtn, 20);
		clickelement(ExportBtn);
	}

	/**
	 * Export as CSV/XLSX of overall report
	 * 
	 * @param ExportType
	 * @param ExportBtn
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void exportVATable(WebElement ExportType, WebElement ExportBtn)
			throws InterruptedException, FileNotFoundException, IOException {
		JSWaiter.waitJQueryAngular();
		if (ExportType.isDisplayed() && ExportType.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(ExportType));
			scrollByElement(ExportType);
			ExportType.click();
			Thread.sleep(5000);
		}
		if (ExportBtn.isDisplayed() && ExportBtn.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(ExportBtn));
			scrollByElement(ExportBtn);
			ExportBtn.click();
			Thread.sleep(5000);
		}
	}

	/**
	 * Export visibility overview report below filter in pdf for current date
	 * 
	 * @param ExportDropdown
	 * @param ExportType
	 * @param SelectPDF
	 * @param LinkClick
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void exportasPDFCurrentDate(WebElement ExportDropdown, WebElement ExportType, WebElement SelectPDF,
			WebElement LinkClick) throws InterruptedException, FileNotFoundException, IOException {
		JSWaiter.waitJQueryAngular();
		if (ExportDropdown.isEnabled() & ExportDropdown.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(ExportDropdown));
			scrollByElement(ExportDropdown);
			action.moveToElement(ExportDropdown).click().perform();
			Thread.sleep(5000);
		}
		if (ExportType.isEnabled() & ExportType.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(ExportType));
			scrollByElement(ExportType);
			action.moveToElement(ExportType).moveToElement(SelectPDF).click().perform();
			Thread.sleep(20000);
		}
		if (LinkClick.isEnabled() & LinkClick.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(LinkClick));
			scrollByElement(LinkClick);
			action.moveToElement(LinkClick).click().perform();
			Thread.sleep(20000);
		}
	}

	/**
	 * Click on PDF Export Link
	 * 
	 * @param ExportBtn
	 * @param LinkClick
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void exportasPDFHistory(
			/* WebElement ExportDropdown, WebElement ExportType, WebElement SelectPDF, */ WebElement ExportBtn,
			WebElement LinkClick) throws InterruptedException, FileNotFoundException, IOException {
		JSWaiter.waitJQueryAngular();
		if (ExportBtn.isDisplayed() & ExportBtn.isEnabled()) {
			waitForElement(ExportBtn, 20);
			scrollByElement(ExportBtn);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// action.moveToElement(ExportBtn).click().perform();
			ExportBtn.click();
			Thread.sleep(20000);
		}
		if (LinkClick.isDisplayed() & LinkClick.isEnabled()) {
			waitForElement(LinkClick, 20);
			scrollByElement(LinkClick);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			action.moveToElement(LinkClick).click().perform();
			Thread.sleep(20000);
		}
	}

	/**
	 * Click on history button Pdf
	 * 
	 * @param ExportDropdown
	 * @param ExportType
	 * @param SelectPDF
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void exporthistrybtn(WebElement ExportDropdown, WebElement ExportType, WebElement SelectPDF)
			throws InterruptedException, FileNotFoundException, IOException {

		if (ExportDropdown.isDisplayed() & ExportDropdown.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(ExportDropdown));
			scrollByElement(ExportDropdown);
			action.moveToElement(ExportDropdown).click().perform();
			Thread.sleep(5000);
		}
		if (ExportType.isDisplayed() & ExportType.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(ExportType));
			action.moveToElement(ExportType).pause(1000).moveToElement(SelectPDF).click().perform();
			Thread.sleep(5000);

		}

	}

	/**
	 * To get the file extension and verify the same
	 * 
	 * @throws InterruptedException
	 */
	public void verifyfileextension() throws InterruptedException {
		String filename = getLastModifiedFile("./downloads");
		System.out.println(filename);
		String extension = (filename.substring(filename.lastIndexOf(".") + 1));
		System.out.println(extension);
		Assert.assertEquals("pdf", extension);

	}
	
	

	/**
	 * Get the initial point and date of the graph
	 * 
	 * @return initial/starting History graph value
	 * @throws ParseException
	 * @throws                       bsh.ParseException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Date verifyinitialHistoryGraph(int start, int end, String elemnt)
			throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {

		String var = null;
		var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println(var);
		JSWaiter.waitJQueryAngular();
		waitForElement(hstryGrph, 10);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth()) / 2 - start, end).click()
				.perform();
		String initialtooltipvalue = driver.findElement(By.cssSelector(elemnt)).getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + initialtooltipvalue);
		String initdate = initialtooltipvalue.substring(0, 10);
		System.out.println(initdate);

		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date startDate = formats.parse(initdate);
		return startDate;
	}

	/**
	 * Get the Latest point and date of the graph
	 * 
	 * @return final/ending History graph value
	 * @throws ParseException
	 * @throws                       bsh.ParseException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Date verifyfinalHistorygraph(int start, int end, String elemnt)
			throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {

		String var = null;
		var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println(var);
		JSWaiter.waitJQueryAngular();
		waitForElement(hstryGrph, 10);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth()) / 2 - start, end).click()
				.perform();
		String finaltooltipvalue = driver.findElement(By.cssSelector(elemnt)).getText();
		;
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + finaltooltipvalue);
		String finaldate = finaltooltipvalue.substring(0, 10);
		System.out.println(finaldate);

		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date endtDate = formats.parse(finaldate);
		return endtDate;
	}

	/**
	 * To get difference between two dates
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getNumberofDays(int start, int end, String elemnt, int start1, int end1) throws Exception {
		Date init = verifyinitialHistoryGraph(start, end, elemnt);
		Date enddate = verifyfinalHistorygraph(start1, end1, elemnt);
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		System.out.println(var);
		String today = ((JavascriptExecutor) driver)
				.executeScript("return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		System.out.println(today);
		Date todaydate = formats.parse(today);
		System.out.println(todaydate);
		String Yesterday = ((JavascriptExecutor) driver).executeScript(
				"return moment().add({days: -1}).format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		Date latest = formats.parse(Yesterday);
		System.out.println(latest);
		Assert.assertEquals(enddate, latest);
		long difference = Math.abs(init.getTime() - enddate.getTime());
		long differenceDates = difference / (24 * 60 * 60 * 1000);
		int diff = (int) (long) differenceDates;
		System.out.println(diff);
		System.out.println(diff);
		return diff;
	}

	/**
	 * To check whether element is clicked
	 * 
	 * @param element
	 * @return
	 */
	public boolean eleClicked(WebElement element) {
		if (element.isDisplayed()) {
			String classes = element.getAttribute("class");
			boolean isDisabled = classes.contains("highcharts-button highcharts-button-pressed");
			System.out.println(isDisabled);
			return !(isDisabled);
		}
		return false;
	}

	/**
	 * Click on highchart zoom functionality
	 * 
	 * @param durationFor
	 * @return
	 * @throws Exception
	 */

	public TPSEE_abstractMethods clickHighchartCriteria(String durationFor) throws Exception {

		durationFor = durationFor.toLowerCase();
		WebElement x = driver.findElement(By.xpath(" //*[name()='rect' and @class='highcharts-background']"));
		scrollByElement(x);
		// scrollByElement(highChartZoom);
		int days;
		if ((!durationFor.equalsIgnoreCase("null"))) {
			switch (durationFor) {
			case "1m":
				try {

					clickelement(highChart_1M);
					if (eleClicked(highChart_1M)) {
						scrollByElement(x);
						days = getNumberofDays();
						if (days >= 28 && days <= 31) {
							assert (true);
							System.out.println("1 Month data is displayed");
						} else {
							System.out.println("Not 1 Month");
						}
					} else {
						System.out.println("Element not clicked");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "3m":
				try {
					clickelement(highChart_3M);
					if (eleClicked(highChart_3M)) {
						scrollByElement(x);
						days = getNumberofDays();
						if (days >= 90 && days <= 92) {
							assert (true);
							System.out.println("3 Month data is displayed");
						} else {
							System.out.println("Not 3 Month");
						}
					} else {
						System.out.println("Element not clicked");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "6m":
				try {
					clickelement(highChart_6M);
					if (eleClicked(highChart_6M)) {
						scrollByElement(x);
						days = getNumberofDays();
						if (days >= 180 && days <= 184) {
							assert (true);
							System.out.println("6 Month data is displayed");
						} else {
							System.out.println("Not 6 Month");
						}
					} else {
						System.out.println("Element not clicked");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "ytd":
				try {
					clickelement(highChart_YTD);
					if (eleClicked(highChart_YTD)) {
						scrollByElement(x);
						days = getNumberofDays();
						System.out.println("Days Abi" + days);
						Date UI_date = getCurrentfromDate();
						System.out.println("UI" + UI_date);
						Date VE_Date = getCurrent();
						System.out.println("VE" + VE_Date);
						Assert.assertEquals(UI_date, VE_Date);
						System.out.println("YearToDate data is displayed");
					} else {
						System.out.println("Element Not clicked");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "1y":
				try {
					clickelement(highChart_1y);
					if (eleClicked(highChart_1y)) {
						scrollByElement(x);
						days = getNumberofDays();
						if (days >= 364 && days <= 366) {
							assert (true);
							System.out.println("1 Year data is displayed");
						} else {
							System.out.println("Not 1 Year");
						}
					} else {
						System.out.println("Element not clicked");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "all":
			default:
				try {
					clickelement(highChart_All);
					if (eleClicked(highChart_All)) {
						scrollByElement(x);
						days = getNumberofDays();
						assert (true);
					} else {
						System.out.println("Element not clicked");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return this;
	}

	/**
	 * To get date of 01/01/current year
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Date getCurrent() throws ParseException {
		/*
		 * String ad1="01/01/"; String year = Integer.toString(Year.now().getValue());
		 * String ad2=ad1.concat(year); System.out.println(ad2);
		 */
		String FirstDayofYear = ((JavascriptExecutor) driver).executeScript(
				"return moment().startOf('year').format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		System.out.println("The first date of year is :" + FirstDayofYear);
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date finalcurrentdate = formats.parse(FirstDayofYear);
		return finalcurrentdate;
	}

	/**
	 * To get difference between two dates
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getNumberofDays() throws Exception {
		int diff = 0;
		Date init = getCurrentfromDate();
		Date enddate = getCurrenttoDate();
		long difference = Math.abs(init.getTime() - enddate.getTime());
		long differenceDates = difference / (24 * 60 * 60 * 1000);
		diff = (int) (long) differenceDates;
		System.out.println(diff);
		return diff;
	}

	/**
	 * To check whether given element is visible
	 * 
	 * @param Title
	 * @return
	 */
	public boolean IsVisible(WebElement Title) {
		boolean Visibiltyofele = false;
		if (Title.isDisplayed()) {
			Visibiltyofele = true;
		} else {
			System.out.println("Element Not Visible");
		}
		return Visibiltyofele;
	}

	/**
	 * Top button fuctionality
	 */
	public void TopButton() {
		try {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-250)");
			waitForElement(Top, 10);
			scrollByElement(Top);
			if (Top.isDisplayed() && Top.isEnabled()) {
				clickelement(Top);
				boolean x = IsVisible(Top);
				System.out.println(x);
				if (x = true) {
					System.out.println("Button Clicked");
				} else {
					System.out.println("Button not clicked");
				}
			} else {
				System.out.println("Title is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To get Number of Locations from Dashboard
	 * 
	 * @return
	 */
	public int getLocations() {
		wait.until(ExpectedConditions.visibilityOf(Locations));
		waitUntilLoad(driver);
		String s = Locations.getText();
		System.out.println("Locations are :" + s);
		int loc = Integer.parseInt(s);
		System.out.println(loc);
		return loc;
	}

	/**
	 * To get Visibility Score from Dashboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public double getVisibilityscore() throws InterruptedException {
		Thread.sleep(5000);
		String sc = Visibilityscore.getText();
		System.out.println(sc);
		String s = sc.replace("%", "");
		s.trim();
		double visibilityscore = Double.parseDouble(s);
		System.out.println(visibilityscore);
		BigDecimal bd = BigDecimal.valueOf(visibilityscore);
		bd = bd.setScale(1, RoundingMode.HALF_UP);
		double finalvisibilityscore = bd.doubleValue();
		return finalvisibilityscore;

	}

	/**
	 * To get Number of Locations of Visibility from Dashboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public int getVisibilityLoc() throws InterruptedException {
		Thread.sleep(5000);
		int visibilityloc = Integer.parseInt(VisibilityLoc.getText());
		System.out.println(visibilityloc);
		return visibilityloc;

	}

	/**
	 * To get Accuracy Score from Dashboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static double getAccuracyscore() throws InterruptedException {
		Thread.sleep(5000);
		String sc = Accuracyscore.getText();
		System.out.println(sc);
		String s = sc.replace("%", "");
		s.trim();
		double accuracyscore = Double.parseDouble(s);
		System.out.println(accuracyscore);
		BigDecimal bd = BigDecimal.valueOf(accuracyscore);
		bd = bd.setScale(1, RoundingMode.HALF_UP);
		double finalaccuracyscore = bd.doubleValue();
		return finalaccuracyscore;
	}

	/**
	 * To get Number of Locations of Accuracy from Dashboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static int getAccuracyLoc() throws InterruptedException {
		Thread.sleep(5000);
		int accuracyloc = Integer.parseInt(AccuracyLoc.getText());
		System.out.println(accuracyloc);
		return accuracyloc;
	}

	/**
	 * To get Content Analysis Score from Dashboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public double getContentscore() throws InterruptedException {
		Thread.sleep(5000);
		String sc = Contentscore.getText();
		System.out.println(sc);
		String s = sc.replace("%", "");
		s.trim();
		double cascore = Double.parseDouble(s);
		System.out.println(cascore);
		BigDecimal bd = BigDecimal.valueOf(cascore);
		bd = bd.setScale(1, RoundingMode.HALF_UP);
		double finalcascore = bd.doubleValue();
		return finalcascore;
	}

	/**
	 * To get Number of Locations of Content Analysis
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public int getContentLoc() throws InterruptedException {
		Thread.sleep(5000);
		int contentloc = Integer.parseInt(ContentLoc.getText());
		System.out.println(contentloc);
		return contentloc;
	}

	/**
	 * To get Google Ranking Score from Dashboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static double getGRScore() throws InterruptedException {
		Thread.sleep(5000);
		double GRscore = Double.parseDouble(GRScore.getText());
		System.out.println(GRscore);
		BigDecimal bd = BigDecimal.valueOf(GRscore);
		bd = bd.setScale(1, RoundingMode.HALF_UP);
		double finalGRscore = bd.doubleValue();
		return finalGRscore;
	}

	/**
	 * To get Number of locations of Google ranking from Dashboard
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static int getGRLoc() throws InterruptedException {
		Thread.sleep(5000);
		int GRloc = Integer.parseInt(GRLoc.getText());
		System.out.println(GRloc);
		return GRloc;
	}

	/**
	 * Overview and Dashboard Comparision
	 * 
	 * @param layout
	 * @param loc
	 * @return
	 */
	public int overviewLocation(WebElement layout, WebElement loc) {
		waitForElement(layout, 10);
		scrollByElement(layout);
		waitForElement(loc, 5);
		scrollByElement(loc);
		int location = Integer.parseInt(loc.getText());
		return location;
	}

	public int numofloc(WebElement locations) {
		waitForElement(locations, 10);
		scrollByElement(locations);
		String s = locations.getText();
		String x = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
		int location = Integer.parseInt(x);
		return location;
	}

	/**
	 * To get overview score from the reports (VA Reports)
	 * 
	 * @param layout
	 * @param score
	 * @return
	 */
	public double overviewscore(WebElement layout, WebElement score) {
		waitForElement(layout, 10);
		scrollByElement(layout);
		waitForElement(score, 5);
		scrollByElement(score);
		String sc = score.getText();
		String s = sc.replace("%", "");
		double scores = Double.parseDouble(s);
		
		  BigDecimal bd = BigDecimal.valueOf(scores); bd = bd.setScale(1,
		  RoundingMode.HALF_UP); 
		  double finaloverviewscore = bd.doubleValue();
		 
		return finaloverviewscore;

	}

	/**
	 * To get overall score for Content Analysis Report
	 * 
	 * @param score
	 * @return
	 */
	public double overviewcascore(WebElement score) {
		waitForElement(score, 10);
		scrollByElement(score);
		String sc = score.getAttribute("data-percent");
		String s = sc.replace("%", "");
		double scores = Double.parseDouble(s);
		/*
		 * BigDecimal bd = BigDecimal.valueOf(scores); bd = bd.setScale(1,
		 * RoundingMode.HALF_UP); double finaloverviewcascore = bd.doubleValue();
		 */
		return scores;
	}

	/**
	 * To get current selected from date from UI Calendar
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Date getCurrentfromDate() throws ParseException {
		String currentfromDate = fromDate.getText();
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println(var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date finalcurrentdate = formats.parse(currentfromDate);
		return finalcurrentdate;
	}

	/**
	 * To get current selected to date from UI Calendar
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Date getCurrenttoDate() throws ParseException {
		String currenttoDate = toDate.getText();
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println(var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date finaltodate = formats.parse(currenttoDate);
		return finaltodate;
	}

	/**
	 * To pass date to the UI Calendar
	 * 
	 * @param calenderField
	 * @param day_d
	 * @param month_MMM
	 * @param year_YYYY
	 */
	private void selectCalender_Date(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		// scrollByElement(hstryGrph);
		driver.findElement(By.xpath(calenderField)).click();
		int diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
		if (diff != 0) {
			while (diff < 0) {
				clickelement(prevMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
			while (diff > 0) {
				clickelement(nextMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
		}
		if (diff == 0) {
			if (!(month_MMM.equals(currentMonth_DatePicker.getText()))) {
				int actualMonthCode = monthCode(currentMonth_DatePicker.getText());
				int expMonthCode = monthCode(month_MMM);
				int diffMonth = expMonthCode - actualMonthCode;
				while (diffMonth < 0) {
					clickelement(prevMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
				while (diffMonth > 0) {
					clickelement(nextMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
			}
		}
		action.moveToElement(
				driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar']//td/a[text()=" + day_d + "]")))
				.click().build().perform();
	}

	/**
	 * To get month code
	 * 
	 * @param month_MMM
	 * @return
	 */
	private int monthCode(String month_MMM) {

		int month = 0;
		Date date;
		try {
			date = new SimpleDateFormat("MMM").parse(month_MMM);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			month = cal.get(Calendar.MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return month;
	}

	/**
	 * To set from date in a calendar
	 * 
	 * @param calenderField
	 * @param day_d
	 * @param month_MMM
	 * @param year_YYYY
	 */
	public void selectCalender_FromDate(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		if (day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0) {
			selectCalender_Date(calenderField, day_d, month_MMM, year_YYYY);
		}

	}

	/**
	 * To set to date in calendar
	 * 
	 * @param calenderField
	 * @param day_d
	 * @param month_MMM
	 * @param year_YYYY
	 */
	public void selectCalender_ToDate(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		if (day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0) {
			selectCalender_Date(calenderField, day_d, month_MMM, year_YYYY);
		}

	}

	/**
	 * Method returns list of found vendors
	 * 
	 * @return
	 */
	public ArrayList<String> verifyfoundSitevendors() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<String> foundVendors = new ArrayList<String>();
		List<WebElement> vendorrow = driver.findElements(By.xpath("(//*[@id='barContainer']//div[@class='row'])"));
		vendorrow.size();
		for (int i = 1; i <= vendorrow.size(); i++) {
			WebElement foundlisting = driver
					.findElement(By.xpath("(*//div[@class='col-lg-1 bar-chart-column found-number'])[" + i + "]"));
			String numoflisting = foundlisting.getText();
			WebElement vendorname = driver
					.findElement(By.xpath("(//*[@class='col-lg-2 bar-chart-column'])[" + i + "]"));
			if (!numoflisting.equals("0")) {
				scrollByElement(vendorname);
				String vendor = vendorname.getText();
				System.out.println(vendor);
				foundVendors.add(vendor);
				System.out.println(foundVendors);
			} else {
				System.out.println("No found Listing");
			}
		}
		return foundVendors;
	}

	/**
	 * To delete the file from download folder
	 */
	public void deletefile() {
		File path = new File("./downloads");
		File[] files = path.listFiles();
		for (File file : files) {
			System.out.println("Deleted filename :" + file.getName());
			file.delete();
		}
	}

	/**
	 * to verify title and title text
	 * 
	 * @param Tit
	 * @param titText
	 */
	public void VerifyTitleText(String Tit, String titText) {

		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		Assert.assertEquals(Tit, Title);
		Assert.assertEquals(titText, TitleText);
	}

	/**
	 * to export csv/xlsx
	 * 
	 * @param ExportBtn
	 * @param ExportType
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void Download(WebElement ExportBtn, WebElement ExportType)
			throws FileNotFoundException, InterruptedException, IOException {
		try {
			exportVATable(ExportBtn, ExportType);
			Robot robot = new Robot();
			robot.setAutoDelay(2000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_S);
			if ("firefox".equalsIgnoreCase(CurrentState.getBrowser()))
				robot.keyPress(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify results per page
	 * 
	 * @param soft
	 * @param entry
	 * @param results
	 * @throws InterruptedException
	 */
	public void ResultsperPage(SoftAssert soft, WebElement entry, WebElement results) throws InterruptedException {
		if (driver.findElement(By.className("dataTables_info")).isDisplayed()) {
			int totalentries = NumOfentries(entry);
			System.out.println("The total number of entries are :" + totalentries);
			int entryperPage;
			if (totalentries >= 10) {
				scrollByElement(entry);
				select = new Select(results);
				select.selectByVisibleText("10");
				JSWaiter.waitJQueryAngular();
				entryperPage = NumOfentriesinPage(entry);
				System.out.println("The entries per page is :" + entryperPage);
				soft.assertEquals(10, entryperPage);
			} else {
				System.out.println("No enough data to perform");
			}
			if (totalentries >= 25) {
				scrollByElement(entry);
				select = new Select(results);
				select.selectByVisibleText("25");
				JSWaiter.waitJQueryAngular();
				entryperPage = NumOfentriesinPage(entry);
				System.out.println("The entries per page is :" + entryperPage);
				soft.assertEquals(25, entryperPage);
			} else {
				System.out.println("No enough data to perform");
			}
			if (totalentries >= 50) {
				scrollByElement(entry);
				select = new Select(results);
				select.selectByVisibleText("50");
				JSWaiter.waitJQueryAngular();
				entryperPage = NumOfentriesinPage(entry);
				System.out.println("The entries per page is :" + entryperPage);
				soft.assertEquals(50, entryperPage);
			} else {
				System.out.println("No enough data to perform");
			}
			if (totalentries >= 100) {
				scrollByElement(entry);
				select = new Select(results);
				select.selectByVisibleText("100");
				JSWaiter.waitJQueryAngular();
				entryperPage = NumOfentriesinPage(entry);
				System.out.println("The entries per page is :" + entryperPage);
				soft.assertEquals(100, entryperPage);
			} else {
				System.out.println("No enough data to perform");
			}
			select = new Select(results);
			select.selectByVisibleText("10");
			JSWaiter.waitJQueryAngular();
		} else {
			System.out.println("No data");
		}
	}

	/**
	 * to verify goto page
	 * 
	 * @param GoTo
	 * @throws InterruptedException
	 */
	public void GoTopage(WebElement GoTo) throws InterruptedException {
		scrollByElement(GoTo);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int totalpage = Integer.parseInt(n);
		clickelement(GoTo);
		GoTo.clear();
		if (totalpage > 1) {
			clickelement(GoTo);
			GoTo.sendKeys("2");
			GoTo.sendKeys(Keys.ENTER);
			JSWaiter.waitJQueryAngular();
			String classname = driver.findElement(By.xpath("(//*[@class='pagination'])//li[3]")).getAttribute("class");
			System.out.println("The class name is :" + classname);
			Assert.assertEquals(classname, "paginate_button active");
		} else {
			System.out.println("No more pages found");
		}
	}

	/**
	 * to verify title and title text
	 * 
	 * @param Tit
	 * @param titText
	 */
	public void VerifyTitleText1(String Tit, String titText) {

		waitForElement(PageTitle1, 10);
		String Title = PageTitle1.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		Assert.assertEquals(Tit, Title);
		Assert.assertEquals(titText, TitleText);
	}

	/**
	 * navigation from KPI
	 * 
	 * @param ele
	 * @param ele1
	 * @param title
	 * @throws InterruptedException
	 */
	public void navigateKPI(WebElement ele, WebElement ele1, String title) throws InterruptedException {
		waitForElement(ele, 10);
		scrollByElement(ele);
		action.moveToElement(ele).click().build().perform();
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No walkme displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No walkme displayed");
		}
		String Title = ele1.getText();
		System.out.println("The page title is :" + Title);
		Assert.assertEquals(Title, title);
	}

	/**
	 * to click on walkme cancel
	 */
	public void clickwalkme() {
		JSWaiter.waitJQueryAngular();
		if (WalkMeCancel.isDisplayed()) {
			clickelement(WalkMeCancel);
		} else {
			System.out.println("No Walkme Displayed");
		}
	}

	/**
	 * to click on ok notification
	 */
	public void clickNotificationPopUp() {
		JSWaiter.waitJQueryAngular();
		if (NotificationPopUp.isDisplayed()) {
			clickelement(NotificationPopUp);
		} else {
			System.out.println("No Notification Displayed");
		}
	}

	/**
	 * to verify mousehover text KPI
	 * 
	 * @param ele
	 * @param txt
	 */
	public void KPIMouseHover(WebElement ele, String txt) {
		action.moveToElement(ele);
		String text = ele.getAttribute("data-original-title");
		System.out.println("The mouse hover text is :" + text);
		Assert.assertEquals(text, txt);
	}

	/**
	 * to verify table sorting
	 * 
	 * @param ele
	 * @param ele1
	 * @param entry
	 * @param tablerow
	 * @param table
	 * @throws InterruptedException
	 */
	public void TableSorting(WebElement ele, String ele1, WebElement entry, List<WebElement> tablerow, WebElement table)
			throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(table, 10);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		int totalentries = NumOfentriesinPage(entry);
		System.out.println("The total entries are :" + totalentries);
		if (paginationNext.isDisplayed()) {
			int count = 0;
			if (ele.isDisplayed()) {
				scrollByElement(ele);
				ele.click();
				for (int i = 1; i <= page; i++) {
					JSWaiter.waitJQueryAngular();
					List<WebElement> rows_table = tablerow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (sortrow = 1; sortrow <= rows_count; sortrow++) {
						String celText = driver.findElement(By.xpath(ele1 + "[" + sortrow + "]")).getText();
						System.out.println("The value is :" + celText);
						List1.add(celText);
					}
					if (paginationNext.isEnabled()) {
						JSWaiter.waitJQueryAngular();
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			} else {
				System.out.println("No column displayed");
			}
			System.out.println("The Final List is :" + List1);
		}
		List<String> temp = new ArrayList<String>();
		temp.addAll(List1);
		System.out.println("The temporary list to compare data is :" + temp);
		Collections.sort(temp);
		System.out.println("The sorted temporary List is :" + temp);
		if (List1.size() == temp.size()) {
			for (int i = 0; i <= temp.size() - 1; i++) {
				soft.assertTrue(List1.get(i).equalsIgnoreCase(temp.get(i)),
						"The value from UI is : " + List1.get(i) + " and value after sorting is : " + temp.get(i));
			}
			soft.assertAll();
		}
	}

	/**
	 * to verify history graph
	 * 
	 * @throws ParseException
	 */
	public void verifyHistoryGraph1() throws ParseException {
		String var = null;
		var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println(var);
		JSWaiter.waitJQueryAngular();
		waitForElement(hstryGrph, 10);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 1, 0).click().perform();
		tooltipvalue = grphtooltipDate.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + tooltipvalue);
		String Yesterday = ((JavascriptExecutor) driver).executeScript(
				"return moment().add({days: -1}).format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date latest = formats.parse(Yesterday);
		System.out.println("The latest date is : " + latest);
		Date UIlatest = formats.parse(tooltipvalue);
		System.out.println("The UI latest date is : " + UIlatest);
		Assert.assertEquals(UIlatest, latest);
	}

	/**
	 * to verify graph score
	 * 
	 * @return
	 */
	public double verifygrphscore() {
		String var = null;
		var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println(var);
		JSWaiter.waitJQueryAngular();
		waitForElement(hstryGrph, 10);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 1, 0).click().perform();
		tooltipvalue = grphtooltipScore.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + tooltipvalue);
		tooltipvalue = tooltipvalue.substring(tooltipvalue.indexOf("V") + 6);
		double scorevalue;
		if (tooltipvalue.contains(">")) {
			tooltipvalue = tooltipvalue.replace(">", "");
			tooltipvalue = tooltipvalue.trim();
			scorevalue = Double.parseDouble(tooltipvalue);
		} else {
			scorevalue = Double.parseDouble(tooltipvalue);
		}
		System.out.println("The graphscorevalue is : " + scorevalue);
		 BigDecimal bd = BigDecimal.valueOf(scorevalue); bd = bd.setScale(1,
				  RoundingMode.HALF_UP); 
				  double finaloverviewscore = bd.doubleValue();
		return finaloverviewscore;
	}

	/**
	 * to verify location filter address
	 * 
	 * @param text
	 * @throws Exception
	 */
	public void verifyLocationFilterAddress(String text) throws Exception {
		waitForElement(Location_Filter_Address, 5);
		scrollByElement(Location_Filter_Address);
		String LocationDetails = Location_Filter_Address.getText();
		System.out.println("The Location Panel Address is : " + LocationDetails);
		BaseClass.addEvidence(driver, "Test to verify location details from location panel", "yes");
		Assert.assertTrue(LocationDetails.contains(text));
	}

	/**
	 * to verify location address with site location
	 * 
	 * @param text
	 */
	public void verifyLocationFilterSiteAddress(String text) {
		waitForElement(Site_Location_Address, 5);
		scrollByElement(Site_Location_Address);
		String Loc_Details = driver
					.findElement(By.xpath("//div[@class='location-address']"))
					.getText().trim();
		System.out.println("The Location details : " + Loc_Details);
		soft.assertTrue(Loc_Details.equals(text), "Location Details is "
					+ Loc_Details + "The Location details from the location filter is : " + text);
		soft.assertAll();
	}

	/**
	 * to verify vendors
	 */
	public void verifyVendorSites() {
		List<String> Vendors = verifyfoundSitevendors();
		System.out.println("The List of vendors is : " + Vendors);
		List<WebElement> Site_Details = driver
				.findElements(By.xpath("(//div[@id='singleLocationReport']//span[@class='item-name'])"));
		int size = Site_Details.size();
		List<String> SiteDetails = new ArrayList<String>();
		System.out.println("The size of vendors list : " + size);
		for (int i = 1; i <= size; i++) {
			String VName = driver
					.findElement(By.xpath("(//div[@id='singleLocationReport']//span[@class='item-name'])[" + i + "]"))
					.getText();
			System.out.println("The vendor name is : " + VName);
			SiteDetails.add(VName);
		}
		System.out.println("The final list of location vendors : " + SiteDetails);
		int Vsize = SiteDetails.size();
		System.out.println("The vendor size is : " + Vsize);
		if (size == Vsize) {
			for (int i = 0; i <= Vsize - 1; i++) {
				soft.assertTrue(Vendors.contains(SiteDetails.get(i)),
						"The Vendor Site is : " + SiteDetails.get(i) + "from location details");
			}
		} else {
			System.out.println("List are not equal");
		}
	}

	/**
	 * to get current time and date
	 * 
	 * @return
	 */
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date).replace("/", "").replace(":", "").trim();
		System.out.println("Timestamp is :" + formattedDate);
		return formattedDate;
	}

	/**
	 * to verify status code of the url
	 * 
	 * @param Url
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void VerifyStatusCode(String Url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(Url); // http get request
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->" + statusCode);
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
	}
	
	
	public void clickReadManual() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(), 'Read Manual')]"));
		ele.click();
		Thread.sleep(3000);
	}
	
	public void verifyContentInPDf(String text, String text1, String imgtext, String ReportName) throws Exception {
		
		String winHandleBefore = driver.getWindowHandle();
		clickReadManual();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		//specify the url of the pdf file
		String url =driver.getCurrentUrl();
		soft.assertTrue(url.contains(ReportName), "Url doesn't contain");
		Thread.sleep(30000);
		try {
			String pdfContent = readPdfContent(url);
			System.out.println("Thee pdf content is : " +pdfContent);
			soft.assertTrue(pdfContent.contains(text), "Doesn't contain report name");
			soft.assertTrue(pdfContent.contains(text1), "doesn't contain report description");
			soft.assertTrue(pdfContent.contains(imgtext), "doesn't contain image text");
			BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify pdf", "yes");
			driver.close();
			driver.switchTo().window(winHandleBefore);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		soft.assertAll();
	}
	
	
	
	
	public String readPdfContent(String url) throws IOException {
		
		URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		int numberOfPages = getPageCount(doc);
		System.out.println("The total number of pages "+numberOfPages);
		String content = new PDFTextStripper().getText(doc);
		doc.close();
		//soft.assertEquals(numberOfPages, 6);
		return content;
}
	
	public static int getPageCount(PDDocument doc) {
		//get the total number of pages in the pdf document
		int pageCount = doc.getNumberOfPages();
		return pageCount;
		
	}
	
	/*public void autoSizeColumns(String PathofXL , String export) throws IOException { 
		FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
		Workbook wb = new XSSFWorkbook(excelFilePath);
        int numberOfSheets = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheetAt(0);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                Row row = sheet.getRow(sheet.getFirstRowNum());
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    sheet.autoSizeColumn(columnIndex);
                    int currentColumnWidth = sheet.getColumnWidth(columnIndex);
                    sheet.setColumnWidth(columnIndex, (currentColumnWidth + 2500));
                }
            }
        
    }*/

}