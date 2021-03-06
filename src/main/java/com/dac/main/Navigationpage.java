package com.dac.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.JSWaiter;

/**
 * Using this class methods it used to navigate to specific page's of navigation
 * panel in TSEE
 */
public class Navigationpage extends BasePage {

	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	WebDriver driver;

	public Navigationpage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 55);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);

	}

	// -------------------------------- TSEE ------------------------------
	@FindBy(xpath = "//a[@href='/Dashboard/AllLocations/']/span")
	private WebElement AllLocations;

	@FindBy(xpath = "//a[@href='/Dashboard/AllGroups/']/span")
	private WebElement AllGroups;

	@FindBy(xpath = "//a[@href='/Dashboard/VaReport/']/span")
	private WebElement Visibility;

	@FindBy(xpath = "//*[@id='Accuracy_Index']")
	private WebElement Accuracy;

	@FindBy(xpath = "//a[@href='/Dashboard/AnalysisReport/']/span")
	private WebElement Analysis;

	@FindBy(xpath = "//a[@href='/Dashboard/GoogleRanking/']/span")
	private WebElement GoogleRanking;

	@FindBy(xpath = "//a[@href='/Dashboard/ReviewStream']/span")
	private WebElement ReviewStream;

	@FindBy(xpath = "//a[@href='/Review/ReviewScore/']/span")
	private WebElement DisplayedReviewScore;

	@FindBy(xpath = "//li[@class='settings-dropdown-cog']")
	private WebElement Settings;

	@FindBy(xpath = "//ul[contains(@class,'settings-list dashboardColor')]")
	private WebElement Group;

	@FindBy(xpath = "//a[@href='/Dashboard/GoogleMyBusiness/']/span")
	private WebElement GMB;

	@FindBy(xpath = "//a[@href='/Dashboard/BingPlacesForBusiness/']/span")
	private WebElement Bing;

	@FindBy(xpath = "//a[@href='/Dashboard/ESRFrequency/']/span")
	private WebElement ESR;

	@FindBy(xpath = "//a[@href='/Dashboard/LocalReportsScoreChange/']")
	private WebElement ScoreChange;

	@FindBy(xpath = "//a[@href='/Dashboard/ReviewsNotification/']")
	private WebElement ReviewNotification;

	@FindBy(xpath = "//*[@id='roi_gmb']/a")
	private WebElement ROI;

	@FindBy(xpath = "//a[@href='/Dashboard/FacebookInsights/']")
	private WebElement FacebookInsights;

	@FindBy(xpath = "//a[@href='/Dashboard/DuplicateManagement/']/span")
	private WebElement DupManagement;
	
	@FindBy(xpath = "//a[@href='/Dashboard/SyndicationStatus']/span")
	private WebElement DataSyndication;

	// ------------------------- SA and RRM -------------------------------------

	@FindBy(xpath = "//a[@href='/Review/ReviewFeed/']")
	private WebElement ReviewFeed;

	@FindBy(xpath = "//a[@href='/ReportCard/Index/']/span")
	private WebElement ReportCard;

	@FindBy(xpath = "//a[@href='/CategorizedSentiment/Index/']/span")
	private WebElement CategorizedSentiment;

	@FindBy(xpath = "//a[@href='/Review/FrequentKeywords/']/span")
	private WebElement FrequentKeywords;

	// ----------DashBoard Language--------------------

	@FindBy(id = "lang-icon")
	private WebElement DBLangLink;

	@FindBy(xpath = "//div[@id='lang-popup']/div/i")
	private WebElement DBLangPopUp;

	@FindBy(linkText = "Deutsch")
	private WebElement selectDB_langDeutsch;

	@FindBy(linkText = "English")
	private WebElement selectDB_langEnglish;

	@FindBy(xpath = "(//a[contains(text(),'Español')])[1]")
	private WebElement selectDB_langSpanish_Spain;

	@FindBy(xpath = "(//a[contains(text(),'Español')])[2]")
	private WebElement selectDB_langSpanish_Mexico;

	@FindBy(xpath = "(//a[contains(text(),'Français')])[1]")
	private WebElement selectDB_langFrench_Canada;

	@FindBy(xpath = "(//a[contains(text(),'Français')])[2]")
	private WebElement selectDB_langFrench_France;

	@FindBy(linkText = "Italiano")
	private WebElement selectDB_langItalian;

	@FindBy(linkText = "Svenska")
	private WebElement selectDB_langSwedish;

	// ----------Review Solicitation or Customer FeedBack ---------

	@FindBy(xpath = "//li[@id='campaign']//span")
	private WebElement CF_CampaignsLink;

	@FindBy(xpath = "(//td[contains(@ng-if,'activeCampaign.MLC')]/a)[1]")
	private WebElement CF_processedCampDetailsLink;

	@FindBy(xpath = "//a[contains(@class,'btnEdit')]/span")
	private WebElement CF_scheduledCampEditBTN;

	// campaign table one of the column for wait the loading of page till this table
	// visible
	@FindBy(xpath = "(//td[contains(@ng-if,'activeCampaign.MLC')]/a)[4]")
	private WebElement CF_campaignTable;

	@FindBy(xpath = "//li[@id='review']//span")
	private WebElement CF_ResponsesLink;

	@FindBy(xpath = "//li[@id='report']//span")
	private WebElement CF_ReportsLink;
	
	@FindBy(xpath = "//a[@href='/Dashboard/Campaigns/']//span")
	private WebElement CampaignsPage;
	
	@FindBy(xpath = "//a[@href='/Dashboard/CfResponses/']//span")
	private WebElement ResponsesPage;
	
	@FindBy(xpath = "//a[@href='/Dashboard/CfReports/']//span")
	private WebElement CF_Reports;

	// ---------------------- CA-------------------------------

	@FindBy(xpath = "//a[@href='/CompetitiveAnalysis/Accuracy']")
	private WebElement CA_Accuracy;

	@FindBy(xpath = "//a[@href='/CompetitiveAnalysis/Visibility']")
	private WebElement CA_Visibility;

	@FindBy(xpath = "//a[@href='/CompetitiveAnalysis/Analysis']")
	private WebElement CA_ContentAnalysis;

    @FindBy(xpath="//a[@href='/CompetitiveAnalysis/Summary']")
    private WebElement CA_Summary;
    
	@FindBy(xpath = "//a[@href='/CompetitiveAnalysis/Review']/span")
	private WebElement CA_Reviewpage;

	// ---------------------- TransparenSEE-------------------------------
	@FindBy(id = "VA")
	private WebElement TPSEE_Visibility;

	/*------------------------------Social---------------------------------------*/
   
    
	@FindBy(id="PostsSocialMedia")
	private WebElement SE_Posts;
  
    @FindBy(id="ContentManagementSocialMedia")
    private WebElement SE_ContentManagement;
    
    @FindBy(xpath="//*[@href='/SocialMediaReports/']")
    private WebElement SE_Reports;
    
    

	/*--------------------------Walkme Snippet-----------------------------------*/

	@FindBy(xpath = "//button//span[@class='walkme-custom-balloon-button-text' and contains(text(),'Cancel')]")
	private WebElement WalkMeCancel;

	@FindBy(xpath = "//span[@class= 'walkme-action-destroy-0 wm-close-link' and contains(text(),'Okay')]")
	private WebElement NotificationPopUp;

	/*--------------------------Walkme Snippet-----------------------------------*/
	
	/**
	 * To Navigate to Campaigns Page
	 */
	public void navigateToCampaignsPage() {
		clickelement(CampaignsPage);
		System.out.println("Waiting for page to load******");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("");
		}
	}

	/**
	 * To Navigate to visbility page
	 */
	public void navigateTPSEE_Visibility() {
		clickelement(TPSEE_Visibility);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("");
		}
	}

	/**
	 * To navigate to accuracy Report
	 */
	public void navigateTPSEE_Accuracy() {
		clickelement(Accuracy);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To navigate to Content Analysis Page
	 */
	public void navigateToContentAnalysis() {
		clickelement(Analysis);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to GR Page
	 */
	public void navigateToGoogleRanking() {
		clickelement(GoogleRanking);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To navigate to All Locations Page
	 */
	public void navigateToAllLocations() {
		clickelement(AllLocations);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to All Groups Page
	 */
	public void navigateToAllGroups() {
		action.moveToElement(Settings).perform();
		waitForElement(Group, 20);
		action.moveToElement(AllGroups).perform();
		driver.findElement(By.xpath("//a[@href='/Dashboard/AllGroups/']/span")).click();
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to Review Stream Page
	 */
	public void navigateToReviewStream() {
		clickelement(ReviewStream);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to DRS Page
	 */
	public void navigateToDisplayedReviewScore() {
		clickelement(DisplayedReviewScore);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to GMB Page
	 */
	public void navigateToGoogleMyBusiness() {
		clickelement(GMB);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to Bing Page
	 */
	public void navigateToBingPlacesForBusiness() {

		clickelement(Bing);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to ESR Page
	 */
	public void navigateToESR() {
		action.moveToElement(Settings).perform();
		waitForElement(ESR, 20);
		action.moveToElement(ESR).perform();
		driver.findElement(By.xpath("//a[@href='/Dashboard/ESRFrequency/']/span")).click();
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to Local Report Score Change Page
	 */
	public void navigateToLocalReportsScoreChange() {
		action.moveToElement(Settings).perform();
		waitForElement(ScoreChange, 20);
		action.moveToElement(ScoreChange).perform();
		clickelement(ScoreChange);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to RN Page
	 */
	public void navigateToReviewNotifications() {
		action.moveToElement(Settings).perform();
		waitForElement(ReviewNotification, 20);
		action.moveToElement(ReviewNotification).perform();
		clickelement(ReviewNotification);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to ROI Page
	 */
	public void navigateToROI() {
		clickelement(ROI);
		System.out.println("Waiting for page to load********");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to Facebook Page
	 */
	public void navigateToFacebookInsights() {
		clickelement(FacebookInsights);
		System.out.println("Waiting for page to Load*******");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to Duplicate Management Page
	 */
	public void navigateToDuplicateManagement() {
		clickelement(DupManagement);
		System.out.println("Waiting for page to Load*******");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}

	/**
	 * To Navigate to Syndication Status Page
	 */
	public void navigateToSyndicationStatus() {
		clickelement(DataSyndication);
		System.out.println("Waiting for page to Load*******");
		waitUntilLoad(driver);
		try {
			clickwalkme();
		} catch (Exception e) {
			System.out.println("No Walkme Displayed");
		}
		try {
			clickNotificationPopUp();
		} catch (Exception e) {
			System.out.println("No Notification PopUp displayed");
		}
	}
	// -----CA

	  /** To click on CA_Review link in LHS to navigate to CA_Summary page   */
		public void navigateCA_Summarypage() {
		  clickelement(CA_Summary);   
	      System.out.println("Waiting for the Summary page to load**********");
	      waitUntilLoad(driver);
		}
	/** To click on CA_Visibility link in LHS to navigate to CA_Visibility page */

	public void navigateCA_Visibility() {
		clickelement(CA_Visibility);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/** To click on CA_Accuracy link in LHS to navigate to CA_Accuracy page */
	public void navigateCA_Accuracy() {
		clickelement(CA_Accuracy);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/** To click on CA_Review link in LHS to navigate to CA_Review page */
	public void navigateCA_Reviewpage() {
		clickelement(CA_Reviewpage);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/**
	 * To click on CA_ContentAnalysis link in LHS to navigate to CA_ContentAnalysis
	 * page
	 */
	public void navigateCA_ContentAnalysispage() {
		clickelement(CA_ContentAnalysis);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/**
	 * To click on SA_ReviewReport link in LHS to navigate to SA_ReviewReport page
	 */
	public void navigateToSA_ReviewReport() {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOf(ReviewFeed));
		scrollByElement(ReviewFeed);
		clickelement(ReviewFeed);
		System.out.println("Waiting for page to load**********");
	}

	/** To click on SA_ReportCard link in LHS to navigate to SA_ReportCard page */
	public void navigateToSA_ReportCard() {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOf(ReportCard));
		scrollByElement(ReportCard);
		clickelement(ReportCard);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/**
	 * To click on SA_CategorizedSentiment link in LHS to navigate to
	 * SA_CategorizedSentiment page
	 */
	public void navigateToSA_CategorizedSentiment() {
		clickelement(CategorizedSentiment);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/**
	 * To click on SA_FrequentKeywords link in LHS to navigate to
	 * SA_FrequentKeywords page
	 */
	public void navigateToSA_FrequentKeywords() {
		clickelement(FrequentKeywords);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/**
	 * To click on Customer Feedback's Campaigns link in LHS to navigate to Customer
	 * Feedback's Campaigns page
	 */
	public void clickCampaigns() {
		wait.until(ExpectedConditions.visibilityOf(CF_CampaignsLink));
		scrollByElement(CF_CampaignsLink);
		clickelement(CF_CampaignsLink);
		waitUntilLoad(driver);
		wait.until(ExpectedConditions.visibilityOf(CF_processedCampDetailsLink));
		wait.until(ExpectedConditions.visibilityOf(CF_scheduledCampEditBTN));
	}

	/**
	 * To click on Customer Feedback's Responses link in LHS to navigate to Customer
	 * Feedback's Responses page
	 */
	public void clickResponses() {
		wait.until(ExpectedConditions.visibilityOf(CF_ResponsesLink));
		scrollByElement(CF_ResponsesLink);
		clickelement(CF_ResponsesLink);
		waitUntilLoad(driver);
	}

	/**
	 * To click on Customer Feedback's Report's link in LHS to navigate to Customer
	 * Feedback's Report's page
	 */
	public void clickReports() {
		wait.until(ExpectedConditions.visibilityOf(CF_ReportsLink));
		scrollByElement(CF_ReportsLink);
		clickelement(CF_ReportsLink);
		waitUntilLoad(driver);
	}

	private void DB_LangList() {
		clickelement(DBLangLink);
		clickelement(DBLangPopUp);
	}

	/**
	 * To click on TSEE_Visibility link in LHS to navigate to TSEE_Visibility page
	 */
	public void navigateToSE_ContentManagement() {
		clickelement(SE_ContentManagement);
		System.out.println("Waiting for page to load**********");
		waitUntilLoad(driver);
	}

	/**
	 * For selecting/changing dashboard language in TSEE to specific language based
	 * on the country and language code
	 * 
	 * @param langCode
	 *            : Language code, date format to be in specific format for English
	 *            : en , german : de , spanish : es french : fr , italian : it ,
	 *            swedish : sv
	 * 
	 * @param countryCode
	 *            : Country code could be case sensitive, date format to be in
	 *            specific format for unitedStates : US , german : DE ,
	 *            spanish(Spain) : ES spanish(Mexico) : MX , french(France) : FR ,
	 *            french(Canada) : CA italian : IT , swedish : SE
	 * 
	 */
	public void select_DB_Lang_Link(String langCode, String contryCode) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String langConCode = langCode + "_" + contryCode;
		wait.until(ExpectedConditions.visibilityOf(DBLangLink));
		if (DBLangLink.isDisplayed()) {
			DB_LangList();
			switch (langConCode) {

			case "de_DE":
				try {
					selectDB_langDeutsch.click();
				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'Deutsch')]")).isDisplayed()) {
						break;
					}
				}
				break;

			case "en_US":
				try {
					selectDB_langEnglish.click();
				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'English')]")).isDisplayed()) {
						System.out.println("clicked selectDB_langEnglish's span");
						break;
					}
				}
				break;

			case "es_ES":
				try {
					selectDB_langSpanish_Spain.click();
				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'Espa�ol')]"))
							.isDisplayed()) {
						break;
					}
				}
				break;

			case "es_MX":
				try {
					selectDB_langSpanish_Mexico.click();
				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'Espa�ol')]"))
							.isDisplayed()) {
						break;
					}
				}
				break;

			case "fr_CA":
				try {
					selectDB_langFrench_Canada.click();
				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'Fran�ais')]"))
							.isDisplayed()) {
						break;
					}
				}
				break;

			case "fr_FR":
				try {
					selectDB_langFrench_France.click();
				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'Fran�ais')]"))
							.isDisplayed()) {
						break;
					}
				}
				break;

			case "it_IT":
				try {
					selectDB_langItalian.click();

				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'Italiano')]"))
							.isDisplayed()) {
						break;
					}
				}
				break;

			case "sv_SE":
				try {
					selectDB_langSwedish.click();
				} catch (Exception e) {
					if (driver.findElement(By.xpath("//i/following::span[contains(text(),'Svenska')]")).isDisplayed()) {
						break;
					}
				}
				break;

			default:
				System.out.println("Selected wrong Language Code or Contry Code please check and Execute once again");
			}
		}
	}

	/**
	 * To Click on Cancel Walkme Snippet
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
	 * To Click on Okay Notification PopUp
	 */
	public void clickNotificationPopUp() {
		JSWaiter.waitJQueryAngular();
		if (NotificationPopUp.isDisplayed()) {
			clickelement(NotificationPopUp);
		} else {
			System.out.println("No Notification Displayed");
		}
	}
	
	
	/** To click on SA_FrequentKeywords link in LHS to navigate to SA_FrequentKeywords page   */
	
	public void navigateToSE_Report() {
		wait.until(ExpectedConditions.visibilityOf(SE_Reports));
		scrollByElement(SE_Reports);
		clickelement(SE_Reports);
		waitUntilLoad(driver);
		
	}
	
	public void navigateToSE_Post() {
		wait.until(ExpectedConditions.visibilityOf(SE_Posts));
		scrollByElement(SE_Posts);
		clickelement(SE_Posts);
		waitUntilLoad(driver);
		
	}
	
	

}
