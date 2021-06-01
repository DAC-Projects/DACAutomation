package com.dac.main.POM_SA;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import resources.BaseClass;
import resources.JSWaiter;

public class Quick_Responses extends SA_Abstarct_Methods{
	
	SoftAssert soft = new SoftAssert();

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
	
	
	
	public void AddOwnerQuickResponse(String Reviews, String Group, String QuickResponse) {
		waitForElement(ReviewTable, 5);
		scrollByElement(ReviewTable);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		Outer : 
		try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> rows_table = Table_Row;
					int rows_count = rows_table.size();
					for (int row = 1; row <= rows_count; row++) {
						/*JavascriptExecutor executor = (JavascriptExecutor)driver;
						executor.executeScript("document.body.style.zoom = '0.67'");*/
						WebElement ReviewTxt = driver.findElement(By.xpath("(//div[@class='reviewEnhancement']//div[@class='review-content'])["+row+"]"));  
						String Review = ReviewTxt.getText();
								if(Review.equals(Reviews)) {
									WebElement AddLink = driver.findElement(By.xpath("(//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]"));
									scrollByElement(AddLink);
									BaseClass.addEvidence(driver, "Test to click on AddLink", "yes");
									clickelement(AddLink);
									Thread.sleep(3000);
									WebElement ManageLink = driver.findElement(By.xpath("(//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]/..//a[contains(text(),'Manage')]"));
									action.moveToElement(ManageLink).click().build().perform();
								//	clickelement(ManageLink);
									JSWaiter.waitJQueryAngular();
									BaseClass.addEvidence(driver, "Test to add Quick Response", "yes");
									clickelement(GroupSelect);
									driver.findElement(By.xpath("//ul[@id='select2-quickResponseGroup-results']//li[contains(text(),'"+Group+"')]")).click();
									BaseClass.addEvidence(driver, "Test to select group", "yes");
									WebElement CreateResponse = driver.findElement(By.xpath("((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]/..//button[@id='create-precanned-response-btn'])[1]"));
									clickelement(CreateResponse);
									BaseClass.addEvidence(driver, "Test to click on create group response", "yes");
									Thread.sleep(2000);
									WebElement AddTitle = driver.findElement(By.xpath("((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]/..//div[@class='create-precanned-response-popup']//div[@class='responseList']//input)[1]"));
									clickelement(AddTitle);
									AddTitle.sendKeys("Automation");
									WebElement AddResponseTxt = driver.findElement(By.xpath("((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]/..//div[@class='create-precanned-response-popup']//div[@class='responseList']//textarea)[1]"));
									clickelement(AddResponseTxt);
									AddResponseTxt.sendKeys(QuickResponse);
									BaseClass.addEvidence(driver, "Test to add quick response", "yes");
									WebElement CreateResponseBtn = driver.findElement(By.xpath("((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]/..//div[@class='create-precanned-response-popup']//button[@id='create-precanned-response-submit-btn'])[1]"));
									clickelement(CreateResponseBtn);
									WebElement SelectResponse = driver.findElement(By.xpath("//div[@class='radioSelection responseListContent']//span[@class='radioResponseContent' and contains(.,'"+QuickResponse+"')]"));
									clickelement(SelectResponse);
									WebElement UseResponseBtn = driver.findElement(By.xpath("((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]/..//button[@id='use-precanned-response-btn'])[1]"));
									clickelement(UseResponseBtn);
									BaseClass.addEvidence(driver, "Test to add created response", "yes");
									WebElement SubmitBtn = driver.findElement(By.xpath("((//div[contains(@class,'reviews-middle-column')]//div[@class='review-content'])["+row+"]/..//a[contains(text(),'Add Owner Response')]/..//button[@class='btn btn-primary submit-btn'])[1]"));
									clickelement(SubmitBtn);
									clickelement(SuccessBtn);
									break Outer;
								}else {
									System.out.println("Review Not found");
								}
					}
					if (paginationNext.isEnabled()) {
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
	
}
