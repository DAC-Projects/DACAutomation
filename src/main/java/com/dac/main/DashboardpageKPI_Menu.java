package com.dac.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardpageKPI_Menu {

	public DashboardpageKPI_Menu(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='location']/div[2]/span")
	private WebElement Locations;

	@FindBy(xpath = "//*[@id='visibility']/div[2]/span")
	private WebElement Visibility;

	@FindBy(xpath = "//*[@id='accuracy']/div[2]/span")
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