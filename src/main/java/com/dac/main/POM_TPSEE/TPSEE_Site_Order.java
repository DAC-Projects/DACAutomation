package com.dac.main.POM_TPSEE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TPSEE_Site_Order extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	List<String> SiteList = new ArrayList<String>();
	List<String> VendorList = new ArrayList<String>();
	
	@FindBy(xpath = "(//div[@class='col-lg-2 bar-chart-column'])")
	private WebElement site;
	
	
	
	public TPSEE_Site_Order(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getSiteList(){
	
		String site;
		List<WebElement> Sitelist = driver.findElements(By.xpath("(//div[@class='ui-sortable-handle']//img)"));
		int size = Sitelist.size();
		System.out.println("The size of element list : " +size);
		for(int i = 1; i <= size; i++ ) {
			site = driver.findElement(By.xpath("(//div[@class='ui-sortable-handle']//img)["+ i +"]")).getAttribute("id");
			System.out.println("The site is : " +site);
			SiteList.add(site);
		}
		System.out.println("The site list is : " +SiteList);
		return SiteList;
	}
	
	public List<String> getVisibilitySite(){
		
		String vendor;
		List<WebElement> Vendors = driver.findElements(By.xpath("(//div[@class='col-lg-2 bar-chart-column'])"));
		scrollByElement(driver.findElement(By.xpath("(//div[@class='col-lg-2 bar-chart-column'])")));
		int size = Vendors.size();
		System.out.println("The size of web element is : " +size);
		for(int i = 1; i <= size; i++) {
			vendor = driver.findElement(By.xpath("(//div[@class='col-lg-2 bar-chart-column'])["+ i +"]")).getText();
			System.out.println("The Vendor is : " +vendor);
			if(vendor.equals("YP.com")) {
				vendor = "YellowpagesCom";
			}else if(vendor.equals("YP.ca")) {
				vendor = "Yellowpages";
			}
			VendorList.add(vendor);
		}
		System.out.println("Vendor List contains : " +VendorList);
		return VendorList;
		
	}
	
}
