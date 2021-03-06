package resources;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class formatConvert {

	String filepath;
	BufferedReader br;

	/**
	 * file path need to be convert to a specific format	*/
	public formatConvert(String filepath) throws FileNotFoundException {

		this.filepath = filepath;
		this.br = new BufferedReader(new FileReader(filepath));
	}

	/**
	 * This method is used to convert the .csv file into .xlsx file format only. 	*/
	public String convertFile(String newFormat) throws IOException {

		String fileExtension = getFileFormat(filepath);
		String newFile = null;

		switch (newFormat) {

		case "xlsx":
			newFile = toExcel(fileExtension, newFormat);

		case "txt":

			// write a new method
		case "csv":
			// write a new method

		}
		return newFile;

	}

	private String toExcel(String fileExtension, String newFormat) throws IOException {
		// create sheet
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		// read from file
		String line = br.readLine().trim().replace("﻿", "");
		//System.out.println(line); //to print each row info in console
		for (int rows = 0; line != null&& !StringUtils.isBlank(line); rows++) {
			
			// split by semicolon
			line=line.trim();
			String[] items = line.split(",");
			if(!StringUtils.containsOnly(line, "[^\\p{Print}]")) {
			//for (String str:StringUtils.split(line, ","))
			// create one row per line
			Row row = sheet.createRow(rows);
			// ignore first item
			
			for (int i = 0, col = 0; i <= items.length-1; i++) {
				// strip quotation marks
				String item = items[i].replaceAll("^\"|\"$", "");
															
						//substring(0, items[i].length() );
				Cell cell = row.createCell(col++);
				// set item
				cell.setCellValue(item);
				
			}
			//System.out.println(line+"***********"); //to print each row info in console
			// read next line
			}
		line = br.readLine();
		
		}
		// write to xlsx
		String path = FilenameUtils.getPath(filepath);
		System.out.println(path);
		
		FileOutputStream out = new FileOutputStream(path+FilenameUtils.getName(filepath).replace(FilenameUtils.getExtension(filepath), "")  + "xlsx");
		System.out.println(path+FilenameUtils.getName(filepath).replace(FilenameUtils.getExtension(filepath), "") + "xlsx");
		wb.write(out);
		// close resources
		br.close();
		out.close();
		return FilenameUtils.getName(filepath).replace(FilenameUtils.getExtension(filepath), "") + "xlsx";

	}

	private String getFileFormat(String filepath) {
	
		return FilenameUtils.getExtension(filepath);

	}
}
