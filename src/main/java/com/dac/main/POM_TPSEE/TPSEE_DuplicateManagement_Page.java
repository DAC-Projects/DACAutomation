package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
	@FindBy(xpath = "//*[@id='duplicate-table_filter']//div//input")
	private WebElement ComPenSearchbox;
	
	@FindBy(xpath = "//*[@id='duplicate-table_filter']//div//button")
	private WebElement ComPenSearchBtn;

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

	@FindBy(xpath = "//*[@id='duplicate-table']")
	private WebElement Dup_Table;

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

	// @FindBy(xpath = "(//*[@class='duplicate-notes-show-more'])")
	String ClickMore = "(//*[@class='duplicate-notes-show-more'])";

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
									Thread.sleep(10000);
									clickelement(Form_Submit_Btn);
									System.out.println("Clicked on Submit Button");
									Thread.sleep(30000);
									waitUntilLoad(driver);
									assertTrue(Dup_Table.isDisplayed());
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
	 * To Get the Location Number from Pending Tab in the UI Table.
	 * 
	 * @param PhNumber
	 * @return
	 * @throws InterruptedException
	 */
	public String getLocationNumber(String PhNumber, WebElement Tab) throws InterruptedException {
		String LocationNumber = null;
		clickelement(Tab);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(ComPenSearchbox);
			clickelement(ComPenSearchbox);
			ComPenSearchbox.sendKeys(PhNumber);
			clickelement(ComPenSearchBtn);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			}
		}else {
			System.out.println("No Data Available in the table");
			return null;
		}
		ComPenSearchbox.click();
		ComPenSearchbox.clear();
		return LocationNumber;
	}
	
	
	/**
	 * To verify the status of the location
	 * 
	 * @param PhNumber
	 * @param Text
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void verifyCompleteTab(String PhNumber, String Text, String timestamp) throws InterruptedException, ParseException {
		driver.navigate().refresh();
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		System.out.println(var);
		String LocNum = getLocationNumber(PhNumber, CompletedTab);
		System.out.println("Location Number of Complete Tab is :" + LocNum);
		if (!LocNum.equals("null")) {
			waitForElement(CompletedTab, 10);
			clickelement(CompletedTab);
			Outer: if (Dup_Table_Info.isDisplayed()) {
				scrollByElement(ComPenSearchbox);
				clickelement(ComPenSearchbox);
				ComPenSearchbox.sendKeys(LocNum);
				clickelement(ComPenSearchBtn);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				clickelement(SearchBtn);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				scrollByElement(Last_Page);
				String n = Last_Page.getText();
				System.out.println("Last PageNumber in String is :" + n);
				int page = Integer.parseInt(n);
				System.out.println("Last Page Number is :" + page);
				int entiresText = NumOfentries(Dup_Table_Info);
				System.out.println("Total Entries are :" + entiresText);
				int count = 0;
				if (Page_Next.isDisplayed()) {
					for (int i = 1; i <= page; i++) {
						scrollByElement(DupTable);
						List<WebElement> rows_table = Dup_TableRow;
						int rows_count = rows_table.size(); // To calculate no of rows In table.
						count = count + rows_count;
						for (int row = 0; row < rows_count -1; row++) {
							String celtext = driver
									.findElement(By
											.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div"))
									.getText();
							System.out.println("The celText is :" + celtext);
							if (celtext.contains(LocNum)) {
								Date todaysDate = getTodaysDate();
								System.out.println("Today's Date is :" + todaysDate);
								String date = driver.findElement(By.xpath("(//*[@id='duplicate-table']/tbody/tr["
										+ (row + 1)
										+ "]/td[2]/div/../preceding-sibling::td//div//span[@class='oblique muteText'])["
										+ (row + 1) + "]")).getText();
								System.out.println("Date displayed is :" + date);
								String finalDate = date.substring(date.lastIndexOf("d") + 1);
								System.out.println("Final Date is :" + finalDate);
								Date displayeddate = formats.parse(finalDate);
								System.out.println("Displayed Date is : " + displayeddate);
								if (todaysDate.equals(displayeddate)) {
									String ExtNotes = driver.findElement(By.xpath("(//*[@id='duplicate-table']/tbody/tr["+(row+1)+"]/td[2]/div/parent::*/following-sibling::td[2]//div[@class='duplicate-note']//span)[1]")).getText();
									System.out.println("External Notes is :" +ExtNotes);
									if(ExtNotes.equalsIgnoreCase(timestamp)) {
									String StatusText = driver.findElement(By.xpath(
											"//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[4]/div/div[3]"))
											.getText();
									assertTrue(StatusText.contains(Text));
									if (driver.findElement(By.xpath(ClickMore + "[" + (row + 1) + "]")).isDisplayed()) {
										driver.findElement(By.xpath(ClickMore + "[" + (row + 1) + "]")).click();
										System.out.println();
									} else {
										System.out.println("No More Link displayed");
									}
									}
								}
								break Outer;
							}
						} if (Page_Next.isEnabled()) {
							scrollByElement(Page_Next);
							Page_Next.click();
							Thread.sleep(4000);
						}
					}
				}
			} else {
				System.out.println("No Data Available");
			}
		} else {
			System.out.println("Location Number not found");
		}
		ComPenSearchbox.click();
		ComPenSearchbox.clear();
	}

	/**
	 * To get location number from complete tab
	 * 
	 * @param PhNumber
	 * @return
	 * @throws InterruptedException
	 */
	public String getLocationNumberComp(String PhNumber) throws InterruptedException {
		String LocationNumber = null;
		clickelement(CompletedTab);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(ComPenSearchbox);
			clickelement(ComPenSearchbox);
			ComPenSearchbox.sendKeys(PhNumber);
			clickelement(ComPenSearchBtn);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			}
		} else {
			System.out.println("No Data Available in the table");
		}
		ComPenSearchbox.click();
		ComPenSearchbox.clear();
		return LocationNumber;
	}

	/**
	 * To ignore a potential duplicate in the table
	 * 
	 * @param LocationNumber
	 * @throws InterruptedException
	 */
	public void Pot_Dup(String LocationNumber) throws InterruptedException {
		clickelement(PotentialDupTab);
		waitForElement(DupTable, 10);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(ComPenSearchbox);
			clickelement(ComPenSearchbox);
			ComPenSearchbox.sendKeys(LocationNumber);
			clickelement(ComPenSearchBtn);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			int entiresText = NumOfentries(Dup_Table_Info);
			System.out.println("Total Entries are :" + entiresText);
			int count = 0;
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					scrollByElement(DupTable);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					List<WebElement> rows_table = Dup_TableRow;
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 0; row < rows_count; row++) {
						String celtext = driver
								.findElement(
										By.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div"))
								.getText();
						System.out.println("The celText is :" + celtext);
						if (celtext.contains(LocationNumber)) {
							WebElement s = driver.findElement(By.xpath(
									"(//div[@class ='ui dropdown duplicate-dropdown selection'])[" + (row + 1) + "]"));
							s.click();
							WebElement v = driver.findElement(By.xpath(
									"(//table[@id='duplicate-table']//div[contains(@class,'item') and @data-value='1'])["
											+ (row + 1) + "]"));
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(v);
							waitUntilLoad(driver);
							waitForElement(Ignore_Confirmation, 10);
							clickelement(Ignore_Confirmation);
							System.out.println("Action Performed is : Ignore");
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							scrollByElement(ComPenSearchbox);
							clickelement(ComPenSearchbox);
							ComPenSearchbox.sendKeys(LocationNumber);
							clickelement(ComPenSearchBtn);
							int entiresText1 = NumOfentries(Dup_Table_Info);
							System.out.println("Final entries in Table is :" + entiresText1);
							Assert.assertEquals(entiresText1, entiresText - 1);
							break Outer;
						}
					} if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
			}
		} else {
			System.out.println("No Data Available");
		}
		ComPenSearchbox.click();
		ComPenSearchbox.clear();
		driver.navigate().refresh();
	}

	/**
	 * To Fix the potential duplicate in the table
	 * 
	 * @param LocationNumber
	 * @throws InterruptedException
	 */
	public void verifyfixPot_Dup(String LocationNumber) throws InterruptedException {
		clickelement(PotentialDupTab);
		waitForElement(DupTable, 10);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(ComPenSearchbox);
			clickelement(ComPenSearchbox);
			ComPenSearchbox.sendKeys(LocationNumber);
			clickelement(ComPenSearchBtn);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			int entiresText = NumOfentries(Dup_Table_Info);
			System.out.println("Total Entries are :" + entiresText);
			int count = 0;
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					scrollByElement(DupTable);
					List<WebElement> rows_table = Dup_TableRow;
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 0; row < rows_count; row++) {
						String celtext = driver
								.findElement(
										By.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div"))
								.getText();
						System.out.println("The celText is :" + celtext);
						if (celtext.contains(LocationNumber)) {
							WebElement s = driver.findElement(By.xpath(
									"(//div[@class ='ui dropdown duplicate-dropdown selection'])[" + (row + 1) + "]"));
							s.click();
							WebElement v = driver.findElement(By.xpath(
									"(//table[@id='duplicate-table']//div[contains(@class,'item') and @data-value='2'])["
											+ (row + 1) + "]"));
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(v);
							waitUntilLoad(driver);
							System.out.println("Action Performed is : Fix");
							break Outer;
						}
					} if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
			}
		} else {
			System.out.println("No Data Available");
		}
		ComPenSearchbox.click();
		ComPenSearchbox.clear();
	}

	/**
	 * To ignore the added potential Duplicate from the pending tab
	 * 
	 * @param LocNum
	 * @throws InterruptedException
	 */
	public void VerifyIgnore_PendingTab(String LocNum) throws InterruptedException {
		clickelement(PendingTab);
		waitForElement(DupTable, 10);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(ComPenSearchbox);
			clickelement(ComPenSearchbox);
			ComPenSearchbox.sendKeys(LocNum);
			clickelement(ComPenSearchBtn);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			int entiresText = NumOfentries(Dup_Table_Info);
			System.out.println("Total Entries are :" + entiresText);
			int count = 0;
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					scrollByElement(DupTable);
					List<WebElement> rows_table = Dup_TableRow;
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 0; row < rows_count; row++) {
						String celtext = driver
								.findElement(
										By.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div"))
								.getText();
						System.out.println("The celText is :" + celtext);
						if (celtext.contains(LocNum)) {
							WebElement s = driver.findElement(By.xpath(
									"(//div[@class ='ui dropdown duplicate-dropdown selection'])[" + (row + 1) + "]"));
							s.click();
							WebElement v = driver.findElement(By.xpath(
									"(//table[@id='duplicate-table']//div[contains(@class,'item') and @data-value='1'])["
											+ (row + 1) + "]"));
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(v);
							waitUntilLoad(driver);
							waitForElement(Ignore_Confirmation, 10);
							clickelement(Ignore_Confirmation);
							System.out.println("Action Performed is : Ignore");
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							clickelement(PendingTab);
							scrollByElement(ComPenSearchbox);
							clickelement(ComPenSearchbox);
							ComPenSearchbox.sendKeys(LocNum);
							clickelement(ComPenSearchBtn);
							int entiresText1 = NumOfentries(Dup_Table_Info);
							System.out.println("Final entries in Table is :" + entiresText1);
							Assert.assertEquals(entiresText1, entiresText - 1);
							break Outer;
						} else {
							System.out.println("Location Number doesn't exist");
						}
					}if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
				
			}
		} else {
			System.out.println("No Data Available");
		}
		ComPenSearchbox.click();
		ComPenSearchbox.clear();
	}

	public void VerifyFix_PendingTab(String LocNum, String Status, String timestamp) throws InterruptedException, ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		clickelement(PendingTab);
		waitForElement(DupTable, 10);
		Outer: if (Dup_Table_Info.isDisplayed()) {
			scrollByElement(ComPenSearchbox);
			clickelement(ComPenSearchbox);
			ComPenSearchbox.sendKeys(LocNum);
			clickelement(ComPenSearchBtn);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			int entiresText = NumOfentries(Dup_Table_Info);
			System.out.println("Total Entries are :" + entiresText);
			int count = 0;
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					List<WebElement> rows_table = Dup_TableRow;
					int rows_count = rows_table.size(); // To calculate no of rows In table.
					count = count + rows_count;
					for (int row = 0; row < rows_count; row++) {
						String celtext = driver
								.findElement(
										By.xpath("//*[@id='duplicate-table']/tbody/tr[" + (row + 1) + "]/td[2]/div"))
								.getText();
						System.out.println("The celText is :" + celtext);
						if (celtext.contains(LocNum)) {
							Date todaysDate = getTodaysDate();
							System.out.println("Today's Date is :" + todaysDate);
							String date = driver.findElement(By.xpath("(//*[@id='duplicate-table']/tbody/tr["
									+ (row + 1) + "]/td[2]/div/parent::*/following-sibling::td//div//span)[1]"))
									.getText();
							System.out.println("Date displayed is :" + date);
							String finalDate = date.substring(date.lastIndexOf(":") + 1);
							System.out.println("Final Date is :" + finalDate);
							Date displayeddate = formats.parse(finalDate);
							System.out.println("Displayed Date is : " + displayeddate);
							if (todaysDate.equals(displayeddate)) {
								int entiresText1 = NumOfentries(Dup_Table_Info);
								System.out.println("Final entries in Table is :" + entiresText1);
								String ExtNotes = driver.findElement(By.xpath("(//*[@id='duplicate-table']/tbody/tr["+(row+1)+"]/td[2]/div/parent::*/following-sibling::td[2]//div[@class='duplicate-note']//span)[1]")).getText();
								System.out.println("External Notes is :" +ExtNotes);
								if(ExtNotes.equalsIgnoreCase(timestamp)) {
								if (!Status.equals("New") || !Status.equals("In Progress")) {
									Assert.assertEquals(entiresText1, entiresText - 1);
								} else {
									Assert.assertEquals(entiresText1, entiresText);
								}
								}
								break Outer;
							} else {
								System.out.println("Location Number doesn't exist");
							}
						}
					} if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
			}
		} else {
			System.out.println("No Data Available");
		}
		ComPenSearchbox.click();
		ComPenSearchbox.clear();
	}

	public Date getTodaysDate() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		System.out.println(var);
		String today = ((JavascriptExecutor) driver)
				.executeScript("return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		System.out.println(today);
		Date todaydate = formats.parse(today);
		System.out.println(todaydate);
		return todaydate;
	}
}
