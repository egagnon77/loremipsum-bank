package employee.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;

public class CliConfigurationTest {

    private static final String ARGUMENT_CLIENT_NAME = "CLIENT_NAME";
    private static final String ADD_DESCRIPTION = "Crée un client de nom CLIENT_NAME";
    private static final String LIST_DESCRIPTION = "Liste tous les produits du client";

    private static final Option ADD_OPTION = Option.builder().required(false).hasArg(true)
        .longOpt(CliOptions.ADD.getValue()).argName(ARGUMENT_CLIENT_NAME).desc(ADD_DESCRIPTION)
        .build();

    private static final Option LIST_OPTION = Option.builder().required(false).hasArg(true)
        .longOpt(CliOptions.LIST.getValue()).argName(ARGUMENT_CLIENT_NAME)
        .desc(LIST_DESCRIPTION)
        .build();

    private CliConfiguration testedClass;

    @Before
    public void setUp() {
        testedClass = new CliConfiguration();
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsAnAddOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(ADD_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsAListOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(LIST_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionAddMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_CLIENT_NAME,
            result.getOption(CliOptions.ADD.getValue()).getArgName());
    }

    @Test
    public void whenGettingOptions_thenOptionListMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_CLIENT_NAME,
            result.getOption(CliOptions.ADD.getValue()).getArgName());
    }

    @Test
    public void whenGettingCommandLineParser_thenCommandLineParserMustBeReturned() {

        CommandLineParser result = testedClass.commandLineParser();

        assertNotNull(result);
    }
}