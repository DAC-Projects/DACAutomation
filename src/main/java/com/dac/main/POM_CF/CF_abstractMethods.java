package com.dac.main.POM_CF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

public abstract class CF_abstractMethods extends BasePage {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public CF_abstractMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
public static String time_Stamp;
	
	
	public void time_get() {
		time_Stamp=timeStamp();
		System.out.println("Getting time"+time_Stamp);
	}
	
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Timestamp is :"+formattedDate);
		return formattedDate;		
	}

	@FindBy(xpath = "//div[@class='pageHead']")
	private WebElement PageTitle;

	@FindBy(xpath = "//div[@class='sampleHead']")
	private WebElement PageTitletext;

	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;

	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;

	@FindBy(xpath = "//*[@data-handler='prev']")
	private WebElement prevMonth;

	@FindBy(xpath = "//*[@data-handler='next']")
	private WebElement nextMonth;

	public void VerifyTitleText(String Tit, String titText) {

		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		Assert.assertEquals(Tit, Title);
		Assert.assertEquals(titText, TitleText);
	}

	/**
	 * To pass date to the UI Calendar
	 * 
	 * @param calenderField
	 * @param day_d
	 * @param month_MMM
	 * @param year_YYYY
	 */
	private void selectCalender_Date(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		// clickelement(calenderField);
		driver.findElement(By.xpath(calenderField)).click();
		int diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
		if (diff != 0) {
			while (diff < 0) {
				clickelement(prevMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
			while (diff > 0) {
				clickelement(nextMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
		}
		if (diff == 0) {
			if (!(month_MMM.equals(currentMonth_DatePicker.getText()))) {
				int actualMonthCode = monthCode(currentMonth_DatePicker.getText());
				int expMonthCode = monthCode(month_MMM);
				int diffMonth = expMonthCode - actualMonthCode;
				while (diffMonth < 0) {
					clickelement(prevMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
				while (diffMonth > 0) {
					clickelement(nextMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
			}
		}
		(driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar']//td/a[text()=" + day_d + "]"))).click();
	}

	/**
	 * To get month code
	 * 
	 * @param month_MMM
	 * @return
	 */
	private int monthCode(String month_MMM) {

		int month = 0;
		Date date;
		try {
			date = new SimpleDateFormat("MMM").parse(month_MMM);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			month = cal.get(Calendar.MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return month;
	}

	/**
	 * To set from date in a calendar
	 * 
	 * @param calenderField
	 * @param day_d
	 * @param month_MMM
	 * @param year_YYYY
	 */
	public void selectCalender_FromDate(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		if (day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0) {
			selectCalender_Date(calenderField, day_d, month_MMM, year_YYYY);
		}

	}

	/**
	 * To set to date in calendar
	 * 
	 * @param calenderField
	 * @param day_d
	 * @param month_MMM
	 * @param year_YYYY
	 */
	public void selectCalender_ToDate(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		if (day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0) {
			selectCalender_Date(calenderField, day_d, month_MMM, year_YYYY);
		}
	}
}
