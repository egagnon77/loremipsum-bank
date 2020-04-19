package employee.infrastructure;

import employee.domain.model.AddClient;
import employee.domain.model.Client;
import employee.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ResponseBuilder {

    private BankSystemUrlBuilder bankSystemUrlBuilder;
    private WebClient webClient;

    @Autowired
    public ResponseBuilder(BankSystemUrlBuilder bankSystemUrlBuilder,
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

    public Flux<Product> listProducts(Client client) {
        String getProductsUrl = bankSystemUrlBuilder.buildGetProductsUrl(client);

        return webClient.get()
            .uri(getProductsUrl)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(Product.class);
    }
}
