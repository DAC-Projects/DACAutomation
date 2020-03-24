package com.dac.main.api;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {
	
JsonCompare jsc = new JsonCompare();
ATUReport atu = new ATUReport();
	
	public void getRequest(String TestName, String URL, String Statuscode, String Response) {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,URL);
		int ActualCode = response.getStatusCode();
		System.out.println("Response"+response.asString());
		verifyResposneCode(TestName,Integer.toString(ActualCode), Statuscode);
	//	Assert.assertEquals(Integer.toString(ActualCode),Statuscode , "Status code not mached");
	//	Assert.assertEquals(response.asString(), Response, "Status response not mached");
		jsc.JSONCompare(Response, response.asString(), TestName);
	}
	
	public void getRequest(String TestName, String header, String parameters,String URL, String Statuscode, String Response) {
		String param[] = parameters.split(",");
		String headers[] = header.split(",");
		
		RestAssured.baseURI =URL; 
		RequestSpecification request = RestAssured.given();

		Response response = request
				.header(headers[0], headers[1])				
				.queryParam(param[0], param[1]) 
				.get("");

		String jsonString = response.asString();
		String responseCode = Integer.toString(response.getStatusCode());


		verifyResposneCode(TestName, responseCode,  Statuscode);
		verifyResposnedata(TestName, jsonString, Response);
	}
	
	
	public void verifyResposneCode(String TestName,String actual, String expected) {
		
		if(actual.equals(expected))
			atu.atuLogAsPass(TestName+" Resposne code  Passed", expected, actual);
		else 
			atu.atuLogAsFail(TestName+"Resposne code  Failed", expected, actual);
		
	}
	
public void verifyResposnedata(String TestName,String actual, String expected) {
		
	jsc.JSONCompare(expected, actual, TestName);
		
	}

}