package twitter4jProj.org.adhithya.twitter4j;

import java.io.File;
import java.io.IOException;
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

public class UserMentionsFunctionDate {

	public static void main(String[] args) {
		TwitterInitializer initTwitter = new TwitterInitializer();
		try {
			Twitter twitter = initTwitter.initTweet().getTweet();
			String userName = "gleisi";
			String queryString = "#brazil politician";
			long sinceId = 1168176410537553920L;
			boolean firstTime = true;
			do {
				long newid = getReplies(twitter, userName, sinceId, firstTime, queryString);
				if (newid > 0) {
					if (firstTime) {
						firstTime = false;
					}
					if (newid == sinceId) {
						System.out.println("done");
						break;
					}
				}
				else {
					System.out.println("done");
					break;
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

	public static long getReplies(Twitter twitter, String userName, long sinceId, boolean isFirstTime,
			String queryString) {
		String screenName = userName;
		long returnId = 0L;
		try {

			Query query = new Query(queryString);
			query.setCount(200);
			if (isFirstTime) {
				query.setSince("2019-09-05");
			} else {
				query.setSinceId(sinceId);
			}

			QueryResult results = null;

			results = twitter.search(query);
			List<Status> tweets = results.getTweets();

			for (Status tweet : tweets) {
				Gson gson = new Gson();
				String rawJSON = gson.toJson(tweet);

				String language = "pt";
				File isAfile = new File("Perfection/" + language + "/" + screenName + "/" + tweet.getId());

				if (!isAfile.exists()) {
					File dir = new File("Perfection/" + language + "/" + screenName + "/mentions/");
					if (!dir.exists()) {
						dir.mkdir();
					}
					long mentionTweetId = tweet.getId();

					String fileName = "Perfection/" + language + "/" + screenName + "/mentions/" + mentionTweetId
							+ ".json";

					StoreAsFile storeFile = new StoreAsFile();
					try {
						storeFile.storeJSON(rawJSON, fileName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			returnId = results.nextQuery() != null ? results.nextQuery().getMaxId() : 0;
		} catch (TwitterException e) {
			if (e.getStatusCode() == 429) {
				try {
					System.out.println(
							"i'll be sleeping for " + e.getRateLimitStatus().getSecondsUntilReset() + " seconds");
					int count = e.getRateLimitStatus().getSecondsUntilReset();

					Thread.sleep(e.getRateLimitStatus().getSecondsUntilReset() * 1000);
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
