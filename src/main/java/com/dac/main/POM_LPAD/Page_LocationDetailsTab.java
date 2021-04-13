package com.dac.main.POM_LPAD;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.ExcelHandler;
import resources.IAutoconst;

public class Page_LocationDetailsTab extends BasePage {
	WebDriver driver;
	Actions actions;
	Select uiSelect; 
	WebDriverWait wait;
	public JavascriptExecutor js;
	
	ExcelHandler data;
	
	
	/*---------Social Info URl's---------------*/
	@FindBy(xpath="//*[@id='FacebookURL']")
	private WebElement FacebookURL;
	
	@FindBy(xpath="//*[@id='TwitterURL']")
	private WebElement TwitterURL;
	
	@FindBy(xpath="//*[@id='LinkedInURL']")
	private WebElement LinkedInURL;
	
	@FindBy(xpath="//*[@id='VideoURL']")
	private WebElement VideoURL;
	
	@FindBy(xpath="//*[@id='PinterestURL']")
	private WebElement PinterestURL;
	
	@FindBy(xpath="//*[@id='InstagramURL']")
	private WebElement InstagramURL;
	
	@FindBy(xpath="//*[@id='SocialNetworkURL']")
	private WebElement SocialNetworkURL;
	
	@FindBy(xpath="//*[@id='CouponURL']")
	private WebElement CouponURL;
	
	@FindBy(xpath="//*[@id='MenuURL']")
	private WebElement MenuURL;
	
	@FindBy(xpath="//*[@id='ReservationsURL']")
	private WebElement ReservationsURL;
	
	@FindBy(xpath="//*[@id='OrderAheadURL']")
	private WebElement OrderAheadURL;
	
	@FindBy(xpath="//*[@id='AppointmentURL']")
	private WebElement AppointmentURL;
	
	@FindBy(xpath="//*[@id='InventorySearchURL']")
	private WebElement InventorySearchURL;
	
	@FindBy(xpath="//*[@id='VirtualCareURL']")
	private WebElement VirtualCareURL;
	
	
	public Page_LocationDetailsTab(WebDriver driver) {
		super(driver);
		this.driver=driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	private void setFacebookURL(String url) {
		FacebookURL.sendKeys(Keys.BACK_SPACE);
		FacebookURL.sendKeys(url);
		
	}
	private void setTwitterURL(String url) {
		TwitterURL.sendKeys(Keys.BACK_SPACE);
		TwitterURL.sendKeys(url);
		
	}
	private void setLinkedInURL(String url) {
		LinkedInURL.sendKeys(Keys.BACK_SPACE);
		LinkedInURL.sendKeys(url);
		
	}
	private void setVideoURL(String url) {
		VideoURL.sendKeys(Keys.BACK_SPACE);
		VideoURL.sendKeys(url);
		
	}
	private void setPinterestURL(String url) {
		PinterestURL.sendKeys(Keys.BACK_SPACE);
		PinterestURL.sendKeys(url);
		
	}
	private void setInstagramURL(String url) {
		InstagramURL.sendKeys(Keys.BACK_SPACE);
		InstagramURL.sendKeys(url);
		
	}
	private void setSocialNetworkURL(String url) {
		SocialNetworkURL.sendKeys(Keys.BACK_SPACE);
		SocialNetworkURL.sendKeys(url);
		
	}
	private void setCouponURL(String url) {
		CouponURL.sendKeys(Keys.BACK_SPACE);
		CouponURL.sendKeys(url);
		
	}
	private void setMenuURL(String url) {
		MenuURL.sendKeys(Keys.BACK_SPACE);
		MenuURL.sendKeys(url);
		
	}
	private void setReservationsURL(String url) {
		ReservationsURL.sendKeys(Keys.BACK_SPACE);
		ReservationsURL.sendKeys(url);
		
	}
	private void setOrderAheadURL(String url) {
		OrderAheadURL.sendKeys(Keys.BACK_SPACE);
		OrderAheadURL.sendKeys(url);
		
	}
	private void setAppointmentURL(String url) {
		AppointmentURL.sendKeys(Keys.BACK_SPACE);
		AppointmentURL.sendKeys(url);
		
	}
	private void setInventorySearchURL(String url) {
		InventorySearchURL.sendKeys(Keys.BACK_SPACE);
		InventorySearchURL.sendKeys(url);
		
	}
	private void setVirtualCareURL(String url) {
		VirtualCareURL.sendKeys(Keys.BACK_SPACE);
		VirtualCareURL.sendKeys(url);
		
	}
	
	
	private void setSocialinfoURL(ExcelHandler data) {
		int row=1;
		String strFacebookURL  	= data.getCellValue(row, data.seacrh_pattern("Facebook_URL", 0).get(0).intValue());
		String strTwitterURL  	= data.getCellValue(row, data.seacrh_pattern("Twitter_URL", 0).get(0).intValue());
		String strLinkedInURL 	= data.getCellValue(row, data.seacrh_pattern("LinkedIn_URL", 0).get(0).intValue());
		String strVideoURL  	= data.getCellValue(row, data.seacrh_pattern("Video_URL", 0).get(0).intValue());
		String strPinterestURL  	= data.getCellValue(row, data.seacrh_pattern("Pinterest_URL", 0).get(0).intValue());
		String strInstagramURL  	= data.getCellValue(row, data.seacrh_pattern("Instagram_URL", 0).get(0).intValue());
		String strSocialNetworkURL  	= data.getCellValue(row, data.seacrh_pattern("SocialNetwork_URL", 0).get(0).intValue());
		String strCouponURL  	= data.getCellValue(row, data.seacrh_pattern("Coupon_URL", 0).get(0).intValue());
		String strMenuURL  	= data.getCellValue(row, data.seacrh_pattern("Menu_URL", 0).get(0).intValue());
		String strReservationsURL  	= data.getCellValue(row, data.seacrh_pattern("Reservations_URL ", 0).get(0).intValue());
		String strOrderAheadURL 	= data.getCellValue(row, data.seacrh_pattern("OrderAhead_URL", 0).get(0).intValue());
		String strAppointmentURL  	= data.getCellValue(row, data.seacrh_pattern("Appointment_URL", 0).get(0).intValue());
		String strInventorySearchURL  	= data.getCellValue(row, data.seacrh_pattern("InventorySearch_URL", 0).get(0).intValue());
		String strVirtualCareURL  	= data.getCellValue(row, data.seacrh_pattern("VirtualCare_URL", 0).get(0).intValue());
		
		
		
		setFacebookURL(strFacebookURL);
		setTwitterURL(strTwitterURL);
		setLinkedInURL(strLinkedInURL);
		setVideoURL(strVideoURL);
		setPinterestURL(strPinterestURL);
		setInstagramURL(strInstagramURL);
		setSocialNetworkURL(strSocialNetworkURL);
		setCouponURL(strCouponURL);
		setMenuURL(strMenuURL);
		setReservationsURL(strReservationsURL);
		setOrderAheadURL(strOrderAheadURL);
		setAppointmentURL(strAppointmentURL);
		setInventorySearchURL(strInventorySearchURL);
		setVirtualCareURL(strVirtualCareURL);
		
	}
	public void fillDetailsTabData() throws Exception {
		
		data = new ExcelHandler(IAutoconst.LocationDataExcelPath,"Details");
		wait = new WebDriverWait(driver, 30);
		
		setSocialinfoURL(data);
		
		Thread.sleep(2000);
		
		
	}
}
