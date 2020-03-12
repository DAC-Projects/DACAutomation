package com.dac.main.api;

import automation_home.ddf.constants.ExcelConstants;
import automation_home.ddf.wrapper.Wrapper;
import automation_home.ddf.wrapperimpl.ExcelWrapper;

public class ExcelHandler {

	Wrapper wrapper = new ExcelWrapper();


	public Object[][] getData(String filePath, String sheetName, String testCaseName ) throws Exception
	{
		try{
			wrapper.setParameter(ExcelConstants.FILE_PATH, filePath );
			//		wrapper.setParameter(ExcelConstants.FILE_PATH, props.getProperty("fileShareLocation").concat(filePath));
			wrapper.setParameter(ExcelConstants.SHEET_NAME, sheetName);
			wrapper.setParameter(ExcelConstants.TESTCASE_NAME, testCaseName);
			wrapper.setParameter(ExcelConstants.TESTCASE_START_ELEMENT, "_START");
			wrapper.setParameter(ExcelConstants.TESTCASE_END_ELEMENT, "_END");
			wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_HEADER_NAME, "Execution");
			wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_YES, "RUN");
			wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_NO, "NO-RUN");
			return wrapper.retrieveTestData();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
}