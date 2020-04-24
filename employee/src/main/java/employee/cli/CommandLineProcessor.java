package employee.cli;

import employee.domain.factory.ClientFactory;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.service.EmployeeService;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommandLineProcessor {

    private EmployeeService employeeService;
    private ClientFactory clientFactory;
    private Logger logger;
    private CommandLineValidator commandLineValidator;

    @Autowired
    public CommandLineProcessor(
        EmployeeService employeeService,
        CommandLineValidator commandLineValidator,
        ClientFactory clientFactory,
        @Qualifier("commandLineProcessorLogger") Logger logger
    ) {
        this.employeeService = employeeService;
        this.clientFactory = clientFactory;
        this.commandLineValidator = commandLineValidator;
        this.logger = logger;
    }

    public void process(CommandLine commandLine) throws ParseException {
        commandLineValidator.process(commandLine);

        if (commandLine.hasOption(CliOptions.ADD.getValue())) {
            processAddClient(commandLine.getOptionValue(CliOptions.ADD.getValue()));
        } else if (commandLine.hasOption(CliOptions.LIST.getValue())) {
            processListClient(commandLine.getOptionValue(CliOptions.LIST.getValue()));
        } else if (commandLine.hasOption(CliOptions.UPGRADE.getValue())) {
            processUpgradeClient(commandLine.getOptionValue(CliOptions.UPGRADE.getValue()));
        } else if (commandLine.hasOption(CliOptions.DOWNGRADE.getValue())) {
            processDowngradeClient(commandLine.getOptionValue(CliOptions.DOWNGRADE.getValue()));
        } else if (commandLine.hasOption(CliOptions.ACCEPT.getValue()) && commandLine
            .hasOption(CliOptions.CLIENT.getValue())) {
            processAcceptProduct(commandLine.getOptionValue(CliOptions.ACCEPT.getValue()),
                commandLine.getOptionValue(CliOptions.CLIENT.getValue()));
        } else if (commandLine.hasOption(CliOptions.REJECT.getValue()) && commandLine
            .hasOption(CliOptions.CLIENT.getValue())) {
            processRejectProduct(commandLine.getOptionValue(CliOptions.REJECT.getValue()),
                commandLine.getOptionValue(CliOptions.CLIENT.getValue()));
        } else if (commandLine.hasOption(CliOptions.TASKS.getValue())) {
            processTask();
        }
    }

    private void processTask() {
        List<Client> clients = employeeService.task();
        logger.info(clients.toString());
    }

    private void processAcceptProduct(String productId, String clientName) throws ParseException {
        if (!StringUtils.isNumeric(productId)) {
            throw new ParseException("Product id is not a numeric value.");
        }
        employeeService.acceptProduct(Integer.valueOf(productId), clientName);
        logger.info("Accept Product {} for Client '{}' completed.", productId, clientName);
    }

    private void processRejectProduct(String productId, String clientName) throws ParseException {
        if (!StringUtils.isNumeric(productId)) {
            throw new ParseException("Product id is not a numeric value.");
        }
        employeeService.rejectProduct(Integer.valueOf(productId), clientName);
        logger.info("Reject Product {} for Client '{}' completed.", productId, clientName);
    }

    private void processUpgradeClient(String clientName) {
        Client upgradedClient = employeeService.upgradeClient(clientFactory.create(clientName));
        logger.info("Upgrade Client completed for: {}", upgradedClient.toString());

    }

    private void processDowngradeClient(String clientName) {
        Client downgradedClient = employeeService.downgradeClient(clientFactory.create(clientName));
        logger.info(downgradedClient.toString());
        logger.info("Downgrade Client completed for: {}", downgradedClient.toString());
    }

    private void processAddClient(String clientName) {
        AddClient addClient = new AddClient();
        addClient.setName(clientName);
        AddClient addedClient = employeeService.addClient(addClient);
        logger.info("Add Client completed for: {}", addedClient.getName());
    }

    private void processListClient(String clientName) {
        Client client = clientFactory.create(clientName);
        client.setProducts(employeeService.getProducts(client));
        logger.info("List products completed: {} ", client.toString());
    }
}
