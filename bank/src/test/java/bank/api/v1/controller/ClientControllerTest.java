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
import org.springframework.web.util.HtmlUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

    private static final String A_CLIENT_NAME = "aClientName";
    private static final Integer A_PRODUCT_ID = 8;

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
    public void givenAClientName_whenUpgradeStatus_thenResponseBodyIsAClient() {

        Client client = new Client(A_CLIENT_NAME);
        when(clientService.upgradeStatus(HtmlUtils.htmlEscape(A_CLIENT_NAME))).thenReturn(client);

        ResponseEntity<Client> result = testedClass.upgradeStatus(A_CLIENT_NAME);

        assertEquals(client, result.getBody());
    }

    @Test
    public void givenAClientName_whenUpgradeStatus_thenResponseHttpStatusIsOk() {

        Client client = new Client(A_CLIENT_NAME);
        when(clientService.upgradeStatus(HtmlUtils.htmlEscape(A_CLIENT_NAME))).thenReturn(client);

        ResponseEntity<Client> result = testedClass.upgradeStatus(A_CLIENT_NAME);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAClientName_whenDowngradeStatus_thenResponseBodyIsAClient() {

        Client client = new Client(A_CLIENT_NAME);
        when(clientService.downgradeStatus(HtmlUtils.htmlEscape(A_CLIENT_NAME))).thenReturn(client);

        ResponseEntity<Client> result = testedClass.downgradeStatus(A_CLIENT_NAME);

        assertEquals(client, result.getBody());
    }

    @Test
    public void givenAClientName_whenDowngradeStatus_thenResponseHttpStatusIsOk() {

        Client client = new Client(A_CLIENT_NAME);
        when(clientService.downgradeStatus(HtmlUtils.htmlEscape(A_CLIENT_NAME))).thenReturn(client);

        ResponseEntity<Client> result = testedClass.downgradeStatus(A_CLIENT_NAME);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAClientName_whenGetAvailableProducts_thenAListOfProductIsReturned() {

        List<Product> products = new ArrayList<>();
        when(clientService.getAvailableProducts(HtmlUtils.htmlEscape(A_CLIENT_NAME))).thenReturn(products);

        ResponseEntity<List<Product>> result = testedClass.getAvailableProducts(A_CLIENT_NAME);

        assertEquals(products, result.getBody());
    }

    @Test
    public void givenAClientName_whenGetAvailableProducts_thenResponseHttpStatusIsOk() {

        List<Product> products = new ArrayList<>();
        when(clientService.getAvailableProducts(HtmlUtils.htmlEscape(A_CLIENT_NAME))).thenReturn(products);

        ResponseEntity<List<Product>> result = testedClass.getAvailableProducts(A_CLIENT_NAME);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAClientNameAndAProductId_whenAcceptManualProduct_thenResponseHttpStatusIsOk() {

        ResponseEntity<Void> result = testedClass.acceptManualProduct(A_CLIENT_NAME, A_PRODUCT_ID);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAClientNameAndAProductId_whenRejectManualProduct_thenResponseHttpStatusIsOk() {

        ResponseEntity<Void> result = testedClass.rejectManualProduct(A_CLIENT_NAME, A_PRODUCT_ID);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenExistingClient_whenGetProducts_thenResponseBodyShouldBeAProductsList() {
        // Given
        Product[] productArray = { new Product(1, "my product", 0, 1) };
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
        Product[] productArray = { new Product(1, "my product", 0,1) };
        List<Product> products = Arrays.asList(productArray);
        when(clientService.getProducts(A_CLIENT_NAME)).thenReturn(products);

        // When
        ResponseEntity<List<Product>> result = testedClass.getProducts(A_CLIENT_NAME);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAClientNameAndAProductId_whenSubscribeProduct_thenResponseHttpStatusIsOk() {

        ResponseEntity<Void> result = testedClass.subscribeProduct(A_CLIENT_NAME, A_PRODUCT_ID);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}