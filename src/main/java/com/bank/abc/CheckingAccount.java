package com.bank.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction;
import com.bank.abc.exception.AmountMustBeGreaterThanZeroException;

/**
 * CheckingAccount class implements IAccount
 * 
 * 1) Deposit 2) Withdraw 3) interestEarned 4)Account type 5) GetTransactions 6)
 * Sum transactions
 * 
 * @author Bharat
 * 
 */
public class CheckingAccount implements IAccount {

	public List<Transaction> transactions;

	/**
	 * initialize transactions
	 */
	public CheckingAccount() {
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * Deposit amount
	 */
	@Override
	public synchronized void deposit(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new AmountMustBeGreaterThanZeroException();
		} else {
			transactions.add(new Transaction(amount));
		}

	}

	/**
	 * With draw data
	 */
	@Override
	public synchronized void withdraw(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) > 0) {
			throw new AmountMustBeGreaterThanZeroException();
		} else {
			transactions.add(new Transaction(amount.negate()));
		}

	}

	/**
	 * Calculate interest Earned
	 * 
	 */
	@Override
	public BigDecimal interestEarned() {
		BigDecimal amount = BigDecimal.ZERO;
		for (Transaction t : transactions)
			amount = amount.add(t.getAmount());

		amount = amount.multiply(new BigDecimal(.001));
		return amount;

	}

	/**
	 * Get account type
	 * 
	 */
	@Override
	public String getAccountType() {
		return this.toString();
	}

	/**
	 * Get list of transactions
	 * 
	 */
	@Override
	public synchronized List<Transaction> getTransaction() {
		return transactions;
	}

	@Override
	public String toString() {
		return "Checking Account\n";
	}

}
