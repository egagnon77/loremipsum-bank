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
    private static final String ARGUMENT_PRODUCT_ID = "PRODUCT_ID";
    private static final String ADD_DESCRIPTION = "Crée un client de nom CLIENT_NAME";
    private static final String LIST_DESCRIPTION = "Liste tous les produits du client";
    private static final String UPGRADE_DESCRIPTION = "Augmente le statut du client";
    private static final String DOWNGRADE_DESCRIPTION = "Diminue le statut du client";
    private static final String ACCEPT_DESCRIPTION = "Accepter la mise en place du produit";
    private static final String REJECT_DESCRIPTION = "Rejeter la mise en place du produit";
    private static final String TASKS_DESCRIPTION = "Liste les clients avec des produits en attente de validation";
    private static final String CLIENT_DESCRIPTION = "Le nom du client";

    private static final Option ADD_OPTION = Option.builder().required(false).hasArg(true)
        .longOpt(CliOptions.ADD.getValue()).argName(ARGUMENT_CLIENT_NAME).desc(ADD_DESCRIPTION)
        .build();

    private static final Option LIST_OPTION = Option.builder().required(false).hasArg(true)
        .longOpt(CliOptions.LIST.getValue()).argName(ARGUMENT_CLIENT_NAME)
        .desc(LIST_DESCRIPTION)
        .build();

    private static final Option UPGRADE_OPTION = Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.UPGRADE.getValue()).argName(ARGUMENT_CLIENT_NAME)
            .desc(UPGRADE_DESCRIPTION)
            .build();

    private static final Option DOWNGRADE_OPTION = Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.DOWNGRADE.getValue()).argName(ARGUMENT_CLIENT_NAME)
            .desc(DOWNGRADE_DESCRIPTION)
            .build();

    private static final Option ACCEPT_OPTION = Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.ACCEPT.getValue()).argName(ARGUMENT_PRODUCT_ID)
            .desc(ACCEPT_DESCRIPTION)
            .build();

    private static final Option REJECT_OPTION = Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.REJECT.getValue()).argName(ARGUMENT_PRODUCT_ID)
            .desc(REJECT_DESCRIPTION)
            .build();

    private static final Option CLIENT_OPTION = Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.CLIENT.getValue()).argName(ARGUMENT_CLIENT_NAME)
            .desc(CLIENT_DESCRIPTION)
            .build();

    private static final Option TASKS_OPTION = Option.builder().required(false).hasArg(false)
            .longOpt(CliOptions.TASKS.getValue())
            .desc(TASKS_DESCRIPTION)
            .build();

    private CliConfiguration testedClass;

    @Before
    public void setUp() {
        testedClass = new CliConfiguration();
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsATaskOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(TASKS_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsARejectOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(REJECT_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsAnAcceptOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(ACCEPT_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsAClientOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(CLIENT_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
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
    public void whenGettingOptions_thenOptionsMustContainsAnUpgradeOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(UPGRADE_OPTION)) {
                optionFound = true;
            }
        }
        assertTrue(optionFound);
    }

    @Test
    public void whenGettingOptions_thenOptionsMustContainsADowngradeOption() {

        Options result = testedClass.options();

        boolean optionFound = false;
        for (Option option : result.getOptions()) {
            if (option.equals(DOWNGRADE_OPTION)) {
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
    public void whenGettingOptions_thenOptionAcceptMustContainsAnArgWithTheProductId() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_PRODUCT_ID,
                result.getOption(CliOptions.ACCEPT.getValue()).getArgName());
    }

    @Test
    public void whenGettingOptions_thenOptionRejectMustContainsAnArgWithTheProductId() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_PRODUCT_ID,
                result.getOption(CliOptions.REJECT.getValue()).getArgName());
    }

    @Test
    public void whenGettingOptions_thenOptionClientMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_CLIENT_NAME,
                result.getOption(CliOptions.CLIENT.getValue()).getArgName());
    }

    @Test
    public void whenGettingOptions_thenOptionListMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_CLIENT_NAME,
            result.getOption(CliOptions.ADD.getValue()).getArgName());
    }

    @Test
    public void whenGettingOptions_thenOptionUpgradeMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_CLIENT_NAME,
                result.getOption(CliOptions.UPGRADE.getValue()).getArgName());
    }

    @Test
    public void whenGettingOptions_thenOptionDowngradeMustContainsAnArgWithTheClientName() {

        Options result = testedClass.options();

        assertEquals(ARGUMENT_CLIENT_NAME,
                result.getOption(CliOptions.DOWNGRADE.getValue()).getArgName());
    }

    @Test
    public void whenGettingCommandLineParser_thenCommandLineParserMustBeReturned() {

        CommandLineParser result = testedClass.commandLineParser();

        assertNotNull(result);
    }
}