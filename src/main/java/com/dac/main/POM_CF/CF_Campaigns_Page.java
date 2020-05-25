package com.dac.main.POM_CF;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CF_Campaigns_Page extends CF_abstractMethods {

	public CF_Campaigns_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	CF_Campaigns_Page data;
	
	
	@FindBy(xpath = "//a[@href='/Dashboard/CampaignSteps']//span[2]")
	private WebElement CreateCampaignLink;

	@FindBy(xpath = "//input[@class='search']")
	private WebElement CampaignDD;

	@FindBy(xpath = "//div[@class='menu transition visible']//div[contains(text(),'Create New Campaign')]")
	private WebElement CreateCamp;

	@FindBy(xpath = "//select[@id='ddlCampaignType']")
	private WebElement CampType;

	@FindBy(xpath = "//select[@id='ddlSetupOption']")
	private WebElement CampOption;

	@FindBy(xpath = "//select[@id='ddlCampaignLanguage']")
	private WebElement CampLang;

	@FindBy(xpath = "//input[@id='txtCampaignName']")
	private WebElement CampName;

	@FindBy(xpath = "//textarea[@id='txtAreaDescription']")
	private WebElement CampDescription;

	@FindBy(xpath = "//button[@id='wizard-next-button']")
	private WebElement NextBtn;

	@FindBy(xpath = "//button[@id='wizard-draft-button']")
	private WebElement SaveDraft;

	@FindBy(xpath = "//span[@id='lblMessage']")
	private WebElement SaveMsg;

	@FindBy(xpath = "//input[@id='locationSearch']")
	private WebElement LocationDetails;

	@FindBy(xpath = "//textarea[@id='campaignemailaddress']")
	private WebElement EmailField;

	@FindBy(xpath = "//input[@id='senderName']")
	private WebElement Sender;

	@FindBy(xpath = "//span[@id='chooseFile']")
	private WebElement UploadPic;

	@FindBy(xpath = "//input[@id='subject']")
	private WebElement EmailSubject;

	@FindBy(xpath = "//input[@id='introbanner']")
	private WebElement EmailBanner;

	@FindBy(xpath = "//textarea[@id='body']")
	private WebElement EmailBody;

	@FindBy(xpath = "//textarea[@id='signature']")
	private WebElement EmailSignature;

	@FindBy(xpath = "//button[@id='wizard-livepreview-button']")
	private WebElement Livepreview;

	@FindBy(xpath = "//input[@id='campStartDt']")
	private WebElement CampStart;

	@FindBy(xpath = "//input[@id='campEndDt']")
	private WebElement CampEnd;

	@FindBy(xpath = "//select[@id='ddlTime']")
	private WebElement DeploymentTime;

	@FindBy(xpath = "//textarea[@id='lowratingmessage']")
	private WebElement OneStar;

	@FindBy(xpath = "//textarea[@id='midratingmessage']")
	private WebElement ThreeStar;

	@FindBy(xpath = "//textarea[@id='highratingmessage']")
	private WebElement FiveStar;

	@FindBy(xpath = "//button[@id='wizard-submit-button']")
	private WebElement SubmitBtn;

	Select selecttype, selectopt, selectlang, selecttime;
	ExcelHandler wb;

	/**
	 * To enter campaign information
	 * 
	 * @param Type
	 * @param Option
	 * @param Lang
	 * @param CamName
	 * @param CampDes
	 */
	public void CampaignInfo(String Type, String Option, String Lang, String CamName, String CampDes) {
		waitForElement(CreateCampaignLink, 10);
		clickelement(CreateCampaignLink);
		waitForElement(CampaignDD, 10);
		clickelement(CampaignDD);
		clickelement(CreateCamp);
		selecttype = new Select(CampType);
		selecttype.selectByVisibleText(Type);
		selectopt = new Select(CampOption);
		selectopt.selectByVisibleText(Option);
		selectlang = new Select(CampLang);
		selectlang.selectByVisibleText(Lang);
		CampName.sendKeys(CamName);
		CampDescription.sendKeys(CampDes);
		clickelement(NextBtn);
	}

	/**
	 * To enter Email Setup Details
	 * 
	 * @param Location
	 * @param Email
	 * @param Esender
	 * @param Esubject
	 * @param Ebanner
	 * @param EBody
	 * @param ESign
	 */
	public void CampaignSetUp(String Location, String Email, String Esender, String Esubject, String Ebanner,
			String EBody, String ESign) {
		waitForElement(LocationDetails, 10);
		LocationDetails.sendKeys(Location);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//li[@class='ui-menu-item' and contains(text(),'"+Location+"')]")).click();
		EmailField.sendKeys(Email);
		Sender.sendKeys(Esender);
		EmailSubject.sendKeys(Esubject);
		EmailBanner.sendKeys(Ebanner);
		EmailBody.sendKeys(EBody);
		EmailSignature.sendKeys(ESign);
		clickelement(NextBtn);
	}

	public void CampaignScheduling(String time) {
		selecttime = new Select(DeploymentTime);
		selecttime.selectByVisibleText(time);
		clickelement(NextBtn);
	}

	public void ThankyouSetUp(String Onestar, String Threestar, String Fivestar) {
		if (!Onestar.equalsIgnoreCase("null")) {
			clickelement(OneStar);
			OneStar.clear();
			OneStar.sendKeys(Onestar);
		}
		if (!Threestar.equalsIgnoreCase("null")) {
			clickelement(ThreeStar);
			ThreeStar.clear();
			ThreeStar.sendKeys(Threestar);
		}
		if (!Fivestar.equalsIgnoreCase("null")) {
			clickelement(FiveStar);
			FiveStar.clear();
			FiveStar.sendKeys(Fivestar);
		}
		clickelement(NextBtn);
	}

	public void SummaryPage() {
		clickelement(SubmitBtn);
	}
	
	public void ECampaignSetUp() throws Exception {
			wb = new ExcelHandler("./data/CF.xlsx", "CreateCampaign");
			for (int i = 1; i <= wb.getRowCount(); i++) {
				String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
				System.out.println("The Location is :" + Location);
				String Recipent = wb.getCellValue(i, wb.seacrh_pattern("Reciepents", 0).get(0).intValue());
				System.out.println("The Reciepents are :" + Recipent);
				String Sender = wb.getCellValue(i, wb.seacrh_pattern("Sender", 0).get(0).intValue());
				System.out.println("The sender is :" + Sender);
				String EmailSubject = wb.getCellValue(i, wb.seacrh_pattern("Email Subject", 0).get(0).intValue());
				System.out.println("The email subject is :" + EmailSubject);
				String EmailBanner = wb.getCellValue(i, wb.seacrh_pattern("Email Banner", 0).get(0).intValue());
				System.out.println("Email Banner is :" + EmailBanner);
				String EmailBody = wb.getCellValue(i, wb.seacrh_pattern("Email Body", 0).get(0).intValue());
				System.out.println("Email Body is :" + EmailBody);
				String EmailSignature = wb.getCellValue(i, wb.seacrh_pattern("Email Signature", 0).get(0).intValue());
				System.out.println("Email Signature is :" + EmailSignature);
				CampaignSetUp(Location, Recipent, Sender, EmailSubject, EmailBanner, EmailBody, EmailSignature);
				BaseClass.addEvidence(CurrentState.getDriver(), "Enter Setup Details", "yes");
			}
		}

		public void ThankYouPage() throws Exception {
			wb = new ExcelHandler("./data/CF.xlsx", "CreateCampaign");
			String Onestar = wb.getCellValue(1, wb.seacrh_pattern("1Star Messaging", 0).get(0).intValue());
			System.out.println("One Two Star Message is : " + Onestar);
			String Threestar = wb.getCellValue(1, wb.seacrh_pattern("3Star Messaging", 0).get(0).intValue());
			System.out.println("One Two Star Message is : " + Threestar);
			String Fourstar = wb.getCellValue(1, wb.seacrh_pattern("4Star Messaging", 0).get(0).intValue());
			System.out.println("One Two Star Message is : " + Fourstar);
			ThankyouSetUp(Onestar, Threestar, Fourstar);
			BaseClass.addEvidence(CurrentState.getDriver(), "Test to Thankyou Page", "yes");
		}
}
