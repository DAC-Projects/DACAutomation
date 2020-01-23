package com.dac.main.POM_CA;

import static org.testng.Assert.assertTrue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class CA_Visibility_Page extends CA_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public CA_Visibility_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	String ss;
	int result;

	// locators
	
	@FindBy(id="myGroups")
	private WebElement filterGroup;
	
	@FindBy(xpath="//*[@class='menu transition hidden']")
	private WebElement filterDropDown;
	
	@FindBy(xpath="(//*[@id='divCIVisibility']/h1)")
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
		
		@FindBy(xpath = "//*[@id=\"compIntVisibilitySitesTable\"]/tbody/tr")
		private List<WebElement> reviewTableRow;
		
		@FindBy(xpath="//*[@id=\"divCIVisibility\"]/div[6]/div[1]/h3")
		private WebElement titlehead;
		
		@FindBy(xpath="//*[@id='compIntVisibilitySitesTable']/tbody/tr/td[2]")
		private WebElement col; 
		
		
		
		
	// export button
	@FindBy(css = "button#btnExport>span")
	private WebElement exportBtn;
	
	// overview report
	@FindBy(css = "div#compIntOverviewContainer")
	private WebElement overviewReport;
	
	// section of overview report
	@FindBy(xpath = "//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')]")
	private List<WebElement> competitors;

	String xpathCompetitors = "(//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')])";
	String compName = ".//div[starts-with(@class, 'competitorName')]";
	String compScore = ".//div[starts-with(@class, 'competitorScore')]"; 
	String locationcount =".//div[starts-with(@class, 'competitorLocations')]";
	
	// site table
	@FindBy(css = "table#compIntVisibilitySitesTable")
	private WebElement siteTable;

	List<WebElement> columns;
	List<WebElement> rows;
	
	ArrayList<Double> list1 = new ArrayList<Double>();
	ArrayList<Double> list2 = new ArrayList<>();

	/**
	 * Export visibility overview report
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 * 
	 */
	
//	Date date = new Date();
//	   SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy_hmmss_a");
//	   String formattedDate = sdf.format(date);
	   
	   
	public void exportvisibilityReport() throws InterruptedException, FileNotFoundException, IOException {
		waitForElement(overviewReport, 10);
		waitForElement(exportBtn, 10);
		scrollByElement(exportBtn);
		download(CurrentState.getBrowser(), exportBtn, 20);
		convertExports(getLastModifiedFile(Exportpath), VisibilityExport);
	}
	/* (non-Javadoc)
	 * Reads overview report and return values as list.
	 * @see com.dac.main.POM_CA.CA_abstractMethods#getOverviewReport()
	 */
	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		waitForElement(overviewReport, 10);
		scrollByElement(overviewReport);
		Map<String, String> kMap;
		
		List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= competitors.size(); i++) {
			WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
			kMap = new HashMap<String, String>();
			kMap.put("compName", s.findElement(By.xpath(compName)).getText());
			kMap.put("score", s.findElement(By.xpath(compScore)).getText());
		
						System.out.format("%10s%10s", s.findElement(By.xpath(compName)).getText(),
					s.findElement(By.xpath(compScore)).getText());
						System.out.println("test");
			
			System.out.println("");
			ovrwRprtData.add(kMap);
		}
		return ovrwRprtData;
}

	
	public List<Double> Tablevalues(String col) throws Exception{
		List<Double> score = new ArrayList<>();
		int columns_count=0;
		WebElement TableTitle = driver.findElement(By.xpath("//*[@id='divCIVisibility']/div[6]/div[1]/h3"));
		scrollByElement(TableTitle);
		WebElement mytable = driver.findElement(By.xpath("//*[@id='compIntVisibilitySitesTable']"));
	    	//To locate rows of table. 
	    	List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
	    	//To calculate no of rows In table.
	    	int rows_count = rows_table.size()-1;
	    	System.out.print("Row count"+rows_count);
	    	List < WebElement > col_table = mytable.findElements(By.tagName("th"));
	    	int col_count = col_table.size()-1;
	    	System.out.print("Col count"+col_count);
	        int i;
			List<WebElement> costColumns= driver.findElements(By.xpath(col));
	        ArrayList<Double> list = new ArrayList<Double>();
			for(WebElement e:costColumns)
			{System.out.println(e.getText());
				String x = "0.0" ;
				String n = e.getText();
				double y= 0.0;
				if(n.contains("%")){
					x =n.replace("%", "").trim();
					System.out.println(x);
					}
				if(n.contains("-")) {
					x =n.replace("-", "0.0").trim();
					System.out.println(x);}
				 y=Double.parseDouble(x);
				 System.out.println(y);	
				 list.add(y);
				 			
			}
			ArrayList<Integer> listarray = new ArrayList<Integer>();
			System.out.println("array"+list);	
			int aa=list.size();
			System.out.println("count" + aa);
			//int[] y = new int[400];
			//for(int i1=0; i1<list.size()/2; i1++) {
				//y[i1]=(int) (result*list.get(i1))/100;
			//listarray.add((int) y[i1]);
			//}
			System.out.println("values"+listarray);
			double sum = 0;
			for( i = 0; i < list.size(); i++) 
			    sum += list.get(i);
			System.out.println("total price: "+sum);
			double avg = (sum/rows_count);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			double avgFinal=Double.parseDouble(numberFormat.format(avg));
			System.out.println(avgFinal);
			score.add(avgFinal);
			System.out.println("abi"+ score);
			return score;	
		
	}
	public  List<Double> total() throws Exception {
		for(int i=2;i<10;i++) {
		System.out.println("Executing");
		List<Double> d = Tablevalues("//*[@id='compIntVisibilitySitesTable']/tbody/tr/td[" + i + "]");
		System.out.println("exe"+ d);	
		list1.addAll(d);
		System.out.println(list1);
		}
	return list1;  }
	
	
public List<Double> getOverviewReport1() {
	// TODO Auto-generated method stub
	waitForElement(overviewReport, 10);
	scrollByElement(overviewReport);
	String a=null;
	String x = null;
	double y=0;
	System.out.println(competitors.size());
	for (int i = 1; i <= competitors.size()/2; i++) {
		WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
		 a=s.findElement(By.xpath(compScore)).getText();
		 if(a.contains("%")){
				x =a.replace("%", "").trim();
				 y=Double.parseDouble(x);
				System.out.println(y);
				}
		 list2.add(y);
		 }
	System.out.println(list2);
	
	return list2;
}

/*public List<WebElement> getLocation() {
	
	List<WebElement> actmenu = driver.findElements(By.xpath(locationcount)); 
	int array[] = new int [actmenu.size()/2];
    for (int i = 0; i <actmenu.size()/2; i++) {
    	System.out.println("size" + actmenu.size());
    	String ss=actmenu.get(i).getText();
    	System.out.println(actmenu.get(i).getText());
       String ad=ss.substring(5, 6);
    System.out.println(ad);
    	 result = Integer.parseInt(ad);			
    	array[i]=result;
    	}
    System.out.println("int"+ Arrays.toString(array));
    return actmenu;
	}*/
	public void compareList(List<Double> total ,List<Double> getOverviewReport1 ) {
		/*System.out.println(list1);
		System.out.println(list2);*/
		//Assert.assertEquals(list1, list2 , "Comparing overview and table");
		Assert.assertEquals(list1.equals(list2), true);
	}
	/**
	 * @param timeout
	 * Verifies all elements are present till timeout in seconds
	 */
	public void verify_pageloadCompletely(int timeout) {
		if (waitForElement(overviewReport, timeout) && waitForElement(siteTable, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	
	/**
	 * Reads export data and return as a list
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getExportData() throws Exception {
		exportvisibilityReport();

		String[][] table = new ExcelHandler(Exportpath + VisibilityExport, "Sheet0").getExcelTable();
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
		System.out.println("Abi"+ exportData);
		

		return exportData;

	}
	public void VisibilityExport1() throws Exception {
//Getting locations
		List<WebElement> actmenu = driver.findElements(By.xpath(locationcount)); 
		int array[] = new int [actmenu.size()/2];
	    for (int i = 0; i <actmenu.size()/2; i++) {
	    //	System.out.println("size" + actmenu.size());
	    	String ss=actmenu.get(i).getText();
	   // 	System.out.println(actmenu.get(i).getText());
	       String ad=ss.substring(5, 6);
	//    System.out.println(ad);
	    	 result = Integer.parseInt(ad);			
	    	array[i]=result;
	    	
	    	//System.out.println("abiarr"+array[i]);
	     System.out.println("int"+ Arrays.toString(array));
	    	}
	    
	    //cal
	   
        String[][] table = new ExcelHandler(Exportpath + VisibilityExport, "Sheet0").getExcelTable();
        //System.out.println("Row length = "+table.length);
       // System.out.println("Score1 = "+table[2][1]);
        int i;
        int col;
        double[] tableValues = new double[table.length-1];
       
        double[] arry = new double[table.length-1];
        int colSize1 = table[0].length;
        double[][] finalArray =new double[colSize1-1][table.length-2];
        //System.out.println(colSize1);
       int k =0;
        for (col = 1; col < colSize1; col++) {

 

        for(i=0;i<table.length-3;i++) {       
        
             tableValues[i] = Double.parseDouble(table[i+2][col]);
             System.out.println(tableValues[i]);
             System.out.println(array[col-1]);
            arry[i]= tableValues[i]/100 *array[col-1];  
           // System.out.println("Array "+Arrays.toString(arry));
            finalArray[col-1][i] = arry[i];
            
           
        }
        System.out.println("array "+col+":"+Arrays.toString(finalArray[col-1]));
        
  }
        
        
        System.out.println(Arrays.deepToString(finalArray));
        //System.out.println("Double array value"+i+":" + Arrays.toString(tableValues) );
        }
        
        
         
        
        
    

	/**
	 * Compare export and table
	 * @param exportData
	 * @param siteTableData
	 */
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
