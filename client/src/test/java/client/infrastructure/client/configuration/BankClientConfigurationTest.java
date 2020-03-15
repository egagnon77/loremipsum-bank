package client.infrastructure.client.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class BankClientConfigurationTest {

    private BankClientConfiguration testedClass;

    @Before
    public void setUp() {
        testedClass = new BankClientConfiguration();
    }

    @Test
    public void whenGettingRestTemplate_thenARestTemplateMustBeReturned() {

        RestTemplate result = testedClass.restTemplate();

        assertNotNull(result);
    }

    @Test
    public void whenGettingObjectMapper_thenAObjectMapperMustBeReturned() {

        ObjectMapper result = testedClass.objectMapper();

        assertNotNull(result);
    }
}