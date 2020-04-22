package bank.api.v1.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import bank.api.v1.dto.CreateClient;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.ApprobationStatus;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.repository.ClientProductRepository;
import bank.domain.repository.ClientRepository;
import bank.domain.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    private static final String A_NAME = "aName";
    private static final Integer A_PRODUCT_ID = 88;
    private static final Integer PRODUCT_LEVEL_NORMAL = 0;

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ClientProductRepository clientProductRepository;

    private ClientService testedClass;

    @Before
    public void setup() {
        testedClass = new ClientService(clientRepository, productRepository, clientProductRepository);
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
        Product[] products = {new Product(1, "my product", 1, 0)};
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

    @Test(expected = NotFoundException.class)
    public void givenAnInexistingClient_whenUpgradeStatus_thenANotFoundExceptionIsThrown() {

        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());

        testedClass.upgradeStatus(A_NAME);


    }

    @Test
    public void givenAClient_whenUpgradeStatus_thenAClientIsReturned() {

        Client client = new Client(A_NAME);
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = testedClass.upgradeStatus(A_NAME);

        assertEquals(client, result);
    }

    @Test(expected = NotFoundException.class)
    public void givenAnInexistingClient_whenDowngradeStatus_thenANotFoundExceptionIsThrown() {

        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());

        testedClass.downgradeStatus(A_NAME);


    }

    @Test
    public void givenAClient_whenDowngradeStatus_thenAClientIsReturned() {

        Client client = new Client(A_NAME);
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = testedClass.downgradeStatus(A_NAME);

        assertEquals(client, result);
    }


    @Test
    public void givenAnExistingClientWithApprobationLevel_whenAvailable_thenReturnList() {
        Client client = new Client(A_NAME);
        client.setProductLevel(PRODUCT_LEVEL_NORMAL);
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.of(client));
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "my product", 1, 0));
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = testedClass.getAvailableProducts(A_NAME);
        assertArrayEquals(result.toArray(), products.toArray());
    }

    @Test(expected = NotFoundException.class)
    public void givenAClientThatDoNotExistInClientRepository_whenAcceptManualProduct_thenANotFoundExceptionIsThrown() {

        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());
        Optional<Product> product = Optional.of(new Product());
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);

        testedClass.acceptManualProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void givenAProductThatDoNotExistInProductRepository_whenAcceptManualProduct_thenANotFoundExceptionIsThrown() {

        Optional<Client> client = Optional.of(new Client());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(Optional.empty());

        testedClass.acceptManualProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test
    public void ifAnApprobationStatusIsNotWaitingForSubscription_whenAcceptManualProduct_thenNothingIsSavedToClientProductRepository() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get())).thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.acceptManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(0)).save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
    }

    @Test
    public void ifAnApprobationStatusHasAWaitingForSubscription_whenAcceptManualProduct_thenThisClientProductIsSavedToSubscribedInClientProductRepository() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get())).thenReturn(ApprobationStatus.WAITING_FOR_SUBCRIPTION);

        testedClass.acceptManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
    }

    @Test(expected = NotFoundException.class)
    public void givenAClientThatDoNotExistInClientRepository_whenRejectManualProduct_thenANotFoundExceptionIsThrown() {

        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());
        Optional<Product> product = Optional.of(new Product());
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);

        testedClass.rejectManualProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void givenAProductThatDoNotExistInProductRepository_whenRejectManualProduct_thenANotFoundExceptionIsThrown() {

        Optional<Client> client = Optional.of(new Client());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(Optional.empty());

        testedClass.rejectManualProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test
    public void ifAnApprobationStatusIsNotWaitingForDeletion_whenRejectManualProduct_thenNothingIsDeleteInClientProductRepository() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get())).thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.rejectManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(0)).save(client.get(), product.get(), ApprobationStatus.NOT_SET);
    }

    @Test
    public void ifAnApprobationStatusHasAWaitingForDeletion_whenRejectManualProduct_thenThisClientProductIsDeleteInClientProductRepository() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get())).thenReturn(ApprobationStatus.WAITING_FOR_DELETION);

        testedClass.rejectManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).save(client.get(), product.get(), ApprobationStatus.NOT_SET);
    }
}
