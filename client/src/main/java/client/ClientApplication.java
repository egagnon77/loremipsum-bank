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
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner, ExitCodeGenerator {

    private static final int EXIT_PARSE_FAILURE = 2;
    private static final int EXIT_WEB_FAILURE = 3;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);
    public static final String CMD_LINE_SYNTAX = "java -jar client.jar";
    private CommandLineParser commandLineParser;
    private CommandLineProcessor commandLineProcessor;
    private Options options;
    private Logger loggerNonStatique;
    private Integer exitCode = 0;


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
        SpringApplication clientApplication = new SpringApplication(ClientApplication.class);
        clientApplication.setAddCommandLineProperties(false);
        clientApplication.setWebApplicationType(WebApplicationType.NONE);

        String[] cleanedArgs = client.cli.CommandLine.cleanArguments(args);
        clientApplication.setBannerMode(Banner.Mode.OFF);
        clientApplication.setLogStartupInfo(false);

        try {
            System.exit(SpringApplication.exit(clientApplication.run(cleanedArgs)));
        } catch (Exception ex) {
            LOGGER.info("Technical error detected. Check if Bank is started.");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (args.length == 0) {
                loggerNonStatique.error("Empty arguments.");
                loggerNonStatique.info(getHelpFormatted());
                exitCode = EXIT_PARSE_FAILURE;
            } else {
                CommandLine commandLine = commandLineParser.parse(options, args);
                commandLineProcessor.process(commandLine);
            }

        } catch (DataSourceBadResponseException dataEx) {
            loggerNonStatique.error(dataEx.getMessage());
            exitCode = EXIT_WEB_FAILURE;
        } catch (ParseException parse) {
            loggerNonStatique.error(parse.getMessage());
            loggerNonStatique.info(getHelpFormatted());
            exitCode = EXIT_PARSE_FAILURE;
        }

    }

    @Bean
    ClientListener clientListenerBean() {
        return new ClientListener();
    }

    private static class ClientListener {

        @EventListener
        public void exitEvent(ExitCodeEvent event) {
            event.getExitCode();
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


    @Override
    public int getExitCode() {
        return exitCode;
    }
}