package bank.domain.repository;

import bank.domain.model.Product;
import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
}
