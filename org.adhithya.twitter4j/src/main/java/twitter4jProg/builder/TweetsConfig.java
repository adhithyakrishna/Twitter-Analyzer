package twitter4jProg.builder;

public class TweetsConfig {
	String lang;
	String createdAt;
	long inReplyToStatusId;
	RetweetStatus retweetedStatus;
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}
	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}
	public RetweetStatus getRetweetedStatus() {
		return retweetedStatus;
	}
	public void setRetweetedStatus(RetweetStatus retweetedStatus) {
		this.retweetedStatus = retweetedStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + (int) (inReplyToStatusId ^ (inReplyToStatusId >>> 32));
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((retweetedStatus == null) ? 0 : retweetedStatus.hashCode());
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
		TweetsConfig other = (TweetsConfig) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (inReplyToStatusId != other.inReplyToStatusId)
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (retweetedStatus == null) {
			if (other.retweetedStatus != null)
				return false;
		} else if (!retweetedStatus.equals(other.retweetedStatus))
			return false;
		return true;
	}
	
	
}
