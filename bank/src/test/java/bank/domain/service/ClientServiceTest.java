package bank.domain.service;

import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.Client;
import bank.domain.model.ClientId;
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

    private static final String AN_ID = "anId";

    @Mock
    private ClientRepository clientRepository;

    private ClientService testedClass;

    @Before
    public void setup() {
        testedClass = new ClientService(clientRepository);
    }

    @Test
    public void givenAnId_whenGet_thenAClientMustBeReturned() {

        Client client = new Client();
        when(clientRepository.findById(AN_ID)).thenReturn(Optional.of(client));

        Client result = testedClass.get(AN_ID);

        assertNotNull(result);
    }

    @Test(expected = NotFoundException.class)
    public void givenAnInvalidId_whenGet_thenANotFoundExceptionMustBeThrown() {

        when(clientRepository.findById(AN_ID)).thenReturn(Optional.empty());

        testedClass.get(AN_ID);
    }

    @Test
    public void givenAClient_whenSave_thenAClientMustBeReturned() {

        Client client = new Client();
        client.setClientId(new ClientId());
        client.getClientId().setUuid("uuid");
        when(clientRepository.findById(client.getClientId().getId())).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);

        Client result = testedClass.save(client);

        assertNotNull(result);
    }

    @Test(expected = AlreadyExistsException.class)
    public void givenAnAlreadyExistingClient_whenSave_thenAnAlreadyExistsExceptionMustBeThrown() {

        Client client = new Client();
        client.setClientId(new ClientId());
        client.getClientId().setUuid("uuid");
        when(clientRepository.findById(client.getClientId().getId())).thenReturn(Optional.of(client));

        testedClass.save(client);
    }

}
