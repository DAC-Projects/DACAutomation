package com.dac.main.POM_TPSEE;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_LocalReportsScoreChange_Page extends TPSEE_abstractMethods {

	public TPSEE_LocalReportsScoreChange_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}

	/*----------------Locators----------------*/
	
	@FindBy(xpath = "//*[@id='name1']")
	private WebElement notificationName;
	
	@FindBy(xpath = "(//div[@class='col-sm-10'])[2]")
	private WebElement notification;
	
	
//	@FindBy(xpath = "(//div[@class='col-sm-10']//input)[3]']")
//	private WebElement emailAddress;
	
	@FindBy(xpath = "//*[@id='report_options']")
	private WebElement selectReport;
	
	@FindBy(xpath = "//*[@id='Condition1']")
	private WebElement selectCondition;
	
	@FindBy(xpath = "//*[@id='percent']")
	private WebElement percentage;

	
	@FindBy(xpath = "//*[@id='btnSave']")
	private WebElement btnSave;
	
	@FindBy(xpath = "//*[@id='btnCancel']")
	private WebElement btnCancel;
	
	@FindBy(xpath = "//*[@id='loading-spinner']")
	private WebElement spinner;
	
	@FindBy(xpath = "//*[@id='notificationList']")
	private WebElement notificationTitle;
	
	@FindBy(xpath = "//*[@class='notification-success success modal fade in']")
	private WebElement DialogBox;


	public void LocalReportScoreChangeNotifiction(String strNotificationName, String strEmail, 
			String strReportname, String strCondition, String strPercentage) {
		WebElement emailAddress;
		JSWaiter.waitJQueryAngular();
		try {
		waitForElement(notificationTitle, 10);
		notificationName.sendKeys(strNotificationName);
		
		waitForElement(notification, 10);
		emailAddress = notification.findElement(By.xpath("(//div[@class='col-sm-10']//input)[3]"));
		emailAddress.sendKeys(strEmail);
		emailAddress.sendKeys(Keys.ENTER);
		
		waitForElement(selectReport, 10);
		selectValue(selectReport, strReportname);
		
		waitForElement(selectCondition, 10);
		selectValue(selectCondition, strCondition);
		
		waitForElement(percentage, 10);
		percentage.sendKeys(strPercentage);
		savedata();
		
		boolean dialog=DialogBox.isDisplayed();
		System.out.println(" dispalyed box :"+ dialog);
		
		if(DialogBox.isDisplayed()) {
			WebElement msg=driver.findElement(By.xpath("//*[@class='notification-success success modal fade in']//div[1]//div//div[2]/h3"));
			String ms=msg.getText();
			System.out.println(ms);
			Assert.assertTrue(ms.toLowerCase().contains("success"));
		}else {
			Assert.fail("Notification not added");
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Notification not added");
		}
		waitUntilLoad(driver);
		
	}
	
	
	
	private void selectValue(WebElement element, String value) {
		
		Select select = new Select(element);
		select.selectByVisibleText(value);
		
	}
	private void savedata() {
		if(btnSave.isDisplayed()) {
			clickelement(btnSave);
			waitForElement(DialogBox, 10);
		}
	}
	private void readXcelFile() {
		try {
			ExcelHandler wb = new ExcelHandler("./data/GroupAndAccountKeywords.xlsx", "AccountLevelKeywords"); wb.deleteEmptyRows();
			for(int i=1;i<=wb.getRowCount();i++) {
				if(i>1) CurrentState.getDriver().navigate().refresh();
				waitUntilLoad(CurrentState.getDriver());
				String AccKey = wb.getCellValue(i, wb.seacrh_pattern("AccountKeyword", 0).get(0).intValue());
				String GrKey = wb.getCellValue(i, wb.seacrh_pattern("GroupKey", 0).get(0).intValue());
				String GrKeyword = wb.getCellValue(i, wb.seacrh_pattern("GroupKeyword", 0).get(0).intValue());
				//applyKeywords(AccKey , GrKey , GrKeyword);
				System.out.println(AccKey+", "+GrKey+", "+GrKeyword);
				System.out.println();
			//	s.clickApplyKeyword();
				BaseClass.addEvidence(CurrentState.getDriver(),
						"Applied Account and Group Level Keywords: "+AccKey+", "+GrKey+", "+GrKeyword+"", "yes");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
