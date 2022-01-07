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
	public static String ReviewId;
	public static String Review;

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

	@FindBy(xpath = "(//div[@class='modal-body']//div[@class='row'])[2]//span//strong")
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
	public int getHistoryCount(String ResponseSelected) throws Exception {
		int Count = 1;
		boolean link = HistoryLink(ResponseSelected);
		if (link == true) {
			try {
				int lastpage = Integer.parseInt(driver
						.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
				System.out.println("Last Page Number is :" + lastpage);
				waitForElement(paginationPrev, 10);
				clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
				List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
				int size = Res.size();
				Outer: if (paginationNext.isDisplayed()) {
					System.out.println("Google it");
					for (int i = 1; i <= lastpage; i++) {
						for (int row = 1; row <= size; row++) {
							WebElement ViewHisLink = driver
									.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row
											+ "]/../..//div//div//a[contains(text(),'View History')]"));

							clickelement(ViewHisLink);
							JSWaiter.waitJQueryAngular();
							Thread.sleep(5000);
							String count = HistoryCount.getText();
							System.out.println("The activity count is : " + count);
							count = count.replace("activities", "").trim();
							Count = Integer.parseInt(count);
							System.out.println("The total History Count is : " + Count);
							BaseClass.addEvidence(driver, "To get history count", "yes");
							clickelement(HistoryClose);
							break Outer;
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Count;
	}

	public boolean HistoryLink(String ResponseSelected) {
		boolean b = false;
		try {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					for (int row = 1; row <= size; row++) {
						String Response = driver
								.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
								.getText();
						System.out.println("The Response is : " + Response);
						if (Response.contains(ResponseSelected)) {
							WebElement ViewHisLink = driver
									.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row
											+ "]/../..//div//div//a[contains(text(),'View History')]"));
							if (ViewHisLink.isDisplayed()) {
								b = true;
								break Outer;
							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
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
	public String getHistoryStatus(String ResponseSelected) throws Exception {
		String Status = null;
		boolean link = HistoryLink(ResponseSelected);
		if (link == true) {
			try {
				int lastpage = Integer.parseInt(driver
						.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
				System.out.println("Last Page Number is :" + lastpage);
				waitForElement(paginationPrev, 10);
				clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
				List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
				int size = Res.size();
				Outer: if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						for (int row = 1; row <= size; row++) {
							WebElement ViewHisLink = driver
									.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row
											+ "]/../..//div//div//a[contains(text(),'View History')]"));

							clickelement(ViewHisLink);
							Thread.sleep(5000);
							Status = HstryStatus.getText();  
							System.out.println("The Status is : " + Status);
							clickelement(HistoryClose);
							break Outer;
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Status;
	}

	/**
	 * To Edit Response and verify count
	 * 
	 * @throws Exception
	 */
	public void EditResponseAndVerifyCountandStatus(String ResponseSelected) throws Exception {
		try {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					for (int row = 1; row <= size; row++) {
						int hstrycountbeforeEdit = getHistoryCount(ResponseSelected); 
						System.out.println("The history count before Editing the Response : " + hstrycountbeforeEdit);
						String Response = driver
								.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
								.getText();
						System.out.println("The Response is : " + Response);
						if (Response.contains(ResponseSelected)) {
							WebElement Edit = driver.findElement(By.xpath("(//a[@id='editLink'])[" + row + "]"));
							clickelement(Edit);
							waitForElement(TextArea, 5);
							clickelement(TextArea);
							TextArea.sendKeys("/+<b>");
							BaseClass.addEvidence(driver, "To Edit Response", "yes");
							scrollByElement(SaveBtn);
							clickelement(SaveBtn);
							JSWaiter.waitJQueryAngular();
							Thread.sleep(5000);
							int hstrycountafterEdit = getHistoryCount(ResponseSelected);
							System.out.println("The history count after Editing the Response : " + hstrycountafterEdit);
							soft.assertTrue((hstrycountafterEdit == hstrycountbeforeEdit + 1),
									"Count has not increased");
							Response = driver
									.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
									.getText();
							System.out.println("The Response Text is : " + Response);
							soft.assertTrue(Response.contains(ResponseSelected + "/+<b>"),
									"Text doesn't contains + </b>");
							JSWaiter.waitJQueryAngular();
							Thread.sleep(6000); 
							String Status = getHistoryStatus(ResponseSelected);
							System.out.println("The Status is : " + Status);
							JSWaiter.waitJQueryAngular();
							Thread.sleep(5000);
							String Stat = driver
									.findElement(By.xpath("(//span[@class='response-status'])[" + row + "]")).getText();
							System.out.println("The status selected is for PA : " + Stat);
							BaseClass.addEvidence(driver, "Test to verify status of the response", "yes");
							soft.assertEquals(Status, Stat);
							soft.assertAll();
							break Outer;
						} else {
							System.out.println("Response Not Found");
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * To Delete the Response
	 * 
	 * @throws InterruptedException
	 */
	public void DeleteResponse(String Responsetxt) throws InterruptedException {
		int numberofentriesbeforeDelete = NumOfentriesinPage(Entry);
		System.out.println("The Number of entries is : " + numberofentriesbeforeDelete);
		try {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					for (int row = 1; row <= size; row++) {
						WebElement response = driver.findElement(By.xpath("(//*[contains(@class,'response-text')])["+ row +"]"));
						scrollByElement(response);
						String Restxt = response.getText();
						System.out.println("The Response txt is : " +Restxt);
						BaseClass.addEvidence(driver, "Test to get response text", "yes");
						Thread.sleep(4000);
						if(Restxt.contains(Responsetxt)) {
							WebElement DeleteBtn = driver.findElement(By.xpath("(//*[contains(@class,'response-text')])["+ row +"]/../..//div//a[@data-target='#deleteResponse']"));
							scrollByElement(DeleteBtn);
							clickelement(DeleteBtn);
							Thread.sleep(4000);
							BaseClass.addEvidence(driver, "Test to delete response", "yes");
							WebElement DelConfirm = driver.findElement(By.xpath("//button[@class='btn btn-width-sm btn-primary']"));
							clickelement(DelConfirm);
							Thread.sleep(4000);
							JSWaiter.waitJQueryAngular();
							int numberofentriesafterDelete = NumOfentriesinPage(Entry);
							System.out.println("The Number of entries is : " + numberofentriesafterDelete);
							Assert.assertTrue((numberofentriesafterDelete == numberofentriesbeforeDelete - 1), "Count has not decreased");
							break Outer;
						}
					}if (paginationNext.isEnabled()) {  
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void clickManageLink(String ResponseSelected) {
		boolean dataavailable = DataAvailable();
		boolean x = true;
		if (dataavailable == false) {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@id='Review']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			try {

				Outer: if (paginationNext.isDisplayed()) {
					List<WebElement> Responses = driver
							.findElements(By.xpath("//div[@class='card card-draft response-padding ']//div//p"));
					int size = Responses.size();
					System.out.println("The size of the list is : " + size);
					for (int i = 1; i <= lastpage; i++) {
						for (int row = 1; row <= size; row++) {
							String Response = driver
									.findElement(By.xpath(
											"(//div[@class='card card-draft response-padding ']//div//p)[" + row + "]"))
									.getText();
							System.out.println("The Response is : " + Response);
							BaseClass.addEvidence(driver, "Test to verify response added", "yes");
							if (Response.equals(ResponseSelected + time_Stamp)) {
								// WebElement manage = driver.findElement(By.xpath("(//div[@class='card
								// card-draft response-padding ']//div//p)["+ i
								// +"]/../../div//button//a[contains(text(),'Manage')]"));
								WebElement manage = driver
										.findElement(By.xpath("//a[@href='/ResponseManagement/Index/']"));
								scrollByElement(manage);
								clickelement(manage);
								x = false;
							} else {
								System.out.println("No Response Found");
							}
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (x == true) {
			System.out.println("Not able to find response added");
			Assert.fail("Not able to find response added");
		}
	}

	public void AddResponseApprover(String Response) throws Exception {
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@id='Review']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);

			try {
				Outer: if (paginationNext.isDisplayed()) {
					List<WebElement> AddLinks = driver
							.findElements(By.xpath("//div[@id='Review']//a[contains(text(),'Add Owner Response')]"));
					int size = AddLinks.size();
					for (int i = 1; i <= size; i++) {
						WebElement AddLink = driver.findElement(
								By.xpath("(//div[@id='Review']//a[contains(text(),'Add Owner Response')])[" + i + "]"));
						clickelement(AddLink);
						Thread.sleep(5000);
						boolean b = BadReview(); 
						if (b == false) {
							time_Stamp = timeStamp();
							System.out.println("The time stamp is : " + time_Stamp);
							WebElement ele1 = driver.findElement(
									By.xpath("(//textarea[@placeholder='Enter response here'])[" + i + "]"));
							scrollByElement(ele1);
							clickelement(ele1);
							ele1.sendKeys(Response + time_Stamp);
							WebElement ele2 = driver
									.findElement(By.xpath("(//button[contains(text(),'Submit')])[" + i + "]"));
							clickelement(ele2);
							WebElement ele3 = driver.findElement(By.xpath("(//button[contains(text(), 'Ok')])"));
							clickelement(ele3);
							JSWaiter.waitJQueryAngular();
							Assert.assertTrue(driver.findElement(By.xpath("//div[@class='status-login-container']"))
									.isDisplayed(), "Container not displayed");
							break Outer;
						} else {
							System.out.println("Cannot be added because it's a bad review");
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To Approve Response
	 * 
	 * @throws Exception
	 */
	public void ApproveResponse(String ResponseSelected) throws Exception { 
		
		
		boolean a = false;
	try {
		int lastpage = Integer.parseInt(
				driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
		List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
		int size = Res.size();
		Outer: if (paginationNext.isDisplayed()) {
			for (int i = 1; i <= lastpage; i++) {
				for (int row = 1; row <= size; row++) {
					String Response = driver
							.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
							.getText();
					System.out.println("The Response is : " + Response);
					System.out.println("The Response Text is : " + Response);
					if (Response.contains(ResponseSelected)) {
						WebElement stattext = driver.findElement(By.xpath(
								"(//div//span[@class='response-status'])["+ row +"]"));
						String text = stattext.getText();
						System.out.println("The status is : " + text);
						BaseClass.addEvidence(driver, "Test to approve response", "yes");
						if (text.equals("Pending Approval")) {
							WebElement ApproveBtn = driver.findElement(By.xpath(
									"(//div//span[@class='response-status'])["+ row +"]//following-sibling::span//button[@id='btnApprove']"));
							clickelement(ApproveBtn);
							a = true;
							break Outer;
						} else {
							System.out.println("Expected status not available");
						}
					} else {
						System.out.println("Response Not Found");
					}
				}
				if (paginationNext.isEnabled()) {
					scrollByElement(paginationNext);
					paginationNext.click();
					JSWaiter.waitJQueryAngular();
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	if(a==false) {
		Assert.fail("No Response found");
	}
}

	public void RejectResponse(String ResponseSelected) throws Exception { 
		
		boolean a = false;
		try {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) {
						for (int i = 1; i <= lastpage; i++) {
							for (int row = 1; row <= size; row++) {
								String Response = driver
										.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
										.getText();
								System.out.println("The Response is : " + Response);
								BaseClass.addEvidence(driver, "Test to get resposne", "yes");
								if (Response.contains(ResponseSelected)) {
									WebElement stattext = driver.findElement(By.xpath(
											"(//div//span[@class='response-status'])["+ row +"]"));
									String text = stattext.getText();
									System.out.println("The status is : " + text);									
									if (text.equals("Pending Approval")) {
										WebElement RejectBtn = driver.findElement(By.xpath("(//div//span[@class='response-status'])["+ row +"]/..//button[@id='btnReject']"));
										clickelement(RejectBtn);
										WebElement RejectCom = driver.findElement(By.xpath("//textarea[@id='rejectComments']"));
										clickelement(RejectCom);
										RejectCom.sendKeys("Please add few more details");
										BaseClass.addEvidence(driver, "Test to reject response", "yes");
										WebElement RejectConfirm = driver.findElement(By.xpath("//button[contains(text(),'Reject')]"));
										clickelement(RejectConfirm);
										a = true;
									}
								}
							}if (paginationNext.isEnabled()) {
								scrollByElement(paginationNext);
								paginationNext.click();
								JSWaiter.waitJQueryAngular();
							}
						}
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(a == false) {
			Assert.fail("No response found");
		}
	}

	/**
	 * To Approve Response with Comments
	 * 
	 * @throws Exception
	 */
	public void ApproveResponsewithComments(String ResponseSelected) throws Exception {
		boolean a = false;
		try {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					for (int row = 1; row <= size; row++) {
						String Response = driver
								.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
								.getText();
						System.out.println("The Response is : " + Response);
						System.out.println("The Response Text is : " + Response);
						if (Response.contains(ResponseSelected)) {
							WebElement stattext = driver.findElement(By.xpath(
									"(//div//span[@class='response-status'])["+ row +"]"));
							String text = stattext.getText();
							System.out.println("The status is : " + text);
							BaseClass.addEvidence(driver, "Test to approve response", "yes");
							if (text.equals("Pending Approval")) {
								WebElement ApproveBtn = driver.findElement(By.xpath(
										"(//div//span[@class='response-status'])["+ row +"]//following-sibling::span//button[@class='btn-approve-dd dropdown-toggle dropdown-toggle-split paddingtop-button']"));
								clickelement(ApproveBtn);
								WebElement Approve = driver.findElement(
										By.xpath("(//div//span[@class='response-status'])["+ row +"]//following-sibling::span//button[@class='btn-approve-dd dropdown-toggle dropdown-toggle-split paddingtop-button']//following-sibling::div//a"));
								clickelement(Approve);
								BaseClass.addEvidence(driver, "Data table after approve of response", "yes");
								WebElement ApproveConfirm = driver.findElement(By.xpath("//textarea[@id='approveComments']"));
								clickelement(ApproveConfirm);
								ApproveConfirm.sendKeys("Response Approved");
								BaseClass.addEvidence(driver, "Data table after approve of response", "yes");
								WebElement ApproveComments = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
								clickelement(ApproveComments);
								a = true;
								break Outer;
							} else {
								System.out.println("Expected status not available");
							}
						} else {
							System.out.println("Response Not Found");
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(a==false) {
			Assert.fail("No Response found");
		}
	}
	
	public void verifyresponse(String Responsetxt) { 
		boolean a = false;
		try {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//div[@id='responseContainer']//div[@class='card card-draft response-padding '])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					for (int row = 1; row <= size; row++) {
						WebElement response = driver.findElement(By.xpath("(//div[@id='responseContainer']//div[@class='card card-draft response-padding '])["+ row +"]"));
						scrollByElement(response);
						String Restxt = response.getText();
						System.out.println("The Response txt is : " +Restxt);
						BaseClass.addEvidence(driver, "Test to get response text", "yes");
						Thread.sleep(4000);
						if(Restxt.contains(Responsetxt)) {
							ReviewId = driver.findElement(By.xpath("(//div[@id='responseContainer'])["+ row +"]")).getAttribute("data-reviewid");
							System.out.println("The Reviews Id is : " +ReviewId); 
							a = true;
							break Outer;
						}else {
							System.out.println("No Response found");
						}
					}if (paginationNext.isEnabled()) {  
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(a == false) {
			Assert.fail("No Reponse found");
		}
	}

	public void DeleteApprovedResponseApprover(String Responsetxt) throws Exception { 

		boolean a = false;
		try {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) { 
				for (int i = 1; i <= lastpage; i++) {
					for (int row = 1; row <= size; row++) {
						WebElement response = driver.findElement(By.xpath("(//*[contains(@class,'response-text')])["+ row +"]"));
						scrollByElement(response);
						String Restxt = response.getText();
						System.out.println("The Response txt is : " +Restxt);
						BaseClass.addEvidence(driver, "Test to get response text", "yes");
						Thread.sleep(4000);
						if(Restxt.contains(Responsetxt)) {
							WebElement Status = driver.findElement(By.xpath("(//*[contains(@class,'response-text')])["+ row +"]/..//div[@class='status-login-container']"));
							String Stat = Status.getText();
							if(Stat.equals("Completed")) {
							WebElement DeleteBtn = driver.findElement(By.xpath("(//*[contains(@class,'response-text')])["+ row +"]/..//div[@class='delete-edit-container']//a[@title='Delete']"));
							scrollByElement(DeleteBtn);
							clickelement(DeleteBtn);
							Thread.sleep(4000);
							WebElement DelConfirm = driver.findElement(By.xpath("//button[@class='btn btn-width-sm btn-primary']"));
							clickelement(DelConfirm);
							Thread.sleep(4000);
							JSWaiter.waitJQueryAngular();
							BaseClass.addEvidence(driver, "Test to delete response", "yes");
							WebElement DeleteSuccess = driver.findElement(By.xpath("//button[@class='btn btn-width-sm btn-success']"));
							clickelement(DeleteSuccess);
							a = true;
							break Outer;
							}else {
								System.out.println("Not found the reponse");
							}
						}
					}if (paginationNext.isEnabled()) {  
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(a==false) {
			Assert.fail("Didn't find the response");
		}
	}

	public void verifyRejectResponse(String Responsetxt) throws Exception {
		
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			boolean a = false;
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				Outer:	if (paginationNext.isDisplayed()) {
					for (int j = 1; j <= lastpage; j++) {
						List<WebElement> Res = driver.findElements(By.xpath("(//div[@id='responseContainer']//div[@class='card card-draft response-padding ']//p)"));
						int size = Res.size();
						for (int row = 1; row <= size; row++) {
							WebElement response = driver.findElement(By.xpath("(//div[@id='responseContainer']//div[@class='card card-draft response-padding ']//p)["+ row +"]"));
							scrollByElement(response);
							String Restxt = response.getText();
							System.out.println("The Response txt is : " +Restxt);
							BaseClass.addEvidence(driver, "Test to get response text", "yes");
							Thread.sleep(4000);
							if(Restxt.contains(Responsetxt)) {
								WebElement stat = driver.findElement(By.xpath("(//div[@id='responseContainer']//div[@class='card card-draft response-padding ']//p)["+ row +"]/..//span[@class='color-red']"));
								scrollByElement(stat);
								String status = stat.getText();
								System.out.println("The status displayed is : " +status);
								soft.assertEquals(status, "Rejected");
								BaseClass.addEvidence(driver, "Test to get response text", "yes");
								a = true;
								break Outer;
							}
						}if (paginationNext.isEnabled()) { 
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(a==false) {
				Assert.fail("No response found");
			}
		}else {
			System.out.println("No Data Available");
		}		
		soft.assertAll();
	}

	public void verifyDeletedResponse() throws Exception {  
		
		boolean dataavailable = DataAvailable();
			if (dataavailable == false) {
				int lastpage = Integer
						.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
				System.out.println("Last Page Number is :" + lastpage);
				waitForElement(paginationPrev, 10);
				clickelement(paginationPrev);
				try {
				Outer:	if (paginationNext.isDisplayed()) {
							for (int j = 1; j <= lastpage; j++) {
								int size = driver.findElements(By.xpath("(//div[@id='responseContainer'])")).size();
								System.out.println(size);
								for (int row = 1; row <= size; row++) {
									WebElement res = driver.findElement(By.xpath("(//div[@id='responseContainer'])["+ row +"]"));
									String id = res.getAttribute("data-reviewid");
									System.out.println("The id is : " +id);
									if(id.equals(ReviewId)) {
										System.out.println("Google it");
										WebElement AddLink = driver.findElement(By.xpath("(//div[@id='responseContainer'])["+ row+"]//a[contains(text(),'Add Owner Response')]"));
										Assert.assertTrue(AddLink.isDisplayed(),"Add Link is not displayed");
										BaseClass.addEvidence(driver, "Test to verify response deleted", "yes");
										break Outer;
									}
								}if (paginationNext.isEnabled()) { 
									scrollByElement(paginationNext);
									paginationNext.click();
									JSWaiter.waitJQueryAngular();
								}
							}
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("No Data Available");
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
	
	
	public boolean IsDataAvailable() {
		boolean x = false;
		if(driver.findElement(By.xpath("(//div[@id='noData-DraftResponse'])")).isDisplayed()) {
		String text = driver.findElement(By.xpath("(//div[@id='noData-DraftResponse'])")).getText();
		System.out.println("The text is : " +text);
		if(text.equals("No data available")) {
			x = true;
			}
		}
		return x;
	}

	public void verifystatusandResponse(String ResponseSelected) throws Exception {
		
		boolean dataavailable = DataAvailable();
		
		if (dataavailable == false) {
			boolean a = false;
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
Outer :			try {
				if (paginationNext.isDisplayed()) {
					for (int j = 1; j <= lastpage; j++) {
						int size = driver.findElements(By.xpath("(//div[@id='responseContainer']//div[@class='owner-responses rrm'])")).size();
						System.out.println("The size of elements is : " +size);
						for (int row = 1; row <= size; row++) {
							WebElement Res = driver.findElement(By.xpath("(//div[@id='responseContainer']//div[@class='owner-responses rrm'])["+ row +"]//div[@class='response-text']"));
							scrollByElement(Res);
							String Response = Res.getText();
							System.out.println("The Response is : " +Response);
							if(Response.contains(ResponseSelected)) {
								WebElement statele = driver.findElement(By.xpath("(//div[@id='responseContainer']//div[@class='owner-responses rrm'])["+ row +"]//span"));
								String Status = statele.getText();
								System.out.println("The status text is : " +Status);
								BaseClass.addEvidence(driver, "Test to verify response status", "yes");
								if (Status.equals("Completed")) {
									Thread.sleep(5000);
									WebElement ele = driver.findElement(By.xpath("(//div[@id='responseContainer']//div[@class='owner-responses rrm'])["+ row +"]/../..//div[@class='author']"));
									scrollByElement(ele);
									Thread.sleep(3000);
									String Author = ele.getText();
									System.out.println("The Author is : " +Author);
									// Store the current window handle
									WebElement Listinglink = driver.findElement(By.xpath("(//div[@class='owner-responses rrm']//div[@class='response-text'])["+ row +"]/../../../../..//a[@id='viewListingLink']"));
									String winHandleBefore = driver.getWindowHandle();
									clickelement(Listinglink);
									// Switch to new window opened
									for (String winHandle : driver.getWindowHandles()) {
										driver.switchTo().window(winHandle);
									}
									Thread.sleep(3000);
									scrollByElement(SearchBtnGMap);
									clickelement(SearchBtnGMap);
									clickelement(SearchTxt);
									SearchTxt.sendKeys(Review);
									SearchTxt.sendKeys(Keys.ENTER);
									Thread.sleep(5000);
									WebElement PersonReviewed = driver.findElement(
											By.xpath("//div[@class='section-review-title']//span[contains(text(),'"+ Author.trim() +"')]"));
									if (PersonReviewed.isDisplayed()) {
										scrollByElement(PersonReviewed);
										scrollByElement(ResponseTitleGMap);
										clickelement(ResponseTitleGMap);
										String responsetext = ResponseTitleGMap.getText();
										System.out.println("The Response text is : " + responsetext);
										soft.assertTrue(responsetext.contains(ResponseSelected));
										//soft.assertEquals(responsetext, "Thanks for your Review!! Automated Response");
										Thread.sleep(5000);
										BaseClass.addEvidence(driver, "Test to verify Response in Vendor Site", "yes");
										soft.assertTrue(ResponseTitleGMap.isDisplayed(), "Response Not Displayed");
									} else {
										soft.fail("The review is not displayed");
									}
									// Close the new window, if that window no more required
									driver.close();
									// Switch back to original browser (first window)
									driver.switchTo().window(winHandleBefore);
									Thread.sleep(5000);
									soft.assertAll();
									a = true;
									break Outer;
								} else {
									System.out.println("Status is not completed");
									Assert.fail("Status is not completed");
								}
							}
						}if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(a==false) {
				Assert.fail("No Response found");
			}
		}else {
			System.out.println("No data available");
		}
		
	}
	public void verifyStatus(String ResponseSelected) throws Exception {
		boolean dataavailable = IsDataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			boolean a = false;
	Outer :		try {
				if (paginationNext.isDisplayed()) {
					for (int j = 1; j <= lastpage; j++) {
						int size = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])")).size();
						for (int row = 1; row <= size; row++) {
							String Response = driver
									.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
									.getText();
							System.out.println("The Response is : " + Response);
							if (Response.contains(ResponseSelected)) {
								a = true;
								String Stat = driver
										.findElement(By.xpath("(//span[@class='response-status'])[" + row + "]")).getText();
								System.out.println("The status selected is for PA : " + Stat);
								Assert.assertEquals(Stat, "Pending Approval");
								break Outer;
							}else {
								System.out.println("Response not found");
							}	
						}if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(a==false) {
				Assert.fail("Response added not found");
			}
		}else {
			System.out.println("No Data available");
		}
	}

	public boolean BadReview() {
		boolean b = false;
		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//button[@data-value='false' and contains(text(),'Close')]"));
			int size = ele.size();
			if (size > 0) {
				driver.findElement(By.xpath("//button[@data-value='false' and contains(text(),'Close')]")).click();
				b = true;
			} else {
				b = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	

	public String AddResponse(String Response) { 
		boolean dataavailable = DataAvailable();
		boolean x = true;
		if (dataavailable == false) {
			int lastpage = Integer.parseInt(
					driver.findElement(By.xpath("(//*[@id='Review']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);

			try {

				Outer: if (paginationNext.isDisplayed()) {
					for (int j = 1; j <= lastpage; j++) {
						List<WebElement> ReviewContent = driver
							.findElements(By.xpath("(//div[@class='review-content'])"));
						int size = ReviewContent.size();

						for (int i = 1; i <= size; i++) {
							WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])["+ i +"]"));
							if(ele.isDisplayed()) {
								scrollByElement(ele);
								//add a condition or select source so that you will add response only to google vendor
								Review = ele.getText(); 
								System.out.println("The Review selected is : " +Review);
								try{
									if((driver.findElement(
											By.xpath("((//div[@class='review-content'])[" + i + "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])"))).isDisplayed()) {
										clickelement(driver.findElement(
												By.xpath("((//div[@class='review-content'])[" + i + "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
										Thread.sleep(5000); 
										boolean b = BadReview();
										if (b == false) {
											time_Stamp = timeStamp();
											System.out.println("The time stamp is : " + time_Stamp);
											WebElement ele1 = driver.findElement(
													By.xpath("(//div[@class='review-content'])["+ i +"]//following-sibling::div//textarea[@placeholder='Enter response here']"));
											scrollByElement(ele1);
											clickelement(ele1);
											ele1.sendKeys(Response + time_Stamp);
											BaseClass.addEvidence(driver, "Test to add Response", "yes");
											WebElement ele2 = driver
													.findElement(By.xpath("(//div[@class='review-content'])["+ i +"]//following-sibling::div//button[contains(text(),'Submit')]"));
											clickelement(ele2);
											WebElement ele3 = driver.findElement(By.xpath("(//button[contains(text(), 'Ok')])"));
											clickelement(ele3);
											JSWaiter.waitJQueryAngular();
											x = false;
											ReviewId = driver.findElement(By.xpath("(//div[@id='responseContainer'])["+ i +"]")).getAttribute("data-reviewid");
											System.out.println("The Reviews Id is : " +ReviewId);
											break Outer;
										}else {
											System.out.println("Cannot be added because it's a bad review");
										}	
									}else {
										System.out.println("No Link found");
									}
								}catch(Exception e) {
									e.printStackTrace();
								}
								}
										 else {
								System.out.println("No Review found");
							}
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
		if (x == true) {
			Assert.fail("Failes because of bad review");
		}
		return ReviewId;
	}

	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Timestamp is :" + formattedDate);
		return formattedDate;
	}

}
