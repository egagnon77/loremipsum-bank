package client.domain.client;

import client.domain.model.Client;
import client.domain.model.Product;

import java.util.List;

public interface BankClient {

    List<Product> getProducts(Client client);
    List<Product> getAvailableProducts(Client client);
    void subscribeProduct(Client client, Integer productId);
    void unSubscribeProduct(Client client, Integer productId);
}
