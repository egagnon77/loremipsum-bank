package bank.domain.model;

import static org.junit.Assert.assertEquals;

import bank.domain.exception.MissingParameterException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ProductTest {

    @Test
    public void givenProductIdAndNameAndCategory_whenConstructProduct_thenMembersShouldBeAssigned() {
        // Given
        final String name = "my product";
        final Integer id = 125;
        final Integer productLevel = 0;
        final Integer productType = 0;
        final Integer approbationStatus = 0;

        // When
        Product product = new Product(id, name, productType, productLevel, approbationStatus);

        // Then
        assertEquals(name, product.getName());
        assertEquals(id, product.getId());
        assertEquals(productType, product.getProductType());
    }

    @Test(expected = MissingParameterException.class)
    public void givenEmptyName_whenConstructProduct_thenMissingParameterMustBeThrown() {
        new Product(1, StringUtils.EMPTY, 2, 0, 0);
    }

    @Test(expected = MissingParameterException.class)
    public void givenNullName_whenConstructProduct_thenMissingParameterMustBeThrown() {
        new Product(1, null, 2, 0, 0);
    }
}