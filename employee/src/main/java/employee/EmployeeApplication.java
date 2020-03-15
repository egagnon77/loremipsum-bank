package employee;

import employee.cli.CommandLineProcessor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EmployeeApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeApplication.class);
    private static final int EXIT_FAILURE = 1;
    private CommandLineParser commandLineParser;
    private CommandLineProcessor commandLineProcessor;
    private Options options;

    @Autowired
    public EmployeeApplication(Options options,
                               CommandLineParser commandLineParser,
                               CommandLineProcessor commandLineProcessor) {
        this.options = options;
        this.commandLineParser = commandLineParser;
        this.commandLineProcessor = commandLineProcessor;
    }

    public static void main(String[] args) {
        try {
            SpringApplication employeeApplication = new SpringApplication(EmployeeApplication.class);
            employeeApplication.setAddCommandLineProperties(false);
            employeeApplication.setWebApplicationType(WebApplicationType.NONE);

            String[] cleanedArgs = employee.cli.CommandLine.cleanArguments(args);

            employeeApplication.run( cleanedArgs);

        } catch (Exception ex) {
            LOGGER.error("Technical Error during Employee execution : " + ex.getMessage(),
                    ex);
            System.exit(EXIT_FAILURE);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        CommandLine commandLine;
        try {
            if (args.length == 0) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("java -jar employee.jar", options, true);
                return;
            } else {
                commandLine = commandLineParser.parse(options, args);
            }

        } catch (ParseException parse) {
            LOGGER.info(parse.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar employee.jar", options, true);
            return;
        }
        commandLineProcessor.process(commandLine);
    }

}



