package client.domain.service;

import client.domain.client.BankClient;
import client.domain.model.Client;
import client.domain.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private BankClient bankClient;

    @Autowired
    public ClientService(BankClient bankClient) {
        this.bankClient = bankClient;
    }

    public List<Product> getProducts(Client client) {
        return bankClient.getProducts(client);
    }

    public List<Product> getAvailableProducts(Client client) {
        return bankClient.getAvailableProducts(client);
    }

    public Product subscribeProduct(Client client, Integer productID) {
        return bankClient.subscribeProduct(client, productID);
    }
}

