package bank.infrastructure.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import bank.domain.model.Product;
import bank.infrastructure.entity.ProductDto;
import bank.infrastructure.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpringDataProductRepositoryTest {

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

}
