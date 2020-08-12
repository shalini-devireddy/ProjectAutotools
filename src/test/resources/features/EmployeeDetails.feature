Feature: Employee Details feature

  Scenario: To verify that the first name columns is present
    Given User is on employee details page
    And user inspects 2 column in the employee table
    Then the column name should be First Name