@login
Feature: Test Login
  This will test when user enter correct username and password

  @337701 @337721
  Scenario: Test url
    Given User enters the url in the browser
    Then User should see the header "Login to Auto Tools"
    And User closes the browser

    @337724 @337861
  Scenario: Test login page header text
    Given User is on Login Page
    Then User should see the header in bold
    And User closes the browser

   @337741 @337864
  Scenario: Test login page header
    Given User is on Login Page
    Then User should see login page header font size is "38.5px"
    And User closes the browser

  @337761
  Scenario: To verify Email label
    Given User is on Login Page
    Then User should see the label "Email"
    And User closes the browser

  @337765
  Scenario: To verify email input box
    Given User is on Login Page
    Then User should see email input box
    And User closes the browser

  @337769
  Scenario: To verify Password label
    Given User is on Login Page
    Then User should see the "Password" label

  @337773
  Scenario: To verify password input box
    Given User is on Login Page
    Then User should see password input box
    And User closes the browser

  @337777 @337783 @testFeature
  Scenario: Test verify the welcome message
    Given User is on Login Page
    When User enters "shalini@kubecloudsinc.com" as username
    And User enters "Testing1!" as password
    And User clicks Sign In
    Then User should see Welcome page
    And User closes the browser

  @337786 @337793 @337799
  Scenario: Test Login with wrong credentials
    Given User is on Login Page
    When User enters "shalini.kubecloudsinc.com" as username
    And User enters "hellotest" as password
    And User clicks Sign In
    Then User should see error message
    And User closes the browser

  @337841
  Scenario: Test remember me check box
    Given User is on Login Page
    When User enters "shalini@kubecloudsinc.com" as username
    And User enters "Testing1!" as password
    And User checks remember me check box
    And User clicks Sign In
    And User should see Welcome page
    And user navigates to user form page from welcome page
    And logout
    Then User form direct call should take to login page with "Login to Auto Tools"
    And User closes the browser

  @337885 @337881
  Scenario: Test Login menu
    Given User is on Login Page
    Then User should see the login menu item
    And User closes the browser

#  @337889 (not yet done)
#  Scenario: Test Login menu item
#    Given User is on Login Page
#    When User clicks the login menu item
#    Then User should stay in login page

#   @oneTest
#   Scenario: Test Footer of the login page
#     Given User is on Login Page
#     Then User shoud see the footer "© 2020 kubecloudsinc.com" at the bottom of the page

#  @oneTest
#  Scenario: Test Sign In button background color
#    Given User is on Login Page
#    Then User should see sign in button with background color "rgb(215, 30, 43)"


#  Scenario: Test remember me check box
#    Given User is on Login Page
#    When User enters "shalini@kubecloudsinc.com" as username
#    And User enters "Testing1!" as password
#    And User checks remember me check box
#    And User clicks Sign In
#    And User should see Welcome page
#    And user navigates to user form page from welcome page
#    And User closes the browser
#    Then User form direct call should take to userform page