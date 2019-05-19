package twitter;

import java.util.Comparator;

public class FollowersCountComparator implements Comparator<TwitterAccount> {

	public int compare(TwitterAccount ta1, TwitterAccount ta2){
		return ta2.getFollowerCount() - ta1.getFollowerCount();
	}
}
