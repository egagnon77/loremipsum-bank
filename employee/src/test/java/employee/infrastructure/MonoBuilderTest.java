package employee.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import employee.domain.model.AddClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
public class MonoBuilderTest {

    private static final String A_NAME = "aName";
    private static final String AN_ADD_CLIENT_URL = "anAddClientUrl";

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
    private WebClient.ResponseSpec response;

    private AddClient addClient;
    private MonoBuilder testedClass;

    @Before
    public void setUp() {
        testedClass = new MonoBuilder(bankSystemUrlBuilder, webClient);
    }

    @Before
    public void initClient() {
        addClient = new AddClient();
        addClient.setName(A_NAME);
    }

    @Test
    public void givenAnAddClientObject_whenAddClient_thenAFluxAddClientMustBeReturned() {

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
}
