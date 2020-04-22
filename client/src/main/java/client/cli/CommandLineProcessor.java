package client.cli;

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
    public CommandLineProcessor(
        ClientService clientService,
        @Qualifier("commandLineProcessorLogger") Logger logger,
        ClientFactory clientFactory
    ) {
        this.clientService = clientService;
        this.logger = logger;
        this.clientFactory = clientFactory;
    }

    public void process(CommandLine commandLine) {
        if (commandLine.hasOption(CliOptions.STATUS.getValue())) {
            processStatusClient(commandLine);
        } else if (commandLine.hasOption(CliOptions.AVAILABLE.getValue())) {
            processAvailableProducts(commandLine);
        } else if (commandLine.hasOption(CliOptions.SUBSCRIBE.getValue())) {
            processSubscribe(commandLine);
        } else if (commandLine.hasOption(CliOptions.UNSUBSCRIBE.getValue())) {
            processUnsubscribe(commandLine);
        }
    }

    private void processStatusClient(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        client.setProducts(clientService.getProducts(client));
        logger.info(client.toString());
    }

    private void processAvailableProducts(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        client.setProducts(clientService.getAvailableProducts(client));
        logger.info(client.toString());
    }

    private void processSubscribe(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        String productId = commandLine.getOptionValue(CliOptions.SUBSCRIBE.getValue());
        clientService.subscribeProduct(client, (Integer.valueOf(productId)));
        logger.info("Subscribed ProductId '{}' Completed.", productId);
    }

    private void processUnsubscribe(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        String productId = commandLine.getOptionValue(CliOptions.UNSUBSCRIBE.getValue());
        clientService.unSubscribeProduct(client, (Integer.valueOf(productId)));
        logger.info("Unsubscribed ProductId '{}' Completed. There may be an authorization required.", productId);
    }
}