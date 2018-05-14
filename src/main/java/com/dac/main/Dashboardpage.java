package com.dac.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboardpage {

	public Dashboardpage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//div[@class='infobox-data']/span)[1]")
	private WebElement Locations;

	@FindBy(xpath = "(//div[@class='infobox-data']/span)[2]")
	private WebElement Visibility;

	@FindBy(xpath = "(//div[@class='infobox-data']/span)[3]")
	private WebElement Accuracy;
	
	
	
	public WebElement getLocations() {
		return Locations;
	}

	public WebElement getVisibility() {
		return Visibility;
	}

	public WebElement getAccuracy() {
		return Accuracy;
	}
	
		
}