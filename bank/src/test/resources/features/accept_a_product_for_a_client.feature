Feature: Accept a manual product for a client
  I want to be able accept a manual product for a client.

  Scenario: Accept a manual product for a client
    When I accept product 8 for client MrWantAll
    Then the status code is 200
    And MrWantAll is now subscribed to product id 8

  Scenario: Accept a manual product for a client that do not exist
    When I accept product 8 for client InvisibleMen
    Then the status code is 404

  Scenario: Accept a product that do not exist for a client
    When I accept product 999 for client MrWantAll
    Then the status code is 404