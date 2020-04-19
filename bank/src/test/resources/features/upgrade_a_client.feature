Feature: Upgrade a client status
  I want to be able to upgrade a client status.

  Scenario: Upgrade a client with a normal status
    Given a client with a name of NormalMen
    When I upgrade status of NormalMen
    Then the status code is 200
    And NormalMen status is VIP

  Scenario: Upgrade a client with a VIP status
    Given a client with a name of VIPMen
    When I upgrade status of VIPMen
    Then the status code is 200
    And VIPMen status is VIP

  Scenario: Upgrade a client that do not exist
    Given a client with a name of InvisibleMen
    When I upgrade status of InvisibleMen
    Then the status code is 404