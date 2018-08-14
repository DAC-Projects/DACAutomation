package com.dac.main.POM_CF;

import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.DateFormats;

public class CampaignsPage extends BasePage{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public CampaignsPage(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 55);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//div[@class='btn-group']//a")
	private WebElement CreateCampaignBTN;
	
	@FindBy(xpath="(//li[@ng-repeat='pane in panes']//a)[1]")
	private WebElement ScheduledTab;
	
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
	
	@FindBy(xpath="//a[contains(@class,'btnEdit')]/span")
	private WebElement scheduledCampEditBTN;
	
	@FindBy(xpath="//b[text()='Close Preview']")
	private WebElement closePreviewBTN;
	
	@FindBy(xpath="//span[text()='Reschedule']")
	private WebElement scheduledCampReScheduleBTN;
	
	@FindBy(xpath="//button[contains(@class,'LivePreviewBtn')]")
	private WebElement scheduledCampLivePreviewBTN;
	
	@FindBy(xpath="//a[contains(@ng-click,'scheduleRemove')]/span")
	private WebElement scheduledCampDeleteBTN;
	
	@FindBy(className="editDraft")
	private WebElement acceptDelBTN;
	
	@FindBy(className="editDraft1")
	private WebElement cancelDelBTN;
	
	@FindBy(xpath=" //button[text()='OK']")
	private WebElement deleteCampConfirmBTN;
	
	@FindBy(xpath="//input[contains(@ng-model,'Active')]")
	private WebElement ProcessedCampaign_SearchBar;
	
	@FindBy(xpath="(//select[contains(@ng-model,'Active')])[1]")
	private WebElement Select_ProcessedCampaignType;
	
	@FindBy(xpath="(//select[contains(@ng-model,'Active')])[2]")
	private WebElement Select_ProcessedCampaignStatus;
	
	// ----------------------------------Draft Section ---------------------------------------
	
	@FindBy(xpath="(//li[@ng-repeat='pane in panes']//a)[2]")
	private WebElement DraftTab;
	
	@FindBy(xpath="//input[contains(@ng-model,'Draft')]")
	private WebElement DraftSearchBar;
	
	@FindBy(xpath="//*[contains(@ng-click,'draftRemove')]")
	private WebElement deleteDraftCamp;
	
	//------------------Processed campaign Table Data----------------------
	
	
	@FindBy(className="dataTables_empty")
	private WebElement emptyTable;
	
	@FindBy(id="reviewTable_info")
	private WebElement tableResult;
	
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
	
	@FindBy(xpath="//*[@ng-bind='headerMessage']")
	private WebElement subTittle_CampaignPage;
	
	//-----------------------------------------------------------
	
	@FindBy(name="campaigntype")
	private WebElement campaignType;
	
	private void waitTillLinksLoad() {
		wait.until(ExpectedConditions.visibilityOf(processedCampDetailsLink));
		wait.until(ExpectedConditions.visibilityOf(scheduledCampEditBTN));
	}

	public void click_CreateCampaignBTN() {
		
		wait.until(ExpectedConditions.visibilityOf(CreateCampaignBTN));
		waitTillLinksLoad();
		clickelement(CreateCampaignBTN, driver);
		wait.until(ExpectedConditions.visibilityOf(campaignType));
	}
	
	public void click_ScheduledTab() {
		scrollByElement(ScheduledTab, driver);
		waitTillLinksLoad();
		wait.until(ExpectedConditions.elementToBeClickable(ScheduledTab));
		clickelement(ScheduledTab, driver);
	}
	
	public void clickEditBTN() throws InterruptedException {
		waitTillLinksLoad();
		clickelement(scheduledCampEditBTN, driver);
		Thread.sleep(6000);
	}
	
	public void clickDeleteBTN() {
		waitTillLinksLoad();
		clickelement(scheduledCampDeleteBTN, driver);
		wait.until(ExpectedConditions.visibilityOf(acceptDelBTN));
		
	}
	
	public void clickDeleteAcceptBTN() throws InterruptedException {
		clickelement(acceptDelBTN, driver);
		wait.until(ExpectedConditions.visibilityOf(deleteCampConfirmBTN));
		clickelement(deleteCampConfirmBTN, driver);
		Thread.sleep(5000);
		
	}
	
	public void clickDeleteCancelBTN() throws InterruptedException {
		clickelement(cancelDelBTN, driver);
		Thread.sleep(4000);
	}
	
	public void clickReScheduleBTN() throws InterruptedException {
		waitTillLinksLoad();
		clickelement(scheduledCampReScheduleBTN, driver);
		
	}
	
	public void clickLivePreviewBTN() {
		waitTillLinksLoad();
		wait.until(ExpectedConditions.visibilityOf(scheduledCampLivePreviewBTN));
		if(scheduledCampLivePreviewBTN.isEnabled()) {
			clickelement(scheduledCampLivePreviewBTN, driver);
			wait.until(ExpectedConditions.visibilityOf(closePreviewBTN));
		}
		else {
			System.out.println("Live preview Button is Disabled");
		}
	}
	
	public void click_DraftTab() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(procCampCustActReportLink));
		Thread.sleep(2000);
		clickelement(DraftTab, driver);
	}
	
	public void search_ScheduledCampaign(String CampName) throws InterruptedException {
		waitTillLinksLoad();
		scrollByElement(ScheduledSearchBar, driver);
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
			//scrollByElement(DraftSearchBar, driver);
			DraftSearchBar.clear();
			DraftSearchBar.sendKeys(CampName);
			Thread.sleep(4000);
		}
		else {
			System.out.println("Please Navigate to Draft tab before serching for \"Drafted Campaign\" ");
		}
	}
	
	public void clickDeleteDraftCamp() {
		clickelement(deleteDraftCamp, driver);
		wait.until(ExpectedConditions.visibilityOf(acceptDelBTN));
		clickelement(acceptDelBTN, driver);
	}
	
	
	public void search_ProcessedCampaign(String CampName) throws InterruptedException {
		waitTillLinksLoad();
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
			waitTillLinksLoad();
			search_ScheduledCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Draft")) {
			search_DraftCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Processed")) {
			waitTillLinksLoad();
			search_ProcessedCampaign(CampName);
		}
		
		WebElement td1 = driver.findElement(By.xpath("//td[1]//span[contains(.,'"+CampName+"')]"));
		verifyText(td1, CampName);
		
	}
	
	public void verifyProcessedCampTableData(String CampName) {
		waitTillLinksLoad();
		WebElement td1 = driver.findElement(By.xpath("//td[1]//span[contains(.,'"+CampName+"')]"));
		verifyText(td1, CampName);
		
		String procCampEndDate = driver.findElement(By.xpath("//td[3]//span[contains(@ng-bind,'EndDateTime')]")).getText();
		verifyText(td1, CampName);
		
	}
	
	public void verifyCampTableData(String TabName, String CampName, String LocOrBrandName) throws InterruptedException {
		if(TabName.equalsIgnoreCase("Scheduled")) {
			waitTillLinksLoad();
			search_ScheduledCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Draft")) {
			search_DraftCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Processed")) {
			waitTillLinksLoad();
			search_ProcessedCampaign(CampName);
		}
		
		WebElement td1 = driver.findElement(By.xpath("//td[1]//span[contains(.,'"+CampName+"')]"));
		verifyText(td1, CampName);
		
		WebElement td2 = driver.findElement(By.xpath("//td[2]//span[contains(.,'"+LocOrBrandName+"')]"));
		verifyText(td2, LocOrBrandName);
	}
	
	//Release Date should be Start date
	public String getReleaseDateTime(String langCode, String contryCode) {
		WebElement dateNtime = driver.findElement(By.xpath("//td[4]//span[@class='ng-binding']"));
		Date startDate = new Date();
		String startDateFormat = DateFormats.dateFormat(langCode, contryCode).format(startDate);
		String tableReleaseDateNtime= dateNtime.getText();
		if(tableReleaseDateNtime.contains(startDateFormat)) {
			Assert.assertTrue(true, "Release date is same as Campaign Start Date");
		}
		else	Assert.fail();
		return tableReleaseDateNtime;
	}
	
	public void verifyLocOrBrandName(String TabName, String CampName, String LocOrBrandName) throws InterruptedException {
		if(TabName.equalsIgnoreCase("Scheduled")) {
			waitTillLinksLoad();
			search_ScheduledCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Draft")) {
			search_DraftCampaign(CampName);
		}
		else if(TabName.equalsIgnoreCase("Processed")) {
			waitTillLinksLoad();
			search_ProcessedCampaign(CampName);
		}
		
		WebElement td2 = driver.findElement(By.xpath("(//td//span[contains(.,'"+LocOrBrandName+"')])[2]"));
		verifyText(td2, CampName);
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method */
	public void clickDetailsLink() {
		waitTillLinksLoad();
		scrollByElement(processedCampSection, driver);
		clickelement(processedCampDetailsLink, driver);
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method 
	 * @throws InterruptedException */
	public void clickResponsesLink() throws InterruptedException {
		waitTillLinksLoad();
		scrollByElement(processedCampSection, driver);
		clickelement(processedCampResponsesLink, driver);
		try {
			wait.until(ExpectedConditions.visibilityOf(tableResult));
		}
		catch(Exception e) {
			wait.until(ExpectedConditions.visibilityOf(emptyTable));
		}
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method */
	public void clickReportsLink() {
		waitTillLinksLoad();
		scrollByElement(processedCampSection, driver);
		clickelement(processedCampReportsLink, driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnApply")));
	}
	
	/** 
	 * This method used to click Details link of Particular Processed campaign
	 * note:  Before calling this method you should invoke search_ProcessedCampaign method 	 */
	public void clickCustActReportLink() {
		waitTillLinksLoad();
		scrollByElement(processedCampSection, driver);
		wait.until(ExpectedConditions.visibilityOf(procCampCustActReportLink));
		clickelement(procCampCustActReportLink, driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customerActivityTable_info")));
	}
}
