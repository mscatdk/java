package com.msc.demo.web;

import java.util.List;

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
import com.msc.demo.das.MovementDAO;
import com.msc.demo.model.Account;
import com.msc.demo.model.Movement;

@Controller
public class MovementController {
	
	@Autowired
	private MovementDAO movementDAO;
	
	@Autowired
	private AccountDAO accountDAO;

	@RequestMapping(value = Config.MOVEMENT_OVERVIEW_URL, method = RequestMethod.GET)
	public ModelAndView customerSearchPost(HttpServletRequest req, RedirectAttributes redirect) {
		ModelAndView model = new ModelAndView();
		String referer = req.getHeader("Referer");
		String customerNumber = req.getParameter("customerNumber");
		String accountNumber = req.getParameter("accountNumber");
		
		try {
			List<Movement> movementList = movementDAO.getMovementByAccount(accountNumber);
			Account account = accountDAO.get(accountNumber);
			
			model.addObject("customerNumber", customerNumber);
			model.addObject("accountNumber", accountNumber);
			model.addObject("account", account);
			model.addObject("movementList", movementList);
			model.setViewName(Config.MOVEMENT_OVERVIEW_VIEW);
			
			return model;
		} catch (Exception e) {
			return Controllers.addErrorMessageAndRedirect("Exception: " + e.getMessage(), referer, redirect);
		}
	}
	
}
