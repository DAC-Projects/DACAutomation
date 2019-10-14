package com.dac.main.POM_TPSEE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.JSWaiter;

public class TPSEE_Groups extends TPSEE_abstractMethods {
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	//Navigating to TPSEE Content_Analysis page
	public TPSEE_Groups(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/* ------------------------------Locators---------------------------------------*/
	
	@FindBy(xpath = "//*[@id='form-field-groupName']")
	private WebElement GroupName;
	
	@FindBy(xpath = "//*[@id='form-field-groupDesc']")
	private WebElement Description;
	
	@FindBy(xpath = "//select[@id='select-field-1']")
	private WebElement Field;
	
	@FindBy(xpath = "//select[@id='select-condition-1']")
	private WebElement Condition;
	
	@FindBy(xpath = "//input[@id='search-item-1']")
	private WebElement SearchText;
	
	@FindBy(xpath = "//button[@id='loading-btn']")
	private WebElement SaveBtn;
	
	@FindBy(xpath = "//button[@id='btnPreviewGroup']")
	private WebElement PreviewBtn;
	
	/*-------------------------Table Info-----------------------*/
	@FindBy(xpath = "//*[@id='GroupTable']")
	private WebElement GroupTable;
	
	@FindBy(xpath = "//*[@id='grouptableInfo']//table")
	private WebElement GroupTableInfo;
	
	@FindBy(xpath = "//*[@id='table_groups']/thead/tr")
	private WebElement TableHead;
	
	@FindBy(xpath = "//*[@id='table_groups_info']")
	private WebElement totalentries;
	
	@FindBy(xpath = "//*[@id='table_groups']/tbody/tr")
	private List<WebElement> TableRow;
	/*-------------------------Table Info-----------------------*/
	
	/*-------------------------Pagination-----------------------*/
	@FindBy(xpath = "(//*[@class='pagination'])")
	private List<WebElement> pagination;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private List<WebElement> paginationLast;
	/*-------------------------Pagination-----------------------*/
	
	/* ------------------------------Locators---------------------------------------*/
	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}

	
			//getting the data from the table 
			public List<Map<String, String>> getTableData() throws InterruptedException{
				JSWaiter.waitJQueryAngular();
				waitForElement(GroupTable,40);
				scrollByElement(GroupTable);
				waitForElement(GroupTableInfo,50);
				scrollByElement(GroupTableInfo);
				JSWaiter.waitJQueryAngular();
				waitForElement(totalentries,50);
				waitForElement(GroupTableInfo,50);
				String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
				int page = Integer.parseInt(n);
				System.out.println("\n"+page);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
				String entiresText = driver.findElement(By.className("dataTables_info")).getText();
				entiresText = entiresText.substring(entiresText.indexOf("("));
				WebElement TableTitle = driver.findElement(By.xpath("//*[@id='GroupTable']/div[1]"));
				scrollByElement(TableTitle);
				int count = 0;
				if(paginationNext.isDisplayed()) {
					for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
						scrollByElement(TableTitle);
						List < WebElement > rows_table = TableRow;	//To locate rows of table. 
						int rows_count = rows_table.size();		//To calculate no of rows In table.
						count = count + rows_count;
						Map<String, String> kMap = new HashMap<String, String>();
						for (int row = 0; row < rows_count; row++) { 
							List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
							int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
							int noOfRows=row+1;
							//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
							for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
								List<WebElement> headerTableRow=TableHead.findElements(By.tagName("th"));
								String headerText = headerTableRow.get(column).getText(), celtext ="";
								if(column==1 & row < rows_count) {
									celtext = driver.findElement(By.xpath("(//*[@id='table_groups']/tbody/tr)["+ (row+1) +"]")).getText();
									System.out.println("\n"+celtext);
								}
								kMap.put("rowdata", celtext);
								tableCellValues.add(kMap);
								//System.out.println("Cell Value of row " + noOfRows + " and column " + headerText + " Is : " + celtext);
							}	
							//System.out.println("-------------------------------------------------- ");
						}
						if(paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}
				System.out.println("Total number of entries in table : "+count);
				Assert.assertTrue(entiresText.contains(""+count+""), "Table Data count matches with total enties count");
				return tableCellValues;
			}
}
