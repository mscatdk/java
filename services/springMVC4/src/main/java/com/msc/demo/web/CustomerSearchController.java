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
import com.msc.demo.das.CustomerDAO;
import com.msc.demo.model.Customer;

@Controller
public class CustomerSearchController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping(value = Config.CUSTOMER_SEARCH_URL, method = RequestMethod.POST)
	public ModelAndView customerSearchPost(HttpServletRequest req, RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView();
		String referer = req.getHeader("Referer");
		
		try {
			String customerNumber = req.getParameter("srch-term");
			
			Optional<Customer> customer = customerDAO.getCustoberByCustomerNumber(customerNumber);
			
			if (customer.isPresent()) {
				model.setViewName("redirect:" + "/customerOverview");
				model.addObject("customerNumber", customerNumber);
				redirect.addFlashAttribute("customer", customer.get());
				
				return model;
			} else {
				return Controllers.addErrorMessageAndRedirect("Can't find customer with customer number = " + customerNumber, referer, redirect);
			}
		} catch (Exception e) {
			return Controllers.addErrorMessageAndRedirect("Exception: " + e.getMessage(), referer, redirect);
		}
	}

}
