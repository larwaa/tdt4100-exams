package twitter;

import java.util.*;
import java.util.stream.Collectors;

public class TwitterAccount {

	private String username;
	private Collection<TwitterAccount> followers = new ArrayList<>(),
										following = new ArrayList<>();
	private List<Tweet> tweets = new ArrayList<>();

	public TwitterAccount(String username){
		this.username = username;
	}

	public String getUserName() {
		return username;
	}

	public int getFollowerCount(){
		return followers.size();
	}

	public int getFollowingCount(){
		return following.size();
	}

	public void follow(TwitterAccount account){
		if (!account.followers.contains(this)){
			account.followers.add(this);
			this.following.add(account);
		}
	}

	public void unfollow(TwitterAccount account){
		if (account.followers.contains(this)){
			account.followers.remove(this);
			this.following.remove(account);
		}
	}

	public boolean isFollowing(TwitterAccount account){
		return following.contains(account);
	}

	public boolean isFollowedBy(TwitterAccount account){
		return followers.contains(account);
	}

	public void tweet(String text){
		tweets.add(new Tweet(this, text));
	}

	public void retweet(Tweet tweet){
		tweets.add(new Tweet(this, tweet));
	}

	public Tweet getTweet(int i){
		if (i < 1 || i > tweets.size()) throw new IndexOutOfBoundsException();
		return tweets.get(tweets.size() - i);
	}

	public int getTweetCount(){
		return tweets.size();
	}

	public Collection<TwitterAccount> getFollowers(Comparator<TwitterAccount> comparator){
		if (comparator == null){
			return followers;
		}
		//return followers.stream().sorted(comparator).collect(Collectors.toList());
		List<TwitterAccount> result = new ArrayList<>(followers);
		Collections.sort(result, comparator);
		return result;
	}

	public int getRetweetCount(){
		int retweets = 0;
		for (Tweet tweet : tweets){
			Tweet originalTweet = tweet.getOriginalTweet();
			if (originalTweet == null){
				originalTweet = tweet;
			}
			if (originalTweet.getOwner() == this) retweets += originalTweet.getRetweetCount();
		}
		return retweets;
	}
}
