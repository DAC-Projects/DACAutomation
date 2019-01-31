package com.dac.main.POM_CF;

import java.awt.AWTException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.dac.main.BasePage;


import resources.ExcelHandler;
import resources.IAutoconst;

public class DetailsPage_CF extends BasePage{

	WebDriver driver;
	Select select;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	public static Date startDate, targetDate;
	public static String startDateFormat, endDateFormat;
	static SimpleDateFormat engDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public DetailsPage_CF(WebDriver driver) throws AWTException {
		super(driver);
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
	
	/** 
	 * Verifying whether Campaign Language field is disabled or not	
	 * @throws InterruptedException */
	public void verifyCampTypeStatus() throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(campaignType));
		scrollByElement(customerFeedbackBreadCrumb);
		while(true) {
			if(campaignType.isEnabled()) {
				Assert.fail("campaign Type should not be enabled");
				break;
			}	
			else if(!campaignType.isEnabled()){
				Assert.assertTrue(true, "Campaign type is NOT enabled");
				break;
			}
		}
	}
	
	/** 
	 * Verifying whether Campaign Language field is disabled or not	*/
	public void verifyCampLangStatus() {
		scrollByElement(customerFeedbackBreadCrumb);
		if(campaignLang.isEnabled()) {
			Assert.fail("campaign Language should not be enabled");
		}	
		else if(!campaignLang.isEnabled()){
			Assert.assertTrue(true, "Campaign Language is NOT enabled");
		}
	}
	
	/** 
	 * This method is used to enter the data into the Campaign name Text Field
	 * @throws Exception 
	 * @column : Enter the column number of campaign name 	   */
	public void verifyCampNameFieldstatus(String sheet, int row, int column) throws Exception {
		scrollByElement(campaignInfoSection);
		if(campaignName.isEnabled()) {
			Assert.fail("campaign Name field should not be enabled");
		}	
		else if(!campaignName.isEnabled()){
			Assert.assertTrue(true, "Campaign Name field is NOT enabled");
		}	
	}
	
	/** 
	 * This method used to select the Location for the Campaign Type : Location
	 * @throws Exception 
	 * @locIndex : To select particular location from selected location name contains @locName based on Index.
	 * @column   : column number of excel sheet to get the location name		 */
	public void selectCampaignLoc(int locIndex, int column) throws Exception {
		scrollByElement(campaignInfoSection);
		if(campaignLocTB.isEnabled()) {
			Assert.fail("campaign Name field should not be enabled");
		}	
		else if(!campaignLocTB.isEnabled()){
			Assert.assertTrue(true, "Campaign Name field is NOT enabled");
		}	
	}
	
	public String setCampaignBrandName(int column) throws Exception {
		scrollByElement(campaignInfoSection);
		if(campaignBrandNameTB.isEnabled()) {
			Assert.fail("campaign Name field should not be enabled");
		}	
		else if(!campaignBrandNameTB.isEnabled()){
			Assert.assertTrue(true, "Campaign Name field is NOT enabled");
		}
		String campBrandName = new ExcelHandler(IAutoconst.RS_XL_PATH, "Brand").getCellValue(7, column);
		String BrandName= campBrandName+" - "+getDate();
		campaignBrandNameTB.sendKeys(BrandName);
		return BrandName;
	}
}
