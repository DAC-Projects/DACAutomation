package resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParse {
	
	static String fileName;
	static String ID;
	static String sheet;
	static String className;
	static String Iterationpath;
	
	public JsonParse(String fileName, String className, String method) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		getTestcaseID(className, method);
	}
	
	
	//ORIGINAL METHOD
	/*public static   void getTestcaseID(String className) throws FileNotFoundException, IOException, ParseException {
		System.out.println(fileName);
		Object obj = new JSONParser().parse(new FileReader(fileName));
        
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
         
        Iterationpath=   jo.get("iterationPath").toString();
        System.out.println("The iteration path is: "+ Iterationpath);
              
        // getting sheets
        Map Sheets = ((Map)jo.get(className));
         
        // iterating Sheets Map
        Iterator<Map.Entry> itr1 = Sheets.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
 
            if (pair.getValue()!=null) {
            	sheet= pair.getKey().toString();
            	ID =pair.getValue().toString();
            	break;
            }
        }
	}*/
	
	//MY CHANGES
	public static   void getTestcaseID(String className, String method) throws FileNotFoundException, IOException, ParseException {
		System.out.println(fileName);
		Object obj = new JSONParser().parse(new FileReader(fileName));
        
        // typecasting obj to JSONObject
        JSONObject js = (JSONObject) obj;
         
        Iterationpath=   js.get("iterationPath").toString();
        System.out.println("The iteration path is: "+ Iterationpath);
              
        JSONObject jo = (JSONObject) js.get(className);
        sheet = (String) jo.get("sheet");
        ID = (String) jo.get(method);
        
     /*   // getting sheets
        Map Sheets = ((Map)jo.get(className));
         
        // iterating Sheets Map
        Iterator<Map.Entry> itr1 = Sheets.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
 
            if (pair.getValue()!=null) {
            	sheet= pair.getKey().toString();
            	ID =pair.getValue().toString();
            	break;
            }*/
//        }
	}

	public static String getSheet() throws FileNotFoundException, IOException, ParseException {
		return sheet;
     }
     
     public static String getID() throws FileNotFoundException, IOException, ParseException {
    	 return ID;
     }
     
     public static String getIterationPath() throws FileNotFoundException, IOException, ParseException {  	
      	  return Iterationpath; 
     }
}
