package bank.acceptance;

import bank.api.v1.dto.CreateClient;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.model.ProductLevel;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestsStepDefs {

    private String CLIENT_URL = "http://localhost:8081/api/bank/v1/client";

    private RequestSpecification request;
    private Response response;
    private ValidatableResponse validatableResponse;

    @Given("a client with a name of (.*)")
    public void a_client_with_a_name(String name) {
        CreateClient createClient = new CreateClient(name);
        request = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(createClient);
    }

    @When("I create a client")
    public void i_create_a_client() {
        response = request.when().post(CLIENT_URL);
    }

    @When("I get products of (.*) client")
    public void i_get_products_of_a_client(String name) {
        request = given();
        response = request.when().get(CLIENT_URL + "/" + name + "/products");
    }

    @When("I upgrade status of (.*)")
    public void i_upgrade_status_of_a_client(String name) {
        request = given();
        response = request.when().patch(CLIENT_URL + "/" + name + "/status/upgrade");
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

    @And("response contains {int} products")
    public void response_contains_products(int numberOfProducts) {
        List<Product> products = Arrays.asList(response.as(Product[].class));
        Assert.assertEquals(numberOfProducts, products.size());
    }

    @And("(.*) status is (.*)")
    public void client_status_is(String name, String status) {
        Client client = response.as(Client.class);
        Assert.assertEquals(ProductLevel.valueOf(status).getValue(), client.getProductLevel());
    }
}