package twitter4jProj.org.adhithya.twitter4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class TweetsRenamer {
	
	public static void main(String args[])
	{
		String userName = "rssurjewala";
		JsonParser parser = new JsonParser();
		try {
			JsonArray pathFile = (JsonArray) parser.parse(new FileReader(userName+"/renameFiles.json"));
			int fileLength = pathFile.size();
			for(int i=0; i<fileLength;i++)
			{
				File oldFile;
				File newFile;
				try
				{
					String fileName = pathFile.get(i).getAsString();
				    oldFile = new File(userName+"/"+fileName+".json");
					Object obj = parser.parse(new FileReader(userName+"/"+fileName+".json"));
					JsonObject data = (JsonObject) obj;
					String fileId = data.get("id").getAsString();
					String newFileName = fileName.replace("-1", fileId);
					newFile = new File(newFileName+".json");
					boolean b = oldFile.renameTo(newFile);
				if(b)
				{
					System.out.println("Rename sucessful");
				}
				}
				catch(FileNotFoundException e)
				{
					
				}
			}
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
