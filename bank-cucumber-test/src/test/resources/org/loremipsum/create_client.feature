Feature: Create client by name
    An employee want to create a client

    Scenario: Create new client
        Given new client
        When I create the client 
        Then I sould receive the new client

    Scenario: Create existing client
        Given existing client
        When I create the existing client 
        Then I sould receive an error already exists

    Scenario: Create new client with empty name
        Given new client with empty name
        When I create the client with empty name 
        Then I sould receive an error bad request