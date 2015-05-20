package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bank.Bank;
import com.bank.abc.CheckingAccount;
import com.bank.abc.IAccount;
import com.bank.abc.MaxiSavingAccount;
import com.bank.abc.SavingsAccount;
import com.bank.abc.exception.AmountMustBeGreaterThanZeroException;

/**
 * Bank Test cases
 * 
 * @author Bharat
 * 
 *         Should have negative test for each scenario
 * 
 */
public class BankTest {
	private static final double DOUBLE_DELTA = 1e-15;
	Customer john = null;
	Customer bill = null;
	ABCBank abcBank = null;

	/**
	 * Setup
	 */
	@Before
	public void setUp() {
		Bank bankFactory = new Bank();
		john = new Customer("John");
		bill = new Customer("Bill");
		abcBank = bankFactory.getABCBank();
	}

	/**
	 * Customer Summary test
	 */
	@Test
	public void customerSummary() {
		abcBank.addCustomer(john.openAccount(new CheckingAccount()));

		assertEquals("Customer Summary\n - John (1 account)",
				abcBank.customerSummary());
	}

	/**
	 * Amount less than or equal to Zero
	 */
	@Test
	public void negativeCheckingAccount() {
		IAccount checkingAccount = new CheckingAccount();
		abcBank.addCustomer(bill.openAccount(checkingAccount));

		try {
			checkingAccount.deposit(new BigDecimal(0));
			fail();
		} catch (AmountMustBeGreaterThanZeroException exception) {
			assertEquals("Amount must be greater than zero",
					exception.getMessage());
		}

		try {
			checkingAccount.deposit(new BigDecimal(-1));
			fail();
		} catch (AmountMustBeGreaterThanZeroException exception) {
			assertEquals("Amount must be greater than zero",
					exception.getMessage());
		}

	}

	/**
	 * Checking account test
	 */
	@Test
	public void checkingAccount() {

		IAccount checkingAccount = new CheckingAccount();
		abcBank.addCustomer(bill.openAccount(checkingAccount));

		checkingAccount.deposit(new BigDecimal(100.0));

		assertEquals(0.1, abcBank.totalInterestPaid().doubleValue(),
				DOUBLE_DELTA);
	}

	/**
	 * Saving
	 */
	@Test
	public void savingAccount() {
		IAccount savingAccount = new SavingsAccount();

		abcBank.addCustomer(bill.openAccount(savingAccount));

		savingAccount.deposit(new BigDecimal(1500.0));

		assertEquals(2.0, abcBank.totalInterestPaid().doubleValue(),
				DOUBLE_DELTA);
	}

	/**
	 * Amount less than or equal to Zero
	 */
	@Test
	public void negativeSavingAccount() {
		IAccount savingAccount = new SavingsAccount();

		abcBank.addCustomer(bill.openAccount(savingAccount));

		try {
			savingAccount.deposit(new BigDecimal(0));
			fail();
		} catch (AmountMustBeGreaterThanZeroException exception) {
			assertEquals("Amount must be greater than zero",
					exception.getMessage());
		}

		try {
			savingAccount.deposit(new BigDecimal(-1));
			fail();
		} catch (AmountMustBeGreaterThanZeroException exception) {
			assertEquals("Amount must be greater than zero",
					exception.getMessage());
		}

	}

	@Test
	public void maxiSavingAccount() {
		ABCBank bank = new ABCBank();
		IAccount maxiSavingAccount = new MaxiSavingAccount();
		bank.addCustomer(bill.openAccount(maxiSavingAccount));

		maxiSavingAccount.deposit(new BigDecimal(3000.0));

		maxiSavingAccount.getTransaction().get(0)
				.setTransactionDate(new Date(new Date().getTime() - 20));

		assertEquals(.3, bank.totalInterestPaid().doubleValue(), DOUBLE_DELTA);
	}

	/**
	 * Tear down
	 */
	@After
	public void tearDown() {
		john = null;
		bill = null;
		abcBank = null;
	}
}