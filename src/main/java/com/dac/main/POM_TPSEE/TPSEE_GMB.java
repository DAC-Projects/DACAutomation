package com.dac.main.POM_TPSEE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	
	//Navigating to TPSEE Content_Analysis page
	public TPSEE_GMB(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
    /*-------------------------------Locators-------------------------------------------------*/
	
	@FindBy(xpath = "//div[@id='customerIndex']")
	private WebElement CustomerActions;
	
	@FindBy(xpath = "//span[@id='customerWebsite']")
	private WebElement WebsitesVisits;
	
	@FindBy(xpath = "//span[@id='customerDirections']")
	private WebElement ReqDir;
	
	@FindBy(xpath = "//span[@id='customerPhonecalls']")
	private WebElement PhCalls;
	
	@FindBy(xpath = "//span[@id='customerTotal']")
	private WebElement TotalActions;
	
	@FindBy(xpath = "//div//button[@id='currentBtnExport']")
	private WebElement export;
	
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
	
	@FindBy(xpath = "//span[@id='whereSearch']")
	private WebElement Search;
	
	@FindBy(xpath = "//span[@id='whereMaps']")
	private WebElement Maps;
	
	@FindBy(xpath = "//span[@id='whereTotal']")
	private WebElement TotalViews;
	
	@FindBy(xpath = "//div[@id='howIndex']")
	private WebElement HowListing;
	
	@FindBy(xpath = "//span[@id='howDiscovery']")
	private WebElement Discovery;
	
	@FindBy(xpath = "//span[@id='howDirect']")
	private WebElement Direct;
	
	@FindBy(xpath = "//span[@id='howBranded']")
	private WebElement Branded;
	
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
	
	
	
	/*-------------------------------Locators-------------------------------------------------*/
	
	
	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void verifyCustomerActionsGraph() {
		
			//display tool tip
		waitForElement(Custgraph, 30);
		scrollByElement(Custgraph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(Custgraph).moveByOffset((Custgraph.getSize().getWidth() / 2) - 2, 0).click().perform();
	
		//read the tooltip variables
		custtooltipvalue = Custgrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +custtooltipvalue);
		}

	public void verifyWhereListingGraph() {

		//display tool tip
		waitForElement(WhereListinggraph, 30);
		scrollByElement(WhereListinggraph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(WhereListinggraph).moveByOffset((WhereListinggraph.getSize().getWidth() / 2) - 2, 0).click().perform();
	
		//read the tooltip variables
		WhereListingtooltipvalue = WhereListingrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +WhereListingtooltipvalue);
	}

	public void verifyPhotoViewGraph() {

		//display tool tip
		waitForElement(PhViewsgraph, 30);
		scrollByElement(PhViewsgraph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(PhViewsgraph).moveByOffset((PhViewsgraph.getSize().getWidth() / 2) - 2, 0).click().perform();
	
		//read the tooltip variables
		PhViewtooltipvalue = PhViewsgrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +PhViewtooltipvalue);
	}

	public void verifyPhotoQtyGraph() {
	
		//display tool tip
		waitForElement(PhQtygraph, 30);
		scrollByElement(PhQtygraph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(PhQtygraph).moveByOffset((PhQtygraph.getSize().getWidth() / 2) - 2, 0).click().perform();
		
		//read the tooltip variables
		PhQtytooltipvalue = PhQtygrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +PhQtytooltipvalue);
	}

	public void verifyHowListingGraph() {	
	
		//display tool tip
		waitForElement(HowlistingFoundgraph, 30);
		scrollByElement(HowlistingFoundgraph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(HowlistingFoundgraph).moveByOffset((HowlistingFoundgraph.getSize().getWidth() / 2) - 2, 0).click().perform();
			
		//read the tooltip variables
		PhViewtooltipvalue = HowListingrphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +PhViewtooltipvalue);
	}

	public void exportGMB() throws InterruptedException, FileNotFoundException, IOException {

		JSWaiter.waitJQueryAngular();
		if(export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
			Thread.sleep(5000);
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+GMBExport));
			Thread.sleep(20000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
			}else {
			System.out.println("No Data Available in GMB");
		}
	}

	public void CustomerAction() {
		
		JSWaiter.waitJQueryAngular();
		try {
		waitForElement(CustomerActions,20);
		scrollByElement(CustomerActions);
		waitForElement(WebsitesVisits,10);
		scrollByElement(WebsitesVisits);
		String WebVisits = WebsitesVisits.getText();
		int TotWebVisits = 0;
		if(WebVisits.contains(",")) {
			String WVisits = WebVisits.replaceAll(",", "");
			WVisits.trim();
			TotWebVisits = Integer.parseInt(WVisits);
		}else {
			TotWebVisits = Integer.parseInt(WebVisits);
		}
		System.out.println("Website Visits : " +TotWebVisits);
		waitForElement(ReqDir, 20);
		scrollByElement(ReqDir);
		String RequestDirectory = ReqDir.getText();
		int TotReqDirectory = 0;
		if(RequestDirectory.contains(",")) {
			String ReqDirectory = RequestDirectory.replaceAll(",", "");
			ReqDirectory.trim();
			TotReqDirectory = Integer.parseInt(ReqDirectory);
		}else {
			TotReqDirectory = Integer.parseInt(RequestDirectory);
		}
		System.out.println("Request Directory :" +TotReqDirectory);
		waitForElement(PhCalls, 20);
		scrollByElement(PhCalls);
		String PhoneCalls = PhCalls.getText();
		int TotPhCalls = 0;
		if(PhoneCalls.contains(",")) {
			String PhoCalls = PhoneCalls.replaceAll(",", "");
			PhoCalls.trim();
			TotPhCalls = Integer.parseInt(PhoCalls);
		}else {
			TotPhCalls = Integer.parseInt(PhoneCalls);
		}
		System.out.println("Request Directory :" +TotPhCalls);
		
		int Total = TotWebVisits+TotReqDirectory+TotPhCalls;
		System.out.println("Total Action :" +Total);
		
		waitForElement(TotalActions,20);
		scrollByElement(TotalActions);
		String TotalAction = TotalActions.getText();
		int TotCustAction = 0;
		if(TotalAction.contains(",")) {
			String TotAction = TotalAction.replaceAll(",", "");
			TotAction.trim();
			TotCustAction = Integer.parseInt(TotAction);
		}else {
			TotCustAction = Integer.parseInt(TotalAction);
		}
		System.out.println("Total Actions is :" +TotCustAction);
		Assert.assertEquals(Total, TotCustAction);
		System.out.println("Count is equal \n");
		}catch(Exception e) {
		e.printStackTrace();
		}
	}

	public void WhereListingFound() {
		
		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(WhereListing, 20);
			scrollByElement(WhereListing);
			waitForElement(Search, 20);
			scrollByElement(Search);
			String NumofSearch = Search.getText();
			int TotNumofSearch = 0;
			if(NumofSearch.contains(",")) {
				String NofSearch = NumofSearch.replaceAll(",", "");
				NofSearch.trim();
				TotNumofSearch = Integer.parseInt(NofSearch);
			}else {
				TotNumofSearch = Integer.parseInt(NumofSearch);
			}
			System.out.println("Total number of search :" +TotNumofSearch);
				
		waitForElement(Maps, 20);
		scrollByElement(Maps);
		String NumofMaps = Maps.getText();
		int TotNumofMaps = 0;
		if(NumofMaps.contains(",")) {
			String NofMaps = NumofMaps.replaceAll(",", "");
			NofMaps.trim();
			TotNumofMaps = Integer.parseInt(NofMaps);
		}else {
			TotNumofMaps = Integer.parseInt(NumofMaps);
		}
		System.out.println("Number of Map :" +NumofMaps);		
		int Total = TotNumofSearch+TotNumofMaps;
		System.out.println("Total Number of views :" +Total);
		
		waitForElement(TotalViews,20);
		scrollByElement(TotalViews);
		String NumofViews = TotalViews.getText();
		int TotNumofViews = 0;
		if(NumofViews.contains(",")) {
			String NofViews = NumofViews.replaceAll(",", "");
			NofViews.trim();
			TotNumofViews = Integer.parseInt(NofViews);
		}else {
			TotNumofViews = Integer.parseInt(NumofViews);
		}
		System.out.println("Total Number of views :" +TotNumofViews);
		Assert.assertEquals(Total, TotNumofViews);
		System.out.println("Count is equal \n");
		}catch(Exception e) {
		e.printStackTrace();
		}
	}

	public void HowListingFound() {

		JSWaiter.waitJQueryAngular();
		try {
		waitForElement(HowListing,20);
		scrollByElement(HowListing);
		waitForElement(Discovery,10);
		scrollByElement(Discovery);
		String Discover = Discovery.getText();
		int TotDiscover = 0;
		if(Discover.contains(",")) {
			String TDiscovery = Discover.replaceAll(",", "");
			TDiscovery.trim();
			TotDiscover = Integer.parseInt(TDiscovery);
		}else {
			TotDiscover = Integer.parseInt(Discover);
		}
		System.out.println("Discovered : " +TotDiscover);
		
		waitForElement(Direct, 20);
		scrollByElement(Direct);
		String Directed = Direct.getText();
		int TotDirected = 0;
		if(Directed.contains(",")) {
			String Directory = Directed.replaceAll(",", "");
			Directory.trim();
			TotDirected = Integer.parseInt(Directory);
		}else {
			TotDirected = Integer.parseInt(Directed);
		}
		System.out.println("Directed :" +TotDirected);
		waitForElement(Branded, 20);
		scrollByElement(Branded);
		String Brand = Branded.getText();
		int TotBrand = 0;
		if(Brand.contains(",")) {
			String NumBrand = Brand.replaceAll(",", "");
			NumBrand.trim();
			TotBrand = Integer.parseInt(NumBrand);
		}else {
			TotBrand = Integer.parseInt(Brand);
		}
		System.out.println("Branded :" +TotBrand);
		
		int Total = TotDiscover+TotDirected+TotBrand;
		System.out.println("Total Number of Search :" +Total);
		
		waitForElement(TotalSearch,20);
		scrollByElement(TotalSearch);
		String NumofSearch = TotalSearch.getText();
		int TotNumofSearch = 0;
		if(NumofSearch.contains(",")) {
			String TotalNumofSearch = NumofSearch.replaceAll(",", "");
			TotalNumofSearch.trim();
			TotNumofSearch = Integer.parseInt(TotalNumofSearch);
		}else {
			TotNumofSearch = Integer.parseInt(NumofSearch);
		}
		System.out.println("Total Number of Search :" +TotNumofSearch);
		Assert.assertEquals(Total, TotNumofSearch);
		System.out.println("count is equal \n");
		}catch(Exception e) {
		e.printStackTrace();
		}
	}

	public void PhotoViews() {
		
		JSWaiter.waitJQueryAngular();
		try {
			waitForElement(PhotoViews, 20);
			scrollByElement(PhotoViews);
			waitForElement(Owner, 20);
			scrollByElement(Owner);
			String OwnerView = Owner.getText();
			int TotOwnerView = 0;
			if(OwnerView.contains(",")) {
				String NofOwnerView = OwnerView.replaceAll(",", "");
				NofOwnerView.trim();
				TotOwnerView = Integer.parseInt(NofOwnerView);
			}else {
				TotOwnerView = Integer.parseInt(OwnerView);
			}
			System.out.println("Total number of Owner View :" +TotOwnerView);
				
		waitForElement(Customer, 20);
		scrollByElement(Customer);
		String CustomerView = Customer.getText();
		int TotCustView = 0;
		if(CustomerView.contains(",")) {
			String NofCustView = CustomerView.replaceAll(",", "");
			NofCustView.trim();
			TotCustView = Integer.parseInt(NofCustView);
		}else {
			TotCustView = Integer.parseInt(CustomerView);
		}
		System.out.println("Number of Map :" +CustomerView);		
		int Total = TotOwnerView+TotCustView;
		System.out.println("Total Number of views :" +Total);
		
		waitForElement(TotalPhViews,20);
		scrollByElement(TotalPhViews);
		String NumofPhViews = TotalPhViews.getText();
		int TotNumofPhViews = 0;
		if(NumofPhViews.contains(",")) {
			String NofPhViews = NumofPhViews.replaceAll(",", "");
			NofPhViews.trim();
			TotNumofPhViews = Integer.parseInt(NofPhViews);
		}else {
			TotNumofPhViews = Integer.parseInt(NumofPhViews);
		}
		System.out.println("Total Number of views :" +TotNumofPhViews);
		Assert.assertEquals(Total, TotNumofPhViews);
		System.out.println("Count is equal \n");
		}catch(Exception e) {
		e.printStackTrace();
		}
	}
}
