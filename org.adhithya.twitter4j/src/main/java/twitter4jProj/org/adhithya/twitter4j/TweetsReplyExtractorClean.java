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

public class TweetsReplyExtractorClean {

	public static void main(String[] args) {
		TwitterInitializer initTwitter = new TwitterInitializer();
		try {
			Twitter twitter = initTwitter.initTweet().getTweet();
			String userName = "yadavtejashwi";
			long sinceId = 1168442013974294528L;
			boolean firstTime = true;
			do {
				long newid = getReplies(twitter, userName, sinceId, firstTime);
				if(firstTime)
				{
					firstTime = false;
					sinceId = 1167378044698464255L;
				}
				System.out.println("the new id " + newid);
				sinceId = newid;
			} while (true);

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

	public static long getReplies(Twitter twitter, String userName, long sinceId, boolean isFirstTime) {
		String screenName = userName;
		long returnId  = 0L;
		try {
			
			Query query = new Query("to:" + screenName);
			query.setCount(200);
			if(isFirstTime)
			{
				query.setSinceId(sinceId);
			}
			else {
				query.setMaxId(sinceId);
			}
			QueryResult results = null;

			results = twitter.search(query);
			List<Status> tweets = results.getTweets();

			for (Status tweet : tweets) {
				if(tweet.getId() > sinceId)
				{
					sinceId = tweet.getId();
				}
				Gson gson = new Gson();
				String rawJSON = gson.toJson(tweet);
				long replyToTweetId = tweet.getInReplyToStatusId();
				File file = new File(userName + "/" + replyToTweetId + ".json");
				if (file.exists()) {
					File dir = new File(userName + "/" + tweet.getInReplyToStatusId());
					if (!dir.exists()) {
						dir.mkdir();
					}
					String fileName = userName + "/" + tweet.getInReplyToStatusId() + "/" + tweet.getId() + ".json";
					StoreAsFile storeFile = new StoreAsFile();
					try {
						storeFile.storeJSON(rawJSON, fileName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			returnId = results.nextQuery().getMaxId();
		} catch (TwitterException e) {
			if (e.getStatusCode() == 429) {
				try {
					System.out.println("i'll be sleeping for " + e.getRateLimitStatus().getSecondsUntilReset() + " seconds");
					int count = e.getRateLimitStatus().getSecondsUntilReset();
					
					Thread.sleep(e.getRateLimitStatus().getSecondsUntilReset()*1000);
					System.out.println("I woke up");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		return returnId;
	}
}
