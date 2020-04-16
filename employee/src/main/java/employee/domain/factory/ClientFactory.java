package employee.domain.factory;

import employee.domain.model.Client;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    public Client create(String name) {
        Client client = new Client();
        client.setName(name);
        client.setProducts(new ArrayList<>());
        return client;
    }
}
