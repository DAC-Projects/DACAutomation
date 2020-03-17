package com.dac.main.api;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonCompare {
	
	ATUReport atu = new ATUReport();
	
	public void JSONCompareLenient(String expected, String actual ,String Text){

		try {
			if(JSONCompare.compareJSON(expected, actual, JSONCompareMode.LENIENT).failed()){
				System.err.println("fail");
				atu.atuLogAsFail(JSONCompare.compareJSON(expected, actual, JSONCompareMode.LENIENT).getMessage());
			}
			else{
		//		atu.atuLogAsPass(info, expected, actual);
				atu.atuLogAsPass(Text+" Verification Passed", expected, actual);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void JSONCompare(String expected, String actual ,String Text){

		try {
			if(JSONCompare.compareJSON(expected, actual, JSONCompareMode.NON_EXTENSIBLE).failed()){
				System.err.println(Text+" Response Verification Failed");
				atu.atuLogAsFail(Text+JSONCompare.compareJSON(expected, actual, JSONCompareMode.NON_EXTENSIBLE).getMessage(),expected, actual);
				
			}
			else{
				atu.atuLogAsPass(Text+"Response Verification Passed");
				System.out.println(Text+" Pass");	
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void JSONCompareStrict(String expected, String actual ,String Text){

		try {
			if(JSONCompare.compareJSON(expected, actual, JSONCompareMode.STRICT).failed()){
				System.err.println(Text+" fail");
				atu.atuLogAsFail(JSONCompare.compareJSON(expected, actual, JSONCompareMode.STRICT).getMessage(),expected, actual);
			}
			else{
				atu.atuLogAsPass(Text+" Verification Passed");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
