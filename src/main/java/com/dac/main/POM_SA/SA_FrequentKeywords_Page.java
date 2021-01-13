package com.dac.main.POM_SA;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
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

public class SA_FrequentKeywords_Page extends BasePage{
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	String exportedData=null;
	String exlScore[];
	String fileName;
	
	@FindBy(id="dateFrom")
	private WebElement fromDataTB;
	
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
	
	@FindBy(xpath="//*[@id='reviewsPagination']")
	private WebElement reviewsPagination;
	//-----------------------------------------------------------
	
	public SA_FrequentKeywords_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/** 
	 * This method is used to click on Export the excel in RRC page		*/
	public void clickExportBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOf(exportBTN));
		scrollByElement(exportBTN);
		if(exportBTN.isEnabled() & exportBTN.isDisplayed()) {
			action.moveToElement(exportBTN).click(exportBTN).perform();
			Thread.sleep(5000);
			//System.out.println("downloaded file name: "+getLastModifiedFile("./downloads"));
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
		}else {
			System.out.println("No Data Available in Review Table");
		}
	}
	
	public void getExportedData() throws InterruptedException {
		List<String> data=getReviewData();
		System.out.println("---------------------------");

		int kwCount=getkeywordlist();
		System.out.println("Keywords Count in UI>> "+kwCount);
		
		int xlCount=data.size()-1;
		System.out.println("Keywords Count in CSV>> "+xlCount);
		Assert.assertEquals(kwCount, xlCount,"Exported Count Mismatch with UI");
	

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
	
	/**
	 * @param dateInFormat : will add in the format of the dashboard language date format 
	 * 						 ex: for en_US --> MM/DD/YYYY	
	 * @throws InterruptedException */
	public void addFromDate(String dateInFormat) throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		fromDataTB.click();
		fromDataTB.clear();
	}
		
	public int getkeywordlist() {
		int count=keywords.size();
		return count;
		
	}
	private List<String> getReviewData() throws InterruptedException{
		List<String> reviewReportData;
		fileName = (getLastModifiedFile("./downloads")).trim();
		
		reviewReportData = readDataFromCSV("./downloads/"+fileName);
		return reviewReportData;
	}
	public void getScoreMouseHover() throws InterruptedException {
		String kw = null;
		
		List<String> data=getReviewData();
		for(int i = 0; i<1; i++) {
			exportedData = data.get(i+1);
			exlScore=exportedData.split(",");
			System.out.println("----------------------------");
				
			kw=exlScore[0];
		}
		WebElement source = driver.findElement(By.xpath("//*[@id='termAnalysis']//span[text()='"+kw+"']"));
		action.moveToElement(source).perform();
		Thread.sleep(5000);
	}
	
	public void VerifyReviewCount() throws InterruptedException {
		String kw = null;
		String xlCount=null;
//		wait =new WebDriverWait(driver, 50);
		
		List<String> data=getReviewData();
		for(int i = 0; i<1; i++) {
			exportedData = data.get(i+1);
			exlScore=exportedData.split(",");
			System.out.println("----------------------------");
				
			kw=exlScore[0];
			xlCount=exlScore[1];
		}
		WebElement source = driver.findElement(By.xpath("//*[@id='termAnalysis']//span[text()='"+kw+"']"));
		action.moveToElement(source).click().perform();
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(reviewsPagination));
		String reivewCount=reviewCount.getText();
		System.out.println("Review Count: "+reivewCount);
		Assert.assertEquals(reivewCount, xlCount,"Review Count Mismatch in Review Feed ");
	}
	public String getFKScore(String keyword) {
		
		WebElement source = driver.findElement(By.xpath("//*[@id='termAnalysis']//span[text()='"+keyword+"']"));
		String score=source.getAttribute("title");
//		System.out.println(score);
		return score;
	}
	public void fkScoreVerification() throws InterruptedException {
		String kw = null;
		String uifkScore=null;
		List<String> data=getReviewData();
//		System.out.println(reviewReportData.size());
		for(int i = 1; i<data.size(); i++) {
			exportedData = data.get(i);
			exlScore=exportedData.split(",");
			System.out.println("----------------------------");
				
			kw=exlScore[0];
			uifkScore=getFKScore(kw);
		
		Thread.sleep(3000);
		String uiSc[]=uifkScore.split(":");
		String xlScore=exlScore[2].replace("Count", "");
		String xlCount=exlScore[1];
		String fkScore=uiSc[1];
		String fkCount=uiSc[2];
		System.out.println("Score "+xlScore+" : "+fkScore);
		System.out.println("Count "+xlCount+" : "+fkCount);
//		for (String element: uiSc) {
//            System.out.println(element);
//        }
		
		Assert.assertTrue(fkScore.trim().contains(xlScore.trim()), "Score Verification Failed");//assertTrue(exlScore[1].trim(), uiSc[2].trim());
		Assert.assertTrue(fkCount.trim().contains(xlCount.trim()), "Count Verification Failed");//Assert.assertEquals(exlScore[2].trim(), uiSc[1].trim());
		}
	}
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
