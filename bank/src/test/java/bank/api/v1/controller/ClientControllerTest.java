package bank.api.v1.controller;

import bank.api.v1.dto.CreateClient;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.api.v1.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

    private static final String A_CLIENT_NAME = "aClientName";

    @Mock
    private ClientService clientService;

    private ClientController testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientController(clientService);
    }

    @Test
    public void givenAClientId_whenGet_thenResponseBodyShouldBeAClient() {

        Client client = new Client(A_CLIENT_NAME);
        when(clientService.get(A_CLIENT_NAME)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.get(A_CLIENT_NAME);

        assertEquals(client, result.getBody());
    }

    @Test
    public void givenAClientId_whenGet_thenResponseHttpStatusMustBeOk() {

        Client client = new Client(A_CLIENT_NAME);
        when(clientService.get(A_CLIENT_NAME)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.get(A_CLIENT_NAME);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenACreateClient_whenSave_thenResponseBodyShouldBeAClient() {

        CreateClient createClient = new CreateClient(A_CLIENT_NAME);
        when(clientService.save(createClient)).thenReturn(createClient);

        ResponseEntity<CreateClient> result = testedClass.save(createClient);

        assertEquals(createClient, result.getBody());
    }

    @Test
    public void givenACreateClient_whenSave_thenResponseHttpStatusMustBeOk() {

        CreateClient createClient = new CreateClient(A_CLIENT_NAME);
        when(clientService.save(createClient)).thenReturn(createClient);

        ResponseEntity<CreateClient> result = testedClass.save(createClient);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenExistingClient_whenGetProducts_thenResponseBodyShouldBeAProductsList() {
        // Given
        Product[] productArray = { new Product(1, "my product", 2) };
        List<Product> products = Arrays.asList(productArray);  
        when(clientService.getProducts(A_CLIENT_NAME)).thenReturn(products);

        // When
        ResponseEntity<List<Product>> result = testedClass.getProducts(A_CLIENT_NAME);

        // Then
        assertEquals(products, result.getBody());
    }

    @Test
    public void givenExistingClient_whenGetProducts_thenResponseHttpStatusMustBeOk() {
        // Given
        Product[] productArray = { new Product(1, "my product", 2) };
        List<Product> products = Arrays.asList(productArray);  
        when(clientService.getProducts(A_CLIENT_NAME)).thenReturn(products);

        // When
        ResponseEntity<List<Product>> result = testedClass.getProducts(A_CLIENT_NAME);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}