package com.dac.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Driver;

public class Navigationpage extends BasePage{

	Actions action;
	WebDriverWait wait;
	
	public Navigationpage(WebDriver driver) {
		action=new Actions(driver);
		wait=new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "//a[@href='/Dashboard/AllLocations/']")
	private WebElement AllLocations;

	@FindBy(id = "//a[@href='/Dashboard/AllGroups/']")
	private WebElement AllGroups;

	@FindBy(xpath = "//a[@href='/Dashboard/VaReport/']")
	private WebElement Visibility;

	@FindBy(xpath = "//a[@href='/Dashboard/AccuracyReport/']")
	private WebElement Accuracy;

	@FindBy(xpath = "//a[@href='/Dashboard/AnalysisReport/']")
	private WebElement Analysis;
	
	@FindBy(xpath = "//a[@href='/Dashboard/GoogleRanking/']")
	private WebElement GoogleRanking;
	
	@FindBy(xpath = "//a[@href='/Dashboard/ReviewStream/']")
	private WebElement ReviewStream;
	
	@FindBy(xpath = "//a[@href='/Dashboard/Review/ReviewReport/']")
	private WebElement ReviewReport;
	
	@FindBy(xpath = "//a[@href=//a[@href='/ReportCard/Index/']]")
	private WebElement ReportCard;
	
	@FindBy(xpath = "//a[@href='/CategorizedSentiment/Index/']")
	private WebElement CategorizedSentiment;
	
	@FindBy(xpath = "//a[@href='/Review/FrequentKeywords/']")
	private WebElement FrequentKeywords;
	
	//----------DashBoard Language--------------------
	
	@FindBy(xpath="//div[@id='lang-flag']")
	private WebElement DBLangLink;
	
	@FindBy(xpath="//div[@class='lang-popup']")
	private WebElement DBLangPopUp;
	

	//----------Review Solicitation or Customer FeedBack ---------
	
	@FindBy(xpath="//li[@id='campaign']//span")
	private WebElement CampaignsLink;
	
	@FindBy(xpath="//li[@id='review']//span")
	private WebElement ResponsesLink; 
	
	@FindBy(xpath="//li[@id='report']//span")
	private WebElement ReportsLink;
	
	
	public WebElement getGoogleRanking() {
		return GoogleRanking;
	}

	public WebElement getReviewStream() {
		return ReviewStream;
	}

	public WebElement getReviewReport() {
		return ReviewReport;
	}

	public WebElement getReportCard() {
		return ReportCard;
	}

	public WebElement getCategorizedSentiment() {
		return CategorizedSentiment;
	}

	public WebElement getFrequentKeywords() {
		return FrequentKeywords;
	}

	public WebElement getAccuracy() {
		return Accuracy;
	}

	public WebElement getAllGroups() {
		return AllGroups;
	}

	public WebElement getAllLocations() {
		return AllLocations;
	}

	public WebElement getAnalysis() {
		return Analysis;
	}

	public WebElement getVisibility() {
		return Visibility;
	}
	
	public void clickCampaigns() {
		CampaignsLink.click();
	}
	
	public void clickResponses() {
		ResponsesLink.click();
	}
	
	public void clickReports() {
		ResponsesLink.click();
	}
     
	public void click_DB_Lang_Link() {
		
		if(DBLangLink.isDisplayed()) {
			System.out.println("displayed");
		}
		System.out.println("giong to click in pom class");
		action.moveToElement(DBLangLink).click().perform();
		System.out.println("clicked ");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lang-popup']")));
		
	}
	

}
