Feature: List all available products for a client
  I want to be able to list all available product for a client.

  Scenario: List product of a client with normal product level
    When I get available products of NormalMenToList client
    Then the status code is 200
    And response contains 4 products

  Scenario: List product of a client with vip product level
    When I get available products of VIPMenToList client
    Then the status code is 200
    And response contains 8 products