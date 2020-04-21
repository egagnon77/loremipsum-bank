Feature: Accept a manual product
    I want to be able to accept a manual product.

    Scenario: Accept a manual product
        When I accept a manual product with id 8 of MrWantAll client
        Then the status code is 200

    Scenario: Accept a manual product of a client that do not exist
        When I accept a manual product with id 8 of InvisibleMen client
        Then the status code is 404

    Scenario: Accept a manual product that do not exist
        When I accept a manual product with id 999 of MrWantAll client
        Then the status code is 404