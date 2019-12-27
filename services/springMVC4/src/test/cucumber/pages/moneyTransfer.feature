Feature: Money transfer from one account to another 

Scenario: Money Transfer
	Given I visit the landing page
	When I search for customer with customer number "6666664444"
	And Save account datails for "3337777777"
	And Save account datails for "8888888822"
	And I click the money transfer link
	And request a money transfer of "1000" from account "3337777777" to account "8888888822" 
	Then I see that the balance of account "3337777777" has decrease with "1000"
	Then I see that the balance of account "8888888822" has increased with "1010"