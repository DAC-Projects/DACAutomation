package com.dac.testcases.DTC;
import org.testng.annotations.Test;
import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_DTC.DTC_Transmission_Setup;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class API_multiple extends BaseClass{
	
	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test(groups = { "Setup" })
  public void launchBrowser() throws Exception {
	CurrentState.getDriver().get(url);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	   dtcLogin.pressYesKey();
	}

	@Test(dependsOnMethods = { "launchBrowser"},groups = { "Multiple_AddnewField" })
	 public void AddindNewfiled() throws Exception {
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(CurrentState.getDriver());
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/DTC.xlsx", "Field");
		String ve_name = wb.getCellValue(1, wb.seacrh_pattern("ve_name", 0).get(0).intValue());
		String se_name = wb.getCellValue(1, wb.seacrh_pattern("se_name", 0).get(0).intValue());
		vendor.setup(ve_name, se_name);		wb.deleteEmptyRows();
		for(int i=1;i<=wb.getRowCount();i++) {
		int a=wb.getRowCount();
		System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
		String V_fieldname = wb.getCellValue(i, wb.seacrh_pattern("Vendor Field Name", 0).get(0).intValue());
		String D_fieldname = wb.getCellValue(i, wb.seacrh_pattern("DTC Field Name", 0).get(0).intValue());
		String divider = wb.getCellValue(i, wb.seacrh_pattern("Divider", 0).get(0).intValue());
		String Format= wb.getCellValue(i, wb.seacrh_pattern("Format", 0).get(0).intValue());
		vendor.Adding_New_filed_multiple(V_fieldname,D_fieldname,divider);
		vendor.click(count,Format);
		addEvidence(CurrentState.getDriver(), "Adding Hours", "yes");
		count++;
	}
	    vendor.multi_save(count);
		vendor.save();
		addEvidence(CurrentState.getDriver(), "save", "yes");
		}

	@Test(dependsOnMethods = { "launchBrowser"},groups = { "APITransmission" })
	public void Transmission() throws Throwable {
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(CurrentState.getDriver());
		ExcelHandler wb2= new ExcelHandler("./data/DTC.xlsx", "Field");
		String location_number = wb2.getCellValue(1, wb2.seacrh_pattern("location_number", 0).get(0).intValue());
		String vendor_name = wb2.getCellValue(1, wb2.seacrh_pattern("vendor", 0).get(0).intValue());
		String addefield = wb2.getCellValue(1, wb2.seacrh_pattern("Vendor Field Name", 0).get(0).intValue());
		String addedfiledname = wb2.getCellValue(1, wb2.seacrh_pattern("DTC Field Name", 0).get(0).intValue());
		vendor.API_Transmission_vendor(location_number, vendor_name, addefield, addedfiledname);
		addEvidence(CurrentState.getDriver(), "Manual transmission Complete", "yes");
		
	}
}

	
	
	
	

	