package com.dac.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CampaignsPage extends BasePage{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public CampaignsPage(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
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
	
	@FindBy(xpath="//input[contains(@ng-model,'Draft')]")
	private WebElement DraftSearchBar;
	
	@FindBy(xpath="//input[contains(@ng-model,'Active')]")
	private WebElement ProcessedCampaign_SearchBar;
	
	@FindBy(xpath="(//select[contains(@ng-model,'Active')])[1]")
	private WebElement Select_ProcessedCampaignType;
	
	@FindBy(xpath="(//select[contains(@ng-model,'Active')])[2]")
	private WebElement Select_ProcessedCampaignStatus;
	

	/** Use Thread.sleep atleast for 5000 millisec before invoking this method 
	 * @throws InterruptedException */
	public void click_CreateCampaignBTN() throws InterruptedException {

		Thread.sleep(5000);
		CreateCampaignBTN.click();
		//action.moveToElement(CreateCampaignBTN).click(CreateCampaignBTN).perform();
	}
	
	public void click_ScheduledTab() {
		
		ScheduledTab.click();
	}
	
	public void click_DraftTab() {
		DraftTab.click();
	}
	
	public void search_ScheduledCampaign(String CampName) {
		
		click_ScheduledTab();
		ScheduledSearchBar.sendKeys(CampName);
		//Thread.sleep(1000);	
	}
	
	public void search_DraftCampaign(String CampName) {
		
		click_DraftTab();
		DraftSearchBar.sendKeys(CampName);
		//Thread.sleep(1000);
	}
	
	public void search_ProcessedCampaign(String CampName) {
		
		ProcessedCampaign_SearchBar.sendKeys(CampName);
		//Thread.sleep(1000);
	}
	
	public void verifyCampName(String TabName, String CampName) {
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
}
