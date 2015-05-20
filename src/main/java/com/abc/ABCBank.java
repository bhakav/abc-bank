package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ABCBank implementation
 * 
 * 1) addCustomer
 * 2) customer summary 
 * 3) total interest paid
 * 
 * @author Bharat
 * 
 */
public class ABCBank {
	private List<Customer> customers;

	/**
	 * Constructor to initialize customers
	 */
	public ABCBank() {
		customers = new ArrayList<Customer>();
	}

	/**
	 * Add customers
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	/**
	 * Customer summary
	 * 
	 * @return
	 */
	public String customerSummary() {
		String summary = "Customer Summary";
		for (Customer c : customers)
			summary += "\n - " + c.getName() + " ("
					+ format(c.getNumberOfAccounts(), "account") + ")";
		return summary;
	}

	/**
	 * Make sure correct plural of word is created based on the number passed
	 * in: If number passed in is 1 just return the word otherwise add an 's' at
	 * the end
	 * 
	 * @param number
	 * @param word
	 * @return
	 */
	private String format(int number, String word) {
		return number + " " + (number == 1 ? word : word + "s");
	}

	/**
	 * Return total interest paid
	 * 
	 * @return
	 */
	public BigDecimal totalInterestPaid() {
		BigDecimal total = BigDecimal.ZERO;
		for (Customer c : customers)
			total = total.add(c.totalInterestEarned());
		return total;
	}

}