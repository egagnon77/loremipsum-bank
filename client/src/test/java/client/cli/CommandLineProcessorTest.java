package client.cli;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import client.domain.exception.NotFoundException;
import client.domain.factory.ClientFactory;
import client.domain.model.Client;
import client.domain.model.Product;
import client.domain.service.ClientService;
import java.util.ArrayList;
import java.util.List;
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
    private static final String A_PRODUCT_NAME = "aProductName";
    private static final String A_MESSAGE = "aMessage";
    private static final Integer A_PRODUCT_ID = 1;

    @Mock
    private ClientFactory clientFactory;
    @Mock
    private ClientService clientService;
    @Mock
    private Logger logger;

    private List<Product> products;

    private CommandLineProcessor testedClass;

    @Before
    public void setUp() {
        testedClass = new CommandLineProcessor(clientService, logger, clientFactory);
    }

    @Before
    public void setUp_listProducts() {
        products = new ArrayList<>();
        products.add(createProduct(1, 1, A_PRODUCT_NAME + 1));
        products.add(createProduct(2, 2, A_PRODUCT_NAME + 2));
    }

    @Test
    public void givenACommandLineWithStatusOption_whenProcess_thenLoggerShouldLogAClientWithHisProducts() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.Status.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptionsValue.Name.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        when(clientService.getProducts(client)).thenReturn(products);

        testedClass.process(commandLine);

        verify(logger).info(client.toString());
    }

    @Test
    public void givenACommandLineWithAvailableOption_whenProcess_thenLoggerShouldLogAClientWithHisProducts() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.Available.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptionsValue.Name.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        when(clientService.getAvailableProducts(client)).thenReturn(products);

        testedClass.process(commandLine);

        verify(logger).info(client.toString());
    }

    @Test(expected = NotFoundException.class)
    public void givenACommandLineWithAvailableOption_whenAnNotFoundExceptionOccursDuringProcess_thenShouldNOtCatchException() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.Available.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptionsValue.Name.getValue())).thenReturn(A_CLIENT_NAME);
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);
        when(clientService.getAvailableProducts(client)).thenThrow(new NotFoundException(A_MESSAGE));

        testedClass.process(commandLine);
    }

    @Test
    public void givenACommandLineWithSubscribeOptionAndClientName_thenSubscribeProductMustBeInvoked() {
        CommandLine commandLine = Mockito.mock(CommandLine.class);
        when(commandLine.hasOption(CliOptions.Subscribe.getValue())).thenReturn(true);
        when(commandLine.hasOption(CliOptionsValue.Name.getValue())).thenReturn(true);
        when(commandLine.getOptionValue(CliOptionsValue.Name.getValue())).thenReturn(A_CLIENT_NAME);
        when(commandLine.getOptionValue(CliOptions.Subscribe.getValue())).thenReturn(A_PRODUCT_ID.toString());
        Client client = new Client();
        client.setName(A_CLIENT_NAME);
        when(clientFactory.create(A_CLIENT_NAME)).thenReturn(client);

        testedClass.process(commandLine);

        verify(clientService).subscribeProduct(client, A_PRODUCT_ID);
    }


    private Product createProduct(Integer category, Integer id, String name) {
        Product product = new Product();
        product.setCategory(category);
        product.setId(id);
        product.setName(name);
        return product;
    }
}