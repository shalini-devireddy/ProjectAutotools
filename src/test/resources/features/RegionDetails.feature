Feature: Region Details feature


  Scenario: To verify that the  Id column is present
    Given Valid User is on region details page
    Then user observes 1 column is "Id"


  Scenario: To verify that the  name column is present
    Given Valid User is on region details page
    Then user observes 2 column is "Name"