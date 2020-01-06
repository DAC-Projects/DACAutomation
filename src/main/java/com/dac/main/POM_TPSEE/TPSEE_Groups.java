package com.dac.main.POM_TPSEE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TPSEE_Groups extends TPSEE_abstractMethods {
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	TPSEE_GoogleRanking_Page data;
	
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
	private WebElement Descrip;
	
	@FindBy(xpath = "//select[@id='select-field-1']")
	private WebElement Field;
	
	@FindBy(xpath = "//select[@id='select-condition-1']")
	private WebElement Condition;
	
	@FindBy(xpath = "//input[@id='search-item-1']")
	private WebElement SearchText;
	
	@FindBy(xpath = "//input[@id='search-item-2']")
	private WebElement SearchText1;
	
	@FindBy(xpath = "//input[@id='search-item-3']")
	private WebElement SearchText2;
	
	@FindBy(xpath = "//button[@id='loading-btn']")
	private WebElement SaveBtn;
	
	@FindBy(xpath = "//button[@id='btnPreviewGroup']")
	private WebElement PreviewBtn;
	
	/*---------------------- View Group ----------------*/
	
	@FindBy(xpath="//div[@class = 'rule-filter-container']")
	private WebElement First_field;
	
	@FindBy(xpath="//div[@class = 'rule-operator-container']")
	private WebElement Second_field;
	
	@FindBy(xpath="//div[@class = 'rule-value-container']")
	private WebElement Third_field;
	
	@FindBy(xpath="(//button[contains(@class, 'close')])[1]")
	private WebElement close_btn;
	/*---------------------- View Group ----------------*/
	
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
	
	/*-------------------------To Edit-----------------------*/
	/*@FindBy(xpath = "//td[text()='country_group_F']/..//*[@class='edit-group']")
	private WebElement Edit_btn;*/
	
	
	/*-------------------------To Edit----------------------*/
	
	/*-------------------------Add Filter-----------------------*/
	
	@FindBy(xpath="//*[@id='addFilter']")
	private WebElement filter_btn;
	
	
	/*-------------------------Add Filter----------------------*/
	
	/*-------------------------To Delete--------------------*/
	
	/*@FindBy(xpath = "//td[text()='country_group_F']/..//*[@class='edit-group']")
	private WebElement Delete_btn;*/
	
	@FindBy(xpath="//button[@data-bb-handler='confirm']")
	private WebElement Ok_btn;
	/*-------------------------To Delete-----------------------*/
	
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
	
	
	
	public void create_Group(String Group, String Description, String CountryField, String FilterCondition, String Search_Term) throws Exception
	{
		
		
		
		GroupName.sendKeys(Group);
		Descrip.sendKeys(Description);
		Select country = new Select(driver.findElement(By.xpath("//*[@id='select-field-1']")));
		Thread.sleep(5000);
		country.selectByVisibleText(CountryField);
		Thread.sleep(5000);
		Select Condition1 = new Select(driver.findElement(By.xpath("//*[@id='select-condition-1']")));
		//Thread.sleep(5000);
		Condition1.selectByVisibleText(FilterCondition);
	//	Thread.sleep(5000);				
		SearchText.sendKeys(Search_Term);	
		
		/*driver.findElement(By.xpath("//button[@id='addFilter']")).click();
		Thread.sleep(5000);
		//*[@id='select-field-2']
		
		Select country1 = new Select(driver.findElement(By.xpath("//*[@id='select-field-2']")));
		Thread.sleep(5000);
		country1.selectByVisibleText(CountryField);
		Thread.sleep(5000);
		Select Condition2 = new Select(driver.findElement(By.xpath("//*[@id='select-condition-2']")));
		Thread.sleep(5000);
		Condition2.selectByVisibleText(FilterCondition);*/
		//Thread.sleep(5000);				
		//SearchText.sendKeys(Search_Term);	
		/*SearchText1.sendKeys(Search_Term);*/
		clickelement(PreviewBtn);
		clickelement(SaveBtn);
		
		
	}
	
	public ArrayList<String> view_Group(String Groupname) throws Exception
	{
		
		ArrayList<String> rules = new ArrayList();
		WebElement View_btn=driver.findElement(By.xpath("//a[@class='view-group'][@data-name='"+Groupname+"']"));
		Thread.sleep(5000);	
		//save.click();
		View_btn.click();
		Thread.sleep(5000);
		String x = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[0].value").toString();
		System.out.println(x);
		rules.add(x);
		Thread.sleep(5000);
		String y = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[1].value").toString();;
		System.out.println(y);
		rules.add(y);
		Thread.sleep(5000);
		String z = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[2].value").toString();;
		System.out.println(z);
		rules.add(z);	
		clickelement(close_btn);
		return rules;
		
		
	}
	
	
	public void edit_Group(String Groupname) throws InterruptedException
	{
		
		WebElement Edit_btn=driver.findElement(By.xpath("//*[@id='btnEdit'][@value='"+Groupname+"']"));
		Thread.sleep(5000);
		
		//save.click();
		Edit_btn.click();	
		
		Thread.sleep(5000);
		clickelement(SaveBtn);
	}
	
	public void addFilter_Group(String Group, String Description, String CountryField, String FilterCondition, String Search_Term, String CountryField1, String FilterCondition1, String Search_Term1, String CountryField2, String FilterCondition2, String Search_Term2) throws InterruptedException
	{
		
		GroupName.sendKeys(Group);
		Descrip.sendKeys(Description);
		Select country = new Select(driver.findElement(By.xpath("//*[@id='select-field-1']")));
		Thread.sleep(5000);
		country.selectByVisibleText(CountryField);
		Thread.sleep(5000);
		Select Condition1 = new Select(driver.findElement(By.xpath("//*[@id='select-condition-1']")));
		Thread.sleep(5000);
		Condition1.selectByVisibleText(FilterCondition);
		Thread.sleep(5000);				
		SearchText.sendKeys(Search_Term);	
		
		driver.findElement(By.xpath("//button[@id='addFilter']")).click();
		Thread.sleep(5000);
		//*[@id='select-field-2']
		
		Select country1 = new Select(driver.findElement(By.xpath("//*[@id='select-field-2']")));
		Thread.sleep(5000);
		country1.selectByVisibleText(CountryField1);
		Thread.sleep(5000);
		Select Condition2 = new Select(driver.findElement(By.xpath("//*[@id='select-condition-2']")));
		Thread.sleep(5000);
		Condition2.selectByVisibleText(FilterCondition1);
		Thread.sleep(5000);				
		//SearchText.sendKeys(Search_Term);	
		SearchText1.sendKeys(Search_Term1);
		

		driver.findElement(By.xpath("//button[@id='addFilter']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='formRadio2']")).click();
		
		Select country2 = new Select(driver.findElement(By.xpath("//*[@id='select-field-3']")));
		Thread.sleep(5000);
		country2.selectByVisibleText(CountryField2);
		Thread.sleep(5000);
		Select Condition3 = new Select(driver.findElement(By.xpath("//*[@id='select-condition-3']")));
		Thread.sleep(5000);
		Condition3.selectByVisibleText(FilterCondition2);
		Thread.sleep(5000);				
		//SearchText.sendKeys(Search_Term);	
		SearchText2.sendKeys(Search_Term2);
		
		clickelement(PreviewBtn);
		clickelement(SaveBtn);
		
		
		
		
	}
	
	public void delete_Group(String Group)
	{
		WebElement Delete_btn=driver.findElement(By.xpath("//*[@class='remove-group'][@data-name= '"+Group+"']"));
		Delete_btn.click();
		Ok_btn.click();
	}
	
	public ArrayList<String> verification(String Groupname) throws Exception
	{
		ArrayList<String> rules1 = new ArrayList();
		
	
		
		WebElement View_btn=driver.findElement(By.xpath("//a[@class='view-group'][@data-name='"+Groupname+"']"));
		Thread.sleep(5000);	
		//save.click();
		View_btn.click();
		Thread.sleep(5000);
		
	
		String x2 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[3].value").toString();
		System.out.println(x2);
		rules1.add(x2);
		Thread.sleep(5000);
		String y2 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[4].value").toString();;
		System.out.println(y2);
		rules1.add(y2);
		Thread.sleep(5000);
		String z2 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[5].value").toString();;
		System.out.println(z2);
		rules1.add(z2);
		
		String x1 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[0].value").toString();
		System.out.println(x1);
		rules1.add(x1);
		Thread.sleep(5000);
		String y1 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[1].value").toString();;
		System.out.println(y1);
		rules1.add(y1);
		Thread.sleep(5000);
		String z1 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[2].value").toString();;
		System.out.println(z1);
		rules1.add(z1);	
		
			
		
		String x3 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[6].value").toString();
		System.out.println(x3);
		rules1.add(x3);
		Thread.sleep(5000);
		String y3 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[7].value").toString();;
		System.out.println(y3);
		rules1.add(y3);
		Thread.sleep(5000);
		String z3 = ((JavascriptExecutor)driver).executeScript("return document.getElementsByName('builder-basic_rule_0_value_0')[8].value").toString();;
		System.out.println(z3);
		rules1.add(z3);	
		
		clickelement(close_btn);
		return rules1;
	}
	
		/*	//getting the data from the table 
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
			}*/
}
