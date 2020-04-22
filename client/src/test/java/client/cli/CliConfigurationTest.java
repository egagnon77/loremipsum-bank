package client.cli;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CliConfigurationTest {

    private static final String ARG_CLIENT_NAME = "CLIENT_NAME";
    private static final String ARG_PRODUCT_ID = "PRODUCT_ID";
    private static final String LIST_DESCRIPTION = "Liste tous les produits du client";
    private static final String AVAILABLE_DESCRIPTION = "Liste tous les produits auquel le client a accès";
    private static final String SUBSCRIBE_DESCRIPTION = "Souscrit à un produit";

    private static final Option CLIENT_NAME_OPTION = Option.builder(CliOptionsValue.NAME.getValue()).argName(ARG_CLIENT_NAME).required(true).hasArg(true).build();
    private static final Option STATUS_OPTION = Option.builder().longOpt(CliOptions.STATUS.getValue()).desc(LIST_DESCRIPTION).build();
    private static final Option AVAILABLE_OPTION =  Option.builder().longOpt(CliOptions.AVAILABLE.getValue()).desc(AVAILABLE_DESCRIPTION).build();
    private static final Option SUBSCRIBE_OPTION = Option.builder().longOpt(CliOptions.AVAILABLE.getValue()).argName(ARG_PRODUCT_ID).required(true).hasArg(true).desc(SUBSCRIBE_DESCRIPTION).build();
    private CliConfiguration testedClass;

    @Before
    public void setUp() {
        testedClass = new CliConfiguration();
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(CLIENT_NAME_OPTION, result.getOption(CliOptionsValue.NAME.getValue()));
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
    public void whenGettingOptions_thenOptionsMustContainsAAvailableOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(AVAILABLE_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsASubscribeOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(SUBSCRIBE_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsASubcribeOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(SUBSCRIBE_OPTION)) {
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