package com.dac.main.POM_LPAD;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page_LPADHome {

	WebDriver driver;
	Actions action; 
	WebDriverWait wait;
	
	/*----------------Locators Start------------*/
	
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
	
	public void NavigateToLocations() {
		action = new Actions(driver);
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

}
