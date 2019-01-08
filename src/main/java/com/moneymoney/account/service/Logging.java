package com.moneymoney.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;

public class Logging {
	Logger logger = Logger.getLogger(Logging.class.getName());
	
	@After("execution(* com.moneymoney.account.service.createNewAccount(..))")
	public void createNewAccountValidationAfter() {
		logger.info("Account Was Created SuccessFully"); 
	}
}
