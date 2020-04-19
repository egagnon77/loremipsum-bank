package bank.domain.model;

import static org.junit.Assert.assertEquals;

import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
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
}