package com.abc;

import java.math.BigDecimal;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

/**
 * Transaction test
 * 
 * Can add more test cases both negative and positive
 * 
 * @author Bharat
 * 
 */
public class TransactionTest {
	/**
	 * Test transactions
	 */
	@Test
	public void transaction() {
		Transaction t = new Transaction(new BigDecimal(5.0));
		assertTrue(t instanceof Transaction);
		assertEquals(t.getAmount(), new BigDecimal(5.0));
	}
}