package employee.infrastructure.configuration;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;

public class BankEmployeeConfigurationTest {

  private BankEmployeeConfiguration testedClass;

  @Before
  public void setUp() {
    testedClass = new BankEmployeeConfiguration();
  }

  @Test
  public void whenGettingRestTemplate_thenARestTemplateMustBeReturned() {

    WebClient result = testedClass.webClient();

    assertNotNull(result);
  }


}
