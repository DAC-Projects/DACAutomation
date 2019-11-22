package com.dac.main.POM_TPSEE;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	@FindBy(xpath = "//*[@id='notificationList']")
	private WebElement notificationList ;
	
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
	private WebElement successDialogBox;
	
	@FindBy(xpath="//*[@id='table_notification']//tr[1]/td[7]")
	private WebElement Actions;
	
	@FindBy(xpath = "//*[@class='bootbox modal fade bootbox-confirm in']")
	private WebElement confirmDialogBox;
	
	/*--------------------Notification List Table------------------*/
	@FindBy(xpath = "//div[@id='table_groups_wrapper']/table")
	private WebElement NotificationTable;
	
	@FindBy(xpath = "//table[@id='table_notification']//thead")
	private WebElement NotificationTableHeader;
	
	@FindBy(xpath = "//table[@id='table_notification']//tbody//tr")
	private List<WebElement> NotificationTableRow;

	public void LocalReportScoreChangeNotifiction(String strNotificationName, String strEmail, 
			String strReportname, String strCondition, String strPercentage) {
		WebElement emailAddress,msg, btnClose;
		JSWaiter.waitJQueryAngular();
		try {
		waitForElement(notificationTitle, 10);
		notificationName.clear();
		notificationName.sendKeys(strNotificationName);
		
		waitForElement(notification, 10);
		emailAddress = notification.findElement(By.xpath("(//div[@class='col-sm-10']//input)[3]"));
		emailAddress.clear();
		emailAddress.sendKeys(strEmail);
		emailAddress.sendKeys(Keys.ENTER);
		
		waitForElement(selectReport, 10);
		selectValue(selectReport, strReportname);
		
		waitForElement(selectCondition, 10);
		selectValue(selectCondition, strCondition);
		
		waitForElement(percentage, 10);
		percentage.clear();
		percentage.sendKeys(strPercentage);
		savedata();
		
		boolean dialog=successDialogBox.isDisplayed();
		System.out.println(" dispalyed box :"+ dialog);
		
		if(successDialogBox.isDisplayed()) {
			msg=driver.findElement(By.xpath("//*[@class='notification-success success modal fade in']//div[1]//div//div[2]/h3"));
			btnClose=driver.findElement(By.xpath("//*[@class='notification-success success modal fade in']//div[1]//div//div[3]//button"));
			String ms=msg.getText();
			System.out.println(ms);
			Assert.assertTrue(ms.toLowerCase().contains("success"));
			clickelement(btnClose);
			scrollByElement(notificationList);
			waitUntilLoad(driver);
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
			waitForElement(successDialogBox, 10);
		}
	}
	
	public String[][] readConfiguration() throws Exception{
				
		String [][] tbl=new ExcelHandler("./data/ReportScoreChangeNotification.xlsx", "Configuration").getExcelTable();
		System.out.println("String Array Excel: "+tbl.length);
		
		return tbl;
		
	}
	public void verifyEmailNotification(String [][] configuration, int row) {
		String[] data = new String[1];
		JSWaiter.waitJQueryAngular();
		scrollByElement(NotificationTableHeader);
		data=getNotificationTableDate(row);
		System.out.println("Notification Name: "+data[0]+" Input Data: "+configuration[row][0]);
		System.out.println("Email: "+data[3]+" Input Data: "+configuration[row][1]);
		System.out.println("Report: "+data[4]+" Input Data: "+configuration[row][2]);
		System.out.println("Condition: "+data[5]+" Input Data: "+configuration[row][3]);
		System.out.println("Percentage: "+data[5]+" Input Data: "+configuration[row][4]);
		
		Assert.assertEquals(data[0], configuration[row][0],"Notification Name verification");
		Assert.assertTrue(data[3].contains(configuration[row][1]),"Email verification");
		Assert.assertEquals(data[4], configuration[row][2],"Report verification");
		Assert.assertTrue(data[5].contains(configuration[row][3]),"Condition verification");
		Assert.assertTrue(data[5].contains(configuration[row][4]),"Percentage verification");
		System.out.println("Notification Verified");
		
	}
	public void editEmailNotification(String [][] configuration) {
		WebElement btnEdit;
		JSWaiter.waitJQueryAngular();
		
		btnEdit=Actions.findElement(By.xpath("//*[@id='table_notification']//tr[1]//td[7]//button[1]"));
		scrollByElement(btnEdit);
		clickelement(btnEdit);
		LocalReportScoreChangeNotifiction(configuration[2][0],configuration[2][1],configuration[2][2],configuration[2][3],configuration[2][4]);
		System.out.println("Notification Updated");
		
	}

	public void deleteEmailNotification() {
		WebElement btnDelete,btnConfirmOK,msg,btnClose;
		JSWaiter.waitJQueryAngular();
		
		btnDelete=Actions.findElement(By.xpath("//*[@id='table_notification']//tr[1]//td[7]//button[2]"));
		scrollByElement(btnDelete);
		clickelement(btnDelete);
		if(confirmDialogBox.isDisplayed()) {
			btnConfirmOK=driver.findElement(By.xpath("//*[@class='bootbox modal fade bootbox-confirm in']//div//div[2]//button[2]"));
			scrollByElement(btnConfirmOK);
			clickelement(btnConfirmOK);
			waitUntilLoad(driver);
		}
		if(successDialogBox.isDisplayed()) {
			msg=driver.findElement(By.xpath("//*[@class='notification-success success modal fade in']//div[1]//div//div[2]/h3"));
			btnClose=driver.findElement(By.xpath("//*[@class='notification-success success modal fade in']//div[1]//div//div[3]//button"));
			String ms=msg.getText();
			System.out.println(ms);
			Assert.assertTrue(ms.toLowerCase().contains("success"));
			clickelement(btnClose);
			waitUntilLoad(driver);
		}else {
			Assert.fail("Notification not Deleted");
		}
		System.out.println("Notification Deleted");
		
	}
	
	@SuppressWarnings("unused")
	private String[] getNotificationTableDate(int row) {
		ArrayList<String> Data = new ArrayList<String>();
		
		JSWaiter.waitJQueryAngular();
		scrollByElement(NotificationTableHeader);
		List < WebElement > rows_table = NotificationTableRow;
		List < WebElement > Columns_row = rows_table.get(row-1).findElements(By.tagName("td"));
		List < WebElement > headerTableRow=NotificationTableHeader.findElements(By.tagName("th"));
		int columns_count = Columns_row.size();	
		String headerText = "" , celtext ="";
		for (int column = 0; column < columns_count; column++) {
			headerText = headerTableRow.get(column).getText().toString();
			celtext = Columns_row.get(column).getText().trim();
			Data.add(celtext);
		}
		System.out.println("Values from Table : "+Data);
		String[] str = GetStringArray(Data); 
		return str;
	}
	
	public static String[] GetStringArray(ArrayList<String> arr) 
    { 
  
        // declaration and initialise String Array 
        String str[] = new String[arr.size()]; 
  
        // ArrayList to Array Conversion 
        for (int j = 0; j < arr.size(); j++) { 
  
            // Assign each value to String array 
            str[j] = arr.get(j); 
        } 
  
        return str; 
    }
}
