package bank.infrastructure.mapper;

import bank.domain.model.Product;
import bank.domain.model.ProductLevel;
import bank.infrastructure.entity.ProductDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.CORBA.PRIVATE_MEMBER;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {
    private ProductMapper productMapper;

    private final String NAME = "my product";
    private final Integer ID = 125;
    private final Integer PRODUCT_LEVEL = 0;
    private final  Integer PRODUCT_TYPE = 0;
    private final Integer APPROBATION_STATUS = 0;

    @Mock
    private ProductDto productDto;

    @Before
    public void setUp() {
        productMapper = new ProductMapper();
    }

    @Test
    public void givenProductDto_WhenMapToDomainProduct_ThenTheyShouldBeEquivalent() {
        // Given
        when(productDto.getId()).thenReturn(ID);
        when(productDto.getProductLevel()).thenReturn(PRODUCT_LEVEL);
        when(productDto.getProductType()).thenReturn(PRODUCT_TYPE);
        when(productDto.getName()).thenReturn(NAME);
        when(productDto.getApprobationStatus()).thenReturn(APPROBATION_STATUS);

        // When
        final Product product = productMapper.toProduct(productDto);
       
        // Then
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getProductLevel(), productDto.getProductLevel());
        assertEquals(product.getProductType(), productDto.getProductType());
        assertEquals(product.getApprobationStatus(), productDto.getApprobationStatus());
    }

    @Test
    public void givenNullProductDto_WhenMapToDomainProduct_ThenShouldBeNull() {
        // Given
        ProductDto nullProductDto = null;
        
        // When
        final Product product = productMapper.toProduct(nullProductDto);

        // Then
        assertNull(product);
    }
}