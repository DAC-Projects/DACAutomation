package com.dac.main.POM_TPSEE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.CurrentState;
import resources.DateFormats;
import resources.ExcelHandler;
import resources.IAutoconst;
import resources.JSWaiter;

public class TPSEE_ReviewStream_Page extends TPSEE_abstractMethods{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	public static List<String> ListOfdays = new ArrayList< String>();
	public int exportedcnt=0, tableCount=0;
	public String[][]exceldata;
	
	

	public TPSEE_ReviewStream_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/* ------------------------------Locators---------------------------------------*/
	
	@FindBy(xpath = "//div[@id='reviewStreamReportExportDropDown']//button")
	private WebElement exportBtn;
	
	@FindBy(xpath = "//li//a[contains(text(),'Export as CSV')]")
	private WebElement exportCSV;
	
	@FindBy(xpath = "//li//a[contains(text(),'Export as XLSX')]")
	private WebElement exportXSLX;
	
	@FindBy(xpath = "//div[@id='table_review_wrapper']/table")
	private WebElement ReviewStreamTable;
	
	@FindBy(xpath = "//table[@id='table_review']//thead")
	private WebElement ReviewStreamTableHeader;
	
	@FindBy(xpath = "//table[@id='table_review']//tbody//tr")
	private List<WebElement> ReviewStreamTableRow;
	
	@FindBy(xpath = "//div[@id='table_review_info']")
	private WebElement entiresTextelmnt;
	
	/*-------------------------Pagination-----------------------*/
	
	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;
	
	@FindBy(xpath = "(//*[@id='table_review_previous'])")
	private WebElement paginationPrev;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	@FindBy(xpath="//*[@id='table_review_length']/label/select")
	private WebElement recordsPerPage;
	
	/*-------------------------Pagination-----------------------*/

	@FindBy(xpath="//*[@id='exportAsyncMessage']")
	private WebElement msgExport;
	
	@FindBy(xpath="//*[@id='exportAsyncMessage']/div[1]/a['Click here to download your report']")
	private WebElement msgExportLink;
	
	
	
	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//get Review stream table data
	public List<Map<String, String>> ReviewStreamDataTable(String [][] exceldata ) throws Exception{
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewStreamTable, 40);
		//getting into progressbar found listing
		System.out.println("\n reading table data********************* \n");
		driver.findElement(By.xpath("(//*[@class='pagination']//a)[2]")).click();
		
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n), records;
		System.out.println("Pages: "+page);
//		page=1;
		Select select = new Select(recordsPerPage);
		WebElement option = select.getFirstSelectedOption();
		records = Integer.parseInt(option.getText());
		
//		driver.findElement(By.xpath("//*[@id='table_review_previous']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='table_review_info']")));
	
		System.out.println("Records : "+records);
		
//		String entiresText = driver.findElement(By.xpath("//*[@id='table_review_paginate']")).getText();
//		System.out.println("Results:  "+entiresText);
//		entiresText = entiresText.substring(entiresText.indexOf("…"));
//		System.out.println("Total pages:  "+entiresText);
		
		waitForElement(paginationNext, 40);
	    if(paginationNext.isDisplayed()) {
	    	allpages:// label for break all pages loop
	    	for(int i=0;i<page;i++) {	//Loop will execute for all records in page
	    		scrollByElement(ReviewStreamTableHeader);
	    		List < WebElement > rows_table = ReviewStreamTableRow;	//To locate rows of table.
	    		
	     		int rows_count = rows_table.size();		//To calculate no of rows In table.
	     		System.out.println("Rows in : "+rows_count+" Page: "+page);
//	    		count = count + rows_count;
	    		Map<String, String> kMap = new HashMap<String, String>();
	    
	    		for (int row = 0; row <rows_count; row++) { 
	    			//system.out.println("Row count is: "+row);//To calculate no of columns (cells). In that specific row.

	    			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
	    			int columns_count = Columns_row.size();	

	    			String reviewdate= Columns_row.get(3).getText();// driver.findElement(By.xpath("//*[@id='table_review']//tr//td[4]")).getText();
	    			String source=driver.findElement(By.xpath("//table[@id='table_review']//tbody//tr["+ (row+1) +"]//td//a")).getAttribute("href");
	       			boolean date_exist=ReviewStreamDate(reviewdate);
	    			//int column =0;
	    			if(!source.contains("yelp")) {
	    				if (date_exist) {
	    						tableCount++;
	    						
	    						
		    					for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
			    				//System.out.println("Column is: "+column);
			    						    			
				    			String headerText = "" , celtext ="";
				    			
				    			List<WebElement> headerTableRow=ReviewStreamTableHeader	.findElements(By.tagName("th"));
				    			
//				    			headerTableRow.forEach(System.out::println);
		    					headerText = headerTableRow.get(column).getText().toString();
//		    					System.out.println("Column Header: "+headerText);
//		    					if (headerText.equalsIgnoreCase("Location Number")||headerText.equals("Date")||headerText.equals("Author"))
////		    	    					celtext = ReviewStreamTableRow.get(row).getText();//driver.findElement(By.xpath("//table[@id='table_review']//tbody//tr["+ (row+1) +"]")).getText();
//		    	    					{
//		    		    					
//		    	    					}
		    					if(column==1 & row < rows_count) 
		    						{
		    				        	celtext = driver.findElement(By.xpath("//table[@id='table_review']//tbody//tr["+ (row+1) +"]")).getText();
		    				    	}
		    					else 
		    				    	{
		    				    	    celtext = Columns_row.get(column).getText().trim();    // To retrieve text from that specific cell.
		    				    	}
		    				    		    					
		    				    			    				kMap.put("Review", celtext);
		    				    			    				tableCellValues.add(kMap);
//		    				    			    				
		    					//verifyUItoExcel(exceldata, celtext,row);
		    				}
						
	    					    										
		    			}else {
//		    			System.out.println("Row Number: "+(row+1)+" Review Date: "+reviewdate);
	    				System.out.println("Review date NOT available in the List ");
	    				break allpages; //exit from all rows and stop
		    			}
	    				
	    			}
	    				
	    					    		
	    		
	    		}
//	    		System.out.println("Rows completed and exited from loop");
	    		
	    		System.out.println("Exit from rows loop");
	    		if(paginationNext.isEnabled()) {
	    			JSWaiter.waitJQueryAngular();
	    			scrollByElement(paginationNext);
	    			paginationNext.click();
	    			Thread.sleep(4000);
	    			
	    		}
	    	
	    	}
	    	 		
	    		
	    	
	    	}
	    
	    
	    	exportedcnt=exceldata.length;
			int tmpExportedcnt=exportedcnt-1;
			System.out.println("exported count: "+tmpExportedcnt);
			System.out.println("Table count: "+tableCount);
			
	    	Assert.assertEquals(tmpExportedcnt,tableCount,"Exported Size Matched");
//   	Assert.assertTrue(entiresText.contains(""+count+""), "Table Data count matches with total entries count");
//	    	System.out.println("Size of Table Map "+tableCellValues.size());
//	    	tableCellValues.forEach(System.out::println);
	    	
	    	return tableCellValues;
	}
	
	/*---------Export Review Stream 7 day Report ----------*/
	public void ReviewStreamDataTableExport() throws FileNotFoundException, IOException, InterruptedException {
		waitForElement(ReviewStreamTable, 40);
		waitForElement(exportBtn, 40);
		JSWaiter.waitUntilJQueryReady();
		System.out.println("Clicking Download button");
		exportBtn.click();
		//exportCSV.click();		
		//convertExports(getLastModifiedFile(Exportpath), CurrentState.getBrowser()+ReviewStreamExportCSV);
		exportXSLX.click();
		
		boolean msgbox=waitForElement(msgExportLink, 180);
		
		/*System.out.println("msgbox is : "+msgbox);
	downloadFile(exportBtn);
		if (msgbox){
			System.out.println("Alert window identified");
		downloadFile(msgExportLink);
			msgbox=true;
		}else {
			System.out.println("Alert window not identified");
			download(CurrentState.getBrowser(), exportBtn, 30);
			downloadFile(exportBtn);
		}*/
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+ReviewStreamExportXLSX));		
	}
	
	/*private void downloadFile(WebElement element) throws InterruptedException, FileNotFoundException, IOException {
		download(CurrentState.getBrowser(), element, 30);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+ReviewStreamExportXLSX));
	}*/
	
	/*-----------printing Review Stream data from download excel sheet------------*/
	public List<Map<String, String>> getReviewStreamExportData(String [][ ]exceldata) throws Exception {
				JSWaiter.waitJQueryAngular();
				
				
				int rowSize=exceldata.length;
				System.out.println("Number of rows: "+rowSize);
				//String [][]table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+ReviewStreamExport), "Sheet0").getExcelTable();
				List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
				
				int colSize = exceldata[0].length;
				for (int col = 1; col < colSize; col++) {
					//adding data into map
//					System.out.println("Excel columns: "+colSize);
//					System.out.println("Exported excel table Length: "+exportedCount);
					Map<String, String> kMap = new HashMap<String, String>();
					for (int i = 1; i < exceldata.length; i++) {// Working on Here  25/10/2019
						kMap.put("Review", exceldata[0][col]);
						kMap.put(exceldata[i][0], exceldata[i][col]);
					}
					exportData.add(kMap);
					System.out.println(":");
				}
				//returning visibility report from excel
				//exportData.forEach(System.out::println);
				return exportData;

			}
	
	public void compareExprttoAnalysisSiteLinkData(List<Map<String, String>> ReviewStreamDataTable,
			List<Map<String, String>> getReviewStreamExportData) {
		

			
		for (Map<String, String> m1 : ReviewStreamDataTable) {
			for (Map<String, String> m2 : getReviewStreamExportData) {
			
				
//				Assert.assertEquals(m1.get("Review"), m2.get("Review"), "Reviews Matched");
//				Assert.assertEquals(m1.get("Review").contains(m2.get("Review")), true);
				Assert.assertEquals(m2.get("Review").contains(m1.get("Review")),"Verification of UI and XL");
				System.out.println("Review");
			}
		}
	}
	
	
	/*---------Verify date in the Table exist in List ----------*/
	public static boolean ReviewStreamDate(String dt) throws FileNotFoundException, IOException, InterruptedException, ParseException {
//		waitForElement(ReviewStreamTable, 40);
		List<String> ListOfdays = new ArrayList< String>();
		
		String locale=IAutoconst.userLocale;
		String dtFormat=DateFormats.localeDateFormat(locale);
		String converteddate=BasePage.convertTimeZone(dtFormat,locale);
		String endDate=BasePage.addDays(converteddate, -7, dtFormat);
		ListOfdays=BasePage.getDaysBetweenDates(converteddate,endDate, dtFormat);
		if (ListOfdays.contains(dt)) {
			return true;
		}else 
		return false;
		
		
		}
	public String[][] excelExported() throws Exception{
		ReviewStreamDataTableExport();
		
		String [][] tbl=new ExcelHandler(Exportpath + (CurrentState.getBrowser()+ReviewStreamExportXLSX), "Review_Stream").getExcelTable();
		System.out.println("String Array Excel: "+tbl.length);
		
		return tbl;
		
	}
	public boolean verifyUItoExcel(String [][]excelData, String UIData) {
		return Arrays.asList(excelData).contains(UIData);
	}
	/*public static boolean useList(String[] arr, String targetValue) {
	    return Arrays.asList(arr).contains(targetValue);
	}*/
}

