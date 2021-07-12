package com.dac.main.POM_SE;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

import resources.BaseClass;
import resources.CurrentState;

public class ContentManagement_Page extends BasePage {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public ContentManagement_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	/*-----------------------------------Unpublished Part-----------------------------------------------------*/

	@FindBy(xpath = "//p[@class = 'lead']")
	private WebElement PageTitletext;
	
	@FindBy(xpath = "//div[@class='social-media-content']//h1")
	private WebElement PageTitle;

	@FindBy(xpath = "//input[@id='txtSearchTextUnpublished']")
	private WebElement UnpublishedTextSearch;

	@FindBy(xpath = "(//div[@class='form-control selection ui dropdown'])[1]")
	private WebElement UnpublishedType;

	@FindBy(xpath = "(//div[@class='form-control selection ui dropdown'])[2]")
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

	@FindBy(xpath = "//div[@class='modal-footer']//button[@class='btn btn-width-sm btn-primary']")
	private WebElement UnDeleteConfirm;

	@FindBy(xpath = "//div[@class='modal-footer']//button[@class='btn btn-width-sm btn-success']")
	private WebElement UnDeleteSuccess;

	@FindBy(xpath = "//div[@class='modal-footer']//button[@class='btn btn-width-sm btn-primary']")
	private WebElement ConfirmApprove;

	@FindBy(xpath = "//div[@class='modal-footer']//button[@class='btn btn-width-sm btn-success']")
	private WebElement SuccessApprove;

	@FindBy(xpath = "//div[@class='modal-footer']//button[@class='btn btn-width-sm btn-primary']")
	private WebElement ConfirmReject;

	@FindBy(xpath = "//div[@class='modal-footer']//button[@class='btn btn-width-sm btn-success']")
	private WebElement SuccessReject;
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

	@FindBy(xpath = "//input[@id='txtSearchTextPublished']")
	private WebElement PublishedTextSearch;

	@FindBy(xpath = "(//div[@class='form-control selection ui dropdown'])[3]")
	private WebElement PublishedType;

	@FindBy(xpath = "//*[@class='container-fluid']//a[@class='btn btn-xs btn-primary btn-block link-view']")
	private WebElement PublishedView;

	@FindBy(xpath = "//button[@id='btnPublished']")
	private WebElement PublishedSearch;

	@FindBy(xpath = "//table[@id='tblPublishedItems']")
	private WebElement PublishedTable;

	@FindBy(xpath = "//table[@id='tblPublishedItems']//tbody//tr")
	private List<WebElement> PublishedTableRow;

	@FindBy(xpath = "//button[@class='btn btn-width-sm btn-primary']")
	private WebElement PubDeleteConfirm;

	@FindBy(xpath = "//button[@class='btn btn-width-sm btn-success']")
	private WebElement PubDeleteSuccess;

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
	 * To get text and verify title text
	 */
	public void VerifyTitleText() {
		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : "+PageTitle);
		waitForElement(PageTitletext, 10);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text for Content Management Page is :" + TitleText);
		Assert.assertEquals("Content Management", Title);
		Assert.assertEquals(
				"This reports on all Pending, Approved, Rejected, Scheduled and Published Posts. Posts can be edited prior to Approval and Publishing. Social Post Approvers will be able to update Posts even after a post has been approved but only prior to publication. Posts can be filtered by Social Platform, Status or by search term/word.",
				TitleText);
	}

	/**
	 * This is enter the keyword to search in unpublished section
	 * 
	 * @param Text
	 */
	public void SearchUnpublishedUsingKeyword(String Text) {
		scrollByElement(UnpublishedTextSearch);
		UnpublishedTextSearch.sendKeys(Text);
		clickelement(UnpublishedSearch);
	}

	/**
	 * This is to search the text with the keyword entered
	 * 
	 * @param Text
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void SearchUnpublishedKeyinTable(String Text) throws Exception {
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			boolean celtext = false;
			if (unpaginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
							if (celtext = driver
									.findElement(By.xpath(
											"(//table[@id='tblUnpublishedItems']//tbody//tr)[" + (row) + "]//td"))
									.getText().contains(Text))
								;
							celtext = true;
						}if (unpaginationNext.isEnabled()) {
							scrollByElement(unpaginationNext);
							unpaginationNext.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						}
					}					
			}
		} else {
			System.out.println("No Data Available for search criteria");
		}
		BaseClass.addEvidence(CurrentState.getDriver(), "To clear the text field at the end of the opration", "Yes");

	}

	/**
	 * To select type of unpublished content
	 * 
	 * @param Type
	 * @throws InterruptedException 
	 */
	public void SearchUnpublishedUsingType(String Type) throws InterruptedException {
		/*scrollByElement(UnpublishedType);
		Select Types = new Select(UnpublishedType);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Types.selectByVisibleText(Type);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickelement(UnpublishedSearch);*/
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		clickelement(UnpublishedSearch);
	}

	/**
	 * To search data using type in unpublished table
	 * 
	 * @param Type
	 * @throws Exception
	 */
	public void SearchUnpublishedTypeinTable(String Type) throws Exception {
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			if (unpaginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						String celtext = driver
								.findElement(By
										.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + (row) + "]//td[6]"))
								.getText();
						System.out.println("CellText is :" + celtext);
						System.out.println("Type selected is :" + Type);
						if (Type.equalsIgnoreCase("Posts")) {
							assertTrue(celtext.contains("Post"));
						} else if (Type.equalsIgnoreCase("Comments")) {
							assertTrue(celtext.contains("Comment"));
						} else if (Type.equalsIgnoreCase("Replies")) {
							assertTrue(celtext.contains("Reply"));
						} else {
							assertTrue(celtext.contains("Post") || celtext.contains("Comment")
									|| celtext.contains("Reply"));
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			}
		} else {
			System.out.println("No Data Avaliable");
		}
	}

	/**
	 * This is enter the keyword to search in unpublished section
	 * 
	 * @param Text
	 */
	public void SearchPublishedUsingKeyword(String Text) {
		scrollByElement(PublishedTextSearch);
		PublishedTextSearch.sendKeys(Text);
		clickelement(PublishedSearch);
	}

	/**
	 * This is to search the text with the keyword entered
	 * 
	 * @param Text
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void SearchPublishedKeyinTable(String Text) throws Exception {
		scrollByElement(PublishedTable); 
		if (driver.findElement(By.xpath("//*[@id='tblPublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(
							By.xpath("(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Published Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//table[@id='tblPublishedItems']")));
			int entiresText =NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			boolean celtext = false;
			if (PubpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(PublishedTable);
					List<WebElement> rows_table = PublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); // To locate
						// columns(cells) of
						// that specific
						// row.
						int columns_count = Columns_row.size(); // To calculate no of columns (cells). In that specific
						// row.
						for (int col = 1; col < columns_count; col++) {
							if (celtext = driver.findElement(By
									.xpath("(//table[@id='tblPublishedItems']//tbody//tr)["
											+ (row) + "]//td[" + (col + 1) + "]"))
									.getText().contains(Text))
								;
							celtext = true;
							break Outer;
						}
					}
					if (PubpaginationNext.isEnabled()) {
						scrollByElement(PubpaginationNext);
						PubpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To select type of Published content
	 * 
	 * @param Type
	 * @throws InterruptedException 
	 */
	public void SearchPublishedUsingType(String Type) throws InterruptedException {
		/*scrollByElement(PublishedType);
		Select Types = new Select(PublishedType);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Types.selectByVisibleText(Type);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickelement(PublishedSearch);*/
		scrollByElement(PublishedType);
		clickelement(PublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("(//div[contains(@class,'form-control selection ui dropdown')])[3]//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		clickelement(PublishedSearch);
	}

	/**
	 * To search data using type in Published table
	 * 
	 * @param Type
	 * @throws Exception
	 */
	public void SearchPublishedTypeinTable(String Type) throws Exception {
		scrollByElement(PublishedSection);
		scrollByElement(PublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblPublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(
							By.xpath("(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Published Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//table[@id='tblPublishedItems']")));
			int entiresText = NumOfentries(driver.findElement(By.xpath(
					"//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			if (PubpaginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(PublishedTable);
					List<WebElement> rows_table = PublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						String celtext = driver.findElement(
								By.xpath("(//table[@id='tblPublishedItems']//tbody//tr)["
										+ (row) + "]//td[6]"))
								.getText();
						System.out.println("CellText is :" + celtext);
						System.out.println("Type selected is :" + Type);
						if (Type.equalsIgnoreCase("Posts")) {
							assertTrue(celtext.contains("Post"));
						} else if (Type.equalsIgnoreCase("Comments")) {
							assertTrue(celtext.contains("Comment"));
						} else if (Type.equalsIgnoreCase("Replies")) {
							assertTrue(celtext.contains("Reply"));
						} else {
							assertTrue(celtext.contains("Post") || celtext.contains("Comment")
									|| celtext.contains("Reply"));
						}
					}
					if (PubpaginationNext.isEnabled()) {
						scrollByElement(PubpaginationNext);
						PubpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}				
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To select multiple elements 
	 * @throws InterruptedException 
	 */
	public void SearchTextnType(WebElement SearchTxt, WebElement Type, WebElement SearchBTN, String Text,
			String Typesel) throws InterruptedException {
		scrollByElement(SearchTxt);
		SearchTxt.sendKeys(Text);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		scrollByElement(Type);
		clickelement(Type);
		System.out.println("The type is : " +Typesel);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("(//div[contains(@class,'form-control selection ui dropdown')])[3]//div[contains(@class,'item') and contains(text(),'"+Typesel+"')]"));
		Thread.sleep(3000);
		clickelement(type);
		clickelement(SearchBTN); 
	}

	/**
	 * To search with text and Type
	 * @throws InterruptedException 
	 */
	public void UnpublishedTXTTypeSearch(String Text, String Type) throws InterruptedException {
		SearchTextnType(UnpublishedTextSearch, UnpublishedType, UnpublishedSearch, Text, Type);
	}

	@SuppressWarnings("unused")
	public void SearchUnpublishedKeynTypeinTable(String Text, String Type) throws Exception {
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries( driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			boolean celtext = false;
			if (unpaginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); // To locate
						// columns(cells)
						// of that
						// specific
						// row.
						int columns_count = Columns_row.size(); // To calculate no of columns (cells). In that specific
						// row.
						for (int col = 1; col < columns_count; col++) {
							if ((celtext = driver
									.findElement(By.xpath(
											"(//table[@id='tblUnpublishedItems']//tbody//tr)[" + (row) + "]//td"))
									.getText().contains(Text))
									&& (celtext = driver
											.findElement(By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)["
													+ (row) + "]////td[6]//strong"))
											.getText().contains(Type)))
								;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			}
		} else {
			System.out.println("No Data Available for search criteria");
		}
	}

	/**
	 * To enter keyword and type to search in published table
	 * @throws InterruptedException 
	 */
	public void PublishedTXTTypeSearch(String Text, String Type) throws InterruptedException {
		SearchTextnType(PublishedTextSearch, PublishedType, PublishedSearch, Text, Type);
	}

	@SuppressWarnings("unused")
	public void SearchPublishedKeynTypeinTable(String Text, String Type) throws Exception {
		scrollByElement(PublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblPublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(
							By.xpath("(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Published Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//table[@id='tblPublishedItems']")));
			int entiresText =  NumOfentries(driver.findElement(By.xpath(
					"//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			boolean celtext = false;
			Outer: if (PubpaginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(PublishedTable);
					List<WebElement> rows_table = PublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); // To locate
						// columns(cells) of
						// that specific
						// row.
						int columns_count = Columns_row.size(); // To calculate no of columns (cells). In that specific
						// row.
						for (int col = 1; col < columns_count; col++) {
							if ((celtext = driver.findElement(By
									.xpath("(//table[@id='tblPublishedItems']//tbody//tr)["
											+ (row) + "]//td[" + (col + 1) + "]"))
									.getText().contains(Text))
									&& (celtext = driver.findElement(By.xpath(
											"(//table[@id='tblPublishedItems']//tbody//tr)["
													+ (row) + "]//td[6]//strong"))
											.getText().contains(Type)))
								;
							celtext = true;
							break Outer;
						}
					}
					if (PubpaginationNext.isEnabled()) {
						scrollByElement(PubpaginationNext);
						PubpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To search with Key, Type and Status
	 */
	public void SearchTextnTypenStatus(WebElement SearchTxt, WebElement Type, WebElement Status, WebElement SearchBTN,
			String Text, String Typesel, String Statsel) {
		scrollByElement(SearchTxt);
		SearchTxt.sendKeys(Text);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Select Types = new Select(Type);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Types.selectByVisibleText(Typesel);
		Select Stat = new Select(Status);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Stat.selectByVisibleText(Statsel);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickelement(SearchBTN);
	}

	/**
	 * To search with Status
	 * @throws InterruptedException 
	 * 
	 */
	public void SearchStatus(String StatText) throws InterruptedException {
		/*scrollByElement(UnpublishedStatus);
		Select stat = new Select(UnpublishedStatus);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		stat.selectByVisibleText(StatText);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickelement(UnpublishedSearch);*/
		scrollByElement(UnpublishedStatus);
		clickelement(UnpublishedStatus);
		Thread.sleep(4000);
		System.out.println("The status selected is : " +StatText);
		WebElement status = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+StatText+"')]"));
		clickelement(status);
		
	}

	/**
	 * Verify Status
	 */
	public void VerifystatusinTable(String StatText) {
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			if (unpaginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						if (StatText.equalsIgnoreCase("Pending Approval")) {
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-approve'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Approve Button Displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-reject'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Reject button is displayed");
							assertTrue(driver
									.findElement(By.xpath(
											"(//a[@class='btn btn-outline-primary btn-xs btn-block btn-icon link-edit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Edit Link is displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Delete Button is displayed");

						} else if (StatText.equalsIgnoreCase("Post Error")) {
							System.out.println("Post Error");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-xs btn-success btn-block btn-resubmit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Resubmit Button is Displayed");
							assertTrue(driver
									.findElement(By.xpath(
											"(//a[@class='btn btn-outline-primary btn-xs btn-block btn-icon link-edit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Edit Link is displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Delete Button is displayed");
						} else if (StatText.equalsIgnoreCase("Approved Scheduled")) {
							assertTrue(driver
									.findElement(By.xpath(
											"(//a[@class='btn btn-outline-primary btn-xs btn-block btn-icon link-edit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Edit Link is displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Delete Button is displayed");
						} else if (StatText.equalsIgnoreCase("Rejected")) {
							assertTrue(driver
									.findElement(By.xpath(
											"(//a[@class='btn btn-outline-primary btn-xs btn-block btn-icon link-edit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Edit Link is displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Delete Button is displayed");
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}				
			}

		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Verify Status
	 */
	public void VerifyCreatorstatusinTable(String StatText) {
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			if (unpaginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						boolean flag;
						if (StatText.equalsIgnoreCase("Pending Approval")) {
							flag = Eleexists(
									"(//button[@class='btn btn-xs btn-success btn-block btn-approve'])[" + row + "]");
							System.out.println("Boolean Value :" + flag);
							assertFalse(flag, "Approve Button is not displayed");
							flag = Eleexists(
									"(//button[@class='btn btn-xs btn-warning btn-block btn-reject'])[" + row + "]");
							assertFalse(flag, "Reject Button is not displayed");
							assertTrue(driver
									.findElement(By.xpath(
											"(//a[@class='btn btn-outline-primary btn-xs btn-block btn-icon link-edit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Edit Link is displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Delete Button is displayed");

						} else if (StatText.equalsIgnoreCase("Post Error")) {
							System.out.println("Post Error");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-xs btn-success btn-block btn-resubmit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Resubmit Button is Displayed");
							assertTrue(driver
									.findElement(By.xpath(
											"(//a[@class='btn btn-xs btn-default btn-block link-edit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Edit Link is displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-xs btn-danger btn-block btn-delete'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Delete Button is displayed");
						} else if (StatText.equalsIgnoreCase("Approved Scheduled")) {
							flag = Eleexists("(//a[@class='btn btn-xs btn-default btn-block link-edit'])[" + row + "]");
							assertFalse(flag, "Edit Link is not displayed");
							flag = Eleexists(
									"(//button[@class='btn btn-xs btn-danger btn-block btn-delete'])[" + row + "]");
							assertFalse(flag, "Delete Button is not displayed");
						} else if (StatText.equalsIgnoreCase("Rejected")) {
							assertTrue(driver
									.findElement(By.xpath(
											"(//a[@class='btn btn-xs btn-default btn-block link-edit'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Edit Link is displayed");
							assertTrue(driver.findElement(By.xpath(
									"(//button[@class='btn btn-xs btn-danger btn-block btn-delete'])[" + row + "]"))
									.isDisplayed());
							System.out.println("Delete Button is displayed");
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To delete data from the published table using type
	 * @throws InterruptedException 
	 */
	public void DeleteDataPublishedTableusingType(String Type) throws InterruptedException {
		scrollByElement(PublishedType);
		clickelement(PublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("(//div[contains(@class,'form-control selection ui dropdown')])[3]//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		clickelement(PublishedSearch);
		waitForElement(PublishedTable, 10);
		scrollByElement(PublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblPublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(
							By.xpath("(//div[@id='tblPublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Published Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblPublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			int count = 0;
			if (PubpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(PublishedTable);
					List<WebElement> rows_table = PublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(By.xpath("(//*[@id='tblPublishedItems']/tbody/tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (cellText.contains("Comment") || cellText.contains("Reply")) {
							action.moveToElement(driver.findElement(By.xpath(
									"(//*[@id='tblPublishedItems']//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])["+row+"]")))
									.click().build().perform();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(PubDeleteConfirm);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(PubDeleteSuccess);
							break Outer;
						}
					}
					if (PubpaginationNext.isEnabled()) {
						scrollByElement(PubpaginationNext);
						PubpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int pubchangedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + pubchangedentiresText);
			if(!(pubchangedentiresText== 0)) {
				//assertTrue(pubchangedentiresText < entiresText);
				Assert.assertEquals(pubchangedentiresText, entiresText - 1);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To delete data from the published table using type
	 * @throws InterruptedException 
	 */
	public void DeleteDataUnPublishedTableusingType(String Type) throws InterruptedException {
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")) {
							driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])["
											+ row + "]"))
									.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(UnDeleteConfirm);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(UnDeleteSuccess);
							System.out.println("Deleted");
							break Outer;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText== 0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To delete data from the published table using Status
	 * @throws InterruptedException 
	 */
	public void DeleteDataUnPublishedTableusingStatus(String Status) throws InterruptedException {
		scrollByElement(UnpublishedStatus);
		clickelement(UnpublishedStatus);
		Thread.sleep(4000);
		System.out.println("The status selected is : " +Status);
		WebElement status = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Status+"')]"));
		clickelement(status);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (driver.findElement(By.xpath(
								"(//*[@id='tblUnpublishedItems']//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])["
										+ row + "]"))
								.isDisplayed()) {
							driver.findElement(By.xpath(
									"(//*[@id='tblUnpublishedItems']//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])["
											+ row + "]"))
									.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(UnDeleteConfirm); 
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(UnDeleteSuccess);
							System.out.println("Deleted");
							break Outer;
						}else {
							System.out.println("Delete Button Not Displayed");
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To delete data from the published table using type and status
	 * @throws InterruptedException 
	 */
	public void DeleteDataUnPublishedTableusingTypenStatus(String Type, String Status) throws InterruptedException {
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		scrollByElement(UnpublishedStatus);
		clickelement(UnpublishedStatus);
		Thread.sleep(4000);
		System.out.println("The status selected is : " +Status);
		WebElement status = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Status+"')]"));
		clickelement(status);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")) {
							cellText = driver
									.findElement(
											By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
									.getText();
							System.out.println("Type selected is POM :" + cellText);
							if (driver.findElement(By.xpath(
									"(//*[@id='tblUnpublishedItems']//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])["
											+ row + "]"))
									.isDisplayed()) {
								driver.findElement(By.xpath(
										"(//*[@id='tblUnpublishedItems']//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-delete'])["
												+ row + "]"))
										.click();
								driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								clickelement(UnDeleteConfirm); 
								driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
								clickelement(UnDeleteSuccess);
								System.out.println("Deleted");
								break Outer;
							}
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Test to approve using Type and Status
	 * @throws InterruptedException 
	 */
	public void ApprovePost(String Type, String Status) throws InterruptedException {
		scrollByElement(UnpublishedStatus);
		clickelement(UnpublishedStatus);
		Thread.sleep(4000);
		System.out.println("The status selected is : " +Status);
		WebElement status = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Status+"')]"));
		clickelement(status);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")) {
							driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-approve'])[" + row + "]"))
									.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(ConfirmApprove); 
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(SuccessApprove);
							System.out.println("Post Approved");
							break Outer;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Reject Post using Type and Status
	 * @throws InterruptedException 
	 */
	public void RejectPost(String Type, String Status) throws InterruptedException {
		waitForElement(UnpublishedType, 10);
		scrollByElement(UnpublishedType);
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		scrollByElement(UnpublishedStatus);
		clickelement(UnpublishedStatus);
		Thread.sleep(4000);
		System.out.println("The status selected is : " +Status);
		WebElement status = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Status+"')]"));
		clickelement(status);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")) {
							driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-reject'])[" + row + "]"))
									.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(ConfirmReject); 
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(SuccessReject);
							System.out.println("Post Rejected");
							break Outer;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Test to approve using Type
	 * @throws InterruptedException 
	 */
	public void ApprovePostWithType(String Type) throws InterruptedException {
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")) {
							driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-approve'])[" + row + "]"))
									.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(ConfirmApprove);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(SuccessApprove);
							System.out.println("Post Approved");
							break Outer;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int pubchangedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + pubchangedentiresText);
			if(!(pubchangedentiresText== 0)) {
				assertTrue(pubchangedentiresText > entiresText);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Reject Post using Type
	 * @throws InterruptedException 
	 */
	public void RejectPostWithType(String Type) throws InterruptedException {
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")) {
							driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-reject'])[" + row + "]"))
									.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(ConfirmReject);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(SuccessReject);
							System.out.println("Post Rejected");
							break Outer;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
			Assert.assertEquals(changedentiresText, entiresText - 1);
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int pubchangedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + pubchangedentiresText);
			if(!(pubchangedentiresText== 0)) {
				assertTrue(pubchangedentiresText > entiresText);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Test to approve using Type and Status
	 * @throws InterruptedException 
	 */
	public void ApprovePost3Criteria(String Type, String Status, String Key) throws InterruptedException {
		waitForElement(UnpublishedTextSearch, 10);
		UnpublishedTextSearch.sendKeys(Key);
		scrollByElement(UnpublishedType);
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		scrollByElement(UnpublishedStatus);
		clickelement(UnpublishedStatus);
		Thread.sleep(4000);
		System.out.println("The status selected is : " +Status);
		WebElement status = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Status+"')]"));
		clickelement(status);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						String celText = driver
								.findElement(By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]"))
								.getText();
						System.out.println("Key is :" + celText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")
								&& ((Status.equalsIgnoreCase("Pending Approval"))) && (celText.contains(Key))) {
							driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-approve'])[" + row + "]"))
									.click(); 
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(ConfirmApprove);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(SuccessApprove);
							System.out.println("Post Approved");
							break Outer;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int pubchangedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + pubchangedentiresText);
			if(!(pubchangedentiresText== 0)) {
				assertTrue(pubchangedentiresText > entiresText);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Reject Post using Type and Status
	 * @throws InterruptedException 
	 */
	public void RejectPost3Criteria(String Type, String Status, String Key) throws InterruptedException {
		waitForElement(UnpublishedTextSearch, 10);
		UnpublishedTextSearch.sendKeys(Key);
		scrollByElement(UnpublishedType);
		scrollByElement(UnpublishedType);
		clickelement(UnpublishedType);
		System.out.println("The type is : " +Type);
		Thread.sleep(3000);
		WebElement type = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Type+"')]"));
		clickelement(type);
		scrollByElement(UnpublishedStatus);
		clickelement(UnpublishedStatus);
		Thread.sleep(4000);
		System.out.println("The status selected is : " +Status);
		WebElement status = driver.findElement(By.xpath("//div[contains(@class,'item') and contains(text(),'"+Status+"')]"));
		clickelement(status);
		clickelement(UnpublishedSearch);
		waitForElement(UnpublishedTable, 10);
		scrollByElement(UnpublishedTable);
		if (driver.findElement(By.xpath("//*[@id='tblUnpublishedItems_info']")).isDisplayed()) {
			String n = driver
					.findElement(By
							.xpath("(//div[@id='tblUnpublishedItems_paginate']//*[@class='pagination']//a)[last()-1]"))
					.getText();
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number of Unpublished Table is : " + page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" //table[@id='tblUnpublishedItems']")));
			int entiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + entiresText);
			if (unpaginationNext.isDisplayed()) {
				Outer: for (int i = 1; i <= page; i++) {
					scrollByElement(UnpublishedTable);
					List<WebElement> rows_table = UnpublishedTableRow; // To locate rows of table.
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					for (int row = 1; row < rows_count; row++) {
						String cellText = driver
								.findElement(
										By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]//td[6]"))
								.getText();
						System.out.println("Type selected is POM :" + cellText);
						String celText = driver
								.findElement(By.xpath("(//table[@id='tblUnpublishedItems']//tbody//tr)[" + row + "]"))
								.getText();
						System.out.println("Key is :" + celText);
						if (cellText.contains("Post") || cellText.contains("Comment") || cellText.contains("Reply")
								&& ((Status.equalsIgnoreCase("Pending Approval"))) && (celText.contains(Key))) {
							driver.findElement(By.xpath(
									"(//button[@class='btn btn-outline-primary btn-xs btn-block btn-icon btn-reject'])[" + row + "]"))
									.click();
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(ConfirmReject);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(SuccessReject);
							System.out.println("Post Rejected");
							break Outer;
						}
					}
					if (unpaginationNext.isEnabled()) {
						scrollByElement(unpaginationNext);
						unpaginationNext.click();
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int changedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblUnpublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + changedentiresText);
			if(!(changedentiresText==0)) {
				Assert.assertEquals(changedentiresText, entiresText - 1);
				}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			int pubchangedentiresText = NumOfentries(driver
					.findElement(By.xpath("//div[@id='tblPublishedItems_info' and @class='dataTables_info']")));
			System.out.println("The total entries in a table is :" + pubchangedentiresText);
			if(!(pubchangedentiresText== 0)) {
				assertTrue(pubchangedentiresText > entiresText);
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	public boolean Eleexists(String x) {
		try {
			WebElement y = driver.findElement(By.xpath(x));
			if (!(y == null))
				return true;
		} catch (NoSuchElementException e) {
			// e.printStackTrace();
		}
		return false;
	}
	
	public int NumOfentries(WebElement entry) {
		waitForElement(entry, 10);
		int finalvalue;
		if(entry.isDisplayed()) {
		String entiresText = entry.getText();
		System.out.println("The total entries in a table is :" + entiresText);
		String result = entiresText.substring(entiresText.indexOf("(") + 3, entiresText.indexOf(")") - 7).trim();
		finalvalue = Integer.parseInt(result);
		System.out.println("The number of entries is : " +finalvalue);
		}else {
			System.out.println("No Data available");
			finalvalue = 0;
			
		}	
		return finalvalue;
	}
}
