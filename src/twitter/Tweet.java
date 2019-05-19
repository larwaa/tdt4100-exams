package twitter;

public class Tweet {

	private String text;
	private TwitterAccount owner;
	private Tweet originalTweet;
	private int retweetCount = 0;

	public Tweet(TwitterAccount owner, String text){
		if (text.isEmpty()) throw new IllegalArgumentException();
		if (owner == null) throw new IllegalArgumentException();

		this.text = text;
		this.owner = owner;

	}

	public Tweet(TwitterAccount owner, Tweet tweet){
		if (owner == null) throw new IllegalArgumentException();
		if (tweet == null || tweet.getOwner() == owner) throw new IllegalArgumentException();

		this.owner = owner;
		this.text = tweet.getText();

		if (tweet.getOriginalTweet() == null){
			this.originalTweet = tweet;
		} else {
			this.originalTweet = tweet.getOriginalTweet();
		}
		originalTweet.iterateRetweet();
	}

	private void iterateRetweet(){
		retweetCount++;
	}

	public String getText(){
		return text;
	}

	public TwitterAccount getOwner() {
		return owner;
	}

	public Tweet getOriginalTweet(){
		return originalTweet;
	}

	public int getRetweetCount(){
		return retweetCount;
	}
}
