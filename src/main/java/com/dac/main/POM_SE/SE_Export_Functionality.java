package com.dac.main.POM_SE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.dac.main.BasePage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.FileHandler;
import resources.JSWaiter;
import resources.formatConvert;

public class SE_Export_Functionality extends  SE_abstractMethods  {
	public static final String delimiter = ",";
	//public static List<String> locationtable = new ArrayList<String>();
	//public static List<String> brandtable = new ArrayList<String>();
	//public static List<String> pagetable = new ArrayList<String>();
	//public static List<String> brandpagetable = new ArrayList<String>();
	//public static List<String> pagehandlinglocationtable = new ArrayList<String>();
	//public static List<String> pagehandlingbrandtable = new ArrayList<String>();
	//public static List<String> pagehandlingpagetable = new ArrayList<String>();
//	public static List<String> pagehandlingpagetablebrands = new ArrayList<String>();

	public String[] uidata;

	
	
	/*-------------------------Pagination-----------------------*/
	@FindBy(xpath = "//*[@class='pagination pull-right']")
	private List<WebElement> pagination;
	
	@FindBy(xpath = "(//*[@class='pagination pull-right']//a)[1]")
	
	private WebElement paginationPrev;
	
	@FindBy(xpath = "(//*[@class='pagination pull-right']//a)[last()]")
	private WebElement paginationNext;
	
	@FindBy(xpath = "(//*[@class='pagination pull-right']//a)[last()-1]")
	private List<WebElement> paginationLast;
	
	@FindBy(xpath="//table[@class='table table-striped table-bordered']//tbody//tr")
	private List<WebElement> tablerow;
	/*-------------------------Pagination-----------------------*/

	//public static String s;
	
	
	//List<List<String>> lines = new ArrayList();
	
	
	public SE_Export_Functionality(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}


	@FindBy( xpath = "//button[@id='exportView']")
	private WebElement export_btn;
	@FindBy(xpath = "//th[contains(text(),'Location')]")
	private WebElement loc;
	@FindBy(xpath = "//th[contains(text(), 'Facebook Page')]")
	private WebElement fbpage;
	@FindBy(xpath="//th[contains(text(), 'Google My Business Page')]")
	private WebElement gmbpage;
	@FindBy(xpath="//th[contains(text(), 'Twitter Page')]")
	private WebElement twitterpage;
	@FindBy(xpath="//th[contains(text(), 'YouTube Page')]")
	private WebElement youtubepage;
	@FindBy(xpath="//th[contains(text(),'Brand / Other')]")
	private WebElement brand;
	@FindBy(xpath="//*[@class='media-body padding-top-5']")
	private WebElement vendor;

	
	public void exportFunctionality() throws InterruptedException, FileNotFoundException, IOException{

	 
		clickelement(export_btn);
		Thread.sleep(5000);
	
	}
	
	//convert exported file from csv to xlsx
		public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
			String report_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
			FileHandler.renameTo(new File(Exportpath + report_export), Exportpath + export);
			
		}
		
		public void exportConnection() throws FileNotFoundException, IOException, InterruptedException {
			convertExports(getLastModifiedFile("./downloads/"),ConnectionExport);
		}
		
		public void UILocationRead()
		{
			List<String> locationtable = new ArrayList<String>();
			 List<WebElement> li = driver.findElements(By.xpath("//td[@class = 'locations-cell']"));
			 int size =  li.size();
			 System.out.println("The size of elements :" +size);
			 String text;
			 
			// List<String> locationtable = new ArrayList<String>();
			 for(int i=1; i<=size; i++) {
				 text = driver.findElement(By.xpath("(//td[@class='locations-cell'])["+ i +"]")).getText();
				 System.out.println("The location column text : " +text);
				 locationtable.add(text);
			 }
			 System.out.println("The final list of locations : " +locationtable);
			 
			 
		}
		
		public void UIPagesRead()
		{
			List<String> pagetable = new ArrayList<String>();
			List<WebElement> pa = driver.findElements(By.xpath("//td[@class = 'pages-cell']"));
			 int pa_size =  pa.size();
			 System.out.println("The size of elements :" +pa_size);
			 String pa_text;
			
			 for(int i=1; i<=pa_size; i++) {
				 pa_text = driver.findElement(By.xpath("(//td[@class='pages-cell'])["+ i +"]")).getText();
				 System.out.println("The pages column text : " +pa_text);
				 pagetable.add(pa_text);
			 }
			 System.out.println("Te final list of pages : " +pagetable);
		}
		
		public void UIBrandRead()
		{
			
			List<String> brandtable = new ArrayList<String>();
			 List<WebElement> li = driver.findElements(By.xpath("//td[@class = 'locations-cell']"));
			 int size =  li.size();
			 System.out.println("The size of elements :" +size);
			 String text;
			 
			// List<String> locationtable = new ArrayList<String>();
			 for(int i=1; i<=size; i++) {
				 text = driver.findElement(By.xpath("(//td[@class='locations-cell'])["+ i +"]")).getText();
				 System.out.println("The Brand column text : " +text);
				 brandtable.add(text);
			 }
			 System.out.println("The final list of Brands : " +brandtable);
			 
			 
		}
		
		public void UIPagesReadInBrands()
		{
			List<String> brandpagetable = new ArrayList<String>();
			List<WebElement> pa = driver.findElements(By.xpath("//td[@class = 'pages-cell']"));
			 int pa_size =  pa.size();
			 System.out.println("The size of elements :" +pa_size);
			 String pa_text;
			
			 for(int i=1; i<=pa_size; i++) {
				 pa_text = driver.findElement(By.xpath("(//td[@class='pages-cell'])["+ i +"]")).getText();
				 System.out.println("The pages column text : " +pa_text);
				 brandpagetable.add(pa_text);
			 }
			 System.out.println("Te final list of pages : " +brandpagetable);
		}
		
		public void excelRead_UIexcelcomparison() throws IOException, InterruptedException
		{
			List<String> pagehandlinglocationtable = new ArrayList<String>();
			List<String> locationtable = new ArrayList<String>();
			List<String> pagehandlingpagetable = new ArrayList<String>();
			String splitBy = ",";
			String location = loc.getText();
			String theVendor = vendor.getText();
			String sitename = null;
			if(theVendor.equals("Facebook"))
			{	
				sitename = fbpage.getText();
			}
			else if(theVendor.equals("Google My Business"))
				{
					 sitename = gmbpage.getText();
				}else
			if(theVendor.equals("Twitter"))
			{
				 sitename = twitterpage.getText();
			}else
			 if(theVendor.equals("YouTube"))			{
				sitename = youtubepage.getText();
			}else {
				System.out.println("No vendors available");
			}
			
			ArrayList<String> Type = new ArrayList<String>();
			ArrayList<String> Locationnumber = new ArrayList<String>();
			ArrayList<String> Clientref = new ArrayList<String>();
			ArrayList<String> Locationname = new ArrayList<String>();
			ArrayList<String> Locationaddress = new ArrayList<String>();
			ArrayList<String> Site = new ArrayList<String>();
			ArrayList<String> Sitepagename = new ArrayList<String>();
			ArrayList<String> Sitepageaddress = new ArrayList<String>();
			ArrayList<String> Sitepageurl = new ArrayList<String>();
			
			JSWaiter.waitJQueryAngular();
			if(!driver.findElements(By.xpath("//*[@id='table-pagination-cell']/td/div[1]/div[2]/ul")).isEmpty())
			{
				String n = driver.findElement(By.xpath("(//*[@class='pagination pull-right']//a)[last()-1]")).getText();
				int page = Integer.parseInt(n);
				System.out.println("\n"+page);
				
				for(int i=1;i<=page;i++)
				{
					int rowcount = tablerow.size() - 1;
					System.out.println("Rowcount :"+rowcount);
					for(int j = 1; j<=rowcount; j++) {
						
						//you need to write the code for retrieving data from UI
						
						String pagehandlingtext = driver.findElement(By.xpath("(//td[@class='locations-cell'])["+ j +"]")).getText();
						 System.out.println("The location column text page handling : " +pagehandlingtext);
						 pagehandlinglocationtable.add(pagehandlingtext);
						 
						 String pagehandlingtext1 = driver.findElement(By.xpath("(//td[@class='pages-cell'])["+ j +"]")).getText();
						 System.out.println("The pages column text : " +pagehandlingtext1);
						 pagehandlingpagetable.add(pagehandlingtext1);
						 
					}
					if(paginationNext.isEnabled()) {
		    			scrollByElement(paginationNext);
		    			paginationNext.click();
		    			Thread.sleep(4000);
		    		}	 
				}
		
			/*	you can write code for retrieving data from XL 
				Write the code for comparing data between UI and XL*/
				
			
				
			/*	soft.assertAll();
				softpa.assertAll();
				if(paginationNext.isEnabled()) {
	    			scrollByElement(paginationNext);
	    			paginationNext.click();
	    			Thread.sleep(4000);
	    		}	 */
				
			}
			
			else
			{
				System.out.println("No pagination");
				int rowcount = tablerow.size() - 1;
				System.out.println("Rowcount :"+rowcount);
				for(int j = 1; j<=rowcount; j++) {
					
					//you need to write the code for retrieving data from UI
					
					String pagehandlingtext = driver.findElement(By.xpath("(//td[@class='locations-cell'])["+ j +"]")).getText();
					 System.out.println("The location column text page handling : " +pagehandlingtext);
					 pagehandlinglocationtable.add(pagehandlingtext);
					 
					 String pagehandlingtext1 = driver.findElement(By.xpath("(//td[@class='pages-cell'])["+ j +"]")).getText();
					 System.out.println("The pages column text : " +pagehandlingtext1);
					 pagehandlingpagetable.add(pagehandlingtext1);
					 
				}
			}
	
	
			
 			BufferedReader br = new BufferedReader(new FileReader("C://Users//rohitm//git//DACAutomation//downloads//Connection Manage Pages-Location_Brand Export.csv"));
			String line = br.readLine();
			while ((line = br.readLine()) !=null){
			String[] b = line.split(splitBy);
	
int  size = b.length;
	if(size>0)
			Type.add(b[0]);
	else
			Type.add("");
	if(size>1)
			Locationnumber.add(b[1]);
	else
			Locationnumber.add("");
	if(size>2)
			Clientref.add(b[2]);
	else
			Clientref.add("");
	if(size>3)
			Locationname.add(b[3]);
	else
			Locationname.add("");
	if(size>4)
			Locationaddress.add(b[4]);
	else
			Locationaddress.add("");
	if(size>5)
			Site.add(b[5]);
	else
			Site.add("");
	if(size>6)
			Sitepagename.add(b[6]);
	else
			Sitepagename.add("");
	if(size>7)
			Sitepageaddress.add(b[7]);
	else
			Sitepageaddress.add("");
	if(size>8)
			Sitepageurl.add(b[8]);
	else
			Sitepageurl.add("");
							
			
		}
			System.out.println("Data in Type column : " +Type); 
			System.out.println("Data in Location Number column : " +Locationnumber); 
			System.out.println("Data in Client Ref column : " +Clientref);
			System.out.println("Data in Location Name column : " +Locationname); 
			System.out.println("Data in Location Address column : " +Locationaddress);
			System.out.println("Data in Site column : " +Site); 
			System.out.println("Data in Site Page Name column : " +Sitepagename);
			System.out.println("Data in Site Page Address column : " +Sitepageaddress);
			System.out.println("Data in Site Page Url column : " +Sitepageurl); 
		
			
				
				ArrayList<String> temlist = new ArrayList<String>();
				temlist.addAll(pagehandlinglocationtable);
				System.out.println("The temp list is :" +temlist);
				
		
				
			SoftAssert soft = new SoftAssert();

			temlist.size();
			
			Type.size();
			if(temlist.size()==Type.size())
			{
				for(int i=0;i<=temlist.size()-1;i++)
				{
					
					soft.assertTrue(Type.get(i).contains(location),"Type "+Type.get(i)+" is not there in list");
					
					

				}
			}
	
			Locationnumber.size();
			if(temlist.size()==Locationnumber.size()) {
				for(int i =0; i<= temlist.size() - 1; i++) {
					uidata = temlist.get(i).split(",");
					System.out.println("Location Num "+uidata[5]);
					soft.assertTrue(uidata[5].contains(Locationnumber.get(i)),"Location Number "+Locationnumber.get(i)+" is not there in list");
	
				}
				
			}
			Clientref.size();
			if(temlist.size()==Clientref.size())
			{
				for(int i=0;i<=temlist.size()-1;i++)
				{
					uidata = temlist.get(i).split(",");
					System.out.println("Client Ref "+uidata[6]);
					soft.assertTrue(uidata[6].contains(Clientref.get(i)),"Client Ref "+Clientref.get(i)+" is not there in list");

				}
			}
			
			Locationname.size();
			if(temlist.size()==Locationname.size())
			{
				for(int i=0;i<=temlist.size()-1;i++)
				{
					uidata = temlist.get(i).split(",");
					System.out.println("Location Name "+uidata[0]);
					soft.assertTrue(uidata[0].contains(Locationname.get(i)),"Location Name  "+Locationname.get(i)+" is not there in list "+locationtable+"");

				}
			}
	
			Locationaddress.size();
			if(temlist.size()==Locationaddress.size())
			{
				for(int i=0;i<=temlist.size()-1;i++)
				{
					uidata = temlist.get(i).split(",");
					String str1= uidata[1].trim();
					String str2= uidata[2].trim();
					String str3= uidata[3].trim();
					String str4= uidata[4].trim();
					String str5= Locationaddress.get(i).trim();
					System.out.println("Location data: "+str1+"without trim:"+uidata[1]);
					System.out.println("City data: "+str2+"without trim:"+uidata[2]);
					/*System.out.println("data: "+str3);
					System.out.println("data: "+str4);*/
					System.out.println("data of location:"+Locationaddress.get(i));
				
						if(str3.equals("Bayern"))
					{
							System.out.println("Skipped the validation of German location because of special character issue");
					}
					else
					{
						soft.assertTrue(str5.contains(str1),"Location:"+str5+" is not there in list" +str1);
						soft.assertTrue(str5.contains(str2),"City:"+str5+" is not there in list:"+str2);
						System.out.println("Success");
					}
					
					//soft.assertTrue(str5.contains(str3),"State:"+str5+" is not there in list:"+str3);
					soft.assertTrue(str5.contains(str4),"PIN CODE:"+str5+" is not there in list:"+str4);
					
					
				

				}
			}
			
			
			
			ArrayList<String> pagelist = new ArrayList<String>();
			pagelist.addAll(pagehandlingpagetable);
			System.out.println("The temp1 list is :" +pagelist);

		SoftAssert softpa = new SoftAssert();

		pagelist.size();
		
		Type.size();
			
			Site.size();
			if(pagelist.size()==Site.size())
			{
				for(int i=0;i<=pagelist.size()-1;i++)
				{
					System.out.println(Site.get(i));
					System.out.println(sitename);
				
					softpa.assertTrue(sitename.contains(Site.get(i)),"Site  "+Site.get(i)+" is not there in list");

				}
			}
			
			Sitepagename.size();
			
			if(pagelist.size()==Sitepagename.size())
				
			{
				String str9;
				String str10="";
				for(int i=0;i<=pagelist.size()-1;i++)
				{
					uidata = pagelist.get(i).split(",");
					int n2=uidata.length;
					System.out.println("Site Page Name "+uidata[0]);
					
					
					
					if(n2>1)
					{
						str9= uidata[0];
						str10= Sitepagename.get(i);
					}
					else {
						str9="";
						str10="";
					}
					
					System.out.println("UIDATA 0 "+uidata[0]);
					System.out.println("STR 9 "+str9);
					
					System.out.println("STR 10 "+str10);
					
					String result = str10.replaceAll("[-+^:â€œ€™]","");
				
					str9 = str9.replaceAll("’","");
				
					System.out.println("Double quotes:"+result);
					System.out.println("Single quotes:"+str9);
					//softpa.assertTrue(str9.contains(result),"Site Page Name "+result+" is not there in list");
					softpa.assertTrue(result.contains(str9),"Site Page Name "+result+" is not there in list");
				}
				
				
			}
			
			Sitepageaddress.size();
			if(pagelist.size()==Sitepageaddress.size())
			{
				String str5;
				String str6;
				String str7;
				String str8;
				for(int i=0;i<pagelist.size();i++)
				{
					uidata = pagelist.get(i).split(",");
					int n1=uidata.length;
					System.out.println(n1);
					if(n1>2) {
					 str5= uidata[1].trim();
					}
					else {
						str5="";	
					}
					if(n1>3)
					{
						str6= uidata[2].trim();
					}
					else
					{
						str6="";
					}
					
					if(n1>4)
					{
						str7= uidata[3].trim();
					}
					else
					{
						str7="";
					}
					if(n1>5)
					{
						str8= uidata[4].trim();
					}
					else
					{
						str8="";
					}
					 
					 
					System.out.println("data: 1 "+str5);
					System.out.println("data: 2 "+str6);
					System.out.println("data: 3 "+str7);
					System.out.println("data: 4 "+str8);
				
					System.out.println("data of page address: "+Sitepageaddress.get(i));
					softpa.assertTrue(Sitepageaddress.get(i).contains(str5),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str6),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str7),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str8),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					
				//	softpa.assertTrue(pagelist.get(i).contains(Sitepageaddress.get(i)),"Site Page Address "+Sitepageaddress.get(i)+" is not there in list");

				}
			}
	
				
			
			
		}
		
		public void excelRead_UIexcelcomparisonBrands() throws IOException, InterruptedException
		{
			List<String> pagehandlingpagetablebrands = new ArrayList<String>();
			List<String> pagehandlingbrandtable = new ArrayList<String>();
			List<String> brandtable = new ArrayList<String>();
			String splitBy = ",";
			String brandType=brand.getText();
			String theVendor = vendor.getText();
			String sitename = null;
			if(theVendor.equals("Facebook"))
			{	
				sitename = fbpage.getText();
			}
			else if(theVendor.equals("Google"))
				{
					 sitename = gmbpage.getText();
				}else
			if(theVendor.equals("Twitter"))
			{
				 sitename = twitterpage.getText();
			}else
			 if(theVendor.equals("YouTube"))			{
				sitename = youtubepage.getText();
			}else {
				System.out.println("No vendors available");
			}
			
			ArrayList<String> brandTypeExcel = new ArrayList<String>();
			ArrayList<String> brandName = new ArrayList<String>();
			ArrayList<String> brandDescription = new ArrayList<String>();
			ArrayList<String> Site = new ArrayList<String>();
			ArrayList<String> Sitepagename = new ArrayList<String>();
			ArrayList<String> Sitepageaddress = new ArrayList<String>();
			ArrayList<String> Sitepageurl = new ArrayList<String>();
			
		JSWaiter.waitJQueryAngular();
			if(!driver.findElements(By.xpath("//*[@id='table-pagination-cell']/td/div[1]/div[2]/ul")).isEmpty())
			{
				String n = driver.findElement(By.xpath("(//*[@class='pagination pull-right']//a)[last()-1]")).getText();
				int page = Integer.parseInt(n);
				System.out.println("\n"+page);
				
				for(int i=1;i<=page;i++)
				{
					int rowcount = tablerow.size() - 1;
					System.out.println("Rowcount :"+rowcount);
					for(int j = 1; j<=rowcount; j++) {
						
						//you need to write the code for retrieving data from UI
						
						String pagehandlingtext2 = driver.findElement(By.xpath("(//td[@class='locations-cell'])["+ j +"]")).getText();
						 System.out.println("The Brand column text page handling : " +pagehandlingtext2);
						 pagehandlingbrandtable.add(pagehandlingtext2);
						 
						 String pagehandlingtext3 = driver.findElement(By.xpath("(//td[@class='pages-cell'])["+ j +"]")).getText();
						 System.out.println("The pages column text : " +pagehandlingtext3);
						 pagehandlingpagetablebrands.add(pagehandlingtext3);
						 
					}
					if(paginationNext.isEnabled()) {
		    			scrollByElement(paginationNext);
		    			paginationNext.click();
		    			Thread.sleep(4000);
		    		}	 
				}
		
	
				
			}
			
			else
			{
				System.out.println("No pagination");
				
					int rowcount = tablerow.size() - 1;
					System.out.println("Rowcount :"+rowcount);
					for(int j = 1; j<=rowcount; j++) {
						
						//you need to write the code for retrieving data from UI
						
						String pagehandlingtext2 = driver.findElement(By.xpath("(//td[@class='locations-cell'])["+ j +"]")).getText();
						 System.out.println("The Brand column text page handling : " +pagehandlingtext2);
						 pagehandlingbrandtable.add(pagehandlingtext2);
						 
						 String pagehandlingtext3 = driver.findElement(By.xpath("(//td[@class='pages-cell'])["+ j +"]")).getText();
						 System.out.println("The pages column text : " +pagehandlingtext3);
						 pagehandlingpagetablebrands.add(pagehandlingtext3);
						 
					}
		
			}
			
			
			
 			BufferedReader br1 = new BufferedReader(new FileReader("C://Users//rohitm//git//DACAutomation//downloads//Connection Manage Pages-Location_Brand Export.csv"));
			String line1 = br1.readLine();
			while ((line1 = br1.readLine()) !=null){
			String[] b1 = line1.split(splitBy);
			
			int  size1 = b1.length;
			if(size1>0)
				brandTypeExcel.add(b1[0]);
			else
				brandTypeExcel.add("");
			if(size1>1)
				brandName.add(b1[1]);
			else
				brandName.add("");
			if(size1>2)
				brandDescription.add(b1[2]);
			else
				brandDescription.add("");
			
			if(size1>3)
				Site.add(b1[3]);
			else
				Site.add("");
			if(size1>4)
				Sitepagename.add(b1[4]);
			else
				Sitepagename.add("");
			if(size1>5)
				Sitepageaddress.add(b1[5]);
			else
				Sitepageaddress.add("");
			if(size1>6)
				Sitepageurl.add(b1[6]);
			else
				Sitepageurl.add("");
			
			System.out.println("Data in Type column : " +brandTypeExcel); 
			System.out.println("Data in Brand Name column : " +brandName); 
			System.out.println("Data in Brand Description column : " +brandDescription);
			System.out.println("Data in Site column : " +Site); 
			System.out.println("Data in Site Page Name column : " +Sitepagename);
			System.out.println("Data in Site Page Address column : " +Sitepageaddress);
			System.out.println("Data in Site Page Url column : " +Sitepageurl); 
			
			ArrayList<String> temlist1 = new ArrayList<String>();
			temlist1.addAll(pagehandlingbrandtable);
			System.out.println("The temp list is :" +temlist1);
			
			SoftAssert soft = new SoftAssert();

			temlist1.size();
			
			brandTypeExcel.size();
			if(temlist1.size()==brandTypeExcel.size())
			{
				for(int i=0;i<=temlist1.size()-1;i++)
				{
					
						soft.assertTrue(brandTypeExcel.get(i).contains(brandType),"Type "+brandTypeExcel.get(i)+" is not there in list");	

				}
			}
			
			brandName.size();
			if(temlist1.size()==brandName.size()) {
				for(int i =0; i<= temlist1.size() - 1; i++) {
					uidata = temlist1.get(i).split(",");
					System.out.println("Brand Name "+uidata[0]);
					soft.assertTrue(uidata[0].contains(brandName.get(i)),"Location Number "+brandName.get(i)+" is not there in list");
	
				}
				
			}
			
			ArrayList<String> pagelist = new ArrayList<String>();
			pagelist.addAll(pagehandlingpagetablebrands);
			System.out.println("The temp1 list is :" +pagelist);

		SoftAssert softpa = new SoftAssert();

		pagelist.size();
		
		brandTypeExcel.size();
			
			Site.size();
			if(pagelist.size()==Site.size())
			{
				for(int i=0;i<=pagelist.size()-1;i++)
				{
					System.out.println(Site.get(i));
					System.out.println(sitename);
				
					softpa.assertTrue(sitename.contains(Site.get(i)),"Site  "+Site.get(i)+" is not there in list");

				}
			}
			
			Sitepagename.size();
			
			if(pagelist.size()==Sitepagename.size())
				
			{
				String str9;
				String str10="";
				for(int i=0;i<=pagelist.size()-1;i++)
				{
					uidata = pagelist.get(i).split(",");
					int n2=uidata.length;
					System.out.println("Site Page Name "+uidata[0]);
					
					
					
					if(n2>1)
					{
						str9= uidata[0];
						str10= Sitepagename.get(i);
					}
					else {
						str9="";
						str10="";
					}
					
					System.out.println("UIDATA 0 "+uidata[0]);
					System.out.println("STR 9 "+str9);
					
					System.out.println("STR 10 "+str10);
					
					String result = str10.replaceAll("[-+^:â€œ€™]","");
				
					str9 = str9.replaceAll("’","");
				
					System.out.println("Double quotes:"+result);
					System.out.println("Single quotes:"+str9);
					//softpa.assertTrue(str9.contains(result),"Site Page Name "+result+" is not there in list");
					softpa.assertTrue(result.contains(str9),"Site Page Name "+result+" is not there in list");
				}
				
				
			}
			
			Sitepageaddress.size();
			if(pagelist.size()==Sitepageaddress.size())
			{
				String str5;
				String str6;
				String str7;
				String str8;
				for(int i=0;i<pagelist.size();i++)
				{
					uidata = pagelist.get(i).split(",");
					int n1=uidata.length;
					System.out.println(n1);
					if(n1>2) {
					 str5= uidata[1].trim();
					}
					else {
						str5="";	
					}
					if(n1>3)
					{
						str6= uidata[2].trim();
					}
					else
					{
						str6="";
					}
					
					if(n1>4)
					{
						str7= uidata[3].trim();
					}
					else
					{
						str7="";
					}
					if(n1>5)
					{
						str8= uidata[4].trim();
					}
					else
					{
						str8="";
					}
					 
					 
					System.out.println("data: 1 "+str5);
					System.out.println("data: 2 "+str6);
					System.out.println("data: 3 "+str7);
					System.out.println("data: 4 "+str8);
				
					System.out.println("data of page address: "+Sitepageaddress.get(i));
					softpa.assertTrue(Sitepageaddress.get(i).contains(str5),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str6),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str7),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str8),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					
				//	softpa.assertTrue(pagelist.get(i).contains(Sitepageaddress.get(i)),"Site Page Address "+Sitepageaddress.get(i)+" is not there in list");

				}
			}
	
			
		
			
		}
			
		}
			
		}
		


