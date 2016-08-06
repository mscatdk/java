package com.msc.demo.cucumber;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LandingPageSteps {
	
	@Given("^I visit the landing page$")
	public void gotoPage() {
		Hooks.landingPage.gotoPage();
	}

	@Then("^I see the page title \"([^\"]*)\" and the heading \"([^\"]*)\"$")
	public void checkTitleAndHeading(String title, String heading) {
		Assert.assertEquals(title, Hooks.landingPage.getTitle());
		Assert.assertEquals(heading, Hooks.landingPage.getHeading());
	}
	
	@When("^I search for customer with customer number \"([^\"]*)\"$")
	public void searchForCustomer(String customerNumber) {
		Hooks.landingPage.searchForCustomer(customerNumber);
	}

}
