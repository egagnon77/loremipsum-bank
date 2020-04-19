package employee.configuration;

import employee.EmployeeApplication;
import employee.cli.CommandLineProcessor;
import employee.infrastructure.WebBankEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfiguration {

    @Bean
    public Logger commandLineProcessorLogger() {
        return LoggerFactory.getLogger(CommandLineProcessor.class);
    }

    @Bean
    public Logger webBankEmployeeLogger() {
        return LoggerFactory.getLogger(WebBankEmployee.class);
    }

    @Bean
    public Logger employeeApplicationLogger() {
        return LoggerFactory.getLogger(EmployeeApplication.class);
    }
}