Feature: Test Login
  This will test when user enter correct username and password

  Scenario: Test Login
    Given User is on Login Page
    When User enters "shalini@kubecloudsinc.com" as username
    And User enters "Testing1" as password
    And User clicks Sign In
    Then User should see Welcome page with msg "Welcome to Auto Tools"

  Scenario: Test Sign In button background color
    Given User is on Login Page
    Then User should see sign in button with background color "rgb(215, 30, 43)"
