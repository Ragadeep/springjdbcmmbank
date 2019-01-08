package com.moneymoney.account;

import org.springframework.stereotype.Component;

@Component
public class BankAccount {
	private  int accountNumber;
	private double accountBalance;
	private String accountHolderName;
	private static int accountId;

	
	
	public BankAccount() {
		super();
	}

	/*static {
		accountId = 100;
	}
*/
	public BankAccount(String accountHolderName, double accountBalance) {
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
	}

	public BankAccount(String accountHolderName) {
		accountNumber = ++accountId;
		this.accountHolderName = accountHolderName;
	}

	public BankAccount(int accountNumber, String accountHolderName,
			double accountBalance) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber
				+ ", accountBalance=" + accountBalance + ", accountHolderName="
				+ accountHolderName + "]";
	}

}
