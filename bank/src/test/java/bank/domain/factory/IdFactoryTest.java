package bank.domain.factory;

import bank.domain.model.ClientId;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdFactoryTest {

    private IdFactory testedClass;

    @Before
    public void setUp() {
        testedClass = new IdFactory();
    }

    @Test
    public void whenGenerateClientId_thenIdPropertyOfClientIdMustContainsANonEmptyString() {

        ClientId result = testedClass.generateClientId();

        assertNotEquals("", result.getId());
    }
}