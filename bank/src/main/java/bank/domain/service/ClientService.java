package bank.domain.service;

import bank.domain.exception.AlreadyExistsException;
import bank.domain.exception.NotFoundException;
import bank.domain.model.Client;
import bank.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Client save(Client client) {

        if (!clientRepository.findById(client.getClientId().getId()).isPresent()) {
            return clientRepository.save(client);
        } else {
            throw new AlreadyExistsException("Client already exists.");
        }
    }
}