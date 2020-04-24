package client.infrastructure.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import client.domain.exception.DataSourceBadResponseException;
import client.domain.model.Client;
import client.domain.model.Product;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class SpringWebBankClientTest {

    private static final String A_NAME = "aName";
    private static final String A_GET_PRODUCTS_URL = "aGetProductsUrl";
    private static final Integer A_PRODUCT_ID = 1;

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

    @Test(expected = DataSourceBadResponseException.class)
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

    @Test(expected = DataSourceBadResponseException.class)
    public void whenGetAvailableProductsFail_thenANotFoundExceptionMustBeThrown() {
        when(bankSystemUrlBuilder.buildGetAvailableProductsUrl(client)).thenReturn(A_GET_PRODUCTS_URL);
        when(restTemplate.getForEntity(A_GET_PRODUCTS_URL, Product[].class)).thenThrow(HttpClientErrorException.class);

        testedClass.getAvailableProducts(client);
    }

    @Test()
    public void givenAClientAndAProduct_whenSubscribeProduct_thenRestemplateWithPutMustBeCalled() {
        when(bankSystemUrlBuilder.buildSubscribeProductUrl(client, A_PRODUCT_ID)).thenReturn(A_GET_PRODUCTS_URL);

        testedClass.subscribeProduct(client, A_PRODUCT_ID);

        verify(restTemplate, times(1)).put(A_GET_PRODUCTS_URL,Object.class);

    }

    @Test(expected = DataSourceBadResponseException.class)
    public void whenSubscribeFail_thenANDataSourceBadResponseExceptionMustBeThrown() {
        when(bankSystemUrlBuilder.buildSubscribeProductUrl(client,A_PRODUCT_ID)).thenReturn(A_GET_PRODUCTS_URL);
        doThrow(new HttpClientErrorException(HttpStatus.TOO_EARLY)).when(restTemplate).put(A_GET_PRODUCTS_URL, Object.class);

        testedClass.subscribeProduct(client, A_PRODUCT_ID);
    }

    @Test()
    public void givenAClientAndAProduct_whenUnsubscribeProduct_thenRestemplateWithPutMustBeCalled() {
        when(bankSystemUrlBuilder.buildUnsubscribeProductUrl(client, A_PRODUCT_ID)).thenReturn(A_GET_PRODUCTS_URL);

        testedClass.unSubscribeProduct(client, A_PRODUCT_ID);

        verify(restTemplate, times(1)).put(A_GET_PRODUCTS_URL,Object.class);

    }

    @Test(expected = DataSourceBadResponseException.class)
    public void whenUnsubscribeFail_thenANDataSourceBadResponseExceptionMustBeThrown() {
        when(bankSystemUrlBuilder.buildUnsubscribeProductUrl(client,A_PRODUCT_ID)).thenReturn(A_GET_PRODUCTS_URL);
        doThrow(new HttpClientErrorException(HttpStatus.TOO_EARLY)).when(restTemplate).put(A_GET_PRODUCTS_URL, Object.class);

        testedClass.unSubscribeProduct(client, A_PRODUCT_ID);
    }
}