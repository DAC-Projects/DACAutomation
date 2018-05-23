package com.dac.main;

import java.util.concurrent.TimeUnit;

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

public class CampaignsPage extends BasePage{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public CampaignsPage(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//div[@class='btn-group']//a")
	private WebElement CreateCampaignBTN;
	
	@FindBy(xpath="(//li[@ng-repeat='pane in panes']//a)[1]")
	private WebElement ScheduledTab;
	
	@FindBy(xpath="(//li[@ng-repeat='pane in panes']//a)[2]")
	private WebElement DraftTab;
	
	@FindBy(xpath="//input[contains(@ng-model,'Scheduled')]")
	private WebElement ScheduledSearchBar;
	
	@FindBy(xpath="//span[@ng-bind='scheduledCampaign.Name']")
	private WebElement scheduledCampName;
	
	@FindBy(xpath="//span[contains(@ng-if,'scheduledCampaign.Location')]")
	private WebElement scheduledCampLocName;
	
	@FindBy(xpath="//span[@ng-bind='scheduledCampaign.BrandName']")
	private WebElement scheduledCampBrnadName;
	
	@FindBy(xpath="//span[contains(text(),' Multi-location Campaign ')]")
	private WebElement scheduledCampMLC;
	
	@FindBy(xpath="//span[contains(@ng-bind,'Campaign.CreatedDate')]")
	private WebElement scheduledCampCreationDate;
	
	@FindBy(xpath="//span[contains(@ng-bind,'StartDateTime')]")
	private WebElement scheduledCampStartDate;
	
	@FindBy(xpath="//span[text()='Edit']")
	private WebElement scheduledCampEditBTN;
	
	@FindBy(xpath="//b[text()='Close Preview']")
	private WebElement closePreviewBTN;
	
	@FindBy(xpath="//span[text()='Reschedule']")
	private WebElement scheduledCampReScheduleBTN;
	
	@FindBy(xpath="//button[contains(@class,'LivePreviewBtn')]")
	private WebElement scheduledCampLivePreviewBTN;
	
	@FindBy(xpath="//a[contains(@ng-click,'scheduleRemove')]/span")
	private WebElement scheduledCampDeleteBTN;
	
	@FindBy(xpath="//button[text()='Yes']")
	private WebElement scheduledCampAcceptDelBTN;
	
	@FindBy(xpath="//button[contains(text(),'Cancel')]")
	private WebElement scheduledCampCancelDelBTN;
	
	@FindBy(xpath=" //button[text()='OK']")
	private WebElement deleteCampConfirmBTN;
	
	@FindBy(xpath="//input[contains(@ng-model,'Draft')]")
	private WebElement DraftSearchBar;
	
	@FindBy(xpath="//input[contains(@ng-model,'Active')]")
	private WebElement ProcessedCampaign_SearchBar;
	
	@FindBy(xpath="(//select[contains(@ng-model,'Active')])[1]")
	private WebElement Select_ProcessedCampaignType;
	
	@FindBy(xpath="(//select[contains(@ng-model,'Active')])[2]")
	private WebElement Select_ProcessedCampaignStatus;
	
	//------------------Processed campaign Table Data----------------------
	
	@FindBy(xpath="//h4[@class='text-primary']")
	private WebElement processedCampSection;
	
	@FindBy(xpath="//span[@ng-bind='activeCampaign.Name']")
	private WebElement processedCampName;

	@FindBy(xpath="//span[@ng-bind='activeCampaign.BrandName']")
	private WebElement processedCampLocOrBrandName;
	
	@FindBy(xpath="//span[contains(@ng-if,'activeCampaign.MLC')]")
	private WebElement processedCampMLC;
	
	@FindBy(xpath="//span[contains(@ng-bind,'activeCampaign.fullEndDate')]")
	private WebElement processedCampEndDate;
	
	@FindBy(xpath="(//td[contains(@ng-if,'activeCampaign.MLC')]/a)[1]")
	private WebElement processedCampDetailsLink;
	
	@FindBy(xpath="(//td[contains(@ng-if,'activeCampaign.MLC')]/a)[2]")
	private WebElement processedCampResponsesLink;
	
	@FindBy(xpath="(//td[contains(@ng-if,'activeCampaign.MLC')]/a)[3]")
	private WebElement processedCampReportsLink;
	
	@FindBy(xpath="(//td[contains(@ng-if,'activeCampaign.MLC')]/a)[4]")
	private WebElement procCampCustActReportLink;
	
	@FindBy(xpath="//span[text()='Archive']")
	private WebElement processedCampArchive;

	@FindBy(xpath="//span[text()='Unarchive']")
	private WebElement processedCampUnArchive;
	

	public void click_CreateCampaignBTN() throws InterruptedException {

		 wait.until(ExpectedConditions.visibilityOf(CreateCampaignBTN));
		Thread.sleep(4000);
		//CreateCampaignBTN.click();
		action.moveToElement(CreateCampaignBTN).click(CreateCampaignBTN).perform();
	}
	
	public void click_ScheduledTab() {
		scrollByElement(ScheduledTab, driver);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ScheduledTab));
			ScheduledTab.click();
		}
		catch(Exception e) {
			
		}
	}
	
	public void clickEditBTN() throws InterruptedException {
		if(scheduledCampEditBTN.isEnabled() & scheduledCampEditBTN.isDisplayed()) {
			scheduledCampEditBTN.click();
			Thread.sleep(6000);
		}
		else {
			System.out.println();
		}
	}
	
	public void clickDeleteBTN() throws InterruptedException {
		action.moveToElement(scheduledCampDeleteBTN).click(scheduledCampDeleteBTN).perform();
		Thread.sleep(2000);
	}
	
	public void clickDeleteAcceptBTN() throws InterruptedException {
		scheduledCampAcceptDelBTN.click();
		Thread.sleep(6000);
		deleteCampConfirmBTN.click();
		Thread.sleep(5000);
	}
	
	public void clickDeleteCancelBTN() throws InterruptedException {
		scheduledCampCancelDelBTN.click();
		Thread.sleep(4000);
	}
	
	public void clickReScheduleBTN() throws InterruptedException {
		scheduledCampReScheduleBTN.click();
		
	}
	
	public void clickLivePreviewBTN() {
		if(scheduledCampLivePreviewBTN.isEnabled()) {
			scheduledCampLivePreviewBTN.click();
			wait.until(ExpectedConditions.visibilityOf(closePreviewBTN));
			//Thread.sleep(6000);			
		}
		else {
			System.out.println("Live preview Button is Disabled");
		}
	}
	
	public void click_DraftTab() {
		scrollByElement(DraftTab, driver);
		DraftTab.click();
	}
	
	public void search_ScheduledCampaign(String CampName) throws InterruptedException {
		
		scrollByElement(ScheduledSearchBar, driver);
		//click_ScheduledTab();
		ScheduledSearchBar.clear();
		ScheduledSearchBar.sendKeys(CampName);
		Thread.sleep(3000);	
	}
	
	/** 
	 * This method should use after invoking of click_DraftTab()
	 * @throws InterruptedException */
	public void search_DraftCampaign(String CampName) throws InterruptedException {
		click_DraftTab();
		if(DraftSearchBar.isDisplayed()) {
			scrollByElement(DraftSearchBar, driver);
			DraftSearchBar.clear();
			DraftSearchBar.sendKeys(CampName);
			Thread.sleep(4000);
		}
		else {
			System.out.println("Please Navigate to Draft tab before serching for \"Drafted Campaign\" ");
		}
	}
	
	public void search_ProcessedCampaign(String CampName) throws InterruptedException {
		//Thread.sleep(4000);
		scrollByElement(processedCampSection, driver);
		ProcessedCampaign_SearchBar.clear();
		ProcessedCampaign_SearchBar.sendKeys(CampName);
		Thread.sleep(4000);
	}
	
	/**
	 * To validate the Searched Campaign displayed in respective Section or not
	 * @throws InterruptedException 
	 * @TabName  : Scheduled/Draft/Processed
	 * @CampName : Campaign Name to search		*/
	public void verifyCampName(String TabName, String CampName) throws InterruptedException {
		if(TabName.equalsIgnoreCase("Scheduled")) {
			search_ScheduledCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Draft")) {
			search_DraftCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Processed")) {
			search_ProcessedCampaign(CampName);
		}
		
		WebElement e = driver.findElement(By.xpath("//span[contains(.,'"+CampName+"')]"));
		verifyText(e, CampName);
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method */
	public void clickDetailsLink() {
		scrollByElement(processedCampSection, driver);
		processedCampDetailsLink.click();
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method */
	public void clickResponsesLink() {
		scrollByElement(processedCampSection, driver);
		try {
			processedCampResponsesLink.click();
		}
		catch(Exception e) {
			action.moveToElement(processedCampResponsesLink).click().perform();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnReviewsExport")));
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method */
	public void clickReportsLink() {
		scrollByElement(processedCampSection, driver);
		try {
			processedCampReportsLink.click();
		}
		catch(Exception e) {
			action.moveToElement(processedCampReportsLink).click().perform();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnApply")));
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method 	 */
	public void clickCustActReportLink() {
		scrollByElement(processedCampSection, driver);
		wait.until(ExpectedConditions.visibilityOf(procCampCustActReportLink));
		try {
			procCampCustActReportLink.click();
		}
		catch(Exception e) {
			action.moveToElement(procCampCustActReportLink).click().perform();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customerActivityTable_info")));
	}
}
