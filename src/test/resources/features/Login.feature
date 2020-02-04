@Login
Feature: Login

  Scenario Outline: A registered user should be able to log in to Travelocity with valid user and valid password
    Given I am on the Travelocity Home page
    And I click on the account menu for not logged users
    And I click on the available option to log in from the account menu
    And I enter a username as <user> on the user field
    And I enter a password as <password> on the password field
    And I click on the login option
    Then I should be able to see the account menu option for my logged user

    Examples:

      | user         | password     |
      | "validUser1" | "validPass1" |
      | "validUser2" | "validPass2" |
		
		