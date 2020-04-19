package client.infrastructure.client;

import client.domain.exception.NotFoundException;
import client.domain.model.Client;
import client.domain.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpringWebBankClientTest {

    private static final String A_NAME = "aName";
    private static final String A_GET_PRODUCTS_URL = "aGetProductsUrl";

    @Mock
    private BankSystemUrlBuilder bankSystemUrlBuilder;
    @Mock
    private RestTemplate restTemplate;

    private Client client;

    private SpringWebBankClient testedClass;

    @Before
    public void setUp() {
        testedClass = new SpringWebBankClient(bankSystemUrlBuilder, restTemplate);
    }

    @Before
    public void initClient() {
        client = new Client();
        client.setName(A_NAME);
    }



    @Test
    public void givenAClient_whenGetProducts_thenProductsMustBeReturned() {
        when(bankSystemUrlBuilder.buildGetProductsUrl(client)).thenReturn(A_GET_PRODUCTS_URL);
        Product[] products = new Product[1];
        ResponseEntity<Product[]> responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        when(restTemplate.getForEntity(A_GET_PRODUCTS_URL, Product[].class)).thenReturn(responseEntity);

        List<Product> result = testedClass.getProducts(client);

        assertEquals(Arrays.asList(products), result);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetProductsReturnANullBody_thenANullPointerExceptionMustBeThrown() {
        when(bankSystemUrlBuilder.buildGetProductsUrl(client)).thenReturn(A_GET_PRODUCTS_URL);
        ResponseEntity<Product[]> responseEntity = new ResponseEntity<>(null, HttpStatus.OK);
        when(restTemplate.getForEntity(A_GET_PRODUCTS_URL, Product[].class)).thenReturn(responseEntity);

        testedClass.getProducts(client);
    }

    @Test(expected = NotFoundException.class)
    public void whenGetProductsFail_thenANotFoundExceptionMustBeThrown() {
        when(bankSystemUrlBuilder.buildGetProductsUrl(client)).thenReturn(A_GET_PRODUCTS_URL);
        when(restTemplate.getForEntity(A_GET_PRODUCTS_URL, Product[].class)).thenThrow(HttpClientErrorException.class);

        testedClass.getProducts(client);
    }

    @Test
    public void givenAClient_whenGetAvailableProducts_thenProductsMustBeReturned() {
        when(bankSystemUrlBuilder.buildGetAvailableProductsUrl(client)).thenReturn(A_GET_PRODUCTS_URL);
        Product[] products = new Product[1];
        ResponseEntity<Product[]> responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        when(restTemplate.getForEntity(A_GET_PRODUCTS_URL, Product[].class)).thenReturn(responseEntity);

        List<Product> result = testedClass.getAvailableProducts(client);

        assertEquals(Arrays.asList(products), result);
    }

    @Test(expected = NotFoundException.class)
    public void whenGetAvailableProductsFail_thenANotFoundExceptionMustBeThrown() {
        when(bankSystemUrlBuilder.buildGetAvailableProductsUrl(client)).thenReturn(A_GET_PRODUCTS_URL);
        when(restTemplate.getForEntity(A_GET_PRODUCTS_URL, Product[].class)).thenThrow(HttpClientErrorException.class);

        testedClass.getAvailableProducts(client);
    }
}