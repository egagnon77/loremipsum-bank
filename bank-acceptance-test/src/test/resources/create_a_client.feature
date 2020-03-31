Feature: Create a client with name
    I want to be able to add a client in the bank.

    Scenario: Create a client with name
        Given a client with a name of Mario
        When I create a client
        Then the status code is 200
        And response include the following
        | name | Mario |

    Scenario: Create a client with name that already exist
        Given a client with a name of Luigi
        When I create a client
        Then the status code is 200
        Given a client with a name of Luigi
        When I create a client
        Then the status code is 409