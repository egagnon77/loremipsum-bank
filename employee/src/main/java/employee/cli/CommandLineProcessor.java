package employee.cli;

import employee.cli.exception.CommandLineException;
import employee.domain.exception.DataSourceBadResponseException;
import employee.domain.exception.NotFoundException;
import employee.domain.factory.ClientFactory;
import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.service.EmployeeService;
import org.apache.commons.cli.CommandLine;
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

    public void process(CommandLine commandLine) {
        commandLineValidator.process(commandLine);

        if (commandLine.hasOption(CliOptions.ADD.getValue())) {
            processAddClient(commandLine.getOptionValue(CliOptions.ADD.getValue()));
        } else if (commandLine.hasOption(CliOptions.LIST.getValue())) {
            processListClient(commandLine.getOptionValue(CliOptions.LIST.getValue()));
        } else if (commandLine.hasOption(CliOptions.UPGRADE.getValue())) {
            processUpgradeClient(commandLine.getOptionValue(CliOptions.UPGRADE.getValue()));
        } else if (commandLine.hasOption(CliOptions.DOWNGRADE.getValue())) {
            processDowngradeClient(commandLine.getOptionValue(CliOptions.DOWNGRADE.getValue()));
        }
    }

    private void processUpgradeClient(String clientName) {
        Client upgradedClient = employeeService.upgradeClient(clientFactory.create(clientName));
        logger.info(upgradedClient.toString());
    }

    private void processDowngradeClient(String clientName) {
        Client downgradedClient = employeeService.downgradeClient(clientFactory.create(clientName));
        logger.info(downgradedClient.toString());       
    }

    private void processAddClient(String clientName) {
        AddClient addClient = new AddClient();
        addClient.setName(clientName);
        AddClient addedClient = employeeService.addClient(addClient);
        logger.info("Add Client '{}' Completed.", addedClient.getName());
    }

    private void processListClient(String clientName) {
        Client client = clientFactory.create(clientName);
        client.setProducts(employeeService.getProducts(client));
        logger.info(client.toString());
    }
}
