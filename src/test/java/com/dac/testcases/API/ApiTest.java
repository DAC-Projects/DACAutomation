package com.dac.testcases.API;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dac.main.api.ApiBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTest extends ApiBase{
	
	@Test
	public void setRequest() {
//		//RestAssured.baseURI = "http://ldmbluebeta.azurewebsites.net/api";
//		RequestSpecification httpRequest = RestAssured.given();
//		Response response = httpRequest.request(Method.GET,"http://ldmbluebeta.azurewebsites.net/api/LocationHistoryGetByLocationID/5e950b02-32ed-47e3-b5f4-77ac8e02af90");
//		int StatusCode = response.getStatusCode();
//		System.out.println("Response"+response.asString());
//		Assert.assertEquals(StatusCode, 200);
		
		getRequest("http://ldmbluebeta.azurewebsites.net/api/LocationHistoryGetByLocationID/5e950b02-32ed-47e3-b5f4-77ac8e02af90", "200", "");
	}
	
	
}
