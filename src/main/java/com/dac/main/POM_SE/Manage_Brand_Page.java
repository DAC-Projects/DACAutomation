package com.dac.main.POM_SE;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.BaseClass;

public class Manage_Brand_Page extends BasePage{
	
	public Manage_Brand_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}



	@FindBy(id = "add-brand-button")
	private WebElement addBrandBtn;
	
	@FindBy(id = "brandName")
	private WebElement brandName;
	
	@FindBy(id = "brandDescription")
	private WebElement brandDescription;
	
	@FindBy(id = "brand-add-save-button-label")
	private WebElement saveBtn;
	
	
	@FindBy(id = "add-submit-button")
	private WebElement editSaveBtn;
	
	@FindBy(id = "delete-submit-button")
	private WebElement deleteConfirm;
	
	@FindBy(id = "brandDescription")
	private WebElement brandD2escription;
	
	private By deletebtn(String name) {
		By deteleBtn = By.xpath("//div[text()='"+name+"']/../../td[3]/div[3]/input");
		return deteleBtn;
	}
	
	private By editbtn(String name) {
		By editBtn = By.xpath("//div[text()='"+name+"']/../../td[3]/div[1]/input");
		return editBtn;
	}
	public void addbrand(String name, String description) throws InterruptedException {
		addBrandBtn.click();
	//	clickelement(addBrandBtn);
		brandName.sendKeys(name);
		brandDescription.sendKeys(description);
		saveBtn.click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("ERROR: (ALERT BOX DETECTED) - ALERT MSG : " + alertText);
		alert.accept();
		assertEquals(alertText, "Changes successfully saved!");
		
	}
	
	
	public void deleteBrand(String name, String description) throws InterruptedException {
		driver.findElement(deletebtn(name)).click();
		deleteConfirm.click();
	//	waitUntilLoad(driver);
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("ERROR: (ALERT BOX DETECTED) - ALERT MSG : " + alertText);
		alert.accept();
		assertEquals(alertText, "Brand / Other deleted!");
	
	}
	
	public void editBrand(String name, String description) throws InterruptedException {
		driver.findElement(editbtn(name)).click();
		brandName.sendKeys(name);
		brandDescription.sendKeys(description);
		editSaveBtn.click();
		Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("ERROR: (ALERT BOX DETECTED) - ALERT MSG : " + alertText);
		alert.accept();
	//	assertEquals(alertText, "Brand / Other deleted!");
	
	}
	
}
