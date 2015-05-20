package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bank.abc.AccountUtil;
import com.bank.abc.IAccount;

/**
 * Customer class 1) Get Name 2) OpenAccount 3) Get number of accounts 4) Get
 * Account summary 5) Transfer between accounts
 * 
 * @author Bharat
 * 
 */
public class Customer {

	private String name;
	private List<IAccount> accounts;

	/**
	 * initialze the name
	 * 
	 * @param name
	 */
	public Customer(String name) {
		this.name = name;
		this.accounts = new ArrayList<IAccount>();
	}

	/**
	 * Open account checking ,saving etc
	 * 
	 * @param account
	 * @return
	 */
	public Customer openAccount(IAccount account) {
		accounts.add(account);
		return this;
	}

	/**
	 * Return number of accounts
	 * 
	 */
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	/**
	 * Get total Interest earned
	 * 
	 * @return
	 */
	public BigDecimal totalInterestEarned() {
		BigDecimal total = BigDecimal.ZERO;
		for (IAccount a : accounts)
			total = total.add(a.interestEarned());
		return total;
	}

	/**
	 * Get Formated statement
	 * 
	 * @return
	 */
	public String getStatement() {
		String statement = null;
		statement = "Statement for " + name + "\n";
		BigDecimal total = BigDecimal.ZERO;
		for (IAccount a : accounts) {
			statement += "\n" + statementForAccount(a) + "\n";
			total = total.add(AccountUtil.sumTransactions(a.getTransaction()));
		}
		statement += "\nTotal In All Accounts " + toDollars(total);
		return statement;
	}

	/**
	 * Get statement for account
	 * 
	 * @param account
	 * @return
	 */
	private String statementForAccount(IAccount account) {
		String s = "";

		// Translate to pretty account type

		s += account.getAccountType();

		// Now total up all the transactions
		BigDecimal total = BigDecimal.ZERO;
		for (Transaction t : account.getTransaction()) {
			s += "  "
					+ (t.getAmount().compareTo(BigDecimal.ZERO) == -1 ? "withdrawal"
							: "deposit") + " " + toDollars(t.getAmount())
					+ "\n";
			total = total.add(t.getAmount());
		}
		s += "Total " + toDollars(total);
		return s;
	}

	/**
	 * Format and give dollars
	 * 
	 * @param d
	 * @return
	 */
	private String toDollars(BigDecimal d) {
		return String.format("$%,.2f", d.abs());
	}

	/**
	 * Transfer money between two accounts
	 * 
	 * @param amount
	 * @param fromAccount
	 * @param toAccount
	 */
	public synchronized void transfer(BigDecimal amount, IAccount fromAccount,
			IAccount toAccount) {
		if (amount.compareTo(BigDecimal.ZERO) == 0
				|| amount.compareTo(BigDecimal.ZERO) == -1)
			throw new IllegalArgumentException(
					"Transfer Amount must be greater than zero");

		BigDecimal fromBallance = AccountUtil.sumTransactions(fromAccount
				.getTransaction());
		if (fromBallance.compareTo(amount) == -1)
			throw new IllegalArgumentException(
					"Trasfer Account balance is not allowed to overdraw");

		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
	}

	public String getName() {
		return name;
	}
}