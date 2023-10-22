Feature: checkout

  Scenario: user want to pay the product
    Given user is already add the product and click checkout
    When user is filling checkout information
    And user checkout the product and click finish button
    Then user will successfully pay the product