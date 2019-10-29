package twitter4jProg.Utility;

import java.io.File;
import java.io.FilenameFilter;

public class FilesUnderFolder {
	
	public File[] listFiles(String directory, String extension)
	{
		File dir = new File(directory);
		final String fileExtension = extension;
		File[] files = dir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(fileExtension);
		    }
		});
		
		return files;
	}
}
