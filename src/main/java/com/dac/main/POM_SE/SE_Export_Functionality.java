package com.dac.main.POM_SE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

import resources.FileHandler;
import resources.JSWaiter;
import resources.formatConvert;

public class SE_Export_Functionality extends BasePage  implements SE_Repository{
	public static final String delimiter = ",";
	public static List<String> locationtable = new ArrayList<String>();
	public static List<String> pagetable = new ArrayList<String>();
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
	
	

	
	public void exportFunctionality() throws InterruptedException, FileNotFoundException, IOException{

	 
		clickelement(export_btn);
		Thread.sleep(5000);
	/*	Thread.sleep(10000);
		convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+ConnectionExport));
		System.out.println("The File Name is" +getLastModifiedFile(Exportpath));
		*/
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
			 System.out.println("Te final list of locations : " +locationtable);
			 
			 
		}
		
		public void UIPagesRead()
		{
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
		
		public void excelRead_UIexcelcomparison() throws IOException, InterruptedException
		{
			JSWaiter.waitJQueryAngular();		
			String n = driver.findElement(By.xpath("(//*[@class='pagination pull-right']//a)[last()-1]")).getText();
			int page = Integer.parseInt(n);
			System.out.println("\n"+page);
			
			String splitBy = ",";
			String location = loc.getText();
			String sitename = fbpage.getText();
			ArrayList<String> Type = new ArrayList<String>();
			ArrayList<String> Locationnumber = new ArrayList<String>();
			ArrayList<String> Clientref = new ArrayList<String>();
			ArrayList<String> Locationname = new ArrayList<String>();
			ArrayList<String> Locationaddress = new ArrayList<String>();
			ArrayList<String> Site = new ArrayList<String>();
			ArrayList<String> Sitepagename = new ArrayList<String>();
			ArrayList<String> Sitepageaddress = new ArrayList<String>();
			ArrayList<String> Sitepageurl = new ArrayList<String>();
			
			
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
				temlist.addAll(locationtable);
				System.out.println("The temp list is :" +temlist);
				
		/*		StringBuilder strbul=new StringBuilder();
				for(String str : Locationaddress)
				{
					strbul.append(str);
					//for adding comma between elements
					strbul.append(",");
				}
				//just for removing last comma
				//strbul.setLength(strbul.length()-1);
				String str=strbul.toString();
				
				System.out.println("Converted String is " + str);
				
				String s1=str.replace("US", "New York");
				
				System.out.println("Replaced String is " + s1);
				
				List<String> myList = new ArrayList<String>(Arrays.asList(s1.split(",")));
				System.out.println("The string converted array list is :" +myList);*/
				
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
					/*System.out.println("data: "+uidata[1]);
					System.out.println("data: "+uidata[2]);
					System.out.println("data: "+uidata[3]);
					System.out.println("data: "+uidata[4]);*/
					System.out.println("data of location: "+Locationaddress.get(i));
					soft.assertTrue(Locationaddress.get(i).contains(str1),"Location Address  "+Locationaddress.get(i)+" is not there in list" +uidata[1]);
					soft.assertTrue(Locationaddress.get(i).contains(str2),"Location Address  "+Locationaddress.get(i)+" is not there in list");
					soft.assertTrue(Locationaddress.get(i).contains(str3),"Location Address  "+Locationaddress.get(i)+" is not there in list");
					soft.assertTrue(Locationaddress.get(i).contains(str4),"Location Address  "+Locationaddress.get(i)+" is not there in list");
					
				//	soft.assertTrue(Locationaddress.get(i).contains(temlist.get(i)),"Location Address  "+Locationaddress.get(i)+" is not there in list");

				}
			}
			
			
			
			ArrayList<String> pagelist = new ArrayList<String>();
			pagelist.addAll(pagetable);
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
					softpa.assertTrue(uidata[0].contains(Sitepagename.get(i)),"Site Page Name "+Sitepagename.get(i)+" is not there in list");

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
				
					System.out.println("data of page address: "+pagelist.get(i));
					softpa.assertTrue(Sitepageaddress.get(i).contains(str5),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str6),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str7),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					softpa.assertTrue(Sitepageaddress.get(i).contains(str8),"Page Address  "+Sitepageaddress.get(i)+" is not there in list");
					
				//	softpa.assertTrue(pagelist.get(i).contains(Sitepageaddress.get(i)),"Site Page Address "+Sitepageaddress.get(i)+" is not there in list");

				}
			}
			
	/*	Sitepageurl.size();
			if(pagelist.size()==Sitepageurl.size())
			{
				for(int i=0;i<=pagelist.size()-1;i++)
				{
					System.out.println("Page List "+pagelist.get(i));
					System.out.println("Site Page URL "+Sitepageurl.get(i));
					softpa.assertTrue(pagelist.get(i).contains(Sitepageurl.get(i)),"Site Page Url "+Sitepageurl.get(i)+" is not there in list");

				}
			}*/
			soft.assertAll();
			softpa.assertAll();
			if(paginationNext.isEnabled()) {
    			scrollByElement(paginationNext);
    			paginationNext.click();
    			Thread.sleep(4000);
    		}	   
		
		}
}

