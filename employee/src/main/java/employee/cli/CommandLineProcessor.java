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
    public CommandLineProcessor(EmployeeService employeeService,
        CommandLineValidator commandLineValidator,
        ClientFactory clientFactory,
        @Qualifier("commandLineProcessorLogger") Logger logger) {
        this.employeeService = employeeService;
        this.clientFactory = clientFactory;
        this.commandLineValidator = commandLineValidator;
        this.logger = logger;
    }

    public void process(CommandLine commandLine) {
        try {
            commandLineValidator.process(commandLine);

            if (commandLine.hasOption(CliOptions.ADD.getValue())) {
                processAddClient(commandLine.getOptionValue(CliOptions.ADD.getValue()));
            } else if (commandLine.hasOption(CliOptions.LIST.getValue())) {
                processListClient(commandLine.getOptionValue(CliOptions.LIST.getValue()));
            }
        } catch (CommandLineException e) {
            logger.error(e.getMessage());
        }

    }

    private void processAddClient(String clientName) {
        AddClient addClient = new AddClient();
        addClient.setName(clientName);
        try {
            AddClient addedClient = employeeService.addClient(addClient);
            logger.info("Add Client '{}' Completed.", addedClient.getName());
        } catch (DataSourceBadResponseException e) {
            logger.error(e.getMessage());
        }
    }

    private void processListClient(String clientName) {

        Client client = clientFactory.create(clientName);

        try {
            client.setProducts(employeeService.getProducts(client));
            logger.info(client.toString());
        } catch (DataSourceBadResponseException e) {
            logger.error(e.getMessage());
        } catch (NotFoundException e) {
            logger.error(e.getMessage());
        }

    }

}
