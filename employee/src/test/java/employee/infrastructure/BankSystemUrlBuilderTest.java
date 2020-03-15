package employee.infrastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankSystemUrlBuilderTest {

    private BankSystemUrlBuilder testedClass;
    private static final String A_BASE_URL = "aBaseUrl";
    private static final String ADD_URL = "/client/";


    @Before
    public void setUp() throws Exception {
        testedClass = new BankSystemUrlBuilder(A_BASE_URL);
    }

    @Test
    public void buildAddClientUrl() {
        String result = testedClass.buildAddClientUrl();

        assertEquals(A_BASE_URL + ADD_URL, result);
    }
}