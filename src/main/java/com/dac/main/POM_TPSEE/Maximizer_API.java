package com.dac.main.POM_TPSEE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Maximizer_API {
	
	
	static String result;
	
	
	@SuppressWarnings("deprecation")
	public static void PostUpdateDetails() throws IOException {		
		String URI = "https://ldmbluebeta.azurewebsites.net/api/LocationSave";
		result = new String(Files.readAllBytes(Paths.get("./data/Update_beta.json")));
		System.out.println(result);
		String response = RestAssured.given().contentType(ContentType.URLENC).with().parameter("location", result)
				.post(URI).then().extract().response().body().prettyPrint();
		System.out.println(response);
	}
	
	@SuppressWarnings("deprecation")
	public static void PostRevertDetails() throws IOException, ParseException {		
		String URI = "https://ldmbluebeta.azurewebsites.net/api/LocationSave";
		result = new String(Files.readAllBytes(Paths.get("C:/Users/avinash/Desktop/Maximizer JSON/Revert_beta.json")));
		System.out.println(result);
		String response = RestAssured.given().contentType(ContentType.URLENC).with().parameter("location", result)
				.post(URI).then().extract().response().body().prettyPrint();
		System.out.println(response);
	}
	

}
