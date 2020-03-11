package bank.infrastructure.repository;

import bank.domain.model.Client;
import bank.infrastructure.entity.ClientDto;
import bank.infrastructure.mapper.ClientMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpringDataClientRepositoryTest {

    private static final String A_VALID_NAME = "aValidName";
    private static final String AN_INVALID_NAME = "anInvalidName";

    @Mock
    private ClientMapper clientMapper;
    @Mock
    private CrudClientRepository crudClientRepository;

    private SpringDataClientRepository testedClass;

    @Before
    public void setUp() {
        testedClass = new SpringDataClientRepository(crudClientRepository, clientMapper);
    }

    @Test
    public void givenAValidId_whenFindById_thenAClientMustBeReturned() {
        ClientDto clientDto = new ClientDto();
        Optional<ClientDto> optionalClientDto = Optional.of(clientDto);
        when(crudClientRepository.findById(A_VALID_NAME)).thenReturn(optionalClientDto);
        Client client = new Client(A_VALID_NAME);
        Optional<Client> optionalClient = Optional.of(client);
        when(clientMapper.toClient(optionalClientDto)).thenReturn(optionalClient);

        Optional<Client> result = testedClass.findById(A_VALID_NAME);

        assertEquals(client, result.get());
    }

    @Test
    public void givenAnInvalidId_whenFindById_thenAClientMustNotBeReturned() {
        when(crudClientRepository.findById(AN_INVALID_NAME)).thenReturn(Optional.empty());
        Optional<Client> optionalClient = Optional.empty();
        when(clientMapper.toClient(Optional.empty())).thenReturn(optionalClient);

        Optional<Client> result = testedClass.findById(AN_INVALID_NAME);

        assertFalse(result.isPresent());
    }

    @Test
    public void givenAClient_whenSave_ThenSavedClientMustBeReturned() {

        Client client = new Client(A_VALID_NAME);
        ClientDto clientDto = new ClientDto();
        when(clientMapper.toDto(client)).thenReturn(clientDto);
        when(crudClientRepository.save(clientDto)).thenReturn(clientDto);
        when(clientMapper.toClient(clientDto)).thenReturn(client);

        testedClass.save(client);

        assertEquals(client, client);
    }
}