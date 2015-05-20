package com.bank.abc;

import java.math.BigDecimal;
import java.util.List;

import com.abc.Transaction;

/**
 * Util calss for Account add util methods
 * 
 * @author e6082118
 * 
 */
public class AccountUtil {

	/**
	 * Sum Transactions
	 * 
	 */
	public static BigDecimal sumTransactions(List<Transaction> transactions) {
		BigDecimal amount = BigDecimal.ZERO;
		for (Transaction t : transactions)
			amount = amount.add(t.getAmount());
		return amount;
	}
}
