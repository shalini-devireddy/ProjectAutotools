Feature: Test User Form Feature
  This will test First Name of the user displays in the first name field of the user form

  Scenario: Validate First Name field data value
    Given User logged in as "shalini@kubecloudsinc.com"
    And user navigates to user form page
    When User inspects the first name field value
    Then the first name field value should be "Shalini"
