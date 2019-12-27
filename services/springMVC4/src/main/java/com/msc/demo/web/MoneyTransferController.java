package com.msc.demo.web;

import java.math.BigDecimal;
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
import com.msc.demo.model.MovementType;

@Controller
public class MoneyTransferController {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private MovementDAO movementDAO;
	
	@RequestMapping(value = Config.MONEY_TRANSFER_URL, method = RequestMethod.GET)
	public ModelAndView moneyTransferGet(ModelAndView model, HttpServletRequest req, RedirectAttributes redirect) {
		String customerNumber = req.getParameter("customerNumber");
		
		if (customerNumber == null) {
			return Controllers.addErrorMessageAndRedirect("Please provide customer number!", "/", redirect);
		}
		
		List<String> accountNumber =  accountDAO.getAccountNumberByCustomerNumber(customerNumber);
		model.addObject("accountNumbers", accountNumber);
		model.addObject("customerNumber", customerNumber);
		model.setViewName(Config.MONEY_TRANSFER_VIEW);
		return model;
	}
	
	@RequestMapping(value = Config.MONEY_TRANSFER_URL, method = RequestMethod.POST)
	public ModelAndView moneyTransferPost(HttpServletRequest req, RedirectAttributes redirect) {
		String referer = req.getHeader("Referer");
		
		String fromAccountNumber = req.getParameter("fromAccount");
		String toAccountNumber = req.getParameter("toAccount");
		String amountString = req.getParameter("amount");
		
		// buidness logic
		Account fromAccount = accountDAO.get(fromAccountNumber);
		if (fromAccount == null) {
			return Controllers.addErrorMessageAndRedirect(String.format("The 'from account' with account number %s does not exists", fromAccountNumber), referer, redirect);
		}
		Account toAccount = accountDAO.get(toAccountNumber);
		if (toAccount == null) {
			return Controllers.addErrorMessageAndRedirect(String.format("The 'to account' with account number %s does not exists", toAccountNumber), referer, redirect);
		}
		BigDecimal amount = new BigDecimal(amountString);
		
		
		fromAccount.updateBalance(amount.negate());
		toAccount.updateBalance(amount);
		
		Movement movementCredit = new Movement(fromAccount, toAccount, amount, MovementType.MONEY_TRANSFER);
		movementDAO.save(movementCredit);
		
		Movement movementDebit = new Movement(toAccount, fromAccount, amount.negate(), MovementType.MONEY_TRANSFER);
		movementDAO.save(movementDebit);
		
		return Controllers.addSuccessMessageAndRedirect("The money transfer has been completed succesfully!", "/customerOverview?customerNumber=" + fromAccount.getCustomerNumber(), redirect);
		
	}
	
}
