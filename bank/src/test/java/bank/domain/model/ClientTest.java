package bank.domain.model;

import bank.domain.exception.MissingParameterException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    private static final String A_NAME = "aName";

    private Client clientUnderTest;

    @Test
    public void givenAName_whenConstructAClient_thenNameMustBeAssigned() {

        clientUnderTest = new Client(A_NAME);

        assertEquals(A_NAME, clientUnderTest.getName());
    }

    @Test(expected = MissingParameterException.class)
    public void givenAEmptyName_whenConstructAClient_thenMissingParameterMustBeThrown() {
        new Client(StringUtils.EMPTY);
    }

    @Test(expected = MissingParameterException.class)
    public void givenANull_whenConstructAClient_thenMissingParameterMustBeThrown() {
        new Client(null);
    }
}