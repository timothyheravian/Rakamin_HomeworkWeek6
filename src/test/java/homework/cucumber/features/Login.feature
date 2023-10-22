Feature: login

  Scenario: user can successfully login with valid credentials
    Given user is in login page
    When user input valid credentials and click login button
    Then user can successfully login and redirect to dashboard page

  Scenario: user can not successfully login using invalid username
    Given user is in login page
    When user input invalid username and valid password and click login button
    Then user can not successfully login and still in login page