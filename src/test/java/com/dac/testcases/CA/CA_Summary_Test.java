package com.dac.testcases.CA;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Summary_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CA_Summary_Test extends BaseClass{

                
                static List<Map<String, String>> export;
                static List<String> export1;
                Navigationpage np;
                CA_Summary_Page data;
                
                static List<Double> finalsummaryoverall = new ArrayList<Double>();
                static List<Double> visibilitylist = new ArrayList<Double>();
                static List<Double> accucracylist = new ArrayList<Double>();
                static List<Double> contentanalysislist = new ArrayList<Double>();
                static List<Double> reviewslist = new ArrayList<Double>();
                static List<Double> visibilitypagelist = new ArrayList<Double>();
                static List<Double> accucracypagelist = new ArrayList<Double>();
                static List<Double> contentanalysispagelist = new ArrayList<Double>();
                static List<Double> reviewspagelist = new ArrayList<Double>();
                static List<Double> Overallscore = new ArrayList<Double>();
                static List<Double> visibilityscore = new ArrayList<Double>();
                static List<Double> accuracyscore = new ArrayList<Double>();
                static List<Double> contentanalysisscore = new ArrayList<Double>();
                static List<Double> reviewscore = new ArrayList<Double>();
                static List<Double> summaryoverall = new ArrayList<Double>();
                static List<Double> compscore = new ArrayList<Double>();
                String path = "./downloads/SummaryExport.xlsx";
                static List<Double> overallpercent = new ArrayList<Double>();
                static List<Double> finalpercent = new ArrayList<Double>(); 

                @Test(enabled = true)
                public void navigateToVisibilityPage() throws Exception {
                                np = new Navigationpage(CurrentState.getDriver());
                                np.navigateCA_Visibility();
                                data = new CA_Summary_Page(CurrentState.getDriver());
                                CurrentState.getLogger().log(Status.PASS, "Navigated successfully to CA Visibility page");    
                                      addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes"); 

    //Assert.assertFalse( "sample error", true);
                }
                
                @Test(dependsOnMethods = {"navigateToVisibilityPage"})
                public void getvisbilitypagescore() throws Exception {
                                Thread.sleep(5000);
                                data = new CA_Summary_Page(CurrentState.getDriver());
                                visibilitypagelist =data.getOverviewReport1();
                                addEvidence(CurrentState.getDriver(), "Get Visibility Score", "Yes");
                                System.out.println("visbility report score :" +visibilitypagelist);    
                }
                
                @Test(dependsOnMethods = {"getvisbilitypagescore"})
                public void navigateToAccuracyPage() throws Exception {
                                np = new Navigationpage(CurrentState.getDriver());
                np.navigateCA_Accuracy();
                data = new CA_Summary_Page(CurrentState.getDriver());
                                CurrentState.getLogger().log(Status.PASS,
                                          "Navigated successfully to CA Accuracy page");    
                                      addEvidence(CurrentState.getDriver(), "Navigate to Accuracy page from Dashboard", "yes"); 

     //Assert.assertFalse( "sample error", true);
                }
                
                @Test(dependsOnMethods = {"navigateToAccuracyPage"})
                public void getaccuracypagescore() throws Exception {
                                Thread.sleep(5000);
                                data = new CA_Summary_Page(CurrentState.getDriver());
                                accucracypagelist =data.getOverviewReport1();
                                addEvidence(CurrentState.getDriver(), "Get Visibility Score", "Yes");
                                System.out.println("Accuracy report score :" +accucracypagelist);                              
                }
                
                @Test(dependsOnMethods = {"getaccuracypagescore"})
                public void navigateToCAPage() throws Exception {
                                np = new Navigationpage(CurrentState.getDriver());
                                np.navigateCA_ContentAnalysispage();
                                data = new CA_Summary_Page(CurrentState.getDriver());
//                            new CA_ContentAnalysis_Page(CurrentState.getDriver()).verify_pageloadCompletely(10);
                                CurrentState.getLogger().log(Status.PASS, "Navigated successfully to CA Review page");
                }
                
                @Test(dependsOnMethods = {"navigateToCAPage"})
                public void getcontentanalysispagescore() throws Exception {
                                Thread.sleep(5000);
                                data = new CA_Summary_Page(CurrentState.getDriver());
                                contentanalysispagelist =data.getcaOverviewReport1();
                                addEvidence(CurrentState.getDriver(), "Get Visibility Score", "Yes");
                                System.out.println("Content Analysis report score :" +contentanalysispagelist);  
                }
                
                @Test(dependsOnMethods = {"getcontentanalysispagescore"})
                public void navigateToReviewPage() throws Exception {
    np = new Navigationpage(CurrentState.getDriver());
    np.navigateCA_Reviewpage();
    data = new CA_Summary_Page(CurrentState.getDriver());
    CurrentState.getLogger().log(Status.PASS,
        "Navigated successfully to CA Review page");    
    addEvidence(CurrentState.getDriver(), "Navigate to Review page from Dashboard", "yes"); 
    

}
                
                @Test(dependsOnMethods = {"navigateToReviewPage"})
                public void getreviewspagescore() throws Exception {
                                Thread.sleep(5000);
                                data = new CA_Summary_Page(CurrentState.getDriver());
                                reviewspagelist =data.getreviewOverviewReport1();
                                addEvidence(CurrentState.getDriver(), "Get Visibility Score", "Yes");
                                System.out.println("Review report score :" +reviewspagelist);
                }
                
                @Test(dependsOnMethods = {"getreviewspagescore"})
                public void navigateToSummaryPage() throws Exception {
                                np = new Navigationpage(CurrentState.getDriver());
                np.navigateCA_Summarypage();
                                CurrentState.getLogger().log(Status.PASS,
                                          "Navigated successfully to CA Summary page");    
                                      addEvidence(CurrentState.getDriver(), "Navigate to Summary page from Dashboard", "yes"); 
                }
                
                @Test(dependsOnMethods = {"navigateToSummaryPage"})
                public void comparesummaryscorewithallreports() throws Exception {
                                try {
                                CA_Summary_Page s = new CA_Summary_Page(CurrentState.getDriver());
                                Thread.sleep(5000);
                                visibilitylist =s.getvacrscore(1);
                                addEvidence(CurrentState.getDriver(), "Get Visibility Score", "Yes");
                                Assert.assertEquals(visibilitylist, visibilitypagelist);
                                accucracylist = s.getvacrscore(2);
                                addEvidence(CurrentState.getDriver(), "Get Accuracy Score", "Yes");
                                Assert.assertEquals(accucracylist, accucracypagelist);
                                contentanalysislist = s.getvacrscore(4);
                                addEvidence(CurrentState.getDriver(), "Get Content Analysis Score", "Yes");
                                Assert.assertEquals(contentanalysislist, contentanalysispagelist);
                                reviewslist = s.getvacrscore(3);
                                addEvidence(CurrentState.getDriver(), "Get Content Analysis Score", "Yes");
                                Assert.assertEquals(reviewslist, reviewspagelist , "Data Matches");
                                }catch(Exception e) {
                                                e.printStackTrace();
                                }                              
                }
                
                @Test(dependsOnMethods = { "navigateToSummaryPage" }, groups= {"smoke"}, description = "Verify Summary page loads after filter applied")
                public void verifyFilteringReportsSummary() throws Exception {try {         
                                int count = 1;
                                ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "CA_FIL"); wb.deleteEmptyRows();
                                CA_Summary_Page s = new CA_Summary_Page(CurrentState.getDriver());
                                for(int i=1;i<=wb.getRowCount();i++) {
                                                System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
                                                if(i>1) CurrentState.getDriver().navigate().refresh();
                                                s.waitUntilLoad(CurrentState.getDriver());
                                                
                                                
                                                String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
                                                String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
                                                String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
                                                String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
                                                
                                                s.applyFilter(CountryCode, State, City, Location);
                                                System.out.println(CountryCode+", "+State+", "+City+", "+Location);
                                                s.clickApplyFilterBTN();
                                                BaseClass.addEvidence(CurrentState.getDriver(),
                                                                                "Applied global filter: "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
                                                
                                                Thread.sleep(4000);
                                                System.out.println("-------------- Scenarios : "+ count++ + "Ends --------------------");
                                                
                                }
                }catch(Exception e) {
                                e.printStackTrace();
                                //Assert.fail("");
                }}
                
                @SuppressWarnings("unchecked")
                  @Test(dependsOnMethods = { "verifyFilteringReportsSummary"}, groups= {"smoke"}, description = "Test for overview export and export verification")
                                public void verifyOverviewReportnExportSummary() throws Exception {
                                                data = new CA_Summary_Page(CurrentState.getDriver());
                                                export = data.getExportData();
                                                data.compareExprttoOvervw(export, data.getOverviewReport());   
                                addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes"); 
                                }
                
                  @Test(dependsOnMethods = { "comparesummaryscorewithallreports"}, groups= {"smoke"}, description = "Test for overview export and export verification")
                                public void verifyOverviewReportnExportSummary1() throws Exception {
                                                data = new CA_Summary_Page(CurrentState.getDriver());
                                                data.exportaccuracyReport();
                                                Thread.sleep(5000);
                                                summaryoverall = data.getOverviewReport1();
                                                Thread.sleep(5000);
                                                Overallscore = data.GetSummaryDataUsingColName(path, "Overall");
                                                Thread.sleep(5000);
                                                Assert.assertEquals(Overallscore, summaryoverall);
                                                visibilityscore = data.GetSummaryDataUsingColName(path, "Visibility");
                                                Thread.sleep(5000);
                                                Assert.assertEquals(visibilityscore, visibilitylist);
                                                accuracyscore = data.GetSummaryDataUsingColName(path, "Accuracy");
                                                Thread.sleep(5000);
                                                Assert.assertEquals(accuracyscore, accucracylist);
                                                contentanalysisscore = data.GetSummaryDataUsingColName(path, "Content Analysis");
                                                Thread.sleep(5000);
                                                Assert.assertEquals(contentanalysisscore, contentanalysislist);
                                                reviewscore = data.GetSummaryDataUsingColName(path, "Reviews");
                                                Assert.assertEquals(reviewscore, reviewslist);
                                                addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes"); 
                                }
                  
                  public List<Double> getreviewpercentage() {
                                  List<Double> xyz = new ArrayList<Double>();
                                  for(int i = 0; i<reviewslist.size(); i++) {
                                                                double a = ((reviewslist.get(i))/5)*100;
                 xyz.add(a);
                                  }
                                  System.out.println(xyz);
                                  return xyz;
                  }
                  
                  @Test(dependsOnMethods = {"verifyOverviewReportnExportSummary1"})
                                public void getscoreofallreports() throws Exception {
                                                try {
                                                CA_Summary_Page s = new CA_Summary_Page(CurrentState.getDriver());
                                                Thread.sleep(5000);
                                                overallpercent = getreviewpercentage();
                                                                for(int i = 0; i<reviewslist.size(); i++) {
                                                                                double x= (visibilitylist.get(i)+accucracylist.get(i)+contentanalysislist.get(i)+overallpercent.get(i))/4;
                                                                                                BigDecimal bd = BigDecimal.valueOf(x);
                                                                                                bd = bd.setScale(1, RoundingMode.HALF_UP);
                                                                                                x = bd.doubleValue();
                                                                                System.out.println(x);
                                                                                finalpercent.add(x);                                                                        
                                                                }
                                                                System.out.println(finalpercent);
                                                                for(int i=0; i<summaryoverall.size();i++){                                                                                
                                                                                double ab = summaryoverall.get(i);
                                                                                BigDecimal bd = BigDecimal.valueOf(ab);
                                                                                bd = bd.setScale(1,RoundingMode.HALF_UP);
                                                                                ab = bd.doubleValue();
                                                                                finalsummaryoverall.add(ab);
                                                                }
                                                                System.out.println(finalsummaryoverall);
                                                                Assert.assertEquals(finalpercent, finalsummaryoverall);
                                                                
                                                }catch(Exception e) {
                                                                e.printStackTrace();
                                                }                              
                                }
}
