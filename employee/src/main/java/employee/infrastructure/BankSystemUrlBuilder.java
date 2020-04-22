package employee.infrastructure;

import employee.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BankSystemUrlBuilder {

    private static final String BANK_SERVER_BASE_URL = "${employee.bank.server.base.url}";
    public static final String CLIENT_URL = "/client/";

    private String bankServerBaseUrl;

    @Autowired
    public BankSystemUrlBuilder(@Value(BANK_SERVER_BASE_URL) String bankServerBaseUrl) {
        this.bankServerBaseUrl = bankServerBaseUrl;
    }

    public String buildAddClientUrl() {
        return bankServerBaseUrl + CLIENT_URL;
    }

    public String buildGetProductsUrl(Client client) {
        return bankServerBaseUrl + CLIENT_URL + client.getName() + "/products";
    }

    public String buildUpgradeClientUrl(Client client) {
        return bankServerBaseUrl + CLIENT_URL + client.getName() + "/status/upgrade";
    }

    public String buildDowngradeClientUrl(Client client) {
        return bankServerBaseUrl + CLIENT_URL + client.getName() + "/status/downgrade";
    }

    public String buildAcceptProductUrl(Integer productId, String clientName) {
        return bankServerBaseUrl + CLIENT_URL + clientName + "/product/" + productId + "/accept";
    }

    public String buildRejectProductUrl(Integer productId, String clientName) {
        return bankServerBaseUrl + CLIENT_URL + clientName + "/product/" + productId + "/reject";
    }

    public String buildGetTaskUrl() {
        return bankServerBaseUrl + "/clients/products/waitingapprobation";
    }
}