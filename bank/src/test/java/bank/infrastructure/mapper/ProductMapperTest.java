package bank.infrastructure.mapper;

import bank.domain.model.Product;
import bank.infrastructure.entity.ProductDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductMapperTest {
    private ProductMapper productMapper;

    private final String NAME = "my product";
    private final Integer ID = 125;
    private final Integer CATEGORY = 2;

    @Before
    public void setUp() {
        productMapper = new ProductMapper();
    }
       
    @Test
    public void givenProductDto_WhenMapToDomainProduct_ThenTheyShouldBeEquivalent() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setId(ID);
        productDto.setCategory(CATEGORY);
        productDto.setName(NAME);
        
        // When
        final Product product = productMapper.toProduct(productDto);
       
        // Then
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getCategory(), productDto.getCategory());
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