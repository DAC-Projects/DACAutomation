package com.dac.testcases.API;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.api.ApiBase;
import com.dac.main.api.*;

import io.restassured.RestAssured;
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


//	@DataProvider
	public Object[][] APIData() throws Exception
	{
		return excel.getData(filePath, "Sheet1", "API");
	}



//		@Test(priority = 1, dataProvider = "APIData")
	public void setRequest(HashMap<String, String> h) {



		getRequest(h.get("TestName"),h.get("URL"), h.get("Status Code"), h.get("Response"));
	}
	//

	@Test
	public void test2() {

		RestAssured.baseURI ="https://dataanywherebeta.azure-api.net/locations-api/locations"; 
		RequestSpecification request = RestAssured.given();

		Response response = request
				.header("Ocp-Apim-Subscription-Key", "FCFE91296EE4492BA5DDAC6143D8F486")
				
				.queryParam("AccountID", "7810d7a0-fec1-4276-8398-d38964ddcfb9") 
//				.queryParam("AccountID", "d43c73b4-8fac-41d1-a897-d44b1d3e6e20") 
//				.queryParam("ClientRefID", "9130101") 
//				.queryParam("LocationID", "5e950b02-32ed-47e3-b5f4-77ac8e02af90") 
				.get("");

		// Response response =  request.get();

		String jsonString = response.asString();
		System.out.println(response.getStatusCode()); 
			 System.err.println(jsonString);
		// Assert.assertEquals(jsonString.contains("London"), true);


	}
//@Test
	public void test3() {
	 RestAssured.baseURI ="https://samples.openweathermap.org/data/2.5/"; 
	 RequestSpecification request = RestAssured.given();
	 
	 Response response = request.queryParam("q", "London,UK") 
	                    .queryParam("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8") 
	                    .get("/weather");
	 
	 String jsonString = response.asString();
	 System.out.println(response.getStatusCode()); 
	 Assert.assertEquals(jsonString.contains("London"), true);

		
	}
	
}
