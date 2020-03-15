package client.domain.factory;

import client.domain.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ClientFactory {

    public Client create(String name) {
        Client client = new Client();
        client.setName(name);
        client.setProducts(new ArrayList<>());
        return client;
    }
}
