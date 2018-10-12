package resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParse {
	
	 String fileName;
	 String ID;
	 String sheet;
	 String className;
	 String Iterationpath;
	
	public JsonParse(String fileName, String className, String method) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		getTestcaseID(className, method);
	}
	

	public    void getTestcaseID(String className, String method) throws FileNotFoundException, IOException, ParseException {
		System.out.println(fileName);
		Object obj = new JSONParser().parse(new FileReader(fileName));
        // typecasting obj to JSONObject
        JSONObject js = (JSONObject) obj;
         
        Iterationpath=   js.get("iterationPath").toString();
        System.out.println("The iteration path is: "+ Iterationpath);
              
        JSONObject jo = (JSONObject) js.get(className);
        sheet = (String) jo.get("sheet");
        ID = (String) jo.get(method);
        
   	}

	public  String getSheet() throws FileNotFoundException, IOException, ParseException {
		return sheet;
     }
     
     public  String getID() throws FileNotFoundException, IOException, ParseException {
    	 return ID;
     }
     
     public  String getIterationPath() throws FileNotFoundException, IOException, ParseException {  	
      	  return Iterationpath; 
     }
}
