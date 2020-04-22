package bank.domain.repository;

import bank.domain.model.ApprobationStatus;
import bank.domain.model.Client;
import bank.domain.model.Product;

public interface ClientProductRepository {

    ApprobationStatus findById(Client client, Product product);

    void save(Client client, Product product, ApprobationStatus approbationStatus);

    void deleteById(Client client, Product product);

}