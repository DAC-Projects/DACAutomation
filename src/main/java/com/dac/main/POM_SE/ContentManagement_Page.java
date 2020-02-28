package com.dac.main.POM_SE;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import junit.framework.Assert;
import resources.BaseClass;
import resources.CurrentState;

public class ContentManagement_Page extends BasePage {


	WebDriver driver;
	Actions action;
	WebDriverWait wait;


	public ContentManagement_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}


	/*-----------------------------------Unpublished Part-----------------------------------------------------*/

	@FindBy(xpath = "//input[@id='txtSearchTextUnpublished']")
	private WebElement UnpublishedTextSearch;

	@FindBy(xpath = "//select[@id='ddlTypeUnpublished']")
	private WebElement UnpublishedType;

	@FindBy(xpath = "//select[@id='ddlStatusUnpublished']")
	private WebElement UnpublishedStatus;

	@FindBy(xpath = "//table[@id='tblUnpublishedItems']")
	private WebElement UnpublishedTable;

	@FindBy(xpath = "//table[@id='tblUnpublishedItems']//tbody//tr")
	private List<WebElement> UnpublishedTableRow;

	@FindBy(xpath = "//button[@id='btnUnpublished']")
	private WebElement UnpublishedSearch;

	@FindBy(xpath = "//button[@class='btn btn-xs btn-success btn-block btn-approve']")
	private WebElement ApproveButton;

	@FindBy(xpath = "//button[@class='btn btn-xs btn-warning btn-block btn-reject']")
	private WebElement RejectButton;

	@FindBy(xpath = "//a[@class='btn btn-xs btn-default btn-block link-edit']")
	private WebElement EditLink;

	@FindBy(xpath = "//button[@class='btn btn-xs btn-danger btn-block btn-delete']")
	private WebElement DeleteButton;

	/*-----------------------------------Unpublished Part-----------------------------------------------------*/

	/*-------------------------Unpublished Pagination-----------------------*/
	@FindBy(xpath = "(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)")
	private List<WebElement> unpagination;

	@FindBy(xpath = "(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[1]")
	private WebElement unpaginationPrev;

	@FindBy(xpath = "(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()]")
	private WebElement unpaginationNext;

	@FindBy(xpath = "(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]")
	private List<WebElement> unpaginationLast;
	/*-------------------------Unpublished Pagination-----------------------*/


	/*-----------------------------------Published Part-----------------------------------------------------*/

	@FindBy(xpath = "//*[@id='formPublished']")
	private WebElement PublishedSection;

	@FindBy(xpath = "//*[@class='container-fluid']//input[@id='txtSearchTextPublished']")
	private WebElement PublishedTextSearch;

	@FindBy(xpath = "//*[@class='container-fluid']//select[@id='ddlTypePublished']")
	private WebElement PublishedType;

	@FindBy(xpath = "//*[@class='container-fluid']//a[@class='btn btn-xs btn-primary btn-block link-view']")
	private WebElement PublishedView;

	@FindBy(xpath = "//*[@class='container-fluid']//button[@id='btnPublished']")
	private WebElement PublishedSearch;

	@FindBy(xpath = "//*[@class='container-fluid']//table[@id='tblPublishedItems']")
	private WebElement PublishedTable;

	@FindBy(xpath = "//*[@class='container-fluid']//table[@id='tblPublishedItems']//tbody//tr")
	private List<WebElement> PublishedTableRow;

	/*-----------------------------------Published Part-----------------------------------------------------*/

	/*-------------------------Published Pagination-----------------------*/
	@FindBy(xpath = "(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)")
	private List<WebElement> Pubpagination;

	@FindBy(xpath = "(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[1]")
	private WebElement PubpaginationPrev;

	@FindBy(xpath = "(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()]")
	private WebElement PubpaginationNext;

	@FindBy(xpath = "(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()-1]")
	private List<WebElement> PubpaginationLast;
	/*-------------------------Published Pagination-----------------------*/


	/**
	 * This is enter the keyword to search in unpublished section
	 * @param Text
	 */
	public void SearchUnpublishedUsingKeyword(String Text) {
		scrollByElement(UnpublishedTextSearch);
		UnpublishedTextSearch.sendKeys(Text);
		clickelement(UnpublishedSearch);
	}

	/**
	 * This is to search the text with the keyword entered
	 * @param Text
	 * @throws Exception 
	 */
	public void SearchUnpublishedKeyinTable(String Text) throws Exception {
		scrollByElement(UnpublishedTable);
		String n = driver.findElement(By.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("Last Page Number of Unpublished Table is : " +page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
		String entiresText = driver.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")).getText();
		System.out.println("The total entries in a table is :" +entiresText);
		int count = 0;
		boolean celtext = false;
		for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
			scrollByElement(UnpublishedTable);
			List < WebElement > rows_table = UnpublishedTableRow;	//To locate rows of table. 
			int rows_count = rows_table.size();		//To calculate no of rows In table.
			count = count + rows_count;
			for (int row = 1; row < rows_count; row++) { 
				List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
				int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
				for (int col = 1; col < columns_count; col++) {	    				
					if(celtext = driver.findElement(By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)["+ (row) +"]//td["+ (col+1) +"]")).getText().contains(Text)); 
					celtext = true;
					break;    					
				}
			}if(unpaginationNext.isEnabled()) {
				scrollByElement(unpaginationNext);
				unpaginationNext.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}
		UnpublishedTextSearch.clear();
		clickelement(UnpublishedSearch);
		BaseClass.addEvidence(CurrentState.getDriver(), "To clear the text field at the end of the opration", "Yes");
	}

	/**
	 * To select type of unpublished content
	 * @param Type
	 */
	public void SearchUnpublishedUsingType(String Type) {
		scrollByElement(UnpublishedType);
		Select Types = new Select(UnpublishedType);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Types.selectByVisibleText(Type);
		clickelement(UnpublishedSearch);
	}

	/**
	 * To search data using type in unpublished table
	 * @param Type
	 * @throws Exception
	 */
	public void SearchUnpublishedTypeinTable(String Type) throws Exception {
		scrollByElement(UnpublishedTable);
		String n = driver.findElement(By.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("Last Page Number of Unpublished Table is : " +page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
		String entiresText = driver.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")).getText();
		System.out.println("The total entries in a table is :" +entiresText);
		int count = 0;
		for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
			scrollByElement(UnpublishedTable);
			List < WebElement > rows_table = UnpublishedTableRow;	//To locate rows of table. 
			int rows_count = rows_table.size();		//To calculate no of rows In table.
			count = count + rows_count;
			for (int row = 1; row < rows_count; row++) { 
				String celtext = driver.findElement(By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)["+ (row) +"]//td[6]//strong[1]")).getText(); 
				System.out.println("CellText is :" +celtext);
				System.out.println("Type selected is :" +Type);
				assertTrue(Type.contains(celtext));
			}if(unpaginationNext.isEnabled()) {
				scrollByElement(unpaginationNext);
				unpaginationNext.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}
		Select Types = new Select(UnpublishedType);
		Types.selectByVisibleText("All");
		clickelement(UnpublishedSearch);
		BaseClass.addEvidence(CurrentState.getDriver(), "To clear the text field at the end of the opration", "Yes");
	}


	
	/**
	 * This is enter the keyword to search in unpublished section
	 * @param Text
	 */
	public void SearchPublishedUsingKeyword(String Text) {
		scrollByElement(PublishedTextSearch);
		PublishedTextSearch.sendKeys(Text);
		clickelement(PublishedSearch);
	}

	/**
	 * This is to search the text with the keyword entered
	 * @param Text
	 * @throws Exception 
	 */
	public void SearchPublishedKeyinTable(String Text) throws Exception {
		scrollByElement(PublishedTable);
		String n = driver.findElement(By.xpath("(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("Last Page Number of Published Table is : " +page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='container-fluid']//table[@id='tblPublishedItems']")));
		String entiresText = driver.findElement(By.xpath("//*[@class='container-fluid']//div[@id='tblPublishedItems_info' and @class='dataTables_info']")).getText();
		System.out.println("The total entries in a table is :" +entiresText);
		int count = 0;
		boolean celtext = false;
		for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
			scrollByElement(PublishedTable);
			List < WebElement > rows_table = PublishedTableRow;	//To locate rows of table. 
			int rows_count = rows_table.size();		//To calculate no of rows In table.
			count = count + rows_count;
			for (int row = 1; row < rows_count; row++) { 
				List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
				int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
				for (int col = 1; col < columns_count; col++) {	    				
					if(celtext = driver.findElement(By.xpath("(//*[@class='container-fluid']//table[@id='tblPublishedItems']//tbody//tr)["+ (row) +"]//td["+ (col+1) +"]")).getText().contains(Text)); 
					celtext = true;
					break;    					
				}
			}if(PubpaginationNext.isEnabled()) {
				scrollByElement(PubpaginationNext);
				PubpaginationNext.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		}
		PublishedTextSearch.clear();
		clickelement(PublishedSearch);
		BaseClass.addEvidence(CurrentState.getDriver(), "To clear the text field at the end of the operation", "Yes");
	}

	/**
	 * To select type of Published content
	 * @param Type
	 */
	public void SearchPublishedUsingType(String Type) {
		scrollByElement(PublishedType);
		Select Types = new Select(PublishedType);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Types.selectByVisibleText(Type);
		clickelement(PublishedSearch);
	}


	/**
	 * To search data using type in Published table
	 * @param Type
	 * @throws Exception
	 */
	public void SearchPublishedTypeinTable(String Type) throws Exception {
		scrollByElement(PublishedSection);
		scrollByElement(PublishedTable);
		String n = driver.findElement(By.xpath("(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("Last Page Number of Published Table is : " +page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='container-fluid']//table[@id='tblPublishedItems']")));
		String entiresText = driver.findElement(By.xpath("//*[@class='container-fluid']//div[@id='tblPublishedItems_info' and @class='dataTables_info']")).getText();
		System.out.println("The total entries in a table is :" +entiresText);
		int count = 0;
		for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
			scrollByElement(PublishedTable);
			List < WebElement > rows_table = PublishedTableRow;	//To locate rows of table. 
			int rows_count = rows_table.size();		//To calculate no of rows In table.
			count = count + rows_count;
			for (int row = 1; row < rows_count; row++) { 
				String celtext = driver.findElement(By.xpath("(//*[@class='container-fluid']//table[@id='tblPublishedItems']//tbody//tr)["+ (row) +"]//td[6]//strong[1]")).getText(); 
				System.out.println("CellText is :" +celtext);
				System.out.println("Type selected is :" +Type);
				assertTrue(Type.contains(celtext));
			}if(PubpaginationNext.isEnabled()) {
				scrollByElement(PubpaginationNext);
				PubpaginationNext.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			}
		}
		Select Types = new Select(PublishedType);
		Types.selectByVisibleText("All");
		clickelement(PublishedSearch);
		BaseClass.addEvidence(CurrentState.getDriver(), "To Set Default Type at the end of the opration", "Yes");
	}

}
