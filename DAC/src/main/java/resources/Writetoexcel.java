package resources;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Writetoexcel {

	
    public void writeExcel(String filePath,String fileName,String sheetName,ArrayList<String> data) throws IOException{

        //Create an object of File class to open xlsx file

        File file =    new File(filePath+"\\"+fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = null;

        //Find the file extension by splitting  file name in substring and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){

        //If it is xlsx file then create object of XSSFWorkbook class

        workbook = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if(fileExtensionName.equals(".xls")){

            //If it is xls file then create object of XSSFWorkbook class

            workbook = new HSSFWorkbook(inputStream);

        }

        

    //Read excel sheet by sheet name  
        System.out.println(sheetName);

    Sheet sheet1 = workbook.getSheet(sheetName);

    //Get the current count of rows in excel file

 

    int rowCount = sheet1.getLastRowNum() - sheet1.getFirstRowNum();

    //Get the first row from the sheet

    Row row = sheet1.getRow(0);

    //Create a new row and append it at last of sheet

    Row newRow = sheet1.createRow(rowCount+1);

    //Create a loop over the cell of newly created Row
    CellStyle cs = workbook.createCellStyle();
    cs.setWrapText(true); 
    
   // for(int j = 0; j < row.getLastCellNum(); j++)

    for(int j = 0; j < data.size(); j++){

        //Fill data in row

        Cell cell = newRow.createCell(j);
        cell.setCellStyle(cs);

        cell.setCellValue(data.get(j));

    }

    //Close input stream

    inputStream.close();

    //Create an object of FileOutputStream class to create write data in excel file

    FileOutputStream outputStream = new FileOutputStream(file);

    //write data in the excel file

    workbook.write(outputStream);

    //close output stream

    outputStream.close();

    

    }

    

   
    
}
