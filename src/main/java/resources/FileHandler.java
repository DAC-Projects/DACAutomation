package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileHandler {

	/**
	 * This method is used to rename the current existing file.
	 * 
	 * @param fileToRename : pass the file which need to be rename. If file name doesn't exist 
	 * 						 will throw IOException		
	 * 
	 * @param newFileName  : pass the new name to change the name of the existing file		*/
	public static File renameTo(File fileToRename, String newFileName) throws IOException {
		File file2 = new File(newFileName);
	 
		FileUtils.moveFile(fileToRename, file2);
		if (file2.exists()) 
			return file2;
		else
			return null;
		
		
	}
}
