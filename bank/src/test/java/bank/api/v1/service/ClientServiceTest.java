package bank.api.v1.service;

import bank.api.v1.dto.CreateClient;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.Client;
import bank.domain.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

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

}
