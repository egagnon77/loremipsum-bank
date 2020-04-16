package employee.cli;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import employee.cli.exception.CommandLineException;
import employee.cli.exception.DataSourceBadResponseException;
import employee.domain.factory.ClientFactory;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.model.Product;
import employee.domain.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
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
    private static final String A_PRODUCT_NAME = "aProductName";
    private static final String THE_COMPLETED_MESSAGE = "Add Client '{}' Completed.";
    private static final String AN_EXCEPTION_MESSAGE = "myExceptionMessage";

    private AddClient addClient;
    private Client client;
    private List<Product> products;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private Logger logger;

    @Mock
    private CommandLineValidator commandLineValidator;

    @Mock
    private CommandLineProcessor testedClass;

    @Mock
    private ClientFactory clientFactory;

    @Before
    public void setUp() {
        testedClass = new CommandLineProcessor(employeeService, commandLineValidator, clientFactory,
            logger);
    }

    @Before
    public void initClient() {
        addClient = new AddClient();
        addClient.setName(A_CLIENT_NAME);
        client = new Client();
        client.setName(A_CLIENT_NAME);
    }

    @Before
    public void setUp_listProducts() {
        products = new ArrayList<>();
        products.add(createProduct(1, 1, A_PRODUCT_NAME + 1));
        products.add(createProduct(2, 2, A_PRODUCT_NAME + 2));
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
        doThrow(new DataSourceBadResponseException(AN_EXCEPTION_MESSAGE)).when(employeeService)
            .addClient(any(AddClient.class));

        testedClass.process(commandLine);

        verify(logger).error(AN_EXCEPTION_MESSAGE);
    }

    @Test
    public void givenACommandLineWithListOption_whenProcess_thenLoggerShouldLogACompletedMessage() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.LIST.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.LIST.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        when(employeeService.getProducts(client)).thenReturn(products);

        testedClass.process(commandLine);

        verify(logger).info(client.toString());
    }

    @Test
    public void givenACommandLineWithListOption_whenException_thenLoggerShouldLogTheException() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);

        when(commandLine.hasOption(CliOptions.LIST.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.LIST.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        doThrow(new DataSourceBadResponseException(AN_EXCEPTION_MESSAGE)).when(employeeService).getProducts(client);
        testedClass.process(commandLine);

        verify(logger).error(AN_EXCEPTION_MESSAGE);
    }

    @Test
    public void givenACommandLineWithInvalidOption_whenException_thenLoggerShouldLogTheException() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        doThrow(new CommandLineException(AN_EXCEPTION_MESSAGE)).when(commandLineValidator).process(commandLine);
        testedClass.process(commandLine);

        verify(logger).error(AN_EXCEPTION_MESSAGE);
    }

    private Product createProduct(Integer category, Integer id, String name) {
        Product product = new Product();
        product.setCategory(category);
        product.setId(id);
        product.setName(name);
        return product;
    }

}
