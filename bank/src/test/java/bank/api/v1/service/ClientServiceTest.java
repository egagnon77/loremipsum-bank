package bank.api.v1.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bank.api.v1.dto.CreateClient;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.InvalidArgumentException;
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
import javax.swing.text.html.Option;
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
    private static final Integer PRODUCT_LEVEL_VIP = 1;
    private static final Integer PRODUCT_TYPE_AUTOMATIC = 0;
    private static final Integer PRODUCT_TYPE_MANUAL = 1;

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
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.WAITING_FOR_SUBSCRIPTION);

        testedClass.acceptManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
    }

    @Test
    public void ifAnApprobationStatusHasAWaitingForDeletion_whenAcceptManualProduct_thenThisClientProductIsDeleted() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.WAITING_FOR_DELETION);

        testedClass.acceptManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).deleteById(client.get(), product.get());
    }

    @Test(expected = NotFoundException.class)
    public void givenAClientThatDoNotExistInClientRepository_whenSubscribeProduct_thenANotFoundExceptionIsThrown() {

        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());
        Optional<Product> product = Optional.of(new Product());
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);

        testedClass.subscribeProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void givenAProductThatDoNotExistInProductRepository_whenSubcribeProduct_thenANotFoundExceptionIsThrown() {

        Optional<Client> client = Optional.of(new Client());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(Optional.empty());

        testedClass.subscribeProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test(expected = AlreadyExistsException.class)
    public void ifAProductIsAlreadySubscribe_whenSubscribeProduct_thenAnAlreadyExceptionShouldBeThrown() {

        Optional<Client> client = Optional.of(new Client("name"));
        Optional<Product> product = Optional.of(new Product(8, "Product", 0, PRODUCT_LEVEL_NORMAL));

        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.subscribeProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test(expected = InvalidArgumentException.class)
    public void ifAProductIsNotAccessible_whenSubscribeProduct_InvalidArgumentExceptionShouldBeThrown() {

        Optional<Client> client = Optional.of(new Client("name"));
        Optional<Product> product = Optional.of(new Product(8, "Product", 0, PRODUCT_LEVEL_VIP));

        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.subscribeProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test()
    public void ifAProductIsAutomatic_whenSubscribeProduct_ThenApprobationStatusShouldBeSubscribed() {

        Optional<Client> client = Optional.of(new Client("name"));
        Optional<Product> product = Optional
            .of(new Product(8, "Product", PRODUCT_TYPE_AUTOMATIC, PRODUCT_LEVEL_NORMAL));

        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(null);

        testedClass.subscribeProduct(A_NAME, A_PRODUCT_ID);
        verify(clientProductRepository, times(1)).save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
    }

    @Test()
    public void ifAProductIsManual_whenSubscribeProduct_ThenApprobationStatusShouldBeWaitingForSubscription() {

        Optional<Client> client = Optional.of(new Client("name"));
        Optional<Product> product = Optional.of(new Product(8, "Product", PRODUCT_TYPE_MANUAL, PRODUCT_LEVEL_NORMAL));

        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(null);

        testedClass.subscribeProduct(A_NAME, A_PRODUCT_ID);
        verify(clientProductRepository, times(1))
            .save(client.get(), product.get(), ApprobationStatus.WAITING_FOR_SUBSCRIPTION);
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
    public void ifAnApprobationStatusHasAWaitingForDeletion_whenRejectManualProduct_thenThisClientProductIsSetBackToSubscribe() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.WAITING_FOR_DELETION);

        testedClass.rejectManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).save(client.get(), product.get(), ApprobationStatus.SUBSCRIBED);
    }

    @Test
    public void ifAnApprobationStatusHasAWaitingForSubscription_whenRejectManualProduct_thenThisClientProductIsDeleted() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product());
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.WAITING_FOR_SUBSCRIPTION);

        testedClass.rejectManualProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).deleteById(client.get(), product.get());
    }


    @Test
    public void ifNoClientInDatabase_thenAnEmptyListIsReturned_whenFindingClientsWaitingProductApprobation() {

        List<Client> emptyList = new ArrayList<>();
        when(clientRepository.findAll()).thenReturn(emptyList);

        List<Client> result = testedClass.findClientsWaitingProductApprobation();

        assertEquals(emptyList, result);
    }

    @Test
    public void ifNoProductWithWaitingApprobationInDatabase_thenAnEmptyListIsReturned_whenFindingClientsWaitingProductApprobation() {

        Client client = new Client();
        List<Client> clients = new ArrayList<>();
        Product product = new Product();
        client.setProducts(new ArrayList<>());
        client.getProducts().add(product);
        clients.add(client);
        when(clientRepository.findAll()).thenReturn(clients);
        when(clientProductRepository.findById(client, product)).thenReturn(ApprobationStatus.SUBSCRIBED);

        List<Client> result = testedClass.findClientsWaitingProductApprobation();

        assertEquals(0, result.size());
    }

    @Test
    public void ifAProductWithWaitingApprobationInDatabase_thenAListOfClientIsReturned_whenFindingClientsWaitingProductApprobation() {

        Client client = new Client();
        List<Client> clients = new ArrayList<>();
        Product product = new Product();
        client.setProducts(new ArrayList<>());
        client.getProducts().add(product);
        clients.add(client);
        when(clientRepository.findAll()).thenReturn(clients);
        when(clientProductRepository.findById(client, product)).thenReturn(ApprobationStatus.WAITING_FOR_SUBSCRIPTION);

        List<Client> result = testedClass.findClientsWaitingProductApprobation();

        assertEquals(clients, result);
    }

    @Test
    public void ifAClientHasMoreThanOneProductWithWaitingApprobationInDatabase_thenListOfClientReturnedMustHaveThisClientOnlyOnce_whenFindingClientsWaitingProductApprobation() {

        Client client = new Client();
        List<Client> clients = new ArrayList<>();
        Product product = new Product();
        Product product2 = new Product();
        client.setProducts(new ArrayList<>());
        client.getProducts().add(product);
        client.getProducts().add(product2);
        clients.add(client);
        when(clientRepository.findAll()).thenReturn(clients);
        when(clientProductRepository.findById(client, product)).thenReturn(ApprobationStatus.WAITING_FOR_SUBSCRIPTION);
        when(clientProductRepository.findById(client, product2)).thenReturn(ApprobationStatus.WAITING_FOR_DELETION);

        List<Client> result = testedClass.findClientsWaitingProductApprobation();

        assertEquals(clients, result);
    }

    @Test
    public void ifAClientAndProductWithApprobationStatusOfWaitingForSubscriptionExistWhenUnsubscribeThenProductIsDeleted() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product(A_PRODUCT_ID,A_NAME,PRODUCT_TYPE_MANUAL,PRODUCT_LEVEL_NORMAL));
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.WAITING_FOR_SUBSCRIPTION);

        testedClass.unSubscribeProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).deleteById(client.get(), product.get());
    }

    @Test
    public void ifAClientAndProductWithApprobationStatusSubscribedExistWhenUnsubscribeTheProductIsDeleted() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product(A_PRODUCT_ID,A_NAME,PRODUCT_TYPE_AUTOMATIC,PRODUCT_LEVEL_NORMAL));
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.unSubscribeProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).deleteById(client.get(), product.get());
    }

    @Test
    public void ifAClientAndProductManualWithApprobationStatusSubscribedExistWhenUnsubscribeTheProductIsWaitingForDeletion() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product(A_PRODUCT_ID,A_NAME,PRODUCT_TYPE_MANUAL,PRODUCT_LEVEL_NORMAL));
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.unSubscribeProduct(A_NAME, A_PRODUCT_ID);

        verify(clientProductRepository, times(1)).save(client.get(), product.get(),ApprobationStatus.WAITING_FOR_DELETION);
    }

    @Test(expected = NotFoundException.class)
    public void ifClientNotExistWhenUnsubscribeThenExceptionIsThrown() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product(A_PRODUCT_ID,A_NAME,PRODUCT_TYPE_MANUAL,PRODUCT_LEVEL_NORMAL));
        when(clientRepository.findById(A_NAME)).thenReturn(Optional.empty());
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.unSubscribeProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test(expected = NotFoundException.class)
    public void ifProductNotExistWhenUnsubscribeThenExceptionIsThrown() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product(A_PRODUCT_ID,A_NAME,PRODUCT_TYPE_MANUAL,PRODUCT_LEVEL_NORMAL));
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(Optional.empty());
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.SUBSCRIBED);

        testedClass.unSubscribeProduct(A_NAME, A_PRODUCT_ID);
    }


    @Test(expected = NotFoundException.class)
    public void ifProductClientIsNotSubscribeWhenUnsubscribeThenExceptionIsThrown() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product(A_PRODUCT_ID,A_NAME,PRODUCT_TYPE_MANUAL,PRODUCT_LEVEL_NORMAL));
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(null);

        testedClass.unSubscribeProduct(A_NAME, A_PRODUCT_ID);
    }

    @Test(expected = AlreadyExistsException.class)
    public void ifProductClientIsWaitingForUnSubscribingWhenUnsubscribeThenExceptionIsThrown() {

        Optional<Client> client = Optional.of(new Client());
        Optional<Product> product = Optional.of(new Product(A_PRODUCT_ID,A_NAME,PRODUCT_TYPE_MANUAL,PRODUCT_LEVEL_NORMAL));
        when(clientRepository.findById(A_NAME)).thenReturn(client);
        when(productRepository.findById(A_PRODUCT_ID)).thenReturn(product);
        when(clientProductRepository.findById(client.get(), product.get()))
            .thenReturn(ApprobationStatus.WAITING_FOR_DELETION);

        testedClass.unSubscribeProduct(A_NAME, A_PRODUCT_ID);
    }
}
