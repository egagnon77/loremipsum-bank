package client.configuration;

import client.ClientApplication;
import client.cli.CommandLineProcessor;
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
    public Logger clientApplicationLogger() {
        return LoggerFactory.getLogger(ClientApplication.class);
    }
}