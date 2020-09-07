Feature: Test User Form Feature
  This will test First Name of the user displays in the first name field of the user form

  @completed
  Scenario: Validate First Name field data value
    Given User logged in as "shalini@kubecloudsinc.com"
    And user navigates to user form page from welcome page
    When User inspects the first name field value
   Then the first name field value should be "Shalini"

  @wip
  Scenario: Change password of the user
    Given  User logged in as "testuserone@kubecloudsinc.com"
    And user navigates to user form page from welcome page
    And Change the password as "brandNewPwd1"
    And logout
    And User "testuserone@kubecloudsinc.com" tries to login with old password
    And Check error with old password
    Then User "testuserone@kubecloudsinc.com" logs in with pwd "brandNewPwd1"

  @completed
  Scenario: First name field is mandatory
    Given User logged in as "shalini@kubecloudsinc.com"
    And user navigates to user form page from welcome page
     And user clears the FirstName field
     And user clicks save button
    Then an error message should show on top of the table
    And an error message should show next to firstname field
