package twitter4jProg.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import twitter4jProg.Utility.FilesUnderFolder;

public class JsonModifier {

	public static void main(String[] args) {

		String[] uniteStatesMember = { "amyklobuchar", "JoeBiden", "SenKamalaHarris", "WalshFreedom", "marwilliamson" };
		String[] indiaMember = { "girirajsinghbjp", "nishantchat", "sardanarohit", "SushantBSinha", "rssurjewala" };
		String[] brazilMember = { "Haddad_Fernando", "jairbolsonaro", "majorolimpio", "MarinaSilva", "onyxlorenzoni"};
		for (String poi : brazilMember) 
		{
			String fileDirectory = "replyTweets/" + poi;
			String extension = ".json";
			Gson gson = new Gson();
			FilesUnderFolder fileList = new FilesUnderFolder();
			File listofFile[] = fileList.listFiles(fileDirectory, extension);
			for (File currentfile : listofFile) {
				String currentFilePath = currentfile.getPath();
				try {

					JsonParser parser = new JsonParser();
					JsonElement jsonElement = parser.parse(new FileReader(currentFilePath));
					JsonObject jsonObj = jsonElement.getAsJsonObject();

					jsonObj.addProperty("country", "Brazil");
					FileWriter writeFile = new FileWriter(currentfile);
					writeFile.write(jsonObj.toString());
					writeFile.close();
					System.out.println("Writing done");
				} catch (JsonIOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
