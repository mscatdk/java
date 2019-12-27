package com.msc.demo.cucumber;

import cucumber.api.java.en.And;

public class MoneyTransferSteps {
	
	
	@And("^request a money transfer of \"([^\"]*)\" from account \"([^\"]*)\" to account \"([^\"]*)\"$")
	public void transferMoney(String amount, String fromAccount, String toAccount) {
		Hooks.moneyTransferPage	.setFromAccount(fromAccount)
								.setToAccount(toAccount)
								.setAmount(amount)
								.commit();
	}

}
