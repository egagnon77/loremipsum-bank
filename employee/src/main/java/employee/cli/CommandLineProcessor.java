package employee.cli;

import employee.domain.model.AddClient;
import employee.domain.service.EmployeeService;
import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommandLineProcessor {

    private EmployeeService employeeService;
    private Logger logger;

    @Autowired
    public CommandLineProcessor(EmployeeService employeeService,
            @Qualifier("commandLineProcessorLogger") Logger logger) {
        this.employeeService = employeeService;
        this.logger = logger;
    }

    public void process(CommandLine commandLine) {
        if (commandLine.hasOption(CliOptions.ADD.getValue())) {
            AddClient addClient = new AddClient();
            addClient.setName(commandLine.getOptionValue(CliOptions.ADD.getValue()));
            try {
                AddClient addedClient =  employeeService.addClient(addClient);
                logger.info("Add Client '{}' Completed.", addedClient.getName());
            } catch (DataSourceBadResponseException e) {
                logger.info(e.getMessage());
            }
        }
    }
}
