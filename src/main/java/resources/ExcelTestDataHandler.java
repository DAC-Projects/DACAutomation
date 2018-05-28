package resources;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTestDataHandler {

	public static String getData(String path,String sheet,int row,int column) {
		String value="";
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));
			value=wb.getSheet(sheet).getRow(row).getCell(column).toString();
		}
		catch(Exception e) {
			
		}
		return value;
	}
	
	public static int getRowCount(String path,String sheet) {
		int rowCount=0;
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));
			rowCount=wb.getSheet(sheet).getLastRowNum();
		}
		catch(Exception e) {
			
		}
		return rowCount;
	}
	
	public static int getColCount(String path,String sheet,int row) {
		int colCount=0;
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));
			colCount=wb.getSheet(sheet).getRow(row).getLastCellNum();
		}
		catch(Exception e) {
			
		}
		return colCount;
	}
}
