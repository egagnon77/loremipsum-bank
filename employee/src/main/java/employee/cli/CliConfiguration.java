package employee.cli;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CliConfiguration {

    private static final String ARGUMENT_CLIENT_NAME = "CLIENT_NAME";
    private static final String ARGUMENT_PRODUCT_ID = "PRODUCT_ID";
    private static final String ADD_DESCRIPTION = "Crée un client de nom CLIENT_NAME";
    private static final String LIST_DESCRIPTION = "Liste tous les produits du client";
    private static final String UPGRADE_DESCRIPTION = "Augmente le statut du client";
    private static final String DOWNGRADE_DESCRIPTION = "Diminue le statut du client";
    private static final String ACCEPT_DESCRIPTION = "Accepter la mise en place du produit";
    private static final String CLIENT_DESCRIPTION = "Le nom du client";

    @Bean
    public Options options() {
        Options options = new Options();

        options.addOption(Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.ADD.getValue()).argName(ARGUMENT_CLIENT_NAME)
            .desc(ADD_DESCRIPTION)
            .build());

        options.addOption(Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.LIST.getValue()).argName(ARGUMENT_CLIENT_NAME)
            .desc(LIST_DESCRIPTION)
            .build());

        options.addOption(Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.UPGRADE.getValue()).argName(ARGUMENT_CLIENT_NAME)
            .desc(UPGRADE_DESCRIPTION)
            .build());

        options.addOption(Option.builder().required(false).hasArg(true)
            .longOpt(CliOptions.DOWNGRADE.getValue()).argName(ARGUMENT_CLIENT_NAME)
            .desc(DOWNGRADE_DESCRIPTION)
            .build());

        options.addOption(Option.builder().required(false).hasArg(true)
                .longOpt(CliOptions.ACCEPT.getValue()).argName(ARGUMENT_PRODUCT_ID)
                .desc(ACCEPT_DESCRIPTION)
                .build());

        options.addOption(Option.builder().required(false).hasArg(true)
                .longOpt(CliOptions.CLIENT.getValue()).argName(ARGUMENT_CLIENT_NAME)
                .desc(CLIENT_DESCRIPTION)
                .build());

        return options;
    }

    @Bean
    public CommandLineParser commandLineParser() {
        return new DefaultParser();
    }


}
