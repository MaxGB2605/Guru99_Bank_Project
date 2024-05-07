Feature: Login functionality

  @regression @validCredentials
    Scenario: Valid admin login
    #Given user is navigated to web application
    When user enters valid userId and password
    And user clicks on login button
    Then user is successfully logged in

    @regression @invalidCredentials
      Scenario: User enters invalid credentials
      When user enters invalid credentials from Excel file
      Then user gets the error message