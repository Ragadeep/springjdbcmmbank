package com.moneymoney.account.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.moneymoney.account.SavingsAccount;

public class SavingsAccountMapper implements RowMapper<SavingsAccount> {

	@Override
	public SavingsAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		int accountNumber = rs.getInt("account_id");
		String accountHolderName = rs.getString("account_hn");
		double accountBalance = rs.getDouble("account_bal");
		boolean salary = rs.getBoolean("salary");
		SavingsAccount savingsAccount = new SavingsAccount(accountNumber,
				accountHolderName, accountBalance, salary);
		return savingsAccount;
	}
	
}
