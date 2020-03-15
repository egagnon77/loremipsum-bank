package client.domain.service;

import client.domain.client.BankClient;
import client.domain.model.Client;
import client.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
}