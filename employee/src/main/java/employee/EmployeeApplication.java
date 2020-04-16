package employee;

import employee.cli.CommandLineProcessor;
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
public class EmployeeApplication implements CommandLineRunner {

    private static final int EXIT_FAILURE = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeApplication.class);
    public static final String CMD_LINE_SYNTAX = "java -jar employee.jar";
    private CommandLineParser commandLineParser;
    private CommandLineProcessor commandLineProcessor;
    private Options options;
    private Logger logger;

    @Autowired
    public EmployeeApplication(
        Options options,
        CommandLineParser commandLineParser,
        CommandLineProcessor commandLineProcessor,
        @Qualifier("employeeApplicationLogger") Logger logger
    ) {
        this.options = options;
        this.commandLineParser = commandLineParser;
        this.commandLineProcessor = commandLineProcessor;
        this.logger = logger;
    }

    public static void main(String[] args) {
        try {
            SpringApplication employeeApplication = new SpringApplication(EmployeeApplication.class);
            employeeApplication.setAddCommandLineProperties(false);
            employeeApplication.setWebApplicationType(WebApplicationType.NONE);

            String[] cleanedArgs = employee.cli.CommandLine.cleanArguments(args);

            employeeApplication.run(cleanedArgs);

        }
        catch (Exception ex) {
            LOGGER.error("Technical Error : " + ex.getMessage());
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
        } catch (ParseException parse) {
            logger.info(getHelpFormatted());
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



