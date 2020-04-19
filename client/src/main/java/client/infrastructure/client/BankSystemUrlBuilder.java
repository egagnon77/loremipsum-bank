package client.infrastructure.client;

import client.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankSystemUrlBuilder {

    private static final String BANK_SERVER_BASE_URL = "${client.bank.server.base.url}";

    private String bankServerBaseUrl;

    @Autowired
    public BankSystemUrlBuilder(@Value(BANK_SERVER_BASE_URL) String bankServerBaseUrl) {
        this.bankServerBaseUrl = bankServerBaseUrl;
    }

    public String buildGetProductsUrl(Client client) {
        return bankServerBaseUrl + "/client/" + client.getName() + "/products";
    }

    public String buildGetAvailableProductsUrl(Client client) {
        return bankServerBaseUrl + "/client/" + client.getName() + "/products/available";
    }
}