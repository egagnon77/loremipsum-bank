package client;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import client.cli.CommandLineProcessor;
import client.domain.exception.DataSourceBadResponseException;
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
public class ClientApplicationTest {

    private static final String AN_ARGUMENT = "anArgument";
    private static final String A_MESSAGE = "aMessage";

    @Mock
    private Options options;
    @Mock
    private CommandLineParser commandLineParser;
    @Mock
    private CommandLineProcessor commandLineProcessor;
    @Mock
    private Logger logger;

    private ClientApplication testedClass;

    @Before
    public void setUp() {
        testedClass = new ClientApplication(options, commandLineParser, commandLineProcessor, logger);
    }

    @Test
    public void givenAnyArguments_whenRun_thenCommandLineProcessorShouldProcessACommandLine() throws Exception {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLineParser.parse(options, new String[]{AN_ARGUMENT})).thenReturn(commandLine);

        testedClass.run(AN_ARGUMENT);

        verify(commandLineProcessor).process(commandLine);
    }

    @Test(expected = ParseException.class)
    public void ifAnExceptionIsThrownByTheCommandLineParserThenAnException() throws Exception {

        when(commandLineParser.parse(options, new String[]{AN_ARGUMENT})).thenThrow(new ParseException(A_MESSAGE));
        testedClass.run(AN_ARGUMENT);
    }

    @Test(expected = ParseException.class)
    public void givenEmptyArgumentWhenParsingThenAParExceptionMustBeThrown() throws Exception {

        String[] arg = new String[0];
        testedClass.run(arg);
    }

}