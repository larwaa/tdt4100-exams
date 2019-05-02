package kexam2014;

import java.io.InputStream;
import java.util.*;

public class Bank {

	private Map<String, Account> accounts = new HashMap<>();
	private List<Transaction> transactions = new ArrayList<>();

	public void addAccount(Account account){
		if (! accounts.containsKey(account.getAccountID())){
			accounts.put(account.getAccountID(), account);
		}
	}

	public Account getAccount(Account account){
		return accounts.get(account.getAccountID());
	}

	public Account getAccount(String accountID){
		return accounts.get(accountID);
	}

	public Account createAccount(){
		String baseAccountID = accounts.keySet().stream()
									.sorted((a1, a2) -> Integer.parseInt(a2) - Integer.parseInt(a1))
									.findFirst()
									.orElse("0");
		String accountID = Integer.toString((Integer.parseInt(baseAccountID) + 1));
		Account account = new Account(accountID);
		addAccount(account);
		return account;
	}

	public void transfer(Account source, Account target, int amount) throws IllegalArgumentException {
		if (getAccount(source) == null || getAccount(target) == null){
			throw new IllegalArgumentException();
		}
		try{
			source.withdraw(amount);
			target.deposit(amount);
			transactions.add(new Transaction(source, target, amount));
		} catch (IllegalArgumentException e){
			throw e;
		}
	}

	public int getTransferSum(Account account, int year, int month){
		return transactions.stream()
				.filter((t) -> t.source == account)
				.filter((t) -> t.date.getYear() == year && t.date.getMonth() == month)
				.mapToInt((t) -> t.amount)
				.sum();
	}

	public void doTransactions(InputStream input){
		Scanner scanner = new Scanner(input);

		while (scanner.hasNextLine()){
			String line = scanner.nextLine();
			try{
				String[] splitLine = line.split("[-:]");
				Account source = getAccount(splitLine[0]);
				Account target = getAccount(splitLine[1]);
				int amount = Integer.valueOf(splitLine[2]);
				transfer(source, target, amount);
			} catch (Exception e){
				continue;
			}
		}
		scanner.close();
	}



}
