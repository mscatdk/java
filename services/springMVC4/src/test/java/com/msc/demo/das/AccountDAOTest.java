package com.msc.demo.das;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.msc.demo.das.AccountDAO;

public class AccountDAOTest {
	
	private AccountDAO accountDAO = new AccountDAO();

	/**
	 * Check behavior with null input
	 */
	@Test
	public void getAccountByCustomerNumberTest() {
		List<String> accountNumberList = accountDAO.getAccountNumberByCustomerNumber(null);
		Assert.assertNotNull(accountNumberList);
		Assert.assertEquals(0, accountNumberList.size());
	}
	
}
