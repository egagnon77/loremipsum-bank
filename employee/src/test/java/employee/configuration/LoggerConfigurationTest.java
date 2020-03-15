package employee.configuration;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

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
    public void whenGettingSpringWebBankClientLogger_thenLoggerMustBeReturned() {

        Logger result = testedClass.webBankEmployeeLogger();

        assertNotNull(result);
    }
}