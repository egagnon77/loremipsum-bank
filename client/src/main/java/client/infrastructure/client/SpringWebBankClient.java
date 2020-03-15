package client.infrastructure.client;

import client.domain.client.BankClient;
import client.domain.exception.NotFoundException;
import client.domain.model.Client;
import client.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class SpringWebBankClient implements BankClient {

    private BankSystemUrlBuilder bankSystemUrlBuilder;
    private RestTemplate restTemplate;

    @Autowired
    public SpringWebBankClient(BankSystemUrlBuilder bankSystemUrlBuilder,
                               RestTemplate restTemplate) {
        this.bankSystemUrlBuilder = bankSystemUrlBuilder;
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts(Client client) {

        String getProductsUrl = bankSystemUrlBuilder.buildGetProductsUrl(client);

        try {
            ResponseEntity<Product[]> response = restTemplate.getForEntity(getProductsUrl, Product[].class);
            return Arrays.asList(response.getBody());

        } catch (HttpClientErrorException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}