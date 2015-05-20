package com.abc;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction management
 * 
 * @author Bharat
 * 
 */
public class Transaction {
	private final BigDecimal amount;

	private Date transactionDate;

	/**
	 * Add amount and date to transaction
	 * 
	 * @param amount
	 */
	public Transaction(BigDecimal amount) {
		this.amount = amount;
		this.setTransactionDate(DateProvider.getInstance().now());
	}

	/**
	 * Get Transaction Date
	 * 
	 * @return
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * Set transaction date
	 * 
	 * @param transactionDate
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * Get Amount
	 * 
	 * @return
	 */
	public BigDecimal getAmount() {
		return amount;
	}

}