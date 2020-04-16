package employee.cli;

import employee.cli.exception.CommandLineException;
import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommandLineValidator {

    private Logger logger;

    @Autowired
    public CommandLineValidator(@Qualifier("commandLineProcessorLogger") Logger logger) {

        this.logger = logger;
    }

    public void process(CommandLine commandLine) {
        if (commandLine.getOptions().length < 0) {
            throw new CommandLineException("No option detected in the command line.");
        }

        if (commandLine.getOptions().length > 1) {
            throw new CommandLineException("More than two options detected in the command line.");
        }
    }
}
