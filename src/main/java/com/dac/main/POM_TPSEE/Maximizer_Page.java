package com.dac.main.POM_TPSEE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class Maximizer_Page extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public static int Completedcount;
	public static int Totalcount;
	public static int ToDocount;
	public static int InProgresscount;
	public static int Ignoredcount;
	public static String RecoType;
	List<String> LPADReco = new ArrayList<String>();
	static String time_stamp;
	List<String> Locations = new ArrayList<String>();

	@FindBy(xpath = "//h3[@class = 'page-title']")
	private WebElement PageTitle;

	@FindBy(xpath = "//p[@class = 'lead']")
	private WebElement PageTitletext;

	@FindBy(xpath = "//div[@class = 'card-header']//i")
	private WebElement tooltiptext;

	@FindBy(xpath = "//div[@class = 'row card-summary-container']")
	private WebElement RecommendationOverview;

	@FindBy(xpath = "//span[@id = 'totalCount']")
	private WebElement TotalCount;

	@FindBy(xpath = "//h3[@id = 'toDoCount']")
	private WebElement ToDoCount;

	@FindBy(xpath = "//h3[@id = 'completedCount']")
	private WebElement CompletedCount;

	@FindBy(xpath = "//h3[@id = 'inProgressCount']")
	private WebElement InProgressCount;

	@FindBy(xpath = "//h3[@id = 'ignoredCount']")
	private WebElement IgnoredCount;

	@FindBy(xpath = "//div[@class = 'card-header']")
	private WebElement RecommendationPercentage;

	@FindBy(xpath = "//h3[@id = 'completedPercentage']")
	private WebElement PercentageComplete;

	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;

	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private List<WebElement> paginationLast;

	@FindBy(xpath = "//*[contains(text(),'Remind Me Later')]")
	private WebElement Walkme;

	@FindBy(xpath = "//*[@id = 'recommendationTable']")
	private WebElement Reco_Table;

	@FindBy(xpath = "//*[@id = 'recommendationTable']//thead")
	private WebElement Reco_Table_Head;

	@FindBy(xpath = "//*[@id = 'recommendationTable']//tbody//tr[@role= 'row']")
	private WebElement Reco_Table_Row;

	@FindBy(xpath = "//*[@id = 'toDoTab']")
	private WebElement To_Do_Tab;

	@FindBy(xpath = "//*[@id = 'inProgressTab']")
	private WebElement In_Progress_Tab;

	@FindBy(xpath = "//*[@id = 'completedTab']")
	private WebElement Completed_Tab;

	@FindBy(xpath = "//*[@id = 'ignoredTab']")
	private WebElement Ignored_Tab;

	@FindBy(xpath = "//button[@id='continueButton']")
	private WebElement Move_To_Progress;

	public Maximizer_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		return null;
	}

	/**
	 * to verify title and title text
	 * 
	 * @param Tit
	 * @param titText
	 */
	public void VerifyTitleText() {
		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		soft.assertEquals("Maximizer", Title);
		soft.assertEquals(
				"The Maximizer report is a checklist of optimizations to boost your online presence. Each recommendation will help improve customer experience and your overall ranking. Read Manual",
				TitleText);
		soft.assertAll();
	}

	/**
	 * Tooltip text verification
	 * 
	 * @throws Exception
	 */
	public void TooltipTextVerification() throws Exception {
		waitForElement(tooltiptext, 5);
		action.moveToElement(tooltiptext).build().perform();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String Tooltiptext = tooltiptext.getAttribute("data-content");
		System.out.println("The tooltiptext is : " + Tooltiptext);
		Assert.assertEquals(Tooltiptext,
				"Only recommendations that have been completed will count towards this score. Any tactic in progress will not be included. To refresh score, log out and back into the TransparenSEE dashboard.");
		BaseClass.addEvidence(driver, "Test to verify tooltip text", "yes");
	}

	/**
	 * Verify Recommendation count
	 */
	public void VerifyRecommendationCount() {
		waitForElement(RecommendationOverview, 3);
		String totcount = TotalCount.getText();
		Totalcount = Integer.parseInt(totcount);
		System.out.println("The total recommendation count is : " + Totalcount);

		String todo = ToDoCount.getText();
		ToDocount = Integer.parseInt(todo);
		System.out.println("The to do recommendation count is : " + ToDocount);

		String inprogress = InProgressCount.getText();
		InProgresscount = Integer.parseInt(inprogress);
		System.out.println("The In Progress Recommendation count is : " + InProgresscount);

		String completed = CompletedCount.getText();
		Completedcount = Integer.parseInt(completed);
		System.out.println("The completed recommendation count is : " + Completedcount);

		String ignored = IgnoredCount.getText();
		Ignoredcount = Integer.parseInt(ignored);
		System.out.println("The ignored recommendation count is : " + Ignoredcount);

		int totalcount = (ToDocount + InProgresscount);
		System.out.println("The total count is : " + totalcount);

		Assert.assertEquals(totalcount, Totalcount);
	}

	/**
	 * Verify Percentage
	 */
	public String verifyPercentageComplete() {
		
		
		waitForElement(RecommendationPercentage, 4);

		String Percent = PercentageComplete.getText();
		System.out.println("The percentage completed is : " + Percent);
		System.out.println("To do count is : " +ToDocount);
		System.out.println("Completed Count is : " +Completedcount);
		int percentcal = (ToDocount + Completedcount);
		System.out.println("Total to calculate percentage : " +percentcal);
		double Percentage = (Double.valueOf(Completedcount) / Double.valueOf(percentcal));
		System.out.println("Per : " +Percentage);
		Percentage = Percentage * 100;
		System.out.println("Calculated Percentage is : " +Percentage);
		long percentage = Math.round(Percentage);
		int percent = (int) percentage;

		String finalpercentage = String.valueOf(percent);
		finalpercentage = finalpercentage + "%";
		System.out.println("Percentage is : " + finalpercentage);

		//Assert.assertEquals(Percent, finalpercentage);
		
		return finalpercentage;
	}

	/**
	 * Get data from XL and compare UI data
	 * 
	 * @param UIRecoType
	 * @param UIDetails
	 * @param UIImpact
	 * @param UIBenefits
	 * @throws Exception
	 */
	public void getDatafromXLandVerify(String UIRecoType, String UIDetails, String UIImpact, String UIBenefits)
			throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Maximizer");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String XLRecoType = wb.getCellValue(i, wb.seacrh_pattern("Reco Type", 0).get(0).intValue()).trim();
			System.out.println("The recommendation type in XL is : " + XLRecoType);
			if (XLRecoType.equals(UIRecoType)) {
				String XLDetails = wb.getCellValue(i, wb.seacrh_pattern("Details", 0).get(0).intValue()).trim();
				System.out.println("The Reco details is : " + XLDetails);
				soft.assertTrue(UIDetails.contains(XLDetails),
						"Details of " + UIDetails + " not matching with " + XLDetails);
				String XLImpact = wb.getCellValue(i, wb.seacrh_pattern("Impact", 0).get(0).intValue()).trim();
				System.out.println("The impact of the reco is : " + XLImpact);
				soft.assertEquals(UIImpact, XLImpact, "Impact of " + UIImpact + " not matching with " + XLImpact);
				String XLBenefits = wb.getCellValue(i, wb.seacrh_pattern("Benefits", 0).get(0).intValue()).trim();
				System.out.println("The benifit of the reco is : " + XLBenefits);
				soft.assertEquals(UIBenefits, XLBenefits,
						"Benefits of " + UIBenefits + " not matching with " + XLBenefits);
			}
		}
	}

	/**
	 * Get Data from UI and Verify with XL
	 * 
	 * @throws Exception
	 */
	public void getDatafromUIandverifywithXLdata() throws Exception {
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			List<WebElement> RecoTableRow = driver
					.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					for (int row = 1; row < rows_count; row++) {

						WebElement RecoType = driver.findElement(
								By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])[" + row
										+ "]//td[2]"));
						String UIRecoType = RecoType.getText().trim();
						System.out.println("The UI Reco Type is : " + UIRecoType);

						WebElement Details = driver.findElement(
								By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])[" + row
										+ "]//td[3]"));
						String UIDetails = Details.getText().trim();
						System.out.println("The UI Details is : " + UIDetails);

						WebElement Impact = driver.findElement(
								By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])[" + row
										+ "]//td[4]"));
						String UIImpact = Impact.getText().trim();
						System.out.println("The Impact Type is : " + UIImpact);

						WebElement Benefits = driver.findElement(
								By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])[" + row
										+ "]//td[5]"));
						String UIBenefits = Benefits.getText().trim();
						System.out.println("The Benefits Type is : " + UIBenefits);

						getDatafromXLandVerify(UIRecoType, UIDetails, UIImpact, UIBenefits);

					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		soft.assertAll();
	}

	/**
	 * Get recommendation types from Test Data and store it in a list
	 * 
	 * @param PathofXL
	 * @param SheetName
	 * @param Col_Name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<String> GetRecoTypeUsingColName(String PathofXL, String SheetName, String Col_Name) throws Exception {
		FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
		Workbook wb = new XSSFWorkbook(excelFilePath);
		Sheet sh = wb.getSheet(SheetName);
		Row row = sh.getRow(0);
		int col = row.getLastCellNum();
		int Last_row = sh.getLastRowNum();
		int col_num = 0;
		System.out.println("" + col);
		for (int i = 0; i <= row.getLastCellNum() - 1; i++) {
			if ((row.getCell(i).toString()).equals(Col_Name)) {
				col_num = i;
				System.out.println("" + col_num);
			}
		}
		String cellValue = null;
		for (int j = 1; j <= Last_row; j++) {
			row = sh.getRow(j);
			Cell cell = row.getCell(col_num);
			if (cell != null) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue().toString();
					LPADReco.add(cellValue);
				}
				wb.close();
			}
		}
		System.out.println("Recommendations are :" + LPADReco);
		return LPADReco;
	}

	/**
	 * Ignore Recomendation and verify the reco is ignored
	 * 
	 * @throws Exception
	 */
	public void Ignore_Reco() throws Exception {
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			List<WebElement> RecoTableRow = driver
					.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					for (int row = 1; row <= rows_count; row++) {
						scrollByElement(driver
								.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
										+ row + "]//td[2])")));
						String RecoType = driver.findElement(By.xpath(
								"(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'][" + row + "]//td[2])"))
								.getText();
						System.out.println("The recommendation type in UI is : " + RecoType);
						BaseClass.addEvidence(driver, "Test to get Reco", "yes");
						for (int j = 0; j < LPADReco.size(); j++) {
							if (LPADReco.get(j).equals(RecoType)) {
								System.out.println("True : " + LPADReco.get(j));
								WebElement Ignore_Btn = driver.findElement(
										By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
												+ row + "]//td[6]//button[@id='ignoreBtn'])"));
								clickelement(Ignore_Btn);
								Thread.sleep(5000);
								WebElement Continue_Btn = driver
										.findElement(By.xpath("(//button[text() = 'Continue'])[2]")); 
								clickelement(Continue_Btn);
								JSWaiter.waitJQueryAngular();
								clickelement(Ignored_Tab);
								JSWaiter.waitJQueryAngular();
								Ignore_Tab_Verification(RecoType);
								break Outer;

							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		soft.assertAll();
	}

	/**
	 * Verification of Ignored Reco in Ignored Tab
	 * 
	 * @param Reco_Type
	 * @throws Exception
	 */
	public void Ignore_Tab_Verification(String Reco_Type) throws Exception {
		boolean val = false;
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			List<WebElement> RecoTableRow = driver
					.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					for (int row = 1; row <= rows_count; row++) {
						scrollByElement(driver
								.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
										+ row + "]//td[2])")));
						String Ignored_Reco = driver.findElement(By.xpath(
								"(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'][" + row + "]//td[2])"))
								.getText();
						System.out.println("The ignored reco is : " + Ignored_Reco);
						if (Ignored_Reco.equals(Reco_Type)) {
							val = true;
							BaseClass.addEvidence(driver, "To Verify Reco_Type in Ignored Tab", "yes");
							break Outer;
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
			if (val == false) {
				soft.fail("Ignored Recommendation not found");
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Move Recommendation to In Progress Tab
	 * 
	 * @throws Exception
	 */
	public void To_Do_to_InProgress_Reco() throws Exception {
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			List<WebElement> RecoTableRow = driver
					.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					System.out.println("The row size is : " + rows_count);
					for (int row = 1; row <= rows_count; row++) {
						scrollByElement(driver
								.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
										+ row + "]//td[2])")));
						RecoType = driver.findElement(By.xpath(
								"(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'][" + row + "]//td[2])"))
								.getText();
						System.out.println("The recommendation type in UI is : " + RecoType);
						BaseClass.addEvidence(driver, "Test to get Reco", "yes");
						for (int j = 0; j < LPADReco.size(); j++) {
							if (LPADReco.get(j).equals(RecoType)) {
								System.out.println("True : " + LPADReco.get(j));
								WebElement TakeAction_Btn = driver.findElement(
										By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
												+ row + "]//td[6]//button[@id='actionBtn'])"));
								clickelement(TakeAction_Btn);
								Thread.sleep(5000);
								WebElement Continue_Btn = driver
										.findElement(By.xpath("//button[@id = 'continueButton']"));
								clickelement(Continue_Btn);
								WebElement Download_Locations_Btn = driver
										.findElement(By.xpath("//button[@id = 'downloadButton']"));
								clickelement(Download_Locations_Btn);
								time_stamp = timeStamp();
								System.out.println("The timestamp is : " + time_stamp);
								Thread.sleep(4000);
								convertExports(getLastModifiedFile(Exportpath),
										(CurrentState.getBrowser() + time_stamp + Maximizer_Location_Export));
								Thread.sleep(5000);
								String path = Exportpath
										+ (CurrentState.getBrowser() + time_stamp + Maximizer_Location_Export);
								System.out.println("The path of the file is : " + path);
								autoSizeColumns(path);
								Thread.sleep(3000);
								Locations = GetSiteDataUsingColName(path, "Location Number");
								System.out.println("The locations are : " + Locations);
								clickelement(Move_To_Progress);
								JSWaiter.waitUntilJQueryReady();
								InProgress_Reco_Verification();
								break Outer;
							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		soft.assertAll();
	}

	/**
	 * Verification of Recommendation Type moved from To_Do Tab
	 * 
	 * @throws Exception
	 */
	public void InProgress_Reco_Verification() throws Exception {
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		clickelement(In_Progress_Tab);
		JSWaiter.waitJQueryAngular();
		boolean b = false;
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			List<WebElement> RecoTableRow = driver
					.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					System.out.println("The row size is : " + rows_count);
					for (int row = 1; row <= rows_count; row++) {
						scrollByElement(driver
								.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
										+ row + "]//td[2])")));
						String In_Progress_RecoType = driver.findElement(By.xpath(
								"(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'][" + row + "]//td[2])"))
								.getText();
						System.out.println("The recommendation type in UI is : " + In_Progress_RecoType);
						BaseClass.addEvidence(driver, "Test to get Reco", "yes");
						if (RecoType.equals(In_Progress_RecoType)) {
							b = true;
							break Outer;
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}

			if (b == false) {
				soft.fail("Recommendation Type not found");
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * autosizing the excel columns
	 * 
	 * @param lastModifiedFile
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void autoSizeColumns(String lastModifiedFile) throws IOException {
		FileInputStream excelFilePath = new FileInputStream(new File(lastModifiedFile)); // or specify the path directly
		Workbook wb = new XSSFWorkbook(excelFilePath);
		Sheet sheet = wb.getSheetAt(0);
		if (sheet.getPhysicalNumberOfRows() > 0) {
			Row row = sheet.getRow(sheet.getFirstRowNum());
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();
				sheet.autoSizeColumn(columnIndex);
				int currentColumnWidth = sheet.getColumnWidth(columnIndex);
				sheet.setColumnWidth(columnIndex, (currentColumnWidth + 2500));
			}
		}
	}

	/**
	 * To move recommendation from Ignored tab to In Progress tab and verify
	 * 
	 * @throws Exception
	 */
	public void MoveRecotoInProgressfromIgnore() throws Exception {
		clickelement(Ignored_Tab);
		JSWaiter.waitUntilJQueryReady();
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			List<WebElement> RecoTableRow = driver
					.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					System.out.println("The row size is : " + rows_count);
					for (int row = 1; row <= rows_count; row++) {
						scrollByElement(driver
								.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
										+ row + "]//td[2])")));
						RecoType = driver.findElement(By.xpath(
								"(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'][" + row + "]//td[2])"))
								.getText();
						System.out.println("The recommendation type in UI is : " + RecoType);
						BaseClass.addEvidence(driver, "Test to get Reco", "yes");
						for (int j = 0; j < LPADReco.size(); j++) {
							if (LPADReco.get(j).equals(RecoType)) {
								System.out.println("True : " + LPADReco.get(j));
								WebElement TakeAction_Btn = driver.findElement(
										By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
												+ row + "]//td[6]//button[@id='actionBtn'])"));
								clickelement(TakeAction_Btn);
								Thread.sleep(5000);
								WebElement Continue_Btn = driver
										.findElement(By.xpath("//button[@id = 'continueButton']"));
								clickelement(Continue_Btn);
								WebElement Download_Locations_Btn = driver
										.findElement(By.xpath("//button[@id = 'downloadButton']"));
								clickelement(Download_Locations_Btn);
								time_stamp = timeStamp();
								System.out.println("The timestamp is : " + time_stamp);
								Thread.sleep(4000);
								convertExports(getLastModifiedFile(Exportpath),
										(CurrentState.getBrowser() + time_stamp + Maximizer_Location_Export));
								Thread.sleep(5000);
								String path = Exportpath
										+ (CurrentState.getBrowser() + time_stamp + Maximizer_Location_Export);
								System.out.println("The path of the file is : " + path);
								autoSizeColumns(path);
								Thread.sleep(3000);
								Locations = GetSiteDataUsingColName(path, "Location Number");
								System.out.println("The locations are : " + Locations);
								clickelement(Move_To_Progress);
								JSWaiter.waitUntilJQueryReady();
								InProgress_Reco_Verification();
								break Outer;
							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		soft.assertAll();
	}

	public void clickInProgressTab() throws InterruptedException {
		JSWaiter.waitUntilJQueryReady();
		clickelement(In_Progress_Tab);
		Thread.sleep(3000);
	}
	
	public void clickCompletedTab() throws InterruptedException {
		JSWaiter.waitUntilJQueryReady();
		clickelement(Completed_Tab);
		Thread.sleep(3000);
	}
	
	public void clickRemindLater() {
		try {
			if(Walkme.isDisplayed()) {
				Walkme.click();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void MoveRecotoCompletedTab() throws Exception {
		
		ArrayList<String> RecoString = new ArrayList<String>();
		JSWaiter.waitUntilJQueryReady();
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			JSWaiter.waitJQueryAngular();
			if (paginationNext.isDisplayed()) {
				Thread.sleep(3000);
				for (int i = 1; i <= page; i++) {
					JSWaiter.waitJQueryAngular();
					Thread.sleep(3000);
					List<WebElement> RecoTableRow = driver
							.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					System.out.println("The row size is : " + rows_count);
					for (int row = 1; row <= rows_count; row++) {
						
						scrollByElement(driver
								.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
										+ row + "]//td[2])")));
						String Recos = driver.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["+ row +"]//td[2])")).getText();
						RecoString.add(Recos);
						BaseClass.addEvidence(driver, "Test to get Reco", "yes");
						
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}				
			}
			System.out.println("The list contains : " +RecoString);
			for(int j = 0; j < RecoString.size(); j++ ) {
				soft.assertTrue(LPADReco.contains(RecoString.get(j)), "does not contain : " +RecoString.get(j));
			}
			
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		soft.assertAll();
	}
	
	
	
	
	/**
	 * Ignore Recomendation and verify the reco is ignored
	 * 
	 * @throws Exception
	 */
	public void Ignore_Reco_In_Progress() throws Exception {
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if (entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " + totalentries);
			List<WebElement> RecoTableRow = driver
					.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			Outer: if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table.
					int rows_count = rows_table.size();
					for (int row = 1; row <= rows_count; row++) {
						scrollByElement(driver
								.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
										+ row + "]//td[2])")));
						String RecoType = driver.findElement(By.xpath(
								"(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'][" + row + "]//td[2])"))
								.getText();
						System.out.println("The recommendation type in UI is : " + RecoType);
						BaseClass.addEvidence(driver, "Test to get Reco", "yes");
						for (int j = 0; j < LPADReco.size(); j++) {
							if (LPADReco.get(j).equals(RecoType)) {
								System.out.println("True : " + LPADReco.get(j));
								WebElement Ignore_Btn = driver.findElement(
										By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row']["
												+ row + "]//td[6]//button[@id='ignoreBtn'])"));
								clickelement(Ignore_Btn);
								Thread.sleep(5000);
								WebElement Continue_Btn = driver
										.findElement(By.xpath("(//button[text() = 'Continue'])")); 
								clickelement(Continue_Btn);
								JSWaiter.waitJQueryAngular();
								clickelement(Ignored_Tab);
								JSWaiter.waitJQueryAngular();
								Ignore_Tab_Verification(RecoType);
								break Outer;

							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		soft.assertAll();
	}

}
