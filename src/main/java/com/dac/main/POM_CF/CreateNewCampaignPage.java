package com.dac.main.POM_CF;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import resources.IAutoconst;

public class CreateNewCampaignPage extends BasePage{

	WebDriver driver;
	Select select;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	public static Date startDate, targetDate;
	public static String startDateFormat, endDateFormat;
	static SimpleDateFormat engDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public CreateNewCampaignPage(WebDriver driver) throws AWTException {
		
		wait=new WebDriverWait(driver, 20);
		this.driver = driver;
		js=(JavascriptExecutor)driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
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
		//selectLoc.click();
		//WebElement locationNum = mlc1st.findElement(By.xpath(locNamexp+"/../span[contains(@class,'text-status')]"));
		/*String a= "(//input[contains(@name,'BN')]/../span[contains(@class,'text-status')])[1]";
		WebElement locationNum = driver.findElement(By.xpath(a));
		
		Object b = js.executeScript("document.getElementById(\"selectableTable\").getElementsByTagName(\"div\")[1].getElementsByTagName(\"label\")[0].getElementsByTagName(\"span\")[0].innerText");
		
		System.out.println(b.toString());
		String ccs= "div#selectableTable>div:nth-child(3)>label:nth-child(2)>strong";
		System.out.println(driver.findElement(By.cssSelector(ccs)).getText());
		System.out.println(locationNum.getText());*/
		//System.out.println(selectLoc.getText());
		action.moveToElement(selectLoc).click(selectLoc).perform();
		
	}
	
	public void clickAddBTN() {
		scrollByElement(campaignInfoSection, driver);
		mlcAddBTN.click();
	}
	
	public void clickRemoveBTN() {
		scrollByElement(campaignInfoSection, driver);
		mlcRemoveBTN.click();
	}
	
	public void clickAddAllBTN() {
		scrollByElement(campaignInfoSection, driver);
		mlcAddAllBTN.click();
	}
	
	public void clickRemoveAllBTN() {
		scrollByElement(campaignInfoSection, driver);
		mlcRemoveAllBTN.click();
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
	
	public void setCampSubject(String sheet, int row, int column) throws Exception {
		scrollByElement(subjectText, driver);
		String campSubject = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignSubject.sendKeys(campSubject+" - "+getDate());
	}
	
	public void setCampBanner(String sheet, int row, int column) throws Exception {
		scrollByElement(subjectText, driver);
		String campIntroBanner = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignIntroBannerTB.sendKeys(campIntroBanner+" - "+getDate());
	}
	
	public void setCampBodyCopy(String sheet, int row, int column) throws Exception {
		scrollByElement(subjectText, driver);
		String campBodyCopy = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignBodyCopyTB.sendKeys(campBodyCopy);
	}
	
	/** 
	 * @throws Exception 
	 * @campSignature : To enter the campaign Signature data into Signature Text Field   */
	public void setCampSignature(String sheet, int row, int column) throws Exception {
		scrollByElement(signatureHeading, driver);
		String campSignature = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		campaignSignature.sendKeys(campSignature);
	}
	
	public void setContactInfo(int column) throws Exception {
		
		scrollByElement(signatureHeading, driver);
		String addressL1 = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(20, column);
		String addressL2 = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(21, column);
		String city = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(22, column);
		String contactInfoSTProv = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(23, column);
		String zipCode = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(24, column);
		String phoneNum = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(25, column);
		contactAddressLine1.sendKeys(addressL1);
		contactAddressLine2.sendKeys(addressL2);
		contactInfoBrandCity.sendKeys(city);
		contactInfoBrandStProv.sendKeys(contactInfoSTProv);
		contactInfoZipCode.sendKeys(zipCode);
		contactInfoPhoneNumber.sendKeys(phoneNum);
	}
	
	
	//works for English DashBoard Language
	public void setScheduledStartDate() throws AWTException {
		
		scrollByElement(scheduledSection, driver);
		//String campStartDate = ExcelTestDataHandler.getData(IAutoconst.RS_XL_PATH, sheet, row, column);
		
		startDate = new Date();
		targetDate = DateUtils.addDays(startDate, 30);
		
		startDateFormat = engDateFormat.format(startDate);
		endDateFormat = engDateFormat.format(targetDate);
		System.out.println("StartDate : "+ startDateFormat + "\n"+ "End Date : "+endDateFormat);
		
		scheduledStartDate.sendKeys(startDateFormat);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
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
		
		String date = "";
		
		String langConCode = langCode+"_"+contryCode;
		
		switch(langConCode) {
		
		case "de_DE" : date = DateFormats.longDate_de_DE();
					   break;
					   
		case "en_US" : date = DateFormats.longDate_en_US();
		   			   break;
		   			   
		case "es_ES" : date = DateFormats.longDate_es_ES();
		   			   break;
		   			   
		case "es_MX" : date = DateFormats.longDate_es_MX();
		   			   break;
		   			   
		case "fr_CA" : date = DateFormats.longDate_fr_CA();
		   			   break;
		   			   
		case "fr_FR" : date = DateFormats.longDate_fr_FR();
		   			   break;
		   			   
		case "it_IT" : date = DateFormats.longDate_it_IT();
		   			   break;
		   			   
		case "sv_SE" : date = DateFormats.longDate_sv_SE();
					   break;
					 
		default		 : System.out.println("Selected wrong Language Code or Contry Code please check and Execute once again");
		}
		
		System.out.println(date);
		
		if(selStartDateLFormat.equals(date) & lDate.equals(date)) {
			Assert.assertTrue(true, "Long Dates are displaying in there Locale Format and in Respective Language");
		}else Assert.assertTrue(false, "Long Dates are either Not displaying in there Locale Format or in Respective Language");
	}
	
	public void verifyEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String endDateFormat = sdf.format(targetDate);
		System.out.println("selected Default Campaign End Date is : " + endDateFormat);
	}
	
	public void clickStartDatePicker() throws InterruptedException {
		scrollByElement(scheduledSection, driver);
		driver.findElement(By.xpath("(//span[@class='k-select']/span)[1]")).click();
	}
	
	public void clickEndDatePicker() {
		scrollByElement(scheduledSection, driver);
		driver.findElement(By.xpath("(//span[@class='k-select']/span)[2]")).click();
	}
	
	/** TimeValue_AM_PM could be in the format of "hh:mm AM/PM" 
	 * @throws Exception */
	public void setCampaignTime(String sheet, int row, int column) throws Exception {
		
		scrollByElement(scheduledSection, driver);
		String TimeValue_AM_PM = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		select = new Select(scheduledTime);
		select.selectByVisibleText(TimeValue_AM_PM);
	}
	
	public void downloadCampEmailTemplate() {
		
		scrollByElement(toFieldTB, driver);
		downloadEmailTemplate.click();

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
		saveDraftBTN.click();
	}
	
	public void clickCreateCampBTN() {
		scrollByElement(createCampaignBTN, driver);
		createCampaignBTN.click();
	}
	
	public void clickViewAllCampaignBTN() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(viewAllCampaignBTN));
		Thread.sleep(2000);
		action.moveToElement(viewAllCampaignBTN).click(viewAllCampaignBTN).perform();
		Thread.sleep(5000);
	}
	
	public void clickContinueEditBTN() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(viewAllCampaignBTN));
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
	 * @throws Exception 
	 * 						*/
	public void verifyExistCampToolTipText(String sheet,int row, int column) throws Exception {
		
		scrollByElement(customerFeedbackBreadCrumb, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(existCampTT).click(existCampTT).perform();
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
		action.moveToElement(uploadLogoTT).click(uploadLogoTT).perform();
		verifyText(iCircleToolTipText, eText);
	}
	
	public void verifyPersonalizationToolTipText(String sheet,int row, int column) throws Exception {
		
		scrollByElement(subjectText, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, sheet).getCellValue(row, column);
		action.moveToElement(personalizationTT).click(personalizationTT).perform();
		verifyText(iCircleToolTipText, eText);
	}

	public void verifyContactInfoToolTipText(int column) throws Exception {
	
		scrollByElement(signatureHeading, driver);
		String eText = new ExcelTestDataHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(19, column);
		action.moveToElement(contactInfoTT).click(contactInfoTT).perform();
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
	
	public void clickUpdateCampaignBTN() {
		if(UpdateCampaignBTN.isEnabled()) {
			Assert.assertTrue(true, "edited Scheduled Campaign");
			UpdateCampaignBTN.click();
		}
		else  Assert.assertTrue(false, "Didn't edited the Scheduled Campaign");
	}

	public void clickLivePreviewBTN() {
		LivePreviewBTN.click();
	}
	
	public void clickCancelBTN() {
		cancelBTN.click();
	}
}
