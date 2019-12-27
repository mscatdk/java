package com.msc.demo.cucumber;

import java.math.BigDecimal;

import org.junit.Assert;

import com.msc.demo.selenium.pagemodel.CustomerPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CustomerOverviewSteps {
	
	@Then("^I see customer number \"([^\"]*)\" and customer name \"([^\"]*)\"$")
	public void checkCustomerNumberAndCustomerName(String customerNumber, String customerName) {
		Assert.assertEquals(customerNumber, Hooks.customerPage.getCustomerNumber());
		Assert.assertEquals(customerName, Hooks.customerPage.getCustomerName());
	}
	
	@And("^I click the money transfer link$")
	public void clickMoneyTransferLink() {
		Hooks.customerPage.gotoMoneyTransfer();
	}
	
	@And("^Save account datails for \"([^\"]*)\"$")
	public void saveAccountDetails(String accountNumber) {
		Hooks.customerPage.saveAccountDetails(accountNumber);
	}
	
	@Then("^I see that the balance of account \"([^\"]*)\" has decrease with \"([^\"]*)\"$")
	public void checkDecreasedAccountBalance(String accountNumber, String amountString) {
		BigDecimal amount = new BigDecimal(amountString);
		checkBalanceChange(Hooks.customerPage, accountNumber, amount.negate());
	}
	
	@Then("^I see that the balance of account \"([^\"]*)\" has increased with \"([^\"]*)\"$")
	public void checkIncreasedAccountBalance(String accountNumber, String amountString) {
		BigDecimal amount = new BigDecimal(amountString);
		checkBalanceChange(Hooks.customerPage, accountNumber, amount);
	}
	
	public void checkBalanceChange(CustomerPage customerPage, String accountNumber, BigDecimal amount) {
		BigDecimal oldBalance = customerPage.getSavedValueBigDecimal(accountNumber, CustomerPage.ACCOUNT_BALANCE);
		BigDecimal currentBalance = customerPage.getAccountBalance(accountNumber);
		 
		Assert.assertTrue(oldBalance.add(amount).compareTo(currentBalance) == 0);
	}

}
