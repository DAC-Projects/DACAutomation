package com.dac.testcases.DTC;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.dac.main.POM_DTC.DTC_Transmission;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class VerifytheStatus_Manual extends BaseClass {
	 WebDriverWait wait;
	 String url="https://beta-dtc-web.azurewebsites.net/";
	 
	@Test(groups = { "Setup" })	
	public void launchBrowser() throws Exception {
	   CurrentState.getDriver().get(url);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	   dtcLogin.pressYesKey();
	}	
	
	int manual_com=1;
	int manual_can=2;
	int manual_allcom=3;
	int manual_allcan=4;

	@Test( dependsOnMethods = { "launchBrowser"})
	 public void complete() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(manual_com, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);	
			        String vendor="Bing";
			     //  dtcLogin.verif_Status_complete(Manual_ID,vendor);
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			addEvidence(CurrentState.getDriver(), "Manual", "yes");
		  } }

	@Test( dependsOnMethods = { "complete"})
	 public void cancel() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(manual_can, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);		
			 	 // dtcLogin.verif_Status_cancel(Manual_ID);
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			addEvidence(CurrentState.getDriver(), "Manual", "yes");

			}}
	

	@Test( dependsOnMethods = { "cancel"})
	 public void allcomplete() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(manual_allcom, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);			        
			 	    dtcLogin.verif_Status_allcomplete(Manual_ID);
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			addEvidence(CurrentState.getDriver(), "Manual", "yes");
			}}

	@Test( dependsOnMethods = { "allcomplete"})
	 public void allcancel() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(manual_allcan, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);
			       // dtcLogin.verif_Status_allcancel(Manual_ID);
					count++;}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			addEvidence(CurrentState.getDriver(), "Manual", "yes");
		  }}
	
	@Test( dependsOnMethods = { "allcancel"})
	 public void Other_venodrs() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  int othervendor=1;
		  int APIvemdor=5;
		  String apple_RqID=null;
		  String zomato_RqID=null;
		  String here_RqID=null;
		  String tomtom_Request_ID=null;
		  String LO_number_API= null;
		  String vendor=null;
		  String pageTitle= dtcLogin.getTitle(CurrentState.getDriver());
		  ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
		  wb1.deleteEmptyRows();
		  int a=wb1.getRowCount();
		  System.out.println(a);
		  String LO_number = null,location_name=null;
				LO_number = wb1.getCellValue(othervendor, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
				location_name =wb1.getCellValue(othervendor, wb1.seacrh_pattern("LocationName", 0).get(0).intValue());
				System.out.println(LO_number);
			    ExcelHandler wb11 = new ExcelHandler("./data/LocationSampleData.xlsx", "Sheet1");
				wb11.deleteEmptyRows();
				int a2=wb11.getRowCount();
				System.out.println(a2);
			    vendor=wb11.getCellValue(othervendor, wb11.seacrh_pattern("Vendor", 0).get(0).intValue());
				System.out.println("vendor"+vendor);
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
			        zomato_RqID = wb.getCellValue(1, wb.seacrh_pattern("Zomato_Request_ID", 0).get(0).intValue());
			        here_RqID = wb.getCellValue(1, wb.seacrh_pattern("Here_Request_ID", 0).get(0).intValue());
			        tomtom_Request_ID = wb.getCellValue(1, wb.seacrh_pattern("tomtom_Request_ID", 0).get(0).intValue());
			      	count++;}
		  	   catch(Exception e)
		  		{
		       e.printStackTrace();
			   addEvidence(CurrentState.getDriver(), "Manual", "yes");
			}
		  System.out.println(pageTitle);
		  dtcLogin.API_Transmission();
		  addEvidence(CurrentState.getDriver(), "API", "yes");
		  ExcelHandler wb111 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
		  wb111.deleteEmptyRows();
		  int a1=wb111.getRowCount();
		  System.out.println(a1);
				System.out.println("*******************  Scenarios : "+"Starts ****************************");
				LO_number_API = wb111.getCellValue(APIvemdor, wb111.seacrh_pattern("LocationNumber", 0).get(0).intValue());
				System.out.println(LO_number);
		        String[] parts = vendor.split(",");
		        System.out.println(parts.length);
		  
		    for(int i=0;i<parts.length;i++) {
			  dtcLogin.API_Transmission_vendor(LO_number_API, parts[i]);
			  System.out.println(parts[i]);
			  addEvidence(CurrentState.getDriver(), "API", "yes");
		  }

		 dtcLogin.verify_apple(LO_number,apple_RqID);
		  addEvidence(CurrentState.getDriver(), "Apple request", "yes");

		  dtcLogin.verify_zomato(LO_number,zomato_RqID );
		  addEvidence(CurrentState.getDriver(), "Zomato request", "yes");

		  dtcLogin.verify_here(here_RqID,location_name);
		  addEvidence(CurrentState.getDriver(), "HERE request", "yes");

		  dtcLogin.verify_tomtom(LO_number,tomtom_Request_ID);
		  addEvidence(CurrentState.getDriver(), "tomtom_request", "yes");

	  }
}



