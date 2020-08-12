Feature: Test Login
  This will test when user enter correct username and wrong password
  Scenario: Test Login
    Given User is on Login Page
    When User enters "shalini.kubecloudsinc.com" as username
    And User enters "hellotest" as password
    And User clicks Sign In
    Then User should see the message "Your login attempt was not successful, please try again."