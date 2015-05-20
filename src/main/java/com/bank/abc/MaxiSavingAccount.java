package com.bank.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.abc.DateProvider;
import com.abc.Transaction;
import com.bank.abc.exception.AmountMustBeGreaterThanZeroException;

/**
 * MaxiSaving accounts product
 * 
 * 1) Deposit 2) Withdraw 3) interestEarned 4)Account type 5) GetTransactions 6)
 * Sum transactions
 * 
 * @author Bharat
 * 
 */
public class MaxiSavingAccount implements IAccount {

	private List<Transaction> transactions;

	/**
	 * Initialize transactions
	 */
	public MaxiSavingAccount() {
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * Made deposit
	 * 
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
	 * With draw money
	 */
	@Override
	public synchronized void withdraw(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new AmountMustBeGreaterThanZeroException();
		} else {
			transactions.add(new Transaction(amount.negate()));
		}

	}

	/**
	 * Calculate interest earned
	 */
	@Override
	public synchronized BigDecimal interestEarned() {
		BigDecimal amount = AccountUtil.sumTransactions(transactions);

		if (anyTransactionInPast(10, transactions)) {
			return amount.multiply(new BigDecimal(0.0001));
		} else {
			return amount.multiply(new BigDecimal(0.05));
		}

	}

	/**
	 * Check if any transactions in the past
	 * 
	 * @param numberOfdays
	 * @param transactions
	 * @return
	 */
	private boolean anyTransactionInPast(int numberOfdays,
			List<Transaction> transactions) {

		for (Transaction t : transactions) {
			if (DateProvider.getDateDiff(t.getTransactionDate(), new Date(),
					TimeUnit.DAYS) >= numberOfdays) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String getAccountType() {
		return this.toString();
	}

	@Override
	public synchronized List<Transaction> getTransaction() {
		return transactions;
	}

	@Override
	public String toString() {
		return "Maxi Savings Account\n";
	}

}
