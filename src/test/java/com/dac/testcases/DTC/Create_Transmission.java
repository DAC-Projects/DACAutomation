package com.dac.testcases.DTC;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_DTC.DTC_Transmission;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
public class Create_Transmission extends BaseClass {

	WebDriverWait wait;
	String url="https://beta-dtc-web.azurewebsites.net/";
	@Test	
	public void launchBrowser() {
		  CurrentState.getDriver().get(url);
		  Dimension d = new Dimension(1000,700);
		   System.out.println(CurrentState.getDriver().getTitle());
		   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
		    dtcLogin.pressYesKey();
}	
	@Test( dependsOnMethods = { "launchBrowser"})
	 public void LoginDTC() throws Exception {
		int manuallocation=4;
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
			ExcelHandler wb1 = new ExcelHandler("./data/LocationDataDownload_20200720134229.xlsx", "LocationDataDownloadReport");

		  for(int i1=1;i1<=1;i1++) {
				System.out.println("*******************  Scenarios : "+"Starts ****************************");
				String LO_number1= wb1.getCellValue(1, wb1.seacrh_pattern("Numéro de téléphone de l'entreprise", 0).get(0).intValue());
				System.out.println(LO_number1);
		  }
		/*  String LO_number = null;
		  try {	
			  dtcLogin.Transmission();
				int count = 1;
				int a1=1;
				int b=0;
				ExcelHandler wb = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
				wb.deleteEmptyRows();
				int a=wb.getRowCount();
				System.out.println(a);
				for(int i=1;i<=manuallocation;i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					LO_number = wb.getCellValue(i, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
					System.out.println(LO_number);
					dtcLogin.manual_Transmission(LO_number,a1,b);
					a1++;
					count++;
				}}
		  catch(Exception e) {
		  e.printStackTrace();*/
		  
			}
	
	@Test( dependsOnMethods = { "LoginDTC"})
	 public void Addrequest() throws Exception {
		int otherlocation=1;
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  String pageTitle= dtcLogin.getTitle(CurrentState.getDriver());
		  String LO_number = null , account= null, reseller=null, parameter=null;
		  ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
		  wb1.deleteEmptyRows();
		  int a1=wb1.getRowCount();
		  System.out.println(a1);
		  
				LO_number = wb1.getCellValue(otherlocation, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
				account=wb1.getCellValue(otherlocation, wb1.seacrh_pattern("AccountName", 0).get(0).intValue());
				System.out.println(LO_number);
		  
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/LocationSampleData.xlsx", "Sheet1");
				wb.deleteEmptyRows();
				int a=wb.getRowCount();
				System.out.println(a);
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					reseller=wb.getCellValue(otherlocation, wb.seacrh_pattern("Reseller", 0).get(0).intValue());
					parameter=wb.getCellValue(otherlocation, wb.seacrh_pattern("Parameter", 0).get(0).intValue());
					System.out.println(LO_number);
					count++;
				}
		  catch(Exception e) {
		  e.printStackTrace();
			}
		  
		System.out.println(pageTitle);
		
		dtcLogin.apple_Trans(account);
		addEvidence(CurrentState.getDriver(), "Apple", "yes");

		dtcLogin.zoamto_Trans(account);
		addEvidence(CurrentState.getDriver(), "Zomato", "yes");

		dtcLogin.Here_Trans(account);
		addEvidence(CurrentState.getDriver(), "Here", "yes");

		dtcLogin.Tomtom_Trans(LO_number, account, reseller, parameter);
		addEvidence(CurrentState.getDriver(), "Tomtom", "yes"); 
		}
		 }
