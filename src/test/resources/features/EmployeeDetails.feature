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

  Scenario: To verify the total number of employees in Employees table
    Given Valid User is on employee details page
    Then 107 employees should display in employees table

  Scenario: To verify the last employee name in Employees table
    Given Valid User is on employee details page
    Then user observes the last employee name is "Winston"