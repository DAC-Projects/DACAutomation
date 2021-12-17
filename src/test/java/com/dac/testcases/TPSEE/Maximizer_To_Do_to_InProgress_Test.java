package com.dac.testcases.TPSEE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.Maximizer_Page;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import resources.BaseClass;
import resources.CurrentState;

public class Maximizer_To_Do_to_InProgress_Test extends BaseClass {
	
	Navigationpage np;
	Maximizer_Page data;
	
	/**
	 * Test to navigate to Maximizer Page
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to navigate to Maximizer")
	public void NavigateToMaximizer() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToMaximizer();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Maximizer", "yes");
	}

	@Test(priority = 2, description = "Test to move reco from To_Do tab to In_Progress tab")
	public void Move_Reco() throws Exception {
		data = new Maximizer_Page(CurrentState.getDriver());
		data.GetRecoTypeUsingColName("./data/Filter.xlsx", "Maximizer_LPAD_Reco", "Recommendation Type");
		Thread.sleep(3000);
		data.To_Do_to_InProgress_Reco();
	}
	
	@Test(priority = 3, description = "Trigger API to move recommendations to Completed")
	public void postUpdatedetails() throws IOException, ParseException {		
		String URI = "https://ldmbluebeta.azurewebsites.net/api/LocationSave";
		String result = new String(Files.readAllBytes(Paths.get("./data/Update_beta.json")));
		System.out.println(result);
		RestAssured.given().contentType(ContentType.URLENC).with().parameter("location", result)
				.post(URI);
	}
	
	@Test(priority = 4, description = "Test to verify that recos moved to Completed tab")
	public void VerifyCompletedTab() throws Exception {
		data = new Maximizer_Page(CurrentState.getDriver());
		CurrentState.getDriver().navigate().refresh();
		Thread.sleep(4000);
		data.clickRemindLater();
		data.clickCompletedTab();
		data.GetRecoTypeUsingColName("./data/Filter.xlsx", "Maximizer_LPAD_Reco", "Recommendation Type");
		data.MoveRecotoCompletedTab();
	}
	
	@Test(priority = 5, description = "Test to revert the changes done to reco")
	public void postRevertdetails() throws IOException, ParseException {		
		String URI = "https://ldmbluebeta.azurewebsites.net/api/LocationSave";
		String result = new String(Files.readAllBytes(Paths.get("./data/Revert_beta.json")));
		System.out.println(result);
		RestAssured.given().contentType(ContentType.URLENC).with().parameter("location", result)
				.post(URI);
	}
	
	@Test(priority = 6, description = "Test to verify reverted changes in TODO Tab")
	public void VerifyToDoTab() throws Exception {
		data = new Maximizer_Page(CurrentState.getDriver());
		CurrentState.getDriver().navigate().refresh();
		Thread.sleep(4000);
		data.clickRemindLater();
		data.GetRecoTypeUsingColName("./data/Filter.xlsx", "Maximizer_LPAD_Reco", "Recommendation Type");
		data.MoveRecotoCompletedTab();
	}
	
}
