package twitter4jProg.builder;

public class RetweetStatus {
	
	boolean retweeted;

	public boolean isRetweeted() {
		return retweeted;
	}

	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (retweeted ? 1231 : 1237);
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
		RetweetStatus other = (RetweetStatus) obj;
		if (retweeted != other.retweeted)
			return false;
		return true;
	}
	
	
	
}
