package employee.infrastructure;

import employee.domain.model.AddClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MonoBuilder {

    private BankSystemUrlBuilder bankSystemUrlBuilder;
    private WebClient webClient;

    @Autowired
    public MonoBuilder(BankSystemUrlBuilder bankSystemUrlBuilder,
            WebClient webClient) {
        this.bankSystemUrlBuilder = bankSystemUrlBuilder;
        this.webClient = webClient;
    }

    public Mono<AddClient> addClient(AddClient addClient) {
        String postAddUrl = bankSystemUrlBuilder.buildAddClientUrl();

        return webClient.post()
                .uri(postAddUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(addClient)
                .retrieve()
                .bodyToMono(AddClient.class);
    }
}
