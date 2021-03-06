package employee.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
public class ResponseBuilderTest {

    private static final String A_NAME = "aName";
    private static final Integer AN_ID = 88;
    private static final String AN_ADD_CLIENT_URL = "anAddClientUrl";
    private static final String A_LIST_PRODUCT_URL = "aListProductUrl";
    private static final String A_UPGRADE_CLIENT_URL = "aUpgradeClientUrl";
    private static final String A_DOWNGRADE_CLIENT_URL = "aDowngradeClientUrl";
    private static final String AN_ACCEPT_PRODUCT_URL = "anAcceptProductUrl";
    private static final String A_REJECT_PRODUCT_URL = "aRejectProductUrl";
    private static final String A_TASK_URL = "aTaskUrl";

    @Mock
    private BankSystemUrlBuilder bankSystemUrlBuilder;
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersSpec requestHeaders;
    @Mock
    private WebClient.RequestBodySpec requestBody;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUri;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec response;
    @Mock
    private Mono<ResponseEntity<Void>> voidResponseEntity;

    private AddClient addClient;
    private Client client;
    private ResponseBuilder testedClass;

    @Before
    public void setUp() {
        testedClass = new ResponseBuilder(bankSystemUrlBuilder, webClient);
    }

    @Before
    public void initClients() {
        addClient = new AddClient();
        addClient.setName(A_NAME);
        client = new Client();
        client.setName(A_NAME);
    }

    @Before
    public void initClient() {
        ;
    }

    @Test
    public void givenAnAddClientObject_whenAddClient_thenAMonoAddClientMustBeReturned() {

        Mono<AddClient> expected = Mockito.mock(Mono.class);
        when(bankSystemUrlBuilder.buildAddClientUrl()).thenReturn(AN_ADD_CLIENT_URL);
        when(webClient.post()).thenReturn(requestBodyUri);
        when(requestBodyUri.uri(AN_ADD_CLIENT_URL)).thenReturn(requestBody);
        when(requestBody.contentType(MediaType.APPLICATION_JSON)).thenReturn(requestBody);
        when(requestBody.bodyValue(addClient)).thenReturn(requestHeaders);
        when(requestHeaders.retrieve()).thenReturn(response);
        when(response.bodyToMono(AddClient.class)).thenReturn(expected);

        Mono<AddClient> result = testedClass.addClient(addClient);

        assertEquals(expected, result);
    }

    @Test
    public void givenClientObject_whenGetProduct_thenAFluxProductMustBeReturned() {
        Flux<Product> expected = Mockito.mock(Flux.class);
        when(bankSystemUrlBuilder.buildGetProductsUrl(client)).thenReturn(A_LIST_PRODUCT_URL);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(A_LIST_PRODUCT_URL)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(response);
        when(response.bodyToFlux(Product.class)).thenReturn(expected);

        Flux<Product> result = testedClass.listProducts(client);

        assertEquals(expected, result);

    }

    @Test
    public void givenClientObject_whenUpgradeClient_thenAMonoClientIsReturned() {
        Mono<Client> expected = Mockito.mock(Mono.class);
        when(bankSystemUrlBuilder.buildUpgradeClientUrl(client)).thenReturn(A_UPGRADE_CLIENT_URL);
        when(webClient.patch()).thenReturn(requestBodyUri);
        when(requestBodyUri.uri(A_UPGRADE_CLIENT_URL)).thenReturn(requestBody);
        when(requestBody.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBody);
        when(requestBody.retrieve()).thenReturn(response);
        when(response.bodyToMono(Client.class)).thenReturn(expected);

        Mono<Client> result = testedClass.upgradeClient(client);

        assertEquals(expected, result);
    }

    @Test
    public void givenClientObject_whenDowngradeClient_thenAMonoClientIsReturned() {
        Mono<Client> expected = Mockito.mock(Mono.class);
        when(bankSystemUrlBuilder.buildDowngradeClientUrl(client)).thenReturn(A_DOWNGRADE_CLIENT_URL);
        when(webClient.patch()).thenReturn(requestBodyUri);
        when(requestBodyUri.uri(A_DOWNGRADE_CLIENT_URL)).thenReturn(requestBody);
        when(requestBody.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBody);
        when(requestBody.retrieve()).thenReturn(response);
        when(response.bodyToMono(Client.class)).thenReturn(expected);

        Mono<Client> result = testedClass.downgradeClient(client);

        assertEquals(expected, result);

    }

    @Test
    public void givenAnIdAndAName_whenAcceptProduct_thenWebClientIsInvoked() {

        when(bankSystemUrlBuilder.buildAcceptProductUrl(AN_ID, A_NAME)).thenReturn(AN_ACCEPT_PRODUCT_URL);
        when(webClient.patch()).thenReturn(requestBodyUri);
        when(requestBodyUri.uri(AN_ACCEPT_PRODUCT_URL)).thenReturn(requestBody);
        when(requestBody.retrieve()).thenReturn(response);
        when(response.toBodilessEntity()).thenReturn(voidResponseEntity);
        when(voidResponseEntity.block()).thenReturn(null);

        testedClass.acceptProduct(AN_ID, A_NAME);

        verify(webClient).patch();
        verify(requestBodyUri).uri(AN_ACCEPT_PRODUCT_URL);
        verify(requestBody).retrieve();
        verify(response).toBodilessEntity();
        verify(voidResponseEntity).block();
    }

    @Test
    public void givenAnIdAndAName_whenRejectProduct_thenWebClientIsInvoked() {

        when(bankSystemUrlBuilder.buildRejectProductUrl(AN_ID, A_NAME)).thenReturn(A_REJECT_PRODUCT_URL);
        when(webClient.patch()).thenReturn(requestBodyUri);
        when(requestBodyUri.uri(A_REJECT_PRODUCT_URL)).thenReturn(requestBody);
        when(requestBody.retrieve()).thenReturn(response);
        when(response.toBodilessEntity()).thenReturn(voidResponseEntity);
        when(voidResponseEntity.block()).thenReturn(null);

        testedClass.rejectProduct(AN_ID, A_NAME);

        verify(webClient).patch();
        verify(requestBodyUri).uri(A_REJECT_PRODUCT_URL);
        verify(requestBody).retrieve();
        verify(response).toBodilessEntity();
        verify(voidResponseEntity).block();
    }

    @Test
    public void whenTask_thenAMonoArrayOfClientIsReturned() {

        Client[] clients = new Client[]{};

        when(bankSystemUrlBuilder.buildGetTaskUrl()).thenReturn(A_TASK_URL);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(A_TASK_URL)).thenReturn(requestBody);
        when(requestBody.retrieve()).thenReturn(response);
        when(response.bodyToMono(Client[].class)).thenReturn(Mono.just(clients));

        Mono<Client[]> result = testedClass.task();

        assertEquals(clients, result.block());
    }
}
