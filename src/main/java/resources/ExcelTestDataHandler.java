package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTestDataHandler {

	private  FileInputStream fis = null;
	private  XSSFWorkbook workbook = null;
	private  XSSFSheet sheet = null;
	private  XSSFRow row = null;
	private  XSSFCell cell = null;
	
	public String filePath="";
	public String sheetName = "";
	public int sheetIndex;
	
	public ExcelTestDataHandler(String filePath, String sheetName) throws Exception {
		this.filePath = filePath;
		this.sheetName=sheetName;
		fis = new FileInputStream(this.filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(this.sheetName);
		fis.close();
	}
	
	public ExcelTestDataHandler(String file, int index) throws IOException {
		// TODO Auto-generated constructor stub
		fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		this.sheetIndex=index;
		sheet = workbook.getSheetAt(this.sheetIndex);
		this.sheetName = sheet.getSheetName();
		fis.close();
	}
	
	public void replaceCellValue(String searchValue, String newValue) throws Exception {
		int row = new ExcelTestDataHandler(filePath, sheetName).getRowCount();
		for(int i=0;i<=row;i++) {
			int col = new ExcelTestDataHandler(filePath, sheetName).getColCount(i);
			for(int j=0;j<col;j++) {
				String cellData = new ExcelTestDataHandler(filePath, sheetName).getCellValue(i, j).trim();
				if(cellData.equals(searchValue)) {
					setCellValue(i, j, newValue);
					System.out.println("value changed");
				}
			}
		}
		
		fis.close(); //Close the InputStream
        FileOutputStream output_file =new FileOutputStream(new File(filePath));  //Open FileOutputStream to write updates
        workbook.write(output_file); //write changes
        output_file.close();  //close the stream
	}
	
	
	private void setCellValue(int rownum, int colNum, String newValue) {
		cell=workbook.getSheet(sheetName).getRow(rownum).getCell(colNum);
		cell.setCellValue(newValue);
	}
	
	
	public String getCellValue(int rowNum,int column) {
		String value="";
		try {
			//System.out.println("row num: "+rowNum+"\n cellNum :"+column);
			cell=sheet.getRow(rowNum).getCell(column);
			if(cell!=null) {
				value=cell.toString().replace("?", "");//.replaceAll("[^\\p{Print}]", "");
			}
			fis.close(); //Close the InputStream
            FileOutputStream output_file =new FileOutputStream(new File(filePath));  //Open FileOutputStream to write updates
            workbook.write(output_file); //write changes
            output_file.close();  //close the stream 
		}
		catch(NullPointerException e) {
			System.out.println("current cell value is null");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public void getAllCellsData() throws Exception {
		int row = new ExcelTestDataHandler(filePath, sheetName).getRowCount();
		System.out.println("number of rows : "+row+"\n");
		for(int i=0;i<=row;i++) {
			int col = new ExcelTestDataHandler(filePath, sheetName).getColCount(i);
			System.out.println("Cell Values in Row Number : "+i);
			for(int j=0;j<col;j++) {		
				//System.out.print(new ExcelTestDataHandler(filePath, sheetName).getValue(i, j)+"   ");
				System.out.print(new ExcelTestDataHandler(filePath, sheetName).getCellValue(i, j)+"  ");
			}
			System.out.println("\n");
		}
	}
	
	//My Change@Rahul
	/**Returns excel as a 2d array
	 * @return 
	 * @throws Exception
	 */
	public String[][] getExcelTable() throws Exception {
		ExcelTestDataHandler excel	= new ExcelTestDataHandler(this.filePath, this.sheetName);
		int row = excel.getRowCount();
		String[][] rows = new String[row+1][];
		System.out.println("total no of rows is"+ row);
		String[] cellValues ;
		for(int i=0;i<=row;i++) {
			Row currentrow = sheet.getRow(i);
			System.out.println("current row is"+i);
			int col = currentrow.getLastCellNum();
			cellValues = new String[col];
			System.out.println("total no of columns is"+ col);
			for(int j=0;j<col;j++) {	
				cellValues[j] = new ExcelTestDataHandler(filePath, sheetName).getCellValue(i, j);
			}
					
			rows[i] = cellValues;
			System.out.println(Arrays.toString(rows[i]));
		}return rows;
	}
	
	public int getRowCount() {
		int rowCount=0;
		try {
			rowCount=sheet.getLastRowNum();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int getColCount(int row) {
		int colCount=0;
		try {
			colCount = sheet.getRow(row).getLastCellNum();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return colCount;
	}
	
	public void createRowInExcel(int numberOfRows, int numberOfCellsInRow, String[] cellValues) throws Exception{
		
		/*FileInputStream fsIP= new FileInputStream(new File(path));
		Workbook wb=WorkbookFactory.create(new FileInputStream(path));*/
		int arrVal = 0;
		int rows = getRowCount();
		int totalRows = rows+numberOfRows;
		for(int i=rows;i<totalRows;i++) {
			int temp=i+1;
			Row newRow = sheet.createRow(temp); 
			for(int j=0;j<numberOfCellsInRow;j++) {
				newRow.createCell(j).setCellValue(cellValues[arrVal++]);
			}
		}
	
		fis.close(); //Close the InputStream
        FileOutputStream output_file =new FileOutputStream(new File(filePath));  //Open FileOutputStream to write updates
        workbook.write(output_file); //write changes
        output_file.close();  //close the stream 
	}
	
	public void deleteRows() throws IOException, EncryptedDocumentException, InvalidFormatException {
		//XSSFWorkbook workbook = null;
	   // XSSFSheet sheet = null;
		
		//fis = new FileInputStream(path);
		//FileInputStream fis= new FileInputStream(new File(path));
		//workbook = new XSSFWorkbook(fis);
		//sheet=workbook.getSheet(sheetName);
		
		/*FileInputStream fsIP= new FileInputStream(new File(path));
		Workbook wb=WorkbookFactory.create(new FileInputStream(path));
		Sheet sheetN = wb.getSheet(sheetName);*/
		
		 for(int i = 0; i < sheet.getLastRowNum(); i++){
			 
			 Row rowN = workbook.getSheet(sheetName).getRow(i);
			 //row=sheet.getRow(i);
		        boolean isRowEmpty=ReadExcel.checkIfRowIsEmpty(rowN);
				if(sheet.getRow(i)==null){
		            isRowEmpty=true;
		            sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
		            i--;
		        continue;
		        }
		        for(int j =0; j<sheet.getRow(i).getLastCellNum();j++){
		            if(sheet.getRow(i).getCell(j).toString().trim().equals("")){
		                isRowEmpty=true;
		            }else {
		                isRowEmpty=false;
		                break;
		            }
		        }
		        if(isRowEmpty==true){
		        	sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
		            i--;
		        }
		    }
		 
		 /*fsIP.close(); //Close the InputStream
	        FileOutputStream output_file =new FileOutputStream(new File(path));  //Open FileOutputStream to write updates
	        wb.write(output_file); //write changes
	        output_file.close();  //close the stream 
*/	        
		 fis.close(); //Close the InputStream
         FileOutputStream output_file =new FileOutputStream(new File(filePath));  //Open FileOutputStream to write updates
         workbook.write(output_file); //write changes
         output_file.close();  //close the stream 
	}
	
	public boolean deleteEmptyRows() throws IOException {

	    /*XSSFWorkbook workbook = null;
	    XSSFSheet sheet = null;*/

	    try {
	       /* FileInputStream file = new FileInputStream(new File(excelPath));
	        workbook = new XSSFWorkbook(file);
	        sheet = workbook.getSheet(sheetName);*/
	        if (sheet == null) {
	            return false;
	        }
	        int lastRowNum = sheet.getLastRowNum();
	        for(int j=0;j<=lastRowNum;j++) {
	        	once:{
		        int i = 0;
				while(i<=lastRowNum) {
					XSSFRow row = workbook.getSheet(sheetName).getRow(i);
					if(ReadExcel.checkIfRowIsEmpty(row)) {
						 lastRowNum = sheet.getLastRowNum();
						 System.out.println("lastRowNum : "+ lastRowNum);
					        if (i >= 0 && i < lastRowNum) {
					        	XSSFRow removingRow=sheet.getRow(i);
					            if(removingRow != null) {
					                sheet.removeRow(removingRow);
					                sheet.shiftRows(i + 1, lastRowNum, -1);
					                System.out.println("Row removed and shifted");
					            }
					        }
					        if (i == lastRowNum) {
					            XSSFRow removingRow=sheet.getRow(i);
					            if(removingRow != null) {
					                sheet.removeRow(removingRow);
					            }
					        }
					break once;
					}
					i++;
				}
	        }
	        }

	        fis.close();
	        FileOutputStream outFile = new FileOutputStream(new File(filePath));
	        workbook.write(outFile);
	        outFile.close();
	    } catch(Exception e) {
	        throw e;
	    } finally {
	        if(workbook != null)
	            workbook.close();
	    }
	    return false;
	}
//--------------------------------------------------------R-code
	/**
	 * Used to get values of a cell given row and column
	 * @param column_no : column number of a excel sheet
	 * @param row_no : Row number of a excel sheet
	 * @throws Exception 
	 */
	public  String getValue(int column_no, int row_no) throws Exception {
		
		row = sheet.getRow(row_no);
		
		if (row.getCell(column_no)!= null  && StringUtils.isNotBlank(row.getCell(column_no).toString())){
			return row.getCell(column_no).toString();
		}else return "";
	}
	
	
	/**
	 * @param name
	 * used to search for a text in a column and return its row nos
	 */
	public  List<Integer> find_Row_no(String text, int column_no){

		List<Integer> matchingRows = new ArrayList<Integer>();
		for (int i=0; i<= sheet.getLastRowNum();i++) {
			row = sheet.getRow(i);
			if (row.getCell(column_no)!= null  && StringUtils.isNotBlank(row.getCell(column_no).toString())){
					
					if(text.trim().equalsIgnoreCase(row.getCell(column_no).toString())) {
						
						matchingRows.add(i);
					}
			}
		}
		return matchingRows;
	}
	
	
	/**
	 * used to search for a pattern in a column and return its row nos
	 * @param name
	 */
	public  List<Integer> seacrh_pattern(String text, int row_no){
		
		
		row = sheet.getRow(row_no);
		List<Integer> matchingColumns = new ArrayList<Integer>();
		Pattern pattern = Pattern.compile(text);
		for (int i=0; i<= row.getLastCellNum();i++)
		{
		if (row.getCell(i)!=null) {
			Matcher matcher =pattern.matcher((row.getCell(i).toString()));
			if(matcher.find()) {
				
				matchingColumns.add(i);
			}		
		}
		}
		return matchingColumns;
				
		
	}
	
	
	/**
	 * Finds the column numbers using column name
	 * @param Column_Name
	 */
	public  List<Integer>  find_column_no(String Column_Name, int row_no) {
		
		row = sheet.getRow(row_no);
		List<Integer> matchingColumns = new ArrayList<Integer>();
		for (int i=0; i<= row.getLastCellNum();i++)
		{
		if (row.getCell(i)!=null&& row.getCell(i).toString().equalsIgnoreCase(Column_Name)) { 
			matchingColumns.add(i);			
		}
		}
		return matchingColumns;
	}
	
	public  int getrowno() {
		return sheet.getLastRowNum();
	}
	
}
