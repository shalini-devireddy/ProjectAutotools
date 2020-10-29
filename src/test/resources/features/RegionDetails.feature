Feature: Region Details feature

#  @completed
#  Scenario: To verify that the  Id column is present
#    Given Valid User is on region details page
#    Then user observes 1 column is "Id"
#
#  @completed
#  Scenario: To verify that the  name column is present
#    Given Valid User is on region details page
#    Then user observes 2 column is "Name"

#  Scenario: To get the countries from region
#    Given Valid User is on region details page
#    Then User observes the countries in "Europe" region

  Scenario Outline: To display the countries in regions
    Given Valid User is on region details page
    And User clicks on "<regionName>" region to observe the countries


    Examples:
      |regionName|
      |Europe|
      |Americas|
      |Asia|
      |Middle East and Africa|