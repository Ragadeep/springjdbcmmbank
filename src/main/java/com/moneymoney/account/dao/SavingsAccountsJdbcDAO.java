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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public SavingsAccount delete(SavingsAccount savingsAccount) throws SQLException, ClassNotFoundException, AccountNotFoundException {
		SavingsAccount obj = getAccountById(savingsAccount.getBankAccount().getAccountNumber());
		jdbcTemplate.update("DELETE FROM ACCOUNT WHERE account_id=?", new Object[] { savingsAccount.getBankAccount().getAccountNumber()});
		return obj;
	}

	@Override
	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return jdbcTemplate.queryForObject("SELECT account_bal FROM account where account_id=?", new Object[] { accountNumber }, Double.class);
	}

}
