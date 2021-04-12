package com.dac.main.POM_SA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	public static String time_Stamp;

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
	
	@FindBy(xpath = "(//*[@class='reviewEnhancement'])[1]")
	private WebElement ReviewSection;

	@FindBy(xpath = "//*[@id='Review']//*[@class='business-info']")
	private List<WebElement> ReviewInfo;
	
	@FindBy(xpath = "//button[@data-tooltip='Search reviews']")
	private WebElement SearchBtnGMap;
	
	@FindBy(xpath = "//input[@aria-label='Search reviews']")
	private WebElement SearchTxt;
	
	@FindBy(xpath = "//a[@id='viewListingLink']")
	private WebElement ListingLink;
	
	@FindBy(xpath = "//div[contains(text(),'Thanks for your Review')]")
	private WebElement ResponseTitleGMap;
	
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
		int Count = 1;
		boolean link = HistoryLink();
		if (link == true) {
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

	public boolean HistoryLink() {
		boolean b = false;
		try {
			if (ViewHistoryLink.isDisplayed()) {
				b = true;
				return b;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * To get the activity count
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getHistoryStatus() throws Exception {
		String Status = null;
		boolean link = HistoryLink();
		if (link == true) {
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
		soft.assertTrue(Resp.contains("+ </b>"), "Text doesn't contains + </b>");
		String Status = getHistoryStatus();
		System.out.println("The Status is : " + Status);
		String Stat = driver.findElement(By.xpath("(//span[@class='response-status'])[1]")).getText();
		System.out.println("The status selected is for PA : " + Stat);
		soft.assertEquals(Status, Stat);
		soft.assertAll();
	}

	/**
	 * To Delete the Response
	 * @throws InterruptedException 
	 */
	public void DeleteResponse() throws InterruptedException {
		int numberofentriesbeforeDelete = NumOfentriesinPage(Entry);
		System.out.println("The Number of entries is : " + numberofentriesbeforeDelete);
		scrollByElement(DeleteResponse);
		clickelement(DeleteResponse); 
		Thread.sleep(3000);
		WebElement DelConfirm = driver.findElement(By.xpath("//button[@class='btn btn-width-sm btn-primary']"));
		clickelement(DelConfirm);
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
		TagInput.sendKeys("Avi");
		TagInput.sendKeys(Keys.ENTER);
		JSWaiter.waitJQueryAngular();
		WebElement AddLink = driver.findElement(By.xpath("(//a[@class='add-owner-response-btn enabled'])"));
		scrollByElement(AddLink);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", AddLink);
		// clickelement(AddLink);
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
	
	
	public void clickManageLink() {
		WebElement manage = driver.findElement(By.xpath("//a[contains(text(),'Manage')]"));
		scrollByElement(manage);
		clickelement(manage);
	}
	
	public void AddResponseApprover() throws Exception {
		waitForElement(AdvancedLink, 10);
		clickelement(AdvancedLink);
		clickelement(TagInput);
		TagInput.sendKeys("Avi");
		TagInput.sendKeys(Keys.ENTER);
		JSWaiter.waitJQueryAngular();
		WebElement AddLink = driver.findElement(By.xpath("(//a[@class='add-owner-response-btn enabled'])"));
		scrollByElement(AddLink);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", AddLink);
		// clickelement(AddLink);
		Thread.sleep(5000);
		waitForElement(EnterResponseText, 10);
		clickelement(EnterResponseText);
		if (EnterResponseText.isDisplayed()) {
			EnterResponseText.sendKeys("Thanks for your Review!! Automated Response");
			clickelement(ResponseSubmit);
			Thread.sleep(5000);
			clickelement(ResponseConfirm);
			Thread.sleep(5000);
			scrollByElement(driver.findElement(By.xpath("//div[@class='status-login-container']")));
			BaseClass.addEvidence(CurrentState.getDriver(), "Test to add response", "yes");
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='status-login-container']")).isDisplayed(), "Container not displayed");
		}else {
			System.out.println("");
			Assert.fail("Response cannot be added");
		}
	}

	/**
	 * To Approve Response
	 * @throws Exception
	 */
	public void ApproveResponse() throws Exception {
		WebElement stattext = driver.findElement(By.xpath("//div[@id='v1-table']//div[contains(text(),'Thanks for your Review!')]/following::div//span[@class='response-status']"));
		String text = stattext.getText();
		System.out.println("The status is : " +text);
		BaseClass.addEvidence(driver, "Test to approve response", "yes");
		if(text.equals("Pending Approval")) {
			WebElement ApproveBtn = driver.findElement(By.xpath(
					"((//div[@id='v1-table']//div[contains(text(),'Thanks for your Review!')]/following::div[@class='btn-group']//button[@id='btnApprove'])[1])"));
			clickelement(ApproveBtn);
			BaseClass.addEvidence(driver, "Data table after approve of response", "yes");
		}else {
			System.out.println("Expected status not available");
		}
	}

	public void RejectResponse() throws Exception {
		WebElement stattext = driver.findElement(By.xpath("//div[@id='v1-table']//div[contains(text(),'Thanks for your Review!')]/following::div//span[@class='response-status']"));
		String text = stattext.getText();
		System.out.println("The status is : " +text);
		BaseClass.addEvidence(driver, "Test to approve response", "yes");
		if(text.equals("Pending Approval")) {
			WebElement RejectBtn = driver.findElement(By.xpath(
					"((//div[@id='v1-table']//div[contains(text(),'Thanks for your Review!')]/following::div[@class='btn-group']//button[@id='btnReject'])[1])"));
			clickelement(RejectBtn);
			Thread.sleep(3000);
			WebElement RejectCom = driver.findElement(By.xpath("//textarea[@id='rejectComments']"));
			clickelement(RejectCom);
			RejectCom.sendKeys("Please add few more details");
			WebElement RejectConfirm = driver.findElement(By.xpath("//button[contains(text(),'Reject')]"));
			clickelement(RejectConfirm);
		}else {
			System.out.println("Expected status not available");
		}
	}

	/**
	 * To Approve Response with Comments
	 * @throws Exception
	 */
	public void ApproveResponsewithComments() throws Exception {
		WebElement stattext = driver.findElement(By.xpath("//div[@id='v1-table']//div[contains(text(),'Thanks for your Review!')]/following::div//span[@class='response-status']"));
		String text = stattext.getText();
		System.out.println("The status is : " +text);
		BaseClass.addEvidence(driver, "Test to approve response", "yes");
		if(text.equals("Pending Approval")) {
			WebElement ApproveBtn = driver.findElement(By.xpath(
					"((//div[@id='v1-table']//div[contains(text(),'Thanks')]/following::div[@class='btn-group']//button[@class='btn-approve-dd dropdown-toggle dropdown-toggle-split paddingtop-button'])[1])"));
			clickelement(ApproveBtn);
			WebElement Approve = driver.findElement(By.xpath(
					"(//span[@class='approveComment' and contains(text(), 'Approve with comment')])[1]"));
			clickelement(Approve);
			BaseClass.addEvidence(driver, "Data table after approve of response", "yes");
			WebElement ApproveConfirm = driver.findElement(By.xpath("//textarea[@id='approveComments']"));
			clickelement(ApproveConfirm);
			ApproveConfirm.sendKeys("Response Approved");
			BaseClass.addEvidence(driver, "Data table after approve of response", "yes");
			WebElement ApproveComments = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
			clickelement(ApproveComments);
		}else {
			System.out.println("Expected status not available");
		}
	}

	
	
	public void DeleteApprovedResponseApprover() throws Exception {
		waitForElement(AdvancedLink, 10);
		clickelement(AdvancedLink);
		clickelement(TagInput);
		TagInput.sendKeys("Avi");
		TagInput.sendKeys(Keys.ENTER);
		JSWaiter.waitJQueryAngular();
		scrollByElement(driver.findElement(By.xpath("//div[@class='owner-responses rrm']")));
		WebElement Stat = driver.findElement(By.xpath("//div[@class='status-login-container']"));
		String text = Stat.getText();
		System.out.println("The status is : " +text);
		BaseClass.addEvidence(driver, "Test to delete Approved Response", "yes");
		if(text.equals("Completed")) {		
			WebElement DeleteBtn = driver.findElement(By.xpath("(//div[@class='delete-edit-container']//a)[2]"));
			clickelement(DeleteBtn);
			Thread.sleep(3000);
			WebElement DeleteConfirm = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
			clickelement(DeleteConfirm);
			JSWaiter.waitJQueryAngular();
			BaseClass.addEvidence(driver, "Test to Confirm delete Approved Response", "yes");
			WebElement DeleteSuccess = driver.findElement(By.xpath("//button[contains(text(),'Ok')]"));
			clickelement(DeleteSuccess);
			Thread.sleep(2000);
			driver.navigate().refresh();
		}else {
			System.out.println("No status expected found");			
		}
	}
	
	
	public void verifyRejectResponse() throws Exception {
		waitForElement(AdvancedLink, 10);
		clickelement(AdvancedLink);
		clickelement(TagInput);
		TagInput.sendKeys("Avi");
		TagInput.sendKeys(Keys.ENTER);
		JSWaiter.waitJQueryAngular();
		Thread.sleep(3000);
		scrollByElement(driver.findElement(By.xpath("(//div[@class='form-group col-xs-12'])[2]")));
		WebElement Stat = driver.findElement(By.xpath("//h5[@class='card-title']//span"));
		scrollByElement(Stat);
		String text = Stat.getText();
		System.out.println("The status is : " +text);
		BaseClass.addEvidence(driver, "Test to delete Reject Response", "yes");
		if(text.equals("Rejected")) {
			Assert.assertTrue(true, "Expected Status is not available");
		}else {
			Assert.fail("Expected Status is not available");
		}
	}

	public void verifyDeletedResponse() throws Exception {
		waitForElement(AdvancedLink, 10);
		clickelement(AdvancedLink);
		clickelement(TagInput);
		TagInput.sendKeys("Avi");
		TagInput.sendKeys(Keys.ENTER);
		JSWaiter.waitJQueryAngular();
		WebElement AddLink = driver.findElement(By.xpath("(//a[@class='add-owner-response-btn enabled'])"));
		scrollByElement(AddLink);
		BaseClass.addEvidence(driver, "Test to verify Response Deleted", "yes");
		if(AddLink.isDisplayed()) {
			Assert.assertTrue(true, "Link is not displayed");
		}else {
			Assert.fail("No Link Displayed");
		}
	}

	public void verifyDateSelected() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat").toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		JSWaiter.waitJQueryAngular();
		List<Date> ReviewDates = new ArrayList<Date>();
		Date FromDate = getFromDate();
		System.out.println("From Date selected is : " + FromDate);
		Date ToDate = getToDate();
		System.out.println("To Date selected is : " + ToDate);
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int j = 1; j <= lastpage; j++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int k = 1; k <= size; k++) {

							WebElement date = driver.findElement(By.xpath("(//div[@class='date-text'])[" + k + "]"));
							scrollByElement(date);
							String reviewdate = date.getText();
							System.out.println("Review Date is :" + reviewdate);
							Date UIDate = formats.parse(reviewdate);
							ReviewDates.add(UIDate);
							BaseClass.addEvidence(driver, "Test to get UI Date", "yes");

						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
					System.out.println("The list consists of :" + ReviewDates);
					for (int l = 0; l <= ReviewDates.size() - 1; l++) {
						soft.assertTrue(ReviewDates.get(l).compareTo(FromDate) >= 0
								&& ReviewDates.get(l).compareTo(ToDate) <= 0);
					}
				}
				soft.assertAll();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Method that returns data is available or not in the table
	 * 
	 * @return
	 */
	public boolean DataAvailable() {
		String text = driver.findElement(By.xpath("(//div[@class='form-group'])[2]")).getText();
		System.out.println("The text is :" + text);
		if (text.equals("No data available")) {
			return true;
		} else {
			return false;
		}
	}

	
	public void verifystatusandResponse() throws Exception {
		clickelement(AdvancedLink);
		clickelement(TagInput);
		TagInput.sendKeys("Avi"); 
		TagInput.sendKeys(Keys.ENTER);
		String stat = driver.findElement(By.xpath("//span[@class='status-text completed']")).getText();
		System.out.println("The Status is : " +stat);
		BaseClass.addEvidence(driver, "Test to verify status", "yes");
		if(stat.equals("Completed")) {
			Thread.sleep(10000);
			clickOnLinknVerifyResponse();
		}else{
			System.out.println("Status is not completed");
			Assert.fail("Status is not completed");
		}
	}
	
	public void clickOnLinknVerifyResponse() throws Exception {
		if(ListingLink.isDisplayed()) {
			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			clickelement(ListingLink);
			// Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}	
			scrollByElement(SearchBtnGMap);
			clickelement(SearchBtnGMap);
			clickelement(SearchTxt);
			SearchTxt.sendKeys("This location is on Scarboro - a bit far but worth the drive. (21-01-05)");
			SearchTxt.sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			WebElement PersonReviewed = driver.findElement(By.xpath("//div[@class='section-review-title']//span[contains(text(),'Greene Sydes')]"));
			if(PersonReviewed.isDisplayed()) {
				scrollByElement(PersonReviewed);
				scrollByElement(ResponseTitleGMap);
				clickelement(ResponseTitleGMap);
				String responsetext = ResponseTitleGMap.getText();
				System.out.println("The Response text is : " +responsetext);
				soft.assertEquals(responsetext, "Thanks for your Review!! Automated Response"); 
				Thread.sleep(5000);
				BaseClass.addEvidence(driver, "Test to verify Response in Vendor Site", "yes");
				soft.assertTrue(ResponseTitleGMap.isDisplayed(), "Response Not Displayed");
			}else {
				soft.fail("The review is not displayed");
			}
			// Close the new window, if that window no more required
			driver.close();
			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);		
			Thread.sleep(5000);
			soft.assertAll();
		}
	}
	
	
	public void verifyStatus() throws Exception {
		WebElement stattext = driver.findElement(By.xpath("//div[@id='v1-table']//div[contains(text(),'Thanks for your Review!')]/following::div//span[@class='response-status']"));
		String text = stattext.getText();
		System.out.println("The status is : " +text);
		BaseClass.addEvidence(driver, "Test to approve response", "yes");
		Assert.assertEquals(text, "Pending Approval");
	}
	
	public boolean BadReview() {
		boolean b =false;
		try {
		List<WebElement> ele = /*driver.findElement(By.xpath("(//div[@class='modal-dialog']//button[@data-dismiss='modal']/following::div//h3[@class='modal-title'])/../..//button[@data-dismiss='modal']"));
						*/ driver.findElements(By.xpath("//button[@data-value='false' and contains(text(),'Close')]"));
		int size = ele.size();
		if(size > 0) {
			driver.findElement(By.xpath("//button[@data-value='false' and contains(text(),'Close')]")).click();
			b = true;
		}else {
			b = false;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public void AddResponse(String Response) {
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='Review']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			
			try {
			Outer :	if (paginationNext.isDisplayed()) {
					List<WebElement> AddLinks = driver.findElements(By.xpath("//div[@id='Review']//a[contains(text(),'Add Owner Response')]"));
					int size = AddLinks.size();
					for(int i = 1; i <= size; i++ ) {
						WebElement AddLink = driver.findElement(By.xpath("(//div[@id='Review']//a[contains(text(),'Add Owner Response')])["+ i +"]"));
						clickelement(AddLink);
						Thread.sleep(5000);
						boolean b = BadReview();
						if(b==false) {
						//WebElement ele = driver.findElement(By.xpath("(//div[@class='modal-dialog']//button[@data-dismiss='modal']/following::div//h3[@class='modal-title'])/../..//button[@data-dismiss='modal']"));
						//if(!(driver.findElement(By.xpath("//button[@data-value='false' and contains(text(),'Close')]")).isDisplayed())) {
							time_Stamp = timeStamp();
							System.out.println("The time stamp is : " +time_Stamp);
							WebElement ele1 = driver.findElement(By.xpath("(//textarea[@placeholder='Enter response here'])["+ i +"]"));
							scrollByElement(ele1);
							clickelement(ele1);
							ele1.sendKeys(Response + time_Stamp);
							WebElement ele2 = driver.findElement(By.xpath("(//button[contains(text(),'Submit')])["+ i +"]"));
							clickelement(ele2);
							WebElement ele3 = driver.findElement(By.xpath("(//button[contains(text(), 'Ok')])"));
							clickelement(ele3);
							JSWaiter.waitJQueryAngular();
							break Outer;
						}else {
							System.out.println("Cannot be added because it's a bad review");
						}
					}if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No Data Available");
		}
	}
	
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Timestamp is :"+formattedDate);
		return formattedDate;		
	}

}
