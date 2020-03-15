package client.domain.factory;

import client.domain.model.Client;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientFactoryTest {

    private static final String A_NAME = "aName";

    private ClientFactory testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientFactory();
    }

    @Test
    public void givenAName_whenCreate_thenClientNameMustBeAssigned() {

        Client result = testedClass.create(A_NAME);

        assertEquals(A_NAME, result.getName());
    }

    @Test
    public void givenAName_whenCreate_thenClientProductsMustBeEmpty() {

        Client result = testedClass.create(A_NAME);

        assertEquals(0, result.getProducts().size());
    }
}