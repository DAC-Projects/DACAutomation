package com.dac.main;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Driver;

import resources.DateFormats;


public class Navigationpage extends BasePage{

	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	WebDriver driver;
	
	public Navigationpage(WebDriver driver) {
		
		this.driver=driver;
		wait=new WebDriverWait(driver, 50);
		action=new Actions(driver);
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
	
	@FindBy(id="lang-icon")
	private WebElement DBLangLink;
	
	@FindBy(xpath="//div[@id='lang-popup']/div/i")
	private WebElement DBLangPopUp;
	
	@FindBy(linkText="Deutsch")
	private WebElement selectDB_langDeutsch;
	
	@FindBy(linkText="English")
	private WebElement selectDB_langEnglish;
	
	@FindBy(xpath="(//a[contains(text(),'Español')])[1]")
	private WebElement selectDB_langSpanish_Spain;
	
	@FindBy(xpath="(//a[contains(text(),'Español')])[2]")
	private WebElement selectDB_langSpanish_Mexico;
	
	@FindBy(xpath="(//a[contains(text(),'Français')])[1]")
	private WebElement selectDB_langFrench_Canada;
	
	@FindBy(xpath="(//a[contains(text(),'Français')])[2]")
	private WebElement selectDB_langFrench_France;
	
	@FindBy(linkText="Italiano")
	private WebElement selectDB_langItalian;
	
	@FindBy(linkText="Svenska")
	private WebElement selectDB_langSwedish;

	//----------Review Solicitation or Customer FeedBack ---------
	
	@FindBy(xpath="//li[@id='campaign']//span")
	private WebElement CampaignsLink;
	
	//campaign table one of the column for wait the loading of page till this table visible
	@FindBy(xpath="(//td[contains(@ng-if,'activeCampaign.MLC')]/a)[4]")
	private WebElement campaignTable;

	@FindBy(xpath="//li[@id='review']//span")
	private WebElement ResponsesLink; 
	
	@FindBy(xpath="//li[@id='report']//span")
	private WebElement ReportsLink;
	
	//---------------------- CA-------------------------------
	
	@FindBy(xpath="//a[@href='/CompetitiveAnalysis/Accuracy']")
	private WebElement CA_Accuracy;
	
	@FindBy(xpath="//a[@href='/CompetitiveAnalysis/Visibility']")
	private WebElement CA_Visibility;
	
	public void  navigateCA_Visibility() {
		wait.until(ExpectedConditions.visibilityOf(CA_Visibility));
		scrollByElement(CA_Visibility, driver);
		try {
			CA_Visibility.click();
		}
		catch(Exception e) {
			action.moveToElement(CA_Visibility).click().perform();
		}
		finally {
			waitUntilLoad(driver);
			
		}
	}

	public void navigateCA_Accuracy() {
		
		wait.until(ExpectedConditions.visibilityOf(CA_Accuracy));
		scrollByElement(CA_Accuracy, driver);
		try {
			CA_Accuracy.click();
		}
		catch(Exception e) {
			action.moveToElement(CA_Accuracy).click().perform();
		}
		finally {
			waitUntilLoad(driver);
			
		}
		
	}
	
	
	
	public void waitUntilLoad(WebDriver driver) {

		    //WebDriverWait wait = new WebDriverWait(driver, 30);

		    // wait for jQuery to load
		    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		      @Override
		      public Boolean apply(WebDriver driver) {
		        try {
		          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
		        }
		        catch (Exception e) {
		          // no jQuery present
		          return true;
		        }
		      }
		    };

		    // wait for Javascript to load
		    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		      @Override
		      public Boolean apply(WebDriver driver) {
		        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
		        .toString().equals("complete");
		      }
		    };

		  wait.until(jQueryLoad);wait.until(jsLoad);
		}
	
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
	
	/** To click on Campaigns link in LHS to navigate to Campaigns page  
	 * @throws InterruptedException */
	public void clickCampaigns() throws InterruptedException {    
		wait.until(ExpectedConditions.visibilityOf(CampaignsLink));
		scrollByElement(CampaignsLink, driver);
		try {
			CampaignsLink.click();
		}
		catch(Exception e) {
			action.moveToElement(CampaignsLink).click().perform();
		}
		finally {
			wait.until(ExpectedConditions.visibilityOf(campaignTable));
			Thread.sleep(45000);
		}
	}
	
	public void clickResponses() {
		wait.until(ExpectedConditions.visibilityOf(ResponsesLink));
		scrollByElement(ResponsesLink, driver);
		try {
			ResponsesLink.click();
		}
		catch(Exception e) {			
			action.moveToElement(ResponsesLink).click().perform();
		}
	}
	
	public void clickReports() {
		wait.until(ExpectedConditions.visibilityOf(ReportsLink));
		scrollByElement(ReportsLink, driver);
		try {
			ReportsLink.click();
		}
		catch(Exception e) {
			action.moveToElement(ReportsLink).click().perform();
		}
	}
	
	private void DB_LangList() {
		//Thread.sleep(2000);
		action.moveToElement(DBLangLink).click(DBLangLink).perform();
		action.moveToElement(DBLangPopUp).click(DBLangPopUp).perform();
		//DBLangLink.click();
		//DBLangPopUp.click();
	}
     
	public void select_DB_Lang_Link(String langCode, String contryCode) {
		
		String langConCode = langCode+"_"+contryCode;
		wait.until(ExpectedConditions.visibilityOf(DBLangLink));
		if(DBLangLink.isDisplayed()) {
			DB_LangList();
				switch(langConCode) {
				
				case "de_DE" : //Thread.sleep(2000);
							   try {
								   selectDB_langDeutsch.click();   
							   }
							   catch(Exception e){
								   if(driver.findElement(By.xpath("//i/following::span[contains(text(),'Deutsch')]")).isDisplayed()) {
									   break;
								   }
							   }
							   break;
							   
				case "en_US" : //Thread.sleep(2000);
							   try {
								   selectDB_langEnglish.click();
							   }
							   catch(Exception e){
								   if(driver.findElement(By.xpath("//i/following::span[contains(text(),'English')]")).isDisplayed()) {
									   break;
								   }
							   }
				   			   break;
				   			   
				case "es_ES" : //Thread.sleep(2000);
				 				try {
				 					selectDB_langSpanish_Spain.click();   
				 				}
				 				catch(Exception e){
				 					if(driver.findElement(By.xpath("//i/following::span[contains(text(),'Español')]")).isDisplayed()) {
										   break;
									}
				 				}
				 				break;
				   			   
				case "es_MX" : //Thread.sleep(2000);
							   try {
								   selectDB_langSpanish_Mexico.click();   
							   }
							   catch(Exception e){
								   if(driver.findElement(By.xpath("//i/following::span[contains(text(),'Español')]")).isDisplayed()) {
									   break;
								   }
							   }
				   			   break;
				   			   
				case "fr_CA" : //Thread.sleep(2000);
							   try {
								   selectDB_langFrench_Canada.click();   
							   }
							   catch(Exception e){
								   if(driver.findElement(By.xpath("//i/following::span[contains(text(),'Français')]")).isDisplayed()) {
									   break;
								   }
							   }
				   			   break;
				   			   
				case "fr_FR" : //Thread.sleep(2000);
				 			   try {
				 				   selectDB_langFrench_France.click();   
				 			   }
				 			   catch(Exception e){
				 				  if(driver.findElement(By.xpath("//i/following::span[contains(text(),'Français')]")).isDisplayed()) {
									   break;
								   }
				 			   }
				   			   break;
				   			   
				case "it_IT" : //Thread.sleep(2000);
							   try {
								   selectDB_langItalian.click();   
							   }
							   catch(Exception e){
								   if(driver.findElement(By.xpath("//i/following::span[contains(text(),'Italiano')]")).isDisplayed()) {
									   break;
								   }
							   }
				   			   break;
				   			   
				case "sv_SE" : //Thread.sleep(2000);
							   try {
								   selectDB_langSwedish.click();   
							   }
							   catch(Exception e){
								   if(driver.findElement(By.xpath("//i/following::span[contains(text(),'Svenska')]")).isDisplayed()) {
									   break;
								   }
							   }
							   break;
							 
				default		 : System.out.println("Selected wrong Language Code or Contry Code please check and Execute once again");
				}
			}
	}
}
