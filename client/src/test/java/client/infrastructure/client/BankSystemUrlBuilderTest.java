package client.infrastructure.client;

import client.domain.model.Client;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankSystemUrlBuilderTest {

    private static final String A_CLIENT_NAME = "aClientName";
    private static final String A_BASE_URL = "aBaseUrl";

    private BankSystemUrlBuilder testedClass;

    @Before
    public void setUp() {
        testedClass = new BankSystemUrlBuilder(A_BASE_URL);
    }

    @Test
    public void givenAClient_whenBuildGetProductsUrl_thenUrlMustBeReturnedWithTheNameOfThisClient() {

        Client client = new Client();
        client.setName(A_CLIENT_NAME);

        String result = testedClass.buildGetProductsUrl(client);

        assertEquals(A_BASE_URL + "/client/" + client.getName() + "/products", result);
    }

    @Test
    public void givenAClient_whenBuildGetAvailableProductsUrl_thenUrlMustBeReturnedWithTheNameOfThisClient() {

        Client client = new Client();
        client.setName(A_CLIENT_NAME);

        String result = testedClass.buildGetAvailableProductsUrl(client);

        assertEquals(A_BASE_URL + "/client/" + client.getName() + "/products/available", result);
    }

}