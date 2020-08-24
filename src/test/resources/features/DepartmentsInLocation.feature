Feature: Departments in locations

  Scenario: To verify that the  Id column is present
    Given Valid User is on departments in location page
    Then user observes 1 column is "Id"

  Scenario: To verify that the  name column is present
    Given Valid User is on departments in location page
    Then user observes 2 column is "Department Name"
