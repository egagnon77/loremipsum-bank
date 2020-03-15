package client;

import client.cli.CommandLineProcessor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    private CommandLineParser commandLineParser;
    private CommandLineProcessor commandLineProcessor;
    private Options options;
    private Logger logger;

    @Autowired
    public ClientApplication(Options options,
                             CommandLineParser commandLineParser,
                             CommandLineProcessor commandLineProcessor,
                             @Qualifier("clientApplicationLogger") Logger logger) {
        this.options = options;
        this.commandLineParser = commandLineParser;
        this.commandLineProcessor = commandLineProcessor;
        this.logger = logger;
    }

    public static void main(String[] args) {
        SpringApplication clientApplication = new SpringApplication(ClientApplication.class);
        clientApplication.run(args);
    }

    @Override
    public void run(String... args) {
        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            commandLineProcessor.process(commandLine);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}