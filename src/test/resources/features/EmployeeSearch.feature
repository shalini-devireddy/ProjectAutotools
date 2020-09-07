Feature: Test Employee search

  Scenario:Test search
    Given Valid user is on employee search page
    When User enters "Alexis" as first name
    And User enters "Bull" as last name
    And User clicks search
    Then User should see employee page with header "Alexis Bull"

  Scenario: To verify that the Phone Number: label is present
    Given Valid user is on employee search page
    When User enters "Alexis" as first name
    And User enters "Bull" as last name
    And User clicks search
    Then user observes label 2 is "Phone Number:"