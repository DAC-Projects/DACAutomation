package com.dac.main.POM_TPSEE;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.Navigationpage;

import resources.CurrentState;

public class TPSEE_ESRreports_Page extends TPSEE_abstractMethods {

	public TPSEE_ESRreports_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	Navigationpage np;
	
	/* ------------------------------Locators---------------------------------------*/
	@FindBy(xpath = "//select[@name='freq-select']")
	private WebElement results;
    
	@FindBy(xpath="//*[@class='btn btn-primary btn-width-xs save-email-frequency']")
	private WebElement save;
	
	@FindBy(xpath= "//*[@id='sendReportNow']")
	private WebElement sendnow;
	
	@FindBy(xpath="//*[@class='form-control']")
	private WebElement textValue;
	
	@FindBy(xpath="//*[@class='btn btn-width-sm btn-primary']")
	private WebElement yesButton;
	
	
	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clicksendReport( String ESRFrequency, String Email) throws Exception   {
		np = new Navigationpage(CurrentState.getDriver());
		
		Select select = new Select(driver.findElement(By.xpath("//select[@name='freq-select']")));
		select.selectByValue(ESRFrequency);
		waitForElement(save, 40);
        clickelement(save);
        np.navigateToESR();
        Select select1 = new Select(driver.findElement(By.xpath("//select[@name='freq-select']")));
        WebElement option = select1.getFirstSelectedOption();
        String text=option.getAttribute("value");
        Assert.assertEquals(ESRFrequency,text);
        Thread.sleep(10000);
		waitForElement(sendnow, 40);
		clickelement(sendnow);	
		String typedText = driver.findElement(By.xpath("//*[@class='form-control']")).getAttribute("value");
		System.out.println(typedText);
		Assert.assertEquals(Email,typedText);
		waitForElement(yesButton, 40);
        clickelement(yesButton);
        
	}

}

