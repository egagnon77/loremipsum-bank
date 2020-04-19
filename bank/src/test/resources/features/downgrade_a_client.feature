Feature: Downgrade a client status
  I want to be able to downgrade a client status.

  Scenario: Downgrade a client with a normal status
    Given a client with a name of NormalMenToDowngrade
    When I downgrade status of NormalMenToDowngrade
    Then the status code is 200
    And NormalMenToDowngrade status is NORMAL

  Scenario: Downgrade a client with a VIP status
    Given a client with a name of VIPMenToDowngrade
    When I downgrade status of VIPMenToDowngrade
    Then the status code is 200
    And VIPMenToDowngrade status is NORMAL

  Scenario: Downgrade a client that do not exist
    Given a client with a name of InvisibleMen
    When I downgrade status of InvisibleMen
    Then the status code is 404