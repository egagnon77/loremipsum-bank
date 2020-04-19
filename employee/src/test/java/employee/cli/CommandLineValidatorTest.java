package employee.cli;

import static org.mockito.Mockito.when;

import employee.cli.exception.CommandLineException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandLineValidatorTest {

    private static String NO_OPTION_DETECTED_MESSAGE = "No option detected in the command line.";
    private static String MORE_THAN_ONE_OPTION_MESSAGE = "More than two options detected in the command line.";

    @Mock
    private CommandLineValidator testedClass;

    @Before
    public void setUp() {
        testedClass = new CommandLineValidator();
    }

    @Test
    public void givenACommandLineWithTwoOptionSThenACommandLineExceptionShouldArise() {
        Option optionA = new Option("OptionA", "Description");
        Option optionB = new Option("OptionB", "Description");

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.getOptions()).thenReturn(new Option[]{optionA, optionB});

        Assert.assertThrows(MORE_THAN_ONE_OPTION_MESSAGE, CommandLineException.class, () -> {
            testedClass.process(commandLine);
        });

    }

    @Test
    public void givenACommandLineWithNoOptionThenACommandLineExceptionShouldArise() {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.getOptions()).thenReturn(new Option[]{});

        Assert.assertThrows(NO_OPTION_DETECTED_MESSAGE, CommandLineException.class, () -> {
            testedClass.process(commandLine);
        });
    }

    @Test
    public void givenACommandLineWithValidOptionCountThenNoExceptionShouldArise() {
        Option optionA = new Option("OptionA", "Description");
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.getOptions()).thenReturn(new Option[]{optionA});

        Assertions.assertThatCode(() -> testedClass.process(commandLine))
            .doesNotThrowAnyException();
    }
}