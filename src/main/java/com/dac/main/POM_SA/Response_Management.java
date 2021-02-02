package com.dac.main.POM_SA;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import resources.BaseClass;
import resources.CurrentState;
import resources.JSWaiter;

public class Response_Management extends SA_Abstarct_Methods {

	static String status;
	SoftAssert soft = new SoftAssert();

	public Response_Management(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='Status']")
	private WebElement StatusDropDown;

	@FindBy(xpath = "//div[@id='v1-table']")
	private WebElement ResponseTable;

	@FindBy(xpath = "//div[@id='v1-table']//tbody//tr")
	private List<WebElement> Table_Row;

	@FindBy(xpath = "(//div[@id='paginationInfo'])[1]")
	private WebElement Entry;

	@FindBy(xpath = "(//a[contains(text(),'View History')])[1]")
	private WebElement ViewHistoryLink;

	@FindBy(xpath = "(//div[@class='modal-content'])[1]//div[contains(@class,'color')]")
	private WebElement HistoryCount;

	@FindBy(xpath = "(//div[@class='modal-content'])[1]//button[contains(text(),'Close')]")
	private WebElement HistoryClose;

	@FindBy(xpath = "(//a[@id='editLink'])[1]")
	private WebElement EditLink;

	@FindBy(xpath = "(//div//*[@id='editText'])")
	private WebElement TextArea;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement SaveBtn;

	@FindBy(xpath = "(//div[@class='response-rectangle']//div[@class='row'])[2]")
	private WebElement ResponseText;

	@FindBy(xpath = "(//a[@class='delete-link actionSize'])[1]")
	private WebElement DeleteResponse;

	@FindBy(xpath = "(//div[@class='modal-body']//div[@class='row'])[2]//span")
	private WebElement HstryStatus;

	@FindBy(xpath = "(//div[@id='filter-search'])[1]")
	private WebElement AdvancedLink;

	@FindBy(xpath = "//div[@class='input-icon input-icon-right']//input[contains(@class,'ui-widget-content ui-autocomplete-input')]")
	private WebElement TagInput;

	@FindBy(xpath = "//ul[contains(@id,'review-tags')]")
	private WebElement TagEntry;

	@FindBy(xpath = "//a[contains(text(),'Add Owner Response')]")
	private WebElement AddResponseLink;

	@FindBy(xpath = "//textarea[@placeholder='Enter response here']")
	private WebElement EnterResponseText;

	@FindBy(xpath = "//button[@id='editbtn']")
	private WebElement ResponseSubmit;

	@FindBy(xpath = "//button[@class='btn btn-width-sm btn-success']")
	private WebElement ResponseConfirm;

	@FindBy(xpath = "//div[@class='card-body']//h5//span")
	private WebElement ResponseStatus;

	/*-------------------------Pagination-----------------------*/

	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;

	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationLast;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;

	/*-------------------------Pagination-----------------------*/

	/**
	 * Method to select status from Dropdown
	 * 
	 * @param status
	 * @throws InterruptedException
	 */
	public void selectPendingStatus() throws InterruptedException {
		status = "Pending Approval";
		scrollByElement(StatusDropDown);
		clickelement(StatusDropDown);
		clickelement(driver.findElement(By.xpath("//div[@id='Status']//div[contains(text(),'" + status + "')]")));
		Thread.sleep(3000);
	}

	public void SelectRejectStatus() throws InterruptedException {
		status = "Rejected";
		scrollByElement(StatusDropDown);
		clickelement(StatusDropDown);
		clickelement(driver.findElement(By.xpath("//div[@id='Status']//div[contains(text(),'" + status + "')]")));
		Thread.sleep(3000);
	}

	public void verifyResponseStatus() {
		waitForElement(ResponseTable, 5);
		scrollByElement(ResponseTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> rows_table = Table_Row;
					int rows_count = rows_table.size();
					for (int row = 1; row <= rows_count; row++) {
						if (status.equals("Pending Approval")) {
							String Stat = driver
									.findElement(By.xpath("(//span[@class='response-status'])[" + row + "]")).getText();
							System.out.println("The status selected is for PA : " + Stat);
							soft.assertEquals(Stat, "Pending Approval");
						} else if (status.equals("Rejected")) {
							String Stat = driver
									.findElement(By.xpath("(//span[@class='response-status'])[" + row + "]")).getText();
							System.out.println("The status selected is for RE : " + Stat);
							soft.assertEquals(Stat, "Rejected");
						} else {
							System.out.println("All types of responses present");
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
			soft.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To get the activity count
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getHistoryCount() throws Exception {
		int Count = 0;
		if (ViewHistoryLink.isDisplayed()) {
			clickelement(ViewHistoryLink);
			String count = HistoryCount.getText();
			System.out.println("The activity count is : " + count);
			count = count.replace("activities", "").trim();
			Count = Integer.parseInt(count);
			System.out.println("The total History Count is : " + Count);
			BaseClass.addEvidence(driver, "To get history count", "yes");
			clickelement(HistoryClose);
			return Count;
		} else {
			Count = 1;
			return Count;
		}
	}

	/**
	 * To get the activity count
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHistoryStatus() throws Exception {
		String Status = null;
		if (ViewHistoryLink.isDisplayed()) {
			clickelement(ViewHistoryLink);
			Status = HstryStatus.getText();
			System.out.println("The Status is : " + Status);
			clickelement(HistoryClose);
			return Status;
		} else {
			return Status;
		}
	}

	/**
	 * To Edit Response and verify count
	 * 
	 * @throws Exception
	 */
	public void EditResponseAndVerifyCountandStatus() throws Exception {
		int hstrycountbeforeEdit = getHistoryCount();
		System.out.println("The history count before Editing the Response : " + hstrycountbeforeEdit);
		String Resp = ResponseText.getText();
		System.out.println("The Response Text is : " + Resp);
		clickelement(EditLink);
		waitForElement(TextArea, 5);
		clickelement(TextArea);
		TextArea.sendKeys("+ </b>");
		BaseClass.addEvidence(driver, "To Edit Response", "yes");
		scrollByElement(SaveBtn);
		clickelement(SaveBtn);
		int hstrycountafterEdit = getHistoryCount();
		System.out.println("The history count after Editing the Response : " + hstrycountafterEdit);
		soft.assertTrue((hstrycountafterEdit == hstrycountbeforeEdit + 1), "Count has not increased");
		Resp = ResponseText.getText();
		System.out.println("The Response Text is : " + Resp);
		soft.assertTrue(Resp.contains(" + </b>"), "Text doesn't contains + </b>");
		String Status = getHistoryStatus();
		System.out.println("The Status is : " + Status);
		String Stat = driver.findElement(By.xpath("(//span[@class='response-status'])[1]")).getText();
		System.out.println("The status selected is for PA : " + Stat);
		soft.assertEquals(Status, Stat);
		soft.assertAll();
	}

	/**
	 * To Delete the Response
	 */
	public void DeleteResponse() {
		int numberofentriesbeforeDelete = NumOfentriesinPage(Entry);
		System.out.println("The Number of entries is : " + numberofentriesbeforeDelete);
		scrollByElement(DeleteResponse);
		clickelement(DeleteResponse);
		int numberofentriesafterDelete = NumOfentriesinPage(Entry);
		System.out.println("The Number of entries is : " + numberofentriesafterDelete);
		Assert.assertTrue((numberofentriesafterDelete == numberofentriesbeforeDelete - 1), "Count has not decreased");
	}

	/**
	 * To add Response and verify status of response created
	 * 
	 * @throws Exception
	 */
	public void AddResponse() throws Exception {
		waitForElement(AdvancedLink, 10);
		clickelement(AdvancedLink);
		clickelement(TagInput);
		TagInput.sendKeys("Automation");
		TagInput.sendKeys(Keys.ENTER);

		WebElement AddLink = driver.findElement(By.xpath("(//a[@class='add-owner-response-btn enabled'])"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", AddLink);
		//clickelement(AddLink);
		Thread.sleep(5000);
		waitForElement(EnterResponseText, 10);
		clickelement(EnterResponseText);
		if (EnterResponseText.isDisplayed()) {
			EnterResponseText.sendKeys("Thanks for your Review!! Automated Response");
			clickelement(ResponseSubmit);
			Thread.sleep(5000);
			clickelement(ResponseConfirm);
			Thread.sleep(5000);
			scrollByElement(driver.findElement(By.xpath("//div[@class='card card-draft response-padding ']")));
			BaseClass.addEvidence(CurrentState.getDriver(), "Test to add response and verify status	", "yes");
			String status = ResponseStatus.getText();
			System.out.println("The status is : " + status);
			soft.assertEquals(status, "Pending Approval");
		} else {
			System.out.println("No text field found");
			soft.fail("Failed to create response");
		}

		soft.assertAll();

	}
}
