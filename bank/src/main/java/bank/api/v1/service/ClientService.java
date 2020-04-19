package bank.api.v1.service;

import bank.api.v1.dto.CreateClient;
import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client get(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return client.get();
        }

        throw new NotFoundException("Client not found.");
    }

    public CreateClient save(CreateClient createClient) {

        Client client = new Client(createClient.getName());

        if (!clientRepository.findById(client.getName()).isPresent()) {
            clientRepository.save(client);
            return createClient;
        } else {
            throw new AlreadyExistsException("Client already exists.");
        }
    }

    public List<Product> getProducts(String id) {
        Client client = get(id);
        return Collections.unmodifiableList(client.getProducts());       
    }

    public Client upgradeStatus(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            client.get().upgradeStatus();
            return clientRepository.save(client.get());
        }

        throw new NotFoundException("Client not found.");
    }

    public Client downgradeStatus(String id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            client.get().downgradeStatus();
            return clientRepository.save(client.get());
        }

        throw new NotFoundException("Client not found.");
    }

    public Client acceptProduct(String clientName, Integer productId) {

        Optional<Client> client = clientRepository.findById(clientName);

        if (client.isPresent()) {
            client.get().acceptProduct(productId);
            return clientRepository.save(client.get());
        }

        throw new NotFoundException("Client not found.");
    }
}