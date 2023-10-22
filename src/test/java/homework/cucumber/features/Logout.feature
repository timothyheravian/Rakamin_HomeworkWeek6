Feature: logout

  Scenario: user can successfully logout from the websites
    Given user is already login
    When user click burger button and click logout
    Then user can successfully logout from the websites