Feature: User Registration
  As a new user
  I want to create an account
  So that I can access the basketball website

  Scenario: Successful user registration
    Given I am on the registration page
    When I fill in all required fields correctly
    And I submit the registration form
    Then I should be redirected to a confirmation page

  Scenario: User registration with missing last name
    Given I am on the registration page
    When I fill in all required fields except the last name
    And I submit the registration form
    Then I should see an error message indicating missing last name

  Scenario: User registration with mismatched passwords
    Given I am on the registration page
    When I fill in all required fields with mismatched passwords
    And I submit the registration form
    Then I should see an error message indicating password mismatch

  Scenario: User registration without accepting terms and conditions
    Given I am on the registration page
    When I fill in all required fields without accepting the terms and conditions
    And I submit the registration form
    Then I should see an error message indicating terms and conditions not accepted
