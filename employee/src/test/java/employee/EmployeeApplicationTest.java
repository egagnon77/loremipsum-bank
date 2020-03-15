package employee;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import employee.cli.CommandLineProcessor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeApplicationTest {

  private static final String ANY_ARGUMENT = "anyArgument";

  @Mock
  private Options options;
  @Mock
  private CommandLineParser commandLineParser;
  @Mock
  private CommandLineProcessor commandLineProcessor;

  private EmployeeApplication testedClass;

  @Before
  public void setUp() {
    testedClass = new EmployeeApplication(options, commandLineParser, commandLineProcessor);
  }

  @Test
  public void givenAnyArguments_whenRun_thenCommandLineProcessorShouldProcessACommandLine()
      throws Exception {

    CommandLine commandLine = Mockito.mock(CommandLine.class);
    when(commandLineParser.parse(options, new String[]{ANY_ARGUMENT})).thenReturn(commandLine);

    testedClass.run(ANY_ARGUMENT);

    verify(commandLineProcessor).process(commandLine);
  }

  @Test(expected = Exception.class)
  public void ifAnExceptionIsThrownByTheCommandLineParserThenAnExceptionShouldBeThrown()
      throws Exception {

    when(commandLineParser.parse(options, new String[]{ANY_ARGUMENT}))
        .thenThrow(Exception.class);

    testedClass.run(ANY_ARGUMENT);
  }


}
