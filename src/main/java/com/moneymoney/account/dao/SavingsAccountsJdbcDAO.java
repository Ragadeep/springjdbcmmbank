package com.moneymoney.account.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.AccountNotFoundException;

@Repository
@Primary
public class SavingsAccountsJdbcDAO implements SavingsAccountDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[] { account.getBankAccount().getAccountNumber(), account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(), account.isSalary(), null, "SA" });
		return account; 
	}

	@Override
	public boolean updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("UPDATE account SET account_hn=?,salary=? WHERE account_id=?", new Object[] { account.getBankAccount().getAccountHolderName(), account.isSalary(), account.getBankAccount().getAccountNumber() });
		return false;
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return jdbcTemplate.queryForObject("SELECT * FROM account where account_id=?", new Object[] { accountNumber }, new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return jdbcTemplate.query("SELECT * FROM ACCOUNT", new SavingsAccountMapper());
	
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("UPDATE ACCOUNT SET account_bal=? where account_id=?",  new Object[] { currentBalance, accountNumber });
	}

	@Override
	public SavingsAccount delete(SavingsAccount savingsAccount) throws SQLException, ClassNotFoundException, AccountNotFoundException {
		jdbcTemplate.update("DELETE FROM ACCOUNT WHERE account_id=?", new Object[] { savingsAccount.getBankAccount().getAccountNumber()});
		return savingsAccount;
	}

	@Override
	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return jdbcTemplate.queryForObject("SELECT account_bal FROM account where account_id=?", new Object[] { accountNumber }, Double.class);
	}

	@Override
	public SavingsAccount searchAccount(int accountNumber) {
		return jdbcTemplate.queryForObject("SELECT * FROM account WHERE account_id=?", new Object[] {accountNumber}, new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> searchAccountByHolderName(String holderName) {
		return jdbcTemplate.query("SELECT * FROM account WHERE account_hn=?",new Object[] {holderName}, new SavingsAccountMapper());
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SavingsAccount> sortByAccountHolderName() {
		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_hn", new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() {
		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_hn DESC", new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> sortByAccountBalance() {
		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_bal", new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance) {
		return jdbcTemplate.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal", new Object[] {minimumBalance,maximumBalance},new SavingsAccountMapper());
	}

	@Override
	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue) {
		return jdbcTemplate.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal DESC", new Object[] {minimumBalanceValue,maximumBalanceValue},new SavingsAccountMapper());
	}
}

