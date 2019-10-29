package twitter4jProj.org.adhithya.twitter4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import twitter4j.Status;

/**
 * Hello world!
 *
 */

//import java.util.*;
import twitter4j.*;
import twitter4jProg.Utility.StoreAsFile;
import twitter4jProg.Utility.TwitterInitializer;
import twitter4jProg.builder.TweetsConfig;

public class TweetsExtractor {

	public static void main(String[] a) {

		try {
			TwitterInitializer initTwitter = new TwitterInitializer();
			Twitter twitter = initTwitter.initTweet().getTweet(); 
			int limit = initTwitter.initTweet().getLimit();
			String user = "yadavtejashwi";
			String language = "hi";
			List<Status> statuses = new ArrayList<Status>();

			File tmpDir = new File(user);
			boolean exists = tmpDir.exists();
			if (!exists) {
				new File(user).mkdir();
			}
			try {
				int count = 0;
				int pageNum = 11;
				while (count <= 1000) {
					Paging page = new Paging();
					page.setCount(limit);
					page.setPage(pageNum);

					statuses.addAll(twitter.getUserTimeline(user, page));

					for (Status status : statuses) {
						Gson gson = new Gson();
						String rawJSON = gson.toJson(status);
						ObjectMapper objectMapper = new ObjectMapper()
								.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
						TweetsConfig tweetInfo = objectMapper.readValue(rawJSON, TweetsConfig.class);
						if (tweetInfo.getLang().equals(language) && tweetInfo.getInReplyToStatusId() <0 && tweetInfo.getRetweetedStatus() == null) {
							long userId = status.getId();
							String dateCreated = tweetInfo.getCreatedAt();
							dateCreated = dateCreated.split(",")[0];
							String fileName = user + "/" + userId +".json";
							StoreAsFile storeFile = new StoreAsFile();
							storeFile.storeJSON(rawJSON, fileName);
							System.out.println("writing for page number " + pageNum);
							count++;
						}
					}
					pageNum++;
				}

			} catch (TwitterException e) {

				e.printStackTrace();
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
