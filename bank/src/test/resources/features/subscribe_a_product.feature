Feature: Subscribe to a product
  I want to subscribe to a product I have access.

  Scenario: Subscribe to a product for a client who have access to this product
    When I subscribe to a product with id 5 of MrsNormalWantAProduct client
    Then the status code is 200

  Scenario: Subscribe to product that a client do not have access to this product
    When I subscribe to a product with id 8 of MrsNormalWantAProduct client
    Then the status code is 400

  Scenario: Subscribe to product that a client already have
    When I subscribe to a product with id 8 of MrsVipWantAProduct client
    Then the status code is 409
