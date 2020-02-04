@HomePage
Feature: Home Page

  Scenario: A user that is not logged in Travelocity should be able to see the login option
    Given I am on the Travelocity Home page
    And I am not logged in Travelocity
    When I click on the account menu for not logged users
    Then I should be able to see the create account option
	
		