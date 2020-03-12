package com.dac.main.api;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {
	

	
	public void getRequest(String URL, String Statuscode, String Response) {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,URL);
		int ActualCode = response.getStatusCode();
		System.out.println("Response"+response.asString());
		Assert.assertEquals(Integer.toString(ActualCode),Statuscode , "Status code not mached");
		Assert.assertEquals(response.asString(), Response, "Status response not mached");
	}
	

}