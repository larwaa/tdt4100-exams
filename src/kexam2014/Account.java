package kexam2014;


public class Account {
	private int balance = 0, withdrawLimit;
	private final String accountID;


	public Account(String accountID){
		checkNumericString(accountID);
		this.accountID = accountID;
	}

	public String getAccountID(){
		return accountID;
	}

	public void setWithdrawLimit(int withdrawLimit){
		this.withdrawLimit = withdrawLimit;
	}

	protected void checkWithdrawLimit(int withdrawAmount){
		if (withdrawLimit > 0 && withdrawAmount > withdrawLimit){
			throw new IllegalArgumentException(String.format("%s exceeds the withdraw limit, %s.", withdrawAmount, withdrawLimit));
		}	}

	protected void checkNumericString(String s){
		try{
			Integer.parseInt(s);
		} catch (NumberFormatException e){
			throw new IllegalArgumentException(e);
		}
	}

	protected void checkNumericCharsInString(String s){
		for (char c : s.toCharArray()){
			if (! Character.isDigit(c)){
				throw new IllegalArgumentException(c + " is not a valid digit.");
			}
		}
	}

	public int getBalance() {
		return balance;
	}

	public void deposit(int amount){
		checkNegativeInt(amount);
		balance += amount;
	}

	public void withdraw(int amount){
		checkNegativeInt(amount);
		checkWithdrawLimit(amount);
		balance -= amount;
	}

	protected void checkNegativeInt(int i){
		if (i < 0){
			throw new IllegalArgumentException();
		}
	}
}
