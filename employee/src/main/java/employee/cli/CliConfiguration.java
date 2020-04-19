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
    private static final String ADD_DESCRIPTION = "Crée un client de nom CLIENT_NAME";
    private static final String LIST_DESCRIPTION = "Liste tous les produits du client";

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

        return options;
    }

    @Bean
    public CommandLineParser commandLineParser() {
        return new DefaultParser();
    }


}
