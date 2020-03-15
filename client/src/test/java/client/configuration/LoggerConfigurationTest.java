package client.configuration;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.*;

public class LoggerConfigurationTest {

    private LoggerConfiguration testedClass;

    @Before
    public void setUp() {
        testedClass = new LoggerConfiguration();
    }

    @Test
    public void whenGettingCommandLineProcessorLogger_thenLoggerMustBeReturned() {

        Logger result = testedClass.commandLineProcessorLogger();

        assertNotNull(result);
    }

    @Test
    public void whenGettingClientApplicationLogger_thenLoggerMustBeReturned() {

        Logger result = testedClass.clientApplicationLogger();

        assertNotNull(result);
    }
}