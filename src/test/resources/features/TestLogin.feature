@login
Feature: Test Login
  This will test when user enter correct username and password

  @completed
  Scenario: Test Login
    Given User is on Login Page
    When User enters "shalini@kubecloudsinc.com" as username
    And User enters "Testing1!" as password
    And User clicks Sign In
    Then User should see Welcome page

  @completed
  Scenario: Test Sign In button background color
    Given User is on Login Page
    Then User should see sign in button with background color "rgb(215, 30, 43)"

  Scenario: Test remember me check box
    Given User is on Login Page
    When User enters "shalini@kubecloudsinc.com" as username
    And User enters "Testing1" as password
#    And User checks remember me check box
    And User clicks Sign In
    And User should see Welcome page
    And user navigates to user form page from welcome page
    And logout
    Then User form direct call should take to login page with "Login to Auto Tools"

#  Scenario: Test remember me check box
#    Given User is on Login Page
#    When User enters "shalini@kubecloudsinc.com" as username
#    And User enters "Testing1" as password
#    And User checks remember me check box
#    And User clicks Sign In
#    And User should see Welcome page with msg "Welcome to Auto Tools"
#    And User closes the browser
#    Then User form direct call should take to userform page "User Form"