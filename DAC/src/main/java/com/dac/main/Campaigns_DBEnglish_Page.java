package com.dac.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Campaigns_DBEnglish_Page extends BasePage{

	WebDriver driver;
	
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
	
	public void click_CreateCampaignBTN() {
		CreateCampaignBTN.click();
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
