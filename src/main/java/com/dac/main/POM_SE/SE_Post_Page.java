package com.dac.main.POM_SE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.JSWaiter;

public class SE_Post_Page extends BasePage {

	
	public static ArrayList<String> tableCellValues = new ArrayList<String>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public SE_Post_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "dateFrom")
	private WebElement fromDate;
	
	@FindBy(id = "dateTo")
	private WebElement toDate;
	
	@FindBy(xpath = "//*[@data-handler='prev']")
	private WebElement prevMonth;
	
	@FindBy(xpath = "//*[@data-handler='next']")
	private WebElement nextMonth;
	
	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;
	
	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;
	
	@FindBy(id = "apply_filter")
	private WebElement applyFilter;
	
	@FindBy(id = "socialMediaType")
	private WebElement socialMediaType;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'all')]")
	private WebElement allTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'facebook')]")
	private WebElement facebookTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'twitter')]")
	private WebElement twitterTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'instagram')]")
	private WebElement instagramTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'youtube')]")
	private WebElement youtubeTab;
	
	
	
	/**
	 *This method is used to check whether data is there in table or not based on the applied criteria
	 * @return true : if data is there otherwise return false			*/
	public boolean isDataAvailable() {
		JSWaiter.waitJQueryAngular();
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
			return true;
		}else if(driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}
	
	private void selectCalender_Date(WebElement calenderField, int day_d, String month_MMM, int year_YYYY) {
		clickelement(calenderField);
		int diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
		if(diff != 0) {
			while(diff < 0) {
				clickelement(prevMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
			while(diff > 0) {
				clickelement(nextMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
		}
		if(diff == 0) {
			if(!(month_MMM.equals(currentMonth_DatePicker.getText()))) {
				int actualMonthCode = monthCode(currentMonth_DatePicker.getText());
				int expMonthCode = monthCode(month_MMM);
				int diffMonth = expMonthCode - actualMonthCode;
				while(diffMonth < 0) {
					clickelement(prevMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
				while(diffMonth > 0) {
					clickelement(nextMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
			}
		}
		clickelement(driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar']//td/a[text()="+day_d+"]")));
	}
	
	
	private int monthCode(String month_MMM) {
		int month = 0;
		Date date;
		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse("February");
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(date);
			 month = cal.get(Calendar.MONTH);
			 //System.out.println(month);
			// System.out.println(month == Calendar.FEBRUARY);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return month;
	}

	public void enterFromDate(String date) {
		JSWaiter.waitJQueryAngular();
		String[] dateSplit = date.split("/");
		String Lchars = Month.of(Integer.parseInt(dateSplit[0])).name().toLowerCase();
		String month =Month.of(Integer.parseInt(dateSplit[0])).name().charAt(0)+Lchars.substring(1, Lchars.length());
		selectCalender_FromDate(Integer.parseInt(dateSplit[0]), month, Integer.parseInt(dateSplit[2]));
	}
		
	public void enterToDate(String date) {
		JSWaiter.waitJQueryAngular();
		String[] dateSplit = date.split("/");
		String Lchars = Month.of(Integer.parseInt(dateSplit[0])).name().toLowerCase();
		String month =Month.of(Integer.parseInt(dateSplit[0])).name().charAt(0)+Lchars.substring(1, Lchars.length());
		selectCalender_ToDate(Integer.parseInt(dateSplit[0]), month, Integer.parseInt(dateSplit[2]));
	}
	
	public void selectCalender_FromDate(int day_d, String month_MMM, int year_YYYY) {
		selectCalender_Date(fromDate, day_d, month_MMM, year_YYYY);
	}
	
	public void selectCalender_ToDate(int day_d, String month_MMM, int year_YYYY) {
		selectCalender_Date(toDate, day_d, month_MMM, year_YYYY);
	}	
	
	public SE_Post_Page forVendor(String vendorName) {
		vendorName = vendorName.toLowerCase();
		scrollByElement(socialMediaType);
		
		switch(vendorName) {
		
		case "facebook"  : clickelement(facebookTab);
				           break;
		case "twitter"   : clickelement(twitterTab);
		                   break;
		case "instagram" : clickelement(instagramTab);
		                   break;
		case "youtube"   : clickelement(youtubeTab);
		                   break;
		case "all"       :
		default          : clickelement(allTab);
		
		}
		return this;
	}
	
	public int getTotalTableDataCount() {
		int dataCount = 0;
		//clickelement(facebookTab);
		if(isDataAvailable()) {
			String tableDataCount = driver.findElement(By.className("dataTables_info")).getText();
			dataCount = Integer.parseInt((tableDataCount.substring(tableDataCount.indexOf("("), tableDataCount.length()).split(" "))[1]);
		}
		return dataCount;
	}
	
	public void clickApplyFilter() {
		JSWaiter.waitJQueryAngular();
		try {
			Thread.sleep(2000);
			clickelement(applyFilter);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
