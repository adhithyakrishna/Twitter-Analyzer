package twitter4jProj.org.adhithya.twitter4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4jProg.Utility.StoreAsFile;
import twitter4jProg.Utility.TwitterInitializer;
import twitter4jProg.builder.InitTwitterLimitConfig;

public class UserMentionsFunction {

	public static void main(String[] args) {
		TwitterInitializer initTwitter = new TwitterInitializer();
		try {
			Twitter twitter = initTwitter.initTweet().getTweet();
			String userName = "RubikaLiyaquat";
			String queryString = "#RubikaLiyaquat";
			long sinceId = 1029798310595444736L;
			boolean firstTime = true;
			String sinceDate = "2019-08-09";
			do {
				long newid = getReplies(twitter, userName, sinceId, firstTime, queryString, sinceDate);
				if (firstTime) {
					firstTime = false;
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
			String queryString, String sinceDate) {
		String screenName = userName;
		long returnId = 0L;
		try {

			Query query = new Query(queryString);
			query.setCount(200);
			if (isFirstTime) {
//				query.setUntil("2019-08-09");
//				query.setSince(sinceDate);
				query.setSinceId(sinceId);
			} else {
				query.setMaxId(sinceId);
			}
			QueryResult results = null;

			results = twitter.search(query);
			List<Status> tweets = results.getTweets();

			for (Status tweet : tweets) {
				Gson gson = new Gson();
				String rawJSON = gson.toJson(tweet);

				File dir = new File(screenName + "/" + "mentions");
				if (!dir.exists()) {
					dir.mkdir();
				}
				URLEntity[] entity = tweet.getURLEntities();
				boolean isUrled = false;
				for (URLEntity tyti : entity) {
					int count = 0;
					if (tyti.getExpandedURL().contains(screenName))
						;
					{
						String[] textArray = queryString.split(" ");
						for (String data : textArray) {
							if (tweet.getText().contains(data)) {
								count++;
							}
						}
					}
					if (count <= 0) {
						isUrled = true;
					}

				}
				boolean isTagged = true;
				if (!isUrled && !tweet.getUser().getScreenName().equals(screenName) && tweet.getInReplyToStatusId() < 0
						&& tweet.getRetweetedStatus() == null) {
					if (tweet.getText().contains("@" + screenName)) {
						/*if (tweet.getText().contains(userName)) {
							isTagged = true;
						} else {
							isTagged = false;
						}*/
						isTagged = false;
					}
					if (isTagged) {
						long mentionTweetId = tweet.getId();

						String fileName = screenName + "/" + "mentions" + "/" + mentionTweetId + ".json";
						StoreAsFile storeFile = new StoreAsFile();
						try {
							storeFile.storeJSON(rawJSON, fileName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}
			returnId = results.nextQuery().getMaxId();
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
