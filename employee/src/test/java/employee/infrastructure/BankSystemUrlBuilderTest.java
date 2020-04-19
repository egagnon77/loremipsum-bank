package employee.infrastructure;

import static org.junit.Assert.assertEquals;

import employee.domain.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.CORBA.PRIVATE_MEMBER;

@RunWith(MockitoJUnitRunner.class)
public class BankSystemUrlBuilderTest {

    private BankSystemUrlBuilder testedClass;
    private static final String A_BASE_URL = "aBaseUrl";
    private static final String CLIENT_URL = "/client/";
    private static final String PRODUCTS_URL = "/products";

    private static final String A_CLIENT_NAME = "aClientName";


    @Before
    public void setUp() throws Exception {
        testedClass = new BankSystemUrlBuilder(A_BASE_URL);
    }

    @Test
    public void buildAddClientUrl() {
        String result = testedClass.buildAddClientUrl();

        assertEquals(A_BASE_URL + CLIENT_URL, result);
    }

    @Test
    public void buildListProductUrl() {
        Client client = new Client();
        client.setName(A_CLIENT_NAME);

        String result = testedClass.buildGetProductsUrl(client);
        assertEquals( A_BASE_URL + CLIENT_URL + client.getName() + PRODUCTS_URL,result);

    }

    @Test
    public void buildUpgradeClientUrl() {
        Client client = new Client();
        client.setName(A_CLIENT_NAME);

        String result = testedClass.buildUpgradeClientUrl(client);

        assertEquals(A_BASE_URL + "/client/" + client.getName() + "/status/upgrade", result);
    }

    @Test
    public void buildDowngradeClientUrl() {
        Client client = new Client();
        client.setName(A_CLIENT_NAME);

        String result = testedClass.buildDowngradeClientUrl(client);

        assertEquals(A_BASE_URL + "/client/" + client.getName() + "/status/downgrade", result);
    }
}