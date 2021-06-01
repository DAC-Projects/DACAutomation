package com.dac.main.POM_TPSEE;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.JSWaiter;

public class TPSEE_LeftHand_Navigation extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	@FindBy(xpath = "//*[@id='locations']")
	private WebElement LocationSec;
	
	@FindBy(xpath = "//*[@id='all_locations']")
	private WebElement AllLocationsPage;
	
	@FindBy(xpath = "//li[@id='visibility_report']")
	private WebElement VisibilityPage;
	
	@FindBy(xpath = "//*[@id='accuracy_report']")
	private WebElement AccuracyPage;
	
	@FindBy(xpath = "//*[@id='completeness_report']")
	private WebElement ContentAnalysisPage;
	
	@FindBy(xpath = "//*[@id='google_ranking_report']")
	private WebElement GRPage;
	
	@FindBy(xpath = "//li[@id='duplicate_management_report']")
	private WebElement Duplicate_Management;
	
	@FindBy(xpath = "//li[@id='syndication_status_report']")
	private WebElement Listing_Verification;
	
	@FindBy(xpath = "//li[@id='local_rank_plus_report']")
	private WebElement Local_Rank_Plus;
	
	@FindBy(xpath = "//*[@id='reports']")
	private WebElement LocalReportsSec;
	
	@FindBy(xpath = "//li[@id='bing_places_report']")
	private WebElement BingPage;
	
	@FindBy(xpath = "//li[@id='facebook_insights']")
	private WebElement FacebookPage;
	
	@FindBy(xpath = "//li[@id='google_places_report']")
	private WebElement GMBPage;
	
	@FindBy(xpath = "//li[@id='local_analytics']")
	private WebElement LocalAnalyticsSec;
	
	@FindBy(xpath = "//li[@id='dashboard']")
	private WebElement Dashboard;
	
	
	public TPSEE_LeftHand_Navigation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Common method to verify active state of report
	 * @param ele
	 * @param ele1
	 */
	public void reporthighlight(WebElement ele, WebElement ele1) {
		waitForElement(ele1, 10);
		JSWaiter.waitJQueryAngular();
		String mainmenu = ele1.getAttribute("class");
		System.out.println("The mainmenu is :" + mainmenu);
		soft.assertEquals(mainmenu, "on_off_root active");
		waitForElement(ele, 5);
		String text = ele.getAttribute("class");
		System.out.println("The class name is :" + text);
		soft.assertEquals(text, "active");
	}
	
	/**
	 * Common method to verify inactive state of report section
	 * @param ele
	 */
	public void verifyinactive(WebElement ele) {
		waitForElement(ele, 5);
		String Othermenu = ele.getAttribute("class");
		System.out.println("The Othermenu class name is : " +Othermenu);
		soft.assertEquals(Othermenu, "on_off_root");
	}
	
	/**
	 * To verify active report - Visibility Report
	 */
	public void visibilityhightlight() {
		reporthighlight(VisibilityPage,LocalReportsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Accuracy Report
	 */
	public void accuracyhightlight() {
		reporthighlight(AccuracyPage,LocalReportsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Content Analysis Report
	 */
	public void contentanalysishightlight() {
		reporthighlight(ContentAnalysisPage,LocalReportsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Google Ranking Report
	 */
	public void GoogleRankinghightlight() {
		reporthighlight(GRPage,LocalReportsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Duplicate Management
	 */
	public void Duplicatehightlight() {
		reporthighlight(Duplicate_Management,LocalReportsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Data Syndication
	 */
	public void ListingVerificationhightlight() {
		reporthighlight(Listing_Verification,LocalReportsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Local Rank Plus
	 */
	public void LocalRankPlushightlight() {
		reporthighlight(Local_Rank_Plus,LocalReportsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Google My Business Report
	 */
	public void GoogleMyBusinesshightlight() {
		reporthighlight(GMBPage,LocalAnalyticsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalReportsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - Bing Places for Business Report
	 */
	public void BingPlacesforBusinesshightlight() {
		reporthighlight(BingPage,LocalAnalyticsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalReportsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}

	/**
	 * To verify active report - Facebook Insights Report
	 */
	public void FacebookInsightshightlight() {
		reporthighlight(FacebookPage,LocalAnalyticsSec);
		verifyinactive(LocationSec);
		verifyinactive(LocalReportsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To verify active report - All Locations Report
	 */
	public void AllLocationshightlight() {
		reporthighlight(AllLocationsPage,LocationSec);
		verifyinactive(LocalAnalyticsSec);
		verifyinactive(LocalReportsSec);
		verifyinactive(Dashboard);
		soft.assertAll();
	}
	
	/**
	 * To click on done button
	 */
	public void clickDone() {
		boolean done = Done();
		try {	
		if(done == true) {
			driver.findElement(By.xpath("//button//span[@class='walkme-custom-balloon-button-text']")).click();
		}else {
			System.out.println("NO Button Displayed");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean Done() {
		if((driver.findElement(By.xpath("//button//span[@class='walkme-custom-balloon-button-text']"))).isDisplayed()) {
			return true;
		}else {
		return false;
		}
	}
}
