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

public class CreateNewCampaignPage extends BasePage{

	WebDriver driver;
	Select select;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	public CreateNewCampaignPage(WebDriver driver) throws AWTException {
		
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
	private WebElement campaignBrandNameTB;
	
	@FindBy(id="CampaignLocationAddress")
	private WebElement campaignLocTB;
	
	@FindBy(xpath="//ul[@role='listbox']/li/a")
	private List<WebElement> campaignLocListBox;
	
	@FindBy(name="description")
	private WebElement campaignDescriptionTB;
	
	@FindBy(name="sendername")
	private WebElement campaignSenderName;

	@FindBy(xpath="//div[@title='upload']")
	private WebElement uploadCampaignLogo;
	
	@FindBy(xpath="//a[@ng-show='removeIcon']")
	private WebElement removeLogoBTN;
	
	@FindBy(name="subject")
	private WebElement campaignSubject;
	
	@FindBy(name="introbanner")
	private WebElement campaignIntroBannerTB;
	
	@FindBy(name="body")
	private WebElement campaignBodyCopyTB;
	
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
	
	@FindBy(xpath="//div[contains(@ng-show,'campaigntype')]/div")
	private WebElement campTypeErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'CampaignLanguage')]/div")
	private WebElement campLangErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'Campaign exists')]/div")
	private WebElement campNameExistErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'campaignName')]/div")
	private WebElement campNameRequiredErrMsg;
	
	@FindBy(xpath="//div[@ng-show='locationNameRequire']/div")
	private WebElement locationNameReqErrMsg;
	
	@FindBy(xpath="//div[@ng-show='brandNameRequire']/div")
	private WebElement brandNameReqErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'sendername')]/div")
	private WebElement campSenderNameErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'EmailAddresses')]/div")
	private WebElement campEmailIdReqErrMsg;
	
	@FindBy(xpath="//div[@ng-show='errorUploadEmail']/div")
	private WebElement invalidEmailTempUploadedErrMsg;
	
	@FindBy(xpath="//div[@ng-show='errorEmailList']/div")
	private WebElement multiEmailsErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'subject')]/div")
	private WebElement subjectErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'introbanner')]/div")
	private WebElement introBannerErrMsg;
	
	@FindBy(xpath="(//div[contains(@ng-show,'body')]/div)[1]")
	private WebElement bodyCopyErrMsg;
	
	//Body Copy Text Box character limit up to 8000 characters
	@FindBy(xpath="(//div[contains(@ng-show,'body')]/div)[2]")
	private WebElement bodyCopyCharLimitErrMsg;
	
	@FindBy(xpath="(//div[contains(@ng-show,'signature')]/div)[1]")
	private WebElement signatureReqErrMsg;
	
	//Signature Text Box line limit up to 4 lines
	@FindBy(xpath="(//div[contains(@ng-show,'signature')]/div)[2]")
	private WebElement signatureLineLimitErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'AddressLine1')]/div")
	private WebElement addressL1ErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'brandCity')]/div")
	private WebElement cityErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'brandStPr')]/div")
	private WebElement STPR_ErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'brandZip')]/div")
	private WebElement zipPostalCodeErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'brandPhoneNum')]/div")
	private WebElement phoneNumErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'startdate')]/div")
	private WebElement startDateErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'time')]/div")
	private WebElement timeReqErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'enddate')]/div")
	private WebElement endDateErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'lowrating')]/div")
	private WebElement ThankU1_2StarErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'midrating')]/div")
	private WebElement ThankU_3StarErrMsg;
	
	@FindBy(xpath="//div[contains(@ng-show,'highrating')]/div")
	private WebElement ThankU4_5StarErrMsg;
	
	@FindBy(xpath="//span[@ng-bind='headerMessage']")
	private WebElement campNewCreateText;
	
	@FindBy(xpath="(//div[@id=\"breadcrumbs\"]//li/a)[2]")
	private WebElement customerFeedbackBreadCrumb;
	
	@FindBy(xpath="(//h3[@class='text-primary'])[1]")
	private WebElement campaignInfoSection;
	
	/** indexes for 
	 * 
	 * @param campTypeIndex 
	 * Location Index : 1
	 * Brand Index    : 2
	 * MLC Index      : 3	
	 * @throws InterruptedException */
	public void selectCampType(int campTypeIndex) throws InterruptedException {
		Thread.sleep(5000);
		scrollByElement(customerFeedbackBreadCrumb, driver);
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
		scrollByElement(customerFeedbackBreadCrumb, driver);
		select = new Select(campaignLang);
		select.selectByIndex(CampLangIndex);
	}
	
	/** 
	 * This method is used to enter the data into the Campaign name Text Field
	 * @campName : Enter the campaign name 	   */
	public void setCampaignName(String campName) {
		scrollByElement(campaignInfoSection, driver);
		scrollByElement(campaignName, driver);
		campaignName.sendKeys(campName);
	}
	
	/** 
	 * This method used to select the Location for the Campaign Type : Location
	 * @locIndex : To select particular location from selected location name contains @locName based on Index.
	 * @locName  : To check the list box of Locations contains this text.		 */
	public void selectCampaignLoc(int locIndex,String locName) {
		scrollByElement(campaignInfoSection, driver);
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
		scrollByElement(campaignInfoSection, driver);
		campaignBrandNameTB.sendKeys(campBrandName);
	}
	
	public void setCampDescr(String campDescription) {
		scrollByElement(campaignInfoSection, driver);
		campaignDescriptionTB.sendKeys(campDescription);
	}
	
	public void setSenderName(String campSenderName) {
		WebElement newCampText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[2]"));
		scrollByElement(newCampText, driver);
		campaignSenderName.sendKeys(campSenderName);
	}
	
	public void setCampSubject(String campSubject) throws InterruptedException {
		Thread.sleep(2000);
		scrollByElement(campaignSubject, driver);
		campaignSubject.sendKeys(campSubject);
	}
	
	public void setCampBanner(String campIntroBanner) {
		scrollByElement(campaignSubject, driver);
		campaignIntroBannerTB.sendKeys(campIntroBanner);
	}
	
	public void setCampBodyCopy(String campBodyCopy) {
		scrollByElement(campaignSubject, driver);
		campaignBodyCopyTB.sendKeys(campBodyCopy);
	}
	
	/** 
	 * @campSignature : To enter the campaign Signature data into Signature Text Field   */
	public void setCampSignature(String campSignature) {
		scrollByElement(campaignSignature, driver);
		campaignSignature.sendKeys(campSignature);
	}
	
	public void setContactInfo(String addressL1, String city, String contactInfoSTProv, String zipCode, String phoneNum, String... addressL2) {
		
		scrollByElement(campaignSignature, driver);
		contactAddressLine1.sendKeys(addressL1);
		contactAddressLine2.sendKeys(addressL2);
		contactInfoBrandCity.sendKeys(city);
		contactInfoBrandStProv.sendKeys(contactInfoSTProv);
		contactInfoZipCode.sendKeys(zipCode);
		contactInfoPhoneNumber.sendKeys(phoneNum);
	}
	
	public void setScheduledStartDate(String month_MM, String date_DD, String year_YYYY) {
		
		WebElement scheduledSecText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[3]"));
		scrollByElement(scheduledSecText, driver);
		scheduledStartDate.sendKeys(month_MM+"/"+date_DD+"/"+year_YYYY);
	}
	
	/** TimeValue_AM_PM could be in the format of "hh:mm AM/PM" */
	public void setCampaignTime(String TimeValue_AM_PM) {
		
		WebElement scheduledSecText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[3]"));
		scrollByElement(scheduledSecText, driver);
		select = new Select(scheduledTime);
		select.selectByVisibleText(TimeValue_AM_PM);
	}
	
	public void downloadCampEmailTemplate() {
		
		scrollByElement(toFieldTB, driver);
		downloadEmailTemplate.click();

	}
	
	
	//Email template can be upload by using send keys.
	public void uploadCampEmailTemplate(String fileName, String extension) throws InterruptedException {
		
		scrollByElement(toFieldTB, driver);
		File uploadingFilePath =new File("./"+fileName+extension);
		String fileAbsPath=uploadingFilePath.getAbsolutePath();
		uploadEmailTemplate.sendKeys(fileAbsPath);	
		Thread.sleep(2000);
		//uploadFile(fileName, extension);
	}
	
	/*public void clickUploadEmailTemplate() throws InterruptedException {
		scrollByElement(toFieldTB, driver);
		Thread.sleep(2000);
		uploadEmailTemplate.click();
		Thread.sleep(6000);
	}*/
	
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
		Thread.sleep(5000);
	}
	
	public void clickContinueEditBTN() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(continueEditBTN).click(continueEditBTN).perform();
		Thread.sleep(5000);
	}
	
	public void uploadLogo() throws InterruptedException {
		WebElement newEmailSetupText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[2]"));
		scrollByElement(newEmailSetupText, driver);
		/*File uploadingFilePath =new File("./"+fileName+extension);
		String fileAbsPath=uploadingFilePath.getAbsolutePath();*/
		uploadCampaignLogo.click();
		//uploadCampaignLogo.sendKeys(fileAbsPath);
		//uploadFile(fileName, extension);
		//Thread.sleep(6000);
		//String fileName, String extension
			
	}
	
	public void verifyLogoUploaded() throws InterruptedException {
		//Thread.sleep(3000);
		WebElement newEmailSetupText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[2]"));
		scrollByElement(newEmailSetupText, driver);
		try {
			wait.until(ExpectedConditions.visibilityOf(removeLogoBTN));
			if(removeLogoBTN.isDisplayed() & removeLogoBTN.isEnabled()) {
				System.out.println("Uploaded Logo");
			}
		}
		catch(Exception e){
			System.out.println("Logo NOT uploaded");
		}
	}
	
	public void clickRemoveLogo() {
		scrollByElement(removeLogoBTN, driver);
		removeLogoBTN.click();
	}
	
	//---------------------Handling Tool Tip's of Create New Cammpaign Page-----------------
	
	/**
	 * Existing campaign tool tip text for different languages
	 * English		:	Copy an existing campaign to quickly create a new one.
	 * 						*/
	public void verifyExistCampToolTipText(String eText) {
		
		scrollByElement(customerFeedbackBreadCrumb, driver);
		action.moveToElement(existCampTT).perform();
		verifyText(toolTipText, eText);
	}
	
	/**Upload Logo Tool tip for Different Languages
	 * English		:	Recommended size is 160 x 70 pixels
	 * 			*/
	public void verifyUploadLogoToolTipText(String eText) {
		
		WebElement newEmailSetupText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[2]"));
		scrollByElement(newEmailSetupText, driver);
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

	/**
	 * Time tool tip for different languages
	 * English 		:	Email processing will commence at the selected time. The deployment of the first email might be delayed with large distribution lists.
	 * 		 */
	public void verifyTimeToolTipText(String eText) {
	
		WebElement scheduledSecText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[3]"));
		scrollByElement(scheduledSecText, driver);
		action.moveToElement(timeTT).perform();
		verifyText(toolTipText, eText);
	}

	public void verifyCampEndDateToolTipText(String eText) {
	
		WebElement scheduledSecText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[3]"));
		scrollByElement(scheduledSecText, driver);
		action.moveToElement(campEndDateTT).perform();
		verifyText(toolTipText, eText);
	}
	
	/** 
	 * To enter the email id's into To Field for Location and Brand campaign
	 * @email : e-mail id to send the created campaign and more than 1 email id's seperated with comma(,) */
	public void setToField(String email) {
		scrollByElement(toFieldTB, driver);
		toFieldTB.sendKeys(email);
	}
	
	public void clickStartDatePicker() {
		WebElement scheduledSecText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[3]"));
		scrollByElement(scheduledSecText, driver);
		driver.findElement(By.xpath("(//span[@class='k-select']/span)[1]")).click();
	}
	
	public void clickEndDatePicker() {
		WebElement scheduledSecText=driver.findElement(By.xpath("(//h3[@class='text-primary'])[3]"));
		scrollByElement(scheduledSecText, driver);
		driver.findElement(By.xpath("(//span[@class='k-select']/span)[2]")).click();
	}

}
