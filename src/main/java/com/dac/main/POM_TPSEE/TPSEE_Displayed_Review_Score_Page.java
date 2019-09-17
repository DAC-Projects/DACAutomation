package com.dac.main.POM_TPSEE;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_Displayed_Review_Score_Page extends TPSEE_abstractMethods{
	
	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	

	public TPSEE_Displayed_Review_Score_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	


	private String GoogleRating = "//div[contains(@class,'google text')]";
	
	private String FacebookRating = "*//div[contains(@class,'facebook text')]";
	
	/*-------------------------Table Scores-----------------------*/
	
	@FindBy(id = "ToolTables_reviewscore_results_0")
	private WebElement TableExport;
	
	@FindBy(xpath = "//div//table")
	private WebElement DataTable;
	
	@FindBy(xpath = "//div//table//thead")
	private WebElement TableHeader;
	
	@FindBy(xpath = "//div//table//tbody//tr")
	private List<WebElement> TableRow;
	
	@FindBy(id = "table_title")
	private WebElement Tabletitle;
		
	/*-------------------------Table Scores-----------------------*/
	
	
	/*-------------------------Pagination-----------------------*/
	
	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private List<WebElement> paginationLast;
	
	@FindBy(xpath = "//*[@id='reviewscore_results_info']")
	private WebElement totalentries;
	
	/*-------------------------Pagination-----------------------*/
	
	public void getscrores() throws Exception{
		
		JSWaiter.waitJQueryAngular();
		String GoogleRatingScore = driver.findElement(By.xpath(GoogleRating)).getText();
		System.out.println("Google Rating is :" +GoogleRatingScore);
		String FacebookRatingScore = driver.findElement(By.xpath(FacebookRating)).getText();
		System.out.println("Facebook Rating is :" +FacebookRatingScore);		
	}
	
	public void DataTableExport() throws FileNotFoundException, IOException, InterruptedException {
		waitForElement(DataTable, 40);
		waitForElement(TableExport, 40);
		JSWaiter.waitUntilJQueryReady();
		download(CurrentState.getBrowser(), TableExport, 30);
		convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+ReviewScoreExport));
		}
	
	public List<Map<String, String>> getReviewDataTableExport() throws Exception {
		JSWaiter.waitJQueryAngular();
		DataTableExport();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+ReviewScoreExport), "Sheet0").getExcelTable();
		List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			//adding data into map
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("Location", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
		}
		return exporttableData;
	}
	
	public List<Map<String, String>> ReviewDataTable() throws InterruptedException{
		JSWaiter.waitJQueryAngular();
	
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
		waitForElement(totalentries,50);
		JSWaiter.waitJQueryAngular();
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n"+page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String entiresText = driver.findElement(By.className("dataTables_info")).getText();
		entiresText = entiresText.substring(entiresText.indexOf("("));
		WebElement TableTitle = driver.findElement(By.xpath("//*[@id='table_title']"));
		scrollByElement(TableTitle);
		int count = 0;
	    if(paginationNext.isDisplayed()) {
	    	for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
	    		scrollByElement(TableTitle);
	    		List < WebElement > rows_table = TableRow;	//To locate rows of table. 
	    		int rows_count = rows_table.size();		//To calculate no of rows In table.
	    		count = count + rows_count;
	    		Map<String, String> kMap = new HashMap<String, String>();
	    		for (int row = 0; row < rows_count; row++) { 
	    			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
	    			int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
	    			int noOfRows=row+1;
	    			//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
	    			for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
	    				List<WebElement> headerTableRow=TableHeader.findElements(By.tagName("th"));
	    				String headerText = headerTableRow.get(column).getText(), celtext ="";
	    				if(column==1 & row < rows_count) {
	    					celtext = driver.findElement(By.xpath("(*//div//table//tbody//tr)["+ (row+1) +"]")).getText();
	    					System.out.println("\n"+celtext);
	    			}
	    				kMap.put("rowdata", celtext);
	    				tableCellValues.add(kMap);
	    				//System.out.println("Cell Value of row " + noOfRows + " and column " + headerText + " Is : " + celtext);
	    			}
	    			//System.out.println("-------------------------------------------------- ");
	    		}
	    		if(paginationNext.isEnabled()) {
	    			scrollByElement(paginationNext);
	    			paginationNext.click();
	    			Thread.sleep(4000);
	    		}
	    	}
	    }
	    System.out.println("Total number of entries in table : "+count);
	    Assert.assertTrue(entiresText.contains(""+count+""), "Table Data count matches with total enties count");
		}else if(driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return tableCellValues;
			}

	
	//comparing data from excell sheet with table data of progress bar Not found and Found
		public void compareXlReviewData_UIdata() throws Exception {
			JSWaiter.waitJQueryAngular();
			List < WebElement > Columns_row = TableHeader.findElements(By.tagName("th"));
			int col_count = Columns_row.size();
			String newfilename = BasePage.getLastModifiedFile("./downloads");
			//String newfilename = new formatConvert("./downloads/"+fileName).convertFile("xlsx");
			ExcelHandler a = new ExcelHandler("./downloads/"+newfilename, "Sheet0"); a.deleteEmptyRows();
			int xlRowCount=new ExcelHandler("./downloads/"+newfilename, "Sheet0").getRowCount();
			int count = 0;
			for(int i=1;i<xlRowCount;i++) {
				col_count = a.getColCount(i);
				for(int j=0;j<=col_count;j++) {
					String cellValue = a.getCellValue(i, j+1).trim();
					if(cellValue.contains("%")) cellValue = new String(""+Double.parseDouble(cellValue.replace("%", ""))+"%");
					if(cellValue.length() != 0 & cellValue != null) {
						Map<String, String> uiTableCellValue = tableCellValues.get(count);
						if(uiTableCellValue.containsValue(cellValue)) { // | uiTableCellValue.equals(cellValue)
							Assert.assertTrue(uiTableCellValue.containsValue(cellValue), uiTableCellValue+" is matches with Downloaded Excel value : "+cellValue);
						}else {
							Assert.assertTrue(false, uiTableCellValue+" is NOT matches with Downloaded Excel value : "+cellValue);
						}
						
						if(j <1 | j >5) count++;
					}
				}
			}
			CurrentState.getLogger().info("UI table data matches with Exported Excel Data");
			Assert.assertTrue(true, "UI table data matches with Exported Excel Data");
			tableCellValues.clear();
		}

		public void compareexporttableDatanstardetails(List<Map<String, String>> reviewDataTable,
				List<Map<String, String>> reviewDataTableExport) {
			// TODO Auto-generated method stub
			
			for (Map<String, String> m1 : reviewDataTable) {
				for (Map<String, String> m2 : reviewDataTableExport) {
					if (m1.get("rowdata").equals(m2.get("rowdata"))) {
						Assert.assertEquals(m1.size(), m2.size());
						Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
					}
				}
			}
			
		}
}
