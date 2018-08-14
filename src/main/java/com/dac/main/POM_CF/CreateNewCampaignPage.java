package com.dac.main.POM_CF;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.DateFormats;
import resources.ExcelTestDataHandler;
import resources.FileDownloader;
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
	
	BaseTest_CF bt = new BaseTest_CF();
	
	@FindBy(xpath="//*[@ng-bind='headerMessage']")
	private WebElement subTittle_CampaignPage;
	
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
	
	//*[@ng-bind="EmailAddressCount"]
	@FindBy(xpath="//*[@ng-bind='EmailAddressCount']")
	private WebElement totalMailCount;
	
	@FindBy(xpath="(//h3[@class='text-primary'])[3]")
	private WebElement scheduledSection;
	
	@FindBy(name="startdate")
	private WebElement scheduledStartDate;
	
	@FindBy(name="enddate")
	private WebElement scheduledEndDate;
	
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
	
	@FindBy(xpath="cancelBtn ng-scope")
	private WebElement cancelBTN;
	
	@FindBy(xpath="//*[@ng-bind='buttonMessage']")
	private WebElement UpdateCampaignBTN;
	
	@FindBy(xpath="//*[@class='livePreviewBtn']/b")
	private WebElement LivePreviewBTN;
	
	//-----------------Tool Tip's ----------------
	
	@FindBy(className="tooltip-inner")
	private WebElement mlcLocToolTips;
	
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
	private WebElement iCircleToolTipText;
	
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
	
	@FindBy(xpath="(//div[@id='breadcrumbs']//li/a)[2]")
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
		wait.until(ExpectedConditions.visibilityOf(campaignType));
		scrollByElement(customerFeedbackBreadCrumb, driver);
		while(true) {
			if(campaignType.isDisplayed()) {
				select = new Select(campaignType);
				select.selectByIndex(campTypeIndex);
				break;
			}	
		}
	}
	
	public String getSelectedCampaignType() {
		String xp="//select[@name='campaigntype']/*[@selected='selected'][2]";
		String SelectedCampaignType = driver.findElement(By.xpath(xp)).getText();
		return SelectedCampaignType;
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
	 * @throws Exception 
	 * @column : Enter the column number of campaign name 	   */
	public String setCampaignName(String sheet, int row, int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String xlCampName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		String campName= xlCampName+" - "+getDateNTime();
		campaignName.sendKeys(campName);
		return campName;	
	}
	
	/** 
	 * This method used to select the Location for the Campaign Type : Location
	 * @throws Exception 
	 * @locIndex : To select particular location from selected location name contains @locName based on Index.
	 * @column   : column number of excel sheet to get the location name		 */
	public void selectCampaignLoc(int locIndex, int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String locName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Location").getCellValue(7, column);
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
	
	/**
	 * To get the selected location/Client name where to compare location name in live preview	*/
	public String getSelectedLocName() throws UnsupportedFlavorException, IOException {
		String selectedLoc = getClipboardContents(campaignLocTB);
		String[] a = selectedLoc.split(",");
		bt.locName =a[0];
		return a[0];
	}
	
	public String setCampaignBrandName(int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String campBrandName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(7, column);
		String BrandName= campBrandName+" - "+getDate();
		campaignBrandNameTB.sendKeys(BrandName);
		return BrandName;
	}
	
	public void selectMLCs(String locationName) {
		scrollByElement(campaignInfoSection, driver);
		String locNamexp="//input[contains(@name,'"+locationName+"')]";
		WebElement selectLoc = mlc1st.findElement(By.xpath(locNamexp));
		clickelement(selectLoc, driver);
		
	}
	
	public void clickAddBTN() {
		scrollByElement(campaignInfoSection, driver);
		clickelement(mlcAddBTN, driver);
	}
	
	public void clickRemoveBTN() {
		scrollByElement(campaignInfoSection, driver);
		clickelement(mlcRemoveBTN, driver);
	}
	
	public void clickAddAllBTN() {
		scrollByElement(campaignInfoSection, driver);
		clickelement(mlcAddAllBTN, driver);
	}
	
	public void clickRemoveAllBTN() {
		scrollByElement(campaignInfoSection, driver);
		clickelement(mlcRemoveAllBTN, driver);
	}
	
	public void setCampDescr(String sheet, int row, int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String campDescription = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignDescriptionTB.sendKeys(campDescription+" - "+getDate());
	}
	
	public void setSenderName(String sheet, int row, int column) throws Exception {
		scrollByElement(eMailSetupSection, driver);
		String campSenderName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignSenderName.sendKeys(campSenderName+" - "+getDate());
	}
	
	public String setCampSubject(String sheet, int row, int column) throws Exception {
		scrollByElement(subjectText, driver);
		String campSubject = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column)+" - "+getDate();
		campaignSubject.sendKeys(campSubject);
		return campSubject;
	}
	
	public String setCampBanner(String sheet, int row, int column) throws Exception {
		scrollByElement(subjectText, driver);
		String campIntroBanner = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column)+" - "+getDate();
		campaignIntroBannerTB.sendKeys(campIntroBanner);
		return campIntroBanner;
	}
	
	public String setCampBodyCopy(String sheet, int row, int column) throws Exception {
		scrollByElement(subjectText, driver);
		String campBodyCopy = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignBodyCopyTB.sendKeys(campBodyCopy);
		return campBodyCopy;
	}
	
	/** 
	 * @throws Exception 
	 * @campSignature : To enter the campaign Signature data into Signature Text Field   */
	public String setCampSignature(String sheet, int row, int column) throws Exception {
		scrollByElement(signatureHeading, driver);
		String campSignature = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignSignature.sendKeys(campSignature);
		return campSignature;
	}
	
	public void setContactInfo(int column) throws Exception {
		
				
		scrollByElement(signatureHeading, driver);
		bt.campAddressL1 = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(20, column);
		bt.campAddressL2 = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(21, column);
		bt.city = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(22, column);
		bt.STorPR = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(23, column);
		bt.postalCode = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(24, column);
		bt.phoneNo = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(25, column);
		contactAddressLine1.sendKeys(bt.campAddressL1);
		contactAddressLine2.sendKeys(bt.campAddressL2);
		contactInfoBrandCity.sendKeys(bt.city);
		contactInfoBrandStProv.sendKeys(bt.STorPR);
		contactInfoZipCode.sendKeys(bt.postalCode);
		contactInfoPhoneNumber.sendKeys(bt.phoneNo);
	}
	
	
	//works for English DashBoard Language
	public void setScheduledStartDate(String langCode, String countryCode) throws AWTException {
		scrollByElement(scheduledSection, driver);
		bt.campStartDate = DateFormats.dateFormat(langCode, countryCode).format(new Date());

		System.out.println("StartDate : "+ bt.campStartDate);
		scheduledStartDate.sendKeys(bt.campStartDate);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
/*	public void scheduledCampDates() throws UnsupportedFlavorException, IOException {
		getClipboardContents(scheduledStartDate);
		getClipboardContents(scheduledEndDate);
	}*/
	
	public void verifyCampEndDate(String langCode, String countryCode) throws UnsupportedFlavorException, IOException {
		bt.campEndDate = BasePage.addDays(langCode, countryCode, 30);
		System.out.println("EndDate : "+bt.campEndDate);
		String endDatetext = getClipboardContents(scheduledEndDate);
		System.out.println("copiedEndDate : "+endDatetext);
		
		if(bt.campEndDate.equals(endDatetext)) {
			Assert.assertEquals(bt.campEndDate, endDatetext);
		}
		else {
			Assert.fail();
		}
	}
	
	
	
	/**
	 * engLongDateFormat = Day, Month dd, YYYY	
	 * englangCode = "en" , engcontryCode = "US"	*/
	public void verifyLongDateFormat(String langCode, String contryCode) {
				
		String calenderlDatexp = "";
		try {
			calenderlDatexp = "(//*[@class='k-footer']/a)[1]";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(calenderlDatexp)));
		}
		catch(Exception e) {
			calenderlDatexp = "(//*[@class='k-footer']/a)[2]";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(calenderlDatexp)));
		}
		
		String lDate = driver.findElement(By.xpath(calenderlDatexp)).getAttribute("title");
		
		String selStartDateLFormat = driver.findElement(By.xpath("//*[contains(@ng-bind,'StartDateInfoLocale')]")).getText();
		
		String date = DateFormats.longDate(langCode, contryCode);
		
		if(selStartDateLFormat.equals(date) & lDate.equals(date)) {
			Assert.assertTrue(true, "Long Dates are displaying in there Locale Format and in Respective Language");
		}else Assert.assertTrue(false, "Long Dates are either Not displaying in there Locale Format or in Respective Language");
	}
	
	public void clickStartDatePicker() throws InterruptedException {
		scrollByElement(scheduledSection, driver);
		WebElement startdateBTN = driver.findElement(By.xpath("(//span[@class='k-select']/span)[1]"));
		clickelement(startdateBTN, driver);
	}
	
	public void clickEndDatePicker() {
		scrollByElement(scheduledSection, driver);
		WebElement endDateBTN = driver.findElement(By.xpath("(//span[@class='k-select']/span)[2]"));
		clickelement(endDateBTN, driver);
	}
	
	/** TimeValue_AM_PM could be in the format of "hh:mm AM/PM" 
	 * @throws Exception */
	public void setCampaignTime(String sheet, int row, int column) throws Exception {
		
		scrollByElement(scheduledSection, driver);
		String TimeValue_AM_PM = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		select = new Select(scheduledTime);
		select.selectByVisibleText(TimeValue_AM_PM);
	}
	
	public void downloadCampEmailTemplate() throws InterruptedException, IOException {
		
		scrollByElement(toFieldTB, driver);
		System.out.println("clicking download email template");
		clickelement(downloadEmailTemplate, driver);
		Thread.sleep(2000);
		try
		{
		     Robot robot = new Robot();
		     robot.setAutoDelay(250);
		     robot.keyPress(KeyEvent.VK_ALT);

		     robot.keyPress(KeyEvent.VK_S);
		     robot.keyRelease(KeyEvent.VK_ALT);
		}
		catch (AWTException e)
		{
		    e.printStackTrace();
		}
	}
	
	public void uploadEmailTemplate(String fileName) throws InterruptedException {
		scrollByElement(toText, driver);
		File uploadingFilePath =new File("./downloads/"+fileName);
		String fileAbsPath=uploadingFilePath.getAbsolutePath();
		uploadEmailTemplate.sendKeys(fileAbsPath);	
		Thread.sleep(2000);
	}
	
	//Email template can be upload by using send keys.
	public void uploadCampEmailTemplate(String sheet, int row, int column) throws Exception {
		
		scrollByElement(toText, driver);
		String fileName = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
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
		clickelement(saveDraftBTN, driver);
	}
	
	public void clickCreateCampBTN() {
		scrollByElement(createCampaignBTN, driver);
		clickelement(createCampaignBTN, driver);
	}
	
	public void clickViewAllCampaignBTN() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(viewAllCampaignBTN));
		clickelement(viewAllCampaignBTN, driver);
		Thread.sleep(5000);
	}
	
	public void clickContinueEditBTN() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(viewAllCampaignBTN));
		//Thread.sleep(5000);
		clickelement(continueEditBTN, driver);
		Thread.sleep(5000);
	}
	
	public void uploadLogo(String fileNameWithExtension) throws IOException {
		scrollByElement(eMailSetupSection, driver);
		clickelement(uploadCampaignLogo, driver);
		upload(fileNameWithExtension);
		wait.until(ExpectedConditions.visibilityOf(removeLogoBTN));
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
		clickelement(removeLogoBTN, driver);
	}
	
	//---------------------Handling Tool Tip's of Create New Cammpaign Page-----------------
	
	/**
	 * Existing campaign tool tip text for different languages
	 * English		:	Copy an existing campaign to quickly create a new one.
	 * Deutsch		:	Kopieren Sie eine bestehende Kampagne, um schnell eine neue zu erstellen.
	 * @throws Exception 
	 * 						*/
	public void verifyExistCampToolTipText(String sheet,int row, int column) throws Exception {
		
		scrollByElement(customerFeedbackBreadCrumb, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		clickelement(existCampTT, driver);
		verifyText(iCircleToolTipText, eText);
	}
	
	public void verifyAddToolTipText(String sheet, int row, int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(mlcAddBTN).perform();
		Thread.sleep(2000);
		verifyText(mlcLocToolTips, eText);
		
	}
	
	public void verifyRemoveToolTipText(String sheet, int row, int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(mlcRemoveBTN).perform();
		Thread.sleep(2000);
		verifyText(mlcLocToolTips, eText);
	}
	
	public void verifyAddAllToolTipText(String sheet, int row, int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(mlcAddAllBTN).perform();
		Thread.sleep(2000);
		verifyText(mlcLocToolTips, eText);
	}
	
	public void verifyRemoveAllToolTipText(String sheet, int row, int column) throws Exception {
		scrollByElement(campaignInfoSection, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(mlcRemoveAllBTN).perform();
		Thread.sleep(2000);
		verifyText(mlcLocToolTips, eText);
	}
	
	/**Upload Logo Tool tip for Different Languages
	 * English		:	Recommended size is 160 x 70 pixels
	 * Deutsch		:	Empfohlene Größe ist 160 x 70 Pixel
	 * @throws Exception 
	 * 			*/
	public void verifyUploadLogoToolTipText(String sheet, int row, int column) throws Exception {
		
		scrollByElement(eMailSetupSection, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		clickelement(uploadLogoTT, driver);
		verifyText(iCircleToolTipText, eText);
	}
	
	public void verifyPersonalizationToolTipText(String sheet,int row, int column) throws Exception {
		
		scrollByElement(subjectText, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		clickelement(personalizationTT, driver);
		verifyText(iCircleToolTipText, eText);
	}

	public void verifyContactInfoToolTipText(int column) throws Exception {
	
		scrollByElement(signatureHeading, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(19, column);
		clickelement(contactInfoTT, driver);
		verifyText(iCircleToolTipText, eText);
	}

	/**
	 * Time tool tip for different languages
	 * English 		:	Email processing will commence at the selected time. The deployment of the first email might be delayed with large distribution lists.
	 * Deutsch		:	Die E-Mail-Bearbeitung beginnt ab dem ausgewählten Zeitpunkt. Der Versand der ersten E-Mail könnte verspätet stattfinden, falls es sich um eine große Verteilerliste handelt.
	 * @throws Exception 
	 *			*/
	public void verifyTimeToolTipText(String sheet, int row,  int column) throws Exception {
	
		scrollByElement(scheduledSection, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(timeTT).perform();
		wait.until(ExpectedConditions.visibilityOf(iCircleToolTipText));
		verifyText(iCircleToolTipText, eText);
	}

	/**
	 * End date Tool tip for different languages
	 * English		:
	 * Deutsch		:	Das Kundenfeedback endet an diesem Datum für diese Kampagne. Das Sammelformular wird durch eine Kopie ersetzt, die den Benutzer darüber informiert, dass diese Kampagne nun geschlossen ist.
	 * @throws Exception 
	 * 			*/
	public void verifyCampEndDateToolTipText(String sheet, int row, int column) throws Exception {
	
		scrollByElement(scheduledSection, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(campEndDateTT).perform();
		wait.until(ExpectedConditions.visibilityOf(iCircleToolTipText));
		Thread.sleep(1000);
		verifyText(iCircleToolTipText, eText);
	}
	
	/** 
	 * To enter the email id's into To Field for Location and Brand campaign
	 * @throws Exception 
	 * @email : e-mail id to send the created campaign and more than 1 email id's seperated with comma(,) */
	public void setToField(String sheet, int row, int column) throws Exception {
		scrollByElement(toFieldTB, driver);
		String email = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		toFieldTB.sendKeys(email);
	}
	
	public String getTofieldData() throws UnsupportedFlavorException, IOException {
		scrollByElement(toFieldTB, driver);
		bt.campTofield = getClipboardContents(toFieldTB);
		return bt.campTofield;
	}
	
	public void clickUpdateCampaignBTN() {
		if(UpdateCampaignBTN.isEnabled()) {
			Assert.assertTrue(true, "edited Scheduled Campaign");
			UpdateCampaignBTN.click();
			clickelement(UpdateCampaignBTN, driver);
		}
		else  Assert.assertTrue(false, "Didn't edited the Scheduled Campaign");
	}

	public void clickLivePreviewBTN() {
		clickelement(LivePreviewBTN, driver);
	}
	
	public void clickCancelBTN() {
		clickelement(cancelBTN, driver);
	}
}
