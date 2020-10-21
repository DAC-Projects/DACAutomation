package com.dac.main.POM_DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class DTC_Pinit extends BasePage {
	WebDriver driver;

	public DTC_Pinit (WebDriver driver) {
		super(driver);
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		}
public void pin() {
	String url="https://dac-beta-ldmpinit.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  driver.findElement(By.xpath("//*[@id=\"searchBox\"]")).sendKeys("9000225164");
	  driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/button")).click();
	  String address=driver.findElement(By.xpath("//*[@id=\"address\"]")).getText();
	  System.out.println(address);
	  driver.findElement(By.xpath("//*[@id=\"melissaCopy\"]")).click();
	  driver.findElement(By.xpath("//*[@id=\"btnSave2\"]")).click();
	  driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/button")).click();
	  String pin=driver.findElement(By.xpath("//*[@id=\"pin\"]")).getText();
	  System.out.println(pin);
	  driver.findElement(By.xpath("//*[@id=\"btnSave2\"]")).click();
	  String a=driver.findElement(By.xpath("//*[@id=\"completed\"]")).getText();
	  System.out.println(a);

}
}
