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

//	@Test
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
@Test
	public void test3() {
	 RestAssured.baseURI ="https://ldmbluebeta.azurewebsites.net"; 
	 RequestSpecification request = RestAssured.given();
	 
	 
	 String payload = "{\r\n" + 
	 		"  \"HoursOfOperation\" : null,\r\n" + 
	 		"  \"ID\" : null,\r\n" + 
	 		"  \"ResellerID\" : \"544da616-517c-4752-bfd9-66d9277859ec\",\r\n" + 
	 		"  \"ExternalReseller\" : true,\r\n" + 
	 		"  \"BusinessUnitID\" : null,\r\n" + 
	 		"  \"AccountID\" : \"a2806bfc-fef4-4fa0-91c6-2f761c3882b1\",\r\n" + 
	 		"  \"LocationNumber\" : null,\r\n" + 
	 		"  \"ClientRefID\" : \"777133\",\r\n" + 
	 		"  \"ParentAccountID\" : null,\r\n" + 
	 		"  \"KeyFields\" : {\r\n" + 
	 		"    \"Account\" : \"9000069502\",\r\n" + 
	 		"    \"ClientName\" : \"Jennifers Testing Account\",\r\n" + 
	 		"    \"Google Preferred Photo\" : \"Cover\"\r\n" + 
	 		"  },\r\n" + 
	 		"  \"OriginalJson\" : null,\r\n" + 
	 		"  \"BusinessStatus\" : 0,\r\n" + 
	 		"  \"BusinessName\" : {\r\n" + 
	 		"    \"Name\" : \"Testing Business\",\r\n" + 
	 		"    \"Type\" : null,\r\n" + 
	 		"    \"ShortName\" : null,\r\n" + 
	 		"    \"LongName\" : \"Testing Business\",\r\n" + 
	 		"    \"Locale\" : 58,\r\n" + 
	 		"    \"DisplayMode\" : null,\r\n" + 
	 		"    \"Description\" : null,\r\n" + 
	 		"    \"ShortDescription\" : null,\r\n" + 
	 		"    \"LongDescription\" : null\r\n" + 
	 		"  },\r\n" + 
	 		"  \"AlternateNames\" : null,\r\n" + 
	 		"  \"MainAddress\" : {\r\n" + 
	 		"    \"StructuredAddress\" : false,\r\n" + 
	 		"    \"Address\" : null,\r\n" + 
	 		"    \"AddressNonStruct_Line1\" : \"424 Penetanguishene Rd\",\r\n" + 
	 		"    \"AddressNonStruct_Line2\" : null,\r\n" + 
	 		"    \"AddressNonStruct_Line3\" : null,\r\n" + 
	 		"    \"AddressNonStruct_Line4\" : null,\r\n" + 
	 		"    \"AddressNonStruct_Line5\" : null,\r\n" + 
	 		"    \"Type\" : 0,\r\n" + 
	 		"    \"Street\" : null,\r\n" + 
	 		"    \"Suite\" : null,\r\n" + 
	 		"    \"Neighborhood\" : null,\r\n" + 
	 		"    \"Locality\" : \"Barrie\",\r\n" + 
	 		"    \"Region\" : \"ON\",\r\n" + 
	 		"    \"RegionName\" : null,\r\n" + 
	 		"    \"RegionCode\" : null,\r\n" + 
	 		"    \"PostalCode\" : \"L4M 0H2\",\r\n" + 
	 		"    \"CountryCode\" : \"CA\",\r\n" + 
	 		"    \"POBox\" : null,\r\n" + 
	 		"    \"Address1\" : \"424 Penetanguishene Rd\",\r\n" + 
	 		"    \"Address2\" : null,\r\n" + 
	 		"    \"DisplayMode\" : {\r\n" + 
	 		"      \"DisplayAllowed\" : true,\r\n" + 
	 		"      \"Preferred\" : false,\r\n" + 
	 		"      \"Priority\" : 0\r\n" + 
	 		"    },\r\n" + 
	 		"   \r\n" + 
	 		"    \"VerificationData\" : null,\r\n" + 
	 		"    \"RawData\" : null\r\n" + 
	 		"  },\r\n" + 
	 		"  \"DisplayPoint\" : null,\r\n" + 
	 		"  \"MainAddressVerified\" : null,\r\n" + 
	 		"  \"DisplayPointVerified\" : null,\r\n" + 
	 		"  \"AdditionalAddresses\" : null,\r\n" + 
	 		"  \"PhoneNumber\" : {\r\n" + 
	 		"    \"DisplayMode\" : null,\r\n" + 
	 		"    \"Number\" : \"+17054319712\",\r\n" + 
	 		"    \"Type\" : 0,\r\n" + 
	 		"    \"Name\" : null,\r\n" + 
	 		"    \"Description\" : null\r\n" + 
	 		"  },\r\n" + 
	 		"  \"AlternatePhoneNumbers\" : null,\r\n" + 
	 		"  \"HomePageURL\" : \"http://www.test.com/business\",\r\n" + 
	 		"  \"SocialMediaURLs\" : [ ],\r\n" + 
	 		"  \"HoursOfOpStruct\" : null,\r\n" + 
	 		"  \"Categories\" : [{\r\n" + 
	 		"    \"DisplayMode\" : null,\r\n" + 
	 		"    \"Locale\" : 58,\r\n" + 
	 		"    \"Name\" : \"Insurance Company\",\r\n" + 
	 		"    \"Type\" : \"DAC Category\",\r\n" + 
	 		"    \"DisplayName\" : null,\r\n" + 
	 		"    \"Priority\" : 1\r\n" + 
	 		"  }],\r\n" + 
	 		"  \"EntryPoints\" : null,\r\n" + 
	 		"  \"Chain\" : null,\r\n" + 
	 		"  \"Description\" : null,\r\n" + 
	 		"  \"Amenities\" : null,\r\n" + 
	 		"  \"PaymentMethods\" : null,\r\n" + 
	 		"  \"Status\" : 0,\r\n" + 
	 		"  \"Verified\" : false,\r\n" + 
	 		"  \"Valid\" : false,\r\n" + 
	 		"  \"VersionCode\" : null,\r\n" + 
	 		"  \"CreatedBy\" : \"hima.dhulipala@gmail.com\",\r\n" + 
	 		"  \"Created\" : \"2017-11-03T16:27:55.8305304Z\",\r\n" + 
	 		"  \"ModifiedBy\" : \"hima.dhulipala@gmail.com\",\r\n" + 
	 		"  \"Modified\" : \"2017-11-03T16:27:58.6703115Z\",\r\n" + 
	 		"  \"DataSource\" : \"CSO\",\r\n" + 
	 		"  \"AdditionalAddressLines\" : null,\r\n" + 
	 		"  \"MainContact\" : {\r\n" + 
	 		"    \"Locale\" : 0,\r\n" + 
	 		"    \"FullName\" : null,\r\n" + 
	 		"    \"FirstName\" : \"Jennifer\",\r\n" + 
	 		"    \"MiddleName\" : null,\r\n" + 
	 		"    \"LastName\" : \"Mann\",\r\n" + 
	 		"    \"Title\" : \"Mrs\",\r\n" + 
	 		"    \"EmailAddress\" : null\r\n" + 
	 		"  },\r\n" + 
	 		"  \"PrimaryEmailAddress\" : \"test@tester.com\",\r\n" + 
	 		"  \"PhotoURLs\" : [ \r\n" + 
	 		"    {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"1\",\r\n" + 
	 		"        \"Type\": 4,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Exterior Photo 1\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"     },\r\n" + 
	 		"     {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"1\",\r\n" + 
	 		"        \"Type\": 5,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Interior Photo 1\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"     },\r\n" + 
	 		"      {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"1\",\r\n" + 
	 		"        \"Type\": 6,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Product Photo 1\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"     },\r\n" + 
	 		"		 {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"4\",\r\n" + 
	 		"        \"Type\": 7,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Photo at Work 4\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"     },           \r\n" + 
	 		"            {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"1\",\r\n" + 
	 		"        \"Type\": 8,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Food and Drink Photo 1\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"        \r\n" + 
	 		"     },           \r\n" + 
	 		"       {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"2\",\r\n" + 
	 		"        \"Type\": 9,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Common Areas 2\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"        \r\n" + 
	 		"     }, {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"1\",\r\n" + 
	 		"        \"Type\": 10,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Room 1\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"        \r\n" + 
	 		"     },       {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"1\",\r\n" + 
	 		"        \"Type\": 11,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Team Photo 1\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"        \r\n" + 
	 		"     },            {\r\n" + 
	 		"\r\n" + 
	 		"        \"Id\": \"1\",\r\n" + 
	 		"        \"Type\": 12,\r\n" + 
	 		"        \"URL\": \"http://www.koloabassfishing.com/bass.png\",\r\n" + 
	 		"        \"CloudinaryURL\": \"https://res.cloudinary.com/cloudasset/image/upload/v1492782679/47njIyhKVSRmj733uok65BkAo2ARdJxYYzniYl47bk2zE61.png\",\r\n" + 
	 		"        \"ThumbnailURL\": null,\r\n" + 
	 		"        \"Name\": \"Menu Photo 1\",\r\n" + 
	 		"        \"Locale\": 0,\r\n" + 
	 		"        \"Description\": null,\r\n" + 
	 		"        \"Error\": null\r\n" + 
	 		"        \r\n" + 
	 		"     }                               \r\n" + 
	 		"     ],\r\n" + 
	 		"  \"AdditionalMediaURLs\" : [ ],\r\n" + 
	 		"  \"Keywords_Specialties\" : null,\r\n" + 
	 		"  \"Credentials_Certifications\" : null,\r\n" + 
	 		"  \"Products\" : null,\r\n" + 
	 		"  \"Services\" : null,\r\n" + 
	 		"  \"Brands\" : null,\r\n" + 
	 		"  \"Year_Founded\" : null,\r\n" + 
	 		"  \"Professional_Associations\" : null,\r\n" + 
	 		"  \"Tags\" : null,\r\n" + 
	 		"  \"Employee_Size\" : null,\r\n" + 
	 		"  \"AreasServed\" : null,\r\n" + 
	 		"  \"Languages\": [\r\n" + 
	 		"            \"af\",\r\n" + 
	 		"            \"bal\",\r\n" + 
	 		"            \"bg\",\r\n" + 
	 		"            \"yue\",\r\n" + 
	 		"            \"fa\",\r\n" + 
	 		"            \"fil\",\r\n" + 
	 		"            \"nl\",\r\n" + 
	 		"            \"gui\",\r\n" + 
	 		"            \"ig\",\r\n" + 
	 		"            \"cmn\",\r\n" + 
	 		"            \"pa\",\r\n" + 
	 		"            \"sd\",\r\n" + 
	 		"            \"sw\",\r\n" + 
	 		"            \"sv\",\r\n" + 
	 		"            \"ta\",\r\n" + 
	 		"            \"te\",\r\n" + 
	 		"            \"tr\"\r\n" + 
	 		"        ],\r\n" + 
	 		"  \"AlternateHomePageURL\" : [ {\r\n" + 
	 		"    \"URL\" : \"http://www.test.com\",\r\n" + 
	 		"    \"Name\" : null,\r\n" + 
	 		"    \"DisplayName\" : null,\r\n" + 
	 		"    \"Description\" : null\r\n" + 
	 		"  } ],\r\n" + 
	 		"  \"VerificationSVCResults\" : null,\r\n" + 
	 		"  \"IsMainAddressTransfered\" : false,\r\n" + 
	 		"   \"CustomObjects\": {\r\n" + 
	 		"            \"GoogleLabels\": {\r\n" + 
	 		"                \"Key\": \"GoogleLabels\",\r\n" + 
	 		"                \"Name\": \"Labels\",\r\n" + 
	 		"                \"Tag\": \"Google\",\r\n" + 
	 		"                \"Type\": \"System.Collections.Generic.Dictionary`2[[System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089],[System.String, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089]], mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089\",\r\n" + 
	 		"                \"Value\": \"{\\\"0\\\":\\\"Test\\\",\\\"1\\\":\\\"Test2\\\"}\"\r\n" + 
	 		"            }\r\n" + 
	 		"        }\r\n" + 
	 		"}";
	 
	 
	 Response response = request.header("Content-Type", "application/x-www-form-urlencoded")
			 .formParam("Location", payload).request().post("/api/LocationSave");
			 
	 
	 String jsonString = response.asString();
	 System.out.println(response.getStatusCode()); 
	 System.out.println(jsonString); 
	// Assert.assertEquals(jsonString.contains("London"), true);

		
	}
	
}
