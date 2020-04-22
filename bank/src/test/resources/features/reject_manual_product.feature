Feature: Reject a manual product
    I want to be able to reject a manual product.

    Scenario: Reject a manual product
        When I reject a manual product with id 8 of MrWantNothing client
        Then the status code is 200

    Scenario: Reject a manual product of a client that do not exist
        When I reject a manual product with id 8 of InvisibleMen client
        Then the status code is 404

    Scenario: Reject a manual product that do not exist
        When I reject a manual product with id 999 of MrWantNothing client
        Then the status code is 404