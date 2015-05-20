package com.bank;

import com.abc.ABCBank;

/**
 * Bank factory implementation
 * 
 * 
 * @author Bharat
 * 
 */
public class Bank extends BankFactory {

	/**
	 * Returns ABCBank object
	 */
	@Override
	public ABCBank getABCBank() {

		return new ABCBank();
	}

}