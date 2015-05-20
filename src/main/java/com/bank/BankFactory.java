package com.bank;

import com.abc.ABCBank;

/**
 * Abstract class for Bank
 * 
 * We can add more banks if required
 * 
 * @author Bharat
 * 
 */
public abstract class BankFactory {

	/**
	 * Get ABC Bank
	 * 
	 * @param bankName
	 */
	public abstract ABCBank getABCBank();

}
