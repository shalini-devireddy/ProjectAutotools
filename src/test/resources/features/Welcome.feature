@ Login
  Feature: Test welcome
    this will test
    Scenario: Test verify the welcome message
      Given User is on Login Page
      When User enters "shalini@kubecloudsinc.com" as username
      And User enters "Testing1!" as password
      And User clicks Sign In
      And User is in Welcome page
      Then User should see the menubar on the top of the welcome page header
      And User closes the browser