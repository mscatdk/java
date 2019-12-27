package com.msc.demo.das;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.msc.demo.common.DateUtil;
import com.msc.demo.model.Customer;

@Component
public class CustomerDAO {
	
	private List<Customer> customerList = new ArrayList<Customer>();
	
	// Inserting demo data at startup
	{
		Customer customer = new Customer();
		customer.setCustomerNumber("6666664444");
		customer.setName("Bonnie Henderson");
		customer.setBirthDate(DateUtil.getDateTime("1986-08-22"));
		customer.seteMailAddress("bh@company.com");
		customer.setPhoneNumber("805-658-8563");
		customer.setHomeAddress("1969 calle de alberto aguilera");
		customer.setGender("Female");
		
		customerList.add(customer);
		
	}
	
	/**
	 * Locate customer by customer number
	 * @param customerNumber
	 * @return
	 */
	public Optional<Customer> getCustoberByCustomerNumber(String customerNumber) {
		return customerList.stream()
							.filter(s -> s.getCustomerNumber().equals(customerNumber))
							.findFirst();		
	}

}
