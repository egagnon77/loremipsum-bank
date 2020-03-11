package bank.infrastructure.mapper;

import bank.domain.model.Client;
import bank.infrastructure.entity.ClientDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ClientMapperTest {

    private static final String A_NAME = "aName";

    private Client client;
    private ClientDto clientDto;

    private ClientMapper testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientMapper();
    }

    @Before
    public void initializeClientDto() {
        clientDto = new ClientDto();
        clientDto.setName(A_NAME);
    }

    @Before
    public void initializeClient() {
        client = new Client(A_NAME);
    }

    @Test
    public void givenAnOptionalClientDto_whenToClient_thenClientNameMustBeCorrectlyAssigned() {

        Optional<Client> client = testedClass.toClient(Optional.of(clientDto));

        assertEquals(A_NAME, client.get().getName());
    }

    @Test
    public void givenAnOptionalEmpty_whenToClient_thenClientMustNotBePresent() {

        Optional<Client> client = testedClass.toClient(Optional.empty());

        assertFalse(client.isPresent());
    }

    @Test
    public void givenAClientDto_whenToClient_thenClientNameMustBeCorrectlyAssigned() {

        Client client = testedClass.toClient(clientDto);

        assertEquals(A_NAME, client.getName());
    }

    @Test
    public void givenAClient_whenToDto_thenClientNameMustBeCorrectlyAssigned() {

        ClientDto clientDto = testedClass.toDto(client);

        assertEquals(A_NAME, clientDto.getName());
    }
}