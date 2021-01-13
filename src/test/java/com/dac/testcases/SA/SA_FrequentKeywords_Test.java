package com.dac.testcases.SA;

import java.util.List;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.SA_FrequentKeywords_Page;
import resources.BaseClass;
import resources.CurrentState;

public class SA_FrequentKeywords_Test extends BaseClass {
	String exlScore[];
	List<String> reviewReportData;
	String exportedData=null;
	
	@Test(enabled = true)
	public void navigateToFrequentKeywords() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_FrequentKeywords();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Frequent Keywords Page", "yes");
	}
	
	@Test(enabled = true, dependsOnMethods= {"navigateToFrequentKeywords"})
	public void applyDatefilter() throws Exception {
		SA_FrequentKeywords_Page fk = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		fk.addFromDate("01/01/2020");
		Thread.sleep(5000);
		addEvidence(CurrentState.getDriver(), "Date Filter Applied", "yes");
	}
	
	
	@Test(enabled = true, dependsOnMethods= {"applyDatefilter"})
	public void ExportFrequentKeywords() throws Exception {
		
		SA_FrequentKeywords_Page s = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		s.clickExportBTN();
		Thread.sleep(3000);
		s.getExportedData();

		addEvidence(CurrentState.getDriver(), "Export Data Verification Completed", "yes");
	}
	
	@Test(enabled = true, dependsOnMethods= {"ExportFrequentKeywords"})
	public void verifyScore() throws Exception {
		SA_FrequentKeywords_Page fk = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		fk.fkScoreVerification();
		addEvidence(CurrentState.getDriver(), "Score Verification", "yes");
	}
	
	@Test(enabled = true, dependsOnMethods= {"verifyScore"})
	public void MouseHoverGetScore() throws Exception {
		
		SA_FrequentKeywords_Page s = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		s.getScoreMouseHover();

		addEvidence(CurrentState.getDriver(), "Get Score using Mouse Hover Verification Completed", "yes");
	}
	
	@Test(enabled = true, dependsOnMethods= {"MouseHoverGetScore"})
	public void VerifyCountOnReviewFeed() throws Exception {
		
		SA_FrequentKeywords_Page s = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		s.VerifyReviewCount();

		addEvidence(CurrentState.getDriver(), "Review Count Verification Completed", "yes");
	}
}
