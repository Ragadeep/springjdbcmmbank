package com.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.dao.SavingsAccountDAO;
import com.moneymoney.account.dao.SavingsAccountDAOImpl;
import com.moneymoney.account.factory.AccountFactory;
import com.moneymoney.exception.AccountNotFoundException;
import com.moneymoney.exception.InsufficientFundsException;
import com.moneymoney.exception.InvalidInputException;
@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl(SavingsAccountDAO savingsAccountDAO) {
		factory = AccountFactory.getInstance();
		this.savingsAccountDAO = savingsAccountDAO;
	}

	public SavingsAccount createNewAccount(String accountHolderName,
			double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(
				accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	public List<SavingsAccount> getAllSavingsAccount()
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		currentBalance += amount;
		savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	}

	public void withdraw(SavingsAccount account, double amount)
			throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		currentBalance -= amount;
		savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);

	}

	@Transactional
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver,
			double amount) throws ClassNotFoundException, SQLException {
			deposit(receiver, amount);
			withdraw(sender, amount);
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public SavingsAccount delete(SavingsAccount savingsAccount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		savingsAccountDAO.delete(savingsAccount);
		return null;
	}

	@Override
	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		return savingsAccountDAO.checkBalance(accountNumber);
	}

	@Override
	public boolean updateAccount(SavingsAccount account)
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.updateAccount(account);
	}

	@Override
	public SavingsAccount searchAccount(int accountNumber) {
		return savingsAccountDAO.searchAccount(accountNumber);
	}

	@Override
	public List<SavingsAccount> searchAccountByHolderName(String holderName) {
		return savingsAccountDAO.searchAccountByHolderName(holderName);
	}

	@Override
	public List<SavingsAccount> sortByAccountHolderName() {
		return savingsAccountDAO.sortByAccountHolderName();
	}

	@Override
	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() {
		return savingsAccountDAO.sortByAccountHolderNameInDescendingOrder();
	}

	@Override
	public List<SavingsAccount> sortByAccountBalance() {
		return savingsAccountDAO.sortByAccountBalance();
	}

	@Override
	public List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance) {
		return savingsAccountDAO.sortByBalanceRange(minimumBalance,maximumBalance);
	}

	@Override
	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue) {
		return savingsAccountDAO.sortByBalanceRangeInDescendingOrder(minimumBalanceValue,maximumBalanceValue);
	}
}
