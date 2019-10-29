package twitter4jProj.org.adhithya.twitter4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class PastFiveDateFinder {

	public static void main(String[] args) {
		JsonParser parser = new JsonParser();
		String userName = "MichelTemer";
		String dateValue ="Aug 30";
		String tweetFolder = "tweetstore"+"/"+userName;
		try {
			int count  = 0;
			JsonArray pathFile = (JsonArray) parser.parse(new FileReader(tweetFolder+"/pathfiles.json"));
			int fileLength = pathFile.size();
			Long minFileName = 0L;
			for(int i=0; i<fileLength;i++)
			{
				String fileName = pathFile.get(i).getAsString();
				long fileNameData = pathFile.get(i).getAsLong(); 
				Object obj = parser.parse(new FileReader(tweetFolder+"/"+fileName+".json"));
				JsonObject data = (JsonObject) obj;
				String dateStr = data.get("createdAt").getAsString();
				
				if(dateStr.contains(dateValue))
				{
					count++;
					
					if(minFileName == 0)
					{
						minFileName = fileNameData;
					}
					if(minFileName > fileNameData)
					{
						minFileName = fileNameData;
					}
				}
			}
			System.out.println("the count is "+ count);
			System.out.println(minFileName);
			
			File tmpDir = new File(tweetFolder+"/"+minFileName.toString()+"_isEnd");
			boolean exists = tmpDir.exists();
			if (!exists) {
				System.out.println("folder is created");
				new File(tweetFolder+"/"+minFileName.toString()+"_isEnd").mkdir();
			}
			System.out.println(pathFile);
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
