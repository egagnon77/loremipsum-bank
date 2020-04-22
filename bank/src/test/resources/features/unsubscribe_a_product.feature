Feature: Unsubscribe to a product
  I want to unsubscribe a product I have.

  Scenario: Unsubscribe a manual product that a client have
    When I unsubscribe to a product with id 5 of ClientDontWantAutomaticProductAnymore client
    Then the status code is 200

  Scenario: Unsubscribe to a automatic product for a client who have access to this product
    When I unsubscribe to a product with id 6 of ClientDontWantManualProductAnymore client
    Then the status code is 200

  Scenario: Unsubscribe a manual product with approbationStatus of Waiting for approbation that a client have
    When I unsubscribe to a product with id 5 of ClientWithProductWaitingForSubscribe client
    Then the status code is 409

  Scenario: Unsubscribe a product that a client don't have
    When I unsubscribe to a product with id 5 of ClientWithNoProductWantToUnsubscribe client
    Then the status code is 404



