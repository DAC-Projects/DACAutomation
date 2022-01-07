package com.dac.main.POM_SA;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.testng.asserts.SoftAssert;

import resources.BaseClass;
import resources.JSWaiter;

public class Quick_Responses extends SA_Abstarct_Methods {

	SoftAssert soft = new SoftAssert();
	public static String time_Stamp;
	public static String ReviewId;
	public static String Review;
	public static int sizeofresponsestobecopiedGroup;
	public static int sizeofresponsestobecopiedfromGroup;
	public static int sizeofresponsestobecopiedGroupaftercopying;
	public static int sizeofResponse;

	public Quick_Responses(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	/*------------------------Locators--------------------------*/

	@FindBy(xpath = "(//div[@class='reviewEnhancement'])[1]")
	private WebElement ReviewTable;

	@FindBy(xpath = "(//div[@id='paginationInfo'])[1]")
	private WebElement Entry;

	@FindBy(xpath = "(//div[@class='reviewEnhancement']//div[@class='review-content'])")
	private List<WebElement> Table_Row;

	@FindBy(xpath = "//span[@id='select2-quickResponseGroup-container']")
	private WebElement GroupSelect;

	@FindBy(xpath = "//button[@class='btn btn-width-sm btn-success']")
	private WebElement SuccessBtn;

	@FindBy(xpath = "(//div//*[@id='editText'])")
	private WebElement TextArea;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement SaveBtn;

	@FindBy(xpath = "//div[@id='Status']")
	private WebElement StatusDropDown;

	/*------------------------Locators--------------------------*/

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
	 * Create and add quick responses using Approver
	 * 
	 * @param Group
	 * @param QuickResponse
	 */
	public void AddOwnerQuickResponseApprover(String Group, String QuickResponse) {
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ReviewContent = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = ReviewContent.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							scrollByElement(ele);
							Review = ele.getText();
							System.out.println("The Review selected is : " + Review);
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										time_Stamp = timeStamp();
										System.out.println("The time stamp is : " + time_Stamp);
										WebElement ManageLink = driver.findElement(By.xpath(
												"(//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//a[contains(text(),'Manage')]"));
										action.moveToElement(ManageLink).click().build().perform();
										// clickelement(ManageLink);
										JSWaiter.waitJQueryAngular();
										BaseClass.addEvidence(driver, "Test to add Quick Response", "yes");
										clickelement(GroupSelect);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ Group + "')]"))
												.click();
										BaseClass.addEvidence(driver, "Test to select group", "yes");
										WebElement CreateResponse = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@id='create-precanned-response-btn'])[1]"));
										clickelement(CreateResponse);
										BaseClass.addEvidence(driver, "Test to click on create group response", "yes");
										Thread.sleep(2000);
										WebElement AddTitle = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//div[@class='create-precanned-response-popup']//div[@class='responseList']//input)[1]"));
										clickelement(AddTitle);
										AddTitle.sendKeys("Automation");
										WebElement AddResponseTxt = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//div[@class='create-precanned-response-popup']//div[@class='responseList']//textarea)[1]"));
										clickelement(AddResponseTxt);
										AddResponseTxt.sendKeys(QuickResponse + time_Stamp);
										BaseClass.addEvidence(driver, "Test to add quick response", "yes");
										WebElement CreateResponseBtn = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//div[@class='create-precanned-response-popup']//button[@id='create-precanned-response-submit-btn'])[1]"));
										clickelement(CreateResponseBtn);
										WebElement SelectResponse = driver.findElement(By.xpath(
												"//div[@class='radioSelection responseListContent']//span[@class='radioResponseContent' and contains(.,'"
														+ QuickResponse + "')]"));
										clickelement(SelectResponse);
										WebElement UseResponseBtn = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@id='use-precanned-response-btn'])[1]"));
										clickelement(UseResponseBtn);
										BaseClass.addEvidence(driver, "Test to add created response", "yes");
										WebElement SubmitBtn = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@class='btn btn-primary submit-btn'])[1]"));
										clickelement(SubmitBtn);
										clickelement(SuccessBtn);
										ReviewId = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content']))["
														+ row + "]//following-sibling::div[@id='responseContainer']"))
												.getAttribute("data-reviewid");
										System.out.println("The Reviews Id is : " + ReviewId);
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
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
	}

	/**
	 * to verify whether a review is bad
	 * 
	 * @return
	 */
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

	/**
	 * To get current time and date
	 * 
	 * @return
	 */
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Timestamp is :" + formattedDate);
		return formattedDate;
	}

	/**
	 * To verify response added
	 * 
	 * @param Response
	 */
	public void verifyResponse(String Response) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseContent = driver
							.findElements(By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])"));
					int size = ResponseContent.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(
								By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])[" + row + "]"));
						String ResponseAdded = ele.getText();
						if (ResponseAdded.contains(Response)) {
							BaseClass.addEvidence(driver, "Test to verify response added", "yes");
							soft.assertEquals(ResponseAdded, Response + time_Stamp);
							//please add code to verify the status is in completed state
							a = true;
							break Outer;
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
		if (a == false) {
			soft.fail("No Response found");
		}
		soft.assertAll();
	}

	/**
	 * To delete added response approver
	 * 
	 * @param Response
	 */
	public void DeleteResponseApprover(String Response) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseContent = driver
							.findElements(By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])"));
					int size = ResponseContent.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(
								By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])[" + row + "]"));
						String ResponseAdded = ele.getText();
						if (ResponseAdded.contains(Response)) {
							BaseClass.addEvidence(driver, "Test to delete response added", "yes");
							WebElement DeleteBtn = driver.findElement(
									By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])[" + row
											+ "]/..//div[@class='delete-edit-container']//a[@title = 'Delete']"));
							clickelement(DeleteBtn);
							BaseClass.addEvidence(driver, "Test to delete confirmation", "yes");
							WebElement DeleteConfirm = driver
									.findElement(By.xpath("//button[@class='btn btn-width-sm btn-primary']"));
							clickelement(DeleteConfirm);
							WebElement DeleteSuccess = driver
									.findElement(By.xpath("//button[@class='btn btn-width-sm btn-success']"));
							clickelement(DeleteSuccess);
							a = true;
							break Outer;
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
		if (a == false) {
			soft.fail("No Response found");
		}
		soft.assertAll();
	}

	/**
	 * to verify response deleted
	 */
	public void verifyDeletedResponseApprover() {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseContent = driver
							.findElements(By.xpath("(//div[@id='responseContainer'])"));
					int size = ResponseContent.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@id='responseContainer'])[" + row + "]"));
						String RevId = ele.getAttribute("data-reviewid");
						System.out.println("The Review Id is : " + RevId);
						System.out.println("The Review Id for Response added : " + ReviewId);
						if (ReviewId.equals(RevId)) {
							WebElement AddLink = driver.findElement(By.xpath("((//div[@id='responseContainer'])[" + row
									+ "]//a[contains(text(),'Add Owner Response')])"));
							scrollByElement(AddLink);
							BaseClass.addEvidence(driver, "Test to verify the response is deleted", "yes");
							soft.assertTrue(AddLink.isDisplayed(), "The Link is not displayed");
							a = true;
							break Outer;
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
		if (a == false) {
			soft.fail("No Response found");
		}
		soft.assertAll();
	}

	/**
	 * To edit added response and verify the same as approver
	 * 
	 * @param Response
	 */
	public void EditandVerifyResponseApprover(String Response) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseContent = driver
							.findElements(By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])"));
					int size = ResponseContent.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(
								By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])[" + row + "]"));
						String ResponseAdded = ele.getText();
						if (ResponseAdded.contains(Response)) {
							BaseClass.addEvidence(driver, "Test to delete response added", "yes");
							WebElement EditBtn = driver.findElement(
									By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])[" + row
											+ "]/..//div[@class='delete-edit-container']//a[@title = 'Edit']"));
							clickelement(EditBtn);
							WebElement textbox = driver
									.findElement(By.xpath("(//textarea[@data-reviewobjid='" + ReviewId + "'])"));
							scrollByElement(textbox);
							clickelement(textbox);
							textbox.sendKeys(" Edited");
							WebElement EditSubmit = driver
									.findElement(By.xpath("//button[@data-reviewobjid='" + ReviewId + "']"));
							clickelement(EditSubmit);
							WebElement submitconfirm = driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
							clickelement(submitconfirm);
							WebElement submittedconfirm = driver
									.findElement(By.xpath("//button[contains(text(),'Ok')]"));
							clickelement(submittedconfirm);
							WebElement EditResponse = driver.findElement(By.xpath(
									"(//div[@id='responseContainer']//div[@class='response-text'])[" + row + "]"));
							String EditedResponse = EditResponse.getText();
							System.out.println("The Edited Response is : " + EditedResponse);
							BaseClass.addEvidence(driver, "Test to verify edited response", "yes");
							soft.assertTrue(EditedResponse.contains("Edited"), "The response is not edited");
							a = true;
							break Outer;
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
		if (a == false) {
			soft.fail("No Response found");
		}
		soft.assertAll();
	}

	/**
	 * to add response using existing responses
	 * 
	 * @param Group
	 */
	public void AddResponseusingexisting(String Group) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseContent = driver
							.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = ResponseContent.size();
					System.out.println("The size of the row is  :  " + size);
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							scrollByElement(ele);
							Review = ele.getText();
							System.out.println("The Review selected is : " + Review);
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
										+ "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										WebElement dropdown = driver.findElement(
												By.xpath("(//select[@id = 'quickResponse-dropdown-location-groups'])["
														+ row + "]"));
										Select select = new Select(dropdown);
										select.selectByVisibleText(Group);
										Thread.sleep(3000);
										List<WebElement> ExistingQuickRespose = driver
												.findElements(By.xpath("((//div[@class='review-content'])[" + row
														+ "]/..//following-sibling::span[contains(@class,'paginationListActive')])"));
										int sizeofEle = ExistingQuickRespose.size();
										System.out.println(":The size of the element is : " + sizeofEle);
										BaseClass.addEvidence(driver, "Test to check if quick response is available",
												"yes");
										if (sizeofEle > 0) {
											WebElement ResponseTobeSelected = driver
													.findElement(By.xpath("((//div[@class='review-content'])[" + row
															+ "]/..//following-sibling::span[contains(@class,'paginationListActive')])[1]"));
											// scrollByElement(ResponseTobeSelected);
											clickelement(ResponseTobeSelected);
											BaseClass.addEvidence(driver, "Test to add existing response", "yes");
											WebElement SubmitBtn = driver.findElement(By.xpath(
													"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
															+ row
															+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@class='btn btn-primary submit-btn'])[1]"));
											clickelement(SubmitBtn);
											clickelement(SuccessBtn);
											ReviewId = driver
													.findElement(By.xpath("(//div[@class='review-content'])[" + row
															+ "]/following-sibling::div[@id='responseContainer']"))
													.getAttribute("data-reviewid");
											System.out.println("The Reviews Id is : " + ReviewId);
											BaseClass.addEvidence(driver, "Test to add existing response", "yes");
											a = true;
											break Outer;
										}

									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}
							} catch (Exception e) {
								e.printStackTrace();

							}

						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (a == false) {
			Assert.fail("No Response found");
		}
	}

	/**
	 * to delete existing response as approver
	 */
	public void DeleteExistingResponseApprover() {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseContent = driver
							.findElements(By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])"));
					int size = ResponseContent.size();
					for (int row = 1; row <= size; row++) {
						System.out.println("The row number is : " + row);
						WebElement ele = driver
								.findElement(By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])["
										+ row + "]/../../..//div[@class='owner-responses rrm']"));
						String ReviewResponseId = ele.getAttribute("data-reviewobjid");
						System.out.println("The Review Id is : " + ReviewResponseId);
						if (ReviewResponseId.equals(ReviewId)) {
							BaseClass.addEvidence(driver, "Test to delete response added", "yes");
							WebElement DeleteBtn = driver.findElement(
									By.xpath("(//div[@id='responseContainer']//div[@class='response-text'])[" + row
											+ "]//preceding-sibling::div//div[@class='delete-edit-container']//a[@title='Delete']"));
							scrollByElement(DeleteBtn);
							clickelement(DeleteBtn);
							BaseClass.addEvidence(driver, "Test to delete confirmation", "yes");
							WebElement DeleteConfirm = driver
									.findElement(By.xpath("//button[@class='btn btn-width-sm btn-primary']"));
							clickelement(DeleteConfirm);
							WebElement DeleteSuccess = driver
									.findElement(By.xpath("//button[@class='btn btn-width-sm btn-success']"));
							clickelement(DeleteSuccess);
							a = true;
							break Outer;
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
		if (a == false) {
			soft.fail("No Response found");
		}
		soft.assertAll();
	}

	/**
	 * to edit response > quick response
	 * 
	 * @param Group
	 */
	public void EditQuickResponses(String Group) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ReviewContent = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = ReviewContent.size();
					System.out.println("The total number of rows is : " + size);
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							scrollByElement(ele);
							Review = ele.getText();
							System.out.println("The Review selected is : " + Review);
							System.out.println("The row number is : " + row);
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										time_Stamp = timeStamp();
										System.out.println("The time stamp is : " + time_Stamp);
										WebElement ManageLink = driver.findElement(By.xpath(
												"(//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//a[contains(text(),'Manage')]"));
										action.moveToElement(ManageLink).click().build().perform();
										// clickelement(ManageLink);
										JSWaiter.waitJQueryAngular();
										clickelement(GroupSelect);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ Group + "')]"))
												.click();
										WebElement EditBtn = driver.findElement(By.xpath(
												"(//span[@class='radioResponseContent']//a[@title = 'Edit'])[1]"));
										scrollByElement(EditBtn);
										clickelement(EditBtn);
										WebElement Edittxt = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//textarea[@id='edittext'])"));
										clickelement(Edittxt);
										Edittxt.sendKeys(time_Stamp);
										WebElement SaveBtn = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[@id = 'edit-precanned-response-submit-btn'])"));
										clickelement(SaveBtn);
										String txt = driver
												.findElement(By.xpath(
														"((//span[@class='radioResponseContent'])[1])/../..//input"))
												.getAttribute("value");
										System.out.println("The txt is : " + txt);
										BaseClass.addEvidence(driver, "Test to edit quickresposne", "yes");
										soft.assertTrue(txt.contains(time_Stamp), "The added content does not exist");
										WebElement CloseBtn = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]"));
										clickelement(CloseBtn);
										a = true;
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * to delete response > quick response
	 * 
	 * @param Group
	 */
	public void DeleteQuickResponse(String Group) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ReviewContent = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = ReviewContent.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							scrollByElement(ele);
							Review = ele.getText();
							System.out.println("The Review selected is : " + Review);
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										WebElement ManageLink = driver.findElement(By.xpath(
												"(//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//a[contains(text(),'Manage')]"));
										action.moveToElement(ManageLink).click().build().perform();
										// clickelement(ManageLink);
										JSWaiter.waitJQueryAngular();
										clickelement(GroupSelect);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ Group + "')]"))
												.click();
										Thread.sleep(3000);
										WebElement quickresponses = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]/../..//div[@class='precanned-count-info']"));
										int sizebeforedelete = Integer
												.parseInt(quickresponses.getAttribute("data-count"));
										System.out.println("The size of the quick responses before deleting is : "
												+ sizebeforedelete);
										WebElement DeleteBtn = driver.findElement(By.xpath(
												"(//span[@class='radioResponseContent']//a[@title = 'Delete'])[1]"));
										clickelement(DeleteBtn);
										WebElement Deleteconfirm = driver
												.findElement(By.xpath("//button[contains(text(), 'Yes')]"));
										clickelement(Deleteconfirm);
										JSWaiter.waitJQueryAngular();
										Thread.sleep(5000);
										clickelement(GroupSelect);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ Group + "')]"))
												.click();
										Thread.sleep(3000);
										int sizeafterdeleting = Integer
												.parseInt(quickresponses.getAttribute("data-count"));
										System.out.println("The size of the quick responses after deleting is : "
												+ sizeafterdeleting);
										BaseClass.addEvidence(driver, "Test to delete quick response", "yes");
										soft.assertEquals(sizeafterdeleting, sizebeforedelete - 1);
										WebElement CloseBtn = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]"));
										clickelement(CloseBtn);
										a = true;
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * to copy quick responses to and from group
	 * 
	 * @param GrouptobeCopied
	 * @param GrouptobeCopiedfrom
	 */
	public void CopyQuickResponses(String GrouptobeCopied, String GrouptobeCopiedfrom) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> AddLinkBtn = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = AddLinkBtn.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							scrollByElement(ele);
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]"
											+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										WebElement ManageLink = driver
												.findElement(By.xpath("(//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//a[@id='use-saved-response-btn']"));
										action.moveToElement(ManageLink).click().build().perform();
										// clickelement(ManageLink);
										JSWaiter.waitJQueryAngular();
										clickelement(GroupSelect);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ GrouptobeCopied + "')]"))
												.click();
										WebElement quickresponses = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]/../..//div[@class='precanned-count-info']"));
										sizeofresponsestobecopiedGroup = Integer
												.parseInt(quickresponses.getAttribute("data-count"));
										System.out.println(
												"The size before copying is : " + sizeofresponsestobecopiedGroup);
										BaseClass.addEvidence(driver, "Test to copy response", "yes");
										clickelement(GroupSelect);
										Thread.sleep(3000);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ GrouptobeCopiedfrom + "')]"))
												.click();
										sizeofresponsestobecopiedfromGroup = Integer
												.parseInt(quickresponses.getAttribute("data-count"));
										System.out.println("The size of group response from group is : "
												+ sizeofresponsestobecopiedfromGroup);
										WebElement CopyResponseBtn = driver
												.findElement(By.xpath("(//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[@id='copyQuickResponses']"));
										clickelement(CopyResponseBtn);
										WebElement FromGroup = driver
												.findElement(By.xpath("//span[@id='select2-fromGroup-container']"));
										clickelement(FromGroup);
										WebElement FromSearchbox = driver
												.findElement(By.xpath("//input[@class='select2-search__field']"));
										clickelement(FromSearchbox);
										driver.findElement(
												By.xpath("//ul[@id='select2-fromGroup-results']//li[contains(text(),'"
														+ GrouptobeCopiedfrom + "')]"))
												.click();
										// clickelement(from);
										// FromSearchbox.sendKeys(GrouptobeCopiedfrom);
										System.out.println(
												"Entered group name to be copied from : " + GrouptobeCopiedfrom);
										Thread.sleep(5000);
										WebElement ToGroup = driver
												.findElement(By.xpath("(//span[@id='select2-toGroup-container'])"));
										clickelement(ToGroup);
										WebElement ToSearchbox = driver
												.findElement(By.xpath("//input[@class='select2-search__field']"));
										clickelement(ToSearchbox);
										driver.findElement(
												By.xpath("//ul[@id = 'select2-toGroup-results']//li[contains(text(),'"
														+ GrouptobeCopied + "')]"))
												.click();
										// clickelement(To);
										// ToSearchbox.sendKeys(GrouptobeCopied);
										System.out.println("Entered group name to be copied : " + GrouptobeCopied);
										WebElement CopyBtn = driver.findElement(By
												.xpath("(//input[@id='copy-responses-radio-item-copy'])[" + row + "]"));
										clickelement(CopyBtn);
										WebElement SaveBtn = driver.findElement(
												By.xpath("(//button[@id='copyortransferResponsesBtn'])[" + row + "]"));
										clickelement(SaveBtn);
										JSWaiter.waitJQueryAngular();
										WebElement CloseBtn = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[@id='copyQuickResponses']/..//button[contains(text(),'Close')])"));
										clickelement(CloseBtn);
										driver.navigate().refresh();
										a = true;
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * to transfer quick responses to and from quick responses
	 * 
	 * @param GrouptobeCopied
	 * @param GrouptobeCopiedfrom
	 */
	public void TransferQuickResponses(String GrouptobeCopied, String GrouptobeCopiedfrom) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> AddLinkBtn = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = AddLinkBtn.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							scrollByElement(ele);
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]"
											+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										WebElement ManageLink = driver
												.findElement(By.xpath("(//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//a[@id='use-saved-response-btn']"));
										action.moveToElement(ManageLink).click().build().perform();
										// clickelement(ManageLink);
										JSWaiter.waitJQueryAngular();
										clickelement(GroupSelect);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ GrouptobeCopied + "')]"))
												.click();
										WebElement quickresponses = driver.findElement(
												By.xpath("(//div[@class='precanned-count-info'])[" + row + "]"));

										String beforesize = quickresponses.getAttribute("data-count");
										sizeofresponsestobecopiedGroup = Integer.parseInt(beforesize);
										System.out.println(
												"The size before copying is : " + sizeofresponsestobecopiedGroup);
										BaseClass.addEvidence(driver, "Test to copy response", "yes");
										clickelement(GroupSelect);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ GrouptobeCopiedfrom + "')]"))
												.click();

										String fromgroupsize = quickresponses.getAttribute("data-count");
										sizeofresponsestobecopiedfromGroup = Integer.parseInt(fromgroupsize);
										System.out.println("The size of group response from group is : "
												+ sizeofresponsestobecopiedfromGroup);
										WebElement CopyResponseBtn = driver.findElement(
												By.xpath("(//button[@id='copyQuickResponses'])[" + row + "]"));
										clickelement(CopyResponseBtn);
										WebElement FromGroup = driver
												.findElement(By.xpath("//span[@id='select2-fromGroup-container']"));
										clickelement(FromGroup);
										WebElement FromSearchbox = driver
												.findElement(By.xpath("//input[@class='select2-search__field']"));
										clickelement(FromSearchbox);
										driver.findElement(
												By.xpath("//ul[@id='select2-fromGroup-results']//li[contains(text(),'"
														+ GrouptobeCopiedfrom + "')]"))
												.click();
										// clickelement(from);
										// FromSearchbox.sendKeys(GrouptobeCopiedfrom);
										System.out.println("Entered group name to be copied from");
										Thread.sleep(5000);
										WebElement ToGroup = driver
												.findElement(By.xpath("(//span[@id='select2-toGroup-container'])"));
										clickelement(ToGroup);
										WebElement ToSearchbox = driver
												.findElement(By.xpath("//input[@class='select2-search__field']"));
										clickelement(ToSearchbox);
										driver.findElement(
												By.xpath("//ul[@id = 'select2-toGroup-results']//li[contains(text(),'"
														+ GrouptobeCopied + "')]"))
												.click();
										// clickelement(To);
										// ToSearchbox.sendKeys(GrouptobeCopied);
										System.out.println("Entered group name to be copied");
										WebElement TransferBtn = driver.findElement(By
												.xpath("(//input[@id='copy-responses-radio-transfer'])[" + row + "]"));
										clickelement(TransferBtn);
										WebElement SaveBtn = driver.findElement(
												By.xpath("(//button[@id='copyortransferResponsesBtn'])[" + row + "]"));
										clickelement(SaveBtn);
										JSWaiter.waitJQueryAngular();
										WebElement CloseBtn = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[@id='copyQuickResponses']/..//button[contains(text(),'Close')])"));
										clickelement(CloseBtn);
										driver.navigate().refresh();
										a = true;
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
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
		if (a == false) {
			soft.fail("Failed due to some issues");
		}
		soft.assertAll();
	}

	/**
	 * to verify copy and transfered responses > quick response
	 * 
	 * @param GrouptobeCopied
	 * @param GrouptobeCopiedfrom
	 */
	public void verifyCopyOrTransferToGroup(String GrouptobeCopied, String GrouptobeCopiedfrom) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> AddLinkBtn = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = AddLinkBtn.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]"
											+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										WebElement ManageLink = driver
												.findElement(By.xpath("(//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//a[@id='use-saved-response-btn']"));
										action.moveToElement(ManageLink).click().build().perform();
										// clickelement(ManageLink);
										JSWaiter.waitJQueryAngular();
										clickelement(GroupSelect);
										Thread.sleep(2000);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ GrouptobeCopied + "')]"))
												.click();
										Thread.sleep(2000);
										WebElement quickresponses = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]/../..//div[@class='precanned-count-info']"));
										String sizeafter = quickresponses.getAttribute("data-count");
										sizeofresponsestobecopiedGroupaftercopying = Integer.parseInt(sizeafter);
										System.out.println("The size after copying is : "
												+ sizeofresponsestobecopiedGroupaftercopying);
										BaseClass.addEvidence(driver, "Test to copy response", "yes");
										soft.assertEquals(sizeofresponsestobecopiedGroupaftercopying,
												sizeofresponsestobecopiedGroup + sizeofresponsestobecopiedfromGroup,
												"The count is not equal");
										Thread.sleep(3000);
										WebElement CloseBtn = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[@id='copyQuickResponses']/..//button[contains(text(),'Close')])"));
										clickelement(CloseBtn);
										Thread.sleep(3000);
										driver.navigate().refresh();
										a = true;
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							System.out.println("Element is not displayed");
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * verify transfered response > quick responses
	 * 
	 * @param GrouptobeCopied
	 * @param GrouptobeCopiedfrom
	 */
	public void verifyTransferFromGroup(String GrouptobeCopied, String GrouptobeCopiedfrom) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> AddLinkBtn = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = AddLinkBtn.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]"
											+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										WebElement ManageLink = driver
												.findElement(By.xpath("(//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//a[@id='use-saved-response-btn']"));
										action.moveToElement(ManageLink).click().build().perform();
										// clickelement(ManageLink);
										JSWaiter.waitJQueryAngular();
										clickelement(GroupSelect);
										Thread.sleep(4000);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ GrouptobeCopiedfrom + "')]"))
												.click();
										WebElement quickresponses = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]/../..//div[@class='precanned-count-info']"));
										String sizeafterfromgroup = quickresponses.getAttribute("data-count");
										sizeofResponse = Integer.parseInt(sizeafterfromgroup);
										System.out.println("The size from group after transfer is : " + sizeofResponse);
										soft.assertEquals(sizeofResponse, 0, "Failed due to issue in count");
										WebElement CloseBtn = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[@id='copyQuickResponses']/..//button[contains(text(),'Close')])"));
										clickelement(CloseBtn);
										a = true;
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							System.out.println("Element is not displayed");
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * to click on viewall and select the response
	 * 
	 * @param Groupname
	 * @param QuickResponse
	 */
	public void clickonViewAllandSelectResponse(String Groupname, String QuickResponse) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> AddLinkBtn = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = AddLinkBtn.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row + "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]"
											+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										Thread.sleep(5000);
										WebElement ViewAll = driver.findElement(By.xpath(
												"((//div[@class='review-content'])[" + row + "]//following-sibling::"
														+ "div//a[contains(@class,'add-owner-response-btn')]/..//a[@id='use-saved-response-btn'])"));
										action.moveToElement(ViewAll).click().build().perform();
										clickelement(GroupSelect);
										Thread.sleep(4000);
										driver.findElement(By.xpath(
												"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
														+ Groupname + "')]"))
												.click();
										WebElement quickresponses = driver
												.findElement(By.xpath("((//div[@class='review-content'])[" + row
														+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]/../..//div[@class='precanned-count-info']"));
										String sizeofgroup = quickresponses.getAttribute("data-count");
										sizeofResponse = Integer.parseInt(sizeofgroup);
										System.out.println("The size from group after transfer is : " + sizeofResponse);
										if (!(sizeofResponse == 0)) {
											WebElement SelectResponse = driver.findElement(By.xpath(
													"//div[@class='radioSelection responseListContent']//span[@class='radioResponseContent' and contains(.,'"
															+ QuickResponse + "')]"));
											clickelement(SelectResponse);
											WebElement UseResponseBtn = driver
													.findElement(By.xpath("((//div[@class='review-content'])[" + row
															+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@id='use-precanned-response-btn'])[1]"));
											clickelement(UseResponseBtn);
											BaseClass.addEvidence(driver, "Test to add created response", "yes");
											WebElement SubmitBtn = driver
													.findElement(By.xpath("((//div[@class='review-content'])[" + row
															+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@class='btn btn-primary submit-btn'])[1]"));
											clickelement(SubmitBtn);
											clickelement(SuccessBtn);
											WebElement res = driver.findElement(
													By.xpath("(//div[@id='responseContainer'])[" + row + "]"));
											ReviewId = res.getAttribute("data-reviewid");
											System.out.println("The id is : " + ReviewId);

										} else {
											System.out.println("No Response available to select");
											soft.fail("No Response available to select");
										}
										a = true;
										break Outer;
									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * to verify status of the response added
	 * 
	 * @param ResponseAdded
	 */
	public void verifyResponseStatus(String ResponseAdded) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseLocator = driver
							.findElements(By.xpath("(//div[@class='card card-draft response-padding ']//p)"));
					int size = ResponseLocator.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(
								By.xpath("(//div[@class='card card-draft response-padding ']//p)[" + row + "]"));
						if (ele.isDisplayed()) {
							String Response = ele.getText();
							System.out.println("The Response is : " + Response);
							if (Response.equals(ResponseAdded)) {
								WebElement Status = driver.findElement(By.xpath(
										"(//div[@class='card card-draft response-padding ']//h5//span)[" + row + "]"));
								scrollByElement(Status);
								String Statustxt = Status.getText();
								System.out.println("The status is : " + Statustxt);
								BaseClass.addEvidence(driver, "Test to verify response status", "yes");
								soft.assertEquals(Statustxt, "Pending Approval");
							}
							a = true;
							break Outer;
						} else {
							System.out.println("Element is not displayed");
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * to edit responses
	 * 
	 * @param ResponseSelected
	 */
	public void EditResponse(String ResponseSelected) {
		boolean a = false;
		time_Stamp = timeStamp();
		try {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
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
							WebElement Edit = driver.findElement(By.xpath("(//a[@id='editLink'])[" + row + "]"));
							clickelement(Edit);
							waitForElement(TextArea, 5);
							clickelement(TextArea);
							TextArea.sendKeys(time_Stamp);
							BaseClass.addEvidence(driver, "To Edit Response", "yes");
							scrollByElement(SaveBtn);
							clickelement(SaveBtn);
							JSWaiter.waitJQueryAngular();
							Thread.sleep(5000);
							Response = driver
									.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"))
									.getText();
							System.out.println("The Response Text is : " + Response);
							soft.assertTrue(Response.contains(ResponseSelected + time_Stamp),
									"Text doesn't contains : " + time_Stamp);
							JSWaiter.waitJQueryAngular();
							Thread.sleep(6000);
							String Stat = driver
									.findElement(By.xpath("(//span[@class='response-status'])[" + row + "]")).getText();
							System.out.println("The status selected is for PA : " + Stat);
							soft.assertEquals(Stat, "Pending Approval");
							BaseClass.addEvidence(driver, "Test to verify status of the response", "yes");
							a = true;
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

		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * Method to select status from Dropdown
	 * 
	 * @param status
	 * @throws InterruptedException
	 */
	public void selectPendingStatus() throws InterruptedException {
		String status = "Pending Approval";
		scrollByElement(StatusDropDown);
		clickelement(StatusDropDown);
		clickelement(driver.findElement(By.xpath("//div[@id='Status']//div[contains(text(),'" + status + "')]")));
		Thread.sleep(3000);
	}

	/**
	 * to delete added response
	 * 
	 * @param Responsetxt
	 * @throws InterruptedException
	 */
	public void DeleteResponse(String Responsetxt) throws InterruptedException {
		int numberofentriesbeforeDelete = NumOfentriesinPage(Entry);
		System.out.println("The Number of entries is : " + numberofentriesbeforeDelete);
		try {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(driver.findElement(By.xpath("//*[@class='pagination']//a[contains(text(),'1')]")));
			List<WebElement> Res = driver.findElements(By.xpath("(//*[contains(@class,'response-text')])"));
			int size = Res.size();
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					for (int row = 1; row <= size; row++) {
						WebElement response = driver
								.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row + "]"));
						scrollByElement(response);
						String Restxt = response.getText();
						System.out.println("The Response txt is : " + Restxt);
						BaseClass.addEvidence(driver, "Test to get response text", "yes");
						Thread.sleep(4000);
						if (Restxt.contains(Responsetxt)) {
							WebElement DeleteBtn = driver
									.findElement(By.xpath("(//*[contains(@class,'response-text')])[" + row
											+ "]/../..//div//a[@data-target='#deleteResponse']"));
							scrollByElement(DeleteBtn);
							clickelement(DeleteBtn);
							Thread.sleep(4000);
							BaseClass.addEvidence(driver, "Test to delete response", "yes");
							WebElement DelConfirm = driver
									.findElement(By.xpath("//button[@class='btn btn-width-sm btn-primary']"));
							clickelement(DelConfirm);
							Thread.sleep(4000);
							JSWaiter.waitJQueryAngular();
							int numberofentriesafterDelete = NumOfentriesinPage(Entry);
							System.out.println("The Number of entries is : " + numberofentriesafterDelete);
							Assert.assertTrue((numberofentriesafterDelete == numberofentriesbeforeDelete - 1),
									"Count has not decreased");
							break Outer;
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
	 * to verify response deleted
	 * 
	 * @throws Exception
	 */
	public void verifyDeletedResponse() throws Exception {

		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				Outer: if (paginationNext.isDisplayed()) {
					for (int j = 1; j <= lastpage; j++) {
						int size = driver.findElements(By.xpath("(//div[@id='responseContainer'])")).size();
						System.out.println(size);
						for (int row = 1; row <= size; row++) {
							WebElement res = driver
									.findElement(By.xpath("(//div[@id='responseContainer'])[" + row + "]"));
							String id = res.getAttribute("data-reviewid");
							System.out.println("The id is : " + id);
							if (id.equals(ReviewId)) {
								WebElement AddLink = driver.findElement(By.xpath("(//div[@id='responseContainer'])["
										+ row + "]//a[contains(text(),'Add Owner Response')]"));
								Assert.assertTrue(AddLink.isDisplayed(), "Add Link is not displayed");
								BaseClass.addEvidence(driver, "Test to verify response deleted", "yes");
								break Outer;
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
	}

	public boolean DataAvailable() {
		String text = driver.findElement(By.xpath("(//div[@class='form-group'])[2]")).getText();
		System.out.println("The text is :" + text);
		if (text.equals("No data available")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * to verify response status
	 */
	@SuppressWarnings("unused")
	public void verifyResponseAddedUsingButtonStatus() {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseLocator = driver
							.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = ResponseLocator.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {

							WebElement bx = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row
									+ "]/following-sibling::div[@id='responseContainer']"));
							String RevId = bx.getAttribute("data-reviewid");
							if (RevId.equals(ReviewId)) {
								WebElement Status = driver.findElement(By.xpath("(//div[@class='review-content'])["
										+ row + "]/following-sibling::div[@id='responseContainer']//h5//span"));
								scrollByElement(Status);
								String Statustxt = Status.getText();
								System.out.println("The status is : " + Statustxt);
								BaseClass.addEvidence(driver, "Test to verify response status", "yes");
								soft.assertEquals(Statustxt, "Pending Approval");
							}

						}
						a = true;
						break Outer;
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * to click on view all and select response as Poster
	 * 
	 * @param Groupname
	 * @param QuickResponse
	 */
	public void clickonViewAllandSelectResponsePoster(String Groupname, String QuickResponse) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> AddLinkBtn = driver.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = AddLinkBtn.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						try {
							if (ele.isDisplayed()) {
								clickelement(driver.findElement((By.xpath("((//div[@class='review-content'])[" + row
										+ "]"
										+ "//following-sibling::div//a[contains(@class,'add-owner-response-btn')])"))));
								Thread.sleep(5000);
								boolean b = BadReview();
								if (b == false) {
									JavascriptExecutor js = (JavascriptExecutor) driver;
									String jsreturn = js
											.executeScript("return document.querySelector('#comment').disabled")
											.toString();
									System.out.println("The value is : " + jsreturn);
									boolean c = Boolean.valueOf(jsreturn);
									soft.assertEquals(c, true, "Textbox is enabled");
									WebElement ViewAll = driver.findElement(By
											.xpath("((//div[@class='review-content'])[" + row + "]//following-sibling::"
													+ "div//a[contains(@class,'add-owner-response-btn')]/..//a[@id='use-saved-response-btn'])"));
									action.moveToElement(ViewAll).click().build().perform();
									clickelement(GroupSelect);
									Thread.sleep(4000);
									driver.findElement(By.xpath(
											"//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"
													+ Groupname + "')]"))
											.click();
									WebElement quickresponses = driver
											.findElement(By.xpath("((//div[@class='review-content'])[" + row
													+ "]//following-sibling::div//button[contains(text(), 'Close')])[1]/../..//div[@class='precanned-count-info']"));
									String sizeofgroup = quickresponses.getAttribute("data-count");
									sizeofResponse = Integer.parseInt(sizeofgroup);
									System.out.println("The size from group after transfer is : " + sizeofResponse);
									if (!(sizeofResponse == 0)) {
										WebElement SelectResponse = driver.findElement(By.xpath(
												"//div[@class='radioSelection responseListContent']//span[@class='radioResponseContent' and contains(.,'"
														+ QuickResponse + "')]"));
										clickelement(SelectResponse);
										WebElement UseResponseBtn = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@id='use-precanned-response-btn'])[1]"));
										clickelement(UseResponseBtn);
										BaseClass.addEvidence(driver, "Test to add created response", "yes");
										WebElement SubmitBtn = driver.findElement(By.xpath(
												"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
														+ row
														+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@class='btn btn-primary submit-btn'])[1]"));
										clickelement(SubmitBtn);
										clickelement(SuccessBtn);
										WebElement res = driver
												.findElement(By.xpath("(//div[@id='responseContainer'])[" + row + "]"));
										ReviewId = res.getAttribute("data-reviewid");
										System.out.println("The id is : " + ReviewId);

									} else {
										System.out.println("No Response available to select");
										soft.fail("No Response available to select");
									}

									a = true;
									break Outer;
								} else {
									System.out.println("Bad Review!");
								}
							} else {
								System.out.println("Element is not displayed");
							}
						} catch (Exception e) {
							e.printStackTrace();
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/**
	 * To add response using existing response > Poster
	 * 
	 * @param Group
	 */
	public void AddResponseusingexistingPoster(String Group) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseContent = driver
							.findElements(By.xpath("(//div[@class='review-content'])"));
					int size = ResponseContent.size();
					System.out.println("The size of the row is  :  " + size);
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@class='review-content'])[" + row + "]"));
						if (ele.isDisplayed()) {
							scrollByElement(ele);
							Review = ele.getText();
							System.out.println("The Review selected is : " + Review);
							try {
								if ((driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
										+ "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")))
												.isDisplayed()) {
									clickelement(driver.findElement(By.xpath("((//div[@class='review-content'])[" + row
											+ "]//following-sibling::div//a[contains(@class,'add-owner-response-btn')])")));
									Thread.sleep(5000);
									boolean b = BadReview();
									if (b == false) {
										JavascriptExecutor js = (JavascriptExecutor) driver;
										String jsreturn = js
												.executeScript("return document.querySelector('#comment').disabled")
												.toString();
										System.out.println("The value is : " + jsreturn);
										boolean c = Boolean.valueOf(jsreturn);
										soft.assertEquals(c, true, "Textbox is enabled");
										WebElement dropdown = driver.findElement(
												By.xpath("(//select[@id = 'quickResponse-dropdown-location-groups'])["
														+ row + "]"));
										Select select = new Select(dropdown);
										select.selectByVisibleText(Group);
										Thread.sleep(3000);
										List<WebElement> ExistingQuickRespose = driver
												.findElements(By.xpath("((//div[@id='responseContainer'])[" + row
														+ "])//span[contains(@class,'paginationListActive')]"));
										int sizeofEle = ExistingQuickRespose.size();
										System.out.println(":The size of the element is : " + sizeofEle);
										BaseClass.addEvidence(driver, "Test to check if quick response is available",
												"yes");
										if (sizeofEle > 1) {
											WebElement ResponseTobeSelected = driver
													.findElement(By.xpath("(((//div[@id='responseContainer'])[" + row
															+ "])//span[contains(@class,'paginationListActive')])[2]"));
											scrollByElement(ResponseTobeSelected);
											clickelement(ResponseTobeSelected);
											BaseClass.addEvidence(driver, "Test to add existing response", "yes");
											WebElement SubmitBtn = driver.findElement(By.xpath(
													"((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["
															+ row
															+ "]/..//a[contains(text(),'Add Owner Response')]/..//button[@class='btn btn-primary submit-btn'])[1]"));
											clickelement(SubmitBtn);
											clickelement(SuccessBtn);
											ReviewId = driver
													.findElement(
															By.xpath("(//div[@id='responseContainer'])[" + row + "]"))
													.getAttribute("data-reviewid");
											System.out.println("The Reviews Id is : " + ReviewId);
											BaseClass.addEvidence(driver, "Test to add existing response", "yes");
											a = true;
											break Outer;
										}

									} else {
										System.out.println("Oh No! It's a bad review!");
									}
								} else {
									System.out.println("No add link button displayed");
								}
							} catch (Exception e) {
								e.printStackTrace();

							}

						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (a == false) {
			Assert.fail("No Response found");
		}
	}

	/**
	 * to verify response status as poster
	 * 
	 * @param ResponseAdded
	 */
	public void verifyResponseStatusPoster(String ResponseAdded) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseLocator = driver
							.findElements(By.xpath("(//div[@class='card card-draft response-padding ']//p)"));
					int size = ResponseLocator.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(
								By.xpath("(//div[@class='card card-draft response-padding ']//p)[" + row + "]"));
						if (ele.isDisplayed()) {
							String Response = ele.getText();
							System.out.println("The Response is : " + Response);
							if (Response.equals(ResponseAdded)) {
								WebElement Status = driver
										.findElement(By.xpath("(//span[@class='status-text completed'])[" + row + "]"));
								scrollByElement(Status);
								String Statustxt = Status.getText();
								System.out.println("The status is : " + Statustxt);
								BaseClass.addEvidence(driver, "Test to verify response status", "yes");
								soft.assertEquals(Statustxt, "Completed");
							}
							a = true;
							break Outer;
						} else {
							System.out.println("Element is not displayed");
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}

	/*
	 * public void EditResponsePoster(String ResponseAdded) {
	 * 
	 * boolean a = false; waitForElement(ReviewTable, 5);
	 * scrollByElement(ReviewTable); int numberofentries =
	 * NumOfentriesinPage(Entry); System.out.println("The number of entries :" +
	 * numberofentries); int lastpage = Integer
	 * .parseInt(driver.findElement(By.xpath(
	 * "(//*[@class='pagination']//a)[last()-1]")).getText());
	 * System.out.println("Last Page Number is :" + lastpage);
	 * waitForElement(paginationPrev, 10); clickelement(paginationPrev); Outer: try
	 * { if (paginationNext.isDisplayed()) { for (int i = 1; i <= lastpage; i++) {
	 * List<WebElement> ResponseLocator = driver .findElements(By.
	 * xpath("(//div[@class='card card-draft response-padding ']//p)")); int size =
	 * ResponseLocator.size(); for (int row = 1; row <= size; row++) { WebElement
	 * ele = driver.findElement(
	 * By.xpath("(//div[@class='card card-draft response-padding ']//p)[" + row +
	 * "]")); if (ele.isDisplayed()) { String Response = ele.getText();
	 * System.out.println("The Response is : " + Response); if
	 * (Response.equals(ResponseAdded)) { WebElement EditBtn =
	 * driver.findElement(By.xpath(
	 * "(//div[@class='delete-edit-container']//a[@data-original-title='Edit'])["+
	 * row +"]")); clickelement(EditBtn); write code to edit the response added } a
	 * = true; break Outer; } else { System.out.println("Element is not displayed");
	 * } } if (paginationNext.isEnabled()) { scrollByElement(paginationNext);
	 * paginationNext.click(); JSWaiter.waitJQueryAngular(); } } } } catch
	 * (Exception e) { e.printStackTrace(); } if (a == false) { soft.fail(); }
	 * soft.assertAll();
	 * 
	 * }
	 */

	/**
	 * to delete response as poster
	 * 
	 * @param ResponseAdded
	 */
	public void DeleteResponsePoster(String ResponseAdded) {
		boolean a = false;
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer: try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> ResponseLocator = driver
							.findElements(By.xpath("(//div[@class='card card-draft response-padding ']//p)"));
					int size = ResponseLocator.size();
					for (int row = 1; row <= size; row++) {
						WebElement ele = driver.findElement(
								By.xpath("(//div[@class='card card-draft response-padding ']//p)[" + row + "]"));
						if (ele.isDisplayed()) {
							String Response = ele.getText();
							System.out.println("The Response is : " + Response);
							if (Response.equals(ResponseAdded)) {
								WebElement DeleteBtn = driver.findElement(By.xpath(
										"(//div[@class='delete-edit-container']//a[@title='Delete'])[" + row + "]"));
								clickelement(DeleteBtn);
								WebElement ConfirnDelete = driver
										.findElement(By.xpath("//button[contains(text(),'Yes')]"));
								clickelement(ConfirnDelete);
								WebElement SubmittedForm = driver
										.findElement(By.xpath("//button[contains(text(),'Ok')]"));
								clickelement(SubmittedForm);
							}
							a = true;
							break Outer;
						} else {
							System.out.println("Element is not displayed");
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
		if (a == false) {
			soft.fail();
		}
		soft.assertAll();
	}
}
