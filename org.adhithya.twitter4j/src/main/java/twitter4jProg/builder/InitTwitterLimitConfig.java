package twitter4jProg.builder;

import twitter4j.Twitter;

public class InitTwitterLimitConfig {

	Twitter tweet;
	int limit;
	public Twitter getTweet() {
		return tweet;
	}
	public void setTweet(Twitter tweet) {
		this.tweet = tweet;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + limit;
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
		InitTwitterLimitConfig other = (InitTwitterLimitConfig) obj;
		if (limit != other.limit)
			return false;
		return true;
	}
	
	
}
