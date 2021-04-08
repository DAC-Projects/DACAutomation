package com.dac.main.POM_SC;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;
import resources.CurrentState;
import resources.JSWaiter;

public class SC_ContentManagement_Page extends BasePage{
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
//	String postmessage=SC_SocialPosts_Page.postmessage;
	String post=null;
	String exlScore[];
	String fileName;
	
	@FindBy(xpath="//*[@id='postTable']")
	private WebElement tablePosts;
	
	@FindBy(id="dateTo")
	private WebElement toDataTB;
	
	@FindBy(id="ulReportTab")
	private WebElement reviewsTab;

	@FindBy(xpath="//*[@id='termAnalysis']//span")
	private List<WebElement> keywords;
	
	@FindBy(id="btnReportExportWordCloud")
	private WebElement exportBTN;
	
	@FindBy(id="btnApply")
	private WebElement btnApply;
	
	@FindBy(xpath="//*[@id='Review']//div[@class='bold paddingleft15 font16']/span")
	private WebElement reviewCount;
	
	
	@FindBy(xpath="//div[@class='wrapper-add-comment']//button[@class='btn btn-primary btn-sm btn-submit-add-comment btn-width-xs']")
	private WebElement submitComment;
	
	@FindBy(xpath="//*[@id='tblUnpublishedItems']")
	private WebElement tableUnpublished;
	
	
	@FindBy(xpath="//*[@id='tblPublishedItems']")
	private WebElement tablePublished;
	
	@FindBy(xpath="//div/h3[.='Published Content']")
	private WebElement headingPublished;
	
	@FindBy(xpath="//div[@class='modal-footer']//button[contains(text(),'Yes')]")
	private WebElement btnYes;
	
//	@FindBy(xpath="//div[@class='modal-footer']//button[contains(text(),'Ok')]")
//	private WebElement btnOk;
	
	@FindBy(xpath="//button[text()='Ok']")
	private WebElement okButton;
	
	//-----------------------------------------------------------
	
	public SC_ContentManagement_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isDataAvailable() {
		JSWaiter.waitJQueryAngular();
		if(driver.findElement(By.xpath("//*[@id='Review']//*[@id='paginationInfo']")).isDisplayed()) {
			return true;
		}
		else if(driver.findElement(By.xpath("//*[@id='Review']//*[@id='noData']")).isDisplayed()) {
			return false;
		}
		return false;
	}
	
	//Scenario for Approver user
	public void verifyPublishedComments(String comment) throws InterruptedException {
		assertTrue(isCommentAvailablePublished(comment),"Comment added in unPublished table");
		assertFalse(isCommentAvailableUnPublished(comment),"Comment added in UnPublished table");
		scrollByElement(headingPublished);
	}

	//Scenario for Creator user
	public void verifyUnPublishedComments(String comment) throws InterruptedException {
		assertTrue(isCommentAvailableUnPublished(comment),"Comment added in Published table");
		assertFalse(isCommentAvailablePublished(comment),"Comment added in Published table");
		
	}
	private boolean isCommentAvailablePublished(String Message) throws InterruptedException {
		
		JSWaiter.waitJQueryAngular();
		scrollByElement(tablePublished);
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@id='tblPublishedItems']//span[.='"+Message+"']")).isDisplayed();
			return true;
		}catch(Exception e) {
			return false;
		}
				
	}
	
	private boolean isCommentAvailableUnPublished(String Message) throws InterruptedException {
		
		JSWaiter.waitJQueryAngular();
		scrollByElement(tableUnpublished);
		Thread.sleep(2000);
//		WebElement comment;
		
		try{
			
			driver.findElement(By.xpath("//*[@id='tblUnpublishedItems']//span[.='"+Message+"']")).isDisplayed();
			return true;
		}
		catch(Exception e){
			return false;
		}
		
				
	}
	
	private void CommentSubmit() {
		submitComment.click();
	}
	private void deleteComment(String Message) {
		WebElement btnDelete=driver.findElement(By.xpath("//*[@id='tblPublishedItems']//span[.='"+Message+"']/../../..//button"));
		clickelement(btnDelete);
		clickelement(btnYes);
		clickelement(okButton);
	}
	private boolean isCommentDeleted(String Message) {
		deleteComment(Message);
		try {
			
			if(driver.findElement(By.xpath("//*[@id='tblPublishedItems']//span[.='"+Message+"']")).isDisplayed()) {
				return false;
			}
		}
		catch(Exception e){
			
		}
		return true;
	}
	public void verifyDeleteComments(String comment) {
		assertTrue(isCommentDeleted(comment));
	}
	
	
	public void refreshBrowser() {
		driver.get(driver.getCurrentUrl());
	}
	private List<String> getReviewData() throws InterruptedException{
		List<String> reviewReportData;
		fileName = (getLastModifiedFile("./downloads")).trim();
		
		reviewReportData = readDataFromCSV("./downloads/"+fileName);
		return reviewReportData;
	}
//	public void getScoreMouseHover() throws InterruptedException {
//		String kw = null;
//		
//		List<String> data=getReviewData();
//		for(int i = 0; i<1; i++) {
//			exportedData = data.get(i+1);
//			exlScore=exportedData.split(",");
//			System.out.println("----------------------------");
//				
//			kw=exlScore[0];
//		}
//		WebElement source = driver.findElement(By.xpath("//*[@id='termAnalysis']//span[text()='"+kw+"']"));
//		action.moveToElement(source).perform();
//		Thread.sleep(5000);
//	}
	
//	public void VerifyReviewCount() throws InterruptedException {
//		String kw = null;
//		String xlCount=null;
////		wait =new WebDriverWait(driver, 50);
//		
//		List<String> data=getReviewData();
//		for(int i = 0; i<1; i++) {
//			exportedData = data.get(i+1);
//			exlScore=exportedData.split(",");
//			System.out.println("----------------------------");
//				
//			kw=exlScore[0];
//			xlCount=exlScore[1];
//		}
//		WebElement source = driver.findElement(By.xpath("//*[@id='termAnalysis']//span[text()='"+kw+"']"));
//		action.moveToElement(source).click().perform();
////		Thread.sleep(5000);
//		wait.until(ExpectedConditions.visibilityOf(reviewsPagination));
//		String reivewCount=reviewCount.getText();
//		System.out.println("Review Count: "+reivewCount);
//		Assert.assertEquals(reivewCount, xlCount,"Review Count Mismatch in Review Feed ");
//	}
	
//	public void fkScoreVerification() throws InterruptedException {
//		String kw = null;
//		String uifkScore=null;
//		List<String> data=getReviewData();
////		System.out.println(reviewReportData.size());
//		for(int i = 1; i<data.size(); i++) {
//			exportedData = data.get(i);
//			exlScore=exportedData.split(",");
//			System.out.println("----------------------------");
//				
//			kw=exlScore[0];
//			uifkScore=getFKScore(kw);
//		
//		Thread.sleep(3000);
//		String uiSc[]=uifkScore.split(":");
//		String xlScore=exlScore[2].replace("Count", "");
//		String xlCount=exlScore[1];
//		String fkScore=uiSc[1];
//		String fkCount=uiSc[2];
//		System.out.println("Score "+xlScore+" : "+fkScore);
//		System.out.println("Count "+xlCount+" : "+fkCount);
////		for (String element: uiSc) {
////            System.out.println(element);
////        }
//		
//		Assert.assertTrue(fkScore.trim().contains(xlScore.trim()), "Score Verification Failed");//assertTrue(exlScore[1].trim(), uiSc[2].trim());
//		Assert.assertTrue(fkCount.trim().contains(xlCount.trim()), "Count Verification Failed");//Assert.assertEquals(exlScore[2].trim(), uiSc[1].trim());
//		}
//	}
	private static List<String> readDataFromCSV(String fileName) { 
		List<String> fkdata = new ArrayList<>();
		Path pathToFile = Paths.get(fileName); // create an instance of BufferedReader // using try with resource, Java 7 feature to close resources 
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) { // read the first line from the text file
			String line = br.readLine().trim(); // loop until all lines are read 
			
			while (line !=null ) { // use string.split to load a string array with the values from 
				System.out.println("line : "+line);
				String strNew = line.replace("\"", "");
				String[] attributes =  strNew.split("\n");
				for(int i=0; i< attributes.length; i++) {
					fkdata.add(attributes[i]);
				}
				if((line = br.readLine()) !=null) {
					line = line.trim();
					if(line.equals("")) line = null; 
				}
			}
		} 
		catch (IOException ioe) { 
			ioe.printStackTrace(); 
		}
		return fkdata;
	}
	
}
