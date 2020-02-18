package bank.api.v1.controller;

import bank.domain.model.Client;
import bank.domain.service.BankService;
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
public class BankControllerTest {

    private static final String A_CLIENT_ID = "aClientId";

    @Mock
    private BankService bankService;

    private BankController testedClass;

    @Before
    public void setup() {
        testedClass = new BankController(bankService);
    }

    @Test
    public void givenAClientId_whenGetClient_thenResponseBodyMustContainTheClientReturnedByTheBankService() {

        Client client = new Client(A_CLIENT_ID);
        when(bankService.getClient(A_CLIENT_ID)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.getClient(A_CLIENT_ID);

        assertEquals(client, result.getBody());
    }

    @Test
    public void givenAClientId_whenGetClient_thenResponseHttpStatusMustBeOk() {

        Client client = new Client(A_CLIENT_ID);
        when(bankService.getClient(A_CLIENT_ID)).thenReturn(client);

        ResponseEntity<Client> result = testedClass.getClient(A_CLIENT_ID);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}