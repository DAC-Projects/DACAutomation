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

public class TPSEE_AllLocations_Page extends TPSEE_abstractMethods{
	
	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	//Navigating to TPSEE Content_Analysis page
	public TPSEE_AllLocations_Page(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/* ------------------------------Locators---------------------------------------*/
	
	@FindBy(xpath = "//a[@id='location_export']")
	private WebElement Export;
	
	@FindBy(xpath = "//div[@id='locationTable']/table")
	private WebElement LocationTable;
	
	@FindBy(xpath = "//table[@id='table_report']//thead")
	private WebElement LocationTableHeader;
	
	@FindBy(xpath = "//table[@id='table_report']//tbody//tr")
	private List<WebElement> LocationTableRow;
	
	@FindBy(xpath = "//div[@id='paginationInfo']")
	private WebElement entiresText;
	
	/*-------------------------Pagination-----------------------*/
	
	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath = "//ul[@class='pagination']//li[@class='active']")
	private WebElement paginationFirst;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationNext;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private List<WebElement> paginationLast;
	
	/*-------------------------Pagination-----------------------*/
	
	/* ------------------------------Locators---------------------------------------*/

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *This method is used to check whether data is there in table or not based on the applied criteria
	 * @return true : if data is there otherwise return false			*/
	public boolean isDataAvailable() throws Exception {
		JSWaiter.waitJQueryAngular();
		scrollByElement(LocationTable);
		if(driver.findElement(By.id("paginationInfo")).isDisplayed()) {
			return true;
		}else if(driver.findElement(By.id("paginationInfo_empty")).isDisplayed()) {
			BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			return false;
		}
		waitForElement(LocationTable, 20);
		return false;
	}
	
	
	//Get UI table
	public List<Map<String, String>> LocationDataTable() throws InterruptedException{
		JSWaiter.waitJQueryAngular();
		waitForElement(LocationTable, 40);
		//getting into progressbar found listing
		System.out.println("\n reading table data********************* \n");
		driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()]")).click();
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-2]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n"+page);
		driver.findElement(By.xpath("//a[@id='firstPage']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("col-md-4")));
		String entiresText = driver.findElement(By.className("col-md-4")).getText();
		entiresText = entiresText.substring(entiresText.indexOf("("));
		//WebElement TableTitle = driver.findElement(By.xpath("//div[@id='keyword_table_title']"));
		//scrollByElement(TableTitle);
		//WebElement locationsText = driver.findElement(By.xpath("//table[@id='rankingDetail']//tbody//tr"));
		//scrollByElement(locationsText);
		int count = 0;
	    if(paginationNext.isDisplayed()) {
	    	for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
	    		scrollByElement(LocationTableHeader);
	    		List < WebElement > rows_table = LocationTableRow;	//To locate rows of table. 
	    		int rows_count = rows_table.size();		//To calculate no of rows In table.
	    		count = count + rows_count;
	    		Map<String, String> kMap = new HashMap<String, String>();
	    		for (int row = 0; row < rows_count; row++) { 
	    			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
	    			int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
	    			int noOfRows=row+1;
	    			//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
	    			for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
	    				List<WebElement> headerTableRow=LocationTableHeader	.findElements(By.tagName("th"));
	    				String headerText = headerTableRow.get(column).getText() , celtext ="";
	    				if(column==1 & row < rows_count) {
	    					celtext = driver.findElement(By.xpath("(//table[@id='table_report']//tbody//tr//td)["+ (row+1) +"]")).getText();
	    				}else {
						celtext = Columns_row.get(column).getText().trim();	// To retrieve text from that specific cell.
					}
	    				kMap.put("Location", celtext);
	    				tableCellValues.add(kMap);
	    				System.out.println("Cell Value of column " + headerText + " Is : " + celtext);
	    			}
	    			//System.out.println("-------------------------------------------------- ");
	    		}
	    		if(paginationNext.isEnabled()) {
	    			JSWaiter.waitJQueryAngular();
	    			scrollByElement(paginationNext);
	    			paginationNext.click();
	    			Thread.sleep(4000);
	    		}
	    	}
	    }
	    	System.out.println("Total number of entries in table : "+count);
	    	Assert.assertTrue(entiresText.contains(""+count+""), "Table Data count matches with total enties count");
	    	return tableCellValues;
	}
	
	
	//Download Excel sheet
	public void LocationDataTableExport() throws FileNotFoundException, IOException, InterruptedException {
		waitForElement(LocationTable, 40);
		waitForElement(Export, 40);
		JSWaiter.waitUntilJQueryReady();
		download(CurrentState.getBrowser(), Export, 30);
		convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+LocationExport));
		}

	
	//Get Excel into Map
	public List<Map<String, String>> getLocationDataTableExport() throws Exception {
		JSWaiter.waitJQueryAngular();
		LocationDataTableExport();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+LocationExport), "Sheet0").getExcelTable();
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

	
	// Compare UI and Excel
	public void compareExprttoAnalysisSiteLinkData(List<Map<String, String>> LocationDataTable,
			List<Map<String, String>> getLocationDataTableExport) {
		
		for (Map<String, String> m1 : LocationDataTable) {
			for (Map<String, String> m2 : getLocationDataTableExport) {
				if (m1.get("Location").equals(m2.get("Location")))
				Assert.assertEquals(m1.size(), m2.size());
				Assert.assertEquals(m1.get("Location").contains(m2.get("Location")), "Data Matches");
				
			}
		}
	}

	
	//Compare UI and Excel
	public void compareXlData_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		List < WebElement > Columns_row = LocationTableHeader.findElements(By.tagName("th"));
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
}
