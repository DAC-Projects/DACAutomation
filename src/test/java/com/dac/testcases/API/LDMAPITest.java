package com.dac.testcases.API;

import org.testng.annotations.*;

import com.dac.main.api.ApiBase;
import com.dac.main.api.ExcelHandler;
import com.dac.main.api.JsonCompare;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LDMAPITest extends ApiBase {


	ExcelHandler excel = new ExcelHandler();
	String filePath ="./data/API.xlsx";
	


	@DataProvider
	public Object[][] APIDataGetLocationById() throws Exception
	{
		return excel.getData(filePath, "Sheet2", "APIDataGetLocationById");
	}	

	@DataProvider
	public Object[][] APIDataGetLocationBySnapShot() throws Exception
	{
		return excel.getData(filePath, "Sheet2", "APIDataGetLocationBySnapShot");

	}@DataProvider
	public Object[][] APIDataGetLocationByAccountID() throws Exception
	{
		return excel.getData(filePath, "Sheet2", "APIDataGetLocationByAccountID");
	}



	@Test(priority = 1, dataProvider = "APIDataGetLocationById")
	public void GetLocationById(HashMap<String, String> h) {
		
		getRequest(h.get("TestName"),h.get("URL"), h.get("Status Code"), h.get("Response"));
	}

	@Test(priority = 2, dataProvider = "APIDataGetLocationBySnapShot")
	public void GetLocationBySnapShot(HashMap<String, String> h) {

		getRequest(h.get("TestName"),h.get("URL"), h.get("Status Code"), h.get("Response"));
	}
	
	@Test(priority = 3, dataProvider = "APIDataGetLocationByAccountID")
	public void GetLocationByAccountID(HashMap<String, String> h) {

		getRequest(h.get("TestName"),h.get("Header"),h.get("Parameter"),h.get("URL"), h.get("Status Code"), h.get("Response"));
		
	}

}
