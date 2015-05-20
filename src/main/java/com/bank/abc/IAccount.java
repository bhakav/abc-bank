package com.bank.abc;

import java.math.BigDecimal;
import java.util.List;

import com.abc.Transaction;

/**
 * Interface for different accounts
 * 
 * There is duplicate data in classes that implement this interface
 * 
 * Reason being if in future we want make changes per AccountTpe we can
 * 
 * @author Bharat
 * 
 */
public interface IAccount {

	/**
	 * Deposit amount from account
	 * 
	 * @param amount
	 */
	public void deposit(BigDecimal amount);

	/**
	 * With draw amount from account
	 * 
	 * @param amount
	 */
	public void withdraw(BigDecimal amount);

	/**
	 * Interest earned for transactions
	 * 
	 * @return
	 */
	public BigDecimal interestEarned();

	/**
	 * Get account type Checking, saving etc
	 * 
	 * @return
	 */
	public String getAccountType();

	/**
	 * Get list of transactions
	 * 
	 * @return
	 */
	public List<Transaction> getTransaction();
}