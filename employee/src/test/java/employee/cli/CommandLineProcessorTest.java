package employee.cli;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import employee.cli.exception.CommandLineException;
import employee.domain.exception.DataSourceBadResponseException;
import employee.domain.factory.ClientFactory;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.model.Product;
import employee.domain.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
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
    private static final Integer A_PRODUCT_ID = 88;
    private static final String AN_INVALID_PRODUCT_ID = "invalideProductId";
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
    public void givenACommandLineWithAddOption_whenProcess_thenLoggerShouldLogACompletedMessage()
        throws ParseException {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.ADD.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.ADD.getValue())).thenReturn(A_CLIENT_NAME);
        when(employeeService.addClient(any(AddClient.class))).thenReturn(addClient);

        testedClass.process(commandLine);

        verify(logger).info("Add Client completed for: {}", addClient.getName());
    }

    @Test(expected = DataSourceBadResponseException.class)
    public void givenACommandLineWithAddOption_whenException_thenExceptionIsNotCatched() throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);

        when(commandLine.hasOption(CliOptions.ADD.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.ADD.getValue())).thenReturn(A_CLIENT_NAME);
        doThrow(new DataSourceBadResponseException(AN_EXCEPTION_MESSAGE)).when(employeeService)
            .addClient(any(AddClient.class));

        testedClass.process(commandLine);
    }

    @Test
    public void givenACommandLineWithListOption_whenProcess_thenLoggerShouldLogACompletedMessage()
        throws ParseException {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.LIST.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.LIST.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        when(employeeService.getProducts(client)).thenReturn(products);

        testedClass.process(commandLine);

        verify(logger).info("List products completed: {} ", client.toString());
    }

    @Test(expected = DataSourceBadResponseException.class)
    public void givenACommandLineWithListOption_whenException_thenLoggerShouldNotCatchTheException()
        throws ParseException {
        CommandLine commandLine = Mockito.mock(CommandLine.class);

        when(commandLine.hasOption(CliOptions.LIST.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.LIST.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        doThrow(new DataSourceBadResponseException(AN_EXCEPTION_MESSAGE)).when(employeeService)
            .getProducts(client);
        testedClass.process(commandLine);
    }

    @Test
    public void givenACommandLineWithUpgradeOption_whenProcess_thenLoggerShouldLogACompletedMessage()
        throws ParseException {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.UPGRADE.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.UPGRADE.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        Client upgradedClient = new Client();
        upgradedClient.setName(A_CLIENT_NAME);
        upgradedClient.setProductLevel(1);
        when(employeeService.upgradeClient(client)).thenReturn(upgradedClient);

        testedClass.process(commandLine);

        verify(logger).info("Upgrade Client completed for: {}", upgradedClient.toString());
    }

    @Test(expected = DataSourceBadResponseException.class)
    public void givenACommandLineWithUpgradeOption_whenException_thenLoggerShouldNotCatchTheException()
        throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.UPGRADE.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.UPGRADE.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        doThrow(new DataSourceBadResponseException(AN_EXCEPTION_MESSAGE)).when(employeeService).upgradeClient(client);

        testedClass.process(commandLine);
    }

    @Test
    public void givenACommandLineWithDowngradeOption_whenProcess_thenLoggerShouldLogACompletedMessage()
        throws ParseException {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.DOWNGRADE.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.DOWNGRADE.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        Client downgradedClient = new Client();
        downgradedClient.setName(A_CLIENT_NAME);
        downgradedClient.setProductLevel(0);
        when(employeeService.downgradeClient(client)).thenReturn(downgradedClient);

        testedClass.process(commandLine);

        verify(logger).info("Downgrade Client completed for: {}", downgradedClient.toString());
    }

    @Test(expected = DataSourceBadResponseException.class)
    public void givenACommandLineWithDowngradeOption_whenException_thenLoggerShouldNotCatchTheException()
        throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.DOWNGRADE.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.DOWNGRADE.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        doThrow(new DataSourceBadResponseException(AN_EXCEPTION_MESSAGE)).when(employeeService).downgradeClient(client);

        testedClass.process(commandLine);
    }

    @Test(expected = CommandLineException.class)
    public void givenACommandLineWithInvalidOption_whenException_thenLoggerShouldNotCatchException()
        throws ParseException {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        doThrow(new CommandLineException(AN_EXCEPTION_MESSAGE)).when(commandLineValidator).process(commandLine);
        testedClass.process(commandLine);
    }

    @Test
    public void givenACommandLineWithAcceptOptionAndClientName_thenAcceptProductMustBeInvoked() throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.ACCEPT.getValue())).thenReturn(true);
        when(commandLine.hasOption(CliOptions.CLIENT.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.ACCEPT.getValue())).thenReturn(A_PRODUCT_ID.toString());
        when(commandLine.getOptionValue(CliOptions.CLIENT.getValue())).thenReturn(A_CLIENT_NAME);

        testedClass.process(commandLine);

        verify(employeeService).acceptProduct(A_PRODUCT_ID, A_CLIENT_NAME);
    }

    @Test(expected = ParseException.class)
    public void givenACommandLineWithAcceptOptionAndInvalidProductId_thenParsExeptionMustBeThrown()
        throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.ACCEPT.getValue())).thenReturn(true);
        when(commandLine.hasOption(CliOptions.CLIENT.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.ACCEPT.getValue())).thenReturn(AN_INVALID_PRODUCT_ID);
        when(commandLine.getOptionValue(CliOptions.CLIENT.getValue())).thenReturn(A_CLIENT_NAME);

        testedClass.process(commandLine);

        verify(employeeService).acceptProduct(A_PRODUCT_ID, A_CLIENT_NAME);
    }

    @Test
    public void givenACommandLineWithRejectOptionAndClientName_thenRejectProductMustBeInvoked() throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.REJECT.getValue())).thenReturn(true);
        when(commandLine.hasOption(CliOptions.CLIENT.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.REJECT.getValue())).thenReturn(A_PRODUCT_ID.toString());
        when(commandLine.getOptionValue(CliOptions.CLIENT.getValue())).thenReturn(A_CLIENT_NAME);

        testedClass.process(commandLine);

        verify(employeeService).rejectProduct(A_PRODUCT_ID, A_CLIENT_NAME);
    }

    @Test(expected = ParseException.class)
    public void givenACommandLineWithRejectOptionAndInvalidProductId_thenParsExeptionMustBeThrown()
        throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.REJECT.getValue())).thenReturn(true);
        when(commandLine.hasOption(CliOptions.CLIENT.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptions.REJECT.getValue())).thenReturn(AN_INVALID_PRODUCT_ID);
        when(commandLine.getOptionValue(CliOptions.CLIENT.getValue())).thenReturn(A_CLIENT_NAME);

        testedClass.process(commandLine);

        verify(employeeService).rejectProduct(A_PRODUCT_ID, A_CLIENT_NAME);
    }

    @Test
    public void givenACommandLineWithTaskOption_thenTaskMustBeInvoked() throws ParseException {

        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.TASKS.getValue())).thenReturn(true);

        testedClass.process(commandLine);

        verify(employeeService).task();
    }

    private Product createProduct(Integer category, Integer id, String name) {
        Product product = new Product();
        product.setProductLevel(category);
        product.setId(id);
        product.setName(name);
        return product;
    }

}
