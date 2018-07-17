package resources;

import java.io.FileInputStream;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static FileInputStream fis = null;
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	private static ArrayList<String[]> arrayofSteps;
	public static String Testcase;
	

	public ReadExcel(String xlFilePath) throws Exception {
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();

	}

	public ArrayList<String[]> getTestcases(String sheetName, String searchKey) {
		try {
			int col_Num = -1;
			int row_Num = -1;
			int testcase_row_Num = -1;
			String testStep;
			String expctedRsult;
			String scrnCaptrNeed;
			XSSFCell Cell;

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				if (row.getCell(i).getStringCellValue().trim().equals("ID"))
					col_Num = i;
			}

			// **************************************************************************************

			/*
			 * it gets column no of ID Search for particular testcaseID in ID column
			 */

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if (!(checkIfRowIsEmpty(row))&& row.getCell(col_Num)!= null  && StringUtils.isNotBlank(row.getCell(col_Num).toString() )) {
				String cellValue = row.getCell(col_Num).toString();
					if (cellValue.trim().equalsIgnoreCase(searchKey))
					{
					testcase_row_Num = i;
					System.out.println("search key found"+searchKey);
					break;
				}else System.out.println("Search key not found"+searchKey);
				
				}

			}

			/* Then read data from the next cell(Testcase name ) return a s a string */
			int testcaseName_col = col_Num + 1;
			Testcase = row.getCell(testcaseName_col).getStringCellValue().trim();
			System.out.println(Testcase);
			int testStep_col = testcaseName_col + 1;
			int expctedRsult_col = testStep_col + 1;
			int scrnCaptrNeed_col = expctedRsult_col + 1;

			/* Declare a two dimensional array to store cell steps and expected value */

			arrayofSteps = new ArrayList<String[]>();
			arrayofSteps.clear();

			for (int i = testcase_row_Num + 1; i <= sheet.getLastRowNum(); i++)

			{ // getting values of teststep , expected result and scrn capture nedded clumns
				// from excel and adding to array list

				row = sheet.getRow(i);
				
				
				if (checkIfRowIsEmpty(row)) {
					// check whether its an empty row
					break;
				}
				cell = row.getCell(testStep_col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				if (null != cell) {

					testStep = row.getCell(testStep_col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
							.getStringCellValue();

					expctedRsult = row.getCell(expctedRsult_col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
							.getStringCellValue();

					scrnCaptrNeed = row.getCell(scrnCaptrNeed_col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
							.getStringCellValue();

					String Step[] = { testStep, expctedRsult, scrnCaptrNeed };

					arrayofSteps.add(Step);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// return "row " + rowNum + " or column " + colName + " does not exist in
			// Excel";
		}
		return arrayofSteps;
	}

	public ArrayList<String> getScreenshotNames(String sheetName, String searchKey) {
		int counter = 0;
		ArrayList<String> imgnames = new ArrayList<String>();
		arrayofSteps = getTestcases(sheetName, searchKey);
		for (String[] step : arrayofSteps) {
			if (step[2].equalsIgnoreCase("yes")) {
				counter += 1;
				// imgnames.add("_"+counter);

				imgnames.add(ReadExcel.Testcase + "_" + counter);
				System.out.println("image array: " + imgnames.toString());

			}
		}

		return imgnames;
	}

	public void getImagesIntoArray(String testcasePath, String Sheet, String ID) throws Exception {

		ReadExcel re = new ReadExcel(testcasePath);

		ArrayList<String> imgnames = new ArrayList<String>();
		ArrayList<String[]> arraySteps = new ArrayList<>();
		imgnames = re.getScreenshotNames(Sheet, ID);
		arraySteps = re.getTestcases(Sheet, ID);
	}
	
	 public static boolean checkIfRowIsEmpty(Row row) {
		    if (row == null) {
		        return true;
		    }
		    if (row.getLastCellNum() <= 0) {
		        return true;
		    }
		    for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
		        Cell cell = row.getCell(cellNum);
		        if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())) {
		            return false;
		        }
		    }
		    return true;
		}

}
