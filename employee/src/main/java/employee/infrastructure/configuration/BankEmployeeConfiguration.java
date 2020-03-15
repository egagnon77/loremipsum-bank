package employee.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BankEmployeeConfiguration {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}
