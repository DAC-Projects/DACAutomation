package com.dac.main.POM_CF;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

public class CampaignLivePreviewPage extends BasePage{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public CampaignLivePreviewPage(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*@FindBy(xpath="//span[contains(@ng-bind,'IntroBanner')]")
	private WebElement emailViewBannerText;*/
	
	@FindBy(xpath="//span[contains(@ng-bind,'IntroBanner')]")
	private WebElement emailViewBannerText;
	
	@FindBy(xpath="//div[contains(@ng-bind-html,'Body')]")
	private WebElement emailViewBodyText;
	
	@FindBy(xpath="//span[contains(@ng-bind-html,'Signature')]")
	private WebElement emailViewSignatureText;
	
	@FindBy(xpath="//button[text()='1']")
	private WebElement emailViewPageBTN;
	
	@FindBy(xpath="//a[contains(@ng-click,'emailEvent')]")
	private WebElement giveAReviewBTN;
	
	@FindBy(xpath="//button[text()='2']")
	private WebElement feedBackPageBTN;
	
	@FindBy(xpath="//button[contains(@class,'feedback-submit')]/b")
	private WebElement sendFeedBackBTN;
	
	@FindBy(xpath="//span[text()='3']")
	private WebElement thankYouPageBTN;
	
	@FindBy(xpath="//b[text()='Close Preview']")
	private WebElement closePreviewBTN;
	
	public void verifyemailViewData() {
		clickemailViewBTN();
		String[] emailviewdata = {emailViewBannerText.getText(), emailViewBodyText.getText(), emailViewSignatureText.getText() };
		String[] storedpreviewData = {BaseTest_CF.campBannerNew, BaseTest_CF.campBodyCopyNew, BaseTest_CF.campSignatureNew};
		for(int i=0; i<3;i++) {
			System.out.println(emailviewdata[i]);
			System.out.println(storedpreviewData[i]);
			if(emailviewdata[i].equalsIgnoreCase(storedpreviewData[i])) {
				Assert.assertEquals(emailviewdata[i], storedpreviewData[i]);
			}
			else {
				Assert.fail();
			}
		}
	}
	
	public void clickemailViewBTN() {
		scrollByElement(emailViewPageBTN, driver);
		emailViewPageBTN.click();
		wait.until(ExpectedConditions.visibilityOf(giveAReviewBTN));
	}
	
	public void clickfeedBackBTN() {
		scrollByElement(feedBackPageBTN, driver);
		action.moveToElement(feedBackPageBTN).click(feedBackPageBTN).perform();
		//feedBackPageBTN.click();
		wait.until(ExpectedConditions.visibilityOf(sendFeedBackBTN));
	}
	
	public void clickthankYouBTN() {
		scrollByElement(thankYouPageBTN, driver);
		thankYouPageBTN.click();
	}
	
	public void clickClosePreviewBTN() {
		scrollByElement(closePreviewBTN, driver);
		action.moveToElement(closePreviewBTN).click(closePreviewBTN).perform();
		//closePreviewBTN.click();	
	}
	
	/**
	 * Give a Review Button Text for different Languages FYI..
	 * Spanish LA : Dar una calificación*/
	public void verifyGiveAReviewBTNText(String eText) {
		verifyText(giveAReviewBTN, eText);
	}
	
	/**
	 * Feed Back button text for different Languages FYI...
	 * Spanish LA : Enviar Comentario*/
	public void verifyFeedBackBTNText(String eText) {
		verifyText(sendFeedBackBTN, eText);
	}
}
