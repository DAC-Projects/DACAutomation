package com.dac.testcases.API;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.api.ApiBase;
import com.dac.main.api.ExcelHandler;

public class DataAnywhereTest extends ApiBase {
	
	ExcelHandler excel = new ExcelHandler();
	String filePath ="./data/API.xlsx";
	


	@DataProvider
	public Object[][] AccountsAPI() throws Exception
	{
		return excel.getData(filePath, "DataAnywhere", "AccountsAPI");
	}	

	@Test(priority = 1, dataProvider = "AccountsAPI")
	public void GetAccountByAccountID(HashMap<String, String> h) {

		getRequest(h.get("TestName"),h.get("Header"),h.get("Parameter"),h.get("URL"), h.get("Status Code"), h.get("Response"));
		
	}
	
	@DataProvider
	public Object[][] LocationRead() throws Exception
	{
		return excel.getData(filePath, "DataAnywhere", "LocationReadAPI");
	}	

	@Test(priority = 2, dataProvider = "LocationRead")
	public void LocationRead(HashMap<String, String> h) {

		getRequest(h.get("TestName"),h.get("Header"),h.get("Parameter"),h.get("URL"), h.get("Status Code"), h.get("Response"));
		
	}

	@DataProvider
	public Object[][] LocationWrite() throws Exception
	{
		return excel.getData(filePath, "DataAnywhere", "LocationWriteAPI");
	}	

	@Test(priority = 3, dataProvider = "LocationWrite")
	public void LocationWrite(HashMap<String, String> h) {

		getRequest(h.get("TestName"),h.get("Header"),h.get("Parameter"),h.get("URL"), h.get("Status Code"), h.get("Response"));
		
	}	
	
	
}
