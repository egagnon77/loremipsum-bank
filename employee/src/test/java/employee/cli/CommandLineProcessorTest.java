package employee.cli;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import employee.domain.model.AddClient;
import employee.domain.service.EmployeeService;
import java.util.ArrayDeque;
import org.apache.commons.cli.CommandLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class CommandLineProcessorTest {

    private static final String A_CLIENT_NAME = "aClientName";
    private static final String THE_COMPLETED_MESSAGE = "Add Client '{}' Completed.";
    private static final String AN_EXCEPTION_MESSAGE = "myExceptionMessage";

    private AddClient addClient;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Logger logger;

    @Mock
    private CommandLineProcessor testedClass;

    @Before
    public void setUp() {
        testedClass = new CommandLineProcessor(employeeService, logger);
    }

    @Before
    public void initClient() {
        addClient = new AddClient();
        addClient.setName(A_CLIENT_NAME);
    }

    @Test
    public void givenACommandLineWithAddOption_whenProcess_thenLoggerShouldLogACompletedMessage() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.ADD.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.ADD.getValue())).thenReturn(A_CLIENT_NAME);
        when(employeeService.addClient(any(AddClient.class))).thenReturn(addClient);

        testedClass.process(commandLine);

        verify(logger).info(THE_COMPLETED_MESSAGE, addClient.getName());
    }

    @Test
    public void givenACommandLineWithAddOption_whenException_thenLoggerShouldLogTheException() {

        CommandLine commandLine = Mockito.mock(CommandLine.class);

        when(commandLine.hasOption(CliOptions.ADD.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.ADD.getValue())).thenReturn(A_CLIENT_NAME);
        doThrow(new DataSourceBadResponseException(AN_EXCEPTION_MESSAGE)).when(employeeService).addClient(any(AddClient.class));

        testedClass.process(commandLine);

        verify(logger).info(AN_EXCEPTION_MESSAGE);
    }

}
