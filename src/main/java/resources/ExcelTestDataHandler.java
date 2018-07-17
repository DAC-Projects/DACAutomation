package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

	private static FileInputStream fis = null;
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow row = null;
	private static XSSFCell cell = null;
	
	public String filePath="";
	public String sheetName = "";
	
	public ExcelTestDataHandler(String filePath, String sheetName) throws Exception {
		this.filePath = filePath;
		this.sheetName=sheetName;
		fis = new FileInputStream(this.filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(this.sheetName);
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
				value=cell.toString();
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
				System.out.print(new ExcelTestDataHandler(filePath, sheetName).getCellValue(i, j)+"   ");
			}
			System.out.println("\n");
		}
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
		int rows = getRowCount();
		int totalRows = rows+numberOfRows;
		for(int i=rows;i<totalRows;i++) {
			int temp=i+1;
			Row newRow = sheet.createRow(temp); 
			for(int j=0;j<numberOfCellsInRow;j++) {
				newRow.createCell(j).setCellValue(cellValues[j]);
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

}
