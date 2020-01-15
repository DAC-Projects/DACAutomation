package com.dac.main.POM_TPSEE;

import java.util.ArrayList;
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

import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_ReviewNotifications_Page extends TPSEE_abstractMethods {
	
	public TPSEE_ReviewNotifications_Page(WebDriver driver) {
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
	/*-------------Locators----------*/
	
	@FindBy(xpath = "//*[@id='Name']")
	private WebElement notificationName;
	
	@FindBy(xpath = "//*[@id='Desc']")
	private WebElement description;
	
	@FindBy(xpath = "//div[@class='selectize-input items required not-full has-options has-items']")
	private WebElement emailAddressColumn;
	
	@FindBy(xpath = "(//div[@class='col-sm-10']//input)[3]")
	private WebElement emailAddressEmpty;
	
	@FindBy(xpath = "//div[@class='selectize-input items required not-full has-options has-items']/div/a")
	private WebElement emailAddressAdded;
	
	@FindBy(xpath = "//*[@id='myGroups_chzn']")
	private WebElement GroupFilter;
	
	@FindBy(xpath = "(//div[@class='chzn-drop'])[1]")
	private WebElement groupDropdown;
	
	@FindBy(xpath = "(//div[@class='chzn-drop'])[1]/ul/li[2]")
	private WebElement allGroups;

	
	@FindBy(xpath = "//*[@id='scoreType_chzn']")
	private WebElement SiteFilter;
	
	@FindBy(xpath = "(//div[@class='chzn-drop'])[2]")
	private WebElement siteDropdown;
	
	@FindBy(xpath = "(//div[@class='chzn-drop'])[2]/ul/li[1]")
	private WebElement allSites;
	
	@FindBy(xpath = "//*[@id='Frequency']")
	private WebElement selectFrequencyFilter;
	
	@FindBy(xpath = "//*[@id='Condition']")
	private WebElement selectConditionFilter;
	
	@FindBy(xpath = "//*[@id='Score']")
	private WebElement ratingScore;
	
	@FindBy(xpath = "//*[@id='btnSave']")
	private WebElement btnSave;
	
	@FindBy(xpath = "//*[@id='btnCancel']")
	private WebElement btnCancel;
	
	@FindBy(xpath = "//*[@class='bootbox modal fade bootbox-confirm in']")
	private WebElement confirmDialogBox;
	
	@FindBy(xpath = "(//ul[@class='chzn-choices'])[2]")
	private WebElement SiteList;
	
	@FindBy(xpath = "(//ul[@class='chzn-choices'])[2]/li/a")
	private WebElement siteListSelectedItem;
	
	@FindBy(xpath = "(//ul[@class='chzn-choices'])[1]")
	private WebElement GroupList;
	
	@FindBy(xpath = "(//ul[@class='chzn-choices'])[1]/li/a")
	private WebElement groupListSelectedItem;
	
	@FindBy(xpath = "//div[contains(text(),'All Reviews')]")
	private WebElement customAllReviews;
	
	@FindBy(xpath = "//div[contains(text(),'Less-than 3 stars')]")
	private WebElement customLessThan3stars;
	
	/*-----------Table Locators----------*/
	
	@FindBy(xpath = "//*[@id='notificationList']")
	private WebElement notificationTableTitle;
	
	@FindBy(xpath = "//table[@id='table_notification']//thead")
	private WebElement NotificationTableHeader;
	
	@FindBy(xpath = "//table[@id='table_notification']//tbody//tr")
	private List<WebElement> NotificationTableRow;
	
	/*---------------------Dialog Boxes-----------------*/
	@FindBy(xpath = "//*[@class='notification-success success modal fade in']")
	private WebElement successDialogBox;
	/*-----------Success Popup message for Save*/
	@FindBy(xpath = "//*[@class='notification-success success modal fade in']//div[1]//div//div[3]//button")
	private WebElement SuccessBtnClose;
	
	@FindBy(xpath = "//*[@class='notification-success success modal fade in']//div[1]//div//div[2]/h3")
	private WebElement SuccessMessage;
	
	/*-------------End Locators----------*/
	
	public String[][] readXcelInput() throws Exception{
		
		String [][] tbl=new ExcelHandler("./data/ReviewNotification.xlsx", "Configuration").getExcelTable();
		System.out.println("String Array Excel: "+tbl.length);
		
		return tbl;
		
	}
	
	public void createAndVerifyEmailNotification(String [][] ExcelData, int newData) throws InterruptedException {

		
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
			
		CreateReviewNotification(ExcelData,newData);
		
		verifyEmailNotification(ExcelData, newData);
		
		System.out.println("Notification Created");
		
	}

	public void createCustomNotification(String [][] inputData,String Customtext) throws InterruptedException {
		
		if (Customtext.equals("All Reviews")) {
			clickelement(customAllReviews);
			Thread.sleep(2000);
		}else if(Customtext.equals("Less-than 3 stars")) {
			clickelement(customLessThan3stars);
			Thread.sleep(2000);
		}
		scrollByElement(btnSave);
		clickelement(btnSave);
		Thread.sleep(2000);
		
	}
	
	public void CreateReviewNotification(String [][] inputData, int excelRow) throws InterruptedException {

		String strNotificationName=inputData[excelRow][0]; String strDesc=inputData[excelRow][1];
		String strEmail=inputData[excelRow][2]; String strFilter1=inputData[excelRow][3];
		String strSites=inputData[excelRow][4]; String strFrequency=inputData[excelRow][5];
		String strCondition=inputData[excelRow][6]; String strRating=inputData[excelRow][7]; 				
		
				JSWaiter.waitJQueryAngular();
				waitForElement(notificationTableTitle, 30);
				notificationName.clear();
				notificationName.sendKeys(strNotificationName);
				description.clear();
				description.sendKeys(strDesc);
				
				GroupFilter.click();
				
				driver.findElement(By.xpath("//li[contains(text(),'" + strFilter1 + "')]")).click();
				
				waitForElement(SiteFilter, 10);
				SiteFilter.click();
				driver.findElement(By.xpath("//li[contains(text(),'" + strSites + "')]")).click();
				
				selectValue(selectFrequencyFilter, strFrequency);
				
				selectValue(selectConditionFilter, strCondition);
				
				setRating(strRating.toString());
				
				scrollByElement(notificationTableTitle);
				
				waitUntilLoad(driver);
		
	}
	
	public void EditReviewNotification(String [][] inputData, int excelRow) throws InterruptedException {

		String strNotificationName=inputData[excelRow][0]; String strDesc=inputData[excelRow][1];
		String strEmail=inputData[excelRow][2]; String strFilter1=inputData[excelRow][3];
		String strSites=inputData[excelRow][4]; String strFrequency=inputData[excelRow][5];
		String strCondition=inputData[excelRow][6]; String strRating=inputData[excelRow][7]; 				
		
				JSWaiter.waitJQueryAngular();
				waitForElement(notificationTableTitle, 30);
				notificationName.clear();
				notificationName.sendKeys(strNotificationName);
				description.clear();
				description.sendKeys(strDesc);
				
				waitForElement(emailAddressColumn, 10);
				
				/*---------------clear all selected email from email column--------------- */
				
				List<WebElement> emails = emailAddressColumn.findElements(By.tagName("div"));
				System.out.println("Number of emails selected>>  "+emails.size());
				for (int i = 0; i < emails.size(); i++)
				{
				    Thread.sleep(1000);
				    emailAddressAdded.click();

				}
				
				/*---------------clear all selected groups from Filter column--------------- */
				List<WebElement> groups = GroupList.findElements(By.tagName("a"));
				System.out.println("Number of groups selected>>  "+groups.size());
				for (int i = 0; i < groups.size(); i++)
				{
				    Thread.sleep(1000);
				    groupListSelectedItem.click();
				}
				
				/*---------------clear all Sites groups from Filter column--------------- */
				List<WebElement> sites = SiteList.findElements(By.tagName("a"));
				System.out.println("Number of sites selected>>  "+sites.size());
				for (int i = 0; i < sites.size(); i++)
				{
				    Thread.sleep(1000);
				    siteListSelectedItem.click();
				}
				
				emailAddressEmpty.sendKeys(strEmail);
				emailAddressEmpty.sendKeys(Keys.ENTER);
				
				GroupFilter.click();
				driver.findElement(By.xpath("//li[contains(text(),'" + strFilter1 + "')]")).click();
				
				SiteFilter.click();
				SiteFilter.click();
				driver.findElement(By.xpath("//li[contains(text(),'" + strSites + "')]")).click();
				
				selectValue(selectFrequencyFilter, strFrequency);
				
				selectValue(selectConditionFilter, strCondition);
				
				setRating(strRating.toString());
				
				Thread.sleep(1000);
				
				scrollByElement(notificationTableTitle);
				
				waitUntilLoad(driver);
		
	}
	public void verifyEmailNotification(String [][] configuration, int row) {
		String[] data = new String[1];
		JSWaiter.waitJQueryAngular();
		System.out.println("verifyEmailNotification "+ row);
		scrollByElement(notificationTableTitle);
		int uiRow=getRowIndex(configuration[row][0]);
		System.out.println("uiRow  "+ uiRow);
		data=getNotificationTableData(uiRow);
		
		System.out.println("Notification Name: "+data[0]+">> Input Data: "+configuration[row][0]);
		System.out.println("Desc: "+data[1]+">> Input Data[1]: "+configuration[row][1]);
		System.out.println("Email: "+data[4]+" Input Data[2]: "+configuration[row][2]);
		System.out.println("Groups: "+data[6]+">> Input Data[3]: "+configuration[row][3]);
		System.out.println("Sites: "+data[6]+">> Input Data[4]: "+configuration[row][4]);
		System.out.println("Frequency: "+data[5]+">> Input Data[5]: "+configuration[row][5]);
		System.out.println("Condition: "+data[6]+">> Input Data[6]: "+configuration[row][6]);
		System.out.println("Rating: "+data[6]+">> Input Data[7]: "+configuration[row][7]);
		
		Assert.assertEquals(data[0], configuration[row][0],"Notification Name verification");
		Assert.assertTrue(data[1].contains(configuration[row][1]),"Description verification");
		Assert.assertTrue(data[4].contains(configuration[row][2]),"email verification");
		Assert.assertTrue(data[6].contains(configuration[row][3]),"Filter(Groups) verification");
		Assert.assertTrue(data[5].toLowerCase().contains(configuration[row][5]),"Frequency verification");
		Assert.assertTrue(data[6].contains(configuration[row][6]+configuration[row][7]),"Condition and Rating verification");
		System.out.println("Notification Verified");
		
	}
	public void editAndVerifyNotification(String [][] ExcelData,int oldData, int newData) throws InterruptedException {
		WebElement btnEdit;
		JSWaiter.waitJQueryAngular();
		System.out.println("editEmailNotification "+ newData+" Old: "+oldData);
		btnEdit=getEditButtonRow(ExcelData[oldData][0]);
		scrollByElement(btnEdit);
		clickelement(btnEdit);
		EditReviewNotification(ExcelData,newData);
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
	
		System.out.println("Notification Deleted");
					
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
	private void selectValue(WebElement element, String value) {
		
		Select select = new Select(element);
		select.selectByVisibleText(value);
		
	}

		public void setRating(String stars) throws InterruptedException
		{
			WebElement star= driver.findElement(By.xpath("//img[@alt='" + stars + "']"));
			star.click();
			Thread.sleep(3000);
			clickelement(btnSave);
		}

		private int getRowIndex(String columnText) {
			System.out.println("Inside getRowIndex Method: "+columnText );
			int k=0;
				
			 List<WebElement> tableRows = notificationTableTitle.findElements(By.tagName("tr"));
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
}

