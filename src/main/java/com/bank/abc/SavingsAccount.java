package com.bank.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction;
import com.bank.abc.exception.AmountMustBeGreaterThanZeroException;
import com.bank.abc.exception.DepositException;

/**
 * 1) Deposit 2) Withdraw 3) interestEarned 4)Account type 5) GetTransactions 6)
 * Sum transactions
 * 
 * @author Bharat
 * 
 */
public class SavingsAccount implements IAccount {

	public List<Transaction> transactions;

	/**
	 * Initialize transactions
	 */
	public SavingsAccount() {
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * Make deposits
	 * 
	 */
	@Override
	public synchronized void deposit(BigDecimal amount) {
		try {
			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
				throw new AmountMustBeGreaterThanZeroException();
			} else {
				transactions.add(new Transaction(amount));
			}
		} catch (DepositException e) {
			e.printStackTrace();
		}

	}

	/**
	 * With draw money
	 * 
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
	 * Interest earned
	 * 
	 */
	@Override
	public BigDecimal interestEarned() {
		BigDecimal amount = AccountUtil.sumTransactions(transactions);

		if (amount.compareTo(new BigDecimal(1000)) == -1)
			amount = amount.multiply(new BigDecimal(0.001));
		else {
			amount = amount.subtract(new BigDecimal(1000))
					.multiply(new BigDecimal(0.002)).add(new BigDecimal(1));
		}

		return amount;
	}

	/**
	 * Get Account Type
	 */
	@Override
	public String getAccountType() {
		return this.toString();
	}

	/**
	 * Get List of transactions
	 */
	@Override
	public List<Transaction> getTransaction() {
		return transactions;
	}

	@Override
	public String toString() {
		return "Savings Account\n";
	}

}
