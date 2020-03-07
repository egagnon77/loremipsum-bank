package bank.api.v1.controller;

import bank.api.v1.dto.ClientDto;
import bank.api.v1.mapper.ClientMapper;
import bank.domain.model.Client;
import bank.domain.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

    private static final String A_CLIENT_ID = "aClientId";

    @Mock
    private ClientMapper clientMapper;
    @Mock
    private ClientService clientService;

    private ClientController testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientController(clientMapper, clientService);
    }

    @Test
    public void givenAClientId_whenGet_thenResponseBodyShouldBeAClient() {

        Client client = new Client();
        when(clientService.get(A_CLIENT_ID)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.get(A_CLIENT_ID);

        assertEquals(client, result.getBody());
    }

    @Test
    public void givenAClientId_whenGet_thenResponseHttpStatusMustBeOk() {

        Client client = new Client();
        when(clientService.get(A_CLIENT_ID)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.get(A_CLIENT_ID);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAClientDto_whenSave_thenResponseBodyShouldBeAClient() {

        ClientDto clientDto = new ClientDto();
        Client client = new Client();
        when(clientMapper.toClient(clientDto)).thenReturn(client);
        when(clientService.save(client)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.save(clientDto);

        assertEquals(client, result.getBody());
    }

    @Test
    public void givenAClientDto_whenSave_thenResponseHttpStatusMustBeOk() {

        ClientDto clientDto = new ClientDto();
        Client client = new Client();
        when(clientMapper.toClient(clientDto)).thenReturn(client);
        when(clientService.save(client)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.save(clientDto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}