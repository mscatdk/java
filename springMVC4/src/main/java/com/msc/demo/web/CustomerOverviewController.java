package com.msc.demo.web;



import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msc.demo.common.Config;
import com.msc.demo.common.Controllers;
import com.msc.demo.das.AccountDAO;
import com.msc.demo.das.CustomerDAO;
import com.msc.demo.model.Customer;

@Controller
public class CustomerOverviewController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private AccountDAO accountDAO;
	
	@RequestMapping(value = Config.CUSTOMER_OVERVIEW_URL, method = RequestMethod.GET)
	public ModelAndView customerSearch(ModelAndView model, HttpServletRequest req, RedirectAttributes redirect) {
		String customerNumber = req.getParameter("customerNumber");
		Customer customer = (Customer) model.getModel().get("customer");
	
		if (customer == null) {
			if (customerNumber == null) {
				return Controllers.addErrorMessageAndRedirect("Please input a customer number!", "/", redirect);
			} else {
				Optional<Customer> customerOpt = customerDAO.getCustoberByCustomerNumber(customerNumber);
				if (customerOpt.isPresent()) {
					customer = customerOpt.get();
					model.addObject("customer", customer);
				} else {
					return Controllers.addErrorMessageAndRedirect("Can't find customer with customer number = " + customerNumber, "/", redirect);
				}
			}
		}
		
		// Find all accounts
		model.addObject("accountList", accountDAO.getAccountForCustomer(customer));
		model.addObject("customerNumber", customerNumber);
		model.setViewName(Config.CUSTOMER_OVERVIEW_VIEW);
		
		return model;
	}	
}
