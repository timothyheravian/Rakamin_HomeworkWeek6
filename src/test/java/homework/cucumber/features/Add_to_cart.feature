Feature: add to cart

  Scenario: user want to add to cart a product
    Given user is in dashboard
    When user is choosing desired product and click add to cart button
    Then user will successfully add product to cart