package bank.domain.service;

import bank.domain.model.Client;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankServiceTest {

    private static final String A_CLIENT_ID = "aClientId";

    private BankService testedClass;

    @Before
    public void setup() {
        testedClass = new BankService();
    }

    @Test
    public void givenAClientId_whenGetClient_thenReturnedClientObjectMustHaveClientIdPropertyEqualsToClientIdReceived() {

        Client result = testedClass.getClient(A_CLIENT_ID);

        assertEquals(A_CLIENT_ID, result.getClientId());
    }

}
