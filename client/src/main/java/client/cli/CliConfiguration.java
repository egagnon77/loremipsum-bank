package client.cli;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CliConfiguration {

    private static final String ARG_CLIENT_NAME = "CLIENT_NAME";
    private static final String LIST_DESCRIPTION = "Liste tous les produits du client";
    private static final String AVAILABLE_DESCRIPTION = "Liste tous les produits auquel le client a accès";

    @Bean
    public Options options() {
        Options options = new Options();
        options.addOption(Option.builder(CliOptionsValue.Name.getValue()).argName(ARG_CLIENT_NAME).required(true).hasArg(true).build());
        options.addOption(Option.builder().longOpt(CliOptions.Status.getValue()).desc(LIST_DESCRIPTION).build());

        options.addOption(Option.builder().longOpt(CliOptions.Available.getValue()).desc(AVAILABLE_DESCRIPTION).build());
        return options;
    }

    @Bean
    public CommandLineParser commandLineParser() {
        return new DefaultParser();
    }

}