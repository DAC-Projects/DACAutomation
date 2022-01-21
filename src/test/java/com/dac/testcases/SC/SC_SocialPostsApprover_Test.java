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

public class SC_SocialPostsApprover_Test extends BaseClass {
	String exlScore[];
	List<String> reviewReportData;
	String exportedData=null;
	String comment=null;
	String postmessage="just relax with a pizza";
	String commentMessage="Test For Approver ";
	String Publishing="*Publishing...*";
	Navigationpage np;
	SC_SocialPosts_Page postpage;
	SC_ContentManagement_Page cm;
	
	@Test(enabled = true,description = "TC: Navigate To Social Posts")
	public void navigateToSocialPosts() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		np.navigateToSC_Posts();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Social Posts Page", "yes");
	}
	@Test(enabled = true,dependsOnMethods= {"navigateToSocialPosts"},description = "TC: addPost_VerifyComment_Published")
	public void addPost_VerifyComment_Published() throws Exception {
		
		np=new Navigationpage(CurrentState.getDriver());
		postpage=new SC_SocialPosts_Page(CurrentState.getDriver());
		cm=new SC_ContentManagement_Page(CurrentState.getDriver());
		
		//Navigate to Social Posts Page
		np.navigateToSC_Posts();
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Social Posts Page", "yes");
		
		//Add Comment to Post
		comment=postpage.addPostComment(postmessage, commentMessage);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "New comment Added", "yes");
		
		//Verify the Status
		postpage.isStatusDisplayed(comment,Publishing);
		addEvidence(CurrentState.getDriver(), "Status is "+Publishing, "yes");
		Thread.sleep(10000);
		
		//Wait for Submit 
		postpage.isCommentSubmitted(comment);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "New comment submitted", "yes");
		
		//Navigate to Content Management Page
		np.navigateToSC_ContentManagement();
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Content Management Page", "yes");
		
		//Verify the comment available in Published 
		cm.verifyPublishedComments(comment);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Comment Published", "yes");
	}
	
	
		
	//Edit Post comment
	@Test(enabled = true,dependsOnMethods= {"addPost_VerifyComment_Published"}, description="TC : SC_SocialPosts_Page")
	public void editPostComment_verfiy() throws Exception {
		postpage=new SC_SocialPosts_Page(CurrentState.getDriver());
		
		//Navigate to Social Posts Page
		np.navigateToSC_Posts();
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Social Posts Page", "yes");
		
		//Edit the comment
		System.out.println(comment+" << New comment for edit");
		comment=postpage.editPostComment(comment);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Comment Edited", "yes");
		
		//Verify the updated comment is available in Published 
		Thread.sleep(10000);
		postpage.isCommentSubmitted(comment);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Comment Updated and submitted", "yes");
		
		//Navigate to Content Management Page
		np.navigateToSC_ContentManagement();
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Content Management Page", "yes");
				
		//Verify the comment available in Published 
		cm.verifyPublishedComments(comment);
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Comment Edited and Published", "yes");
	}
	
	//Delete Comment from post and Verify
	@Test(enabled = true,dependsOnMethods= {"editPostComment_verfiy"}, description="TC : Delete Comment")
	public void verifyIsCommentDeleted() throws Exception {
		SC_ContentManagement_Page cm=new SC_ContentManagement_Page(CurrentState.getDriver());
		cm.verifyDeleteComments(comment);
//		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Comment Deleted", "yes");
	}
	/*	
	
	

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
