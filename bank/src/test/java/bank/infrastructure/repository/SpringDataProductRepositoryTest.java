package bank.infrastructure.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import bank.domain.model.Product;
import bank.infrastructure.entity.ProductDto;
import bank.infrastructure.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpringDataProductRepositoryTest {

    private static final Integer A_PRODUCT_ID = 88;

    @Mock
    private CrudProductRepository crudProductRepository;

    @Mock
    private ProductMapper productMapper;

    private SpringDataProductRepository testedClass;

    @Before
    public void setUp() {
        testedClass = new SpringDataProductRepository(crudProductRepository, productMapper);
    }

    @Test
    public void givenAValidProduct_WhenFindAll_thenAListMustBeReturned(){
        List<ProductDto> productdtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productdtos.add(productDto);
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        products.add(product);
        when(crudProductRepository.findAll()).thenReturn(productdtos);
        when(productMapper.toProduct(productDto)).thenReturn(product);

        List<Product> results = testedClass.findAll();

        assertEquals(product, results.get(0));

    }

    @Test
    public void ifProductDontExistInDatabase_whenFindById_thenNoProductIsReturned() {

        when(crudProductRepository.findById(A_PRODUCT_ID)).thenReturn(Optional.empty());

        Optional<Product> result = testedClass.findById(A_PRODUCT_ID);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void whenFindById_thenAProductIsReturned() {

        ProductDto productDto = new ProductDto();
        Optional<ProductDto> optionalProductDto = Optional.of(productDto);
        when(crudProductRepository.findById(A_PRODUCT_ID)).thenReturn(optionalProductDto);
        Product product = new Product();
        when(productMapper.toProduct(productDto)).thenReturn(product);

        Optional<Product> result = testedClass.findById(A_PRODUCT_ID);

        assertEquals(product, result.get());
    }

}
