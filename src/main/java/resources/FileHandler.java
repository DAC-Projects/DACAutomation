package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileHandler {

	public static File renameTo(File file, String name) throws IOException {
		File file2 = new File(name);
	 
		FileUtils.moveFile(file, file2);
		if (file2.exists()) 
			return file2;
		else
			return null;
		
		
	}
}
