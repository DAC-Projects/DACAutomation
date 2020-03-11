package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class TPSEE_GoogleRanking_Page extends TPSEE_abstractMethods{
	
	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	//Navigating to TPSEE Content_Analysis page
	public TPSEE_GoogleRanking_Page(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	/* ------------------------------Locators---------------------------------------*/
	
	/*-----------------------ScoresTable---------------------------*/
	
	@FindBy(xpath = "//div[@id='divRankingTable']/table")
	private WebElement ScoresTable;
	
	@FindBy(xpath = "//div[@id='divRankingTable']//thead")
	private WebElement ScoresTableHeader;
	
	@FindBy(xpath = "//div[@id='divRankingTable']//tbody//tr")
	private List<WebElement> ScoresTableRow;
	
	/*-----------------------ScoresTable---------------------------*/
	
	/*-----------------------Ranking Table---------------------------*/
		
	@FindBy(xpath = "//div[@id='keywordTableExportDropdown']//button")
	private WebElement Export;
	
	@FindBy(xpath = "//div[@id='keywordTableExportDropdown']//a[contains(text(),'Export as CSV')]")
	private WebElement Export_csv;
	
	@FindBy(xpath = "//div[@id='keywordTableExportDropdown']//a[contains(text(),'Export as XLSX')]")
	private WebElement Export_xlsx;
	
	  
	
	@FindBy(xpath = "//table[@id='rankingDetail']")
	private WebElement RankingTable;
	
	@FindBy(xpath = "//div[@id='keyword_table_title']")
	private WebElement RankingTableTitle;
	
	@FindBy(xpath = "//table[@id='rankingDetail']//thead")
	private WebElement RankingTableHeader;
	
	@FindBy(xpath = "//table[@id='rankingDetail']//tbody//tr")
	private List<WebElement> RankingTableRow;
	
	@FindBy(xpath = "//div[@id='rankingDetail_info']")
	private WebElement entiresText;
	
	@FindBy(xpath = "//div[@class='col-sm-12'][1]")
	private WebElement acckeypanel;
	
	@FindBy(xpath = "(//div/a[@class='remove'])[last()]")
	private WebElement removeacckey;
	
	@FindBy(xpath = "//div[@class = 'selectize-input items not-full has-options has-items']")
	//selectize-input items has-options has-items not-full
	private WebElement accountkeyword;
	
	@FindBy(xpath = "//select[@id='ddlGroup']")
	private WebElement Group;
	
	@FindBy(xpath = "(//div[@class='col-sm-12']//table)[2]")
	private WebElement GroupKeypanel;
	
	@FindBy(xpath = "//div[@class='selectize-input items has-options has-items full']")
	private WebElement GroupKeywords;
	
	@FindBy(xpath = "//button[@id='btnSave']")
	private WebElement SaveKeyword;
	
	
	/*-----------------------Ranking Table---------------------------*/
	
	/*-------------------------Pagination-----------------------*/
	
	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private List<WebElement> paginationLast;
	
	/*-------------------------Pagination-----------------------*/
	
	//tooltipvalue in the graph
	@FindBy(css = "g.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 
	
	/* ------------------------------End of Locators---------------------------------------*/
	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void applyKeywords(String AccKey, String GrKey, String GrKeyword) {
		JSWaiter.waitJQueryAngular();
		WebElement AccountKeyword,GroupKey,GroupKeyword;
		if(AccKey == null || AccKey.equalsIgnoreCase("null")) AccKey = "";
		if(GrKey == null || GrKey.equalsIgnoreCase("none")) GrKey = "None";
		if(GrKeyword == null || GrKeyword.equalsIgnoreCase("null")) GrKeyword = "";
		try {
			waitForElement(acckeypanel, 25);
			scrollByElement(acckeypanel);
			waitUntilLoad(driver);
			waitForElement(accountkeyword, 20);
			scrollByElement(accountkeyword);
			if(accountkeyword.isEnabled()) {
				if(!AccKey.equals("null")) {
					if(removeacckey.isDisplayed()){
						clickelement(removeacckey);
						waitForElement(accountkeyword,20);
						AccountKeyword = acckeypanel.findElement(By.xpath("(//div[@class='col-sm-12'][1]//input)[2]"));
						AccountKeyword.sendKeys(AccKey);
						AccountKeyword.sendKeys(Keys.ENTER);
						}else{
							System.out.println("No keywords");
						}
				}
			waitForElement(GroupKeypanel, 25);
			scrollByElement(Group);
			waitUntilLoad(driver);
			if(!GrKey.equals("None")) {			
				clickelement(GroupKeypanel);
				waitForElement(Group, 20);
				GroupKey = Group.findElement(By.xpath("//div[@data-value='"+GrKey+"']"));
				waitForElement(GroupKey, 10);
				clickelement(GroupKey);
				waitUntilLoad(driver);
				GroupKeyword = acckeypanel.findElement(By.xpath("(//div[@class='col-sm-12'][1]//input)[2]"));
				GroupKeyword.sendKeys(GrKeyword);
				GroupKeyword.sendKeys(Keys.ENTER);
			}else {
				System.out.println("No Keywords");
			}
		}else {
			System.out.println("Cannot add keywords");
			}
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Keywords not added");
	}
	waitUntilLoad(driver);
	

}
	
	
	
	public List<Map<String, String>> RankingScoresData() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if(ScoresTable.isDisplayed()){
		//WebElement vendorname= driver.findElement(By.xpath("//*[@class='logo-img img-responsive sourceImg']"));
		WebElement rankingscores= driver.findElement(By.xpath("//*[@class='center ranking']"));
		scrollByElement(rankingscores);
		int count = 0;
	    	//Loop will execute till the all the row of table completes.
	    		scrollByElement(rankingscores);
	    		List < WebElement > rows_table = ScoresTableRow;	//To locate rows of table. 
	    		int rows_count = rows_table.size();		//To calculate no of rows In table.
	    		count = count + rows_count;
	    		Map<String, String> kMap = new HashMap<String, String>();
	    		for (int row = 0; row < rows_count; row++) { 
	    			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));//To locate columns(cells) of that specific row.
	    			int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
	    			int noOfRows=row+1;
	    			//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
	    			for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
	    				List<WebElement> headerTableRow=ScoresTableHeader.findElements(By.tagName("th"));
	    				String headerText = headerTableRow.get(column).getText(), vendorname="", celtext ="";
	    				if(column==1 & row < rows_count) {
					vendorname= driver.findElement(By.xpath("(//*[@class='logo-img'])["+ (row+1) +"]")).getAttribute("id");
	    			celtext = driver.findElement(By.xpath("(//*[contains(@class,'center ranking')])["+ (row+1) +"]")).getText();
	    				}else {
	    					celtext = Columns_row.get(column).getText().trim();	// To retrieve text from that specific cell.
	    				}
	    				kMap.put("rowdata", celtext);
	    				tableCellValues.add(kMap);
	    				System.out.println("Cell Value of row " + noOfRows + vendorname +" and column " + headerText + " Is : " + celtext);
	    			}
	    			//System.out.println("-------------------------------------------------- ");
	    		}
	    		
	    System.out.println("Total number of entries in table : "+rows_count);
	}
		return tableCellValues;
	}
	
	public List<Map<String, String>> RankingDataTable() throws InterruptedException{
		JSWaiter.waitJQueryAngular();
		waitForElement(RankingTable, 40);
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
		//getting into progressbar found listing
		System.out.println("\n reading table data********************* \n");
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n"+page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String entiresText = driver.findElement(By.className("dataTables_info")).getText();
		entiresText = entiresText.substring(entiresText.indexOf("("));
		WebElement TableTitle = driver.findElement(By.xpath("//div[@id='keyword_table_title']"));
		scrollByElement(TableTitle);
		WebElement locationsText = driver.findElement(By.xpath("//table[@id='rankingDetail']//tbody//tr"));
		scrollByElement(locationsText);
		int count = 0;
	    if(paginationNext.isDisplayed()) {
	    	for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
	    		scrollByElement(RankingTableHeader);
	    		List < WebElement > rows_table = RankingTableRow;	//To locate rows of table. 
	    		int rows_count = rows_table.size();		//To calculate no of rows In table.
	    		count = count + rows_count;
	    		Map<String, String> kMap = new HashMap<String, String>();
	    		for (int row = 0; row < rows_count; row++) { 
	    			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("div"));	//To locate columns(cells) of that specific row.
	    			int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
	    			int noOfRows=row+1;
	    			//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
	    			for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
	    				List<WebElement> headerTableRow=RankingTableHeader.findElements(By.tagName("th"));
	    				String headerText = headerTableRow.get(column).getText() , celtext ="";
	    				if(column==1 & row < rows_count) {
	    					celtext = driver.findElement(By.xpath("(//*[@id='rankingDetail']/tbody/tr/td/div)["+ (row+1) +"]")).getText();
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
	
	/**
	 * exporting progress bar table data CSV
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void GRDataTableExportCSV() throws FileNotFoundException, IOException, InterruptedException {				
			JSWaiter.waitJQueryAngular();
			exportVATable(Export, Export_csv);
			renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+ GoogleRankingExportCSV));
			Thread.sleep(5000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
		}
		
	/**
	 * exporting progress bar table data XSLX
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
		public void GRDataTableExportXLSX() throws FileNotFoundException, IOException, InterruptedException {				
				JSWaiter.waitJQueryAngular();
				exportVATable(Export, Export_xlsx);
				renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+GoogleRankingExportXLSX));
				Thread.sleep(5000);
				CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
			}
	

	
	public List<Map<String, String>> getRankingDataTableExport() throws Exception {
		JSWaiter.waitJQueryAngular();
		GRDataTableExportXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+GoogleRankingExportXLSX), "Google_Ranking").getExcelTable();
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

	
	@SuppressWarnings("unlikely-arg-type")
	public void compareExprttoAnalysisSiteLinkData(List<Map<String, String>> RankingDataTable,
			List<Map<String, String>> getRankingDataTableExport) {
		
		for (Map<String, String> m1 : RankingDataTable) {
			for (Map<String, String> m2 : getRankingDataTableExport) {
				Assert.assertEquals(m1.size(), m2.size());
				assertTrue(getRankingDataTableExport.contains(RankingDataTable), "Data Matches");
			}
		}
	}
	
	
	public void compareXlData_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		List < WebElement > Columns_row = RankingTableHeader.findElements(By.tagName("th"));
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



	public void clickApplyKeyword() throws InterruptedException {
		// TODO Auto-generated method stub
		
		JSWaiter.waitJQueryAngular();
		if(SaveKeyword.isDisplayed()) {
			clickelement(SaveKeyword);
			Thread.sleep(3000);
		}
		
	}


	public void compareexporttableDatanrankingdetails(List<Map<String, String>> rankingDataTable,
			List<Map<String, String>> rankingDataTableExport) {
		// TODO Auto-generated method stub
						for (Map<String, String> m1 : rankingDataTable) {
					for (Map<String, String> m2 : rankingDataTableExport) {
						if (m1.get("rowdata").equals(m2.get("rowdata"))) {
							Assert.assertEquals(m1.size(), m2.size());
							Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
						}
					}
				}
				
			}
	
	
	public double GRScore() throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth())/2 -2, 0).click().perform();
		String tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +tooltipvalue);	
		double score =  Double.parseDouble(tooltipvalue.substring(44 , 48));
		System.out.println(score);
		return score;			
	}
	
	public int GRLoc() throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth())/2 -2, 0).click().perform();
		String tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +tooltipvalue);
		int numberoflocations = Integer.parseInt(tooltipvalue.substring(31 , 32));
		System.out.println(numberoflocations);
		return numberoflocations;	
	}
	
	/**
	 * @return History graph value read
	 */
	public List<Map<String, String>> verifyGRHistoryGraph() {
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
		tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +tooltipvalue);
		rows = grphtooltip.findElements(By.tagName("span"));
		String[][] table = readTable(grphtooltip);
		List<Map<String, String>> tooltipdata = new ArrayList<Map<String, String>>();
			for (int i = 0; i < table.length; i += 4) {
				Map<String, String> kMap = new HashMap<String, String>();
				kMap.put("Date", table[i][0]);
				kMap.put(table[i + 1][0], table[i + 1][1]);
				kMap.put(table[i + 2][0], table[i + 2][1]);
				tooltipdata.add(kMap);
			}
			return tooltipdata;
	}			
	}
