package com.dac.main.POM_TPSEE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;
import com.dac.main.Navigationpage;
import com.dac.testcases.LPAD.LaunchLPAD;

import resources.BaseClass;
import resources.CurrentState;
import resources.DateFormats;
import resources.ExcelHandler;
import resources.IAutoconst;
import resources.JSWaiter;

public class TPSEE_Login_Page  {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	
	

	public TPSEE_Login_Page(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/* ------------------------------Locators---------------------------------------*/
	@FindBy(xpath="//*[@id='txtUserName']")
	private WebElement txtUserName;
	
	@FindBy(xpath="//*[@id='txtPassword']")
	private WebElement txtPassword;
	@FindBy(xpath = "//span[contains(text(),'Sign in')]")
	private WebElement btnSubmit;
	
	/*Walkme Snippet-----------------------------------*/
    @FindBy(xpath = "//button//span[@class='walkme-custom-balloon-button-text' and contains(text(),'Cancel')]")
    private WebElement WalkMeCancel;

    @FindBy(xpath = "//span[@class= 'walkme-action-destroy-0 wm-close-link' and contains(text(),'Okay')]")
    private WebElement NotificationPopUp;
    /*--------------------------Walkme Snippet-----------------------------------*/
	private void setUsername(String name) {
		txtUserName.sendKeys(name);
	}
	
	private void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void TSEE_Login(String UserName, String Password) throws InterruptedException {
		setUsername(UserName);
		setPassword(Password);
		btnSubmit.click();
	
//		nav.waitUntilLoad(driver);
		Thread.sleep(8000);
		try {
            clickwalkme();
            System.out.println("Walkme is Displayed");
        } catch (Exception e) {
        	System.out.println(e);
            System.out.println("No Walkme Displayed");
        }
        try {
            clickNotificationPopUp();
            System.out.println("Notification PopUp is displayed");
        } catch (Exception e) {
        	System.out.println(e);
            System.out.println("No Notification PopUp displayed");
        }
	}

	public void clickwalkme() {
        JSWaiter.waitJQueryAngular();
        if (WalkMeCancel.isDisplayed()) {
            WalkMeCancel.click();
        } else {
        	
            System.out.println("No Walkme Displayed");
        }
    }
	  public void clickNotificationPopUp() {
	        JSWaiter.waitJQueryAngular();
	        if (NotificationPopUp.isDisplayed()) {
	            NotificationPopUp.click();
	        } else {
	            System.out.println("No Notification Displayed");
	        }
	    }
	

	
}


