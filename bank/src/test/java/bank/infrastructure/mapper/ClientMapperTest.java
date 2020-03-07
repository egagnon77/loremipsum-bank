package bank.infrastructure.mapper;

import bank.domain.model.Client;
import bank.domain.model.ClientId;
import bank.infrastructure.entity.ClientDto;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class ClientMapperTest {

    private static final String AN_ID = "anId";
    private static final String A_FIRST_NAME = "aFirstName";
    private static final String A_LAST_NAME = "aLastName";
    private static final LocalDate A_BIRTH_DATE = LocalDate.of(1940, 1 ,1);

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
        clientDto.setId(AN_ID);
        clientDto.setFirstName(A_FIRST_NAME);
        clientDto.setLastName(A_LAST_NAME);
        clientDto.setBirthDate(A_BIRTH_DATE);
    }

    @Before
    public void initializeClient() {
        client = new Client();
        client.setClientId(new ClientId());
        client.getClientId().setUuid(AN_ID);
        client.setFirstName(A_FIRST_NAME);
        client.setLastName(A_LAST_NAME);
        client.setBirthDate(A_BIRTH_DATE);
    }

    @Test
    public void givenAnOptionalClientDto_whenToClient_thenClientIdMustBeCorrectlyAssigned() {

        Optional<Client> client = testedClass.toClient(Optional.of(clientDto));

        assertEquals(AN_ID, client.get().getClientId().getId());
    }

    @Test
    public void givenAnOptionalClientDto_whenToClient_thenClientFirstNameMustBeCorrectlyAssigned() {

        Optional<Client> client = testedClass.toClient(Optional.of(clientDto));

        assertEquals(A_FIRST_NAME, client.get().getFirstName());
    }

    @Test
    public void givenAnOptionalClientDto_whenToClient_thenClientLastNameMustBeCorrectlyAssigned() {

        Optional<Client> client = testedClass.toClient(Optional.of(clientDto));

        assertEquals(A_LAST_NAME, client.get().getLastName());
    }

    @Test
    public void givenAnOptionalClientDto_whenToClient_thenClientBirthDateMustBeCorrectlyAssigned() {

        Optional<Client> client = testedClass.toClient(Optional.of(clientDto));

        assertEquals(A_BIRTH_DATE, client.get().getBirthDate());
    }

    @Test
    public void givenAnOptionalEmpty_whenToClient_thenClientMustNotBePresent() {

        Optional<Client> client = testedClass.toClient(Optional.empty());

        assertFalse(client.isPresent());
    }

    @Test
    public void givenAClientDto_whenToClient_thenClientIdMustBeCorrectlyAssigned() {

        Client client = testedClass.toClient(clientDto);

        assertEquals(AN_ID, client.getClientId().getId());
    }

    @Test
    public void givenAClientDto_whenToClient_thenClientFirstNameMustBeCorrectlyAssigned() {

        Client client = testedClass.toClient(clientDto);

        assertEquals(A_FIRST_NAME, client.getFirstName());
    }

    @Test
    public void givenAClientDto_whenToClient_thenClientLastNameMustBeCorrectlyAssigned() {

        Client client = testedClass.toClient(clientDto);

        assertEquals(A_LAST_NAME, client.getLastName());
    }

    @Test
    public void givenAClientDto_whenToClient_thenClientBirthDateMustBeCorrectlyAssigned() {

        Client client = testedClass.toClient(clientDto);

        assertEquals(A_BIRTH_DATE, client.getBirthDate());
    }

    @Test
    public void givenAClient_whenToDto_thenClientIdMustBeCorrectlyAssigned() {

        ClientDto clientDto = testedClass.toDto(client);

        assertEquals(AN_ID, clientDto.getId());
    }

    @Test
    public void givenAClient_whenToDto_thenClientFirstNameMustBeCorrectlyAssigned() {

        ClientDto clientDto = testedClass.toDto(client);

        assertEquals(A_FIRST_NAME, clientDto.getFirstName());
    }

    @Test
    public void givenAClient_whenToDto_thenClientLastNameMustBeCorrectlyAssigned() {

        ClientDto clientDto = testedClass.toDto(client);

        assertEquals(A_LAST_NAME, clientDto.getLastName());
    }

    @Test
    public void givenAClient_whenToDto_thenClientBirthDateMustBeCorrectlyAssigned() {

        ClientDto clientDto = testedClass.toDto(client);

        assertEquals(A_BIRTH_DATE, clientDto.getBirthDate());
    }
}