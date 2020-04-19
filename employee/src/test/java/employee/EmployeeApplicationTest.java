package employee;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import employee.cli.CommandLineProcessor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeApplicationTest {

    private static final String AN_ARGUMENT = "anArgument";
    private static final String A_MESSAGE = "aMessage";
    public static final String CMD_LINE_SYNTAX = "usage: java -jar employee.jar";

    @Mock
    private Options options;
    @Mock
    private CommandLineParser commandLineParser;
    @Mock
    private CommandLineProcessor commandLineProcessor;
    @Mock
    private Logger logger;

    private EmployeeApplication testedClass;

    @Before
    public void setUp() {
        testedClass = new EmployeeApplication(options, commandLineParser, commandLineProcessor,
            logger);
    }

    @Test
    public void givenAnyArguments_whenRun_thenCommandLineProcessorShouldProcessACommandLine()
        throws Exception {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLineParser.parse(options, new String[]{AN_ARGUMENT})).thenReturn(commandLine);

        testedClass.run(AN_ARGUMENT);

        verify(commandLineProcessor).process(commandLine);
    }

    @Test(expected = Exception.class)
    public void ifAnExceptionIsThrownByTheCommandLineParserThenAnExceptionShouldBeThrown()
        throws Exception {

        when(commandLineParser.parse(options, new String[]{AN_ARGUMENT}))
            .thenThrow(Exception.class);

        testedClass.run(AN_ARGUMENT);
    }

    @Test
    public void ifAnExceptionIsThrownByTheCommandLineParserThenAnExceptionShouldBeLogged()
        throws Exception {

        when(commandLineParser.parse(options, new String[]{AN_ARGUMENT}))
            .thenThrow(new ParseException(A_MESSAGE));

        testedClass.run(AN_ARGUMENT);

        verify(logger).error(eq(A_MESSAGE));
    }

    @Test
    public void ifNoArgumentArePassedToCommandLineParserThenHelpMessageShouldBeLogged()
        throws Exception {

        testedClass.run();

        verify(logger).info(startsWith(CMD_LINE_SYNTAX));
    }


}
