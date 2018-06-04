package com.dac.main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import resources.ExcelTestDataHandler;
import resources.IAutoconst;

public class CreateNewCampaignPage extends BasePage{

	WebDriver driver;
	Select select;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	public CreateNewCampaignPage(WebDriver driver) throws AWTException {
		
		wait=new WebDriverWait(driver, 20);
		this.driver = driver;
		js=(JavascriptExecutor)driver;
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
	
	@FindBy(id="selectableTable")
	private WebElement mlc1st;
	
	@FindBy(className="tooltip-inner")
	private WebElement mlctooltips;
	
	@FindBy(xpath="(//*[contains(@class,'btn-move-right')])[1]")
	private WebElement mlcAddBTN;
	
	@FindBy(xpath="(//*[contains(@class,'btn-move-left')])[1]")
	private WebElement mlcRemoveBTN;
	
	@FindBy(xpath="//*[contains(@class,'btn-move-right-all')]")
	private WebElement mlcAddAllBTN;
	
	@FindBy(xpath="//*[contains(@class,'btn-move-left-all')]")
	private WebElement mlcRemoveAllBTN;
	
	@FindBy(name="description")
	private WebElement campaignDescriptionTB;
	
	@FindBy(name="sendername")
	private WebElement campaignSenderName;
	
	@FindBy(xpath="(//h3[@class='text-primary'])[2]")
	private WebElement eMailSetupSection;

	@FindBy(xpath="//div[@title='upload']")
	private WebElement uploadCampaignLogo;
	
	@FindBy(xpath="//a[@ng-show='removeIcon']")
	private WebElement removeLogoBTN;
	
	@FindBy(xpath="//*[@name='subject']/../h4")
	private WebElement subjectText;
	
	@FindBy(name="subject")
	private WebElement campaignSubject;
	
	@FindBy(name="introbanner")
	private WebElement campaignIntroBannerTB;
	
	@FindBy(name="body")
	private WebElement campaignBodyCopyTB;
	
	@FindBy(xpath="//*[@name='signature']/../h4")
	private WebElement signatureHeading;
	
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
	
	@FindBy(xpath="(//h3[@class='text-primary'])[3]")
	private WebElement scheduledSection;
	
	@FindBy(name="startdate")
	private WebElement scheduledStartDate;
	
	@FindBy(name="time")
	private WebElement scheduledTime;
	
	@FindBy(id="emailTemplate")
	private WebElement downloadEmailTemplate;
	
	@FindBy(xpath="(//*[@name='campaignemailaddress'])[1]/../../h4")
	private WebElement toText;
	
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
	 * @column : Enter the column number of campaign name 	   */
	public String setCampaignName(String sheet, int row, int column) {
		scrollByElement(campaignInfoSection, driver);
		String xlCampName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		String campName= xlCampName+" - "+getDateNTime();
		campaignName.sendKeys(campName);
		Assert.assertTrue(true, "Entered data in Campaign Name Text Field");
		return campName;
		
	}
	
	/** 
	 * This method used to select the Location for the Campaign Type : Location
	 * @locIndex : To select particular location from selected location name contains @locName based on Index.
	 * @column   : column number of excel sheet to get the location name		 */
	public void selectCampaignLoc(int locIndex, int column) {
		scrollByElement(campaignInfoSection, driver);
		String locName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Location", 7, column);
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
	
	public void setCampaignBrandName(int column) {
		scrollByElement(campaignInfoSection, driver);
		String campBrandName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 7, column);
		campaignBrandNameTB.sendKeys(campBrandName+" - "+getDate());
	}
	
	public void selectMLCs(String locationName) {
		scrollByElement(campaignInfoSection, driver);
		String locNamexp="//input[contains(@name,'"+locationName+"')]";
		WebElement selectLoc = mlc1st.findElement(By.xpath(locNamexp));
		//selectLoc.click();
		//WebElement locationNum = mlc1st.findElement(By.xpath(locNamexp+"/../span[contains(@class,'text-status')]"));
		String a= "(//input[contains(@name,'BN')]/../span[contains(@class,'text-status')])[1]";
		WebElement locationNum = driver.findElement(By.xpath(a));
		
		Object b = js.executeScript("document.getElementById(\"selectableTable\").getElementsByTagName(\"div\")[1].getElementsByTagName(\"label\")[0].getElementsByTagName(\"span\")[0].innerText");
		
		System.out.println(b.toString());
		String ccs= "div#selectableTable>div:nth-child(3)>label:nth-child(2)>strong";
		System.out.println(driver.findElement(By.cssSelector(ccs)).getText());
		System.out.println(locationNum.getText());
		//System.out.println(selectLoc.getText());
		action.moveToElement(selectLoc).click(selectLoc).perform();
		
	}
	
	public void clickAddAllBTN() {
		scrollByElement(campaignInfoSection, driver);
		mlcAddBTN.click();
	}
	public void setCampDescr(String sheet, int row, int column) {
		scrollByElement(campaignInfoSection, driver);
		String campDescription = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		campaignDescriptionTB.sendKeys(campDescription+" - "+getDate());
	}
	
	public void setSenderName(String sheet, int row, int column) {
		scrollByElement(eMailSetupSection, driver);
		String campSenderName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		campaignSenderName.sendKeys(campSenderName+" - "+getDate());
	}
	
	public void setCampSubject(String sheet, int row, int column) {
		scrollByElement(subjectText, driver);
		String campSubject = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		campaignSubject.sendKeys(campSubject+" - "+getDate());
	}
	
	public void setCampBanner(String sheet, int row, int column) {
		scrollByElement(subjectText, driver);
		String campIntroBanner = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		campaignIntroBannerTB.sendKeys(campIntroBanner+" - "+getDate());
	}
	
	public void setCampBodyCopy(String sheet, int row, int column) {
		scrollByElement(subjectText, driver);
		String campBodyCopy = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		campaignBodyCopyTB.sendKeys(campBodyCopy);
	}
	
	/** 
	 * @campSignature : To enter the campaign Signature data into Signature Text Field   */
	public void setCampSignature(String sheet, int row, int column) {
		scrollByElement(signatureHeading, driver);
		String campSignature = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		campaignSignature.sendKeys(campSignature);
	}
	
	public void setContactInfo(int column) {
		
		scrollByElement(signatureHeading, driver);
		String addressL1 = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 20, column);
		String addressL2 = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 21, column);
		String city = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 22, column);
		String contactInfoSTProv = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 23, column);
		String zipCode = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 24, column);
		String phoneNum = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 25, column);
		contactAddressLine1.sendKeys(addressL1);
		contactAddressLine2.sendKeys(addressL2);
		contactInfoBrandCity.sendKeys(city);
		contactInfoBrandStProv.sendKeys(contactInfoSTProv);
		contactInfoZipCode.sendKeys(zipCode);
		contactInfoPhoneNumber.sendKeys(phoneNum);
	}
	
	public void setScheduledStartDate() throws AWTException {
		
		scrollByElement(scheduledSection, driver);
		//String campStartDate = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String dateFormat = sdf.format(date);
		
		scheduledStartDate.sendKeys(dateFormat);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	/** TimeValue_AM_PM could be in the format of "hh:mm AM/PM" */
	public void setCampaignTime(String sheet, int row, int column) {
		
		scrollByElement(scheduledSection, driver);
		String TimeValue_AM_PM = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		select = new Select(scheduledTime);
		select.selectByVisibleText(TimeValue_AM_PM);
	}
	
	public void downloadCampEmailTemplate() {
		
		scrollByElement(toFieldTB, driver);
		downloadEmailTemplate.click();

	}
	
	
	//Email template can be upload by using send keys.
	public void uploadCampEmailTemplate(String sheet, int row, int column) throws InterruptedException {
		
		scrollByElement(toText, driver);
		String fileName = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		File uploadingFilePath =new File("./filesToUpload/"+fileName);
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
		scrollByElement(eMailSetupSection, driver);
		uploadCampaignLogo.click();
			
	}
	
	public void verifyLogoUploaded() throws InterruptedException {
		scrollByElement(eMailSetupSection, driver);
		try {
			wait.until(ExpectedConditions.visibilityOf(removeLogoBTN));
			if(removeLogoBTN.isDisplayed() & removeLogoBTN.isEnabled()) {
				System.out.println("Uploaded Logo");
				Assert.assertTrue(true, "Uploaded Logo");
			}
		}
		catch(Exception e){
			System.out.println("Logo NOT uploaded");
			Assert.assertTrue(true, "Logo NOT uploaded");
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
	 * Deutsch		:	Kopieren Sie eine bestehende Kampagne, um schnell eine neue zu erstellen.
	 * 						*/
	public void verifyExistCampToolTipText(String sheet,int row, int column) {
		
		scrollByElement(customerFeedbackBreadCrumb, driver);
		String eText = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		action.moveToElement(existCampTT).click(existCampTT).perform();
		verifyText(toolTipText, eText);
	}
	
	/**Upload Logo Tool tip for Different Languages
	 * English		:	Recommended size is 160 x 70 pixels
	 * Deutsch		:	Empfohlene Größe ist 160 x 70 Pixel
	 * 			*/
	public void verifyUploadLogoToolTipText(String sheet, int row, int column) {
		
		scrollByElement(eMailSetupSection, driver);
		String eText = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		action.moveToElement(uploadLogoTT).click(uploadLogoTT).perform();
		verifyText(toolTipText, eText);
	}
	
	public void verifyPersonalizationToolTipText(String sheet,int row, int column) {
		
		scrollByElement(subjectText, driver);
		String eText = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		action.moveToElement(personalizationTT).click(personalizationTT).perform();
		verifyText(toolTipText, eText);
	}

	public void verifyContactInfoToolTipText(int column) {
	
		scrollByElement(signatureHeading, driver);
		String eText = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, "Brand", 19, column);
		action.moveToElement(contactInfoTT).click(contactInfoTT).perform();
		verifyText(toolTipText, eText);
	}

	/**
	 * Time tool tip for different languages
	 * English 		:	Email processing will commence at the selected time. The deployment of the first email might be delayed with large distribution lists.
	 * Deutsch		:	Die E-Mail-Bearbeitung beginnt ab dem ausgewählten Zeitpunkt. Der Versand der ersten E-Mail könnte verspätet stattfinden, falls es sich um eine große Verteilerliste handelt.
	 *			*/
	public void verifyTimeToolTipText(String sheet, int row,  int column) {
	
		scrollByElement(scheduledSection, driver);
		String eText = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		action.moveToElement(timeTT).click(timeTT).perform();
		verifyText(toolTipText, eText);
	}

	/**
	 * End date Tool tip for different languages
	 * English		:
	 * Deutsch		:	Das Kundenfeedback endet an diesem Datum für diese Kampagne. Das Sammelformular wird durch eine Kopie ersetzt, die den Benutzer darüber informiert, dass diese Kampagne nun geschlossen ist.
	 * @throws InterruptedException 
	 * 			*/
	public void verifyCampEndDateToolTipText(String sheet, int row, int column) throws InterruptedException {
	
		scrollByElement(scheduledSection, driver);
		String eText = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		action.moveToElement(campEndDateTT).click(campEndDateTT).perform();
		wait.until(ExpectedConditions.visibilityOf(toolTipText));
		Thread.sleep(1000);
		verifyText(toolTipText, eText);
	}
	
	/** 
	 * To enter the email id's into To Field for Location and Brand campaign
	 * @email : e-mail id to send the created campaign and more than 1 email id's seperated with comma(,) */
	public void setToField(String sheet, int row, int column) {
		scrollByElement(toFieldTB, driver);
		String email = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		toFieldTB.sendKeys(email);
	}
	
	public void clickStartDatePicker() {
		scrollByElement(scheduledSection, driver);
		driver.findElement(By.xpath("(//span[@class='k-select']/span)[1]")).click();
	}
	
	public void clickEndDatePicker() {
		scrollByElement(scheduledSection, driver);
		driver.findElement(By.xpath("(//span[@class='k-select']/span)[2]")).click();
	}

}
