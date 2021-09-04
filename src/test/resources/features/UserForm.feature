Feature: Test User Form Feature
  This will test First Name of the user displays in the first name field of the user form

  Scenario: Validate First Name field data value
    Given User logged in as "shalini@kubecloudsinc.com"
    And user navigates to user form page from welcome page
    When User inspects the first name field value
   Then the first name field value should be "Shalini"
#failed
  @completed
  Scenario: Change password of the user
    Given  User logged in as "testuserone@kubecloudsinc.com"
    And user navigates to user form page from welcome page
    And Change the password to new password
    And logout
    And the same user to login with old password
    And Check error with old password
    Then the same user logs in with new pwd

  @completed
  Scenario: First name field is mandatory
    Given User logged in as "shalini@kubecloudsinc.com"
    And user navigates to user form page from welcome page
     And user clears the FirstName field
     And user clicks save button
    Then an error message "Form Errors" should show on top of the table
    And an error message should show next to firstname field
  @completed
  Scenario: Last name field is mandatory
    Given User logged in as "shalini@kubecloudsinc.com"
    And user navigates to user form page from welcome page
    And user clears the Last Name field
    And user clicks save button
    Then an error message "Form Errors" should show on top of the table
    And an error message should show next to lastname field
#    faled
   @completed
   Scenario: Title field is not mandatory
     Given User logged in as "shalini@kubecloudsinc.com"
     And user navigates to user form page from welcome page
     And user clears the Title field
     And user clicks save button
     And a success message should display on top of the table
#  failed
   Scenario: Organization field is not mandatory
     Given User logged in as "shalini@kubecloudsinc.com"
     And user navigates to user form page from welcome page
     And user clears the Organization field
     And user clicks save button
     And a success message should display on top of the table

   @completed
   Scenario: Email field is mandatory
     Given User logged in as "shalini@kubecloudsinc.com"
    And user navigates to user form page from welcome page
    Then user can not edit the Email field

#   Scenario: