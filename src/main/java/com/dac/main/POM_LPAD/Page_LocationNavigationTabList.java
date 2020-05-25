package com.dac.main.POM_LPAD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.testcases.LPAD.LaunchLPAD;


public class Page_LocationNavigationTabList extends LaunchLPAD {
	
	WebDriver driver;
	Actions actions;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	//-----------Navigation Tabs------------------
	@FindBy(xpath="//ul[@class='nav nav-tabs']//li//a[@id='liStep1']")
	private WebElement basicInfoTab;
	
	@FindBy(xpath="//ul[@class='nav nav-tabs']//li//a[@id='liStep2']")
	private WebElement businessInfoTab;

	@FindBy(xpath="//ul[@class='nav nav-tabs']//li//a[@id='liStep3']")
	private WebElement detailsTab;
	
	@FindBy(xpath="//ul[@class='nav nav-tabs']//li//a[@id='liStep4']")
	private WebElement customInfoTab;
	
	@FindBy(xpath="//ul[@class='nav nav-tabs']//li//a[@id='liStep5']")
	private WebElement productsTab;
	
	@FindBy(xpath="//ul[@class='nav nav-tabs']//li//a[@id='liStep6']")
	private WebElement summaryTab;
	
		
	//-----Buttons-----------
	
	@FindBy(xpath="//form[@class='form-horizontal']//input[@id='btn_save']")
	private WebElement btnSubmit;
	
	@FindBy(xpath="//div[@class='bootbox modal fade saveProNew in']//button[@data-bb-handler='Ok']")
	private WebElement btnIncompleteWarningOK;
	
	//---------Messge Box------------//
	@FindBy(xpath="//div[@class='bootbox modal fade in']/div/div/div[@class='modal-header']")
	private WebElement LocationSuccessMessageTitle;
	@FindBy(xpath="//div[@class='bootbox modal fade in']/div/div/div[@class='modal-body']/div")
	private WebElement LocationSuccessMessage;
	
	@FindBy(xpath="//div[@class='bootbox modal fade in']/div/div/div[@class='modal-footer']/button")
	private WebElement BtnLocationSuccessMessage;
	
	public Page_LocationNavigationTabList(WebDriver driver) {
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
		
	}
	
	public void navigateBasicInfoTab() throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		wait.until(ExpectedConditions.elementToBeClickable(basicInfoTab));
		System.out.println("-------------clicking basicInfoTab");
		actions.moveToElement(basicInfoTab).perform();
		actions.moveToElement(basicInfoTab).doubleClick(basicInfoTab).build().perform();
	}
	
	public void navigateBusinessInfoTab() throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		wait.until(ExpectedConditions.elementToBeClickable(businessInfoTab));
		System.out.println("-------------clicking businessInfoTab");
		actions.moveToElement(businessInfoTab).perform();
		actions.moveToElement(businessInfoTab).doubleClick(businessInfoTab).build().perform();
	}
	
	public void navigateProductsTab() throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		wait.until(ExpectedConditions.elementToBeClickable(productsTab));
		System.out.println("-------------clicking productsTab");
//		productsTab.click();
		actions.moveToElement(productsTab).perform();
		actions.moveToElement(productsTab).doubleClick(productsTab).build().perform();
	}
	public void submitLocation() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(3000);
		if (btnSubmit.isEnabled()) {
			System.out.println("Submit Button is Enabled");
			actions.moveToElement(btnSubmit).click().build().perform();
			wait.until(ExpectedConditions.visibilityOf(BtnLocationSuccessMessage));//for warning message popup
			BtnLocationSuccessMessage.click();//for warning message popup
			Thread.sleep(2000);
			if(btnIncompleteWarningOK.isDisplayed()) {
				Thread.sleep(2000);
				btnIncompleteWarningOK.click();
			}
			wait.until(ExpectedConditions.visibilityOf(BtnLocationSuccessMessage));//for success message popup
			BtnLocationSuccessMessage.click();//for success message popup
//			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		}else {
			System.out.println("Submit Button Not enabled");
		}
	}
	
	
	
}
