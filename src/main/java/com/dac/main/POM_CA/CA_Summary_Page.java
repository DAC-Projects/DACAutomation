package com.dac.main.POM_CA;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.CurrentState;
import resources.ExcelHandler;

public class CA_Summary_Page extends CA_abstractMethods{

                

                WebDriver driver;
                Actions action;
                WebDriverWait wait;
                ArrayList<Double> list2 = new ArrayList<>();
                

                public CA_Summary_Page(WebDriver driver) {

                                super(driver);
                                this.driver = driver;
                                wait = new WebDriverWait(driver, 35);
                                action = new Actions(driver);
                                PageFactory.initElements(driver, this);
                }
                
                @FindBy(id="myGroups")
                private WebElement filterGroup;
                
                @FindBy(xpath="//*[@class='menu transition hidden']")
                private WebElement filterDropDown;
                
                @FindBy(xpath="(//*[@id='divCISummary']/h1)")
                private WebElement CATitleContent;
                
                // Global Filter locators

                                @FindBy(css = "div.ui.fluid.search.selection.dropdown.myList")
                                private WebElement FilterCountry;

                                @FindBy(css = "div.ui.fluid.search.selection.dropdown.myList1")
                                private WebElement FilterState;

                                @FindBy(css = "div.ui.fluid.search.selection.dropdown.myList2")
                                private WebElement FilterCity;

                                @FindBy(css = "div.ui.fluid.search.selection.dropdown.myList3")
                                private WebElement Filterlocation;

                                @FindBy(css = "button#apply_filter")
                                private WebElement Apply_filter;
                                
                // export button
                @FindBy(css = "button#btnExport>span")
                private WebElement exportBtn;
                
                // overview report
                @FindBy(css = "div#compIntOverviewContainer")
                private WebElement overviewReport;
                
                @FindBy(xpath="//*[@id='compIntOverviewContainer']//div[@class='overviewSubContainer div-table-cell ng-scope']")
                private List<WebElement> vendorscore;
                
                @FindBy(xpath="//*[@id='compIntOverviewContainer']//div[@class='progress'][1]")
                private List<WebElement> visibilityscore;
                
                @FindBy(xpath="//*[@id='compIntOverviewContainer']//div[@class='progress'][2]")
                private WebElement accuracyscore;
                
                @FindBy(xpath="//*[@id='compIntOverviewContainer']//div[@class='progress'][3]")
                private WebElement reviewscore;
                
                @FindBy(xpath="//*[@id='compIntOverviewContainer']//div[@class='progress'][4]")
                private WebElement contentscore;
                
                // section of overview report
                @FindBy(xpath = "//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')]")
                private List<WebElement> competitors;

                String xpathCompetitors = "(//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')])";
                String compName = ".//div[starts-with(@class, 'competitorName')]";
                String compScore = ".//div[starts-with(@class, 'competitorScore')]";
                
                // site table
                @FindBy(css = "table#compIntAccuracySitesTable")
                private WebElement siteTable;

                List<WebElement> columns;
                List<WebElement> rows;


                public void exportaccuracyReport() throws InterruptedException, FileNotFoundException, IOException {
                                waitForElement(overviewReport, 10);
                                waitForElement(exportBtn, 10);
                                scrollByElement(exportBtn);
                                download(CurrentState.getBrowser(), exportBtn, 20);
                                convertExports(getLastModifiedFile(Exportpath), SummaryExport);
                }


                
                @Override
                public List<Map<String, String>> getOverviewReport() {
                                // TODO Auto-generated method stub
                                waitForElement(overviewReport, 10);
                                scrollByElement(overviewReport);
                                Map<String, String> kMap;
                                List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
                                for (int i = 1; i <= competitors.size(); i++) {
                                                WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
                                                // ovrwRprtData.put("Score", s.findElement(By.xpath(compScore)).getText());
                                                kMap = new HashMap<String, String>();
                                                kMap.put("compName", s.findElement(By.xpath(compName)).getText());
                                                kMap.put("score", s.findElement(By.xpath(compScore)).getText());
                                                System.out.format("%10s%10s", s.findElement(By.xpath(compName)).getText(),
                                                                                s.findElement(By.xpath(compScore)).getText());
                                                System.out.println("");
                                                ovrwRprtData.add(kMap);
                                }
                                return ovrwRprtData;
                }
                
                

                
                public List<Double>getvacrscore(int colcount){
                                waitForElement(overviewReport, 10);
                                scrollByElement(overviewReport);
                                List<Double> visibilitylist = new ArrayList<Double>();
                                String a = null;
                                String x = null;
                                double y = 0;
                                System.out.println(vendorscore.size());
                                for (int i = 1; i <= vendorscore.size()/2; i++) {        
                                                WebElement     s = driver.findElement(By.xpath (("//*[@id='compIntOverviewContainer']//div[@class='overviewSubContainer div-table-cell ng-scope'][" + i + "]//div[@class='progress']["+ colcount +"]")));
                                                a = s.getAttribute("data-percent");
                                                if(a.contains("%")){
                                                                x =a.replace("%", "").trim();
                                                                y=Double.parseDouble(x);
                                                                System.out.println(y);
                                                                }else {
                                                                                y=Double.parseDouble(a);
                                                                }
                                                BigDecimal bd = BigDecimal.valueOf(y);
            bd = bd.setScale(0, RoundingMode.HALF_UP);
           double ax = bd.doubleValue();
           System.out.println(ax);
                                                visibilitylist.add(y);
                                }
                                
                                System.out.println(visibilitylist);
                                return visibilitylist;
                }
                
                public List<Double>getallrepscore(int rowno){
                                waitForElement(overviewReport, 10);
                                scrollByElement(overviewReport);
                                List<Double> accuracylist = new ArrayList<Double>();
                                String a = null;
                                String x = null;
                                double y = 0;
                                System.out.println(vendorscore.size());
                                for (int i = 1; i <= vendorscore.size()/2; i++) {        
                                                WebElement     s = driver.findElement(By.xpath (("//*[@id='compIntOverviewContainer']//div[@class='overviewSubContainer div-table-cell ng-scope'][" + rowno + "]//div[@class='progress']")));
                                                a = s.getAttribute("data-percent");
                                                if(a.contains("%")){
                                                                x =a.replace("%", "").trim();
                                                                y=Double.parseDouble(x);
                                                                System.out.println(y);
                                                                }else if(!a.contains("%")) {
                                                                                double z = Double.parseDouble(a);                                                                          
                                                                                double average = (z/5)*100;
                                                                                y=average;
                                                                }
                                                BigDecimal bd = BigDecimal.valueOf(y);
            bd = bd.setScale(0, RoundingMode.HALF_UP);
           double ax = bd.doubleValue();
           System.out.println(ax);
                                                accuracylist.add(ax);
                                }
                                
                                System.out.println(accuracylist);
                                return accuracylist;
                }
                
                
                public void verify_pageloadCompletely(int timeout) {
                                if (waitForElement(overviewReport, timeout) && waitForElement(siteTable, timeout)
                                                                & waitForElement(filter_Panel, timeout))
                                                assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
                                else
                                                assertTrue(false, "Page not loaded completely");
                }
                
                public List<Map<String, String>> getExportData() throws Exception {
                                exportaccuracyReport();

                                String[][] table = new ExcelHandler(Exportpath + SummaryExport, "Sheet0").getExcelTable();
                                List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
                                int colSize = table[0].length;
                                for (int col = 1; col < colSize; col++) {
                                                Map<String, String> kMap = new HashMap<String, String>();

                                                for (int i = 1; i < table.length; i++) {
                                                                kMap.put("compName", table[0][col]);
                                                                kMap.put(table[i][0], table[i][col]);
                                                }
                                                exportData.add(kMap);//to be reviewed
                                }
                                

                                return exportData;

                }
                
                public void compareExportnTable(List<Map<String, String>> exportData, List<Map<String, String>> siteTableData) {
                                
                                for (Map<String, String> m1 : exportData) {
                                                for (Map<String, String> m2 : siteTableData) {
                                                                
                                                                if (m1.get("compName").equals(m2.get("compName"))) {
                                                                                
                                                                                Assert.assertEquals(m1.size() - 1, m2.size());
                                                                                m1.forEach((k,v)->{
                                                                                                System.out.println("for name " + k + " score from export " + v
                                                                                                + "and score from site table" + m2.get(k));
                                                                                                if (!k.equalsIgnoreCase("Overall") && !k.contains("Yellowpages")
                                                                                                                                && !k.equals("compName")) {
                                                                                                                Assert.assertEquals(formatFloat(v), formatFloat(m2.get(k)), 0.05,
                                                                                                                                                "Verifying score for " + k + "for" + m1.get("compName"));
                                                                                                } else if (k.equalsIgnoreCase("YellowpagesCom")) {
                                                                                                                Assert.assertEquals(formatFloat(v), formatFloat(m2.get("Yellowpages.com")), 0.05,
                                                                                                                                                "Verifying score for " + k + "for" + m1.get("compName"));
                                                                                                } else if (k.equalsIgnoreCase("Yellowpages")) {
                                                                                                                Assert.assertEquals(formatFloat(v), formatFloat(m2.get("Yellowpages.ca")), 0.05,
                                                                                                                                                "Verifying score for " + k + "for" + m1.get("compName"));
                
                                                                                                }
                                                                                
                                                                });
                                                                                
                                                                }
                                                }

                                }

                }

                

}
