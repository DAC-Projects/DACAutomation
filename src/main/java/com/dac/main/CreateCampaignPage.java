package com.dac.main;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.util.List;

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

public class CreateCampaignPage extends BasePage{

	WebDriver driver;
	Select select;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	public CreateCampaignPage(WebDriver driver) throws AWTException {
		
		wait=new WebDriverWait(driver, 20);
		this.driver = driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="campaigntype")
	private WebElement campaignType;
	
	@FindBy(name="campaignLanguage")
	private WebElement campaignLang;
	
	@FindBy(name="campaignName")
	private WebElement campaignName;
	
	@FindBy(xpath="//input[@ng-model='BrandName']")
	private WebElement campaignBrandName;
	
	@FindBy(id="CampaignLocationAddress")
	private WebElement campaignLocTB;
	
	@FindBy(xpath="//ul[@role='listbox']/li/a")
	private List<WebElement> campaignLocListBox;
	
	@FindBy(name="description")
	private WebElement campaignDescription;
	
	@FindBy(xpath="//div[@title='upload']")
	private WebElement uploadCampaignLogo;
	
	@FindBy(name="sendername")
	private WebElement campaignSenderName;
	
	@FindBy(name="subject")
	private WebElement campaignSubject;
	
	@FindBy(name="introbanner")
	private WebElement campaignIntroBanner;
	
	@FindBy(name="body")
	private WebElement campaignBodyCopy;
	
	@FindBy(name="signature")
	private WebElement campaignSignature;
	
	// --------------- Contact info section -----------------------
	
	@FindBy(xpath="//input[@ng-model='BrandAddressLine1']")
	private WebElement contactAddressLine1;
	
	@FindBy(xpath="//input[@ng-model='BrandAddressLine2']")
	private WebElement contactAddressLine2;
	
	@FindBy(xpath="//input[@ng-model='BrandCity']")
	private WebElement contactInfoBrandCity;
	
	@FindBy(xpath="//input[@ng-model='BrandStProv']")
	private WebElement contactInfoBrandStProv;
	
	@FindBy(xpath="//input[@ng-model='BrandZipPostalCode']")
	private WebElement contactInfoZipCode;
	
	@FindBy(xpath="//input[@ng-model='BrandPhoneNumber']")
	private WebElement contactInfoPhoneNumber;
	
	//---------------------------------------------------------------
	
	@FindBy(name="startdate")
	private WebElement scheduledStartDate;
	
	@FindBy(name="time")
	private WebElement scheduledTime;
	
	@FindBy(id="emailTemplate")
	private WebElement downloadEmailTemplate;
	
	@FindBy(xpath="(//*[@name='campaignemailaddress'])[1]")
	private WebElement toFieldTB;
	
	@FindBy(xpath="//input[@ng-model='file']")
	private WebElement uploadEmailTemplate;
	
	@FindBy(xpath="//button[@class='draftBtn']//span")
	private WebElement saveDraftBTN;
	
	@FindBy(xpath="//button[@class='scheduleBtn']//span")
	private WebElement createCampaignBTN;
	
	@FindBy(xpath="//button[contains(@class,'cancelBtn')]")
	private WebElement campaignCancelBTN;
	
	@FindBy(className="editDraft")
	private WebElement viewAllCampaignBTN;
	
	@FindBy(className="editDraft1")
	private WebElement continueEditBTN;
	
	//-----------------Tool Tip's ----------------
	
	@FindBy(xpath="(//i[@class='fa fa-info-circle'])[1]")
	private WebElement existCampTT;
	
	@FindBy(xpath="(//i[@class='fa fa-info-circle'])[2]")
	private WebElement uploadLogoTT;
	
	@FindBy(xpath="(//i[@class='fa fa-info-circle'])[3]")
	private WebElement personalizationTT;
	
	@FindBy(xpath="(//i[@class='fa fa-info-circle'])[4]")
	private WebElement contactInfoTT;
	
	@FindBy(xpath="(//i[@class='fa fa-info-circle'])[5]")
	private WebElement timeTT;
	
	@FindBy(xpath="(//i[@class='fa fa-info-circle'])[6]")
	private WebElement campEndDateTT;
	
	@FindBy(className="popover-content")
	private WebElement toolTipText;
	
	//-------------------------Mandatory Field Error Messages------------------------
	
	@FindBy(xpath="//div[@ng-show='campaignValidation']//span")
	private WebElement campValidationErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'Campaign exists')]/div")
	private WebElement campNameExistErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'campaignName')]/div")
	private WebElement campNameRequiredErrMsg;
	
	@FindBy(xpath="//div[@ng-show='brandNameRequire']/div")
	private WebElement brandNameReqErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'sendername')]/div")
	private WebElement campSenderName;
	

	
	
	/** indexes for 
	 * 
	 * @param campTypeIndex 
	 * Location Index : 1
	 * Brand Index    : 2
	 * MLC Index      : 3	
	 * @throws InterruptedException */
	public void selectCampType(int campTypeIndex) throws InterruptedException {
		Thread.sleep(5000);
		while(true) {
			if(campaignType.isDisplayed()) {
				select = new Select(campaignType);
				select.selectByIndex(campTypeIndex);
				break;
			}	
		}
	}
	
	
	/** indexes of campaign languages 
	 * 
	 * English        : 1	   French (Canada) : 2	  French (France) : 3	
	 * German         : 4	   Italian		   : 5	  Spanish (Mexico): 6
	 * Spanish(Spain) : 7      Swedish         : 7							*/
	public void selectCampLang(int CampLangIndex) {
		
		select = new Select(campaignLang);
		select.selectByIndex(CampLangIndex);
	}
	
	public void setCampaignName(String campName) {
		
		scrollByElement(campaignName, driver);
		campaignName.sendKeys(campName);
	}
	
	/** 
	 * This method used to select the Location for the Campaign Type : Location
	 * @locIndex : To select particular location from selected location name contains @locName based on Index.
	 * @locName  : To check the list box of Locations contains this text.	
	 * 
	 * if location */
	public void selectCampaignLoc(int locIndex,String locName) {
		
			campaignLocTB.sendKeys(locName);
			int index=0;
			for(index=0;index<campaignLocListBox.size();index++) {
				System.out.println("Location List: ");
				System.out.println(campaignLocListBox.get(index).getText());
			}
			if(index==0) {
				System.out.println("Selected "+locName+" and "+locIndex+" not matches with list of Locations");
			}
			else {
				campaignLocListBox.get(locIndex).click();				
			}
	}
	
	public void setCampaignBrandName(String campBrandName) {
		
		scrollByElement(campaignBrandName, driver);
		campaignBrandName.sendKeys(campBrandName);
	}
	
	public void setCampDescr(String campDescription) {
		
		campaignDescription.sendKeys(campDescription);
	}
	
	public void setSenderName(String campSenderName) {
		
		scrollByElement(campaignSenderName, driver);
		campaignSenderName.sendKeys(campSenderName);
	}
	
	public void setCampSubject(String campSubject) {
		
		scrollByElement(campaignSubject, driver);
		campaignSubject.sendKeys(campSubject);
	}
	
	public void setCampBanner(String campIntroBanner) {
		campaignIntroBanner.sendKeys(campIntroBanner);
	}
	
	public void setCampBodyCopy(String campBodyCopy) {
		campaignBodyCopy.sendKeys(campBodyCopy);
	}
	
	/** 
	 * @campSignature : To enter the campaign Signature data into Signature Text Field   */
	public void setCampSignature(String campSignature) {
		scrollByElement(campaignSignature, driver);
		campaignSignature.sendKeys(campSignature);
	}
	
	public void setContactInfo(String addressL1, String city, String contactInfoSTProv, String zipCode, String phoneNum, String... addressL2) {
		
		scrollByElement(contactAddressLine1, driver);
		contactAddressLine1.sendKeys(addressL1);
		contactAddressLine2.sendKeys(addressL2);
		contactInfoBrandCity.sendKeys(city);
		contactInfoBrandStProv.sendKeys(contactInfoSTProv);
		contactInfoZipCode.sendKeys(zipCode);
		contactInfoPhoneNumber.sendKeys(phoneNum);
	}
	
	public void setScheduledStartDate(String month_MM, String date_DD, String year_YYYY) {
		
		scrollByElement(scheduledStartDate, driver);
		scheduledStartDate.sendKeys(month_MM+"/"+date_DD+"/"+year_YYYY);
	}
	
	/** TimeValue_AM_PM could be in the format of "hh:mm AM/PM" */
	public void setCampaignTime(String TimeValue_AM_PM) {
		
		scrollByElement(scheduledTime, driver);
		select = new Select(scheduledTime);
		select.selectByVisibleText(TimeValue_AM_PM);
	}
	
	public void downloadCampEmailTemplate() {
		
		scrollByElement(downloadEmailTemplate, driver);
		downloadEmailTemplate.click();

	}
	
	public void uploadCampEmailTemplate(String fileName, String extension) {
		
		scrollByElement(uploadEmailTemplate, driver);
		uploadEmailTemplate.click();	
		uploadFile(fileName, extension);
	}
	
	public void clickSaveDraft() {
		scrollByElement(saveDraftBTN, driver);
		saveDraftBTN.click();
	}
	
	public void clickCreateCampBTN() {
		scrollByElement(createCampaignBTN, driver);
		createCampaignBTN.click();
	}
	
	public void clickViewAllCampaignBTN() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(viewAllCampaignBTN).click(viewAllCampaignBTN).perform();
	}
	
	public void clickContinueEditBTN() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(continueEditBTN).click(continueEditBTN).perform();
	}
	
	public void uploadLogo(String fileName, String extension) {
		scrollByElement(uploadCampaignLogo, driver);
		uploadCampaignLogo.click();
		uploadFile(fileName, extension);
	}
	
	//---------------------Handling Tool Tip's of Create New Cammpaign Page-----------------
	
	public void verifyExistCampToolTipText(String eText) {
		
		scrollByElement(existCampTT, driver);
		action.moveToElement(existCampTT).perform();
		verifyText(toolTipText, eText);
	}
	
	public void verifyUploadLogoToolTipText(String eText) {
		
		scrollByElement(uploadLogoTT, driver);
		action.moveToElement(uploadLogoTT).perform();
		verifyText(toolTipText, eText);
	}
	
	public void verifyPersonalizationToolTipText(String eText) {
		
		scrollByElement(personalizationTT, driver);
		action.moveToElement(personalizationTT).perform();
		verifyText(toolTipText, eText);
	}

	public void verifyContactInfoToolTipText(String eText) {
	
		scrollByElement(contactInfoTT, driver);
		action.moveToElement(contactInfoTT).perform();
		verifyText(toolTipText, eText);
	}

	public void verifyTimeToolTipText(String eText) {
	
		scrollByElement(timeTT, driver);
		action.moveToElement(timeTT).perform();
		verifyText(toolTipText, eText);
	}

	public void verifyCampEndDateToolTipText(String eText) {
	
		scrollByElement(campEndDateTT, driver);
		action.moveToElement(campEndDateTT).perform();
		verifyText(toolTipText, eText);
	}
	
	/** 
	 * To enter the email id's into To Field for Location and Brand campaign
	 * @email : e-mail id to send the created campaign and more than 1 email id's seperated with comma(,) */
	public void setToField(String email) {
		toFieldTB.sendKeys(email);
	}
	
	public void verifyErrMsg() {
		
	}
}
