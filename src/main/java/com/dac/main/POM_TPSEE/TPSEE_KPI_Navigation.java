package com.dac.main.POM_TPSEE;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.BaseClass;

public class TPSEE_KPI_Navigation extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public TPSEE_KPI_Navigation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='location']")
	private WebElement AllLocations;

	@FindBy(xpath = "//div[@id='visibility']")
	private WebElement VisibilityReport;

	@FindBy(xpath = "//div[@id='accuracy']")
	private WebElement AccuracyReport;

	@FindBy(xpath = "//div[@id='contentAnalysis']")
	private WebElement ContentAnalysis;

	@FindBy(xpath = "//div[@id='googleRanking']")
	private WebElement GoogleRanking;

	@FindBy(xpath = "//div[@id='average']")
	private WebElement ReviewInsights;

	@FindBy(xpath = "//li[@id='dashboard']")
	private WebElement Dashboard;

	@FindBy(xpath = "//h3[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath = "//*[@id='page-content']//h1")
	private WebElement CATitle;

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}

	public void navigateToAllLocations() throws Exception {
		KPIMouseHover(AllLocations, "Number of participating locations");
		Thread.sleep(3000);
		navigateKPI(AllLocations, PageTitle, "Locations");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to All Location", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	public void navigateToVisibilityRpt() throws Exception {
		KPIMouseHover(VisibilityReport, "Visibility score for all participating locations");
		Thread.sleep(3000);
		navigateKPI(VisibilityReport, PageTitle, "Visibility Report");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Visibility Report", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	public void navigateToAccuracyReport() throws Exception {
		KPIMouseHover(AccuracyReport, "Accuracy score for all found listings");
		Thread.sleep(3000);
		navigateKPI(AccuracyReport, PageTitle, "Accuracy Report");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Accuracy Report", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	public void navigateToContentAnalysis() throws Exception {
		KPIMouseHover(ContentAnalysis, "Content Analysis score for all found locations");
		Thread.sleep(3000);
		navigateKPI(ContentAnalysis, CATitle, "Content Analysis Report");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Content Analysis", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	public void navigateToGoogleRanking() throws Exception {
		KPIMouseHover(GoogleRanking, "Google Ranking score for all participating locations");
		Thread.sleep(3000);
		navigateKPI(GoogleRanking, CATitle, "Google Ranking Report"
				+"\n" +"Ranking Info");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Google Ranking", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	public void navigateToReviews() throws Exception {
		KPIMouseHover(ReviewInsights, "Average review score of participating locations over the last month");
		Thread.sleep(3000);
		navigateKPI(ReviewInsights, PageTitle, "Review Insights");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Review Insights", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

}
