package employee.infrastructure;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import employee.domain.exception.DataSourceBadResponseException;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WebBankEmployeeTest {

  private static final String A_NAME = "aName";

  @Mock
  private ResponseBuilder responseBuilder;

  private AddClient addClient;
  private Client client;

  private WebBankEmployee testedClass;

  @Before
  public void setUp() throws Exception {
    testedClass = new WebBankEmployee(responseBuilder);
  }

  @Before
  public void initClient() {
    addClient = new AddClient();
    addClient.setName(A_NAME);
    client = new Client();
    client.setName(A_NAME);
  }

  @Test(expected = DataSourceBadResponseException.class)
  public void whenAddClientFail_thenADataSourceBadResponseExceptionIsThrown() {
    when(responseBuilder.addClient(any(AddClient.class))).thenThrow(Exception.class);

    testedClass.addClient(addClient);
  }

  @Test(expected = DataSourceBadResponseException.class)
  public void whenGetProductsFail_thenADataSourceBadResponseExceptionIsThrown() {
    when(responseBuilder.listProducts(any(Client.class))).thenThrow(Exception.class);

    testedClass.getProducts(client);
  }

  @Test(expected = DataSourceBadResponseException.class)
  public void whenUpgradeClientFail_thenADataSourceBadResponseExceptionIsThrown() {
    when(responseBuilder.upgradeClient(any(Client.class))).thenThrow(Exception.class);

    testedClass.upgradeClient(client);
  }

  @Test(expected = DataSourceBadResponseException.class)
  public void whenDowngradeClientFail_thenADataSourceBadResponseExceptionIsThrown() {
    when(responseBuilder.downgradeClient(any(Client.class))).thenThrow(Exception.class);

    testedClass.downgradeClient(client);
  }

}