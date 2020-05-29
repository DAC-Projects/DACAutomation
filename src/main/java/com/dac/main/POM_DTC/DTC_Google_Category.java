package com.dac.main.POM_DTC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.ExcelHandler;

public class DTC_Google_Category {
	
	WebDriver driver;
	ExcelHandler wb1;
	
public DTC_Google_Category (WebDriver driver) {
	this.driver =  driver;
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[5]")
	private WebElement mnuDACCategory;
	
	@FindBy(xpath="//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[1]")
	private WebElement category_mapping;
	
	@FindBy(xpath="//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/button")
	private WebElement dropdown_click;
	
	@FindBy(xpath="//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/div/input")
	private WebElement search;
	
	@FindBy(xpath="//*[@id=\"divselcountry\"]/div/button")
	private WebElement country;
	
	@FindBy(xpath="//*[@id=\"divselcountry\"]/div/div/div/input")
	private WebElement country_send;
	
	public void navigateDACCategory() throws InterruptedException {
	mnuDACCategory.click();
	Thread.sleep(1000);
	category_mapping.click();
	Thread.sleep(1000);
	dropdown_click.click();
	Thread.sleep(1000);
	search.sendKeys("Google");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),'Google')]")).click();
	Thread.sleep(1000);
	country.click();
	  ArrayList<String> list=new ArrayList<String>();//Creating arraylist    
	  int i;
	  String a=null;
	List<WebElement> actmenu = driver.findElements(By.xpath("//*[@id=\"divselcountry\"]/div/div/ul")); 
	System.out.println(actmenu.size());
    for (i = 0; i < actmenu.size(); i++) {
    	System.out.println(actmenu.get(i).getText());    
    	 a= actmenu.get(i).getText();
    	 System.out.println("Abi"+a);
    }
    list.add(a);
    System.out.println(list.size());

    country_send.sendKeys("Aruba");
    Thread.sleep(1000);
    driver.findElement(By.xpath("  //*[@id=\"divselcountry\"]/div/div/ul/li/a/span[contains(text(),'Aruba')]")).click();  
    Thread.sleep(1000);
    
}
}
