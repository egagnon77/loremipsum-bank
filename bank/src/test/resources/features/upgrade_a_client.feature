Feature: Upgrade a client status
  I want to be able to upgrade a client status.

  Scenario: Upgrade a client with a normal status
    Given a client with a name of NormalMenToUpgrade
    When I upgrade status of NormalMenToUpgrade
    Then the status code is 200
    And NormalMenToUpgrade status is VIP

  Scenario: Upgrade a client with a VIP status
    Given a client with a name of VIPMenToUpgrade
    When I upgrade status of VIPMenToUpgrade
    Then the status code is 200
    And VIPMenToUpgrade status is VIP

  Scenario: Upgrade a client that do not exist
    Given a client with a name of InvisibleMen
    When I upgrade status of InvisibleMen
    Then the status code is 404