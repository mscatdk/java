Feature: Landing page has correct title and heading

Scenario: Goto Landing page
	Given I visit the landing page
	Then I see the page title "Demo Bank" and the heading "Demo Bank"