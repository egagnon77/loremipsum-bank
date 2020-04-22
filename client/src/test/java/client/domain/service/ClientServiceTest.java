package client.domain.service;

import client.domain.client.BankClient;
import client.domain.model.Client;
import client.domain.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private BankClient bankClient;

    private ClientService testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientService(bankClient);
    }

    @Test
    public void givenAClient_whenGetProducts_thenAListOfProductsMustBeReturned() {

        List<Product> products = new ArrayList<>();
        Client client = new Client();
        when(bankClient.getProducts(client)).thenReturn(products);

        List<Product> result = testedClass.getProducts(client);

        assertEquals(products, result);
    }

    @Test
    public void givenAClient_whenGetAvailableProducts_thenAListOfProductsMustBeReturned() {

        List<Product> products = new ArrayList<>();
        Client client = new Client();
        when(bankClient.getProducts(client)).thenReturn(products);

        List<Product> result = testedClass.getAvailableProducts(client);

        assertEquals(products, result);
    }

    @Test
    public void givenAProductIdAndAClientName_whenSubscribeProduct_thenWeShouldCallBankEmployee() {

        Integer aProductId = 88;
        String aClientName = "aClientName";
        Client client = new Client();
        testedClass.subscribeProduct(client,aProductId);

        verify(bankClient, times(1)).subscribeProduct(client, aProductId);
    }
}