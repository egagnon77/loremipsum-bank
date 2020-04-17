package bank.infrastructure.mapper;

import bank.domain.model.Product;
import bank.infrastructure.entity.ProductDto;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PRIVATE_MEMBER;

import static org.junit.Assert.*;

public class ProductMapperTest {
    private ProductMapper productMapper;

    private final String NAME = "my product";
    private final Integer ID = 125;
    private final Integer PRODUCT_LEVEL = 0;
    private final  Integer PRODUCT_TYPE = 0;

    @Before
    public void setUp() {
        productMapper = new ProductMapper();
    }
       
    @Test
    public void givenProductDto_WhenMapToDomainProduct_ThenTheyShouldBeEquivalent() {
        // Given
        ProductDto productDto = new ProductDto();
        productDto.setId(ID);
        productDto.setProductLevel(PRODUCT_LEVEL);
        productDto.setProductType(PRODUCT_TYPE);
        productDto.setName(NAME);
        
        // When
        final Product product = productMapper.toProduct(productDto);
       
        // Then
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getProductLevel(), productDto.getProductLevel());
        assertEquals(product.getProductType(), productDto.getProductType());
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