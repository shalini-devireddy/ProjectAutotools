Feature: Employee Details feature

  Scenario: To verify that the id columns is present
    Given Valid User is on employee details page
    Then user observes 1 column is "Id"

  Scenario: To verify that the first name columns is present
    Given Valid User is on employee details page
    Then user observes 2 column is "First Name"

  Scenario: To verify that the last name columns is present
    Given Valid User is on employee details page
    Then user observes 3 column is "Last Name"

  Scenario: To verify that the email columns is present
    Given Valid User is on employee details page
    Then user observes 4 column is "Email"

  Scenario: To verify that the phone number columns is present
    Given Valid User is on employee details page
    Then user observes 5 column is "Phone Number"