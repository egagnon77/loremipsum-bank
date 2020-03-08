package org.loremipsum;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class CreateClientStepDefinitions {
    //String newClient;
    //IResult result;

    @Given("new client")
    public void new_client() {
        // Write code here that turns the phrase above into concrete actions
        //newClient = "new-client";
    }

    @When("I create the client")
    public void i_create_the_client() {
        // Write code here that turns the phrase above into concrete actions
        //result = Employee.createClient(newClient);
    }

    @Then("I sould receive the new client")
    public void i_sould_receive_the_new_client() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }

    @Given("existing client")
    public void existing_client() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }

    @When("I create the existing client")
    public void i_create_the_existing_client() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }

    @Then("I sould receive an error already exists")
    public void i_sould_receive_an_error_already_exists() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }

    @Given("new client with empty name")
    public void new_client_with_empty_name() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }

    @When("I create the client with empty name")
    public void i_create_the_client_with_empty_name() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }

    @Then("I sould receive an error bad request")
    public void i_sould_receive_an_error_bad_request() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }
}
