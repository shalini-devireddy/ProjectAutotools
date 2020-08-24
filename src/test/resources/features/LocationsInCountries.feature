Feature: Locations in Countries

  Scenario: To verify that the  Id column is present
    Given Valid User navigates to locations in country "United Kingdom"
    Then user observes 1 column is "Id"

  Scenario: To verify that the  name column is present
    Given Valid User navigates to locations in country "United Kingdom"
    Then user observes 2 column is "Street Address"

  Scenario: To verify that the  Id column is present
    Given Valid User navigates to locations in country "United Kingdom"
    Then user observes 3 column is "Postal Code"

  Scenario: To verify that the  Id column is present
    Given Valid User navigates to locations in country "United Kingdom"
    Then user observes 4 column is "City"

  Scenario: To verify that the  Id column is present
    Given Valid User navigates to locations in country "United Kingdom"
    Then user observes 5 column is "State or Province"