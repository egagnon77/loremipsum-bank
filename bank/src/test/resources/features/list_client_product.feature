Feature: List all products of a client
    I want to be able to list all product of a client.

    Scenario: List product of a client with no product
        When I get products of PoorMen client
        Then the status code is 200
        And response contains 0 products

    Scenario: List product of a client with one product
        When I get products of MiddleMen client
        Then the status code is 200
        And response contains 1 products

    Scenario: List product of a client with two products
        When I get products of RichMen client
        Then the status code is 200
        And response contains 2 products