package bank.domain.repository;

import bank.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    List<Client> findAll();

    Optional<Client> findById(String id);

    Client save(Client client);
}
