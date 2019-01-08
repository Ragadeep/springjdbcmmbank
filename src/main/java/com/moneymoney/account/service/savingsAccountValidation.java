package com.moneymoney.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.InsufficientFundsException;

@Aspect
@Service
public class savingsAccountValidation {
	Logger logger = Logger.getLogger(savingsAccountValidation.class.getName());
	
	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		double amount = (double) param[1];
		if(amount > 0) {
			pjp.proceed();
		}
		else {
			logger.warning("Ammout should be more than 0 to deposit"); 
		}
	}
	
	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		SavingsAccount savingsAccount = (SavingsAccount)param[0];
		double currentBalance = savingsAccount.getBankAccount().getAccountBalance();
		double amount = (double) param[1];
		if(amount > 0 && currentBalance >= amount) {
			pjp.proceed();
		}
		else {
			logger.warning("No sufficient ammount to withdraw Ammount"); 
		}
	}
}
