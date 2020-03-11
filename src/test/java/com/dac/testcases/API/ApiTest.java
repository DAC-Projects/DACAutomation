package com.dac.testcases.API;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTest {
	
	@Test
	public void setRequest() {
		RestAssured.baseURI = "http://ldmbluebeta.azurewebsites.net/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"/LocationHistoryGetByLocationID/5e950b02-32ed-47e3-b5f4-77ac8e02af90");
		int StatusCode = response.getStatusCode();
		System.out.println("Response"+response.asString());
	Assert.assertEquals(StatusCode, 200);
	}
	
	
}
