Feature: List all clients waiting for a product approbation
    I want to be able to list all clients waiting for a product approbation

    Scenario: List all clients waiting for a product approbation
        When I list all clients waiting for a product approbation
        Then the status code is 200
        And response contains 3 clients