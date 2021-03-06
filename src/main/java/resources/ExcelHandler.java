package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class is used to handle the actions to perform on the excel sheet	*/
public class ExcelHandler {

	private  FileInputStream fis = null;
	private  XSSFWorkbook workbook = null;
	private  XSSFSheet sheet;// = null;
	private  Row row = null;
	private  XSSFCell cell = null;

	public String filePath="";
	public String sheetName = "";
	public int sheetIndex;

	public ExcelHandler() {}

	public ExcelHandler(String filePath, String sheetName) throws Exception {
		this.filePath = filePath;
		this.sheetName=sheetName;
		fis = new FileInputStream(this.filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		fis.close();
	}

	public ExcelHandler(String file, int index) throws IOException {
		fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		this.sheetIndex=index;
		sheet = workbook.getSheetAt(this.sheetIndex);
		this.sheetName = sheet.getSheetName();
		fis.close();
	}

	/**
	 * create a new excel based on parameter passed as excel file name to create
	 * 
	 * @param excelFileName : name of an excel file to create
	 */
	public void createExcel(String excelFileName) {
		workbook =  new XSSFWorkbook();
		try {
			FileOutputStream out = 
					new FileOutputStream(new File(excelFileName));
			workbook.write(out);
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Accepts a map(consist of rowno as string and values as Obj array), sheet  to create, excel name
	 * writes the map into the excel by creating a new sheet
	 * 
	 * @param data
	 * @param rowNum : from which row number data need to write.
	 * @param colNum : from which cell number of a row need to write the data
	 * @throws IOException		 */
	public void write(Map<String, Object[]> data, int rowNum, int colNum) throws IOException {
		Set<String> keyset = data.keySet();

		int rownum = rowNum;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = colNum;
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
				else if(obj instanceof Long)
					cell.setCellValue((Long)obj);
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}

		try {
			fis.close(); //Close the InputStream
			FileOutputStream output_file =new FileOutputStream(new File(filePath));  //Open FileOutputStream to write updates
			workbook.write(output_file); //write changes
			output_file.close();  //close the stream
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void replaceCellValue(String searchValue, String newValue) throws Exception {
		int row = new ExcelHandler(filePath, sheetName).getRowCount();
		for(int i=0;i<=row;i++) {
			int col = new ExcelHandler(filePath, sheetName).getColCount(i);
			for(int j=0;j<col;j++) {
				String cellData = new ExcelHandler(filePath, sheetName).getCellValue(i, j).trim();
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
		System.out.println("Excel value replaced successfully..");
	}


	private void setCellValue(int rownum, int colNum, String newValue) {
		cell=workbook.getSheet(sheetName).getRow(rownum).getCell(colNum);
		cell.setCellValue(newValue);
	}


	public String getCellValue(int rowNum,int column) {
		String value="";
		try {
			//System.out.println("row num: "+rowNum+"\n cellNum :"+column);
			//sheet.autoSizeColumn(column);
			cell=sheet.getRow(rowNum).getCell(column);
			if(cell!=null) {
				value=cell.toString().replace("?", "");//.replaceAll("[^\\p{Print}]", "");
			}
			/*fis.close(); //Close the InputStream
            FileOutputStream output_file =new FileOutputStream(new File(filePath));  //Open FileOutputStream to write updates
            workbook.write(output_file); //write changes
            output_file.close();  //close the stream 
			 */		}
		catch(NullPointerException e) {
			System.out.println("current cell value is null");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void getAllCellsData() throws Exception {
		int row = new ExcelHandler(filePath, sheetName).getRowCount();
		System.out.println("number of rows : "+row+"\n");
		for(int i=0;i<=row;i++) {
			int col = new ExcelHandler(filePath, sheetName).getColCount(i);
			System.out.println("Cell Values in Row Number : "+i);
			for(int j=0;j<col;j++) {		
				//System.out.print(new ExcelTestDataHandler(filePath, sheetName).getValue(i, j)+"   ");
				System.out.print(new ExcelHandler(filePath, sheetName).getCellValue(i, j)+"  ");
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
		System.out.println("Reading excel------");
		ExcelHandler excel	= new ExcelHandler(this.filePath, this.sheetName);
		int row = excel.getRowCount();
		String[][] rows = new String[row+1][];
		System.out.println("total no of rows is"+ row);
		String[] cellValues ;
		for(int i=0;i<=row;i++) {
			Row currentrow = sheet.getRow(i);
			int col = currentrow.getLastCellNum();
			
			cellValues = new String[col];
			for(int j=0;j<col;j++) {	
				//sheet.autoSizeColumn(j);
				cellValues[j] = new ExcelHandler(filePath, sheetName).getCellValue(i, j);
			}

			rows[i] = cellValues;
			System.out.println("Array values"+Arrays.toString(rows[i]));
		}return rows;

	}

	public String[][] getExcelTableWithout0() throws Exception {
		String flag;
		int count = 0;
		int col = 0;
		System.out.println("Reading excel------");
		ExcelHandler excel	= new ExcelHandler(this.filePath, this.sheetName);
		int row = excel.getRowCount();
		String[][] rows = new String[row+1][];
		System.out.println("total no of rows is"+ row);
		String[] cellValues ;
		for(int i=0;i<=row;i++) {
			flag = "0";
			Row currentrow = sheet.getRow(i);
			col = currentrow.getLastCellNum();
			cellValues = new String[col];
			for(int j=0;j<col;j++) {	
				cellValues[j] = new ExcelHandler(filePath, sheetName).getCellValue(i, j);

				if(j!=0) {					
					if(!cellValues[j].equals("0")) {
						System.out.println("flag set to 1" +cellValues[j]);
						flag = "1";
					}
				}
			}
			if(flag.contains("1")) {
				rows[count] = cellValues;
				System.out.println("rw value "+count+Arrays.toString(rows[count]));
				count++;
			}

		}
		String[][] newarray = new String[count][];
		for (int i = 0; i < count; ++i) {
			newarray[i] = new String[col];
			for (int j = 0; j < col; ++j) {
				newarray[i][j] = rows[i][j];
			}
		}
		System.out.println(Arrays.deepToString(newarray));



		return newarray;


	}


public String[][] getExcelTableNew() throws Exception {
	System.out.println("Reading excel------");
	ExcelHandler excel	= new ExcelHandler(this.filePath, this.sheetName);
	int row = excel.getRowCount();
	int colum = excel.getColCount(0);
	System.out.println("row count :"+row+" Col count : "+colum);

	String[][] rows = new String[row+1][colum-1];
	System.out.println("total no of rows is"+ row);
	String[] cellValues ;
	for(int i=0;i<=row;i++) {
		Row currentrow = sheet.getRow(i);
		int col = currentrow.getLastCellNum();
		cellValues = new String[col];
		for(int j=0;j<col;j++) {	
			cellValues[j] = new ExcelHandler(filePath, sheetName).getCellValue(i, j);
		}

		rows[i] = cellValues;
		System.out.println("excel row "+i+Arrays.toString(rows[i]));
	}
	System.out.println(" excle array sze"+rows.length+" "+rows[0].length);
	return rows;
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

public void deleteRows() throws IOException {
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

		Row rowN = sheet.getRow(i);
		//row=sheet.getRow(i);
		boolean isRowEmpty=checkIfRowIsEmpty(rowN);
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
				Row row = workbook.getSheet(sheetName).getRow(i);
				if(checkIfRowIsEmpty(row)) {
					lastRowNum = sheet.getLastRowNum();
					System.out.println("lastRowNum : "+ lastRowNum);
					if (i >= 0 && i < lastRowNum) {
						Row removingRow=sheet.getRow(i);
						if(removingRow != null) {
							sheet.removeRow(removingRow);
							sheet.shiftRows(i + 1, lastRowNum, -1);
							System.out.println("Row removed and shifted");
						}
					}
					if (i == lastRowNum) {
						Row removingRow=sheet.getRow(i);
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
 * @param fromRow : from which row number you want to search for a text
 * 					from header-->0
 * 					from 1st row data --> 1								
 * used to search for a text in a column and return its row nos
 */
public List<Integer> find_Row_no( List<?> expectedValue, int fromRow, int column_no) throws IOException{

	List<Integer> matchingRows = new ArrayList<Integer>();
	//System.out.println("matchingRows.size() : "+matchingRows.size());
	for (int i = fromRow; i <= sheet.getLastRowNum();i++) {
		XSSFRow row = sheet.getRow(i);
		if (row.getCell(column_no)!= null && StringUtils.isNotBlank(row.getCell(column_no).toString())){
			for (Object obj : expectedValue) {
				if(obj instanceof Date) {
					if((Date)(row.getCell(column_no).getDateCellValue()) == (Date)obj) {
						matchingRows.add(i);
					}
				}
				else if(obj instanceof Boolean) {
					if((boolean)(row.getCell(column_no).getBooleanCellValue()) == (boolean)obj) {
						matchingRows.add(i);
					}
				}
				else if(obj instanceof String) {
					if(((String)obj).trim().equalsIgnoreCase(row.getCell(column_no).toString())) {
						matchingRows.add(i);
					}
				}
				else if(obj instanceof Double) {
					if((double)(row.getCell(column_no).getNumericCellValue()) == (double)obj) {
						matchingRows.add(i);
					}
				}
				else if(obj instanceof Long) {						
					if(((long)(row.getCell(column_no).getNumericCellValue())) == ((Long)obj).longValue()) {					
						matchingRows.add(i);
					}
				}
				else if(obj instanceof Integer) {
					if((int)(row.getCell(column_no).getNumericCellValue()) == (int)obj) {
						matchingRows.add(i);
					}
				}
			}
		}
	}
	//System.out.println("matchingRows.size() : "+matchingRows.size());
	return matchingRows;
}


/*public  List<Integer> find_Row_no(String[] text, int fromRow, int column_no){

		List<Integer> matchingRows = new ArrayList<Integer>();
		for (int i=0; i<= sheet.getLastRowNum();i++) {
			row = sheet.getRow(i);
			if (row.getCell(column_no)!= null  && StringUtils.isNotBlank(row.getCell(column_no).toString())){
				for( String a : text ) {
					if(a.trim().equalsIgnoreCase(row.getCell(column_no).toString())) {
						matchingRows.add(i);
					}
				}
			}
		}
		return matchingRows;
	}*/


/**
 * used to search for a pattern in a row and return its col nos
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


/**
 * @param row Accepts row as parameter
 * @return True if row is empty Else return False
 */
public boolean checkIfRowIsEmpty(Row row) {
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


/*public static void setCriteriaFilter(XSSFSheet sheet, int colId, int firstRow, int lastRow, String[] criteria) throws Exception {
	  CTAutoFilter ctAutoFilter = sheet.getCTWorksheet().getAutoFilter();
	  CTFilterColumn ctFilterColumn = ctAutoFilter.addNewFilterColumn();
	  ctFilterColumn.setColId(colId);
	  CTFilters ctFilters = ctFilterColumn.addNewFilters();

	  for (int i = 0; i < criteria.length; i++) {
	   ctFilters.addNewFilter().setVal(criteria[i]);
	  }

	  //hiding the rows not matching the criterias
	  DataFormatter dataformatter = new DataFormatter();
	  for (int r = firstRow; r <= lastRow; r++) {
	     XSSFRow row = sheet.getRow(r);
	     boolean hidden = true;
	     for (int i = 0; i < criteria.length; i++) {
	        String cellValue = dataformatter.formatCellValue(row.getCell(colId));
	        if (criteria[i].equals(cellValue)) hidden = false;
	     }
	     if (hidden) row.getCTRow().setHidden(hidden);
	  }
   }*/

  
  public ArrayList<String> getExcelTablewith() throws Exception {
		
		System.out.println("Reading excel------");
		ExcelHandler excel	= new ExcelHandler(this.filePath, this.sheetName);
		System.out.println(this.sheetName);
		System.out.println(this.filePath);
		int row = excel.getRowCount();
		System.out.println("total no of rows is"+ row);
		String cellValues ;
		ArrayList<String> a = new ArrayList<String>();
	//	for(int i=3;i<=3;i++) {
			Row currentrow = sheet.getRow(3);
			int col = currentrow.getLastCellNum();
			cellValues = new String();
			for(int j=1;j<col;j++) {	
				cellValues = new ExcelHandler(filePath, sheetName).getCellValue(3, j);
				j++;
				a.add(cellValues);	
			}
			System.out.println("Array "+a);
			return a;
			
			
//		}
	}
  }
