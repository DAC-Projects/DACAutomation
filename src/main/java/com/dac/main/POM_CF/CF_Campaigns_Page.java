package com.dac.main.POM_CF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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

	@FindBy(xpath = "//input[@id='fw_scheduled_input']")
	private WebElement ScheduledSearchField;

	@FindBy(xpath = "//div[@class='input-group search-box-camp-process']//span[@class='input-group-addon']")
	private WebElement ScheduledSearchBtn;

	@FindBy(xpath = "//table[@id='tblCampaigns']")
	private WebElement ScheduledTable;

	@FindBy(xpath = "//table[@id='tblCampaigns']//tbody//tr")
	private List<WebElement> ScheduledTableRow;

	@FindBy(xpath = "//div[@class='dataTables_info' and @id='tblCampaigns_info']")
	private WebElement ScheduledTableEntries;

	@FindBy(xpath = "//div[@class='feedbackHeaderText']//span")
	private WebElement PreviewBanner;

	@FindBy(xpath = "//div[@class='livePreview_Body']")
	private WebElement PreviewBody;

	@FindBy(xpath = "//span[@class='livePreview_Signature']")
	private WebElement PreviewSign;

	@FindBy(xpath = "//button[@id='preview-step-header-2']")
	private WebElement FeedBackPage;

	@FindBy(xpath = "//span[@id='ratingmessage']")
	private WebElement RatingMessage;

	@FindBy(xpath = "//input[@id='optionalName']")
	private WebElement Name;

	@FindBy(xpath = "//*[@id='comment']")
	private WebElement Comments;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement checkbox;

	@FindBy(xpath = "//button[@class='btn btn-primary wizard-button wizard-button-blue Send_feedback']")
	private WebElement SendFeedBack;

	@FindBy(xpath = "//span[@class='livePreview_HighRatingMessage']")
	private WebElement HighRate;

	@FindBy(xpath = "//span[@class='livePreview_MidRatingMessage']")
	private WebElement MidRate;

	@FindBy(xpath = "//span[@class='livePreview_LowRatingMessage']")
	private WebElement LowRate;

	@FindBy(xpath = "//button[@id='prev-back-button']")
	private WebElement ExitPreview;

	@FindBy(xpath = "//button[@id='wizard-view-button']")
	private WebElement ViewCampaign;

	@FindBy(xpath = "//input[@id='brandname']")
	private WebElement brandName;

	@FindBy(xpath = "//input[@id='brandaddressline1']")
	private WebElement AddressLine1;

	@FindBy(xpath = "//input[@id='brandcity']")
	private WebElement BrandCity;

	@FindBy(xpath = "//input[@id='brandstate']")
	private WebElement BrandState;

	@FindBy(xpath = "//input[@id='brandpostalcode']")
	private WebElement BrandPost;

	@FindBy(xpath = "//input[@id='brandphonenumber']")
	private WebElement BrandPhone;

	@FindBy(xpath = "//input[@id='cbxGenerateCode']")
	private WebElement GenerateChkBox;

	@FindBy(xpath = "//div//h4[contains(text(),'Processed Campaigns')]")
	private WebElement ProcessedCampTitle;

	@FindBy(xpath = "//input[@id='fw_all_input']")
	private WebElement ProcessedSearch;

	@FindBy(xpath = "//table[@id='tblAllCampaigns']")
	private WebElement ProcessedTable;

	@FindBy(xpath = "//table[@id='tblAllCampaigns']")
	private WebElement AllProcessedTable;

	@FindBy(xpath = "//table[@id='tblActiveCampaigns']")
	private WebElement ActiveProcessedTable;

	@FindBy(xpath = "//table[@id='tblCompletedCampaigns']")
	private WebElement CompletedProcessedTable;

	@FindBy(xpath = "//table[@id='tblArchivedCampaigns']")
	private WebElement ArchivedProcessedTable;

	@FindBy(xpath = "//table[@id='tblAllCampaigns']//tbody//tr[@role='row']")
	private List<WebElement> ProcessedTableRow;

	@FindBy(xpath = "//div[@class='input-group search-box-campaign-grid']//span[@class='input-group-addon']")
	private WebElement ProcessedSearchBtn;

	@FindBy(xpath = "//div[@id='tblAllCampaigns_info']")
	private WebElement ProcessedEntries;

	@FindBy(xpath = "//input[@id='locSearch_input']")
	private WebElement MultiLocationSearch;

	@FindBy(xpath = "//label[@id='lbluploadEmail']")
	private WebElement UploadEmail;

	@FindBy(xpath = "//span[@id='chooseFile']")
	private WebElement UploadEmailLogo;

	@FindBy(xpath = "//*[@id='ddlCampaignSetup_all']")
	private WebElement AllFiltertype;

	@FindBy(xpath = "//*[@id='ddlCampaignType_all']")
	private WebElement AllFilterSetup;

	@FindBy(xpath = "//*[@id='ddlCampaignSetup']")
	private WebElement ActiveFiltertype;

	@FindBy(xpath = "//*[@id='ddlCampaignType']")
	private WebElement ActiveFilterSetup;

	@FindBy(xpath = "//*[@id='ddlCampaignSetup_completed']")
	private WebElement CompletedFiltertype;

	@FindBy(xpath = "//*[@id='ddlCampaignType_completed']")
	private WebElement CompletedFilterSetup;

	@FindBy(xpath = "//*[@id='ddlCampaignSetup_archived']")
	private WebElement ArchivedFiltertype;

	@FindBy(xpath = "//*[@id='ddlCampaignType_archived']")
	private WebElement ArchivedFilterSetup;

	@FindBy(xpath = "//table[@id='tblAllCampaigns']//tbody//tr//td[2]")
	private List<WebElement> Camptype;

	@FindBy(xpath = "//*[@href='/CampaignsGrid/All']")
	private WebElement AllTab;

	@FindBy(xpath = "//*[@href='/CampaignsGrid/Active']")
	private WebElement ActiveTab;

	@FindBy(xpath = "//*[@href='/CampaignsGrid/Completed']")
	private WebElement CompletedTab;

	@FindBy(xpath = "//*[@href='/CampaignsGrid/Archived']")
	private WebElement ArchivedTab;

	/*-------------------------Pagination Scheduled-----------------------*/
	@FindBy(xpath = "(//div[@id = 'tblCampaigns_paginate']//*[@class='pagination']//a)")
	private List<WebElement> pagination;

	@FindBy(xpath = "(//div[@id = 'tblCampaigns_paginate']//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "(//div[@id = 'tblCampaigns_paginate']//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;

	@FindBy(xpath = "(//div[@id = 'tblCampaigns_paginate']//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationLast;
	/*-------------------------Pagination-----------------------*/

	/*-------------------------Pagination Scheduled-----------------------*/
	@FindBy(xpath = "(//div[@id = 'tblAllCampaigns_paginate']//*[@class='pagination']//a)")
	private List<WebElement> paginationProcess;

	@FindBy(xpath = "(//div[@id = 'tblAllCampaigns_paginate']//*[@class='pagination']//a)[1]")
	private WebElement paginationPrevProcess;

	@FindBy(xpath = "(//div[@id = 'tblAllCampaigns_paginate']//*[@class='pagination']//a)[last()]")
	private WebElement paginationNextProcess;

	@FindBy(xpath = "(//div[@id = 'tblAllCampaigns_paginate']//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationLastProcess;
	/*-------------------------Pagination-----------------------*/

	Select selecttype, selectopt, selectlang, selecttime, selectSetup;
	ExcelHandler wb;
	SoftAssert soft = new SoftAssert();
	SimpleDateFormat Dateformat;

	/**
	 * To enter campaign information
	 * 
	 * @param Type
	 * @param Option
	 * @param Lang
	 * @param CamName
	 * @param CampDes
	 * @throws Exception
	 */
	public void CampaignInfo(String Type, String Option, String Lang, String CamName, String CampDes) throws Exception {
		time_Stamp = timeStamp();
		System.out.println("The current time stamp is :" + time_Stamp);
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
		CampName.sendKeys(CamName + time_Stamp);
		CampDescription.sendKeys(CampDes);
		BaseClass.addEvidence(CurrentState.getDriver(), "Test to write Campaign Information", "yes");
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
	 * @throws Exception
	 */
	public void CampaignSetUp(String Location, String Email, String Esender, String Esubject, String Ebanner,
			String EBody, String ESign) throws Exception {
		waitForElement(LocationDetails, 10);
		LocationDetails.sendKeys(Location);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//li[@class='ui-menu-item' and contains(text(),'" + Location + "')]")).click();
		EmailField.sendKeys(Email);
		Sender.clear();
		Sender.sendKeys(Esender);
		EmailSubject.clear();
		EmailSubject.sendKeys(Esubject);
		EmailBanner.clear();
		EmailBanner.sendKeys(Ebanner);
		EmailBody.sendKeys(EBody);
		EmailSignature.sendKeys(ESign);
		BaseClass.addEvidence(CurrentState.getDriver(), "Enter Setup Details", "yes");
		clickelement(NextBtn);
	}

	/**
	 * Enter Time into the campaign
	 * 
	 * @param time
	 * @throws Exception
	 */
	public void CampaignScheduling(String SheetName) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", SheetName);
		String time = wb.getCellValue(1, wb.seacrh_pattern("Time", 0).get(0).intValue());
		if (!time.equals("null")) {
			selecttime = new Select(DeploymentTime);
			System.out.println("The time is :" + time);
			selecttime.selectByVisibleText(time);
			clickelement(NextBtn);
		}
	}

	/**
	 * Enter details of Thank you page
	 * 
	 * @param Onestar
	 * @param Threestar
	 * @param Fivestar
	 * @throws Exception
	 */
	public void ThankyouSetUp(String Onestar, String Threestar, String Fivestar) throws Exception {
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
		BaseClass.addEvidence(CurrentState.getDriver(), "Test to Thankyou Page", "yes");
		clickelement(NextBtn);
	}

	/**
	 * Click on Submit Button
	 * 
	 * @throws Exception
	 */
	public void SummaryPage() throws Exception {
		clickelement(SubmitBtn);
		BaseClass.addEvidence(CurrentState.getDriver(), "Test to Submit the Summary Page", "yes");
		waitForElement(ViewCampaign, 10);
		scrollByElement(ViewCampaign);
		// ViewCampaign.click();
		action.moveToElement(ViewCampaign).click().build().perform();
		// action.click(ViewCampaign).build().perform();
		// clickelement(ViewCampaign);
		BaseClass.addEvidence(CurrentState.getDriver(), "Test to navigate to campaigns Page", "yes");
	}

	/**
	 * Details to enter in Campaign SetUp
	 * 
	 * @throws Exception
	 */
	public void ECampaignSetUp(int i) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailLocationCampaign");
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
	}

	/**
	 * Thank You Page details
	 * 
	 * @throws Exception
	 */
	public void ThankYouPage(String filename) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", filename); // "EmailLocationCampaign"
		String Onestar = wb.getCellValue(1, wb.seacrh_pattern("1Star Messaging", 0).get(0).intValue());
		System.out.println("One Two Star Message is : " + Onestar);
		String Threestar = wb.getCellValue(1, wb.seacrh_pattern("3Star Messaging", 0).get(0).intValue());
		System.out.println("One Two Star Message is : " + Threestar);
		String Fourstar = wb.getCellValue(1, wb.seacrh_pattern("4Star Messaging", 0).get(0).intValue());
		System.out.println("One Two Star Message is : " + Fourstar);
		ThankyouSetUp(Onestar, Threestar, Fourstar);
	}

	/**
	 * Search for the campaign created
	 * 
	 * @return
	 * @throws Exception
	 */
	public String SearchSchedule(String Filename) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", Filename);
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("CamName", 0).get(0).intValue());
		System.out.println("The Campaign Name is:" + CampaignName + time_Stamp);
		ScheduledSearchField.clear();
		ScheduledSearchField.sendKeys(CampaignName + time_Stamp);
		clickelement(ScheduledSearchBtn);
		return CampaignName + time_Stamp;
	}

	/**
	 * Verify preview schedule
	 * 
	 * @throws Exception
	 */
	public void PreviewSchedule(String Filename) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", Filename);
		waitForElement(ScheduledTable, 10);
		String CampaignName = SearchSchedule(Filename);
		System.out.println("The Campaign Name is :" + CampaignName);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (ScheduledTableEntries.isDisplayed()) {
			String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
			int page = Integer.parseInt(n);
			System.out.println("\n" + page);
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = ScheduledTableRow;
					int rows_count = rows_table.size();
					System.out.println("The total Number of Rows are :" + rows_count);
					for (int row = 0; row < rows_count; row++) {
						if (driver.findElement(By.xpath("//table[@id='tblCampaigns']//tbody//tr[" + (row + 1)
								+ "]//td[contains(text(),'" + CampaignName + "')]")).isDisplayed()) {
							WebElement PreviewButton = driver
									.findElement(By.xpath("//table[@id='tblCampaigns']//tbody//tr[" + (row + 1)
											+ "]//td[5]//div[2]//span//a[contains(@id,'preview')]"));
							wait.until(ExpectedConditions.visibilityOf(PreviewButton));
							if (PreviewButton.isEnabled()) {
								waitForElement(PreviewButton, 10);
								scrollByElement(PreviewButton);
								clickelement(PreviewButton);
								String EmailBanner = wb.getCellValue(1,
										wb.seacrh_pattern("Email Banner", 0).get(0).intValue());
								System.out.println("Email Banner is :" + EmailBanner);
								String EmailBody = wb.getCellValue(1,
										wb.seacrh_pattern("Email Body", 0).get(0).intValue());
								System.out.println("Email Body is :" + EmailBody);
								String EmailSignature = wb.getCellValue(1,
										wb.seacrh_pattern("Email Signature", 0).get(0).intValue());
								System.out.println("Email Signature is :" + EmailSignature);
								waitForElement(PreviewBanner, 10);
								String Banner = PreviewBanner.getText();
								System.out.println("Banner is :" + Banner);
								soft.assertEquals(Banner, EmailBanner);
								String Body = PreviewBody.getText();
								System.out.println("Body is :" + Body);
								soft.assertEquals(Body, EmailBody);
								String Signature = PreviewSign.getText();
								System.out.println("Signature is:" + Signature);
								soft.assertEquals(Signature, EmailSignature);
								BaseClass.addEvidence(driver, "Test to verify Preview Page", "yes");
								FeedBack();
								break Outer;
							} else {
								System.out.println("Preview Button not found");
							}
						} else {
							System.out.println("No Campaigns displayed");
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
			soft.assertAll();
		}
	}

	/**
	 * Verify Feedback Page
	 * 
	 * @throws Exception
	 */
	public void FeedBack() throws Exception {
		clickelement(FeedBackPage);
		wb = new ExcelHandler("./data/CF.xlsx", "EmailLocationCampaign");
		String StarRating = wb.getCellValue(1, wb.seacrh_pattern("Rating", 0).get(0).intValue());
		System.out.println("The star rating is :" + StarRating);
		String ReviewerName = wb.getCellValue(1, wb.seacrh_pattern("Name", 0).get(0).intValue());
		System.out.println("Reviewer Name is :" + ReviewerName);
		String ReviewerComments = wb.getCellValue(1, wb.seacrh_pattern("Comments", 0).get(0).intValue());
		System.out.println("Comment is :" + ReviewerComments);
		String Onestar = wb.getCellValue(1, wb.seacrh_pattern("1Star Messaging", 0).get(0).intValue());
		System.out.println("One Two Star Message is : " + Onestar);
		String Threestar = wb.getCellValue(1, wb.seacrh_pattern("3Star Messaging", 0).get(0).intValue());
		System.out.println("Three Star Message is : " + Threestar);
		String Fourstar = wb.getCellValue(1, wb.seacrh_pattern("4Star Messaging", 0).get(0).intValue());
		System.out.println("Four Star Message is : " + Fourstar);
		driver.findElement(By.xpath(
				"(//div[@id='ratingResponse']//span//input[@value='" + StarRating + "'])//following-sibling::label"))
				.click();
		String Message = RatingMessage.getText();
		if (StarRating.equals("1") || StarRating.equals("2")) {
			soft.assertEquals(Message, "Below expectations");
		} else if (StarRating.equals("3")) {
			soft.assertEquals(Message, "Room for improvement");
		} else if (StarRating.equals("4")) {
			soft.assertEquals(Message, "Yay! I'm a fan!");
		} else if (StarRating.equals("5")) {
			soft.assertEquals(Message, "Yay! I'm a fan!");
		}
		if (!ReviewerName.equals("null")) {
			clickelement(Name);
			Name.sendKeys(ReviewerName);
		}
		if (!ReviewerComments.equals("null")) {
			clickelement(Comments);
			Comments.sendKeys(ReviewerComments);
		}
		clickelement(checkbox);
		BaseClass.addEvidence(driver, "Test to verify Star Rating", "yes");
		clickelement(SendFeedBack);
		if (StarRating.equals("1") || StarRating.equals("2")) {
			String LowRating = LowRate.getText();
			System.out.println("The message is :" + LowRating);
			soft.assertEquals(LowRating, Onestar);
		} else if (StarRating.equals("3")) {
			String MidRating = MidRate.getText();
			System.out.println("The message is :" + MidRating);
			soft.assertEquals(MidRating, Threestar);
		} else if (StarRating.equals("4") || StarRating.equals("5")) {
			String HighRating = HighRate.getText();
			System.out.println("The message is :" + HighRating);
			soft.assertEquals(HighRating, Fourstar);
		}
		BaseClass.addEvidence(driver, "Test to verify Feedback Page", "yes");
		clickelement(ExitPreview);
		soft.assertTrue(driver.findElement(By.xpath("//div[@class='pageHead']")).isDisplayed(),
				"Page is not displayed");
	}

	/**
	 * Delete Campaign
	 * 
	 * @throws Exception
	 */
	public void DeleteCampaign(String Filename) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", Filename);
		waitForElement(ScheduledTable, 10);
		String CampaignName = SearchSchedule(Filename);
		System.out.println("Campaign Name is :" + CampaignName);
		if (ScheduledTableEntries.isDisplayed()) {
			String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
			int page = Integer.parseInt(n);
			System.out.println("\n" + page);
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = ScheduledTableRow;
					int rows_count = rows_table.size();
					System.out.println("The total Number of Rows are :" + rows_count);
					for (int row = 0; row < rows_count; row++) {
						if (driver.findElement(By.xpath("//table[@id='tblCampaigns']//tbody//tr[" + (row + 1)
								+ "]//td[contains(text(),'" + CampaignName + "')]")).isDisplayed()) {
							BaseClass.addEvidence(driver, "Test to search for the campaign", "yes");
							WebElement DeleteBtn = driver.findElement(By.xpath(
									"//table[@id='tblCampaigns']//tbody//tr[" + (row + 1) + "]//td[5]//div[3]//input"));
							wait.until(ExpectedConditions.visibilityOf(DeleteBtn));
							if (DeleteBtn.isEnabled()) {
								waitForElement(DeleteBtn, 10);
								scrollByElement(DeleteBtn);
								clickelement(DeleteBtn);
								WebElement confirm = driver.findElement(
										By.xpath("//div[@class='modal-footer']//button[@id='btnSchConfirm']"));
								JavascriptExecutor executor = (JavascriptExecutor) driver;
								executor.executeScript("arguments[0].click();", confirm);
								driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
								WebElement close = driver.findElement(By.xpath("(//button[@class='close'])[2]"));
								clickelement(close);
								BaseClass.addEvidence(driver, "Test to delete the campaign", "yes");
								break Outer;
							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		}
	}

	/**
	 * set value for reschedule campaign
	 * 
	 * @param i
	 * @throws Exception
	 */
	public void Reschedulecampname(int i, String FileName) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", FileName);
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("CamName", 0).get(0).intValue());
		wb.setCellValue(i, wb.seacrh_pattern("ReSchedule CampName", 0).get(0), CampaignName + time_Stamp);
	}

	/**
	 * Search for rescheduled campaign
	 * 
	 * @return
	 * @throws Exception
	 */
	public String SearchRESchedule() throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailLocationCampaign");
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("ReSchedule CampName", 0).get(0).intValue());
		System.out.println("The Campaign Name is:" + CampaignName);
		waitForElement(ScheduledSearchField, 10);
		ScheduledSearchField.sendKeys(CampaignName);
		clickelement(ScheduledSearchBtn);
		return CampaignName;
	}

	/**
	 * Reschedule campaign
	 * 
	 * @throws Exception
	 */
	public void REschedule(String Filename) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailLocationCampaign");
		waitForElement(ScheduledTable, 10);
		String CampaignName = SearchSchedule(Filename);
		System.out.println("Campaign Name is :" + CampaignName);
		if (ScheduledTableEntries.isDisplayed()) {
			String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
			int page = Integer.parseInt(n);
			System.out.println("\n" + page);
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = ScheduledTableRow;
					int rows_count = rows_table.size();
					System.out.println("The total Number of Rows are :" + rows_count);
					for (int row = 0; row < rows_count; row++) {
						if (driver.findElement(By.xpath("//table[@id='tblCampaigns']//tbody//tr[" + (row + 1)
								+ "]//td[contains(text(),'" + CampaignName + "')]")).isDisplayed()) {
							WebElement RescheduleBtn = driver.findElement(By.xpath(
									"//table[@id='tblCampaigns']//tbody//tr[" + (row + 1) + "]//td[5]//div[1]//input"));
							if (RescheduleBtn.isEnabled()) {
								scrollByElement(RescheduleBtn);
								clickelement(RescheduleBtn);
								clickelement(NextBtn);
								String ESender = wb.getCellValue(1, wb.seacrh_pattern("Sender", 0).get(0).intValue());
								System.out.println("The sender is :" + Sender);
								Sender.sendKeys(ESender);
								clickelement(NextBtn);
								break Outer;
							} else {
								System.out.println("Reschedule Btn is disabled");
							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		}
	}

	/**
	 * Set reschedule date and time
	 */
	public void SetDateTimeReschedule() {
		clickelement(NextBtn);
		clickelement(SubmitBtn);
	}

	/**
	 * verify rescheduled campaign
	 * 
	 * @throws Exception
	 */
	public void verifyRescheduleCampaign() throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailLocationCampaign");
		waitForElement(ScheduledTable, 10);
		String CampaignName = SearchRESchedule();
		System.out.println("Campaign Name is :" + CampaignName);
		if (ScheduledTableEntries.isDisplayed()) {
			String n = driver
					.findElement(
							By.xpath("(//div[@id = 'tblCampaigns_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("\n" + page);
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = ScheduledTableRow;
					int rows_count = rows_table.size();
					System.out.println("The total Number of Rows are :" + rows_count);
					for (int row = 0; row < rows_count; row++) {
						if (driver.findElement(By.xpath("//table[@id='tblCampaigns']//tbody//tr[@role='row']["
								+ (row + 1) + "]//td[contains(text(),'" + CampaignName + "')]")).isDisplayed()) {
							WebElement TimeCreated = driver.findElement(
									By.xpath("//table[@id='tblCampaigns']//tbody//tr[" + (row + 1) + "]//td[3]"));
							String DateCreated = TimeCreated.getText();
							System.out.println("Date Created is :" + DateCreated);
							soft.assertEquals(DateCreated, time_Stamp);
							break Outer;
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
			soft.assertAll();
		}
	}

	/**
	 * Create campaign email brand
	 * 
	 * @param BrandName
	 * @param Address1
	 * @param City
	 * @param State
	 * @param PostCode
	 * @param PhNum
	 * @param Reciepent
	 * @param sender
	 * @param Subject
	 * @param Banner
	 * @param Body
	 * @param Sign
	 * @throws Exception
	 */
	public void BrandcampSetUp(String BrandName, String Address1, String City, String State, String PostCode,
			String PhNum, String Reciepent, String sender, String Subject, String Banner, String Body, String Sign)
			throws Exception {

		waitForElement(brandName, 10);
		clickelement(brandName);
		brandName.sendKeys(BrandName);
		clickelement(AddressLine1);
		AddressLine1.sendKeys(Address1);
		clickelement(BrandCity);
		BrandCity.sendKeys(City);
		clickelement(BrandState);
		BrandState.sendKeys(State);
		clickelement(BrandPost);
		BrandPost.sendKeys(PostCode);
		clickelement(BrandPhone);
		BrandPhone.sendKeys(PhNum);
		clickelement(EmailField);
		EmailField.sendKeys(Reciepent);
		clickelement(Sender);
		Sender.sendKeys(sender);
		clickelement(UploadPic);
		System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
		String autoITExecutable = ".\\UploadFile.exe " + System.getProperty("user.dir") + "\\filesToUpload\\logo.jpeg";
		Runtime.getRuntime().exec(autoITExecutable);
		clickelement(EmailSubject);
		EmailSubject.clear();
		EmailSubject.sendKeys(Subject);
		clickelement(EmailBanner);
		EmailBanner.clear();
		EmailBanner.sendKeys(Banner);
		clickelement(EmailBody);
		EmailBody.sendKeys(Body);
		clickelement(EmailSignature);
		EmailSignature.sendKeys(Sign);
		clickelement(NextBtn);
	}

	/**
	 * Reschedule or Edit Email Brand Campaign Name to set in XL
	 * 
	 * @param i
	 * @throws Exception
	 */
	public void RescheduleEBrandcampname(int i) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailBrandCampaign");
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("CamName", 0).get(0).intValue());
		wb.setCellValue(i, wb.seacrh_pattern("ReSchedule CampName", 0).get(0), CampaignName + time_Stamp);
	}

	/**
	 * Test to enter details of setup campaign for General Review Link with Location
	 * 
	 * @param Location
	 * @throws Exception
	 */
	public void GRLocationSetUp(String Location) throws Exception {
		waitForElement(LocationDetails, 10);
		LocationDetails.click();
		LocationDetails.sendKeys(Location);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//li[@class='ui-menu-item' and contains(text(),'" + Location + "')]")).click();
		clickelement(GenerateChkBox);
		clickelement(UploadPic);
		String autoITExecutable = ".\\UploadFile.exe " + System.getProperty("user.dir") + "\\filesToUpload\\logo.jpeg";
		Runtime.getRuntime().exec(autoITExecutable);
		Thread.sleep(10000);
		BaseClass.addEvidence(CurrentState.getDriver(), "Test to enter details of GRLLocation Setup Page", "yes");
		clickelement(NextBtn);
	}

	/**
	 * Test to enter details of setup campaign for General Review Link with Location
	 * 
	 * @throws Exception
	 */
	public void GRLocationScheduling() throws Exception {
		waitForElement(NextBtn, 10);
		BaseClass.addEvidence(CurrentState.getDriver(), "GRLocation Scheduling Page", "yes");
		scrollByElement(NextBtn);
		clickelement(NextBtn);
	}

	public void Processedcampname(int i, String FileName) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", FileName);
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("CamName", 0).get(0).intValue());
		wb.setCellValue(i, wb.seacrh_pattern("ProcessedCampaign Name", 0).get(0), CampaignName + time_Stamp);
	}

	public String SearchProcessedCampaign(String FileName) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", FileName);
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("ProcessedCampaign Name", 0).get(0).intValue());
		System.out.println("Campaign Name is :" + CampaignName);
		scrollByElement(ProcessedSearch);
		ProcessedSearch.click();
		ProcessedSearch.clear();
		ProcessedSearch.sendKeys(CampaignName);
		clickelement(ProcessedSearchBtn);
		return CampaignName;
	}

	public void ProcessedCampaign(String FileName) throws Exception {
		waitForElement(ProcessedTable, 10);
		String CampaignName = SearchProcessedCampaign(FileName);
		System.out.println("Campaign Name is :" + CampaignName);
		if (ProcessedEntries.isDisplayed()) {
			String n = paginationLastProcess.getText();
			int page = Integer.parseInt(n);
			System.out.println("\n" + page);
			Outer: if (paginationNextProcess.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = ProcessedTableRow;
					int rows_count = rows_table.size();
					System.out.println("The total Number of Rows are :" + rows_count);
					for (int row = 0; row <= rows_count - 1; row++) {
						if (driver
								.findElement(By.xpath("//table[@id='tblAllCampaigns']//tbody//tr[@role='row']["
										+ (row + 1) + "]//td[contains(text(),'" + CampaignName + "')]"))
								.isDisplayed()) {
							System.out.println("Campaign Displayed");
							break Outer;
						}
					}
					if (paginationNextProcess.isEnabled()) {
						scrollByElement(paginationNextProcess);
						paginationNextProcess.click();
						Thread.sleep(4000);
					}
				}
			}
		}
	}

	public void GRLBrandSetUp(String BrandName, String Address1, String City, String State, String PostCode,
			String PhoneNumber) throws Exception {
		clickelement(brandName);
		brandName.clear();
		brandName.sendKeys(BrandName);
		clickelement(AddressLine1);
		AddressLine1.clear();
		AddressLine1.sendKeys(Address1);
		clickelement(BrandCity);
		BrandCity.clear();
		BrandCity.sendKeys(City);
		clickelement(BrandState);
		BrandState.clear();
		BrandState.sendKeys(State);
		clickelement(BrandPost);
		BrandPost.clear();
		BrandPost.sendKeys(PostCode);
		clickelement(BrandPhone);
		BrandPhone.clear();
		BrandPhone.sendKeys(PhoneNumber);
		clickelement(GenerateChkBox);
		clickelement(UploadPic);
		String autoITExecutable = ".\\UploadFile.exe " + System.getProperty("user.dir") + "\\filesToUpload\\logo.jpeg";
		Runtime.getRuntime().exec(autoITExecutable);
		Thread.sleep(10000);
		BaseClass.addEvidence(CurrentState.getDriver(), "Test to enter details of GRLLocation Setup Page", "yes");
		clickelement(NextBtn);
	}

	public void EmailMultiLocSetUp(String sender, String Subject, String Banner, String Body, String Sign)
			throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailMultiLocation");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String Location = wb.getCellValue(i, wb.seacrh_pattern("Locations", 0).get(0).intValue());
			String[] LocationNumbers = Location.split(",");
			int size = LocationNumbers.length;
			System.out.println(" The size of the list :" + size);
			for (int k = 0; k <= size - 1; k++) {
				clickelement(MultiLocationSearch);
				MultiLocationSearch.clear();
				String Loc = LocationNumbers[k];
				System.out.println("The Location Number is :" + Loc);
				MultiLocationSearch.sendKeys(Loc);
				if (driver
						.findElement(
								By.xpath("//table[@id='tblLocations']//tbody//tr//td[contains(text(),'" + Loc + "')]"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//table[@id='tblLocations']//tbody//tr//td[contains(text(),'" + Loc
							+ "')]//preceding-sibling::td//input")).click();
				}
			}
			clickelement(UploadEmail);
			String autoITExecutable = ".\\UploadFile.exe " + System.getProperty("user.dir")
					+ "\\filesToUpload\\EmailTemplate-MLC.xlsx";
			Runtime.getRuntime().exec(autoITExecutable);
			Thread.sleep(10000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String msg = jse.executeScript("return document.getElementById('campaignemailaddress').value").toString();
			System.out.println("The message is :" + msg);
			Assert.assertTrue(msg.contains("email addresses have been uploaded"), msg + "doesn't contains expected");
			clickelement(Sender);
			Sender.clear();
			Sender.sendKeys(sender);
			clickelement(UploadEmailLogo);
			String autoITExecutable1 = ".\\UploadFile.exe " + System.getProperty("user.dir")
					+ "\\filesToUpload\\logo.jpeg";
			Runtime.getRuntime().exec(autoITExecutable1);
			Thread.sleep(10000);
			clickelement(EmailSubject);
			EmailSubject.clear();
			EmailSubject.sendKeys(Subject);
			clickelement(EmailBanner);
			EmailBanner.clear();
			EmailBanner.sendKeys(Banner);
			clickelement(EmailBody);
			EmailBody.clear();
			EmailBody.sendKeys(Body);
			clickelement(EmailSignature);
			EmailSignature.clear();
			EmailSignature.sendKeys(Sign);
			clickelement(NextBtn);
		}
	}

	/**
	 * Reschedule or Edit Email Brand Campaign Name to set in XL
	 * 
	 * @param i
	 * @throws Exception
	 */
	public void RescheduleEMultiLoccampname() throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailMultiLocation");
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("CamName", 0).get(0).intValue());
		wb.setCellValue(1, wb.seacrh_pattern("Reschedule CampName", 0).get(0), CampaignName + time_Stamp);
	}

	public void GRLMultiLocSetUp() throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "GRLMultiLocation");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String Location = wb.getCellValue(i, wb.seacrh_pattern("Locations", 0).get(0).intValue());
			String[] LocationNumbers = Location.split(",");
			int size = LocationNumbers.length;
			System.out.println(" The size of the list :" + size);
			for (int k = 0; k <= size - 1; k++) {
				clickelement(MultiLocationSearch);
				MultiLocationSearch.clear();
				String Loc = LocationNumbers[k];
				System.out.println("The Location Number is :" + Loc);
				MultiLocationSearch.sendKeys(Loc);
				if (driver
						.findElement(
								By.xpath("//table[@id='tblLocations']//tbody//tr//td[contains(text(),'" + Loc + "')]"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//table[@id='tblLocations']//tbody//tr//td[contains(text(),'" + Loc
							+ "')]//preceding-sibling::td//input")).click();
				}
			}
			clickelement(GenerateChkBox);
			clickelement(UploadEmailLogo);
			String autoITExecutable1 = ".\\UploadFile.exe " + System.getProperty("user.dir")
					+ "\\filesToUpload\\logo.jpeg";
			Runtime.getRuntime().exec(autoITExecutable1);
			Thread.sleep(10000);
			clickelement(NextBtn);
		}
	}

	public void VerifyProcessedCampaign(String CampName, String FileName) throws Exception {

		wb = new ExcelHandler("./data/CF.xlsx", FileName);
		String CampaignName = wb.getCellValue(1, wb.seacrh_pattern("ProcessedCampaign Name", 0).get(0).intValue());
		System.out.println("Campaign Name is:" + CampaignName);

	}

	public void SelectAllFilter(String FilterType, String FilterSetUp) {
		if (!FilterType.equals("null")) {
			selecttype = new Select(AllFiltertype);
			selecttype.selectByVisibleText(FilterType);
		}
		if (!FilterSetUp.equals("null")) {
			selectSetup = new Select(AllFilterSetup);
			selectSetup.selectByVisibleText(FilterSetUp);
		}
	}

	public void SelectActiveFilter(String FilterType, String FilterSetUp) {
		if (!FilterType.equals("null")) {
			selecttype = new Select(ActiveFiltertype);
			selecttype.selectByVisibleText(FilterType);
		}
		if (!FilterSetUp.equals("null")) {
			selectSetup = new Select(ActiveFilterSetup);
			selectSetup.selectByVisibleText(FilterSetUp);
		}
	}

	public void SelectCompletedFilter(String FilterType, String FilterSetUp) {
		if (!FilterType.equals("null")) {
			selecttype = new Select(CompletedFiltertype);
			selecttype.selectByVisibleText(FilterType);
		}
		if (!FilterSetUp.equals("null")) {
			selectSetup = new Select(CompletedFilterSetup);
			selectSetup.selectByVisibleText(FilterSetUp);
		}
	}

	public void SelectArchivedFilter(String FilterType, String FilterSetUp) {
		if (!FilterType.equals("null")) {
			selecttype = new Select(ArchivedFiltertype);
			selecttype.selectByVisibleText(FilterType);
		}
		if (!FilterSetUp.equals("null")) {
			selectSetup = new Select(ArchivedFilterSetup);
			selectSetup.selectByVisibleText(FilterSetUp);
		}
	}

	public void verifyAllTabDatawithFilters() {
		waitForElement(AllTab, 10);
		scrollByElement(AllTab);
		clickelement(AllTab);
		waitForElement(AllProcessedTable, 10);
		scrollByElement(AllProcessedTable);
	}

	public void verifyActiveTabDatawithFilters() {
		waitForElement(ActiveTab, 10);
		scrollByElement(ActiveTab);
		clickelement(ActiveTab);
		waitForElement(ActiveProcessedTable, 10);
		scrollByElement(ActiveProcessedTable);
	}

	public void verifyCompletedTabDatawithFilters() {
		waitForElement(CompletedTab, 10);
		scrollByElement(CompletedTab);
		clickelement(CompletedTab);
		waitForElement(CompletedProcessedTable, 10);
		scrollByElement(CompletedProcessedTable);
	}

	public void verifyArchivedTabDatawithFilters() {
		waitForElement(ArchivedTab, 10);
		scrollByElement(ArchivedTab);
		clickelement(ArchivedTab);
		waitForElement(ArchivedProcessedTable, 10);
		scrollByElement(ArchivedProcessedTable);
	}

	public void verifyTypeFilter(String FilterType, String FilterSetUp, String id, String tblid, SoftAssert soft)
			throws InterruptedException {
		Thread.sleep(10000);
		List<WebElement> s = driver.findElements(By.xpath("//table[@id='" + tblid + "']//tbody//tr[@role='row']"));
		int totalrows = s.size();
		for (int i = 1; i <= totalrows; i++) {
			String Type = driver
					.findElement(By.xpath("//table[@id='" + tblid + "']//tbody//tr[@role='row'][" + i + "]//td[2]"))
					.getText();
			System.out.println("The Type is :" + Type);
			if (FilterSetUp.equals("Multi-location")) {
				String Setup = driver
						.findElement(By.xpath("//table[@id='" + tblid + "']//tbody//tr[@role='row'][" + i + "]//td[3]"))
						.getText();
				System.out.println("The Setup is :" + Setup);
				soft.assertEquals(Setup, "Multi-location Campaign");
				clicksecondpage(id);
				Thread.sleep(10000);
			}
			soft.assertEquals(Type, FilterType);
		}
	}

	public void verifyDateActive(String id, String tblid) throws ParseException, InterruptedException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println("Current Date Format is :" + var);
		Dateformat = new SimpleDateFormat(var);
		String TodayDate = ((JavascriptExecutor) driver)
				.executeScript("return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		Date finalDate = Dateformat.parse(TodayDate);
		System.out.println("The Date is :" + finalDate);
		List<WebElement> s = driver.findElements(By.xpath("//table[@id='" + tblid + "']//tbody//tr[@role='row']"));
		int totalrows = s.size();
		for (int i = 1; i <= totalrows; i++) {
			String Udate = driver
					.findElement(By.xpath("//table[@id='" + tblid + "']//tbody//tr[@role='row'][" + i + "]//td[4]"))
					.getText();
			String result = Udate.substring(Udate.indexOf(0) + 1, Udate.indexOf("M") - 8).trim();
			Date UIDate = Dateformat.parse(result);
			System.out.println("UI Date is :" + UIDate);
			soft.assertTrue(UIDate.after(finalDate), "Date is before current date");
			clicksecondpage(id);
			Thread.sleep(10000);
		}
		soft.assertAll();
	}

	public void verifyDateCompleted(String id, String tblid) throws ParseException, InterruptedException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		Dateformat = new SimpleDateFormat(var);
		String TodayDate = ((JavascriptExecutor) driver)
				.executeScript("return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		Date finalDate = Dateformat.parse(TodayDate);
		System.out.println("The Date is :" + finalDate);
		List<WebElement> s = driver
				.findElements(By.xpath("//table[@id='tblCompletedCampaigns']//tbody//tr[@role='row']"));
		int totalrows = s.size();
		for (int i = 1; i <= totalrows; i++) {
			String Udate = driver
					.findElement(By.xpath("//table[@id='" + tblid + "']//tbody//tr[@role='row'][" + i + "]//td[4]"))
					.getText();
			String result = Udate.substring(Udate.indexOf(0) + 1, Udate.indexOf("M") - 8).trim();
			Date UIDate = Dateformat.parse(result);
			System.out.println("UI Date is :" + UIDate);
			soft.assertTrue(UIDate.before(finalDate), "Date is after current date");
			clicksecondpage(id);
			Thread.sleep(10000);
		}
		soft.assertAll();
	}

	public void clicksecondpage(String id) {
		if (driver.findElement(By.xpath("(//div[@id = '" + id + "']//*[@class='pagination']//a)[contains(text(),'2')]"))
				.isDisplayed()) {
			clickelement(driver.findElement(
					By.xpath("(//div[@id = '" + id + "']//*[@class='pagination']//a)[contains(text(),'2')]")));
		}
	}

	public void CompletedArchiveData(SoftAssert soft, String FileName) throws Exception {
		waitForElement(CompletedTab, 10);
		clickelement(CompletedTab);
		waitForElement(CompletedProcessedTable, 10);
		int FinalEntries;
		int NumberOfEntries = NumOfentries(driver.findElement(By.xpath("//*[@id='tblCompletedCampaigns_info']")));
		System.out.println("The Number of entries before Archived :" + NumberOfEntries);
		List<WebElement> s = driver
				.findElements(By.xpath("//table[@id='tblCompletedCampaigns']//tbody//tr[@role='row']"));
		int totalrows = s.size();
		Outer: for (int i = 1; i <= totalrows; i++) {
			if (driver.findElement(By.xpath(
					"//table[@id='tblCompletedCampaigns']//tbody//tr[@role='row']//td[5]//span//label[contains(text(),'ARCHIVE')]["
							+ i + "]"))
					.isDisplayed()) {
				String name = driver.findElement(By.xpath(
						"((//table[@id='tblCompletedCampaigns']//tbody//tr[@role='row']//td[5]//span//label[contains(text(),'ARCHIVE')]["
								+ i + "])/../../../../preceding-sibling::td)[" + i + "]"))
						.getText();
				clickelement(driver.findElement(By.xpath(
						"//table[@id='tblCompletedCampaigns']//tbody//tr[@role='row']//td[5]//span//label[contains(text(),'ARCHIVE')]["
								+ i + "]")));
				WebElement close = driver.findElement(By.xpath("(//button[@class='close'])[7]"));
				clickelement(close);
				System.out.println("The Campaign Name is :" + name);
				wb = new ExcelHandler("./data/CF.xlsx", FileName);
				wb.setCellValue(1, wb.seacrh_pattern("", 0).get(0), name);
				FinalEntries = NumOfentries(driver.findElement(By.xpath("//*[@id='tblCompletedCampaigns_info']")));
				System.out.println("The Number of entries before Archived :" + FinalEntries);
				soft.assertEquals(FinalEntries, NumberOfEntries - 1);
				break Outer;
			}
		}
	}

	public void ArchivedData(String FileName, SoftAssert soft) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", FileName);
		String Name = wb.getCellValue(1, wb.seacrh_pattern("Camp Name", 0).get(0).intValue());
		System.out.println("The Name is :" + Name);
		waitForElement(ArchivedTab, 10);
		clickelement(ArchivedTab);
		waitForElement(ArchivedProcessedTable, 10);
		WebElement SearchFileld = driver.findElement(By.xpath("//input[@id='fw_archived_input']"));
		SearchFileld.click();
		SearchFileld.clear();
		SearchFileld.sendKeys(Name);
		WebElement SearchBtn = driver.findElement(By.xpath("//div//input[@id='fw_all_input']/../span"));
		SearchBtn.click();
		String CampName = driver.findElement(By.xpath(
				"(//table[@id='tblArchivedCampaigns']//tbody//tr[@role='row'])[1]//td[contains(@class,'sorting')]"))
				.getText();
		System.out.println("Column Text is : " + CampName);
		if (CampName.equalsIgnoreCase(Name)) {
			soft.assertTrue(true, "Campaign Name is not equal");
		}
	}
}
