package com.abc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.bank.abc.AccountUtil;
import com.bank.abc.CheckingAccount;
import com.bank.abc.IAccount;
import com.bank.abc.SavingsAccount;

/**
 * CustomerTest can add more negative test
 * 
 * @author Bharat
 * 
 */
public class CustomerTest {

	private Customer oscar = null;

	/**
	 * Setup
	 */
	@Before
	public void setUp() {
		oscar = new Customer("Oscar");

	}

	@Test
	/**
	 * Test customer statement generation
	 */
	public void testApp() {

		IAccount checkingAccount = new CheckingAccount();
		IAccount savingsAccount = new SavingsAccount();

		oscar.openAccount(checkingAccount).openAccount(savingsAccount);

		checkingAccount.deposit(new BigDecimal(100.0));
		savingsAccount.deposit(new BigDecimal(4000.0));
		savingsAccount.withdraw(new BigDecimal(200.0));

		assertEquals("Statement for Oscar\n" + "\n" + "Checking Account\n"
				+ "  deposit $100.00\n" + "Total $100.00\n" + "\n"
				+ "Savings Account\n" + "  deposit $4,000.00\n"
				+ "  withdrawal $200.00\n" + "Total $3,800.00\n" + "\n"
				+ "Total In All Accounts $3,900.00", oscar.getStatement());
	}

	/**
	 * Test one account
	 */
	@Test
	public void testOneAccount() {
		oscar.openAccount(new SavingsAccount());
		assertEquals(1, oscar.getNumberOfAccounts());
	}

	/**
	 * Test two account add
	 */
	@Test
	public void testTwoAccount() {
		oscar.openAccount(new SavingsAccount());
		oscar.openAccount(new CheckingAccount());
		assertEquals(2, oscar.getNumberOfAccounts());
	}

	/**
	 * This test case will be ignored
	 */
	@Ignore
	public void testThreeAcounts() {
		oscar.openAccount(new SavingsAccount());
		oscar.openAccount(new CheckingAccount());
		assertEquals(3, oscar.getNumberOfAccounts());
	}

	/**
	 * Test amount transfer
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTransfer() throws Exception {

		IAccount checkingAccount = new CheckingAccount();
		IAccount savingsAccount = new SavingsAccount();

		oscar.openAccount(checkingAccount).openAccount(savingsAccount);

		checkingAccount.deposit(new BigDecimal("100.00"));
		savingsAccount.deposit(new BigDecimal("4000.00"));

		oscar.transfer(new BigDecimal("4000.00"), savingsAccount,
				checkingAccount);

		assertEquals(new BigDecimal("4100.00"),
				AccountUtil.sumTransactions(checkingAccount.getTransaction()));
		assertEquals(
				AccountUtil.sumTransactions(savingsAccount.getTransaction()),
				new BigDecimal("0.00"));

	}

	/**
	 * Clear up the initialize
	 */
	@After
	public void tearDown() {
		oscar = null;
	}
}