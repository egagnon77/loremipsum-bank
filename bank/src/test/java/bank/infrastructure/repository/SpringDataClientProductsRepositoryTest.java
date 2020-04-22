package bank.infrastructure.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import bank.domain.model.ApprobationStatus;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.infrastructure.entity.ClientDto;
import bank.infrastructure.entity.ClientProductsDto;
import bank.infrastructure.entity.ClientProductsPrimaryKeys;
import bank.infrastructure.entity.ProductDto;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpringDataClientProductsRepositoryTest {

    @Mock
    private CrudClientProductsRepository crudClientProductsRepository;

    @Mock
    private CrudClientRepository crudClientRepository;

    @Mock
    private CrudProductRepository crudProductRepository;

    private Client client;
    private Product product;

    private SpringDataClientProductsRepository testedClass;

    @Before
    public void setUp() {
        client = new Client();
        product = new Product();
        testedClass = new SpringDataClientProductsRepository(crudClientProductsRepository, crudClientRepository,
            crudProductRepository);
    }

    @Test
    public void ifNoClientProductExist_whenFindById_thenNullIsReturned() {

        when(crudClientProductsRepository.findById(any(ClientProductsPrimaryKeys.class))).thenReturn(Optional.empty());

        ApprobationStatus result = testedClass.findById(client, product);

        assertNull(result);
    }

    @Test
    public void ifAClientProductExist_whenFindById_thenAnApprobationStatusIsReturned() {

        ClientProductsDto clientProductsDto = new ClientProductsDto();
        clientProductsDto.setApprobationStatus(1);
        when(crudClientProductsRepository.findById(any(ClientProductsPrimaryKeys.class)))
            .thenReturn(Optional.of(clientProductsDto));

        ApprobationStatus result = testedClass.findById(client, product);

        assertEquals(ApprobationStatus.fromInteger(clientProductsDto.getApprobationStatus()), result);
    }

    @Test
    public void ifNoClientProductExistAndNoClient_whenSaving_thenNothingIsSavedToDatabase() {

        when(crudClientProductsRepository.findById(any(ClientProductsPrimaryKeys.class))).thenReturn(Optional.empty());
        when(crudClientRepository.findById(any(String.class))).thenReturn(Optional.empty());
        when(crudProductRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        testedClass.save(client, product, ApprobationStatus.SUBSCRIBED);

        verify(crudClientProductsRepository, times(0)).save(any(ClientProductsDto.class));
    }

    @Test
    public void ifNoClientProductExistAndClientExist_whenSaving_thenDataIsSavedToDatabase() {
        ClientDto clientDto = new ClientDto();
        ProductDto productDto = new ProductDto();
        when(crudClientProductsRepository.findById(any(ClientProductsPrimaryKeys.class))).thenReturn(Optional.empty());
        when(crudClientRepository.findById(any(String.class))).thenReturn(Optional.of(clientDto));
        when(crudProductRepository.findById(any(Integer.class))).thenReturn(Optional.of(productDto));
        testedClass.save(client, product, ApprobationStatus.SUBSCRIBED);

        verify(crudClientProductsRepository, times(1)).save(any(ClientProductsDto.class));
    }

    @Test
    public void ifAClientProductExist_whenSaving_thenClientProductIsSavedToDatabase() {

        ClientProductsDto clientProductsDto = new ClientProductsDto();
        clientProductsDto.setApprobationStatus(1);
        when(crudClientProductsRepository.findById(any(ClientProductsPrimaryKeys.class)))
            .thenReturn(Optional.of(clientProductsDto));

        testedClass.save(client, product, ApprobationStatus.SUBSCRIBED);

        verify(crudClientProductsRepository, times(1)).save(clientProductsDto);
    }

    @Test
    public void ifAClientProductDoesNotExist_whenDeleting_thenClientProductIsNotDeletedToDatabase() {

        ClientProductsDto clientProductsDto = new ClientProductsDto();
        when(crudClientProductsRepository.findById(any(ClientProductsPrimaryKeys.class)))
            .thenReturn(Optional.empty());

        testedClass.deleteById(client, product);

        verify(crudClientProductsRepository, times(0)).deleteById(any(ClientProductsPrimaryKeys.class));
    }


    @Test
    public void ifAClientProductExist_whenDeleting_thenClientProductIsDeletedToDatabase() {

        ClientDto clientDto = new ClientDto();
        ProductDto productDto = new ProductDto();
        clientDto.setId("Mab");
        productDto.setId(1);
        ClientProductsDto clientProductsDto = new ClientProductsDto(clientDto, productDto);
        clientProductsDto.setApprobationStatus(ApprobationStatus.SUBSCRIBED.getValue());

        when(crudClientProductsRepository.findById(any(ClientProductsPrimaryKeys.class)))
            .thenReturn(Optional.of(clientProductsDto));

        testedClass.deleteById(client, product);

        verify(crudClientProductsRepository, times(1)).deleteById(any(ClientProductsPrimaryKeys.class));
    }
}