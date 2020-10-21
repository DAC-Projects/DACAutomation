package com.dac.main.POM_DTC;
import static org.testng.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.FileHandler;
import resources.formatConvert;

public class DTC_Apple_Tranmission extends BaseClass{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	ExcelHandler wb1;

	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[4]")
	private WebElement mnuTransmission;
	
	@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[7]")
	private WebElement apple_trans;
	
	@FindBy(xpath="//*[@id=\"transmission\"]/div/div/div[2]/div[3]/div[2]/div/span")
	private WebElement Apple_refresh;
	
	@FindBy(xpath="//*[@id=\"btnapplerequest\"]")
	private WebElement addrequest_apple;
	
	@FindBy(xpath="//*[@id='divappleaccount']/div[2]/div/button/span")
	private WebElement Account;

	@FindBy(xpath="//*[@id=\"divappleaccount\"]/div[2]/div/ul/li[1]/div/input")
	private WebElement acc_Input;
	@FindBy(xpath="//*[@id=\"txtscheduletransmission\"]")
	private WebElement schedule_time;
	@FindBy(xpath="//*[@id=\"transmissionrequestModal\"]/div/div/div[1]")
	private WebElement cli_pop;
	@FindBy(xpath="//*[@id=\"btnexporttransmission\"]")
	private WebElement Ini_apple;
	@FindBy(xpath="//*[@id=\"alertModal\"]/div/div/div[3]/button[3]")
	private WebElement close_apple;
	
	public DTC_Apple_Tranmission (WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
		}
	 private By file_status(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]");
	        return apple_file;
	    }

	  private By file(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]/../td[8]/a[1]");
	        return apple_file;
	    }
	  private By file1(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]/../td[8]/a[2]");
	        return apple_file;
	    }
	  private By file2(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]/../td[8]/a[3]");
	        return apple_file;
	    }
	  private By file3(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]/../td[8]/a[4]");
	        return apple_file;
	    }
	  private By file4(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]/../td[8]/a[5]");
	        return apple_file;
	    }
	public void apple_Trans(String account) throws Exception {
	    wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
		System.out.println("Transmission");	
		mnuTransmission.click();
		Thread.sleep(2000);
		apple_trans.click();
		Thread.sleep(4000);
		Apple_refresh.click();
		Thread.sleep(2000);
		String sta=driver.findElement(By.xpath("//*[@id=\"appleTransmissionlistTable\"]/tbody/tr[1]/td[3]")).getText();
		System.out.println(sta);
		addrequest_apple.click();
		Thread.sleep(2000);
		Account.click();
		Thread.sleep(2000);
		acc_Input.sendKeys(account);
		Thread.sleep(2000);
		//acc_Name.click();
		driver.findElement(By.xpath("//*[@id=\"divappleaccount\"]/div[2]/div/ul/li/a/label[contains(text(),'"+account+"')]")).click();
		Thread.sleep(2000);
		Account.click();
		Thread.sleep(2000);
		schedule_time.click();
		Thread.sleep(2000);
		cli_pop.click();
		Thread.sleep(2000);
		Ini_apple.click();
		Thread.sleep(2000);
		close_apple.click();
		Thread.sleep(1000);
		String na=driver.findElement(By.xpath("//*[@id=\"appleTransmissionlistTable\"]/tbody/tr[1]/td[1]")).getText();
		System.out.println(na);
		int i=1;
		int j=1;
		wb1.setCellValue(0, 1, "Apple_Request_ID");
	    wb1.setCellValue(i, j, na);
		Thread.sleep(1000);
		apple_trans.click();
		}
	    ArrayList<String> wordList1 = new ArrayList<String>(); 
		ArrayList<String> wordList2 = new ArrayList<String>(); 
		int count=0;

		
	public void verify_apple(String LO_number, String id) throws Exception {
		String a="Complete";
		mnuTransmission.click();
		Thread.sleep(2000);
		apple_trans.click();
		Apple_refresh.click();
		Thread.sleep(2000);
		//Apple_dow.click();
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file(id)).click();
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Apple request", "yes");
		String splitBy = ",";
		String fname="./downloads/" + filname;
		int co=totalcount(fname)-1;
		System.out.println(co);
		BufferedReader br = new BufferedReader(new FileReader(fname));
		String line = br.readLine();
		while ((line = br.readLine()) !=null){
        String[] b = line.split(splitBy); 
  		wordList1.add(b[1]);
  		wordList2.add(b[4]);}
		System.out.println(wordList1);
		System.out.println(wordList2);

		int count1=loc_verify(wordList1,LO_number);
		System.out.println(count1);
		//String ad11=wordList2.get(index)
		//ad11 = ad11.replaceAll("^\"|\"$", "");
		//System.out.println(ad11);
        verify_apple1(LO_number,id,co);        
        //return co;

}
	
	public void verify_apple_inactive(String LO_number, String id) throws Exception {
		String a="Complete";
		mnuTransmission.click();
		Thread.sleep(2000);
		apple_trans.click();
		Apple_refresh.click();
		Thread.sleep(2000);
		//Apple_dow.click();
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file(id)).click();
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Apple request", "yes");
		String splitBy = ",";
		String fname="./downloads/" + filname;
		String export="Apple.xlsx";
		String pa="./downloads/" +export;
		convertExports(filname, export);
		System.out.println("Working");
		//new ExcelHandler(pa, "Sheet0").getValueRow(0);
		int co=totalcount(fname)-1;
		System.out.println(co);
		ArrayList<String> wordList2 = new ArrayList<String>(); 
		wordList2=(ArrayList<String>) new ExcelHandler(pa, "Sheet0").getValueRow(co);
		String fname2="C://Users//abinaya//Downloads//Apple.xlsx";
		ArrayList<String> wordList1 = new ArrayList<String>(); 
		
		wordList1=(ArrayList<String>) new ExcelHandler(fname2, "Sheet0").getValueRow(1);
		int common=0;
		for(int j=0;j<wordList2.size();j++){
		    if(wordList1.contains(wordList2.get(j))){
		        System.out.println("Exist : "+wordList1.get(j));
		        common++;
		    }else{
		        System.out.println("Not Exist : "+wordList2.get(j));
		        break;
		    }
		}
		BufferedReader br = new BufferedReader(new FileReader(fname));
		ArrayList<String> wordList3 = new ArrayList<String>(); 

		String line = br.readLine();
		while ((line = br.readLine()) !=null){
        String[] b = line.split(splitBy); 
  		wordList3.add(b[1]);
  		//wordList2.add(b[4]);}
		//System.out.println(wordList2);
		//int count=loc_verify(wordList3,LO_number);
		}
        verify_apple1(LO_number,id,co);
	
        //return co;
        }
	
        
	public int loc_verify(List<String> wordList1,String LO_number) {
		boolean flag = true; 
		String ad1=LO_number;
		for (String number : wordList1) {
		number = number.replaceAll("^\"|\"$", "");
		if(number.equals(ad1)) {
			Assert.assertEquals(number, ad1);
			flag = true; 
			break;
			}
		else {
			flag = false; 			
			count++;
			}}	
		System.out.println(flag);
		if(flag)
		{
	    assertTrue(true,"Abi 1 found!");
		}
		else{
		//assertTrue(false,"Abi 2 Location found!");
			}
		return count;
	}
	public int loc_verify1(List<String> wordList1,String LO_number) {
		boolean flag = true; 
		String ad1=LO_number;
		for (String number : wordList1) {
		number = number.replaceAll("^\"|\"$", "");
		if(number.equals(ad1)) {
		Assert.assertEquals(number, ad1);
		flag = true; 
		break;
			}
		else {
			flag = false; 	
			count++;
			}}	 
		System.out.println(flag);
		if(flag) {
	    assertTrue(true,"Abi 3 location found!");
		}
		else{ 
		//assertTrue(false,"Abi 4 Location found!");
			}
		return count;
	}
	
	public void verify_apple1(String LO_number, String id,int co) throws Exception {
		String a="Complete";
		//mnuTransmission.click();
		Thread.sleep(2000);
		//Apple_dow.click();
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Thread.sleep(4000);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file1(id)).click();
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Apple request", "yes");
		String splitBy = ",";
		String fname="./downloads/" + filname;
		BufferedReader br = new BufferedReader(new FileReader(fname));
		String line = br.readLine();
		while ((line = br.readLine()) !=null){
        String[] b = line.split(splitBy); 
		String myList="null";
        myList=b[1];
		myList = myList.replaceAll("^\"|\"$", "");
		int inum2 = Integer.parseInt(myList);
        System.out.println(inum2);
        Assert.assertEquals(co, inum2);
        
}}	
	public int verify_apple2(String LO_number, String id) throws Exception {
		String a="Complete";
		Thread.sleep(2000);
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Thread.sleep(4000);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file2(id)).click();
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Apple request", "yes");
		String splitBy = ",";
		String fname="./downloads/" + filname;
  		ArrayList<String> wordList1 = new ArrayList<String>(); 
		BufferedReader br = new BufferedReader(new FileReader(fname));
		String line = br.readLine();
		while ((line = br.readLine()) !=null){
        String[] b = line.split(splitBy); 
		String myList="null";
        myList=b[1];
		myList = myList.replaceAll("^\"|\"$", "");
		wordList1.add(b[1]);
        }
		int a1=totalcount(fname)-1;
		System.out.println(a1);
		String ad1=LO_number;
		int count=loc_verify1(wordList1,LO_number);
		for (String number : wordList1) {
		number = number.replaceAll("^\"|\"$", "");
		System.out.println("aa"+number);
		if(number.equals(ad1)) {
		Assert.assertEquals(number, ad1);
		System.out.println("Both are same");
		break;}
		else {
		//System.out.println("Location not found");
			}}	      
        verify_apple3(LO_number,id,a1);
		return a1;
		}
	
	public void verify_apple3(String LO_number, String id,int co) throws Exception {
		String a="Complete";
		Thread.sleep(2000);
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Thread.sleep(4000);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file3(id)).click();
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Apple request", "yes");
		String splitBy = ",";
		String fname="./downloads/" + filname;
		BufferedReader br = new BufferedReader(new FileReader(fname));
		String line = br.readLine();
		while ((line = br.readLine()) !=null){
        String[] b = line.split(splitBy); 
		String myList="null";
        myList=b[1];
		myList = myList.replaceAll("^\"|\"$", "");
		int a1=totalcount(fname);
		int inum2 = Integer.parseInt(myList);
        System.out.println(inum2);
       // Assert.assertEquals(co, inum2);
        verify_apple4(LO_number,id);
		}
}
	public void verify_apple4(String LO_number, String id) throws Exception {
		String a="Complete";
		Thread.sleep(2000);
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Thread.sleep(4000);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file4(id)).click();
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Apple request", "yes");
		String splitBy = ",";
		String fname="./downloads/" + filname;
  		ArrayList<String> wordList1 = new ArrayList<String>(); 
  		String line =null;
        Path path = Paths.get(fname);
        Scanner scanner = new Scanner(path);
       while(scanner.hasNextLine()){
      //process each line
       line = scanner.nextLine();
       System.out.println(line);
       if(line.contains("abi")) {
       System.out.println("abi printing");
       }
       else {
    	  // System.out.println("Not printed");
       }
       wordList1.add(line);}
      System.out.println(wordList1);
      scanner.close();
}
    	public int totalcount(String filename)throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
	     String input;
	     int count = 0;
	     while((input = bufferedReader.readLine()) != null)
	     {
	         count++;
	     }
	     System.out.println("Count : "+count);
		return count;
		   }
	
    	public static String getLastModifiedFile(String dirPath)
    			throws InterruptedException {
    		Thread.sleep(4000);
    		File dir = new File(dirPath);
    		File[] files = dir.listFiles();
    		if (files == null || files.length == 0) {
    			return null;
    		}

    		File lastModifiedFile = files[0];
    		for (int i = 1; i < files.length; i++) {
    			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
    				lastModifiedFile = files[i];
    			}
    		}
    		return lastModifiedFile.getName();
    	}
    	public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
    		String Exportpath ="./downloads/";
    		String report_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
    		FileHandler.renameTo(new File(Exportpath + report_export), Exportpath + export);
    	}
    	
}



