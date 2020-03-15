package client.domain.client;

import client.domain.model.Client;
import client.domain.model.Product;

import java.util.List;

public interface BankClient {

    List<Product> getProducts(Client client);
}
