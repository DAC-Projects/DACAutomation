package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.api.ExcelHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Statefarm {

	ExcelHandler excel = new ExcelHandler();
	//String filePath ="C:\\Users\\aneeshc\\Desktop\\Statefarm - Copy.xlsx";
	String filePath ="C:\\Users\\aneeshc\\Desktop\\Statefarm.xlsx";
	String outputFilePath = "C:\\Users\\aneeshc\\Desktop\\RCF Lines - Copy.xlsx";
	//String outputFilePath = "C:\\Users\\aneeshc\\Desktop\\t.xlsx";
	String phnum;
	String expected;
	WebDriver  driver;
	String url;
	int count;
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	@BeforeClass
	public void beforeclass() throws IOException {
		WebDriverManager.chromedriver().version("83.0.4103.39").setup(); 
		driver = new ChromeDriver();
		count=2702;

		fis = new FileInputStream(outputFilePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("Sheet1");

		//	ExcelHandler exc = new ExcelHandler("outputFilePath","Sheet1");
	}

	@AfterClass
	public void afterclass() throws IOException {
		driver.quit();
		FileOutputStream fos = new FileOutputStream(outputFilePath);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}


	@DataProvider
	public Object[][] test() throws Exception
	{
		return excel.getData(filePath, "Sheet1", "ST");
	}	

	@Test(priority = 1,dataProvider = "test")
	public void tc1(HashMap<String, String> h) throws IOException {
		count++;
		expected = h.get("PhNum");

		try {
			url = h.get("URL");

			driver.get(url);
			Thread.sleep(1000);
			phnum = driver.findElement(By.id("logo-bar-phone")).getText();

			if(phnum.equals(expected)) {
				System.out.println(+count+" || "+url+" // UI value "+phnum+" excel value "+h.get("PhNum"));
			}
			else {				
				Thread.sleep(2000);
				phnum = driver.findElement(By.id("logo-bar-phone")).getText();
				if(phnum.equals(expected))
					System.out.println(+count+" || "+url+" // UI value "+phnum+" excel value "+h.get("PhNum"));
				else
				{
					System.err.println(+count+" || "+url+" //Failed  UI value "+phnum+" excel value "+h.get("PhNum"));	
				sheet.getRow(count-1).createCell(5).setCellValue("Failed");
				sheet.getRow(count-1).createCell(6).setCellValue("Incorrect Phone Number");
				//assertEquals(phnum, expected);
				}
			}
		}


		catch(Exception e){
			System.err.println(+count+" || "+url+" // Page not found ");	
			sheet.getRow(count-1).createCell(5).setCellValue("Failed");
			sheet.getRow(count-1).createCell(6).setCellValue("Page not found");

		}
	}


}

