package com.dac.testcases.DTC;


import org.testng.annotations.Test;
import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_DTC.DTC_Transmission_Setup;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Vendor_Transmission_Setup extends BaseClass{
	String url="https://beta-dtc-web.azurewebsites.net/";
	@Test(groups = { "Setup" })	
  public void launchBrowser() throws Exception {
		CurrentState.getDriver().get(url);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	   dtcLogin.pressYesKey();
	}
	@Test(dependsOnMethods = { "launchBrowser"},groups = { "AddnewField" })
	 public void AddingNewfiled() throws Exception {
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(CurrentState.getDriver());
		int count = 1;
		String format_field=null;
		ExcelHandler wb = new ExcelHandler("./data/DTC.xlsx", "Field");
		String ve_name = wb.getCellValue(1, wb.seacrh_pattern("ve_name", 0).get(0).intValue());
		String se_name = wb.getCellValue(1, wb.seacrh_pattern("se_name", 0).get(0).intValue());
		vendor.setup(ve_name, se_name);	
		wb.deleteEmptyRows();
		for(int i=1;i<=wb.getRowCount();i++) {
			format_field= wb.getCellValue(i, wb.seacrh_pattern("format_field", 0).get(0).intValue());
	
	if(format_field.equals("false")) {
		int a=wb.getRowCount();
		System.out.println(a);
		System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
		String V_fieldname = wb.getCellValue(i, wb.seacrh_pattern("Vendor Field Name", 0).get(0).intValue());
		String D_fieldname = wb.getCellValue(i, wb.seacrh_pattern("DTC Field Name", 0).get(0).intValue());
		System.out.println(V_fieldname);
		vendor.Adding_New_filed(V_fieldname,D_fieldname);
		addEvidence(CurrentState.getDriver(), "Adding Hours", "yes");
		count++;
		}
	else {
		int a=wb.getRowCount();
		System.out.println(a);
		vendor.setup("Facebook", "CA Manual setup");
		System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
		String V_fieldname = wb.getCellValue(i, wb.seacrh_pattern("Vendor Field Name", 0).get(0).intValue());
		String D_fieldname = wb.getCellValue(i, wb.seacrh_pattern("DTC Field Name", 0).get(0).intValue());
		String Format = wb.getCellValue(i, wb.seacrh_pattern("Format", 0).get(0).intValue());
		System.out.println(V_fieldname);
		vendor.Adding_New_filed_format("Facebook","CA Manual setup",V_fieldname,D_fieldname,Format);
		addEvidence(CurrentState.getDriver(), "Adding Hours", "yes");
		count++;
	}}
		vendor.save();
		addEvidence(CurrentState.getDriver(), "save", "yes");
}
	@Test(dependsOnMethods = { "launchBrowser"},groups = {"ManualTransmission"})
	 public void Transmission() throws Throwable {
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(CurrentState.getDriver());
		ExcelHandler wb2= new ExcelHandler("./data/DTC.xlsx", "Field");
		String location_number = wb2.getCellValue(1, wb2.seacrh_pattern("location_number", 0).get(0).intValue());
		vendor.transmission(location_number);
		addEvidence(CurrentState.getDriver(), "Manual transmission", "yes");
	}
	@Test( dependsOnMethods = { "launchBrowser"},groups = { "VerifyManualTransmission" })
	public void Verification() throws Throwable{
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(CurrentState.getDriver());
		ExcelHandler wb2= new ExcelHandler("./data/DTC.xlsx", "Field");
		String request_ID= wb2.getCellValue(1, wb2.seacrh_pattern("request_ID", 0).get(0).intValue());
		String V_fieldname = wb2.getCellValue(1, wb2.seacrh_pattern("Vendor Field Name", 0).get(0).intValue());
		vendor.verif_Status_complete(request_ID,"CAManualsetup",V_fieldname);
		addEvidence(CurrentState.getDriver(), "Manual transmission Complete", "yes");
	}
}

	