package bank.api.v1.service;

import bank.api.v1.dto.CreateClient;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    private static final String A_NAME = "aName";

    @Mock
    private ClientRepository clientRepository;

    private ClientService testedClass;

    @Before
    public void setup() {
        testedClass = new ClientService(clientRepository);
    }

    @Test
    public void givenAnId_whenGet_thenAClientMustBeReturned() {

        Client client = new Client(A_NAME);
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.of(client));

        Client result = testedClass.get(A_NAME);

        assertNotNull(result);
    }

    @Test(expected = NotFoundException.class)
    public void givenAnInvalidId_whenGet_thenANotFoundExceptionMustBeThrown() {

        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());

        testedClass.get(A_NAME);
    }

    @Test
    public void givenACreateClient_whenSave_thenACreateClientMustBeReturned() {

        CreateClient createClient = new CreateClient(A_NAME);
        Client client = new Client(createClient.getName());
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);

        CreateClient result = testedClass.save(createClient);

        assertNotNull(result);
    }

    @Test(expected = AlreadyExistsException.class)
    public void givenAnAlreadyExistingClient_whenSave_thenAnAlreadyExistsExceptionMustBeThrown() {

        CreateClient createClient = new CreateClient(A_NAME);
        Client client = new Client(createClient.getName());
        when(clientRepository.findById(client.getName())).thenReturn(Optional.of(client));

        testedClass.save(createClient);
    }

    @Test
    public void givenAnExistingClientWithoutAnyProduct_whenGetProducts_thenReturnAnEmptyList() {
        // Given
        Client client = new Client(A_NAME);
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.of(client));

        // When
        List<Product> result = testedClass.getProducts(A_NAME);

        // Then
        List<Product> expected = new ArrayList<Product>();
        assertArrayEquals(result.toArray(), expected.toArray());
    }

    @Test
    public void givenAnExistingClientWithProducts_whenGetProducts_thenReturnList() {
        // Given
        Client client = new Client(A_NAME);
        Product[] products = { new Product(1, "my product", 1,0) };
        client.setProducts(Arrays.asList(products));
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.of(client));

        // When
        List<Product> result = testedClass.getProducts(A_NAME);

        // Then
        assertArrayEquals(result.toArray(), products);
    }

    @Test(expected = NotFoundException.class)
    public void givenAnInexistingClient_whenGetProducts_thenANotFoundExceptionMustBeThrown() {
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());
        testedClass.getProducts(A_NAME);
    }
}
