package kexam2014;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SafeBank extends Bank {

	private int limit;
	private Map<Account, Integer> limits = new HashMap<>();

	public void setLimit(Account account, int limit){
		if (getAccount(account) != null){
			limits.put(account, limit);
		}
	}

	public int getLimit(Account account){
		return limits.getOrDefault(account, -1);
	}

	@Override
	public void transfer(Account source, Account target, int amount){
		Date date = new Date();
		int transfers = getTransferSum(source, date.getYear(), date.getMonth());
		int limit = getLimit(source);

		if (limit >= 0 && transfers == limit){
			throw new IllegalStateException();
		}

		if (limit >= 0 && transfers + amount > limit){
			throw new IllegalArgumentException();
		}

		super.transfer(source, target, amount);
	}

}
