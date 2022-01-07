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
import resources.CurrentState;

public class TPSEE_Settings_Mousehover extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	String text;

	@FindBy(xpath = "//li[contains(@class,'settings-dropdown-cog')]")
	private WebElement SettingsIcon;

	@FindBy(xpath = "//a[@href='/SocialMedia/Configuration/']//span")
	private WebElement Connections;

	@FindBy(xpath = "//a[@href='/SocialMedia/Configuration/']")
	private WebElement Connectionstext;

	@FindBy(xpath = "//a[@href='/SocialMedia/ManageBrands']//span")
	private WebElement Brands;

	@FindBy(xpath = "//a[@href='/SocialMedia/ManageBrands']")
	private WebElement Brandstext;

	@FindBy(xpath = "//a[@href='/Settings/LocalRankPlusConfiguration']//span")
	private WebElement LocalRankPlus;

	@FindBy(xpath = "//a[@href='/Settings/LocalRankPlusConfiguration']")
	private WebElement LocalRankPlustext;

	@FindBy(xpath = "//a[@href='/Dashboard/AllGroups/']//span")
	private WebElement AllGroups;

	@FindBy(xpath = "//a[@href='/Dashboard/AllGroups/']")
	private WebElement AllGroupstext;

	@FindBy(xpath = "//a[@href='/Dashboard/ReportSiteOrder/']//span")
	private WebElement SiteOrder;

	@FindBy(xpath = "//a[@href='/Dashboard/ReportSiteOrder/']")
	private WebElement SiteOrdertext;

	@FindBy(xpath = "//a[@href='/Dashboard/ReviewsNotification/']//span")
	private WebElement ReviewNotification;

	@FindBy(xpath = "//a[@href='/Dashboard/ReviewsNotification/']")
	private WebElement ReviewNotificationtext;

	@FindBy(xpath = "//a[@href='/Dashboard/ESRFrequency/']//span")
	private WebElement ESR;

	@FindBy(xpath = "//a[@href='/Dashboard/ESRFrequency/']")
	private WebElement ESRtext;

	@FindBy(xpath = "//a[@href='/Dashboard/LocalReportsScoreChange/']//span")
	private WebElement LocalReportScoreChange;

	@FindBy(xpath = "//a[@href='/Dashboard/LocalReportsScoreChange/']")
	private WebElement LocalReportScoreChangetext;

	@FindBy(xpath = "//a[@href='/Dashboard/CfNotifications/']//span")
	private WebElement CustomerFeedBack;

	@FindBy(xpath = "//a[@href='/Dashboard/CfNotifications/']")
	private WebElement CustomerFeedBacktext;

	public TPSEE_Settings_Mousehover(WebDriver driver) {
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
	 * to verify mouse hover text under settings
	 * 
	 * @throws Exception
	 */
	public void verifyMousehoverText() throws Exception {
		action.moveToElement(SettingsIcon).click().build().perform();
		try {
			if (Connections.isDisplayed()) {
				action.moveToElement(Connections).perform();
				text = Connectionstext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to manage your connections");
			} else {
				System.out.println("Connections not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connections not enabled");
		}
		try {
			if (Brands.isDisplayed()) {
				action.moveToElement(Brands).perform();
				text = Brandstext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to manage your brands / others");
			} else {
				System.out.println("Brands not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Brands not enabled");
		}
		try {
			if (LocalRankPlus.isDisplayed()) {
				action.moveToElement(LocalRankPlus).perform();
				text = LocalRankPlustext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertTrue(text.contains("Click here to configure settings for your Local Rank"),
						"does not contain " + text);
			} else {
				System.out.println("LocalRankPlus id not anabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LocalRankPlus id not anabled");
		}
		try {
			if (AllGroups.isDisplayed()) {
				action.moveToElement(AllGroups).perform();
				text = AllGroupstext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to view all groupings");
			} else {
				System.out.println("All Groups not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("All Groups not enabled");
		}
		try {
			if (SiteOrder.isDisplayed()) {
				action.moveToElement(SiteOrder).perform();
				text = SiteOrdertext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to change your Site Order");
			} else {
				System.out.println("Site Order not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Site Order not enabled");
		}
		try {
			if (ReviewNotification.isDisplayed()) {
				action.moveToElement(ReviewNotification).perform();
				text = ReviewNotificationtext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to set review notifications");
			} else {
				System.out.println("RN not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("RN not enabled");
		}
		try {
			if (ESR.isDisplayed()) {
				action.moveToElement(ESR).perform();
				text = ESRtext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to set executive summary report frequency");
			} else {
				System.out.println("ESR not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ESR not enabled");
		}
		try {
			if (LocalReportScoreChange.isDisplayed()) {
				action.moveToElement(LocalReportScoreChange).perform();
				text = LocalReportScoreChangetext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to set local reports score change notifications");
			} else {
				System.out.println("Local Reports Score Change not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Local Reports Score Change not enabled");
		}
		try {
			if (CustomerFeedBack.isDisplayed()) {
				action.moveToElement(CustomerFeedBack).perform();
				text = CustomerFeedBacktext.getAttribute("data-original-title");
				BaseClass.addEvidence(CurrentState.getDriver(), "Test to verify mousehover text", "yes");
				System.out.println("The text is : " + text);
				soft.assertEquals(text, "Click here to set customer feedback notifications");
			} else {
				System.out.println("Customer Feedback is not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Customer Feedback is not enabled");
		}
		soft.assertAll();
	}

}
