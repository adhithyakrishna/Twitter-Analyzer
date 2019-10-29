package twitter4jProg.builder;

public class TwitterJsonConfig {
	int tweetLimit;
	String oAuthConsumerKey;
	String oAuthConsumerSecret;
	String oAuthAccessToken;
	String oAuthAccessTokenSecret;
	
	public int getTweetLimit() {
		return tweetLimit;
	}
	public void setTweetLimit(int tweetLimit) {
		this.tweetLimit = tweetLimit;
	}
	public String getoAuthConsumerKey() {
		return oAuthConsumerKey;
	}
	public void setoAuthConsumerKey(String oAuthConsumerKey) {
		this.oAuthConsumerKey = oAuthConsumerKey;
	}
	public String getoAuthConsumerSecret() {
		return oAuthConsumerSecret;
	}
	public void setoAuthConsumerSecret(String oAuthConsumerSecret) {
		this.oAuthConsumerSecret = oAuthConsumerSecret;
	}
	public String getoAuthAccessToken() {
		return oAuthAccessToken;
	}
	public void setoAuthAccessToken(String oAuthAccessToken) {
		this.oAuthAccessToken = oAuthAccessToken;
	}
	public String getoAuthAccessTokenSecret() {
		return oAuthAccessTokenSecret;
	}
	public void setoAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
		this.oAuthAccessTokenSecret = oAuthAccessTokenSecret;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oAuthAccessToken == null) ? 0 : oAuthAccessToken.hashCode());
		result = prime * result + ((oAuthAccessTokenSecret == null) ? 0 : oAuthAccessTokenSecret.hashCode());
		result = prime * result + ((oAuthConsumerKey == null) ? 0 : oAuthConsumerKey.hashCode());
		result = prime * result + ((oAuthConsumerSecret == null) ? 0 : oAuthConsumerSecret.hashCode());
		result = prime * result + tweetLimit;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwitterJsonConfig other = (TwitterJsonConfig) obj;
		if (oAuthAccessToken == null) {
			if (other.oAuthAccessToken != null)
				return false;
		} else if (!oAuthAccessToken.equals(other.oAuthAccessToken))
			return false;
		if (oAuthAccessTokenSecret == null) {
			if (other.oAuthAccessTokenSecret != null)
				return false;
		} else if (!oAuthAccessTokenSecret.equals(other.oAuthAccessTokenSecret))
			return false;
		if (oAuthConsumerKey == null) {
			if (other.oAuthConsumerKey != null)
				return false;
		} else if (!oAuthConsumerKey.equals(other.oAuthConsumerKey))
			return false;
		if (oAuthConsumerSecret == null) {
			if (other.oAuthConsumerSecret != null)
				return false;
		} else if (!oAuthConsumerSecret.equals(other.oAuthConsumerSecret))
			return false;
		if (tweetLimit != other.tweetLimit)
			return false;
		return true;
	}
}
