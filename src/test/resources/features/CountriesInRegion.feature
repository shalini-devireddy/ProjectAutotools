Feature: Countries in region feature

  Scenario: To verify that the  Id column is present
    Given Valid User is on countries in "Europe" region page
    Then user observes 1 column is "Id"

  Scenario: To verify that the  name column is present
    Given Valid User is on countries in "Americas" region page
    Then user observes 2 column is "Name"

  Scenario Outline: To verify the count of countries in regions
    Given Valid User is on countries in "<regionName>" region page
    Then the user counts the countries and validates the count to be correct

    Examples:
      |regionName|
      |Europe|
      |Americas|
      |Asia|
      |Middle East and Africa|