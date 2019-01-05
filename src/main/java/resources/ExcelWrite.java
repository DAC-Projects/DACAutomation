package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelWrite {
	private  XSSFWorkbook workbook ;
	private  XSSFSheet sheet = null;
	private  XSSFRow row = null;
	private  XSSFCell cell = null;
	int rowno=0;
	
	/**
	 * create a new excel based on parameter passed as excel file name to create
	 * 
	 * @param name
	 */
	public void createExcel(String name) {
		workbook =  new XSSFWorkbook();
		try {
			FileOutputStream out = 
					new FileOutputStream(new File(name));
			workbook.write(out);
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
	}

	
	/**
	 * Accepts a map(consist of rowno as string and values as Obj array), sheet name to create, excel name
	 * writes the map into the excel by creating a new sheet
	 * 
	 * @param data
	 * @param sheetName
	 * @param excelName
	 * @throws IOException		 */
	public void write(Map<String, Object[]> data, String sheetName, String excelName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(excelName));
		workbook =  new XSSFWorkbook(fis);
		sheet = workbook.createSheet(sheetName);
		Set<String> keyset = data.keySet();
		
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		
		try {
			FileOutputStream out = 
					new FileOutputStream(new File(excelName));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	


	

	

}