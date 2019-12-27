package com.msc.demo.das;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.msc.demo.model.Account;
import com.msc.demo.model.AccountStatus;
import com.msc.demo.model.AccountType;
import com.msc.demo.model.Customer;

@Component
public class AccountDAO {

	private List<Account> accountList = new ArrayList<Account>();
	
	// Inserting demo data at startup
	{
		Account account01 = new Account("6666664444", "8888888822", new BigDecimal("2000"), AccountType.CASH_PENSION, AccountStatus.ACTIVE);
		accountList.add(account01);
		
		Account account02 = new Account("6666664444", "3337777777", new BigDecimal("2000000"), AccountType.FIXED_INTEREST, AccountStatus.ACTIVE);
		accountList.add(account02);
	}
	
	/**
	 * Return a list of accounts belonging to the customer given as argument.
	 * @param customer Customer object. Only the Customer number is needed.
	 * @return List of Accounts.
	 */
	public List<Account> getAccountForCustomer(Customer customer) {
		if (customer == null) {
			System.out.println("AccountDAO -> Customer is null");
			return Collections.emptyList();
		}
		return getAccountByCustomerNumber(customer.getCustomerNumber());
	}
	
	/**
	 * Return a list of accounts belonging to the customer number given as argument.
	 * @param customerNumber Customer number
	 * @return List of accounts
	 */
	public List<Account> getAccountByCustomerNumber(String customerNumber) {
		return accountList.stream()
				.filter(s -> s.getCustomerNumber().equals(customerNumber))
				.collect(Collectors.toList());
	}
	
	/**
	 * Return a list of account numbers belonging to the customer number given as argument.
	 * @param customerNumber Customer number.
	 * @return List of account numbers.
	 */
	public List<String> getAccountNumberByCustomerNumber(String customerNumber) {		
		return accountList.stream()
				.filter(s -> s.getCustomerNumber().equals(customerNumber))
				.map(s -> s.getAccountNumber())
				.collect(Collectors.toList());
	}
	
	/**
	 * Return the account with the account number given as argument.
	 * @param accountNumber The account number.
	 * @return The account or null id not found.
	 */
	public Account get(String accountNumber) {
		Optional<Account> opt = accountList.stream()
											.filter(s -> s.getAccountNumber().equals(accountNumber))
											.findFirst(); 
		
		return opt.isPresent() ? opt.get() : null;
	}
	
}
