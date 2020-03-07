package bank.api.v1.mapper;

import bank.api.v1.dto.ClientDto;
import bank.domain.factory.IdFactory;
import bank.domain.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ClientMapperTest {

    private static final LocalDate BIRTH_DATE = LocalDate.of(1990, 1 ,1);
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    private ClientDto aClientDto;

    private ClientMapper testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientMapper(new IdFactory());
    }

    @Before
    public void initializeClientDto() {
        aClientDto = new ClientDto();

        aClientDto.setBirthDate(BIRTH_DATE);
        aClientDto.setFirstName(FIRST_NAME);
        aClientDto.setLastName(LAST_NAME);
    }

    @Test
    public void givenAClientDtoWithABirthDate_whenToClient_thenBirthDateMustBeTheSame() {

        Client result = testedClass.toClient(aClientDto);

        assertEquals(BIRTH_DATE, result.getBirthDate());
    }

    @Test
    public void givenAClientDtoWithAFirstName_whenToClient_thenFirstNameMustBeTheSame() {

        Client result = testedClass.toClient(aClientDto);

        assertEquals(FIRST_NAME, result.getFirstName());
    }

    @Test
    public void givenAClientDtoWithALastName_whenToClient_thenLastNameMustBeTheSame() {

        Client result = testedClass.toClient(aClientDto);

        assertEquals(LAST_NAME, result.getLastName());
    }

    @Test
    public void givenAClientDto_whenToClient_thenClientShouldHaveAnId() {

        Client result = testedClass.toClient(aClientDto);

        assertNotNull(result.getClientId().getId());
    }
}