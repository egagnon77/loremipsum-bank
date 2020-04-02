package client.cli;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CliConfigurationTest {

    private static final String ARG_CLIENT_NAME = "CLIENT_NAME";
    private static final String LIST_DESCRIPTION = "Liste tous les produits du client";

    private static final Option CLIENT_NAME_OPTION = Option.builder(CliOptionsValue.Name.getValue()).argName(ARG_CLIENT_NAME).required(true).hasArg(true).build();
    private static final Option STATUS_OPTION = Option.builder().longOpt(CliOptions.Status.getValue()).desc(LIST_DESCRIPTION).build();

    private CliConfiguration testedClass;

    @Before
    public void setUp() {
        testedClass = new CliConfiguration();
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(CLIENT_NAME_OPTION, result.getOption(CliOptionsValue.Name.getValue()));
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsAStatusOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(STATUS_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingCommandLineParser_thenCommandLineParserMustBeReturned() {

        CommandLineParser result = testedClass.commandLineParser();

        assertNotNull(result);
    }
}