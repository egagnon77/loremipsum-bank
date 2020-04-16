package employee.cli;

import employee.cli.exception.CommandLineException;
import org.apache.commons.cli.CommandLine;
import org.springframework.stereotype.Component;

@Component
public class CommandLineValidator {

    private static String NO_OPTION_DETECTED_MESSAGE = "No option detected in the command line.";
    private static String MORE_THAN_ONE_OPTION_MESSAGE = "More than two options detected in the command line.";


    public void process(CommandLine commandLine) {
        if (commandLine.getOptions().length < 1) {
            throw new CommandLineException(NO_OPTION_DETECTED_MESSAGE);
        }

        if (commandLine.getOptions().length > 1) {
            throw new CommandLineException(MORE_THAN_ONE_OPTION_MESSAGE);
        }
    }
}
