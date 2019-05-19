package twitter;

import java.util.Comparator;

public class UserNameComparator implements Comparator<TwitterAccount> {

	public int compare(TwitterAccount ta1, TwitterAccount ta2){
		return ta1.getUserName().compareTo(ta2.getUserName());
	}

}
