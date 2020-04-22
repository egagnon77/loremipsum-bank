package client;

import client.cli.CommandLineProcessor;
import client.domain.exception.DataSourceBadResponseException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    private static final int EXIT_FAILURE = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);
    public static final String CMD_LINE_SYNTAX = "java -jar client.jar";
    private CommandLineParser commandLineParser;
    private CommandLineProcessor commandLineProcessor;
    private Options options;
    private Logger loggerNonStatique;

    @Autowired
    public ClientApplication(
        Options options,
        CommandLineParser commandLineParser,
        CommandLineProcessor commandLineProcessor,
        @Qualifier("clientApplicationLogger") Logger logger
    ) {
        this.options = options;
        this.commandLineParser = commandLineParser;
        this.commandLineProcessor = commandLineProcessor;
        this.loggerNonStatique = logger;
    }

    public static void main(String[] args) {
        try {
            SpringApplication clientApplication = new SpringApplication(ClientApplication.class);
            clientApplication.setAddCommandLineProperties(false);
            clientApplication.setWebApplicationType(WebApplicationType.NONE);

            String[] cleanedArgs = client.cli.CommandLine.cleanArguments(args);
            clientApplication.run(cleanedArgs);
        } catch (Exception ex) {
            LOGGER.error("Technical Error : {}", ex.getMessage());
            System.exit(EXIT_FAILURE);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (args.length == 0) {
                throw new ParseException("Empty arguments.");
            } else {
                CommandLine commandLine = commandLineParser.parse(options, args);
                commandLineProcessor.process(commandLine);
            }

        } catch (DataSourceBadResponseException ds) {
            LOGGER.error(ds.getMessage());

        } catch (ParseException parse) {
            loggerNonStatique.info(getHelpFormatted());
            throw parse;
        }
    }

    private String getHelpFormatted() {
        HelpFormatter formatter = new HelpFormatter();

        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);

        formatter.printHelp(
            printWriter,
            80,
            CMD_LINE_SYNTAX,
            "",
            options,
            formatter.getLeftPadding(),
            formatter.getDescPadding(),
            "",
            true
        );

        printWriter.flush();

        return out.toString();
    }
}