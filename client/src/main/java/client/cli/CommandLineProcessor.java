package client.cli;

import client.domain.exception.NotFoundException;
import client.domain.factory.ClientFactory;
import client.domain.model.Client;
import client.domain.service.ClientService;
import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommandLineProcessor {

    private ClientFactory clientFactory;
    private ClientService clientService;
    private Logger logger;

    @Autowired
    public CommandLineProcessor(ClientService clientService,
                                @Qualifier("commandLineProcessorLogger") Logger logger,
                                ClientFactory clientFactory) {
        this.clientService = clientService;
        this.logger = logger;
        this.clientFactory = clientFactory;
    }

    public void process(CommandLine commandLine) {
        if (commandLine.hasOption(CliOptions.Status.getValue())) {
            processStatusClient(commandLine);
        } else if (commandLine.hasOption(CliOptions.Available.getValue())) {
            processAvailableProducts(commandLine);
        }
    }

    private void processStatusClient(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.Name.getValue()));
        try {
            client.setProducts(clientService.getProducts(client));
            logger.info(client.toString());
        } catch (NotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    private void processAvailableProducts(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.Name.getValue()));
        try {
            client.setProducts(clientService.getAvailableProducts(client));
            logger.info(client.toString());
        } catch (NotFoundException e) {
            logger.error(e.getMessage());
        }
    }
}