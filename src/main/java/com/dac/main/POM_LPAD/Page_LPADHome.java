package com.dac.main.POM_LPAD;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.miscellaneous.TrimFilter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import junit.framework.AssertionFailedError;

public class Page_LPADHome{

	WebDriver driver;
	Actions action; 
	WebDriverWait wait;
	JavascriptExecutor js;
	
	/*----------------Locators Start------------*/
	/*----------------Title Bar-----------------*/
	
	@FindBy(xpath="//li[@id='fontcolor']/a/span/span[2]/label")
	private WebElement profile;
	
	@FindBy(xpath="//li[@id='fontcolor']")
	private WebElement profileLink;
	
	@FindBy(xpath="//a[text()='Switch Domain']")
	private WebElement linkSwitchDomain;
	
	@FindBy(xpath="//a[text()='OK']")
	private WebElement btnOK;

	@FindBy(xpath = "//div[@id='div_Domain']/input")
	private List<WebElement> allDomains;
	
	@FindBy(xpath="//a[text()='Extract PFO Country Setup']")
	private WebElement btnExtractPFOSetup;
	
	/*----------Navigation bar Menu-------------*/
	@FindBy(xpath="//*[@href='/Homes/HomesIndex']")
	private WebElement Home;
	
	@FindBy(xpath="//*[@href='/BusinessUnit/BUIndex']")
	private WebElement BusinessUnits;

	@FindBy(xpath="//*[@href='/Accounts/AccountsIndex']")
	private WebElement Accounts;
	
	@FindBy(id="subList")
	private WebElement Users;
	
	@FindBy(xpath="//*[@href='/AdminUsers/AdminUsersIndex']")
	private WebElement AdminUsers;
	
	@FindBy(xpath="//*[@href='/Contacts/Index']")
	private WebElement Contacts;
	
	@FindBy(xpath="//*[@href='/Locations/LocationsIndex']")
	private WebElement Locations;
		
	@FindBy(xpath="//*[@href='/Products/ActiveProduct']")
	private WebElement Products;
	
	@FindBy(id="reportList")
	private WebElement Reports;
	
	/*----------------Others------------*/
	
	@FindBy(xpath="//h1")
	private WebElement pageTitle;
	
	
	/*----------------Locators End------------*/
	
	public Page_LPADHome(WebDriver driver) {
		this.driver =  driver;
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
//		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
	}
	
	public void NavigateToHome() {
		action = new Actions(driver);
		action.moveToElement(Home).click().perform();
		System.out.println("Result: Navigated to Home Page");
		}
	
	public void NavigateToBusinessUnits() {
		action = new Actions(driver);
		action.moveToElement(BusinessUnits).click().perform();
		System.out.println("Result: Navigated to BU Page");
		}
	
	public void NavigateToAccounts() {
		action = new Actions(driver);
		action.moveToElement(Accounts).click().perform();
		System.out.println("Result: Navigated to Accounts Page");
		}
	
	public void NavigateToUsers() {
		action = new Actions(driver);
		action.moveToElement(Users).click().perform();
		System.out.println("Result: Navigated to Users Page");
		}
	
	public void NavigateToAdminUsers() {
		action = new Actions(driver);
		NavigateToUsers();
		action.moveToElement(AdminUsers).click().perform();
		System.out.println("Result: Navigated to Admin Users Page");
		}
	
	public void NavigateToContacts() {
		action = new Actions(driver);
		NavigateToUsers();
		action.moveToElement(Contacts).click().perform();
		System.out.println("Result: Navigated to Contacts Page");
		}
	
	public void NavigateToLocations() throws InterruptedException {
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		Thread.sleep(2000);
		action.moveToElement(Locations).click().perform();
		System.out.println("Result: Navigated to Locations Page");
		}
	
	public void NavigateToProducts() {
		action = new Actions(driver);
		action.moveToElement(Products).click().perform();
		System.out.println("Result: Navigated to Products Page");
		}
	
	public void NavigateToReports() {
		action = new Actions(driver);
		action.moveToElement(Reports).click().perform();
		System.out.println("Result: Navigated to Reports Page");
		}
	private String getDomain() {
		String Username= profile.getText();
		String domain=Username.substring(Username.indexOf("-") + 1, Username.length());
		return domain;
	}
	public void switchToDomain(String domain) throws InterruptedException {
		String CurrentDomain=getDomain();
		CurrentDomain=CurrentDomain.trim();
		if (!domain.equalsIgnoreCase(CurrentDomain)) {
			System.out.println("Required Switch To Domain");
			profileLink.click();
			linkSwitchDomain.click();
			Thread.sleep(2000);
			boolean bind=searchDomain(domain);
			if(bind) {
				btnOK.click();
				System.out.println("Switch To Domain Completed");
			}else{
				btnOK.click();
				Assert.fail("Switch To Domain Failed: Domain Not Binded with user");
			}
			

		 }else{
			 System.out.println("In the Same Domain");
		 }
		
	}
	private boolean searchDomain(String Domain) {
		List < WebElement > domains = allDomains;
		String reseller="";boolean flag=false;
		int domainCount=domains.size();
		for(int i=0;i<domainCount;i++) {
			reseller=allDomains.get(i).getAttribute("alt");
			if (reseller.equalsIgnoreCase(Domain)) {
				allDomains.get(i).click();
				flag=true;
				break;
			}else {
				reseller=null;
				flag=false;
			}
		}
		return flag;
		
	}
	public void writeToNotepad(String inputText) throws IOException {
		FileWriter fr=new FileWriter("./data/Locations.txt");
		BufferedWriter br=new BufferedWriter(fr);
		br.newLine();
		br.append(inputText);
		br.close();
	}
}
