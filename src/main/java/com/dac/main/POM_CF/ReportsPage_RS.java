package com.dac.main.POM_CF;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportsPage_RS {

	WebDriver driver;
	Actions action;
	Select select;
	WebDriverWait wait;
	Robot robot;
	JavascriptExecutor js;
	
	public ReportsPage_RS(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ddlCampaign")
	private WebElement campNameDropDown;
	
	@FindBy(id="btnApply")
	private WebElement applyFilterBTN;
	
	@FindBy(xpath="(//*[@class='highcharts-plot-background'])[1]")
	private WebElement reviewSubmittedGraph;
	
	@FindBy(css="g.highcharts-label>text :nth-child(1)")
	private WebElement reviewSubmittedGraphTT;
	
	@FindBy(xpath="(//*[@class='widget-title'])[1]")
	private WebElement emailsentTittleText;
	
	@FindBy(xpath="(//*[@class='widget-title'])[2]")
	private WebElement reviewOpenedTittleText;
	
	@FindBy(xpath="(//*[@class='widget-title'])[3]")
	private WebElement reviewSubmittedTittleText;
	
	@FindBy(id="divEmailsSent")
	private WebElement emailSentCount;
	
	@FindBy(id="divEmailsOpen")
	private WebElement reviewsOpenedCount;
	
	@FindBy(id="divReviewsSubmitted")
	private WebElement reviewSubmittedCount;
	
	@FindBy(id="dateFrom")
	private WebElement dateFrom;
	
	public void getClipboardContents() throws UnsupportedFlavorException, IOException {

			String copy = Keys.chord(Keys.CONTROL,Keys.chord("c"));

			dateFrom.sendKeys(Keys.CONTROL+"a");
			dateFrom.sendKeys(copy);

			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable contents = clipboard.getContents(null);
			String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
			System.out.println(x);
			//int a= result.length();
			int b = x.length();
			//System.out.println(a);
			System.out.println(b);
			/*if(a<=b) 
			{
			System.out.println("Matched Character length")
			}else 
			{
			 System.out.println("Issue In Character length");
			}
			 }*/

	}
	
	public void clickApplyFilterBTN() {
		applyFilterBTN.click();
	}
	
	public String overviewSectionCountData() {
		
		String emailCount=emailsentTittleText.getText()+" : "+emailSentCount.getText();
		String reviewsentCount=reviewOpenedTittleText.getText()+" : "+reviewsOpenedCount.getText();
		String reviewSubCount=reviewSubmittedTittleText.getText()+" : "+reviewSubmittedCount.getText();
		
		return emailCount+"\n"+reviewsentCount+"\n"+reviewSubCount;
	}
	
	public void selectCampaignName(String campName) {
		select = new Select(campNameDropDown);
		select.selectByVisibleText(campName);
	}
	
	public void fromDateCampaign() {
		
		System.out.println("From Date : "+dateFrom.getText());
	}
	
	public void getReviewSubmittedGraphText() {
		action.moveToElement(reviewSubmittedGraph).moveByOffset((reviewSubmittedGraph.getSize().getWidth()/2)-2, 0).perform();
		System.out.println(reviewSubmittedGraphTT.getText());
	}
}
