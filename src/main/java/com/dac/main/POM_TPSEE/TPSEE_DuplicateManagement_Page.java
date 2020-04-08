package com.dac.main.POM_TPSEE;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TPSEE_DuplicateManagement_Page extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public TPSEE_DuplicateManagement_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {

		return null;
	}

	/*---------------------------------------Locators-----------------------------------------------------------*/

	@FindBy(xpath = "(//*[@id='page-content']//h1)[2]")
	private WebElement PageTitle;

	@FindBy(xpath = "(//p[@class='lead'])[2]")
	private WebElement PageTitletext;

	@FindBy(xpath = "//*[@id='li-duplicates']")
	private WebElement PotentialDupTab;

	@FindBy(xpath = "//*[@id='li-pending']")
	private WebElement PendingTab;

	@FindBy(xpath = "//*[@id='li-completed']")
	private WebElement CompletedTab;

	@FindBy(xpath = "//*[@id='add-button']")
	private WebElement AddBtn;

	@FindBy(xpath = "//input[@class='form-control input-sm']")
	private WebElement SearchBox;

	@FindBy(xpath = "//div[@class='c-search-input']//button")
	private WebElement SearchBtn;

	@FindBy(xpath = "//table[@id='duplicate-table']")
	private WebElement DupTable;

	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement Page_Prev;

	@FindBy(xpath = "(//*[@class='pagination']//a)[2]")
	private WebElement First_Page;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement Page_Next;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last() - 1]")
	private WebElement Last_Page;

	@FindBy(xpath = "//button[@id='duplicate-form-submit']")
	private WebElement Form_Submit_Btn;

	@FindBy(xpath = "(//button[@class='btn btn-secondary cancel-bttn'])[1]")
	private WebElement Form_Cancel_Btn;

	@FindBy(xpath = "//form[@id='duplicate-form']")
	private WebElement Dup_List_Form;

	@FindBy(xpath = "//input[@id='duplicate-listing-url']")
	private WebElement Search_Dup_Form;

	@FindBy(xpath = "//table[@id='duplicate-form-table']")
	private WebElement Dup_List_Form_Table;

	@FindBy(xpath = "//*[@id='duplicate-table_info']")
	private WebElement Dup_Table_Info;

	@FindBy(xpath = "//table[@id='duplicate-table']//tbody//tr")
	private List<WebElement> Dup_TableRow;

	@FindBy(xpath = "//table[@id='duplicate-form-table']//tbody//tr")
	private List<WebElement> Dup_List_Form_TableRow;

	@FindBy(xpath = "//form[@id='duplicate-form']//h1[@class='main-title']")
	private WebElement Form_Title;

	@FindBy(xpath = "//form[@id='duplicate-form']//p[@class='lead']")
	private WebElement Form_Title_Text;

	@FindBy(xpath = "(//input[@type='button'])")
	private WebElement Form_Radio_Btn;

	@FindBy(xpath = "(//div[@id='duplicate-form-table_paginate']//*[@class='pagination']//a)[last()-1]")
	private WebElement Form_Last_Page;

	@FindBy(xpath = "(//div[@id='duplicate-form-table_paginate']//*[@class='pagination']//a)[last()]")
	private WebElement Form_Next_Page;

	@FindBy(xpath = "(//div[@id='duplicate-form-table_paginate']//*[@class='pagination']//a)[1]")
	private WebElement Form_Prev_Page;

	@FindBy(xpath = "(//div[@id='duplicate-form-table_paginate']//*[@class='pagination']//a)[2]")
	private WebElement Form_First_Page;

	@FindBy(xpath = "//div[@id='duplicate-form-table_info']")
	private WebElement Form_Table_Info;

	@FindBy(xpath = "//button[@id ='btn_ignore_potential_duplicate']")
	private WebElement Ignore_Confirmation;

	/*---------------------------------------Locators-----------------------------------------------------------*/

	/**
	 * To verify title and title text
	 */
	public void VerifyTitlenText() {
		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		String PgeTitle = "Duplicate Management";
		String TitleTxt = "This report will display possible duplicate listings that have been found on vendor sites. Action is required below to clarify and resolve these potential duplicate listings.";
		Assert.assertEquals(Title, PgeTitle);
		Assert.assertEquals(TitleText, TitleTxt);
	}

	/**
	 * To
	 * 
	 * @param Text
	 * @throws InterruptedException
	 */
	public void AddPotentialDuplicate(String Text, String URL) throws InterruptedException {
		clickelement(AddBtn);
		waitForElement(Dup_List_Form, 5);
		String FormTitle = Form_Title.getText();
		System.out.println("Form Title is :" + FormTitle);
		String FormTitleText = Form_Title_Text.getText();
		System.out.println("Form Title Text is :" + FormTitleText);
		String FrmTitle = "Add Duplicate Listing",
				FrmTitleTxt = "Add a new duplicate manually and we will process it. Simply enter its URL, match it up with the correct location, and click submit.";
		Assert.assertEquals(FormTitle, FrmTitle);
		Assert.assertEquals(FormTitleText, FrmTitleTxt);
		Outer: if (Form_Table_Info.isDisplayed()) {
			scrollByElement(Form_Last_Page);
			String n = Form_Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			String entiresText = Form_Table_Info.getText();
			entiresText = entiresText.substring(entiresText.indexOf("("));
			int count = 0;
			if (Form_Next_Page.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = Dup_List_Form_TableRow;
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 1; row < rows_count; row++) {
						List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
						int columns_count = Columns_row.size();
						for (int column = 0; column < columns_count; column++) {
							List<WebElement> headerTableRow = Dup_List_Form_Table.findElements(By.tagName("th"));
							String headerText = headerTableRow.get(column).getText(), celtext = "";
							System.out.println("Header Text is :" + headerText);
							if (column == 1 & row < rows_count) {
								celtext = driver
										.findElement(By.xpath(
												"(//table[@id='duplicate-form-table']//tbody//tr)[" + (row) + "]"))
										.getText();
								System.out.println("\n" + celtext);
								if (celtext.contains(Text)) {
									driver.findElement(By.xpath("(//input[@type='button'])[" + row + "]")).click();
									System.out.println("Clicked on Radio");
									Search_Dup_Form.sendKeys(URL);
									clickelement(Form_Submit_Btn);
									System.out.println("Clicked on Submit Button");
									waitUntilLoad(driver);
									wait.until(ExpectedConditions.visibilityOf(Dup_Table_Info));
									System.out.println("Duplicate Listing Table Displayed");
									break Outer;
								} else {
									System.out.println("No Given Text Found");
								}
							}
						}
					}
					if (Form_Next_Page.isEnabled()) {
						scrollByElement(Form_Next_Page);
						Form_Next_Page.click();
						Thread.sleep(4000);
					}
				}
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To Get the Location Number from the UI Table.
	 * 
	 * @param PhNumber
	 * @return
	 * @throws InterruptedException
	 */
	public String getLocationNumber(String PhNumber) throws InterruptedException {
		String LocationNumber = null;
		clickelement(PendingTab);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			String entiresText = Dup_Table_Info.getText();
			entiresText = entiresText.substring(entiresText.indexOf("("));
			int count = 0;
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					scrollByElement(DupTable);
					List<WebElement> rows_table = Dup_TableRow;
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 0; row < rows_count; row++) {
						String celtext = driver
								.findElement(By
										.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div/div"))
								.getText();
						System.out.println("The celText is :" + celtext);
						if (celtext.contains(PhNumber)) {
							LocationNumber = driver
									.findElement(By
											.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div"))
									.getText();
							System.out.println("The Location Number is :" + LocationNumber);
							LocationNumber = LocationNumber.substring(LocationNumber.lastIndexOf(":"));
							LocationNumber = LocationNumber.replace(":", "");
							LocationNumber = LocationNumber.trim();
							System.out.println("The Location Number after is :" + LocationNumber);
							break Outer;
						} else {
							System.out.println("\n Phone Number given not found");
						}
					}
					if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
			} else {
				System.out.println("No Data Available in the table");
			}
		}
		return LocationNumber;
	}

	public void TakeAction(String PhNumber, String action) throws InterruptedException {
		String LocNumber = getLocationNumber(PhNumber);
		System.out.println("The Location Number found is :" + LocNumber);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			String entiresText = Dup_Table_Info.getText();
			entiresText = entiresText.substring(entiresText.indexOf("(") + 3, entiresText.indexOf(")") - 7).trim();
			int count = 0;
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					scrollByElement(DupTable);
					List<WebElement> rows_table = Dup_TableRow;
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 0; row < rows_count; row++) {
						String celtext = driver
								.findElement(By
										.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div/div"))
								.getText();
						System.out.println("The celText is :" + celtext);
						if (celtext.contains(PhNumber)) {
							WebElement s =driver.findElement(By.xpath(
									"(//div[@class ='ui dropdown duplicate-dropdown selection'])[" + (row + 1) + "]"));
							s.click();
						WebElement v =	s.findElement(By.xpath("(//div[@class ='ui dropdown duplicate-dropdown selection'])["+(row+1)+"]//div[contains(@class,'item') and contains(text(),'"+action+"')]"));
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						clickelement(v);
						System.out.println("Selected action is :" + action);
							if (action.equalsIgnoreCase("Ignore")) {
								clickelement(Ignore_Confirmation);
								System.out.println("Action Performed is : " + action);
							} else {
								System.out.println("Action Performed is : " + action);
							}
							break Outer;
						}
					}
					if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
			} else {
				System.out.println("No Data Available in the table");
			}
		}
	}
}
