package employee.infrastructure;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import employee.cli.exception.DataSourceBadResponseException;
import employee.domain.model.AddClient;
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

  private WebBankEmployee testedClass;

  @Before
  public void setUp() throws Exception {
    testedClass = new WebBankEmployee(responseBuilder);
  }

  @Before
  public void initClient() {
    addClient = new AddClient();
    addClient.setName(A_NAME);
  }

  @Test(expected = DataSourceBadResponseException.class)
  public void whenAddProductsFail_thenADataSourceBadResponseExceptionIsThrown() {
    when(responseBuilder.addClient(any(AddClient.class))).thenThrow(Exception.class);

    testedClass.addClient(addClient);
  }
}