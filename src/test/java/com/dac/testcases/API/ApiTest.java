package com.dac.testcases.API;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.api.ApiBase;
import com.dac.main.api.*;

import io.restassured.RestAssured.*;
import io.restassured.http.Method;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class ApiTest extends ApiBase{
	
	
	ExcelHandler excel = new ExcelHandler();
	String filePath ="./data/API.xlsx";
	
	
	@DataProvider
	public Object[][] APIData() throws Exception
	{
		return excel.getData(filePath, "Sheet1", "API");
	}
	
	
	
	@Test(priority = 1, dataProvider = "APIData")
	public void setRequest(HashMap<String, String> h) {

	
		
		getRequest(h.get("URL"), h.get("Status Code"), h.get("Response"));
	}
	//
	
	
	public void test2() {

		
	}
	
}
