package client.cli;

import client.domain.factory.ClientFactory;
import client.domain.model.Client;
import client.domain.service.ClientService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
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

    public void process(CommandLine commandLine) throws ParseException {
        if (commandLine.hasOption(CliOptions.STATUS.getValue())) {
            processStatusClient(commandLine);
        } else if (commandLine.hasOption(CliOptions.AVAILABLE.getValue())) {
            processAvailableProducts(commandLine);
        } else if (commandLine.hasOption(CliOptions.SUBSCRIBE.getValue())) {
            processSubscribe(commandLine);
        } else if (commandLine.hasOption(CliOptions.UNSUBSCRIBE.getValue())) {
            processUnsubscribe(commandLine);
        } else {
            throw new ParseException("Invalid command.");
        }
    }

    private void processStatusClient(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        client.setProducts(clientService.getProducts(client));
        logger.info("Status completed: {}", client.toString());
    }

    private void processAvailableProducts(CommandLine commandLine) {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        client.setProducts(clientService.getAvailableProducts(client));
        logger.info("Available products completed: {}", client.toString());
    }

    private void processSubscribe(CommandLine commandLine) throws ParseException {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        String productId = commandLine.getOptionValue(CliOptions.SUBSCRIBE.getValue());
        if (!StringUtils.isNumeric(productId)) {
            throw new ParseException("Product id is not a numeric value.");
        }
        clientService.subscribeProduct(client, (Integer.valueOf(productId)));
        logger.info("Subscribed ProductId '{}' Completed. There may be an authorization required.", productId);
    }

    private void processUnsubscribe(CommandLine commandLine) throws ParseException {
        Client client = clientFactory.create(commandLine.getOptionValue(CliOptionsValue.NAME.getValue()));
        String productId = commandLine.getOptionValue(CliOptions.UNSUBSCRIBE.getValue());
        if (!StringUtils.isNumeric(productId)) {
            throw new ParseException("Product id is not a numeric value.");
        }
        clientService.unSubscribeProduct(client, (Integer.valueOf(productId)));
        logger.info("Unsubscribed ProductId '{}' Completed. There may be an authorization required.", productId);
    }
}