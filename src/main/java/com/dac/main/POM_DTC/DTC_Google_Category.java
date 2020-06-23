package com.dac.main.POM_DTC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.ExcelHandler;
import resources.JSWaiter;

public class DTC_Google_Category   {
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

public DTC_Google_Category (WebDriver driver) {
	
	this.driver = driver;
	wait = new WebDriverWait(driver, 30);
	action = new Actions(driver);
	PageFactory.initElements(driver, this);
	}

	int count=0;
	@FindBy(xpath="//*[@id=\"container\"]/header")
	private WebElement header;
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[5]")
	private WebElement mnuDACCategory;
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[1]")
	private WebElement mnuVendors;
	
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
	
	@FindBy(xpath="//*[@id=\"btnnewcategory\"]")
	private WebElement newcategory;
	
	@FindBy(xpath="//*[@id=\"newcategoryname\"]")
	private WebElement Vcategoryname;
	
	@FindBy(xpath="//*[@id=\"selcoutrydiv\"]/div/button")
	private WebElement country_pop;
	
	@FindBy(xpath="//*[@id=\"selcoutrydiv\"]/div/div/div/input")
	private WebElement country_send_pop;
	
	@FindBy(xpath="//*[@id=\"seldacdiv\"]/div/button")
	private WebElement DAC_pop;
	
	@FindBy(xpath="//*[@id=\"seldacdiv\"]/div/div/div/input")
	private WebElement DAC_name;
	
	@FindBy(xpath="//*[@id=\"btnsavenewcategory\"]")
	private WebElement save;
	
	public void AddGooglecategory(String goocategory, String selectcatgoogle, String selectcountry) throws InterruptedException {
	mnuDACCategory.click();
	Thread.sleep(1000);
	category_mapping.click();
	Thread.sleep(1000);
	dropdown_click.click();
	Thread.sleep(1000);
	search.sendKeys(selectcatgoogle);
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),'"+goocategory+"')]")).click();
	Thread.sleep(1000);
	country.click();
	  ArrayList<String> list=new ArrayList<String>();//Creating arraylist    
	  int i;
	  String a=null;
	List<WebElement> actmenu = driver.findElements(By.xpath("//*[@id=\"divselcountry\"]/div/div/ul")); 
	System.out.println(actmenu.size());
    for (i = 0; i < actmenu.size(); i++) {
    	//System.out.println(actmenu.get(i).getText());    
    	 a= actmenu.get(i).getText();
    	 if(a.contains("Guyana"))  {
	System.out.println("Conuntry is displayed.");
	}}
  
    country_send.sendKeys(selectcountry);
    Thread.sleep(10000);
    driver.findElement(By.xpath("  //*[@id=\"divselcountry\"]/div/div/ul/li/a/span[contains(text(),'"+selectcountry+"')]")).click();  
    Thread.sleep(10000);
    driver.findElement(By.xpath("//*[@id=\"txtfilter\"]")).sendKeys("Acrylic store");
    Thread.sleep(10000);
    driver.findElement(By.xpath("//td[contains(text(),'Acrylic store')]/../td[3]")).click();    
   driver.findElement(By.xpath("//*[@id=\"temppopId\"]/div[1]/div/div/button/span[1]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[@id=\"temppopId\"]/div[1]/div/div/div/div/input")).sendKeys("Sports Bar");
    Thread.sleep(1000);
   driver.findElement(By.xpath("//*[@id=\"temppopId\"]/div[1]/div/div/div/ul/li/a/span[contains(text(),'Sports Bar')]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//*[@id=\"btnpopoverok\"])[2]")).click();
    Thread.sleep(2000);
}
	public void scrollByElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int yLoc = element.getLocation().getY() - 10;
		int xLoc = element.getLocation().getX();
		js.executeScript("window.scrollTo(" + xLoc + ", " + yLoc + ")");
}
	public void verifyAddeedGoogleCategory(String category,String selectdaccategory) throws Exception {
		 dropdown_click.click();
			Thread.sleep(1000);
			search.sendKeys(selectdaccategory);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),'"+category+"')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"txtfilter\"]")).sendKeys("Sports Bar");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"dacId1944\"]")).click();
			Thread.sleep(1000);
			List<WebElement> actmenu1 = driver.findElements(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr")); 
			System.out.println(actmenu1.size());
			int i1;
			String a1=null;
			int count=1;
		    for (i1 = 0; i1 < actmenu1.size(); i1++) {
		    	//System.out.println(actmenu.get(i).getText());    
		    	 a1= actmenu1.get(i1).getText();
		    	 System.out.println(a1);

		    	 //String ad="Google\nBB\nSchool Administrator";
		    	 if(a1.contains("Google")) {
		    		 System.out.println("Google is displyed");
		    	  if(a1.contains("GY")) {
		    		 System.out.println("GY is displyed");
		    	  if(a1.contains("Acrylic store"))
		    	 {
		    		 System.out.println("School Bar is displyed");
		    		 break;
		    	 }}}
		    	 else {
		    		 System.out.println("Not found");

		    	 }
		    	 count++;
		    }
		    System.out.println(count);
		    Thread.sleep(1000);
		    WebElement ad=driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr[30]"));
		    scrollByElement(ad);
		    Thread.sleep(1000);
		    String tes=driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr["+count+"]/td[2]/span")).getText();
		    Thread.sleep(1000);
		    String a="Acrylic store";
		   Assert.assertEquals(a, tes);
		    System.out.println("ABI"+tes);
	}
	
	
	
	public void deleteGoogleCategory(String goocategory,String selectcatgoogle, String selectcountry) throws Exception {
		scrollByElement(mnuVendors);
	    Thread.sleep(2000);
	    dropdown_click.click();
		Thread.sleep(1000);
		search.sendKeys(selectcatgoogle);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),'"+goocategory+"')]")).click();
		Thread.sleep(1000);
		country.click();
		  ArrayList<String> list=new ArrayList<String>();//Creating arraylist    
		  int i;
		  String a=null;
		List<WebElement> actmenu = driver.findElements(By.xpath("//*[@id=\"divselcountry\"]/div/div/ul")); 
		System.out.println(actmenu.size());
	    for (i = 0; i < actmenu.size(); i++) {
	    	//System.out.println(actmenu.get(i).getText());    
	    	 a= actmenu.get(i).getText();
	    	 if(a.contains("Austria"))  {
		System.out.println("Conuntry is displayed.");
		}}
	    list.add(a);
	    System.out.println(list.size());
	    country_send.sendKeys(selectcountry);
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("  //*[@id=\"divselcountry\"]/div/div/ul/li/a/span[contains(text(),'"+selectcountry+"')]")).click();  
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//*[@id=\"txtfilter\"]")).sendKeys("Acrylic store");
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//td[contains(text(),'Acrylic store')]/../td[4]")).click();
	    Thread.sleep(1000);
	   driver.findElement(By.xpath("//*[@id=\"btnYesConfirmYes\"]")).click();
	}

	public void verifydeletedGooglecategory(String category, String selectdaccategory) throws InterruptedException {
		 dropdown_click.click();
		 Thread.sleep(1000);
		search.sendKeys(selectdaccategory);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),'"+category+"')]")).click();
		driver.findElement(By.xpath("//*[@id=\"txtfilter\"]")).sendKeys("Sports Bar");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"dacId1944\"]")).click();
		Thread.sleep(1000);
		List<WebElement> actmenu1 = driver.findElements(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr")); 
		System.out.println(actmenu1.size());
		int i1;
		int count1=1;
		String a1=null;
	    for (i1 = 0; i1 < actmenu1.size(); i1++) {
	    	 a1= actmenu1.get(i1).getText();
	    	 System.out.println(a1);
	    	 if(a1.contains("Google")) {
	    		 System.out.println("Google is displyed");
	    	  if(a1.contains("GY")) {
	    		 System.out.println("AT is displyed");
	    	 	    		 break; }}
	    	 else {
	    		 System.out.println("Not found");
	    	 }
	    	 count1++;
	    }
	    System.out.println(count1);
	    Thread.sleep(1000);
	    WebElement ad=driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr[30]"));
	    scrollByElement(ad);
	    String tes=driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr["+count1+"]/td[2]")).getText();
	    System.out.println(tes);
	    Thread.sleep(1000);
	}
	public void AddDACcatgeory(String selectdaccategory) throws InterruptedException {
		List<WebElement> actmenu1 = driver.findElements(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr")); 
		System.out.println(actmenu1.size());
		int i1;
		int count1=1;
		String a1=null;
	    for (i1 = 0; i1 < actmenu1.size(); i1++) {
	    	//System.out.println(actmenu.get(i).getText());    
	    	 a1= actmenu1.get(i1).getText();
	    	 System.out.println(a1);
	    	 if(a1.contains(selectdaccategory)) {
	    		 System.out.println("Google is displyed");
	    	  if(a1.contains("GY")) {
	    		 System.out.println("GY is displyed");
	    	 	    		 break;  }}
	    	 else {
	    		 System.out.println("Not found");
	    	 }
	    	 count1++;
	    }
	    System.out.println(count1);
	    Thread.sleep(1000);
	    WebElement ad=driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr[30]"));
	    scrollByElement(ad);
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr["+count1+"]/td[2]")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id=\"temppopId1\"]/div[1]/div/div/button/span[1]")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//*[@id=\"temppopId1\"]/div[1]/div/div/div/div/input")).sendKeys("Acrylic store");
	   Thread.sleep(1000);
	   driver.findElement(By.xpath("//*[@id=\"temppopId1\"]/div[1]/div/div/div/ul/li/a/span[contains(text(),'Acrylic')]")).click();
	   Thread.sleep(5000);  
	    WebElement we = driver.findElement(By.xpath("(//*[@id='btnpopoverok1'])[2]"));
	    we.click();
	}
	public void verifyDACcatgeory(String goocategory, String selectdaccategory) throws InterruptedException {
		scrollByElement(mnuVendors);
	    Thread.sleep(2000);
	    dropdown_click.click();
		Thread.sleep(1000);
		search.sendKeys(selectdaccategory);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),'"+goocategory+"')]")).click();
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
	    	 if(a.contains("Austria"))  {
		System.out.println("Conuntry is displayed.");
		}}
	    list.add(a);
	    System.out.println(list.size());
	    country_send.sendKeys("Guyana");
	    Thread.sleep(10000); 
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("  //*[@id=\"divselcountry\"]/div/div/ul/li/a/span[contains(text(),'Guyana')]")).click();  
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//*[@id=\"txtfilter\"]")).sendKeys("Acrylic store");
	    Thread.sleep(10000);
	    String ad=driver.findElement(By.xpath("//td[contains(text(),'Acrylic')]/../td[3]")).getText();
	    System.out.println(ad);
	    	}
	public void DeleteDACcatgeory(String category, String selectdaccategory) throws InterruptedException {
	    dropdown_click.click();
		Thread.sleep(1000);
		search.sendKeys(selectdaccategory);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),category)]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"txtfilter\"]")).sendKeys("Sports Bar");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"dacId1944\"]")).click();
		Thread.sleep(1000);
		List<WebElement> actmenu1 = driver.findElements(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr")); 
		System.out.println(actmenu1.size());
		int i1,count=1;
		String a1=null;
	    for (i1 = 0; i1 < actmenu1.size(); i1++) {
	    	//System.out.println(actmenu.get(i).getText());    
	    	 a1= actmenu1.get(i1).getText();
	    	 System.out.println(a1);

	    	 //String ad="Google\nBB\nSchool Administrator";
	    	 if(a1.contains("Google")) {
	    		 System.out.println("Google is displyed");
	    	  if(a1.contains("GY")) {
	    		 System.out.println("AT is displyed");
	    	  if(a1.contains("Abenteuersportcenter"))
	    	 {
	    		 System.out.println("School Bar is displyed");
	    		 break;
	    	 }}}
	    	 else {
	    		 System.out.println("Not found");
	    	 }
	    	 count++;
	    }
	    System.out.println(count);
	    Thread.sleep(1000);
	    WebElement ad=driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr[30]"));
	    scrollByElement(ad);
	    Thread.sleep(1000);
	    String tes=driver.findElement(By.xpath("//*[@id=\"selectedDACTable\"]/tbody/tr["+count+"]/td[2]/span")).getText();
	    Thread.sleep(1000);
	    String a="Sports Bar";
	   // Assert.assertEquals(a, tes);
	    System.out.println("ABI"+tes);
	    Actions action = new Actions(driver);
	    WebElement we = driver.findElement(By.xpath("//*[@id='selectedDACTable']/tbody/tr["+count+"]/td[3]"));
	    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//*[@id='selectedDACTable']/tbody/tr["+count+"]/td[3]/span"))).click().build().perform();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//*[@id=\"btnOnlyCountry\"]")).click();
		}
	public void verifyDeleteDACcatgeory(String goocategory, String selectgoocategory) throws InterruptedException {
		scrollByElement(dropdown_click);
	    Thread.sleep(2000);
	    dropdown_click.click();
		Thread.sleep(1000);
		search.sendKeys(selectgoocategory);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(), goocategory)]")).click();
		Thread.sleep(1000);
		country.click();
		  ArrayList<String> list=new ArrayList<String>();//Creating arraylist    
		  int i;
		  String a=null;
		List<WebElement> actmenu = driver.findElements(By.xpath("//*[@id=\"divselcountry\"]/div/div/ul")); 
		System.out.println(actmenu.size());
	    for (i = 0; i < actmenu.size(); i++) {
	    	//System.out.println(actmenu.get(i).getText());    
	    	 a= actmenu.get(i).getText();
	    	 if(a.contains("Austria"))  {
		System.out.println("Conuntry is displayed.");
		}}
	    list.add(a);
	    System.out.println(list.size());
	    country_send.sendKeys("Gyuana");
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("  //*[@id=\"divselcountry\"]/div/div/ul/li/a/span[contains(text(),'Gyuana')]")).click();  
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//*[@id=\"txtfilter\"]")).sendKeys("Acrylic store");
	    Thread.sleep(1000);
	    String ad=null;
	    ad=driver.findElement(By.xpath("//td[contains(text(),'Acrylic')]/../td[3]")).getText();
	    System.out.println("Its ss" +ad);
	}
	
	public void newcategory(String goocategory,String selectgoocategory,  String selectcountry) throws Exception {
		mnuDACCategory.click();
		Thread.sleep(1000);
		category_mapping.click();
		Thread.sleep(1000);
		dropdown_click.click();
			Thread.sleep(1000);
			search.sendKeys(selectgoocategory);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"categorymap\"]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/ul/li/a/span[contains(text(),'"+goocategory+"')]")).click();
			Thread.sleep(1000);
			country.click();
			  ArrayList<String> list=new ArrayList<String>();//Creating arraylist    
			  int i;
			  String a=null;
			List<WebElement> actmenu = driver.findElements(By.xpath("//*[@id=\"divselcountry\"]/div/div/ul")); 
			System.out.println(actmenu.size());
		    for (i = 0; i < actmenu.size(); i++) {
		    	//System.out.println(actmenu.get(i).getText());    
		    	 a= actmenu.get(i).getText();
		    	 if(a.contains("Guyana"))  {
			System.out.println("Conuntry is displayed.");
			}}
		    list.add(a);
		    System.out.println(list.size());
		    country_send.sendKeys(selectcountry);
		    Thread.sleep(10000);
		    driver.findElement(By.xpath("  //*[@id=\"divselcountry\"]/div/div/ul/li/a/span[contains(text(),'Guyana')]")).click();  
		    Thread.sleep(10000);
		   /* newcategory.click();
			Vcategoryname.sendKeys("testing123");
			country_pop.click();
			country_send_pop.sendKeys("AT");
			driver.findElement(By.xpath("//*[@id=\"selcoutrydiv\"]/div/div/ul/li/a/span[contains(text(),'AT')]")).click();	
			DAC_pop.click();
			DAC_name.sendKeys("Accountant");	
			driver.findElement(By.xpath("//*[@id=\"seldacdiv\"]/div/div/ul/li/a/span[contains(text(),'Accountant')]")).click();
			Thread.sleep(1000);
			save.click();*/
			
		
	}
	@SuppressWarnings("unlikely-arg-type")
	public void scrollelement() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
		    long lastHeight=((Number)js.executeScript("return document.body.scrollHeight")).longValue();
		    while (true) {
		        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		        Thread.sleep(2000);

		        long newHeight = ((Number)js.executeScript("return document.body.scrollHeight")).longValue();
		        if (newHeight == lastHeight) {
		            break;
		        }
		        lastHeight = newHeight;
		    }
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		List<WebElement> actmenu = driver.findElements(By.xpath("//*[@class=\"table categoryTable categoryeditable\"]/tbody/tr/td[1]")); 
		List<WebElement> actmenu2 = driver.findElements(By.xpath("//*[@class=\"table categoryTable categoryeditable\"]/tbody/tr/td[2]")); 
		  ArrayList<String> list=new ArrayList<String>();//Creating arraylist  
		  ArrayList<String> list2=new ArrayList<String>();//Creating arraylist    
		  String a,b=null;
		  for (int i = 0; i < actmenu.size(); i++) {
		   a= actmenu.get(i).getAttribute("data-orgcontent");
		   b= actmenu2.get(i).getText();
		   String c=a+b;
		   list.add(c);	

}
   
	String fname="C:\\Users\\abinaya\\Downloads\\Category_GY-en.csv";
	String splitBy = ",";
	BufferedReader br = new BufferedReader(new FileReader(fname));
	String line = br.readLine();
	//Arrays.sort(strings, defaultComparator);
	//List<String> sortedStrings = Arrays.asList(strings);
	ArrayList<String> wordList1 = new ArrayList<String>(); 
	ArrayList<String> wordList2 = new ArrayList<String>(); 
	ArrayList<String> wordList3 = new ArrayList<String>(); 
	ArrayList<String> wordList4 = new ArrayList<String>(); 
	while ((line = br.readLine()) !=null){
    String[] bb = line.split(splitBy); 
    wordList1.add(bb[0]);
    wordList2.add(bb[1]);
	} 			
	String a1 = null;
			//Assert.assertEquals(list2, wordList3);
			System.out.println(wordList1.size());
			for (int i = 0; i < wordList1.size(); i++) {
			   String ag= wordList1.get(i);
			   ag=ag.replaceAll("^\"|\"$", "");
			   String ag1= wordList2.get(i);
			   ag1=ag1.replaceAll("^\"|\"$", "");
			   String[] number2=ag1.split(":");
			   a1=number2[1];
			   String a2=ag+a1;
			   wordList3.add(a2);
			}
			for(int i=0; i<wordList3.size();i++) {
				boolean ans = wordList3.contains(list.get(i)); 
				//System.out.println(ans);
				String s1=Boolean.toString(ans); 
				if(s1.equals("true")) {
					
				}
				else {
					System.out.println(list.get(i));
				}

				
				}
			}
			
}
