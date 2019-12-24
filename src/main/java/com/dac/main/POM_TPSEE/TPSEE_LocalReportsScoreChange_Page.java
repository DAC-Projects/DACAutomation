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
	public String [] NotificationTabelData;

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
	
	/*-----------Success Popup message for Save*/
	@FindBy(xpath = "//*[@class='notification-success success modal fade in']//div[1]//div//div[3]//button")
	private WebElement SuccessBtnClose;
	
	@FindBy(xpath = "//*[@class='notification-success success modal fade in']//div[1]//div//div[2]/h3")
	private WebElement SuccessMessage;
	
	
	
	
	public void LocalReportScoreChangeNotifiction(String [][] inputData, int excelRow) {
		WebElement emailAddress;
		String strNotificationName=inputData[excelRow][0]; String strEmail=inputData[excelRow][1];
		String strReportname=inputData[excelRow][2]; String strCondition=inputData[excelRow][3];
		String strPercentage=inputData[excelRow][4]; 				
		JSWaiter.waitJQueryAngular();
		
			try {
				waitForElement(notificationTitle, 30);
				waitForElement(notificationName, 30);
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
				
					
				waitForElement(successDialogBox, 20);
				Assert.assertTrue(SuccessMessage.getText().equals("Success! Your request has been completed!"));
				
				clickelement(SuccessBtnClose);
				scrollByElement(notificationList);
				
			
				
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
	
	public String[][] readXcelInput() throws Exception{
				
		String [][] tbl=new ExcelHandler("./data/ReportScoreChangeNotification.xlsx", "Configuration").getExcelTable();
		System.out.println("String Array Excel: "+tbl.length);
		
		return tbl;
		
	}
	public void createEmailNotification(String [][] ExcelData, int newData) {
		
		JSWaiter.waitJQueryAngular();
		System.out.println("createEmailNotification "+ newData);
		loop:
		for(int i=1;i<=10;i++) {
			try {
				deleteEmailNotification(ExcelData,newData);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("No details existing for delete");
				break loop;
			}
		}
			
		LocalReportScoreChangeNotifiction(ExcelData,newData);
		verifyEmailNotification(ExcelData, newData);
		
		System.out.println("Notification Created");
		
	}
	public void verifyEmailNotification(String [][] configuration, int row) {
		String[] data = new String[1];
		JSWaiter.waitJQueryAngular();
		System.out.println("verifyEmailNotification "+ row);
		scrollByElement(NotificationTableHeader);
		int uiRow=getRowIndex(configuration[row][0]);
		System.out.println("uiRow  "+ uiRow);
		data=getNotificationTableData(uiRow);
		
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
	public void editEmailNotification(String [][] ExcelData,int oldData, int newData) {
		WebElement btnEdit;
		JSWaiter.waitJQueryAngular();
		System.out.println("editEmailNotification "+ newData+"Old: "+oldData);
		btnEdit=getEditButtonRow(ExcelData[oldData][0]);
		scrollByElement(btnEdit);
		clickelement(btnEdit);
		LocalReportScoreChangeNotifiction(ExcelData,newData);
		verifyEmailNotification(ExcelData,newData);
		System.out.println("Notification Updated");
		
	}

	public void deleteEmailNotification(String [][] ExcelData, int excelRow) {
		WebElement btnDelete,btnConfirmOK;
		JSWaiter.waitJQueryAngular();
		System.out.println("deleteEmailNotification "+ excelRow);
		
		btnDelete=getDeleteButtonRow(ExcelData[excelRow][0]);
		scrollByElement(btnDelete);
		clickelement(btnDelete);
		waitForElement(confirmDialogBox, 30);
		btnConfirmOK=driver.findElement(By.xpath("//*[@class='bootbox modal fade bootbox-confirm in']//div//div[2]//button[2]"));
		waitForElement(btnConfirmOK, 20);
		scrollByElement(btnConfirmOK);
		clickelement(btnConfirmOK);
	
		waitForElement(successDialogBox,10);
		Assert.assertTrue(SuccessMessage.getText().equals("Success! Your request has been completed!"));
		clickelement(SuccessBtnClose);
		System.out.println("Notification Deleted");
					
	}
	
	@SuppressWarnings("unused")
	private String[] getNotificationTableData(int row) {
		ArrayList<String> Data = new ArrayList<String>();
		
		JSWaiter.waitJQueryAngular();
		scrollByElement(NotificationTableHeader);
		List < WebElement > rows_table = NotificationTableRow;
		int rows_count = rows_table.size();
		
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
	private WebElement getDeleteButtonRow(String columnText) {
		System.out.println("Inside getDeleteButtonRow Method: "+ columnText);
		
		WebElement btnRemove=driver.findElement(By.xpath("//td[text()='"+ columnText +"']/..//button[@class='btn btn-xs btn-danger remove-notification fa fa-remove']"));
		
		return btnRemove;
		
	}
	
	private WebElement getEditButtonRow(String columnText) {
		System.out.println("Inside getEditButtonRow Method: "+columnText );
		
		WebElement btnRemove=driver.findElement(By.xpath("//td[text()='"+ columnText +"']/..//button[@class='btn btn-xs btn-danger edit-notification fa fa-edit']"));
		
		return btnRemove;
		
	}
	
	private int getRowIndex(String columnText) {
		System.out.println("Inside getRowIndex Method: "+columnText );
		int k=0;
			
		 List<WebElement> tableRows = NotificationTable.findElements(By.tagName("tr"));
	        for (int i = 0; i < tableRows.size(); i++) {
	            List<WebElement> tableCols = tableRows.get(i).findElements(By.tagName("td"));
	                for (int j = 0; j < tableCols.size(); j++) {
	                if (tableCols.get(j).getText().equals(columnText)) { 
	                        System.out.println("i = "+i);
	                    
	                    	k = +i;
	                    	break;
	                        }
	                    }
	                }
	    	return k;
	
		
	}
	
	
}
