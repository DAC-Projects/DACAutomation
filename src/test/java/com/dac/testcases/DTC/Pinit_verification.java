package com.dac.testcases.DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_DTC.DTC_Transmission;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Pinit_verification {
	
	public static WebDriver driver;
	 WebDriverWait wait;
	String url="https://dac-beta-ldmpinit.azurewebsites.net/";
	
	@Test	
 public void launchBrowser() {
	  
	   WebDriverManager.chromedriver().version("81.0.4044.69").setup(); 
	   driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.get("https://dac-beta-ldmpinit.azurewebsites.net/");
	 

	 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  System.out.println(driver.getTitle());
 }
	@Test( dependsOnMethods = { "launchBrowser"})
	 public void LoginDTC() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  DTC_Navigation navi=new DTC_Navigation(driver);
		  dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
		  dtcLogin.pressYesKey();
		  String pageTitle= dtcLogin.getTitle(driver);
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
