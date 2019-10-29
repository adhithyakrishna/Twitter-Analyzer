package twitter4jProj.org.adhithya.twitter4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4jProg.Utility.StoreAsFile;
import twitter4jProg.Utility.TwitterInitializer;

public class TweetsReplyExtractor {

	public static void main(String[] args) {
		TwitterInitializer initTwitter = new TwitterInitializer();
		try {
			Twitter twitter = initTwitter.initTweet().getTweet();
			String userName = "yadavtejashwi";
			long sinceId = 1167972329734885378L;	
			long maxId = 1170462906179313664L;
			boolean isOnlySinceId = false;
			boolean switchOnSinceMaxId = true;
			getReplies(twitter, userName, sinceId, maxId, isOnlySinceId, switchOnSinceMaxId);
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

	public static void getReplies(Twitter twitter, String userName, long sinceId, long maxId, boolean isOnlySinceId, boolean switchOnSinceMaxId) {
		String screenName = userName;
		try {
			Query query = new Query("to:" + screenName);
			query.setUntil("2019-09-08");
			if(switchOnSinceMaxId)
			{
				if(isOnlySinceId)
				{
					query.setSinceId(sinceId);
					
				}
				else 
				{
					query.setMaxId(maxId);
					query.setSinceId(sinceId);
				}
			}
			query.setCount(200);
			QueryResult results = null;
			do {
				try {
				
				results = twitter.search(query);
				List<Status> tweets = results.getTweets();
				
				for (Status tweet : tweets) {
					Gson gson = new Gson();
					String rawJSON = gson.toJson(tweet);
					long replyToTweetId = tweet.getInReplyToStatusId();
				    File file = new File(userName+"/"+replyToTweetId+".json");
				    if(file.exists())
				    {
				    	File dir = new File(userName + "/" + tweet.getInReplyToStatusId());
						if(!dir.exists())
						{
							dir.mkdir();
						}
				    	String fileName = userName + "/" + tweet.getInReplyToStatusId() + "/" + tweet.getId() +".json";
						StoreAsFile storeFile = new StoreAsFile();
						storeFile.storeJSON(rawJSON, fileName);
				    }
				    System.out.println(results.nextQuery());
				}
				}catch(TwitterException e) {
					if(e.getStatusCode()==429)
					{
						System.out.println("Stopped with the next query");
//						System.out.println(results.nextQuery());
						int timeuntilreset = e.getRateLimitStatus().getSecondsUntilReset();
					}
				}
				if(results.hasNext())
				{
					System.out.println("I am done");
//					break;
				}
			} while ((query = results.nextQuery()) != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
