package com.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.AccountNotFoundException;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName,
			double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException;

	boolean updateAccount(SavingsAccount account)
			throws ClassNotFoundException, SQLException;

	SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;

	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException,
			SQLException;

	void fundTransfer(SavingsAccount sender, SavingsAccount receiver,
			double amount) throws ClassNotFoundException, SQLException;

	void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;

	void withdraw(SavingsAccount account, double amount)
			throws ClassNotFoundException, SQLException;

	SavingsAccount delete(SavingsAccount savingsAccount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;

	double checkBalance(int accountNumber) throws ClassNotFoundException,
			SQLException, AccountNotFoundException;

	SavingsAccount searchAccount(int accountNumber);

	List<SavingsAccount> searchAccountByHolderName(String holderName);

	List<SavingsAccount> sortByAccountHolderName();

	List<SavingsAccount> sortByAccountHolderNameInDescendingOrder();

	List<SavingsAccount> sortByAccountBalance();

	List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance);

	List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue);
}
