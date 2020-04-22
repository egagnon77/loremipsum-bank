package bank.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ClientTest {

    private static final String A_NAME = "aName";
    private static final Integer PRODUCT_LEVEL_NORMAL = 0;
    private static final Integer INVALID_PRODUCT_LEVEL = -1;

    private Client clientUnderTest;

    @Test
    public void givenAName_whenConstructAClient_thenNameMustBeAssigned() {

        clientUnderTest = new Client(A_NAME);

        assertEquals(A_NAME, clientUnderTest.getName());
    }

    @Test(expected = MissingParameterException.class)
    public void givenAEmptyName_whenConstructAClient_thenMissingParameterMustBeThrown() {
        new Client(StringUtils.EMPTY);
    }

    @Test(expected = MissingParameterException.class)
    public void givenANull_whenConstructAClient_thenMissingParameterMustBeThrown() {
        new Client(null);
    }

    @Test
    public void givenAName_whenConstructAClient_thenAProductLevelNormalMustBeAssigned() {

        clientUnderTest = new Client(A_NAME);

        assertEquals(PRODUCT_LEVEL_NORMAL, clientUnderTest.getProductLevel());
    }

    @Test
    public void givenAName_whenSettingProductLevelAClient_thenAProductLevelShouldBeEqual() {

        clientUnderTest = new Client(A_NAME);
        clientUnderTest.setProductLevel(PRODUCT_LEVEL_NORMAL);

        assertEquals(PRODUCT_LEVEL_NORMAL, clientUnderTest.getProductLevel());
    }

    @Test(expected = InvalidArgumentException.class)
    public void givenAName_whenSettingInvalidProductLevelAClient_thenAInvalidExceptionShouldBeThrown() {

        clientUnderTest = new Client(A_NAME);
        clientUnderTest.setProductLevel(INVALID_PRODUCT_LEVEL);

    }

    @Test
    public void whenUpgradingANormalStatus_thenStatusMustBeVIP() {

        clientUnderTest = new Client(A_NAME);

        clientUnderTest.upgradeStatus();

        assertEquals(ProductLevel.VIP.getValue(), clientUnderTest.getProductLevel());
    }

    @Test
    public void whenUpgradingAVIPStatus_thenStatusMustStillBeVIP() {

        clientUnderTest = new Client(A_NAME);
        clientUnderTest.setProductLevel(ProductLevel.VIP.getValue());

        clientUnderTest.upgradeStatus();

        assertEquals(ProductLevel.VIP.getValue(), clientUnderTest.getProductLevel());
    }

    @Test
    public void whenDowngradingANormalStatus_thenStatusMustStillBeNormal() {

        clientUnderTest = new Client(A_NAME);

        clientUnderTest.downgradeStatus();

        assertEquals(ProductLevel.NORMAL.getValue(), clientUnderTest.getProductLevel());
    }

    @Test
    public void whenDowngradingAVIPStatus_thenStatusMustBeNormal() {

        clientUnderTest = new Client(A_NAME);
        clientUnderTest.setProductLevel(ProductLevel.VIP.getValue());

        clientUnderTest.downgradeStatus();

        assertEquals(ProductLevel.NORMAL.getValue(), clientUnderTest.getProductLevel());
    }

    @Test
    public void whenCreatingAClientWithNoArgumentThenANotNullClientIsConstructed()
    {
        Client client= new Client();
        assertNotNull(client);
    }

    @Test
    public void whenAssigningProductThenClientContaintProduct() {
        Product product = new Product();
        clientUnderTest = new Client(A_NAME);
        clientUnderTest.getProducts().add(product);

        assertTrue(clientUnderTest.getProducts().contains(product));
    }

    @Test
    public void whenAssigningProductListThenClientListIsContained() {
        Product product = new Product();
        List<Product> productArrayList = new ArrayList<>();
        productArrayList.add(product);
        clientUnderTest = new Client(A_NAME);
        clientUnderTest.setProducts(productArrayList);
        assertEquals(productArrayList, clientUnderTest.getProducts());
    }

    @Test
    public void whenComparingTwoClientByNameThenTheyShouldBeEquals() {
        Client a = new Client(A_NAME);
        Client b = new Client(A_NAME);

        assertTrue(a.equals(b));
    }

    @Test
    public void whenComparingSameClientThenTheyShouldBeEquals() {
        Client a = new Client(A_NAME);

        assertTrue(a.equals(a));
    }

    @Test
    public void whenTypeComparedIsNotClientThenTheyShouldNotEquals() {
        Client a = new Client(A_NAME);
        Integer b = 0;
        assertFalse(a.equals(b));
    }

    @Test
    public void whenClientIsCreateThenAHashCodeShouldBeCreated() {
        Client a = new Client(A_NAME);
        Integer b = 0;
        assertTrue(a.hashCode() > 0);
    }
}