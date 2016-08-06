Feature: Customer Overview page  

Scenario: Customer Search
	Given I visit the landing page
	When I search for customer with customer number "6666664444"
	Then I see customer number "6666664444" and customer name "Bonnie Henderson"