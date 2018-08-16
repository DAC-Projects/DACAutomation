package com.dac.main.POM_CF;

import java.awt.Robot;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

public class ReportsPage_RS extends BasePage{

	WebDriver driver;
	Actions action;
	Select select;
	WebDriverWait wait;
	Robot robot;
	JavascriptExecutor js;
	
	public ReportsPage_RS(WebDriver driver) {
		super(driver);
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
	private WebElement fromDate;
	
	public void getFromDate() throws UnsupportedFlavorException, IOException  {
		getClipboardContents(fromDate);
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
	
/*	public void fromDateCampaign() {
		
		System.out.println("From Date : "+fromDate.getText());
	}*/
	
	public void getReviewSubmittedGraphText() {
		action.moveToElement(reviewSubmittedGraph).moveByOffset((reviewSubmittedGraph.getSize().getWidth()/2)-2, 0).perform();
		System.out.println(reviewSubmittedGraphTT.getText());
	}
}
