package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import resources.ExcelHandler;

public class SDM  {


	String x;
	String y;
	String header1;
	String header2;
	String store1;
	String store2;
	ExcelHandler a;
	ExcelHandler b;
	ExcelHandler c;
	ArrayList<String> added;
	ArrayList<String> deleted;
	int rowcount;
	int yellow[] = { 94, 95, 96, 97, 98, 99, 100, 101, 102, 103}; 
	int green[] = { 222, 251, 253, 415}; 
	int blue[] = { 260, 262, 303, 318, 437, 439, 440, 442, 102, 103};
	int orange[] = { 319, 320, 321, 322, 323, 326, 327, 328, 330, 331};
	@Test
	public void testcase1() throws Exception {

		a = new ExcelHandler("C:\\Users\\aneeshc\\Desktop\\SF2\\SDM - sourcefile1.xlsx", "Store Data 081820");
		b = new ExcelHandler("C:\\Users\\aneeshc\\Desktop\\SF2\\SDM - sourcefile2.xlsx", "Store Data 081820");
		c =  new ExcelHandler("C:\\Users\\aneeshc\\Desktop\\SF2\\Shoppers_newUmbracofields_sample.xlsx", "New report sample");

		rowcount =a.getrowno();
		
		x= a.getCellValue(1, green[0]-1);
System.out.println(x);



		readdata(yellow,"Beauty Brand Added Services","Beauty Brand Deleted Services");
		readdata(green,"Department  Added Services","Department  Deleted Services");
		readdata(blue,"Service & Facilities  Added Services","Service & Facilities  Deleted Services");
		readdata(orange,"Health Service  Added Services","Health Service  Deleted Services");
		//List<Integer> data = a.seacrh_pattern("CXB-ADE", 1);

		//System.out.println(data.);
	}




	public void readdata(int[] colNum,String addedcol, String deletedcol)  throws Exception {


		for(int i=2;i<20;i++) {

			store1=a.getCellValue(i, 0);
			store2=b.getCellValue(i, 0);

			if(store1.equals(store2)) {
				c.setCellValue(i-1, c.seacrh_pattern("Reference Code", 0).get(0).intValue(),store1.replace(".0", ""));

				//System.out.println("i value"+i);
				added = new ArrayList<String>();
				deleted = new ArrayList<String>();
				
				
				
				for(int j=0;j<colNum.length;j++) {				

					header1=a.getCellValue(1, colNum[j]-1);
					header2=b.getCellValue(1, colNum[j]-1);

					if(header1.equals(header2)) 
					{
						

						x= a.getCellValue(i, colNum[j]-1);
						y= b.getCellValue(i, colNum[j]-1);


						if(!x.equals(y)) {

							if(x.equals("TRUE")) {
								deleted.add(header1);

							}
							else {
								added.add(header1);
							}


						}


					}
					else {
						System.err.println("HEader mismatch in column "+colNum[j]);
					}
				}

				if(!added.isEmpty()) {
					Collections.sort(added);
					String addedStr = Arrays.toString(added.toArray()).replaceAll("\\[|\\]", "");
					c.setCellValue(i-1, c.seacrh_pattern(addedcol, 0).get(0).intValue(),addedStr);
					System.out.println("row"+i +"added "+addedStr);
				}
				if(!deleted.isEmpty()) {
					Collections.sort(deleted);
					String deletedStr = Arrays.toString(deleted.toArray()).replaceAll("\\[|\\]", "");
					c.setCellValue(i-1, c.seacrh_pattern(deletedcol, 0).get(0).intValue(), deletedStr);
					System.out.println("row"+i +"deleted "+deletedStr);
				}
			}

			else {
				System.err.println("Store number mismatch for row "+i);
			}

		}

	}


}