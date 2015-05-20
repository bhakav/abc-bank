package com.bank.abc.exception;

public class AmountMustBeGreaterThanZeroException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8554544576818039756L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Amount must be greater than zero";
	}

}
