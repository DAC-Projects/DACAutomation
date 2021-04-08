package com.dac.testcases.SC;

import java.util.List;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.SA_FrequentKeywords_Page;
import com.dac.main.POM_SC.SC_ContentManagement_Page;
import com.dac.main.POM_SC.SC_SocialPosts_Page;

import resources.BaseClass;
import resources.CurrentState;

public class SC_SocialPostsCreator_Test extends BaseClass {
	String exlScore[];
	List<String> reviewReportData;
	String exportedData=null;
	String newComment=null;
	String postmessage="Cup Cake";
	String commentMessage="Test For Creator ";
	
	@Test(enabled = true)
	public void navigateToSocialPosts() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.navigateToSC_Posts();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Social Posts Page", "yes");
	}
	@Test(enabled = true,dependsOnMethods= {"navigateToSocialPosts"})
	public void addPostComment() throws Exception {
		SC_SocialPosts_Page postpage=new SC_SocialPosts_Page(CurrentState.getDriver());
		newComment=postpage.addPostComment(postmessage, commentMessage);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "New comment Added", "yes");
		Thread.sleep(10000);
		postpage.isCommentSubmitted(newComment);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "New comment submitted", "yes");
	}
	
	@Test(enabled = true,dependsOnMethods= {"addPostComment"})
	public void navigateToSocialContentPage() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.navigateToSC_ContentManagement();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Content Management Page", "yes");
	}
	
	@Test(enabled = true,dependsOnMethods= {"navigateToSocialContentPage"})
	public void verifyIsCommentUnPulbished() throws Exception {
		SC_ContentManagement_Page cm=new SC_ContentManagement_Page(CurrentState.getDriver());
		cm.verifyUnPublishedComments(newComment);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Comment Available as Unpublished", "yes");
	}
	
	
	/*	
	@Test(enabled = true,dependsOnMethods= {"verifyIsCommentUnPulbished"})
	public void verifyIsCommentDeleted() throws Exception {
		SC_ContentManagement_Page cm=new SC_ContentManagement_Page(CurrentState.getDriver());
		cm.verifyDeleteComments(newComment);
//		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Comment Deleted", "yes");
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
	}*/
}
