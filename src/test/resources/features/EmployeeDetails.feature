Feature: Employee Details feature

  @completed
  Scenario: To verify that the id columns is present
    Given Valid User is on employee details page
    Then user observes 1 column is "Id"

  @completed
  Scenario: To verify that the first name columns is present
    Given Valid User is on employee details page
    Then user observes 2 column is "First Name"

  @completed
  Scenario: To verify that the last name columns is present
    Given Valid User is on employee details page
    Then user observes 3 column is "Last Name"

  @completed
  Scenario: To verify that the email columns is present
    Given Valid User is on employee details page
    Then user observes 4 column is "Email"

  @completed
  Scenario: To verify that the phone number columns is present
    Given Valid User is on employee details page
    Then user observes 5 column is "Phone Number"