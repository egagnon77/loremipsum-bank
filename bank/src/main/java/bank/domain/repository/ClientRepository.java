package bank.domain.repository;

import bank.domain.model.Client;

import java.util.Optional;

public interface ClientRepository {

    Optional<Client> findById(String id);

    Client save(Client client);
}
