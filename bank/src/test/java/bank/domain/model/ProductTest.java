package bank.domain.model;

import static org.junit.Assert.assertEquals;

import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ProductTest {

    private final String NAME = "my product";
    private final Integer ID_PRODUCT = 125;
    private final Integer PRODUCT_LEVEL = 0;
    private final Integer PRODUCT_TYPE_AUTOMATIC = 0;
    private final Integer INVALID_PRODUCT_LEVEL = -1;
    private final Integer INVALID_PRODUCT_TYPE = -1;

    @Test
    public void givenProductIdAndNameAndCategory_whenConstructProduct_thenMembersShouldBeAssigned() {
        Product product = new Product(ID_PRODUCT, NAME, PRODUCT_TYPE_AUTOMATIC, PRODUCT_LEVEL);

        // Then
        assertEquals(NAME, product.getName());
        assertEquals(ID_PRODUCT, product.getId());
        assertEquals(PRODUCT_TYPE_AUTOMATIC, product.getProductType());
        assertEquals(PRODUCT_LEVEL, product.getProductLevel());
    }

    @Test(expected = MissingParameterException.class)
    public void givenEmptyName_whenConstructProduct_thenMissingParameterMustBeThrown() {
        new Product(1, StringUtils.EMPTY, 2, 0);
    }

    @Test(expected = MissingParameterException.class)
    public void givenNullName_whenConstructProduct_thenMissingParameterMustBeThrown() {
        new Product(ID_PRODUCT, null, 2, 0);
    }

    @Test(expected = InvalidArgumentException.class)
    public void givenInvalidProductType_whenConstructProduct_thenInvalidArgumentMustBeThrown() {
        new Product(ID_PRODUCT, NAME, INVALID_PRODUCT_TYPE, PRODUCT_LEVEL);
    }

    @Test(expected = InvalidArgumentException.class)
    public void givenInvalidProductLevel_whenConstructProduct_thenInvalidArgumentMustBeThrown() {
        new Product(ID_PRODUCT, NAME, PRODUCT_TYPE_AUTOMATIC, INVALID_PRODUCT_LEVEL);
    }
}