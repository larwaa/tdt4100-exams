package twitter;

import java.util.Comparator;

public class TweetsCountComparator implements Comparator<TwitterAccount> {

	public int compare(TwitterAccount ta1, TwitterAccount ta2){
		return ta2.getTweetCount() - ta1.getTweetCount();
	}

}
