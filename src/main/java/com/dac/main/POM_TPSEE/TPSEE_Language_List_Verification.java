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

public class TPSEE_Language_List_Verification extends TPSEE_abstractMethods {
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public TPSEE_Language_List_Verification(WebDriver driver) {
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
	
	@FindBy(xpath = "//div[@id = 'lang-flag']")
	private WebElement Language;
	
	/**
	 * Test to get and verify language list
	 */
	public void VerifyLanguageList() {
		
		clickelement(Language);
		List<String> languagelist = new ArrayList<String>();
		List<WebElement> langlist = driver.findElements(By.xpath("//div[@id = 'lang-popup']//div"));
		int size = langlist.size();
		System.out.println("The size of lang list of ele is : " +size);
		for(int i = 1; i <= size; i++) {
			String Language = driver.findElement(By.xpath("(//div[@id = 'lang-popup']//i)["+ i +"]")).getAttribute("class");
			System.out.println("The language is : " +Language);
			languagelist.add(Language);
		}
		System.out.println("The language list contains : " +languagelist);
		List<String> lanlist = new ArrayList<String>();
		lanlist.add("de flag");
		lanlist.add("us flag");
		lanlist.add("es flag");
		lanlist.add("mx flag");
		lanlist.add("ca flag");
		lanlist.add("fr flag");
		lanlist.add("it flag");
		System.out.println("The lanlist should be : " +lanlist);
		if(languagelist.size() == lanlist.size()) {
			for(int j = 0; j <= lanlist.size() - 1; j++) {
				soft.assertTrue(languagelist.get(j).equals(lanlist.get(j)), "The language is not equal");
			}
		}else {
			soft.fail("The language list are not equal");
		}
		soft.assertAll();
	}

}
