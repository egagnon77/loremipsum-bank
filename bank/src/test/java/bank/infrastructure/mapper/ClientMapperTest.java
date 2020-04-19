package bank.infrastructure.mapper;

import bank.domain.model.Client;
import bank.domain.model.ProductLevel;
import bank.infrastructure.entity.ClientDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ClientMapperTest {

    private static final String A_NAME = "aName";
    private static final Integer A_PRODUCT_LEVEL = ProductLevel.VIP.getValue();

    private Client client;
    private ClientDto clientDto;

    private ClientMapper testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientMapper(new ProductMapper());
    }

    @Before
    public void initializeClientDto() {
        clientDto = new ClientDto();
        clientDto.setId(A_NAME);
        clientDto.setProductLevel(A_PRODUCT_LEVEL);
    }

    @Before
    public void initializeClient() {
        client = new Client(A_NAME);
        client.setProductLevel(A_PRODUCT_LEVEL);
    }

    @Test
    public void givenAnOptionalClientDto_whenToClient_thenClientNameMustBeCorrectlyAssigned() {

        Optional<Client> client = testedClass.toClient(Optional.of(clientDto));

        assertEquals(A_NAME, client.get().getName());
    }

    @Test
    public void givenAnOptionalClientDto_whenToClient_thenClientProductLevelMustBeCorrectlyAssigned() {

        Optional<Client> client = testedClass.toClient(Optional.of(clientDto));

        assertEquals(A_PRODUCT_LEVEL, client.get().getProductLevel());
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

        assertEquals(A_NAME, clientDto.getId());
    }

    @Test
    public void givenAClient_whenToDto_thenClientProductLevelMustBeCorrectlyAssigned() {

        ClientDto clientDto = testedClass.toDto(client);

        assertEquals(A_PRODUCT_LEVEL, clientDto.getProductLevel());
    }
}