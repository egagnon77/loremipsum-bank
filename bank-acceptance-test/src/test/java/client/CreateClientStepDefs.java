package client;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateClientStepDefs {

    private static final String CLIENT_URL = "http://localhost:8081/api/bank/v1/client";

    private RequestSpecification request;
    private Response response;
    private ValidatableResponse validatableResponse;

    @Given("a client with a name of (.*)")
    public void a_client_with_a_name(String name) {
        CreateClientDto createClient = new CreateClientDto(name);
        request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(createClient);
    }

    @When("I create a client")
    public void i_create_a_client() {
        response = request.when().post(CLIENT_URL);
    }

    @Then("the status code is {int}")
    public void verify_status_code(int statusCode) {
        validatableResponse = response.then().statusCode(statusCode);
    }

    @And("response include the following$")
    public void response_equals(Map<String, String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            validatableResponse.body(field.getKey(), equalTo(field.getValue()));
        }
    }
}