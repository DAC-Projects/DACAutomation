package com.dac.main.POM_TPSEE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import resources.BaseClass;
import resources.CurrentState;
import resources.JSWaiter;

public class TPSEE_GMB extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	String custtooltipvalue;
	String WhereListingtooltipvalue;
	String PhViewtooltipvalue;
	String PhQtytooltipvalue;
	String HowListingtooltipvalue;
	String Value;
	String Value1;
	String Value2;
	// int sum ;

	// Navigating to GMB Page
	public TPSEE_GMB(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	/*-------------------------------Locators-------------------------------------------------*/

	@FindBy(xpath = "//li[@data-varible='customerWebsite']//*[@class='tooltip-info']")
	private WebElement customerWebsite_MousehoverText;

	@FindBy(xpath = "//li[@data-varible='customerDirections']//*[@class='tooltip-info']")	
	private WebElement customerDirections_MousehoverText;

	@FindBy(xpath = "//li[@data-varible='customerPhonecalls']//*[@class='tooltip-info']")
	private WebElement customerPhonecalls_MousehoverText;

	@FindBy(xpath = "(//*[@class='sr-only tooltip'])[1]") 
	private WebElement TotalActionsMousehoverText;

	// where listing found

	@FindBy(xpath = "//li[@data-varible='whereSearch']//*[@class='tooltip-info']")
	private WebElement whereSearch_MousehoverText;

	@FindBy(xpath = "//li[@data-varible='whereMaps']//*[@class='tooltip-info']")
	private WebElement whereMaps_MousehoverText;

	@FindBy(xpath = "(//*[@class='sr-only tooltip'])[2]") 
	private WebElement Totalviews_MousehoverText;
	// how listing found

	@FindBy(xpath = "//li[@data-varible='howDiscovery']//*[@class='tooltip-info']")
	private WebElement howDiscoveryMousehoverText;

	@FindBy(xpath = "//li[@data-varible='howDirect']//*[@class='tooltip-info']")
	private WebElement howDirect_MousehoverText;

	@FindBy(xpath = "//li[@data-varible='howBranded']//*[@class='tooltip-info']")
	private WebElement howBranded_MousehoverText;

	@FindBy(xpath = "(//*[@class='sr-only tooltip'])[3]") 
	private WebElement TotalSearches_MousehoverText;
	// photoview

	@FindBy(xpath = "//li[@data-varible='photoViewsOwner']//*[@class='tooltip-info']")
	private WebElement photoViewsOwner_MousehoverText;

	@FindBy(xpath = "//li[@data-varible='photoViewsCustomer']//*[@class='tooltip-info']")
	private WebElement photoViewsCustomer_MousehoverText;

	@FindBy(xpath = "(//*[@class='sr-only tooltip'])[4]")
	private WebElement photoviewtotalcount_MousehoverText;

	// photo quantity

	@FindBy(xpath = "//li[@data-varible='photoCountOwner']//*[@class='tooltip-info']")
	private WebElement photoCountOwner_MousehoverText;

	@FindBy(xpath = "//li[@data-varible='photoCountCustomer']//*[@class='tooltip-info']")
	private WebElement photoCountCustomer_MousehoverText;

	@FindBy(xpath = "//*[@id='page-content']//h2")
	private WebElement PageTitle;

	@FindBy(xpath = "//p[@class='lead']")
	private WebElement PageTitletext;

	@FindBy(xpath = "//div[@id='customerIndex']")
	private WebElement CustomerActions;

	@FindBy(xpath = "//span[@id='customerTotal']")
	private WebElement TotalActions;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-width-md btn-icon dropdown-toggle pull-right']")
	private WebElement export;

	@FindBy(xpath = "//a[contains(text(), 'Export as CSV')]")
	private WebElement CSVExport;

	@FindBy(xpath = "//a[contains(text(), 'Export as XLSX')]")
	private WebElement XLSXExport;

	@FindBy(xpath = "(//*[contains(@class, 'highcharts-plot-background')])[1]")
	private WebElement Custgraph;

	@FindBy(xpath = "(//*[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[1]")
	private WebElement Custgrphtooltip;

	@FindBy(xpath = "(//*[contains(@class, 'highcharts-plot-background')])[2]")
	private WebElement WhereListinggraph;

	@FindBy(xpath = "(//*[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[2]")
	private WebElement WhereListingrphtooltip;

	@FindBy(xpath = "(//*[contains(@class, 'highcharts-plot-background')])[3]")
	private WebElement HowlistingFoundgraph;

	@FindBy(xpath = "(//*[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[3]")
	private WebElement HowListingrphtooltip;

	@FindBy(xpath = "(//*[contains(@class, 'highcharts-plot-background')])[4]")
	private WebElement PhViewsgraph;

	@FindBy(xpath = "(//*[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[4]")
	private WebElement PhViewsgrphtooltip;

	@FindBy(xpath = "(//*[contains(@class, 'highcharts-plot-background')])[5]")
	private WebElement PhQtygraph;

	@FindBy(xpath = "(//*[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[5]")
	private WebElement PhQtygrphtooltip;

	@FindBy(xpath = "//div[@id='whereIndex']")
	private WebElement WhereListing;

	@FindBy(xpath = "//span[@id='whereTotal']")
	private WebElement TotalViews;

	@FindBy(xpath = "//div[@id='howIndex']")
	private WebElement HowListing;

	@FindBy(xpath = "//span[@id='howTotal']")
	private WebElement TotalSearch;

	@FindBy(xpath = "//div[@id='photoViewsIndex']")
	private WebElement PhotoViews;

	@FindBy(xpath = "//span[@id='photoViewsOwner']")
	private WebElement Owner;

	@FindBy(xpath = "//span[@id='photoViewsCustomer']")
	private WebElement Customer;

	@FindBy(xpath = "(//span[@id='photoViewsTotal'])[1]")
	private WebElement TotalPhViews;

	@FindBy(xpath = "//div[@id='photoCountIndex']")
	private WebElement PhotoQty;

	@FindBy(xpath = "//span[contains (text(),'Website Visits')]")
	private WebElement WebsiteVisits;

	@FindBy(xpath = "//span[contains (text(),'Request Directions')]")
	private WebElement RequestDir;

	@FindBy(xpath = "//span[contains (text(),'Phone Calls')]")
	private WebElement PhoneCalls;

	@FindBy(xpath = "(//span[contains(text(),'Search')])[2]")
	private WebElement SearchFound;

	@FindBy(xpath = "(//span[contains(text(),'Map')])[2]")
	private WebElement MapFound;

	@FindBy(xpath = "(//span[contains(text(),'Discovery')])[2]")
	private WebElement Dis;

	@FindBy(xpath = "(//span[contains(text(),'Direct')])[3]")
	private WebElement Dir;

	@FindBy(xpath = "//span[contains(text(),'Branded')]")
	private WebElement Brand;

	@FindBy(xpath = "//div[@id='photoViewsIndex']//span[@class='infobox-content'][contains(text(),'Owner')]")
	private WebElement Phowner;

	@FindBy(xpath = "//div[@id='photoViewsIndex']//span[@class='infobox-content'][contains(text(),'Customer')]")
	private WebElement PhCust;

	@FindBy(xpath = "//li[@id='google_places_report']")
	private WebElement GMBPage;

	@FindBy(xpath = "//li[@id='local_analytics']")
	private WebElement GMBSec;

	/*-------------------------------Locators-------------------------------------------------*/

	@Override
	public List<Map<String, String>> getOverviewReport() {

		return null;
	}

	/**
	 * to get action graph data
	 */
	public void verifyCustomerActionsGraph() {

		// display tool tip
		JSWaiter.waitJQueryAngular();
		waitForElement(Custgraph, 5);
		scrollByElement(Custgraph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(Custgraph).moveByOffset((Custgraph.getSize().getWidth() / 2) - 2, 0).click().perform();

		// read the tooltip variables
		custtooltipvalue = Custgrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + custtooltipvalue);
	}

	/**
	 * to get listing graph data
	 */
	public void verifyWhereListingGraph() {

		// display tool tip
		JSWaiter.waitJQueryAngular();
		waitForElement(WhereListinggraph, 5);
		scrollByElement(WhereListinggraph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(WhereListinggraph).moveByOffset((WhereListinggraph.getSize().getWidth() / 2) - 2, 0)
				.click().perform();

		// read the tooltip variables
		WhereListingtooltipvalue = WhereListingrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + WhereListingtooltipvalue);
	}

	/**
	 * to get photo view graph data
	 */
	public void verifyPhotoViewGraph() {

		// display tool tip
		JSWaiter.waitJQueryAngular();
		waitForElement(PhViewsgraph, 5);
		scrollByElement(PhViewsgraph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(PhViewsgraph).moveByOffset((PhViewsgraph.getSize().getWidth() / 2) - 2, 0).click()
				.perform();

		// read the tooltip variables
		PhViewtooltipvalue = PhViewsgrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + PhViewtooltipvalue);
	}

	/**
	 * to get photo qty graph data
	 */
	public void verifyPhotoQtyGraph() {

		// display tool tip
		JSWaiter.waitJQueryAngular();
		waitForElement(PhQtygraph, 5);
		scrollByElement(PhQtygraph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(PhQtygraph).moveByOffset((PhQtygraph.getSize().getWidth() / 2) - 2, 0).click().perform();

		// read the tooltip variables
		PhQtytooltipvalue = PhQtygrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + PhQtytooltipvalue);
	}

	/**
	 * to get HOw listing graph data
	 */
	public void verifyHowListingGraph() {

		// display tool tip
		JSWaiter.waitJQueryAngular();
		waitForElement(HowlistingFoundgraph, 5);
		scrollByElement(HowlistingFoundgraph);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		action.moveToElement(HowlistingFoundgraph).moveByOffset((HowlistingFoundgraph.getSize().getWidth() / 2) - 2, 0)
				.click().perform();

		// read the tooltip variables
		PhViewtooltipvalue = HowListingrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + PhViewtooltipvalue);
	}

	/**
	 * Export as CSV
	 */
	public void exportcsvGMB() throws InterruptedException, FileNotFoundException, IOException {

		JSWaiter.waitJQueryAngular();
		if (export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
		}
		if (CSVExport.isDisplayed() && CSVExport.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(CSVExport));
			CSVExport.click();
			System.out.println("" + getLastModifiedFile(Exportpath));
		} else {
			System.out.println("No Data Available in GMB");
		}
	}

	/**
	 * Export as XLSX
	 */
	public void exportXLSXGMB(String file) throws InterruptedException, FileNotFoundException, IOException {

		JSWaiter.waitJQueryAngular();
		if (export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
		}
		if (XLSXExport.isDisplayed() && XLSXExport.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(XLSXExport));
			XLSXExport.click();
			Thread.sleep(10000);
			renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + file));
			Thread.sleep(10000);
			CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile(Exportpath));
		} else {
			System.out.println("No Data Available in GMB");
		}
	}

	/**
	 * to get UI text
	 * 
	 * @param e
	 * @param f
	 * @param g
	 * @return
	 */
	public int GetUIText(WebElement e, WebElement f, WebElement g) {
		Value = e.getText();
		Value1 = f.getText();
		Value2 = g.getText();
		int sum = convertint(Value) + convertint(Value1) + convertint(Value2);
		System.out.println(":" + sum);
		return sum;
	}

	/**
	 * to get UI text
	 * 
	 * @param e
	 * @param f
	 * @return
	 */
	public int GetUIText2(WebElement e, WebElement f) {
		Value = e.getText();
		Value1 = f.getText();
		int sum = convertint(Value) + convertint(Value1);
		System.out.println(":" + sum);
		return sum;
	}

	/**
	 * to get UI text
	 * 
	 * @param e
	 * @return
	 */
	public int GetUIText3(WebElement e) {
		Value = e.getText();
		int sum = convertint(Value);
		System.out.println(":" + sum);
		return sum;
	}

	/**
	 * to convert string to int
	 * 
	 * @param s
	 * @return
	 */
	public int convertint(String s) {
		int Tot = 0;
		if (s.contains(",")) {
			String value = s.replaceAll(",", "");
			value.trim();
			Tot = Integer.parseInt(value);
		} else {
			Tot = Integer.parseInt(s);
		}
		System.out.println("Total :" + Tot);
		return Tot;
	}

	/**
	 * to verify customer action data
	 */
	public void CustomerAction() {
		try {
			JSWaiter.waitJQueryAngular();
			waitForElement(CustomerActions, 5);
			scrollByElement(CustomerActions);
			WebElement WebVisits = driver.findElement(By.xpath("//span[@id='customerWebsite']"));
			WebElement ReqDirectory = driver.findElement(By.xpath("//span[@id='customerDirections']"));
			WebElement PhCalls = driver.findElement(By.xpath("//span[@id='customerPhonecalls']"));
			int actual = GetUIText(WebVisits, ReqDirectory, PhCalls);
			WebElement TotalActions = driver.findElement(By.xpath("//span[@id='customerTotal']"));
			int total = GetUIText3(TotalActions);
			Assert.assertEquals(actual, total, "Count is equal");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify where listing data
	 */
	public void WhereListingFound() {

		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(WhereListing, 5);
			scrollByElement(WhereListing);
			WebElement Search = driver.findElement(By.xpath("//span[@id='whereSearch']"));
			WebElement Maps = driver.findElement(By.xpath("//span[@id='whereMaps']"));
			int Total = GetUIText2(Search, Maps);
			waitForElement(TotalViews, 5);
			scrollByElement(TotalViews);
			String NumofViews = TotalViews.getText();
			int i = convertint(NumofViews);
			System.out.println("Total Number of views :" + i);
			Assert.assertEquals(Total, i);
			System.out.println("Count is equal \n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify how listing data
	 */
	public void HowListingFound() {

		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(HowListing, 5);
			scrollByElement(HowListing);
			WebElement Discovery = driver.findElement(By.xpath("//span[@id='howDiscovery']"));
			WebElement Direct = driver.findElement(By.xpath("//span[@id='howDirect']"));
			WebElement Branded = driver.findElement(By.xpath("//span[@id='howBranded']"));
			int actual = GetUIText(Discovery, Direct, Branded);
			waitForElement(TotalSearch, 5);
			scrollByElement(TotalSearch);
			String NumofSearch = TotalSearch.getText();
			int i = convertint(NumofSearch);
			Assert.assertEquals(actual, i);
			System.out.println("count is equal \n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify photo view data
	 */
	public void PhotoViews() {

		JSWaiter.waitJQueryAngular();
		waitForElement(PhotoViews, 5);
		scrollByElement(PhotoViews);
		waitForElement(Owner, 5);
		scrollByElement(Owner);
		WebElement OwnerView = driver.findElement(By.xpath("//span[@id='photoViewsOwner']"));
		WebElement CustView = driver.findElement(By.xpath("//span[@id='photoViewsCustomer']"));
		int actual = GetUIText2(OwnerView, CustView);
		waitForElement(TotalPhViews, 5);
		scrollByElement(TotalPhViews);
		String NumofPhViews = TotalPhViews.getText();
		int i = convertint(NumofPhViews);
		Assert.assertEquals(actual, i);
		System.out.println("Count is equal \n");
	}

	/**
	 * to verify customer action web visits data
	 */
	public void VerifyCustomerActiononWebVisits() {

		try {
			JSWaiter.waitJQueryAngular();
			waitForElement(CustomerActions, 5);
			scrollByElement(CustomerActions);
			WebElement ReqDirectory = driver.findElement(By.xpath("//span[@id='customerDirections']"));
			WebElement PhCalls = driver.findElement(By.xpath("//span[@id='customerPhonecalls']"));
			waitForElement(WebsiteVisits, 5);
			scrollByElement(WebsiteVisits);
			if (WebsiteVisits.isDisplayed() && WebsiteVisits.isEnabled()) {
				clickelement(WebsiteVisits);
				int actual = GetUIText2(ReqDirectory, PhCalls);
				waitForElement(TotalActions, 5);
				scrollByElement(TotalActions);
				String TotalAction = TotalActions.getText();
				int i = convertint(TotalAction);
				System.out.println("Total Webvisits is :" + i);
				Assert.assertEquals(actual, i);
				System.out.println("Count is equal \n");
				clickelement(WebsiteVisits);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify customer action directory data
	 */
	public void VerifyCustomerActiononWebReqDirectory() {

		try {
			JSWaiter.waitJQueryAngular();
			waitForElement(CustomerActions, 5);
			scrollByElement(CustomerActions);
			WebElement WebVisits = driver.findElement(By.xpath("//span[@id='customerWebsite']"));
			WebElement PhCalls = driver.findElement(By.xpath("//span[@id='customerPhonecalls']"));
			waitForElement(RequestDir, 5);
			scrollByElement(RequestDir);
			if (RequestDir.isDisplayed() && RequestDir.isEnabled()) {
				clickelement(RequestDir);
				int actual = GetUIText2(WebVisits, PhCalls);
				waitForElement(TotalActions, 5);
				scrollByElement(TotalActions);
				String TotalAction = TotalActions.getText();
				int i = convertint(TotalAction);
				System.out.println("Total Request is :" + i);
				Assert.assertEquals(actual, i);
				System.out.println("Count is equal \n");
				clickelement(RequestDir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify customer action phone call data
	 */
	public void VerifyCustomerActiononPhCalls() {

		try {
			JSWaiter.waitJQueryAngular();
			waitForElement(CustomerActions, 5);
			scrollByElement(CustomerActions);
			WebElement WebVisits = driver.findElement(By.xpath("//span[@id='customerWebsite']"));
			WebElement ReqDirectory = driver.findElement(By.xpath("//span[@id='customerDirections']"));
			waitForElement(PhoneCalls, 5);
			scrollByElement(PhoneCalls);
			if (PhoneCalls.isDisplayed() && PhoneCalls.isEnabled()) {
				clickelement(PhoneCalls);
				int actual = GetUIText2(WebVisits, ReqDirectory);
				waitForElement(TotalActions, 5);
				scrollByElement(TotalActions);
				String TotalAction = TotalActions.getText();
				int i = convertint(TotalAction);
				System.out.println("Total Phone is :" + i);
				Assert.assertEquals(actual, i);
				System.out.println("Count is equal \n");
				clickelement(PhoneCalls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify where listing search data
	 */
	public void WhereListingSearchFound() {

		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(WhereListing, 5);
			scrollByElement(WhereListing);
			WebElement Maps = driver.findElement(By.xpath("//span[@id='whereMaps']"));
			waitForElement(SearchFound, 5);
			scrollByElement(SearchFound);
			if (SearchFound.isDisplayed() && SearchFound.isEnabled()) {
				clickelement(SearchFound);
				int Total = GetUIText3(Maps);
				waitForElement(TotalViews, 5);
				scrollByElement(TotalViews);
				String NumofViews = TotalViews.getText();
				int i = convertint(NumofViews);
				System.out.println("Total Number of views :" + i);
				Assert.assertEquals(Total, i);
				System.out.println("Count is equal \n");
				clickelement(SearchFound);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify where listing map data
	 */
	public void WhereListingMapFound() {

		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(WhereListing, 5);
			scrollByElement(WhereListing);
			WebElement Search = driver.findElement(By.xpath("//span[@id='whereSearch']"));
			waitForElement(SearchFound, 5);
			scrollByElement(SearchFound);
			if (MapFound.isDisplayed() && MapFound.isEnabled()) {
				clickelement(MapFound);
				int Total = GetUIText3(Search);
				waitForElement(TotalViews, 5);
				scrollByElement(TotalViews);
				String NumofViews = TotalViews.getText();
				int i = convertint(NumofViews);
				System.out.println("Total Number of views :" + i);
				Assert.assertEquals(Total, i);
				System.out.println("Count is equal \n");
				clickelement(MapFound);
			} else {
				System.out.println("No Data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify how listing discovery data
	 */
	public void HowListingDiscoveryFound() {

		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(HowListing, 5);
			scrollByElement(HowListing);
			WebElement Direct = driver.findElement(By.xpath("//span[@id='howDirect']"));
			WebElement Branded = driver.findElement(By.xpath("//span[@id='howBranded']"));
			waitForElement(Dis, 5);
			if (Dis.isDisplayed() && Dis.isEnabled()) {
				clickelement(Dis);
				int actual = GetUIText2(Direct, Branded);
				waitForElement(TotalSearch, 5);
				scrollByElement(TotalSearch);
				String NumofSearch = TotalSearch.getText();
				int i = convertint(NumofSearch);
				Assert.assertEquals(actual, i);
				System.out.println("count is equal \n");
				clickelement(Dis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * to verify how listing directory data
	 */
	public void HowListingDirFound() {

		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(HowListing, 5);
			scrollByElement(HowListing);
			WebElement Discovery = driver.findElement(By.xpath("//span[@id='howDiscovery']"));
			WebElement Branded = driver.findElement(By.xpath("//span[@id='howBranded']"));
			if (Dir.isDisplayed() && Dir.isEnabled()) {
				scrollByElement(Dir);
				clickelement(Dir);
				int actual = GetUIText2(Discovery, Branded);
				waitForElement(TotalSearch, 5);
				scrollByElement(TotalSearch);
				String NumofSearch = TotalSearch.getText();
				int i = convertint(NumofSearch);
				Assert.assertEquals(actual, i);
				System.out.println("count is equal \n");
				clickelement(Dir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * to verify how listing brand data
	 */
	public void HowListingBrandFound() {

		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(HowListing, 5);
			scrollByElement(HowListing);
			WebElement Discovery = driver.findElement(By.xpath("//span[@id='howDiscovery']"));
			WebElement Direct = driver.findElement(By.xpath("//span[@id='howDirect']"));
			waitForElement(Dir, 5);
			if (Brand.isDisplayed() && Brand.isEnabled()) {
				clickelement(Brand);
				int actual = GetUIText2(Discovery, Direct);
				waitForElement(TotalSearch, 5);
				scrollByElement(TotalSearch);
				String NumofSearch = TotalSearch.getText();
				int i = convertint(NumofSearch);
				Assert.assertEquals(actual, i);
				System.out.println("count is equal \n");
				clickelement(Brand);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to verify Owner photo views data
	 */
	public void OwnerPhotoViews() {

		JSWaiter.waitJQueryAngular();
		waitForElement(PhotoViews, 5);
		scrollByElement(PhotoViews);
		waitForElement(Owner, 5);
		scrollByElement(Owner);
		WebElement CustView = driver.findElement(By.xpath("//span[@id='photoViewsCustomer']"));
		if (Phowner.isDisplayed() && Phowner.isEnabled()) {
			clickelement(Phowner);
			int actual = GetUIText3(CustView);
			waitForElement(TotalPhViews, 5);
			scrollByElement(TotalPhViews);
			String NumofPhViews = TotalPhViews.getText();
			int i = convertint(NumofPhViews);
			Assert.assertEquals(actual, i);
			System.out.println("Count is equal \n");
			clickelement(Phowner);
		}
	}

	/**
	 * to verify customer photo view data
	 */
	public void CustomerPhotoViews() {

		JSWaiter.waitJQueryAngular();
		waitForElement(PhotoViews, 5);
		scrollByElement(PhotoViews);
		waitForElement(Owner, 5);
		scrollByElement(Owner);
		WebElement OwnerView = driver.findElement(By.xpath("//span[@id='photoViewsOwner']"));
		if (PhCust.isDisplayed() && PhCust.isEnabled()) {
			clickelement(PhCust);
			int actual = GetUIText3(OwnerView);
			waitForElement(TotalPhViews, 5);
			scrollByElement(TotalPhViews);
			String NumofPhViews = TotalPhViews.getText();
			int i = convertint(NumofPhViews);
			Assert.assertEquals(actual, i);
			System.out.println("Count is equal \n");
			clickelement(PhCust);
		}
	}

	/**
	 * to compare UI and XL data of web actions
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLWebActions(String filename) throws Exception {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int x = GetDataUsingColName(filename, "Website Actions");
		WebElement WebActions = driver.findElement(By.xpath("//span[@id='customerWebsite']"));
		int total = GetUIText3(WebActions);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of req dir actions
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLReqDirActions(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Request Directions Actions");
		WebElement ReqDirActions = driver.findElement(By.xpath("//span[@id='customerDirections']"));
		int total = GetUIText3(ReqDirActions);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of phone calls
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLPhCalls(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Phone Call Actions");
		WebElement PhCallActions = driver.findElement(By.xpath("//span[@id='customerPhonecalls']"));
		int total = GetUIText3(PhCallActions);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of total actions
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLTotActions(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Total Actions");
		WebElement TotalActions = driver.findElement(By.xpath("//span[@id='customerTotal']"));
		int total = GetUIText3(TotalActions);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of where search
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLWhereSearch(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Search Views");
		WebElement WhereSearch = driver.findElement(By.xpath("//span[@id='whereSearch']"));
		int total = GetUIText3(WhereSearch);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of listing map
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLWhereMap(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Maps Views");
		WebElement WhereMap = driver.findElement(By.xpath("//span[@id='whereMaps']"));
		int total = GetUIText3(WhereMap);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of total views
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLWhereTotalViews(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Total Views");
		WebElement WhereTotalViews = driver.findElement(By.xpath("//span[@id='whereTotal']"));
		int total = GetUIText3(WhereTotalViews);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of discovery
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLDiscovery(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Discovery");
		WebElement Discovery = driver.findElement(By.xpath("//span[@id='howDiscovery']"));
		int total = GetUIText3(Discovery);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of direct
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLDirect(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Direct");
		WebElement Direct = driver.findElement(By.xpath("//span[@id='howDirect']"));
		int total = GetUIText3(Direct);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of brand
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLBranded(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Branded");
		WebElement Direct = driver.findElement(By.xpath("//span[@id='howBranded']"));
		int total = GetUIText3(Direct);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of total search
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLTotalSearch(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Total Searches");
		WebElement TotalSearch = driver.findElement(By.xpath("//span[@id='howTotal']"));
		int total = GetUIText3(TotalSearch);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of phone view
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLOwnerPhView(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Owner Photo Views");
		WebElement OwnerPhView = driver.findElement(By.xpath("//span[@id='photoViewsOwner']"));
		int total = GetUIText3(OwnerPhView);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of customer photo view
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLCustPhView(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Customer Photo Views");
		WebElement CustPhView = driver.findElement(By.xpath("//span[@id='photoViewsCustomer']"));
		int total = GetUIText3(CustPhView);
		Assert.assertEquals(x, total, "Count is equal");

	}

	/**
	 * to compare UI and XL data of total photo view
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void CompareUIXLTotPhView(String filename) throws Exception {

		int x = GetDataUsingColName(filename, "Total Photo Views");
		WebElement TotPhView = driver.findElement(By.xpath("(//span[@id='photoViewsTotal'])[1]"));
		int total = GetUIText3(TotPhView);
		Assert.assertEquals(x, total, "Count is equal");
	}

	/**
	 * to verify if data is available
	 * 
	 * @return
	 */
	public String IsDataAvailable() {
		String data = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		return data;

	}

	/**
	 * to verify title and title text
	 * 
	 * @param Tit
	 * @param titText
	 */
	public void VerifyGMBTitleText(String Tit, String titText) {
		JSWaiter.waitJQueryAngular();
		SoftAssert soft = new SoftAssert();
		waitForElement(PageTitle, 5);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		waitForElement(PageTitletext, 2);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		Assert.assertEquals(Tit, Title);
		Assert.assertEquals(titText, TitleText);
		soft.assertAll();
	}

	/**
	 * to get hover text
	 * 
	 * @param Tet
	 * @param e
	 */
	public void GetHoverText(String Tet, WebElement e) {
		JSWaiter.waitJQueryAngular();
		waitForElement(e, 5);
		scrollByElement(e);
		action.moveToElement(e).build().perform();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String Text = e.getAttribute("data-original-title");
		System.out.println(Text);
		Assert.assertEquals(Tet, Text);
	}

	/**
	 * to verify mouse hover text
	 * 
	 * @throws Exception
	 */
	public void verifyMouseHoverText() throws Exception {
		try {
			GetHoverText(
					"The number of clicks to your business’s website from local search results in Google Maps, Search, and Maps for Mobile.",
					customerWebsite_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "WebsiteVisitHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText(
					" The number of clicks for driving directions from a local search result in Google Maps, Search, and Maps for Mobile.",
					customerDirections_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "ReqDirHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText(
					"The number of clicks for phone calls from a local search result in Google Maps, Search, and Maps for Mobile.",
					customerPhonecalls_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "PhCallHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("The combined total of website visits, direction requests, and phone calls",
					TotalActionsMousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "TotalActionHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText(
					"This section shows how many customers found you via Google Search. Total Listing on Search for a selected date range",
					whereSearch_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "SearchHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText(
					"This section shows how many customers found you via Google Maps. Total Listing on Map for a selected date range",
					whereMaps_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "MapsHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText(
					"Total number of Listing on Search and Total number of Listing on Maps for the selected date range",
					Totalviews_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "TotalViewsHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("Customers who found your listing searching for a category, product, or service.",
					howDiscoveryMousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "DiscoveryHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("Customers who found your listing searching for your business name or address.",
					howDirect_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "DirectHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("Customers who find your listing searching for a brand related to your business.",
					howBranded_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "BrandedHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("Total Discovery and Total Direct based on the selected date range.",
					TotalSearches_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "TotalSearchesHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("How many times photos uploaded by the business owner were viewed.",
					photoViewsOwner_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "PhotoViewsOwnerHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("How many times photos uploaded by a customer were viewed.",
					photoViewsCustomer_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "PhotoViewCustomerHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("Total photo views", photoviewtotalcount_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "TotalPhotoViewHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("Number of photos added by the business owner.", photoCountOwner_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "PhQtyOwnerHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			GetHoverText("Number of photos added by customers.", photoCountCustomer_MousehoverText);
			BaseClass.addEvidence(CurrentState.getDriver(), "PhQtyCusomerHoverText", "yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
