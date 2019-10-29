package twitter4jProg.Utility;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StoreAsFile {

	public void storeJSON(String rawJSON, String fileName) throws IOException {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(fileName);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
			bw.write(rawJSON);
			bw.flush();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ignore) {
				}
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException ignore) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ignore) {
				}
			}
		}
	}

}

/*
 * public static ArrayList<Status> getReplies(Twitter twitter, Status status,
 * long tweetID) { ArrayList<Status> replies = new ArrayList<Status>(); String
 * screenName = status.getUser().getScreenName(); try { Query query = new
 * Query("to:" + screenName + " since_id:" + tweetID); query.setCount(100);
 * QueryResult results; do { results = twitter.search(query); //
 * System.out.println("Results: " + results.getTweets().size()); List<Status>
 * tweets = results.getTweets();
 * 
 * for (Status tweet : tweets) { //
 * System.out.println(tweet.getInReplyToStatusId() + " and " + tweetID); if
 * (tweet.getInReplyToStatusId() == tweetID) { System.out.println("yay!!"); //
 * replies.add(tweet); } } } while ((query = results.nextQuery()) != null);
 * 
 * } catch (Exception e) { e.printStackTrace(); } return replies; }
 */